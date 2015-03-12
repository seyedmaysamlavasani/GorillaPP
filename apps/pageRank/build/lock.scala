package Tutorial

import Chisel._
import Node._
import Literal._
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer


class lock (extCompName:String) extends gComponentLeaf  (() => new rankCalc_in_t)(() => new rankCalc_out_t) (ArrayBuffer(("mul", () => new FU_in_t , () => new FU_out_t), ("div", () => new FU_in_t , () => new FU_out_t)))  (extCompName =  extCompName + "__type_engine__pipelined__") with include {
  val tagWidth = 5*2
  val numOfThreads = 4 
  val NONE_SELECTED = UFix(numOfThreads,log2Up(numOfThreads)+1)
  val outputReg_ready = io.out.ready 


  def  myOff = io.elements.find(_._1 == "off").getOrElse(elseV)._2
  /*****************Definitions******************************************************/
   val GS_step1PRegPostOff_counter = Vec(GS_step1NumOfThreads) {Reg(UFix(width = 32))}	//Gobal variable
   val GS_step2PRegPreOff_counter = {Reg(UFix(width = 32))}	//Gobal variable
   val GS_step2PRegPostOff_counter = Vec(GS_step2NumOfThreads) {Reg(UFix(width = 32))}	//Gobal variable
   val GS_step1PRegPostOff_divOut = Vec(GS_step1NumOfThreads) {Reg(new FU_out_t)}	//Gobal variable
   val GS_step2PRegPreOff_divOut = {Reg(new FU_out_t)}	//Gobal variable
   val GS_step2PRegPostOff_divOut = Vec(GS_step2NumOfThreads) {Reg(new FU_out_t)}	//Gobal variable
   val outputReg_bits = Reg(new rankCalc_out_t)
   val outputReg_valid = Reg(resetVal=Bool(false))
   val outputReg_tag = Reg(resetVal=UFix(width=tagWidth))
   def mymyOffdiv = myOff.asInstanceOf[Bundle].elements.find(_._1 == "div").getOrElse(elseV)._2
   val divPort = new gOffBundleND(() => new FU_in_t, () => new FU_out_t)
   divPort <> mymyOffdiv
   def mymyOffmul = myOff.asInstanceOf[Bundle].elements.find(_._1 == "mul").getOrElse(elseV)._2
   val mulPort = new gOffBundleND(() => new FU_in_t, () => new FU_out_t)
   mulPort <> mymyOffmul
   val GS_step1PRegPostOff_valid = Vec(GS_step1NumOfThreads) {Reg(resetVal=Bool(false))}
   val GS_step1PRegPostOff_tag = Vec(GS_step1NumOfThreads) {Reg(resetVal=UFix(width=tagWidth))}
   val GS_step1PRegPostOff_Input = Vec(GS_step1NumOfThreads) {Reg(resetVal=new rankCalc_in_t)}
   val GS_step1PRegPostOff_ready = Bool()
   val fuInputDiv = new FU_in_t
   val GS_step2PRegPreOff_valid = Reg(resetVal=Bool(false))
   val GS_step2PRegPreOff_tag = Reg(resetVal=UFix(width=tagWidth))
   val GS_step2PRegPreOff_Input = Reg(resetVal=new rankCalc_in_t)
   val GS_step2PRegPreOff_ready = Bool()
   val GS_step2PRegPostOff_valid = Vec(GS_step2NumOfThreads) {Reg(resetVal=Bool(false))}
   val GS_step2PRegPostOff_tag = Vec(GS_step2NumOfThreads) {Reg(resetVal=UFix(width=tagWidth))}
   val GS_step2PRegPostOff_ready = Bool()
   val fuInputMul = new FU_in_t
   val fuOutputMul = new FU_out_t
val GS_step1PRegSS_valid = Bool(true)


  val mulPortHadReadyRequest = Reg(resetVal=Bool(false))
  val mulReadyReceived = Reg(resetVal=Bool(false))
  val divPortHadReadyRequest = Reg(resetVal=Bool(false))
  val divReadyReceived = Reg(resetVal=Bool(false))



  /******************Winner threads for each instruction with offload*****************/


  /*****************Combination logic************************************************/
   GS_step1PRegPostOff_ready := GS_step1RThread != NONE_SELECTED 
   fuInputDiv.in1:=io.in.bits.rank 
   fuInputDiv.in2:=io.in.bits.fanoutDegree 
   GS_step2PRegPreOff_ready :=GS_step2PRegPostOff_ready && GS_step2AllOffloadsReady
   GS_step2PRegPostOff_ready := GS_step2RThread != NONE_SELECTED 
   fuInputMul.in1:=GS_step2PRegPreOff_divOut.out 
   fuInputMul.in2:=GS_step2PRegPreOff_Input.damping 
   fuOutputMul := mulPort.rep.bits
   io.out.tag := outputReg_tag
   io.out.bits := outputReg_bits
   io.out.valid := outputReg_valid
   io.in.ready := GS_step1PRegPostOff_ready && GS_step1AllOffloadsReady
   divPort.req.tag :=  GS_step1RThread
   divPort.req.valid :=  (GS_step1RThread != NONE_SELECTED) && !divValidReceived(GS_step1RThread) && (io.in.valid && (GS_step1RThread != NONE_SELECTED))
   divPort.req.bits := MuxCase(UFix(0, 32),Seq(((GS_step1RThread != NONE_SELECTED),fuInputDiv)))
   mulPort.req.tag :=  GS_step2RThread
   mulPort.req.valid :=  (GS_step2RThread != NONE_SELECTED) && !mulValidReceived(GS_step2RThread) && (GS_step2PRegPreOff_valid && (GS_step2RThread != NONE_SELECTED))
   mulPort.req.bits := MuxCase(UFix(0, 32),Seq(((GS_step2RThread != NONE_SELECTED),fuInputMul)))

  /*****************Context edit logic************************************************/

   
    when (GS_step2PRegPreOff_ready || !GS_step2PRegPreOff_valid) {
   when (GS_step1VThread != NONE_SELECTED) {
   GS_step2PRegPreOff_valid := GS_step1PRegPostOff_valid(GS_step1VThread)
   GS_step2PRegPreOff_tag := GS_step1PRegPostOff_tag(GS_step1VThread)
   GS_step2PRegPreOff_Input := GS_step1PRegPostOff_Input(GS_step1VThread)
   GS_step2PRegPreOff_divOut:=divPort.rep.bits
   } .otherwise { 
   GS_step2PRegPreOff_valid := Bool(false)
   } 
   } 
   
    when (outputReg_ready || !outputReg_valid) {
   when (GS_step2VThread != NONE_SELECTED) {
   outputReg_valid := GS_step2PRegPostOff_valid(GS_step2VThread)
   outputReg_tag := GS_step2PRegPostOff_tag(GS_step2VThread)
   outputReg_bits.out:=fuOutputMul.out 
   } .otherwise { 
   outputReg_valid := Bool(false)
   } 
   } 

  /*****************Request builder logic************************************************/

   
    when (GS_step1PRegPreOff_valid && GS_step1PRegPostOff_ready && GS_step1AllOffloadsReady) {
   GS_step1PRegPostOff_valid(GS_step1RThread) := io.in.valid
   GS_step1PRegPostOff_tag(GS_step1RThread) := io.in.tag
   GS_step1PRegPostOff_Input(GS_step1RThread) := io.in.bits
   } 
   
    when (GS_step2PRegPreOff_valid && GS_step2PRegPostOff_ready && GS_step2AllOffloadsReady) {
   GS_step2PRegPostOff_valid(GS_step2RThread) := GS_step2PRegPreOff_valid
   GS_step2PRegPostOff_tag(GS_step2RThread) := GS_step2PRegPreOff_tag
   } 



  /***************Offload ports helper logic*******************************/
  mulPort.rep.ready := Bool(true)
  divPort.rep.ready := Bool(true)

  /******************Ready stage handler************************/



  /******************Valid stage handler************************/


}
