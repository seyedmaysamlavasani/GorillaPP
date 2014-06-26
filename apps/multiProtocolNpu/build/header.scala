package Tutorial

import Chisel._
import Node._
import Literal._
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer


class IPv4Header_t extends Bundle { 
val version = UFix(width = 4)
val hLength = UFix(width = 4)
val tos = UFix(width = 8)
val length = UFix(width = 16)
val identification = UFix(width = 16)
val flagsOffset = UFix(width = 16)
val ttl = UFix(width = 8)
val protocol = UFix(width = 8)
val chksum = UFix(width = 16)
val srcAddr = UFix(width = 32)
val dstAddr = UFix(width = 32)
}
class IPv6Header_t extends Bundle { 
val version = UFix(width = 4)
val hLength = UFix(width = 4)
val tos = UFix(width = 8)
val length = UFix(width = 16)
val identification = UFix(width = 16)
val flagsOffset = UFix(width = 16)
val ttl = UFix(width = 8)
val protocol = UFix(width = 8)
val chksum = UFix(width = 16)
val srcAddr = UFix(width = 128)
val dstAddr = UFix(width = 128)
}
class mpl3Header_t extends Bundle { 
val h1 = UFix(width = 128)
val h2 = UFix(width = 128)
val h3 = UFix(width = 128)
val h4 = UFix(width = 128)
val h5 = UFix(width = 128)
val h6 = UFix(width = 128)
val h7 = UFix(width = 128)
val h8 = UFix(width = 128)
}
class EthernetHeader_t extends Bundle { 
val dstAddr = UFix(width = 48)
val srcAddr = UFix(width = 48)
val l3Type = UFix(width = 8)
val length = UFix(width = 8)
}
class NP_EthIPv4Header_t extends Bundle { 
val l2Protocol = UFix(width = 8)
val outPort = UFix(width = 8)
val eth = new EthernetHeader_t
val ipv4 = new IPv4Header_t
}
class NP_EthMpl3Header_t extends Bundle { 
val l2Protocol = UFix(width = 8)
val outPort = UFix(width = 8)
val eth = new EthernetHeader_t
val l3 = new mpl3Header_t
}
