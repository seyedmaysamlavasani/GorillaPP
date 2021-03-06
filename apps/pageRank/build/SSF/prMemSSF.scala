//XX variable version 
package Tutorial

import Chisel._
import Node._
import Literal._
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer
object prMem {
  def apply() = {
    val md = new gComponentMD(() => new mem_req_t , () => new mem_rep_t, ArrayBuffer())
    (md, () => new prMemComponent).asInstanceOf[(gComponentMD[Chisel.Data,Chisel.Data], 
     () => gComponent[Chisel.Data,Chisel.Data])]
  }
}

class prMemComponent extends gComponent(() => new mem_req_t) (() => new mem_rep_t) (ArrayBuffer()) (extCompName="prMem") with include {

  val md = new gComponentMD(() => UFix(width=32) , () => UFix(width=32), ArrayBuffer())
  val cacheMissPipe = (md, () => new gPipe(#NUM_OF_CACHE_MISS_PIPE_STAGES)).asInstanceOf[
   (gComponentMD[Chisel.Data,Chisel.Data], () => gComponent[Chisel.Data,Chisel.Data])]

  val dramBank0 = Engine("dramBank.c")
  val dramBank1 = Engine("dramBank.c")
  val dramBank2 = Engine("dramBank.c")
  val dramBank3 = Engine("dramBank.c")
  val dramBank4 = Engine("dramBank.c")
  val dramBank5 = Engine("dramBank.c")
  val dramBank6 = Engine("dramBank.c")
  val dramBank7 = Engine("dramBank.c")
  val cache = MTEngine("cache.c", #numOfCacheThreads)
  val dram = MTEngine("dram.c", 8)
  val dramOffs = ArrayBuffer((dramBank0, "dramBank0"), (dramBank1, "dramBank1"), (dramBank2, "dramBank2"), (dramBank3, "dramBank3"), (dramBank4, "dramBank4"), (dramBank5, "dramBank5"), (dramBank6, "dramBank6"), (dramBank7, "dramBank7"))
  val cachePlus = Offload(cache, cacheMissPipe, "cacheMissPipe") 
  val dramPlus = Offload(dram, dramOffs)
  val result = Offload(cachePlus, dramPlus, "dram")
}

