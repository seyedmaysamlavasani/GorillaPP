package Tutorial

import Chisel._
import Node._
import Literal._
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer

class dualInput[T <: Data] (data: () => T) extends Bundle {
  val in1 = data().asInput
  val in2 = data().asInput
}

class fuBB [T <: Data] (ioType: () => T) extends BlackBox {
  val io = new Bundle {
    val a = ioType().asInput 
    val b = ioType().asInput 
    val result = ioType().asOutput
    val ce = Bool(INPUT)
    val rdy = Bool(OUTPUT)
  }
}

class FUSynWrapper [ioT <: Data] (ioType: () => ioT) (fuGen: () => fuBB[ioT]) (stages: Int) (extCompName: String) extends gComponentLeaf (() => new dualInput(ioType)) (ioType) (ArrayBuffer()) (extCompName=extCompName) with include {
  val tagPipe = new gPipe(stages)
  val bb = fuGen()
  tagPipe.io.in.valid := io.in.valid
  tagPipe.io.in.tag := io.in.tag
  tagPipe.io.out.ready := io.out.ready
  io.out.tag := tagPipe.io.out.tag
  io.in.ready := tagPipe.io.in.ready   
  io.out.valid := bb.io.rdy && tagPipe.io.out.valid //TODO:fifo is not required
  bb.io.a <> io.in.bits.in1  
  bb.io.b <> io.in.bits.in2 
  //This causes the output unconnected
  //bb.io.result <> io.out.bits
  io.out.bits := bb.io.result
  bb.io.ce := Bool(true) //TODO:This should be attached to (valid || outstanding > 0)
}
