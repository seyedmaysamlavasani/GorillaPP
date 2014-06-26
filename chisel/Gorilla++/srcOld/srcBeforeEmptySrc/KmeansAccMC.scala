package Tutorial

import Chisel._
import Node._
import Literal._
import scala.collection.mutable.HashMap
import scala.math.log
import scala.math.abs
import scala.math.ceil
import scala.math.max
import scala.math.min


class KmeansAccMC(cores:Int) extends Component {
  val io = new Bundle {
    val error = Bits(OUTPUT, 1) 
    val done = Bool(OUTPUT)
    val lcInStream = (new FIFOIO()) {new StreamData}
    val lcOutStream = (new FIFOIO()) {new StreamData}
    val lcInStreamReady = Bits(OUTPUT, 1)
    val reducerInStreamReady = Bits(OUTPUT, 1)
   } 
  val streamer = new StreamerAndMem
  val splitter = new KmeansSplitter(cores)
  val dLCore = Range(0, cores).map(i => new DistanceLambdaCore)
  val reducer = new KmeansReducer(cores)
 
  
  
  streamer.io.inStream <> reducer.io.outStream
  streamer.io.outStream <> splitter.io.inStream   
  for (i <- 0 until cores) {
    splitter.io.outStreams(i) <> dLCore(i).io.inStream  
    dLCore(i).io.outStream <> reducer.io.inStreams(i)
  }
  streamer.io.config.req.enable := Bool(false)
  io.done := streamer.io.done

  val coresError = Range(0, cores).map(i => dLCore(i).io.error).reduceLeft(_||_) 
  io.error := coresError || streamer.io.error || 
              reducer.io.error 
              //splitter.io.error || reducer.io.error 

  //Instrumentation
  io.lcInStream.valid <> splitter.io.outStreams(0).valid
  io.lcInStream.bits <> splitter.io.outStreams(0).bits
  io.lcInStreamReady <> dLCore(0).io.inStream.ready
  io.lcOutStream.valid <> dLCore(0).io.outStream.valid
  io.lcOutStream.bits <> dLCore(0).io.outStream.bits
  io.reducerInStreamReady <> reducer.io.inStreams(0).ready
}

//class KmeansAccMCTests(c: KmeansAccMC) extends Tester(c, Array(c.io)) {
//class KmeansAccTests(c: KmeansAccMC) extends Tester(c, Array(c.io, c.d.state, c.d.numOfCenters, c.d.id, c.d.dim)) {
class KmeansAccMCTests(c: KmeansAccMC) extends Tester(c, Array(c.io, c.streamer.streamer.state, c.streamer.streamer.id, c.streamer.streamer.io.outStream, c.splitter.io.inStream.ready, c.dLCore(0).state, c.dLCore(0).id)) {
//class KmeansAccMCTests(c: KmeansAccMC) extends Tester(c, Array(c.io, c.dLCore(0).state, c.dLCore(0).id, c.dLCore(0).dim, c.dLCore(0).pointId, c.reducer.serialize.state, c.reducer.sAverage.state, c.reducer.serialize.currOutput, c.reducer.sAverage.io.inStream.ready, c.reducer.sAverage.id, c.reducer.sAverage.dim)) {
//class KmeansAccMCTests(c: KmeansAccMC) extends Tester(c, Array(c.io, c.reducer.io.inStreams(0), c.reducer.serialize.io.outStream, c.reducer.sAverage.io.inStream.ready, c.reducer.serialize.state, c.reducer.sAverage.state, c.reducer.serialize.currOutput, c.reducer.serialize.currentReady, c.dLCore(0).id, c.dLCore(0).dim, c.dLCore(0).state, c.dLCore(0).pointId, c.streamer.io.inStream.ready, c.dLCore(1).io.outStream.valid, c.dLCore(1).state)) {
  defTests {
    def splitCount(count: UFix, id: UFix) : UFix = {
      if (LINQAccConfig.cores == 1) count else (count >> UFix(log2Up(LINQAccConfig.cores)))
    }
    var allGood = true
    val svars = new HashMap[Node, Node]()
    val ovars = new HashMap[Node, Node]()
    var time = 0
    println("cores are " + LINQAccConfig.cores + " points are " + 
      KmeansConfig.points + " core 0 gets " + 
      splitCount(UFix(KmeansConfig.points), UFix(0)).litValue().intValue())
    step(svars, ovars, true)
    while (ovars(c.io.done).litValue().intValue() == 0 && time < 10000000) {
      if (time % 1000 == 0 || KmeansConfig.debug == 1) { 
        println(time)
        step(svars, ovars, true)
      } else  
        step(svars, ovars, false)
      time = time + 1
//      if (ovars(c.io.lcOutStream.valid).litValue().intValue() == 1 &&
//        ovars(c.reducer.io.inStreams(0).ready).litValue().intValue() == 1) {
//        println("From core 0 to reducer transfer happend at time " + time)
//      }
    }

    println("Elapsed time:" + time)
    allGood
  }
}
