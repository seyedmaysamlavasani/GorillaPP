#pragma INPUT pointMemInput_t 
#pragma OUTPUT pointMemOutput_t


GS_step1()
{
  Output.data.x = 1;
  Output.data.y = 2;
  Output.data.z = 3;
  finish();
}
