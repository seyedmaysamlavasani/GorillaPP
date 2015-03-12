#pragma INPUT rankCalc_in_t
#pragma OUTPUT rankCalc_out_t
#pragma OFFLOAD (mul, FU_in_t, FU_out_t)
#pragma OFFLOAD (div, FU_in_t, FU_out_t)
int counter;
FU_out_t divOut;

GS_step1()
{
  FU_in_t fuInputDiv;
  fuInputDiv.in1 = Input.rank;
  fuInputDiv.in2 = Input.fanoutDegree; 
  divOut = div(fuInputDiv); 
  State = GS_step2;
}


GS_step2()
{
  FU_in_t fuInputMul;
  FU_out_t fuOutputMul;

  fuInputMul.in1 = divOut.out;
  fuInputMul.in2 = Input.damping; 
  fuOutputMul = mul(fuInputMul);
  Output.out = fuOutputMul.out;
  finish();
}

