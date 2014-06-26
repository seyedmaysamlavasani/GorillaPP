package Tutorial

import Chisel._
import Node._
import Literal._
import scala.collection.mutable.HashMap


class gBundle[inT <: Data, outT <: Data]  (inData: => inT, outData: => outT) extends Bundle {
  val in = (new FIFOIO()) {inData}.flip
  val out = (new FIFOIO()) {outData}
}

//class gComponent[inT <: Data, outT <: Data] (inData: => inT) (outData: => outT) extends Component {
class gComponent extends Component {
  //val io = (new gBundle()) (inData, inData);
  val io = new gBundle (UFix(width=32), UFix(width=32));
  //val inDataGen = inData
  //val outDataGen = outData
    
}

//class gChainedComponent [inT <: Data, connT <: Data, outT <: Data] (inData : => inT) (connData : => connT) (outData: => outT) (sourceGen: () => gComponent[inT, connT]) (sinkGen: () => gComponent[connT, outT]) extends gComponent[inT, connT] (inData) (outData) {
class gChainedComponent (sourceGen: () => gComponent) (sinkGen: () => gComponent) extends gComponent {
//  val source = sourceGen() (inData) (connData)
  val source = sourceGen()  
//  val sink = sinkGen() (connData) (outData) 
  val sink = sinkGen() 
  io.in <> source.io.in
  io.out <> sink.io.out
  source.io.out <> sink.io.in
}

trait GorillaUtil {
//  def Chain [inT <: Data, connT <: Data, outT <: Data] (sourceGen:() => gComponent[inT, connT](inData1 : => inT)(outData1: => connT), sinkGen: () => gComponent[connT, outT](inData2: => connT)(outData2: => outT)) = {
  def Chain (sourceGen:() => gComponent, sinkGen: () => gComponent) = {
    val resGen = () => {
//      new  gChainedComponent(inData) (connData) (outData) (sourceGen) (sinkGen)
      new  gChainedComponent (sourceGen) (sinkGen)
    }
    resGen
  }  
}
