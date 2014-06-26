package Tutorial

import Chisel._
import Node._
import Literal._
import scala.collection.mutable.HashMap
import scala.math.log
import scala.math.abs
import scala.math.ceil
import scala.math.max
import scala.math.min


class BiDirFIFOIO[T <: Data] () (data: => T) extends Bundle {
  val enq = (new FIFOIO()) {data}.flip  
  val deq = (new FIFOIO()) {data}
}


class Fifo[T <: Data] (depth: Int) (t: => T) extends Component {
  val io = (new BiDirFIFOIO ()) {t};
  val enqPtr      = Reg(resetVal = UFix(0, width = sizeof(depth)))
  val deqPtr      = Reg(resetVal = UFix(0, width = sizeof(depth)))
  val isFull      = Reg(resetVal = Bool(false))
  val doEnq       = io.enq.ready && io.enq.valid
  val doDeq       = io.deq.ready && io.deq.valid
  val isEmpty     = !isFull && (enqPtr === deqPtr)
  val deqPtrInc  = deqPtr + UFix(1)
  val enqPtrInc  = enqPtr + UFix(1)
  val isFullNext = 
    Mux(doEnq && ~doDeq && (enqPtrInc === deqPtr), 
        Bool(true),
        Mux(doDeq && isFull, Bool(false), isFull))
  enqPtr := Mux(doEnq, enqPtrInc, enqPtr)
  deqPtr := Mux(doDeq, deqPtrInc, deqPtr)
  isFull := isFullNext
  val ram = Mem(depth) {t}
  when (doEnq) {
    ram(enqPtr) := io.enq.bits
  }
  io.enq.ready := !isFull
  io.deq.valid := !isEmpty
  io.deq.bits := ram(deqPtr)

}


class MyFifo extends Component {
  val io = (new BiDirFIFOIO()) {UFix(width = 32)}
  val f = (new Fifo(32)) {UFix(width = 32)}
  io <> f.io
}

class MyFifoTests(c: MyFifo) extends Tester(c, Array(c.io)) {
  defTests {
    val inputs_data = List(1, 2, 4, 8)
    val svars = new HashMap[Node, Node]()
    val ovars = new HashMap[Node, Node]()

    // let it spin for a bit
    for (time <- 0 until 5) {
      svars(c.io.enq.valid) = Bool(false)
      svars(c.io.enq.bits) = UFix(0) 
      step(svars, ovars, false)
    }

    var sourced = 0
    var sinked = 0
    var time = 0
	var sinkStarted = false
    var allPassed = true

    while(time < 200 && (sourced < 4 || sinked < 4)) {
      if (sourced < 4) {
        svars(c.io.enq.bits) = Bits(inputs_data(sourced))
        svars(c.io.enq.valid) = Bool(true)
      } else {
        svars(c.io.enq.bits) = UFix(0) 
        svars(c.io.enq.valid) = Bool(false)
      }

      // this advances the clock
      step(svars, ovars)

      // bump counters and check outputs after advancing clock
      if (ovars(c.io.enq.ready).litValue() == 1) sourced += 1
      if (ovars(c.io.deq.valid).litValue() == 1 && sinkStarted) {
        allPassed = allPassed && ovars(c.io.deq.bits).litValue() == inputs_data(sinked)
        sinked += 1
      }
	  if (time >10) {
		svars(c.io.deq.ready) = UFix(1)
		sinkStarted = true 
	  }
      time += 1
    }
    allPassed && time < 200
  }
}

