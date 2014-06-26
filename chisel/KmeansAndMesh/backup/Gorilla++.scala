package Tutorial

import Chisel._
import Node._
import Literal._
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer
class ioDistributer[T <: Data](n: Int)(data: => T) extends Bundle {
  val out  = Vec(n) { (new FIFOIO()) { data } }
  val in = (new FIFOIO()) { data }.flip
  val chosen = Bits(OUTPUT, log2Up(n))
}

class RRDistributer[T <: Data] (n: Int) (data: => T) extends Component {
  val io = new ioDistributer(n)(data)
  val last_grant = Reg(resetVal = Bits(0, log2Up(n)))
  val g = ArbiterCtrl((0 until n).map(i => io.out(i).ready && UFix(i) > last_grant) ++ io.out.map(_.ready))
  val grant = (0 until n).map(i => g(i) && UFix(i) > last_grant || g(i+n))
  (0 until n).map(i => io.out(i).valid := grant(i) && io.in.valid)

  var choose = Bits(n-1)
  for (i <- n-2 to 0 by -1)
    choose = Mux(io.out(i).ready, Bits(i), choose)
  for (i <- n-1 to 1 by -1)
    choose = Mux(io.out(i).ready && UFix(i) > last_grant, Bits(i), choose)
  when (io.in.valid && io.in.ready) {
    last_grant := choose
  }

  (0 until n).map(i =>  io.out(i).bits := io.in.bits)
  io.in.ready := io.out.map(_.ready).foldLeft(Bool(false))( _ || _)
  io.chosen := choose
}

class MyFIFOIO[T <: Data]()(data: () => T) extends Bundle
{
  val ready = Bool(INPUT)
  val valid = Bool(OUTPUT)
  val bits  = data().asOutput
  def fire(dummy: Int = 0) = ready && valid
}

class MyFIFOIOND[T <: Data]()(data: () => T) extends Bundle
{
  val ready = Bool()
  val valid = Bool()
  val bits  = data()
  def fire(dummy: Int = 0) = ready && valid
}

class gInOutBundle[inT <: Data, outT <: Data]  (inData: () => inT, outData: () => outT) extends Bundle {
  val in = (new MyFIFOIO()) {inData}.flip
  val out = (new MyFIFOIO()) {outData}
  val pcIn = (new PipeIO()) {new PcBundle}.flip
  val pcOut = (new PipeIO()) {new PcBundle}
  //val off = Bundle(ArrayBuffer())
  override def clone = { new gInOutBundle(inData, outData).asInstanceOf[this.type]; }
}

class gOffBundle[inT <: Data, outT <: Data]  (reqData: () => inT, repData: () => outT) extends Bundle {
  val req = (new MyFIFOIO()) {reqData}
  val rep = (new MyFIFOIO()) {repData}.flip
  override def clone = { new gOffBundle(reqData, repData).asInstanceOf[this.type]; }
}

//offBundle type without direction
class gOffBundleND[inT <: Data, outT <: Data]  (reqData: () => inT, repData: () => outT) extends Bundle {
  val req = (new MyFIFOIOND()) {reqData}
  val rep = (new MyFIFOIOND()) {repData}
  override def clone = { new gOffBundle(reqData, repData).asInstanceOf[this.type]; }
}

class gComponentBase[inT <: Data, outT <: Data] (inData: () => inT) (outData: () => outT) extends Component {
  val io = new gInOutBundle (inData, outData);
  override def clone = { new gComponentBase(inData) (outData).asInstanceOf[this.type]; }
}

class PcBundle extends Bundle {
  val request = Bool(OUTPUT)
  val moduleId = UFix(OUTPUT, 16)
  val portId = UFix(OUTPUT, 8)
  val pcValue = UFix(OUTPUT, Pcounters.PCWIDTH)
  val pcType = UFix(OUTPUT, 4)
}

class gComponentLeaf[inT <: Data, outT <: Data] (inData: () => inT) (outData: () => outT) (offloadData: ArrayBuffer[(String, () => Data, () => Data)]) extends gComponent(inData) (outData) (offloadData) {
  val pcOutValid = Reg(resetVal=Bool(false))
  val pcOutRequest = Reg(resetVal=Bool(true))
  val pcOutModuleId = Reg(resetVal=UFix(0, 16))
  val pcOutPortId = Reg(resetVal=UFix(0, 8))
  val pcOutPcValue = Reg(resetVal=UFix(0, Pcounters.PCWIDTH))
  val pcOutPcType = Reg(resetVal=UFix(0, 4))
  when (io.pcIn.valid && io.pcIn.bits.moduleId === UFix(moduleId,16) && io.pcIn.bits.request) {
    pcOutValid := Bool(true)
    pcOutPcValue := pcMuxed
    pcOutValid := io.pcIn.valid
    pcOutRequest := Bool(false) 
    pcOutPcType := io.pcIn.bits.pcType
    pcOutModuleId := io.pcIn.bits.moduleId
    pcOutPortId := io.pcIn.bits.portId
  } .otherwise {
    pcOutValid := io.pcIn.valid
    pcOutPcValue := io.pcIn.bits.pcValue
    pcOutRequest := io.pcIn.bits.request 
    pcOutPcType := io.pcIn.bits.pcType
    pcOutModuleId := io.pcIn.bits.moduleId
    pcOutPortId := io.pcIn.bits.portId
  }
  io.pcOut.valid := pcOutValid
  io.pcOut.bits.request :=    pcOutRequest 
  io.pcOut.bits.moduleId := pcOutModuleId
  io.pcOut.bits.portId := pcOutPortId
  io.pcOut.bits.pcValue := pcOutPcValue
  io.pcOut.bits.pcType := pcOutPcType
}

class gComponent[inT <: Data, outT <: Data] (inData: () => inT) (outData: () => outT) (offloadData: ArrayBuffer[(String, () => Data, () => Data)]) extends gComponentBase(inData) (outData) {
  override def clone = { new gComponent(inData) (outData) (offloadData).asInstanceOf[this.type]; }
  val off = Bundle(offloadData.map((t) => (t._1, {val tt=new gOffBundle(t._2, t._3); tt.name=t._1; tt})))
  off.name = "off"
  io += off
  val inPcBackPressure = Reg(resetVal = UFix(0, Pcounters.PCWIDTH))
  val outPcBackPressure = Reg(resetVal = UFix(0, Pcounters.PCWIDTH))
  println("Num of offload ports " + offloadData.size)
  val offPcBackPressure = Vec(offloadData.size) {Reg(resetVal = UFix(0, Pcounters.PCWIDTH))}
  when (io.pcIn.bits.request && io.pcIn.bits.pcType === Pcounters.pcReset) {
    inPcBackPressure := UFix(0, Pcounters.PCWIDTH)
  } .elsewhen (io.in.valid && !io.in.ready) {
    inPcBackPressure := inPcBackPressure + UFix(1, Pcounters.PCWIDTH)
  }
  when (io.pcIn.bits.request && io.pcIn.bits.pcType === Pcounters.pcReset) {
    outPcBackPressure := UFix(0, Pcounters.PCWIDTH)
  } .elsewhen (io.out.valid && !io.out.ready) {
    outPcBackPressure := outPcBackPressure + UFix(1, Pcounters.PCWIDTH)
  }

  val elseV = ("nullOff", new gOffBundle(() => UFix(32), () => UFix(32)))
  def  IOOff = io.elements.find(_._1 == "off").getOrElse(elseV)._2.asInstanceOf[Bundle]
  var portId : Int = 1; //leave zero for broadcast 
  var pcMuxed = UFix(0, Pcounters.PCWIDTH)  
  val moduleId = Pcounters.registerModule()
  Pcounters.registerPC("in", Pcounters.backPressure, moduleId.asInstanceOf[Int], portId) 
  pcMuxed = Mux(io.pcIn.bits.portId === UFix(portId, Pcounters.PCWIDTH), inPcBackPressure, pcMuxed) 
  portId = portId + 1
  Pcounters.registerPC("out", Pcounters.backPressure, moduleId.asInstanceOf[Int], portId) 
  pcMuxed = Mux(io.pcIn.bits.portId === UFix(portId, Pcounters.PCWIDTH), outPcBackPressure, pcMuxed) 
  portId = portId + 1
  for ((n, i) <- IOOff.elements) {
    Pcounters.registerPC(n, Pcounters.backPressure, moduleId.asInstanceOf[Int], portId) 
    when (io.pcIn.bits.request && io.pcIn.bits.pcType === Pcounters.pcReset) {
      offPcBackPressure(portId-3) := UFix(0, Pcounters.PCWIDTH)
    } .elsewhen (i.asInstanceOf[gOffBundle[Bundle, Bundle]].req.valid && ! i.asInstanceOf[gOffBundle[Bundle, Bundle]].req.ready) {
      offPcBackPressure(portId-3) := offPcBackPressure(portId-3) + UFix(1, Pcounters.PCWIDTH)
    }
    //Mux the pcounter based on the index
    pcMuxed = Mux(io.pcIn.bits.portId === UFix(portId, Pcounters.PCWIDTH), offPcBackPressure(portId-3), pcMuxed) 
    portId = portId + 1
  }
}

class gComponentMD[inT <: Data, outT <: Data] (inData:  () => inT,  outData:  () => outT, offloadData : ArrayBuffer[(String, () => Data, () => Data)]) {
 val inDataGen = inData 
 val outDataGen = outData 
 val offloadDataGen =  offloadData 
 override def clone = { new gComponentMD(inData, outData, offloadData).asInstanceOf[this.type]; }
}

class gChainedComponent [inT <: Data, connT <: Data, outT <: Data] (inDataSource : () => inT) (outDataSource : () => connT) (inDataSink : () => connT) (outDataSink: () => outT) (offloadData: ArrayBuffer[(String,() => Data, () => Data)])  (sourceGen: () => gComponent[inT, connT]) (sinkGen: () => gComponent[connT, outT]) extends gComponent(inDataSource) (outDataSink) (offloadData) with GorillaUtil{
  val source = sourceGen()
  val sink = sinkGen()
  //override val off = Bundle((offloadData.map((t) => (t._1, new gOffBundle(t._2, t._3)))))
  //off.name = "off"
  //io += off
 
  io.in <> source.io.in
  io.out <> sink.io.out
  source.io.out <> sink.io.in
  //Connect the offload interfaces of offloaded compoenent to the enclosing component's offload interfaces
  //val elseV = ("nullOff", new gOffBundle(() => UFix(32), () => UFix(32)))
  //def  IOOff = io.elements.find(_._1 == "off").getOrElse(elseV)._2.asInstanceOf[Bundle]
  def  sourceOff = source.io.elements.find(_._1 == "off").getOrElse(elseV)._2.asInstanceOf[Bundle]
  def  sinkOff = sink.io.elements.find(_._1 == "off").getOrElse(elseV)._2.asInstanceOf[Bundle]
    
  for ((n, i) <- IOOff.elements) {
    for ((n1, i1) <- sinkOff.elements) {
      if (n == n1) {
        i1.asInstanceOf[gOffBundle[Bundle, Bundle]].req <> i.asInstanceOf[gOffBundle[Bundle, Bundle]].req
        i1.asInstanceOf[gOffBundle[Bundle, Bundle]].rep <> i.asInstanceOf[gOffBundle[Bundle, Bundle]].rep
      }
    }
  }
  for ((n, i) <- IOOff.elements) {
    for ((n1, i1) <- sourceOff.elements) {
      if (n == n1) {
        i1.asInstanceOf[gOffBundle[Bundle, Bundle]].req <> i.asInstanceOf[gOffBundle[Bundle, Bundle]].req
        i1.asInstanceOf[gOffBundle[Bundle, Bundle]].rep <> i.asInstanceOf[gOffBundle[Bundle, Bundle]].rep
      }
    }
  }
  //attach the performance counter interfaces
  io.pcIn <> source.io.pcIn
  io.pcOut <> sink.io.pcOut
  source.io.pcOut <> sink.io.pcIn
}

class gOffloadedComponent [inT <: Data, outT <: Data, inOffT <: Data, outOffT <: Data] (inData: () => inT) (outData: () => outT) (inOff : () => inOffT) (outOff: () => outOffT) (offData: ArrayBuffer[(String, () => Data, () => Data)]) (mainGen: () => gComponent[inT, outT]) (offGen: () => gComponent[inOffT, outOffT]) (offPort: String) extends gComponent(inData)(outData)(offData) with GorillaUtil {
  //println("This is gOffloadedComponent, offPort is " , offPort)
  val mainComp = mainGen()
  val offComp = offGen()
  //val (left, right) = offloadDataMain.span( _._1 == offPort) 
  //if (left.isEmpty) println("Gorilla++ Error: no offloaded port " + offPort + " in module")
  //val restOfOffloadedDataMain = { if (left.isEmpty) right else left.init ++ right} 
  //val offoff = Bundle((mergeOffloads(restOfOffloadedDataMain, offloadDataOff).map((t) => (t._1, t._2()))))
  //offoff.name = "off"
  //io +=  offoff

  io.in <> mainComp.io.in
  io.out <> mainComp.io.out

  //val elseV = ("nullOff", new gOffBundle(() => UFix(32), () => UFix(32)))
  def  mainOff = mainComp.io.elements.find(_._1 == "off").getOrElse(elseV)._2.asInstanceOf[Bundle]
  //def  IOOff = io.elements.find(_._1 == "off").getOrElse(elseV)._2.asInstanceOf[Bundle]
  def  offOff = offComp.io.elements.find(_._1 == "off").getOrElse(elseV)._2.asInstanceOf[Bundle]
  //Connect main component offload interface with argument port name to in/out of the offload component
  //Connect the rest of main component offload interfaces to the enclosing component's offload interfaces 
  for ((n, i) <- mainOff.elements) {
    println("offload name is " + n)
    if (n == offPort && i.isInstanceOf[gOffBundle[inOffT, outOffT]]) {
      i.asInstanceOf[gOffBundle[inOffT, outOffT]].req <> offComp.io.in
      i.asInstanceOf[gOffBundle[inOffT, outOffT]].rep <> offComp.io.out
    } else if (i.isInstanceOf[gOffBundle[Bundle, Bundle]]) {
      for ((n1, i1) <- IOOff.elements) {
        if (n == n1) {
          i1.asInstanceOf[gOffBundle[Bundle, Bundle]].req <> i.asInstanceOf[gOffBundle[Bundle, Bundle]].req
          i1.asInstanceOf[gOffBundle[Bundle, Bundle]].rep <> i.asInstanceOf[gOffBundle[Bundle, Bundle]].rep
        }
      }
    }
  }
  //Connect the offload interfaces of offloaded compoenent to the enclosing component's offload interfaces
  for ((n, i) <- offOff.elements) {
    for ((n1, i1) <- IOOff.elements) {
      if (n == n1) {
        i1.asInstanceOf[gOffBundle[Bundle, Bundle]].req <> i.asInstanceOf[gOffBundle[Bundle, Bundle]].req
        i1.asInstanceOf[gOffBundle[Bundle, Bundle]].rep <> i.asInstanceOf[gOffBundle[Bundle, Bundle]].rep
      }
    }
  }
  //attache the performance counter interfaces
  io.pcIn <> mainComp.io.pcIn
  io.pcOut <> offComp.io.pcOut
  mainComp.io.pcOut <> offComp.io.pcIn
}

class gReplicatedComponent [inT <: Data, outT <: Data] (inData : () => inT) (outData : () => outT)  (offloadData: ArrayBuffer[(String,() => Data, () => Data)]) (componentGen: () => gComponent[inT, outT]) (n: Int) extends gComponent(inData) (outData) (offloadData) with GorillaUtil{
  //val off = Bundle((offloadData.map((t) => (t._1, new gOffBundle(t._2, t._3)))))
  //off.name = "off"
  //io += off

  val components = Range(0, n).map(i => componentGen())  
  val inputDist = (new RRDistributer(n)) {inData()}
  val outputArb = (new RRArbiter(n)) {outData()}
  val reqArbs = offloadData.map(i => (new RRArbiter(n)) {i._2()}) 
  val repDists = offloadData.map(i => (new RRDistributer(n)) {i._3()}) 
  //offloadData.map(i => Range(0, n-1).map(reqArbs(i).in(j) := components(j) 
  io.in <> inputDist.io.in
  Range(0, n).foreach(i => inputDist.io.out(i) <> components(i).io.in)
  io.out <> outputArb.io.out  
  Range(0, n).foreach(i => outputArb.io.in(i) <> components(i).io.out)
  var i = 0
  for (j <- 0 until n) {
    i = 0
    val elseV = ("nullOff", new gOffBundle(() => UFix(32), () => UFix(32)))
    def  cOff = components(j).io.elements.find(_._1 == "off").getOrElse(elseV)._2.asInstanceOf[Bundle]
    //Chain the performance counter interfaces
    if (j >0) {
      def  pcIn = components(j).io.elements.find(_._1 == "pcIn").getOrElse(elseV)._2.asInstanceOf[Bundle]
      def  pcOutPrevious = components(j-1).io.elements.find(_._1 == "pcOut").getOrElse(elseV)._2.asInstanceOf[Bundle]
      pcIn <> pcOutPrevious
    }
    for ((name, interface) <- cOff.elements) {
      if (interface.isInstanceOf[gOffBundle[Bundle, Bundle]]) {
        interface.asInstanceOf[gOffBundle[Bundle, Bundle]].req <> reqArbs(i).io.in(j)
        interface.asInstanceOf[gOffBundle[Bundle, Bundle]].rep <> repDists(i).io.out(j)
      }
      i = i + 1
    }
  }
  //Attached the first component pc input to the main pc input
  def  pcIn0 = components(0).io.elements.find(_._1 == "pcIn").getOrElse(elseV)._2.asInstanceOf[Bundle]
  io.pcIn <> pcIn0 
  //Attached the last component pc out to the main pc output 
  def  pcOutN = components(n-1).io.elements.find(_._1 == "pcOut").getOrElse(elseV)._2.asInstanceOf[Bundle]
  io.pcIn <> pcOutN 

  i = 0
  //val elseV = ("nullOff", new gOffBundle(() => UFix(32), () => UFix(32)))
  def  cOff = io.elements.find(_._1 == "off").getOrElse(elseV)._2.asInstanceOf[Bundle]

  for ((name, interface) <- cOff.elements) {
    interface.asInstanceOf[gOffBundle[Bundle, Bundle]].req  <> reqArbs(i).io.out
    interface.asInstanceOf[gOffBundle[Bundle, Bundle]].rep  <> repDists(i).io.in
    i = i + 1
  }
}

class PcElement(myName: String, myPCType: UFix, myModuleId: Int, myPortId: Int) {
  val name = myName 
  val pcType = myPCType 
  val ModuleId = myModuleId 
  val PortId = myPortId 
  //Following is useless
  var pcValue : Int = 0
  def setValue(v: Int) {
    pcValue = v;
  } 
}



object Pcounters {
  val PCWIDTH = 16;
  val nopc::backPressure::pcReset::Nil = Enum(3){UFix(width = 2)}
  var moduleId : Int = 1 //leave zero for broadcast 
  var elements = new ArrayBuffer[PcElement]() ;
  def registerPC(name: String, pcType: UFix, moduleId: Int, portId: Int) {
    elements = elements + new PcElement(name, pcType, moduleId, portId) 
  }
  def registerModule() = { moduleId = moduleId +1; moduleId-1} 
}

trait GorillaUtil {
  def mergeOffloads(offloadData1: ArrayBuffer[(String, () => Data, () => Data)], offloadData2: ArrayBuffer[(String, () => Data, () => Data)])={
    val offNames1 = offloadData1.map((t) => t._1).toSet
    val offNames2 = offloadData2.map((t) => t._1).toSet
    if (! offNames1.intersect(offNames2).isEmpty) {
      println("Gorilla++ Error: There is a common name in offload interface names")
    }
    offloadData1 ++ offloadData2 
  }
  def Chain  [inT <: Data, connT <: Data, outT<: Data] (
        a: (gComponentMD[inT, connT], () => gComponent[inT, connT]), 
        b: (gComponentMD[connT, outT], () => gComponent[connT, outT]))={
    val res = 
      () => 
        new gChainedComponent(a._1.inDataGen) (a._1.outDataGen) (b._1.inDataGen) (b._1.outDataGen) (mergeOffloads(a._1.offloadDataGen, b._1.offloadDataGen)) (a._2) (b._2) 
    (new gComponentMD(a._1.inDataGen, b._1.outDataGen, mergeOffloads(a._1.offloadDataGen, b._1.offloadDataGen)), res.asInstanceOf[() => gComponent[Bundle, Bundle]])
  }

  def Offload [inT <: Data, outT <: Data, inOffT<: Data, outOffT<: Data] (
        a: (gComponentMD[inT, outT], () => gComponent[inT, outT]), 
        b: (gComponentMD[inOffT, outOffT], () => gComponent[inOffT, outOffT]), offPort: String)={ 
    val (left, right) = a._1.offloadDataGen.span( _._1 == offPort) 
    val restOfOffloadedDataMain = { if (right.isEmpty) left else left ++ right.tail} 
    val offoff = mergeOffloads(restOfOffloadedDataMain, b._1.offloadDataGen)
    val res = 
      () =>
       new gOffloadedComponent(a._1.inDataGen) (a._1.outDataGen) (b._1.inDataGen) (b._1.outDataGen) (offoff) (a._2) (b._2) (offPort) 

    (new gComponentMD(a._1.inDataGen, a._1.outDataGen, offoff).asInstanceOf[gComponentMD[Data, Data]], res.asInstanceOf[() => gComponent[Data, Data]])
  }

  def Replicate [inT <: Data, outT <: Data, OffT<: Data] (
        a: (gComponentMD[inT, outT], () => gComponent[inT, outT]), 
        n: Int)={ 
    val res = 
      () =>
       new gReplicatedComponent(a._1.inDataGen) (a._1.outDataGen) (a._1.offloadDataGen) (a._2) (n) 

    (new gComponentMD(a._1.inDataGen, a._1.outDataGen, a._1.offloadDataGen).asInstanceOf[gComponentMD[Data, Data]], res.asInstanceOf[() => gComponent[Data, Data]])
  }

}  
