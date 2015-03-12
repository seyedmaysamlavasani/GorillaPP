#pragma INPUT mem_req_t
#pragma OUTPUT mem_rep_t
#pragma OFFLOAD (dramBank0, uint32_t, uint32_t)  
#pragma OFFLOAD (dramBank1, uint32_t, uint32_t)  
#pragma OFFLOAD (dramBank2, uint32_t, uint32_t)  
#pragma OFFLOAD (dramBank3, uint32_t, uint32_t)  
#pragma OFFLOAD (dramBank4, uint32_t, uint32_t)  
#pragma OFFLOAD (dramBank5, uint32_t, uint32_t)  
#pragma OFFLOAD (dramBank6, uint32_t, uint32_t)  
#pragma OFFLOAD (dramBank7, uint32_t, uint32_t)  

//A DRAM configuration adopted from:
//http://www.eng.utah.edu/~cs6810/pres/10-6810-15.pdf
//
//12bit row address x 12bit col address  
//16Mb DRAM array * 8 = 16MB DRAM bank
//8*Banks = 128MB DRAM chip
//8*Chip = 1GB DRAM DIMM
//1 Rank
//Row buffer 4K Bytes
//20ns RB hit
//40ns RB empty
//60ns RB conflict
//10ns address/cmd/data transfer 
 
#define RB_HIT_DELAY 3 
#define RB_MISS_DELAY 7 

//rowAddrWidth,bankAddrWidth,columnAddrWidth,chipAddrWidth
#define CHIP_ADDR_WIDTH 3
#define COLUMN_ADDR_WIDTH 12
#define BANK_ADDR_WIDTH 3
#define ROW_ADDR_WIDTH 12

#define CHIP_SHIFT_R 0
#define CHIP_SHIFT_L (CHIP_SHIFT_R+CHIP_ADDR_WIDTH)
#define COLUMN_SHIFT_R CHIP_SHIFT_L 
#define COLUMN_SHIFT_L (CHIP_SHIFT_R+COLUMN_ADDR_WIDTH) 
#define BANK_SHIFT_R COLUMN_SHIFT_L 
#define BANK_SHIFT_L (BANK_ADDR_WIDTH+BANK_SHIFT_R) 
#define ROW_SHIFT_R BANK_SHIFT_L 
#define ROW_SHIFT_L (ROW_SHIFT_R+ROW_ADDR_WIDTH)

#define chip(addr) ((addr>>CHIP_SHIFT_R) & ((1<<CHIP_SHIFT_L) - 1))
#define column(addr) ((addr>>COLUMN_SHIFT_R) & ((1<<COLUMN_SHIFT_L) - 1)) 
#define bank(addr) ((addr>>BANK_SHIFT_R) & ((1<<BANK_SHIFT_L) - 1)) 
#define row(addr) ((addr>>ROW_SHIFT_R) & ((1<<ROW_SHIFT_L) - 1)) 

//TODO These should be modeled as arrays. Each element 
//is associated with a bank 
uint32_t rb0RowAddr = 1;
uint32_t rb1RowAddr = 0;
uint32_t rb2RowAddr = 0;
uint32_t rb3RowAddr = 0;
uint32_t rb4RowAddr = 0;
uint32_t rb5RowAddr = 0;
uint32_t rb6RowAddr = 0;
uint32_t rb7RowAddr = 0;

uint32_t bankAddr;
uint32_t rowAddr;

GS_CHECK_RB_HIT()
{
  uint8_t b;
  uint32_t r; 
  b = bank(Input.addr);
  r = row(Input.addr);
  bankAddr = b;
  rowAddr = r;
  if (b == 0) { 
    if (rb0RowAddr == r) {
      State = GS_FINISH;
    } else {
      rb0RowAddr = r;
      State = GS_WAIT_BANK_0;
    }
  } else if (b == 1) {
    if (rb1RowAddr == r) {
      State = GS_FINISH;
    } else {  
      rb1RowAddr = r;
      State = GS_WAIT_BANK_1;
    }
  } else if (b == 2) {
    if (rb2RowAddr == r) {
      State = GS_FINISH;
    } else {
      rb2RowAddr = r;
      State = GS_WAIT_BANK_2;
    }
  } else if (b == 3) {
    if (rb3RowAddr == r) {
      State = GS_FINISH;
    } else {
      rb3RowAddr = r;
      State = GS_WAIT_BANK_3;
    }
  } else if (b == 4) {
    if (rb4RowAddr == r) {
      State = GS_FINISH;
    } else {
      rb4RowAddr = r;
      State = GS_WAIT_BANK_4;
    }
  } else if (b == 5) {
    if (rb5RowAddr == r) {
      State = GS_FINISH;
    } else {
      rb5RowAddr = r;
      State = GS_WAIT_BANK_5;
    }
  } else if (b == 6) {
    if (rb6RowAddr == r) {
      State = GS_FINISH;
    } else {
      rb6RowAddr = r;
      State = GS_WAIT_BANK_6;
    }
  } else if (b == 7) {
    if (rb7RowAddr == r) {
      State = GS_FINISH;
    } else {
      rb7RowAddr = r;
      State = GS_WAIT_BANK_7;
    }
  } else {
    State = GS_FINISH;
  } 
}

GS_WAIT_BANK_0()
{
  uint32_t t0;
  t0 = dramBank0(0);
  finish();
}

GS_WAIT_BANK_1()
{
  uint32_t t1;
  t1 = dramBank1(0);
  finish();
}

GS_WAIT_BANK_2()
{
  uint32_t t2;
  t2 = dramBank2(0);
  finish();
}

GS_WAIT_BANK_3()
{
  uint32_t t3;
  t3 = dramBank3(0);
  finish();
}

GS_WAIT_BANK_4()
{
  uint32_t t4;
  t4 = dramBank4(0);
  finish();
}

GS_WAIT_BANK_5()
{
  uint32_t t5;
  t5 = dramBank5(0);
  finish();
}

GS_WAIT_BANK_6()
{
  uint32_t t6;
  t6 = dramBank6(0);
  finish();
}

GS_WAIT_BANK_7()
{
  uint32_t t7;
  t7 = dramBank7(0);
  finish();
}

GS_FINISH()
{
  finish();
}
