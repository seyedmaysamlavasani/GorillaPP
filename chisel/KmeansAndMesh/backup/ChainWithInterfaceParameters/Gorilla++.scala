package Tutorial

import Chisel._
import Node._
import Literal._
import scala.collection.mutable.HashMap

class gBundle[inT <: Data, outT <: Data]  (inData: => inT, outData: => outT) extends Bundle {
  val in = (new FIFOIO()) {inData}.flip
  val out = (new FIFOIO()) {outData}
  override def clone = { new gBundle(inData, outData).asInstanceOf[this.type]; }
}

class gComponent[inT <: Data, outT <: Data] (inData: => inT) (outData: => outT) extends Component {
//class gComponent extends Component {
  val io = new gBundle (inData, inData);
  //val io = new gBundle (UFix(width = 32), UFix(width = 32));
  override def clone = { new gComponent(inData) (outData).asInstanceOf[this.type]; }
  //override def clone = { new gComponent.asInstanceOf[this.type]; }
}

class gComponentMD[inT <: Data, outT <: Data] (inData:  => inT,  outData:  => outT) {
 val inDataGen = inData 
 val outDataGen = outData 
 override def clone = { new gComponentMD(inData, outData).asInstanceOf[this.type]; }
}

class gChainedComponent1 [inT <: Data, connT <: Data, outT <: Data] (inDataSource : => inT) (outDataSource : => connT) (inDataSink : => connT) (outDataSink: => outT) (_source: gComponent[inT, connT]) (_sink: gComponent[connT, outT]) extends gComponent(inDataSource) (outDataSink){
  val source = _source.clone()
  val sink = _sink.clone()
  io.in <> source.io.in
  io.out <> sink.io.out
  source.io.out <> sink.io.in
}


class gChainedComponent [inT <: Data, connT <: Data, outT <: Data] (inDataSource : => inT) (outDataSource : => connT) (inDataSink : => connT) (outDataSink: => outT) (sourceGen: () => gComponent[inT, connT]) (sinkGen: () => gComponent[connT, outT]) extends gComponent(inDataSource) (outDataSink){
//class gChainedComponent [inT <: Data, connT <: Data, outT <: Data] (inDataSource : => inT) (outDataSource : => connT) (inDataSink : => connT) (outDataSink: => outT) (_source: gComponent[inT, connT]) (_sink: gComponent[connT, outT]) extends gComponent(inDataSource) (outDataSink){
//class gChainedComponent [inT <: Data, connT <: Data, outT <: Data] (inDataSource : => inT) (outDataSource : => connT) (inDataSink : => connT) (outDataSink: => outT) (sourceGen: () => gComponent[inT, connT]) (sinkGen: () => gComponent[connT, outT]) extends Component{
//class gChainedComponent [inT <: Data, connT <: Data, outT <: Data]  (sourceGen: () => gComponent) (sinkGen: () => gComponent) extends Component {
//class gChainedComponent [inT <: Data, connT <: Data, outT <: Data]  (sourceGen: () => gComponent[inT, connT]) (sinkGen: () => gComponent[connT, outT]) extends Component {
//class gChainedComponent [inT <: Data, connT <: Data, outT <: Data] (inDataSource : => inT) (outDataSource : => connT) (inDataSink : => connT) (outDataSink: => outT) (_source: gComponent[inT, connT]) (_sink: gComponent[connT, outT]) extends Component {
//class gChainedComponent [inT <: Data, connT <: Data, outT <: Data] (inDataSource : => inT) (outDataSource : => connT) (inDataSink : => connT) (outDataSink: => outT) (_source: gComponent[inT, connT]) (_sink: gComponent[connT, outT]) extends gComponent (inDataSource) (outDataSink) {
//class gChainedComponent [inT <: Data, connT <: Data, outT <: Data] (inDataSource : => inT) (outDataSource : => connT) (inDataSink : => connT) (outDataSink: => outT) (sourceGen: () => gComponent[inT, connT]) (sinkGen: () => gComponent[connT, outT]) extends gComponent (inDataSource) (outDataSink) {
  //override def clone = { new gChainedComponent(sourceGen) (sinkGen).asInstanceOf[this.type]; }
  //override def clone = { new gChainedComponent(inDataSource) (outDataSource) (inDataSink) (outDataSink) (sourceGen) (sinkGen).asInstanceOf[this.type]; }
  //override def clone = { new gChainedComponent(inDataSource) (outDataSource) (inDataSink) (outDataSink) (_source) (_sink).asInstanceOf[this.type]; }
  //val io = new gBundle(inDataSource, outDataSink)
  //override val io = new gBundle(UFix(width = 32), UFix(width = 32))
  //val source = _source.clone() 
  //val sink = _sink.clone() 
  val source = sourceGen()
  val sink = sinkGen()
  io.in <> source.io.in
  io.out <> sink.io.out
  source.io.out <> sink.io.in
}

trait GorillaUtil {
//  def Chain[inT <: Data, connT <: Data, outT <: Data] (source: (() => gComponent [inT, connT],  
//                                                                gComponentMD[inT, connT]), 
//                                                       sink:   (() => gComponent [connT, outT],  
//                                                                gComponentMD[connT, outT])) = {
  //def Chain[inT <: Data, connT <: Data, outT <: Data] (source: (() => gComponent [inT, connT], inT, connT), sink: (() => gComponent [connT, outT], connT, outT)) = { 
  //  //val resGen = () => {
  //    //new  gChainedComponent(source._2.inDataGen)(source._2.outDataGen)(sink._2.inDataGen)(sink._2.outDataGen)(source._1)(sink._1)
  //    val res = new  gChainedComponent(source._2)(source._3)(sink._2)(sink._3)(source._1)(sink._1)
  //  //}
  //  //(resGen, source._2, sink._2) 
  //  //resGen 
  //  res
  //}
}  
