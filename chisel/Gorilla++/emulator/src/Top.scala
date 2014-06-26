//XX variable version 1
package Tutorial

import Chisel._
import Node._
import Literal._
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer



class Top extends Component with include {
  val io = new gInOutBundle (() => new KEngineIO_t , () => new KEngineIO_t)

  val KEngine_MD =new gComponentMD(() => new KEngineIO_t, () => new KEngineIO_t, ArrayBuffer(("distanceFU", () => new distanceFU_in_t , () => new distanceFU_out_t), ("centeroidMem", () => new pointMemInput_t , () => new pointMemOutput_t), ("partialAccumulatorMem", () => new pointMemInput_t , () => new pointMemOutput_t), ("add", () => new FU_in_t , () => new FU_out_t)))
  val ke =  (KEngine_MD , () => new KEngine ).asInstanceOf[(gComponentMD[Chisel.Data,Chisel.Data], () => gComponent[Chisel.Data,Chisel.Data])]
  val KDistribute_MD =new gComponentMD(() => new KEngineIO_t, () => new KEngineIO_t, ArrayBuffer())
  val kd =  (KDistribute_MD , () => new KDistribute ).asInstanceOf[(gComponentMD[Chisel.Data,Chisel.Data], () => gComponent[Chisel.Data,Chisel.Data])]
  val KReduce_MD =new gComponentMD(() => new KEngineIO_t, () => new KEngineIO_t, ArrayBuffer(("partialAccumulatorMem", () => new pointMemInput_t , () => new pointMemOutput_t), ("add", () => new FU_in_t , () => new FU_out_t)))
  val kr =  (KReduce_MD , () => new KReduce ).asInstanceOf[(gComponentMD[Chisel.Data,Chisel.Data], () => gComponent[Chisel.Data,Chisel.Data])]
  val distanceFU_MD =new gComponentMD(() => new distanceFU_in_t, () => new distanceFU_out_t, ArrayBuffer())
  val dFU =  (distanceFU_MD , () => new distanceFU ).asInstanceOf[(gComponentMD[Chisel.Data,Chisel.Data], () => gComponent[Chisel.Data,Chisel.Data])]
  val add_MD =new gComponentMD(() => new FU_in_t, () => new FU_out_t, ArrayBuffer())
  val add =  (add_MD , () => new add ).asInstanceOf[(gComponentMD[Chisel.Data,Chisel.Data], () => gComponent[Chisel.Data,Chisel.Data])]
  val pAccMem_MD =new gComponentMD(() => new pointMemInput_t, () => new pointMemOutput_t, ArrayBuffer())
  val pa =  (pAccMem_MD , () => new pAccMem ).asInstanceOf[(gComponentMD[Chisel.Data,Chisel.Data], () => gComponent[Chisel.Data,Chisel.Data])]
  val cMem_MD =new gComponentMD(() => new pointMemInput_t, () => new pointMemOutput_t, ArrayBuffer())
  val cm =  (cMem_MD , () => new cMem ).asInstanceOf[(gComponentMD[Chisel.Data,Chisel.Data], () => gComponent[Chisel.Data,Chisel.Data])]
  val ab1 = ArrayBuffer((dFU, "distanceFU"), (cm, "centeroidMem"), (pa, "partialAccumulatorMem"), (add, "add"))
  val kePlus = Offload (ke, ab1, extCompName="kePlus")
  val ab2 = ArrayBuffer((pa, "partialAccumulatorMem"), (add, "add"))
  val krPlus = Offload (kr, ab2, extCompName="krPlus")
  val xx1 = Replicate (kePlus, 2, extCompName="xx1")
  val result = Chain (kd,  xx1 , krPlus, extCompName="result")
  val generatedTop = result._2()
  generatedTop.io <> io
}

class TopTests(o: Top) extends Tester(o, Array(o.io)) with include {
  defTests {
    val inputs_data = List(1, 2, 4, 8)
    val svars = new HashMap[Node, Node]()
    val ovars = new HashMap[Node, Node]()
    //val test = new Top 
    val iDelay = 4

    for (time <- 0 until 5) {
      svars(o.io.in.valid) = Bool(false)
      //svars(o.io.in.bits) = UFix(0) 
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
    var sourcedPoints = 0
    while(sourcedPoints < NUM_OF_POINTS.litValue().intValue || sinked < NUM_OF_CENTEROIDS.litValue().intValue) {
      if (cycles % iDelay == 0) {
        sourced match {
          case x if (x < NUM_OF_CENTEROIDS.litValue().intValue) => {
            svars(o.io.in.bits.pointsFinished) = Bool(false)
            svars(o.io.in.bits.centeroidsFinished) = Bool(false)
            svars(o.io.in.valid) = Bool(true)
            svars(o.io.out.ready) = Bool(true)
          }
          case x if (x == NUM_OF_CENTEROIDS.litValue().intValue) => {
            svars(o.io.in.bits.pointsFinished) = Bool(false)
            svars(o.io.in.bits.centeroidsFinished) = Bool(true)
            svars(o.io.in.valid) = Bool(true)
            svars(o.io.out.ready) = Bool(true)
          }
          case x if (x > NUM_OF_CENTEROIDS.litValue().intValue && x <= NUM_OF_CENTEROIDS.litValue().intValue + NUM_OF_POINTS.litValue().intValue) => {
            svars(o.io.in.bits.pointsFinished) = Bool(false)
            svars(o.io.in.bits.centeroidsFinished) = Bool(false)
            svars(o.io.in.valid) = Bool(true)
            svars(o.io.out.ready) = Bool(true)
          }
          case x if (x == NUM_OF_CENTEROIDS.litValue().intValue + 1 + NUM_OF_POINTS.litValue().intValue) => {
            svars(o.io.in.bits.pointsFinished) = Bool(true)
            svars(o.io.in.bits.centeroidsFinished) = Bool(false)
            svars(o.io.in.valid) = Bool(true)
            svars(o.io.out.ready) = Bool(true)
          }
	      case _ => {
            svars(o.io.in.bits.pointsFinished) = Bool(false)
            svars(o.io.in.bits.centeroidsFinished) = Bool(false)
            svars(o.io.in.valid) = Bool(false)
            svars(o.io.out.ready) = Bool(false)
	      }
       }
     } else {
       svars(o.io.in.bits.pointsFinished) = Bool(false)
       svars(o.io.in.bits.centeroidsFinished) = Bool(false)
       svars(o.io.in.valid) = Bool(false)
       svars(o.io.out.ready) = Bool(false)
     }
      step(svars, ovars)
      // bump counters and check outputs after advancing clock
      if (ovars(o.io.in.ready).litValue() == 1 && (cycles % iDelay == 0)) {
        if (sourced > NUM_OF_CENTEROIDS.litValue().intValue && sourced <= NUM_OF_CENTEROIDS.litValue().intValue + NUM_OF_POINTS.litValue().intValue) {
          sourcedPoints += 1
        }
        sourced += 1
        sourcedIndex = sourced % 4 
        println("sourced and sourcedIndex are " + sourced + " " + sourcedIndex)
      }
      cycles += 1
      if (ovars(o.io.out.valid).litValue() == 1) {
        //allPassed = allPassed && (ovars(o.io.out.bits).litValue() == (inputs_data(sinkedIndex) + 2))
        if (allPassed == false) {
			println("Test failed ") //+ ovars(o.io.out.bits).litValue() + 
                          //" expected " + (inputs_data(sinkedIndex) +2))
			println("Sinked is " + sinked)
        }
		println("At " + time + " outpout " + " sinked. Sinked is " + sinked + " sourced is " + sourced );
        sinked += 1
        sinkedIndex = sinked % 4
      }
      time += 1
    }
    println("PCREPORT: throughput " + NUM_OF_POINTS.litValue().intValue.toDouble/cycles.toDouble)
    println("PCREPORT: cycles " + cycles)

    for ((n,i) <- Pcounters.moduleIDs) {
      step(svars, ovars)
      svars(o.io.pcIn.valid) = Bool(true)
      svars(o.io.pcIn.bits.request) = Bool(true) 
      svars(o.io.pcIn.bits.pcType) = Pcounters.backPressure
      svars(o.io.pcIn.bits.moduleId) = UFix(i)
      svars(o.io.pcIn.bits.portId) = UFix(1) //Input port
      step(svars, ovars)
      svars(o.io.pcIn.valid) = Bool(false)
      while(ovars(o.io.pcOut.valid).litValue().intValue() == 0) {
        step(svars, ovars)
      }
      println("PCREPORT: back pressure " +  n + " received " + ovars(o.io.pcOut.bits.pcValue).litValue().intValue().toDouble/cycles.toDouble)
      step(svars, ovars)
    }
    step(svars, ovars)
    println("PCREPORT: Post simulation")
    //Doesnt work. All numbers are printed as -1
    //printPC(o)

    allPassed = allPassed && sinked == NUM_OF_CENTEROIDS.litValue().intValue 
    allPassed 
  }
}


