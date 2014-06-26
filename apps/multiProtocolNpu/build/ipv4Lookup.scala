package Tutorial

import Chisel._
import Node._
import Literal._
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer


class ipv4Lookup (extCompName:String) extends gComponentLeaf  (() => UFix(width = 32))(() => UFix(width = 8)) (ArrayBuffer(("mem1", () => new memReq32_t , () => new memRep32_t), ("mem2", () => new memReq32_t , () => new memRep32_t), ("mem3", () => new memReq32_t , () => new memRep32_t)))  (extCompName = extCompName + "__type__engine__MT__1__") with include {
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
   val result = Vec (numOfThreads) {Reg(UFix(width = 32))}	//Global variable 
   val inputReg = Vec(numOfThreads) {Reg(UFix(width = 32))}
   val outputReg = Vec(numOfThreads) {Reg(UFix(width = 8))}
   def mymyOffmem2 = myOff.asInstanceOf[Bundle].elements.find(_._1 == "mem2").getOrElse(elseV)._2
   val mem2Port = new gOffBundleND(() => new memReq32_t, () => new memRep32_t)
   mem2Port <> mymyOffmem2
   def mymyOffmem1 = myOff.asInstanceOf[Bundle].elements.find(_._1 == "mem1").getOrElse(elseV)._2
   val mem1Port = new gOffBundleND(() => new memReq32_t, () => new memRep32_t)
   mem1Port <> mymyOffmem1
   def mymyOffmem3 = myOff.asInstanceOf[Bundle].elements.find(_._1 == "mem3").getOrElse(elseV)._2
   val mem3Port = new gOffBundleND(() => new memReq32_t, () => new memRep32_t)
   mem3Port <> mymyOffmem3
   val GS_FIRST_LEVEL = UFix(1)
   val memReq1 = new memReq32_t
   val memRep1 = new memRep32_t
   val GS_SECOND_LEVEL = UFix(2)
   val memReq2 = new memReq32_t
   val memRep2 = new memRep32_t
   val GS_THIRD_LEVEL = UFix(3)
   val memReq3 = new memReq32_t
   val memRep3 = new memRep32_t

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

  mem1Port.rep.ready := Bool(true)
  mem2Port.rep.ready := Bool(true)
  mem3Port.rep.ready := Bool(true)

/******************Ready stage handler************************/
val mem1PortHadReadyRequest = Reg(resetVal=Bool(false))
val mem1_ready_received = Reg(resetVal=Bool(false))
val mem2PortHadReadyRequest = Reg(resetVal=Bool(false))
val mem2_ready_received = Reg(resetVal=Bool(false))
val mem3PortHadReadyRequest = Reg(resetVal=Bool(false))
val mem3_ready_received = Reg(resetVal=Bool(false))

AllOffloadsReady :=  
  (mem1Port.req.ready || mem1_ready_received || (!mem1PortHadReadyRequest && !mem1Port.req.valid)) &&  
  (mem2Port.req.ready || mem2_ready_received || (!mem2PortHadReadyRequest && !mem2Port.req.valid)) &&  
  (mem3Port.req.ready || mem3_ready_received || (!mem3PortHadReadyRequest && !mem3Port.req.valid)) &&  
  Bool(true)

mem1_ready_received := !(AllOffloadsReady) && (mem1_ready_received || mem1Port.req.ready)
mem1PortHadReadyRequest := !AllOffloadsReady && (mem1PortHadReadyRequest || mem1Port.req.valid)
mem2_ready_received := !(AllOffloadsReady) && (mem2_ready_received || mem2Port.req.ready)
mem2PortHadReadyRequest := !AllOffloadsReady && (mem2PortHadReadyRequest || mem2Port.req.valid)
mem3_ready_received := !(AllOffloadsReady) && (mem3_ready_received || mem3Port.req.ready)
mem3PortHadReadyRequest := !AllOffloadsReady && (mem3PortHadReadyRequest || mem3Port.req.valid)


/******************Valid stage handler************************/
val mem1PortHadValidRequest = Vec(numOfThreads) {Reg(resetVal=Bool(false))}
val mem1_valid_received = Vec(numOfThreads) {Reg(resetVal=Bool(false))}
val mem2PortHadValidRequest = Vec(numOfThreads) {Reg(resetVal=Bool(false))}
val mem2_valid_received = Vec(numOfThreads) {Reg(resetVal=Bool(false))}
val mem3PortHadValidRequest = Vec(numOfThreads) {Reg(resetVal=Bool(false))}
val mem3_valid_received = Vec(numOfThreads) {Reg(resetVal=Bool(false))}

for (i <- 0 to numOfThreads-1) {
AllOffloadsValid(i) :=  
  ((mem1Port.rep.valid && (mem1Port.rep.tag === UFix(i, 5)))|| mem1_valid_received(i) || !mem1PortHadValidRequest(i)) &&  
  ((mem2Port.rep.valid && (mem2Port.rep.tag === UFix(i, 5)))|| mem2_valid_received(i) || !mem2PortHadValidRequest(i)) &&  
  ((mem3Port.rep.valid && (mem3Port.rep.tag === UFix(i, 5)))|| mem3_valid_received(i) || !mem3PortHadValidRequest(i)) &&  
  Bool(true)

mem1_valid_received(i) := !(vThread === UFix(i, 5)) && ((mem1_valid_received(i)) || (mem1Port.rep.valid && mem1Port.rep.tag === UFix(i, 5)))
mem1PortHadValidRequest(i) := !(vThread === UFix(i,5)) && (mem1PortHadValidRequest(i) || (UFix(i,5)===rThread && mem1Port.req.valid)/*(mem1PortHadReadyRequest && AllOffloadsReady && (UFix(i,5) === rThread))*/)
mem2_valid_received(i) := !(vThread === UFix(i, 5)) && ((mem2_valid_received(i)) || (mem2Port.rep.valid && mem2Port.rep.tag === UFix(i, 5)))
mem2PortHadValidRequest(i) := !(vThread === UFix(i,5)) && (mem2PortHadValidRequest(i) || (UFix(i,5)===rThread && mem2Port.req.valid)/*(mem2PortHadReadyRequest && AllOffloadsReady && (UFix(i,5) === rThread))*/)
mem3_valid_received(i) := !(vThread === UFix(i, 5)) && ((mem3_valid_received(i)) || (mem3Port.rep.valid && mem3Port.rep.tag === UFix(i, 5)))
mem3PortHadValidRequest(i) := !(vThread === UFix(i,5)) && (mem3PortHadValidRequest(i) || (UFix(i,5)===rThread && mem3Port.req.valid)/*(mem3PortHadReadyRequest && AllOffloadsReady && (UFix(i,5) === rThread))*/)
}

   memReq1.addr:=(inputReg(rThread)>>LOOKUP_FIRST_LEVEL_SHIFT) 
   memReq1.rw:=UFix(1, width = 32) 
   memReq1.wData:=inputReg(rThread)>>UFix(16, width = 32) 
   memReq1.rw:=UFix(0, width = 32) 
   memRep1 := mem1Port.rep.bits
   memReq2.addr:=(LOOKUP_SECOND_LEVEL_BASE+(result(rThread)<<LOOKUP_SECOND_LEVEL_CHUNK_SIZE_LOG)+((inputReg(rThread)>>(LOOKUP_SECOND_LEVEL_SHIFT))&LOOKUP_SECOND_LEVEL_MASK)) 
   memReq2.rw:=UFix(1, width = 32) 
   memReq2.wData:=inputReg(rThread)>>UFix(16, width = 32) 
   memReq2.rw:=UFix(0, width = 32) 
   memRep2 := mem2Port.rep.bits
   memReq3.addr:=LOOKUP_THIRD_LEVEL_BASE+(result(rThread)<<LOOKUP_THIRD_LEVEL_CHUNK_SIZE_LOG)+((inputReg(rThread)>>(LOOKUP_THIRD_LEVEL_SHIFT))&LOOKUP_THIRD_LEVEL_MASK) 
   memReq3.rw:=UFix(1, width = 32) 
   memReq3.wData:=inputReg(rThread)>>UFix(16, width = 32) 
   memReq3.rw:=UFix(0, width = 32) 
   memRep3 := mem3Port.rep.bits
   mem2Port.req.tag :=  rThread
   mem2Port.req.valid :=  (rThread != NONE_SELECTED) && !mem2_valid_received(rThread) && ( (rThread != NONE_SELECTED && State(rThread) === GS_SECOND_LEVEL))
   mem2Port.req.bits := MuxCase(UFix(0, 32),Seq( ((rThread != NONE_SELECTED && State(rThread) === GS_SECOND_LEVEL),memReq2)))
   mem1Port.req.tag :=  rThread
   mem1Port.req.valid :=  (rThread != NONE_SELECTED) && !mem1_valid_received(rThread) && ( (rThread != NONE_SELECTED && State(rThread) === GS_FIRST_LEVEL))
   mem1Port.req.bits := MuxCase(UFix(0, 32),Seq( ((rThread != NONE_SELECTED && State(rThread) === GS_FIRST_LEVEL),memReq1)))
   mem3Port.req.tag :=  rThread
   mem3Port.req.valid :=  (rThread != NONE_SELECTED) && !mem3_valid_received(rThread) && ( (rThread != NONE_SELECTED && State(rThread) === GS_THIRD_LEVEL))
   mem3Port.req.bits := MuxCase(UFix(0, 32),Seq( ((rThread != NONE_SELECTED && State(rThread) === GS_THIRD_LEVEL),memReq3)))

  when (sThread != NONE_SELECTED && io.in.valid) {
    inputReg(sThread) := io.in.bits
    inputTag(sThread) := io.in.tag
    State(sThread) := GS_FIRST_LEVEL
 
  } 

  when (rThread != NONE_SELECTED && State(rThread) === WaitForOutputReady && io.out.ready) {
    State(rThread) := EmitReturnState(rThread) 
  } 

   
    when (vThread != NONE_SELECTED &&State(vThread) === GS_FIRST_LEVEL){
   when (((inputReg(vThread)&UFix(65535, width = 32))^UFix(1000, width = 32))===UFix(0, width = 32)) {
   }
   .otherwise {
   }
   result(vThread):=memRep1.rData 
   State(vThread):=GS_SECOND_LEVEL 
   } 
   
    when (vThread != NONE_SELECTED &&State(vThread) === GS_SECOND_LEVEL){
   when (((inputReg(vThread)&UFix(65534, width = 32))^UFix(1000, width = 32))===UFix(0, width = 32)) {
   }
   .otherwise {
   }
   result(vThread):=memRep2.rData 
   State(vThread):=GS_THIRD_LEVEL 
   } 
   
    when (vThread != NONE_SELECTED &&State(vThread) === GS_THIRD_LEVEL){
   when (((inputReg(vThread)&UFix(65533, width = 32))^UFix(1000, width = 32))===UFix(0, width = 32)) {
   }
   .otherwise {
   }
   outputReg(vThread):=memRep3.rData 
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
    if (n == "mem1") {
      when (IsPcReset) {
        offloadRateArray(portId-3) := UFix(0, Pcounters.PCWIDTH)
      } .elsewhen (i.asInstanceOf[gOffBundle[Bundle, Bundle]].req.ready && 
       (mem1PortHadValidRequest(0) || mem1Port.req.valid) && !pcPaused) {
        offloadRateArray(portId-3) := offloadRateArray(portId-3) + UFix(1, Pcounters.PCWIDTH)
      }
    }
    if (n == "mem2") {
      when (IsPcReset) {
        offloadRateArray(portId-3) := UFix(0, Pcounters.PCWIDTH)
      } .elsewhen (i.asInstanceOf[gOffBundle[Bundle, Bundle]].req.ready && 
       (mem2PortHadValidRequest(0) || mem2Port.req.valid) && !pcPaused) {
        offloadRateArray(portId-3) := offloadRateArray(portId-3) + UFix(1, Pcounters.PCWIDTH)
      }
    }
    if (n == "mem3") {
      when (IsPcReset) {
        offloadRateArray(portId-3) := UFix(0, Pcounters.PCWIDTH)
      } .elsewhen (i.asInstanceOf[gOffBundle[Bundle, Bundle]].req.ready && 
       (mem3PortHadValidRequest(0) || mem3Port.req.valid) && !pcPaused) {
        offloadRateArray(portId-3) := offloadRateArray(portId-3) + UFix(1, Pcounters.PCWIDTH)
      }
    }
    portId = portId + 1
  }
}

