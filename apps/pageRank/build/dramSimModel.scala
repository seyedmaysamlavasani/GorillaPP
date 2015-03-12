package Tutorial

import Chisel._
import Node._
import Literal._
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer


class dramSimModel (extCompName:String) extends gComponentLeaf  (() => new memReq16_128_t)(() => new memRep128_t) (ArrayBuffer(("lPipe", () => UFix(width = 32) , () => UFix(width = 32)), ("data", () => new memReq16_128_t , () => new memRep128_t)))  (extCompName = extCompName + "__type__engine__MT__10__") with include {
  val numOfThreads = 10 
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
   val inputReg = Vec(numOfThreads) {Reg(new memReq16_128_t)}
   val outputReg = Vec(numOfThreads) {Reg(new memRep128_t)}
   def mymyOfflPipe = myOff.asInstanceOf[Bundle].elements.find(_._1 == "lPipe").getOrElse(elseV)._2
   val lPipePort = new gOffBundleND(() => UFix(width = 32), () => UFix(width = 32))
   lPipePort <> mymyOfflPipe
   val lPipePortReplyStorage = Vec(numOfThreads) {Reg(UFix(width = 32))}
   def mymyOffdata = myOff.asInstanceOf[Bundle].elements.find(_._1 == "data").getOrElse(elseV)._2
   val dataPort = new gOffBundleND(() => new memReq16_128_t, () => new memRep128_t)
   dataPort <> mymyOffdata
   val dataPortReplyStorage = Vec(numOfThreads) {Reg(new memRep128_t)}
   val GS_MEMOP = UFix(1)
   val dramReq = new memReq16_128_t
   val dramRep = new memRep128_t
   val GS_DELAY = UFix(2)
  //stateCounters
  val prevStateThread0 = Reg(UFix(width=8), resetVal=WaitForInputValid)
  prevStateThread0 := State(0)
  when ((State(0) != prevStateThread0) && 
   (State(0) < UFix(16)) && !pcPaused) {
    stateCounters(State(0)) := stateCounters(State(0)) + UFix(1)
  }
  

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

  lPipePort.rep.ready := Bool(true)
  dataPort.rep.ready := Bool(true)

/******************Ready stage handler************************/
val lPipePortHadReadyRequest = Reg(resetVal=Bool(false))
val lPipe_ready_received = Reg(resetVal=Bool(false))
val dataPortHadReadyRequest = Reg(resetVal=Bool(false))
val data_ready_received = Reg(resetVal=Bool(false))

AllOffloadsReady :=  
  (lPipePort.req.ready || lPipe_ready_received || (!lPipePortHadReadyRequest && !lPipePort.req.valid)) &&  
  (dataPort.req.ready || data_ready_received || (!dataPortHadReadyRequest && !dataPort.req.valid)) &&  
  Bool(true)

lPipe_ready_received := !(AllOffloadsReady) && (lPipe_ready_received || lPipePort.req.ready)
lPipePortHadReadyRequest := !AllOffloadsReady && (lPipePortHadReadyRequest || lPipePort.req.valid)
data_ready_received := !(AllOffloadsReady) && (data_ready_received || dataPort.req.ready)
dataPortHadReadyRequest := !AllOffloadsReady && (dataPortHadReadyRequest || dataPort.req.valid)


/******************Valid stage handler************************/
val lPipePortHadValidRequest = Vec(numOfThreads) {Reg(resetVal=Bool(false))}
val lPipe_valid_received = Vec(numOfThreads) {Reg(resetVal=Bool(false))}
val dataPortHadValidRequest = Vec(numOfThreads) {Reg(resetVal=Bool(false))}
val data_valid_received = Vec(numOfThreads) {Reg(resetVal=Bool(false))}

for (i <- 0 to numOfThreads-1) {
AllOffloadsValid(i) :=  
  ((lPipePort.rep.valid && (lPipePort.rep.tag === UFix(i, 5)))|| lPipe_valid_received(i) || !lPipePortHadValidRequest(i)) &&  
  ((dataPort.rep.valid && (dataPort.rep.tag === UFix(i, 5)))|| data_valid_received(i) || !dataPortHadValidRequest(i)) &&  
  Bool(true)

lPipe_valid_received(i) := !(vThread === UFix(i, 5)) && ((lPipe_valid_received(i)) || (lPipePort.rep.valid && lPipePort.rep.tag === UFix(i, 5)))
lPipePortHadValidRequest(i) := !(vThread === UFix(i,5)) && (lPipePortHadValidRequest(i) || (UFix(i,5)===rThread && lPipePort.req.valid)/*(lPipePortHadReadyRequest && AllOffloadsReady && (UFix(i,5) === rThread))*/)
data_valid_received(i) := !(vThread === UFix(i, 5)) && ((data_valid_received(i)) || (dataPort.rep.valid && dataPort.rep.tag === UFix(i, 5)))
dataPortHadValidRequest(i) := !(vThread === UFix(i,5)) && (dataPortHadValidRequest(i) || (UFix(i,5)===rThread && dataPort.req.valid)/*(dataPortHadReadyRequest && AllOffloadsReady && (UFix(i,5) === rThread))*/)
}

val lPipePortReplyValue = Mux(lPipePort.rep.valid && (vThread === lPipePort.rep.tag), lPipePort.rep.bits, lPipePortReplyStorage(vThread)) 
when (lPipePort.rep.valid) {
  lPipePortReplyStorage(lPipePort.rep.tag) := lPipePort.rep.bits
} 
val dataPortReplyValue = Mux(dataPort.rep.valid && (vThread === dataPort.rep.tag), dataPort.rep.bits, dataPortReplyStorage(vThread)) 
when (dataPort.rep.valid) {
  dataPortReplyStorage(dataPort.rep.tag) := dataPort.rep.bits
} 
/**************************************************************/

   dramReq:=inputReg(rThread) 
   dramRep := dataPortReplyValue
   val lPipeReq=inputReg(rThread).addr 
   val lPipeRep = lPipePortReplyValue
   lPipePort.req.tag :=  rThread
   lPipePort.req.valid :=  (rThread != NONE_SELECTED) && !lPipe_valid_received(rThread) && ( (rThread != NONE_SELECTED && State(rThread) === GS_DELAY))
   lPipePort.req.bits := MuxCase(UFix(0, 32),Seq( ((rThread != NONE_SELECTED && State(rThread) === GS_DELAY),lPipeReq)))
   dataPort.req.tag :=  rThread
   dataPort.req.valid :=  (rThread != NONE_SELECTED) && !data_valid_received(rThread) && ( (rThread != NONE_SELECTED && State(rThread) === GS_MEMOP))
   dataPort.req.bits := MuxCase(UFix(0, 32),Seq( ((rThread != NONE_SELECTED && State(rThread) === GS_MEMOP),dramReq)))

  when (sThread != NONE_SELECTED && io.in.valid) {
    inputReg(sThread) := io.in.bits
    inputTag(sThread) := io.in.tag
    State(sThread) := GS_MEMOP
 
  } 

  when (rThread != NONE_SELECTED && State(rThread) === WaitForOutputReady && io.out.ready) {
    State(rThread) := EmitReturnState(rThread) 
  } 

   
    when (vThread != NONE_SELECTED &&State(vThread) === GS_MEMOP){
   outputReg(vThread):=dramRep 
   State(vThread):=GS_DELAY 
   } 
   
    when (vThread != NONE_SELECTED &&State(vThread) === GS_DELAY){
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
    when (State(0) != WaitForInputValid && 
     State(0) != WaitForOutputReady && !pcPaused) {
      engineUtilization := engineUtilization + 
       UFix(1, Pcounters.PCWIDTH) 
    }
  }
  for ((n, i) <- ioOff.elements) {
    if (n == "lPipe") {
      when (IsPcReset) {
        offloadRateArray(portId-3) := UFix(0, Pcounters.PCWIDTH)
      } .elsewhen (/*i.asInstanceOf[gOffBundle[Bundle, Bundle]].req.ready &&*/
       (lPipePortHadValidRequest(0) /*|| lPipePort.req.valid*/) && !pcPaused) {
        offloadRateArray(portId-3) := offloadRateArray(portId-3) + UFix(1, Pcounters.PCWIDTH)
      }
    }
    if (n == "data") {
      when (IsPcReset) {
        offloadRateArray(portId-3) := UFix(0, Pcounters.PCWIDTH)
      } .elsewhen (/*i.asInstanceOf[gOffBundle[Bundle, Bundle]].req.ready &&*/
       (dataPortHadValidRequest(0) /*|| dataPort.req.valid*/) && !pcPaused) {
        offloadRateArray(portId-3) := offloadRateArray(portId-3) + UFix(1, Pcounters.PCWIDTH)
      }
    }
    portId = portId + 1
  }
}

