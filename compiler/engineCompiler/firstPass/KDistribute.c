#pragma INPUT KEngineIO_t 
#pragma OUTPUT KEngineIO_t

int broadcastIndex = 0;
int mode = 0;
#define BROADCAST 0
#define UNICAST 1
//Input stream starts with a number of cnetroids
//Then a null message indicating the finish of centeroids
//Then points start
//At the end a message indicating the finish of points

GS_POINT() {
  Output = Input;
  if (mode == BROADCAST && NUM_OF_ENGINES != 1) {
    EmitReturnState = GS_BROADCAST;
    broadcastIndex = broadcastIndex + 1;
  } else {
    EmitReturnState = WaitForInputValid;
  } 
  if (Input.centeroidsFinished) {
    mode = UNICAST;
  }
  if (Input.pointsFinished) {
    mode = BROADCAST;
  }

  emit();
}

GS_BROADCAST() {
  if ( broadcastIndex >= NUM_OF_ENGINES - 1) {
    broadcastIndex = 0;
    EmitReturnState = WaitForInputValid;
  } else {
    broadcastIndex = broadcastIndex + 1;
    EmitReturnState = GS_BROADCAST;
  }
  emit();
}
