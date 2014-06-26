package Tutorial

import Chisel._
import Node._
import Literal._
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer


/*This source implements a module that add a constat value to each input data element,
 * and send the result out as the output data element. The constant value is retrived 
  val generatedTop = result._2()
  generatedTop.io <> io
 * through an offload interface. 
 */
class Top extends Component with GorillaUtil {
 val io = new gInOutBundle (() => UFix(width = 32), () => UFix(width = 32))
 val a = new gComponentMD (() => UFix(width = 32), () => UFix(width = 32), 
               ArrayBuffer(("IncFactor", () => UFix(width = 32), () => UFix(width = 32))))
 val b = new gComponentMD (() => UFix(width = 32), () => UFix(width = 32), 
               ArrayBuffer())

 //val c = (a, () => new IncThroughOffloadgComponent)
 val c = (a, () => new incThroughOffload)
 //val d = (b, () => new SendConstantgComponent)
 val d = (b, () => new sendConst)

 //val e =  Offload(c, d, "constOff")
///////////////////////////////////////////////////////////
 val e =  Offload(Replicate(c, 2), d, "IncFactor")
//////////////////////////////////////////////////////////
 val f = e._2()
 println("final object name is " + f.getClass.getName)
 io <> f.io



//  val io = new gInOutBundle (() => UFix(width = 32), () => UFix(width = 32))
//
//  val incThroughOffload_MD =new gComponentMD(() => UFix(width = 32), () => UFix(width = 32), ArrayBuffer(("incrementFactor", () => UFix(width = 32) , () => UFix(width = 32))))
//  val main =  (incThroughOffload_MD , () => new incThroughOffload )
//  val sendConst_MD =new gComponentMD(() => UFix(width = 32), () => UFix(width = 32), ArrayBuffer())
//  val incFactor =  (sendConst_MD , () => new SendConstantgComponent )
//  //val incFactor =  (sendConst_MD , () => new sendConst )
//  val result =  Offload(Replicate(main, 2), incFactor, "incFactor")
//  val generatedTop = result._2()
//  generatedTop.io <> io
}

class TopTests(o: Top) extends Tester(o, Array(o.io)) {
  defTests {
    val inputs_data = List(1, 2, 4, 8)
    val svars = new HashMap[Node, Node]()
    val ovars = new HashMap[Node, Node]()
    //val test = new Top 
    val iDelay = 4

    for (time <- 0 until 5) {
      svars(o.io.in.valid) = Bool(false)
      svars(o.io.in.bits) = UFix(0) 
      svars(o.io.pcIn.valid)= Bool(false)
      svars(o.io.pcIn.bits.pcType)= Pcounters.pcReset
      svars(o.io.pcIn.bits.moduleId)= UFix(0) 
      svars(o.io.pcIn.bits.portId)= UFix(0) 
      step(svars, ovars, false)
    }

    svars(o.io.pcIn.valid) = Bool(true)
    svars(o.io.pcIn.bits.request) = Bool(true) 
    svars(o.io.pcIn.bits.pcType) = Pcounters.pcReset
    step(svars, ovars)
    svars(o.io.pcIn.valid) = Bool(false)
    while(ovars(o.io.pcOut.valid).litValue().intValue == 0) {
      step(svars, ovars)
    }
    println("PCREPORT: Performance counter reset received")
    var sourced = 0
    var sourcedIndex = 0
    var sinked = 0
    var sinkedIndex = 0
    var time = 0
	//var sinkStarted = false
    var allPassed = true
    var cycles = 0

    while(sourced < 200 || sinked < 200) {
      if ((sourced < 200) && (cycles % iDelay == 0)) {
        svars(o.io.in.bits) = Bits(inputs_data(sourcedIndex))
        svars(o.io.in.valid) = Bool(true)
        svars(o.io.out.ready) = Bool(true)
      } else {
        svars(o.io.in.bits) = UFix(0) 
        svars(o.io.in.valid) = Bool(false)
        svars(o.io.out.ready) = Bool(true)
      }
      // this advances the clock
      step(svars, ovars)
      cycles += 1
      // bump counters and check outputs after advancing clock
      if (ovars(o.io.in.ready).litValue() == 1 && (cycles % iDelay == 0)) {
        sourced += 1
        sourcedIndex = sourced % 4 
        println("sourced and sourcedIndex are " + sourced + " " + sourcedIndex)
      }
      if (ovars(o.io.out.valid).litValue() == 1) {
        allPassed = allPassed && (ovars(o.io.out.bits).litValue() == (inputs_data(sinkedIndex) + 2))
        if (allPassed == false) {
			println("Test failed because output is " + ovars(o.io.out.bits).litValue() + 
                          " expected " + (inputs_data(sinkedIndex) +2))
			println("Sinked is " + sinked)
        }
		println("At " + time + " outpout " + ovars(o.io.out.bits).litValue() + " sinked");
        sinked += 1
        sinkedIndex = sinked % 4
      }
      time += 1
    }
    step(svars, ovars)
    svars(o.io.pcIn.valid) = Bool(true)
    svars(o.io.pcIn.bits.request) = Bool(true) 
    svars(o.io.pcIn.bits.pcType) = Pcounters.backPressure
    svars(o.io.pcIn.bits.moduleId) = UFix(3) //incthrough module Replicated
    //svars(o.io.pcIn.bits.moduleId) = UFix(2) //incthroug module Offloaded
    svars(o.io.pcIn.bits.portId) = UFix(1) //Input port
    step(svars, ovars)
    svars(o.io.pcIn.valid) = Bool(false)
    while(ovars(o.io.pcOut.valid).litValue().intValue() == 0) {
      step(svars, ovars)
    }
    println("PCREPORT: Performance counter received " + ovars(o.io.pcOut.bits.pcValue))
    step(svars, ovars)

    allPassed 
  }
}


