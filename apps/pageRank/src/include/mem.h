#define CACHE_HIT_DELAY 1
#define CACHE_LINE_SIZE_LOG 4
#define cacheTag(addr) (addr >> CACHE_LINE_SIZE_LOG)

