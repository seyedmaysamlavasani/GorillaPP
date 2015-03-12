#pragma INPUT mem_req_t
#pragma OUTPUT mem_rep_t
#pragma OFFLOAD (cacheMissPipe, uint32_t, uint32_t)  
#pragma OFFLOAD (dram, mem_req_t, mem_rep_t)  

uint32_t cachedAddr = 0;
mem_rep_t dramOut;

int enableRandomFanout = 1;
int random = 1;
int burst = 1;
//#define BURST_FACTOR 2
//#define FIXED_FANOUT 1
//#define AVERAGE_FANOUT 10
#define MAX_FANOUT (AVERAGE_FANOUT >> 1) 
GS_HIT_CHECK()
{
  uint32_t ct;
  if ((Input.addr <= RANK_A_BASE_ADDRESS)) {
    if (((Input.addr >> 2) & 1) == 0) {
      //Reading a fanout degree 
//      if (1) {
        if (FIXED_FANOUT == 1) {
          Output.data = AVERAGE_FANOUT;
        } else {
          Output.data = random; 
        }
        if (random == MAX_FANOUT) {
          if (burst == BURST_FACTOR) {
            random = 1;
          }
        } else {
          if (burst == BURST_FACTOR) {
            random = random + 1;
          } 
        }
        if (burst == BURST_FACTOR) {
          burst = 1;
        } else { 
          burst = burst + 1;
        }
//      } else {
//        Output.data = fixedFanout; 
//      }
    } else {
      //Reading startLink address
      Output.data = RANK_A_BASE_ADDRESS + random << 5; 
    }
  } else {
    Output.data = Input.addr + 1000;
  }
  ct = cacheTag(Input.addr);
  if ( ct == cachedAddr || Input.rw == WRITE) {
    State = GS_FINISH;
  } else {
    State = GS_MISS;
    cachedAddr = ct;
  }
}

GS_MISS() {
  uint32_t t;
  t = cacheMissPipe(0);
  State = GS_DRAM;
}

GS_DRAM() {
  uint32_t t;
  dramOut = dram(Input);
  State = GS_FINISH;
}

GS_FINISH() {
  finish();
}
