package Tutorial

import Chisel._
import Node._
import Literal._
import scala.collection.mutable.HashMap

class MyFIFOIO[T <: Data]()(data: () => T) extends Bundle
{
  val ready = Bool(INPUT)
  val valid = Bool(OUTPUT)
  val bits  = data().asOutput
  def fire(dummy: Int = 0) = ready && valid
}

class gBundle[inT <: Data, outT <: Data]  (inData: () => inT, outData: () => outT) extends Bundle {
  val in = (new MyFIFOIO()) {inData}.flip
  val out = (new MyFIFOIO()) {outData}
  override def clone = { new gBundle(inData, outData).asInstanceOf[this.type]; }
}


class gComponent[inT <: Data, outT <: Data] (inData: () => inT) (outData: () => outT) extends Component {
  val io = new gBundle (inData, inData);
  override def clone = { new gComponent(inData) (outData).asInstanceOf[this.type]; }
}

class gComponentMD[inT <: Data, outT <: Data] (inData:  () => inT,  outData:  () => outT) {
 val inDataGen = inData 
 val outDataGen = outData 
 override def clone = { new gComponentMD(inData, outData).asInstanceOf[this.type]; }
}


class gChainedComponent [inT <: Data, connT <: Data, outT <: Data] (inDataSource : () => inT) (outDataSource : () => connT) (inDataSink : () => connT) (outDataSink: () => outT) (sourceGen: () => gComponent[inT, connT]) (sinkGen: () => gComponent[connT, outT]) extends gComponent(inDataSource) (outDataSink){
  //val io = new gBundle(inDataSource, outDataSink)
  val source = sourceGen()
  val sink = sinkGen()
  io.in <> source.io.in
  io.out <> sink.io.out
  source.io.out <> sink.io.in
}

trait GorillaUtil {
 def Chain  [inT <: Data, connT <: Data, outT<: Data] (a: (gComponentMD[inT, connT], () => gComponent[inT, connT]), 
                                                       b: (gComponentMD[connT, outT], () => gComponent[connT, outT])) = {
   val res = 
     () => new gChainedComponent(a._1.inDataGen) (a._1.outDataGen) (b._1.inDataGen) (b._1.outDataGen) (a._2) (b._2)
   (new gComponentMD(a._1.inDataGen, b._1.outDataGen), res)
 }
}  
