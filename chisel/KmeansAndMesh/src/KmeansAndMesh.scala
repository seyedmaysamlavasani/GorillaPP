package Tutorial

import Chisel._
import Node._;
import Literal._;
import scala.collection.mutable.HashMap
import scala.math.log
import scala.math.abs
import scala.math.ceil
import scala.math.max
import scala.math.min



class KmeansAndMesh (cores:Int) extends Component {
  val io = new Bundle {
    val error = Bool(OUTPUT)
    val done = Bool(OUTPUT)
    val debugStream = (new FIFOIO()) {new StreamData}
    val debugReady = Bool(OUTPUT)
    val transfer = Bool(OUTPUT)
    val fromStreamerTransfer = Bool(OUTPUT)
    val instrument = new InstrumentIO
  }
  val aray = KmeansTestConfig.networkAray 
  val streamer = new StreamerAndMem
  val splitter = new KmeansSplitter(cores)
  val dLCores = Range(0, cores).map(i => new DistanceLambdaCore)
  val nics = Range(0, 2*cores).map(i => new NIC)
  val reducer = new KmeansReducer(cores)
  val mesh = new MyTwoDMesh(aray)
  

  val debugID = 0
//  io.debugStream.valid := dLCores(debugID).io.outStream.valid
//  io.debugStream.bits.header := dLCores(debugID).io.outStream.bits.header
//  io.debugStream.bits.trailer := dLCores(debugID).io.outStream.bits.trailer
//  io.debugStream.bits.data := dLCores(debugID).io.outStream.bits.data
//  io.debugReady := nics(cores+debugID).io.coreSend.ready
//  io.transfer := io.debugReady && io.debugStream.valid 
//
  io.debugStream.valid := nics(cores+debugID).io.coreRcv.valid
  io.debugStream.bits.header := nics(cores+debugID).io.coreRcv.bits.header
  io.debugStream.bits.trailer := nics(cores+debugID).io.coreRcv.bits.trailer
  io.debugStream.bits.data := nics(cores+debugID).io.coreRcv.bits.data
  io.debugReady := dLCores(debugID).io.inStream.ready
  io.transfer := io.debugReady && io.debugStream.valid 

//  io.debugStream.valid := nics(debugID).io.coreRcv.valid
//  io.debugStream.bits.header := nics(debugID).io.coreRcv.bits.header
//  io.debugStream.bits.trailer := nics(debugID).io.coreRcv.bits.trailer
//  io.debugStream.bits.data := nics(debugID).io.coreRcv.bits.data
//  io.debugReady := reducer.io.inStreams(debugID).ready
//  io.transfer := io.debugReady && io.debugStream.valid 

  io.fromStreamerTransfer := streamer.io.outStream.valid && splitter.io.inStream.ready 

  streamer.io.inStream <> reducer.io.outStream
  streamer.io.outStream <> splitter.io.inStream   

  io.instrument <> dLCores(0).io.instrument
  for (i <- 0 until cores) {
    splitter.io.outStreams(i) <> nics(i).io.coreSend 
    nics(i).io.sendAddr := UFix(cores+i)
    reducer.io.inStreams(i) <> nics(i).io.coreRcv
    dLCores(i).io.inStream <> nics(cores+i).io.coreRcv  
    dLCores(i).io.outStream <> nics(cores+i).io.coreSend
    nics(cores+i).io.sendAddr := UFix(i)
  }
  streamer.io.config.req.enable := Bool(false)
  io.done := streamer.io.done

  val coresError = Range(0, cores).map(i => dLCores(i).io.error).reduceLeft(_||_) 
  io.error := coresError || streamer.io.error || 
              //splitter.io.error || reducer.io.error 
              reducer.io.error 

  for (i <- 0 until 2*cores) {
    mesh.io.tap(i).enq <> nics(i).io.networkSend
    mesh.io.tap(i).deq <> nics(i).io.networkRcv
  }
}

//class KmeansAndMeshTests(c: KmeansAndMesh) extends Tester(c, Array(c.io)) {
//class KmeansAndMeshTests(c: KmeansAndMesh) extends Tester(c, Array(c.io, c.mesh.io.tap(1).deq, c.splitter.io.outStreams(0), c.streamer.dummyMem.io.reply.rData , c.streamer.dummyMem.io.reply.ready ,c.streamer.streamer.state, c.streamer.streamer.io.outStream, c.splitter.io.inStream.ready, c.splitter.state, c.mesh.io.tap(0).enq.ready, c.nics(0).io.coreSend.ready, c.dLCores(0).state, c.dLCores(0).state, c.reducer.serialize.state, c.reducer.sAverage.state, c.reducer.sAverage.id, c.reducer.sAverage.dim, c.reducer.serialize.currOutput, c.dLCores(0).numOfPoints, c.dLCores(0).computeCyclesLeft, c.mesh.routers(0)(0).inPorts(4).linkCount)) {
class KmeansAndMeshTests(c: KmeansAndMesh) extends Tester(c, Array(c.io, c.mesh.io.tap(1).deq, c.splitter.io.outStreams(0), c.streamer.dummyMem.io.reply.rData , c.streamer.dummyMem.io.reply.ready ,c.streamer.streamer.state, c.streamer.streamer.io.outStream, c.splitter.io.inStream.ready, c.splitter.state, c.mesh.io.tap(0).enq.ready, c.nics(0).io.coreSend.ready, c.dLCores(0).state, c.dLCores(0).state, c.reducer.serialize.state, c.reducer.sAverage.state, c.reducer.sAverage.id, c.reducer.sAverage.dim, c.reducer.serialize.currOutput, c.dLCores(0).numOfPoints, c.dLCores(0).computeCyclesLeft)) {

//class KmeansAndMeshTests(c: KmeansAndMesh) extends Tester(c, Array(c.io, c.mesh.io.tap(1).deq, c.splitter.io.outStreams(0), c.streamer.dummyMem.io.reply.rData , c.streamer.dummyMem.io.reply.ready ,c.streamer.streamer.state, c.streamer.streamer.io.outStream, c.splitter.io.inStream.ready, c.splitter.state, c.mesh.io.tap(0).enq.ready, c.nics(0).io.coreSend.ready, c.dLCores(0).state, c.dLCores(1).state, c.reducer.serialize.state, c.reducer.sAverage.state, c.reducer.sAverage.id, c.reducer.sAverage.dim, c.reducer.serialize.currOutput)) {
//class KmeansAccTests(c: KmeansAccMC) extends Tester(c, Array(c.io, c.d.state, c.d.numOfCenters, c.d.id, c.d.dim)) {
//class KmeansAccMCTests(c: KmeansAccMC) extends Tester(c, Array(c.io, c.streamer.streamer.state, c.streamer.streamer.id, c.dLCore(0).state, c.dLCore(0).id, c.dLCore(0).dim)) {
//class KmeansAccMCTests(c: KmeansAccMC) extends Tester(c, Array(c.io, c.dLCore(0).state, c.dLCore(0).id, c.dLCore(0).dim, c.dLCore(0).pointId, c.reducer.serialize.state, c.reducer.sAverage.state, c.reducer.serialize.currOutput, c.reducer.sAverage.io.inStream.ready, c.reducer.sAverage.id, c.reducer.sAverage.dim)) {
//class KmeansAccMCTests(c: KmeansAccMC) extends Tester(c, Array(c.io, c.reducer.io.inStreams(0), c.reducer.serialize.io.outStream, c.reducer.sAverage.io.inStream.ready, c.reducer.serialize.state, c.reducer.sAverage.state, c.reducer.serialize.currOutput, c.reducer.serialize.currentReady, c.dLCore(0).id, c.dLCore(0).dim, c.dLCore(0).state, c.dLCore(0).pointId, c.streamer.io.inStream.ready, c.dLCore(1).io.outStream.valid, c.dLCore(1).state)) {
  defTests {
    var allGood = true
    val svars = new HashMap[Node, Node]()
    val ovars = new HashMap[Node, Node]()
    var time = 0
    svars(c.io.instrument.address) = 0
    step(svars, ovars, true)
    println ("log2up " + log2Up(2))
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
    println("Kmeans done")
    for (i <- 0 until 100) {
      svars(c.io.instrument.address) = i
      step(svars, ovars, true)
    }
    println("Elapsed time:" + time)
    allGood
  }
}
