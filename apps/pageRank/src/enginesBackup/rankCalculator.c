#pragma INPUT rankCalc_in_t
#pragma OUTPUT rankCalc_out_t

int counter;

GS_step1()
{
//  Output.out = Input.damping + Input.rank + Input.fanoutDegree;
  Output.out = Input.damping + Input.rank + Input.fanoutDegree;
  counter = 5;
  State = GS_step2;
}
GS_step2()
{
  counter = counter - 1;
  if (counter == 0) 
    State = GS_finish;
}

GS_finish()
{
  finish();
}
