package Tutorial

import Chisel._
import Node._
import Literal._
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer


class rankCalculator (extCompName:String) extends gComponentLeaf  (() => new rankCalc_in_t)(() => new rankCalc_out_t) (ArrayBuffer(("mul", () => new FU_in_t , () => new FU_out_t), ("div", () => new FU_in_t , () => new FU_out_t)))  (extCompName =  extCompName + "__type_engine__pipelined__") with include {
  val tagWidth = 5*2
  val numOfThreads = 4 
  val NONE_SELECTED = UFix(numOfThreads,log2Up(numOfThreads)+1)
  val outputReg_ready = io.out.ready 

  val GS_step1AllOffloadsReady = Bool ()
  val GS_step1PipeReadyMove = Bool ()
  val GS_step1NumOfThreads = numOfThreads
  val GS_step1AllOffloadsValid = Vec(GS_step1NumOfThreads) {Bool ()}
  val GS_step1PipeValidMove = Bool ()
  val GS_step2AllOffloadsReady = Bool ()
  val GS_step2PipeReadyMove = Bool ()
  val GS_step2NumOfThreads = numOfThreads
  val GS_step2AllOffloadsValid = Vec(GS_step2NumOfThreads) {Bool ()}
  val GS_step2PipeValidMove = Bool ()

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
val GS_step1PRegPreOff_valid = Bool(true)

  val GS_step1RThreadEncoder = new RREncode (GS_step1NumOfThreads) 
  val GS_step1RThread =  GS_step1RThreadEncoder.io.chosen
  val GS_step1VThreadEncoder = new RREncode (GS_step1NumOfThreads) 
  val GS_step1VThread =  GS_step1VThreadEncoder.io.chosen
  val GS_step2RThreadEncoder = new RREncode (GS_step2NumOfThreads) 
  val GS_step2RThread =  GS_step2RThreadEncoder.io.chosen
  val GS_step2VThreadEncoder = new RREncode (GS_step2NumOfThreads) 
  val GS_step2VThread =  GS_step2VThreadEncoder.io.chosen

  val mulPortHadReadyRequest = Reg(resetVal=Bool(false))
  val mulReadyReceived = Reg(resetVal=Bool(false))
  val divPortHadReadyRequest = Reg(resetVal=Bool(false))
  val divReadyReceived = Reg(resetVal=Bool(false))

  val divPortHadValidRequest = Vec(GS_step1NumOfThreads) {Reg(resetVal=Bool(false))}
  val divValidReceived = Vec(GS_step1NumOfThreads) {Reg(resetVal=Bool(false))}
  val mulPortHadValidRequest = Vec(GS_step2NumOfThreads) {Reg(resetVal=Bool(false))}
  val mulValidReceived = Vec(GS_step2NumOfThreads) {Reg(resetVal=Bool(false))}


  /******************Winner threads for each instruction with offload*****************/
  GS_step1PipeReadyMove := (GS_step1RThread != NONE_SELECTED && GS_step1PRegPostOff_ready && GS_step1AllOffloadsReady) 
  Range(0, GS_step1NumOfThreads, 1).map(i => 
    GS_step1RThreadEncoder.io.valid(i) := !GS_step1PRegPostOff_valid(i))
  GS_step1RThreadEncoder.io.ready := (GS_step1PipeReadyMove)

  GS_step1PipeValidMove := (GS_step1VThread != NONE_SELECTED && GS_step2PRegPreOff_ready && GS_step1AllOffloadsValid(GS_step1VThread)) 
  Range(0, GS_step1NumOfThreads, 1).map(i => 
    GS_step1VThreadEncoder.io.valid(i) := (GS_step1PRegPostOff_valid(i)) && GS_step1AllOffloadsValid(i))
  GS_step1VThreadEncoder.io.ready := (GS_step1PipeValidMove)
  GS_step2PipeReadyMove := (GS_step2RThread != NONE_SELECTED && GS_step2PRegPostOff_ready && GS_step2AllOffloadsReady) 
  Range(0, GS_step2NumOfThreads, 1).map(i => 
    GS_step2RThreadEncoder.io.valid(i) := !GS_step2PRegPostOff_valid(i))
  GS_step2RThreadEncoder.io.ready := (GS_step2PipeReadyMove)

  GS_step2PipeValidMove := (GS_step2VThread != NONE_SELECTED && outputReg_ready && GS_step2AllOffloadsValid(GS_step2VThread)) 
  Range(0, GS_step2NumOfThreads, 1).map(i => 
    GS_step2VThreadEncoder.io.valid(i) := (GS_step2PRegPostOff_valid(i)) && GS_step2AllOffloadsValid(i))
  GS_step2VThreadEncoder.io.ready := (GS_step2PipeValidMove)


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
GS_step1AllOffloadsReady :=  
  (divPort.req.ready || divReadyReceived || (!divPortHadReadyRequest && !divPort.req.valid)) &&  
  Bool(true)
GS_step2AllOffloadsReady :=  
  (mulPort.req.ready || mulReadyReceived || (!mulPortHadReadyRequest && !mulPort.req.valid)) &&  
  Bool(true)

  divReadyReceived := !(GS_step1PipeReadyMove) && (divReadyReceived || divPort.req.ready)
  divPortHadReadyRequest := !GS_step1PipeReadyMove && (divPortHadReadyRequest || divPort.req.valid)
  mulReadyReceived := !(GS_step2PipeReadyMove) && (mulReadyReceived || mulPort.req.ready)
  mulPortHadReadyRequest := !GS_step2PipeReadyMove && (mulPortHadReadyRequest || mulPort.req.valid)


  /******************Valid stage handler************************/
  when (GS_step1PipeValidMove) {
    GS_step1PRegPostOff_valid(GS_step1VThread) := Bool(false)
  }
  when (GS_step2PipeValidMove) {
    GS_step2PRegPostOff_valid(GS_step2VThread) := Bool(false)
  }

  for (i <- 0 to GS_step1NumOfThreads-1) {
    GS_step1AllOffloadsValid(i) :=  
     ((divPort.rep.valid && (divPort.rep.tag === UFix(i, tagWidth)))|| divValidReceived(i) || !divPortHadValidRequest(i)) &&  
     Bool(true)
  for (i <- 0 to GS_step2NumOfThreads-1) {
    GS_step2AllOffloadsValid(i) :=  
     ((mulPort.rep.valid && (mulPort.rep.tag === UFix(i, tagWidth)))|| mulValidReceived(i) || !mulPortHadValidRequest(i)) &&  
     Bool(true)

  divValidReceived(i) := !(GS_step1PipeValidMove && GS_step1VThread === UFix(i, tagWidth)) && ((divValidReceived(i)) || (divPort.rep.valid && divPort.rep.tag === UFix(i, tagWidth)))
  divPortHadValidRequest(i) := !(GS_step1PipeValidMove && GS_step1VThread === UFix(i,tagWidth)) && (divPortHadValidRequest(i) || (UFix(i,tagWidth)===GS_step1RThread && divPort.req.valid))
}
  mulValidReceived(i) := !(GS_step2PipeValidMove && GS_step2VThread === UFix(i, tagWidth)) && ((mulValidReceived(i)) || (mulPort.rep.valid && mulPort.rep.tag === UFix(i, tagWidth)))
  mulPortHadValidRequest(i) := !(GS_step2PipeValidMove && GS_step2VThread === UFix(i,tagWidth)) && (mulPortHadValidRequest(i) || (UFix(i,tagWidth)===GS_step2RThread && mulPort.req.valid))
}
}
