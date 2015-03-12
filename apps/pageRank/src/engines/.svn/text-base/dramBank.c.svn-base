#pragma INPUT uint32_t
#pragma OUTPUT uint32_t

int counter;
int addr;
GS_step1()
{
  counter = 10;
  State = GS_step2;
  Output = Input + 1; 
}

GS_step2()
{
  counter = counter - 1;
  if (counter == 0) { 
    State = GS_finish;
  }
}

GS_finish()
{
  finish();
}
