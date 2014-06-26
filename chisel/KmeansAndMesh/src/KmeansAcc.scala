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


class KmeansAcc extends Component {
  val io = new Bundle {
    val error = Bits(OUTPUT, 1) 
    val done = Bool(OUTPUT)
    val lcInStream = (new FIFOIO()) {new StreamData}
    val lcOutStream = (new FIFOIO()) {new StreamData}
    val lcInStreamReady = Bits(OUTPUT, 1)
    val stInStreamReady = Bits(OUTPUT, 1)
   } 
  val s = new StreamerAndMem
  val d = new DistanceLambdaCore
  s.io.inStream <> d.io.outStream
  s.io.outStream <> d.io.inStream   
  io.lcInStream.valid <> s.io.outStream.valid
  io.lcInStream.bits <> s.io.outStream.bits
  io.lcInStreamReady <> d.io.inStream.ready
  io.lcOutStream.valid <> d.io.outStream.valid
  io.lcOutStream.bits <> d.io.outStream.bits
  io.stInStreamReady <> s.io.inStream.ready
  io.error := d.io.error || s.io.error
  s.io.config.req.enable := Bool(false)
  io.done := s.io.done
}

//class KmeansAccTests(c: KmeansAcc) extends Tester(c, Array(c.io, c.d.state, c.d.numOfCenters, c.d.id, c.d.dim)) {
class KmeansAccTests(c: KmeansAcc) extends Tester(c, Array(c.io, c.s.streamer.state, c.s.streamer.id, c.d.state, c.d.id)) {
  defTests {
    var allGood = true
    val svars = new HashMap[Node, Node]()
    val ovars = new HashMap[Node, Node]()
    var time = 0
    step(svars, ovars, true)
    while (ovars(c.io.done).litValue().intValue() == 0 && time < 10000000) {
      if (time % 1000 == 0 || KmeansConfig.debug == 1) 
        step(svars, ovars, true)
      else  
        step(svars, ovars, false)
      time = time + 1
    }
    println(time)
    allGood
  }
} 
