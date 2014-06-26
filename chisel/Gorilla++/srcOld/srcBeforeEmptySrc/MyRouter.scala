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




object constants {
  val ring::crossbar::mesh::torus::Nil = Enum(4){UFix()}
  val west::east::north::south::cpu::Nil = Enum(5){UFix()}
  val unifiedRandom::complement::reverse::rotate::shuffle::transpose::list::tornado::neighbor::Nil = 
    0::1::2::3::4::5::6::7::8::Nil
}

object routerConfig {
  val topology = constants.mesh
  val numDim = 2 
  val networkAray = if (routerTestConfig.valid)  
                      routerTestConfig.networkAray 
                    else 
                      meshTestConfig.aray
  def numNeighborsPerDim = 2 
  val dimAddrWidth = UFix(log2Up(networkAray)) 
  val numCpuConnPerNode = 1
  val numPortsPerNode = (numNeighborsPerDim * numDim) + numCpuConnPerNode
  val inputBufferDepth : Int = 4  //This should be power of two and at least 4
  val flitCntrlWidth : Int = 2
  val flitDataWidth : Int = (32/LINQAccConfig.networkSerializationFactor) + 2 
  //val flitDataWidth : Int = 34 
  //val flitDataWidth : Int = 18 
  //val flitDataWidth : Int = 10 
}

trait NOCUtil {
 // def dimAddr(id:UFix, dim:UFix) : UFix = (id>>(dim*routerConfig.dimAddrWidth))&((UFix(1)<<routerConfig.dimAddrWidth)-UFix(1))
  //def xDimAddr(id: UFix) : UFix = dimAddr(id, UFix(0))
  def xDimAddr(id: UFix) : UFix = (id)&((UFix(1)<<routerConfig.dimAddrWidth)-UFix(1))
  //def yDimAddr(id: UFix) : UFix = dimAddr(id, UFix(1))
  def yDimAddr(id: UFix) : UFix = (id>>routerConfig.dimAddrWidth) &
    ((UFix(1)<<routerConfig.dimAddrWidth)-UFix(1))
  def routeXY(id: UFix, dest: UFix) : UFix = {
    MuxCase(constants.cpu, Array(
                   ((yDimAddr(dest)===yDimAddr(id)) && (xDimAddr(dest)<xDimAddr(id)), 
                     constants.west),
                   ((yDimAddr(dest)===yDimAddr(id)) && (xDimAddr(dest)>xDimAddr(id)), 
                     constants.east),
                   (yDimAddr(dest) < yDimAddr(id), constants.south),
                   (yDimAddr(dest) > yDimAddr(id), constants.north)
                 )
    )
  }
  def idToXYBits(id: Int, aray: Int):UFix = 
    UFix((((id/aray) << routerConfig.dimAddrWidth.litValue().intValue()) + (id % aray)), 
      width = routerConfig.dimAddrWidth.litValue().intValue()*2) 
}

class RFifoIO[T <: Data] () (data: => T) extends Bundle {
  val enq = (new FIFOIO()) {data}.flip
  val deq = (new FIFOIO()) {data}
}


class RFifo[T <: Data] (depth: Int) (t: => T) extends Component {
  val io = (new RFifoIO ()) {t};
  val enqPtr      = Reg(resetVal = UFix(0, width = log2Up(depth)))
  val deqPtr      = Reg(resetVal = UFix(0, width = log2Up(depth)))
  val isFull      = Reg(resetVal = Bool(false))
  val isAlmostFull      = Reg(resetVal = Bool(false))
  val previousEnqReady = Reg(resetVal = Bool(true))
  //Use this for signel cycle lag for ready
  //val doEnq       = previousEnqReady && io.enq.valid
  //Otherwise use this
  val doEnq       = io.enq.ready && io.enq.valid
  val doDeq       = io.deq.ready && io.deq.valid
  val isEmpty     = !isFull && (enqPtr === deqPtr)
  val deqPtrInc  = deqPtr + UFix(1)
  val enqPtrInc  = enqPtr + UFix(1)
  val enqPtrIncInc  = enqPtrInc + UFix(1)
  val isFullNext =
    Mux(doEnq && ~doDeq && (enqPtrInc === deqPtr),
        Bool(true),
        Mux(doDeq && isFull, Bool(false), isFull))
  val isAlmostFullNext =
    Mux(doEnq && ~doDeq && (enqPtrIncInc === deqPtr),
        Bool(true),
        Mux(doDeq && isAlmostFull, Bool(false), isAlmostFull))
  previousEnqReady := io.enq.ready
  enqPtr := Mux(doEnq, enqPtrInc, enqPtr)
  deqPtr := Mux(doDeq, deqPtrInc, deqPtr)
  isAlmostFull := isAlmostFullNext
  isFull := isFullNext
  val ram = Mem(depth) {t}
  when (doEnq) {
    ram(enqPtr) := io.enq.bits
  }
  //Use this for signel cycle lag for ready
  //io.enq.ready := !isAlmostFull && !isFullNext
  //Otherwise use this
  io.enq.ready := !isFull
  io.deq.valid := !isEmpty
  io.deq.bits := ram(deqPtr)
}

class InPortFifo extends Component {
  val io = ((new RFifoIO )) {new FlitIO()}
  val fifo = (new RFifo(routerConfig.inputBufferDepth)) {Bits(width = routerConfig.flitDataWidth+2)}
  fifo.io.enq.valid := io.enq.valid 
  fifo.io.deq.valid <> io.deq.valid 
  fifo.io.enq.ready <> io.enq.ready 
  fifo.io.deq.ready <> io.deq.ready 
  fifo.io.enq.bits := io.enq.bits.head ## io.enq.bits.tail ## io.enq.bits.data

  io.deq.bits.data := fifo.io.deq.bits(routerConfig.flitDataWidth-1, 0)  
  io.deq.bits.tail := (fifo.io.deq.bits(routerConfig.flitDataWidth) === UFix(1))
  io.deq.bits.head := (fifo.io.deq.bits(routerConfig.flitDataWidth+1) === UFix(1))
}

class FlitIO extends Bundle with NOCUtil{
  val head = Bool()
  val tail = Bool()
//  val head = Bits(1)
//  val tail = Bits(1)
  val data = Bits(width = routerConfig.flitDataWidth)
}

class InputPort (num_of_ports: Int, id : UFix) extends Component with NOCUtil {
  val waiting :: routing :: Nil = Enum(2){UFix()}
  val state = Reg(resetVal = UFix(0, width = 1))
  val latchedDest = Reg(resetVal = UFix(0, width = log2Up(routerConfig.numPortsPerNode)))
  val io = new Bundle {
    val routerInPortSide = (new FIFOIO()) {new FlitIO()}.flip
    val toOutPorts = (new FIFOIO()) {new FlitIO()}
    val outPort = UFix(OUTPUT)
  }
  val fsFactor = KmeansTestConfig.networkFSF 
  val count = Reg(resetVal=UFix(0,2))
  //val linkCount = Reg(resetVal=UFix(0,64))

/*
  when (io.routerInPortSide.valid && io.routerInPortSide.ready) {
    linkCount := linkCount + UFix(1)
  }
*/

  count := count + UFix(1)

  //val fifo = (new RFifo(routerConfig.inputBufferDepth)) {new FlitIO()}
  val fifo = new InPortFifo() 

  fifo.io.enq.valid := io.routerInPortSide.valid && count < UFix(fsFactor)
  fifo.io.enq.bits <> io.routerInPortSide.bits
  io.routerInPortSide.ready := count < UFix(fsFactor) && fifo.io.enq.ready
  //io.routerInPortSide <> fifo.io.enq
  io.toOutPorts <> fifo.io.deq
  val routeResult = routeXY(id, fifo.io.deq.bits.data)
  io.outPort := Mux(state, latchedDest, routeResult)
  when (state === waiting && fifo.io.deq.bits.head && fifo.io.deq.valid) {
    when (!fifo.io.deq.bits.tail) {
      state := routing
      latchedDest := routeResult
    } .otherwise {
      state := waiting  
    }
  }
  //Bug point
  //when (state === routing && fifo.io.deq.bits.tail) {
  when (state === routing && fifo.io.deq.bits.tail && 
         fifo.io.deq.valid && io.toOutPorts.ready) {
    state := waiting
  }
}

class OutputPort (num_of_ports: Int) extends Component with NOCUtil {
  val waiting :: routing :: Nil = Enum(2){UFix()}
  val state = Reg(resetVal = UFix(0, width = 1))
  val latchedInport = Reg(resetVal = UFix(0, width = log2Up(routerConfig.numPortsPerNode)))
  val io = new Bundle {
    val routerOutPortSide = (new FIFOIO()) {new FlitIO()}
    val fromInPorts = Vec(num_of_ports) {(new FIFOIO()) {new FlitIO()}}.flip()
  }
  val arb = (new RRArbiter(num_of_ports)) {new FlitIO()}

  when (state === waiting && arb.io.out.valid && io.routerOutPortSide. ready && 
         arb.io.out.bits.head) {
    when (!arb.io.out.bits.tail) {
      state := routing
      latchedInport := arb.io.chosen.toUFix()
    } .otherwise {
      state := waiting
    }
  } 

  when (state === routing && arb.io.out.bits.tail && 
         arb.io.out.valid && io.routerOutPortSide.ready) {
    state := waiting 
  }

  val bidders = Range(0, num_of_ports).map(i => 
                  (io.fromInPorts(i).valid && (state === waiting) ||
                  (io.fromInPorts(i).valid && (state === routing) && 
                    latchedInport === UFix(i))))
    
  //We are assuming that only request to this port has valid == 1
  
  for (i <- 0 until num_of_ports) {
    arb.io.in(i).bits <> io.fromInPorts(i).bits
 //   io.fromInPorts(i).ready := arb.io.in(i).ready 
 //   arb.io.in(i).valid := io.fromInPorts(i).valid 
    io.fromInPorts(i).ready := arb.io.in(i).ready && bidders(i) 
    arb.io.in(i).valid := bidders(i) 
  }
  io.routerOutPortSide <> arb.io.out
}

class MyRouter (num_of_ports: Int, id: UFix) extends Component with NOCUtil {
  val io = new Bundle {
    val ins = Vec(num_of_ports) {(new FIFOIO()) {new FlitIO()}}.flip
    val outs = Vec(num_of_ports) {(new FIFOIO()) {new FlitIO()}}
    val routeResult = Vec(num_of_ports) {UFix(OUTPUT)}
    //val grantsOuts = Vec(num_of_ports) {Bool(OUTPUT)}
    //val inPortsValidOuts = Vec(num_of_ports) {Bool(OUTPUT)}
  }
  val inPorts = Range(0, num_of_ports).map(i => new InputPort(num_of_ports, id))
  val outPorts = Range(0, num_of_ports).map(i => new OutputPort(num_of_ports))
  //io.grantsOuts <> grants
  for (i <- 0 until num_of_ports) {
    inPorts(i).io.routerInPortSide <> io.ins(i)
    outPorts(i).io.routerOutPortSide <> io.outs(i)
    io.routeResult(i) <> inPorts(i).io.outPort
    //io.inPortsValidOuts(i) := inPorts(i).io.toOutPorts.valid 
  }
  //for each output 
    //for each input
  for (i <- 0 until num_of_ports) {
    for (j <- 0 until num_of_ports) {
      outPorts(i).io.fromInPorts(j).bits <> inPorts(j).io.toOutPorts.bits
      val request = inPorts(j).io.toOutPorts.valid && (inPorts(j).io.outPort === UFix(i))
      outPorts(i).io.fromInPorts(j).valid := request
    }
  }
  //for each input 
    //for each output 
  for (i <- 0 until num_of_ports) {
    var grants = Range(0, num_of_ports).map(i => Bool())
    for (j <- 0 until num_of_ports) {
      val request = inPorts(i).io.toOutPorts.valid && (inPorts(i).io.outPort === UFix(j))
      grants(j) := (outPorts(j).io.fromInPorts(i).ready && request)
    }
    inPorts(i).io.toOutPorts.ready := grants.reduceLeft(_||_) 
  }

}

trait NOCTestUtil extends NOCUtil {
}

class MyRouterTests(c: MyRouter) extends Tester(c, Array(c.io, c.inPorts(0).fifo.io)) with NOCTestUtil {
  defTests{
    val list::roundRobin::random::Nil = Enum(3) {UFix()}
    val portSelection = list 
    val svars = new HashMap[Node, Node]()
    val ovars = new HashMap[Node, Node]()

    var sentPackets = Array(0, 0, 0, 0, 0)
    var sentWholePackets = Array(0, 0, 0, 0, 0)
    var sentFlitsThisPacket = Array(0, 0, 0, 0, 0)
    var sentFlits = Array(0, 0, 0, 0, 0)
    var sentPacketsTotal = 0
    var sentWholePacketsTotal = 0
    var sentFlitsTotal = 0
    var toReceivePackets = Array(0, 0, 0, 0, 0)
    var toReceiveFlits = Array(0, 0, 0, 0, 0)
    var receivedPackets = Array(0, 0, 0, 0, 0)
    var receivedFlits = Array(0, 0, 0, 0, 0)
    var pIndex = Array(0, 1, 2, 3, 4) //A level of indirection to have independent
                                      //indexes for each port 

    var pHInc = Array(0, 0, 0, 0, 0) //This is how we postpone our counters 
    var pTInc = Array(0, 0, 0, 0, 0) //update to next cycle. We use these commands
    var fInc = Array(0, 0, 0, 0, 0)  //because we will know the output only in next cycle.
    var receivedPacketsTotal = 0
    var receivedFlitsTotal = 0
    
    var speculativeSentPacketsTotal = 0 //This is incremented without knowing
                                        //if the ready is one cause we see the
                                        //ready in next cycle. 
    var time = 0
    var allPassed = true


    def dimAddrFunctional(id:Int, dim:Int) : Int = 
      (id>>dim*routerConfig.dimAddrWidth.litValue().intValue())&
        ((1<<routerConfig.dimAddrWidth.litValue().intValue())-1)
    def xDimAddrFunctional(id: Int) : Int = dimAddrFunctional(id, 0)
    def yDimAddrFunctional(id: Int) : Int = dimAddrFunctional(id, 1)

    def routeXYFunctional(id: Int, dest: Int) : Int = {
//      if (yDimAddrFunctional(dest) < yDimAddrFunctional(id)) {
//        constants.south.litValue().intValue() 
//      } else if (yDimAddrFunctional(dest) > yDimAddrFunctional(id)) {
//        constants.north.litValue().intValue() 
//      } else if (yDimAddrFunctional(dest) == yDimAddrFunctional(id) && 
//                  xDimAddrFunctional(dest) < xDimAddrFunctional(id)) {
//        constants.west.litValue().intValue() 
//      } else if (yDimAddrFunctional(dest) == yDimAddrFunctional(id) && 
//                  xDimAddrFunctional(dest) > xDimAddrFunctional(id)) {
//        constants.east.litValue().intValue() 
//      } else {
//        constants.cpu.litValue().intValue()
//      }
      routeXY(idToXYBits(id, routerConfig.networkAray), 
        idToXYBits(dest, routerConfig.networkAray)).litValue().intValue() 
    }

    def justSendValid() {
      for (i <- 0 until routerConfig.numPortsPerNode) {
        svars(c.io.ins(i).valid) = Bool(true)
      }
    }

    def sendNoFlit() {
      for (i <- 0 until routerConfig.numPortsPerNode) {
        svars(c.io.ins(i).valid) = Bool(false)
      }
    }

    def pSize(port: Int): Int  = {
      routerTestConfig.packetSizes(pIndex(port) % routerTestConfig.packetSizes.size)
    }

    def pDest(port: Int): Int = {
      routerTestConfig.packetDests(pIndex(port) % routerTestConfig.packetDests.size)
    }
    
    def pIndexInc(port: Int) = {
      pIndex(port) = (pIndex(port) + 1) % routerConfig.numPortsPerNode 
    }
    
    def sendFlitsofCycle(newPacket: Bool) {
      sendNoFlit()
      for (i <- 0 until routerConfig.numPortsPerNode) {
        if (routerTestConfig.sourcePorts(i) == 1) {
          if (ovars(c.io.ins(i).ready).litValue() == 1) {
            if (pHInc(i) == 1) {
              sentPackets(i) += 1
              sentPacketsTotal +=1
            }
            if (pTInc(i) == 1) {
              toReceivePackets(routeXYFunctional(routerTestConfig.routerId, 
                pDest(i)).litValue().intValue()) += 1 
              toReceiveFlits(routeXYFunctional(routerTestConfig.routerId, 
                pDest(i)).litValue().intValue()) += sentFlitsThisPacket(i) + 1 
              sentFlitsThisPacket(i) = 0
              sentFlits(i) += 1
              sentFlitsTotal += 1
              sentWholePackets(i) += 1
              pIndexInc(i)
              sentWholePacketsTotal += 1
            } else if (fInc(i) == 1) {
              sentFlitsThisPacket(i) += 1
              sentFlits(i) += 1
              sentFlitsTotal += 1
            }
          } else {
            println("REPORT:" + time + " Backpressure: on port " + i)
          }
        }
      }
      speculativeSentPacketsTotal = sentPacketsTotal
      println("time is" + time + "speculative sent packets" + 
        speculativeSentPacketsTotal + 
        "sent flits this packet ")
      for (i <- 0 until routerConfig.numPortsPerNode) {
        pHInc(i) = 0
        pTInc(i) = 0
        fInc(i) = 0 
        println("time is" + time + "speculative sent packets" + 
          speculativeSentPacketsTotal + 
          "sent flits this packet " + sentFlitsThisPacket(i))
       if (routerTestConfig.sourcePorts(i) == 1) {
          if ((speculativeSentPacketsTotal < routerTestConfig.toSendPackets && 
             (newPacket.litValue() == 1)) || (sentFlitsThisPacket(i) != 0)) {
            sendFlit(i)
          }
        }
      }
    }

    def sendFlit(port: Int) {
        print("REPORT:" + time + " Flit: Sending from port " + port)
        svars(c.io.ins(port).valid) = Bool(true)
        if (sentFlitsThisPacket(port) == 0) {
          print(" head is 1 id is " + routerTestConfig.routerId.litValue() + 
            " dest is " + pDest(port))
          println(" Size is " + pSize(port) + 
            " Outport is " + routeXYFunctional(routerTestConfig.routerId, pDest(port)))
          svars(c.io.ins(port).bits.head) = Bool(true) 
          svars(c.io.ins(port).bits.data) = 
            idToXYBits(pDest(port), routerConfig.networkAray)
          pHInc(port) = 1
          speculativeSentPacketsTotal += 1
        } else {
          svars(c.io.ins(port).bits.head) = Bool(false) 
          svars(c.io.ins(port).bits.data) = sentFlitsThisPacket(port) 
          print(" head is 0 data is " + sentFlitsThisPacket(port))
        }
        if (sentFlitsThisPacket(port) == pSize(port)-1) {
          println(" tail is 1")
          svars(c.io.ins(port).bits.tail) = Bool(true) 
          pTInc(port) = 1
          fInc(port) = 1 //This should not have any effect
        } else {
          println()
          svars(c.io.ins(port).bits.tail) = Bool(false) 
          fInc(port) = 1
        }
    }

    def receiveFlits() {
      println("time is " + time)
      for (i <- 0 until routerConfig.numPortsPerNode) {
        if ((time % routerTestConfig.backPressurePeriod) == 0) {
         svars(c.io.outs(i).ready) = Bool(true)
        } else {
         svars(c.io.outs(i).ready) = Bool(false)
        } 
        if (ovars(c.io.outs(i).valid).litValue() == 1 && 
             (((time - 1) % routerTestConfig.backPressurePeriod) == 0)) {
          println("REPORT:" + time + " Flit: Received from port " + i + " data is " + 
            ovars(c.io.outs(i).bits.data).litValue() + " head is " +  
            ovars(c.io.outs(i).bits.head).litValue() + " tail is " + 
            ovars(c.io.outs(i).bits.tail).litValue())
          receivedFlits(i) += 1
          receivedFlitsTotal += 1
          if (ovars(c.io.outs(i).bits.tail).litValue() == 1) {
            receivedPackets(i) += 1
            receivedPacketsTotal += 1
          }
        } 
      }
    }

    def report() {
      println("REPORT: Elapsed time is " + time)
      println("REPORT: Total number of sent packets are " + sentPacketsTotal +
       " sent flits are " + sentFlitsTotal) 

      println("REPORT: Total number of received packets are " + receivedPacketsTotal +
       " received flits are " + receivedFlitsTotal) 

      print("REPORT: Total number of sent port:packets:flits \n")
      for (i <- 0 until routerConfig.numPortsPerNode) {
        print(i + ":" + sentPackets(i) + ":" + sentFlits(i) + " ")
      }
      println()
      print("REPORT: Total number of received port:packets:flits \n")
      for (i <- 0 until routerConfig.numPortsPerNode) {
        print(i + ":" + receivedPackets(i) + ":" + receivedFlits(i) + " ")
      }
      println()
      for (i <- 0 until routerConfig.numPortsPerNode) {
        if (toReceivePackets(i) != receivedPackets(i)) {
          println("Error: in port " + i + " received packets are " + receivedPackets(i) + 
           " supposed to be " + toReceivePackets(i))
           allPassed = false 
        }
        if (toReceiveFlits(i) != receivedFlits(i)) {
          println("Error: in port " + i + " received flits are " + receivedFlits(i) + 
           " supposed to be " + toReceiveFlits(i))
           allPassed = false 
        }

      }
      if (allPassed == true)
        println("REPORT: PASSED")
      else 
        println("REPORT: FAILED")
    }

//    println ("XXXX")
    val id = UFix(0)
    val dest = UFix(1)
    println ("id is " + id + " dest is " + dest)  
    println ("yDimAddr(id) is " + yDimAddr(id).litValue())
    println ("yDimAddr(dest) is " + yDimAddr(dest).litValue())
    println ("xDimAddr(id) is " + xDimAddr(id).litValue())
    println ("xDimAddr(dest) is " + xDimAddr(dest).litValue())
    println ("condition is " + ((yDimAddr(dest) === yDimAddr(id)) && 
                (xDimAddr(dest) < xDimAddr(id))).litValue()) 
    println ("condition_1 is " + (yDimAddr(dest) === yDimAddr(id)).litValue()) 
    println ("condition_2 is " + (xDimAddr(dest) < xDimAddr(id)).litValue()) 

    var rr = UFix(0)
    when (yDimAddr(dest) < yDimAddr(id)) {
      println("rr is south")
      rr = constants.south
    } .elsewhen (yDimAddr(dest) > yDimAddr(id)) {
      println("rr is north")
      rr = constants.north 
    } .elsewhen ((yDimAddr(dest) === yDimAddr(id)) && 
                (xDimAddr(dest) < xDimAddr(id))) {
      println("rr is west")
      rr = constants.west 
    } .elsewhen ((yDimAddr(dest) === yDimAddr(id)) && 
                (xDimAddr(dest) > xDimAddr(id))) {
      println("rr is east")
      rr = constants.east 
    } .otherwise {
      println("rr is cpu")
      rr = constants.cpu
    }
    println("rr is " + rr.litValue())
    for (time <- 0 until 5) {
      sendNoFlit()
      step(svars, ovars, false)
      receiveFlits() 
    }

    while((time < 100000 && 
      (sentFlitsTotal == 0 || sentFlitsTotal > receivedFlitsTotal ||
      sentWholePacketsTotal < routerTestConfig.toSendPackets))|| time < 20) {
      svars.clear()
//      justSendValid()

      if (sentPacketsTotal < routerTestConfig.toSendPackets)
        sendFlitsofCycle(newPacket = Bool(true))
      else if (sentWholePacketsTotal < routerTestConfig.toSendPackets)
        sendFlitsofCycle(newPacket = Bool(false))
      else  
        sendNoFlit()
      receiveFlits() 
      time+=1
      step(svars, ovars)
    }
    report()
    allPassed
  }
}
