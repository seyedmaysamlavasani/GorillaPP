module IncThroughOffloadgComponent(input clk, input reset,
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
  wire T16;
  wire T17;
  reg[3:0] R18;
  wire T19;
  wire[3:0] T20;
  wire T21;
  wire offWire_rep_ready;
  wire T22;
  reg[2:0] state;
  wire T23;
  wire T24;
  wire T25;
  wire T26;
  wire T27;
  wire T28;
  wire T29;
  wire offWire_rep_valid;
  wire offWire_req_valid;
  reg[0:0] haveOffReq;
  wire T30;
  wire T31;
  wire T32;
  wire T33;
  wire T34;
  wire T35;
  wire T36;
  wire T37;
  wire T38;
  wire T39;
  wire T40;
  wire T41;
  wire T42;
  wire T43;
  wire T44;
  wire T45;
  wire[31:0] T46;
  reg[31:0] counter;
  wire T47;
  wire T48;
  wire T49;
  wire T50;
  wire T51;
  wire[31:0] T52;
  wire[31:0] T53;
  wire[31:0] T54;
  wire[31:0] T55;
  wire T56;
  wire T57;
  wire T58;
  wire T59;
  wire[2:0] T60;
  wire[2:0] T61;
  wire[2:0] T62;
  wire[2:0] T63;
  wire[2:0] T64;
  reg[7:0] R65;
  wire T66;
  wire[7:0] T67;
  wire T68;
  reg[15:0] R69;
  wire T70;
  wire[15:0] T71;
  wire[15:0] T72;
  wire[15:0] T73;
  wire[15:0] T74;
  wire[15:0] T75;
  reg[15:0] R76;
  wire T77;
  wire T78;
  wire T79;
  wire T80;
  wire T81;
  wire T82;
  wire T83;
  wire T84;
  wire T85;
  wire[3:0] T86;
  wire[15:0] T87;
  wire[15:0] T88;
  wire[15:0] T89;
  wire T90;
  wire[15:0] T91;
  reg[15:0] R92;
  wire T93;
  wire T94;
  wire T95;
  wire T96;
  wire T97;
  wire T98;
  wire T99;
  wire T100;
  wire[3:0] T101;
  wire[15:0] T102;
  wire[15:0] T103;
  wire[15:0] T104;
  wire T105;
  wire[15:0] T106;
  reg[15:0] R107;
  wire T108;
  wire T109;
  wire T110;
  wire T111;
  wire T112;
  wire T113;
  wire T114;
  wire[3:0] T115;
  wire[15:0] T116;
  wire[15:0] T117;
  wire[15:0] T118;
  wire T119;
  wire[15:0] T120;
  wire[31:0] T121;
  wire[31:0] offWire_rep_bits;

  assign io_pcOut_bits_request = R0;
  assign T1 = T3 || T2;
  assign T2 = ! T3;
  assign T3 = T4 && io_pcIn_bits_request;
  assign T4 = io_pcIn_valid && T5;
  assign T5 = io_pcIn_bits_moduleId == 16'h2/* 2*/;
  assign T6 = T2 ? io_pcIn_bits_request : T7;
  assign T7 = T3 ? 1'h0/* 0*/ : R0;
  assign io_pcOut_bits_moduleId = R8;
  assign T9 = T3 || T2;
  assign T10 = T11 ? io_pcIn_bits_moduleId : R8;
  assign T11 = T3 || T2;
  assign io_pcOut_valid = R12;
  assign T13 = T14 || T2;
  assign T14 = T3 || T3;
  assign T15 = T17 ? io_pcIn_valid : T16;
  assign T16 = T3 ? 1'h1/* 1*/ : R12;
  assign T17 = T3 || T2;
  assign io_pcOut_bits_pcType = R18;
  assign T19 = T3 || T2;
  assign T20 = T21 ? io_pcIn_bits_pcType : R18;
  assign T21 = T3 || T2;
  assign mainOff_constOff_rep_ready = offWire_rep_ready;
  assign offWire_rep_ready = T22;
  assign T22 = state != 3'h4/* 4*/;
  assign T23 = T43 || T24;
  assign T24 = T27 && T25;
  assign T25 = T26 && io_out_ready;
  assign T26 = state == 3'h4/* 4*/;
  assign T27 = ! T28;
  assign T28 = T39 || T29;
  assign T29 = T38 && offWire_rep_valid;
  assign offWire_rep_valid = mainOff_constOff_rep_valid;
  assign mainOff_constOff_req_valid = offWire_req_valid;
  assign offWire_req_valid = haveOffReq;
  assign T30 = T31 || T24;
  assign T31 = T33 && T32;
  assign T32 = state == 3'h1/* 1*/;
  assign T33 = ! T34;
  assign T34 = T35 && io_in_valid;
  assign T35 = state == 3'h0/* 0*/;
  assign T36 = T24 ? 1'h0/* 0*/ : T37;
  assign T37 = T31 ? 1'h1/* 1*/ : haveOffReq;
  assign T38 = state == 3'h3/* 3*/;
  assign T39 = T42 || T40;
  assign T40 = T41 && offWire_rep_valid;
  assign T41 = state == 3'h2/* 2*/;
  assign T42 = T34 || T32;
  assign T43 = T56 || T44;
  assign T44 = T50 && T45;
  assign T45 = counter == T46;
  assign T46 = {31'h0/* 0*/, 1'h0/* 0*/};
  assign T47 = T44 || T48;
  assign T48 = T50 && T49;
  assign T49 = ! T45;
  assign T50 = T51 && T29;
  assign T51 = ! T39;
  assign T52 = T48 ? T54 : T53;
  assign T53 = T44 ? 32'ha/* 10*/ : counter;
  assign T54 = counter - T55;
  assign T55 = {31'h0/* 0*/, 1'h1/* 1*/};
  assign T56 = T59 || T57;
  assign T57 = T58 && T40;
  assign T58 = ! T42;
  assign T59 = T34 || T31;
  assign T60 = T24 ? 3'h0/* 0*/ : T61;
  assign T61 = T44 ? 3'h4/* 4*/ : T62;
  assign T62 = T57 ? 3'h3/* 3*/ : T63;
  assign T63 = T31 ? 3'h2/* 2*/ : T64;
  assign T64 = T34 ? 3'h1/* 1*/ : state;
  assign io_pcOut_bits_portId = R65;
  assign T66 = T3 || T2;
  assign T67 = T68 ? io_pcIn_bits_portId : R65;
  assign T68 = T3 || T2;
  assign io_pcOut_bits_pcValue = R69;
  assign T70 = T3 || T2;
  assign T71 = T2 ? io_pcIn_bits_pcValue : T72;
  assign T72 = T3 ? T73 : R69;
  assign T73 = T119 ? R107 : T74;
  assign T74 = T105 ? R92 : T75;
  assign T75 = T90 ? R76 : 16'h0/* 0*/;
  assign T77 = T84 || T78;
  assign T78 = T83 && T79;
  assign T79 = io_in_valid && T80;
  assign T80 = ! io_in_ready;
  assign io_in_ready = T81;
  assign T81 = T82 && io_out_ready;
  assign T82 = state == 3'h4/* 4*/;
  assign T83 = ! T84;
  assign T84 = io_pcIn_bits_request && T85;
  assign T85 = io_pcIn_bits_pcType == T86;
  assign T86 = {2'h0/* 0*/, 2'h2/* 2*/};
  assign T87 = T78 ? T89 : T88;
  assign T88 = T84 ? 16'h0/* 0*/ : R76;
  assign T89 = R76 + 16'h1/* 1*/;
  assign T90 = T91 == 16'h1/* 1*/;
  assign T91 = {8'h0/* 0*/, io_pcIn_bits_portId};
  assign T93 = T99 || T94;
  assign T94 = T98 && T95;
  assign T95 = io_out_valid && T96;
  assign T96 = ! io_out_ready;
  assign io_out_valid = T97;
  assign T97 = state == 3'h4/* 4*/;
  assign T98 = ! T99;
  assign T99 = io_pcIn_bits_request && T100;
  assign T100 = io_pcIn_bits_pcType == T101;
  assign T101 = {2'h0/* 0*/, 2'h2/* 2*/};
  assign T102 = T94 ? T104 : T103;
  assign T103 = T99 ? 16'h0/* 0*/ : R92;
  assign T104 = R92 + 16'h1/* 1*/;
  assign T105 = T106 == 16'h2/* 2*/;
  assign T106 = {8'h0/* 0*/, io_pcIn_bits_portId};
  assign T108 = T113 || T109;
  assign T109 = T112 && T110;
  assign T110 = mainOff_constOff_req_valid && T111;
  assign T111 = ! mainOff_constOff_req_ready;
  assign T112 = ! T113;
  assign T113 = io_pcIn_bits_request && T114;
  assign T114 = io_pcIn_bits_pcType == T115;
  assign T115 = {2'h0/* 0*/, 2'h2/* 2*/};
  assign T116 = T109 ? T118 : T117;
  assign T117 = T113 ? 16'h0/* 0*/ : R107;
  assign T118 = R107 + 16'h1/* 1*/;
  assign T119 = T120 == 16'h3/* 3*/;
  assign T120 = {8'h0/* 0*/, io_pcIn_bits_portId};
  assign io_out_bits = T121;
  assign T121 = io_in_bits + offWire_rep_bits;
  assign offWire_rep_bits = mainOff_constOff_rep_bits;

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
      R12 <= T15;
    end
    if(reset) begin
      R18 <= 4'h0/* 0*/;
    end else if(T19) begin
      R18 <= T20;
    end
    if(reset) begin
      state <= 3'h0/* 0*/;
    end else if(T23) begin
      state <= T60;
    end
    if(reset) begin
      haveOffReq <= 1'h0/* 0*/;
    end else if(T30) begin
      haveOffReq <= T36;
    end
    if(reset) begin
      counter <= 32'ha/* 10*/;
    end else if(T47) begin
      counter <= T52;
    end
    if(reset) begin
      R65 <= 8'h0/* 0*/;
    end else if(T66) begin
      R65 <= T67;
    end
    if(reset) begin
      R69 <= 16'h0/* 0*/;
    end else if(T70) begin
      R69 <= T71;
    end
    if(reset) begin
      R76 <= 16'h0/* 0*/;
    end else if(T77) begin
      R76 <= T87;
    end
    if(reset) begin
      R92 <= 16'h0/* 0*/;
    end else if(T93) begin
      R92 <= T102;
    end
    if(reset) begin
      R107 <= 16'h0/* 0*/;
    end else if(T108) begin
      R107 <= T116;
    end
  end
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
    output[3:0] io_pcOut_bits_pcType,
    input  offOff_test_req_ready,
    output offOff_test_req_valid,
    output[31:0] offOff_test_req_bits,
    output offOff_test_rep_ready,
    input  offOff_test_rep_valid,
    input [31:0] offOff_test_rep_bits);

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
  wire[15:0] T13;
  wire[15:0] T14;
  reg[15:0] R15;
  wire T16;
  wire T17;
  wire T18;
  wire T19;
  wire T20;
  reg[0:0] outputValid;
  wire T21;
  wire T22;
  wire T23;
  wire[3:0] T24;
  wire[15:0] T25;
  wire[15:0] T26;
  wire[15:0] T27;
  wire T28;
  wire[15:0] T29;
  reg[15:0] R30;
  wire T31;
  wire T32;
  wire T33;
  wire T34;
  wire T35;
  wire T36;
  wire T37;
  wire[3:0] T38;
  wire[15:0] T39;
  wire[15:0] T40;
  wire[15:0] T41;
  wire T42;
  wire[15:0] T43;
  reg[15:0] R44;
  wire T45;
  wire T46;
  wire T47;
  wire T48;
  wire T49;
  wire T50;
  wire T51;
  wire[3:0] T52;
  wire[15:0] T53;
  wire[15:0] T54;
  wire[15:0] T55;
  wire T56;
  wire[15:0] T57;
  reg[7:0] R58;
  wire T59;
  wire[7:0] T60;
  wire T61;
  reg[0:0] R62;
  wire T63;
  wire T64;
  wire T65;
  reg[15:0] R66;
  wire T67;
  wire[15:0] T68;
  wire T69;
  reg[0:0] R70;
  wire T71;
  wire T72;
  wire T73;
  wire T74;
  wire T75;
  reg[31:0] outputData;

  assign io_pcOut_bits_pcType = R0;
  assign T1 = T3 || T2;
  assign T2 = ! T3;
  assign T3 = T4 && io_pcIn_bits_request;
  assign T4 = io_pcIn_valid && T5;
  assign T5 = io_pcIn_bits_moduleId == 16'h3/* 3*/;
  assign T6 = T7 ? io_pcIn_bits_pcType : R0;
  assign T7 = T3 || T2;
  assign io_pcOut_bits_pcValue = R8;
  assign T9 = T3 || T2;
  assign T10 = T2 ? io_pcIn_bits_pcValue : T11;
  assign T11 = T3 ? T12 : R8;
  assign T12 = T56 ? R44 : T13;
  assign T13 = T42 ? R30 : T14;
  assign T14 = T28 ? R15 : 16'h0/* 0*/;
  assign T16 = T22 || T17;
  assign T17 = T21 && T18;
  assign T18 = io_in_valid && T19;
  assign T19 = ! io_in_ready;
  assign io_in_ready = io_out_ready;
  assign io_out_valid = T20;
  assign T20 = outputValid && io_out_ready;
  assign T21 = ! T22;
  assign T22 = io_pcIn_bits_request && T23;
  assign T23 = io_pcIn_bits_pcType == T24;
  assign T24 = {2'h0/* 0*/, 2'h2/* 2*/};
  assign T25 = T17 ? T27 : T26;
  assign T26 = T22 ? 16'h0/* 0*/ : R15;
  assign T27 = R15 + 16'h1/* 1*/;
  assign T28 = T29 == 16'h1/* 1*/;
  assign T29 = {8'h0/* 0*/, io_pcIn_bits_portId};
  assign T31 = T36 || T32;
  assign T32 = T35 && T33;
  assign T33 = io_out_valid && T34;
  assign T34 = ! io_out_ready;
  assign T35 = ! T36;
  assign T36 = io_pcIn_bits_request && T37;
  assign T37 = io_pcIn_bits_pcType == T38;
  assign T38 = {2'h0/* 0*/, 2'h2/* 2*/};
  assign T39 = T32 ? T41 : T40;
  assign T40 = T36 ? 16'h0/* 0*/ : R30;
  assign T41 = R30 + 16'h1/* 1*/;
  assign T42 = T43 == 16'h2/* 2*/;
  assign T43 = {8'h0/* 0*/, io_pcIn_bits_portId};
  assign T45 = T50 || T46;
  assign T46 = T49 && T47;
  assign T47 = offOff_test_req_valid && T48;
  assign T48 = ! offOff_test_req_ready;
  assign T49 = ! T50;
  assign T50 = io_pcIn_bits_request && T51;
  assign T51 = io_pcIn_bits_pcType == T52;
  assign T52 = {2'h0/* 0*/, 2'h2/* 2*/};
  assign T53 = T46 ? T55 : T54;
  assign T54 = T50 ? 16'h0/* 0*/ : R44;
  assign T55 = R44 + 16'h1/* 1*/;
  assign T56 = T57 == 16'h3/* 3*/;
  assign T57 = {8'h0/* 0*/, io_pcIn_bits_portId};
  assign io_pcOut_bits_portId = R58;
  assign T59 = T3 || T2;
  assign T60 = T61 ? io_pcIn_bits_portId : R58;
  assign T61 = T3 || T2;
  assign io_pcOut_bits_request = R62;
  assign T63 = T3 || T2;
  assign T64 = T2 ? io_pcIn_bits_request : T65;
  assign T65 = T3 ? 1'h0/* 0*/ : R62;
  assign io_pcOut_bits_moduleId = R66;
  assign T67 = T3 || T2;
  assign T68 = T69 ? io_pcIn_bits_moduleId : R66;
  assign T69 = T3 || T2;
  assign io_pcOut_valid = R70;
  assign T71 = T72 || T2;
  assign T72 = T3 || T3;
  assign T73 = T75 ? io_pcIn_valid : T74;
  assign T74 = T3 ? 1'h1/* 1*/ : R70;
  assign T75 = T3 || T2;
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
      R15 <= 16'h0/* 0*/;
    end else if(T16) begin
      R15 <= T25;
    end
    outputValid <= io_in_valid;
    if(reset) begin
      R30 <= 16'h0/* 0*/;
    end else if(T31) begin
      R30 <= T39;
    end
    if(reset) begin
      R44 <= 16'h0/* 0*/;
    end else if(T45) begin
      R44 <= T53;
    end
    if(reset) begin
      R58 <= 8'h0/* 0*/;
    end else if(T59) begin
      R58 <= T60;
    end
    if(reset) begin
      R62 <= 1'h1/* 1*/;
    end else if(T63) begin
      R62 <= T64;
    end
    if(reset) begin
      R66 <= 16'h0/* 0*/;
    end else if(T67) begin
      R66 <= T68;
    end
    if(reset) begin
      R70 <= 1'h0/* 0*/;
    end else if(T71) begin
      R70 <= T73;
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
  wire[7:0] mainComp_io_pcOut_bits_portId;
  wire[15:0] mainComp_io_pcOut_bits_pcValue;
  wire offComp_io_in_ready;
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
  IncThroughOffloadgComponent mainComp(.clk(clk), .reset(reset),
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
       .io_pcOut_bits_pcType( offComp_io_pcOut_bits_pcType ),
       .offOff_test_req_ready(  ),
       .offOff_test_req_valid(  ),
       .offOff_test_req_bits(  ),
       .offOff_test_rep_ready(  ),
       .offOff_test_rep_valid(  ),
       .offOff_test_rep_bits(  ));
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

  wire[3:0] gOffloadedComponent_io_pcOut_bits_pcType;
  wire[15:0] gOffloadedComponent_io_pcOut_bits_pcValue;
  wire[7:0] gOffloadedComponent_io_pcOut_bits_portId;
  wire gOffloadedComponent_io_pcOut_bits_request;
  wire[15:0] gOffloadedComponent_io_pcOut_bits_moduleId;
  wire gOffloadedComponent_io_pcOut_valid;
  wire[31:0] gOffloadedComponent_io_out_bits;
  wire gOffloadedComponent_io_out_valid;
  wire gOffloadedComponent_io_in_ready;

  assign io_pcOut_bits_pcType = gOffloadedComponent_io_pcOut_bits_pcType;
  assign io_pcOut_bits_pcValue = gOffloadedComponent_io_pcOut_bits_pcValue;
  assign io_pcOut_bits_portId = gOffloadedComponent_io_pcOut_bits_portId;
  assign io_pcOut_bits_request = gOffloadedComponent_io_pcOut_bits_request;
  assign io_pcOut_bits_moduleId = gOffloadedComponent_io_pcOut_bits_moduleId;
  assign io_pcOut_valid = gOffloadedComponent_io_pcOut_valid;
  assign io_out_bits = gOffloadedComponent_io_out_bits;
  assign io_out_valid = gOffloadedComponent_io_out_valid;
  assign io_in_ready = gOffloadedComponent_io_in_ready;
  gOffloadedComponent gOffloadedComponent(.clk(clk), .reset(reset),
       .io_in_ready( gOffloadedComponent_io_in_ready ),
       .io_in_valid( io_in_valid ),
       .io_in_bits( io_in_bits ),
       .io_out_ready( io_out_ready ),
       .io_out_valid( gOffloadedComponent_io_out_valid ),
       .io_out_bits( gOffloadedComponent_io_out_bits ),
       .io_pcIn_valid( io_pcIn_valid ),
       .io_pcIn_bits_request( io_pcIn_bits_request ),
       .io_pcIn_bits_moduleId( io_pcIn_bits_moduleId ),
       .io_pcIn_bits_portId( io_pcIn_bits_portId ),
       .io_pcIn_bits_pcValue( io_pcIn_bits_pcValue ),
       .io_pcIn_bits_pcType( io_pcIn_bits_pcType ),
       .io_pcOut_valid( gOffloadedComponent_io_pcOut_valid ),
       .io_pcOut_bits_request( gOffloadedComponent_io_pcOut_bits_request ),
       .io_pcOut_bits_moduleId( gOffloadedComponent_io_pcOut_bits_moduleId ),
       .io_pcOut_bits_portId( gOffloadedComponent_io_pcOut_bits_portId ),
       .io_pcOut_bits_pcValue( gOffloadedComponent_io_pcOut_bits_pcValue ),
       .io_pcOut_bits_pcType( gOffloadedComponent_io_pcOut_bits_pcType ),
       .io_off_constOff_req_ready(  ),
       .io_off_constOff_req_valid(  ),
       .io_off_constOff_req_bits(  ),
       .io_off_constOff_rep_ready(  ),
       .io_off_constOff_rep_valid(  ),
       .io_off_constOff_rep_bits(  ));
endmodule

