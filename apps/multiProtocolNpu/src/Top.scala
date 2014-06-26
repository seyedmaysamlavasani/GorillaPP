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
  val ipv4Lookup1 = Offload(Engine("ipv4Lookup.c"), lookupMems1)
  val ipv4Lookup2 = Offload(Engine("ipv4Lookup.c"), lookupMems2)
  val qosCountMems = ArrayBuffer((mem7, "mem1"), (mem8, "mem2"), (mem9, "mem3"))
  val qosCount = Offload(Engine("qosCount.c"), qosCountMems)
  val lookups = ArrayBuffer ((ipv4Lookup1, "ipv4Lookup1"), (ipv4Lookup2, "ipv4Lookup2"))
  val result = Offload(Offload(mpEngine, lookups), qosCount, "qosCount")
}

