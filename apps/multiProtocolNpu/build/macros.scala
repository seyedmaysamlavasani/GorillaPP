package Tutorial

import Chisel._
import Node._
import Literal._
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer


trait include extends GorillaUtil {
 val dummy = 0 

val	lookup_offloaded = Bool(true)//constant macro definition 
val	ETHERNET = (UFix(width = 8)).fromBits(Bits(0, width=(UFix(width = 8)).getWidth) | (UFix(128, width = 32)).toBits)//constant macro definition 
val	IPV4 = (UFix(width = 8)).fromBits(Bits(0, width=(UFix(width = 8)).getWidth) | (UFix(64, width = 32)).toBits)//constant macro definition 
def test(x:UFix) = x+UFix(1, width = 32)//constant macro definition 
val	CONTROL_PLANE = UFix(255, width = 32)//constant macro definition 
val	INVALID_ADDRESS = UFix(255, width = 32)//constant macro definition 
val	LOOKUP_FIRST_LEVEL_SHIFT = UFix(12, width = 32)//constant macro definition 
val	LOOKUP_FIRST_LEVEL_MASK = ((UFix(1, width = 32)<<UFix(20, width = 32))-UFix(1, width = 32))//constant macro definition 
val	LOOKUP_SECOND_LEVEL_SHIFT = UFix(4, width = 32)//constant macro definition 
val	LOOKUP_SECOND_LEVEL_MASK = ((UFix(1, width = 32)<<UFix(8, width = 32))-UFix(1, width = 32))//constant macro definition 
val	LOOKUP_THIRD_LEVEL_SHIFT = UFix(0, width = 32)//constant macro definition 
val	LOOKUP_THIRD_LEVEL_MASK = ((UFix(1, width = 32)<<UFix(4, width = 32))-UFix(1, width = 32))//constant macro definition 
val	LOOKUP_SECOND_LEVEL_BASE = ((UFix(1, width = 32)<<UFix(20, width = 32))<<UFix(2, width = 32))//constant macro definition 
val	LOOKUP_THIRD_LEVEL_BASE = LOOKUP_SECOND_LEVEL_BASE+((UFix(1, width = 32)<<UFix(4, width = 32))<<UFix(4, width = 32))//constant macro definition 
val	LOOKUP_SECOND_LEVEL_CHUNK_SIZE_LOG = UFix(4, width = 32)//constant macro definition 
val	LOOKUP_THIRD_LEVEL_CHUNK_SIZE_LOG = UFix(8, width = 32)//constant macro definition 
}

