package Tutorial

import Chisel._
import Node._
import Literal._
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer

class gPipe[T <: Data](latency: Int = 1) extends gComponentLeaf (() => UFix(width = 32))(() => UFix(width = 32)) (ArrayBuffer()){
  val tags = Vec(latency) {Reg(UFix(width=5))}
  val valids = Vec(latency) {Reg(Bool(), resetVal=Bool(false))}
  when (io.out.ready) { 
    tags(0) := io.in.tag
    valids(0) := io.in.valid
  }
  for (i <- latency-1 to 1 by -1) {
    when (io.out.ready) {
      valids(i) := valids(i-1) 
      tags(i) := tags(i-1) 
    }
  } 
  io.out.tag := tags(latency-1) 
  io.out.valid := valids(latency-1) 
  io.in.ready := io.out.ready
}

