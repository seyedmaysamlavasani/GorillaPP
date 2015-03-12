package Tutorial

import Chisel._
import Node._
import Literal._
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer

class Top extends Component with include {
  val io = new gInOutBundle (() => new KEngineIO_t , () => new KEngineIO_t)

  val ke =  MTEngine("KEngine.c", #NUM_OF_THREADS)
  val kd =  Engine("KDistribute.c")
  val kr =  Engine("KReduce.c")
  val sub1 = FPDPAdder() 
  val sub2 = FPDPAdder()
  val sub3 = FPDPAdder()
  val mul1 = FPDPMultiplier() 
  val mul2 = FPDPMultiplier()
  val mul3 = FPDPMultiplier()
  val add1 = FPDPAdder()
  val add2 = FPDPAdder()
  val sqrt = FPDPSqrt() 
  val dFUab = ArrayBuffer((sub1, "sub1"), (sub2, "sub2"), (sub3, "sub3"), (mul1, "mul1"), (mul2, "mul2"), (mul3, "mul3"), (add1, "add1"), (add2, "add2"), (sqrt, "sqrt"))  

  var dFU = Offload(PipeEngine("distanceFU.c"), dFUab)
  
  if (#PIPED_DFU) {
    dFU = Offload(PipeEngine("distanceFU.c"), dFUab)
  } else {
    dFU = Offload(MTEngine("distanceFU.c", #NUM_OF_DFU_THREADS), dFUab)
  }
  val add = FPDPAdder() 
  val pa = spMem(height = 1000, width = 192)
  val cm = spMem(height = 1000, width = 192)
  val ab11 = ArrayBuffer((dFU, "distanceFU"), (cm, "centeroidMem"), (pa, "partialAccumulatorMem"), (add, "add"))
  val ab12 = ArrayBuffer((cm, "centeroidMem"), (pa, "partialAccumulatorMem"), (add, "add"))
  val kePlus = Offload(ke, ab11)
  val ab2 = ArrayBuffer((pa, "partialAccumulatorMem"), (add, "add"))
  val krPlus = Offload(kr, ab2)
//  val result = Chain(kd, Replicate(kePlus, #NUM_OF_ENGINES), krPlus)   
  val result = Chain(kd, Replicate(kePlus, #NUM_OF_ENGINES))   
}
