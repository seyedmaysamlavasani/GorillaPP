#pragma INPUT  bulkUpdate_t
#pragma OUTPUT update_t
#pragma OFFLOAD (mem, mem_req_t, mem_rep_t)
#pragma OFFLOAD (rankCalc, rankCalc_in_t, rankCalc_out_t) 

bit mode = 1;
int pageId;
int length;
int linkId;
int linkIndex;
int fanoutDegree;
int outPageId;
double rank;
double rankUpdate;
mem_rep_t memRep;


GS_RECEIVE_ITERATION()
{
  pageId = Input.startPageId;
  length = Input.length;
  if (Input.done) {
    State = GS_FINISH;
  } else {
    State = GS_PROCESS_PAGE;
  }
}

GS_PROCESS_PAGE()
{
  //First, send out the contribution of DAMPING_FACTOR as 
  //the first update
  Output.done = false;
  Output.pageId = pageId;
  Output.rankUpdate = DAMPING_RANK; 
  EmitReturnState = GS_READ_RANK;
  emit();
}

GS_READ_RANK()
{
  mem_req_t memReq1;
  memReq1.cached = false;
  memReq1.rw = READ;
  memReq1.size = DWORD;
  memReq1.addr = rankAddress(pageId); 
  memRep = mem(memReq1); 
  rank = memRep.data;
  State = GS_READ_PAGE;
}

GS_READ_PAGE()
{
  mem_req_t memReq2;
  page_t page;

  memReq2.cached = false;
  memReq2.rw = READ;
  memReq2.size = DWORD;
  memReq2.addr = pageAddress(pageId); 
  memRep = mem(memReq2); 
  page.fanoutDegree = memRep.data; //If we say memRep the LHS will be unconnected
  linkId = memRep.data >> 32;
  //linkId = page.startLinkId;
  fanoutDegree = page.fanoutDegree;
  if (page.fanoutDegree == 0) {
    if (pageId == (Input.startPageId + Input.length - 1)) 
      State =  WaitForInputValid;
    else  {
      pageId = pageId + 1;
      State = GS_PROCESS_PAGE;
    }
  } else {
   State = GS_CALC_RANK;
  }
// State = GS_CALC_RANK; 
}

GS_CALC_RANK()
{
  rankCalc_in_t rankCalc_in;
  rankCalc_out_t rankCalc_out;

  rankCalc_in.damping = DAMPING_FACTOR;
  rankCalc_in.rank = rank;
  rankCalc_in.fanoutDegree = fanoutDegree;  
  rankCalc_out = rankCalc(rankCalc_in);
  rankUpdate = rankCalc_out.out;
  State = GS_READ_LINK;
  linkIndex = 0;
}

GS_READ_LINK()
{
  mem_req_t memReq3;
  memReq3.rw = READ_CACHED;
  memReq3.size = WORD;
  memReq3.addr = linkAddress(linkId); 
  memRep = mem(memReq3); 
  outPageId = memRep.data;
  State = GS_SEND;
}

GS_SEND()
{
  Output.pageId = outPageId;
  Output.rankUpdate = rankUpdate;
  if (linkIndex ==  fanoutDegree - 1) {
    if (pageId == (Input.startPageId +  Input.length - 1)) {
      EmitReturnState =  WaitForInputValid;
    } else {
      pageId = pageId + 1;
      EmitReturnState =  GS_PROCESS_PAGE;
    }
  } else {
    linkIndex = linkIndex + 1;
    EmitReturnState = GS_READ_LINK; 
  }
  emit();
}

GS_FINISH()
{
  Output.done = Input.done;
  mode = 1-mode;
  finish();
}
