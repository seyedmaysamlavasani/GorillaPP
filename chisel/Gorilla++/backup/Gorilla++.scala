package Tutorial

import Chisel._
import Node._
import Literal._
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer
class ioDistributor[T <: Data](n: Int)(data: => T) extends Bundle {
  val out  = Vec(n) { (new FIFOIO()) { data} }
  val in = (new FIFOIO()) { data }.flip
  val chosen = Bits(OUTPUT, log2Up(n))
}

class RRDistributor[T <: Data] (n: Int) (data: => T) extends Component {
  val io = (new ioDistributor(n)) {data}
  val last_grant = Reg(resetVal = Bits(0, log2Up(n)))
  val g = ArbiterCtrl((0 until n).map(i => io.out(i).ready && UFix(i) > last_grant) ++ io.out.map(_.ready))
  val grant = (0 until n).map(i => g(i) && UFix(i) > last_grant || g(i+n))
  (0 until n).map(i => io.out(i).valid := grant(i) && io.in.valid)
  name_it()
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
    pcOutPcValue := mypcMuxed 
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
  io.pcOut.bits.pcValue := pcOutPcValue //mypcMuxed //inPcBackPressure //pcOutPcValue
  io.pcOut.bits.pcType := pcOutPcType
}

class gComponent[inT <: Data, outT <: Data] (inData: () => inT) (outData: () => outT) (offloadData: ArrayBuffer[(String, () => Data, () => Data)]) extends gComponentBase(inData) (outData) {
  override def clone = { new gComponent(inData) (outData) (offloadData).asInstanceOf[this.type]; }
  val off = Bundle(offloadData.map((t) => (t._1, {val tt=new gOffBundle(t._2, t._3); tt.name=t._1; tt})))
  off.name = "off"
  io += off
  val inPcBackPressure = Reg(resetVal = UFix(0, Pcounters.PCWIDTH))
  val outPcBackPressure = Reg(resetVal = UFix(0, Pcounters.PCWIDTH))
  name_it()
  println("In module " + name + " ,num of offload ports " + offloadData.size)
  offloadData.foreach(x => println("Port name is " + x._1))
  val offPcBackPressure = if (offloadData.size == 0) Vec(1) {UFix(Pcounters.PCWIDTH)}
                          else Vec(offloadData.size) {Reg(resetVal = UFix(0, Pcounters.PCWIDTH))}
  val elseV = ("nullOff", new gOffBundle(() => UFix(32), () => UFix(32)))
  def  IOOff = io.elements.find(_._1 == "off").getOrElse(elseV)._2.asInstanceOf[Bundle]
  var pcMuxed = UFix(0, Pcounters.PCWIDTH)  
  //println("Component name is " + name )
  val moduleId = Pcounters.registerModule(name)
  var portId : Int = 1; //leave zero for broadcast 
  //Input back pressure pc
  Pcounters.registerPC("in", Pcounters.backPressure, moduleId.asInstanceOf[Int], portId) 
  when (io.pcIn.valid && io.pcIn.bits.request && io.pcIn.bits.pcType === Pcounters.pcReset) {
    inPcBackPressure := UFix(0, Pcounters.PCWIDTH)
  } .elsewhen (io.in.valid && !io.in.ready) {
    inPcBackPressure := inPcBackPressure + UFix(1, Pcounters.PCWIDTH)
  }
  pcMuxed = Mux(io.pcIn.bits.portId === UFix(portId, Pcounters.PCWIDTH), inPcBackPressure, pcMuxed) 
  //Output back pressure pc
  portId = portId + 1
  Pcounters.registerPC("out", Pcounters.backPressure, moduleId.asInstanceOf[Int], portId) 
  when (io.pcIn.valid && io.pcIn.bits.request && io.pcIn.bits.pcType === Pcounters.pcReset) {
    outPcBackPressure := UFix(0, Pcounters.PCWIDTH)
  } .elsewhen (io.out.valid && !io.out.ready) {
    outPcBackPressure := outPcBackPressure + UFix(1, Pcounters.PCWIDTH)
  }
  pcMuxed = Mux(io.pcIn.bits.portId === UFix(portId, Pcounters.PCWIDTH), outPcBackPressure, pcMuxed) 
  portId = portId + 1
  val mypcMuxed = Mux(io.pcIn.bits.portId === UFix(1, Pcounters.PCWIDTH), inPcBackPressure, outPcBackPressure) 
  //Offload backpressure pcs
  for ((n, i) <- IOOff.elements) {
    Pcounters.registerPC(n, Pcounters.backPressure, moduleId.asInstanceOf[Int], portId) 
    when (io.pcIn.valid && io.pcIn.bits.request && io.pcIn.bits.pcType === Pcounters.pcReset) {
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
  if (source.parent != this) {
    source.parent.children -= source
    source.parent = this;  
  }
  if (!this.children.contains(source))
    this.children += source 
  if (sink.parent != this) {
    sink.parent.children -= sink
    sink.parent = this;  
  }
  if (!this.children.contains(sink))
    this.children += sink 
  println("In gChained")
  printChildren(this)

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
  if (mainComp.parent != this) {
    mainComp.parent.children -= mainComp
    mainComp.parent = this;  
  }
  if (!this.children.contains(mainComp))
    this.children += mainComp
  val offComp = offGen()
  if (offComp.parent != this) {
    offComp.parent.children -= offComp
    offComp.parent = this;  
  }
  if (!this.children.contains(offComp))
    this.children += offComp
  println("In gOffloaded")
  printChildren(this)

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

class distributorComponent [T <: Data] (n: Int, data: () => T) extends Component { 
  val io = (new ioDistributor(n)) {data()} 
}

class RRDistributorComponent [T <: Data] (n: Int, data: () => T) extends distributorComponent (n, data) {
  val rrDist = (new RRDistributor(n)) {data()}
  name_it()
  io <> rrDist.io
}

class distributorEngine [T <: Data] (n: Int, data: () => T) extends Component { 
  val io =  new Bundle {
    val out  = (new FIFOIO()) {data() } 
    val in = (new FIFOIO()) {data()}.flip
    val outIndex = Bits(OUTPUT, log2Up(n))
  }
}

class aggregatorComponent [T <: Data] (n: Int, data: () => T) extends Component { 
  val io = new ioArbiter(n)(data()) 
}

class RRAggregatorComponent [T <: Data] (n: Int, data: () => T) extends aggregatorComponent (n, data) {
  val rrArb = (new RRArbiter(n)) {data()}
  rrArb.name_it()
  name_it()
  io <> rrArb.io
}



class aggregatorEngine [T <: Data] (n: Int, data: => T) extends Component { 
  val io =  new Bundle {
    val out  = (new FIFOIO()) {data }.flip 
    val in = (new FIFOIO()) { data }
    val inIndex = Bits(OUTPUT, log2Up(n))
  }
}

class pDistributor [T <: Data] (n: Int) (data: () => T) (distributorEngineGen: (Int, => T) => distributorEngine [T]) extends distributorComponent[T] (n, data) with GorillaUtil {
  val distEngine = distributorEngineGen(n, {data()})
  val readies = Vec(n) {Bool()}

  io.in <> distEngine.io.in
  val broadcast = 
    distEngine.io.outIndex === broadcastDistribute  && io.out.map(x => x.ready).reduce(_&&_) && distEngine.io.out.valid

  for (i <- 0 to n) {
    io.out(i).valid := Mux(broadcast, Bool(true), UFix(i) === distEngine.io.outIndex && distEngine.io.out.valid) 
    io.out(i).bits <> io.in.bits
    readies(i) := io.out(i).ready && UFix(i) === distEngine.io.outIndex
  }
  distEngine.io.out.ready := broadcast || readies.reduce(_&&_) 
  io.in.ready := distEngine.io.in.ready
}

class pAggregrator [T <: Data] (n: Int) (data: ()  => T) (aggregatorEngineGen: (Int, => T) => aggregatorEngine [T]) extends aggregatorComponent(n, data)  with GorillaUtil {
  val aggEngine = aggregatorEngineGen(n, {data()})
  val rrArb = (new RRArbiter(n)) {data()}   //TODO: if aggregator wants an specific input RR should be off 
  io.in <> rrArb.io.in
  rrArb.io.out <> aggEngine.io.in 
  io.out <> aggEngine.io.out 
}

//class gReplicatedComponentDistAgg [inT <: Data, outT <: Data] (inData : () => inT) (outData : () => outT)  (offloadData: ArrayBuffer[(String,() => Data, () => Data)]) (componentGen: () => gComponent[inT, outT]) (n: Int) (distributorGen: (Int, () => inT)  => distributorComponent[inT]) (aggregatorGen: (Int, () => outT) => aggregatorComponent[outT]) extends gComponent(inData) (outData) (offloadData) with GorillaUtil{

class gReplicatedComponent [inT <: Data, outT <: Data] (inData :  () => inT) (outData :  () => outT)  (offloadData: ArrayBuffer[(String,() => Data, () => Data)]) (componentGen: () => gComponent[inT, outT]) (n: Int) extends gComponent (inData) (outData) (offloadData) with GorillaUtil{
  val components = Range(0, n).map(i => componentGen())  
  components.foreach(i => { if (i.parent != this) {
                              i.parent.children -= i
    						  i.parent = this  
                            }
  							if (!this.children.contains(i))
    						  this.children += i 
						  }) 
//  val inputDist = distributorGen(n, inData) 
  val inputDist = (new RRDistributorComponent(n, inData)) 
  val outputArb = (new RRAggregatorComponent(n, outData)) 
  outputArb.name_it()
//  val outputArb = aggregatorGen(n, outData) 
  val reqArbs = offloadData.map(i => (new RRArbiter(n)) {i._2()})  
  val repDists = offloadData.map(i => (new RRDistributor(n)) {i._3()}) 
  reqArbs.foreach(i => i.name_it())
  println("In gReplicated")
  printChildren(this)
  //offloadData.map(i => Range(0, n-1).map(reqArbs(i).in(j) := components(j) 
  io.in <> inputDist.io.in
  Range(0, n).foreach(i => inputDist.io.out(i) <> components(i).io.in)
  io.out <> outputArb.io.out  
  Range(0, n).foreach(i => outputArb.io.in(i) <> components(i).io.out)

  var i = 0
  //val elseV = ("nullOff", new gOffBundle(() => UFix(32), () => UFix(32)))
  def  cOff = io.elements.find(_._1 == "off").getOrElse(elseV)._2.asInstanceOf[Bundle]

  for ((name, interface) <- cOff.elements) {
    interface.asInstanceOf[gOffBundle[Bundle, Bundle]].req  <> reqArbs(i).io.out
    interface.asInstanceOf[gOffBundle[Bundle, Bundle]].rep  <> repDists(i).io.in
    i = i + 1
  }

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
  io.pcOut <> pcOutN 

}

//class gReplicatedComponent [inT <: Data, outT <: Data] (inData :  () => inT) (outData :  () => outT)  (offloadData: ArrayBuffer[(String,() => Data, () => Data)]) (componentGen: () => gComponent[inT, outT]) (n: Int) extends gReplicatedComponentDistAgg (inData) (outData) (offloadData) (componentGen) (n) ((n, inData) => {new RRDistributorComponent (n, inData)}) ((n, outData) => {new RRAggregatorComponent(n, outData)})  with GorillaUtil{

//}

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
    println("PCREPORT: PC is registered name: " + name + " type " + pcType.litValue().intValue() 
      + " moduleId " +  moduleId + " portId " + portId)
  }
  def registerModule(name: String) = { 
    println("PCREPORT: module " + name + " registered for pc moduleId is " + moduleId)
    moduleId = moduleId +1; 
    moduleId-1 
  }  
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
  def Chain(
        a: (gComponentMD[Data,  Data], () => gComponent[Data, Data]), 
        b: (gComponentMD[Data, Data], () => gComponent[Data, Data])): (gComponentMD[Data, Data], () => gComponent[Data, Data])={
    val res = 
      () => 
        new gChainedComponent(a._1.inDataGen) (a._1.outDataGen) (b._1.inDataGen) (b._1.outDataGen) (mergeOffloads(a._1.offloadDataGen, b._1.offloadDataGen)) (a._2) (b._2) 
    //(new gComponentMD(a._1.inDataGen, b._1.outDataGen, mergeOffloads(a._1.offloadDataGen, b._1.offloadDataGen)), res.asInstanceOf[() => gComponent[Data, Data]])
    (new gComponentMD(a._1.inDataGen, b._1.outDataGen, mergeOffloads(a._1.offloadDataGen, b._1.offloadDataGen)), res)
  }

 def Chain   (
       a: (gComponentMD[Data, Data], () => gComponent[Data, Data]), 
       b: (gComponentMD[Data, Data], () => gComponent[Data, Data]),
       c: (gComponentMD[Data, Data], () => gComponent[Data, Data])): (gComponentMD[Data, Data], () => gComponent[Data, Data])={
   Chain(Chain(a,b),c)
 }


  def Chain (a: ArrayBuffer[(gComponentMD[Data, Data], () => gComponent[Data, Data])]): (gComponentMD[Data, Data], () => gComponent[Data, Data])={
    a.reduceLeft {(x,y) => Chain(x,y)} 
  }
 
  def Offload [inT <: Data, outT <: Data, inOffT<: Data, outOffT<: Data] (
        a: (gComponentMD[inT, outT], () => gComponent[inT, outT]), 
        b: (gComponentMD[inOffT, outOffT], () => gComponent[inOffT, outOffT]), offPort: String)={ 
    val (left, right) = a._1.offloadDataGen.span( _._1 != offPort) 


    println("Offload called for port " + offPort)
    println("main offlaod ports are")
	a._1.offloadDataGen.foreach(x => println(x._1))
    println("left offlaod ports are")
    left.foreach(x => println(x._1))
    println("right offlaod ports are")
    right.foreach(x => println(x._1))
 
    val restOfOffloadedDataMain = { if (right.isEmpty) left else left ++ right.tail} 
    
    println("in Offload main offPorts a is ")
    a._1.offloadDataGen.foreach(x => println(x._1))
    println("in Offload main offPorts b is ")
    b._1.offloadDataGen.foreach(x => println(x._1))

    val offoff = mergeOffloads(restOfOffloadedDataMain, b._1.offloadDataGen)
    println("merged offlaod ports are")
    offoff.foreach(x => println(x._1))

    val res = 
      () =>
       new gOffloadedComponent(a._1.inDataGen) (a._1.outDataGen) (b._1.inDataGen) (b._1.outDataGen) (offoff) (a._2) (b._2) (offPort) 

    (new gComponentMD(a._1.inDataGen, a._1.outDataGen, offoff).asInstanceOf[gComponentMD[Data, Data]], res.asInstanceOf[() => gComponent[Data, Data]])
  }

  def Offload ( a: (gComponentMD[Data, Data], () => gComponent[Data, Data]), 
        b: ArrayBuffer[((gComponentMD[Data, Data], () => gComponent[Data, Data]), String)]):(gComponentMD[Data, Data], () => gComponent[Data, Data])={ 
    b.foldLeft (a) {(x,y) => Offload(x, y._1, y._2)} 
  }

  def Replicate [inT <: Data, outT <: Data, OffT<: Data] (
        a: (gComponentMD[inT, outT], () => gComponent[inT, outT]), 
        n: Int)={ 
    val res = 
      () =>
       new gReplicatedComponent(a._1.inDataGen) (a._1.outDataGen) (a._1.offloadDataGen) (a._2) (n) 

    (new gComponentMD(a._1.inDataGen, a._1.outDataGen, a._1.offloadDataGen).asInstanceOf[gComponentMD[Data, Data]], res.asInstanceOf[() => gComponent[Data, Data]])
  }

val broadcastDistribute = UFix(255);
  def printChildren( p: Component) {
    p.children.foreach( c => {
                               println("Design hierarchy --- parent " + p.name + " child " + c.name) 
                               printChildren(c)
                             })
                     
  }
}  
