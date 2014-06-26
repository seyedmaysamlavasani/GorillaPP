package Tutorial

import Chisel._
import Node._
import Literal._
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer


class KDistribute extends gComponentLeaf  (() => new KEngineIO_t)(() => new KEngineIO_t) (ArrayBuffer())  with include {
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
   val broadcastIndex = Vec (numOfThreads) {Reg(UFix(width = 32), resetVal=UFix(0, width = 32))}	//Global variable 
   val mode = Vec (numOfThreads) {Reg(UFix(width = 32), resetVal=UFix(0, width = 32))}	//Global variable 
   val	BROADCAST = UFix(0, width = 32)//constant macro definition 
   val	UNICAST = UFix(1, width = 32)//constant macro definition 
   val inputReg = Vec(numOfThreads) {Reg(new KEngineIO_t)}
   val outputReg = Vec(numOfThreads) {Reg(new KEngineIO_t)}
   val GS_POINT = UFix(1)
   val GS_BROADCAST = UFix(2)


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


/******************Ready stage handler************************/

AllOffloadsReady :=  
  Bool(true)



/******************Valid stage handler************************/

for (i <- 0 to numOfThreads-1) {
AllOffloadsValid(i) :=  
  Bool(true)

}


  when (sThread != NONE_SELECTED && io.in.valid) {
    inputReg(sThread) := io.in.bits
    inputTag(sThread) := io.in.tag
    State(sThread) := GS_POINT
 
  } 

  when (rThread != NONE_SELECTED && State(rThread) === WaitForOutputReady && io.out.ready) {
    State(rThread) := EmitReturnState(rThread) 
  } 

   
    when (vThread != NONE_SELECTED && State(vThread) === GS_POINT){
   outputReg(vThread):=inputReg(vThread) 
   when (mode(vThread)===BROADCAST&&NUM_OF_ENGINES!=UFix(1, width = 32)) {
   EmitReturnState(vThread):=GS_BROADCAST 
   broadcastIndex(vThread):=broadcastIndex(vThread)+UFix(1, width = 32) 
   }
   .otherwise {
   EmitReturnState(vThread):=WaitForInputValid 
   }
   when (inputReg(vThread).centeroidsFinished) {
   mode(vThread):=UNICAST 
   }
   when (inputReg(vThread).pointsFinished) {
   mode(vThread):=BROADCAST 
   }
   State(vThread) := WaitForOutputReady
   } 
   
    when (vThread != NONE_SELECTED && State(vThread) === GS_BROADCAST){
   when (broadcastIndex(vThread)>=NUM_OF_ENGINES-UFix(1, width = 32)) {
   broadcastIndex(vThread):=UFix(0, width = 32) 
   EmitReturnState(vThread):=WaitForInputValid 
   }
   .otherwise {
   broadcastIndex(vThread):=broadcastIndex(vThread)+UFix(1, width = 32) 
   EmitReturnState(vThread):=GS_BROADCAST 
   }
   State(vThread) := WaitForOutputReady
   } 

  io.out.tag := inputTag(rThread)
  io.out.bits := outputReg(rThread)
  io.out.valid := rThread != NONE_SELECTED && State(rThread) === WaitForOutputReady
  io.in.ready := sThread != NONE_SELECTED 
}

