package Tutorial

import Chisel._
import Node._
import Literal._
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer

class gioArbiter[T <: Data](n: Int)(data: => T) extends Bundle {
  val out = (new gFIFOIO()) {data}
  val in  = Vec(n) {(new gFIFOIO()) {data} }.flip
  val chosen = Bits(OUTPUT, log2Up(n))
}

class gioDistributor[T <: Data](n: Int)(data: => T) extends Bundle {
  val out  = Vec(n) { (new gFIFOIO()) {data} }
  val in = (new gFIFOIO()) {data}.flip
  val chosen = Bits(OUTPUT, log2Up(n))
}

class RREncode (n:Int) extends Component {
  val io = new Bundle {
    val valid = Vec(n) {Bool()}.asInput
    val chosen = UFix(OUTPUT, log2Up(n+1))
    val ready = Bool(INPUT)
  }
  val last_grant = Reg(resetVal = UFix(0, log2Up(n)))
  val g = ArbiterCtrl((0 until n).map(i => io.valid(i) && UFix(i) > last_grant) ++ io.valid)
  val grant = (0 until n).map(i => g(i) && UFix(i) > last_grant || g(i+n))

  var choose = UFix(n)
  for (i <- n-1 to 0 by -1)
    choose = Mux(io.valid(i), UFix(i), choose)
  for (i <- n-1 to 1 by -1)
    choose = Mux(io.valid(i) && UFix(i) > last_grant, UFix(i), choose)
  val outValid = io.valid.foldLeft(Bool(false))( _ || _)
  when (outValid && io.ready) {
    last_grant := choose
  }
  io.chosen := choose
}

class gRRArbiter[T <: Data](n: Int)(data: => T) extends Component {
  val io = new gioArbiter(n)(data)

  val last_grant = Reg(resetVal = Bits(0, log2Up(n)))
  val g = ArbiterCtrl((0 until n).map(i => io.in(i).valid && UFix(i) > last_grant) ++ io.in.map(_.valid))
  val grant = (0 until n).map(i => g(i) && UFix(i) > last_grant || g(i+n))
  (0 until n).map(i => io.in(i).ready := grant(i) && io.out.ready)

  var choose = Bits(n-1)
  for (i <- n-2 to 0 by -1)
    choose = Mux(io.in(i).valid, Bits(i), choose)
  for (i <- n-1 to 1 by -1)
    choose = Mux(io.in(i).valid && UFix(i) > last_grant, Bits(i), choose)
  when (io.out.valid && io.out.ready) {
    last_grant := choose
  }

  val dvec = Vec(n) { data } 
  val tvec = Vec(n) { UFix(width=7) } 
  (0 until n).map(i => dvec(i) := io.in(i).bits )
  (0 until n).map(i => tvec(i) := io.in(i).tag )

  io.out.valid := io.in.map(_.valid).foldLeft(Bool(false))( _ || _)
  io.out.bits := dvec(choose)
  io.out.tag := tvec(choose)
  io.chosen := choose
}

class gRRDistributor[T <: Data] (n: Int) (data: => T) extends Component {
  val io = (new gioDistributor(n)) {data}
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
  (0 until n).map(i =>  io.out(i).tag := io.in.tag)
  io.in.ready := io.out.map(_.ready).foldLeft(Bool(false))( _ || _)
  io.chosen := choose
}

class gTaggedRRArbiter[T <: Data](n: Int)(data: => T) extends Component with GorillaUtil {
  val io = new gioArbiter(n)(data)

  val last_grant = Reg(resetVal = Bits(0, log2Up(n)))
  val g = ArbiterCtrl((0 until n).map(i => io.in(i).valid && UFix(i) > last_grant) ++ io.in.map(_.valid))
  val grant = (0 until n).map(i => g(i) && UFix(i) > last_grant || g(i+n))
  (0 until n).map(i => io.in(i).ready := grant(i) && io.out.ready)

  var choose = Bits(n-1)
  for (i <- n-2 to 0 by -1)
    choose = Mux(io.in(i).valid, Bits(i), choose)
  for (i <- n-1 to 1 by -1)
    choose = Mux(io.in(i).valid && UFix(i) > last_grant, Bits(i), choose)
  when (io.out.valid && io.out.ready) {
    last_grant := choose
  }

  val dvec = Vec(n) { data } 
  val tvec = Vec(n) { UFix(width=7) } 
  (0 until n).map(i => dvec(i) := io.in(i).bits )
  (0 until n).map(i => tvec(i) := io.in(i).tag )

  io.out.valid := io.in.map(_.valid).foldLeft(Bool(false))( _ || _)
  io.out.bits := dvec(choose)
  io.out.tag := (choose << UFix(TAGWIDTH)) | tagLower(tvec(choose)) 
  io.chosen := choose
}

class gTaggedDistributor[T <: Data] (n: Int) (data: => T) extends Component with GorillaUtil {
  val io = (new gioDistributor(n)) {data}
  (0 until n).map(i =>  io.out(i).bits := io.in.bits)
  (0 until n).map(i =>  io.out(i).tag := tagLower(io.in.tag))
  (0 until n).map(i =>  io.out(i).valid := io.in.valid && (UFix(i, TAGWIDTH) === tagUpper(io.in.tag)))
  io.in.ready := io.out(tagUpper(io.in.tag)).ready
  io.chosen := tagUpper(io.in.tag) 
}

class gFIFOIO[T <: Data]()(data: => T) extends Bundle with GorillaUtil
{
  val ready = Bool(INPUT)
  val valid = Bool(OUTPUT)
  val bits  = data.asOutput
  val tag = UFix(OUTPUT, 5)
  //val tag = UFix(OUTPUT, width=TAGWIDTH*2)
  def fire(dummy: Int = 0) = ready && valid
  override def clone =
    try {
      super.clone()
    } catch {
      case e: java.lang.Exception => {
        new gFIFOIO()(data).asInstanceOf[this.type]
      }
    }
}

class MyFIFOIO[T <: Data]()(data: () => T) extends Bundle with GorillaUtil
{
  val ready = Bool(INPUT)
  val valid = Bool(OUTPUT)
  val bits  = data().asOutput
  //val tag = UFix(OUTPUT, width=TAGWIDTH*2)
  val tag = UFix(OUTPUT, width=5)
  def fire(dummy: Int = 0) = ready && valid
}

class MyFIFOIOND[T <: Data]()(data: () => T) extends Bundle with GorillaUtil
{
  val ready = Bool()
  val valid = Bool()
  val bits  = data()
  //val tag = UFix(width=TAGWIDTH*2)
  val tag = UFix(width=5)
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

class gComponentLeaf[inT <: Data, outT <: Data] (inData: () => inT) (outData: () => outT) (offloadData: ArrayBuffer[(String, () => Data, () => Data)]) (extCompName: String = "") extends gComponent(inData) (outData) (offloadData) (extCompName=extCompName) {
  val pcOutValid = Reg(resetVal=Bool(false))
  val pcOutRequest = Reg(resetVal=Bool(true))
  val pcOutModuleId = Reg(resetVal=UFix(0, 16))
  val pcOutPortId = Reg(resetVal=UFix(0, 8))
  val pcOutPcValue = Reg(resetVal=UFix(0, Pcounters.PCWIDTH))
  val pcOutPcType = Reg(resetVal=UFix(0, 4))
  when (io.pcIn.valid && io.pcIn.bits.moduleId === UFix(moduleId,16) && io.pcIn.bits.request) {
    pcOutValid := Bool(true)
    pcOutPcValue := pcMuxed 
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
  io.pcOut.bits.pcValue := pcOutPcValue //mypcMuxed //inPCBackPressure //pcOutPcValue
  io.pcOut.bits.pcType := pcOutPcType
}

class gComponent[inT <: Data, outT <: Data] (inData: () => inT) (outData: () => outT) (offloadData: ArrayBuffer[(String, () => Data, () => Data)]) (extCompName : String = "") extends gComponentBase(inData) (outData) {
  override def clone = { new gComponent(inData) (outData) (offloadData)().asInstanceOf[this.type]; }
  val off = Bundle(offloadData.map((t) => (t._1, {val tt=new gOffBundle(t._2, t._3); tt.name=t._1; tt})))
  off.name = "off"
  io += off
  val inPCBackPressure = Reg(resetVal = UFix(0, Pcounters.PCWIDTH))
  def getInPCBackPressure = {inPCBackPressure}
  val outPCBackPressure = Reg(resetVal = UFix(0, Pcounters.PCWIDTH))
  name_it()
  if (extCompName != "") {
    name = extCompName
  }
  println("In module " + name + " ,num of offload ports " + offloadData.size)
  offloadData.foreach(x => println("Port name is " + x._1))
  val offPCBackPressure = if (offloadData.size == 0) Vec(1) {UFix(Pcounters.PCWIDTH)}
                          else Vec(offloadData.size) {Reg(resetVal = UFix(0, Pcounters.PCWIDTH))}

  val offloadRateArray = if (offloadData.size == 0) Vec(1) {UFix(Pcounters.PCWIDTH)}
                          else Vec(offloadData.size) {Reg(resetVal = UFix(0, Pcounters.PCWIDTH))}
  
  val elseV = ("nullOff", new gOffBundle(() => UFix(32), () => UFix(32)))
  def  IOOff = io.elements.find(_._1 == "off").getOrElse(elseV)._2.asInstanceOf[Bundle]
  var pcMuxed = UFix(0, Pcounters.PCWIDTH)  
  //println("Component name is " + name )
  val moduleId = Pcounters.registerModule(name)
  var portId : Int = 1; //leave zero for broadcast 
  val pcPaused = Reg(resetVal=Bool(false))
  val IsPcReset = io.pcIn.valid && io.pcIn.bits.request && io.pcIn.bits.pcType === Pcounters.pcReset
  when (IsPcReset) {
    pcPaused := Bool(false)
  }
  when(io.pcIn.valid && io.pcIn.bits.request && io.pcIn.bits.pcType === Pcounters.pcPause) {
    pcPaused := !pcPaused 
  }
  //Input back pressure pc
  Pcounters.registerPC("in", Pcounters.backPressure, moduleId.asInstanceOf[Int], portId) 
  when (IsPcReset) {
    inPCBackPressure := UFix(0, Pcounters.PCWIDTH)
  } .elsewhen (io.in.valid && !io.in.ready && !pcPaused) {
    inPCBackPressure := inPCBackPressure + UFix(1, Pcounters.PCWIDTH)
  }
  pcMuxed = Mux(io.pcIn.bits.portId === UFix(portId, Pcounters.PCWIDTH) &&
    io.pcIn.bits.pcType === Pcounters.backPressure, inPCBackPressure, pcMuxed) 

  //Output back pressure pc
  portId = portId + 1
  Pcounters.registerPC("out", Pcounters.backPressure, moduleId.asInstanceOf[Int], portId) 
  when (IsPcReset) {
    outPCBackPressure := UFix(0, Pcounters.PCWIDTH)
  } .elsewhen (io.out.valid && !io.out.ready && !pcPaused) {
    outPCBackPressure := outPCBackPressure + UFix(1, Pcounters.PCWIDTH)
  }
  pcMuxed = Mux(io.pcIn.bits.portId === UFix(portId, Pcounters.PCWIDTH) &&
    io.pcIn.bits.pcType === Pcounters.backPressure, outPCBackPressure, pcMuxed) 
  portId = portId + 1

  //Offload backpressure pcs
  for ((n, i) <- IOOff.elements) {
    Pcounters.registerPC(n, Pcounters.backPressure, moduleId.asInstanceOf[Int], portId) 
    when (IsPcReset) {
      offPCBackPressure(portId-3) := UFix(0, Pcounters.PCWIDTH)
    } .elsewhen (i.asInstanceOf[gOffBundle[Bundle, Bundle]].req.valid && !i.asInstanceOf[gOffBundle[Bundle, Bundle]].req.ready && !pcPaused) {
      offPCBackPressure(portId-3) := offPCBackPressure(portId-3) + UFix(1, Pcounters.PCWIDTH)
    }
    //Mux the pcounter based on the index
    pcMuxed = Mux(io.pcIn.bits.portId === UFix(portId, Pcounters.PCWIDTH) && 
      io.pcIn.bits.pcType === Pcounters.backPressure, offPCBackPressure(portId-3), pcMuxed) 
    when (IsPcReset) {
      offloadRateArray(portId-3) := UFix(0, Pcounters.PCWIDTH)
    } .elsewhen (i.asInstanceOf[gOffBundle[Bundle, Bundle]].req.ready && ! i.asInstanceOf[gOffBundle[Bundle, Bundle]].req.valid && !pcPaused) {
      offloadRateArray(portId-3) := offloadRateArray(portId-3) + UFix(1, Pcounters.PCWIDTH)
    }
    portId = portId + 1
  }
  Pcounters.registerPC("offloadRate", Pcounters.offloadRate, moduleId.asInstanceOf[Int], 0)
  val offloadRate = offloadRateArray.reduceLeft((x,y) => Mux(x>y, x, y)) 
  pcMuxed = Mux(io.pcIn.bits.pcType === Pcounters.offloadRate, offloadRate, pcMuxed) 
}

class gComponentMD[inT <: Data, outT <: Data] (inData:  () => inT,  outData:  () => outT, offloadData : ArrayBuffer[(String, () => Data, () => Data)]) {
 val inDataGen = inData 
 val outDataGen = outData 
 val offloadDataGen =  offloadData 
 override def clone = { new gComponentMD(inData, outData, offloadData).asInstanceOf[this.type]; }
}

class gChainedComponent [inT <: Data, connT <: Data, outT <: Data] (inDataSource : () => inT) (outDataSource : () => connT) (inDataSink : () => connT) (outDataSink: () => outT) (offloadData: ArrayBuffer[(String,() => Data, () => Data)])  (sourceGen: () => gComponent[inT, connT]) (sinkGen: () => gComponent[connT, outT]) (extCompName: String) extends gComponent (inDataSource) (outDataSink) (offloadData) (extCompName) with GorillaUtil{
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

class gOffloadedComponent [inT <: Data, outT <: Data, inOffT <: Data, outOffT <: Data] (inData: () => inT) (outData: () => outT) (inOff : () => inOffT) (outOff: () => outOffT) (offData: ArrayBuffer[(String, () => Data, () => Data)]) (mainGen: () => gComponent[inT, outT]) (offGen: () => gComponent[inOffT, outOffT]) (offPort: String) (extCompName: String) extends gComponent(inData)(outData)(offData) (extCompName) with GorillaUtil {
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
  val io = (new gioDistributor(n)) {data()} 
}

class RRDistributorComponent [T <: Data] (n: Int, data: () => T) extends distributorComponent (n, data) {
  val rrDist = (new gRRDistributor(n)) {data()}
  name_it()
  io <> rrDist.io
}

class distributorEngine [T <: Data] (n: Int, data: () => T) extends Component { 
  val io =  new Bundle {
    val out  = (new MyFIFOIO()) {data} 
    val in = (new MyFIFOIO()) {data}.flip
    val outIndex = Bits(OUTPUT, log2Up(n))
  }
}

class aggregatorComponent [T <: Data] (n: Int, data:  () => T) extends Component { 
  val io = (new gioArbiter(n)) {data()} 
}

class RRAggregatorComponent [T <: Data] (n: Int, data: () => T) extends aggregatorComponent (n, data) {
  val rrArb = (new gRRArbiter(n)) {data()}
  rrArb.name_it()
  name_it()
  io <> rrArb.io
}



class aggregatorEngine [T <: Data] (n: Int, data:() => T) extends Component { 
  val io =  new Bundle {
    val out  = (new MyFIFOIO()) {data }.flip 
    val in = (new MyFIFOIO()) { data }
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

//class pAggregrator [T <: Data] (n: Int) (data:  => T) (aggregatorEngineGen: (Int, => T) => aggregatorEngine [T]) extends aggregatorComponent(n, data)  with GorillaUtil {
//  val aggEngine = aggregatorEngineGen(n, {data})
//  val rrArb = (new gRRArbiter(n)) {data}   //TODO: if aggregator wants an specific input RR should be off 
//  io.in <> rrArb.io.in
//  rrArb.io.out <> aggEngine.io.in 
//  io.out <> aggEngine.io.out 
//}

//class gReplicatedComponentDistAgg [inT <: Data, outT <: Data] (inData : () => inT) (outData : () => outT)  (offloadData: ArrayBuffer[(String,() => Data, () => Data)]) (componentGen: () => gComponent[inT, outT]) (n: Int) (distributorGen: (Int, () => inT)  => distributorComponent[inT]) (aggregatorGen: (Int, () => outT) => aggregatorComponent[outT]) extends gComponent(inData) (outData) (offloadData) with GorillaUtil{

class gReplicatedComponent [inT <: Data, outT <: Data] (inData :  () => inT) (outData :  () => outT)  (offloadData: ArrayBuffer[(String,() => Data, () => Data)]) (componentGen: () => gComponent[inT, outT]) (n: Int) (extCompName: String) extends gComponent (inData) (outData) (offloadData) (extCompName) with GorillaUtil{
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
  //val reqArbs = offloadData.map(i => (new gTaggedRRArbiter(n)) {i._2()})  
  val reqArbs = offloadData.map(i => (new gRRArbiter(n)) {i._2()})  
  val repDists = offloadData.map(i => (new gRRDistributor(n)) {i._3()}) 
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
  val moduleId = myModuleId 
  val portId = myPortId 
  var pcValue : Int = 0
}

object Pcounters {
  val PCWIDTH = 20;
  val nopc::backPressure::offloadRate::pcReset::pcPause::Nil = Enum(5){UFix(width = 4)}
  var moduleId : Int = 1 //leave zero for broadcast 
  var elements = new ArrayBuffer[PcElement]() ;
  var moduleIDs = new HashMap[String, Int]();
  def numOfOffloadPorts(moduleName: String) = {
    elements.count(e => e.moduleId == moduleIDs.getOrElse(moduleName, 0) && e.pcType == backPressure)-2 
  }
  def registerPC(name: String, pcType: UFix, moduleId: Int, portId: Int) {
    elements = elements + new PcElement(name, pcType, moduleId, portId) 
    println("PCREPORT: PC is registered name: " + name + " type " + pcType.litValue().intValue() 
      + " moduleId " +  moduleId + " portId " + portId)
  }
  def registerModule(name: String) = { 
    println("PCREPORT: module " + name + " registered for pc moduleId is " + moduleId)
    moduleIDs += ( name -> moduleId)
    moduleId = moduleId +1; 
    moduleId-1 
  }  
}

trait GorillaUtil {
  def mergeOffloads(offloadData1: ArrayBuffer[(String, () => Data, () => Data)], offloadData2: ArrayBuffer[(String, () => Data, () => Data)])={
    val offNames1 = offloadData1.map((t) => t._1).toSet
    val offNames2 = offloadData2.map((t) => t._1).toSet
    if (!offNames1.intersect(offNames2).isEmpty) {
      println("Gorilla++ Error: There is a common name in offload interface names")
    }
    offloadData1 ++ offloadData2 
  }
  def Chain(
        a: (gComponentMD[Data,  Data], () => gComponent[Data, Data]), 
        b: (gComponentMD[Data, Data], () => gComponent[Data, Data]), 
        extCompName: String): (gComponentMD[Data, Data], () => gComponent[Data, Data])={
    val res = 
      () => 
        new gChainedComponent(a._1.inDataGen) (a._1.outDataGen) (b._1.inDataGen) (b._1.outDataGen) (mergeOffloads(a._1.offloadDataGen, b._1.offloadDataGen)) (a._2) (b._2) (extCompName + "__Chained") 
    //(new gComponentMD(a._1.inDataGen, b._1.outDataGen, mergeOffloads(a._1.offloadDataGen, b._1.offloadDataGen)), res.asInstanceOf[() => gComponent[Data, Data]])
    (new gComponentMD(a._1.inDataGen, b._1.outDataGen, mergeOffloads(a._1.offloadDataGen, b._1.offloadDataGen)), res)
  }

 def Chain   (
       a: (gComponentMD[Data, Data], () => gComponent[Data, Data]), 
       b: (gComponentMD[Data, Data], () => gComponent[Data, Data]),
       c: (gComponentMD[Data, Data], () => gComponent[Data, Data]), 
       extCompName: String): (gComponentMD[Data, Data], () => gComponent[Data, Data])={
   Chain(Chain(a, b, extCompName + "__" + "Chained_1"), c, extCompName + "__" + "Chained__2")
 }


  def Chain (a: ArrayBuffer[(gComponentMD[Data, Data], () => gComponent[Data, Data])], 
        extCompName: String): (gComponentMD[Data, Data], () => gComponent[Data, Data])={
    var i = 0;
    a.reduceLeft {(x, y) => {i+=1; Chain(x, y, extCompName + "__" +  "Chained__" + i)}} 
  }
 
  def Offload [inT <: Data, outT <: Data, inOffT<: Data, outOffT<: Data] (
        a: (gComponentMD[inT, outT], () => gComponent[inT, outT]), 
        b: (gComponentMD[inOffT, outOffT], () => gComponent[inOffT, outOffT]), offPort: String, extCompName: String)={ 
    val (left, right) = a._1.offloadDataGen.span( _._1 != offPort) 


    println("Offload called for port " + offPort)
    println("main offlaod ports are")
	a._1.offloadDataGen.foreach(x => println(x._1))
    println("left offlaod ports are")
    left.foreach(x => println(x._1))
    println("right offlaod ports are")
    right.foreach(x => println(x._1))
 
    val restOfOffloadedDataMain = {if (right.isEmpty) left else left ++ right.tail} 
    
    println("in Offload main offPorts a is ")
    a._1.offloadDataGen.foreach(x => println(x._1))
    println("in Offload main offPorts b is ")
    b._1.offloadDataGen.foreach(x => println(x._1))

    val offoff = mergeOffloads(restOfOffloadedDataMain, b._1.offloadDataGen)
    println("merged offlaod ports are")
    offoff.foreach(x => println(x._1))

    val res = 
      () =>
       new gOffloadedComponent(a._1.inDataGen) (a._1.outDataGen) (b._1.inDataGen) (b._1.outDataGen) (offoff) (a._2) (b._2) (offPort) (extCompName + "__Offloaded__" + offPort) 

    (new gComponentMD(a._1.inDataGen, a._1.outDataGen, offoff).asInstanceOf[gComponentMD[Data, Data]], res.asInstanceOf[() => gComponent[Data, Data]])
  }

  def Offload ( a: (gComponentMD[Data, Data], () => gComponent[Data, Data]), 
        b: ArrayBuffer[((gComponentMD[Data, Data], () => gComponent[Data, Data]), String)], 
        extCompName: String): (gComponentMD[Data, Data], () => gComponent[Data, Data])={ 
    b.foldLeft (a) {(x,y) => Offload(x, y._1, y._2, extCompName + "__Offloaded__" + y._2)} 
  }

  def Replicate [inT <: Data, outT <: Data, OffT<: Data] (
        a: (gComponentMD[inT, outT], () => gComponent[inT, outT]), 
        n: Int,
        extCompName: String)={ 
    val res = 
      () =>
       new gReplicatedComponent(a._1.inDataGen) (a._1.outDataGen) (a._1.offloadDataGen) (a._2) (n) (extCompName + "__Replicated") 

    (new gComponentMD(a._1.inDataGen, a._1.outDataGen, a._1.offloadDataGen).asInstanceOf[gComponentMD[Data, Data]], res.asInstanceOf[() => gComponent[Data, Data]])
  }

  val broadcastDistribute = UFix(255);
  def printChildren( p: Component) {
    p.children.foreach( c => {
                               println("Design hierarchy --- parent " + p.name + " child " + c.name) 
                               printChildren(c)
                             })
                     
  }

  def printPC(p: Component) {
    p.children.foreach(c => {
                               if (p.isInstanceOf[gComponent[Data, Data]]) {
                                 println("Backpressure in module " + p.name + " is " + p.asInstanceOf[gComponent[Data, Data]].inPCBackPressure.litValue().intValue()) 
                               }
                               printPC(c)
                             })
                     
  }
  val TAGWIDTH = 5
  def tagUpper(x: UFix) =  ((x >> UFix(TAGWIDTH)) & ((UFix(1) << UFix(TAGWIDTH)) - UFix(1)))
  def tagLower(x: UFix) = (x & ((UFix(1) << UFix(TAGWIDTH)) - UFix(1)))
}  
