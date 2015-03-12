#pragma INPUT FU_in_t 
#pragma OUTPUT FU_out_t
//Assume latency of 32 at 298Mhz for 32 bits fully pipelined floating point divider 
//http://www.xilinx.com/support/documentation/ip_documentation/div_gen_ds530.pdf
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
 State = GS_step5;
}

GS_step5()
{
 State = GS_step6;
}

GS_step6()
{
 State = GS_step7;
}

GS_step7()
{
 State = GS_step8;
}

GS_step8()
{
 State = GS_step9;
}

GS_step9()
{
 State = GS_step10;
}

GS_step10()
{
 State = GS_step11;
}

GS_step11()
{
 State = GS_step12;
}

GS_step12()
{
  finish();
}
