package Tutorial

import Chisel._
import Node._
import Literal._
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer

class IncgComponent extends gComponentLeaf (() => UFix(width = 32)) (() => UFix(width = 32)) (ArrayBuffer(("testOff", () => UFix(width = 32), () => UFix(width = 32)))) {
 val outputData = Reg(UFix(width=32)) 
 val outputValid = Reg(Bool(false)) 
 outputValid := io.in.valid
 outputData := io.in.bits + UFix(1, 32)
 io.in.ready :=  io.out.ready
 io.out.bits :=  outputData
 io.out.valid := outputValid && io.out.ready
 io.out.bits := outputData
}
 
class Chained extends Component with GorillaUtil {
 val io = new gInOutBundle (() => UFix(width = 32), () => UFix(width = 32))
 val a = new gComponentMD (() => UFix(width = 32), 
               () => UFix(width = 32), 
               ArrayBuffer(("testOff1", () => UFix(width = 32), () => UFix(width = 32))))
 val b = new gComponentMD (() => UFix(width = 32), 
               () => UFix(width = 32), 
               ArrayBuffer(("testOff2", () => UFix(width = 32), () => UFix(width = 32))))

 val c = (a, () => new IncgComponent)
 val d = (b, () => new IncgComponent)

 val e =  Chain(c,d)
 io <> e._2().io
}

class ChainedTests(c: Chained) extends Tester(c, Array(c.io)) {
  defTests {
    val inputs_data = List(1, 2, 4, 8)
    val svars = new HashMap[Node, Node]()
    val ovars = new HashMap[Node, Node]()

    // let it spin for a bit
    for (time <- 0 until 5) {
      svars(c.io.in.valid) = Bool(false)
      svars(c.io.in.bits) = UFix(0) 
      step(svars, ovars, false)
    }

    var sourced = 0
    var sinked = 0
    var time = 0
	//var sinkStarted = false
    var allPassed = true

    while(time < 200 && (sourced < 4 || sinked < 4)) {
      if (sourced < 4) {
        svars(c.io.in.bits) = Bits(inputs_data(sourced))
        svars(c.io.in.valid) = Bool(true)
        svars(c.io.out.ready) = Bool(true)
      } else {
        svars(c.io.in.bits) = UFix(0) 
        svars(c.io.in.valid) = Bool(false)
      }
      // this advances the clock
      step(svars, ovars)
      // bump counters and check outputs after advancing clock
      if (ovars(c.io.in.ready).litValue() == 1) sourced += 1
      if (ovars(c.io.out.valid).litValue() == 1) {
        allPassed = allPassed && (ovars(c.io.out.bits).litValue() == (inputs_data(sinked) + 2))
        if (allPassed == false) {
			println("Test failed because output is " + ovars(c.io.out.bits).litValue() + 
                          " expected " + (inputs_data(sinked) +2))
			println("Sinked is " + sinked)
        }
        //allPassed = allPassed 
        sinked += 1
      }
	  //if (time >10) {
	  //  svars(c.io.out.ready) = UFix(1)
	  //  sinkStarted = true 
	  //}
      time += 1
    }
    allPassed && time < 200
  }
}

