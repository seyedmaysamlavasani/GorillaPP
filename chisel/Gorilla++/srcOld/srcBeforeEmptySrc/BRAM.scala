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


class BRAMDualAddress(widthLog: Int, heightLog: Int) extends Bundle {
  val readCmd::writeCmd::Nil = Enum(2) {UFix(1)}
  val io = new SyncMemDualAddressIO(addrSize=heightLog, dataSize=widthLog).flip()
  val ram = Mem(1<<heightLog) {Bits(1<<widthLog)}
  when (io.req.enable && (io.req.rw === writeCmd)) {
    ram(io.req.wAddress) := io.req.wData
  } 
  io.reply.rData := ram(io.req.rAddress) 
}

class BRAM(widthLog: Int, heightLog: Int) extends Component {
  val io = new SyncMemIO(addrSize=heightLog, dataSize=widthLog).flip()
  val read::write::Nil = Enum(2){UFix()}
  val ram = Mem(1<<heightLog) {Bits(width = 1<<widthLog)}
  when (io.req.enable && (io.req.rw === write)) {
    ram(io.req.address) := io.req.wData
  } 
  io.reply.rData := ram(io.req.address) 
}

class BRAMTests(c: BRAM) extends Tester(c, Array(c.io)) {
  defTests {
    var allGood = true
    val svars = new HashMap[Node, Node]()
    val ovars = new HashMap[Node, Node]()

    step(svars, ovars, false)
    allGood
  }
}
 
