package Tutorial

import Chisel._
import Node._
import Literal._
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer


class multiProtocolEngine (extCompName:String) extends gComponentLeaf  (() => new NP_EthMpl3Header_t)(() => new NP_EthMpl3Header_t) (ArrayBuffer(("ipv4Lookup1", () => UFix(width = 32) , () => UFix(width = 8)), ("ipv4Lookup2", () => UFix(width = 32) , () => UFix(width = 8)), ("qosCount", () => UFix(width = 32) , () => UFix(width = 8))))  (extCompName = extCompName + "__type__engine__MT__1__") with include {
  val numOfThreads = 1 
  val NONE_SELECTED = UFix(numOfThreads,log2Up(numOfThreads+1))
  val WaitForInputValid = UFix(0, 8)
  val WaitForOutputReady = UFix(255, 8)
  val WaitForReady = UFix(0, 1)
  val WaitForValid = UFix(1, 1)
  val inputTag = Vec(numOfThreads) {Reg(UFix(width=TAGWIDTH*2))}
  val State = Vec(numOfThreads) {Reg(UFix(width=8), resetVal=WaitForInputValid)} 
  val EmitReturnState = Vec(numOfThreads) {Reg(UFix(width=8), resetVal=WaitForInputValid)} 
  //val outstandingOffs = Vec(numOfThreads) {Reg(resetVal=UFix(0, 5))}
  val AllOffloadsReady = Bool()
  val AllOffloadsValid  = Vec(numOfThreads) {Bool()}
  /*******************Thread states*********************************/
  val subStateTh = Vec(numOfThreads) {Reg(resetVal=WaitForReady)}

  def  myOff = io.elements.find(_._1 == "off").getOrElse(elseV)._2
   val ipv4Input = Vec (numOfThreads) {Reg(new IPv4Header_t)}	//Global variable 
   val ipv4Output = Vec (numOfThreads) {Reg(new IPv4Header_t)}	//Global variable 
   val gOutPort = Vec (numOfThreads) {Reg(UFix(width = 32))}	//Global variable 
   val inputReg = Vec(numOfThreads) {Reg(new NP_EthMpl3Header_t)}
   val outputReg = Vec(numOfThreads) {Reg(new NP_EthMpl3Header_t)}
   def mymyOffipv4Lookup1 = myOff.asInstanceOf[Bundle].elements.find(_._1 == "ipv4Lookup1").getOrElse(elseV)._2
   val ipv4Lookup1Port = new gOffBundleND(() => UFix(width = 32), () => UFix(width = 8))
   ipv4Lookup1Port <> mymyOffipv4Lookup1
   def mymyOffipv4Lookup2 = myOff.asInstanceOf[Bundle].elements.find(_._1 == "ipv4Lookup2").getOrElse(elseV)._2
   val ipv4Lookup2Port = new gOffBundleND(() => UFix(width = 32), () => UFix(width = 8))
   ipv4Lookup2Port <> mymyOffipv4Lookup2
   def mymyOffqosCount = myOff.asInstanceOf[Bundle].elements.find(_._1 == "qosCount").getOrElse(elseV)._2
   val qosCountPort = new gOffBundleND(() => UFix(width = 32), () => UFix(width = 8))
   qosCountPort <> mymyOffqosCount
   val GS_ETHERNET = UFix(1)
   val GS_IPV4 = UFix(2)
   val GS_LOOKUP = UFix(3)
   val GS_UPDATE = UFix(4)
   val GS_EXCEPTION = UFix(5)

   /******************Winner threads*********************************/
  val rThreadEncoder = new RREncode (numOfThreads) 
  val rThread =  rThreadEncoder.io.chosen
  Range(0, numOfThreads, 1).map(i => 
    rThreadEncoder.io.valid(i) := (subStateTh(i) === WaitForReady))
  rThreadEncoder.io.ready := (rThread != NONE_SELECTED)

  val vThreadEncoder = new RREncode (numOfThreads) 
  val vThread =  vThreadEncoder.io.chosen
  Range(0, numOfThreads, 1).map(i => 
    vThreadEncoder.io.valid(i) := (subStateTh(i) === WaitForValid) && AllOffloadsValid(i))
  vThreadEncoder.io.ready := vThread != NONE_SELECTED

  val sThreadEncoder = new RREncode (numOfThreads) 
  val sThread =  sThreadEncoder.io.chosen
  Range(0, numOfThreads, 1).map(i => sThreadEncoder.io.valid(i) := (subStateTh(i) === WaitForReady) && (State(i) === WaitForInputValid))
  sThreadEncoder.io.ready := sThread != NONE_SELECTED


Range(0, numOfThreads, 1).foreach(i => subStateTh(i) := MuxCase(subStateTh(i), Seq((AllOffloadsReady && UFix(i) === rThread && State(i) != WaitForInputValid && State(i) != WaitForOutputReady , WaitForValid), (UFix(i) === vThread, WaitForReady)))) 

  ipv4Lookup1Port.rep.ready := Bool(true)
  ipv4Lookup2Port.rep.ready := Bool(true)
  qosCountPort.rep.ready := Bool(true)

/******************Ready stage handler************************/
val ipv4Lookup1PortHadReadyRequest = Reg(resetVal=Bool(false))
val ipv4Lookup1_ready_received = Reg(resetVal=Bool(false))
val ipv4Lookup2PortHadReadyRequest = Reg(resetVal=Bool(false))
val ipv4Lookup2_ready_received = Reg(resetVal=Bool(false))
val qosCountPortHadReadyRequest = Reg(resetVal=Bool(false))
val qosCount_ready_received = Reg(resetVal=Bool(false))

AllOffloadsReady :=  
  (ipv4Lookup1Port.req.ready || ipv4Lookup1_ready_received || (!ipv4Lookup1PortHadReadyRequest && !ipv4Lookup1Port.req.valid)) &&  
  (ipv4Lookup2Port.req.ready || ipv4Lookup2_ready_received || (!ipv4Lookup2PortHadReadyRequest && !ipv4Lookup2Port.req.valid)) &&  
  (qosCountPort.req.ready || qosCount_ready_received || (!qosCountPortHadReadyRequest && !qosCountPort.req.valid)) &&  
  Bool(true)

ipv4Lookup1_ready_received := !(AllOffloadsReady) && (ipv4Lookup1_ready_received || ipv4Lookup1Port.req.ready)
ipv4Lookup1PortHadReadyRequest := !AllOffloadsReady && (ipv4Lookup1PortHadReadyRequest || ipv4Lookup1Port.req.valid)
ipv4Lookup2_ready_received := !(AllOffloadsReady) && (ipv4Lookup2_ready_received || ipv4Lookup2Port.req.ready)
ipv4Lookup2PortHadReadyRequest := !AllOffloadsReady && (ipv4Lookup2PortHadReadyRequest || ipv4Lookup2Port.req.valid)
qosCount_ready_received := !(AllOffloadsReady) && (qosCount_ready_received || qosCountPort.req.ready)
qosCountPortHadReadyRequest := !AllOffloadsReady && (qosCountPortHadReadyRequest || qosCountPort.req.valid)


/******************Valid stage handler************************/
val ipv4Lookup1PortHadValidRequest = Vec(numOfThreads) {Reg(resetVal=Bool(false))}
val ipv4Lookup1_valid_received = Vec(numOfThreads) {Reg(resetVal=Bool(false))}
val ipv4Lookup2PortHadValidRequest = Vec(numOfThreads) {Reg(resetVal=Bool(false))}
val ipv4Lookup2_valid_received = Vec(numOfThreads) {Reg(resetVal=Bool(false))}
val qosCountPortHadValidRequest = Vec(numOfThreads) {Reg(resetVal=Bool(false))}
val qosCount_valid_received = Vec(numOfThreads) {Reg(resetVal=Bool(false))}

for (i <- 0 to numOfThreads-1) {
AllOffloadsValid(i) :=  
  ((ipv4Lookup1Port.rep.valid && (ipv4Lookup1Port.rep.tag === UFix(i, 5)))|| ipv4Lookup1_valid_received(i) || !ipv4Lookup1PortHadValidRequest(i)) &&  
  ((ipv4Lookup2Port.rep.valid && (ipv4Lookup2Port.rep.tag === UFix(i, 5)))|| ipv4Lookup2_valid_received(i) || !ipv4Lookup2PortHadValidRequest(i)) &&  
  ((qosCountPort.rep.valid && (qosCountPort.rep.tag === UFix(i, 5)))|| qosCount_valid_received(i) || !qosCountPortHadValidRequest(i)) &&  
  Bool(true)

ipv4Lookup1_valid_received(i) := !(vThread === UFix(i, 5)) && ((ipv4Lookup1_valid_received(i)) || (ipv4Lookup1Port.rep.valid && ipv4Lookup1Port.rep.tag === UFix(i, 5)))
ipv4Lookup1PortHadValidRequest(i) := !(vThread === UFix(i,5)) && (ipv4Lookup1PortHadValidRequest(i) || (UFix(i,5)===rThread && ipv4Lookup1Port.req.valid)/*(ipv4Lookup1PortHadReadyRequest && AllOffloadsReady && (UFix(i,5) === rThread))*/)
ipv4Lookup2_valid_received(i) := !(vThread === UFix(i, 5)) && ((ipv4Lookup2_valid_received(i)) || (ipv4Lookup2Port.rep.valid && ipv4Lookup2Port.rep.tag === UFix(i, 5)))
ipv4Lookup2PortHadValidRequest(i) := !(vThread === UFix(i,5)) && (ipv4Lookup2PortHadValidRequest(i) || (UFix(i,5)===rThread && ipv4Lookup2Port.req.valid)/*(ipv4Lookup2PortHadReadyRequest && AllOffloadsReady && (UFix(i,5) === rThread))*/)
qosCount_valid_received(i) := !(vThread === UFix(i, 5)) && ((qosCount_valid_received(i)) || (qosCountPort.rep.valid && qosCountPort.rep.tag === UFix(i, 5)))
qosCountPortHadValidRequest(i) := !(vThread === UFix(i,5)) && (qosCountPortHadValidRequest(i) || (UFix(i,5)===rThread && qosCountPort.req.valid)/*(qosCountPortHadReadyRequest && AllOffloadsReady && (UFix(i,5) === rThread))*/)
}

   val outPort = ipv4Lookup1Port.rep.bits
   val srcLookupResult = ipv4Lookup2Port.rep.bits
   val qcOutput = qosCountPort.rep.bits
   ipv4Lookup1Port.req.tag :=  rThread
   ipv4Lookup1Port.req.valid :=  (rThread != NONE_SELECTED) && !ipv4Lookup1_valid_received(rThread) && ( (rThread != NONE_SELECTED && State(rThread) === GS_LOOKUP))
   ipv4Lookup1Port.req.bits := MuxCase(UFix(0, 32),Seq( ((rThread != NONE_SELECTED && State(rThread) === GS_LOOKUP),ipv4Input(rThread).dstAddr)))
   ipv4Lookup2Port.req.tag :=  rThread
   ipv4Lookup2Port.req.valid :=  (rThread != NONE_SELECTED) && !ipv4Lookup2_valid_received(rThread) && ( (rThread != NONE_SELECTED && State(rThread) === GS_LOOKUP))
   ipv4Lookup2Port.req.bits := MuxCase(UFix(0, 32),Seq( ((rThread != NONE_SELECTED && State(rThread) === GS_LOOKUP),ipv4Input(rThread).srcAddr)))
   qosCountPort.req.tag :=  rThread
   qosCountPort.req.valid :=  (rThread != NONE_SELECTED) && !qosCount_valid_received(rThread) && ( (rThread != NONE_SELECTED && State(rThread) === GS_UPDATE))
   qosCountPort.req.bits := MuxCase(UFix(0, 32),Seq( ((rThread != NONE_SELECTED && State(rThread) === GS_UPDATE),ipv4Input(rThread).srcAddr)))

  when (sThread != NONE_SELECTED && io.in.valid) {
    inputReg(sThread) := io.in.bits
    inputTag(sThread) := io.in.tag
    State(sThread) := GS_ETHERNET
 
  } 

  when (rThread != NONE_SELECTED && State(rThread) === WaitForOutputReady && io.out.ready) {
    State(rThread) := EmitReturnState(rThread) 
  } 

   
    when (vThread != NONE_SELECTED &&State(vThread) === GS_ETHERNET){
   ipv4Input(vThread):=(new IPv4Header_t).fromBits(Bits(0, width=(new IPv4Header_t).getWidth) | (inputReg(vThread).l3).toBits) 
   outputReg(vThread):=inputReg(vThread) 
   when (inputReg(vThread).l2Protocol===ETHERNET) {
   State(vThread):=GS_IPV4 
   }
   .otherwise {
   State(vThread):=GS_EXCEPTION 
   }
   } 
   
    when (vThread != NONE_SELECTED &&State(vThread) === GS_IPV4){
   when (inputReg(vThread).eth.l3Type===IPV4) {
   State(vThread):=GS_LOOKUP 
   ipv4Output(vThread):=ipv4Input(vThread) 
   }
   .otherwise {
   State(vThread):=GS_EXCEPTION 
   }
   when (ipv4Input(vThread).length<UFix(20, width = 32)||ipv4Input(vThread).version!=UFix(4, width = 32)) {
   State(vThread):=GS_EXCEPTION 
   }
   } 
   
    when (vThread != NONE_SELECTED &&State(vThread) === GS_LOOKUP){
   outputReg(vThread).outPort:=outPort+srcLookupResult 
   gOutPort(vThread):=outPort 
   State(vThread):=GS_UPDATE 
   } 
   
    when (vThread != NONE_SELECTED &&State(vThread) === GS_UPDATE){
   outputReg(vThread).outPort:=gOutPort(vThread)+qcOutput 
   ipv4Output(vThread).ttl:=ipv4Input(vThread).ttl-UFix(1, width = 32) 
   ipv4Output(vThread).chksum:=ipv4Input(vThread).chksum+UFix(128, width = 32) 
   outputReg(vThread).l3:=(new mpl3Header_t).fromBits(Bits(0, width=(new mpl3Header_t).getWidth) | (ipv4Output(vThread)).toBits) 
   EmitReturnState(vThread) := WaitForInputValid
   State(vThread) := WaitForOutputReady
   } 
   
    when (vThread != NONE_SELECTED &&State(vThread) === GS_EXCEPTION){
   outputReg(vThread).outPort:=CONTROL_PLANE 
   EmitReturnState(vThread) := WaitForInputValid
   State(vThread) := WaitForOutputReady
   } 

  io.out.tag := inputTag(rThread)
  io.out.bits := outputReg(rThread)
  io.out.valid := rThread != NONE_SELECTED && State(rThread) === WaitForOutputReady
  io.in.ready := sThread != NONE_SELECTED 

/******************Engine specific performance counters************************/
  val IsPcReset = 
   io.pcIn.valid && io.pcIn.bits.request && io.pcIn.bits.pcType === Pcounters.pcReset
  var portId = 3
  when (IsPcReset) {
    engineUtilization := UFix(0, Pcounters.PCWIDTH) 
  } .otherwise {
    when (State(0) != WaitForInputValid) {
      engineUtilization := engineUtilization + 
       UFix(1, Pcounters.PCWIDTH) 
    }
  }
  for ((n, i) <- ioOff.elements) {
    if (n == "ipv4Lookup1") {
      when (IsPcReset) {
        offloadRateArray(portId-3) := UFix(0, Pcounters.PCWIDTH)
      } .elsewhen (i.asInstanceOf[gOffBundle[Bundle, Bundle]].req.ready && 
       (ipv4Lookup1PortHadValidRequest(0) || ipv4Lookup1Port.req.valid) && !pcPaused) {
        offloadRateArray(portId-3) := offloadRateArray(portId-3) + UFix(1, Pcounters.PCWIDTH)
      }
    }
    if (n == "ipv4Lookup2") {
      when (IsPcReset) {
        offloadRateArray(portId-3) := UFix(0, Pcounters.PCWIDTH)
      } .elsewhen (i.asInstanceOf[gOffBundle[Bundle, Bundle]].req.ready && 
       (ipv4Lookup2PortHadValidRequest(0) || ipv4Lookup2Port.req.valid) && !pcPaused) {
        offloadRateArray(portId-3) := offloadRateArray(portId-3) + UFix(1, Pcounters.PCWIDTH)
      }
    }
    if (n == "qosCount") {
      when (IsPcReset) {
        offloadRateArray(portId-3) := UFix(0, Pcounters.PCWIDTH)
      } .elsewhen (i.asInstanceOf[gOffBundle[Bundle, Bundle]].req.ready && 
       (qosCountPortHadValidRequest(0) || qosCountPort.req.valid) && !pcPaused) {
        offloadRateArray(portId-3) := offloadRateArray(portId-3) + UFix(1, Pcounters.PCWIDTH)
      }
    }
    portId = portId + 1
  }
}

