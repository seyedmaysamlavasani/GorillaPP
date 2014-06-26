package Tutorial

object meshTestConfig {
  val trafficPattern = constants.unifiedRandom
  val injectionRate = 10 
  val aray = KmeansTestConfig.networkAray  
  //  val sourcePorts = List(1,1,1,1,1)
  //  val activeTaps = List(1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1)
  val activeTaps = List(1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1)
  val toSendPackets = 6000 
  val packetSizes = List(2,2,2,2,2,2,2,2,2,2,2)
  //  val packetDests = List(1,0,1,2,3,5,6,10,12,14,9)
  val packetDests = List(3,2,1,0,0,1,2,3)
  //  val packetDests = List(0,0,1,2,3)
  //  val packetSizes = List(2,2,2,2,2)
  //  val packetDests = List(7,10,7,2)
  //  val packetDests = List(7,7,7,7)
  val backPressurePeriod = 1
  val debug = 0
}
