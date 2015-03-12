package Tutorial

import Chisel._
import Node._
import Literal._
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer


trait include extends GorillaUtil {
 val dummy = 0 

val	NUM_OF_POINTS = UFix(200, width = 32)//constant macro definition 
val	NUM_OF_CENTEROIDS = UFix(5, width = 32)//constant macro definition 
val	NUM_OF_ENGINES = UFix(1, width = 32)//constant macro definition 
val	NUM_OF_THREADS = UFix(1, width = 32)//constant macro definition 
val	NUM_OF_DFU_THREADS = UFix(1, width = 32)//constant macro definition 
val	SHARED_DFU = Bool(true)//constant macro definition 
val	PIPED_DFU = Bool(true)//constant macro definition 
val	READ = UFix(0, width = 32)//constant macro definition 
val	WRITE = UFix(1, width = 32)//constant macro definition 
val	INFINITI = UFix(1, width = 32)<<UFix(63, width = 32)//constant macro definition 
}

