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



trait StreamUtil {
  val sTCMDCentersArg :: sTCMDPointsArg :: sTCMDCentersResult :: sTCMDGroup :: Nil = Enum(4) {UFix()}
  def mkHeader(command:UFix, count:UFix):UFix = {
    count + (command << UFix(20))
//     count
  }

  def commandPart(header:UFix):UFix = {
    header >> UFix(20)
  }

  def countPart(header:UFix):UFix = {
    header & ((UFix(1)<< UFix(20)) - UFix(1))
  }
}

class DummyMem extends Component {
   val io = new MemIO().flip()
   val count = Reg(resetVal = Bits(0, 32)) 
//   val outputValidReg = Reg(resetVal = Bool(false))
   //val delay = Bits(3, 32)
   val delay = Bits(KmeansTestConfig.memDelay, 32)
   val delayCount = Reg(resetVal = Bits(0,32))

   when (io.req.enable  && delayCount === UFix(0)) {
   //when (io.req.enable && (io.req.rw === UFix(0)) && delayCount === UFix(0)) {
   //when (io.req.enable && (io.req.rw === UFix(0))) {
     count := count + UFix(1)
     delayCount := delay
   } .otherwise {
     when (delayCount > UFix(0)) {
       delayCount := delayCount - UFix(1)
     }
   } 

   when (io.req.enable && io.req.rw === UFix(1)) {
     count := io.req.wData
   }

   //io.reply.rData := count
   io.reply.rData := count
   io.reply.ready := delayCount === UFix(0) 
}



class StreamerAndMem extends Component {
   val io = new Bundle {
     val config = new MemIO().flip()
     val inStream = (new FIFOIO()) {new StreamData}.flip()
     val outStream = (new FIFOIO()) {new StreamData}
     val error = Bits(OUTPUT, 1)
     val done = Bool(OUTPUT)
   }
   val streamer = new Streamer
   streamer.io.config <> io.config
   streamer.io.inStream <> io.inStream
   streamer.io.outStream <> io.outStream
   io.error <> streamer.io.error
   val dummyMem = new DummyMem
   dummyMem.io <> streamer.io.mem
   io.done := streamer.io.done
}



class Streamer() extends Component with StreamUtil{
   val io = new Bundle {
     val mem = new MemIO()
     val config = new MemIO().flip()
     val inStream = (new FIFOIO()) {new StreamData}.flip()
     val outStream = (new FIFOIO()) {new StreamData}
     val error = Bits(OUTPUT, 1)
     val done = Bool(OUTPUT)
   }


  //config registers
   val go = Reg(resetVal=Bool(LINQAccConfig.goOnReset))

   val doneReg = Reg(resetVal=Bool(false))
   val centersBaseAddress = Reg(resetVal=Bits(0, LINQAccConfig.addrWidth))
   val numOfCenters = Reg(resetVal = Bits(KmeansConfig.centers, 32))
   val pointsBaseAddress = Reg(resetVal = Bits(0, LINQAccConfig.addrWidth))
   val numOfPoints = Reg(resetVal = Bits(KmeansConfig.points, 32))
   val numOfIterations = Reg(resetVal = Bits(1, 32))
   val elapsedTime = Reg(resetVal = Bits(0, 64))

   val read::write::Nil=Enum(2) {UFix()}
   //Memory mapped config  snooper
   val MmapConfigRegAddress = Bits("h_1000000")
   val configOutReg = Reg(resetVal=Bits(0,LINQAccConfig.wordWidth))
   
   io.done := doneReg

   when (io.config.req.enable) {
     when ((io.config.req.address === MmapConfigRegAddress) &&
       (io.config.req.rw === write)) {
       go := Bool(true)
       doneReg := Bool(false)
     } .otherwise {
       go := Bool(false)
     }

     when (io.config.req.address === (MmapConfigRegAddress + Bits("h_4")) &&
       (io.config.req.rw === read) && doneReg) {
       configOutReg := Bits(1)
     } .otherwise {
       configOutReg := Bits(0)
     }

     when (io.config.req.address === (MmapConfigRegAddress + Bits("h_8")) &&
       (io.config.req.rw === write)) {
       centersBaseAddress := io.config.req.wData
     }

     when (io.config.req.address === (MmapConfigRegAddress + Bits("h_c")) &&
       (io.config.req.rw === write)) {
       numOfCenters := io.config.req.wData
     }

     when (io.config.req.address === (MmapConfigRegAddress + Bits("h_10")) &&
       (io.config.req.rw === write)) {
       pointsBaseAddress := io.config.req.wData
     }

     when (io.config.req.address === (MmapConfigRegAddress + Bits("h_14")) &&
       (io.config.req.rw === write)) {
       numOfPoints := io.config.req.wData
     }

     when (io.config.req.address === (MmapConfigRegAddress + Bits("h_18")) &&
       (io.config.req.rw === write)) {
       numOfIterations := io.config.req.wData
     }

     when (io.config.req.address === (MmapConfigRegAddress + Bits("h_1c")) &&
       (io.config.req.rw === read)) {
       configOutReg := elapsedTime(31,0)
     }

     when (io.config.req.address === (MmapConfigRegAddress + Bits("h_20")) &&
       (io.config.req.rw === read)) {
       configOutReg := elapsedTime(63,32)
     }
   }

   when (go && !doneReg) {
     elapsedTime := elapsedTime + UFix(1)
   }


    //BRAM definitions

   val xCentersMem = new BRAM(LINQAccConfig.BramWordSize, LINQAccConfig.BramSizeLog)
   val yCentersMem = new BRAM(LINQAccConfig.BramWordSize, LINQAccConfig.BramSizeLog)

   val state = Reg(resetVal=UFix(0,5))

   val waiting::streamOutCentersFromMemHeader::streamOutCentersFromMemReq::streamOutCentersFromMem::streamOutCentersFromMemTrailer::streamOutPointsFromMemHeader::streamOutPointsFromMemReq::streamOutPointsFromMem::streamOutPointsFromMemTrailer::streamOutCentersFromBramHeader::streamOutCentersFromBram::streamOutCentersFromBramTrailer::waitForLambdaCores::streamInAndSaveCentersHeader::streamInAndSaveCenters::streamInAndSaveCentersTrailer::saveCentersToMemReq::saveCentersToMem::Nil = Enum(18) {UFix()}

//0 waiting
//1 streamOutCentersFromMemHeader
//2 streamOutCentersFromMemReq
//3 streamOutCentersFromMem
//4 streamOutCentersFromMemTrailer
//5 streamOutPointsFromMemHeader
//6 streamOutPointsFromMemReq
//7 streamOutPointsFromMem
//8 streamOutPointsFromMemTrailer
//9 streamOutCentersFromBramHeader
//10 streamOutCentersFromBram
//11 streamOutCentersFromBramTrailer
//12 waitForLambdaCores
//13 streamInAndSaveCentersHeader
//14 streamInAndSaveCenters
//15 streamInAndSaveCentersTrailer
//16 saveCentersToMemReq
//17 saveCentersToMem

   val id = Reg(resetVal=UFix(0, 32))
   val centersDataSize = numOfCenters << UFix(1)
   val pointsDataSize = numOfPoints << UFix(1)
   val iteration = Reg(resetVal=UFix(0, 32))
   //State machine
   //0------------waiting------------------------------------------/
   when(state === waiting && go && iteration < numOfIterations) {
     id := UFix(0)
     state := streamOutCentersFromMemHeader
   }


   when (state === streamOutCentersFromMemReq && io.mem.reply.ready) {
     state := streamOutCentersFromMem
   }

   when (state === streamOutPointsFromMemReq && io.mem.reply.ready) {
     state := streamOutPointsFromMem
   }


   when (io.outStream.ready && iteration < numOfIterations) {
     //1------------Stream out Centers from Mem------------------/
     //If memory responds increment the center pointer
     when (state === streamOutCentersFromMemHeader) {
       state := streamOutCentersFromMemReq
     }

     when (state === streamOutCentersFromMem && 
           id != centersDataSize - UFix(1)) {
       id := id + UFix(1)
       state := streamOutCentersFromMemReq
     }

     when (state === streamOutCentersFromMem && 
       id === centersDataSize - UFix(1)) {
       state := streamOutCentersFromMemTrailer
     }

     when (state === streamOutCentersFromMemTrailer) {
       id := UFix(0)
       state := streamOutPointsFromMemHeader
     }

     //2------------Stream out points from Mem------------------/
     //If memory responds increment the points pointer
     when (state === streamOutPointsFromMemHeader) {
       id := UFix(0)
 //      state := streamOutPointsFromMemReq
       state := streamOutPointsFromMem
     }

     when (state === streamOutPointsFromMem && 
           id != pointsDataSize - UFix(1)) {
       state := streamOutPointsFromMem
//       state := streamOutPointsFromMemReq
       id := id + UFix(1)
     }

     when (state === streamOutPointsFromMem && 
       id === pointsDataSize - UFix(1)) {
       state := streamOutPointsFromMemTrailer
     }

     when (state === streamOutPointsFromMemTrailer) {
       id := UFix(0)
       state := waitForLambdaCores
       iteration := iteration + UFix(1)
     }
     //3------------Stream out centers from Bram----------------/
     //if were reading Bram increment the center data pointer
     when (state === streamOutCentersFromBramHeader) {
       id := UFix(0)
       state := streamOutCentersFromBram
     }

     when (state === streamOutCentersFromBram) {
       id := id + UFix(1)
     }

     when (state === streamOutCentersFromBram && (id === centersDataSize - UFix(1))) {
       state := streamOutCentersFromBramTrailer
     }

     when (state === streamOutCentersFromBramTrailer) {
       state := streamOutPointsFromMemHeader
       id := UFix(0)
     }
   }

   val lambdaCoresFinished = io.inStream.valid &&
                             io.inStream.bits.header &&
                             commandPart(io.inStream.bits.data) === sTCMDCentersResult

   when (io.inStream.valid) {
     //4------------Wait for lambda cores---------------------/
     when (state === waitForLambdaCores && lambdaCoresFinished) {
       state := streamInAndSaveCenters
       id := UFix(0)
     }
     //5------------Stream in and save centers----------------/
     //If new stream in component we increment id
     when (state === streamInAndSaveCenters && 
           id != centersDataSize - UFix(1)) { 
       id := id + UFix(1)
     }
     //if done with streaming in go to either next iteration or
     //go to stream out centers to mem
     when (state === streamInAndSaveCenters &&
           id === centersDataSize - UFix(1)) {
       state := streamInAndSaveCentersTrailer
     }

     when (state === streamInAndSaveCentersTrailer) {
       id := UFix(0)
       when (iteration === numOfIterations) {
	    state := saveCentersToMemReq
       } .otherwise {
         state := streamOutCentersFromBramHeader
       }
     }
   }

   //6------------Save centers to Mem----------------/
   when (state === saveCentersToMemReq && io.mem.reply.ready) {
     state := saveCentersToMem
   }
   when (state === saveCentersToMem && id != centersDataSize) {
     state := saveCentersToMemReq
     id := id + UFix(1)
   }
   when (state === saveCentersToMem && id === centersDataSize) {
     state := waiting
     doneReg := Bool(true)
   }


   //Driving stream in signals
   io.inStream.ready := 
     (state === waitForLambdaCores) || 
     (state === streamInAndSaveCentersHeader) ||
     (state === streamInAndSaveCenters) ||
     (state === streamInAndSaveCentersTrailer)

   //Driving snoop port signals
   io.config.reply.ready := Bool(true)
   io.config.reply.rData := configOutReg

   //Driving Bram signals
   xCentersMem.io.req.rw :=
     state === streamInAndSaveCenters && io.inStream.valid && id(0) === Bits(0)
   yCentersMem.io.req.rw :=
     state === streamInAndSaveCenters && io.inStream.valid && id(0) === Bits(1)

   xCentersMem.io.req.address := id >> UFix(1)
   yCentersMem.io.req.address := id >> UFix(1)
   xCentersMem.io.req.enable := Bool(true)
   yCentersMem.io.req.enable := Bool(true)
   xCentersMem.io.req.wData := io.inStream.bits.data
   yCentersMem.io.req.wData := io.inStream.bits.data

   //Driving Stream out signals

   val sendCentersHeader = (state === streamOutCentersFromMemHeader ||
                            state === streamOutCentersFromBramHeader)

   val sendPointsHeader = (state === streamOutPointsFromMemHeader)

   val sendCentersTrailer = (state === streamOutCentersFromMemTrailer ||
                            state === streamOutCentersFromBramTrailer)

   val sendPointsTrailer = (state === streamOutPointsFromMemTrailer)

   io.outStream.valid := sendCentersHeader || sendPointsHeader ||
                         sendCentersTrailer || sendPointsTrailer ||
                         (state === streamOutCentersFromMem) ||
                         (state === streamOutPointsFromMem) ||
                         state === streamOutCentersFromBram

   io.outStream.bits.header := sendCentersHeader || sendPointsHeader

   io.outStream.bits.trailer := sendCentersTrailer || sendPointsTrailer


   val centersHeader = mkHeader(sTCMDCentersArg, numOfCenters)
   val pointsHeader = mkHeader(sTCMDPointsArg, numOfPoints)

   val bramData = Mux(id(0) === Bits(0), xCentersMem.io.reply.rData,
                                         yCentersMem.io.reply.rData)

   io.outStream.bits.data := MuxCase(bramData,
                                     Array((sendCentersHeader, centersHeader),
                                           (sendPointsHeader, pointsHeader),
                                           ((state === streamOutCentersFromMem ||
                                             state === streamOutPointsFromMem), io.mem.reply.rData)))
   //Driving mem signals
   io.mem.req.enable := state === streamOutCentersFromMemReq || 
                        state === streamOutPointsFromMemReq || 
                        state === saveCentersToMemReq
//
//   io.mem.req.enable := Bool(true)
   io.mem.req.rw := saveCentersToMemReq
   io.mem.req.wData := bramData
   io.mem.req.address := Mux(state === streamOutPointsFromMem,
                             pointsBaseAddress+id, centersBaseAddress+id)

   //Sanity checks
   //first stream element in inStream should be header siwth size centers
   //command CentersResult

   io.error := !(state === waitForLambdaCores &&
                   io.inStream.valid &&
                   io.inStream.bits.header &&
                   commandPart(io.inStream.bits.data) === sTCMDCentersResult &&
                     countPart(io.inStream.bits.data) === centersDataSize)
}

//class StreamerTests(c: StreamerAndMem) extends Tester(c, Array(c.io, c.streamer.state, c.dummyMem.io)) { 
class StreamerTests(c: StreamerAndMem) extends 
  Tester(c, Array(c.io, c.streamer.state,  c.streamer.id, c.streamer.numOfCenters)) {
   defTests {
     var allGood = true
     val svars = new HashMap[Node, Node]()
     val ovars = new HashMap[Node, Node]()

     def writeConfig(address:Bits, data:Bits) = {
       svars(c.io.config.req.enable) = Bool(true)
       svars(c.io.config.req.address) = address
       svars(c.io.config.req.rw) = Bits(1)
       svars(c.io.config.req.wData) = Bits(1)
     }

     def wroteConfig():Boolean = (ovars(c.io.config.reply.ready).litValue().intValue() == 1)
     // let it spin for a bit
     svars(c.io.config.req.enable) = Bool(false)
     svars(c.io.outStream.ready) = Bool(false)
     for (time <- 0 until 5) {
       step(svars, ovars, false)
     }
     //command the streamer to go
     writeConfig(Bits("h_1000000"), Bits(1))
     step(svars, ovars, false)
     svars(c.io.outStream.ready) = Bool(true)
     if (!wroteConfig()) {
       println("Error writing the go command")
     }
     var time = 0
     while (ovars(c.io.done).litValue().intValue() == 0 && time < 10000000) {
       step(svars, ovars, true)
       time = time + 1
     }
     allGood
   }
}



