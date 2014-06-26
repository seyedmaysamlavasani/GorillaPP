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
import scala.collection.mutable.ArrayBuffer

class memReq (addrSize : Int , dataSize: Int) extends Bundle {
  var addr = UFix(width=addrSize)
  var rw = UFix(width=1)
  var wData = UFix(width=dataSize) 
}

class memReadOnlyReq (addrSize : Int , dataSize: Int) extends Bundle {
  var addr = UFix(width=addrSize)
}

class memReadOnlyRep (dataSize: Int) extends Bundle {
  var data = UFix(width=dataSize)
}

class memWriteOnlyReq (addrSize : Int , dataSize: Int) extends Bundle {
  var addr = UFix(width=addrSize)
  var data = UFix(width=dataSize) 
}

class memWriteOnlyRep (dataSize: Int) extends Bundle {
}

class memRep (dataSize: Int) extends Bundle {
  var rData = UFix(width=dataSize)
}

class memReq32_t extends memReq (32, 32)
class memReq192_t extends memReq (32, 192)
class memReadOnlyReq32_t extends memReadOnlyReq (32, 32)
class memWriteOnlyReq32_t extends memWriteOnlyReq (32, 32)
class memRep32_t extends memRep(32)
class memRep192_t extends memRep(192)
class memReadOnlyRep32_t extends memReadOnlyRep(32)
class memWriteOnlyRep32_t extends memWriteOnlyRep(32)

class memReqDualAddress (addrSize : Int , dataSize: Int) extends Bundle {
  var rAddress = UFix(width=addrSize)
  var wAddress = UFix(width=addrSize)
  var rw = UFix(width=1)
  var wData = UFix(width=dataSize) 
}

object spMem {
  def apply(height: Int, width: Int) = {
    val md = new gComponentMD(() => new memReq(log2Up(height), width),
     () => new memRep(width), ArrayBuffer())
    (md, () => new spMemComponent(height, width)).asInstanceOf[
     (gComponentMD[Chisel.Data,Chisel.Data], 
     () => gComponent[Chisel.Data,Chisel.Data])]
  }
}

object rwSpMem {
  def apply(height: Int, width: Int) = {
    val mdRead = new gComponentMD(
     () => new memReadOnlyReq(log2Up(height), width),
     () => new memReadOnlyRep(width), ArrayBuffer())
    val mdWrite = new gComponentMD(
     () => new memWriteOnlyReq(log2Up(height), width),
     () => new memWriteOnlyRep(width), ArrayBuffer())

    val h = (() => new rwSpMemComponent(height, width))

    (mdRead, mdWrite, h).asInstanceOf[
     (gComponentMD[Chisel.Data,Chisel.Data], 
      gComponentMD[Chisel.Data,Chisel.Data],
      () => rwSpMemComponent)]
  }
}

class spMemComponent(height: Int, width: Int) extends gComponentLeaf (() => new memReq(addrSize = log2Up(height), dataSize = width)) (() => new memRep(dataSize = width)) (ArrayBuffer()) (extCompName = "spMem") with include {
  val read::write::Nil = Enum(2){UFix()}
  val tagReg = Reg(UFix(width=TAGWIDTH*2))
  val hasReqReg =  Reg(io.in.valid, resetVal=Bool(false))

  io.in.ready := io.out.ready
  tagReg := io.in.tag
  io.out.tag := tagReg
  val ram = Mem(height, seqRead=true) {UFix(width=width)}
  //val ram = Mem(height) {UFix(width=width)}
  val rAddrReg = Reg(UFix(width = width)) 
  when (io.in.valid && (io.in.bits.rw === write)) {
    ram(io.in.bits.addr) := io.in.bits.wData
  } 
  when (io.in.valid && (io.in.bits.rw === read)) {
    rAddrReg := io.in.bits.addr 
  }
  io.out.bits.rData := ram(io.in.bits.addr)
  io.out.valid := hasReqReg 
}

class rwSpMemComponent(height: Int, width: Int) extends Component with include {
  val io = new Bundle {
    val read = new gInOutBundle(() => new memReadOnlyReq(addrSize = log2Up(height), dataSize = width), 
     () => new memReadOnlyRep(dataSize = width))
    val write = new gInOutBundle(() => new memWriteOnlyReq(addrSize = log2Up(height), dataSize = width), 
     () => new memWriteOnlyRep(dataSize = width))
  }
  val read::write::Nil = Enum(2){UFix()}
  val readTagReg = Reg(UFix(width=TAGWIDTH*2))
  val writeTagReg = Reg(UFix(width=TAGWIDTH*2))
  val hasReadReqReg =  Reg(io.read.in.valid, resetVal=Bool(false))
  val hasWriteReqReg =  Reg(io.write.in.valid, resetVal=Bool(false))
  io.read.in.ready := io.read.out.ready
  io.write.in.ready := io.write.out.ready
  readTagReg := io.read.in.tag
  writeTagReg := io.write.in.tag
  io.read.out.tag := readTagReg
  io.write.out.tag := writeTagReg

  val ram1r1w = 
   Mem(height, seqRead = true) {UFix(width = width)}
  val reg_raddr = Reg(UFix(width = width)) 
  when (io.write.in.valid) 
   { ram1r1w(io.write.in.bits.addr) := io.write.in.bits.data } 
  when (io.read.in.valid) 
   { reg_raddr := io.read.in.bits.addr} 
  io.read.out.bits.data := ram1r1w(reg_raddr) 
  io.read.out.valid := hasReadReqReg 
  io.read.in.ready := io.read.out.ready 
  io.write.out.valid := hasWriteReqReg 
  io.write.in.ready := io.write.out.ready 
}

class spMemDualAddress (height: Int, width: Int) extends gComponentLeaf (() => new memReqDualAddress(addrSize = log2Up(height), dataSize = width)) (() => new memRep(dataSize = width)) (ArrayBuffer()) (extCompName = "spMemDualAddress") {
  val readCmd::writeCmd::Nil = Enum(2) {UFix(1)}
  val ram = Mem(height) {UFix(width)}

  when (io.in.valid && (io.in.bits.rw === writeCmd)) {
    ram(io.in.bits.wAddress) := io.in.bits.wData
  } 
  io.out.bits.rData := ram(io.in.bits.rAddress) 
  io.out.valid := Bool(true)
  io.in.ready := Bool(true)
}

class spMem32 extends spMemComponent(1024, 1024)

class spMemTests(c: spMemComponent) extends Tester(c, Array(c.io)) {
  defTests {
    var allGood = true
    val svars = new HashMap[Node, Node]()
    val ovars = new HashMap[Node, Node]()

    step(svars, ovars, false)
    allGood
  }
}
