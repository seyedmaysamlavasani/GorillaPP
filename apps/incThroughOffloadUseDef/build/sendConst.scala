package Tutorial

import Chisel._
import Node._
import Literal._
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer


class sendConst (extCompName:String) extends gComponentLeaf  (() => UFix(width = 32))(() => UFix(width = 32)) (ArrayBuffer())  (extCompName = extCompName + "__type__engine__MT__1__") with include {
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

   val Input = Vec(numOfThreads) {Reg(UFix(width = 32))}
   val Output = Vec(numOfThreads) {Reg(UFix(width = 32))}
   val GS_GET = UFix(1)
   var GS_GET__Output = UFix(width = 32)
   GS_GET__Output := Output(rThread)
   var GS_GET__State = UFix(width=8)
   GS_GET__State := State(rThread)
   val GS_GET__State__rviReg  = Reg(UFix(width = 8))
   val GS_SEND = UFix(2)

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


/******************Ready stage handler************************/

AllOffloadsReady :=  
  Bool(true)



/******************Valid stage handler************************/

for (i <- 0 to numOfThreads-1) {
AllOffloadsValid(i) :=  
  Bool(true)

}

   GS_GET__Output=UFix(2, width = 32) 
   GS_GET__State=GS_SEND 

  when (sThread != NONE_SELECTED && io.in.valid) {
    Input(sThread) := io.in.bits
    inputTag(sThread) := io.in.tag
    State(sThread) := GS_GET
 
  } 

  when (rThread != NONE_SELECTED && State(rThread) === WaitForOutputReady && io.out.ready) {
    State(rThread) := EmitReturnState(rThread) 
  } 
   
    when (rThread != NONE_SELECTED && State(rThread) === GS_GET){
   GS_GET__State__rviReg :=GS_GET__State
   Output(rThread):= GS_GET__Output
   } 
   
    when (rThread != NONE_SELECTED && State(rThread) === GS_SEND){
   } 


   
    when (vThread != NONE_SELECTED &&State(vThread) === GS_GET){
   State(vThread) := GS_GET__State__rviReg
   } 
   
    when (vThread != NONE_SELECTED &&State(vThread) === GS_SEND){
   EmitReturnState(vThread) := WaitForInputValid
   State(vThread) := WaitForOutputReady
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
    portId = portId + 1
  }
}

