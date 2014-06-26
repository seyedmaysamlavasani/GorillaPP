package Tutorial

import Chisel._
import Node._
import Literal._
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer


trait include extends GorillaUtil {
 val dummy = 0 

val	NUM_OF_POINTS = UFix(100, width = 32)//constant macro definition 
val	NUM_OF_CENTEROIDS = UFix(10, width = 32)//constant macro definition 
val	NUM_OF_ENGINES = UFix(2, width = 32)//constant macro definition 
val	NUM_OF_THREADS = UFix(1, width = 32)//constant macro definition 
val	READ = Bool(false)//constant macro definition 
val	WRITE = Bool(true)//constant macro definition 
val	INFINITI = UFix(1, width = 32)<<UFix(63, width = 32)//constant macro definition 
}

