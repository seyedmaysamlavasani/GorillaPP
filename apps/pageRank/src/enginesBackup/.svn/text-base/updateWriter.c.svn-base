#pragma INPUT  update_t
#pragma OUTPUT prDone_t
#pragma OFFLOAD (mem, mem_req_t, mem_rep_t)
#pragma OFFLOAD (lock, lock_req_t, lock_rep_t)

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

//First lock the page. Locking is done on a big enough granularity making sure that there is no 
//inconsistency at any of the data even cache line granularity
GS_LOCK_PAGE() 
{
  lock_in_t lock_in;
  lock_out_t lock_out;

  lock_in.id =  Input.pageId;
  lock_in.op = LOCK;
  lock_out = lock(lock_in);
  State = GS_READ_MODE;
}

//Then see if page rank is initialized yet
//If not, zero it out. If yest, go to add. 
GS_READ_MODE()
{
  bit oldMode;
  mem_req_t memReq;
  memReq.rw = READ;
  memReq.size = WORD;
  memReq.addr = modeAddress(Input.pageId); 
  memRep = mem(memReq); 
  oldMode = modeRead(memRep.data);
  if (oldMode ^ mode) {
    State = GS_READ_RANK; 
  } else {
    State = GS_CHANGE_MODE; 
  }
}

GS_CHANGE_MODE()
{
  mem_req_t memReq;
  memReq.rw = WRITE;
  memReq.size = WORD;
  memReq.addr = modeAddress(Input.pageId) 
  memReq.data = modeChange(memRep.data, Input.pageId)
  memRep = mem(memReq); 
  rank = Input.rankUpdate; 
  State = GS_WRITE_RANK; 
}

GS_READ_RANK()
{
  mem_req_t memReq;
  memReq.rw = READ;
  memReq.size = DWORD;
  memReq.addr = rankAddress(Input.pageId);
  memRep = mem(memReq); 
  rank = memRep.data;
  State = GS_ADD_PROB; 
}

GS_ADD_RANK()
{
  FU_in_t addIn;
  addIn.in1 = rank; 
  addIn.in2 = Input.rank; 
  addOut.out = add(addIn); 
  rank = addOut.out;
  State = GS_WRITE_RANK; 
}

GS_WRITE_RANK()
{
  mem_req_t memReq;
  memReq.rw = WRITE;
  memReq.size = DWORD;
  memReq.addr = rankAddress(Input.pageId);
  memRep = mem(memReq); 
  State = GS_UNLOCK_PAGE;
}

GS_UNLOCK_PAGE() 
{
  lock_in_t lock_in;
  lock_in.id =  Input.pageId;
  lock_in.op = UNLOCK;
  lock(Input.pageId);
  State = waitForInputValid; 
}

GS_FINISH() 
{
  mode = 1-mode;
  finish();
}
