package Tutorial

import Chisel._
import Node._
import Literal._
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer


class tag_t extends Bundle { 
val tagValue = UFix(width = 24)
val dirty = Bool()
val valid = Bool()
val tainted = Bool()
val lockId = UFix(width = 16)
}
class memReqCacheTagBank_t extends memReq10_64_t
class memRepCacheTagBank_t extends memRep64_t
class memReqCacheDataBank_t extends memReq10_128_t
class memRepCacheDataBank_t extends memRep128_t
class memReqDram_t extends memReq16_128_t
class memRepDram_t extends memRep128_t
