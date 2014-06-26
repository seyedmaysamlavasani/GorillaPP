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


 
class Serializer(factor:Int) extends Component {
  val io = new Bundle {
    val input = (new FIFOIO()) {new StreamData()}.flip
    val output = (new FIFOIO()) {new FlitIO()}
  }
  val currChunk = Reg(resetVal=UFix(0,6))
  val currOffset = Reg(resetVal=UFix(0,10))
  val outWidth = io.output.bits.data.getWidth
  val shiftedData = Reg(resetVal=Bits(0,outWidth))

  when (io.input.valid && io.output.ready) {
    when (currChunk === UFix(0) && currChunk != UFix(factor-1)) {
      currChunk := currChunk + UFix(1)
      currOffset := currOffset + UFix(outWidth)-UFix(2)
      shiftedData := io.input.bits.data
    } .elsewhen (currChunk != UFix(factor-1)) {
      currChunk := currChunk + UFix(1)
      currOffset := currOffset + UFix(outWidth)-UFix(2)
      shiftedData := shiftedData >> UFix(outWidth-2) 
    } .otherwise {
      currChunk := UFix(0)
      currOffset := UFix(0)
    }
  }

  io.output.valid := io.input.valid
  io.input.ready := io.output.ready && currChunk === UFix(factor - 1)
  io.output.bits.data := io.input.bits.header ## io.input.bits.trailer ## Mux(currChunk === UFix(0), io.input.bits.data(outWidth-3,0), (shiftedData >> UFix(outWidth-2))(outWidth-3,0)) 
  //io.output.bits.data := io.input.bits.header ## io.input.bits.trailer ## io.input.bits.data(currOffset+UFix(outWidth-3),currOffset)
  //val upBit = currOffset+UFix(outWidth-3)
  //io.output.bits.data := io.input.bits.header ## io.input.bits.trailer ## io.input.bits.data(15,0)

//  io.output.bits.data := io.input.bits.header.toBits() ##
//                         io.input.bits.trailer.toBits() ##
//                         io.input.bits.data(currOffset+UFix(outWidth-3),currOffset)
//
}
 
class DeSerializer(factor:Int) extends Component {
  val io = new Bundle {
    val input = (new FIFOIO()) {new FlitIO()}.flip
    val output = (new FIFOIO()) {new StreamData()}
  }
  val storedChunks = Reg(resetVal=Bits(0, io.output.bits.getWidth))
  val currChunk = Reg(resetVal=UFix(0,6))
  val currOffset = Reg(resetVal=UFix(0,10))
  val inputWidth = io.input.bits.data.getWidth
  val headReg = Reg(resetVal=Bool(false))
  val tailReg = Reg(resetVal=Bool(false))

  when (io.input.valid) {
    when (currChunk === UFix(0) && currChunk != UFix(factor-1)) {
      currChunk := currChunk + UFix(1)
      currOffset := UFix(inputWidth) - UFix(2)   
      storedChunks := io.input.bits.data(inputWidth-3,0)
      headReg := io.input.bits.data(inputWidth-1) === Bits(1) 
      tailReg := io.input.bits.data(inputWidth-2) === Bits(1) 
    } .elsewhen (currChunk != UFix(factor-1)) {
      currChunk := currChunk + UFix(1)
      currOffset := currOffset + UFix(inputWidth) - UFix(2)
      storedChunks :=  
        (io.input.bits.data(inputWidth-2,0) << currOffset) +
          storedChunks(currOffset-UFix(1),UFix(0))
    }
    when (currChunk === UFix(factor-1) && io.output.ready) {
      headReg := Bool(false)
      tailReg := Bool(false)
      currChunk := UFix(0)
      currOffset := UFix(0)
    }
  }

  io.output.valid := io.input.valid && currChunk === UFix(factor-1)
  io.input.ready := currChunk != UFix(factor-1) || io.output.ready
  if (factor > 1) {
    io.output.bits.header := headReg 
    io.output.bits.trailer := tailReg
    io.output.bits.data := io.input.bits.data ## storedChunks(currOffset-UFix(1),UFix(0))
  } else {
    io.output.bits.header := io.input.bits.data(inputWidth-1) 
    io.output.bits.trailer := io.input.bits.data(inputWidth-2)
    io.output.bits.data := io.input.bits.data(inputWidth-3, 0)
  }
}
 
 
class Packetizer extends Component {
  val io = new Bundle {
    val addr = UFix(INPUT, log2Up(routerConfig.networkAray)*2)
    val input = (new FIFOIO()) {new FlitIO()}.flip
    val output = (new FIFOIO()) {new FlitIO()}
  }
  val maxPSize = UFix(LINQAccConfig.maxPSize) 
  val waiting::sendH::sendPL::sendT::Nil = Enum(4) {UFix()}
  val state = Reg(resetVal=waiting)
  val currSize = Reg(resetVal=UFix(0, 6))
 
  when (state === waiting && io.input.valid) {
    state := sendH
  }
 
  when (state === sendH && io.output.ready) {
    currSize := UFix(0)
    state := sendPL
  }
 
  when (state === sendPL && io.input.valid && io.output.ready && currSize != maxPSize) {
    state := sendPL
    currSize := currSize + UFix(1)
  }
 
  when (state === sendPL && io.input.valid && io.output.ready && currSize === maxPSize) {
    state := sendT
  }
 
  when (state === sendPL && !io.input.valid && io.output.ready && currSize != maxPSize) {
    state := sendT
  }
 
  when (state === sendT && io.output.ready) {
    when (io.input.valid) {
      state := sendH
    } .otherwise {
      state := waiting
    }
  }
 
  io.output.valid := state === sendH ||
                     (state === sendPL && io.input.valid) ||
                     state === sendT
 
  io.output.bits.head := state === sendH
  io.output.bits.tail := state === sendT
 
  //io.output.bits.data := MuxCase( io.input.bits.data,
  //                                Array((state === sendH, io.addr),
  //                                      (state === sendT, UFix(0))))
  io.output.bits.data := Mux( state === sendH, io.addr, io.input.bits.data)


  io.input.ready := io.output.ready && state === sendPL
}
 
class DePacketizer extends Component {
  val io = new Bundle {
    val addr = UFix(OUTPUT, log2Up(routerConfig.networkAray)*2)
    val input = (new FIFOIO()) {new FlitIO}.flip
    val output = (new FIFOIO()) {new FlitIO}
  }
  val addrReg = Reg(resetVal = Bits(0,log2Up(routerConfig.networkAray)*2)) 
  when (io.input.bits.head) {
    addrReg := io.input.bits.data
  }
 
  io.output.valid := io.input.valid && !(io.input.bits.head || io.input.bits.tail)
  io.output.bits.data := io.input.bits.data(io.output.bits.data.getWidth-1, 0)
  io.input.ready := io.output.ready ||
                    (io.input.valid && io.input.bits.head) ||
                    (io.input.valid && io.input.bits.tail)
}
 
class NIC extends Component {
  val io = new Bundle {
    val coreSend = (new FIFOIO()) {new StreamData}.flip
    val sendAddr = UFix(INPUT, log2Up(routerConfig.networkAray)*2)
    val coreRcv = (new FIFOIO()) {new StreamData}
    val rcvAddr = UFix(OUTPUT, log2Up(routerConfig.networkAray)*2)
    val networkSend = (new FIFOIO()) {new FlitIO}
    val networkRcv = (new FIFOIO()) {new FlitIO}.flip
    val debugFlitIO = (new FIFOIO()) {new FlitIO}
    val debugFlitReady = Bool(OUTPUT) 
  }
  val ser = new Serializer(LINQAccConfig.networkSerializationFactor)
  val des = new DeSerializer(LINQAccConfig.networkSerializationFactor)
  val packetizer = new Packetizer
  val dePacketizer = new DePacketizer
 
  io.coreSend <> ser.io.input
  ser.io.output <> packetizer.io.input
  io.networkSend <> packetizer.io.output
 
  io.networkRcv <> dePacketizer.io.input
  dePacketizer.io.output <> des.io.input
  des.io.output <> io.coreRcv
 
  packetizer.io.addr := io.sendAddr
  io.rcvAddr := dePacketizer.io.addr
  io.debugFlitReady := des.io.input.ready
  io.debugFlitIO.valid := dePacketizer.io.output.valid
  io.debugFlitIO.bits := dePacketizer.io.output.bits
}

class NICTests(c: NIC) extends Tester(c, Array(c.io)) {
  defTests {
    var allGood = true
    val svars = new HashMap[Node, Node]()
    val ovars = new HashMap[Node, Node]()
    var time = 0
    println(LINQAccConfig.networkSerializationFactor)
    step(svars, ovars, true)
    for (i <- 0 until 100) {
      step(svars, ovars, false)
    }
    allGood
  }

}  
