package Tutorial

import Chisel._
import Node._
import Literal._
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer


class incThroughOffload extends gComponentLeaf  (() => UFix(width = 32))(() => UFix(width = 32)) (ArrayBuffer(("incrementFactor", () => UFix(width = 32) , () => UFix(width = 32))))  {
  val WaitForInputValid = UFix(0, 8)
  val WaitForOutputReady = UFix(255, 8)
  val WaitForReady = UFix(0, 1)
  val WaitForValid = UFix(1, 1)
  val State = Reg(resetVal=WaitForInputValid) 
  val SubState = Reg(resetVal=WaitForReady)

   val counter = Reg(UFix(width = 32))	//Global variable 
   val inputReg = Reg(UFix(width = 32))
   val outputReg = Reg(UFix(width = 32))
   def  myOff = io.elements.find(_._1 == "off").getOrElse(elseV)._2
   def mymyOff = myOff.asInstanceOf[Bundle].elements.find(_._1 == "incrementFactor").getOrElse(elseV)._2
   val incrementFactorPort = new gOffBundleND(() => UFix(width = 32), () => UFix(width = 32))
   incrementFactorPort <>mymyOff
   val GS_INITIALIZE = UFix(1)
   val GS_COUNT = UFix(2)
   val GS_GET_INC_FACTOR = UFix(3)

val incrementFactor_ready_received = Reg(resetVal=Bool(false))
val incrementFactor_valid_received = Reg(resetVal=Bool(false))
incrementFactor_ready_received := !(SubState === WaitForValid) && (incrementFactor_ready_received || incrementFactorPort.req.ready)
incrementFactor_valid_received := !(SubState === WaitForReady) && (incrementFactor_valid_received || incrementFactorPort.rep.valid)

val incrementFactorPortHadRequest = Reg(resetVal=Bool(false))

val AllOffloadsReady =  
  (incrementFactor_ready_received || !incrementFactorPort.req.valid) &&  
  Bool(true)
val AllOffloadsValid =  
  incrementFactor_valid_received || !incrementFactorPortHadRequest&& 
  Bool(true)

incrementFactorPortHadRequest := Mux(SubState === WaitForReady, incrementFactorPortHadRequest || incrementFactorPort.req.valid, incrementFactorPortHadRequest && !AllOffloadsValid)



   val waitVar= Mux((counter===UFix(-1, width = 64)),UFix(0, width = 64),counter) 
   incrementFactorPort.req.bits := inputReg
   incrementFactorPort.req.valid := State === GS_GET_INC_FACTOR
   val incF = incrementFactorPort.rep.bits

  when (State === WaitForInputValid && io.in.valid) {
    inputReg := io.in.bits
    State := GS_INITIALIZE
 
    SubState := WaitForReady 
  } 

  when (State === WaitForOutputReady && io.out.ready) {
    State := WaitForInputValid 
  } 

  when (SubState === WaitForReady && AllOffloadsReady) {
    SubState := WaitForValid 
  }

   when (SubState === WaitForValid && AllOffloadsValid) {
    when (State === GS_INITIALIZE){
   counter:=UFix(10, width = 64) 
   State:=GS_COUNT 
   } 
   
    when (State === GS_COUNT){
   counter:=waitVar-UFix(1, width = 64) 
   when (waitVar===UFix(0, width = 64)) {
   State:=GS_GET_INC_FACTOR 
   }
   } 
   
    when (State === GS_GET_INC_FACTOR){
   outputReg:=inputReg+incF 
   State := WaitForOutputReady
   } 
    SubState := WaitForValid 
    } 


io.out.bits := incrementFactorPort.rep.bits 
io.out.valid := State === WaitForOutputReady
io.in.ready := State === WaitForInputValid
}
