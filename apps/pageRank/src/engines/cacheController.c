#pragma INPUT memReq32_t 
#pragma OUTPUT memRep32_t 

//#pragma OFFLOAD (tag, memReq#cacheHeightLog_#tagWidth_t, memRep#tagWidth_t) 
#pragma OFFLOAD (tag, memReqCacheTagBank_t, memRepCacheTagBank_t) 
//#pragma OFFLOAD (data, memReq#cacheHeightLog_#cacheLineWidth_t, memRep#cacheLineWidth_t) 
#pragma OFFLOAD (data, memReqCacheDataBank_t, memRepCacheDataBank_t) 
#pragma OFFLOAD (dram, memReqDram_t, memRepDram_t)


uint128_t gCacheLine;
tag_t gTag;
uint32_t gWord = 0;
uint2_t missAlignment;
memRepDram_t dramRep;
uint32_t addr;
bool secondRound = false;
bool aligned;
uint32_t initializationCounter = 0;
shared bool lock[1024] = false;
uint32_t lockIndex;
bool lockGrabbed = false;

GS_FIRST ()  //Aligned loads are finished here 
{            //Also, the first part of unaligned 
             //loads and stores are handled here
  uint32_t lWord1, lWord2;
  uint32_t lAddr1, lAddr11;
  memReqCacheDataBank_t cacheDataReq1;  
  memReqCacheTagBank_t cacheTagReq1;  
  memRepCacheDataBank_t cacheDataRep1;
  memRepCacheTagBank_t cacheTagRep1;
  tag_t tag;
  uint24_t tagValue;
  uint32_t lockIndexLocal;
  bool localAligned;
 
  lAddr1 = secondRound ? addr : Input.addr;
  cacheDataReq1.addr = INDEX(lAddr1);
  cacheDataReq1.rw = READ;   
  cacheTagReq1.addr = INDEX(lAddr1);
  cacheTagReq1.rw = READ;   
  cacheTagRep1 = tag(cacheTagReq1);
  cacheDataRep1 = data(cacheDataReq1);
  lAddr11 = secondRound ? addr : Input.addr;
  lockIndexLocal = INDEX(lAddr11);
  lockIndex = lockIndexLocal;
  localAligned = ALIGNED(Input.addr);
  aligned = localAligned;
  if (!lock[lockIndexLocal] || lockGrabbed) {
    lockGrabbed = true;
    lock[lockIndexLocal] = true;
    gCacheLine = cacheDataRep1.rData;
    missAlignment = MISSALIGNEMENT(Input.addr);
    tag = (tag_t) cacheTagRep1;
    gTag = tag;
    tagValue = TAG(lAddr11);
    if (!Input.initialize && tag.valid && tag.tagValue == tagValue) {
      if (Input.rw == READ) {
        if (localAligned || secondRound) {
          if(!secondRound) {
            Output.rData = READ_WORD_IN_CACHE_LINE(Input.addr, 
             cacheDataRep1.rData);
            lock[lockIndexLocal] = false;
            finish();
          } else {
            lWord1 = FIRST_BYTES(cacheDataRep1.rData, missAlignment);
            lWord2 = FIRST_BYTES(gWord, (4 - missAlignment));
            Output.rData = (lWord1 << ((4 - missAlignment) << 3)) + lWord2; 
            lock[lockIndexLocal] = false;
            finish();
          }
        } else { 
          gWord = READ_WORD_IN_CACHE_LINE(Input.addr, 
           cacheDataRep1.rData); 
          addr = Input.addr + 4 - missAlignment; 
          secondRound = true;
          lock[lockIndexLocal] = false;
          lockGrabbed = false;
          State = GS_FIRST;
        }
      }
      if (Input.rw == WRITE) {
        gTag.dirty = true;
        State = GS_STORE_HIT;
      }
    } else {
      if (tag.dirty) {
        gTag.dirty = false;
        State = GS_WRITE_BACK;      
      } else {
        State = GS_CACHE_FILL_DRAM_READ;
      }
    }
    if (Input.initialize) {
      State = GS_INITIALIZE;
    }
  }
}

GS_STORE_HIT() {  //Write back the aligned store 
  memReqCacheDataBank_t cacheDataReq3;  
  memRepCacheDataBank_t cacheDataRep3;
  memReqCacheTagBank_t cacheTagReq3;  
  memRep32_t cacheTagRep3;

  uint128_t lCacheLine1, lCacheLine2;
  uint32_t lAddr2;
  
  lAddr2 = secondRound ? addr : Input.addr;
  cacheDataReq3.addr = INDEX(lAddr2);
  cacheDataReq3.rw = WRITE;
  lCacheLine1 = WRITE_WORD_IN_CACHE_LINE(
   Input.addr, gCacheLine, 
   Input.wData, 4);
  lCacheLine2 = WRITE_WORD_IN_CACHE_LINE(
   addr, 
   gCacheLine, (Input.wData >> ((4 - missAlignment) << 3)), 
   missAlignment);
  cacheDataReq3.wData =  secondRound ? lCacheLine2 : lCacheLine1; 
  cacheTagReq3.addr = INDEX(lAddr2);
  cacheTagReq3.wData = (uint32_t) gTag;
  cacheTagReq3.rw = WRITE;
  cacheDataRep3 = data(cacheDataReq3);
  cacheTagRep3 = tag(cacheTagReq3);
  if (aligned || secondRound) {
    lock[lockIndex] = false;
    finish(); 
  } else {
    addr = Input.addr + 4 - missAlignment; 
    secondRound = true;
    lock[lockIndex] = false;
    lockGrabbed = false;
    State = GS_FIRST;
  }
}


GS_CACHE_FILL_DRAM_READ() {
  uint32_t lAddr3;
  memReqDram_t dramReq1;
  lAddr3 = secondRound ? addr : Input.addr;
  dramReq1.addr = DRAM_ADDR(lAddr3);
  dramReq1.rw = READ;
  dramRep = dram(dramReq1);  
  State = GS_CACHE_FILL_CACHE_WRITE; 
}

GS_CACHE_FILL_CACHE_WRITE() {
  memReqCacheDataBank_t cacheDataReq4;  
  memRepCacheDataBank_t cacheDataRep4;
  memReqCacheTagBank_t cacheTagReq4;  
  memRep32_t cacheTagRep4;
  tag_t newTag;
  uint32_t lAddr4;
  lAddr4 = secondRound ? addr : Input.addr;

  newTag.tagValue = TAG(lAddr4); 
  newTag.valid = true;
  newTag.dirty = false;
  cacheDataReq4.wData = dramRep.rData;
  cacheDataReq4.rw = WRITE;
  cacheDataReq4.addr = INDEX(lAddr4);
  cacheTagReq4.wData = (uint32_t) newTag;
  cacheTagReq4.rw = WRITE;
  cacheTagReq4.addr = INDEX(lAddr4);
  cacheDataRep4 = data(cacheDataReq4);
  cacheTagRep4 = tag(cacheTagReq4);
  State = GS_FIRST; 
}

GS_WRITE_BACK() {
  memReqDram_t dramReq2;
  memRepCacheDataBank_t dramRep2;
  memReqCacheTagBank_t cacheTagReq5;  
  memRep32_t cacheTagRep5;

  uint32_t lAddr5;
  lAddr5 = secondRound ? addr : Input.addr;
  cacheTagReq5.wData = (uint32_t) gTag;
  cacheTagReq5.rw = WRITE;
  cacheTagReq5.addr = INDEX(lAddr5);
  dramReq2.wData = gCacheLine;
  dramReq2.rw = WRITE;
  dramReq2.addr = 
   DRAM_ADDR_WITH_NEW_TAG(gTag.tagValue, lAddr5);
  cacheTagRep5 = tag(cacheTagReq5);
  dramRep = dram(dramReq2);  
  State = GS_FIRST;
}

GS_INITIALIZE() {
  memReqCacheTagBank_t cacheTagReq6;  
  memRep32_t cacheTagRep6;
  tag_t initialTag;

  initialTag.dirty = false;
  initialTag.valid = false;  
  cacheTagReq6.addr = initializationCounter;
  cacheTagReq6.rw = WRITE;
  cacheTagReq6.wData = (uint32_t) initialTag;
  cacheTagRep6 = tag(cacheTagReq6);
  if (initializationCounter + 1 == (1 << cacheHeightLog)) {
    lock[lockIndex] = false;
    finish();
  } else {
    initializationCounter = initializationCounter + 1;   
  }
}
