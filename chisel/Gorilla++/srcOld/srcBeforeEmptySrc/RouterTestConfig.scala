package Tutorial
import Chisel._
import Node._;
import Literal._;

object routerTestConfig {
  //val valid = true
  val valid = false 
  val networkAray = 3 
  val sourcePorts = List(0,0,0,0,1)
  val toSendPackets = 1 
  val routerId = 0
  val packetSizes = List(2,2,1,4,2)
  val packetDests = List(2,1,1,2,3)
//    val packetDests = List(6,10,1,5,7)
//  val packetDests = List(7,10,7,2)
//  val packetDests = List(7,7,7,7)
//  val packetSizes = List(2,2,2,2,2)
  val backPressurePeriod = 1
}
