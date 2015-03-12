package Tutorial

import Chisel._
import Node._
import Literal._
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer


class point_t extends Bundle { 
val x = UFix(width = 64)
val y = UFix(width = 64)
val z = UFix(width = 64)
}
class KEngineIO_t extends Bundle { 
val centeroidsFinished = Bool()
val pointsFinished = Bool()
val centeroidIndex = UFix(width = 16)
val point = new point_t
}
class FU_in_t extends Bundle { 
val in1 = UFix(width = 64)
val in2 = UFix(width = 64)
}
class FU_out_t extends Bundle { 
val out = UFix(width = 64)
}
class distanceFU_in_t extends Bundle { 
val in1 = new point_t
val in2 = new point_t
}
class distanceFU_out_t extends Bundle { 
val out = UFix(width = 64)
}
class pointMemInput_t extends Bundle { 
val rw = Bool()
val addr = UFix(width = 16)
val data = new point_t
}
class pointMemOutput_t extends Bundle { 
val data = new point_t
}
