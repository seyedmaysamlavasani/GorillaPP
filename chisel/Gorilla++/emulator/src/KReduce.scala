package Tutorial

import Chisel._
import Node._
import Literal._
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer


class KReduce extends gComponentLeaf  (() => new KEngineIO_t)(() => new KEngineIO_t) (ArrayBuffer(("partialAccumulatorMem", () => new pointMemInput_t , () => new pointMemOutput_t), ("add", () => new FU_in_t , () => new FU_out_t)))  with include {
  val numOfThreads =  1  
  val NONE_SELECTED = UFix(numOfThreads,log2Up(numOfThreads)+1)
  val WaitForInputValid = UFix(0, 8)
  val WaitForOutputReady = UFix(255, 8)
  val WaitForReady = UFix(0, 1)
  val WaitForValid = UFix(1, 1)
  val inputTag = Vec(numOfThreads) {Reg(UFix(width=5))}
  val State = Vec(numOfThreads) {Reg(UFix(width=8), resetVal=WaitForInputValid)} 
  val EmitReturnState = Vec(numOfThreads) {Reg(UFix(width=8), resetVal=WaitForInputValid)} 
  //val outstandingOffs = Vec(numOfThreads) {Reg(resetVal=UFix(0, 5))}
  val AllOffloadsReady = Bool()
  val AllOffloadsValid  = Vec(numOfThreads) {Bool()}
  /*******************Thread states*********************************/
  val subStateTh = Vec(numOfThreads) {Reg(resetVal=WaitForReady)}

  def  myOff = io.elements.find(_._1 == "off").getOrElse(elseV)._2
   val pMemOut = Vec (numOfThreads) {Reg(new pointMemOutput_t)}	//Global variable 
   val pMemInGlobal = Vec (numOfThreads) {Reg(new pointMemInput_t)}	//Global variable 
   val centeroidIndex = Vec (numOfThreads) {Reg(UFix(width = 32), resetVal=UFix(0, width = 32))}	//Global variable 
   val inputReg = Vec(numOfThreads) {Reg(new KEngineIO_t)}
   val outputReg = Vec(numOfThreads) {Reg(new KEngineIO_t)}
   def mymyOffpartialAccumulatorMem = myOff.asInstanceOf[Bundle].elements.find(_._1 == "partialAccumulatorMem").getOrElse(elseV)._2
   val partialAccumulatorMemPort = new gOffBundleND(() => new pointMemInput_t, () => new pointMemOutput_t)
   partialAccumulatorMemPort <> mymyOffpartialAccumulatorMem
   def mymyOffadd = myOff.asInstanceOf[Bundle].elements.find(_._1 == "add").getOrElse(elseV)._2
   val addPort = new gOffBundleND(() => new FU_in_t, () => new FU_out_t)
   addPort <> mymyOffadd
   val GS_RECEIVE = UFix(1)
   val pMemIn1 = new pointMemInput_t
   val GS_ADD_X = UFix(2)
   val addIn1 = new FU_in_t
   val addOut1 = new FU_out_t
   val GS_ADD_Y = UFix(3)
   val addIn2 = new FU_in_t
   val addOut2 = new FU_out_t
   val GS_ADD_Z = UFix(4)
   val addIn3 = new FU_in_t
   val addOut3 = new FU_out_t
   val GS_WRITE = UFix(5)
   val pMemIn2 = new pointMemInput_t
   val GS_SEND = UFix(6)


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

  partialAccumulatorMemPort.rep.ready := Bool(true)
  addPort.rep.ready := Bool(true)

/******************Ready stage handler************************/
val partialAccumulatorMemPortHadReadyRequest = Reg(resetVal=Bool(false))
val partialAccumulatorMem_ready_received = Reg(resetVal=Bool(false))
val addPortHadReadyRequest = Reg(resetVal=Bool(false))
val add_ready_received = Reg(resetVal=Bool(false))

AllOffloadsReady :=  
  (partialAccumulatorMemPort.req.ready || partialAccumulatorMem_ready_received || (!partialAccumulatorMemPortHadReadyRequest && !partialAccumulatorMemPort.req.valid)) &&  
  (addPort.req.ready || add_ready_received || (!addPortHadReadyRequest && !addPort.req.valid)) &&  
  Bool(true)

partialAccumulatorMem_ready_received := !(AllOffloadsReady) && (partialAccumulatorMem_ready_received || partialAccumulatorMemPort.req.ready)
partialAccumulatorMemPortHadReadyRequest := !AllOffloadsReady && (partialAccumulatorMemPortHadReadyRequest || partialAccumulatorMemPort.req.valid)
add_ready_received := !(AllOffloadsReady) && (add_ready_received || addPort.req.ready)
addPortHadReadyRequest := !AllOffloadsReady && (addPortHadReadyRequest || addPort.req.valid)


/******************Valid stage handler************************/
val partialAccumulatorMemPortHadValidRequest = Vec(numOfThreads) {Reg(resetVal=Bool(false))}
val partialAccumulatorMem_valid_received = Vec(numOfThreads) {Reg(resetVal=Bool(false))}
val addPortHadValidRequest = Vec(numOfThreads) {Reg(resetVal=Bool(false))}
val add_valid_received = Vec(numOfThreads) {Reg(resetVal=Bool(false))}

for (i <- 0 to numOfThreads-1) {
AllOffloadsValid(i) :=  
  ((partialAccumulatorMemPort.rep.valid && (partialAccumulatorMemPort.rep.tag === UFix(i, 5)))|| partialAccumulatorMem_valid_received(i) || !partialAccumulatorMemPortHadValidRequest(i)) &&  
  ((addPort.rep.valid && (addPort.rep.tag === UFix(i, 5)))|| add_valid_received(i) || !addPortHadValidRequest(i)) &&  
  Bool(true)

partialAccumulatorMem_valid_received(i) := !(vThread === UFix(i, 5)) && ((partialAccumulatorMem_valid_received(i)) || (partialAccumulatorMemPort.rep.valid && partialAccumulatorMemPort.rep.tag === UFix(i, 5)))
partialAccumulatorMemPortHadValidRequest(i) := !(vThread === UFix(i,5)) && (partialAccumulatorMemPortHadValidRequest(i) || (UFix(i,5)===rThread && partialAccumulatorMemPort.req.valid)/*(partialAccumulatorMemPortHadReadyRequest && AllOffloadsReady && (UFix(i,5) === rThread))*/)
add_valid_received(i) := !(vThread === UFix(i, 5)) && ((add_valid_received(i)) || (addPort.rep.valid && addPort.rep.tag === UFix(i, 5)))
addPortHadValidRequest(i) := !(vThread === UFix(i,5)) && (addPortHadValidRequest(i) || (UFix(i,5)===rThread && addPort.req.valid)/*(addPortHadReadyRequest && AllOffloadsReady && (UFix(i,5) === rThread))*/)
}

   pMemIn1.addr:=inputReg(rThread).centeroidIndex 
   pMemIn1.data:=inputReg(rThread).point 
   pMemIn1.rw:=READ 
   addIn1.in1:=pMemOut(rThread).data.x 
   addIn1.in2:=inputReg(rThread).point.x 
   addOut1 := addPort.rep.bits
   addIn2.in1:=pMemOut(rThread).data.y 
   addIn2.in2:=inputReg(rThread).point.y 
   addOut2 := addPort.rep.bits
   addIn3.in1:=pMemOut(rThread).data.z 
   addIn3.in2:=inputReg(rThread).point.z 
   addOut3 := addPort.rep.bits
   pMemIn2.addr:=inputReg(rThread).centeroidIndex 
   pMemIn2.rw:=WRITE 
   pMemIn2.data:=pMemInGlobal(rThread).data 
   val finishCenteroidIndex=NUM_OF_CENTEROIDS-UFix(1, width = 32) 
   partialAccumulatorMemPort.req.tag :=  rThread
   partialAccumulatorMemPort.req.valid :=  (rThread != NONE_SELECTED) && !partialAccumulatorMem_valid_received(rThread) && ( (rThread != NONE_SELECTED && State(rThread) === GS_RECEIVE) ||  (rThread != NONE_SELECTED && State(rThread) === GS_WRITE) ||  (rThread != NONE_SELECTED && State(rThread) === GS_SEND))
   partialAccumulatorMemPort.req.bits := MuxCase(UFix(0, 32),Seq( ((rThread != NONE_SELECTED && State(rThread) === GS_RECEIVE),pMemIn1) ,  ((rThread != NONE_SELECTED && State(rThread) === GS_WRITE),pMemIn2) ,  ((rThread != NONE_SELECTED && State(rThread) === GS_SEND),pMemInGlobal(rThread))))
   addPort.req.tag :=  rThread
   addPort.req.valid :=  (rThread != NONE_SELECTED) && !add_valid_received(rThread) && ( (rThread != NONE_SELECTED && State(rThread) === GS_ADD_X) ||  (rThread != NONE_SELECTED && State(rThread) === GS_ADD_Y) ||  (rThread != NONE_SELECTED && State(rThread) === GS_ADD_Z))
   addPort.req.bits := MuxCase(UFix(0, 32),Seq( ((rThread != NONE_SELECTED && State(rThread) === GS_ADD_X),addIn1) ,  ((rThread != NONE_SELECTED && State(rThread) === GS_ADD_Y),addIn2) ,  ((rThread != NONE_SELECTED && State(rThread) === GS_ADD_Z),addIn3)))

  when (sThread != NONE_SELECTED && io.in.valid) {
    inputReg(sThread) := io.in.bits
    inputTag(sThread) := io.in.tag
    State(sThread) := GS_RECEIVE
 
  } 

  when (rThread != NONE_SELECTED && State(rThread) === WaitForOutputReady && io.out.ready) {
    State(rThread) := EmitReturnState(rThread) 
  } 

   
    when (vThread != NONE_SELECTED && State(vThread) === GS_RECEIVE){
   pMemOut(vThread) := partialAccumulatorMemPort.rep.bits
   State(vThread):=GS_ADD_X 
   } 
   
    when (vThread != NONE_SELECTED && State(vThread) === GS_ADD_X){
   pMemInGlobal(vThread).data.x:=addOut1.out 
   State(vThread):=GS_ADD_Y 
   } 
   
    when (vThread != NONE_SELECTED && State(vThread) === GS_ADD_Y){
   pMemInGlobal(vThread).data.y:=addOut2.out 
   State(vThread):=GS_ADD_Z 
   } 
   
    when (vThread != NONE_SELECTED && State(vThread) === GS_ADD_Z){
   pMemInGlobal(vThread).data.z:=addOut3.out 
   State(vThread):=GS_WRITE 
   } 
   
    when (vThread != NONE_SELECTED && State(vThread) === GS_WRITE){
   pMemOut(vThread) := partialAccumulatorMemPort.rep.bits
   when (inputReg(vThread).centeroidIndex===finishCenteroidIndex) {
   State(vThread):=GS_SEND 
   centeroidIndex(vThread):=UFix(0, width = 32) 
   }
   .otherwise {
   State(vThread):=WaitForInputValid 
   }
   } 
   
    when (vThread != NONE_SELECTED && State(vThread) === GS_SEND){
   pMemInGlobal(vThread).addr:=centeroidIndex(vThread) 
   pMemInGlobal(vThread).rw:=READ 
   pMemOut(vThread) := partialAccumulatorMemPort.rep.bits
   outputReg(vThread).point:=pMemOut(vThread).data 
   centeroidIndex(vThread):=centeroidIndex(vThread)+UFix(1, width = 32) 
   when (centeroidIndex(vThread)===NUM_OF_CENTEROIDS) {
   centeroidIndex(vThread):=UFix(0, width = 32) 
   EmitReturnState(vThread):=WaitForInputValid 
   }
   .otherwise {
   EmitReturnState(vThread):=State(vThread) 
   }
   State(vThread) := WaitForOutputReady
   } 

  io.out.tag := inputTag(rThread)
  io.out.bits := outputReg(rThread)
  io.out.valid := rThread != NONE_SELECTED && State(rThread) === WaitForOutputReady
  io.in.ready := sThread != NONE_SELECTED 
}

