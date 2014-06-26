module IncgComponent(input clk, input reset,
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
    input  sourceOff_testOff_req_ready,
    output sourceOff_testOff_req_valid,
    output[31:0] sourceOff_testOff_req_bits,
    output sourceOff_testOff_rep_ready,
    input  sourceOff_testOff_rep_valid,
    input [31:0] sourceOff_testOff_rep_bits);

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
  wire T22;
  reg[0:0] outputValid;
  reg[7:0] R23;
  wire T24;
  wire[7:0] T25;
  wire T26;
  reg[15:0] R27;
  wire T28;
  wire[15:0] T29;
  wire[15:0] T30;
  wire[15:0] T31;
  wire[15:0] T32;
  wire[15:0] T33;
  reg[15:0] R34;
  wire T35;
  wire T36;
  wire T37;
  wire T38;
  wire T39;
  wire T40;
  wire T41;
  wire[3:0] T42;
  wire[15:0] T43;
  wire[15:0] T44;
  wire[15:0] T45;
  wire T46;
  wire[15:0] T47;
  reg[15:0] R48;
  wire T49;
  wire T50;
  wire T51;
  wire T52;
  wire T53;
  wire T54;
  wire T55;
  wire[3:0] T56;
  wire[15:0] T57;
  wire[15:0] T58;
  wire[15:0] T59;
  wire T60;
  wire[15:0] T61;
  reg[15:0] R62;
  wire T63;
  wire T64;
  wire T65;
  wire T66;
  wire T67;
  wire T68;
  wire T69;
  wire[3:0] T70;
  wire[15:0] T71;
  wire[15:0] T72;
  wire[15:0] T73;
  wire T74;
  wire[15:0] T75;
  reg[31:0] outputData;
  wire[31:0] T76;

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
  assign io_out_valid = T22;
  assign T22 = outputValid && io_out_ready;
  assign io_pcOut_bits_portId = R23;
  assign T24 = T3 || T2;
  assign T25 = T26 ? io_pcIn_bits_portId : R23;
  assign T26 = T3 || T2;
  assign io_pcOut_bits_pcValue = R27;
  assign T28 = T3 || T2;
  assign T29 = T2 ? io_pcIn_bits_pcValue : T30;
  assign T30 = T3 ? T31 : R27;
  assign T31 = T74 ? R62 : T32;
  assign T32 = T60 ? R48 : T33;
  assign T33 = T46 ? R34 : 16'h0/* 0*/;
  assign T35 = T40 || T36;
  assign T36 = T39 && T37;
  assign T37 = io_in_valid && T38;
  assign T38 = ! io_in_ready;
  assign io_in_ready = io_out_ready;
  assign T39 = ! T40;
  assign T40 = io_pcIn_bits_request && T41;
  assign T41 = io_pcIn_bits_pcType == T42;
  assign T42 = {2'h0/* 0*/, 2'h2/* 2*/};
  assign T43 = T36 ? T45 : T44;
  assign T44 = T40 ? 16'h0/* 0*/ : R34;
  assign T45 = R34 + 16'h1/* 1*/;
  assign T46 = T47 == 16'h1/* 1*/;
  assign T47 = {8'h0/* 0*/, io_pcIn_bits_portId};
  assign T49 = T54 || T50;
  assign T50 = T53 && T51;
  assign T51 = io_out_valid && T52;
  assign T52 = ! io_out_ready;
  assign T53 = ! T54;
  assign T54 = io_pcIn_bits_request && T55;
  assign T55 = io_pcIn_bits_pcType == T56;
  assign T56 = {2'h0/* 0*/, 2'h2/* 2*/};
  assign T57 = T50 ? T59 : T58;
  assign T58 = T54 ? 16'h0/* 0*/ : R48;
  assign T59 = R48 + 16'h1/* 1*/;
  assign T60 = T61 == 16'h2/* 2*/;
  assign T61 = {8'h0/* 0*/, io_pcIn_bits_portId};
  assign T63 = T68 || T64;
  assign T64 = T67 && T65;
  assign T65 = sourceOff_testOff_req_valid && T66;
  assign T66 = ! sourceOff_testOff_req_ready;
  assign T67 = ! T68;
  assign T68 = io_pcIn_bits_request && T69;
  assign T69 = io_pcIn_bits_pcType == T70;
  assign T70 = {2'h0/* 0*/, 2'h2/* 2*/};
  assign T71 = T64 ? T73 : T72;
  assign T72 = T68 ? 16'h0/* 0*/ : R62;
  assign T73 = R62 + 16'h1/* 1*/;
  assign T74 = T75 == 16'h3/* 3*/;
  assign T75 = {8'h0/* 0*/, io_pcIn_bits_portId};
  assign io_out_bits = outputData;
  assign T76 = io_in_bits + 32'h1/* 1*/;

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
    outputValid <= io_in_valid;
    if(reset) begin
      R23 <= 8'h0/* 0*/;
    end else if(T24) begin
      R23 <= T25;
    end
    if(reset) begin
      R27 <= 16'h0/* 0*/;
    end else if(T28) begin
      R27 <= T29;
    end
    if(reset) begin
      R34 <= 16'h0/* 0*/;
    end else if(T35) begin
      R34 <= T43;
    end
    if(reset) begin
      R48 <= 16'h0/* 0*/;
    end else if(T49) begin
      R48 <= T57;
    end
    if(reset) begin
      R62 <= 16'h0/* 0*/;
    end else if(T63) begin
      R62 <= T71;
    end
    outputData <= T76;
  end
endmodule

module IncgComponent_1(input clk, input reset,
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
    input  sinkOff_testOff_req_ready,
    output sinkOff_testOff_req_valid,
    output[31:0] sinkOff_testOff_req_bits,
    output sinkOff_testOff_rep_ready,
    input  sinkOff_testOff_rep_valid,
    input [31:0] sinkOff_testOff_rep_bits);

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
  wire T21;
  wire T22;
  wire[3:0] T23;
  wire[15:0] T24;
  wire[15:0] T25;
  wire[15:0] T26;
  wire T27;
  wire[15:0] T28;
  reg[15:0] R29;
  wire T30;
  wire T31;
  wire T32;
  wire T33;
  wire T34;
  reg[0:0] outputValid;
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
  wire[31:0] T76;

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
  assign T13 = T42 ? R29 : T14;
  assign T14 = T27 ? R15 : 16'h0/* 0*/;
  assign T16 = T21 || T17;
  assign T17 = T20 && T18;
  assign T18 = io_in_valid && T19;
  assign T19 = ! io_in_ready;
  assign io_in_ready = io_out_ready;
  assign T20 = ! T21;
  assign T21 = io_pcIn_bits_request && T22;
  assign T22 = io_pcIn_bits_pcType == T23;
  assign T23 = {2'h0/* 0*/, 2'h2/* 2*/};
  assign T24 = T17 ? T26 : T25;
  assign T25 = T21 ? 16'h0/* 0*/ : R15;
  assign T26 = R15 + 16'h1/* 1*/;
  assign T27 = T28 == 16'h1/* 1*/;
  assign T28 = {8'h0/* 0*/, io_pcIn_bits_portId};
  assign T30 = T36 || T31;
  assign T31 = T35 && T32;
  assign T32 = io_out_valid && T33;
  assign T33 = ! io_out_ready;
  assign io_out_valid = T34;
  assign T34 = outputValid && io_out_ready;
  assign T35 = ! T36;
  assign T36 = io_pcIn_bits_request && T37;
  assign T37 = io_pcIn_bits_pcType == T38;
  assign T38 = {2'h0/* 0*/, 2'h2/* 2*/};
  assign T39 = T31 ? T41 : T40;
  assign T40 = T36 ? 16'h0/* 0*/ : R29;
  assign T41 = R29 + 16'h1/* 1*/;
  assign T42 = T43 == 16'h2/* 2*/;
  assign T43 = {8'h0/* 0*/, io_pcIn_bits_portId};
  assign T45 = T50 || T46;
  assign T46 = T49 && T47;
  assign T47 = sinkOff_testOff_req_valid && T48;
  assign T48 = ! sinkOff_testOff_req_ready;
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
  assign T76 = io_in_bits + 32'h1/* 1*/;

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
      R15 <= T24;
    end
    if(reset) begin
      R29 <= 16'h0/* 0*/;
    end else if(T30) begin
      R29 <= T39;
    end
    outputValid <= io_in_valid;
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
    outputData <= T76;
  end
endmodule

module gChainedComponent(input clk, input reset,
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
    input  io_off_testOff1_req_ready,
    output io_off_testOff1_req_valid,
    output[31:0] io_off_testOff1_req_bits,
    output io_off_testOff1_rep_ready,
    input  io_off_testOff1_rep_valid,
    input [31:0] io_off_testOff1_rep_bits,
    input  io_off_testOff2_req_ready,
    output io_off_testOff2_req_valid,
    output[31:0] io_off_testOff2_req_bits,
    output io_off_testOff2_rep_ready,
    input  io_off_testOff2_rep_valid,
    input [31:0] io_off_testOff2_rep_bits);

  wire[3:0] sink_io_pcOut_bits_pcType;
  wire source_io_pcOut_bits_request;
  wire[15:0] source_io_pcOut_bits_moduleId;
  wire source_io_pcOut_valid;
  wire[3:0] source_io_pcOut_bits_pcType;
  wire[15:0] sink_io_pcOut_bits_pcValue;
  wire source_io_out_valid;
  wire sink_io_in_ready;
  wire[7:0] source_io_pcOut_bits_portId;
  wire[15:0] source_io_pcOut_bits_pcValue;
  wire[7:0] sink_io_pcOut_bits_portId;
  wire sink_io_pcOut_bits_request;
  wire[15:0] sink_io_pcOut_bits_moduleId;
  wire sink_io_pcOut_valid;
  wire[31:0] sink_io_out_bits;
  wire[31:0] source_io_out_bits;
  wire sink_io_out_valid;
  wire source_io_in_ready;

  assign io_pcOut_bits_pcType = sink_io_pcOut_bits_pcType;
  assign io_pcOut_bits_pcValue = sink_io_pcOut_bits_pcValue;
  assign io_pcOut_bits_portId = sink_io_pcOut_bits_portId;
  assign io_pcOut_bits_request = sink_io_pcOut_bits_request;
  assign io_pcOut_bits_moduleId = sink_io_pcOut_bits_moduleId;
  assign io_pcOut_valid = sink_io_pcOut_valid;
  assign io_out_bits = sink_io_out_bits;
  assign io_out_valid = sink_io_out_valid;
  assign io_in_ready = source_io_in_ready;
  IncgComponent source(.clk(clk), .reset(reset),
       .io_in_ready( source_io_in_ready ),
       .io_in_valid( io_in_valid ),
       .io_in_bits( io_in_bits ),
       .io_out_ready( sink_io_in_ready ),
       .io_out_valid( source_io_out_valid ),
       .io_out_bits( source_io_out_bits ),
       .io_pcIn_valid( io_pcIn_valid ),
       .io_pcIn_bits_request( io_pcIn_bits_request ),
       .io_pcIn_bits_moduleId( io_pcIn_bits_moduleId ),
       .io_pcIn_bits_portId( io_pcIn_bits_portId ),
       .io_pcIn_bits_pcValue( io_pcIn_bits_pcValue ),
       .io_pcIn_bits_pcType( io_pcIn_bits_pcType ),
       .io_pcOut_valid( source_io_pcOut_valid ),
       .io_pcOut_bits_request( source_io_pcOut_bits_request ),
       .io_pcOut_bits_moduleId( source_io_pcOut_bits_moduleId ),
       .io_pcOut_bits_portId( source_io_pcOut_bits_portId ),
       .io_pcOut_bits_pcValue( source_io_pcOut_bits_pcValue ),
       .io_pcOut_bits_pcType( source_io_pcOut_bits_pcType ),
       .sourceOff_testOff_req_ready(  ),
       .sourceOff_testOff_req_valid(  ),
       .sourceOff_testOff_req_bits(  ),
       .sourceOff_testOff_rep_ready(  ),
       .sourceOff_testOff_rep_valid(  ),
       .sourceOff_testOff_rep_bits(  ));
  IncgComponent_1 sink(.clk(clk), .reset(reset),
       .io_in_ready( sink_io_in_ready ),
       .io_in_valid( source_io_out_valid ),
       .io_in_bits( source_io_out_bits ),
       .io_out_ready( io_out_ready ),
       .io_out_valid( sink_io_out_valid ),
       .io_out_bits( sink_io_out_bits ),
       .io_pcIn_valid( source_io_pcOut_valid ),
       .io_pcIn_bits_request( source_io_pcOut_bits_request ),
       .io_pcIn_bits_moduleId( source_io_pcOut_bits_moduleId ),
       .io_pcIn_bits_portId( source_io_pcOut_bits_portId ),
       .io_pcIn_bits_pcValue( source_io_pcOut_bits_pcValue ),
       .io_pcIn_bits_pcType( source_io_pcOut_bits_pcType ),
       .io_pcOut_valid( sink_io_pcOut_valid ),
       .io_pcOut_bits_request( sink_io_pcOut_bits_request ),
       .io_pcOut_bits_moduleId( sink_io_pcOut_bits_moduleId ),
       .io_pcOut_bits_portId( sink_io_pcOut_bits_portId ),
       .io_pcOut_bits_pcValue( sink_io_pcOut_bits_pcValue ),
       .io_pcOut_bits_pcType( sink_io_pcOut_bits_pcType ),
       .sinkOff_testOff_req_ready(  ),
       .sinkOff_testOff_req_valid(  ),
       .sinkOff_testOff_req_bits(  ),
       .sinkOff_testOff_rep_ready(  ),
       .sinkOff_testOff_rep_valid(  ),
       .sinkOff_testOff_rep_bits(  ));
endmodule

module Chained(input clk, input reset,
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

  wire[3:0] gChainedComponent_io_pcOut_bits_pcType;
  wire[15:0] gChainedComponent_io_pcOut_bits_pcValue;
  wire[7:0] gChainedComponent_io_pcOut_bits_portId;
  wire gChainedComponent_io_pcOut_bits_request;
  wire[15:0] gChainedComponent_io_pcOut_bits_moduleId;
  wire gChainedComponent_io_pcOut_valid;
  wire[31:0] gChainedComponent_io_out_bits;
  wire gChainedComponent_io_out_valid;
  wire gChainedComponent_io_in_ready;

  assign io_pcOut_bits_pcType = gChainedComponent_io_pcOut_bits_pcType;
  assign io_pcOut_bits_pcValue = gChainedComponent_io_pcOut_bits_pcValue;
  assign io_pcOut_bits_portId = gChainedComponent_io_pcOut_bits_portId;
  assign io_pcOut_bits_request = gChainedComponent_io_pcOut_bits_request;
  assign io_pcOut_bits_moduleId = gChainedComponent_io_pcOut_bits_moduleId;
  assign io_pcOut_valid = gChainedComponent_io_pcOut_valid;
  assign io_out_bits = gChainedComponent_io_out_bits;
  assign io_out_valid = gChainedComponent_io_out_valid;
  assign io_in_ready = gChainedComponent_io_in_ready;
  gChainedComponent gChainedComponent(.clk(clk), .reset(reset),
       .io_in_ready( gChainedComponent_io_in_ready ),
       .io_in_valid( io_in_valid ),
       .io_in_bits( io_in_bits ),
       .io_out_ready( io_out_ready ),
       .io_out_valid( gChainedComponent_io_out_valid ),
       .io_out_bits( gChainedComponent_io_out_bits ),
       .io_pcIn_valid( io_pcIn_valid ),
       .io_pcIn_bits_request( io_pcIn_bits_request ),
       .io_pcIn_bits_moduleId( io_pcIn_bits_moduleId ),
       .io_pcIn_bits_portId( io_pcIn_bits_portId ),
       .io_pcIn_bits_pcValue( io_pcIn_bits_pcValue ),
       .io_pcIn_bits_pcType( io_pcIn_bits_pcType ),
       .io_pcOut_valid( gChainedComponent_io_pcOut_valid ),
       .io_pcOut_bits_request( gChainedComponent_io_pcOut_bits_request ),
       .io_pcOut_bits_moduleId( gChainedComponent_io_pcOut_bits_moduleId ),
       .io_pcOut_bits_portId( gChainedComponent_io_pcOut_bits_portId ),
       .io_pcOut_bits_pcValue( gChainedComponent_io_pcOut_bits_pcValue ),
       .io_pcOut_bits_pcType( gChainedComponent_io_pcOut_bits_pcType ),
       .io_off_testOff1_req_ready(  ),
       .io_off_testOff1_req_valid(  ),
       .io_off_testOff1_req_bits(  ),
       .io_off_testOff1_rep_ready(  ),
       .io_off_testOff1_rep_valid(  ),
       .io_off_testOff1_rep_bits(  ),
       .io_off_testOff2_req_ready(  ),
       .io_off_testOff2_req_valid(  ),
       .io_off_testOff2_req_bits(  ),
       .io_off_testOff2_rep_ready(  ),
       .io_off_testOff2_rep_valid(  ),
       .io_off_testOff2_rep_bits(  ));
endmodule

