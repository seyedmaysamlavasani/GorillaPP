package Tutorial

import Chisel._
import Node._
import Literal._
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer


class divider (extCompName:String) extends gComponentLeaf  (() => new FU_in_t)(() => new FU_out_t) (ArrayBuffer())  (extCompName =  extCompName + "__type_engine__pipelined__") with include {
  val tagWidth = 5*2
  val numOfThreads = 4 
  val NONE_SELECTED = UFix(numOfThreads,log2Up(numOfThreads)+1)
  val outputReg_ready = io.out.ready 


  def  myOff = io.elements.find(_._1 == "off").getOrElse(elseV)._2
  /*****************Definitions******************************************************/
   val outputReg_bits = Reg(new FU_out_t)
   val outputReg_valid = Reg(resetVal=Bool(false))
   val outputReg_tag = Reg(resetVal=UFix(width=tagWidth))
   val GS_step2PRegSS_valid = Reg(resetVal=Bool(false))
   val GS_step2PRegSS_tag = Reg(resetVal=UFix(width=tagWidth))
   val GS_step2PRegSS_Input = Reg(resetVal=new FU_in_t)
   val GS_step2PRegSS_Output = Reg(resetVal=new FU_out_t)
   val GS_step2PRegSS_ready = Bool()
   val GS_step3PRegSS_valid = Reg(resetVal=Bool(false))
   val GS_step3PRegSS_tag = Reg(resetVal=UFix(width=tagWidth))
   val GS_step3PRegSS_Input = Reg(resetVal=new FU_in_t)
   val GS_step3PRegSS_Output = Reg(resetVal=new FU_out_t)
   val GS_step3PRegSS_ready = Bool()
   val GS_step4PRegSS_valid = Reg(resetVal=Bool(false))
   val GS_step4PRegSS_tag = Reg(resetVal=UFix(width=tagWidth))
   val GS_step4PRegSS_Input = Reg(resetVal=new FU_in_t)
   val GS_step4PRegSS_Output = Reg(resetVal=new FU_out_t)
   val GS_step4PRegSS_ready = Bool()
   val GS_step5PRegSS_valid = Reg(resetVal=Bool(false))
   val GS_step5PRegSS_tag = Reg(resetVal=UFix(width=tagWidth))
   val GS_step5PRegSS_Input = Reg(resetVal=new FU_in_t)
   val GS_step5PRegSS_Output = Reg(resetVal=new FU_out_t)
   val GS_step5PRegSS_ready = Bool()
   val GS_step6PRegSS_valid = Reg(resetVal=Bool(false))
   val GS_step6PRegSS_tag = Reg(resetVal=UFix(width=tagWidth))
   val GS_step6PRegSS_Input = Reg(resetVal=new FU_in_t)
   val GS_step6PRegSS_Output = Reg(resetVal=new FU_out_t)
   val GS_step6PRegSS_ready = Bool()
   val GS_step7PRegSS_valid = Reg(resetVal=Bool(false))
   val GS_step7PRegSS_tag = Reg(resetVal=UFix(width=tagWidth))
   val GS_step7PRegSS_Input = Reg(resetVal=new FU_in_t)
   val GS_step7PRegSS_Output = Reg(resetVal=new FU_out_t)
   val GS_step7PRegSS_ready = Bool()
   val GS_step8PRegSS_valid = Reg(resetVal=Bool(false))
   val GS_step8PRegSS_tag = Reg(resetVal=UFix(width=tagWidth))
   val GS_step8PRegSS_Input = Reg(resetVal=new FU_in_t)
   val GS_step8PRegSS_Output = Reg(resetVal=new FU_out_t)
   val GS_step8PRegSS_ready = Bool()
   val GS_step9PRegSS_valid = Reg(resetVal=Bool(false))
   val GS_step9PRegSS_tag = Reg(resetVal=UFix(width=tagWidth))
   val GS_step9PRegSS_Input = Reg(resetVal=new FU_in_t)
   val GS_step9PRegSS_Output = Reg(resetVal=new FU_out_t)
   val GS_step9PRegSS_ready = Bool()
   val GS_step10PRegSS_valid = Reg(resetVal=Bool(false))
   val GS_step10PRegSS_tag = Reg(resetVal=UFix(width=tagWidth))
   val GS_step10PRegSS_Input = Reg(resetVal=new FU_in_t)
   val GS_step10PRegSS_Output = Reg(resetVal=new FU_out_t)
   val GS_step10PRegSS_ready = Bool()
   val GS_step11PRegSS_valid = Reg(resetVal=Bool(false))
   val GS_step11PRegSS_tag = Reg(resetVal=UFix(width=tagWidth))
   val GS_step11PRegSS_Input = Reg(resetVal=new FU_in_t)
   val GS_step11PRegSS_Output = Reg(resetVal=new FU_out_t)
   val GS_step11PRegSS_ready = Bool()
   val GS_step12PRegSS_valid = Reg(resetVal=Bool(false))
   val GS_step12PRegSS_tag = Reg(resetVal=UFix(width=tagWidth))
   val GS_step12PRegSS_Input = Reg(resetVal=new FU_in_t)
   val GS_step12PRegSS_Output = Reg(resetVal=new FU_out_t)
   val GS_step12PRegSS_ready = Bool()
val GS_step1PRegSS_valid = Bool(true)





  /******************Winner threads for each instruction with offload*****************/


  /*****************Combination logic************************************************/
   GS_step2PRegSS_ready := GS_step3PRegSS_ready
   GS_step3PRegSS_ready := GS_step4PRegSS_ready
   GS_step4PRegSS_ready := GS_step5PRegSS_ready
   GS_step5PRegSS_ready := GS_step6PRegSS_ready
   GS_step6PRegSS_ready := GS_step7PRegSS_ready
   GS_step7PRegSS_ready := GS_step8PRegSS_ready
   GS_step8PRegSS_ready := GS_step9PRegSS_ready
   GS_step9PRegSS_ready := GS_step10PRegSS_ready
   GS_step10PRegSS_ready := GS_step11PRegSS_ready
   GS_step11PRegSS_ready := GS_step12PRegSS_ready
   GS_step12PRegSS_ready := outputReg_ready
   io.out.tag := outputReg_tag
   io.out.bits := outputReg_bits
   io.out.valid := outputReg_valid
   io.in.ready := GS_step2PRegSS_ready

  /*****************Context edit logic************************************************/

   
    when (GS_step2PRegSS_ready || !GS_step2PRegSS_valid) {
   GS_step2PRegSS_valid := io.in.valid
   GS_step2PRegSS_tag := io.in.tag
   GS_step2PRegSS_Input := io.in.bits
   GS_step2PRegSS_Output.out:=io.in.bits.in1+io.in.bits.in2 
   } 
   
    when (GS_step3PRegSS_ready || !GS_step3PRegSS_valid) {
   GS_step3PRegSS_valid := GS_step2PRegSS_valid
   GS_step3PRegSS_tag := GS_step2PRegSS_tag
   GS_step3PRegSS_Input := GS_step2PRegSS_Input
   GS_step3PRegSS_Output := GS_step2PRegSS_Output
   } 
   
    when (GS_step4PRegSS_ready || !GS_step4PRegSS_valid) {
   GS_step4PRegSS_valid := GS_step3PRegSS_valid
   GS_step4PRegSS_tag := GS_step3PRegSS_tag
   GS_step4PRegSS_Input := GS_step3PRegSS_Input
   GS_step4PRegSS_Output := GS_step3PRegSS_Output
   } 
   
    when (GS_step5PRegSS_ready || !GS_step5PRegSS_valid) {
   GS_step5PRegSS_valid := GS_step4PRegSS_valid
   GS_step5PRegSS_tag := GS_step4PRegSS_tag
   GS_step5PRegSS_Input := GS_step4PRegSS_Input
   GS_step5PRegSS_Output := GS_step4PRegSS_Output
   } 
   
    when (GS_step6PRegSS_ready || !GS_step6PRegSS_valid) {
   GS_step6PRegSS_valid := GS_step5PRegSS_valid
   GS_step6PRegSS_tag := GS_step5PRegSS_tag
   GS_step6PRegSS_Input := GS_step5PRegSS_Input
   GS_step6PRegSS_Output := GS_step5PRegSS_Output
   } 
   
    when (GS_step7PRegSS_ready || !GS_step7PRegSS_valid) {
   GS_step7PRegSS_valid := GS_step6PRegSS_valid
   GS_step7PRegSS_tag := GS_step6PRegSS_tag
   GS_step7PRegSS_Input := GS_step6PRegSS_Input
   GS_step7PRegSS_Output := GS_step6PRegSS_Output
   } 
   
    when (GS_step8PRegSS_ready || !GS_step8PRegSS_valid) {
   GS_step8PRegSS_valid := GS_step7PRegSS_valid
   GS_step8PRegSS_tag := GS_step7PRegSS_tag
   GS_step8PRegSS_Input := GS_step7PRegSS_Input
   GS_step8PRegSS_Output := GS_step7PRegSS_Output
   } 
   
    when (GS_step9PRegSS_ready || !GS_step9PRegSS_valid) {
   GS_step9PRegSS_valid := GS_step8PRegSS_valid
   GS_step9PRegSS_tag := GS_step8PRegSS_tag
   GS_step9PRegSS_Input := GS_step8PRegSS_Input
   GS_step9PRegSS_Output := GS_step8PRegSS_Output
   } 
   
    when (GS_step10PRegSS_ready || !GS_step10PRegSS_valid) {
   GS_step10PRegSS_valid := GS_step9PRegSS_valid
   GS_step10PRegSS_tag := GS_step9PRegSS_tag
   GS_step10PRegSS_Input := GS_step9PRegSS_Input
   GS_step10PRegSS_Output := GS_step9PRegSS_Output
   } 
   
    when (GS_step11PRegSS_ready || !GS_step11PRegSS_valid) {
   GS_step11PRegSS_valid := GS_step10PRegSS_valid
   GS_step11PRegSS_tag := GS_step10PRegSS_tag
   GS_step11PRegSS_Input := GS_step10PRegSS_Input
   GS_step11PRegSS_Output := GS_step10PRegSS_Output
   } 
   
    when (GS_step12PRegSS_ready || !GS_step12PRegSS_valid) {
   GS_step12PRegSS_valid := GS_step11PRegSS_valid
   GS_step12PRegSS_tag := GS_step11PRegSS_tag
   GS_step12PRegSS_Input := GS_step11PRegSS_Input
   GS_step12PRegSS_Output := GS_step11PRegSS_Output
   } 
   
    when (outputReg_ready || !outputReg_valid) {
   outputReg_valid := GS_step12PRegSS_valid
   outputReg_tag := GS_step12PRegSS_tag
   outputReg_bits := GS_step12PRegSS_Output
   } 

  /*****************Request builder logic************************************************/




  /***************Offload ports helper logic*******************************/

  /******************Ready stage handler************************/



  /******************Valid stage handler************************/


}
