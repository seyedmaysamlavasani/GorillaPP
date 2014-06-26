package Tutorial

import Chisel._
import Node._
import Literal._
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer


class qosCount (extCompName:String) extends gComponentLeaf  (() => UFix(width = 32))(() => UFix(width = 8)) (ArrayBuffer(("mem1Read", () => new memReadOnlyReq32_t , () => new memReadOnlyRep32_t), ("mem1Write", () => new memWriteOnlyReq32_t , () => new memWriteOnlyRep32_t), ("mem2Read", () => new memReadOnlyReq32_t , () => new memReadOnlyRep32_t), ("mem2Write", () => new memWriteOnlyReq32_t , () => new memWriteOnlyRep32_t), ("mem3Read", () => new memReadOnlyReq32_t , () => new memReadOnlyRep32_t), ("mem3Write", () => new memWriteOnlyReq32_t , () => new memWriteOnlyRep32_t)))  (extCompName = extCompName + "__type__engine__MT__1__") with include {
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
   val carry2 = Vec (numOfThreads) {Reg(UFix(width = 1))}	//Global variable 
   val carry1 = Vec (numOfThreads) {Reg(UFix(width = 1))}	//Global variable 
   val inputReg = Vec(numOfThreads) {Reg(UFix(width = 32))}
   val outputReg = Vec(numOfThreads) {Reg(UFix(width = 8))}
   def mymyOffmem3Write = myOff.asInstanceOf[Bundle].elements.find(_._1 == "mem3Write").getOrElse(elseV)._2
   val mem3WritePort = new gOffBundleND(() => new memWriteOnlyReq32_t, () => new memWriteOnlyRep32_t)
   mem3WritePort <> mymyOffmem3Write
   def mymyOffmem2Read = myOff.asInstanceOf[Bundle].elements.find(_._1 == "mem2Read").getOrElse(elseV)._2
   val mem2ReadPort = new gOffBundleND(() => new memReadOnlyReq32_t, () => new memReadOnlyRep32_t)
   mem2ReadPort <> mymyOffmem2Read
   def mymyOffmem3Read = myOff.asInstanceOf[Bundle].elements.find(_._1 == "mem3Read").getOrElse(elseV)._2
   val mem3ReadPort = new gOffBundleND(() => new memReadOnlyReq32_t, () => new memReadOnlyRep32_t)
   mem3ReadPort <> mymyOffmem3Read
   def mymyOffmem1Write = myOff.asInstanceOf[Bundle].elements.find(_._1 == "mem1Write").getOrElse(elseV)._2
   val mem1WritePort = new gOffBundleND(() => new memWriteOnlyReq32_t, () => new memWriteOnlyRep32_t)
   mem1WritePort <> mymyOffmem1Write
   def mymyOffmem1Read = myOff.asInstanceOf[Bundle].elements.find(_._1 == "mem1Read").getOrElse(elseV)._2
   val mem1ReadPort = new gOffBundleND(() => new memReadOnlyReq32_t, () => new memReadOnlyRep32_t)
   mem1ReadPort <> mymyOffmem1Read
   def mymyOffmem2Write = myOff.asInstanceOf[Bundle].elements.find(_._1 == "mem2Write").getOrElse(elseV)._2
   val mem2WritePort = new gOffBundleND(() => new memWriteOnlyReq32_t, () => new memWriteOnlyRep32_t)
   mem2WritePort <> mymyOffmem2Write
   val GS_FIRST_LEVEL = UFix(1)
   val memReq1 = new memReadOnlyReq32_t
   val memRep1 = new memReadOnlyRep32_t
   val GS_SECOND_LEVEL = UFix(2)
   val memReq2 = new memWriteOnlyReq32_t
   val memRep2 = new memWriteOnlyRep32_t
   val GS_THIRD_LEVEL = UFix(3)
   val memReq3 = new memReadOnlyReq32_t
   val memRep3 = new memReadOnlyRep32_t
   val GS_FOURTH_LEVEL = UFix(4)
   val memReq4 = new memWriteOnlyReq32_t
   val memRep4 = new memWriteOnlyRep32_t
   val GS_FIFTH_LEVEL = UFix(5)
   val memReq5 = new memReadOnlyReq32_t
   val memRep5 = new memReadOnlyRep32_t
   val GS_SIXTH_LEVEL = UFix(6)
   val memReq6 = new memWriteOnlyReq32_t
   val memRep6 = new memWriteOnlyRep32_t

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

  mem1ReadPort.rep.ready := Bool(true)
  mem1WritePort.rep.ready := Bool(true)
  mem2ReadPort.rep.ready := Bool(true)
  mem2WritePort.rep.ready := Bool(true)
  mem3ReadPort.rep.ready := Bool(true)
  mem3WritePort.rep.ready := Bool(true)

/******************Ready stage handler************************/
val mem1ReadPortHadReadyRequest = Reg(resetVal=Bool(false))
val mem1Read_ready_received = Reg(resetVal=Bool(false))
val mem1WritePortHadReadyRequest = Reg(resetVal=Bool(false))
val mem1Write_ready_received = Reg(resetVal=Bool(false))
val mem2ReadPortHadReadyRequest = Reg(resetVal=Bool(false))
val mem2Read_ready_received = Reg(resetVal=Bool(false))
val mem2WritePortHadReadyRequest = Reg(resetVal=Bool(false))
val mem2Write_ready_received = Reg(resetVal=Bool(false))
val mem3ReadPortHadReadyRequest = Reg(resetVal=Bool(false))
val mem3Read_ready_received = Reg(resetVal=Bool(false))
val mem3WritePortHadReadyRequest = Reg(resetVal=Bool(false))
val mem3Write_ready_received = Reg(resetVal=Bool(false))

AllOffloadsReady :=  
  (mem1ReadPort.req.ready || mem1Read_ready_received || (!mem1ReadPortHadReadyRequest && !mem1ReadPort.req.valid)) &&  
  (mem1WritePort.req.ready || mem1Write_ready_received || (!mem1WritePortHadReadyRequest && !mem1WritePort.req.valid)) &&  
  (mem2ReadPort.req.ready || mem2Read_ready_received || (!mem2ReadPortHadReadyRequest && !mem2ReadPort.req.valid)) &&  
  (mem2WritePort.req.ready || mem2Write_ready_received || (!mem2WritePortHadReadyRequest && !mem2WritePort.req.valid)) &&  
  (mem3ReadPort.req.ready || mem3Read_ready_received || (!mem3ReadPortHadReadyRequest && !mem3ReadPort.req.valid)) &&  
  (mem3WritePort.req.ready || mem3Write_ready_received || (!mem3WritePortHadReadyRequest && !mem3WritePort.req.valid)) &&  
  Bool(true)

mem1Read_ready_received := !(AllOffloadsReady) && (mem1Read_ready_received || mem1ReadPort.req.ready)
mem1ReadPortHadReadyRequest := !AllOffloadsReady && (mem1ReadPortHadReadyRequest || mem1ReadPort.req.valid)
mem1Write_ready_received := !(AllOffloadsReady) && (mem1Write_ready_received || mem1WritePort.req.ready)
mem1WritePortHadReadyRequest := !AllOffloadsReady && (mem1WritePortHadReadyRequest || mem1WritePort.req.valid)
mem2Read_ready_received := !(AllOffloadsReady) && (mem2Read_ready_received || mem2ReadPort.req.ready)
mem2ReadPortHadReadyRequest := !AllOffloadsReady && (mem2ReadPortHadReadyRequest || mem2ReadPort.req.valid)
mem2Write_ready_received := !(AllOffloadsReady) && (mem2Write_ready_received || mem2WritePort.req.ready)
mem2WritePortHadReadyRequest := !AllOffloadsReady && (mem2WritePortHadReadyRequest || mem2WritePort.req.valid)
mem3Read_ready_received := !(AllOffloadsReady) && (mem3Read_ready_received || mem3ReadPort.req.ready)
mem3ReadPortHadReadyRequest := !AllOffloadsReady && (mem3ReadPortHadReadyRequest || mem3ReadPort.req.valid)
mem3Write_ready_received := !(AllOffloadsReady) && (mem3Write_ready_received || mem3WritePort.req.ready)
mem3WritePortHadReadyRequest := !AllOffloadsReady && (mem3WritePortHadReadyRequest || mem3WritePort.req.valid)


/******************Valid stage handler************************/
val mem1ReadPortHadValidRequest = Vec(numOfThreads) {Reg(resetVal=Bool(false))}
val mem1Read_valid_received = Vec(numOfThreads) {Reg(resetVal=Bool(false))}
val mem1WritePortHadValidRequest = Vec(numOfThreads) {Reg(resetVal=Bool(false))}
val mem1Write_valid_received = Vec(numOfThreads) {Reg(resetVal=Bool(false))}
val mem2ReadPortHadValidRequest = Vec(numOfThreads) {Reg(resetVal=Bool(false))}
val mem2Read_valid_received = Vec(numOfThreads) {Reg(resetVal=Bool(false))}
val mem2WritePortHadValidRequest = Vec(numOfThreads) {Reg(resetVal=Bool(false))}
val mem2Write_valid_received = Vec(numOfThreads) {Reg(resetVal=Bool(false))}
val mem3ReadPortHadValidRequest = Vec(numOfThreads) {Reg(resetVal=Bool(false))}
val mem3Read_valid_received = Vec(numOfThreads) {Reg(resetVal=Bool(false))}
val mem3WritePortHadValidRequest = Vec(numOfThreads) {Reg(resetVal=Bool(false))}
val mem3Write_valid_received = Vec(numOfThreads) {Reg(resetVal=Bool(false))}

for (i <- 0 to numOfThreads-1) {
AllOffloadsValid(i) :=  
  ((mem1ReadPort.rep.valid && (mem1ReadPort.rep.tag === UFix(i, 5)))|| mem1Read_valid_received(i) || !mem1ReadPortHadValidRequest(i)) &&  
  ((mem1WritePort.rep.valid && (mem1WritePort.rep.tag === UFix(i, 5)))|| mem1Write_valid_received(i) || !mem1WritePortHadValidRequest(i)) &&  
  ((mem2ReadPort.rep.valid && (mem2ReadPort.rep.tag === UFix(i, 5)))|| mem2Read_valid_received(i) || !mem2ReadPortHadValidRequest(i)) &&  
  ((mem2WritePort.rep.valid && (mem2WritePort.rep.tag === UFix(i, 5)))|| mem2Write_valid_received(i) || !mem2WritePortHadValidRequest(i)) &&  
  ((mem3ReadPort.rep.valid && (mem3ReadPort.rep.tag === UFix(i, 5)))|| mem3Read_valid_received(i) || !mem3ReadPortHadValidRequest(i)) &&  
  ((mem3WritePort.rep.valid && (mem3WritePort.rep.tag === UFix(i, 5)))|| mem3Write_valid_received(i) || !mem3WritePortHadValidRequest(i)) &&  
  Bool(true)

mem1Read_valid_received(i) := !(vThread === UFix(i, 5)) && ((mem1Read_valid_received(i)) || (mem1ReadPort.rep.valid && mem1ReadPort.rep.tag === UFix(i, 5)))
mem1ReadPortHadValidRequest(i) := !(vThread === UFix(i,5)) && (mem1ReadPortHadValidRequest(i) || (UFix(i,5)===rThread && mem1ReadPort.req.valid)/*(mem1ReadPortHadReadyRequest && AllOffloadsReady && (UFix(i,5) === rThread))*/)
mem1Write_valid_received(i) := !(vThread === UFix(i, 5)) && ((mem1Write_valid_received(i)) || (mem1WritePort.rep.valid && mem1WritePort.rep.tag === UFix(i, 5)))
mem1WritePortHadValidRequest(i) := !(vThread === UFix(i,5)) && (mem1WritePortHadValidRequest(i) || (UFix(i,5)===rThread && mem1WritePort.req.valid)/*(mem1WritePortHadReadyRequest && AllOffloadsReady && (UFix(i,5) === rThread))*/)
mem2Read_valid_received(i) := !(vThread === UFix(i, 5)) && ((mem2Read_valid_received(i)) || (mem2ReadPort.rep.valid && mem2ReadPort.rep.tag === UFix(i, 5)))
mem2ReadPortHadValidRequest(i) := !(vThread === UFix(i,5)) && (mem2ReadPortHadValidRequest(i) || (UFix(i,5)===rThread && mem2ReadPort.req.valid)/*(mem2ReadPortHadReadyRequest && AllOffloadsReady && (UFix(i,5) === rThread))*/)
mem2Write_valid_received(i) := !(vThread === UFix(i, 5)) && ((mem2Write_valid_received(i)) || (mem2WritePort.rep.valid && mem2WritePort.rep.tag === UFix(i, 5)))
mem2WritePortHadValidRequest(i) := !(vThread === UFix(i,5)) && (mem2WritePortHadValidRequest(i) || (UFix(i,5)===rThread && mem2WritePort.req.valid)/*(mem2WritePortHadReadyRequest && AllOffloadsReady && (UFix(i,5) === rThread))*/)
mem3Read_valid_received(i) := !(vThread === UFix(i, 5)) && ((mem3Read_valid_received(i)) || (mem3ReadPort.rep.valid && mem3ReadPort.rep.tag === UFix(i, 5)))
mem3ReadPortHadValidRequest(i) := !(vThread === UFix(i,5)) && (mem3ReadPortHadValidRequest(i) || (UFix(i,5)===rThread && mem3ReadPort.req.valid)/*(mem3ReadPortHadReadyRequest && AllOffloadsReady && (UFix(i,5) === rThread))*/)
mem3Write_valid_received(i) := !(vThread === UFix(i, 5)) && ((mem3Write_valid_received(i)) || (mem3WritePort.rep.valid && mem3WritePort.rep.tag === UFix(i, 5)))
mem3WritePortHadValidRequest(i) := !(vThread === UFix(i,5)) && (mem3WritePortHadValidRequest(i) || (UFix(i,5)===rThread && mem3WritePort.req.valid)/*(mem3WritePortHadReadyRequest && AllOffloadsReady && (UFix(i,5) === rThread))*/)
}

   memReq1.addr:=inputReg(rThread) 
   memRep1 := mem1ReadPort.rep.bits
   memReq2.addr:=inputReg(rThread) 
   memReq2.data:=result(rThread)+UFix(1, width = 32) 
   memRep2 := mem1WritePort.rep.bits
   memReq3.addr:=inputReg(rThread) 
   memRep3 := mem2ReadPort.rep.bits
   memReq4.addr:=inputReg(rThread) 
   memReq4.data:=result(rThread)+UFix(1, width = 32) 
   memReq4.data:=result(rThread) 
   memRep4 := mem2WritePort.rep.bits
   memReq5.addr:=inputReg(rThread) 
   memRep5 := mem3ReadPort.rep.bits
   memReq6.addr:=inputReg(rThread) 
   memReq6.data:=result(rThread)+UFix(1, width = 32) 
   memReq6.data:=result(rThread) 
   memRep6 := mem3WritePort.rep.bits
   mem3WritePort.req.tag :=  rThread
   mem3WritePort.req.valid :=  (rThread != NONE_SELECTED) && !mem3Write_valid_received(rThread) && ( (rThread != NONE_SELECTED && State(rThread) === GS_SIXTH_LEVEL))
   mem3WritePort.req.bits := MuxCase(UFix(0, 32),Seq( ((rThread != NONE_SELECTED && State(rThread) === GS_SIXTH_LEVEL),memReq6)))
   mem2ReadPort.req.tag :=  rThread
   mem2ReadPort.req.valid :=  (rThread != NONE_SELECTED) && !mem2Read_valid_received(rThread) && ( (rThread != NONE_SELECTED && State(rThread) === GS_THIRD_LEVEL))
   mem2ReadPort.req.bits := MuxCase(UFix(0, 32),Seq( ((rThread != NONE_SELECTED && State(rThread) === GS_THIRD_LEVEL),memReq3)))
   mem3ReadPort.req.tag :=  rThread
   mem3ReadPort.req.valid :=  (rThread != NONE_SELECTED) && !mem3Read_valid_received(rThread) && ( (rThread != NONE_SELECTED && State(rThread) === GS_FIFTH_LEVEL))
   mem3ReadPort.req.bits := MuxCase(UFix(0, 32),Seq( ((rThread != NONE_SELECTED && State(rThread) === GS_FIFTH_LEVEL),memReq5)))
   mem1WritePort.req.tag :=  rThread
   mem1WritePort.req.valid :=  (rThread != NONE_SELECTED) && !mem1Write_valid_received(rThread) && ( (rThread != NONE_SELECTED && State(rThread) === GS_SECOND_LEVEL))
   mem1WritePort.req.bits := MuxCase(UFix(0, 32),Seq( ((rThread != NONE_SELECTED && State(rThread) === GS_SECOND_LEVEL),memReq2)))
   mem1ReadPort.req.tag :=  rThread
   mem1ReadPort.req.valid :=  (rThread != NONE_SELECTED) && !mem1Read_valid_received(rThread) && ( (rThread != NONE_SELECTED && State(rThread) === GS_FIRST_LEVEL))
   mem1ReadPort.req.bits := MuxCase(UFix(0, 32),Seq( ((rThread != NONE_SELECTED && State(rThread) === GS_FIRST_LEVEL),memReq1)))
   mem2WritePort.req.tag :=  rThread
   mem2WritePort.req.valid :=  (rThread != NONE_SELECTED) && !mem2Write_valid_received(rThread) && ( (rThread != NONE_SELECTED && State(rThread) === GS_FOURTH_LEVEL))
   mem2WritePort.req.bits := MuxCase(UFix(0, 32),Seq( ((rThread != NONE_SELECTED && State(rThread) === GS_FOURTH_LEVEL),memReq4)))

  when (sThread != NONE_SELECTED && io.in.valid) {
    inputReg(sThread) := io.in.bits
    inputTag(sThread) := io.in.tag
    State(sThread) := GS_FIRST_LEVEL
 
  } 

  when (rThread != NONE_SELECTED && State(rThread) === WaitForOutputReady && io.out.ready) {
    State(rThread) := EmitReturnState(rThread) 
  } 

   
    when (vThread != NONE_SELECTED &&State(vThread) === GS_FIRST_LEVEL){
   result(vThread):=memRep1.data 
   State(vThread):=GS_SECOND_LEVEL 
   } 
   
    when (vThread != NONE_SELECTED &&State(vThread) === GS_SECOND_LEVEL){
   when (result(vThread)===UFix(0, width = 32)) {
   carry1(vThread):=UFix(1, width = 32) 
   }
   .otherwise {
   carry1(vThread):=UFix(0, width = 32) 
   }
   State(vThread):=GS_THIRD_LEVEL 
   } 
   
    when (vThread != NONE_SELECTED &&State(vThread) === GS_THIRD_LEVEL){
   result(vThread):=memRep3.data 
   State(vThread):=GS_FOURTH_LEVEL 
   } 
   
    when (vThread != NONE_SELECTED &&State(vThread) === GS_FOURTH_LEVEL){
   when (carry1(vThread)) {
   }
   .otherwise {
   }
   when (carry1(vThread)&&result(vThread)===UFix(0, width = 32)) {
   carry2(vThread):=UFix(1, width = 32) 
   }
   .otherwise {
   carry2(vThread):=UFix(0, width = 32) 
   }
   State(vThread):=GS_FIFTH_LEVEL 
   } 
   
    when (vThread != NONE_SELECTED &&State(vThread) === GS_FIFTH_LEVEL){
   result(vThread):=memRep5.data 
   State(vThread):=GS_SIXTH_LEVEL 
   } 
   
    when (vThread != NONE_SELECTED &&State(vThread) === GS_SIXTH_LEVEL){
   when (carry2(vThread)) {
   }
   .otherwise {
   }
   outputReg(vThread):=UFix(1, width = 32) 
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
    if (n == "mem1Read") {
      when (IsPcReset) {
        offloadRateArray(portId-3) := UFix(0, Pcounters.PCWIDTH)
      } .elsewhen (i.asInstanceOf[gOffBundle[Bundle, Bundle]].req.ready && 
       (mem1ReadPortHadValidRequest(0) || mem1ReadPort.req.valid) && !pcPaused) {
        offloadRateArray(portId-3) := offloadRateArray(portId-3) + UFix(1, Pcounters.PCWIDTH)
      }
    }
    if (n == "mem1Write") {
      when (IsPcReset) {
        offloadRateArray(portId-3) := UFix(0, Pcounters.PCWIDTH)
      } .elsewhen (i.asInstanceOf[gOffBundle[Bundle, Bundle]].req.ready && 
       (mem1WritePortHadValidRequest(0) || mem1WritePort.req.valid) && !pcPaused) {
        offloadRateArray(portId-3) := offloadRateArray(portId-3) + UFix(1, Pcounters.PCWIDTH)
      }
    }
    if (n == "mem2Read") {
      when (IsPcReset) {
        offloadRateArray(portId-3) := UFix(0, Pcounters.PCWIDTH)
      } .elsewhen (i.asInstanceOf[gOffBundle[Bundle, Bundle]].req.ready && 
       (mem2ReadPortHadValidRequest(0) || mem2ReadPort.req.valid) && !pcPaused) {
        offloadRateArray(portId-3) := offloadRateArray(portId-3) + UFix(1, Pcounters.PCWIDTH)
      }
    }
    if (n == "mem2Write") {
      when (IsPcReset) {
        offloadRateArray(portId-3) := UFix(0, Pcounters.PCWIDTH)
      } .elsewhen (i.asInstanceOf[gOffBundle[Bundle, Bundle]].req.ready && 
       (mem2WritePortHadValidRequest(0) || mem2WritePort.req.valid) && !pcPaused) {
        offloadRateArray(portId-3) := offloadRateArray(portId-3) + UFix(1, Pcounters.PCWIDTH)
      }
    }
    if (n == "mem3Read") {
      when (IsPcReset) {
        offloadRateArray(portId-3) := UFix(0, Pcounters.PCWIDTH)
      } .elsewhen (i.asInstanceOf[gOffBundle[Bundle, Bundle]].req.ready && 
       (mem3ReadPortHadValidRequest(0) || mem3ReadPort.req.valid) && !pcPaused) {
        offloadRateArray(portId-3) := offloadRateArray(portId-3) + UFix(1, Pcounters.PCWIDTH)
      }
    }
    if (n == "mem3Write") {
      when (IsPcReset) {
        offloadRateArray(portId-3) := UFix(0, Pcounters.PCWIDTH)
      } .elsewhen (i.asInstanceOf[gOffBundle[Bundle, Bundle]].req.ready && 
       (mem3WritePortHadValidRequest(0) || mem3WritePort.req.valid) && !pcPaused) {
        offloadRateArray(portId-3) := offloadRateArray(portId-3) + UFix(1, Pcounters.PCWIDTH)
      }
    }
    portId = portId + 1
  }
}

