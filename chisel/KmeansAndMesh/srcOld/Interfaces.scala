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
  val address = Bits(addrSize*8, OUTPUT)
  val rw = Bits(1, OUTPUT)
  val wData = Bits(dataSize*8, OUTPUT) 
}

class MemOpReqDualAddress (addrSize : Int , dataSize: Int) extends Bundle {
  val enable = Bool(OUTPUT)
  val rAddress = Bits(addrSize*8, OUTPUT)
  val wAddress = Bits(addrSize*8, OUTPUT)
  val rw = Bits(1, OUTPUT)
  val wData = Bits(dataSize*8, OUTPUT) 
}

class MemOpRep (dataSize: Int) extends Bundle {
  val rData = Bits(dataSize*8, INPUT)
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
  val in0 = Fix(KmeansConfig.pointWidth, INPUT)
  val in1 = Fix(KmeansConfig.pointWidth, INPUT) 
  val out = Fix(KmeansConfig.pointWidth, OUTPUT)
}

class InstrumentIO extends Bundle {
  val address = UFix(16, INPUT)
  val data = UFix(32, OUTPUT)
}
