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
import util.Random
import scala.collection.mutable.Queue

class MyTwoDMeshTests(c: MyTwoDMesh) extends Tester(c, Array(c.io)) with NOCTestUtil {
  defTests{
    val svars = new HashMap[Node, Node]()
    val ovars = new HashMap[Node, Node]()
    val numOfTaps = meshTestConfig.aray*meshTestConfig.aray 
    var sentPackets = new Array[Int](numOfTaps)
    var sentWholePackets = new Array[Int](numOfTaps)
    var sentFlitsThisPacket = new Array[Int](numOfTaps)
    var sentFlits = new Array[Int](numOfTaps)
    var sentPacketsTotal = 0
    var sentWholePacketsTotal = 0
    var sentFlitsTotal = 0
    var toReceivePackets = new Array[Int](numOfTaps)  
    var toReceiveFlits = new Array[Int](numOfTaps) 
    var receivedPackets = new Array[Int](numOfTaps) 
    var receivedFlits = new Array[Int](numOfTaps)  
    var nextFlitIsTime = new Array[Int](numOfTaps)
    var queuedFlits = new Array[Int](numOfTaps)
    var queues = new Array[Queue[Int]](numOfTaps)
    for (i <- 0 until numOfTaps) {
      queues(i) =  new Queue[Int]
    }
    //pIndex creates a level of indirection to have independent
    //indexes for each port 
    var pIndex = Array(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15) 
    //This is how we postpone our counters 
    //update to next cycle. We use these commands
    //because we will know the output only in next cycle.
    var pHInc = new Array[Int](numOfTaps) 
    var pTInc = new Array[Int](numOfTaps) 
    var fInc = new Array[Int](numOfTaps) 
    var pDests = new Array[Int](numOfTaps)

    var receivedPacketsTotal = 0
    var receivedFlitsTotal = 0
    
    var speculativeSentPacketsTotal = 0 //This is incremented without knowing
                                        //if the ready is one cause we see the
                                        //ready in next cycle. 
    var queuedFlitsTotal = 0
    var time = 0
    var allPassed = true

    val rndsIR = Range(0, numOfTaps).map(i => new Random())
    val rndsDest = Range(0, numOfTaps).map(i => new Random())

    var totalFlitDelay : Double = 0
    var totalTimeFlits = 0
    var maxQueuedFlits = 0

    for (i <- 0 until numOfTaps) {
      queuedFlits(i) = 0
      sentPackets(i) = 0
      sentWholePackets(i) = 0     
      sentFlitsThisPacket(i) = 0 
      sentFlits(i) = 0
      toReceivePackets(i) = 0
      toReceiveFlits(i) = 0
      receivedPackets(i) = 0
      receivedFlits(i) = 0
      nextFlitIsTime(i) = 0
      queuedFlits(i) = 0   
    }

    def dprint(s:String) {
      if ((meshTestConfig.debug == 1) || (time%1000 == 0))
        print(s)
    }

    def dprintln(s:String) {
      if ((meshTestConfig.debug == 1) || (time%1000 == 0))
        println(s)
    }

    def justSendValid() {
      for (i <- 0 until numOfTaps) {
        svars(c.io.tap(i).enq.valid) = Bool(true)
      }
    }

    def sendNoFlit() {
      for (i <- 0 until numOfTaps) {
        svars(c.io.tap(i).enq.valid) = Bool(false)
      }
    }

    def pSize(tId: Int): Int  = {
      meshTestConfig.packetSizes(pIndex(tId % pIndex.size) % 
        meshTestConfig.packetSizes.size)
    }

    def pDest(tId: Int): Int = {
      val addrWidth = routerConfig.dimAddrWidth.litValue().intValue() * 2
      if (meshTestConfig.trafficPattern == constants.list) { 
        meshTestConfig.packetDests(pIndex(tId % pIndex.size) % 
          meshTestConfig.packetDests.size)
      } else if (meshTestConfig.trafficPattern == constants.unifiedRandom) {
        rndsDest(tId).nextInt(numOfTaps)
      } else if (meshTestConfig.trafficPattern == constants.complement) {
        (UFix(tId) ^ ((UFix(1) << (routerConfig.dimAddrWidth << UFix(1))) 
          - UFix(1))).litValue().intValue() 
      } else if (meshTestConfig.trafficPattern == constants.reverse) {
        val result = Range(0, addrWidth).map(i => UFix(tId)(i)).reduceLeft(_ ## _)
        result.litValue().intValue()
      } else if (meshTestConfig.trafficPattern == constants.rotate) {
        val result = Range(addrWidth-1, -1, -1).map(i => 
          UFix(tId)((i+1) % addrWidth)).reduceRight(_ ## _)
        result.litValue().intValue()
      } else if (meshTestConfig.trafficPattern == constants.shuffle) {
        val result = Range(addrWidth-1, -1, -1).map(i => 
          UFix(tId)((addrWidth+i-1) % addrWidth)).reduceRight(_ ## _)
        result.litValue().intValue()
      } else if (meshTestConfig.trafficPattern == constants.transpose) {
        val result = Range(addrWidth-1, -1, -1).map(i => 
          UFix(tId)((i+(addrWidth/2)) % addrWidth)).reduceRight(_ ## _)
        result.litValue().intValue()
      } else if (meshTestConfig.trafficPattern == constants.neighbor) {  
        val d0 = (tId + 1) % routerConfig.networkAray 
        val d1 = ((tId / routerConfig.networkAray) + 1) % routerConfig.networkAray 
        d0 + (d1 * routerConfig.networkAray)
      } else if (meshTestConfig.trafficPattern == constants.tornado) {
        if (routerConfig.networkAray % 2 == 1) {
          val d0 = (tId + (routerConfig.networkAray/2)) % routerConfig.networkAray
          val d1 = ((tId/routerConfig.networkAray) + 
            (routerConfig.networkAray/2)) % routerConfig.networkAray
          d0 + (d1 * routerConfig.networkAray)
        } else {
          val d0 = (tId + (routerConfig.networkAray/2) - 1) % routerConfig.networkAray
          val d1 = ((tId/routerConfig.networkAray) + 
            (routerConfig.networkAray/2) - 1) % routerConfig.networkAray
          d0 + (d1 * routerConfig.networkAray)
        }
      } else {
        1
      }
    }
    
    def pIndexInc(tId: Int) = {
      pIndex(tId % pIndex.size) = (pIndex(tId % pIndex.size) + 1) % numOfTaps 
    }
    
    def sendFlit(tId: Int) {
        dprint("REPORT:" + time + " Flit: Sending from tap " + tId)
        svars(c.io.tap(tId).enq.valid) = Bool(true)
        if (sentFlitsThisPacket(tId) == 0) {
          val dest = pDest(tId) 
          pDests(tId) = dest
          dprint(" head is 1 id is " + tId + 
            " dest is " + dest + " Size is " + pSize(tId))
          svars(c.io.tap(tId).enq.bits.head) = Bool(true) 
          svars(c.io.tap(tId).enq.bits.data) = 
            idToXYBits(dest, routerConfig.networkAray)
          dprint("converted dest is " + 
            idToXYBits(dest, routerConfig.networkAray).litValue().intValue())
          pHInc(tId) = 1
          speculativeSentPacketsTotal += 1
        } else {
          svars(c.io.tap(tId).enq.bits.head) = Bool(false) 
          if (sentFlitsThisPacket(tId) == 1) {
            svars(c.io.tap(tId).enq.bits.data) = queues(tId).front 
            //svars(c.io.tap(tId).enq.bits.data) = time 
            dprint(" sending time stamp " + time)
          } else {
            svars(c.io.tap(tId).enq.bits.data) = sentFlitsThisPacket(tId) 
          }
          dprint(" head is 0 data is " + sentFlitsThisPacket(tId))
        }
        if (sentFlitsThisPacket(tId) == pSize(tId)-1) {
          dprintln(" tail is 1")
          svars(c.io.tap(tId).enq.bits.tail) = Bool(true) 
          pTInc(tId) = 1
          fInc(tId) = 1 //This should not have any effect
        } else {
          dprintln("")
          svars(c.io.tap(tId).enq.bits.tail) = Bool(false) 
          fInc(tId) = 1
        }
    }

    var bufferedPackets = 0
    def sendFlitsofCycle(newPacket: Bool) {
      sendNoFlit()
      //Since the outputs we are seeing is from prevous cycle
      //we record the "counters to increments" and update the counters
      //before we start sending.
      //Put it in a simple way, we only know wether the flit
      //was accepted or not in next cycle. So in next cycle 
      //we update the counters and act based on them. 
      //note that we keep the same values of inputs as long as the 
      //counters are the same
      for (i <- 0 until numOfTaps) {
        if (meshTestConfig.activeTaps(i) == 1) {
          if (ovars(c.io.tap(i).enq.ready).litValue() == 1) {
            if ((pHInc(i) == 1) || (pTInc(i) == 1) || (fInc(i) == 1)) {
              if (queuedFlits(i) == 0)
                println("Assertion Failed: queuedFlits is zero and" + 
                  "want to decrement it")
              else { 
                queuedFlits(i) -= 1
                queues(i).dequeue()
              }
            }
            if (pHInc(i) == 1) {
              sentPackets(i) += 1
              sentPacketsTotal +=1
            }
            if (pTInc(i) == 1) {
              toReceivePackets(pDests(i)) += 1 
              toReceiveFlits(pDests(i)) += sentFlitsThisPacket(i) + 1 
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
            dprintln("REPORT:" + time + " Backpressure: on tap " + i)
          }
        }
      }
      speculativeSentPacketsTotal = sentPacketsTotal
      dprintln("time is " + time + " Speculative Sent packets " + 
        speculativeSentPacketsTotal + " Sent whole packets to netowrk " +
        sentWholePacketsTotal)
        
      for (i <- 0 until numOfTaps) {
        pHInc(i) = 0
        pTInc(i) = 0
        fInc(i) = 0 
        dprintln("time is " + time + " sent flits to input buffer is " + 
          queuedFlitsTotal + " sentWholePackets is " + sentWholePackets(i) + 
          " sentFlitsThisPacket on tap " + i + " is " +  sentFlitsThisPacket(i)) 
        dprintln("queuedFlits for tap " + i + " is " + queuedFlits(i))
        if (meshTestConfig.activeTaps(i) == 1) {
          if (rndsIR(i).nextInt(100) <= meshTestConfig.injectionRate) {
            queuedFlits(i) += 1
            queues(i).enqueue(time)
            if (maxQueuedFlits < queuedFlits(i) && 
              speculativeSentPacketsTotal < meshTestConfig.toSendPackets) {
              maxQueuedFlits = queuedFlits(i)
              dprintln("max queued flits occured at port " + i + " which is " +
                maxQueuedFlits) 
            }
          }
          if (queuedFlits(i) > 0 && 
             ((speculativeSentPacketsTotal < meshTestConfig.toSendPackets) || 
              (sentFlitsThisPacket(i) != 0)))
            sendFlit(i)
        }
      }
    }


    def receiveFlits() {
      dprintln("time is " + time)
      for (i <- 0 until numOfTaps) {
        if ((time % meshTestConfig.backPressurePeriod) == 0) {
         svars(c.io.tap(i).deq.ready) = Bool(true)
        } else {
         svars(c.io.tap(i).deq.ready) = Bool(false)
        } 
        if (ovars(c.io.tap(i).deq.valid).litValue() == 1 && 
             (((time - 1) % meshTestConfig.backPressurePeriod) == 0)) {
          dprintln("REPORT:" + time + " Flit: Received from tap " + i + " data is " + 
            ovars(c.io.tap(i).deq.bits.data).litValue() + " head is " +  
            ovars(c.io.tap(i).deq.bits.head).litValue() + " tail is " + 
            ovars(c.io.tap(i).deq.bits.tail).litValue())
          receivedFlits(i) += 1
          receivedFlitsTotal += 1
          if (ovars(c.io.tap(i).deq.bits.tail).litValue() == 1) {
            receivedPackets(i) += 1
            receivedPacketsTotal += 1
          }
          if (nextFlitIsTime(i) == 1) {
            dprintln("Receiving time stamp " + 
              ovars(c.io.tap(i).deq.bits.data).litValue().intValue() + " time is " + time)
            totalFlitDelay += 
              time - ovars(c.io.tap(i).deq.bits.data).litValue().intValue()-1
            totalTimeFlits += 1
            nextFlitIsTime(i) = 0
          }
          if (ovars(c.io.tap(i).deq.bits.head).litValue() == 1 &&
              ovars(c.io.tap(i).deq.bits.tail).litValue() != 1) {
            nextFlitIsTime(i) = 1
          } 
        } 
        if (ovars(c.io.debugWest(i).valid).litValue() == 1) {
          dprintln("REPORT: " + time + " intermediate flit: received " +
            "on west of router " + i  + " data is " + 
            ovars(c.io.debugWest(i).bits.data).litValue() + " head is " +  
            ovars(c.io.debugWest(i).bits.head).litValue() + " tail is " + 
            ovars(c.io.debugWest(i).bits.tail).litValue())
        }
        if (ovars(c.io.debugEast(i).valid).litValue() == 1) {
          dprintln("REPORT: " + time + " intermediate flit: received " +
            "on east of router " + i  + " data is " + 
            ovars(c.io.debugEast(i).bits.data).litValue() + " head is " +  
            ovars(c.io.debugEast(i).bits.head).litValue() + " tail is " + 
            ovars(c.io.debugEast(i).bits.tail).litValue())
        }
        if (ovars(c.io.debugNorth(i).valid).litValue() == 1) {
          dprintln("REPORT: " + time + " intermediate flit: received " +
            "on north of router " + i  + " data is " + 
            ovars(c.io.debugNorth(i).bits.data).litValue() + " head is " +  
            ovars(c.io.debugNorth(i).bits.head).litValue() + " tail is " + 
            ovars(c.io.debugNorth(i).bits.tail).litValue())
        }
        if (ovars(c.io.debugSouth(i).valid).litValue() == 1) {
          dprintln("REPORT: " + time + " intermediate flit: received " +
            "on south of router " + i  + " data is " + 
            ovars(c.io.debugSouth(i).bits.data).litValue() + " head is " +  
            ovars(c.io.debugSouth(i).bits.head).litValue() + " tail is " + 
            ovars(c.io.debugSouth(i).bits.tail).litValue())
        }
      }
    }

    def report() {
      println("REPORT: Elapsed time is " + time)
      print("REPORT: Average delay is\t" + (totalFlitDelay)/totalTimeFlits)
      println(" Max Send Buffer size is " + maxQueuedFlits) 
      println("REPORT: Total number of sent packets are " + sentPacketsTotal +
       " sent flits are " + sentFlitsTotal) 

      println("REPORT: Total number of received packets are " + receivedPacketsTotal +
       " received flits are " + receivedFlitsTotal) 

      print("REPORT: Total number of sent tap:packets:flits \n")
      for (i <- 0 until numOfTaps) {
        print(i + ":" + sentPackets(i) + ":" + sentFlits(i) + " ")
      }
      println("")
      print("REPORT: Total number of received tap:packets:flits \n")
      for (i <- 0 until numOfTaps) {
        print(i + ":" + receivedPackets(i) + ":" + receivedFlits(i) + " ")
      }
      println("")
      for (i <- 0 until numOfTaps) {
        if (toReceivePackets(i) != receivedPackets(i)) {
          println("Error: in tap " + i + " received packets are " + receivedPackets(i) + 
           " supposed to be " + toReceivePackets(i))
           allPassed = false 
        }
        if (toReceiveFlits(i) != receivedFlits(i)) {
          println("Error: in tap " + i + " received flits are " + receivedFlits(i) + 
           " supposed to be " + toReceiveFlits(i))
           allPassed = false 
        }
      }
      if (allPassed == true)
        println("REPORT: PASSED")
      else 
        println("REPORT: FAILED")
    }

    for (time <- 0 until 5) {
      sendNoFlit()
      step(svars, ovars, false)
      receiveFlits() 
    }

    while(time < 500000 && 
      (sentFlitsTotal == 0 || sentFlitsTotal > receivedFlitsTotal) ||
        sentWholePacketsTotal < meshTestConfig.toSendPackets) {
      svars.clear()
//      justSendValid()
      if (sentPacketsTotal < meshTestConfig.toSendPackets)
        sendFlitsofCycle(newPacket = Bool(true))
      else if (sentWholePacketsTotal < meshTestConfig.toSendPackets)
        sendFlitsofCycle(newPacket = Bool(false))
      else  
        sendNoFlit()
      receiveFlits() 
      time+=1
      step(svars, ovars, false)
      //if ((time % 1000 == 0)) {
      //  report()
      //}
    }
    report()
    allPassed
  }
}
