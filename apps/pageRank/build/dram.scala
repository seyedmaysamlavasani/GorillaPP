package Tutorial

import Chisel._
import Node._
import Literal._
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer


class dram (extCompName:String) extends gComponentLeaf  (() => new mem_req_t)(() => new mem_rep_t) (ArrayBuffer(("dramBank0", () => UFix(width = 32) , () => UFix(width = 32)), ("dramBank1", () => UFix(width = 32) , () => UFix(width = 32)), ("dramBank2", () => UFix(width = 32) , () => UFix(width = 32)), ("dramBank3", () => UFix(width = 32) , () => UFix(width = 32)), ("dramBank4", () => UFix(width = 32) , () => UFix(width = 32)), ("dramBank5", () => UFix(width = 32) , () => UFix(width = 32)), ("dramBank6", () => UFix(width = 32) , () => UFix(width = 32)), ("dramBank7", () => UFix(width = 32) , () => UFix(width = 32))))  (extCompName = extCompName + "__type__engine__MT__8__") with include {
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
   val	RB_HIT_DELAY = UFix(3, width = 32)//constant macro definition 
   val	RB_MISS_DELAY = UFix(7, width = 32)//constant macro definition 
   val	CHIP_ADDR_WIDTH = UFix(3, width = 32)//constant macro definition 
   val	COLUMN_ADDR_WIDTH = UFix(12, width = 32)//constant macro definition 
   val	BANK_ADDR_WIDTH = UFix(3, width = 32)//constant macro definition 
   val	ROW_ADDR_WIDTH = UFix(12, width = 32)//constant macro definition 
   val	CHIP_SHIFT_R = UFix(0, width = 32)//constant macro definition 
   val	CHIP_SHIFT_L = (CHIP_SHIFT_R+CHIP_ADDR_WIDTH)//constant macro definition 
   val	COLUMN_SHIFT_R = CHIP_SHIFT_L//constant macro definition 
   val	COLUMN_SHIFT_L = (CHIP_SHIFT_R+COLUMN_ADDR_WIDTH)//constant macro definition 
   val	BANK_SHIFT_R = COLUMN_SHIFT_L//constant macro definition 
   val	BANK_SHIFT_L = (BANK_ADDR_WIDTH+BANK_SHIFT_R)//constant macro definition 
   val	ROW_SHIFT_R = BANK_SHIFT_L//constant macro definition 
   val	ROW_SHIFT_L = (ROW_SHIFT_R+ROW_ADDR_WIDTH)//constant macro definition 
   def chip(addr:UFix) = ((addr>>CHIP_SHIFT_R)& ((UFix(1, width = 32)<<CHIP_SHIFT_L)-UFix(1, width = 32)))//constant macro definition 
   def column(addr:UFix) = ((addr>>COLUMN_SHIFT_R)& ((UFix(1, width = 32)<<COLUMN_SHIFT_L)-UFix(1, width = 32)))//constant macro definition 
   def bank(addr:UFix) = ((addr>>BANK_SHIFT_R)& ((UFix(1, width = 32)<<BANK_SHIFT_L)-UFix(1, width = 32)))//constant macro definition 
   def row(addr:UFix) = ((addr>>ROW_SHIFT_R)& ((UFix(1, width = 32)<<ROW_SHIFT_L)-UFix(1, width = 32)))//constant macro definition 
   val rb0RowAddr = Vec (numOfThreads) {Reg(UFix(width = 32), resetVal=UFix(1, width = 32))}	//Global variable 
   val rb1RowAddr = Vec (numOfThreads) {Reg(UFix(width = 32), resetVal=UFix(0, width = 32))}	//Global variable 
   val rb2RowAddr = Vec (numOfThreads) {Reg(UFix(width = 32), resetVal=UFix(0, width = 32))}	//Global variable 
   val rb3RowAddr = Vec (numOfThreads) {Reg(UFix(width = 32), resetVal=UFix(0, width = 32))}	//Global variable 
   val rb4RowAddr = Vec (numOfThreads) {Reg(UFix(width = 32), resetVal=UFix(0, width = 32))}	//Global variable 
   val rb5RowAddr = Vec (numOfThreads) {Reg(UFix(width = 32), resetVal=UFix(0, width = 32))}	//Global variable 
   val rb6RowAddr = Vec (numOfThreads) {Reg(UFix(width = 32), resetVal=UFix(0, width = 32))}	//Global variable 
   val rb7RowAddr = Vec (numOfThreads) {Reg(UFix(width = 32), resetVal=UFix(0, width = 32))}	//Global variable 
   val bankAddr = Vec (numOfThreads) {Reg(UFix(width = 32))}	//Global variable 
   val rowAddr = Vec (numOfThreads) {Reg(UFix(width = 32))}	//Global variable 
   val inputReg = Vec(numOfThreads) {Reg(new mem_req_t)}
   val outputReg = Vec(numOfThreads) {Reg(new mem_rep_t)}
   def mymyOffdramBank2 = myOff.asInstanceOf[Bundle].elements.find(_._1 == "dramBank2").getOrElse(elseV)._2
   val dramBank2Port = new gOffBundleND(() => UFix(width = 32), () => UFix(width = 32))
   dramBank2Port <> mymyOffdramBank2
   val dramBank2PortReplyStorage = Vec(numOfThreads) {Reg(UFix(width = 32))}
   def mymyOffdramBank3 = myOff.asInstanceOf[Bundle].elements.find(_._1 == "dramBank3").getOrElse(elseV)._2
   val dramBank3Port = new gOffBundleND(() => UFix(width = 32), () => UFix(width = 32))
   dramBank3Port <> mymyOffdramBank3
   val dramBank3PortReplyStorage = Vec(numOfThreads) {Reg(UFix(width = 32))}
   def mymyOffdramBank4 = myOff.asInstanceOf[Bundle].elements.find(_._1 == "dramBank4").getOrElse(elseV)._2
   val dramBank4Port = new gOffBundleND(() => UFix(width = 32), () => UFix(width = 32))
   dramBank4Port <> mymyOffdramBank4
   val dramBank4PortReplyStorage = Vec(numOfThreads) {Reg(UFix(width = 32))}
   def mymyOffdramBank5 = myOff.asInstanceOf[Bundle].elements.find(_._1 == "dramBank5").getOrElse(elseV)._2
   val dramBank5Port = new gOffBundleND(() => UFix(width = 32), () => UFix(width = 32))
   dramBank5Port <> mymyOffdramBank5
   val dramBank5PortReplyStorage = Vec(numOfThreads) {Reg(UFix(width = 32))}
   def mymyOffdramBank0 = myOff.asInstanceOf[Bundle].elements.find(_._1 == "dramBank0").getOrElse(elseV)._2
   val dramBank0Port = new gOffBundleND(() => UFix(width = 32), () => UFix(width = 32))
   dramBank0Port <> mymyOffdramBank0
   val dramBank0PortReplyStorage = Vec(numOfThreads) {Reg(UFix(width = 32))}
   def mymyOffdramBank1 = myOff.asInstanceOf[Bundle].elements.find(_._1 == "dramBank1").getOrElse(elseV)._2
   val dramBank1Port = new gOffBundleND(() => UFix(width = 32), () => UFix(width = 32))
   dramBank1Port <> mymyOffdramBank1
   val dramBank1PortReplyStorage = Vec(numOfThreads) {Reg(UFix(width = 32))}
   def mymyOffdramBank7 = myOff.asInstanceOf[Bundle].elements.find(_._1 == "dramBank7").getOrElse(elseV)._2
   val dramBank7Port = new gOffBundleND(() => UFix(width = 32), () => UFix(width = 32))
   dramBank7Port <> mymyOffdramBank7
   val dramBank7PortReplyStorage = Vec(numOfThreads) {Reg(UFix(width = 32))}
   def mymyOffdramBank6 = myOff.asInstanceOf[Bundle].elements.find(_._1 == "dramBank6").getOrElse(elseV)._2
   val dramBank6Port = new gOffBundleND(() => UFix(width = 32), () => UFix(width = 32))
   dramBank6Port <> mymyOffdramBank6
   val dramBank6PortReplyStorage = Vec(numOfThreads) {Reg(UFix(width = 32))}
   val GS_CHECK_RB_HIT = UFix(1)
   val GS_WAIT_BANK_0 = UFix(2)
   val GS_WAIT_BANK_1 = UFix(3)
   val GS_WAIT_BANK_2 = UFix(4)
   val GS_WAIT_BANK_3 = UFix(5)
   val GS_WAIT_BANK_4 = UFix(6)
   val GS_WAIT_BANK_5 = UFix(7)
   val GS_WAIT_BANK_6 = UFix(8)
   val GS_WAIT_BANK_7 = UFix(9)
   val GS_FINISH = UFix(10)
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

  dramBank0Port.rep.ready := Bool(true)
  dramBank1Port.rep.ready := Bool(true)
  dramBank2Port.rep.ready := Bool(true)
  dramBank3Port.rep.ready := Bool(true)
  dramBank4Port.rep.ready := Bool(true)
  dramBank5Port.rep.ready := Bool(true)
  dramBank6Port.rep.ready := Bool(true)
  dramBank7Port.rep.ready := Bool(true)

/******************Ready stage handler************************/
val dramBank0PortHadReadyRequest = Reg(resetVal=Bool(false))
val dramBank0_ready_received = Reg(resetVal=Bool(false))
val dramBank1PortHadReadyRequest = Reg(resetVal=Bool(false))
val dramBank1_ready_received = Reg(resetVal=Bool(false))
val dramBank2PortHadReadyRequest = Reg(resetVal=Bool(false))
val dramBank2_ready_received = Reg(resetVal=Bool(false))
val dramBank3PortHadReadyRequest = Reg(resetVal=Bool(false))
val dramBank3_ready_received = Reg(resetVal=Bool(false))
val dramBank4PortHadReadyRequest = Reg(resetVal=Bool(false))
val dramBank4_ready_received = Reg(resetVal=Bool(false))
val dramBank5PortHadReadyRequest = Reg(resetVal=Bool(false))
val dramBank5_ready_received = Reg(resetVal=Bool(false))
val dramBank6PortHadReadyRequest = Reg(resetVal=Bool(false))
val dramBank6_ready_received = Reg(resetVal=Bool(false))
val dramBank7PortHadReadyRequest = Reg(resetVal=Bool(false))
val dramBank7_ready_received = Reg(resetVal=Bool(false))

AllOffloadsReady :=  
  (dramBank0Port.req.ready || dramBank0_ready_received || (!dramBank0PortHadReadyRequest && !dramBank0Port.req.valid)) &&  
  (dramBank1Port.req.ready || dramBank1_ready_received || (!dramBank1PortHadReadyRequest && !dramBank1Port.req.valid)) &&  
  (dramBank2Port.req.ready || dramBank2_ready_received || (!dramBank2PortHadReadyRequest && !dramBank2Port.req.valid)) &&  
  (dramBank3Port.req.ready || dramBank3_ready_received || (!dramBank3PortHadReadyRequest && !dramBank3Port.req.valid)) &&  
  (dramBank4Port.req.ready || dramBank4_ready_received || (!dramBank4PortHadReadyRequest && !dramBank4Port.req.valid)) &&  
  (dramBank5Port.req.ready || dramBank5_ready_received || (!dramBank5PortHadReadyRequest && !dramBank5Port.req.valid)) &&  
  (dramBank6Port.req.ready || dramBank6_ready_received || (!dramBank6PortHadReadyRequest && !dramBank6Port.req.valid)) &&  
  (dramBank7Port.req.ready || dramBank7_ready_received || (!dramBank7PortHadReadyRequest && !dramBank7Port.req.valid)) &&  
  Bool(true)

dramBank0_ready_received := !(AllOffloadsReady) && (dramBank0_ready_received || dramBank0Port.req.ready)
dramBank0PortHadReadyRequest := !AllOffloadsReady && (dramBank0PortHadReadyRequest || dramBank0Port.req.valid)
dramBank1_ready_received := !(AllOffloadsReady) && (dramBank1_ready_received || dramBank1Port.req.ready)
dramBank1PortHadReadyRequest := !AllOffloadsReady && (dramBank1PortHadReadyRequest || dramBank1Port.req.valid)
dramBank2_ready_received := !(AllOffloadsReady) && (dramBank2_ready_received || dramBank2Port.req.ready)
dramBank2PortHadReadyRequest := !AllOffloadsReady && (dramBank2PortHadReadyRequest || dramBank2Port.req.valid)
dramBank3_ready_received := !(AllOffloadsReady) && (dramBank3_ready_received || dramBank3Port.req.ready)
dramBank3PortHadReadyRequest := !AllOffloadsReady && (dramBank3PortHadReadyRequest || dramBank3Port.req.valid)
dramBank4_ready_received := !(AllOffloadsReady) && (dramBank4_ready_received || dramBank4Port.req.ready)
dramBank4PortHadReadyRequest := !AllOffloadsReady && (dramBank4PortHadReadyRequest || dramBank4Port.req.valid)
dramBank5_ready_received := !(AllOffloadsReady) && (dramBank5_ready_received || dramBank5Port.req.ready)
dramBank5PortHadReadyRequest := !AllOffloadsReady && (dramBank5PortHadReadyRequest || dramBank5Port.req.valid)
dramBank6_ready_received := !(AllOffloadsReady) && (dramBank6_ready_received || dramBank6Port.req.ready)
dramBank6PortHadReadyRequest := !AllOffloadsReady && (dramBank6PortHadReadyRequest || dramBank6Port.req.valid)
dramBank7_ready_received := !(AllOffloadsReady) && (dramBank7_ready_received || dramBank7Port.req.ready)
dramBank7PortHadReadyRequest := !AllOffloadsReady && (dramBank7PortHadReadyRequest || dramBank7Port.req.valid)


/******************Valid stage handler************************/
val dramBank0PortHadValidRequest = Vec(numOfThreads) {Reg(resetVal=Bool(false))}
val dramBank0_valid_received = Vec(numOfThreads) {Reg(resetVal=Bool(false))}
val dramBank1PortHadValidRequest = Vec(numOfThreads) {Reg(resetVal=Bool(false))}
val dramBank1_valid_received = Vec(numOfThreads) {Reg(resetVal=Bool(false))}
val dramBank2PortHadValidRequest = Vec(numOfThreads) {Reg(resetVal=Bool(false))}
val dramBank2_valid_received = Vec(numOfThreads) {Reg(resetVal=Bool(false))}
val dramBank3PortHadValidRequest = Vec(numOfThreads) {Reg(resetVal=Bool(false))}
val dramBank3_valid_received = Vec(numOfThreads) {Reg(resetVal=Bool(false))}
val dramBank4PortHadValidRequest = Vec(numOfThreads) {Reg(resetVal=Bool(false))}
val dramBank4_valid_received = Vec(numOfThreads) {Reg(resetVal=Bool(false))}
val dramBank5PortHadValidRequest = Vec(numOfThreads) {Reg(resetVal=Bool(false))}
val dramBank5_valid_received = Vec(numOfThreads) {Reg(resetVal=Bool(false))}
val dramBank6PortHadValidRequest = Vec(numOfThreads) {Reg(resetVal=Bool(false))}
val dramBank6_valid_received = Vec(numOfThreads) {Reg(resetVal=Bool(false))}
val dramBank7PortHadValidRequest = Vec(numOfThreads) {Reg(resetVal=Bool(false))}
val dramBank7_valid_received = Vec(numOfThreads) {Reg(resetVal=Bool(false))}

for (i <- 0 to numOfThreads-1) {
AllOffloadsValid(i) :=  
  ((dramBank0Port.rep.valid && (dramBank0Port.rep.tag === UFix(i, 5)))|| dramBank0_valid_received(i) || !dramBank0PortHadValidRequest(i)) &&  
  ((dramBank1Port.rep.valid && (dramBank1Port.rep.tag === UFix(i, 5)))|| dramBank1_valid_received(i) || !dramBank1PortHadValidRequest(i)) &&  
  ((dramBank2Port.rep.valid && (dramBank2Port.rep.tag === UFix(i, 5)))|| dramBank2_valid_received(i) || !dramBank2PortHadValidRequest(i)) &&  
  ((dramBank3Port.rep.valid && (dramBank3Port.rep.tag === UFix(i, 5)))|| dramBank3_valid_received(i) || !dramBank3PortHadValidRequest(i)) &&  
  ((dramBank4Port.rep.valid && (dramBank4Port.rep.tag === UFix(i, 5)))|| dramBank4_valid_received(i) || !dramBank4PortHadValidRequest(i)) &&  
  ((dramBank5Port.rep.valid && (dramBank5Port.rep.tag === UFix(i, 5)))|| dramBank5_valid_received(i) || !dramBank5PortHadValidRequest(i)) &&  
  ((dramBank6Port.rep.valid && (dramBank6Port.rep.tag === UFix(i, 5)))|| dramBank6_valid_received(i) || !dramBank6PortHadValidRequest(i)) &&  
  ((dramBank7Port.rep.valid && (dramBank7Port.rep.tag === UFix(i, 5)))|| dramBank7_valid_received(i) || !dramBank7PortHadValidRequest(i)) &&  
  Bool(true)

dramBank0_valid_received(i) := !(vThread === UFix(i, 5)) && ((dramBank0_valid_received(i)) || (dramBank0Port.rep.valid && dramBank0Port.rep.tag === UFix(i, 5)))
dramBank0PortHadValidRequest(i) := !(vThread === UFix(i,5)) && (dramBank0PortHadValidRequest(i) || (UFix(i,5)===rThread && dramBank0Port.req.valid)/*(dramBank0PortHadReadyRequest && AllOffloadsReady && (UFix(i,5) === rThread))*/)
dramBank1_valid_received(i) := !(vThread === UFix(i, 5)) && ((dramBank1_valid_received(i)) || (dramBank1Port.rep.valid && dramBank1Port.rep.tag === UFix(i, 5)))
dramBank1PortHadValidRequest(i) := !(vThread === UFix(i,5)) && (dramBank1PortHadValidRequest(i) || (UFix(i,5)===rThread && dramBank1Port.req.valid)/*(dramBank1PortHadReadyRequest && AllOffloadsReady && (UFix(i,5) === rThread))*/)
dramBank2_valid_received(i) := !(vThread === UFix(i, 5)) && ((dramBank2_valid_received(i)) || (dramBank2Port.rep.valid && dramBank2Port.rep.tag === UFix(i, 5)))
dramBank2PortHadValidRequest(i) := !(vThread === UFix(i,5)) && (dramBank2PortHadValidRequest(i) || (UFix(i,5)===rThread && dramBank2Port.req.valid)/*(dramBank2PortHadReadyRequest && AllOffloadsReady && (UFix(i,5) === rThread))*/)
dramBank3_valid_received(i) := !(vThread === UFix(i, 5)) && ((dramBank3_valid_received(i)) || (dramBank3Port.rep.valid && dramBank3Port.rep.tag === UFix(i, 5)))
dramBank3PortHadValidRequest(i) := !(vThread === UFix(i,5)) && (dramBank3PortHadValidRequest(i) || (UFix(i,5)===rThread && dramBank3Port.req.valid)/*(dramBank3PortHadReadyRequest && AllOffloadsReady && (UFix(i,5) === rThread))*/)
dramBank4_valid_received(i) := !(vThread === UFix(i, 5)) && ((dramBank4_valid_received(i)) || (dramBank4Port.rep.valid && dramBank4Port.rep.tag === UFix(i, 5)))
dramBank4PortHadValidRequest(i) := !(vThread === UFix(i,5)) && (dramBank4PortHadValidRequest(i) || (UFix(i,5)===rThread && dramBank4Port.req.valid)/*(dramBank4PortHadReadyRequest && AllOffloadsReady && (UFix(i,5) === rThread))*/)
dramBank5_valid_received(i) := !(vThread === UFix(i, 5)) && ((dramBank5_valid_received(i)) || (dramBank5Port.rep.valid && dramBank5Port.rep.tag === UFix(i, 5)))
dramBank5PortHadValidRequest(i) := !(vThread === UFix(i,5)) && (dramBank5PortHadValidRequest(i) || (UFix(i,5)===rThread && dramBank5Port.req.valid)/*(dramBank5PortHadReadyRequest && AllOffloadsReady && (UFix(i,5) === rThread))*/)
dramBank6_valid_received(i) := !(vThread === UFix(i, 5)) && ((dramBank6_valid_received(i)) || (dramBank6Port.rep.valid && dramBank6Port.rep.tag === UFix(i, 5)))
dramBank6PortHadValidRequest(i) := !(vThread === UFix(i,5)) && (dramBank6PortHadValidRequest(i) || (UFix(i,5)===rThread && dramBank6Port.req.valid)/*(dramBank6PortHadReadyRequest && AllOffloadsReady && (UFix(i,5) === rThread))*/)
dramBank7_valid_received(i) := !(vThread === UFix(i, 5)) && ((dramBank7_valid_received(i)) || (dramBank7Port.rep.valid && dramBank7Port.rep.tag === UFix(i, 5)))
dramBank7PortHadValidRequest(i) := !(vThread === UFix(i,5)) && (dramBank7PortHadValidRequest(i) || (UFix(i,5)===rThread && dramBank7Port.req.valid)/*(dramBank7PortHadReadyRequest && AllOffloadsReady && (UFix(i,5) === rThread))*/)
}

val dramBank0PortReplyValue = Mux(dramBank0Port.rep.valid && (vThread === dramBank0Port.rep.tag), dramBank0Port.rep.bits, dramBank0PortReplyStorage(vThread)) 
when (dramBank0Port.rep.valid) {
  dramBank0PortReplyStorage(dramBank0Port.rep.tag) := dramBank0Port.rep.bits
} 
val dramBank1PortReplyValue = Mux(dramBank1Port.rep.valid && (vThread === dramBank1Port.rep.tag), dramBank1Port.rep.bits, dramBank1PortReplyStorage(vThread)) 
when (dramBank1Port.rep.valid) {
  dramBank1PortReplyStorage(dramBank1Port.rep.tag) := dramBank1Port.rep.bits
} 
val dramBank2PortReplyValue = Mux(dramBank2Port.rep.valid && (vThread === dramBank2Port.rep.tag), dramBank2Port.rep.bits, dramBank2PortReplyStorage(vThread)) 
when (dramBank2Port.rep.valid) {
  dramBank2PortReplyStorage(dramBank2Port.rep.tag) := dramBank2Port.rep.bits
} 
val dramBank3PortReplyValue = Mux(dramBank3Port.rep.valid && (vThread === dramBank3Port.rep.tag), dramBank3Port.rep.bits, dramBank3PortReplyStorage(vThread)) 
when (dramBank3Port.rep.valid) {
  dramBank3PortReplyStorage(dramBank3Port.rep.tag) := dramBank3Port.rep.bits
} 
val dramBank4PortReplyValue = Mux(dramBank4Port.rep.valid && (vThread === dramBank4Port.rep.tag), dramBank4Port.rep.bits, dramBank4PortReplyStorage(vThread)) 
when (dramBank4Port.rep.valid) {
  dramBank4PortReplyStorage(dramBank4Port.rep.tag) := dramBank4Port.rep.bits
} 
val dramBank5PortReplyValue = Mux(dramBank5Port.rep.valid && (vThread === dramBank5Port.rep.tag), dramBank5Port.rep.bits, dramBank5PortReplyStorage(vThread)) 
when (dramBank5Port.rep.valid) {
  dramBank5PortReplyStorage(dramBank5Port.rep.tag) := dramBank5Port.rep.bits
} 
val dramBank6PortReplyValue = Mux(dramBank6Port.rep.valid && (vThread === dramBank6Port.rep.tag), dramBank6Port.rep.bits, dramBank6PortReplyStorage(vThread)) 
when (dramBank6Port.rep.valid) {
  dramBank6PortReplyStorage(dramBank6Port.rep.tag) := dramBank6Port.rep.bits
} 
val dramBank7PortReplyValue = Mux(dramBank7Port.rep.valid && (vThread === dramBank7Port.rep.tag), dramBank7Port.rep.bits, dramBank7PortReplyStorage(vThread)) 
when (dramBank7Port.rep.valid) {
  dramBank7PortReplyStorage(dramBank7Port.rep.tag) := dramBank7Port.rep.bits
} 
/**************************************************************/

   val b=bank(inputReg(vThread).addr) 
   val r=row(inputReg(vThread).addr) 
   val t0 = dramBank0PortReplyValue
   val t1 = dramBank1PortReplyValue
   val t2 = dramBank2PortReplyValue
   val t3 = dramBank3PortReplyValue
   val t4 = dramBank4PortReplyValue
   val t5 = dramBank5PortReplyValue
   val t6 = dramBank6PortReplyValue
   val t7 = dramBank7PortReplyValue
   dramBank2Port.req.tag :=  rThread
   dramBank2Port.req.valid :=  (rThread != NONE_SELECTED) && !dramBank2_valid_received(rThread) && ( (rThread != NONE_SELECTED && State(rThread) === GS_WAIT_BANK_2))
   dramBank2Port.req.bits := MuxCase(UFix(0, 32),Seq( ((rThread != NONE_SELECTED && State(rThread) === GS_WAIT_BANK_2),UFix(0, width = 32))))
   dramBank3Port.req.tag :=  rThread
   dramBank3Port.req.valid :=  (rThread != NONE_SELECTED) && !dramBank3_valid_received(rThread) && ( (rThread != NONE_SELECTED && State(rThread) === GS_WAIT_BANK_3))
   dramBank3Port.req.bits := MuxCase(UFix(0, 32),Seq( ((rThread != NONE_SELECTED && State(rThread) === GS_WAIT_BANK_3),UFix(0, width = 32))))
   dramBank4Port.req.tag :=  rThread
   dramBank4Port.req.valid :=  (rThread != NONE_SELECTED) && !dramBank4_valid_received(rThread) && ( (rThread != NONE_SELECTED && State(rThread) === GS_WAIT_BANK_4))
   dramBank4Port.req.bits := MuxCase(UFix(0, 32),Seq( ((rThread != NONE_SELECTED && State(rThread) === GS_WAIT_BANK_4),UFix(0, width = 32))))
   dramBank5Port.req.tag :=  rThread
   dramBank5Port.req.valid :=  (rThread != NONE_SELECTED) && !dramBank5_valid_received(rThread) && ( (rThread != NONE_SELECTED && State(rThread) === GS_WAIT_BANK_5))
   dramBank5Port.req.bits := MuxCase(UFix(0, 32),Seq( ((rThread != NONE_SELECTED && State(rThread) === GS_WAIT_BANK_5),UFix(0, width = 32))))
   dramBank0Port.req.tag :=  rThread
   dramBank0Port.req.valid :=  (rThread != NONE_SELECTED) && !dramBank0_valid_received(rThread) && ( (rThread != NONE_SELECTED && State(rThread) === GS_WAIT_BANK_0))
   dramBank0Port.req.bits := MuxCase(UFix(0, 32),Seq( ((rThread != NONE_SELECTED && State(rThread) === GS_WAIT_BANK_0),UFix(0, width = 32))))
   dramBank1Port.req.tag :=  rThread
   dramBank1Port.req.valid :=  (rThread != NONE_SELECTED) && !dramBank1_valid_received(rThread) && ( (rThread != NONE_SELECTED && State(rThread) === GS_WAIT_BANK_1))
   dramBank1Port.req.bits := MuxCase(UFix(0, 32),Seq( ((rThread != NONE_SELECTED && State(rThread) === GS_WAIT_BANK_1),UFix(0, width = 32))))
   dramBank7Port.req.tag :=  rThread
   dramBank7Port.req.valid :=  (rThread != NONE_SELECTED) && !dramBank7_valid_received(rThread) && ( (rThread != NONE_SELECTED && State(rThread) === GS_WAIT_BANK_7))
   dramBank7Port.req.bits := MuxCase(UFix(0, 32),Seq( ((rThread != NONE_SELECTED && State(rThread) === GS_WAIT_BANK_7),UFix(0, width = 32))))
   dramBank6Port.req.tag :=  rThread
   dramBank6Port.req.valid :=  (rThread != NONE_SELECTED) && !dramBank6_valid_received(rThread) && ( (rThread != NONE_SELECTED && State(rThread) === GS_WAIT_BANK_6))
   dramBank6Port.req.bits := MuxCase(UFix(0, 32),Seq( ((rThread != NONE_SELECTED && State(rThread) === GS_WAIT_BANK_6),UFix(0, width = 32))))

  when (sThread != NONE_SELECTED && io.in.valid) {
    inputReg(sThread) := io.in.bits
    inputTag(sThread) := io.in.tag
   rb0RowAddr(sThread) :=UFix(1, width = 32)
   rb1RowAddr(sThread) :=UFix(0, width = 32)
   rb2RowAddr(sThread) :=UFix(0, width = 32)
   rb3RowAddr(sThread) :=UFix(0, width = 32)
   rb4RowAddr(sThread) :=UFix(0, width = 32)
   rb5RowAddr(sThread) :=UFix(0, width = 32)
   rb6RowAddr(sThread) :=UFix(0, width = 32)
   rb7RowAddr(sThread) :=UFix(0, width = 32)
    State(sThread) := GS_CHECK_RB_HIT
 
  } 

  when (rThread != NONE_SELECTED && State(rThread) === WaitForOutputReady && io.out.ready) {
    State(rThread) := EmitReturnState(rThread) 
  } 

   
    when (vThread != NONE_SELECTED &&State(vThread) === GS_CHECK_RB_HIT){
   bankAddr(vThread):=b 
   rowAddr(vThread):=r 
   when (b=== UFix(0, width = 32)) {
   when (rb0RowAddr(vThread)=== r) {
   State(vThread):=GS_FINISH 
   }
   .otherwise {
   rb0RowAddr(vThread):=r 
   State(vThread):=GS_WAIT_BANK_0 
   }
   }
   .otherwise {
   when (b=== UFix(1, width = 32)) {
   when (rb1RowAddr(vThread)=== r) {
   State(vThread):=GS_FINISH 
   }
   .otherwise {
   rb1RowAddr(vThread):=r 
   State(vThread):=GS_WAIT_BANK_1 
   }
   }
   .otherwise {
   when (b=== UFix(2, width = 32)) {
   when (rb2RowAddr(vThread)=== r) {
   State(vThread):=GS_FINISH 
   }
   .otherwise {
   rb2RowAddr(vThread):=r 
   State(vThread):=GS_WAIT_BANK_2 
   }
   }
   .otherwise {
   when (b=== UFix(3, width = 32)) {
   when (rb3RowAddr(vThread)=== r) {
   State(vThread):=GS_FINISH 
   }
   .otherwise {
   rb3RowAddr(vThread):=r 
   State(vThread):=GS_WAIT_BANK_3 
   }
   }
   .otherwise {
   when (b=== UFix(4, width = 32)) {
   when (rb4RowAddr(vThread)=== r) {
   State(vThread):=GS_FINISH 
   }
   .otherwise {
   rb4RowAddr(vThread):=r 
   State(vThread):=GS_WAIT_BANK_4 
   }
   }
   .otherwise {
   when (b=== UFix(5, width = 32)) {
   when (rb5RowAddr(vThread)=== r) {
   State(vThread):=GS_FINISH 
   }
   .otherwise {
   rb5RowAddr(vThread):=r 
   State(vThread):=GS_WAIT_BANK_5 
   }
   }
   .otherwise {
   when (b=== UFix(6, width = 32)) {
   when (rb6RowAddr(vThread)=== r) {
   State(vThread):=GS_FINISH 
   }
   .otherwise {
   rb6RowAddr(vThread):=r 
   State(vThread):=GS_WAIT_BANK_6 
   }
   }
   .otherwise {
   when (b=== UFix(7, width = 32)) {
   when (rb7RowAddr(vThread)=== r) {
   State(vThread):=GS_FINISH 
   }
   .otherwise {
   rb7RowAddr(vThread):=r 
   State(vThread):=GS_WAIT_BANK_7 
   }
   }
   .otherwise {
   State(vThread):=GS_FINISH 
   }
   }
   }
   }
   }
   }
   }
   }
   } 
   
    when (vThread != NONE_SELECTED &&State(vThread) === GS_WAIT_BANK_0){
   EmitReturnState(vThread) := WaitForInputValid
   State(vThread) := WaitForOutputReady
   } 
   
    when (vThread != NONE_SELECTED &&State(vThread) === GS_WAIT_BANK_1){
   EmitReturnState(vThread) := WaitForInputValid
   State(vThread) := WaitForOutputReady
   } 
   
    when (vThread != NONE_SELECTED &&State(vThread) === GS_WAIT_BANK_2){
   EmitReturnState(vThread) := WaitForInputValid
   State(vThread) := WaitForOutputReady
   } 
   
    when (vThread != NONE_SELECTED &&State(vThread) === GS_WAIT_BANK_3){
   EmitReturnState(vThread) := WaitForInputValid
   State(vThread) := WaitForOutputReady
   } 
   
    when (vThread != NONE_SELECTED &&State(vThread) === GS_WAIT_BANK_4){
   EmitReturnState(vThread) := WaitForInputValid
   State(vThread) := WaitForOutputReady
   } 
   
    when (vThread != NONE_SELECTED &&State(vThread) === GS_WAIT_BANK_5){
   EmitReturnState(vThread) := WaitForInputValid
   State(vThread) := WaitForOutputReady
   } 
   
    when (vThread != NONE_SELECTED &&State(vThread) === GS_WAIT_BANK_6){
   EmitReturnState(vThread) := WaitForInputValid
   State(vThread) := WaitForOutputReady
   } 
   
    when (vThread != NONE_SELECTED &&State(vThread) === GS_WAIT_BANK_7){
   EmitReturnState(vThread) := WaitForInputValid
   State(vThread) := WaitForOutputReady
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
    if (n == "dramBank0") {
      when (IsPcReset) {
        offloadRateArray(portId-3) := UFix(0, Pcounters.PCWIDTH)
      } .elsewhen (/*i.asInstanceOf[gOffBundle[Bundle, Bundle]].req.ready &&*/
       (dramBank0PortHadValidRequest(0) /*|| dramBank0Port.req.valid*/) && !pcPaused) {
        offloadRateArray(portId-3) := offloadRateArray(portId-3) + UFix(1, Pcounters.PCWIDTH)
      }
    }
    if (n == "dramBank1") {
      when (IsPcReset) {
        offloadRateArray(portId-3) := UFix(0, Pcounters.PCWIDTH)
      } .elsewhen (/*i.asInstanceOf[gOffBundle[Bundle, Bundle]].req.ready &&*/
       (dramBank1PortHadValidRequest(0) /*|| dramBank1Port.req.valid*/) && !pcPaused) {
        offloadRateArray(portId-3) := offloadRateArray(portId-3) + UFix(1, Pcounters.PCWIDTH)
      }
    }
    if (n == "dramBank2") {
      when (IsPcReset) {
        offloadRateArray(portId-3) := UFix(0, Pcounters.PCWIDTH)
      } .elsewhen (/*i.asInstanceOf[gOffBundle[Bundle, Bundle]].req.ready &&*/
       (dramBank2PortHadValidRequest(0) /*|| dramBank2Port.req.valid*/) && !pcPaused) {
        offloadRateArray(portId-3) := offloadRateArray(portId-3) + UFix(1, Pcounters.PCWIDTH)
      }
    }
    if (n == "dramBank3") {
      when (IsPcReset) {
        offloadRateArray(portId-3) := UFix(0, Pcounters.PCWIDTH)
      } .elsewhen (/*i.asInstanceOf[gOffBundle[Bundle, Bundle]].req.ready &&*/
       (dramBank3PortHadValidRequest(0) /*|| dramBank3Port.req.valid*/) && !pcPaused) {
        offloadRateArray(portId-3) := offloadRateArray(portId-3) + UFix(1, Pcounters.PCWIDTH)
      }
    }
    if (n == "dramBank4") {
      when (IsPcReset) {
        offloadRateArray(portId-3) := UFix(0, Pcounters.PCWIDTH)
      } .elsewhen (/*i.asInstanceOf[gOffBundle[Bundle, Bundle]].req.ready &&*/
       (dramBank4PortHadValidRequest(0) /*|| dramBank4Port.req.valid*/) && !pcPaused) {
        offloadRateArray(portId-3) := offloadRateArray(portId-3) + UFix(1, Pcounters.PCWIDTH)
      }
    }
    if (n == "dramBank5") {
      when (IsPcReset) {
        offloadRateArray(portId-3) := UFix(0, Pcounters.PCWIDTH)
      } .elsewhen (/*i.asInstanceOf[gOffBundle[Bundle, Bundle]].req.ready &&*/
       (dramBank5PortHadValidRequest(0) /*|| dramBank5Port.req.valid*/) && !pcPaused) {
        offloadRateArray(portId-3) := offloadRateArray(portId-3) + UFix(1, Pcounters.PCWIDTH)
      }
    }
    if (n == "dramBank6") {
      when (IsPcReset) {
        offloadRateArray(portId-3) := UFix(0, Pcounters.PCWIDTH)
      } .elsewhen (/*i.asInstanceOf[gOffBundle[Bundle, Bundle]].req.ready &&*/
       (dramBank6PortHadValidRequest(0) /*|| dramBank6Port.req.valid*/) && !pcPaused) {
        offloadRateArray(portId-3) := offloadRateArray(portId-3) + UFix(1, Pcounters.PCWIDTH)
      }
    }
    if (n == "dramBank7") {
      when (IsPcReset) {
        offloadRateArray(portId-3) := UFix(0, Pcounters.PCWIDTH)
      } .elsewhen (/*i.asInstanceOf[gOffBundle[Bundle, Bundle]].req.ready &&*/
       (dramBank7PortHadValidRequest(0) /*|| dramBank7Port.req.valid*/) && !pcPaused) {
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

