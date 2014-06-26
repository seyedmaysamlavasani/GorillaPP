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

//For now degree should be a power of two

class KmeansSplitter(degree: Int) extends Component with StreamUtil {
  val io = new Bundle {
     val inStream = (new FIFOIO()) {new StreamData()}.flip()
     val outStreams = Vec(degree) {(new FIFOIO()) {new StreamData()}}
     //val error = Bool()
     //val transfer = Bool()
  }
  val waiting::split::broadcast::Nil=Enum(3){UFix()}
  val state = Reg(resetVal=waiting)
  val errorReg = Reg(resetVal=Bool(false))
  val bcastDone = Vec(degree) {Reg(resetVal=Bool(false))}

  def assert(cond: Bool) = {
      errorReg := errorReg || !cond
  }

  val allReady = (Range(0, degree).map(i => io.outStreams(i).ready)).reduceLeft(_&&_)
  val dim = Reg(resetVal = UFix(0, log2Up(KmeansConfig.dims)))
  val rrCounter = Reg(resetVal = UFix(0, width = log2Up(degree)))
  def splitCount(count: UFix, id: UFix) : UFix = {
    if (degree == 1) 
      count
    else 
      (count >> UFix(log2Up(degree)))
  }

  //io.transfer := io.outStreams(0).valid && io.outStreams(0).ready

  when (state ===  waiting && io.inStream.valid) {
    assert(io.inStream.bits.header)      
    state := broadcast 
  }  

  when (state === broadcast && io.inStream.valid && 
    io.inStream.bits.trailer && io.inStream.ready) {
    state := split
    rrCounter := UFix(0)
  }

  when (state === split && io.inStream.valid && io.inStream.bits.trailer && 
    io.inStream.ready) {
    state := waiting
  }

  val currentReady = Range(0, degree).map(i => 
                       io.outStreams(i).ready && rrCounter === UFix(i)).reduceLeft(_||_)
 
  val transfer = Range(0, degree).map(i => 
                       io.outStreams(i).ready && io.outStreams(i).valid)
 
  val allBcastDone = Range(0, degree).map(i => bcastDone(i)).reduceLeft(_&&_)
                       
  val allBcastDoneAndReady = allBcastDone && io.inStream.ready

  for (i <- 0 until degree) {
    bcastDone(i) := !allBcastDoneAndReady && 
                    (state === broadcast || io.inStream.bits.header ||
                    io.inStream.bits.trailer) && (transfer(i) || bcastDone(i))
  } 

  when (state === split && io.inStream.valid && currentReady) {
    when (rrCounter === (UFix(degree) - UFix(1)) && 
      dim === UFix(KmeansConfig.dims)-UFix(1)) {
      rrCounter := UFix(0)
      dim := UFix(0)
    } .otherwise {
      when (dim === UFix(KmeansConfig.dims)-UFix(1)) {
        dim := UFix(0)
        rrCounter := rrCounter + UFix(1)
      } .otherwise {
        dim := dim + UFix(1)
      }
    } 
  }

  for (i <- 0 until degree) {
    io.outStreams(i).valid := MuxCase(Bool(false), Array(
                            (state === broadcast && !bcastDone(i), io.inStream.valid),
//                            (state === broadcast, io.inStream.valid),
                            (state === split, io.inStream.valid && (rrCounter === UFix(i) ||
                              io.inStream.bits.header || io.inStream.bits.trailer))))
//Maysam commented for debug
//    io.outStreams(i).bits.data := io.inStream.bits.data

    io.outStreams(i).bits.data := Mux(state === split && io.inStream.bits.header,
                                mkHeader(commandPart(io.inStream.bits.data), 
                                   splitCount(countPart(io.inStream.bits.data), UFix(i))),
                                io.inStream.bits.data)

    io.outStreams(i).bits.header := io.inStream.bits.header
    io.outStreams(i).bits.trailer := io.inStream.bits.trailer
  } 

//  io.inStream.ready :=  MuxCase(Bool(false), Array((state === broadcast  || 
//                         (state === split && (io.inStream.bits.header || 
//                           io.inStream.bits.trailer)) , allReady || allBcastDone),
//                         (state === split && !io.inStream.bits.header && 
//                           !io.inStream.bits.trailer , currentReady))) 

  io.inStream.ready :=  (((state === broadcast)  || 
                         ((state === split) && (io.inStream.bits.header || 
                           io.inStream.bits.trailer))) && (allReady || allBcastDone)) ||
                        (state === split && !io.inStream.bits.header && 
                          !io.inStream.bits.trailer && currentReady) 



}
