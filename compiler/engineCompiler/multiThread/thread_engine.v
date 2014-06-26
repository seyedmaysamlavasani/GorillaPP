package Tutorial

import Chisel._
import Node._
import Literal._
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer


class incThroughOffload extends gComponentLeaf {
  val WaitForInputValid = UFix(0, 8)
  val WaitForOutputReady = UFix(255, 8)
  val WaitForReady = UFix(0, 1)
  val WaitForValid = UFix(1, 1)
  val State = Reg(resetVal=WaitForInput) 
  val SubState = Reg(resetVal=WaitForReady)

  when (State === WaitForInputValid && io.in.valid) {
    inputReg := io.in.bits
    State := GS_INITIALIZE
 
    subState := WaitForReady 
  } 

  when (State === WaitForOutputReady && io.out.ready) {
    State := WaitForInputValid 
  } 

  when (SubState === WaitForReady && AllOffloadsReady) {
    SubState := ContextEdit
  }

   when (SubState === ContextEdit && AllOffloadsValid) {
    when (State === GS_INITIALIZE){
   wait:=10 
   State:=GS_COUNT 
   } 
   
    when (State === GS_COUNT){
   wait:=waitVar-1 
   when (wait===0) {
   State:=GS_GET_INC_FACTOR 
   }
   } 
   
    when (State === GS_GET_INC_FACTOR){
   incF := incrementFactor.io.rep.bits
   Output:=Input+incF 
   State := WaitForOutputReady
   } 
    SubState := RequestBuilder 
    } 
   waitVar:=wait 
   incrementFactor.io.req.bits := Input
   incrementFactor.io.req.valid := State === GS_GET_INC_FACTOR

}
