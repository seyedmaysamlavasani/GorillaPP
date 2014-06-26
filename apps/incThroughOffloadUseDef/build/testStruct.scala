package Tutorial

import Chisel._
import Node._
import Literal._
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer


class testStruct_t extends Bundle { 
var a = UFix(width = 32)
var b = UFix(width = 32)
}
