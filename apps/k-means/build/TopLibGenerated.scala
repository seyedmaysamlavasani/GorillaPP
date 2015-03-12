//XX variable version 5
package Tutorial

import Chisel._
import Node._
import Literal._
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer

class Top extends Component with include {
  val io = new gInOutBundle (() => new KEngineIO_t , () => new KEngineIO_t)

  val ke =  MTEngine("KEngine.c", 1)
  val kd =  Engine("KDistribute.c")
  val kr =  Engine("KReduce.c")
  val sub1 = Engine("adder.c")
  val sub2 = Engine("adder.c")
  val sub3 = Engine("adder.c")
  val mul1 = Engine("multiplier.c")
  val mul2 = Engine("multiplier.c")
  val mul3 = Engine("multiplier.c")
  val add1 = Engine("adder.c")
  val add2 = Engine("adder.c")
  val sqrt = Engine("sqrt.c")
  val dFUab = ArrayBuffer((sub1, "sub1"), (sub2, "sub2"), (sub3, "sub3"), (mul1, "mul1"), (mul2, "mul2"), (mul3, "mul3"), (add1, "add1"), (add2, "add2"), (sqrt, "sqrt"))  

  val xx1 = PipeEngine("distanceFU.c")
  var dFU = Offload( xx1 , dFUab)
  
  if (true) {
    val xx2 = PipeEngine("distanceFU.c")
    dFU = Offload( xx2 , dFUab)
  } else {
    val xx3 = MTEngine("distanceFU.c", 1)
    dFU = Offload( xx3 , dFUab)
  }
  val add = Engine("adder.c")
  val pa = spMem(height = 1000, width = 192)
  val cm = spMem(height = 1000, width = 192)
  val ab11 = ArrayBuffer((dFU, "distanceFU"), (cm, "centeroidMem"), (pa, "partialAccumulatorMem"), (add, "add"))
  val ab12 = ArrayBuffer((cm, "centeroidMem"), (pa, "partialAccumulatorMem"), (add, "add"))
  val kePlus = Offload(ke, ab11)
  val ab2 = ArrayBuffer((pa, "partialAccumulatorMem"), (add, "add"))
  val krPlus = Offload(kr, ab2)
val xx4 = Replicate(kePlus, 1)
//  val result = Chain(kd,  xx4 , krPlus)
  val xx5 = Replicate(kePlus, 1)
  val result = Chain(kd,  xx5 )
}
