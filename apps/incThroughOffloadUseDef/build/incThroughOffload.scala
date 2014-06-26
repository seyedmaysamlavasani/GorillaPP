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
  val rThreadEncoder = new RREncode (numOfThreads) 
  val rThread =  rThreadEncoder.io.chosen
  val vThreadEncoder = new RREncode (numOfThreads) 
  val vThread =  vThreadEncoder.io.chosen

   val temp = Vec (numOfThreads) {Reg(new testStruct_t)}	//Global variable 
   val counter = Vec (numOfThreads) {Reg(UFix(width = 32))}	//Global variable 
   val Input = Vec(numOfThreads) {Reg(new testStruct_t)}
   val Output = Vec(numOfThreads) {Reg(new testStruct_t)}
   def mymyOffincrementFactor = myOff.asInstanceOf[Bundle].elements.find(_._1 == "incrementFactor").getOrElse(elseV)._2
   val incrementFactorPort = new gOffBundleND(() => UFix(width = 32), () => UFix(width = 32))
   setStaticComp(incrementFactorPort, this)
   incrementFactorPort <> mymyOffincrementFactor
   val GS_INITIALIZE = UFix(1)
   var GS_INITIALIZE__counter = UFix(width = 32)
   GS_INITIALIZE__counter := counter(rThread)
   var GS_INITIALIZE__State = UFix(width=8)
   GS_INITIALIZE__State := State(rThread)
   val GS_INITIALIZE__State__rviReg  = Reg(UFix(width = 8))
   val GS_COUNT = UFix(2)
   var GS_COUNT__counter = UFix(width = 32)
   GS_COUNT__counter := counter(rThread)
   var GS_COUNT__State = UFix(width=8)
   GS_COUNT__State := State(rThread)
   val GS_COUNT__State__rviReg  = Reg(UFix(width = 8))
   val GS_GET_INC_FACTOR = UFix(3)
   var GS_GET_INC_FACTOR__temp = new testStruct_t
   GS_GET_INC_FACTOR__temp := temp(rThread)
   var GS_GET_INC_FACTOR__incF = UFix(width = 32)
   val GS_GET_INC_FACTOR__incF__rviReg  = Reg(UFix(width = 32))
   GS_GET_INC_FACTOR__incF := GS_GET_INC_FACTOR__incF__rviReg
   GS_GET_INC_FACTOR__temp := temp(vThread)
   var GS_GET_INC_FACTOR__Output = new testStruct_t
   GS_GET_INC_FACTOR__Output := Output(vThread)

   /******************Winner threads*********************************/
  Range(0, numOfThreads, 1).map(i => 
    rThreadEncoder.io.valid(i) := (subStateTh(i) === WaitForReady))
  rThreadEncoder.io.ready := (rThread != NONE_SELECTED)

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

   GS_INITIALIZE__counter=UFix(10, width = 32) 
   GS_INITIALIZE__State=GS_COUNT 
   GS_COUNT__State=Mux(GS_COUNT__counter===UFix(0, width = 32) ,GS_GET_INC_FACTOR, GS_COUNT__State) 
   GS_COUNT__counter=GS_COUNT__counter-UFix(1, width = 32) 
   GS_GET_INC_FACTOR__temp.a=UFix(2, width = 32)
   updateElementsCache(GS_GET_INC_FACTOR__temp , GS_GET_INC_FACTOR__temp.a , "a")
    
   GS_GET_INC_FACTOR__incF=UFix(1, width = 32) 
   incrementFactorPort.req.tag :=  rThread
   incrementFactorPort.req.valid :=  (rThread != NONE_SELECTED) && !incrementFactor_valid_received(rThread) && ( (rThread != NONE_SELECTED && State(rThread) === GS_GET_INC_FACTOR))
   incrementFactorPort.req.bits := MuxCase(UFix(0, 32),Seq( ((rThread != NONE_SELECTED && State(rThread) === GS_GET_INC_FACTOR),Input(rThread).a)))

  when (sThread != NONE_SELECTED && io.in.valid) {
    Input(sThread) := io.in.bits
    inputTag(sThread) := io.in.tag
    State(sThread) := GS_INITIALIZE
 
  } 

  when (rThread != NONE_SELECTED && State(rThread) === WaitForOutputReady && io.out.ready) {
    State(rThread) := EmitReturnState(rThread) 
  } 
   
    when (rThread != NONE_SELECTED && State(rThread) === GS_INITIALIZE){
   GS_INITIALIZE__State__rviReg :=GS_INITIALIZE__State
   counter(rThread):= GS_INITIALIZE__counter
   } 
   
    when (rThread != NONE_SELECTED && State(rThread) === GS_COUNT){
   GS_COUNT__State__rviReg :=GS_COUNT__State
   counter(rThread):= GS_COUNT__counter
   } 
   
    when (rThread != NONE_SELECTED && State(rThread) === GS_GET_INC_FACTOR){
   GS_GET_INC_FACTOR__incF__rviReg :=GS_GET_INC_FACTOR__incF
   temp(rThread):= GS_GET_INC_FACTOR__temp
   } 


   GS_GET_INC_FACTOR__incF = incrementFactorPort.rep.bits
   GS_GET_INC_FACTOR__temp.a=Input(vThread).a+GS_GET_INC_FACTOR__incF
   updateElementsCache(GS_GET_INC_FACTOR__temp , GS_GET_INC_FACTOR__temp.a , "a")
    
   GS_GET_INC_FACTOR__incF=GS_GET_INC_FACTOR__temp.a 
   GS_GET_INC_FACTOR__Output.a=GS_GET_INC_FACTOR__incF
   updateElementsCache(GS_GET_INC_FACTOR__Output , GS_GET_INC_FACTOR__Output.a , "a")
    
   
    when (vThread != NONE_SELECTED &&State(vThread) === GS_INITIALIZE){
   State(vThread) := GS_INITIALIZE__State__rviReg
   } 
   
    when (vThread != NONE_SELECTED &&State(vThread) === GS_COUNT){
   State(vThread) := GS_COUNT__State__rviReg
   } 
   
    when (vThread != NONE_SELECTED &&State(vThread) === GS_GET_INC_FACTOR){
   EmitReturnState(vThread) := WaitForInputValid
   State(vThread) := WaitForOutputReady
   temp(vThread) := GS_GET_INC_FACTOR__temp
   Output(vThread) := GS_GET_INC_FACTOR__Output
   } 

  io.out.tag := inputTag(rThread)
  io.out.bits := Output(rThread)
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

