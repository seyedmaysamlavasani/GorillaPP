package Tutorial

import Chisel._
import Node._
import Literal._
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer


class incThroughOffload (extCompName:String) extends gComponentLeaf  (() => new testStruct_t)(() => new testStruct_t) (ArrayBuffer(("incrementFactor", () => UFix(width = 32) , () => UFix(width = 32))))  (extCompName = extCompName + "__type__engine__MT__1__") with include {
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
   val temp = Vec (numOfThreads) {Reg(new testStruct_t)}	//Global variable 
   val counter = Vec (numOfThreads) {Reg(UFix(width = 32))}	//Global variable 
   val inputReg = Vec(numOfThreads) {Reg(new testStruct_t)}
   val outputReg = Vec(numOfThreads) {Reg(new testStruct_t)}
   def mymyOffincrementFactor = myOff.asInstanceOf[Bundle].elements.find(_._1 == "incrementFactor").getOrElse(elseV)._2
   val incrementFactorPort = new gOffBundleND(() => UFix(width = 32), () => UFix(width = 32))
   incrementFactorPort <> mymyOffincrementFactor
   val GS_INITIALIZE = UFix(1)
   val GS_COUNT = UFix(2)
   val GS_GET_INC_FACTOR = UFix(3)

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

  incrementFactorPort.rep.ready := Bool(true)

/******************Ready stage handler************************/
val incrementFactorPortHadReadyRequest = Reg(resetVal=Bool(false))
val incrementFactor_ready_received = Reg(resetVal=Bool(false))

AllOffloadsReady :=  
  (incrementFactorPort.req.ready || incrementFactor_ready_received || (!incrementFactorPortHadReadyRequest && !incrementFactorPort.req.valid)) &&  
  Bool(true)

incrementFactor_ready_received := !(AllOffloadsReady) && (incrementFactor_ready_received || incrementFactorPort.req.ready)
incrementFactorPortHadReadyRequest := !AllOffloadsReady && (incrementFactorPortHadReadyRequest || incrementFactorPort.req.valid)


/******************Valid stage handler************************/
val incrementFactorPortHadValidRequest = Vec(numOfThreads) {Reg(resetVal=Bool(false))}
val incrementFactor_valid_received = Vec(numOfThreads) {Reg(resetVal=Bool(false))}

for (i <- 0 to numOfThreads-1) {
AllOffloadsValid(i) :=  
  ((incrementFactorPort.rep.valid && (incrementFactorPort.rep.tag === UFix(i, 5)))|| incrementFactor_valid_received(i) || !incrementFactorPortHadValidRequest(i)) &&  
  Bool(true)

incrementFactor_valid_received(i) := !(vThread === UFix(i, 5)) && ((incrementFactor_valid_received(i)) || (incrementFactorPort.rep.valid && incrementFactorPort.rep.tag === UFix(i, 5)))
incrementFactorPortHadValidRequest(i) := !(vThread === UFix(i,5)) && (incrementFactorPortHadValidRequest(i) || (UFix(i,5)===rThread && incrementFactorPort.req.valid)/*(incrementFactorPortHadReadyRequest && AllOffloadsReady && (UFix(i,5) === rThread))*/)
}

   val incF = incrementFactorPort.rep.bits
   incrementFactorPort.req.tag :=  rThread
   incrementFactorPort.req.valid :=  (rThread != NONE_SELECTED) && !incrementFactor_valid_received(rThread) && ( (rThread != NONE_SELECTED && State(rThread) === GS_GET_INC_FACTOR))
   incrementFactorPort.req.bits := MuxCase(UFix(0, 32),Seq( ((rThread != NONE_SELECTED && State(rThread) === GS_GET_INC_FACTOR),inputReg(rThread).a)))

  when (sThread != NONE_SELECTED && io.in.valid) {
    inputReg(sThread) := io.in.bits
    inputTag(sThread) := io.in.tag
    State(sThread) := GS_INITIALIZE
 
  } 

  when (rThread != NONE_SELECTED && State(rThread) === WaitForOutputReady && io.out.ready) {
    State(rThread) := EmitReturnState(rThread) 
  } 

   
    when (vThread != NONE_SELECTED &&State(vThread) === GS_INITIALIZE){
   counter(vThread):=UFix(10, width = 32) 
   State(vThread):=GS_COUNT 
   } 
   
    when (vThread != NONE_SELECTED &&State(vThread) === GS_COUNT){
   when (counter(vThread)===UFix(0, width = 32)) {
   State(vThread):=GS_GET_INC_FACTOR 
   }
   counter(vThread):=counter(vThread)-UFix(1, width = 32) 
   } 
   
    when (vThread != NONE_SELECTED &&State(vThread) === GS_GET_INC_FACTOR){
   outputReg(vThread).a:=incF+inputReg(vThread).a 
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
    if (n == "incrementFactor") {
      when (IsPcReset) {
        offloadRateArray(portId-3) := UFix(0, Pcounters.PCWIDTH)
      } .elsewhen (i.asInstanceOf[gOffBundle[Bundle, Bundle]].req.ready && 
       (incrementFactorPortHadValidRequest(0) || incrementFactorPort.req.valid) && !pcPaused) {
        offloadRateArray(portId-3) := offloadRateArray(portId-3) + UFix(1, Pcounters.PCWIDTH)
      }
    }
    portId = portId + 1
  }
}

