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
  val multiProtocolEngine_MD = new gComponentMD(() => new NP_EthMpl3Header_t, () => new NP_EthMpl3Header_t, ArrayBuffer(("ipv4Lookup1", () => UFix(width = 32) , () => UFix(width = 8)), ("ipv4Lookup2", () => UFix(width = 32) , () => UFix(width = 8)), ("qosCount", () => UFix(width = 32) , () => UFix(width = 8))))
  val  mpEngine =  (multiProtocolEngine_MD , () => new multiProtocolEngine (extCompName="mpEngine") ).asInstanceOf[(gComponentMD[Chisel.Data,Chisel.Data], () => gComponent[Chisel.Data,Chisel.Data])]
  val mem = spMem(height = 1000, width = 16)
  val mem1, mem2, mem3 = mem
  val mem4, mem5, mem6 = mem
  val rwMem = rwSpMem(height = 1000, width = 16)
  val mem7, mem8, mem9 = rwMem
  val lookupMems1 = ArrayBuffer((mem1, "mem1"), (mem2, "mem2"), (mem3, "mem3"))
  val lookupMems2 = ArrayBuffer((mem4, "mem1"), (mem5, "mem2"), (mem6, "mem3"))
  val ipv4Lookup_MD = new gComponentMD(() => UFix(width = 32), () => UFix(width = 8), ArrayBuffer(("mem1", () => new memReq32_t , () => new memRep32_t), ("mem2", () => new memReq32_t , () => new memRep32_t), ("mem3", () => new memReq32_t , () => new memRep32_t)))
  val  xx1 =  (ipv4Lookup_MD , () => new ipv4Lookup (extCompName="xx1") ).asInstanceOf[(gComponentMD[Chisel.Data,Chisel.Data], () => gComponent[Chisel.Data,Chisel.Data])]
  val  ipv4Lookup1 = Offload ( xx1 , lookupMems1, extCompName="ipv4Lookup1")
  val  xx2 =  (ipv4Lookup_MD , () => new ipv4Lookup (extCompName="xx2") ).asInstanceOf[(gComponentMD[Chisel.Data,Chisel.Data], () => gComponent[Chisel.Data,Chisel.Data])]
  val  ipv4Lookup2 = Offload ( xx2 , lookupMems2, extCompName="ipv4Lookup2")
  val qosCountMems = ArrayBuffer((mem7, "mem1"), (mem8, "mem2"), (mem9, "mem3"))
  val qosCount_MD = new gComponentMD(() => UFix(width = 32), () => UFix(width = 8), ArrayBuffer(("mem1Read", () => new memReadOnlyReq32_t , () => new memReadOnlyRep32_t), ("mem1Write", () => new memWriteOnlyReq32_t , () => new memWriteOnlyRep32_t), ("mem2Read", () => new memReadOnlyReq32_t , () => new memReadOnlyRep32_t), ("mem2Write", () => new memWriteOnlyReq32_t , () => new memWriteOnlyRep32_t), ("mem3Read", () => new memReadOnlyReq32_t , () => new memReadOnlyRep32_t), ("mem3Write", () => new memWriteOnlyReq32_t , () => new memWriteOnlyRep32_t)))
  val  xx3 =  (qosCount_MD , () => new qosCount (extCompName="xx3") ).asInstanceOf[(gComponentMD[Chisel.Data,Chisel.Data], () => gComponent[Chisel.Data,Chisel.Data])]
  val  qosCount = Offload ( xx3 , qosCountMems, extCompName="qosCount")
  val lookups = ArrayBuffer ((ipv4Lookup1, "ipv4Lookup1"), (ipv4Lookup2, "ipv4Lookup2"))
  val  xx4 = Offload (mpEngine, lookups, extCompName="xx4")
  val  result = Offload ( xx4 , qosCount, "qosCount", extCompName="result")
  val generatedTop = result._2()
  generatedTop.io <> io
}

class TopTests(o: Top) extends gTester(o) { 
  defTests {
    val inputs_data = List(1, 2, 4, 8)
    //val test = new Top 
    val iDelay = 1
    for (time <- 0 until 5) {
      svars(o.io.in.valid) = Bool(false)
      step(svars, ovars, false)
    }
    if (compilerControl.pcEnable) {
      resetPC()
    }
    var sourced = 0
    var sourcedIndex = 0
    var sinked = 0
    var sinkedIndex = 0
    var time = 0
    var allPassed = true
    var cycles = 0
    //val numOfPackets = 5000
    val numOfPackets = 2500
    val version = UFix(4, width=4)
    val hLength = UFix(14, width=4)
    val tos = UFix(0, width=8)
    val length = UFix(21, width=16)
    val identification = UFix(10, width=16)
    val flagsOffset = UFix(10, width=16)
    val ttl = UFix(5, width=8)
    val protocol = UFix(4, width=8)
    val checksum = UFix(21, width=16)
    val srcAddr = UFix(0, width=32)
    val dstAddr = UFix(0, width=32)
    val ipv4Header = Cat(version, hLength, tos, length, 
     identification, flagsOffset, ttl, protocol, checksum, 
     srcAddr, dstAddr)
    while(sourced < numOfPackets || sinked < numOfPackets-100) {
      if ((sourced < numOfPackets) && (cycles % iDelay == 0)) {
        svars(o.io.in.bits.l3) = (new mpl3Header_t).fromBits(ipv4Header.toBits)
        svars(o.io.in.bits.l2Protocol) = Bits(128)
        svars(o.io.in.bits.eth.l3Type) = Bits(64)
        svars(o.io.in.valid) = Bool(true)
        svars(o.io.out.ready) = Bool(true)
      } else {
        //svars(o.io.in.bits) = UFix(0) 
        svars(o.io.in.valid) = Bool(false)
        svars(o.io.out.ready) = Bool(true)
      }
      step(svars, ovars)
      // bump counters and check outputs after advancing clock
      if (ovars(o.io.in.ready).litValue() == 1 && (cycles % iDelay == 0) && (sourced < numOfPackets)) {
        sourced += 1
        sourcedIndex = sourced % 4 
        println("sourced and sourcedIndex are " + sourced + " " + sourcedIndex)
      }
      cycles += 1
      if (ovars(o.io.out.valid).litValue() == 1) {
        if (allPassed == false) {
			println("Test failed ") //+ ovars(o.io.out.bits).litValue() + 
                          //" expected " + (inputs_data(sinkedIndex) +2))
			println("Sinked is " + sinked)
        }
          println("At " + time + " outpout " + " sinked. Sinked is " + 
                 sinked + " sourced is " + sourced );
        sinked += 1
        sinkedIndex = sinked % 4
      }
      time += 1
    }
    if (compilerControl.pcEnable) {
      PCReport(cycles, numOfPackets)
    }
    step(svars, ovars)
    allPassed 
  }
}
