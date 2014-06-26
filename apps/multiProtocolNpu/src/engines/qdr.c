#pragma INPUT memReq32_t 
#pragma OUTPUT memRep32_t 

GS_QDR_EMULATE () {
  if (Input.addr == 0) {
    Output.rData = 0x100;
  }
  if (Input.addr == 1) {
    Output.rData = 0x100;
  }
  if (Input.addr == 2) {
    Output.rData = 0x100;
  }
  if (Input.addr == 3) {
    Output.rData = 0x100;
  }
  if (Input.addr == 4) {
    Output.rData = 0x100;
  }
  if (Input.addr == 0x100) {
    Output.rData = 0x01000000;
  }
  if (Input.addr == 0x101) {
    Output.rData = 0x02000000;
  }
  if (Input.addr == 0x102) {
    Output.rData = 0x02000000;
  }
  if (Input.addr == 0x103) {
    Output.rData = 0x01000000;
  }
  if (Input.addr == 0x104) {
    Output.rData = 0x01000000;
  }
  if (Input.addr == 0x01000000) {
    Output.rData = 0x04;
  }
  if (Input.addr == 0x02000000) {
    Output.rData = 0x06;
  }
  State = GS_QDR_EMULATE_1;
}

GS_QDR_EMULATE_1 () {
  finish();
}
