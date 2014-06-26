
module sendConst(input clk, input reset,
    output io_in_ready,
    input  io_in_valid,
    input [31:0] io_in_bits,
    input [4:0] io_in_tag,
    input  io_out_ready,
    output io_out_valid,
    output[31:0] io_out_bits,
    output[4:0] io_out_tag,
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

  reg[0:0] R0;
  wire T1;
  wire T2;
  wire T3;
  wire T4;
  wire T5;
  wire T6;
  wire T7;
  reg[3:0] R8;
  wire T9;
  wire[3:0] T10;
  wire T11;
  reg[7:0] R12;
  wire T13;
  wire[7:0] T14;
  wire T15;
  reg[15:0] R16;
  wire T17;
  wire[15:0] T18;
  wire[15:0] T19;
  wire[15:0] T20;
  reg[15:0] R21;
  wire T22;
  wire T23;
  wire T24;
  wire T25;
  wire T26;
  wire T27;
  wire[7:0] T28;
  wire[7:0] T29;
  wire T30;
  wire T31;
  wire[1:0] T32;
  wire rThreadEncoder_io_chosen;
  wire T33;
  reg[0:0] subStateTh_0;
  wire T34;
  wire T35;
  wire T36;
  wire vThreadEncoder_io_chosen;
  wire T37;
  wire AllOffloadsValid_0;
  wire T38;
  wire T39;
  wire T40;
  reg[7:0] State_0;
  wire T41;
  wire T42;
  wire T43;
  wire[7:0] T44;
  wire[7:0] T45;
  wire[7:0] T46;
  wire T47;
  wire T48;
  wire[1:0] T49;
  wire T50;
  wire T51;
  wire T52;
  wire T53;
  wire T54;
  wire T55;
  wire T56;
  wire T57;
  wire T58;
  wire T59;
  wire[1:0] T60;
  wire sThreadEncoder_io_chosen;
  wire T61;
  wire T62;
  wire T63;
  wire T64;
  wire T65;
  wire T66;
  wire[7:0] T67;
  wire[7:0] T68;
  wire[7:0] T69;
  wire[7:0] T70;
  wire[7:0] T71;
  wire[7:0] T72;
  reg[7:0] EmitReturnState_0;
  wire T73;
  wire[7:0] T74;
  wire[7:0] T75;
  wire T76;
  wire T77;
  wire T78;
  wire T79;
  wire T80;
  wire T81;
  wire T82;
  wire[3:0] T83;
  wire T84;
  wire[15:0] T85;
  wire[15:0] T86;
  wire[15:0] T87;
  reg[15:0] R88;
  wire T89;
  wire T90;
  wire T91;
  wire T92;
  wire T93;
  wire T94;
  wire T95;
  wire[3:0] T96;
  wire T97;
  wire[15:0] T98;
  wire[15:0] T99;
  wire[15:0] T100;
  wire T101;
  wire[15:0] T102;
  reg[15:0] R103;
  wire T104;
  wire[15:0] T105;
  wire T106;
  reg[0:0] R107;
  wire T108;
  wire T109;
  wire T110;
  wire[31:0] T111;
  wire[31:0] T112;
  reg[31:0] outputReg_0;
  wire[31:0] T113;

  assign io_pcOut_valid = R0;
  assign T1 = T3 || T2;
  assign T2 = ! T3;
  assign T3 = T4 && io_pcIn_bits_request;
  assign T4 = io_pcIn_valid && T5;
  assign T5 = io_pcIn_bits_moduleId == 16'h5/* 5*/;
  assign T6 = T2 ? io_pcIn_valid : T7;
  assign T7 = T3 ? 1'h1/* 1*/ : R0;
  assign io_pcOut_bits_pcType = R8;
  assign T9 = T3 || T2;
  assign T10 = T11 ? io_pcIn_bits_pcType : R8;
  assign T11 = T3 || T2;
  assign io_pcOut_bits_portId = R12;
  assign T13 = T3 || T2;
  assign T14 = T15 ? io_pcIn_bits_portId : R12;
  assign T15 = T3 || T2;
  assign io_pcOut_bits_pcValue = R16;
  assign T17 = T3 || T2;
  assign T18 = T2 ? io_pcIn_bits_pcValue : T19;
  assign T19 = T3 ? T20 : R16;
  assign T20 = T101 ? R88 : R21;
  assign T22 = T81 || T23;
  assign T23 = T80 && T24;
  assign T24 = io_out_valid && T25;
  assign T25 = ! io_out_ready;
  assign io_out_valid = T26;
  assign T26 = T79 && T27;
  assign T27 = T28 == 8'hff/* 255*/;
  assign T28 = State_0 & T29;
  assign T29 = {4'h8/* 8*/{T30}};
  assign T30 = T31;
  assign T31 = T32[1'h0/* 0*/:1'h0/* 0*/];
  assign T32 = 1'h1/* 1*/ << rThreadEncoder_io_chosen;
  assign T33 = subStateTh_0 == 1'h0/* 0*/;
  assign T34 = T39 ? 1'h1/* 1*/ : T35;
  assign T35 = T36 ? 1'h0/* 0*/ : subStateTh_0;
  assign T36 = 1'h0/* 0*/ == vThreadEncoder_io_chosen;
  assign T37 = T38 && AllOffloadsValid_0;
  assign AllOffloadsValid_0 = 1'h1/* 1*/;
  assign T38 = subStateTh_0 == 1'h1/* 1*/;
  assign T39 = T76 && T40;
  assign T40 = State_0 != 8'hff/* 255*/;
  assign T41 = T51 || T42;
  assign T42 = T50 && T43;
  assign T43 = T45 == T44;
  assign T44 = {7'h0/* 0*/, 1'h1/* 1*/};
  assign T45 = State_0 & T46;
  assign T46 = {4'h8/* 8*/{T47}};
  assign T47 = T48;
  assign T48 = T49[1'h0/* 0*/:1'h0/* 0*/];
  assign T49 = 1'h1/* 1*/ << vThreadEncoder_io_chosen;
  assign T50 = vThreadEncoder_io_chosen != 1'h1/* 1*/;
  assign T51 = T57 || T52;
  assign T52 = T53 && T30;
  assign T53 = T54 && io_out_ready;
  assign T54 = T56 && T55;
  assign T55 = T28 == 8'hff/* 255*/;
  assign T56 = rThreadEncoder_io_chosen != 1'h1/* 1*/;
  assign T57 = T64 && T58;
  assign T58 = T59;
  assign T59 = T60[1'h0/* 0*/:1'h0/* 0*/];
  assign T60 = 1'h1/* 1*/ << sThreadEncoder_io_chosen;
  assign T61 = T63 && T62;
  assign T62 = State_0 == 8'h0/* 0*/;
  assign T63 = subStateTh_0 == 1'h0/* 0*/;
  assign T64 = T66 && io_in_valid;
  assign io_in_ready = T65;
  assign T65 = sThreadEncoder_io_chosen != 1'h1/* 1*/;
  assign T66 = sThreadEncoder_io_chosen != 1'h1/* 1*/;
  assign T67 = T42 ? T75 : T68;
  assign T68 = T52 ? T71 : T69;
  assign T69 = T57 ? T70 : State_0;
  assign T70 = {7'h0/* 0*/, 1'h1/* 1*/};
  assign T71 = EmitReturnState_0 & T72;
  assign T72 = {4'h8/* 8*/{T30}};
  assign T73 = T42 && T47;
  assign T74 = T73 ? 8'h0/* 0*/ : EmitReturnState_0;
  assign T75 = {7'h0/* 0*/, 1'h1/* 1*/};
  assign T76 = T78 && T77;
  assign T77 = State_0 != 8'h0/* 0*/;
  assign T78 = 1'h0/* 0*/ == rThreadEncoder_io_chosen;
  assign T79 = rThreadEncoder_io_chosen != 1'h1/* 1*/;
  assign T80 = ! T81;
  assign T81 = T84 && T82;
  assign T82 = io_pcIn_bits_pcType == T83;
  assign T83 = {2'h0/* 0*/, 2'h2/* 2*/};
  assign T84 = io_pcIn_valid && io_pcIn_bits_request;
  assign T85 = T23 ? T87 : T86;
  assign T86 = T81 ? 16'h0/* 0*/ : R21;
  assign T87 = R21 + 16'h1/* 1*/;
  assign T89 = T94 || T90;
  assign T90 = T93 && T91;
  assign T91 = io_in_valid && T92;
  assign T92 = ! io_in_ready;
  assign T93 = ! T94;
  assign T94 = T97 && T95;
  assign T95 = io_pcIn_bits_pcType == T96;
  assign T96 = {2'h0/* 0*/, 2'h2/* 2*/};
  assign T97 = io_pcIn_valid && io_pcIn_bits_request;
  assign T98 = T90 ? T100 : T99;
  assign T99 = T94 ? 16'h0/* 0*/ : R88;
  assign T100 = R88 + 16'h1/* 1*/;
  assign T101 = T102 == 16'h1/* 1*/;
  assign T102 = {8'h0/* 0*/, io_pcIn_bits_portId};
  assign io_pcOut_bits_moduleId = R103;
  assign T104 = T3 || T2;
  assign T105 = T106 ? io_pcIn_bits_moduleId : R103;
  assign T106 = T3 || T2;
  assign io_pcOut_bits_request = R107;
  assign T108 = T3 || T2;
  assign T109 = T2 ? io_pcIn_bits_request : T110;
  assign T110 = T3 ? 1'h0/* 0*/ : R107;
  assign io_out_bits = T111;
  assign T111 = outputReg_0 & T112;
  assign T112 = {6'h20/* 32*/{T30}};
  assign T113 = T73 ? 32'h2/* 2*/ : outputReg_0;
  RREncode_6 rThreadEncoder(
       .io_valid_0( T33 ),
       .io_chosen( rThreadEncoder_io_chosen ),
       .io_ready(  ));
  RREncode_7 vThreadEncoder(
       .io_valid_0( T37 ),
       .io_chosen( vThreadEncoder_io_chosen ),
       .io_ready(  ));
  RREncode_8 sThreadEncoder(
       .io_valid_0( T61 ),
       .io_chosen( sThreadEncoder_io_chosen ),
       .io_ready(  ));

  always @(posedge clk) begin
    if(reset) begin
      R0 <= 1'h0/* 0*/;
    end else if(T1) begin
      R0 <= T6;
    end
    if(reset) begin
      R8 <= 4'h0/* 0*/;
    end else if(T9) begin
      R8 <= T10;
    end
    if(reset) begin
      R12 <= 8'h0/* 0*/;
    end else if(T13) begin
      R12 <= T14;
    end
    if(reset) begin
      R16 <= 16'h0/* 0*/;
    end else if(T17) begin
      R16 <= T18;
    end
    if(reset) begin
      R21 <= 16'h0/* 0*/;
    end else if(T22) begin
      R21 <= T85;
    end
    subStateTh_0 <= reset ? 1'h0/* 0*/ : T34;
    if(reset) begin
      State_0 <= 8'h0/* 0*/;
    end else if(T41) begin
      State_0 <= T67;
    end
    if(reset) begin
      EmitReturnState_0 <= 8'h0/* 0*/;
    end else if(T73) begin
      EmitReturnState_0 <= T74;
    end
    if(reset) begin
      R88 <= 16'h0/* 0*/;
    end else if(T89) begin
      R88 <= T98;
    end
    if(reset) begin
      R103 <= 16'h0/* 0*/;
    end else if(T104) begin
      R103 <= T105;
    end
    if(reset) begin
      R107 <= 1'h1/* 1*/;
    end else if(T108) begin
      R107 <= T109;
    end
    if(T73) begin
      outputReg_0 <= T113;
    end
  end
endmodule


