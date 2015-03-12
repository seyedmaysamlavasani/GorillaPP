//#define READ 0
//#define WRITE 1
#define ACQUIRE2CACHE 2
#define RELEASE2CACHE 3
#define NOLOCK (1 << 16)
#define FIRST_BYTES(data, size) (((1 << (size << 3)) - 1) & data)
#define INDEX(addr) ((addr >> cacheLineSizeLog) & ((1 << cacheHeightLog) - 1))
#define LLINDEX(addr) (addr & ((1 << llCacheHeightLog) - 1))
//#define ALIGNED(addr) (INDEX(addr) == INDEX(addr + 3))
#define ALIGNED(addr) (((addr >> cacheLineSizeLog) & ((1 << cacheHeightLog) - 1)) == (((addr +3) >> cacheLineSizeLog) & ((1 << cacheHeightLog) - 1)))
#define WORD_BITOFFSET_IN_CACHE_LINE(addr) ((addr & ((1 << cacheLineSizeLog) - 1)) << 3)
//#define READ_WORD_IN_CACHE_LINE(addr, data) ((data >> WORD_BITOFFSET_IN_CACHE_LINE(addr)) & ((1 << 32) - 1))  
#define READ_WORD_IN_CACHE_LINE(addr, data) ((data >> ((addr & ((1 << cacheLineSizeLog) - 1)) << 3)) & ((1 << 32) - 1))  
#define ALL_ONES(offset, wordSize) (((1 << (wordSize << 3)) - 1) << offset) 
//#define WRITE_WORD_IN_CACHE_LINE(addr, data, word, wordSize) ((~(ALL_ONES(WORD_BITOFFSET_IN_CACHE_LINE(addr), wordSize)) & data) | (word << WORD_BITOFFSET_IN_CACHE_LINE(addr)))    
#define WRITE_WORD_IN_CACHE_LINE(addr, data, word, wordSize) ((~(((1 << (wordSize << 3)) - 1) << ((addr & ((1 << cacheLineSizeLog) - 1)) << 3)))& data) | (word << ((addr & ((1 << cacheLineSizeLog) - 1)) << 3))    
#define MISSALIGNEMENT(addr) ((addr & ((1 << cacheLineSizeLog) - 1)) < ((1 << cacheLineSizeLog) - 4)) ? 0 : ((addr & ((1 << cacheLineSizeLog) - 1)) - ((1 << cacheLineSizeLog) - 4))
#define TAG(addr) (addr >> (cacheLineSizeLog + cacheHeightLog))
#define LLTAG(addr) (addr >> (llCacheHeightLog))
#define DRAM_ADDR(addr) (addr >> cacheLineSizeLog)
#define LLDRAM_ADDR(addr) addr
#define DRAM_ADDR_WITH_NEW_TAG(tag, addr) ((tag << cacheHeightLog) + ((addr >> cacheLineSizeLog) & ((1 << cacheHeightLog) - 1)))
#define LLDRAM_ADDR_WITH_NEW_TAG(tag, addr) ((tag << llCacheHeightLog) + (addr & ((1 << llCacheHeightLog) - 1)))
typedef memReq10_64_t memReqCacheTagBank_t;
typedef memRep64_t memRepCacheTagBank_t;
typedef memReq10_128_t memReqCacheDataBank_t;
typedef memRep128_t memRepCacheDataBank_t;
typedef memReq16_128_t memReqDram_t;
typedef memRep128_t memRepDram_t;


typedef struct {
  uint24_t tagValue;
  bool dirty;
  bool valid;
  bool tainted;
  uint16_t lockId;
} tag_t;
