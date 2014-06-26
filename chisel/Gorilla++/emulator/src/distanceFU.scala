package Tutorial

import Chisel._
import Node._
import Literal._
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer


class distanceFU extends gComponentLeaf  (() => new distanceFU_in_t)(() => new distanceFU_out_t) (ArrayBuffer())  with include {
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
   val inputReg = Vec(numOfThreads) {Reg(new distanceFU_in_t)}
   val outputReg = Vec(numOfThreads) {Reg(new distanceFU_out_t)}
   val GS_step1 = UFix(1)
   val GS_step2 = UFix(2)
   val GS_step3 = UFix(3)
   val GS_step4 = UFix(4)
   val GS_step5 = UFix(5)
   val GS_step6 = UFix(6)
   val GS_step7 = UFix(7)
   val GS_step8 = UFix(8)
   val GS_step9 = UFix(9)
   val GS_step10 = UFix(10)
   val GS_step11 = UFix(11)
   val GS_step12 = UFix(12)
   val GS_step13 = UFix(13)
   val GS_step14 = UFix(14)
   val GS_step15 = UFix(15)
   val GS_step16 = UFix(16)
   val GS_step17 = UFix(17)
   val GS_step18 = UFix(18)
   val GS_step19 = UFix(19)
   val GS_step20 = UFix(20)
   val GS_step21 = UFix(21)


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
    State(sThread) := GS_step1
 
  } 

  when (rThread != NONE_SELECTED && State(rThread) === WaitForOutputReady && io.out.ready) {
    State(rThread) := EmitReturnState(rThread) 
  } 

   
    when (vThread != NONE_SELECTED && State(vThread) === GS_step1){
   outputReg(vThread).out:=inputReg(vThread).in1.x+inputReg(vThread).in2.x+inputReg(vThread).in1.y+inputReg(vThread).in2.y+inputReg(vThread).in1.z+inputReg(vThread).in2.z 
   State(vThread):=GS_step2 
   } 
   
    when (vThread != NONE_SELECTED && State(vThread) === GS_step2){
   State(vThread):=GS_step3 
   } 
   
    when (vThread != NONE_SELECTED && State(vThread) === GS_step3){
   State(vThread):=GS_step4 
   } 
   
    when (vThread != NONE_SELECTED && State(vThread) === GS_step4){
   State(vThread):=GS_step5 
   } 
   
    when (vThread != NONE_SELECTED && State(vThread) === GS_step5){
   State(vThread):=GS_step6 
   } 
   
    when (vThread != NONE_SELECTED && State(vThread) === GS_step6){
   State(vThread):=GS_step7 
   } 
   
    when (vThread != NONE_SELECTED && State(vThread) === GS_step7){
   State(vThread):=GS_step8 
   } 
   
    when (vThread != NONE_SELECTED && State(vThread) === GS_step8){
   State(vThread):=GS_step9 
   } 
   
    when (vThread != NONE_SELECTED && State(vThread) === GS_step9){
   State(vThread):=GS_step10 
   } 
   
    when (vThread != NONE_SELECTED && State(vThread) === GS_step10){
   State(vThread):=GS_step11 
   } 
   
    when (vThread != NONE_SELECTED && State(vThread) === GS_step11){
   State(vThread):=GS_step12 
   } 
   
    when (vThread != NONE_SELECTED && State(vThread) === GS_step12){
   State(vThread):=GS_step13 
   } 
   
    when (vThread != NONE_SELECTED && State(vThread) === GS_step13){
   State(vThread):=GS_step14 
   } 
   
    when (vThread != NONE_SELECTED && State(vThread) === GS_step14){
   State(vThread):=GS_step15 
   } 
   
    when (vThread != NONE_SELECTED && State(vThread) === GS_step15){
   State(vThread):=GS_step16 
   } 
   
    when (vThread != NONE_SELECTED && State(vThread) === GS_step16){
   State(vThread):=GS_step17 
   } 
   
    when (vThread != NONE_SELECTED && State(vThread) === GS_step17){
   State(vThread):=GS_step18 
   } 
   
    when (vThread != NONE_SELECTED && State(vThread) === GS_step18){
   State(vThread):=GS_step19 
   } 
   
    when (vThread != NONE_SELECTED && State(vThread) === GS_step19){
   State(vThread):=GS_step20 
   } 
   
    when (vThread != NONE_SELECTED && State(vThread) === GS_step20){
   State(vThread):=GS_step21 
   } 
   
    when (vThread != NONE_SELECTED && State(vThread) === GS_step21){
   EmitReturnState(vThread) := WaitForInputValid
   State(vThread) := WaitForOutputReady
   } 

  io.out.tag := inputTag(rThread)
  io.out.bits := outputReg(rThread)
  io.out.valid := rThread != NONE_SELECTED && State(rThread) === WaitForOutputReady
  io.in.ready := sThread != NONE_SELECTED 
}

