#pragma INPUT uint32_t
#pragma OUTPUT uint8_t
#pragma OFFLOAD(mem1Read, memReadOnlyReq32_t, memReadOnlyRep32_t)
#pragma OFFLOAD(mem1Write, memWriteOnlyReq32_t, memWriteOnlyRep32_t)
#pragma OFFLOAD(mem2Read, memReadOnlyReq32_t, memReadOnlyRep32_t)
#pragma OFFLOAD(mem2Write, memWriteOnlyReq32_t, memWriteOnlyRep32_t)
#pragma OFFLOAD(mem3Read, memReadOnlyReq32_t, memReadOnlyRep32_t)
#pragma OFFLOAD(mem3Write, memWriteOnlyReq32_t, memWriteOnlyRep32_t)

#pragma CONCURRENT_SAFE

int result;
uint1_t carry1, carry2;

//GS_TEST_LEVEL() {
//  Output = Input;
//  finish();
//}

GS_FIRST_LEVEL()
{
  memReadOnlyReq32_t memReq1;
  memReadOnlyRep32_t memRep1;
  memReq1.addr = Input;
  memRep1 = mem1Read(memReq1);
  result = memRep1.data;
  State = GS_SECOND_LEVEL;
}

GS_SECOND_LEVEL()
{
  memWriteOnlyReq32_t memReq2;
  memWriteOnlyRep32_t memRep2;
  memReq2.addr = Input;
  memReq2.data = result + 1;
  memRep2 = mem1Write(memReq2);
  if (result ==0) {
    carry1 = 1;
  } else {
    carry1 = 0; 
  }
  State = GS_THIRD_LEVEL;
}

GS_THIRD_LEVEL()
{
  memReadOnlyReq32_t memReq3;
  memReadOnlyRep32_t memRep3;
  memReq3.addr = Input;
  memRep3 = mem2Read(memReq3);
  result = memRep3.data;
  State = GS_FOURTH_LEVEL;
}

GS_FOURTH_LEVEL()
{
  memWriteOnlyReq32_t memReq4;
  memWriteOnlyRep32_t memRep4;
  memReq4.addr = Input;
  if (carry1) {
    memReq4.data = result + 1;
  } else {
    memReq4.data = result;
  }
  memRep4 = mem2Write(memReq4);
  if (carry1 && result ==0) {
    carry2 = 1;
  } else {
    carry2 = 0; 
  }
  State = GS_FIFTH_LEVEL;
}

GS_FIFTH_LEVEL()
{
  memReadOnlyReq32_t memReq5;
  memReadOnlyRep32_t memRep5;
  memReq5.addr = Input;
  memRep5 = mem3Read(memReq5);
  result = memRep5.data;
  State = GS_SIXTH_LEVEL;
}

GS_SIXTH_LEVEL()
{
  memWriteOnlyReq32_t memReq6;
  memWriteOnlyRep32_t memRep6;
  memReq6.addr = Input;
  if (carry2) {
    memReq6.data = result + 1;
  } else {
    memReq6.data = result;
  }
  memRep6 = mem3Write(memReq6);
  Output = 1;
  finish();
}
