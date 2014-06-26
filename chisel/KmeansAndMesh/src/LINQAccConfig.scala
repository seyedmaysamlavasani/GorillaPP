package Tutorial 

object LINQAccConfig {
  val goOnReset = true
  val addrSize = 4
  val wordSize = 4
  val wordWidth = 8*wordSize
  val addrWidth = addrSize * 8 
  val BramSizeLog = 256
  val BramWordSize = wordSize
  val cores = KmeansTestConfig.cores 
  val fuDelay = KmeansTestConfig.fuDelay
  //val networkSerializationFactor = KmeansConfig.pointWidth / routerConfig.flitDataWidth 
  val networkSerializationFactor = KmeansTestConfig.networkSF 
//  val networkSerializationFactor =  if ((KmeansConfig.pointWidth + 2)%
//                                     (routerConfig.flitDataWidth + 
//                                      routerConfig.flitCntrlWidth) == 0) {
//    ((KmeansConfig.pointWidth + 2)/ 
//      (routerConfig.flitDataWidth + routerConfig.flitCntrlWidth))  
//  } else {
//    ((KmeansConfig.pointWidth + 2)/ 
//      (routerConfig.flitDataWidth + routerConfig.flitCntrlWidth)) + 1  
//  }
  val maxPSize = 16
}


