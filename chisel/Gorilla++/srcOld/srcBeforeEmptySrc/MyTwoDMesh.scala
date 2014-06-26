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
import util.Random

object meshConfig {
  val linkWidth = routerConfig.flitDataWidth 
}

class FIFOIOProbe[T <: Data]()(data: => T) extends Bundle
{
  val ready = Bool(OUTPUT)
  val valid = Bool(OUTPUT)
  val bits  = data.asOutput
}

class MyTwoDMesh(aray: Int, debug: Boolean = false) extends Component {
  val west::east::north::south::cpu::Nil = 0::1::2::3::4::Nil 
  val io = new Bundle {
    val tap =  Vec(aray*aray) {(new RFifoIO()) {new FlitIO()}}
    val debugWest = Vec(aray*aray) {(new FIFOIOProbe()) {new FlitIO()}}
    val debugEast = Vec(aray*aray) {(new FIFOIOProbe()) {new FlitIO()}}
    val debugNorth = Vec(aray*aray) {(new FIFOIOProbe()) {new FlitIO()}}
    val debugSouth = Vec(aray*aray) {(new FIFOIOProbe()) {new FlitIO()}}
  }
  def idXY2UFix(x: Int, y: Int, aray: Int) : UFix = UFix(idXYBitFormat(x, y, aray), 
                                                          width=log2Up(aray)<<1)
  def idXYBitFormat(x: Int, y: Int, aray: Int) : Int = ((y << log2Up(aray)) + x)
  def idXY(x: Int, y: Int, aray: Int) : Int =  (y * aray) + x
  def idX(id:Int, aray:Int) : Int = id & (1 << log2Up(aray))-1
  def idY(id:Int, aray:Int) : Int = id >> log2Up(aray) 

  val routers = Range(0, aray, 1).map(i => 
                  new Range(0, aray, 1).map(j => 
                    new MyRouter(5, idXY2UFix(i, j, aray))))
  for (j <- 0 until aray) {
    for (i <- 1 until aray) {
      routers(i)(j).io.ins(west) <> routers(i-1)(j).io.outs(east)
      routers(i)(j).io.outs(west) <> routers(i-1)(j).io.ins(east)
//BUG point when it was RFifoIO forgot to add .deq and .enq and still compiled
      if (debug) {
      io.debugWest(idXY(i, j, aray)).valid <> routers(i)(j).io.outs(west).valid
      io.debugWest(idXY(i, j, aray)).bits <> routers(i)(j).io.outs(west).bits
      io.debugWest(idXY(i, j, aray)).ready <> routers(i-1)(j).io.ins(east).ready
      io.debugEast(idXY(i-1, j, aray)).valid <> routers(i-1)(j).io.outs(east).valid
      io.debugEast(idXY(i-1, j, aray)).bits <> routers(i-1)(j).io.outs(east).bits
      io.debugEast(idXY(i-1, j, aray)).ready <> routers(i)(j).io.ins(west).ready
      }
    }
  }

  for (i <- 0 until aray) {
    for (j <- 1 until aray) {
      routers(i)(j).io.ins(south) <> routers(i)(j-1).io.outs(north)
      routers(i)(j).io.outs(south) <> routers(i)(j-1).io.ins(north)
      if (debug) {
      io.debugSouth(idXY(i, j, aray)).valid <> routers(i)(j).io.outs(south).valid
      io.debugSouth(idXY(i, j, aray)).bits <> routers(i)(j).io.outs(south).bits
      io.debugSouth(idXY(i, j, aray)).ready <> routers(i)(j-1).io.ins(north).ready
      io.debugNorth(idXY(i, j-1, aray)).valid <> routers(i)(j-1).io.outs(north).valid
      io.debugNorth(idXY(i, j-1, aray)).bits <> routers(i)(j-1).io.outs(north).bits
      io.debugNorth(idXY(i, j-1, aray)).ready <> routers(i)(j).io.ins(south).ready
      }
    }
  }

  for (i <- 0 until aray) {
    for (j <- 0 until aray) {
      io.tap(idXY(i, j, aray)).deq <> routers(i)(j).io.outs(cpu)
      io.tap(idXY(i, j, aray)).enq <> routers(i)(j).io.ins(cpu)
    }
  }
  //Border connections 
  for (i <- 0 until aray) {
    routers(0)(i).io.ins(west).valid := Bool(false) 
    routers(0)(i).io.outs(west).ready := Bool(false)
    routers(i)(0).io.ins(south).valid := Bool(false)  
    routers(i)(0).io.outs(south).ready := Bool(false) 
    routers(aray-1)(i).io.ins(east).valid := Bool(false)  
    routers(aray-1)(i).io.outs(east).ready := Bool(false) 
    routers(i)(aray-1).io.ins(north).valid := Bool(false) 
    routers(i)(aray-1).io.outs(north).ready := Bool(false) 
  }

}


