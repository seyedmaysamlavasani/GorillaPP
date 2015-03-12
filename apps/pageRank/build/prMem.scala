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
  val cacheMissPipe = (md, () => new gPipe(2)).asInstanceOf[
   (gComponentMD[Chisel.Data,Chisel.Data], () => gComponent[Chisel.Data,Chisel.Data])]

  val dramBank_MD = new gComponentMD(() => UFix(width = 32), () => UFix(width = 32), ArrayBuffer())
  val  dramBank0 =  (dramBank_MD , () => new dramBank (extCompName="dramBank0") ).asInstanceOf[(gComponentMD[Chisel.Data,Chisel.Data], () => gComponent[Chisel.Data,Chisel.Data])]
  val  dramBank1 =  (dramBank_MD , () => new dramBank (extCompName="dramBank1") ).asInstanceOf[(gComponentMD[Chisel.Data,Chisel.Data], () => gComponent[Chisel.Data,Chisel.Data])]
  val  dramBank2 =  (dramBank_MD , () => new dramBank (extCompName="dramBank2") ).asInstanceOf[(gComponentMD[Chisel.Data,Chisel.Data], () => gComponent[Chisel.Data,Chisel.Data])]
  val  dramBank3 =  (dramBank_MD , () => new dramBank (extCompName="dramBank3") ).asInstanceOf[(gComponentMD[Chisel.Data,Chisel.Data], () => gComponent[Chisel.Data,Chisel.Data])]
  val  dramBank4 =  (dramBank_MD , () => new dramBank (extCompName="dramBank4") ).asInstanceOf[(gComponentMD[Chisel.Data,Chisel.Data], () => gComponent[Chisel.Data,Chisel.Data])]
  val  dramBank5 =  (dramBank_MD , () => new dramBank (extCompName="dramBank5") ).asInstanceOf[(gComponentMD[Chisel.Data,Chisel.Data], () => gComponent[Chisel.Data,Chisel.Data])]
  val  dramBank6 =  (dramBank_MD , () => new dramBank (extCompName="dramBank6") ).asInstanceOf[(gComponentMD[Chisel.Data,Chisel.Data], () => gComponent[Chisel.Data,Chisel.Data])]
  val  dramBank7 =  (dramBank_MD , () => new dramBank (extCompName="dramBank7") ).asInstanceOf[(gComponentMD[Chisel.Data,Chisel.Data], () => gComponent[Chisel.Data,Chisel.Data])]
  val cache_MD = new gComponentMD(() => new mem_req_t, () => new mem_rep_t, ArrayBuffer(("cacheMissPipe", () => UFix(width = 32) , () => UFix(width = 32)), ("dram", () => new mem_req_t , () => new mem_rep_t)))
  val  cache =  (cache_MD , () => new cache (extCompName="cache")).asInstanceOf[(gComponentMD[Chisel.Data,Chisel.Data], () => gComponent[Chisel.Data,Chisel.Data])]
  val dram_MD = new gComponentMD(() => new mem_req_t, () => new mem_rep_t, ArrayBuffer(("dramBank0", () => UFix(width = 32) , () => UFix(width = 32)), ("dramBank1", () => UFix(width = 32) , () => UFix(width = 32)), ("dramBank2", () => UFix(width = 32) , () => UFix(width = 32)), ("dramBank3", () => UFix(width = 32) , () => UFix(width = 32)), ("dramBank4", () => UFix(width = 32) , () => UFix(width = 32)), ("dramBank5", () => UFix(width = 32) , () => UFix(width = 32)), ("dramBank6", () => UFix(width = 32) , () => UFix(width = 32)), ("dramBank7", () => UFix(width = 32) , () => UFix(width = 32))))
  val  dram =  (dram_MD , () => new dram (extCompName="dram")).asInstanceOf[(gComponentMD[Chisel.Data,Chisel.Data], () => gComponent[Chisel.Data,Chisel.Data])]
  val dramOffs = ArrayBuffer((dramBank0, "dramBank0"), (dramBank1, "dramBank1"), (dramBank2, "dramBank2"), (dramBank3, "dramBank3"), (dramBank4, "dramBank4"), (dramBank5, "dramBank5"), (dramBank6, "dramBank6"), (dramBank7, "dramBank7"))
  val  cachePlus = Offload (cache, cacheMissPipe, "cacheMissPipe", extCompName="cachePlus") 
  val  dramPlus = Offload (dram, dramOffs, extCompName="dramPlus")
  val  result = Offload (cachePlus, dramPlus, "dram", extCompName="result")
  val generatedTop = result._2()
  generatedTop.io <> io
}

