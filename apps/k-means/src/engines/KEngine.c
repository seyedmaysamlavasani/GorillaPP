#pragma INPUT KEngineIO_t
#pragma OUTPUT KEngineIO_t
#pragma OFFLOAD (distanceFU, distanceFU_in_t, distanceFU_out_t)
#pragma OFFLOAD (centeroidMem, memReq192_t , memRep192_t)
#pragma OFFLOAD (partialAccumulatorMem, memReq192_t , memRep192_t)
#pragma OFFLOAD (add, FU_in_t, FU_out_t)

#pragma CONCURRENT_SAFE

#define RECEIVE_CENTROID 0
#define RECEIVE_POINT 1

double closestDistance;
int centeroidIndex = 0;
int closestIndex;
shared int mode = 0;

memRep192_t pMemOut;
memRep192_t cMemOut;
point_t pMemData;
point_t cMemData;
point_t closestCentroid;

GS_RECEIVE() 
{
  if (mode == RECEIVE_CENTROID) {
    State = GS_RECEIVE_CENTROID;
  }
  if(Input.centeroidsFinished) {
    mode = RECEIVE_POINT;
    State = WaitForInputValid;
  }
  if(mode == RECEIVE_POINT) {
    State = GS_RECEIVE_POINT;
  }
  if (Input.pointsFinished) {
    mode = RECEIVE_CENTROID;
    State = GS_SEND_RESULTS_INIT;
  } 
}

GS_RECEIVE_CENTROID()
{
  memReq192_t pMemIn1;
  memReq192_t cMemIn1;
  point_t data;
  data.x = 0;
  data.y = 0;
  data.z = 0;
  pMemIn1.addr = centeroidIndex;
  pMemIn1.rw = WRITE;
  pMemIn1.wData = (uint192_t) data;
  cMemIn1.addr = centeroidIndex;
  cMemIn1.rw = WRITE;
  cMemIn1.wData = (uint192_t) Input.point;
  pMemOut = partialAccumulatorMem(pMemIn1);
  cMemOut = centeroidMem(cMemIn1);
  centeroidIndex = centeroidIndex + 1;
  State =  WaitForInputValid;
}

GS_RECEIVE_POINT() {
  closestDistance = INFINITI;
  centeroidIndex = 0;
  State = GS_READ_CENTROID;
}
  
GS_READ_CENTROID()
{
  memReq192_t cMemIn2;
  cMemIn2.addr = centeroidIndex;
  cMemIn2.rw = READ;
  cMemOut = centeroidMem(cMemIn2);
  State = GS_DISTANCE;
}

GS_DISTANCE()
{
  distanceFU_out_t d;
  distanceFU_in_t dfuin;

  dfuin.in1 = (point_t) cMemOut.rData;
  dfuin.in2 = Input.point;

  d = distanceFU(dfuin);
  if ( d.out < closestDistance) { //double comparision
    closestDistance = d.out;
    closestIndex = centeroidIndex;
    closestCentroid = (point_t) cMemOut.rData;
  } 
  centeroidIndex = centeroidIndex + 1;
  if (centeroidIndex == NUM_OF_CENTEROIDS) {
    State = GS_UPDATE_PARTIAL_ACCUMULATE_READ; 
  } else {
    State = GS_READ_CENTROID;
  }
}

GS_UPDATE_PARTIAL_ACCUMULATE_READ()
{
  memReq192_t pMemIn2;
  pMemIn2.addr = closestIndex;
  pMemIn2.rw =  READ;
  pMemOut = partialAccumulatorMem(pMemIn2);
  pMemData = (point_t) pMemOut.rData;
  State = GS_UPDATE_PARTIAL_ACCUMULATE_ADD_X;
}

GS_UPDATE_PARTIAL_ACCUMULATE_ADD_X()
{
  FU_in_t addIn1;
  FU_out_t addOut1;

  
  addIn1.in1 = pMemData.x;
  addIn1.in2 = closestCentroid.x;

  addOut1 = add(addIn1);
  pMemData.x = addOut1.out;
  State = GS_UPDATE_PARTIAL_ACCUMULATE_ADD_Y;
}

GS_UPDATE_PARTIAL_ACCUMULATE_ADD_Y()
{
  FU_in_t addIn2;
  FU_out_t addOut2;

  addIn2.in1 = pMemData.y;
  addIn2.in2 = closestCentroid.y;

  addOut2 = add(addIn2);
  pMemData.y = addOut2.out;
  State = GS_UPDATE_PARTIAL_ACCUMULATE_ADD_Z;
}

GS_UPDATE_PARTIAL_ACCUMULATE_ADD_Z()
{
  FU_in_t addIn3;
  FU_out_t addOut3;

  addIn3.in1 = pMemData.z;
  addIn3.in2 = closestCentroid.z;

  addOut3 = add(addIn3);
  pMemData.z = addOut3.out;
  State = GS_UPDATE_PARTIAL_ACCUMULATE_WRITE;
}

GS_UPDATE_PARTIAL_ACCUMULATE_WRITE()
{
  memReq192_t pMemIn3;

  pMemIn3.addr = closestIndex;
  //pMemIn3.rw = WRITE; 
  pMemIn3.rw = 1; 
  pMemIn3.wData = (uint192_t) pMemData;
  pMemOut = partialAccumulatorMem(pMemIn3);
  State = WaitForInputValid;
}

GS_SEND_RESULTS_INIT()
{
   centeroidIndex = 0; 
   State = GS_SEND_RESULTS;
}

GS_SEND_RESULTS()
{
  memReq192_t pMemIn4;

  pMemIn4.addr = centeroidIndex;
  pMemIn4.rw = READ;
  pMemOut = partialAccumulatorMem(pMemIn4);
  Output.point = (point_t) pMemOut.rData;
  Output.centeroidIndex = centeroidIndex;
  centeroidIndex = centeroidIndex + 1;
  if (centeroidIndex == NUM_OF_CENTEROIDS) {
    centeroidIndex = 0;
    State = WaitForInputValid;
  }  else {
    State = GS_EMIT;
  }
}

GS_EMIT()
{
  EmitReturnState = GS_SEND_RESULTS;
  emit();
}
