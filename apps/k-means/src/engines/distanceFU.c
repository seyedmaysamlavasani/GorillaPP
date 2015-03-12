#pragma INPUT distanceFU_in_t 
#pragma OUTPUT distanceFU_out_t
#pragma OFFLOAD (sub1, FU_in_t, FU_out_t)
#pragma OFFLOAD (sub2, FU_in_t, FU_out_t)
#pragma OFFLOAD (sub3, FU_in_t, FU_out_t)
#pragma OFFLOAD (add1, FU_in_t, FU_out_t)
#pragma OFFLOAD (add2, FU_in_t, FU_out_t)
#pragma OFFLOAD (mul1, FU_in_t, FU_out_t)
#pragma OFFLOAD (mul2, FU_in_t, FU_out_t)
#pragma OFFLOAD (mul3, FU_in_t, FU_out_t)
#pragma OFFLOAD (sqrt, FU_in_t, FU_out_t)
#pragma CONCURRENT_SAFE

FU_out_t sub1Out, sub2Out, sub3Out;
FU_out_t mul1Out, mul2Out, mul3Out;
FU_out_t add1Out, add2Out;
FU_out_t sqrtOut;

GS_step1()
{
// Output.out = Input.in1.x + Input.in2.x + Input.in1.y + Input.in2.y + Input.in1.z + Input.in2.z;
  FU_in_t sub1In, sub2In, sub3In;
  sub1In.in1 = Input.in1.x;
  sub1In.in2 = Input.in2.x;
  sub2In.in1 = Input.in1.y;
  sub2In.in2 = Input.in2.y;
  sub3In.in1 = Input.in1.z;
  sub3In.in2 = Input.in2.z;
  sub1Out = sub1(sub1In);
  sub2Out = sub2(sub2In);
  sub3Out = sub3(sub3In);
  State = GS_step2;
}

GS_step2()
{
  FU_in_t mul1In, mul2In, mul3In;
  mul1In.in1 = sub1Out.out;
  mul1In.in2 = sub1Out.out;
  mul2In.in1 = sub2Out.out;
  mul2In.in2 = sub2Out.out;
  mul3In.in1 = sub3Out.out;
  mul3In.in2 = sub3Out.out;
  mul1Out = mul1(mul1In);
  mul2Out = mul2(mul2In);
  mul3Out = mul3(mul3In);
  State = GS_step3;
}

GS_step3()
{
  FU_in_t add1In;
  add1In.in1 = mul1Out.out;
  add1In.in2 = mul2Out.out;
  add1Out = add1(add1In);
  State = GS_step4;
}

GS_step4()
{
  FU_in_t add2In;
  add2In.in1 = add1Out.out;
  add2In.in2 = mul3Out.out;
  add2Out = add2(add2In);
  State = GS_step5;
}

GS_step5()
{
  FU_in_t sqrtIn;
  sqrtIn.in1 = add2Out.out;
  sqrtOut = sqrt(sqrtIn);
  State = GS_step6;
}

GS_step6()
{
  Output.out =  sqrtOut.out;
  finish();
}

