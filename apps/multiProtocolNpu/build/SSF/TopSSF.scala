//XX variable version 4
package Tutorial

import Chisel._
import Node._
import Literal._
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer


/*This source implements an IPv4 network processor */

class Top extends Component with GorillaUtil {
  val io = new gInOutBundle (() => new NP_EthMpl3Header_t , () => new NP_EthMpl3Header_t)
  val mpEngine = Engine("multiProtocolEngine.c")
  val mem = spMem(height = 1000, width = 16)
  val mem1, mem2, mem3 = mem
  val mem4, mem5, mem6 = mem
  val rwMem = rwSpMem(height = 1000, width = 16)
  val mem7, mem8, mem9 = rwMem
  val lookupMems1 = ArrayBuffer((mem1, "mem1"), (mem2, "mem2"), (mem3, "mem3"))
  val lookupMems2 = ArrayBuffer((mem4, "mem1"), (mem5, "mem2"), (mem6, "mem3"))
  val xx1 = Engine("ipv4Lookup.c")
  val ipv4Lookup1 = Offload( xx1 , lookupMems1)
  val xx2 = Engine("ipv4Lookup.c")
  val ipv4Lookup2 = Offload( xx2 , lookupMems2)
  val qosCountMems = ArrayBuffer((mem7, "mem1"), (mem8, "mem2"), (mem9, "mem3"))
  val xx3 = Engine("qosCount.c")
  val qosCount = Offload( xx3 , qosCountMems)
  val lookups = ArrayBuffer ((ipv4Lookup1, "ipv4Lookup1"), (ipv4Lookup2, "ipv4Lookup2"))
  val xx4 = Offload(mpEngine, lookups)
  val result = Offload( xx4 , qosCount, "qosCount")
}

