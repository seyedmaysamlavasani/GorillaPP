#pragma INPUT uint32_t
#pragma OUTPUT uint8_t
#pragma OFFLOAD(mem1, memReq32_t, memRep32_t)
#pragma OFFLOAD(mem2, memReq32_t, memRep32_t)
#pragma OFFLOAD(mem3, memReq32_t, memRep32_t)
#pragma CONCURRENT_SAFE

int result;

GS_FIRST_LEVEL()
{
  memReq32_t memReq1;
  memRep32_t memRep1;
  memReq1.addr = (Input >> LOOKUP_FIRST_LEVEL_SHIFT);
  if (((Input & 0xffff) ^ 1000) == 0) {
    memReq1.rw = 1;
    memReq1.wData = Input >> 16;
  } else { 
    memReq1.rw = 0;
  }
  memRep1 = mem1(memReq1);
  result = memRep1.rData;
  State = GS_SECOND_LEVEL;
}

GS_SECOND_LEVEL()
{
  memReq32_t memReq2;
  memRep32_t memRep2;

  memReq2.addr = (LOOKUP_SECOND_LEVEL_BASE + 
   (result << LOOKUP_SECOND_LEVEL_CHUNK_SIZE_LOG) + 
   ((Input >> (LOOKUP_SECOND_LEVEL_SHIFT)) & LOOKUP_SECOND_LEVEL_MASK));
  if (((Input & 0xfffe) ^ 1000) == 0) {
    memReq2.rw = 1;
    memReq2.wData = Input >> 16;
  } else { 
    memReq2.rw = 0;
  }
  memRep2 = mem2(memReq2);
  result = memRep2.rData;
  State = GS_THIRD_LEVEL;
}

GS_THIRD_LEVEL()
{
  memReq32_t memReq3;
  memRep32_t memRep3;

  memReq3.addr = LOOKUP_THIRD_LEVEL_BASE + 
   (result << LOOKUP_THIRD_LEVEL_CHUNK_SIZE_LOG) + 
   ((Input >> (LOOKUP_THIRD_LEVEL_SHIFT)) & LOOKUP_THIRD_LEVEL_MASK);
  if (((Input & 0xfffd) ^ 1000) == 0) {
    memReq3.rw = 1;
    memReq3.wData = Input >> 16;
  } else { 
    memReq3.rw = 0;
  }
  memRep3 = mem3(memReq3);
  Output = memRep3.rData;
  finish();
}
