package Tutorial

import Chisel._
import Node._
import Literal._
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer

class gPipe[T <: Data](latency: Int = 1) extends 
 gComponentLeaf (() => UFix(width = 32)) (() => UFix(width = 32)) (ArrayBuffer()) () {
  val tags = Vec(latency) {Reg(UFix(width=5))}
  val valids = Vec(latency) {Reg(Bool(), resetVal=Bool(false))}
  when (io.out.ready) { 
    tags(0) := io.in.tag
    valids(0) := io.in.valid
  }
  for (i <- latency-1 to 1 by -1) {
    when (io.out.ready) {
      valids(i) := valids(i-1) 
      tags(i) := tags(i-1) 
    }
  } 
  io.out.tag := tags(latency-1) 
  io.out.valid := valids(latency-1) 
  io.in.ready := io.out.ready
}



class gTester (o: Component) extends Tester(o, Array(o.io)) {
  val svars = new HashMap[Node, Node]()
  val ovars = new HashMap[Node, Node]()

  def PCReport(cycles:Int, dataElements:Int) = {
    println("PCREPORT: throughput " + 
     "%.4f".format(dataElements.intValue.toDouble/
      cycles.toDouble))
    println("PCREPORT: cycles " + cycles)
    pausePC()
    getBackPressures(cycles)
    getOffloadRates(cycles)
    getEngineUtilizationis(cycles)
    getInOutTokens(dataElements)
  }

  def resetPC() = {
    val io = o.asInstanceOf[Top].io
    svars(io.in.valid) = Bool(false)
    svars(io.pcIn.valid) = Bool(true)
    svars(io.pcIn.bits.request) = Bool(true) 
    svars(io.pcIn.bits.pcType) = Pcounters.pcReset
    step(svars, ovars)
    svars(io.pcIn.valid) = Bool(false)
    while(ovars(io.pcOut.valid).litValue().intValue == 0) {
      step(svars, ovars)
    }
    println("PCREPORT: Performance counter reset received")
  }

  def pausePC() = {
    val io = o.asInstanceOf[Top].io
    svars(io.pcIn.valid) = Bool(true)
    svars(io.pcIn.bits.request) = Bool(true) 
    svars(io.pcIn.bits.pcType) = Pcounters.pcPause
    svars(io.pcIn.bits.moduleId)= UFix(0) 
    svars(io.pcIn.bits.portId)= UFix(0) 
    step(svars, ovars)
    svars(io.pcIn.valid) = Bool(false)
    while(ovars(io.pcOut.valid).litValue().intValue == 0) {
      step(svars, ovars)
    }
    println("PCREPORT: Performance counter pause ack received")
  }

  def getBackPressure(moduleId: Int, portId: Int) : Int = {
      val io = o.asInstanceOf[Top].io
      step(svars, ovars)
      svars(io.pcIn.valid) = Bool(true)
      svars(io.pcIn.bits.request) = Bool(true) 
      svars(io.pcIn.bits.pcType) = Pcounters.backPressure
      svars(io.pcIn.bits.moduleId) = UFix(moduleId)
      svars(io.pcIn.bits.portId) = UFix(portId) 
      step(svars, ovars)
      svars(io.pcIn.valid) = Bool(false)
      while(ovars(io.pcOut.valid).litValue().intValue() == 0) {
        step(svars, ovars)
      }
    ovars(io.pcOut.bits.pcValue).litValue().intValue
  }

  def getBackPressures(cycles: Int) = {
    val io = o.asInstanceOf[Top].io
    for ((name, id) <- Pcounters.moduleIDs) {
      //Input backPressure
      println("PCREPORT: input back pressure " +  name + 
       " received " + "%.4f".format(getBackPressure(id, 1).
       litValue().intValue().toDouble/cycles.toDouble))
      step(svars, ovars)
      //Output backPressure
      println("PCREPORT: output back pressure " +  name + 
       " received " + "%.4f".format(getBackPressure(id, 2).
       toDouble/cycles.toDouble))
      //Offload backpressure
      for (i <- 3 to Pcounters.numOfOffloadPorts(name)+2) {
        println("PCREPORT: offload back pressure " +  i + 
         " " + name + " received " + "%.4f".format(
          getBackPressure(id, i).toDouble/cycles.toDouble))
      }
    }
  }

  def getGenericRatioPc(cycles: Int, pcName: String, 
   pcType: UFix) {
    val io = o.asInstanceOf[Top].io
    for ((name, id) <- Pcounters.moduleIDs) {
      step(svars, ovars)
      svars(io.pcIn.valid) = Bool(true)
      svars(io.pcIn.bits.request) = Bool(true) 
      svars(io.pcIn.bits.pcType) = pcType
      svars(io.pcIn.bits.moduleId) = UFix(id)
      svars(io.pcIn.bits.portId) = UFix(0) //Doesnt matter
      step(svars, ovars)
      svars(io.pcIn.valid) = Bool(false)
      while(ovars(io.pcOut.valid).litValue().intValue() == 0) {
        step(svars, ovars)
      }
      println("PCREPORT: " + pcName + " " +  name + " received " + 
       "%.4f".format(ovars(io.pcOut.bits.pcValue).litValue().
        toDouble/cycles.toDouble))
      step(svars, ovars)
    }
  }

  def getEngineUtilizationis(cycles: Int) = {
    getGenericRatioPc(cycles, "engine utilization", 
     Pcounters.engineUtilization)
  }

  def getInOutTokens(inputs: Int) = {
    getGenericRatioPc(inputs, "in tokens", Pcounters.inTokens)
    getGenericRatioPc(inputs, "out tokens", Pcounters.outTokens)
  }

  def getOffloadRates(cycles: Int) = {
    getGenericRatioPc(cycles, "offload rate", 
     Pcounters.offloadRate)
  }

  def round(value: Either[Double, Float], places: Int) = {
    if (places < 0) 0
    else {
      val factor = Math.pow(10, places)
      value match {
        case Left(d) => (Math.round(d * factor) / factor)
        case Right(f) => (Math.round(f * factor) / factor)
      }
    }
  }
  
  def round(value: Double): Double = 
   round(Left(value), 0)
  def round(value: Double, places: Int): Double = 
   round(Left(value), places)
  def round(value: Float): Double = 
   round(Right(value), 0)
  def round(value: Float, places: Int): Double = 
   round(Right(value), places)
}


