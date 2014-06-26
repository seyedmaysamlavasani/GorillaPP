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

object FUUtil {
  def sign(in:UFix):UFix = in(KmeansConfig.pointWidth-1)
  def abs(in:UFix):UFix = in(KmeansConfig.pointWidth-2,0)
  def decimal(in:UFix):UFix = in(KmeansConfig.pointWidth-2,KmeansConfig.fractionWidth)
  def fraction(in:UFix):UFix = in(KmeansConfig.pointWidth-1,0)
}
class AddFU extends Component {
  val io = new FUIO
  val delay = UFix(LINQAccConfig.fuDelay)
  val counter = Reg(resetVal=delay)
  val readyReg = Reg(resetVal=Bool(true))
  val in0Reg = Reg(resetVal=Fix(0, KmeansConfig.pointWidth))
  val in1Reg = Reg(resetVal=Fix(0, KmeansConfig.pointWidth))

  io.inputReady := readyReg
  when (readyReg && io.inputValid) {
    readyReg := Bool(false)
    in0Reg := io.in0
    in1Reg := io.in1
  }
  when (!readyReg) {
    counter := counter - UFix(1)
  }
  io.outputValid := counter === UFix(0)
  when (!readyReg && counter === UFix(0)) {
    counter := delay
    readyReg := Bool(true)
  }
  io.out := in0Reg + in1Reg 
}

class DivideFU extends Component {
  val io = new FUIO
  val delay = UFix(LINQAccConfig.fuDelay)
  val counter = Reg(resetVal=delay)
  val readyReg = Reg(resetVal=Bool(true))
  val in0Reg = Reg(resetVal=Fix(0, KmeansConfig.pointWidth))
  val in1Reg = Reg(resetVal=Fix(0, KmeansConfig.pointWidth))
 

  io.inputReady := readyReg
  when (readyReg && io.inputValid) {
    readyReg := Bool(false)
    in0Reg := io.in0
    in1Reg := io.in1
  }
  when (!readyReg) {
    counter := counter - UFix(1)
  }
  io.outputValid := counter === UFix(0)
  when (!readyReg && counter === UFix(0)) {
    counter := delay
    readyReg := Bool(true)
  }
  //TODO Fix divide is not implemented !!
  //  io.out := (io.in0 / io.in1) << UFix(KmeansConfig.fractionWidth)
  io.out := (in0Reg * in1Reg) << UFix(KmeansConfig.fractionWidth)
}

class MullFU extends Component {
  val io = new FUIO
  val delay = UFix(LINQAccConfig.fuDelay)
  val counter = Reg(resetVal=delay)
  val readyReg = Reg(resetVal=Bool(true))
  io.inputReady := readyReg
  val in0Reg = Reg(resetVal=Fix(0, KmeansConfig.pointWidth))
  val in1Reg = Reg(resetVal=Fix(0, KmeansConfig.pointWidth))
  
  when (readyReg && io.inputValid) {
    readyReg := Bool(false)
    in0Reg := io.in0
    in1Reg := io.in1
  }

  when (!readyReg) {
    counter := counter - UFix(1)
  }
  io.outputValid := counter === UFix(0)

  when (!readyReg && counter === UFix(0)) {
    counter := delay
    readyReg := Bool(true)
  }
  io.out := (in0Reg * in1Reg) >> UFix(KmeansConfig.fractionWidth)
}

class DistanceFU extends Component {
  val io = new Bundle {
    val xCurrCenter = Fix(INPUT, KmeansConfig.pointWidth)
    val yCurrCenter = Fix(INPUT, KmeansConfig.pointWidth)
    val currMinDistance = Fix(INPUT, KmeansConfig.pointWidth)
    val xCurrPoint = Fix(INPUT, KmeansConfig.pointWidth) 
    val yCurrPoint = Fix(INPUT, KmeansConfig.pointWidth) 
    val inputValid = Bool(INPUT)
    val inputReady = Bool(OUTPUT)
    val outputValid = Bool(OUTPUT)
    val closer = Bool(OUTPUT)
    val newMinDistance = Bits(OUTPUT, KmeansConfig.pointWidth)
  }
  val xCurrCenterReg = Reg(resetVal = Fix(0, KmeansConfig.pointWidth))
  val yCurrCenterReg = Reg(resetVal = Fix(0, KmeansConfig.pointWidth)) 
  val currMinDistanceReg = Reg(resetVal = Fix(0, KmeansConfig.pointWidth))
  val xCurrPointReg = Reg(resetVal = Fix(0, KmeansConfig.pointWidth)) 
  val yCurrPointReg = Reg(resetVal = Fix(0, KmeansConfig.pointWidth)) 

  val delay = UFix(LINQAccConfig.fuDelay*3)
  val counter = Reg(resetVal=delay)
  val readyReg = Reg(resetVal=Bool(true))
  io.inputReady := readyReg
  when (readyReg && io.inputValid) {
    readyReg := Bool(false)
    xCurrCenterReg := io.xCurrCenter 
    yCurrCenterReg := io.xCurrCenter 
    currMinDistanceReg := io.currMinDistance 
    xCurrPointReg := io.xCurrPoint 
    yCurrPointReg := io.yCurrPoint 
  }
  when (!readyReg) {
    counter := counter - UFix(1)
  }
  io.outputValid := counter === UFix(0)
  when (!readyReg && counter === UFix(0)) {
    counter := delay
    readyReg := Bool(true)
  }
  def sqr(x:UFix) = x*x

  val dist = sqr(xCurrPointReg-xCurrCenterReg) +
               sqr(yCurrPointReg-yCurrCenterReg)
  io.closer := dist < currMinDistanceReg
  io.newMinDistance := dist
}

class MullFUTests(c: MullFU) extends Tester(c, Array(c.io)) {
  def fix(x: Int) = Fix(x) << UFix(KmeansConfig.fractionWidth)
  def fix(x: Int, f:Int) = Fix(x) << UFix(KmeansConfig.fractionWidth) + (UFix(f) & 
                             ((UFix(1) << UFix(KmeansConfig.fractionWidth)) - UFix(1)))
  defTests{
    val svars = new HashMap[Node, Node]()
    val ovars = new HashMap[Node, Node]()

    var allGood = true

    for (i <- 0 until 5) {
      svars(c.io.inputValid) = Bool(false)
      step(svars, ovars, false)
    }
    svars(c.io.inputValid) = Bool(true) 
    svars(c.io.in0) = fix(1) 
    svars(c.io.in1) = fix(2) 
    step(svars, ovars, true)
    svars(c.io.inputValid) = Bool(true) 
    svars(c.io.in0) = fix(2) 
    svars(c.io.in1) = fix(3) 
    step(svars, ovars, true)
     for (i <- 0 until 5) {
      step(svars, ovars, true)
    }
    allGood
  }
}
