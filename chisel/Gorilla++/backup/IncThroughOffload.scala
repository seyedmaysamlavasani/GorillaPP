package Tutorial

import Chisel._
import Node._
import Literal._
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer


class IncThroughOffload extends gComponentLeaf  (() => UFix(width = 32))(() => UFix(width = 32)) (ArrayBuffer(("IncFactor", () => UFix(width = 32) , () => UFix(width = 32))))  {
//   def  myOff = io.elements.find(_._1 == "off").getOrElse(elseV)._2
//   def mymyOff = myOff.asInstanceOf[Bundle].elements.find(_._1 == "incrementFactor").getOrElse(elseV)._2
//   val incrementFactorPort = new gOffBundleND(() => UFix(width = 32), () => UFix(width = 32))
//   incrementFactorPort <>mymyOff
//io.out.bits := incrementFactorPort.rep.bits //tincF //inputReg + UFix(2, width = 32)//inputReg+incF
//io.out.valid := Bool(true) 
//io.in.ready := Bool(true) 



  def  myOff = io.elements.find(_._1 == "off").getOrElse(elseV)._2//.asInstanceOf[gOffBundle[UFix, UFix]]
  def mymyOff = myOff.asInstanceOf[Bundle].elements.find(_._1 == "IncFactor").getOrElse(elseV)._2 
 
  val offWire = new gOffBundleND(() => UFix(width = 32), () => UFix(width = 32))
  offWire <> mymyOff
  io.out.bits := offWire.rep.bits //tincF //inputReg + UFix(2, width = 32)//inputReg+incF
  io.out.valid := Bool(true) 
  io.in.ready := Bool(true) 
 
}
