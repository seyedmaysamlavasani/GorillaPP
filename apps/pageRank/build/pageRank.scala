package Tutorial

import Chisel._
import Node._
import Literal._
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer


class page_t extends Bundle { 
val fanoutDegree = UFix(width = 32)
val startLinkId = UFix(width = 32)
}
class rank_t extends Bundle { 
val rank = UFix(width = 64)
}
class link_t extends Bundle { 
val targetPageId = UFix(width = 32)
}
class update_t extends Bundle { 
val done = Bool()
val pageId = UFix(width = 32)
val rankUpdate = UFix(width = 64)
}
class prDone_t extends Bundle { 
val out = Bool()
}
class lock_req_t extends Bundle { 
val id = UFix(width = 32)
val op = UFix(width=1)
}
class lock_rep_t extends Bundle { 
val out = Bool()
}
class bulkUpdate_t extends Bundle { 
val done = Bool()
val startPageId = UFix(width = 32)
val length = UFix(width = 32)
}
class rankCalc_in_t extends Bundle { 
val damping = UFix(width = 64)
val rank = UFix(width = 64)
val fanoutDegree = UFix(width = 32)
}
class rankCalc_out_t extends Bundle { 
val out = UFix(width = 64)
}
class mem_req_t extends Bundle { 
val addr = UFix(width = 32)
val rw = UFix(width=1)
val cached = Bool()
val data = UFix(width = 128)
val size = UFix(width = 4)
}
class mem_rep_t extends Bundle { 
val data = UFix(width = 128)
}
class FU_in_t extends Bundle { 
val in1 = UFix(width = 64)
val in2 = UFix(width = 64)
}
class FU_out_t extends Bundle { 
val out = UFix(width = 64)
}
class double extends uint64_t
