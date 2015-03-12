package Tutorial

import Chisel._
import Node._
import Literal._
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer


class updateWriter (extCompName:String) extends gComponentLeaf  (() => new update_t)(() => new prDone_t) (ArrayBuffer(("mem", () => new mem_req_t , () => new mem_rep_t), ("lock", () => new lock_req_t , () => new lock_rep_t), ("add", () => new FU_in_t , () => new FU_out_t)))  (extCompName = extCompName + "__type__engine__MT__2__") with include {
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
   val mode = Vec (numOfThreads) {Reg(UFix(width=1), resetVal=UFix(0, width = 1))}	//Global variable 
   val rank = Vec (numOfThreads) {Reg(UFix(width = 64))}	//Global variable 
   val memRep = Vec (numOfThreads) {Reg(new mem_rep_t)}	//Global variable 
   val inputReg = Vec(numOfThreads) {Reg(new update_t)}
   val outputReg = Vec(numOfThreads) {Reg(new prDone_t)}
   def mymyOfflock = myOff.asInstanceOf[Bundle].elements.find(_._1 == "lock").getOrElse(elseV)._2
   val lockPort = new gOffBundleND(() => new lock_req_t, () => new lock_rep_t)
   lockPort <> mymyOfflock
   val lockPortReplyStorage = Vec(numOfThreads) {Reg(new lock_rep_t)}
   def mymyOffmem = myOff.asInstanceOf[Bundle].elements.find(_._1 == "mem").getOrElse(elseV)._2
   val memPort = new gOffBundleND(() => new mem_req_t, () => new mem_rep_t)
   memPort <> mymyOffmem
   val memPortReplyStorage = Vec(numOfThreads) {Reg(new mem_rep_t)}
   def mymyOffadd = myOff.asInstanceOf[Bundle].elements.find(_._1 == "add").getOrElse(elseV)._2
   val addPort = new gOffBundleND(() => new FU_in_t, () => new FU_out_t)
   addPort <> mymyOffadd
   val addPortReplyStorage = Vec(numOfThreads) {Reg(new FU_out_t)}
   val GS_RECEIVE_UPDATE = UFix(1)
   val GS_LOCK_PAGE = UFix(2)
   val lock_req1 = new lock_req_t
   val lock_rep1 = new lock_rep_t
   val GS_READ_MODE = UFix(3)
   val memReq1 = new mem_req_t
   val GS_CHANGE_MODE = UFix(4)
   val memReq2 = new mem_req_t
   val GS_READ_RANK = UFix(5)
   val memReq3 = new mem_req_t
   val GS_ADD_RANK = UFix(6)
   val addIn = new FU_in_t
   val addOut = new FU_out_t
   val GS_WRITE_RANK = UFix(7)
   val memReq4 = new mem_req_t
   val GS_UNLOCK_PAGE = UFix(8)
   val lock_req2 = new lock_req_t
   val lock_rep2 = new lock_rep_t
   val GS_FINISH = UFix(9)
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
  lockPort.rep.ready := Bool(true)
  addPort.rep.ready := Bool(true)

/******************Ready stage handler************************/
val memPortHadReadyRequest = Reg(resetVal=Bool(false))
val mem_ready_received = Reg(resetVal=Bool(false))
val lockPortHadReadyRequest = Reg(resetVal=Bool(false))
val lock_ready_received = Reg(resetVal=Bool(false))
val addPortHadReadyRequest = Reg(resetVal=Bool(false))
val add_ready_received = Reg(resetVal=Bool(false))

AllOffloadsReady :=  
  (memPort.req.ready || mem_ready_received || (!memPortHadReadyRequest && !memPort.req.valid)) &&  
  (lockPort.req.ready || lock_ready_received || (!lockPortHadReadyRequest && !lockPort.req.valid)) &&  
  (addPort.req.ready || add_ready_received || (!addPortHadReadyRequest && !addPort.req.valid)) &&  
  Bool(true)

mem_ready_received := !(AllOffloadsReady) && (mem_ready_received || memPort.req.ready)
memPortHadReadyRequest := !AllOffloadsReady && (memPortHadReadyRequest || memPort.req.valid)
lock_ready_received := !(AllOffloadsReady) && (lock_ready_received || lockPort.req.ready)
lockPortHadReadyRequest := !AllOffloadsReady && (lockPortHadReadyRequest || lockPort.req.valid)
add_ready_received := !(AllOffloadsReady) && (add_ready_received || addPort.req.ready)
addPortHadReadyRequest := !AllOffloadsReady && (addPortHadReadyRequest || addPort.req.valid)


/******************Valid stage handler************************/
val memPortHadValidRequest = Vec(numOfThreads) {Reg(resetVal=Bool(false))}
val mem_valid_received = Vec(numOfThreads) {Reg(resetVal=Bool(false))}
val lockPortHadValidRequest = Vec(numOfThreads) {Reg(resetVal=Bool(false))}
val lock_valid_received = Vec(numOfThreads) {Reg(resetVal=Bool(false))}
val addPortHadValidRequest = Vec(numOfThreads) {Reg(resetVal=Bool(false))}
val add_valid_received = Vec(numOfThreads) {Reg(resetVal=Bool(false))}

for (i <- 0 to numOfThreads-1) {
AllOffloadsValid(i) :=  
  ((memPort.rep.valid && (memPort.rep.tag === UFix(i, 5)))|| mem_valid_received(i) || !memPortHadValidRequest(i)) &&  
  ((lockPort.rep.valid && (lockPort.rep.tag === UFix(i, 5)))|| lock_valid_received(i) || !lockPortHadValidRequest(i)) &&  
  ((addPort.rep.valid && (addPort.rep.tag === UFix(i, 5)))|| add_valid_received(i) || !addPortHadValidRequest(i)) &&  
  Bool(true)

mem_valid_received(i) := !(vThread === UFix(i, 5)) && ((mem_valid_received(i)) || (memPort.rep.valid && memPort.rep.tag === UFix(i, 5)))
memPortHadValidRequest(i) := !(vThread === UFix(i,5)) && (memPortHadValidRequest(i) || (UFix(i,5)===rThread && memPort.req.valid)/*(memPortHadReadyRequest && AllOffloadsReady && (UFix(i,5) === rThread))*/)
lock_valid_received(i) := !(vThread === UFix(i, 5)) && ((lock_valid_received(i)) || (lockPort.rep.valid && lockPort.rep.tag === UFix(i, 5)))
lockPortHadValidRequest(i) := !(vThread === UFix(i,5)) && (lockPortHadValidRequest(i) || (UFix(i,5)===rThread && lockPort.req.valid)/*(lockPortHadReadyRequest && AllOffloadsReady && (UFix(i,5) === rThread))*/)
add_valid_received(i) := !(vThread === UFix(i, 5)) && ((add_valid_received(i)) || (addPort.rep.valid && addPort.rep.tag === UFix(i, 5)))
addPortHadValidRequest(i) := !(vThread === UFix(i,5)) && (addPortHadValidRequest(i) || (UFix(i,5)===rThread && addPort.req.valid)/*(addPortHadReadyRequest && AllOffloadsReady && (UFix(i,5) === rThread))*/)
}

val memPortReplyValue = Mux(memPort.rep.valid && (vThread === memPort.rep.tag), memPort.rep.bits, memPortReplyStorage(vThread)) 
when (memPort.rep.valid) {
  memPortReplyStorage(memPort.rep.tag) := memPort.rep.bits
} 
val lockPortReplyValue = Mux(lockPort.rep.valid && (vThread === lockPort.rep.tag), lockPort.rep.bits, lockPortReplyStorage(vThread)) 
when (lockPort.rep.valid) {
  lockPortReplyStorage(lockPort.rep.tag) := lockPort.rep.bits
} 
val addPortReplyValue = Mux(addPort.rep.valid && (vThread === addPort.rep.tag), addPort.rep.bits, addPortReplyStorage(vThread)) 
when (addPort.rep.valid) {
  addPortReplyStorage(addPort.rep.tag) := addPort.rep.bits
} 
/**************************************************************/

   lock_req1.id:=inputReg(rThread).pageId 
   lock_req1.op:=LOCK 
   lock_rep1 := lockPortReplyValue
   memReq1.rw:=READ 
   memReq1.size:=WORD 
   memReq1.addr:=modeAddress(inputReg(rThread).pageId) 
   val oldMode=modeRead(memRep(vThread).data,inputReg(vThread).pageId) 
   memReq2.rw:=WRITE 
   memReq2.size:=WORD 
   memReq2.addr:=modeAddress(inputReg(rThread).pageId) 
   memReq2.data:=modeWrite(memRep(rThread).data,inputReg(rThread).pageId) 
   memReq3.rw:=READ 
   memReq3.size:=DWORD 
   memReq3.addr:=rankAddress(inputReg(rThread).pageId) 
   addIn.in1:=rank(rThread) 
   addIn.in2:=inputReg(rThread).rankUpdate 
   addOut := addPortReplyValue
   memReq4.rw:=WRITE 
   memReq4.size:=DWORD 
   memReq4.addr:=rankAddress(inputReg(rThread).pageId) 
   memReq4.data:=rank(rThread) 
   lock_req2.id:=inputReg(rThread).pageId 
   lock_req2.op:=UNLOCK 
   lock_rep2 := lockPortReplyValue
   lockPort.req.tag :=  rThread
   lockPort.req.valid :=  (rThread != NONE_SELECTED) && !lock_valid_received(rThread) && ( (rThread != NONE_SELECTED && State(rThread) === GS_LOCK_PAGE) ||  (rThread != NONE_SELECTED && State(rThread) === GS_UNLOCK_PAGE))
   lockPort.req.bits := MuxCase(UFix(0, 32),Seq( ((rThread != NONE_SELECTED && State(rThread) === GS_LOCK_PAGE),lock_req1) ,  ((rThread != NONE_SELECTED && State(rThread) === GS_UNLOCK_PAGE),lock_req2)))
   memPort.req.tag :=  rThread
   memPort.req.valid :=  (rThread != NONE_SELECTED) && !mem_valid_received(rThread) && ( (rThread != NONE_SELECTED && State(rThread) === GS_READ_MODE) ||  (rThread != NONE_SELECTED && State(rThread) === GS_CHANGE_MODE) ||  (rThread != NONE_SELECTED && State(rThread) === GS_READ_RANK) ||  (rThread != NONE_SELECTED && State(rThread) === GS_WRITE_RANK))
   memPort.req.bits := MuxCase(UFix(0, 32),Seq( ((rThread != NONE_SELECTED && State(rThread) === GS_READ_MODE),memReq1) ,  ((rThread != NONE_SELECTED && State(rThread) === GS_CHANGE_MODE),memReq2) ,  ((rThread != NONE_SELECTED && State(rThread) === GS_READ_RANK),memReq3) ,  ((rThread != NONE_SELECTED && State(rThread) === GS_WRITE_RANK),memReq4)))
   addPort.req.tag :=  rThread
   addPort.req.valid :=  (rThread != NONE_SELECTED) && !add_valid_received(rThread) && ( (rThread != NONE_SELECTED && State(rThread) === GS_ADD_RANK))
   addPort.req.bits := MuxCase(UFix(0, 32),Seq( ((rThread != NONE_SELECTED && State(rThread) === GS_ADD_RANK),addIn)))

  when (sThread != NONE_SELECTED && io.in.valid) {
    inputReg(sThread) := io.in.bits
    inputTag(sThread) := io.in.tag
   mode(sThread) :=UFix(0, width = 1)
    State(sThread) := GS_RECEIVE_UPDATE
 
  } 

  when (rThread != NONE_SELECTED && State(rThread) === WaitForOutputReady && io.out.ready) {
    State(rThread) := EmitReturnState(rThread) 
  } 

   
    when (vThread != NONE_SELECTED &&State(vThread) === GS_RECEIVE_UPDATE){
   when (inputReg(vThread).done) {
   State(vThread):=GS_FINISH 
   }
   .otherwise {
   State(vThread):=GS_LOCK_PAGE 
   }
   } 
   
    when (vThread != NONE_SELECTED &&State(vThread) === GS_LOCK_PAGE){
   State(vThread):=GS_READ_MODE 
   } 
   
    when (vThread != NONE_SELECTED &&State(vThread) === GS_READ_MODE){
   memRep(vThread) := memPortReplyValue
   State(vThread):=GS_READ_RANK 
   } 
   
    when (vThread != NONE_SELECTED &&State(vThread) === GS_CHANGE_MODE){
   memRep(vThread) := memPortReplyValue
   State(vThread):=GS_WRITE_RANK 
   } 
   
    when (vThread != NONE_SELECTED &&State(vThread) === GS_READ_RANK){
   memRep(vThread) := memPortReplyValue
   rank(vThread):=memRep(vThread).data 
   State(vThread):=GS_ADD_RANK 
   } 
   
    when (vThread != NONE_SELECTED &&State(vThread) === GS_ADD_RANK){
   rank(vThread):=addOut.out 
   State(vThread):=GS_WRITE_RANK 
   } 
   
    when (vThread != NONE_SELECTED &&State(vThread) === GS_WRITE_RANK){
   memRep(vThread) := memPortReplyValue
   State(vThread):=GS_UNLOCK_PAGE 
   } 
   
    when (vThread != NONE_SELECTED &&State(vThread) === GS_UNLOCK_PAGE){
   EmitReturnState(vThread):=WaitForInputValid 
   State(vThread) := WaitForOutputReady
   } 
   
    when (vThread != NONE_SELECTED &&State(vThread) === GS_FINISH){
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
    if (n == "lock") {
      when (IsPcReset) {
        offloadRateArray(portId-3) := UFix(0, Pcounters.PCWIDTH)
      } .elsewhen (/*i.asInstanceOf[gOffBundle[Bundle, Bundle]].req.ready &&*/
       (lockPortHadValidRequest(0) /*|| lockPort.req.valid*/) && !pcPaused) {
        offloadRateArray(portId-3) := offloadRateArray(portId-3) + UFix(1, Pcounters.PCWIDTH)
      }
    }
    if (n == "add") {
      when (IsPcReset) {
        offloadRateArray(portId-3) := UFix(0, Pcounters.PCWIDTH)
      } .elsewhen (/*i.asInstanceOf[gOffBundle[Bundle, Bundle]].req.ready &&*/
       (addPortHadValidRequest(0) /*|| addPort.req.valid*/) && !pcPaused) {
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

