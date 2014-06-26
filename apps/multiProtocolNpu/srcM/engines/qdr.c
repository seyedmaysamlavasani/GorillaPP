#pragma INPUT uint32_t
#pragma OUTPUT uint32_t

GS_QDR_EMULATE () {
  if (Input == 0) {
    Output = 0x100;
  }
  if (Input == 1) {
    Output = 0x100;
  }
  if (Input == 2) {
    Output = 0x100;
  }
  if (Input == 3) {
    Output = 0x100;
  }
  if (Input == 4) {
    Output = 0x100;
  }
  if (Input == 0x100) {
    Output = 0x01000000;
  }
  if (Input == 0x101) {
    Output = 0x02000000;
  }
  if (Input == 0x102) {
    Output = 0x02000000;
  }
  if (Input == 0x103) {
    Output = 0x01000000;
  }
  if (Input == 0x104) {
    Output = 0x01000000;
  }
  if (Input == 0x01000000) {
    Output = 0x04;
  }
  if (Input == 0x02000000) {
    Output = 0x06;
  }
  State = GS_QDR_EMULATE_1;
}

GS_QDR_EMULATE_1 () {
  finish();
}
