package Tutorial

import Chisel._
import Node._
import Literal._
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer


class cache (extCompName:String) extends gComponentLeaf  (() => new mem_req_t)(() => new mem_rep_t) (ArrayBuffer(("cacheMissPipe", () => UFix(width = 32) , () => UFix(width = 32)), ("dram", () => new mem_req_t , () => new mem_rep_t)))  (extCompName = extCompName + "__type__engine__MT__3__") with include {
  val numOfThreads = 3 
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
   val cachedAddr = Vec (numOfThreads) {Reg(UFix(width = 32), resetVal=UFix(0, width = 32))}	//Global variable 
   val dramOut = Vec (numOfThreads) {Reg(new mem_rep_t)}	//Global variable 
   val enableRandomFanout = Vec (numOfThreads) {Reg(UFix(width = 32), resetVal=UFix(1, width = 32))}	//Global variable 
   val random = Vec (numOfThreads) {Reg(UFix(width = 32), resetVal=UFix(1, width = 32))}	//Global variable 
   val burst = Vec (numOfThreads) {Reg(UFix(width = 32), resetVal=UFix(1, width = 32))}	//Global variable 
   val	MAX_FANOUT = (AVERAGE_FANOUT>>UFix(1, width = 32))//constant macro definition 
   val inputReg = Vec(numOfThreads) {Reg(new mem_req_t)}
   val outputReg = Vec(numOfThreads) {Reg(new mem_rep_t)}
   def mymyOffdram = myOff.asInstanceOf[Bundle].elements.find(_._1 == "dram").getOrElse(elseV)._2
   val dramPort = new gOffBundleND(() => new mem_req_t, () => new mem_rep_t)
   dramPort <> mymyOffdram
   val dramPortReplyStorage = Vec(numOfThreads) {Reg(new mem_rep_t)}
   def mymyOffcacheMissPipe = myOff.asInstanceOf[Bundle].elements.find(_._1 == "cacheMissPipe").getOrElse(elseV)._2
   val cacheMissPipePort = new gOffBundleND(() => UFix(width = 32), () => UFix(width = 32))
   cacheMissPipePort <> mymyOffcacheMissPipe
   val cacheMissPipePortReplyStorage = Vec(numOfThreads) {Reg(UFix(width = 32))}
   val GS_HIT_CHECK = UFix(1)
   val GS_MISS = UFix(2)
   val GS_DRAM = UFix(3)
   val GS_FINISH = UFix(4)
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

  cacheMissPipePort.rep.ready := Bool(true)
  dramPort.rep.ready := Bool(true)

/******************Ready stage handler************************/
val cacheMissPipePortHadReadyRequest = Reg(resetVal=Bool(false))
val cacheMissPipe_ready_received = Reg(resetVal=Bool(false))
val dramPortHadReadyRequest = Reg(resetVal=Bool(false))
val dram_ready_received = Reg(resetVal=Bool(false))

AllOffloadsReady :=  
  (cacheMissPipePort.req.ready || cacheMissPipe_ready_received || (!cacheMissPipePortHadReadyRequest && !cacheMissPipePort.req.valid)) &&  
  (dramPort.req.ready || dram_ready_received || (!dramPortHadReadyRequest && !dramPort.req.valid)) &&  
  Bool(true)

cacheMissPipe_ready_received := !(AllOffloadsReady) && (cacheMissPipe_ready_received || cacheMissPipePort.req.ready)
cacheMissPipePortHadReadyRequest := !AllOffloadsReady && (cacheMissPipePortHadReadyRequest || cacheMissPipePort.req.valid)
dram_ready_received := !(AllOffloadsReady) && (dram_ready_received || dramPort.req.ready)
dramPortHadReadyRequest := !AllOffloadsReady && (dramPortHadReadyRequest || dramPort.req.valid)


/******************Valid stage handler************************/
val cacheMissPipePortHadValidRequest = Vec(numOfThreads) {Reg(resetVal=Bool(false))}
val cacheMissPipe_valid_received = Vec(numOfThreads) {Reg(resetVal=Bool(false))}
val dramPortHadValidRequest = Vec(numOfThreads) {Reg(resetVal=Bool(false))}
val dram_valid_received = Vec(numOfThreads) {Reg(resetVal=Bool(false))}

for (i <- 0 to numOfThreads-1) {
AllOffloadsValid(i) :=  
  ((cacheMissPipePort.rep.valid && (cacheMissPipePort.rep.tag === UFix(i, 5)))|| cacheMissPipe_valid_received(i) || !cacheMissPipePortHadValidRequest(i)) &&  
  ((dramPort.rep.valid && (dramPort.rep.tag === UFix(i, 5)))|| dram_valid_received(i) || !dramPortHadValidRequest(i)) &&  
  Bool(true)

cacheMissPipe_valid_received(i) := !(vThread === UFix(i, 5)) && ((cacheMissPipe_valid_received(i)) || (cacheMissPipePort.rep.valid && cacheMissPipePort.rep.tag === UFix(i, 5)))
cacheMissPipePortHadValidRequest(i) := !(vThread === UFix(i,5)) && (cacheMissPipePortHadValidRequest(i) || (UFix(i,5)===rThread && cacheMissPipePort.req.valid)/*(cacheMissPipePortHadReadyRequest && AllOffloadsReady && (UFix(i,5) === rThread))*/)
dram_valid_received(i) := !(vThread === UFix(i, 5)) && ((dram_valid_received(i)) || (dramPort.rep.valid && dramPort.rep.tag === UFix(i, 5)))
dramPortHadValidRequest(i) := !(vThread === UFix(i,5)) && (dramPortHadValidRequest(i) || (UFix(i,5)===rThread && dramPort.req.valid)/*(dramPortHadReadyRequest && AllOffloadsReady && (UFix(i,5) === rThread))*/)
}

val cacheMissPipePortReplyValue = Mux(cacheMissPipePort.rep.valid && (vThread === cacheMissPipePort.rep.tag), cacheMissPipePort.rep.bits, cacheMissPipePortReplyStorage(vThread)) 
when (cacheMissPipePort.rep.valid) {
  cacheMissPipePortReplyStorage(cacheMissPipePort.rep.tag) := cacheMissPipePort.rep.bits
} 
val dramPortReplyValue = Mux(dramPort.rep.valid && (vThread === dramPort.rep.tag), dramPort.rep.bits, dramPortReplyStorage(vThread)) 
when (dramPort.rep.valid) {
  dramPortReplyStorage(dramPort.rep.tag) := dramPort.rep.bits
} 
/**************************************************************/

   val ct=cacheTag(inputReg(vThread).addr) 
   val t = cacheMissPipePortReplyValue
   dramPort.req.tag :=  rThread
   dramPort.req.valid :=  (rThread != NONE_SELECTED) && !dram_valid_received(rThread) && ( (rThread != NONE_SELECTED && State(rThread) === GS_DRAM))
   dramPort.req.bits := MuxCase(UFix(0, 32),Seq( ((rThread != NONE_SELECTED && State(rThread) === GS_DRAM),inputReg(rThread))))
   cacheMissPipePort.req.tag :=  rThread
   cacheMissPipePort.req.valid :=  (rThread != NONE_SELECTED) && !cacheMissPipe_valid_received(rThread) && ( (rThread != NONE_SELECTED && State(rThread) === GS_MISS))
   cacheMissPipePort.req.bits := MuxCase(UFix(0, 32),Seq( ((rThread != NONE_SELECTED && State(rThread) === GS_MISS),UFix(0, width = 32))))

  when (sThread != NONE_SELECTED && io.in.valid) {
    inputReg(sThread) := io.in.bits
    inputTag(sThread) := io.in.tag
   cachedAddr(sThread) :=UFix(0, width = 32)
   enableRandomFanout(sThread) :=UFix(1, width = 32)
   random(sThread) :=UFix(1, width = 32)
   burst(sThread) :=UFix(1, width = 32)
    State(sThread) := GS_HIT_CHECK
 
  } 

  when (rThread != NONE_SELECTED && State(rThread) === WaitForOutputReady && io.out.ready) {
    State(rThread) := EmitReturnState(rThread) 
  } 

   
    when (vThread != NONE_SELECTED &&State(vThread) === GS_HIT_CHECK){
   when ((inputReg(vThread).addr<=RANK_A_BASE_ADDRESS)) {
   when (((inputReg(vThread).addr>>UFix(2, width = 32))& UFix(1, width = 32))=== UFix(0, width = 32)) {
   when (FIXED_FANOUT=== UFix(1, width = 32)) {
   outputReg(vThread).data:=AVERAGE_FANOUT 
   }
   .otherwise {
   outputReg(vThread).data:=random(vThread) 
   }
   when (random(vThread)=== MAX_FANOUT) {
   when (burst(vThread)=== BURST_FACTOR) {
   random(vThread):=UFix(1, width = 32) 
   }
   }
   .otherwise {
   when (burst(vThread)=== BURST_FACTOR) {
   random(vThread):=random(vThread)+UFix(1, width = 32) 
   }
   }
   when (burst(vThread)=== BURST_FACTOR) {
   burst(vThread):=UFix(1, width = 32) 
   }
   .otherwise {
   burst(vThread):=burst(vThread)+UFix(1, width = 32) 
   }
   }
   .otherwise {
   outputReg(vThread).data:=RANK_A_BASE_ADDRESS+random(vThread)<<UFix(5, width = 32) 
   }
   }
   .otherwise {
   outputReg(vThread).data:=inputReg(vThread).addr+UFix(1000, width = 32) 
   }
   when (ct=== cachedAddr(vThread)|| inputReg(vThread).rw=== WRITE) {
   State(vThread):=GS_FINISH 
   }
   .otherwise {
   State(vThread):=GS_MISS 
   cachedAddr(vThread):=ct 
   }
   } 
   
    when (vThread != NONE_SELECTED &&State(vThread) === GS_MISS){
   State(vThread):=GS_DRAM 
   } 
   
    when (vThread != NONE_SELECTED &&State(vThread) === GS_DRAM){
   dramOut(vThread) := dramPortReplyValue
   State(vThread):=GS_FINISH 
   } 
   
    when (vThread != NONE_SELECTED &&State(vThread) === GS_FINISH){
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
    if (n == "cacheMissPipe") {
      when (IsPcReset) {
        offloadRateArray(portId-3) := UFix(0, Pcounters.PCWIDTH)
      } .elsewhen (/*i.asInstanceOf[gOffBundle[Bundle, Bundle]].req.ready &&*/
       (cacheMissPipePortHadValidRequest(0) /*|| cacheMissPipePort.req.valid*/) && !pcPaused) {
        offloadRateArray(portId-3) := offloadRateArray(portId-3) + UFix(1, Pcounters.PCWIDTH)
      }
    }
    if (n == "dram") {
      when (IsPcReset) {
        offloadRateArray(portId-3) := UFix(0, Pcounters.PCWIDTH)
      } .elsewhen (/*i.asInstanceOf[gOffBundle[Bundle, Bundle]].req.ready &&*/
       (dramPortHadValidRequest(0) /*|| dramPort.req.valid*/) && !pcPaused) {
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

