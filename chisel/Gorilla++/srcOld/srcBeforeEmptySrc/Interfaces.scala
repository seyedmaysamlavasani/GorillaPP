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

class MemOpReq (addrSize : Int , dataSize: Int) extends Bundle {
  val enable = Bool(OUTPUT)
  val address = Bits(OUTPUT, addrSize*8)
  val rw = Bits(OUTPUT, 1)
  val wData = Bits(OUTPUT, dataSize*8) 
}

class MemOpReqDualAddress (addrSize : Int , dataSize: Int) extends Bundle {
  val enable = Bool(OUTPUT)
  val rAddress = Bits(OUTPUT, addrSize*8)
  val wAddress = Bits(OUTPUT, addrSize*8)
  val rw = Bits(OUTPUT, 1)
  val wData = Bits(OUTPUT, dataSize*8) 
}

class MemOpRep (dataSize: Int) extends Bundle {
  val rData = Bits(INPUT, dataSize*8)
}

class MemOpRepAsync (dataSize: Int) extends MemOpRep (dataSize) {
  val ready = Bool(INPUT)
} 

class SyncMemIO(addrSize: Int, dataSize: Int) extends Bundle {
  val req = new MemOpReq(addrSize, dataSize)
  val reply = new MemOpRep(dataSize)
}

class SyncMemDualAddressIO(addrSize: Int, dataSize: Int) extends Bundle {
  val req = new MemOpReqDualAddress(addrSize, dataSize)
  val reply = new MemOpRep(dataSize)
}

class AsyncMemIO (addrSize: Int, dataSize: Int) extends Bundle {
  val req = new MemOpReq(addrSize, dataSize)
  val reply = new MemOpRepAsync(dataSize)
}

class MemIO extends AsyncMemIO(addrSize = LINQAccConfig.addrSize, 
                               dataSize = LINQAccConfig.wordSize) {}

class StreamData extends Bundle {
  val header = Bool() 
  val trailer = Bool() 
  val data = Bits(width = KmeansConfig.pointWidth)  
}

class FUIO extends Bundle {
  val inputValid = Bool(INPUT)
  val inputReady = Bool(OUTPUT)
  val outputValid = Bool(OUTPUT)
  val in0 = Fix(INPUT, KmeansConfig.pointWidth)
  val in1 = Fix(INPUT, KmeansConfig.pointWidth) 
  val out = Fix(OUTPUT, KmeansConfig.pointWidth)
}

class InstrumentIO extends Bundle {
  val address = UFix(INPUT, 16)
  val data = UFix(OUTPUT, 32)
}
