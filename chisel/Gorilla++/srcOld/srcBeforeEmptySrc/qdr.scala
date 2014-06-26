package Tutorial

import Chisel._
import Node._
import Literal._
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer


class qdr extends gComponentLeaf  (() => UFix(width = 32))(() => UFix(width = 8)) (ArrayBuffer())  with include {
  val WaitForInputValid = UFix(0, 8)
  val WaitForOutputReady = UFix(255, 8)
  val WaitForReady = UFix(0, 1)
  val WaitForValid = UFix(1, 1)
  val State = Reg(resetVal=WaitForInputValid) 
  val SubState = Reg(resetVal=WaitForReady)

   val inputReg = Reg(UFix(width = 32))
   val outputReg = Reg(UFix(width = 8))
   val GS_GDR_EMULATE = UFix(1)




val AllOffloadsReady =  
  Bool(true)
val AllOffloadsValid =  
  Bool(true)





  when (State === WaitForInputValid && io.in.valid) {
    inputReg := io.in.bits
    State := GS_GDR_EMULATE
 
    SubState := WaitForReady 
  } 

  when (State === WaitForOutputReady && io.out.ready) {
    State := WaitForInputValid 
  } 

  when (SubState === WaitForReady && AllOffloadsReady) {
    SubState := WaitForValid 
  }

   when (SubState === WaitForValid && AllOffloadsValid) { SubState := WaitForReady 
    } 


io.out.bits := outputReg
io.out.valid := State === WaitForOutputReady
io.in.ready := State === WaitForInputValid
}
