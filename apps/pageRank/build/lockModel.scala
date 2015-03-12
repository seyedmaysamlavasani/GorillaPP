package Tutorial

import Chisel._
import Node._
import Literal._
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer


class lockModel (extCompName:String) extends gComponentLeaf  (() => new lock_req_t)(() => new lock_rep_t) (ArrayBuffer())  (extCompName =  extCompName + "__type_engine__pipelined__") with include {
  val tagWidth = 5*2
  val numOfThreads = 4 
  val NONE_SELECTED = UFix(numOfThreads,log2Up(numOfThreads)+1)
  val outputReg_ready = io.out.ready 


  def  myOff = io.elements.find(_._1 == "off").getOrElse(elseV)._2
  /*****************Definitions******************************************************/
   val outputReg_bits = Reg(new lock_rep_t)
   val outputReg_valid = Reg(resetVal=Bool(false))
   val outputReg_tag = Reg(resetVal=UFix(width=tagWidth))
val GS_step1PRegSS_valid = Bool(true)





  /******************Winner threads for each instruction with offload*****************/


  /*****************Combination logic************************************************/
   io.out.tag := outputReg_tag
   io.out.bits := outputReg_bits
   io.out.valid := outputReg_valid
   io.in.ready := outputReg_ready

  /*****************Context edit logic************************************************/

   
    when (outputReg_ready || !outputReg_valid) {
   outputReg_valid := io.in.valid
   outputReg_tag := io.in.tag
   } 

  /*****************Request builder logic************************************************/




  /***************Offload ports helper logic*******************************/

  /******************Ready stage handler************************/



  /******************Valid stage handler************************/


}
