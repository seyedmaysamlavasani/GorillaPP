//XX variable version 4
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
  val lookup =  Engine("lookup.c")
  val qdr =  PipeEngine("qdr.c",1)
  if (#lookup_offloaded) {
    val xx3 = Replicate(ipv4Engine, #ipv4Engine_counts)
    val xx1 = Offload( xx3 , lookup, "lookup")
    val result = Offload( xx1 , qdr, "qdr")
  } else {
    val xx4 = Offload(ipv4Engine, lookup, "lookup")
    val xx2 = Replicate( xx4 , #ipv4Engine_counts)
    val result = Offload( xx2 , qdr, "qdr")
  }
}

