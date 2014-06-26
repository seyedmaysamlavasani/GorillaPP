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

class StreamerSplitter extends Component {
  val degree = 2
  val io = new Bundle {
    val outStreams = Vec(degree) {(new FIFOIO()) {new StreamData}}
  }

  val streamer = new StreamerAndMem
  val splitter = new KmeansSplitter(degree)
  
  streamer.io.outStream <> splitter.io.inStream
  streamer.io.config.req.enable := Bool(false)
  io.outStreams <> splitter.io.outStreams 
  streamer.io.inStream.valid := Bool(false)
}

class StreamerSplitterTests(c: StreamerSplitter) extends Tester(c, Array(c.io, c.streamer.io.outStream, c.splitter.io.inStream, c.streamer.streamer.state, c.streamer.streamer.go, c.splitter.state)) {
  defTests {
    var allGood = true
    val svars = new HashMap[Node, Node]()
    val ovars = new HashMap[Node, Node]()
    var time = 0
    svars(c.io.outStreams(0).ready) = Bool(true)
    svars(c.io.outStreams(1).ready) = Bool(true)
    step(svars, ovars, true)
    while (time < 1000) {
      step(svars, ovars, true)
    }
    println(time)
    allGood
  }
}

