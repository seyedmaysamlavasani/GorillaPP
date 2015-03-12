package Tutorial

import Chisel._
import Node._
import Literal._
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer


class updateGenerator (extCompName:String) extends gComponentLeaf  (() => new bulkUpdate_t)(() => new update_t) (ArrayBuffer(("mem", () => new mem_req_t , () => new mem_rep_t), ("rankCalc", () => new rankCalc_in_t , () => new rankCalc_out_t)))  (extCompName = extCompName + "__type__engine__MT__2__") with include {
  val numOfThreads = 2 
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
   val mode = Vec (numOfThreads) {Reg(UFix(width=1), resetVal=UFix(1, width = 1))}	//Global variable 
   val pageId = Vec (numOfThreads) {Reg(UFix(width = 32))}	//Global variable 
   val length = Vec (numOfThreads) {Reg(UFix(width = 32))}	//Global variable 
   val linkId = Vec (numOfThreads) {Reg(UFix(width = 32))}	//Global variable 
   val linkIndex = Vec (numOfThreads) {Reg(UFix(width = 32))}	//Global variable 
   val fanoutDegree = Vec (numOfThreads) {Reg(UFix(width = 32))}	//Global variable 
   val outPageId = Vec (numOfThreads) {Reg(UFix(width = 32))}	//Global variable 
   val rank = Vec (numOfThreads) {Reg(UFix(width = 64))}	//Global variable 
   val rankUpdate = Vec (numOfThreads) {Reg(UFix(width = 64))}	//Global variable 
   val memRep = Vec (numOfThreads) {Reg(new mem_rep_t)}	//Global variable 
   val inputReg = Vec(numOfThreads) {Reg(new bulkUpdate_t)}
   val outputReg = Vec(numOfThreads) {Reg(new update_t)}
   def mymyOffrankCalc = myOff.asInstanceOf[Bundle].elements.find(_._1 == "rankCalc").getOrElse(elseV)._2
   val rankCalcPort = new gOffBundleND(() => new rankCalc_in_t, () => new rankCalc_out_t)
   rankCalcPort <> mymyOffrankCalc
   val rankCalcPortReplyStorage = Vec(numOfThreads) {Reg(new rankCalc_out_t)}
   def mymyOffmem = myOff.asInstanceOf[Bundle].elements.find(_._1 == "mem").getOrElse(elseV)._2
   val memPort = new gOffBundleND(() => new mem_req_t, () => new mem_rep_t)
   memPort <> mymyOffmem
   val memPortReplyStorage = Vec(numOfThreads) {Reg(new mem_rep_t)}
   val GS_RECEIVE_ITERATION = UFix(1)
   val GS_PROCESS_PAGE = UFix(2)
   val GS_READ_RANK = UFix(3)
   val memReq1 = new mem_req_t
   val GS_READ_PAGE = UFix(4)
   val memReq2 = new mem_req_t
   val memRep1 = new mem_rep_t
   val page = new page_t
   val GS_CALC_RANK = UFix(5)
   val rankCalc_in = new rankCalc_in_t
   val rankCalc_out = new rankCalc_out_t
   val GS_READ_LINK = UFix(6)
   val memReq3 = new mem_req_t
   val GS_SEND = UFix(7)
   val GS_FINISH = UFix(8)
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

  memPort.rep.ready := Bool(true)
  rankCalcPort.rep.ready := Bool(true)

/******************Ready stage handler************************/
val memPortHadReadyRequest = Reg(resetVal=Bool(false))
val mem_ready_received = Reg(resetVal=Bool(false))
val rankCalcPortHadReadyRequest = Reg(resetVal=Bool(false))
val rankCalc_ready_received = Reg(resetVal=Bool(false))

AllOffloadsReady :=  
  (memPort.req.ready || mem_ready_received || (!memPortHadReadyRequest && !memPort.req.valid)) &&  
  (rankCalcPort.req.ready || rankCalc_ready_received || (!rankCalcPortHadReadyRequest && !rankCalcPort.req.valid)) &&  
  Bool(true)

mem_ready_received := !(AllOffloadsReady) && (mem_ready_received || memPort.req.ready)
memPortHadReadyRequest := !AllOffloadsReady && (memPortHadReadyRequest || memPort.req.valid)
rankCalc_ready_received := !(AllOffloadsReady) && (rankCalc_ready_received || rankCalcPort.req.ready)
rankCalcPortHadReadyRequest := !AllOffloadsReady && (rankCalcPortHadReadyRequest || rankCalcPort.req.valid)


/******************Valid stage handler************************/
val memPortHadValidRequest = Vec(numOfThreads) {Reg(resetVal=Bool(false))}
val mem_valid_received = Vec(numOfThreads) {Reg(resetVal=Bool(false))}
val rankCalcPortHadValidRequest = Vec(numOfThreads) {Reg(resetVal=Bool(false))}
val rankCalc_valid_received = Vec(numOfThreads) {Reg(resetVal=Bool(false))}

for (i <- 0 to numOfThreads-1) {
AllOffloadsValid(i) :=  
  ((memPort.rep.valid && (memPort.rep.tag === UFix(i, 5)))|| mem_valid_received(i) || !memPortHadValidRequest(i)) &&  
  ((rankCalcPort.rep.valid && (rankCalcPort.rep.tag === UFix(i, 5)))|| rankCalc_valid_received(i) || !rankCalcPortHadValidRequest(i)) &&  
  Bool(true)

mem_valid_received(i) := !(vThread === UFix(i, 5)) && ((mem_valid_received(i)) || (memPort.rep.valid && memPort.rep.tag === UFix(i, 5)))
memPortHadValidRequest(i) := !(vThread === UFix(i,5)) && (memPortHadValidRequest(i) || (UFix(i,5)===rThread && memPort.req.valid)/*(memPortHadReadyRequest && AllOffloadsReady && (UFix(i,5) === rThread))*/)
rankCalc_valid_received(i) := !(vThread === UFix(i, 5)) && ((rankCalc_valid_received(i)) || (rankCalcPort.rep.valid && rankCalcPort.rep.tag === UFix(i, 5)))
rankCalcPortHadValidRequest(i) := !(vThread === UFix(i,5)) && (rankCalcPortHadValidRequest(i) || (UFix(i,5)===rThread && rankCalcPort.req.valid)/*(rankCalcPortHadReadyRequest && AllOffloadsReady && (UFix(i,5) === rThread))*/)
}

val memPortReplyValue = Mux(memPort.rep.valid && (vThread === memPort.rep.tag), memPort.rep.bits, memPortReplyStorage(vThread)) 
when (memPort.rep.valid) {
  memPortReplyStorage(memPort.rep.tag) := memPort.rep.bits
} 
val rankCalcPortReplyValue = Mux(rankCalcPort.rep.valid && (vThread === rankCalcPort.rep.tag), rankCalcPort.rep.bits, rankCalcPortReplyStorage(vThread)) 
when (rankCalcPort.rep.valid) {
  rankCalcPortReplyStorage(rankCalcPort.rep.tag) := rankCalcPort.rep.bits
} 
/**************************************************************/

   memReq1.cached:=Bool(false) 
   memReq1.rw:=READ 
   memReq1.size:=DWORD 
   memReq1.addr:=rankAddress(pageId(rThread)) 
   memReq2.cached:=Bool(false) 
   memReq2.rw:=READ 
   memReq2.size:=DWORD 
   memReq2.addr:=pageAddress(pageId(rThread)) 
   memRep1 := memPortReplyValue
   page.fanoutDegree:=fanoutInPage(memRep1.data) 
   rankCalc_in.damping:=DAMPING_FACTOR 
   rankCalc_in.rank:=rank(rThread) 
   rankCalc_in.fanoutDegree:=fanoutDegree(rThread) 
   rankCalc_out := rankCalcPortReplyValue
   memReq3.rw:=READ_CACHED 
   memReq3.size:=WORD 
   memReq3.addr:=linkAddress(linkId(rThread)) 
   rankCalcPort.req.tag :=  rThread
   rankCalcPort.req.valid :=  (rThread != NONE_SELECTED) && !rankCalc_valid_received(rThread) && ( (rThread != NONE_SELECTED && State(rThread) === GS_CALC_RANK))
   rankCalcPort.req.bits := MuxCase(UFix(0, 32),Seq( ((rThread != NONE_SELECTED && State(rThread) === GS_CALC_RANK),rankCalc_in)))
   memPort.req.tag :=  rThread
   memPort.req.valid :=  (rThread != NONE_SELECTED) && !mem_valid_received(rThread) && ( (rThread != NONE_SELECTED && State(rThread) === GS_READ_RANK) ||  (rThread != NONE_SELECTED && State(rThread) === GS_READ_PAGE) ||  (rThread != NONE_SELECTED && State(rThread) === GS_READ_LINK))
   memPort.req.bits := MuxCase(UFix(0, 32),Seq( ((rThread != NONE_SELECTED && State(rThread) === GS_READ_RANK),memReq1) ,  ((rThread != NONE_SELECTED && State(rThread) === GS_READ_PAGE),memReq2) ,  ((rThread != NONE_SELECTED && State(rThread) === GS_READ_LINK),memReq3)))

  when (sThread != NONE_SELECTED && io.in.valid) {
    inputReg(sThread) := io.in.bits
    inputTag(sThread) := io.in.tag
   mode(sThread) :=UFix(1, width = 1)
    State(sThread) := GS_RECEIVE_ITERATION
 
  } 

  when (rThread != NONE_SELECTED && State(rThread) === WaitForOutputReady && io.out.ready) {
    State(rThread) := EmitReturnState(rThread) 
  } 

   
    when (vThread != NONE_SELECTED &&State(vThread) === GS_RECEIVE_ITERATION){
   pageId(vThread):=inputReg(vThread).startPageId 
   length(vThread):=inputReg(vThread).length 
   when (inputReg(vThread).done) {
   State(vThread):=GS_FINISH 
   }
   .otherwise {
   State(vThread):=GS_PROCESS_PAGE 
   }
   } 
   
    when (vThread != NONE_SELECTED &&State(vThread) === GS_PROCESS_PAGE){
   outputReg(vThread).done:=Bool(false) 
   outputReg(vThread).pageId:=pageId(vThread) 
   outputReg(vThread).rankUpdate:=DAMPING_RANK 
   EmitReturnState(vThread):=GS_READ_RANK 
   State(vThread) := WaitForOutputReady
   } 
   
    when (vThread != NONE_SELECTED &&State(vThread) === GS_READ_RANK){
   memRep(vThread) := memPortReplyValue
   rank(vThread):=memRep(vThread).data 
   State(vThread):=GS_READ_PAGE 
   } 
   
    when (vThread != NONE_SELECTED &&State(vThread) === GS_READ_PAGE){
   memRep(vThread):=memRep1 
   linkId(vThread):=startLinkIndxInPage(memRep1.data) 
   when (page.fanoutDegree<=UFix(32, width = 32)) {
   fanoutDegree(vThread):=page.fanoutDegree 
   }
   .otherwise {
   fanoutDegree(vThread):=page.fanoutDegree& UFix(31, width = 32) 
   }
   when (page.fanoutDegree=== UFix(0, width = 32)) {
   when (pageId(vThread)=== (inputReg(vThread).startPageId+inputReg(vThread).length-UFix(1, width = 32))) {
   State(vThread):=WaitForInputValid 
   }
   .otherwise {
   pageId(vThread):=pageId(vThread)+UFix(1, width = 32) 
   State(vThread):=GS_PROCESS_PAGE 
   }
   }
   .otherwise {
   State(vThread):=GS_CALC_RANK 
   }
   } 
   
    when (vThread != NONE_SELECTED &&State(vThread) === GS_CALC_RANK){
   rankUpdate(vThread):=rankCalc_out.out 
   State(vThread):=GS_READ_LINK 
   linkIndex(vThread):=UFix(0, width = 32) 
   } 
   
    when (vThread != NONE_SELECTED &&State(vThread) === GS_READ_LINK){
   memRep(vThread) := memPortReplyValue
   outPageId(vThread):=memRep(vThread).data 
   State(vThread):=GS_SEND 
   } 
   
    when (vThread != NONE_SELECTED &&State(vThread) === GS_SEND){
   outputReg(vThread).pageId:=outPageId(vThread) 
   outputReg(vThread).rankUpdate:=rankUpdate(vThread) 
   when (linkIndex(vThread)=== fanoutDegree(vThread)) {
   when (pageId(vThread)=== (inputReg(vThread).startPageId+inputReg(vThread).length-UFix(1, width = 32))) {
   EmitReturnState(vThread):=WaitForInputValid 
   }
   .otherwise {
   pageId(vThread):=pageId(vThread)+UFix(1, width = 32) 
   EmitReturnState(vThread):=GS_PROCESS_PAGE 
   }
   }
   .otherwise {
   linkIndex(vThread):=linkIndex(vThread)+UFix(1, width = 32) 
   EmitReturnState(vThread):=GS_READ_LINK 
   }
   State(vThread) := WaitForOutputReady
   } 
   
    when (vThread != NONE_SELECTED &&State(vThread) === GS_FINISH){
   outputReg(vThread).done:=inputReg(vThread).done 
   mode(vThread):=UFix(1, width = 32)-mode(vThread) 
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
    if (n == "mem") {
      when (IsPcReset) {
        offloadRateArray(portId-3) := UFix(0, Pcounters.PCWIDTH)
      } .elsewhen (/*i.asInstanceOf[gOffBundle[Bundle, Bundle]].req.ready &&*/
       (memPortHadValidRequest(0) /*|| memPort.req.valid*/) && !pcPaused) {
        offloadRateArray(portId-3) := offloadRateArray(portId-3) + UFix(1, Pcounters.PCWIDTH)
      }
    }
    if (n == "rankCalc") {
      when (IsPcReset) {
        offloadRateArray(portId-3) := UFix(0, Pcounters.PCWIDTH)
      } .elsewhen (/*i.asInstanceOf[gOffBundle[Bundle, Bundle]].req.ready &&*/
       (rankCalcPortHadValidRequest(0) /*|| rankCalcPort.req.valid*/) && !pcPaused) {
        offloadRateArray(portId-3) := offloadRateArray(portId-3) + UFix(1, Pcounters.PCWIDTH)
      }
    }
    portId = portId + 1
  }
  when (IsPcReset) {
    offloadRateOverall := UFix(0, Pcounters.PCWIDTH)
  } .elsewhen (!AllOffloadsValid(0) && (subStateTh(0) === WaitForValid) && !pcPaused) {
    offloadRateOverall := offloadRateOverall + UFix(1, Pcounters.PCWIDTH)
  }
}

