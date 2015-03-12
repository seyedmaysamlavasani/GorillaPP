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

  val updateWriter_MD = new gComponentMD(() => new update_t, () => new prDone_t, ArrayBuffer(("mem", () => new mem_req_t , () => new mem_rep_t), ("lock", () => new lock_req_t , () => new lock_rep_t), ("add", () => new FU_in_t , () => new FU_out_t)))
  val  uw =  (updateWriter_MD , () => new updateWriter (extCompName="uw")).asInstanceOf[(gComponentMD[Chisel.Data,Chisel.Data], () => gComponent[Chisel.Data,Chisel.Data])]
  val updateGenerator_MD = new gComponentMD(() => new bulkUpdate_t, () => new update_t, ArrayBuffer(("mem", () => new mem_req_t , () => new mem_rep_t), ("rankCalc", () => new rankCalc_in_t , () => new rankCalc_out_t)))
  val  ug =  (updateGenerator_MD , () => new updateGenerator (extCompName="ug")).asInstanceOf[(gComponentMD[Chisel.Data,Chisel.Data], () => gComponent[Chisel.Data,Chisel.Data])]
  val mem1 = prMem() 
  val mem2 = prMem() 
  val lockModel_MD = new gComponentMD(() => new lock_req_t, () => new lock_rep_t, ArrayBuffer())
  val  lock =  (lockModel_MD , () => new lockModel (extCompName="lock")).asInstanceOf[(gComponentMD[Chisel.Data,Chisel.Data], () => gComponent[Chisel.Data,Chisel.Data])]
  //var add : (gComponentMD[Chisel.Data,Chisel.Data], () => gComponent[Chisel.Data,Chisel.Data])
  val adder_MD = new gComponentMD(() => new FU_in_t, () => new FU_out_t, ArrayBuffer())
  val  add1 =  (adder_MD , () => new adder (extCompName="add1")).asInstanceOf[(gComponentMD[Chisel.Data,Chisel.Data], () => gComponent[Chisel.Data,Chisel.Data])]
  val  add2 =  (adder_MD , () => new adder (extCompName="add2") ).asInstanceOf[(gComponentMD[Chisel.Data,Chisel.Data], () => gComponent[Chisel.Data,Chisel.Data])]
  var add = add1
  if (false) {
    add = add1 
  } else {
    add = add2 
  }
  //var mul : (gComponentMD[Chisel.Data,Chisel.Data], () => gComponent[Chisel.Data,Chisel.Data])
  val multiplier_MD = new gComponentMD(() => new FU_in_t, () => new FU_out_t, ArrayBuffer())
  val  mul1 =  (multiplier_MD , () => new multiplier (extCompName="mul1")).asInstanceOf[(gComponentMD[Chisel.Data,Chisel.Data], () => gComponent[Chisel.Data,Chisel.Data])]
  val  mul2 =  (multiplier_MD , () => new multiplier (extCompName="mul2") ).asInstanceOf[(gComponentMD[Chisel.Data,Chisel.Data], () => gComponent[Chisel.Data,Chisel.Data])]
  var mul = mul1
  if (false) {
    mul = mul1 
  } else {
    mul = mul2 
  }
  //var div :  (gComponentMD[Chisel.Data,Chisel.Data], () => gComponent[Chisel.Data,Chisel.Data])
  val divider_MD = new gComponentMD(() => new FU_in_t, () => new FU_out_t, ArrayBuffer())
  val  div1 =  (divider_MD , () => new divider (extCompName="div1")).asInstanceOf[(gComponentMD[Chisel.Data,Chisel.Data], () => gComponent[Chisel.Data,Chisel.Data])]
  val  div2 =  (divider_MD , () => new divider (extCompName="div2") ).asInstanceOf[(gComponentMD[Chisel.Data,Chisel.Data], () => gComponent[Chisel.Data,Chisel.Data])]
  var div = div1
  if (false) {
    div = div1 
  } else {
    div = div2 
  }
  val rcOffs = ArrayBuffer((mul, "mul"), (div, "div"))
  //var rc :  (gComponentMD[Chisel.Data,Chisel.Data], () => gComponent[Chisel.Data,Chisel.Data])
  val rankCalculator_MD = new gComponentMD(() => new rankCalc_in_t, () => new rankCalc_out_t, ArrayBuffer(("mul", () => new FU_in_t , () => new FU_out_t), ("div", () => new FU_in_t , () => new FU_out_t)))
  val  xx1 =  (rankCalculator_MD , () => new rankCalculator (extCompName="xx1")).asInstanceOf[(gComponentMD[Chisel.Data,Chisel.Data], () => gComponent[Chisel.Data,Chisel.Data])]
  val  rc1 = Offload ( xx1 , rcOffs, extCompName="rc1")
  val  xx2 =  (rankCalculator_MD , () => new rankCalculator (extCompName="xx2") ).asInstanceOf[(gComponentMD[Chisel.Data,Chisel.Data], () => gComponent[Chisel.Data,Chisel.Data])]
  val  rc2 = Offload ( xx2 , rcOffs, extCompName="rc2")
  var rc = rc1
  if (false) {
    rc = rc1 
  } else {
    rc = rc2 
  }
  //var ugPlus : (gComponentMD[Chisel.Data,Chisel.Data], () => gComponent[Chisel.Data,Chisel.Data])
  val  xx7 = Replicate (ug, 2, extCompName="xx7")
  val  xx3 = Offload ( xx7 , rc , "rankCalc", extCompName="xx3")
  val  ugPlus1 = Offload ( xx3 , mem1, "mem", extCompName="ugPlus1")
  val  xx8 = Offload (ug, rc, "rankCalc", extCompName="xx8")
  val  xx4 = Replicate ( xx8 , 2, extCompName="xx4")
  val  ugPlus2 = Offload ( xx4 , mem1, "mem", extCompName="ugPlus2")
  var ugPlus = ugPlus1
  if (false) {
    ugPlus = ugPlus1
  } else {
    ugPlus = ugPlus2
  }
  val ablm = ArrayBuffer((lock, "lock"), (mem2, "mem"))
  val ablma = ArrayBuffer((lock, "lock"), (mem2, "mem"), (add, "add"))
  val  xx5 = Replicate (uw, 4, extCompName="xx5")
  val  uwPlus1 = Offload ( xx5 , ablma, extCompName="uwPlus1")
  val  xx9 = Offload (uw, add, "add", extCompName="xx9")
  val  xx6 = Replicate ( xx9 , 4, extCompName="xx6")
  val  uwPlus2 = Offload ( xx6 , ablm, extCompName="uwPlus2")
  var uwPlus = uwPlus1
  if (false) {
    uwPlus = uwPlus1
  } else {
    uwPlus = uwPlus2
  }
  val  result = Chain (ugPlus, uwPlus, extCompName="result")   
  val generatedTop = result._2()
  generatedTop.io <> io
}

class TopTests(o: Top) extends gTester(o) with include {
  defTests {
    val svars = new HashMap[Node, Node]()
    val ovars = new HashMap[Node, Node]()
    val iDelay = 1

    for (time <- 0 until 5) {
      svars(o.io.in.valid) = Bool(false)
      step(svars, ovars, false)
    }
    if (compilerControl.pcEnable) {
      resetPC()
    }
    val timeOut = 500
    var sourced = 0
    var sinked = 0
    var allPassed = true
    var cycles = 0
    var lastReceivedCycle = 0 
    var pageId = 0
    var pageChunkSize = 5 
    val numOfPageChunks = 20  
    while((sourced < numOfPageChunks) /*|| (sinked < 400)*/ && ((cycles-lastReceivedCycle) < timeOut)) {
      if (cycles % 200 == 0) {
        println("At " + cycles + " lastReceived was " + lastReceivedCycle )
      }
      pageId = sourced * pageChunkSize
      svars(o.io.out.ready) = Bool(true)
      svars(o.io.in.bits.startPageId) = UFix(pageId) 
      svars(o.io.in.bits.length) = UFix(pageChunkSize)
      if (cycles % iDelay == 0 && sourced < numOfPageChunks) {
        if (sourced == numOfPageChunks-1) {
          svars(o.io.in.valid) = Bool(true)
          svars(o.io.in.bits.done) = Bool(false)  //TODO: making this true causes a problem
        } else { 
          svars(o.io.in.valid) = Bool(true)
          svars(o.io.in.bits.done) = Bool(false)
        }
      } else {
        svars(o.io.in.valid) = Bool(false)
      }
      step(svars, ovars)
      // bump counters and check outputs after advancing the clock
      if (ovars(o.io.in.ready).litValue() == 1 && 
       (cycles % iDelay == 0) && 
       (sourced < numOfPageChunks)) {
        sourced += 1
        println("sourced is " + sourced)
      }
      cycles += 1
      if (ovars(o.io.out.valid).litValue() == 1) {
        if (allPassed == false) {
          println("Test failed ") 
          println("Sinked is " + sinked)
        }
        sinked += 1
        println("At " + cycles + " outpout " + " sinked. Sinked is " + sinked + 
         " sourced is " + sourced );
        lastReceivedCycle = cycles;
      }
    }
    println("At the end, after while loop\n");
    if (compilerControl.pcEnable) {
      println("XXX last received at " + lastReceivedCycle + " " + cycles)
      PCReport(lastReceivedCycle, numOfPageChunks * pageChunkSize)
    }
    step(svars, ovars)
    allPassed 
  }
}


