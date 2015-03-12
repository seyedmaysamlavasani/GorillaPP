#pragma INPUT memReq16_128_t 
#pragma OUTPUT memRep128_t
#pragma OFFLOAD (lPipe, uint32_t, uint32_t) 
#pragma OFFLOAD (data, memReq16_128_t, memRep128_t) 

GS_MEMOP()
{
  memReq16_128_t dramReq;
  memRep128_t dramRep;
  dramReq = Input;
  dramRep = data(dramReq);
  Output = dramRep;
  State = GS_DELAY;
}

GS_DELAY()
{
 uint32_t lPipeReq, lPipeRep;
 lPipeReq = Input.addr;
 lPipeRep = lPipe(lPipeReq);
 finish(); 
}

