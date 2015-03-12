#pragma INPUT  bulkUpdate_t
#pragma OUTPUT  bulkUpdate_t

int finishCounter;
int pageId;

GS_RECEIVE()
{
  finishCounter = 0;
  pageId = 0;
  State = GS_SEND;
}

GS_SEND()
{
  Output.done = false;
  Output.startPageId = pageId;
  if (Input.length + Input.startPageId < pageId + UPDATE_CHUNK_SIZE) {
    Output.length = (pageId >> 1) + UPDATE_CHUNK_SIZE - Input.length - Input.startPageId;
    EmitReturnState = GS_FINISH; 
  } else {
    Output.length = UPDATE_CHUNK_SIZE;
    EmitReturnState = GS_SEND;
    pageId = pageId + Input.length;
  }
  emit();
}

GS_FINISH()
{
  Output.done = true;
  if (finishCounter >= NUM_OF_UPDATE_GENERATORS - 1) {
    EmitReturnState = WaitForInputValid;
  } else {
    finishCounter = finishCounter + 1;
    EmitReturnState = GS_FINISH;
    EmitReturnState = WaitForInputValid;
  } 
  emit();
}
