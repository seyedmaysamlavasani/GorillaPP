package Tutorial

import Chisel._
import Node._
import Literal._
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer


class lookup extends gComponentLeaf  (() => UFix(width = 32))(() => UFix(width = 8)) (ArrayBuffer())  with include {
  val WaitForInputValid = UFix(0, 8)
  val WaitForOutputReady = UFix(255, 8)
  val WaitForReady = UFix(0, 1)
  val WaitForValid = UFix(1, 1)
  val State = Reg(resetVal=WaitForInputValid) 
  val SubState = Reg(resetVal=WaitForReady)

   val result = Reg(UFix(width = 32))	//Global variable 
   val inputReg = Reg(UFix(width = 32))
   val outputReg = Reg(UFix(width = 8))
   val GS_FIRST_LEVEL = UFix(1)
   val GS_SECOND_LEVEL = UFix(2)
   val GS_THIRD_LEVEL = UFix(3)




val AllOffloadsReady =  
  Bool(true)
val AllOffloadsValid =  
  Bool(true)





  when (State === WaitForInputValid && io.in.valid) {
    inputReg := io.in.bits
    State := GS_FIRST_LEVEL
 
    SubState := WaitForReady 
  } 

  when (State === WaitForOutputReady && io.out.ready) {
    State := WaitForInputValid 
  } 

  when (SubState === WaitForReady && AllOffloadsReady) {
    SubState := WaitForValid 
  }

   when (SubState === WaitForValid && AllOffloadsValid) {
    when (State === GS_FIRST_LEVEL){
   result:=inputReg>>LOOKUP_FIRST_LEVEL_SHIFT 
   State:=GS_SECOND_LEVEL 
   } 
   
    when (State === GS_SECOND_LEVEL){
   result:=LOOKUP_SECOND_LEVEL_BASE+(result<<LOOKUP_SECOND_LEVEL_CHUNK_SIZE_LOG)+((inputReg>>(LOOKUP_SECOND_LEVEL_SHIFT))&LOOKUP_SECOND_LEVEL_MASK) 
   State:=GS_THIRD_LEVEL 
   } 
   
    when (State === GS_THIRD_LEVEL){
   result:=LOOKUP_THIRD_LEVEL_BASE+(result<<LOOKUP_THIRD_LEVEL_CHUNK_SIZE_LOG)+((inputReg>>(LOOKUP_THIRD_LEVEL_SHIFT))&LOOKUP_THIRD_LEVEL_MASK) 
   outputReg:=result 
   State := WaitForOutputReady
   } 
    SubState := WaitForReady 
    } 


io.out.bits := outputReg
io.out.valid := State === WaitForOutputReady
io.in.ready := State === WaitForInputValid
}
