//#pragma INPUT uint32_t 
//#pragma OUTPUT uint32_t 
#pragma INPUT testStruct_t 
#pragma OUTPUT testStruct_t 

#pragma OFFLOAD (incrementFactor, uint32_t, uint32_t) 
#pragma CONCURRENT_SAFE

testStruct_t temp;
int counter;

GS_INITIALIZE ()
{
  counter = 10;
  State = GS_COUNT; 
}

GS_COUNT ()
{
  if (counter == 0) {
    State = GS_GET_INC_FACTOR;
  } 
  counter = counter - 1;
}

GS_GET_INC_FACTOR ()
{
  int incF;
  
  temp.a =2;
  incF = 1;
  incF = incrementFactor(Input.a);
  //temp = Input + incF;
  temp.a = Input.a + incF;
  incF =  temp.a;
  //Output = incF;
  Output.a = incF;
  finish();
}


