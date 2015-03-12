package Tutorial

import Chisel._
import Node._
import Literal._
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer


trait include extends GorillaUtil {
 val dummy = 0 

val	ACQUIRE2CACHE = UFix(2, width = 32)//constant macro definition 
val	RELEASE2CACHE = UFix(3, width = 32)//constant macro definition 
val	NOLOCK = (UFix(1, width = 32)<<UFix(16, width = 32))//constant macro definition 
def FIRST_BYTES(data:UFix, size:UFix) = (((UFix(1, width = 32)<<(size<<UFix(3, width = 32)))-UFix(1, width = 32))& data)//constant macro definition 
def INDEX(addr:UFix) = ((addr>>cacheLineSizeLog)& ((UFix(1, width = 32)<<cacheHeightLog)-UFix(1, width = 32)))//constant macro definition 
def LLINDEX(addr:UFix) = (addr& ((UFix(1, width = 32)<<llCacheHeightLog)-UFix(1, width = 32)))//constant macro definition 
def ALIGNED(addr:UFix) = (((addr>>cacheLineSizeLog)& ((UFix(1, width = 32)<<cacheHeightLog)-UFix(1, width = 32)))=== (((addr+UFix(3, width = 32))>>cacheLineSizeLog)& ((UFix(1, width = 32)<<cacheHeightLog)-UFix(1, width = 32))))//constant macro definition 
def WORD_BITOFFSET_IN_CACHE_LINE(addr:UFix) = ((addr& ((UFix(1, width = 32)<<cacheLineSizeLog)-UFix(1, width = 32)))<<UFix(3, width = 32))//constant macro definition 
def READ_WORD_IN_CACHE_LINE(addr:UFix, data:UFix) = ((data>>((addr& ((UFix(1, width = 32)<<cacheLineSizeLog)-UFix(1, width = 32)))<<UFix(3, width = 32)))& ((UFix(1, width = 32)<<UFix(32, width = 32))-UFix(1, width = 32)))//constant macro definition 
def ALL_ONES(offset:UFix, wordSize:UFix) = (((UFix(1, width = 32)<<(wordSize<<UFix(3, width = 32)))-UFix(1, width = 32))<<offset)//constant macro definition 
def WRITE_WORD_IN_CACHE_LINE(addr:UFix, data:UFix, word:UFix, wordSize:UFix) = ((~(((UFix(1, width = 32)<<(wordSize<<UFix(3, width = 32)))-UFix(1, width = 32))<<((addr& ((UFix(1, width = 32)<<cacheLineSizeLog)-UFix(1, width = 32)))<<UFix(3, width = 32))))& data)| (word<<((addr& ((UFix(1, width = 32)<<cacheLineSizeLog)-UFix(1, width = 32)))<<UFix(3, width = 32)))//constant macro definition 
def MISSALIGNEMENT(addr:UFix) =  Mux(((addr& ((UFix(1, width = 32)<<cacheLineSizeLog)-UFix(1, width = 32)))<((UFix(1, width = 32)<<cacheLineSizeLog)-UFix(4, width = 32))),UFix(0, width = 32),((addr& ((UFix(1, width = 32)<<cacheLineSizeLog)-UFix(1, width = 32)))-((UFix(1, width = 32)<<cacheLineSizeLog)-UFix(4, width = 32))))//constant macro definition 
def TAG(addr:UFix) = (addr>>(cacheLineSizeLog+cacheHeightLog))//constant macro definition 
def LLTAG(addr:UFix) = (addr>>(llCacheHeightLog))//constant macro definition 
def DRAM_ADDR(addr:UFix) = (addr>>cacheLineSizeLog)//constant macro definition 
def LLDRAM_ADDR(addr:UFix) = addr//constant macro definition 
def DRAM_ADDR_WITH_NEW_TAG(tag:UFix, addr:UFix) = ((tag<<cacheHeightLog)+((addr>>cacheLineSizeLog)& ((UFix(1, width = 32)<<cacheHeightLog)-UFix(1, width = 32))))//constant macro definition 
def LLDRAM_ADDR_WITH_NEW_TAG(tag:UFix, addr:UFix) = ((tag<<llCacheHeightLog)+(addr& ((UFix(1, width = 32)<<llCacheHeightLog)-UFix(1, width = 32))))//constant macro definition 
val	NUM_OF_CACHE_MISS_PIPE_STAGES = UFix(2, width = 32)//constant macro definition 
val	BURST_FACTOR = UFix(1, width = 32)//constant macro definition 
val	FIXED_FANOUT = UFix(1, width = 32)//constant macro definition 
val	AVERAGE_FANOUT = UFix(5, width = 32)//constant macro definition 
val	uwThreads = UFix(2, width = 32)//constant macro definition 
val	ugThreads = UFix(2, width = 32)//constant macro definition 
val	addPipelined = Bool(false)//constant macro definition 
val	mulPipelined = Bool(false)//constant macro definition 
val	divPipelined = Bool(false)//constant macro definition 
val	rcPipelined = Bool(false)//constant macro definition 
val	sharedRc = Bool(false)//constant macro definition 
val	sharedRa = Bool(false)//constant macro definition 
val	ugRepFactor = UFix(2, width = 32)//constant macro definition 
val	uwRepFactor = UFix(4, width = 32)//constant macro definition 
val	numOfCacheThreads = UFix(3, width = 32)//constant macro definition 
val	cacheHeightLog = UFix(7, width = 32)//constant macro definition 
val	llCacheHeightLog = UFix(7, width = 32)//constant macro definition 
val	cacheLineSizeLog = UFix(4, width = 32)//constant macro definition 
val	CACHE_HIT_DELAY = UFix(1, width = 32)//constant macro definition 
val	CACHE_LINE_SIZE_LOG = UFix(4, width = 32)//constant macro definition 
def cacheTag(addr:UFix) = (addr>>CACHE_LINE_SIZE_LOG)//constant macro definition 
val	PAGES_BASE_ADDRESS = UFix(0, width = 32)//constant macro definition 
val	RANK_A_BASE_ADDRESS = UFix(1, width = 32)<<UFix(24, width = 32)//constant macro definition 
val	RANK_B_BASE_ADDRES = UFix(1, width = 32)<<UFix(25, width = 32)//constant macro definition 
val	LINKS_BASE_ADDRESS = UFix(1, width = 32)<<UFix(26, width = 32)//constant macro definition 
val	MODES_BASE_ADDRESS = UFix(1, width = 32)<<UFix(27, width = 32)//constant macro definition 
val	PAGE_ELEMENT_SIZE_LOG = UFix(3, width = 32)//constant macro definition 
val	RANK_ELEMENT_SIZE_LOG = UFix(3, width = 32)//constant macro definition 
val	LINK_ELEMENT_SIZE_LOG = UFix(2, width = 32)//constant macro definition 
def rankAddress(index:UFix) = (RANK_A_BASE_ADDRESS+(index<<RANK_ELEMENT_SIZE_LOG))//constant macro definition 
def pageAddress(index:UFix) = (PAGES_BASE_ADDRESS+(index<<PAGE_ELEMENT_SIZE_LOG))//constant macro definition 
def linkAddress(index:UFix) = (LINKS_BASE_ADDRESS+(index<<LINK_ELEMENT_SIZE_LOG))//constant macro definition 
def modeAddress(index:UFix) = (MODES_BASE_ADDRESS+(index>>UFix(3, width = 32)))//constant macro definition 
def modeRead(word:UFix, index:UFix) = ((word& (UFix(1, width = 32)<<(index& UFix(31, width = 32))))!= UFix(0, width = 32))//constant macro definition 
def modeWrite(word:UFix, index:UFix) = (word^ (UFix(1, width = 32)<<(index& UFix(31, width = 32))))//constant macro definition 
def fanoutInPage(pageElement:UFix) = pageElement//constant macro definition 
def startLinkIndxInPage(pageElement:UFix) = (pageElement>>UFix(32, width = 32))//constant macro definition 
val	LOCK = UFix(0, width = 32)//constant macro definition 
val	UNLOCK = UFix(1, width = 32)//constant macro definition 
val	READ = UFix(0, width = 32)//constant macro definition 
val	WRITE = UFix(1, width = 32)//constant macro definition 
val	READ_CACHED = UFix(2, width = 32)//constant macro definition 
val	DAMPING_FACTOR = UFix(800, width = 32)//constant macro definition 
val	DAMPING_RANK = UFix(10, width = 32)//constant macro definition 
val	WORD = UFix(4, width = 32)//constant macro definition 
val	DWORD = UFix(8, width = 32)//constant macro definition 
val	QWORD = UFix(16, width = 32)//constant macro definition 
}

