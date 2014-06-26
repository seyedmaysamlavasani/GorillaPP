//XX variable version 5
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

  val uw = MTEngine("updateWriter.c", 4)
  val ug =  MTEngine("updateGenerator.c", 1)
  val mem1 = prMem() 
  val mem2 = prMem() 
  val lock = Engine("lock.c")
  val add = FPDPAdder()
  val mul = FPDPMultiplier()
  val div = FPDPDivider()
  val rcOffs = ArrayBuffer((mul, "mul"), (div, "div"))
  val xx1 = Engine("rankCalculator.c")
  val rc = Offload( xx1 , rcOffs)
  val xx4 = Offload(ug, rc, "rankCalc")
  val xx2 = Replicate( xx4 , 1)
  val ugPlus = Offload( xx2 , mem1, "mem")
  val ab = ArrayBuffer((lock, "lock"), (add, "add"))
  val xx5 = Offload(uw, ab)
  val xx3 = Replicate( xx5 , 1)
  val uwPlus = Offload( xx3 , mem2, "mem")
  val result = Chain(ugPlus, uwPlus)   
}

