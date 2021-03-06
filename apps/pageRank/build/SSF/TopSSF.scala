//XX variable version 9
package Tutorial

import Chisel._
import Node._
import Literal._
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer


/*This source implements a PageRank accelerator */
//The module is supposed to work in a distributed environment
//through NIC IO. In our test environment, we have a work distributor
//which gets a piece of work and distribute it among different update
//generators. Update generators generates updates for each piece of work 
//and send them to the Update writers.
//The tester send only one bulk piece of work to work distributor.
//work distributor divides this bulk piece of work 
// among generators and send a finish signal
//at the end. Update generator will change the mode after getting the finish
//signal and pass the finish signal. Update writers are also pass the finish 
//signals at the end.

class Top extends Component with include {
  //val io = new gInOutBundle (() => new bulkUpdate_t , () => new prDone_t)
  val io = new gInOutBundle (() => new bulkUpdate_t , () => new update_t)

  val uw = MTEngine("updateWriter.c", #uwThreads)
  val ug =  MTEngine("updateGenerator.c", #ugThreads)
  val mem1 = prMem() 
  val mem2 = prMem() 
  val lock = PipeEngine("lockModel.c")
  //var add : (gComponentMD[Chisel.Data,Chisel.Data], () => gComponent[Chisel.Data,Chisel.Data])
  val add1 = PipedFPDPAdder()
  val add2 = FPDPAdder()
  var add = add1
  if (#addPipelined) {
    add = add1 
  } else {
    add = add2 
  }
  //var mul : (gComponentMD[Chisel.Data,Chisel.Data], () => gComponent[Chisel.Data,Chisel.Data])
  val mul1 = PipedFPDPMultiplier()
  val mul2 = FPDPMultiplier()
  var mul = mul1
  if (#mulPipelined) {
    mul = mul1 
  } else {
    mul = mul2 
  }
  //var div :  (gComponentMD[Chisel.Data,Chisel.Data], () => gComponent[Chisel.Data,Chisel.Data])
  val div1 = PipedFPDPDivider()
  val div2 = FPDPDivider()
  var div = div1
  if (#divPipelined) {
    div = div1 
  } else {
    div = div2 
  }
  val rcOffs = ArrayBuffer((mul, "mul"), (div, "div"))
  //var rc :  (gComponentMD[Chisel.Data,Chisel.Data], () => gComponent[Chisel.Data,Chisel.Data])
  val xx1 = PipeEngine("rankCalculator.c")
  val rc1 = Offload( xx1 , rcOffs)
  val xx2 = Engine("rankCalculator.c")
  val rc2 = Offload( xx2 , rcOffs)
  var rc = rc1
  if (#rcPipelined) {
    rc = rc1 
  } else {
    rc = rc2 
  }
  //var ugPlus : (gComponentMD[Chisel.Data,Chisel.Data], () => gComponent[Chisel.Data,Chisel.Data])
  val xx7 = Replicate(ug, #ugRepFactor)
  val xx3 = Offload( xx7 , rc , "rankCalc")
  val ugPlus1 = Offload( xx3 , mem1, "mem")
  val xx8 = Offload(ug, rc, "rankCalc")
  val xx4 = Replicate( xx8 , #ugRepFactor)
  val ugPlus2 = Offload( xx4 , mem1, "mem")
  var ugPlus = ugPlus1
  if (#sharedRc) {
    ugPlus = ugPlus1
  } else {
    ugPlus = ugPlus2
  }
  val ablm = ArrayBuffer((lock, "lock"), (mem2, "mem"))
  val ablma = ArrayBuffer((lock, "lock"), (mem2, "mem"), (add, "add"))
  val xx5 = Replicate(uw, #uwRepFactor)
  val uwPlus1 = Offload( xx5 , ablma)
  val xx9 = Offload(uw, add, "add")
  val xx6 = Replicate( xx9 , #uwRepFactor)
  val uwPlus2 = Offload( xx6 , ablm)
  var uwPlus = uwPlus1
  if (#sharedRa) {
    uwPlus = uwPlus1
  } else {
    uwPlus = uwPlus2
  }
  val result = Chain(ugPlus, uwPlus)   
}

