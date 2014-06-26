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


class TwoNICs extends Component {
  val io = new Bundle {
    val send = (new FIFOIO()) {new StreamData}.flip
    val rcv = (new FIFOIO()) {new StreamData}
    val debug = (new FIFOIO()) {new FlitIO}
    val debugReady = Bool(OUTPUT) 
    val transfer = Bool(OUTPUT) 
  }

  val sendNIC = new NIC 
  val rcvNIC = new NIC
  sendNIC.io.coreSend <> io.send
  rcvNIC.io.coreRcv <> io.rcv
  sendNIC.io.networkSend <> rcvNIC.io.networkRcv 
//  io.debug.valid := rcvNIC.io.networkRcv.valid
//  io.debug.bits.head := rcvNIC.io.networkRcv.bits.head
//  io.debug.bits.tail := rcvNIC.io.networkRcv.bits.tail
//  io.debug.bits.data := rcvNIC.io.networkRcv.bits.data
  io.debugReady := rcvNIC.io.networkRcv.ready
  io.debug.valid := sendNIC.io.networkSend.valid
  io.debug.bits.head := sendNIC.io.networkSend.bits.head
  io.debug.bits.tail := sendNIC.io.networkSend.bits.tail
  io.debug.bits.data := sendNIC.io.networkSend.bits.data
  io.transfer := io.debug.valid && io.debugReady
}

//class TwoNICTests(c:TwoNICs) extends Tester(c, Array(c.io, c.sendNIC.ser.io, c.sendNIC.packetizer.io, c.rcvNIC.dePacketizer.io.output)) {
class TwoNICTests(c:TwoNICs) extends Tester(c, Array(c.io, c.sendNIC.ser.io, c.rcvNIC.io.debugFlitIO, c.rcvNIC.io.debugFlitReady, c.rcvNIC.des.currChunk, c.rcvNIC.des.currOffset, c.rcvNIC.des.storedChunks)) {
  defTests {
    var allGood = true
    val svars = new HashMap[Node, Node]()
    val ovars = new HashMap[Node, Node]()
    var time = 0
    println(LINQAccConfig.networkSerializationFactor)
    svars(c.io.rcv.ready) = Bool(true)
    step(svars, ovars, true)
    var sent = 0
    var acked = true
    val size = 3

    for (i <- 0 until 100) {
      if (ovars(c.io.rcv.valid).litValue().intValue() == 1) {
        if (ovars(c.io.rcv.bits.header).litValue().intValue() == 1) 
          println("received header " + ovars(c.io.rcv.bits.data).litValue().intValue())
        println("received " + ovars(c.io.rcv.bits.data).litValue().intValue())
        if (ovars(c.io.rcv.bits.trailer).litValue().intValue() == 1) 
          println("received trailer " + ovars(c.io.rcv.bits.data).litValue().intValue())
      }
      if (sent == 0) {
        svars(c.io.send.valid) = Bool(true) 
        svars(c.io.send.bits.header) = Bool(true) 
        svars(c.io.send.bits.trailer) = Bool(false) 
        svars(c.io.send.bits.data) = 1024+sent
      } 
      if (sent != 0 && sent < size) {
        svars(c.io.send.valid) = Bool(true) 
        svars(c.io.send.bits.header) = Bool(false) 
        svars(c.io.send.bits.trailer) = Bool(false) 
        svars(c.io.send.bits.data) = 1024+sent
      }
      if (sent == size) {
        svars(c.io.send.valid) = Bool(true) 
        svars(c.io.send.bits.header) = Bool(false) 
        svars(c.io.send.bits.trailer) = Bool(true) 
        svars(c.io.send.bits.data) = 1024+sent
      }
      if (sent > size) {
        svars(c.io.send.valid) = Bool(false) 
        svars(c.io.send.bits.header) = Bool(false) 
        svars(c.io.send.bits.trailer) = Bool(true) 
        svars(c.io.send.bits.data) = 1024+sent
      }
      step(svars, ovars, true)
      if (ovars(c.io.send.ready).litValue().intValue() == 1) {
        acked = true 
        sent = sent + 1
      } else {
        acked = false
      }
    }
    allGood
  }
}
