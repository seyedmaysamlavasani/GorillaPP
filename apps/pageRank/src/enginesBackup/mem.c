#pragma INPUT mem_req_t
#pragma OUTPUT mem_rep_t

int counter;
int addr;
#define CACHE_HIT_DELAY 1
#define RB_HIT_DELAY 10 
#define BANK_HIT_DELAY 20
#define BANK_MISS_DELAY 30 

#define BANK_SHIFT_R 18
#define BANK_SHIFT_L 10
#define RB_SHIFT_R 10
#define RB_SHIFT_L 12
#define CACHE_LINE_SIZE_LOG 4

#define bank(addr) ((addr>>BANK_SHIFT_R) & ((1<<BANK_SHIFT_L) - 1)) 
#define rb(addr) ((addr>>RB_SHIFT_R) & ((1<<RB_SHIFT_L) - 1)) 
#define cacheTag(addr) (addr>>CACHE_LINE_SIZE_LOG)

GS_step1()
{
//  if ((Input.addr <= RANK_A_BASE_ADDRESS) && (Input.addr & 3 == 0)) {
  if ((Input.addr <= RANK_A_BASE_ADDRESS)) {
    //Reading a fanout degree 
    Output.data = 4; 
  } else {
    Output.data = Input.addr + Input.data + Input.rw + Input.size + Input.cached;
  }
  counter = 20;
  State = GS_step2;
}
GS_step2()
{
  counter = counter - 1;
  if (counter == 0) 
    State = GS_finish;
}

GS_finish()
{
  finish();
}
