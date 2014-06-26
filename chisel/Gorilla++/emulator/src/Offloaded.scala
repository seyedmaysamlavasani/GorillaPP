package Tutorial

import Chisel._
import Node._
import Literal._
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer

class IncThroughOffloadgComponent extends gComponentLeaf (() => UFix(width = 32)) (() => UFix(width = 32)) (ArrayBuffer(("constOff", () => UFix(width = 32), () => UFix(width = 32)))) {
//  println("This is the main component")
//  val waitForInput :: requesting :: waitForOffload :: waitForCounter :: waitForOutput :: Nil = Enum(5){UFix()} 
//  val counterResetValue = UFix(2, 32)
//  val inputReg = Reg(resetVal = UFix(0, 32)) 
//  val offReg = Reg(resetVal = UFix(0, 32)) 
//  val counter = Reg(resetVal = counterResetValue) 
//  val state = Reg(resetVal = waitForInput)
  def  myOff = io.elements.find(_._1 == "off").getOrElse(elseV)._2//.asInstanceOf[gOffBundle[UFix, UFix]]
  def mymyOff = myOff.asInstanceOf[Bundle].elements.find(_._1 == "constOff").getOrElse(elseV)._2 
 
  val offWire = new gOffBundleND(() => UFix(width = 32), () => UFix(width = 32))
  offWire <> mymyOff
//  when (state === waitForInput && io.in.valid) {
//    inputReg := io.in.bits
//    state := requesting    
//  } .elsewhen (state === requesting && offWire.req.ready) {
//    state := waitForOffload 
//  } .elsewhen (state === waitForOffload && offWire.rep.valid) {
//    state := waitForCounter 
//    offReg := offWire.rep.bits
//  } .elsewhen (state === waitForCounter) {
//    when (counter === UFix(0)) {
//      state := waitForOutput 
//      counter := counterResetValue 
//    } .otherwise {
//      counter := counter - UFix(1)
//    }
//  } .elsewhen (state === waitForOutput && io.out.ready) {
//    state := waitForInput 
//  } .otherwise {
//  }
//  io.in.ready := state === waitForInput 
//  io.out.valid := state === waitForOutput 
//  io.out.bits := inputReg + offReg 
//  offWire.req.valid := state === requesting 
//  offWire.rep.ready := state != waitForOutput 
  io.in.ready := Bool(true) 
  io.out.valid := Bool(true) 
  io.out.bits := offWire.rep.bits 

}

//class SendConstantgComponent extends gComponentLeaf (() => UFix(width = 32)) (() => UFix(width = 32)) (ArrayBuffer(("test", () => UFix(width = 32), ()=> UFix(width = 32)))) {
class SendConstantgComponent extends gComponentLeaf (() => UFix(width = 32)) (() => UFix(width = 32)) (ArrayBuffer()) {
 val outputData = Reg(UFix(width=32)) 
 val outputValid = Reg(Bool(false)) 
 outputValid := io.in.valid
 outputData := UFix(2, 32)
 io.in.ready :=  io.out.ready
 io.out.bits :=  outputData
 io.out.valid := outputValid && io.out.ready
 io.out.bits := outputData
}
 
class Offloaded extends Component with GorillaUtil {
 //def report() = {bfs { n => println(n.name) }}
 val io = new gInOutBundle (() => UFix(width = 32), () => UFix(width = 32))
 val a = new gComponentMD (() => UFix(width = 32), () => UFix(width = 32), 
               ArrayBuffer(("constOff", () => UFix(width = 32), () => UFix(width = 32))))
 val b = new gComponentMD (() => UFix(width = 32), () => UFix(width = 32), 
               ArrayBuffer())

 val c = (a, () => new IncThroughOffloadgComponent)
 val d = (b, () => new SendConstantgComponent)

 //val e =  Offload(c, d, "constOff", "e")
///////////////////////////////////////////////////////////
 val e =  Offload(Replicate(c, 2, "o"), d, "constOff", "e")
//////////////////////////////////////////////////////////
 val f = e._2()
 println("final object name is " + f.getClass.getName)
 io <> f.io
 //io <> d._2().io
}

class OffloadedTests(o: Offloaded) extends Tester(o, Array(o.io)) {
  defTests {
    val inputs_data = List(1, 2, 4, 8)
    val svars = new HashMap[Node, Node]()
    val ovars = new HashMap[Node, Node]()
    val test = new Offloaded 
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

