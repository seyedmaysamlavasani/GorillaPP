package Tutorial

import Chisel._
import Node._
import Literal._
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer


class cacheController (extCompName:String) extends gComponentLeaf  (() => new memReq32_t)(() => new memRep32_t) (ArrayBuffer(("tag", () => new memReq10_64_t , () => new memRep64_t), ("data", () => new memReq10_128_t , () => new memRep128_t), ("dram", () => new memReq16_128_t , () => new memRep128_t)))  (extCompName = extCompName + "__type__engine__MT__8__") with include {
  val numOfThreads = 8 
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
   val gCacheLine = Vec (numOfThreads) {Reg(UFix(width = 128))}	//Global variable 
   val gTag = Vec (numOfThreads) {Reg(new tag_t)}	//Global variable 
   val gWord = Vec (numOfThreads) {Reg(UFix(width = 32), resetVal=UFix(0, width = 32))}	//Global variable 
   val missAlignment = Vec (numOfThreads) {Reg(UFix(width = 2))}	//Global variable 
   val dramRep = Vec (numOfThreads) {Reg(new memRep128_t)}	//Global variable 
   val addr = Vec (numOfThreads) {Reg(UFix(width = 32))}	//Global variable 
   val secondRound = Vec (numOfThreads) {Reg(Bool(), resetVal=Bool (false))}	//Global variable 
   val aligned = Vec (numOfThreads) {Reg(Bool())}	//Global variable 
   val initializationCounter = Vec (numOfThreads) {Reg(UFix(width = 32), resetVal=UFix(0, width = 32))}	//Global variable 
   val lock = Vec (1024) {Reg(Bool(), resetVal=Bool (false))}	//Global variable 
   val lockIndex = Vec (numOfThreads) {Reg(UFix(width = 32))}	//Global variable 
   val lockGrabbed = Vec (numOfThreads) {Reg(Bool(), resetVal=Bool (false))}	//Global variable 
   val inputReg = Vec(numOfThreads) {Reg(new memReq32_t)}
   val outputReg = Vec(numOfThreads) {Reg(new memRep32_t)}
   def mymyOfftag = myOff.asInstanceOf[Bundle].elements.find(_._1 == "tag").getOrElse(elseV)._2
   val tagPort = new gOffBundleND(() => new memReq10_64_t, () => new memRep64_t)
   tagPort <> mymyOfftag
   val tagPortReplyStorage = Vec(numOfThreads) {Reg(new memRep64_t)}
   def mymyOffdata = myOff.asInstanceOf[Bundle].elements.find(_._1 == "data").getOrElse(elseV)._2
   val dataPort = new gOffBundleND(() => new memReq10_128_t, () => new memRep128_t)
   dataPort <> mymyOffdata
   val dataPortReplyStorage = Vec(numOfThreads) {Reg(new memRep128_t)}
   def mymyOffdram = myOff.asInstanceOf[Bundle].elements.find(_._1 == "dram").getOrElse(elseV)._2
   val dramPort = new gOffBundleND(() => new memReq16_128_t, () => new memRep128_t)
   dramPort <> mymyOffdram
   val dramPortReplyStorage = Vec(numOfThreads) {Reg(new memRep128_t)}
   val GS_FIRST = UFix(1)
   val cacheDataReq1 = new memReqCacheDataBank_t
   val cacheTagReq1 = new memReqCacheTagBank_t
   val cacheDataRep1 = new memRepCacheDataBank_t
   val cacheTagRep1 = new memRepCacheTagBank_t
   val tag = new tag_t
   val GS_STORE_HIT = UFix(2)
   val cacheDataReq3 = new memReqCacheDataBank_t
   val cacheDataRep3 = new memRepCacheDataBank_t
   val cacheTagReq3 = new memReqCacheTagBank_t
   val cacheTagRep3 = new memRep32_t
   val GS_CACHE_FILL_DRAM_READ = UFix(3)
   val dramReq1 = new memReqDram_t
   val GS_CACHE_FILL_CACHE_WRITE = UFix(4)
   val cacheDataReq4 = new memReqCacheDataBank_t
   val cacheDataRep4 = new memRepCacheDataBank_t
   val cacheTagReq4 = new memReqCacheTagBank_t
   val cacheTagRep4 = new memRep32_t
   val newTag = new tag_t
   val GS_WRITE_BACK = UFix(5)
   val dramReq2 = new memReqDram_t
   val dramRep2 = new memRepCacheDataBank_t
   val cacheTagReq5 = new memReqCacheTagBank_t
   val cacheTagRep5 = new memRep32_t
   val GS_INITIALIZE = UFix(6)
   val cacheTagReq6 = new memReqCacheTagBank_t
   val cacheTagRep6 = new memRep32_t
   val initialTag = new tag_t
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

  tagPort.rep.ready := Bool(true)
  dataPort.rep.ready := Bool(true)
  dramPort.rep.ready := Bool(true)

/******************Ready stage handler************************/
val tagPortHadReadyRequest = Reg(resetVal=Bool(false))
val tag_ready_received = Reg(resetVal=Bool(false))
val dataPortHadReadyRequest = Reg(resetVal=Bool(false))
val data_ready_received = Reg(resetVal=Bool(false))
val dramPortHadReadyRequest = Reg(resetVal=Bool(false))
val dram_ready_received = Reg(resetVal=Bool(false))

AllOffloadsReady :=  
  (tagPort.req.ready || tag_ready_received || (!tagPortHadReadyRequest && !tagPort.req.valid)) &&  
  (dataPort.req.ready || data_ready_received || (!dataPortHadReadyRequest && !dataPort.req.valid)) &&  
  (dramPort.req.ready || dram_ready_received || (!dramPortHadReadyRequest && !dramPort.req.valid)) &&  
  Bool(true)

tag_ready_received := !(AllOffloadsReady) && (tag_ready_received || tagPort.req.ready)
tagPortHadReadyRequest := !AllOffloadsReady && (tagPortHadReadyRequest || tagPort.req.valid)
data_ready_received := !(AllOffloadsReady) && (data_ready_received || dataPort.req.ready)
dataPortHadReadyRequest := !AllOffloadsReady && (dataPortHadReadyRequest || dataPort.req.valid)
dram_ready_received := !(AllOffloadsReady) && (dram_ready_received || dramPort.req.ready)
dramPortHadReadyRequest := !AllOffloadsReady && (dramPortHadReadyRequest || dramPort.req.valid)


/******************Valid stage handler************************/
val tagPortHadValidRequest = Vec(numOfThreads) {Reg(resetVal=Bool(false))}
val tag_valid_received = Vec(numOfThreads) {Reg(resetVal=Bool(false))}
val dataPortHadValidRequest = Vec(numOfThreads) {Reg(resetVal=Bool(false))}
val data_valid_received = Vec(numOfThreads) {Reg(resetVal=Bool(false))}
val dramPortHadValidRequest = Vec(numOfThreads) {Reg(resetVal=Bool(false))}
val dram_valid_received = Vec(numOfThreads) {Reg(resetVal=Bool(false))}

for (i <- 0 to numOfThreads-1) {
AllOffloadsValid(i) :=  
  ((tagPort.rep.valid && (tagPort.rep.tag === UFix(i, 5)))|| tag_valid_received(i) || !tagPortHadValidRequest(i)) &&  
  ((dataPort.rep.valid && (dataPort.rep.tag === UFix(i, 5)))|| data_valid_received(i) || !dataPortHadValidRequest(i)) &&  
  ((dramPort.rep.valid && (dramPort.rep.tag === UFix(i, 5)))|| dram_valid_received(i) || !dramPortHadValidRequest(i)) &&  
  Bool(true)

tag_valid_received(i) := !(vThread === UFix(i, 5)) && ((tag_valid_received(i)) || (tagPort.rep.valid && tagPort.rep.tag === UFix(i, 5)))
tagPortHadValidRequest(i) := !(vThread === UFix(i,5)) && (tagPortHadValidRequest(i) || (UFix(i,5)===rThread && tagPort.req.valid)/*(tagPortHadReadyRequest && AllOffloadsReady && (UFix(i,5) === rThread))*/)
data_valid_received(i) := !(vThread === UFix(i, 5)) && ((data_valid_received(i)) || (dataPort.rep.valid && dataPort.rep.tag === UFix(i, 5)))
dataPortHadValidRequest(i) := !(vThread === UFix(i,5)) && (dataPortHadValidRequest(i) || (UFix(i,5)===rThread && dataPort.req.valid)/*(dataPortHadReadyRequest && AllOffloadsReady && (UFix(i,5) === rThread))*/)
dram_valid_received(i) := !(vThread === UFix(i, 5)) && ((dram_valid_received(i)) || (dramPort.rep.valid && dramPort.rep.tag === UFix(i, 5)))
dramPortHadValidRequest(i) := !(vThread === UFix(i,5)) && (dramPortHadValidRequest(i) || (UFix(i,5)===rThread && dramPort.req.valid)/*(dramPortHadReadyRequest && AllOffloadsReady && (UFix(i,5) === rThread))*/)
}

val tagPortReplyValue = Mux(tagPort.rep.valid && (vThread === tagPort.rep.tag), tagPort.rep.bits, tagPortReplyStorage(vThread)) 
when (tagPort.rep.valid) {
  tagPortReplyStorage(tagPort.rep.tag) := tagPort.rep.bits
} 
val dataPortReplyValue = Mux(dataPort.rep.valid && (vThread === dataPort.rep.tag), dataPort.rep.bits, dataPortReplyStorage(vThread)) 
when (dataPort.rep.valid) {
  dataPortReplyStorage(dataPort.rep.tag) := dataPort.rep.bits
} 
val dramPortReplyValue = Mux(dramPort.rep.valid && (vThread === dramPort.rep.tag), dramPort.rep.bits, dramPortReplyStorage(vThread)) 
when (dramPort.rep.valid) {
  dramPortReplyStorage(dramPort.rep.tag) := dramPort.rep.bits
} 
/**************************************************************/

   val lAddr1= Mux(secondRound(rThread),addr(rThread),inputReg(rThread).addr) 
   cacheDataReq1.addr:=INDEX(lAddr1) 
   cacheDataReq1.rw:=READ 
   cacheTagReq1.addr:=INDEX(lAddr1) 
   cacheTagReq1.rw:=READ 
   cacheTagRep1 := tagPortReplyValue
   cacheDataRep1 := dataPortReplyValue
   val lAddr11= Mux(secondRound(vThread),addr(vThread),inputReg(vThread).addr) 
   val lockIndexLocal=INDEX(lAddr11) 
   val localAligned=ALIGNED(inputReg(vThread).addr) 
   tag:=(new tag_t).fromBits(Bits(0, width=(new tag_t).getWidth) | (cacheTagRep1).toBits) 
   val tagValue=TAG(lAddr11) 
   val lWord1=FIRST_BYTES(cacheDataRep1.rData,missAlignment(vThread)) 
   val lWord2=FIRST_BYTES(gWord(vThread),(UFix(4, width = 32)-missAlignment(vThread))) 
   val lAddr2= Mux(secondRound(rThread),addr(rThread),inputReg(rThread).addr) 
   cacheDataReq3.addr:=INDEX(lAddr2) 
   cacheDataReq3.rw:=WRITE 
   val lCacheLine1=WRITE_WORD_IN_CACHE_LINE(inputReg(rThread).addr,gCacheLine(rThread),inputReg(rThread).wData,UFix(4, width = 32)) 
   val lCacheLine2=WRITE_WORD_IN_CACHE_LINE(addr(rThread),gCacheLine(rThread),(inputReg(rThread).wData>>((UFix(4, width = 32)-missAlignment(rThread))<<UFix(3, width = 32))),missAlignment(rThread)) 
   cacheDataReq3.wData:= Mux(secondRound(rThread),lCacheLine2,lCacheLine1) 
   cacheTagReq3.addr:=INDEX(lAddr2) 
   cacheTagReq3.wData:=(UFix(width = 32)).fromBits(Bits(0, width=(UFix(width = 32)).getWidth) | (gTag(rThread)).toBits) 
   cacheTagReq3.rw:=WRITE 
   cacheDataRep3 := dataPortReplyValue
   cacheTagRep3 := tagPortReplyValue
   val lAddr3= Mux(secondRound(rThread),addr(rThread),inputReg(rThread).addr) 
   dramReq1.addr:=DRAM_ADDR(lAddr3) 
   dramReq1.rw:=READ 
   val lAddr4= Mux(secondRound(rThread),addr(rThread),inputReg(rThread).addr) 
   newTag.tagValue:=TAG(lAddr4) 
   newTag.valid:=Bool(true) 
   newTag.dirty:=Bool(false) 
   cacheDataReq4.wData:=dramRep(rThread).rData 
   cacheDataReq4.rw:=WRITE 
   cacheDataReq4.addr:=INDEX(lAddr4) 
   cacheTagReq4.wData:=(UFix(width = 32)).fromBits(Bits(0, width=(UFix(width = 32)).getWidth) | (newTag).toBits) 
   cacheTagReq4.rw:=WRITE 
   cacheTagReq4.addr:=INDEX(lAddr4) 
   cacheDataRep4 := dataPortReplyValue
   cacheTagRep4 := tagPortReplyValue
   val lAddr5= Mux(secondRound(rThread),addr(rThread),inputReg(rThread).addr) 
   cacheTagReq5.wData:=(UFix(width = 32)).fromBits(Bits(0, width=(UFix(width = 32)).getWidth) | (gTag(rThread)).toBits) 
   cacheTagReq5.rw:=WRITE 
   cacheTagReq5.addr:=INDEX(lAddr5) 
   dramReq2.wData:=gCacheLine(rThread) 
   dramReq2.rw:=WRITE 
   dramReq2.addr:=DRAM_ADDR_WITH_NEW_TAG(gTag(rThread).tagValue,lAddr5) 
   cacheTagRep5 := tagPortReplyValue
   initialTag.dirty:=Bool(false) 
   initialTag.valid:=Bool(false) 
   cacheTagReq6.addr:=initializationCounter(rThread) 
   cacheTagReq6.rw:=WRITE 
   cacheTagReq6.wData:=(UFix(width = 32)).fromBits(Bits(0, width=(UFix(width = 32)).getWidth) | (initialTag).toBits) 
   cacheTagRep6 := tagPortReplyValue
   tagPort.req.tag :=  rThread
   tagPort.req.valid :=  (rThread != NONE_SELECTED) && !tag_valid_received(rThread) && ( (rThread != NONE_SELECTED && State(rThread) === GS_FIRST) ||  (rThread != NONE_SELECTED && State(rThread) === GS_STORE_HIT) ||  (rThread != NONE_SELECTED && State(rThread) === GS_CACHE_FILL_CACHE_WRITE) ||  (rThread != NONE_SELECTED && State(rThread) === GS_WRITE_BACK) ||  (rThread != NONE_SELECTED && State(rThread) === GS_INITIALIZE))
   tagPort.req.bits := MuxCase(UFix(0, 32),Seq( ((rThread != NONE_SELECTED && State(rThread) === GS_FIRST),cacheTagReq1) ,  ((rThread != NONE_SELECTED && State(rThread) === GS_STORE_HIT),cacheTagReq3) ,  ((rThread != NONE_SELECTED && State(rThread) === GS_CACHE_FILL_CACHE_WRITE),cacheTagReq4) ,  ((rThread != NONE_SELECTED && State(rThread) === GS_WRITE_BACK),cacheTagReq5) ,  ((rThread != NONE_SELECTED && State(rThread) === GS_INITIALIZE),cacheTagReq6)))
   dataPort.req.tag :=  rThread
   dataPort.req.valid :=  (rThread != NONE_SELECTED) && !data_valid_received(rThread) && ( (rThread != NONE_SELECTED && State(rThread) === GS_FIRST) ||  (rThread != NONE_SELECTED && State(rThread) === GS_STORE_HIT) ||  (rThread != NONE_SELECTED && State(rThread) === GS_CACHE_FILL_CACHE_WRITE))
   dataPort.req.bits := MuxCase(UFix(0, 32),Seq( ((rThread != NONE_SELECTED && State(rThread) === GS_FIRST),cacheDataReq1) ,  ((rThread != NONE_SELECTED && State(rThread) === GS_STORE_HIT),cacheDataReq3) ,  ((rThread != NONE_SELECTED && State(rThread) === GS_CACHE_FILL_CACHE_WRITE),cacheDataReq4)))
   dramPort.req.tag :=  rThread
   dramPort.req.valid :=  (rThread != NONE_SELECTED) && !dram_valid_received(rThread) && ( (rThread != NONE_SELECTED && State(rThread) === GS_CACHE_FILL_DRAM_READ) ||  (rThread != NONE_SELECTED && State(rThread) === GS_WRITE_BACK))
   dramPort.req.bits := MuxCase(UFix(0, 32),Seq( ((rThread != NONE_SELECTED && State(rThread) === GS_CACHE_FILL_DRAM_READ),dramReq1) ,  ((rThread != NONE_SELECTED && State(rThread) === GS_WRITE_BACK),dramReq2)))

  when (sThread != NONE_SELECTED && io.in.valid) {
    inputReg(sThread) := io.in.bits
    inputTag(sThread) := io.in.tag
   gWord(sThread) :=UFix(0, width = 32)
   secondRound(sThread) :=Bool (false)
   initializationCounter(sThread) :=UFix(0, width = 32)
   lockGrabbed(sThread) :=Bool (false)
    State(sThread) := GS_FIRST
 
  } 

  when (rThread != NONE_SELECTED && State(rThread) === WaitForOutputReady && io.out.ready) {
    State(rThread) := EmitReturnState(rThread) 
  } 

   
    when (vThread != NONE_SELECTED &&State(vThread) === GS_FIRST){
   lockIndex(vThread):=lockIndexLocal 
   aligned(vThread):=localAligned 
   when (!lock(lockIndexLocal)|| lockGrabbed(vThread)) {
   lockGrabbed(vThread):=Bool(true) 
   lock(lockIndexLocal):=Bool(true) 
   gCacheLine(vThread):=cacheDataRep1.rData 
   missAlignment(vThread):=MISSALIGNEMENT(inputReg(vThread).addr) 
   gTag(vThread):=tag 
   when (!inputReg(vThread).initialize&& tag.valid&& tag.tagValue=== tagValue) {
   when (inputReg(vThread).rw=== READ) {
   when (localAligned|| secondRound(vThread)) {
   when (!secondRound(vThread)) {
   outputReg(vThread).rData:=READ_WORD_IN_CACHE_LINE(inputReg(vThread).addr,cacheDataRep1.rData) 
   lock(lockIndexLocal):=Bool(false) 
   EmitReturnState(vThread) := WaitForInputValid
   State(vThread) := WaitForOutputReady
   }
   .otherwise {
   outputReg(vThread).rData:=(lWord1<<((UFix(4, width = 32)-missAlignment(vThread))<<UFix(3, width = 32)))+lWord2 
   lock(lockIndexLocal):=Bool(false) 
   EmitReturnState(vThread) := WaitForInputValid
   State(vThread) := WaitForOutputReady
   }
   }
   .otherwise {
   gWord(vThread):=READ_WORD_IN_CACHE_LINE(inputReg(vThread).addr,cacheDataRep1.rData) 
   addr(vThread):=inputReg(vThread).addr+UFix(4, width = 32)-missAlignment(vThread) 
   secondRound(vThread):=Bool(true) 
   lock(lockIndexLocal):=Bool(false) 
   lockGrabbed(vThread):=Bool(false) 
   State(vThread):=GS_FIRST 
   }
   }
   when (inputReg(vThread).rw=== WRITE) {
   gTag(vThread).dirty:=Bool(true) 
   State(vThread):=GS_STORE_HIT 
   }
   }
   .otherwise {
   when (tag.dirty) {
   gTag(vThread).dirty:=Bool(false) 
   State(vThread):=GS_WRITE_BACK 
   }
   .otherwise {
   State(vThread):=GS_CACHE_FILL_DRAM_READ 
   }
   }
   when (inputReg(vThread).initialize) {
   State(vThread):=GS_INITIALIZE 
   }
   }
   } 
   
    when (vThread != NONE_SELECTED &&State(vThread) === GS_STORE_HIT){
   when (aligned(vThread)|| secondRound(vThread)) {
   lock(lockIndex(vThread)):=Bool(false) 
   EmitReturnState(vThread) := WaitForInputValid
   State(vThread) := WaitForOutputReady
   }
   .otherwise {
   addr(vThread):=inputReg(vThread).addr+UFix(4, width = 32)-missAlignment(vThread) 
   secondRound(vThread):=Bool(true) 
   lock(lockIndex(vThread)):=Bool(false) 
   lockGrabbed(vThread):=Bool(false) 
   State(vThread):=GS_FIRST 
   }
   } 
   
    when (vThread != NONE_SELECTED &&State(vThread) === GS_CACHE_FILL_DRAM_READ){
   dramRep(vThread) := dramPortReplyValue
   State(vThread):=GS_CACHE_FILL_CACHE_WRITE 
   } 
   
    when (vThread != NONE_SELECTED &&State(vThread) === GS_CACHE_FILL_CACHE_WRITE){
   State(vThread):=GS_FIRST 
   } 
   
    when (vThread != NONE_SELECTED &&State(vThread) === GS_WRITE_BACK){
   dramRep(vThread) := dramPortReplyValue
   State(vThread):=GS_FIRST 
   } 
   
    when (vThread != NONE_SELECTED &&State(vThread) === GS_INITIALIZE){
   when (initializationCounter(vThread)+UFix(1, width = 32)=== (UFix(1, width = 32)<<cacheHeightLog)) {
   lock(lockIndex(vThread)):=Bool(false) 
   EmitReturnState(vThread) := WaitForInputValid
   State(vThread) := WaitForOutputReady
   }
   .otherwise {
   initializationCounter(vThread):=initializationCounter(vThread)+UFix(1, width = 32) 
   }
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
    if (n == "tag") {
      when (IsPcReset) {
        offloadRateArray(portId-3) := UFix(0, Pcounters.PCWIDTH)
      } .elsewhen (/*i.asInstanceOf[gOffBundle[Bundle, Bundle]].req.ready &&*/
       (tagPortHadValidRequest(0) /*|| tagPort.req.valid*/) && !pcPaused) {
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
}

