package Tutorial

import Chisel._
import Node._
import Literal._
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer


class ipv4Engine extends gComponentLeaf  (() => new NP_EthIPv4Header_t)(() => new NP_EthIPv4Header_t) (ArrayBuffer(("lookup", () => UFix(width = 32) , () => UFix(width = 8))))  with include {
  val WaitForInputValid = UFix(0, 8)
  val WaitForOutputReady = UFix(255, 8)
  val WaitForReady = UFix(0, 1)
  val WaitForValid = UFix(1, 1)
  val State = Reg(resetVal=WaitForInputValid) 
  val SubState = Reg(resetVal=WaitForReady)

   val inputReg = Reg(new NP_EthIPv4Header_t)
   val outputReg = Reg(new NP_EthIPv4Header_t)
   def  myOff = io.elements.find(_._1 == "off").getOrElse(elseV)._2
   def mymyOff = myOff.asInstanceOf[Bundle].elements.find(_._1 == "lookup").getOrElse(elseV)._2
   val lookupPort = new gOffBundleND(() => UFix(width = 32), () => UFix(width = 8))
   lookupPort <>mymyOff
   val GS_ETHERNET = UFix(1)
   val GS_IPV4 = UFix(2)
   val GS_LOOKUP = UFix(3)
   val GS_UPDATE = UFix(4)
   val GS_EXCEPTION = UFix(5)

  lookupPort.rep.ready := SubState === WaitForValid

val lookup_ready_received = Reg(resetVal=Bool(false))
val lookup_valid_received = Reg(resetVal=Bool(false))
lookup_ready_received := !(SubState === WaitForValid) && (lookup_ready_received || lookupPort.req.ready)
lookup_valid_received := !(SubState === WaitForReady) && (lookup_valid_received || lookupPort.rep.valid)

val lookupPortHadRequest = Reg(resetVal=Bool(false))

val AllOffloadsReady =  
  (lookupPort.req.ready || lookup_ready_received || !lookupPort.req.valid) &&  
  Bool(true)
val AllOffloadsValid =  
  (lookupPort.rep.valid || lookup_valid_received || !lookupPortHadRequest) && 
  Bool(true)

lookupPortHadRequest := Mux(SubState === WaitForReady, lookupPortHadRequest || lookupPort.req.valid, lookupPortHadRequest && !AllOffloadsValid)



   lookupPort.req.bits := inputReg.ipv4.dstAddr
   lookupPort.req.valid := State === GS_LOOKUP
   val outPort = lookupPort.rep.bits

  when (State === WaitForInputValid && io.in.valid) {
    inputReg := io.in.bits
    State := GS_UPDATE
 
    SubState := WaitForReady 
  } 

  when (State === WaitForOutputReady && io.out.ready) {
    State := WaitForInputValid 
  } 

  when (SubState === WaitForReady && AllOffloadsReady) {
    SubState := WaitForValid 
  }

   when (SubState === WaitForValid && AllOffloadsValid) {
    when (State === GS_ETHERNET){
   when (inputReg.l2Protocol===ETHERNET) {
   State:=GS_IPV4 
   }
   .otherwise {
   State:=GS_EXCEPTION 
   }
   } 
   
    when (State === GS_IPV4){
   when (inputReg.eth.l3Type===IPV4) {
   State:=GS_LOOKUP 
   outputReg:=inputReg 
   }
   .otherwise {
   State:=GS_EXCEPTION 
   }
   } 
   
    when (State === GS_LOOKUP){
   outputReg.outPort:=outPort 
   State:=GS_UPDATE 
   } 
   
    when (State === GS_UPDATE){
   when (inputReg.ipv4.ttl===UFix(1, width = 64)) {
   State:=GS_EXCEPTION 
   }
   .otherwise {
   outputReg.ipv4.ttl:=inputReg.ipv4.ttl-UFix(1, width = 64) 
   outputReg.ipv4.chksum:=inputReg.ipv4.chksum+UFix(128, width = 64) 
   }
   State := WaitForOutputReady
   } 
   
    when (State === GS_EXCEPTION){
   State := WaitForOutputReady
   } 
    SubState := WaitForReady 
    } 


io.out.bits := outputReg
io.out.valid := State === WaitForOutputReady
io.in.ready := State === WaitForInputValid
}
