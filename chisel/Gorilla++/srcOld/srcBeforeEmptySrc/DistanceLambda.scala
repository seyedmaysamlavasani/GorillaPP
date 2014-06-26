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

class CoreInstrument extends Bundle {
  val waiting = Reg(resetVal=UFix(0, 32))
  val waitForPoint = Reg(resetVal=UFix(0, 32))
  val collectCenters = Reg(resetVal=UFix(0, 32))
  val collectCentersTail = Reg(resetVal=UFix(0, 32))
  val receivePoints = Reg(resetVal=UFix(0, 32)) 
  val receivePointX = Reg(resetVal=UFix(0, 32))
  val receivePointY = Reg(resetVal=UFix(0, 32))
  val receivePointsTail = Reg(resetVal=UFix(0, 32))
  val computeReq = Reg(resetVal=UFix(0, 32))
  val compute = Reg(resetVal=UFix(0, 32))
  val aggregateReq = Reg(resetVal=UFix(0, 32))
  val aggregate = Reg(resetVal=UFix(0, 32))
  val sendOutResultsHead = Reg(resetVal=UFix(0, 32))
  val sendOutResults = Reg(resetVal=UFix(0, 32))
  val sendOutResultsTail = Reg(resetVal=UFix(0, 32))
  val averageReq = Reg(resetVal=UFix(0, 32))
  val average = Reg(resetVal=UFix(0, 32))
}


class DistanceLambdaCore extends Component with StreamUtil {

  val io = new Bundle {
    val inStream = (new FIFOIO()) {new StreamData}.flip()
    val outStream = (new FIFOIO()) {new StreamData}
    val error = Bits(OUTPUT, 1)
    val instrument = new InstrumentIO
  }

  def update_simple_counter(n: UFix, inc: Bool, dec: Bool) = 
    Mux(inc && !dec, n+UFix(1),
    Mux(!inc && dec, n-UFix(1), n))

  def simple_counter(max: UFix, inc: Bool, dec: Bool) = {
    val x = Reg(resetVal = UFix(0, max.getWidth))
    x := update_simple_counter(x, inc, dec)
    x
  }
 
  //////////////////////////////////////////////////////
  // Local Buffering State and Signals
  //////////////////////////////////////////////////////
  
  val id                    = Reg(resetVal=UFix(0,32)) // point count
  val bufferLimit           = KmeansConfig.local_points // half depth of blockRAM
  val localPointCount       = Reg(resetVal=UFix(0,32))
  val incLocalBuffer        = Bool()
  val decLocalBuffer        = Bool()
  val localBufferLevel      = simple_counter(UFix(4), incLocalBuffer, decLocalBuffer)
  val backPressure          = Bool()

  //////////////////////////////////////////////////////
  // Fetch State Machine
  //////////////////////////////////////////////////////
 
  val centerWidth           = 10
  val numOfCenters          = Reg(resetVal=UFix(0,10))
  val numOfPoints           = Reg(resetVal=UFix(0,32))
  val pointCount            = Reg(resetVal=UFix(0,32))

  val numFetchStates        = 9
  val state                 = Reg(resetVal=UFix(0,4))
  val idle::collectCenters::collectCentersTail::receivePoints::receivePointElements::waitForCompute::sendOutResultsHead::sendOutResults::sendOutResultsTail::Nil = Enum(numFetchStates) {UFix()}

  //////////////////////////////////////////////////////
  // Compute State Machine
  //////////////////////////////////////////////////////
 
  val startCompute          = Bool()
  val finishCompute         = Bool()
  val computeQueueSize      = simple_counter(UFix(2), startCompute, finishCompute)
  val computeCyclesLeft     = Reg(resetVal=UFix(0,32))

  val numComputeStates      = 2
  val computeState          = Reg(resetVal=UFix(0,5)) 
  val computeIdle::computeBusy::Nil = Enum{numComputeStates} {UFix()}

  //////////////////////////////////////////////////////
  // Core instrumentation
  //////////////////////////////////////////////////////
 
  val ci = new CoreInstrument
  ci.waiting := UFix(0) 
  ci.waitForPoint := UFix(0) 
  ci.collectCenters := UFix(0) 
  ci.collectCentersTail := UFix(0) 
  ci.receivePoints := UFix(0) 
  ci.receivePointX := UFix(0) 
  ci.receivePointY := UFix(0) 
  ci.receivePointsTail := UFix(0) 
  ci.computeReq := UFix(0) 
  ci.compute := UFix(0) 
  ci.aggregateReq := UFix(0) 
  ci.aggregate := UFix(0) 
  ci.sendOutResultsHead := UFix(0) 
  ci.sendOutResults := UFix(0) 
  ci.sendOutResultsTail := UFix(0) 
  ci.averageReq := UFix(0) 
  ci.average := UFix(0) 
  io.instrument.data       := UFix(0) 

  //////////////////////////////////////////////////////
  // Default combinational values
  ////////////////////////////////////////////////////// 
 
  backPressure          := Bool(false)
  startCompute          := Bool(false)
  finishCompute         := Bool(false)
  incLocalBuffer        := Bool(false)
  decLocalBuffer        := Bool(false)

  //////////////////////////////////////////////////////
  // Fetch FSM
  ////////////////////////////////////////////////////// 

  def assert(cond: Bool) = {
    when(!cond) {
      println("[debug] ERROR")
    }
  } 

  when (io.inStream.valid) {

    when (state === idle) {
      state := collectCenters
      assert(io.inStream.bits.header)
      numOfCenters := countPart(io.inStream.bits.data)
      id := UFix(0)
    }

    when (state === collectCenters) {
      id := id + UFix(1)
      when (id === (numOfCenters*UFix(KmeansConfig.dims, centerWidth)-UFix(1, centerWidth))) {
        state := collectCentersTail
      }
    }

    when (state === collectCentersTail) {
      assert(io.inStream.bits.trailer)
      state := receivePoints
    }

    when (state === receivePoints) {
      assert(io.inStream.bits.header)



      ///////////////////////////////////////////////////////////////////////
      // UNDO THIS !!!!
      numOfPoints := countPart(io.inStream.bits.data)
      //numOfPoints := UFix(512) // countPart(io.inStream.bits.data)
      ///////////////////////////////////////////////////////////////////////



      state := receivePointElements
      pointCount := UFix(0)
      localPointCount := UFix(0)
    }

    when (state === receivePointElements) {
      // if only 0 or 1 buffers loaded
      when (localBufferLevel < UFix(2)) {
        // collected enough points to start computation
        when (localPointCount === ((UFix(KmeansConfig.dims,32) * UFix(bufferLimit,32)) - UFix(1,32))) {
          startCompute := Bool(true)
          incLocalBuffer := Bool(true)
          localPointCount := UFix(0)
        } .otherwise {
          localPointCount := localPointCount + UFix(1)
        }

        // all points loaded => wait for computation
        when (pointCount === (UFix(KmeansConfig.dims,32)*numOfPoints) - UFix(1,32)) {
          state := waitForCompute
          pointCount := UFix(0)
        } .otherwise {
          pointCount := pointCount + UFix(1)
        }

      } 
      .otherwise {
        backPressure := Bool(true) // backpressure if insufficient buffer space
      }
    }
  }

  when (state === waitForCompute) {
    when ((computeState === computeIdle) && (computeQueueSize === UFix(0))) {
      state := sendOutResultsHead
    }
  }

  when ((state === sendOutResultsHead) && io.outStream.ready) {
    state := sendOutResults
    pointCount := UFix(0)
  }

  when ((state === sendOutResults) && io.outStream.ready) {
    pointCount := pointCount + UFix(1)
    when (pointCount === (numOfCenters * UFix(KmeansConfig.dims) - UFix(1))) {
      state := sendOutResultsTail
    }
  }

  when ((state === sendOutResultsTail) && io.outStream.ready) {
    state := idle
  }

  //////////////////////////////////////////////////////
  // Compute FSM
  //////////////////////////////////////////////////////

  when (computeState === computeIdle) {
    when (computeQueueSize > UFix(0)) {
      computeCyclesLeft := UFix(bufferLimit) * UFix(KmeansConfig.dims) * numOfCenters + UFix(KmeansConfig.dims) * numOfCenters
      computeState := computeBusy
    }
  } 

  when (computeState === computeBusy) {
    when (computeCyclesLeft > UFix(0)) {
      computeCyclesLeft := computeCyclesLeft - UFix(1)
    } .otherwise {
      computeCyclesLeft := UFix(0)
      finishCompute := Bool(true)
      decLocalBuffer := Bool(true) // used up a buffer
      computeState := computeIdle
    }
  }

  io.inStream.ready         := !backPressure
  io.outStream.valid        := state === sendOutResults ||
                               state === sendOutResultsHead ||
                               state === sendOutResultsTail
  io.outStream.bits.header  := state === sendOutResultsHead
  io.outStream.bits.trailer := state === sendOutResultsTail
  io.outStream.bits.data    := Mux(state === sendOutResultsHead, mkHeader(sTCMDCentersResult, numOfCenters), UFix(0))
}
