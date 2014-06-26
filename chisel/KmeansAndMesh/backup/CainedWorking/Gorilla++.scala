package Tutorial

import Chisel._
import Node._
import Literal._
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer

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
  val io = new gBundle (inData, outData);
  override def clone = { new gComponent(inData) (outData).asInstanceOf[this.type]; }
}

class gComponentLeaf[inT <: Data, outT <: Data] (inData: () => inT) (outData: () => outT) (offloadData: ArrayBuffer[(String, () => Data)]) extends gComponent(inData) (outData) {
  override def clone = { new gComponentLeaf(inData) (outData) (offloadData).asInstanceOf[this.type]; }
  val off = Bundle(offloadData.map((t) => (t._1, t._2())))
  io += off
  println("Bundle string is" + io.toString)
}

class gComponentMD[inT <: Data, outT <: Data] (inData:  () => inT,  outData:  () => outT, offloadData : ArrayBuffer[(String, () => Data)]) {
 val inDataGen = inData 
 val outDataGen = outData 
 val offloadDataGen =  offloadData 
 override def clone = { new gComponentMD(inData, outData, offloadData).asInstanceOf[this.type]; }
}

class gChainedComponent [inT <: Data, connT <: Data, outT <: Data] (inDataSource : () => inT) (outDataSource : () => connT) (inDataSink : () => connT) (outDataSink: () => outT) (offloadData1: ArrayBuffer[(String, () => Data)]) (offloadData2: ArrayBuffer[(String, () => Data)]) (sourceGen: () => gComponent[inT, connT]) (sinkGen: () => gComponent[connT, outT]) extends gComponent(inDataSource) (outDataSink) with GorillaUtil{
  val source = sourceGen()
  val sink = sinkGen()
  val off = Bundle((mergeOffloads(offloadData1, offloadData2).map((t) => (t._1, t._2()))))
  io += off

  io.in <> source.io.in
  io.out <> sink.io.out
  source.io.out <> sink.io.in
}

//class gOffloadedComponent [inT <: Data, outT <: Data, inOffT <: Data, outOffT <: Data] (inData: () => inT) (outData: () => outT) (inOff : () => inOffT) (outOff: () => outOffT) (offloadDataMain: ArrayBuffer[(String, () => Data)]) (offloadDataOff: ArrayBuffer[(String, () => Data)]) (mainGen: () => gComponent[inT, outT]) (offGen: () => gComponent[inOffT, outOffT]) (offPort: String) extends gComponent(inData)(outData) with GorillaUtil {
//  val main = mainGen()
//  val off = offGen()
//  val (left, right) = offloadDataMain.span( _._1 != offPort) 
//  val resetOfOffloadedDataMain = left ::: right.tail 
//  val offoff = Bundle((mergeOffloads(restOfOffloadedDataMain, offloadData2).map((t) => (t._1, t._2()))))
//  io += offoff
//
//  io.in <> main.io.in
//  io.out <> main.io.out
//  main.io.off. .req <> off.io.in
//  main.io.off. .rep <> off.io.out
//}

trait GorillaUtil {
  def mergeOffloads(offloadData1: ArrayBuffer[(String, () => Data)], offloadData2: ArrayBuffer[(String, () => Data)])={
    val offNames1 = offloadData1.map((t) => t._1).toSet
    val offNames2 = offloadData2.map((t) => t._1).toSet
    if (! offNames1.intersect(offNames2).isEmpty) {
      println("Gorilla++ Error: There is a common name in offload interface names")
    }
    offloadData1 ++ offloadData2 
  }
  def Chain  [inT <: Data, connT <: Data, outT<: Data] (a: (gComponentMD[inT, connT], () => gComponent[inT, connT]), 
        b: (gComponentMD[connT, outT], () => gComponent[connT, outT]))={
    val res = 
      () => 
        new gChainedComponent(a._1.inDataGen) (a._1.outDataGen) (b._1.inDataGen) (b._1.outDataGen) (a._1.offloadDataGen) (b._1.offloadDataGen) (a._2) (b._2)
    (new gComponentMD(a._1.inDataGen, b._1.outDataGen, mergeOffloads(a._1.offloadDataGen, b._1.offloadDataGen)), res)
  }

//  def Offload [inT <: Data, outT <: Data, inOffT<: Data, outOffT<: Data] (
//        a: (gComponentMD[inT, outT], () => gComponent[inT, outT]), 
//        b: (gComponentMD[inOffT, outOffT], () => gComponent[inOffT, outOffT]))={ 
//    val res = 
//      () =>
//       new gOffloadedComponet( 
//  }
}  
