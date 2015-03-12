#pragma INPUT  update_t
#pragma OUTPUT prDone_t
#pragma OFFLOAD (mem, mem_req_t, mem_rep_t)
#pragma OFFLOAD (lock, lock_req_t, lock_rep_t)
#pragma OFFLOAD (add, FU_in_t, FU_out_t)
#pragma CONCURRENT_SAFE

bit mode = 0;  
double rank;
mem_rep_t memRep;

GS_RECEIVE_UPDATE()
{
  if (Input.done) {
    State = GS_FINISH; 
  } else  {
    State = GS_LOCK_PAGE; 
  }
}

//First lock the page. Locking is done on a big enough granularity not to hurt the performance 
//while making sure that there is no 
//inconsistency. lock granulairity should be bigger than cache line granularity
GS_LOCK_PAGE() 
{
  lock_req_t lock_req1;
  lock_rep_t lock_rep1;

  lock_req1.id =  Input.pageId;
  lock_req1.op = LOCK;
  lock_rep1 = lock(lock_req1);
  State = GS_READ_MODE;
}

//Then see if page rank is initialized yet
//If not, zero it out. If yes, go to add. 
GS_READ_MODE()
{
  bit oldMode;
  mem_req_t memReq1;
  memReq1.rw = READ;
  memReq1.size = WORD;
  memReq1.addr = modeAddress(Input.pageId); 
  memRep = mem(memReq1); 
  oldMode = modeRead(memRep.data, Input.pageId);
//  if (oldMode ^ mode) {
    State = GS_READ_RANK; 
//  } else {
//    State = GS_CHANGE_MODE; 
//  }
}

GS_CHANGE_MODE()
{
  mem_req_t memReq2;
  memReq2.rw = WRITE;
  memReq2.size = WORD;
  memReq2.addr = modeAddress(Input.pageId); 
  memReq2.data = modeWrite(memRep.data, Input.pageId);
  memRep = mem(memReq2); 
  //rank = Input.rankUpdate; 
  State = GS_WRITE_RANK; 
}

GS_READ_RANK()
{
  mem_req_t memReq3;
  memReq3.rw = READ;
  memReq3.size = DWORD;
  memReq3.addr = rankAddress(Input.pageId);
  memRep = mem(memReq3); 
  rank = memRep.data;
  State = GS_ADD_RANK; 
}

GS_ADD_RANK()
{
  FU_in_t addIn;
  FU_out_t addOut;

  addIn.in1 = rank; 
  addIn.in2 = Input.rankUpdate; 
  addOut = add(addIn); 
  rank = addOut.out;
  State = GS_WRITE_RANK; 
}

GS_WRITE_RANK()
{
  mem_req_t memReq4;
  memReq4.rw = WRITE;
  memReq4.size = DWORD;
  memReq4.addr = rankAddress(Input.pageId);
  memReq4.data = rank;
  memRep = mem(memReq4); 
  State = GS_UNLOCK_PAGE;
}

GS_UNLOCK_PAGE() 
{
  lock_req_t lock_req2;
  lock_rep_t lock_rep2;
  lock_req2.id =  Input.pageId;
  lock_req2.op = UNLOCK;
  lock_rep2 = lock(lock_req2);
  EmitReturnState = WaitForInputValid; 
  emit();
}

GS_FINISH() 
{
  mode = 1-mode;
  finish();
}
