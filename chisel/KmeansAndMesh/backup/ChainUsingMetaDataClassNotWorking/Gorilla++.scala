package Tutorial

import Chisel._
import Node._
import Literal._
import scala.collection.mutable.HashMap

class gBundle[inT <: Data, outT <: Data]  (inData: => inT, outData: => outT) extends Bundle {
  val in = (new FIFOIO()) {inData}.flip
  val out = (new FIFOIO()) {outData}
}

class gComponent[inT <: Data, outT <: Data] (inData: => inT) (outData: => outT) extends Component {
  val io = new gBundle (inData, inData);
}

class gComponentMD[inT <: Data, outT <: Data] (inData:  => inT,  outData:  => outT, val gen: () => gComponent[inT, outT]) {
 val inDataGen = inData 
 val outDataGen = outData 
 val compGen = gen
}

//class gChainedComponent [inT <: Data, connT <: Data, outT <: Data] (inDataSource : => inT) (outDataSource : => connT) (inDataSink : => connT) (outDataSink: => outT) (sourceGen: () => gComponent[inT, connT]) (sinkGen: () => gComponent[connT, outT]) extends gComponent[inT, outT] (inDataSource) (outDataSink) {
class gChainedComponent [inT <: Data, connT <: Data, outT <: Data] (inDataSource : => inT) (outDataSource : => connT) (inDataSink : => connT) (outDataSink: => outT) (sourceGen: () => gComponent[inT, connT]) (sinkGen: () => gComponent[connT, outT]) extends gComponent (inDataSource) (outDataSink) {
  val source = sourceGen() 
  val sink = sinkGen() 
  io.in <> source.io.in
  io.out <> sink.io.out
  source.io.out <> sink.io.in
}

trait GorillaUtil {
  def Chain[inT <: Data, connT <: Data, outT <: Data] (sourceMD: gComponentMD [inT, connT], sinkMD: gComponentMD [connT, outT]) = {
    val resGen = () => {
      new  gChainedComponent(sourceMD.inDataGen)(sourceMD.outDataGen)(sinkMD.inDataGen)(sinkMD.outDataGen)(sourceMD.compGen)(sinkMD.compGen)
    }
    val resComponentMD = new gComponentMD(sourceMD.inDataGen, sinkMD.outDataGen, resGen)
    resComponentMD
  }
}  
