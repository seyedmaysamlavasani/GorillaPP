package Tutorial

import Chisel._
import Node._
import Literal._
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer


class testStruct extends Bundle { 
val a = UFix(width = 32)
val b = UFix(width = 32)
}
