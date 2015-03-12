#pragma INPUT FU_in_t 
#pragma OUTPUT FU_out_t
GS_step1()
{
 Output.out = Input.in1 + Input.in2;
 State = GS_step2;
}

GS_step2()
{
 State = GS_step3;
}

GS_step3()
{
 State = GS_step4;
}

GS_step4()
{
  finish();
}


