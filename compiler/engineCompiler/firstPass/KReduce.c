#pragma INPUT KEngineIO_t 
#pragma OUTPUT KEngineIO_t
#pragma OFFLOAD (partialAccumulatorMem, memReq192_t , memRep192_t)
#pragma OFFLOAD (add, FU_in_t, FU_out_t)

//Reducer is getting  partial sum of points for each 
//centeroid along the corresponding index

memRep192_t pMemOut;
point_t memData;

int centeroidIndex = 0;

GS_RECEIVE() {
  memReq192_t pMemIn1;

  pMemIn1.addr = Input.centeroidIndex; 
  pMemIn1.rw = READ;
  pMemOut = partialAccumulatorMem(pMemIn1);
  State = GS_ADD_X;
}

GS_ADD_X() {
  FU_in_t addIn1;
  FU_out_t addOut1;

  memData = (point_t) pMemOut.rData;
  addIn1.in1 = memData.x;
  addIn1.in2 = Input.point.x;


  addOut1 = add(addIn1);
  memData.x = addOut1.out;
  State = GS_ADD_Y;
}

GS_ADD_Y() {
  FU_in_t addIn2;
  FU_out_t addOut2;

  addIn2.in1 = memData.y;
  addIn2.in2 = Input.point.y;

  addOut2 = add(addIn2);
  memData.y  = addOut2.out;
  State = GS_ADD_Z;
}

GS_ADD_Z() {
  FU_in_t addIn3;
  FU_out_t addOut3;

  addIn3.in1 = memData.z;
  addIn3.in2 = Input.point.z;

  addOut3 = add(addIn3);
  memData.z  = addOut3.out;

  State = GS_WRITE;
}

GS_WRITE() {
  memReq192_t pMemIn2;
  int finishCenteroidIndex;

  pMemIn2.addr = Input.centeroidIndex; 
  pMemIn2.rw = WRITE;
  pMemIn2.wData = (uint192_t) memData;
  pMemOut = partialAccumulatorMem(pMemIn2);
  finishCenteroidIndex = NUM_OF_CENTEROIDS - 1;
  if (Input.centeroidIndex == finishCenteroidIndex) {
    State = GS_SEND;
    centeroidIndex = 0;
  } else {
    State = WaitForInputValid; 
  }
}

GS_SEND() {
  memReq192_t pMemIn3;

  pMemIn3.addr = centeroidIndex; 
  pMemIn3.rw = READ;
  pMemOut = partialAccumulatorMem(pMemIn3);
  Output.point = (point_t) pMemOut.rData;
  centeroidIndex = centeroidIndex + 1;
  if (centeroidIndex == NUM_OF_CENTEROIDS) {
    centeroidIndex = 0;
    EmitReturnState = WaitForInputValid;
  } else {
    EmitReturnState = GS_SEND;
  }
  emit();
}
