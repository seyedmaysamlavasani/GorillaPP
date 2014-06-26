package Tutorial

import Chisel._
import Node._
import Literal._
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer


/*This source implements an IPv4 network processor */

class Top extends Component with GorillaUtil {
  val io = new gInOutBundle (() => new NP_EthIPv4Header_t , () => new NP_EthIPv4Header_t)
  val ipv4Engine =  MTEngine("ipv4Engine.c", #ipv4Engine_numOfThreads)
  val lookup = Engine("lookup.c")
  val qdr = PipeEngine("qdr.c",1)
  val result = Offload(Offload(ipv4Engine, lookup, "lookup"), qdr, "qdr")
}

