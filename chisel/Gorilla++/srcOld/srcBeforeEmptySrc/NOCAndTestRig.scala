package Tutorial 

import Chisel._
import Node._;
import Literal._;
import scala.collection.mutable.HashMap
import scala.math.log
import scala.math.abs
import scala.math.ceil
import scala.math.max
import scala.math.min
import util.Random

class MyLFSR16 extends Component {
  val io = new Bundle {
    val out = UFix(OUTPUT, 16) 
  }
  val data = Reg(resetVal = (UFix(1, 16)))

  data := Cat(data(0)^data(2)^data(3)^data(5), data(15,1)).toUFix 
  io.out := data  
}

class TrafficGenerator (ChksumLength: Int = 1) extends Component {
  val io = new Bundle {
    val enable = Bits(INPUT, 1)
    val out = (new FIFOIO()) {new FlitIO()}
    val packetLost = Bits(OUTPUT, 1)
    val sourcedPackets = UFix(OUTPUT, 64)
    val sourcedFlits = UFix(OUTPUT, 64)
    val sourcedChksum = UFix(OUTPUT, ChksumLength) 
    val injectionRate = UFix(INPUT, 4)
//    val queuedFlits = UFix(OUTPUT, 64)
  }
  val waiting::sentHead::Nil = Enum(2){UFix(width=1)}
  val state = Reg(resetVal = waiting)
  val lfsr = new MyLFSR16()
  val remainingFlits = Reg(resetVal = UFix(0, width = 2))   
  val queuedFlitsReg = Reg(resetVal = UFix(0, 64))
 
  val triger = 
    Mux((queuedFlitsReg > UFix(0)) && io.enable && io.out.ready, 
      UFix(1,1), UFix(0,1))

  val sourcedPacketsReg = Reg(resetVal = UFix(0, 64))
  val sourcedFlitsReg = Reg(resetVal = UFix(0, 64))
  val sourcedChksumReg = Reg(resetVal = UFix(0, ChksumLength))

  val randomData = lfsr.io.out ## lfsr.io.out 
  val randomDataForChksum = randomData(ChksumLength-1, 0)

  def chksum (in:UFix):UFix = {
    in ^ randomDataForChksum ^ (xorR(randomData))
  }


  //io.queuedFlits := queuedFlitsReg

  io.out.valid := MuxCase(Bool(false), 
                    Array((((state === waiting) && triger), Bool(true)),
                    (((state === sentHead) && remainingFlits != UFix(0) && triger), 
                      Bool(true)),
                    (((state === sentHead) && remainingFlits === UFix(0) && triger), 
                      Bool(true))))

  io.out.bits.head := MuxCase(Bool(false), 
                    Array((((state === waiting) && triger), Bool(true)),
                    (((state === sentHead) && remainingFlits != UFix(0) && triger), 
                      Bool(false)),
                    (((state === sentHead) && remainingFlits === UFix(0) && triger), 
                      Bool(false))))

  io.out.bits.tail := MuxCase(Bool(false), 
                    Array((((state === waiting) && triger), Bool(false)),
                    (((state === sentHead) && remainingFlits != UFix(0) && triger), 
                      Bool(false)),
                    (((state === sentHead) && remainingFlits === UFix(0) && triger), 
                      Bool(true))))
 
  io.out.bits.data := randomData 

    
  when (lfsr.io.out(3,0) < io.injectionRate) {
    when (!triger) {
      queuedFlitsReg := queuedFlitsReg + UFix(1)
    }
  } .otherwise {
    when (triger) {
      queuedFlitsReg := queuedFlitsReg - UFix(1)
    } 
  }

  when ((state === waiting) && triger) {
    state := sentHead 
    remainingFlits := lfsr.io.out(3,0) + UFix(1)
    sourcedPacketsReg := sourcedPacketsReg + UFix(1)
    sourcedFlitsReg := sourcedFlitsReg + UFix(1)
    sourcedChksumReg := chksum(sourcedChksumReg) 
  } .elsewhen (state === sentHead && remainingFlits != UFix(0) && triger) {
    state := sentHead 
    remainingFlits := remainingFlits - UFix(1)
    sourcedFlitsReg := sourcedFlitsReg + UFix(1)
    sourcedChksumReg := chksum(sourcedChksumReg) 
  } .elsewhen (state === sentHead && remainingFlits === UFix(0) && triger) {
    state := waiting 
    remainingFlits := UFix(0) 
    sourcedFlitsReg := sourcedFlitsReg + UFix(1)
    sourcedChksumReg := chksum(sourcedChksumReg) 
  }

  io.packetLost := (io.out.ready === Bool(false))
  io.sourcedPackets := sourcedPacketsReg
  io.sourcedFlits := sourcedFlitsReg
  io.sourcedChksum := sourcedChksumReg
}


class StatCollector (ChksumLength : Int = 1) extends Component {
  val io = new Bundle {
    val in = (new FIFOIO()) {new FlitIO()}.flip
    val sinkedPackets = UFix(OUTPUT, 64)
    val sinkedFlits = UFix(OUTPUT, 64)
    val sinkedChksum = UFix(OUTPUT, ChksumLength)
  }
  val sinkedPacketsReg = Reg(resetVal = UFix(0, 64))
  val sinkedFlitsReg = Reg(resetVal = UFix(0, 64))
  val sinkedChksumReg = Reg(resetVal = UFix(0, ChksumLength))
  def chksum (in1:UFix, in2:UFix):UFix = {
    in1 ^ in2 ^ (xorR(in2))
  }

  when(io.in.valid && io.in.bits.head) { 
    sinkedPacketsReg := sinkedPacketsReg + UFix(1)
  }
  when(io.in.valid) { 
    sinkedFlitsReg := sinkedFlitsReg + UFix(1)
    sinkedChksumReg := chksum(sinkedChksumReg, io.in.bits.data)
  }
  io.sinkedPackets := sinkedPacketsReg
  io.sinkedFlits := sinkedFlitsReg
  io.sinkedChksum := sinkedChksumReg
  io.in.ready := Bool(true)
}


class NOCAndTestRig(aray: Int, ChksumLength : Int = 1) extends Component {
  val io = new Bundle {
    val enable = Bits(INPUT, 1)
    val injectionRate = UFix(INPUT, 4) 
    val sourcedChksum = Bits(OUTPUT, ChksumLength)
    val sourcedPackets = UFix(OUTPUT, 64)
    val sourcedFlits = UFix(OUTPUT, 64)
    val sinkedChksum = Bits(OUTPUT, ChksumLength)
    val sinkedPackets = UFix(OUTPUT, 64)
    val sinkedFlits = UFix(OUTPUT, 64)
    val error = Bits(OUTPUT, 1)
  }
  
  val mesh = new MyTwoDMesh(aray)
  val tg = Range(0, aray, 1).map(i =>
                   new Range(0, aray, 1).map(j =>
                     new TrafficGenerator())) 

  val inputFifos = Range(0, aray, 1).map(i =>
                   new Range(0, aray, 1).map(j =>
                     new InPortFifo())) 

  val sc = Range(0, aray, 1).map(i =>
                   new Range(0, aray, 1).map(j =>
                     new StatCollector())) 
 
  for (i <- 0 until aray) {
    for (j <- 0 until aray) {
      tg(i)(j).io.injectionRate := io.injectionRate 
      tg(i)(j).io.enable := io.enable 
      tg(i)(j).io.out <> inputFifos(i)(j).io.enq
      inputFifos(i)(j).io.deq <> mesh.io.tap((i*aray)+j).enq
      mesh.io.tap((i*aray)+j).deq <> sc(i)(j).io.in
    }
  }
  
 io.sourcedPackets := Range(0, aray).map(i => Range(0, aray).map(j => 
    tg(i)(j).io.sourcedPackets).reduceLeft(_+_)).reduceLeft(_+_)  
 io.sourcedFlits := Range(0, aray).map(i => Range(0, aray).map(j => 
    tg(i)(j).io.sourcedFlits).reduceLeft(_+_)).reduceLeft(_+_)  
 io.sourcedChksum := Range(0, aray).map(i => Range(0, aray).map(j => 
    tg(i)(j).io.sourcedChksum).reduceLeft(_^_)).reduceLeft(_^_)  

 io.sinkedPackets := Range(0, aray).map(i => Range(0, aray).map(j => 
    sc(i)(j).io.sinkedPackets).reduceLeft(_+_)).reduceLeft(_+_)  
 io.sinkedFlits := Range(0, aray).map(i => Range(0, aray).map(j => 
    sc(i)(j).io.sinkedFlits).reduceLeft(_+_)).reduceLeft(_+_)  
 io.sinkedChksum := Range(0, aray).map(i => Range(0, aray).map(j => 
    sc(i)(j).io.sinkedChksum).reduceLeft(_^_)).reduceLeft(_^_)  

 io.error := Range(0, aray).map(i => Range(0, aray).map(j => 
    tg(i)(j).io.packetLost).reduceLeft(_|_)).reduceLeft(_|_)  

}

class NOCAndTestRigTests(c: NOCAndTestRig) extends Tester(c, Array(c.io)) {
//class NOCAndTestRigTests(c: NOCAndTestRig) extends Tester(c, Array(c.io, c.tg(0)(0).io.queuedFlits)) {
//class NOCAndTestRigTests(c: NOCAndTestRig) extends Tester(c, Array(c.io, c.tg(0)(0).io.out, c.tg(0)(0).state, c.tg(0)(0).triger, c.tg(0)(0).remainingFlits, c.inputFifos(0)(0).io)) {
//class NOCAndTestRigTests(c: NOCAndTestRig) extends Tester(c, Array(c.io, c.mesh.io.tap(0), c.mesh.io.tap(3), c.sc(0)(0).io, c.sc(1)(1).io)) {
//class NOCAndTestRigTests(c: NOCAndTestRig) extends Tester(c, Array(c.io, c.mesh.io.tap(0))) {
//class NOCAndTestRigTests(c: NOCAndTestRig) extends Tester(c, Array(c.io)) {
  defTests{
    val svars = new HashMap[Node, Node]()
    val ovars = new HashMap[Node, Node]()
    for (i <- 0 until 5) {
      //************should change another line too 
      svars(c.io.injectionRate) = UFix(15)
      svars(c.io.enable) = Bits(0)
      step(svars, ovars, true) 
    }

    for (i <- 0 until 1000000) {
      svars(c.io.enable) = Bits(1)
      svars(c.io.injectionRate) = UFix(15)
      if (i % 1000 == 0) {
        println("time is " + i + " sinked packets are " + 
          ovars(c.io.sinkedPackets).litValue().intValue())
        println("time is " + i + " sinked flits are " + 
          ovars(c.io.sinkedFlits).litValue().intValue())
        println("time is " + i + " sinked checksum is " + 
          ovars(c.io.sinkedChksum).litValue().intValue())

        println("time is " + i + " sourced packets are " + 
          ovars(c.io.sourcedPackets).litValue().intValue())
        println("time is " + i + " sourced flits are " + 
          ovars(c.io.sourcedFlits).litValue().intValue())
        println("time is " + i + " sourced checksum is " + 
          ovars(c.io.sourcedChksum).litValue().intValue())
 
        println("time is " + i + " on the fly packets are " + 
          (ovars(c.io.sourcedPackets).litValue().intValue() -
            ovars(c.io.sinkedPackets).litValue().intValue()))
        println("time is " + i + " on the fly flits are " + 
          (ovars(c.io.sourcedFlits).litValue().intValue() -
            ovars(c.io.sinkedFlits).litValue().intValue()))
//        println("time is " + i + " queued flits 0,0 are " + 
//          ovars(c.tg(0)(0).io.queuedFlits).litValue().intValue()) 
       }
      step(svars, ovars, false) 
      //step(svars, ovars, true) 
    }
    true
  }  
}

