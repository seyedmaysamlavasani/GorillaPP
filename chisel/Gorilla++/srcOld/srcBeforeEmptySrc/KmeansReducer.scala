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

class ReducerFifo extends Component {
  val io = ((new RFifoIO )) {new FlitIO()}
  val fifo = (new RFifo(100)) {Bits(width = routerConfig.flitDataWidth+2)}
  fifo.io.enq.valid := io.enq.valid 
  fifo.io.deq.valid <> io.deq.valid 
  fifo.io.enq.ready <> io.enq.ready 
  fifo.io.deq.ready <> io.deq.ready 
  fifo.io.enq.bits := io.enq.bits.head ## io.enq.bits.tail ## io.enq.bits.data

  io.deq.bits.data := fifo.io.deq.bits(routerConfig.flitDataWidth-1, 0)  
  io.deq.bits.tail := (fifo.io.deq.bits(routerConfig.flitDataWidth) === UFix(1))
  io.deq.bits.head := (fifo.io.deq.bits(routerConfig.flitDataWidth+1) === UFix(1))
}

class KmeansReducer(degree: Int) extends Component {
  val io = new Bundle {
    val inStreams = Vec(degree) {(new FIFOIO()) {new StreamData()}}.flip()
    val outStream = (new FIFOIO()) {new StreamData()}
    val error = Bool(OUTPUT)
  }
  val rfifos = Range(0, degree).map(i => new ReducerFifo)
  val serialize = new Serialize(degree) 
  val sAverage = new SerialAverage(degree)

  serialize.io.inStreams <>  io.inStreams
//Put fifos in front of serializer drain 
//the network faster
  //for (i <- 0 until degree) {
  //  rfifos(i).io.enq <> io.inStreams(i)
  //  serialize.io.inStreams(i) <> rfifos(i).io.deq
  //}
  serialize.io.outStream <> sAverage.io.inStream
  sAverage.io.outStream <> io.outStream
  io.error := sAverage.io.error
}

class Serialize(degree: Int) extends Component with StreamUtil {
  val io = new Bundle {
    val inStreams = Vec(degree) {(new FIFOIO()) {new StreamData()}}.flip()
    val outStream = (new FIFOIO()) {new StreamData()}
  }
  val waiting::sendHeader::merge::sendTrailer::Nil=Enum(4){UFix()}
  val state = Reg(resetVal=waiting)
  val currOutput = Reg(resetVal = UFix(0, width = log2Up(degree)))
 
  val allValid = (Range(0, degree).map(i => io.inStreams(i).valid)).reduceLeft(_&&_)

  val currentReady = Range(0, degree).map(i => io.inStreams(i).ready && 
                                            currOutput === UFix(i)).reduceLeft(_||_)

  val currentValid = Range(0, degree).map(i => io.inStreams(i).valid && 
                                            currOutput === UFix(i)).reduceLeft(_||_)

  val currentHeader = Range(0, degree).map(i => io.inStreams(i).bits.header && 
                                            currOutput === UFix(i)).reduceLeft(_||_)

  val currentTrailer = Range(0, degree).map(i => io.inStreams(i).bits.trailer && 
                                            currOutput === UFix(i)).reduceLeft(_||_)
  
  val currData = MuxCase(UFix(0), 
                  Range(0, degree).map(i => 
                    (UFix(i) === currOutput, io.inStreams(i).bits.data)))
 
  when (state === waiting && allValid) {
    state := sendHeader 
  }

  when (state === sendHeader && io.outStream.ready) {
    state := merge
    currOutput := UFix(0)
  }

  when (state === merge && currOutput === UFix(degree)-UFix(1) && 
    currentTrailer && currentValid && io.outStream.ready) {
    state := sendTrailer 
  }   

  when (state === merge && currOutput != UFix(degree)-UFix(1) && 
    currentTrailer && currentValid && io.outStream.ready) {
    currOutput := currOutput + UFix(1) 
  }   
 
  when (state === sendTrailer && io.outStream.ready) {
    state := waiting
  }

  for (i <- 0 until degree) {
    io.inStreams(i).ready := state === merge && 
                             io.outStream.ready &&
                             currOutput === UFix(i)
  }


  io.outStream.valid := state === sendHeader || 
                        state === sendTrailer || 
                        (currentReady && state === merge && currentValid)

  io.outStream.bits.header := state === sendHeader || 
                              state === merge && 
                              currentHeader
 
  io.outStream.bits.trailer := state === sendTrailer || 
                              state === merge && 
                              currentTrailer

  io.outStream.bits.data := Mux(state === sendHeader, 
                                sTCMDGroup, 
                                currData)                                
}

class SerialAverage (numOfStreams: Int) extends Component with StreamUtil {
  val io = new Bundle {
    val inStream = (new FIFOIO()) {new StreamData}.flip()
    val outStream = (new FIFOIO()) {new StreamData}
    val error = Bool(OUTPUT)
  }

  val waiting::receiveGroupHeader::receiveHeader::receive::addReq::add::divideReq::divide::receiveGroupTrailer::receiveTrailer::sendHeader::send::sendTrailer::Nil = Enum(13){UFix()}


  // 0 waiting
  // 1 receiveGroupHeader
  // 2 receiveHeader
  // 3 receive 
  // 4 addReq
  // 5 add
  // 6 divideReq
  // 7 divide
  // 8 receiveGroupTrailer
  // 9 receiveTrailer
  // 10 sendHeader
  // 11 send
  // 12 sendTrailer

  val state = Reg(resetVal=waiting)

  val addFU = new AddFU
  val divideFU = new DivideFU
  val centers = UFix(KmeansConfig.centers)
  val dims = UFix(KmeansConfig.dims) 

  val centersMem = Range(0, KmeansConfig.dims).map(i => 
                     new BRAM(LINQAccConfig.BramWordSize, LINQAccConfig.BramSizeLog))

  val lastCenterId = UFix(KmeansConfig.centers) - UFix(1)
  val lastDimId = UFix(KmeansConfig.dims) - UFix(1) 
 
  val groupId = Reg(resetVal=Bits(0, 6))
  val id = Reg(resetVal=Bits(0, 32))
  val dim = Reg(resetVal=Bits(0, 5))
     
  val errorReg = Reg(resetVal=Bool(false))
  val data = Reg(resetVal=Bits(0, KmeansConfig.pointWidth))

  io.error := errorReg

  def assert(cond: Bool) = {
      errorReg := errorReg || !cond
  }

  when (state === waiting && io.inStream.valid) {
    state := receiveGroupHeader  
  }

  when (state === receiveGroupHeader && io.inStream.valid) {
    assert(io.inStream.bits.header)
    assert(io.inStream.bits.data === sTCMDGroup)  
    groupId := UFix(0)  
    state := receiveHeader
  }

  when (state === receiveHeader && io.inStream.valid) { 
    assert(io.inStream.bits.header)
    assert(io.inStream.bits.data === sTCMDCentersResult)  
    id := UFix(0)
    dim := UFix(0)
    state := receive 
  } 

  when (state === receive && io.inStream.valid) {
    data := io.inStream.bits.data
    state := addReq
  }

  io.inStream.ready := state === receiveGroupHeader ||
                       state === receiveHeader  ||
                       state === receive ||
                       state === receiveGroupTrailer ||
                       state === receiveTrailer
   
  val fillMem = state === add && addFU.io.outputValid

  for (i <- 0 until KmeansConfig.dims) {
    centersMem(i).io.req.address := id
    centersMem(i).io.req.wData := addFU.io.out 
    centersMem(i).io.req.rw := fillMem && dim === UFix(i)
    centersMem(i).io.req.enable := Bool(true)
  }
  
  val savedData = Mux1H(UFixToOH(dim, 2), 
                  Range(0, KmeansConfig.dims).map(i => centersMem(i).io.reply.rData))
  addFU.io.in0 := data.toFix()
  addFU.io.in1 := savedData.toFix() 
  addFU.io.inputValid := state === addReq 
 
  when (state === addReq && addFU.io.inputReady) {
    state := add
  }

  when (state === add && addFU.io.outputValid) {
    when (id === lastCenterId &&
          dim === lastDimId) {
      state := receiveTrailer 
      id := UFix(0)
      dim := UFix(0)
    } 

    when (id != lastCenterId &&
          dim === lastDimId) {
      state := receive
      dim := UFix(0)
      id := id + UFix(1)
    }

    when (dim != lastDimId) {
      state := receive
      dim := dim + UFix(1)
    }
  }

  when (state === receiveTrailer && io.inStream.valid) {
    assert(io.inStream.bits.trailer)
    when (groupId === UFix(numOfStreams) - UFix(1)) {
      state := receiveGroupTrailer
    } .otherwise {
      state := receiveHeader
      groupId := groupId + UFix(1)
    }
  }
  
  when (state === receiveGroupTrailer && io.inStream.valid) {
    state := sendHeader
    groupId := UFix(0)
    id := UFix(0)
    dim := UFix(0)
  }

  when (state === sendHeader && io.outStream.ready) {
    state := divideReq
  }

  when (state === divideReq && divideFU.io.inputReady) {
    state := divide
  }

  when (state === divide && divideFU.io.outputValid) {
    data := divideFU.io.out
    state := send
  }
  
  divideFU.io.in0 := savedData.toFix() 
  divideFU.io.in1 := centers.toFix()
  divideFU.io.inputValid := state === divideReq
 
  when (state === send && io.outStream.ready) {
    when (dim != lastDimId) {
      dim := dim + UFix(1)  
    } .otherwise {
      id := id + UFix(1)
      dim := UFix(0)
    }

    when (id === lastCenterId && dim === lastDimId) {
      state := sendTrailer
    } .otherwise {
      state := divideReq
    }
  }

  when (state === sendTrailer && io.outStream.ready) {
    state := waiting
  }

  io.outStream.valid := state === sendHeader || 
                        state === send ||
                        state === sendTrailer

  io.outStream.bits.header := state === sendHeader

  io.outStream.bits.trailer := state === sendTrailer

  io.outStream.bits.data := Mux(state === sendHeader, 
                              mkHeader(sTCMDCentersResult, UFix(KmeansConfig.centers)),
                              data)
}

class AvergeOut extends Component with StreamUtil {
  val io = new Bundle {
     val inStream0 = (new FIFOIO()) {new StreamData}.flip()
     val inStream1 = (new FIFOIO()) {new StreamData}.flip()
     val outStream = (new FIFOIO()) {new StreamData}
     val error = Bool()
  }

  val noCompute::addReq::add::divideReq::divide::send::Nil = Enum(6){UFix()}
  val state = Reg(resetVal=UFix(0,3))
  
  val addFU = new AddFU
  val divideFU = new DivideFU

  val bothValid = io.inStream0.valid &&  io.inStream1.valid
  val addResult = Reg(resetVal=Bits(0, KmeansConfig.pointWidth))
  val computeResult = Reg(resetVal=Bits(0, KmeansConfig.pointWidth))

  when (state === noCompute && bothValid) {
    state := addReq
  }
  
  when (state === addReq && addFU.io.inputReady) {
    state := add
  }
  
  when (state === add && addFU.io.outputValid) {
    addResult := addFU.io.out
    state := divideReq
  }

  addFU.io.in0 := io.inStream0.bits.data
  addFU.io.in1 := io.inStream1.bits.data
  addFU.io.inputValid := state === addReq

  when (state === divideReq && divideFU.io.inputReady) {
    state := divide
  }

  when (state === divide && divideFU.io.outputValid) {
    state := send 
    computeResult := divideFU.io.out
  }

  divideFU.io.in0 := addResult
  divideFU.io.in1 := Fix(2)
  divideFU.io.inputValid := state === divideReq

  when (state === send && io.outStream.ready) {
    state := noCompute    
  }
 
  io.outStream.valid := state === send

  io.inStream0.ready := state === noCompute
  io.inStream1.ready := state === noCompute
 
  io.outStream.bits.header := io.inStream0.valid && io.inStream1.valid && 
                           io.inStream0.bits.header && io.inStream1.bits.header 
  
  io.outStream.bits.trailer := io.inStream0.valid && io.inStream1.valid && 
                            io.inStream0.bits.trailer && io.inStream1.bits.header 

  io.outStream.bits.data := Mux(io.outStream.bits.header, 
                              mkHeader(commandPart(io.inStream0.bits.data),
                                    countPart(io.inStream0.bits.data) + 
                                      countPart(io.inStream1.bits.data)),
                              computeResult) 
}
