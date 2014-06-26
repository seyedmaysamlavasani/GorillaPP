module IncThroughOffloadgComponent(input clk, input reset,
    output io_in_ready,
    input  io_in_valid,
    input [31:0] io_in_bits,
    input  io_out_ready,
    output io_out_valid,
    output[31:0] io_out_bits,
    input  pcIn0_valid,
    input  pcIn0_bits_request,
    input [15:0] pcIn0_bits_moduleId,
    input [7:0] pcIn0_bits_portId,
    input [15:0] pcIn0_bits_pcValue,
    input [3:0] pcIn0_bits_pcType,
    output io_pcOut_valid,
    output io_pcOut_bits_request,
    output[15:0] io_pcOut_bits_moduleId,
    output[7:0] io_pcOut_bits_portId,
    output[15:0] io_pcOut_bits_pcValue,
    output[3:0] io_pcOut_bits_pcType,
    input  io_off_constOff_req_ready,
    output io_off_constOff_req_valid,
    output[31:0] io_off_constOff_req_bits,
    output io_off_constOff_rep_ready,
    input  io_off_constOff_rep_valid,
    input [31:0] io_off_constOff_rep_bits);

  reg[0:0] R0;
  wire T1;
  wire T2;
  wire T3;
  wire T4;
  wire T5;
  wire T6;
  wire T7;
  reg[15:0] R8;
  wire T9;
  wire[15:0] T10;
  wire T11;
  reg[0:0] R12;
  wire T13;
  wire T14;
  wire T15;
  reg[3:0] R16;
  wire T17;
  wire[3:0] T18;
  wire T19;
  wire T20;
  reg[2:0] state;
  wire T21;
  wire T22;
  wire T23;
  wire T24;
  wire T25;
  wire T26;
  wire T27;
  wire T28;
  wire T29;
  wire offWire_rep_valid;
  wire offWire_req_valid;
  wire T30;
  wire offWire_rep_ready;
  wire T31;
  wire T32;
  wire T33;
  wire T34;
  wire offWire_req_ready;
  wire T35;
  wire T36;
  wire T37;
  wire T38;
  wire T39;
  wire T40;
  wire T41;
  wire[31:0] T42;
  reg[31:0] counter;
  wire T43;
  wire T44;
  wire T45;
  wire T46;
  wire T47;
  wire[31:0] T48;
  wire[31:0] T49;
  wire[31:0] T50;
  wire[31:0] T51;
  wire T52;
  wire T53;
  wire T54;
  wire T55;
  wire T56;
  wire T57;
  wire[2:0] T58;
  wire[2:0] T59;
  wire[2:0] T60;
  wire[2:0] T61;
  wire[2:0] T62;
  reg[7:0] R63;
  wire T64;
  wire[7:0] T65;
  wire T66;
  reg[15:0] R67;
  wire T68;
  wire[15:0] T69;
  wire[15:0] T70;
  wire[15:0] T71;
  reg[15:0] R72;
  wire T73;
  wire T74;
  wire T75;
  wire T76;
  wire T77;
  wire T78;
  wire T79;
  wire[3:0] T80;
  wire T81;
  wire[15:0] T82;
  wire[15:0] T83;
  wire[15:0] T84;
  reg[15:0] R85;
  wire T86;
  wire T87;
  wire T88;
  wire T89;
  wire T90;
  wire T91;
  wire T92;
  wire[3:0] T93;
  wire T94;
  wire[15:0] T95;
  wire[15:0] T96;
  wire[15:0] T97;
  wire T98;
  wire[15:0] T99;
  wire[31:0] T100;
  reg[31:0] offReg;
  wire[31:0] T101;
  wire[31:0] offWire_rep_bits;
  reg[31:0] inputReg;
  wire[31:0] T102;

  assign io_pcOut_bits_request = R0;
  assign T1 = T3 || T2;
  assign T2 = ! T3;
  assign T3 = T4 && pcIn0_bits_request;
  assign T4 = pcIn0_valid && T5;
  assign T5 = pcIn0_bits_moduleId == 16'h3/* 3*/;
  assign T6 = T2 ? pcIn0_bits_request : T7;
  assign T7 = T3 ? 1'h0/* 0*/ : R0;
  assign io_pcOut_bits_moduleId = R8;
  assign T9 = T3 || T2;
  assign T10 = T11 ? pcIn0_bits_moduleId : R8;
  assign T11 = T3 || T2;
  assign io_pcOut_valid = R12;
  assign T13 = T3 || T2;
  assign T14 = T2 ? pcIn0_valid : T15;
  assign T15 = T3 ? 1'h1/* 1*/ : R12;
  assign io_pcOut_bits_pcType = R16;
  assign T17 = T3 || T2;
  assign T18 = T19 ? pcIn0_bits_pcType : R16;
  assign T19 = T3 || T2;
  assign io_out_valid = T20;
  assign T20 = state == 3'h4/* 4*/;
  assign T21 = T39 || T22;
  assign T22 = T25 && T23;
  assign T23 = T24 && io_out_ready;
  assign T24 = state == 3'h4/* 4*/;
  assign T25 = ! T26;
  assign T26 = T28 || T27;
  assign T27 = state == 3'h3/* 3*/;
  assign T28 = T33 || T29;
  assign T29 = T32 && offWire_rep_valid;
  assign offWire_rep_valid = io_off_constOff_rep_valid;
  assign io_off_constOff_req_valid = offWire_req_valid;
  assign offWire_req_valid = T30;
  assign T30 = state == 3'h1/* 1*/;
  assign io_off_constOff_rep_ready = offWire_rep_ready;
  assign offWire_rep_ready = T31;
  assign T31 = state != 3'h4/* 4*/;
  assign T32 = state == 3'h2/* 2*/;
  assign T33 = T36 || T34;
  assign T34 = T35 && offWire_req_ready;
  assign offWire_req_ready = io_off_constOff_req_ready;
  assign T35 = state == 3'h1/* 1*/;
  assign T36 = T38 && io_in_valid;
  assign io_in_ready = T37;
  assign T37 = state == 3'h0/* 0*/;
  assign T38 = state == 3'h0/* 0*/;
  assign T39 = T52 || T40;
  assign T40 = T46 && T41;
  assign T41 = counter == T42;
  assign T42 = {31'h0/* 0*/, 1'h0/* 0*/};
  assign T43 = T40 || T44;
  assign T44 = T46 && T45;
  assign T45 = ! T41;
  assign T46 = T47 && T27;
  assign T47 = ! T28;
  assign T48 = T44 ? T50 : T49;
  assign T49 = T40 ? 32'h2/* 2*/ : counter;
  assign T50 = counter - T51;
  assign T51 = {31'h0/* 0*/, 1'h1/* 1*/};
  assign T52 = T55 || T53;
  assign T53 = T54 && T29;
  assign T54 = ! T33;
  assign T55 = T36 || T56;
  assign T56 = T57 && T34;
  assign T57 = ! T36;
  assign T58 = T22 ? 3'h0/* 0*/ : T59;
  assign T59 = T40 ? 3'h4/* 4*/ : T60;
  assign T60 = T53 ? 3'h3/* 3*/ : T61;
  assign T61 = T56 ? 3'h2/* 2*/ : T62;
  assign T62 = T36 ? 3'h1/* 1*/ : state;
  assign io_pcOut_bits_portId = R63;
  assign T64 = T3 || T2;
  assign T65 = T66 ? pcIn0_bits_portId : R63;
  assign T66 = T3 || T2;
  assign io_pcOut_bits_pcValue = R67;
  assign T68 = T3 || T2;
  assign T69 = T2 ? pcIn0_bits_pcValue : T70;
  assign T70 = T3 ? T71 : R67;
  assign T71 = T98 ? R85 : R72;
  assign T73 = T78 || T74;
  assign T74 = T77 && T75;
  assign T75 = io_out_valid && T76;
  assign T76 = ! io_out_ready;
  assign T77 = ! T78;
  assign T78 = T81 && T79;
  assign T79 = pcIn0_bits_pcType == T80;
  assign T80 = {2'h0/* 0*/, 2'h2/* 2*/};
  assign T81 = pcIn0_valid && pcIn0_bits_request;
  assign T82 = T74 ? T84 : T83;
  assign T83 = T78 ? 16'h0/* 0*/ : R72;
  assign T84 = R72 + 16'h1/* 1*/;
  assign T86 = T91 || T87;
  assign T87 = T90 && T88;
  assign T88 = io_in_valid && T89;
  assign T89 = ! io_in_ready;
  assign T90 = ! T91;
  assign T91 = T94 && T92;
  assign T92 = pcIn0_bits_pcType == T93;
  assign T93 = {2'h0/* 0*/, 2'h2/* 2*/};
  assign T94 = pcIn0_valid && pcIn0_bits_request;
  assign T95 = T87 ? T97 : T96;
  assign T96 = T91 ? 16'h0/* 0*/ : R85;
  assign T97 = R85 + 16'h1/* 1*/;
  assign T98 = T99 == 16'h1/* 1*/;
  assign T99 = {8'h0/* 0*/, pcIn0_bits_portId};
  assign io_out_bits = T100;
  assign T100 = inputReg + offReg;
  assign T101 = T53 ? offWire_rep_bits : offReg;
  assign offWire_rep_bits = io_off_constOff_rep_bits;
  assign T102 = T36 ? io_in_bits : inputReg;

  always @(posedge clk) begin
    if(reset) begin
      R0 <= 1'h1/* 1*/;
    end else if(T1) begin
      R0 <= T6;
    end
    if(reset) begin
      R8 <= 16'h0/* 0*/;
    end else if(T9) begin
      R8 <= T10;
    end
    if(reset) begin
      R12 <= 1'h0/* 0*/;
    end else if(T13) begin
      R12 <= T14;
    end
    if(reset) begin
      R16 <= 4'h0/* 0*/;
    end else if(T17) begin
      R16 <= T18;
    end
    if(reset) begin
      state <= 3'h0/* 0*/;
    end else if(T21) begin
      state <= T58;
    end
    if(reset) begin
      counter <= 32'h2/* 2*/;
    end else if(T43) begin
      counter <= T48;
    end
    if(reset) begin
      R63 <= 8'h0/* 0*/;
    end else if(T64) begin
      R63 <= T65;
    end
    if(reset) begin
      R67 <= 16'h0/* 0*/;
    end else if(T68) begin
      R67 <= T69;
    end
    if(reset) begin
      R72 <= 16'h0/* 0*/;
    end else if(T73) begin
      R72 <= T82;
    end
    if(reset) begin
      R85 <= 16'h0/* 0*/;
    end else if(T86) begin
      R85 <= T95;
    end
    if(reset) begin
      offReg <= 32'h0/* 0*/;
    end else if(T53) begin
      offReg <= T101;
    end
    if(reset) begin
      inputReg <= 32'h0/* 0*/;
    end else if(T36) begin
      inputReg <= T102;
    end
  end
endmodule

module IncThroughOffloadgComponent_1(input clk, input reset,
    output io_in_ready,
    input  io_in_valid,
    input [31:0] io_in_bits,
    input  io_out_ready,
    output io_out_valid,
    output[31:0] io_out_bits,
    input  io_pcIn_valid,
    input  io_pcIn_bits_request,
    input [15:0] io_pcIn_bits_moduleId,
    input [7:0] io_pcIn_bits_portId,
    input [15:0] io_pcIn_bits_pcValue,
    input [3:0] io_pcIn_bits_pcType,
    output pcOutN_valid,
    output pcOutN_bits_request,
    output[15:0] pcOutN_bits_moduleId,
    output[7:0] pcOutN_bits_portId,
    output[15:0] pcOutN_bits_pcValue,
    output[3:0] pcOutN_bits_pcType,
    input  io_off_constOff_req_ready,
    output io_off_constOff_req_valid,
    output[31:0] io_off_constOff_req_bits,
    output io_off_constOff_rep_ready,
    input  io_off_constOff_rep_valid,
    input [31:0] io_off_constOff_rep_bits);

  reg[0:0] R0;
  wire T1;
  wire T2;
  wire T3;
  wire T4;
  wire T5;
  wire T6;
  wire T7;
  reg[15:0] R8;
  wire T9;
  wire[15:0] T10;
  wire T11;
  reg[0:0] R12;
  wire T13;
  wire T14;
  wire T15;
  reg[3:0] R16;
  wire T17;
  wire[3:0] T18;
  wire T19;
  wire offWire_rep_ready;
  wire T20;
  reg[2:0] state;
  wire T21;
  wire T22;
  wire T23;
  wire T24;
  wire offWire_req_valid;
  wire T25;
  wire T26;
  wire T27;
  wire T28;
  wire T29;
  wire T30;
  wire T31;
  wire T32;
  wire offWire_rep_valid;
  wire T33;
  wire T34;
  wire T35;
  wire offWire_req_ready;
  wire T36;
  wire T37;
  wire T38;
  wire T39;
  wire T40;
  wire T41;
  wire[31:0] T42;
  reg[31:0] counter;
  wire T43;
  wire T44;
  wire T45;
  wire T46;
  wire T47;
  wire[31:0] T48;
  wire[31:0] T49;
  wire[31:0] T50;
  wire[31:0] T51;
  wire T52;
  wire T53;
  wire T54;
  wire T55;
  wire T56;
  wire T57;
  wire[2:0] T58;
  wire[2:0] T59;
  wire[2:0] T60;
  wire[2:0] T61;
  wire[2:0] T62;
  reg[7:0] R63;
  wire T64;
  wire[7:0] T65;
  wire T66;
  reg[15:0] R67;
  wire T68;
  wire[15:0] T69;
  wire[15:0] T70;
  wire[15:0] T71;
  reg[15:0] R72;
  wire T73;
  wire T74;
  wire T75;
  wire T76;
  wire T77;
  wire T78;
  wire T79;
  wire[3:0] T80;
  wire T81;
  wire[15:0] T82;
  wire[15:0] T83;
  wire[15:0] T84;
  reg[15:0] R85;
  wire T86;
  wire T87;
  wire T88;
  wire T89;
  wire T90;
  wire T91;
  wire T92;
  wire[3:0] T93;
  wire T94;
  wire[15:0] T95;
  wire[15:0] T96;
  wire[15:0] T97;
  wire T98;
  wire[15:0] T99;
  wire[31:0] T100;
  reg[31:0] offReg;
  wire[31:0] T101;
  wire[31:0] offWire_rep_bits;
  reg[31:0] inputReg;
  wire[31:0] T102;

  assign pcOutN_bits_request = R0;
  assign T1 = T3 || T2;
  assign T2 = ! T3;
  assign T3 = T4 && io_pcIn_bits_request;
  assign T4 = io_pcIn_valid && T5;
  assign T5 = io_pcIn_bits_moduleId == 16'h4/* 4*/;
  assign T6 = T2 ? io_pcIn_bits_request : T7;
  assign T7 = T3 ? 1'h0/* 0*/ : R0;
  assign pcOutN_bits_moduleId = R8;
  assign T9 = T3 || T2;
  assign T10 = T11 ? io_pcIn_bits_moduleId : R8;
  assign T11 = T3 || T2;
  assign pcOutN_valid = R12;
  assign T13 = T3 || T2;
  assign T14 = T2 ? io_pcIn_valid : T15;
  assign T15 = T3 ? 1'h1/* 1*/ : R12;
  assign pcOutN_bits_pcType = R16;
  assign T17 = T3 || T2;
  assign T18 = T19 ? io_pcIn_bits_pcType : R16;
  assign T19 = T3 || T2;
  assign io_off_constOff_rep_ready = offWire_rep_ready;
  assign offWire_rep_ready = T20;
  assign T20 = state != 3'h4/* 4*/;
  assign T21 = T39 || T22;
  assign T22 = T28 && T23;
  assign T23 = T27 && io_out_ready;
  assign io_out_valid = T24;
  assign T24 = state == 3'h4/* 4*/;
  assign io_off_constOff_req_valid = offWire_req_valid;
  assign offWire_req_valid = T25;
  assign T25 = state == 3'h1/* 1*/;
  assign io_in_ready = T26;
  assign T26 = state == 3'h0/* 0*/;
  assign T27 = state == 3'h4/* 4*/;
  assign T28 = ! T29;
  assign T29 = T31 || T30;
  assign T30 = state == 3'h3/* 3*/;
  assign T31 = T34 || T32;
  assign T32 = T33 && offWire_rep_valid;
  assign offWire_rep_valid = io_off_constOff_rep_valid;
  assign T33 = state == 3'h2/* 2*/;
  assign T34 = T37 || T35;
  assign T35 = T36 && offWire_req_ready;
  assign offWire_req_ready = io_off_constOff_req_ready;
  assign T36 = state == 3'h1/* 1*/;
  assign T37 = T38 && io_in_valid;
  assign T38 = state == 3'h0/* 0*/;
  assign T39 = T52 || T40;
  assign T40 = T46 && T41;
  assign T41 = counter == T42;
  assign T42 = {31'h0/* 0*/, 1'h0/* 0*/};
  assign T43 = T40 || T44;
  assign T44 = T46 && T45;
  assign T45 = ! T41;
  assign T46 = T47 && T30;
  assign T47 = ! T31;
  assign T48 = T44 ? T50 : T49;
  assign T49 = T40 ? 32'h2/* 2*/ : counter;
  assign T50 = counter - T51;
  assign T51 = {31'h0/* 0*/, 1'h1/* 1*/};
  assign T52 = T55 || T53;
  assign T53 = T54 && T32;
  assign T54 = ! T34;
  assign T55 = T37 || T56;
  assign T56 = T57 && T35;
  assign T57 = ! T37;
  assign T58 = T22 ? 3'h0/* 0*/ : T59;
  assign T59 = T40 ? 3'h4/* 4*/ : T60;
  assign T60 = T53 ? 3'h3/* 3*/ : T61;
  assign T61 = T56 ? 3'h2/* 2*/ : T62;
  assign T62 = T37 ? 3'h1/* 1*/ : state;
  assign pcOutN_bits_portId = R63;
  assign T64 = T3 || T2;
  assign T65 = T66 ? io_pcIn_bits_portId : R63;
  assign T66 = T3 || T2;
  assign pcOutN_bits_pcValue = R67;
  assign T68 = T3 || T2;
  assign T69 = T2 ? io_pcIn_bits_pcValue : T70;
  assign T70 = T3 ? T71 : R67;
  assign T71 = T98 ? R85 : R72;
  assign T73 = T78 || T74;
  assign T74 = T77 && T75;
  assign T75 = io_out_valid && T76;
  assign T76 = ! io_out_ready;
  assign T77 = ! T78;
  assign T78 = T81 && T79;
  assign T79 = io_pcIn_bits_pcType == T80;
  assign T80 = {2'h0/* 0*/, 2'h2/* 2*/};
  assign T81 = io_pcIn_valid && io_pcIn_bits_request;
  assign T82 = T74 ? T84 : T83;
  assign T83 = T78 ? 16'h0/* 0*/ : R72;
  assign T84 = R72 + 16'h1/* 1*/;
  assign T86 = T91 || T87;
  assign T87 = T90 && T88;
  assign T88 = io_in_valid && T89;
  assign T89 = ! io_in_ready;
  assign T90 = ! T91;
  assign T91 = T94 && T92;
  assign T92 = io_pcIn_bits_pcType == T93;
  assign T93 = {2'h0/* 0*/, 2'h2/* 2*/};
  assign T94 = io_pcIn_valid && io_pcIn_bits_request;
  assign T95 = T87 ? T97 : T96;
  assign T96 = T91 ? 16'h0/* 0*/ : R85;
  assign T97 = R85 + 16'h1/* 1*/;
  assign T98 = T99 == 16'h1/* 1*/;
  assign T99 = {8'h0/* 0*/, io_pcIn_bits_portId};
  assign io_out_bits = T100;
  assign T100 = inputReg + offReg;
  assign T101 = T53 ? offWire_rep_bits : offReg;
  assign offWire_rep_bits = io_off_constOff_rep_bits;
  assign T102 = T37 ? io_in_bits : inputReg;

  always @(posedge clk) begin
    if(reset) begin
      R0 <= 1'h1/* 1*/;
    end else if(T1) begin
      R0 <= T6;
    end
    if(reset) begin
      R8 <= 16'h0/* 0*/;
    end else if(T9) begin
      R8 <= T10;
    end
    if(reset) begin
      R12 <= 1'h0/* 0*/;
    end else if(T13) begin
      R12 <= T14;
    end
    if(reset) begin
      R16 <= 4'h0/* 0*/;
    end else if(T17) begin
      R16 <= T18;
    end
    if(reset) begin
      state <= 3'h0/* 0*/;
    end else if(T21) begin
      state <= T58;
    end
    if(reset) begin
      counter <= 32'h2/* 2*/;
    end else if(T43) begin
      counter <= T48;
    end
    if(reset) begin
      R63 <= 8'h0/* 0*/;
    end else if(T64) begin
      R63 <= T65;
    end
    if(reset) begin
      R67 <= 16'h0/* 0*/;
    end else if(T68) begin
      R67 <= T69;
    end
    if(reset) begin
      R72 <= 16'h0/* 0*/;
    end else if(T73) begin
      R72 <= T82;
    end
    if(reset) begin
      R85 <= 16'h0/* 0*/;
    end else if(T86) begin
      R85 <= T95;
    end
    if(reset) begin
      offReg <= 32'h0/* 0*/;
    end else if(T53) begin
      offReg <= T101;
    end
    if(reset) begin
      inputReg <= 32'h0/* 0*/;
    end else if(T37) begin
      inputReg <= T102;
    end
  end
endmodule

module RRDistributer(input clk, input reset,
    input  io_out_0_ready,
    output io_out_0_valid,
    output[31:0] io_out_0_bits,
    input  io_out_1_ready,
    output io_out_1_valid,
    output[31:0] io_out_1_bits,
    output io_in_ready,
    input  io_in_valid,
    input [31:0] io_in_bits,
    output io_chosen);

  wire T0;
  wire T1;
  wire T2;
  wire T3;
  wire T4;
  wire T5;
  reg[0:0] last_grant;
  wire T6;
  wire T7;
  wire T8;
  wire choose;
  wire T9;
  wire T10;
  wire T11;
  wire T12;
  wire T13;
  wire T14;
  wire T15;
  wire T16;
  wire T17;
  wire T18;
  wire T19;
  wire T20;
  wire T21;
  wire T22;

  assign io_out_0_valid = T0;
  assign T0 = T1 && io_in_valid;
  assign T1 = T14 || T2;
  assign T2 = ! T3;
  assign T3 = T12 || T4;
  assign T4 = io_out_1_ready && T5;
  assign T5 = 1'h1/* 1*/ > last_grant;
  assign T6 = io_in_valid && io_in_ready;
  assign io_in_ready = T7;
  assign T7 = io_out_0_ready || io_out_1_ready;
  assign T8 = T6 ? choose : last_grant;
  assign choose = T10 ? 1'h1/* 1*/ : T9;
  assign T9 = io_out_0_ready ? 1'h0/* 0*/ : 1'h1/* 1*/;
  assign T10 = io_out_1_ready && T11;
  assign T11 = 1'h1/* 1*/ > last_grant;
  assign T12 = io_out_0_ready && T13;
  assign T13 = 1'h0/* 0*/ > last_grant;
  assign T14 = 1'h0/* 0*/ > last_grant;
  assign io_out_1_valid = T15;
  assign T15 = T16 && io_in_valid;
  assign T16 = T20 || T17;
  assign T17 = ! T18;
  assign T18 = T19 || io_out_0_ready;
  assign T19 = T12 || T4;
  assign T20 = T22 && T21;
  assign T21 = 1'h1/* 1*/ > last_grant;
  assign T22 = ! T12;
  assign io_out_1_bits = io_in_bits;
  assign io_out_0_bits = io_in_bits;

  always @(posedge clk) begin
    if(reset) begin
      last_grant <= 1'h0/* 0*/;
    end else if(T6) begin
      last_grant <= T8;
    end
  end
endmodule

module RRArbiter(input clk, input reset,
    output io_in_0_ready,
    input  io_in_0_valid,
    input [31:0] io_in_0_bits,
    output io_in_1_ready,
    input  io_in_1_valid,
    input [31:0] io_in_1_bits,
    input  io_out_ready,
    output io_out_valid,
    output[31:0] io_out_bits,
    output io_chosen);

  wire T0;
  wire T1;
  wire T2;
  wire T3;
  wire T4;
  wire T5;
  wire T6;
  wire T7;
  wire T8;
  wire T9;
  reg[0:0] last_grant;
  wire T10;
  wire T11;
  wire T12;
  wire choose;
  wire T13;
  wire T14;
  wire T15;
  wire T16;
  wire T17;
  wire T18;
  wire T19;
  wire T20;
  wire T21;
  wire T22;
  wire[31:0] T23;
  wire[31:0] T24;
  wire[31:0] T25;
  wire T26;
  wire[1:0] T27;
  wire[2:0] T28;
  wire[31:0] dvec_1;
  wire[31:0] T29;
  wire[31:0] T30;
  wire T31;
  wire[31:0] dvec_0;

  assign io_in_1_ready = T0;
  assign T0 = T1 && io_out_ready;
  assign T1 = T20 || T2;
  assign T2 = ! T3;
  assign T3 = T19 || io_in_0_valid;
  assign io_in_0_ready = T4;
  assign T4 = T5 && io_out_ready;
  assign T5 = T18 || T6;
  assign T6 = ! T7;
  assign T7 = T16 || T8;
  assign T8 = io_in_1_valid && T9;
  assign T9 = 1'h1/* 1*/ > last_grant;
  assign T10 = io_out_valid && io_out_ready;
  assign io_out_valid = T11;
  assign T11 = io_in_0_valid || io_in_1_valid;
  assign T12 = T10 ? choose : last_grant;
  assign choose = T14 ? 1'h1/* 1*/ : T13;
  assign T13 = io_in_0_valid ? 1'h0/* 0*/ : 1'h1/* 1*/;
  assign T14 = io_in_1_valid && T15;
  assign T15 = 1'h1/* 1*/ > last_grant;
  assign T16 = io_in_0_valid && T17;
  assign T17 = 1'h0/* 0*/ > last_grant;
  assign T18 = 1'h0/* 0*/ > last_grant;
  assign T19 = T16 || T8;
  assign T20 = T22 && T21;
  assign T21 = 1'h1/* 1*/ > last_grant;
  assign T22 = ! T16;
  assign io_out_bits = T23;
  assign T23 = T29 | T24;
  assign T24 = dvec_1 & T25;
  assign T25 = {6'h20/* 32*/{T26}};
  assign T26 = T27[1'h1/* 1*/];
  assign T27 = T28[1'h1/* 1*/:1'h0/* 0*/];
  assign T28 = 2'h1/* 1*/ << choose;
  assign dvec_1 = io_in_1_bits;
  assign T29 = dvec_0 & T30;
  assign T30 = {6'h20/* 32*/{T31}};
  assign T31 = T27[1'h0/* 0*/];
  assign dvec_0 = io_in_0_bits;

  always @(posedge clk) begin
    if(reset) begin
      last_grant <= 1'h0/* 0*/;
    end else if(T10) begin
      last_grant <= T12;
    end
  end
endmodule

module RRArbiter_1(input clk, input reset,
    output io_in_0_ready,
    input  io_in_0_valid,
    input [31:0] io_in_0_bits,
    output io_in_1_ready,
    input  io_in_1_valid,
    input [31:0] io_in_1_bits,
    input  io_out_ready,
    output io_out_valid,
    output[31:0] io_out_bits,
    output io_chosen);

  wire T0;
  wire T1;
  wire T2;
  wire T3;
  wire T4;
  wire T5;
  wire T6;
  reg[0:0] last_grant;
  wire T7;
  wire T8;
  wire choose;
  wire T9;
  wire T10;
  wire T11;
  wire T12;
  wire T13;
  wire T14;
  wire T15;
  wire T16;
  wire T17;
  wire T18;
  wire T19;
  wire T20;
  wire T21;
  wire T22;

  assign io_out_valid = T0;
  assign T0 = io_in_0_valid || io_in_1_valid;
  assign io_in_0_ready = T1;
  assign T1 = T2 && io_out_ready;
  assign T2 = T14 || T3;
  assign T3 = ! T4;
  assign T4 = T12 || T5;
  assign T5 = io_in_1_valid && T6;
  assign T6 = 1'h1/* 1*/ > last_grant;
  assign T7 = io_out_valid && io_out_ready;
  assign T8 = T7 ? choose : last_grant;
  assign choose = T10 ? 1'h1/* 1*/ : T9;
  assign T9 = io_in_0_valid ? 1'h0/* 0*/ : 1'h1/* 1*/;
  assign T10 = io_in_1_valid && T11;
  assign T11 = 1'h1/* 1*/ > last_grant;
  assign T12 = io_in_0_valid && T13;
  assign T13 = 1'h0/* 0*/ > last_grant;
  assign T14 = 1'h0/* 0*/ > last_grant;
  assign io_in_1_ready = T15;
  assign T15 = T16 && io_out_ready;
  assign T16 = T20 || T17;
  assign T17 = ! T18;
  assign T18 = T19 || io_in_0_valid;
  assign T19 = T12 || T5;
  assign T20 = T22 && T21;
  assign T21 = 1'h1/* 1*/ > last_grant;
  assign T22 = ! T12;

  always @(posedge clk) begin
    if(reset) begin
      last_grant <= 1'h0/* 0*/;
    end else if(T7) begin
      last_grant <= T8;
    end
  end
endmodule

module RRDistributer_1(input clk, input reset,
    input  io_out_0_ready,
    output io_out_0_valid,
    output[31:0] io_out_0_bits,
    input  io_out_1_ready,
    output io_out_1_valid,
    output[31:0] io_out_1_bits,
    output io_in_ready,
    input  io_in_valid,
    input [31:0] io_in_bits,
    output io_chosen);

  wire T0;
  wire T1;
  wire T2;
  wire T3;
  wire T4;
  wire T5;
  wire T6;
  reg[0:0] last_grant;
  wire T7;
  wire T8;
  wire choose;
  wire T9;
  wire T10;
  wire T11;
  wire T12;
  wire T13;
  wire T14;
  wire T15;
  wire T16;
  wire T17;
  wire T18;
  wire T19;
  wire T20;
  wire T21;
  wire T22;

  assign io_in_ready = T0;
  assign T0 = io_out_0_ready || io_out_1_ready;
  assign io_out_0_valid = T1;
  assign T1 = T2 && io_in_valid;
  assign T2 = T14 || T3;
  assign T3 = ! T4;
  assign T4 = T12 || T5;
  assign T5 = io_out_1_ready && T6;
  assign T6 = 1'h1/* 1*/ > last_grant;
  assign T7 = io_in_valid && io_in_ready;
  assign T8 = T7 ? choose : last_grant;
  assign choose = T10 ? 1'h1/* 1*/ : T9;
  assign T9 = io_out_0_ready ? 1'h0/* 0*/ : 1'h1/* 1*/;
  assign T10 = io_out_1_ready && T11;
  assign T11 = 1'h1/* 1*/ > last_grant;
  assign T12 = io_out_0_ready && T13;
  assign T13 = 1'h0/* 0*/ > last_grant;
  assign T14 = 1'h0/* 0*/ > last_grant;
  assign io_out_1_valid = T15;
  assign T15 = T16 && io_in_valid;
  assign T16 = T20 || T17;
  assign T17 = ! T18;
  assign T18 = T19 || io_out_0_ready;
  assign T19 = T12 || T5;
  assign T20 = T22 && T21;
  assign T21 = 1'h1/* 1*/ > last_grant;
  assign T22 = ! T12;
  assign io_out_1_bits = io_in_bits;
  assign io_out_0_bits = io_in_bits;

  always @(posedge clk) begin
    if(reset) begin
      last_grant <= 1'h0/* 0*/;
    end else if(T7) begin
      last_grant <= T8;
    end
  end
endmodule

module gReplicatedComponent(input clk, input reset,
    output io_in_ready,
    input  io_in_valid,
    input [31:0] io_in_bits,
    input  io_out_ready,
    output io_out_valid,
    output[31:0] io_out_bits,
    input  io_pcIn_valid,
    input  io_pcIn_bits_request,
    input [15:0] io_pcIn_bits_moduleId,
    input [7:0] io_pcIn_bits_portId,
    input [15:0] io_pcIn_bits_pcValue,
    input [3:0] io_pcIn_bits_pcType,
    output io_pcOut_valid,
    output io_pcOut_bits_request,
    output[15:0] io_pcOut_bits_moduleId,
    output[7:0] io_pcOut_bits_portId,
    output[15:0] io_pcOut_bits_pcValue,
    output[3:0] io_pcOut_bits_pcType,
    input  mainOff_constOff_req_ready,
    output mainOff_constOff_req_valid,
    output[31:0] mainOff_constOff_req_bits,
    output mainOff_constOff_rep_ready,
    input  mainOff_constOff_rep_valid,
    input [31:0] mainOff_constOff_rep_bits);

  wire IncThroughOffloadgComponent_1_pcOutN_bits_request;
  wire IncThroughOffloadgComponent_io_pcOut_bits_request;
  wire[15:0] IncThroughOffloadgComponent_io_pcOut_bits_moduleId;
  wire IncThroughOffloadgComponent_io_pcOut_valid;
  wire[15:0] IncThroughOffloadgComponent_1_pcOutN_bits_moduleId;
  wire IncThroughOffloadgComponent_1_pcOutN_valid;
  wire[3:0] IncThroughOffloadgComponent_1_pcOutN_bits_pcType;
  wire[3:0] IncThroughOffloadgComponent_io_pcOut_bits_pcType;
  wire RRDistributer_io_in_ready;
  wire IncThroughOffloadgComponent_1_io_off_constOff_rep_ready;
  wire outputArb_io_in_1_ready;
  wire IncThroughOffloadgComponent_io_out_valid;
  wire outputArb_io_in_0_ready;
  wire IncThroughOffloadgComponent_1_io_out_valid;
  wire RRDistributer_io_out_0_valid;
  wire RRArbiter_io_out_valid;
  wire IncThroughOffloadgComponent_1_io_off_constOff_req_valid;
  wire IncThroughOffloadgComponent_io_off_constOff_req_valid;
  wire IncThroughOffloadgComponent_io_off_constOff_rep_ready;
  wire RRArbiter_io_in_0_ready;
  wire inputDist_io_out_0_valid;
  wire IncThroughOffloadgComponent_1_io_in_ready;
  wire IncThroughOffloadgComponent_io_in_ready;
  wire RRDistributer_io_out_1_valid;
  wire RRArbiter_io_in_1_ready;
  wire inputDist_io_out_1_valid;
  wire[7:0] IncThroughOffloadgComponent_1_pcOutN_bits_portId;
  wire[7:0] IncThroughOffloadgComponent_io_pcOut_bits_portId;
  wire[15:0] IncThroughOffloadgComponent_1_pcOutN_bits_pcValue;
  wire[15:0] IncThroughOffloadgComponent_io_pcOut_bits_pcValue;
  wire[31:0] outputArb_io_out_bits;
  wire[31:0] IncThroughOffloadgComponent_1_io_out_bits;
  wire[31:0] RRDistributer_io_out_1_bits;
  wire[31:0] inputDist_io_out_1_bits;
  wire[31:0] IncThroughOffloadgComponent_io_out_bits;
  wire[31:0] RRDistributer_io_out_0_bits;
  wire[31:0] inputDist_io_out_0_bits;
  wire outputArb_io_out_valid;
  wire inputDist_io_in_ready;

  assign io_pcOut_bits_request = IncThroughOffloadgComponent_1_pcOutN_bits_request;
  assign io_pcOut_bits_moduleId = IncThroughOffloadgComponent_1_pcOutN_bits_moduleId;
  assign io_pcOut_valid = IncThroughOffloadgComponent_1_pcOutN_valid;
  assign io_pcOut_bits_pcType = IncThroughOffloadgComponent_1_pcOutN_bits_pcType;
  assign mainOff_constOff_rep_ready = RRDistributer_io_in_ready;
  assign mainOff_constOff_req_valid = RRArbiter_io_out_valid;
  assign io_pcOut_bits_portId = IncThroughOffloadgComponent_1_pcOutN_bits_portId;
  assign io_pcOut_bits_pcValue = IncThroughOffloadgComponent_1_pcOutN_bits_pcValue;
  assign io_out_bits = outputArb_io_out_bits;
  assign io_out_valid = outputArb_io_out_valid;
  assign io_in_ready = inputDist_io_in_ready;
  IncThroughOffloadgComponent IncThroughOffloadgComponent(.clk(clk), .reset(reset),
       .io_in_ready( IncThroughOffloadgComponent_io_in_ready ),
       .io_in_valid( inputDist_io_out_0_valid ),
       .io_in_bits( inputDist_io_out_0_bits ),
       .io_out_ready( outputArb_io_in_0_ready ),
       .io_out_valid( IncThroughOffloadgComponent_io_out_valid ),
       .io_out_bits( IncThroughOffloadgComponent_io_out_bits ),
       .pcIn0_valid( io_pcIn_valid ),
       .pcIn0_bits_request( io_pcIn_bits_request ),
       .pcIn0_bits_moduleId( io_pcIn_bits_moduleId ),
       .pcIn0_bits_portId( io_pcIn_bits_portId ),
       .pcIn0_bits_pcValue( io_pcIn_bits_pcValue ),
       .pcIn0_bits_pcType( io_pcIn_bits_pcType ),
       .io_pcOut_valid( IncThroughOffloadgComponent_io_pcOut_valid ),
       .io_pcOut_bits_request( IncThroughOffloadgComponent_io_pcOut_bits_request ),
       .io_pcOut_bits_moduleId( IncThroughOffloadgComponent_io_pcOut_bits_moduleId ),
       .io_pcOut_bits_portId( IncThroughOffloadgComponent_io_pcOut_bits_portId ),
       .io_pcOut_bits_pcValue( IncThroughOffloadgComponent_io_pcOut_bits_pcValue ),
       .io_pcOut_bits_pcType( IncThroughOffloadgComponent_io_pcOut_bits_pcType ),
       .io_off_constOff_req_ready( RRArbiter_io_in_0_ready ),
       .io_off_constOff_req_valid( IncThroughOffloadgComponent_io_off_constOff_req_valid ),
       .io_off_constOff_req_bits(  ),
       .io_off_constOff_rep_ready( IncThroughOffloadgComponent_io_off_constOff_rep_ready ),
       .io_off_constOff_rep_valid( RRDistributer_io_out_0_valid ),
       .io_off_constOff_rep_bits( RRDistributer_io_out_0_bits ));
  IncThroughOffloadgComponent_1 IncThroughOffloadgComponent_1(.clk(clk), .reset(reset),
       .io_in_ready( IncThroughOffloadgComponent_1_io_in_ready ),
       .io_in_valid( inputDist_io_out_1_valid ),
       .io_in_bits( inputDist_io_out_1_bits ),
       .io_out_ready( outputArb_io_in_1_ready ),
       .io_out_valid( IncThroughOffloadgComponent_1_io_out_valid ),
       .io_out_bits( IncThroughOffloadgComponent_1_io_out_bits ),
       .io_pcIn_valid( IncThroughOffloadgComponent_io_pcOut_valid ),
       .io_pcIn_bits_request( IncThroughOffloadgComponent_io_pcOut_bits_request ),
       .io_pcIn_bits_moduleId( IncThroughOffloadgComponent_io_pcOut_bits_moduleId ),
       .io_pcIn_bits_portId( IncThroughOffloadgComponent_io_pcOut_bits_portId ),
       .io_pcIn_bits_pcValue( IncThroughOffloadgComponent_io_pcOut_bits_pcValue ),
       .io_pcIn_bits_pcType( IncThroughOffloadgComponent_io_pcOut_bits_pcType ),
       .pcOutN_valid( IncThroughOffloadgComponent_1_pcOutN_valid ),
       .pcOutN_bits_request( IncThroughOffloadgComponent_1_pcOutN_bits_request ),
       .pcOutN_bits_moduleId( IncThroughOffloadgComponent_1_pcOutN_bits_moduleId ),
       .pcOutN_bits_portId( IncThroughOffloadgComponent_1_pcOutN_bits_portId ),
       .pcOutN_bits_pcValue( IncThroughOffloadgComponent_1_pcOutN_bits_pcValue ),
       .pcOutN_bits_pcType( IncThroughOffloadgComponent_1_pcOutN_bits_pcType ),
       .io_off_constOff_req_ready( RRArbiter_io_in_1_ready ),
       .io_off_constOff_req_valid( IncThroughOffloadgComponent_1_io_off_constOff_req_valid ),
       .io_off_constOff_req_bits(  ),
       .io_off_constOff_rep_ready( IncThroughOffloadgComponent_1_io_off_constOff_rep_ready ),
       .io_off_constOff_rep_valid( RRDistributer_io_out_1_valid ),
       .io_off_constOff_rep_bits( RRDistributer_io_out_1_bits ));
  RRDistributer inputDist(.clk(clk), .reset(reset),
       .io_out_0_ready( IncThroughOffloadgComponent_io_in_ready ),
       .io_out_0_valid( inputDist_io_out_0_valid ),
       .io_out_0_bits( inputDist_io_out_0_bits ),
       .io_out_1_ready( IncThroughOffloadgComponent_1_io_in_ready ),
       .io_out_1_valid( inputDist_io_out_1_valid ),
       .io_out_1_bits( inputDist_io_out_1_bits ),
       .io_in_ready( inputDist_io_in_ready ),
       .io_in_valid( io_in_valid ),
       .io_in_bits( io_in_bits ),
       .io_chosen(  ));
  RRArbiter outputArb(.clk(clk), .reset(reset),
       .io_in_0_ready( outputArb_io_in_0_ready ),
       .io_in_0_valid( IncThroughOffloadgComponent_io_out_valid ),
       .io_in_0_bits( IncThroughOffloadgComponent_io_out_bits ),
       .io_in_1_ready( outputArb_io_in_1_ready ),
       .io_in_1_valid( IncThroughOffloadgComponent_1_io_out_valid ),
       .io_in_1_bits( IncThroughOffloadgComponent_1_io_out_bits ),
       .io_out_ready( io_out_ready ),
       .io_out_valid( outputArb_io_out_valid ),
       .io_out_bits( outputArb_io_out_bits ),
       .io_chosen(  ));
  RRArbiter_1 RRArbiter(.clk(clk), .reset(reset),
       .io_in_0_ready( RRArbiter_io_in_0_ready ),
       .io_in_0_valid( IncThroughOffloadgComponent_io_off_constOff_req_valid ),
       .io_in_0_bits(  ),
       .io_in_1_ready( RRArbiter_io_in_1_ready ),
       .io_in_1_valid( IncThroughOffloadgComponent_1_io_off_constOff_req_valid ),
       .io_in_1_bits(  ),
       .io_out_ready( mainOff_constOff_req_ready ),
       .io_out_valid( RRArbiter_io_out_valid ),
       .io_out_bits(  ),
       .io_chosen(  ));
  RRDistributer_1 RRDistributer(.clk(clk), .reset(reset),
       .io_out_0_ready( IncThroughOffloadgComponent_io_off_constOff_rep_ready ),
       .io_out_0_valid( RRDistributer_io_out_0_valid ),
       .io_out_0_bits( RRDistributer_io_out_0_bits ),
       .io_out_1_ready( IncThroughOffloadgComponent_1_io_off_constOff_rep_ready ),
       .io_out_1_valid( RRDistributer_io_out_1_valid ),
       .io_out_1_bits( RRDistributer_io_out_1_bits ),
       .io_in_ready( RRDistributer_io_in_ready ),
       .io_in_valid( mainOff_constOff_rep_valid ),
       .io_in_bits( mainOff_constOff_rep_bits ),
       .io_chosen(  ));
endmodule

module SendConstantgComonent(input clk, input reset,
    output io_in_ready,
    input  io_in_valid,
    input [31:0] io_in_bits,
    input  io_out_ready,
    output io_out_valid,
    output[31:0] io_out_bits,
    input  io_pcIn_valid,
    input  io_pcIn_bits_request,
    input [15:0] io_pcIn_bits_moduleId,
    input [7:0] io_pcIn_bits_portId,
    input [15:0] io_pcIn_bits_pcValue,
    input [3:0] io_pcIn_bits_pcType,
    output io_pcOut_valid,
    output io_pcOut_bits_request,
    output[15:0] io_pcOut_bits_moduleId,
    output[7:0] io_pcOut_bits_portId,
    output[15:0] io_pcOut_bits_pcValue,
    output[3:0] io_pcOut_bits_pcType);

  reg[3:0] R0;
  wire T1;
  wire T2;
  wire T3;
  wire T4;
  wire T5;
  wire[3:0] T6;
  wire T7;
  reg[15:0] R8;
  wire T9;
  wire[15:0] T10;
  wire[15:0] T11;
  wire[15:0] T12;
  reg[15:0] R13;
  wire T14;
  wire T15;
  wire T16;
  wire T17;
  wire T18;
  reg[0:0] outputValid;
  wire T19;
  wire T20;
  wire T21;
  wire[3:0] T22;
  wire T23;
  wire[15:0] T24;
  wire[15:0] T25;
  wire[15:0] T26;
  reg[15:0] R27;
  wire T28;
  wire T29;
  wire T30;
  wire T31;
  wire T32;
  wire T33;
  wire T34;
  wire[3:0] T35;
  wire T36;
  wire[15:0] T37;
  wire[15:0] T38;
  wire[15:0] T39;
  wire T40;
  wire[15:0] T41;
  reg[7:0] R42;
  wire T43;
  wire[7:0] T44;
  wire T45;
  reg[0:0] R46;
  wire T47;
  wire T48;
  wire T49;
  reg[15:0] R50;
  wire T51;
  wire[15:0] T52;
  wire T53;
  reg[0:0] R54;
  wire T55;
  wire T56;
  wire T57;
  reg[31:0] outputData;

  assign io_pcOut_bits_pcType = R0;
  assign T1 = T3 || T2;
  assign T2 = ! T3;
  assign T3 = T4 && io_pcIn_bits_request;
  assign T4 = io_pcIn_valid && T5;
  assign T5 = io_pcIn_bits_moduleId == 16'h5/* 5*/;
  assign T6 = T7 ? io_pcIn_bits_pcType : R0;
  assign T7 = T3 || T2;
  assign io_pcOut_bits_pcValue = R8;
  assign T9 = T3 || T2;
  assign T10 = T2 ? io_pcIn_bits_pcValue : T11;
  assign T11 = T3 ? T12 : R8;
  assign T12 = T40 ? R27 : R13;
  assign T14 = T20 || T15;
  assign T15 = T19 && T16;
  assign T16 = io_out_valid && T17;
  assign T17 = ! io_out_ready;
  assign io_out_valid = T18;
  assign T18 = outputValid && io_out_ready;
  assign io_in_ready = io_out_ready;
  assign T19 = ! T20;
  assign T20 = T23 && T21;
  assign T21 = io_pcIn_bits_pcType == T22;
  assign T22 = {2'h0/* 0*/, 2'h2/* 2*/};
  assign T23 = io_pcIn_valid && io_pcIn_bits_request;
  assign T24 = T15 ? T26 : T25;
  assign T25 = T20 ? 16'h0/* 0*/ : R13;
  assign T26 = R13 + 16'h1/* 1*/;
  assign T28 = T33 || T29;
  assign T29 = T32 && T30;
  assign T30 = io_in_valid && T31;
  assign T31 = ! io_in_ready;
  assign T32 = ! T33;
  assign T33 = T36 && T34;
  assign T34 = io_pcIn_bits_pcType == T35;
  assign T35 = {2'h0/* 0*/, 2'h2/* 2*/};
  assign T36 = io_pcIn_valid && io_pcIn_bits_request;
  assign T37 = T29 ? T39 : T38;
  assign T38 = T33 ? 16'h0/* 0*/ : R27;
  assign T39 = R27 + 16'h1/* 1*/;
  assign T40 = T41 == 16'h1/* 1*/;
  assign T41 = {8'h0/* 0*/, io_pcIn_bits_portId};
  assign io_pcOut_bits_portId = R42;
  assign T43 = T3 || T2;
  assign T44 = T45 ? io_pcIn_bits_portId : R42;
  assign T45 = T3 || T2;
  assign io_pcOut_bits_request = R46;
  assign T47 = T3 || T2;
  assign T48 = T2 ? io_pcIn_bits_request : T49;
  assign T49 = T3 ? 1'h0/* 0*/ : R46;
  assign io_pcOut_bits_moduleId = R50;
  assign T51 = T3 || T2;
  assign T52 = T53 ? io_pcIn_bits_moduleId : R50;
  assign T53 = T3 || T2;
  assign io_pcOut_valid = R54;
  assign T55 = T3 || T2;
  assign T56 = T2 ? io_pcIn_valid : T57;
  assign T57 = T3 ? 1'h1/* 1*/ : R54;
  assign io_out_bits = outputData;

  always @(posedge clk) begin
    if(reset) begin
      R0 <= 4'h0/* 0*/;
    end else if(T1) begin
      R0 <= T6;
    end
    if(reset) begin
      R8 <= 16'h0/* 0*/;
    end else if(T9) begin
      R8 <= T10;
    end
    if(reset) begin
      R13 <= 16'h0/* 0*/;
    end else if(T14) begin
      R13 <= T24;
    end
    outputValid <= io_in_valid;
    if(reset) begin
      R27 <= 16'h0/* 0*/;
    end else if(T28) begin
      R27 <= T37;
    end
    if(reset) begin
      R42 <= 8'h0/* 0*/;
    end else if(T43) begin
      R42 <= T44;
    end
    if(reset) begin
      R46 <= 1'h1/* 1*/;
    end else if(T47) begin
      R46 <= T48;
    end
    if(reset) begin
      R50 <= 16'h0/* 0*/;
    end else if(T51) begin
      R50 <= T52;
    end
    if(reset) begin
      R54 <= 1'h0/* 0*/;
    end else if(T55) begin
      R54 <= T56;
    end
    outputData <= 32'h2/* 2*/;
  end
endmodule

module gOffloadedComponent(input clk, input reset,
    output io_in_ready,
    input  io_in_valid,
    input [31:0] io_in_bits,
    input  io_out_ready,
    output io_out_valid,
    output[31:0] io_out_bits,
    input  io_pcIn_valid,
    input  io_pcIn_bits_request,
    input [15:0] io_pcIn_bits_moduleId,
    input [7:0] io_pcIn_bits_portId,
    input [15:0] io_pcIn_bits_pcValue,
    input [3:0] io_pcIn_bits_pcType,
    output io_pcOut_valid,
    output io_pcOut_bits_request,
    output[15:0] io_pcOut_bits_moduleId,
    output[7:0] io_pcOut_bits_portId,
    output[15:0] io_pcOut_bits_pcValue,
    output[3:0] io_pcOut_bits_pcType,
    input  io_off_constOff_req_ready,
    output io_off_constOff_req_valid,
    output[31:0] io_off_constOff_req_bits,
    output io_off_constOff_rep_ready,
    input  io_off_constOff_rep_valid,
    input [31:0] io_off_constOff_rep_bits);

  wire[3:0] offComp_io_pcOut_bits_pcType;
  wire mainComp_io_pcOut_bits_request;
  wire[15:0] mainComp_io_pcOut_bits_moduleId;
  wire mainComp_io_pcOut_valid;
  wire[3:0] mainComp_io_pcOut_bits_pcType;
  wire[15:0] offComp_io_pcOut_bits_pcValue;
  wire mainComp_mainOff_constOff_rep_ready;
  wire offComp_io_out_valid;
  wire mainComp_mainOff_constOff_req_valid;
  wire offComp_io_in_ready;
  wire[7:0] mainComp_io_pcOut_bits_portId;
  wire[15:0] mainComp_io_pcOut_bits_pcValue;
  wire[7:0] offComp_io_pcOut_bits_portId;
  wire offComp_io_pcOut_bits_request;
  wire[15:0] offComp_io_pcOut_bits_moduleId;
  wire offComp_io_pcOut_valid;
  wire[31:0] mainComp_io_out_bits;
  wire[31:0] offComp_io_out_bits;
  wire mainComp_io_out_valid;
  wire mainComp_io_in_ready;

  assign io_pcOut_bits_pcType = offComp_io_pcOut_bits_pcType;
  assign io_pcOut_bits_pcValue = offComp_io_pcOut_bits_pcValue;
  assign io_pcOut_bits_portId = offComp_io_pcOut_bits_portId;
  assign io_pcOut_bits_request = offComp_io_pcOut_bits_request;
  assign io_pcOut_bits_moduleId = offComp_io_pcOut_bits_moduleId;
  assign io_pcOut_valid = offComp_io_pcOut_valid;
  assign io_out_bits = mainComp_io_out_bits;
  assign io_out_valid = mainComp_io_out_valid;
  assign io_in_ready = mainComp_io_in_ready;
  gReplicatedComponent mainComp(.clk(clk), .reset(reset),
       .io_in_ready( mainComp_io_in_ready ),
       .io_in_valid( io_in_valid ),
       .io_in_bits( io_in_bits ),
       .io_out_ready( io_out_ready ),
       .io_out_valid( mainComp_io_out_valid ),
       .io_out_bits( mainComp_io_out_bits ),
       .io_pcIn_valid( io_pcIn_valid ),
       .io_pcIn_bits_request( io_pcIn_bits_request ),
       .io_pcIn_bits_moduleId( io_pcIn_bits_moduleId ),
       .io_pcIn_bits_portId( io_pcIn_bits_portId ),
       .io_pcIn_bits_pcValue( io_pcIn_bits_pcValue ),
       .io_pcIn_bits_pcType( io_pcIn_bits_pcType ),
       .io_pcOut_valid( mainComp_io_pcOut_valid ),
       .io_pcOut_bits_request( mainComp_io_pcOut_bits_request ),
       .io_pcOut_bits_moduleId( mainComp_io_pcOut_bits_moduleId ),
       .io_pcOut_bits_portId( mainComp_io_pcOut_bits_portId ),
       .io_pcOut_bits_pcValue( mainComp_io_pcOut_bits_pcValue ),
       .io_pcOut_bits_pcType( mainComp_io_pcOut_bits_pcType ),
       .mainOff_constOff_req_ready( offComp_io_in_ready ),
       .mainOff_constOff_req_valid( mainComp_mainOff_constOff_req_valid ),
       .mainOff_constOff_req_bits(  ),
       .mainOff_constOff_rep_ready( mainComp_mainOff_constOff_rep_ready ),
       .mainOff_constOff_rep_valid( offComp_io_out_valid ),
       .mainOff_constOff_rep_bits( offComp_io_out_bits ));
  SendConstantgComonent offComp(.clk(clk), .reset(reset),
       .io_in_ready( offComp_io_in_ready ),
       .io_in_valid( mainComp_mainOff_constOff_req_valid ),
       .io_in_bits(  ),
       .io_out_ready( mainComp_mainOff_constOff_rep_ready ),
       .io_out_valid( offComp_io_out_valid ),
       .io_out_bits( offComp_io_out_bits ),
       .io_pcIn_valid( mainComp_io_pcOut_valid ),
       .io_pcIn_bits_request( mainComp_io_pcOut_bits_request ),
       .io_pcIn_bits_moduleId( mainComp_io_pcOut_bits_moduleId ),
       .io_pcIn_bits_portId( mainComp_io_pcOut_bits_portId ),
       .io_pcIn_bits_pcValue( mainComp_io_pcOut_bits_pcValue ),
       .io_pcIn_bits_pcType( mainComp_io_pcOut_bits_pcType ),
       .io_pcOut_valid( offComp_io_pcOut_valid ),
       .io_pcOut_bits_request( offComp_io_pcOut_bits_request ),
       .io_pcOut_bits_moduleId( offComp_io_pcOut_bits_moduleId ),
       .io_pcOut_bits_portId( offComp_io_pcOut_bits_portId ),
       .io_pcOut_bits_pcValue( offComp_io_pcOut_bits_pcValue ),
       .io_pcOut_bits_pcType( offComp_io_pcOut_bits_pcType ));
endmodule

module Offloaded(input clk, input reset,
    output io_in_ready,
    input  io_in_valid,
    input [31:0] io_in_bits,
    input  io_out_ready,
    output io_out_valid,
    output[31:0] io_out_bits,
    input  io_pcIn_valid,
    input  io_pcIn_bits_request,
    input [15:0] io_pcIn_bits_moduleId,
    input [7:0] io_pcIn_bits_portId,
    input [15:0] io_pcIn_bits_pcValue,
    input [3:0] io_pcIn_bits_pcType,
    output io_pcOut_valid,
    output io_pcOut_bits_request,
    output[15:0] io_pcOut_bits_moduleId,
    output[7:0] io_pcOut_bits_portId,
    output[15:0] io_pcOut_bits_pcValue,
    output[3:0] io_pcOut_bits_pcType);

  wire[3:0] f_io_pcOut_bits_pcType;
  wire[15:0] f_io_pcOut_bits_pcValue;
  wire[7:0] f_io_pcOut_bits_portId;
  wire f_io_pcOut_bits_request;
  wire[15:0] f_io_pcOut_bits_moduleId;
  wire f_io_pcOut_valid;
  wire[31:0] f_io_out_bits;
  wire f_io_out_valid;
  wire f_io_in_ready;

  assign io_pcOut_bits_pcType = f_io_pcOut_bits_pcType;
  assign io_pcOut_bits_pcValue = f_io_pcOut_bits_pcValue;
  assign io_pcOut_bits_portId = f_io_pcOut_bits_portId;
  assign io_pcOut_bits_request = f_io_pcOut_bits_request;
  assign io_pcOut_bits_moduleId = f_io_pcOut_bits_moduleId;
  assign io_pcOut_valid = f_io_pcOut_valid;
  assign io_out_bits = f_io_out_bits;
  assign io_out_valid = f_io_out_valid;
  assign io_in_ready = f_io_in_ready;
  gOffloadedComponent f(.clk(clk), .reset(reset),
       .io_in_ready( f_io_in_ready ),
       .io_in_valid( io_in_valid ),
       .io_in_bits( io_in_bits ),
       .io_out_ready( io_out_ready ),
       .io_out_valid( f_io_out_valid ),
       .io_out_bits( f_io_out_bits ),
       .io_pcIn_valid( io_pcIn_valid ),
       .io_pcIn_bits_request( io_pcIn_bits_request ),
       .io_pcIn_bits_moduleId( io_pcIn_bits_moduleId ),
       .io_pcIn_bits_portId( io_pcIn_bits_portId ),
       .io_pcIn_bits_pcValue( io_pcIn_bits_pcValue ),
       .io_pcIn_bits_pcType( io_pcIn_bits_pcType ),
       .io_pcOut_valid( f_io_pcOut_valid ),
       .io_pcOut_bits_request( f_io_pcOut_bits_request ),
       .io_pcOut_bits_moduleId( f_io_pcOut_bits_moduleId ),
       .io_pcOut_bits_portId( f_io_pcOut_bits_portId ),
       .io_pcOut_bits_pcValue( f_io_pcOut_bits_pcValue ),
       .io_pcOut_bits_pcType( f_io_pcOut_bits_pcType ),
       .io_off_constOff_req_ready(  ),
       .io_off_constOff_req_valid(  ),
       .io_off_constOff_req_bits(  ),
       .io_off_constOff_rep_ready(  ),
       .io_off_constOff_rep_valid(  ),
       .io_off_constOff_rep_bits(  ));
endmodule

