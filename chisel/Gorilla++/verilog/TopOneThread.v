module RREncode(
    input  io_valid_0,
    output io_chosen,
    input  io_ready);

  wire choose;

  assign io_chosen = choose;
  assign choose = io_valid_0 ? 1'h0/* 0*/ : 1'h1/* 1*/;
endmodule

module RREncode_1(
    input  io_valid_0,
    output io_chosen,
    input  io_ready);

  wire choose;

  assign io_chosen = choose;
  assign choose = io_valid_0 ? 1'h0/* 0*/ : 1'h1/* 1*/;
endmodule

module RREncode_2(
    input  io_valid_0,
    output io_chosen,
    input  io_ready);

  wire choose;

  assign io_chosen = choose;
  assign choose = io_valid_0 ? 1'h0/* 0*/ : 1'h1/* 1*/;
endmodule

module KDistribute(input clk, input reset,
    output io_in_ready,
    input  io_in_valid,
    input  io_in_bits_centeroidsFinished,
    input  io_in_bits_pointsFinished,
    input [15:0] io_in_bits_centeroidIndex,
    input [63:0] io_in_bits_point_x,
    input [63:0] io_in_bits_point_y,
    input [63:0] io_in_bits_point_z,
    input [4:0] io_in_tag,
    input  io_out_ready,
    output io_out_valid,
    output io_out_bits_centeroidsFinished,
    output io_out_bits_pointsFinished,
    output[15:0] io_out_bits_centeroidIndex,
    output[63:0] io_out_bits_point_x,
    output[63:0] io_out_bits_point_y,
    output[63:0] io_out_bits_point_z,
    output[4:0] io_out_tag,
    input  io_pcIn_valid,
    input  io_pcIn_bits_request,
    input [15:0] io_pcIn_bits_moduleId,
    input [7:0] io_pcIn_bits_portId,
    input [19:0] io_pcIn_bits_pcValue,
    input [3:0] io_pcIn_bits_pcType,
    output io_pcOut_valid,
    output io_pcOut_bits_request,
    output[15:0] io_pcOut_bits_moduleId,
    output[7:0] io_pcOut_bits_portId,
    output[19:0] io_pcOut_bits_pcValue,
    output[3:0] io_pcOut_bits_pcType);

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
  wire T21;
  wire[7:0] T22;
  wire[7:0] T23;
  wire T24;
  wire T25;
  wire[1:0] T26;
  wire rThreadEncoder_io_chosen;
  wire T27;
  reg[0:0] subStateTh_0;
  wire T28;
  wire T29;
  wire T30;
  wire vThreadEncoder_io_chosen;
  wire T31;
  wire AllOffloadsValid_0;
  wire T32;
  wire T33;
  wire T34;
  reg[7:0] State_0;
  wire T35;
  wire T36;
  wire T37;
  wire T38;
  wire[1:0] T39;
  wire T40;
  wire T41;
  wire[7:0] T42;
  wire[7:0] T43;
  wire[7:0] T44;
  wire T45;
  wire[1:0] T46;
  wire T47;
  wire T48;
  wire T49;
  wire T50;
  wire[7:0] T51;
  wire T52;
  wire[1:0] T53;
  wire T54;
  wire T55;
  wire T56;
  wire T57;
  wire T58;
  wire T59;
  wire[1:0] T60;
  wire T61;
  wire T62;
  wire T63;
  wire[1:0] T64;
  wire sThreadEncoder_io_chosen;
  wire T65;
  wire T66;
  wire T67;
  wire T68;
  wire T69;
  wire[1:0] T70;
  wire[7:0] T71;
  wire[7:0] T72;
  wire[7:0] T73;
  wire[7:0] T74;
  wire[7:0] T75;
  wire[7:0] T76;
  reg[7:0] EmitReturnState_0;
  wire T77;
  wire T78;
  wire T79;
  wire T80;
  wire T81;
  wire[32:0] T82;
  wire[31:0] T83;
  wire[31:0] T84;
  reg[31:0] broadcastIndex_0;
  wire T85;
  wire T86;
  wire T87;
  wire[31:0] T88;
  wire[31:0] T89;
  wire[31:0] T90;
  wire T91;
  wire[7:0] T92;
  wire[7:0] T93;
  wire T94;
  wire[7:0] T95;
  wire T96;
  wire T97;
  wire T98;
  wire T99;
  wire T100;
  wire AllOffloadsReady;
  wire T101;
  wire[1:0] T102;
  wire T103;
  reg[0:0] outputReg_0_pointsFinished;
  wire T104;
  wire T105;
  reg[0:0] inputReg_0_pointsFinished;
  wire T106;
  wire T107;
  reg[0:0] outputReg_0_centeroidsFinished;
  wire T108;
  wire T109;
  reg[0:0] inputReg_0_centeroidsFinished;
  wire T110;
  reg[7:0] R111;
  wire T112;
  wire[7:0] T113;
  wire T114;
  reg[19:0] R115;
  wire T116;
  wire[19:0] T117;
  wire[19:0] T118;
  wire[19:0] T119;
  wire[19:0] T120;
  wire[19:0] T121;
  reg[19:0] R122;
  wire T123;
  wire T124;
  wire T125;
  wire T126;
  reg[0:0] R127;
  wire T128;
  wire T129;
  wire T130;
  wire[3:0] T0;
  wire T131;
  wire T132;
  wire T133;
  wire[3:0] T1;
  wire T134;
  wire T135;
  wire T136;
  wire T137;
  wire T138;
  wire T139;
  wire T140;
  wire[1:0] T141;
  wire T142;
  wire[19:0] T143;
  wire[19:0] T144;
  wire[19:0] T145;
  wire T146;
  wire T147;
  wire[3:0] T2;
  wire T148;
  wire[19:0] T149;
  reg[19:0] R150;
  wire T151;
  wire T152;
  wire T153;
  wire T154;
  wire T155;
  wire T156;
  wire T157;
  wire[19:0] T158;
  wire[19:0] T159;
  wire[19:0] T160;
  wire T161;
  wire T162;
  wire T163;
  wire[19:0] T164;
  wire[19:0] T165;
  wire T166;
  wire[3:0] T3;
  wire[4:0] T167;
  wire[4:0] T168;
  reg[4:0] inputTag_0;
  wire[4:0] T169;

  assign io_pcOut_bits_request = R0;
  assign T1 = T3 || T2;
  assign T2 = ! T3;
  assign T3 = T4 && io_pcIn_bits_request;
  assign T4 = io_pcIn_valid && T5;
  assign T5 = io_pcIn_bits_moduleId == 16'h3/* 3*/;
  assign T6 = T2 ? io_pcIn_bits_request : T7;
  assign T7 = T3 ? 1'h0/* 0*/ : R0;
  assign io_pcOut_bits_moduleId = R8;
  assign T9 = T3 || T2;
  assign T10 = T11 ? io_pcIn_bits_moduleId : R8;
  assign T11 = T3 || T2;
  assign io_pcOut_valid = R12;
  assign T13 = T3 || T2;
  assign T14 = T2 ? io_pcIn_valid : T15;
  assign T15 = T3 ? 1'h1/* 1*/ : R12;
  assign io_pcOut_bits_pcType = R16;
  assign T17 = T3 || T2;
  assign T18 = T19 ? io_pcIn_bits_pcType : R16;
  assign T19 = T3 || T2;
  assign io_out_valid = T20;
  assign T20 = T101 && T21;
  assign T21 = T22 == 8'hff/* 255*/;
  assign T22 = State_0 & T23;
  assign T23 = {4'h8/* 8*/{T24}};
  assign T24 = T25;
  assign T25 = T26[1'h0/* 0*/:1'h0/* 0*/];
  assign T26 = 1'h1/* 1*/ << rThreadEncoder_io_chosen;
  assign T27 = subStateTh_0 == 1'h0/* 0*/;
  assign T28 = T33 ? 1'h1/* 1*/ : T29;
  assign T29 = T30 ? 1'h0/* 0*/ : subStateTh_0;
  assign T30 = 1'h0/* 0*/ == vThreadEncoder_io_chosen;
  assign T31 = T32 && AllOffloadsValid_0;
  assign AllOffloadsValid_0 = 1'h1/* 1*/;
  assign T32 = subStateTh_0 == 1'h1/* 1*/;
  assign T33 = T97 && T34;
  assign T34 = State_0 != 8'hff/* 255*/;
  assign T35 = T47 || T36;
  assign T36 = T40 && T37;
  assign T37 = T38;
  assign T38 = T39[1'h0/* 0*/:1'h0/* 0*/];
  assign T39 = 1'h1/* 1*/ << vThreadEncoder_io_chosen;
  assign T40 = T45 && T41;
  assign T41 = T43 == T42;
  assign T42 = {6'h0/* 0*/, 2'h2/* 2*/};
  assign T43 = State_0 & T44;
  assign T44 = {4'h8/* 8*/{T37}};
  assign T45 = T46 != 2'h1/* 1*/;
  assign T46 = {1'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T47 = T54 || T48;
  assign T48 = T49 && T37;
  assign T49 = T52 && T50;
  assign T50 = T43 == T51;
  assign T51 = {7'h0/* 0*/, 1'h1/* 1*/};
  assign T52 = T53 != 2'h1/* 1*/;
  assign T53 = {1'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T54 = T61 || T55;
  assign T55 = T56 && T24;
  assign T56 = T57 && io_out_ready;
  assign T57 = T59 && T58;
  assign T58 = T22 == 8'hff/* 255*/;
  assign T59 = T60 != 2'h1/* 1*/;
  assign T60 = {1'h0/* 0*/, rThreadEncoder_io_chosen};
  assign T61 = T68 && T62;
  assign T62 = T63;
  assign T63 = T64[1'h0/* 0*/:1'h0/* 0*/];
  assign T64 = 1'h1/* 1*/ << sThreadEncoder_io_chosen;
  assign T65 = T67 && T66;
  assign T66 = State_0 == 8'h0/* 0*/;
  assign T67 = subStateTh_0 == 1'h0/* 0*/;
  assign T68 = T69 && io_in_valid;
  assign T69 = T70 != 2'h1/* 1*/;
  assign T70 = {1'h0/* 0*/, sThreadEncoder_io_chosen};
  assign T71 = T96 ? 8'hff/* 255*/ : T72;
  assign T72 = T55 ? T75 : T73;
  assign T73 = T61 ? T74 : State_0;
  assign T74 = {7'h0/* 0*/, 1'h1/* 1*/};
  assign T75 = EmitReturnState_0 & T76;
  assign T76 = {4'h8/* 8*/{T24}};
  assign T77 = T91 || T78;
  assign T78 = T79 && T37;
  assign T79 = T40 && T80;
  assign T80 = ! T81;
  assign T81 = T82 >= 33'h0/* 0*/;
  assign T82 = {1'h0/* 0*/, T83};
  assign T83 = broadcastIndex_0 & T84;
  assign T84 = {6'h20/* 32*/{T37}};
  assign T85 = T86 || T78;
  assign T86 = T87 && T37;
  assign T87 = T40 && T81;
  assign T88 = T78 ? T90 : T89;
  assign T89 = T86 ? 32'h0/* 0*/ : broadcastIndex_0;
  assign T90 = T83 + 32'h1/* 1*/;
  assign T91 = T48 || T86;
  assign T92 = T78 ? T95 : T93;
  assign T93 = T94 ? 8'h0/* 0*/ : EmitReturnState_0;
  assign T94 = T48 || T86;
  assign T95 = {6'h0/* 0*/, 2'h2/* 2*/};
  assign T96 = T48 || T36;
  assign T97 = T99 && T98;
  assign T98 = State_0 != 8'h0/* 0*/;
  assign T99 = AllOffloadsReady && T100;
  assign T100 = 1'h0/* 0*/ == rThreadEncoder_io_chosen;
  assign AllOffloadsReady = 1'h1/* 1*/;
  assign T101 = T102 != 2'h1/* 1*/;
  assign T102 = {1'h0/* 0*/, rThreadEncoder_io_chosen};
  assign io_out_bits_pointsFinished = T103;
  assign T103 = outputReg_0_pointsFinished & T24;
  assign T104 = T48 ? T105 : outputReg_0_pointsFinished;
  assign T105 = inputReg_0_pointsFinished & T37;
  assign T106 = T61 ? io_in_bits_pointsFinished : inputReg_0_pointsFinished;
  assign io_out_bits_centeroidsFinished = T107;
  assign T107 = outputReg_0_centeroidsFinished & T24;
  assign T108 = T48 ? T109 : outputReg_0_centeroidsFinished;
  assign T109 = inputReg_0_centeroidsFinished & T37;
  assign T110 = T61 ? io_in_bits_centeroidsFinished : inputReg_0_centeroidsFinished;
  assign io_pcOut_bits_portId = R111;
  assign T112 = T3 || T2;
  assign T113 = T114 ? io_pcIn_bits_portId : R111;
  assign T114 = T3 || T2;
  assign io_pcOut_bits_pcValue = R115;
  assign T116 = T3 || T2;
  assign T117 = T2 ? io_pcIn_bits_pcValue : T118;
  assign T118 = T3 ? T119 : R115;
  assign T119 = T166 ? T165 : T120;
  assign T120 = T161 ? R150 : T121;
  assign T121 = T146 ? R122 : 20'h0/* 0*/;
  assign T123 = T132 || T124;
  assign T124 = T142 && T125;
  assign T125 = T138 && T126;
  assign T126 = ! R127;
  assign T128 = T132 || T129;
  assign T129 = T131 && T130;
  assign T130 = io_pcIn_bits_pcType == T0;
  assign T0 = {1'h0/* 0*/, 3'h4/* 4*/};
  assign T131 = io_pcIn_valid && io_pcIn_bits_request;
  assign T132 = T134 && T133;
  assign T133 = io_pcIn_bits_pcType == T1;
  assign T1 = {1'h0/* 0*/, 3'h3/* 3*/};
  assign T134 = io_pcIn_valid && io_pcIn_bits_request;
  assign T135 = T129 ? T137 : T136;
  assign T136 = T132 ? 1'h0/* 0*/ : R127;
  assign T137 = ! R127;
  assign T138 = io_in_valid && T139;
  assign T139 = ! io_in_ready;
  assign io_in_ready = T140;
  assign T140 = T141 != 2'h1/* 1*/;
  assign T141 = {1'h0/* 0*/, sThreadEncoder_io_chosen};
  assign T142 = ! T132;
  assign T143 = T124 ? T145 : T144;
  assign T144 = T132 ? 20'h0/* 0*/ : R122;
  assign T145 = R122 + 20'h1/* 1*/;
  assign T146 = T148 && T147;
  assign T147 = io_pcIn_bits_pcType == T2;
  assign T2 = {1'h0/* 0*/, 3'h1/* 1*/};
  assign T148 = T149 == 20'h1/* 1*/;
  assign T149 = {12'h0/* 0*/, io_pcIn_bits_portId};
  assign T151 = T132 || T152;
  assign T152 = T157 && T153;
  assign T153 = T155 && T154;
  assign T154 = ! R127;
  assign T155 = io_out_valid && T156;
  assign T156 = ! io_out_ready;
  assign T157 = ! T132;
  assign T158 = T152 ? T160 : T159;
  assign T159 = T132 ? 20'h0/* 0*/ : R150;
  assign T160 = R150 + 20'h1/* 1*/;
  assign T161 = T163 && T162;
  assign T162 = io_pcIn_bits_pcType == T2;
  assign T163 = T164 == 20'h2/* 2*/;
  assign T164 = {12'h0/* 0*/, io_pcIn_bits_portId};
  assign T165 = {15'h0/* 0*/, 5'h14/* 20*/};
  assign T166 = io_pcIn_bits_pcType == T3;
  assign T3 = {1'h0/* 0*/, 3'h2/* 2*/};
  assign io_out_tag = T167;
  assign T167 = inputTag_0 & T168;
  assign T168 = {3'h5/* 5*/{T24}};
  assign T169 = T61 ? io_in_tag : inputTag_0;
  RREncode rThreadEncoder(
       .io_valid_0( T27 ),
       .io_chosen( rThreadEncoder_io_chosen ),
       .io_ready(  ));
  RREncode_1 vThreadEncoder(
       .io_valid_0( T31 ),
       .io_chosen( vThreadEncoder_io_chosen ),
       .io_ready(  ));
  RREncode_2 sThreadEncoder(
       .io_valid_0( T65 ),
       .io_chosen( sThreadEncoder_io_chosen ),
       .io_ready(  ));

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
    subStateTh_0 <= reset ? 1'h0/* 0*/ : T28;
    if(reset) begin
      State_0 <= 8'h0/* 0*/;
    end else if(T35) begin
      State_0 <= T71;
    end
    if(reset) begin
      EmitReturnState_0 <= 8'h0/* 0*/;
    end else if(T77) begin
      EmitReturnState_0 <= T92;
    end
    if(reset) begin
      broadcastIndex_0 <= 32'h0/* 0*/;
    end else if(T85) begin
      broadcastIndex_0 <= T88;
    end
    if(T48) begin
      outputReg_0_pointsFinished <= T104;
    end
    if(T61) begin
      inputReg_0_pointsFinished <= T106;
    end
    if(T48) begin
      outputReg_0_centeroidsFinished <= T108;
    end
    if(T61) begin
      inputReg_0_centeroidsFinished <= T110;
    end
    if(reset) begin
      R111 <= 8'h0/* 0*/;
    end else if(T112) begin
      R111 <= T113;
    end
    if(reset) begin
      R115 <= 20'h0/* 0*/;
    end else if(T116) begin
      R115 <= T117;
    end
    if(reset) begin
      R122 <= 20'h0/* 0*/;
    end else if(T123) begin
      R122 <= T143;
    end
    if(reset) begin
      R127 <= 1'h0/* 0*/;
    end else if(T128) begin
      R127 <= T135;
    end
    if(reset) begin
      R150 <= 20'h0/* 0*/;
    end else if(T151) begin
      R150 <= T158;
    end
    if(T61) begin
      inputTag_0 <= T169;
    end
  end
endmodule

module RREncode_3(
    input  io_valid_0,
    output io_chosen,
    input  io_ready);

  wire choose;

  assign io_chosen = choose;
  assign choose = io_valid_0 ? 1'h0/* 0*/ : 1'h1/* 1*/;
endmodule

module RREncode_4(
    input  io_valid_0,
    output io_chosen,
    input  io_ready);

  wire choose;

  assign io_chosen = choose;
  assign choose = io_valid_0 ? 1'h0/* 0*/ : 1'h1/* 1*/;
endmodule

module RREncode_5(
    input  io_valid_0,
    output io_chosen,
    input  io_ready);

  wire choose;

  assign io_chosen = choose;
  assign choose = io_valid_0 ? 1'h0/* 0*/ : 1'h1/* 1*/;
endmodule

module KEngine(input clk, input reset,
    output io_in_ready,
    input  io_in_valid,
    input  io_in_bits_centeroidsFinished,
    input  io_in_bits_pointsFinished,
    input [15:0] io_in_bits_centeroidIndex,
    input [63:0] io_in_bits_point_x,
    input [63:0] io_in_bits_point_y,
    input [63:0] io_in_bits_point_z,
    input [4:0] io_in_tag,
    input  io_out_ready,
    output io_out_valid,
    output io_out_bits_centeroidsFinished,
    output io_out_bits_pointsFinished,
    output[15:0] io_out_bits_centeroidIndex,
    output[63:0] io_out_bits_point_x,
    output[63:0] io_out_bits_point_y,
    output[63:0] io_out_bits_point_z,
    output[4:0] io_out_tag,
    input  io_pcIn_valid,
    input  io_pcIn_bits_request,
    input [15:0] io_pcIn_bits_moduleId,
    input [7:0] io_pcIn_bits_portId,
    input [19:0] io_pcIn_bits_pcValue,
    input [3:0] io_pcIn_bits_pcType,
    output io_pcOut_valid,
    output io_pcOut_bits_request,
    output[15:0] io_pcOut_bits_moduleId,
    output[7:0] io_pcOut_bits_portId,
    output[19:0] io_pcOut_bits_pcValue,
    output[3:0] io_pcOut_bits_pcType,
    input  mainOff_distanceFU_req_ready,
    output mainOff_distanceFU_req_valid,
    output[63:0] mainOff_distanceFU_req_bits_in1_x,
    output[63:0] mainOff_distanceFU_req_bits_in1_y,
    output[63:0] mainOff_distanceFU_req_bits_in1_z,
    output[63:0] mainOff_distanceFU_req_bits_in2_x,
    output[63:0] mainOff_distanceFU_req_bits_in2_y,
    output[63:0] mainOff_distanceFU_req_bits_in2_z,
    output[4:0] mainOff_distanceFU_req_tag,
    output mainOff_distanceFU_rep_ready,
    input  mainOff_distanceFU_rep_valid,
    input [63:0] mainOff_distanceFU_rep_bits_out,
    input [4:0] mainOff_distanceFU_rep_tag,
    input  mainOff_centeroidMem_req_ready,
    output mainOff_centeroidMem_req_valid,
    output mainOff_centeroidMem_req_bits_rw,
    output[15:0] mainOff_centeroidMem_req_bits_addr,
    output[63:0] mainOff_centeroidMem_req_bits_data_x,
    output[63:0] mainOff_centeroidMem_req_bits_data_y,
    output[63:0] mainOff_centeroidMem_req_bits_data_z,
    output[4:0] mainOff_centeroidMem_req_tag,
    output mainOff_centeroidMem_rep_ready,
    input  mainOff_centeroidMem_rep_valid,
    input [63:0] mainOff_centeroidMem_rep_bits_data_x,
    input [63:0] mainOff_centeroidMem_rep_bits_data_y,
    input [63:0] mainOff_centeroidMem_rep_bits_data_z,
    input [4:0] mainOff_centeroidMem_rep_tag,
    input  mainOff_partialAccumulatorMem_req_ready,
    output mainOff_partialAccumulatorMem_req_valid,
    output mainOff_partialAccumulatorMem_req_bits_rw,
    output[15:0] mainOff_partialAccumulatorMem_req_bits_addr,
    output[63:0] mainOff_partialAccumulatorMem_req_bits_data_x,
    output[63:0] mainOff_partialAccumulatorMem_req_bits_data_y,
    output[63:0] mainOff_partialAccumulatorMem_req_bits_data_z,
    output[4:0] mainOff_partialAccumulatorMem_req_tag,
    output mainOff_partialAccumulatorMem_rep_ready,
    input  mainOff_partialAccumulatorMem_rep_valid,
    input [63:0] mainOff_partialAccumulatorMem_rep_bits_data_x,
    input [63:0] mainOff_partialAccumulatorMem_rep_bits_data_y,
    input [63:0] mainOff_partialAccumulatorMem_rep_bits_data_z,
    input [4:0] mainOff_partialAccumulatorMem_rep_tag,
    input  mainOff_add_req_ready,
    output mainOff_add_req_valid,
    output[63:0] mainOff_add_req_bits_in1,
    output[63:0] mainOff_add_req_bits_in2,
    output[4:0] mainOff_add_req_tag,
    output mainOff_add_rep_ready,
    input  mainOff_add_rep_valid,
    input [63:0] mainOff_add_rep_bits_out,
    input [4:0] mainOff_add_rep_tag);

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
  wire T21;
  wire[7:0] T22;
  wire[7:0] T23;
  wire T24;
  wire T25;
  wire[1:0] T26;
  wire rThreadEncoder_io_chosen;
  wire T27;
  reg[0:0] subStateTh_0;
  wire T28;
  wire T29;
  wire T30;
  wire vThreadEncoder_io_chosen;
  wire T31;
  wire AllOffloadsValid_0;
  wire T32;
  wire T33;
  wire T34;
  reg[0:0] addPortHadValidRequest_0;
  wire T35;
  wire T36;
  wire T37;
  wire addPort_req_valid;
  wire T38;
  wire T39;
  wire T40;
  wire T41;
  wire[7:0] T42;
  wire T43;
  wire[1:0] T44;
  wire T45;
  wire T46;
  wire T47;
  wire[7:0] T48;
  wire T49;
  wire[1:0] T50;
  wire T51;
  wire T52;
  wire[7:0] T53;
  wire T54;
  wire[1:0] T55;
  wire T56;
  wire T57;
  wire T58;
  reg[0:0] add_valid_received_0;
  wire T59;
  wire T60;
  wire T61;
  wire T62;
  wire[4:0] addPort_rep_tag;
  wire addPort_rep_ready;
  wire[4:0] addPort_req_tag;
  wire[4:0] T63;
  wire addPort_rep_valid;
  wire T64;
  wire T65;
  wire[4:0] T66;
  wire T67;
  wire[1:0] T68;
  wire T69;
  wire[4:0] T70;
  wire T71;
  wire T72;
  wire[4:0] T73;
  wire T74;
  wire T75;
  wire T76;
  wire T77;
  wire T78;
  wire T79;
  reg[0:0] partialAccumulatorMemPortHadValidRequest_0;
  wire T80;
  wire T81;
  wire T82;
  wire partialAccumulatorMemPort_req_valid;
  wire T83;
  wire T84;
  wire T85;
  wire T86;
  wire[7:0] T87;
  wire T88;
  wire[1:0] T89;
  wire T90;
  wire T91;
  wire T92;
  wire[7:0] T93;
  wire T94;
  wire[1:0] T95;
  wire T96;
  wire T97;
  wire T98;
  wire[7:0] T99;
  wire T100;
  wire[1:0] T101;
  wire T102;
  wire T103;
  wire[7:0] T104;
  wire T105;
  wire[1:0] T106;
  wire T107;
  wire T108;
  wire T109;
  reg[0:0] partialAccumulatorMem_valid_received_0;
  wire T110;
  wire T111;
  wire T112;
  wire T113;
  wire[4:0] partialAccumulatorMemPort_rep_tag;
  wire partialAccumulatorMemPort_rep_ready;
  wire[4:0] partialAccumulatorMemPort_req_tag;
  wire[4:0] T114;
  wire partialAccumulatorMemPort_rep_valid;
  wire T115;
  wire T116;
  wire[4:0] T117;
  wire T118;
  wire[1:0] T119;
  wire T120;
  wire[4:0] T121;
  wire T122;
  wire T123;
  wire[4:0] T124;
  wire T125;
  wire T126;
  wire T127;
  wire T128;
  wire T129;
  wire T130;
  reg[0:0] centeroidMemPortHadValidRequest_0;
  wire T131;
  wire T132;
  wire T133;
  wire centeroidMemPort_req_valid;
  wire T134;
  wire T135;
  wire T136;
  wire T137;
  wire[7:0] T138;
  wire T139;
  wire[1:0] T140;
  wire T141;
  wire T142;
  wire[7:0] T143;
  wire T144;
  wire[1:0] T145;
  wire T146;
  wire T147;
  wire T148;
  reg[0:0] centeroidMem_valid_received_0;
  wire T149;
  wire T150;
  wire T151;
  wire T152;
  wire[4:0] centeroidMemPort_rep_tag;
  wire centeroidMemPort_rep_ready;
  wire[4:0] centeroidMemPort_req_tag;
  wire[4:0] T153;
  wire centeroidMemPort_rep_valid;
  wire T154;
  wire T155;
  wire[4:0] T156;
  wire T157;
  wire[1:0] T158;
  wire T159;
  wire[4:0] T160;
  wire T161;
  wire T162;
  wire[4:0] T163;
  wire T164;
  wire T165;
  wire T166;
  wire T167;
  wire T168;
  reg[0:0] distanceFUPortHadValidRequest_0;
  wire T169;
  wire T170;
  wire T171;
  wire distanceFUPort_req_valid;
  wire T172;
  wire T173;
  wire T174;
  wire[7:0] T175;
  wire T176;
  wire[1:0] T177;
  wire T178;
  wire T179;
  wire T180;
  reg[0:0] distanceFU_valid_received_0;
  wire T181;
  wire T182;
  wire T183;
  wire T184;
  wire[4:0] distanceFUPort_rep_tag;
  wire distanceFUPort_rep_ready;
  wire[4:0] distanceFUPort_req_tag;
  wire[4:0] T185;
  wire distanceFUPort_rep_valid;
  wire T186;
  wire T187;
  wire[4:0] T188;
  wire T189;
  wire[1:0] T190;
  wire T191;
  wire[4:0] T192;
  wire T193;
  wire T194;
  wire[4:0] T195;
  wire T196;
  wire T197;
  wire T198;
  wire T199;
  wire T200;
  wire T201;
  reg[7:0] State_0;
  wire T202;
  wire T203;
  wire T204;
  wire T205;
  wire[1:0] T206;
  wire T207;
  wire T208;
  wire[7:0] T209;
  wire[7:0] T210;
  wire[7:0] T211;
  wire T212;
  wire[1:0] T213;
  wire T214;
  wire T215;
  wire T216;
  wire T217;
  wire T218;
  wire[31:0] T219;
  wire[31:0] T220;
  reg[31:0] centeroidIndex_0;
  wire T221;
  wire T222;
  wire T223;
  wire T224;
  wire T225;
  wire[7:0] T226;
  wire T227;
  wire[1:0] T228;
  wire T229;
  wire T230;
  wire T231;
  wire T232;
  wire T233;
  wire T234;
  wire[7:0] T235;
  wire T236;
  wire[1:0] T237;
  wire T238;
  wire T239;
  wire T240;
  wire T241;
  wire[7:0] T242;
  wire T243;
  wire[1:0] T244;
  wire T245;
  wire T246;
  wire T247;
  wire T248;
  wire[7:0] T249;
  wire T250;
  wire[1:0] T251;
  wire T252;
  wire T253;
  wire T254;
  wire[7:0] T255;
  wire T256;
  wire[1:0] T257;
  wire[31:0] T258;
  wire[31:0] T259;
  wire[31:0] T260;
  wire[31:0] T261;
  wire[31:0] T262;
  wire[31:0] T263;
  wire[31:0] T264;
  wire[31:0] T265;
  wire[31:0] T266;
  wire T267;
  wire T268;
  wire T269;
  wire T270;
  wire T271;
  wire T272;
  wire[7:0] T273;
  wire T274;
  wire[1:0] T275;
  wire T276;
  wire T277;
  wire T278;
  wire T279;
  wire[7:0] T280;
  wire T281;
  wire[1:0] T282;
  wire T283;
  wire T284;
  wire T285;
  wire T286;
  wire[7:0] T287;
  wire T288;
  wire[1:0] T289;
  wire T290;
  wire T291;
  wire T292;
  wire T293;
  wire[7:0] T294;
  wire T295;
  wire[1:0] T296;
  wire T297;
  wire T298;
  wire T299;
  wire T300;
  wire[7:0] T301;
  wire T302;
  wire[1:0] T303;
  wire T304;
  wire T305;
  wire T306;
  wire T307;
  wire T308;
  wire T309;
  wire T310;
  wire T311;
  wire T312;
  wire T313;
  wire T314;
  wire T315;
  wire[7:0] T316;
  wire T317;
  wire[1:0] T318;
  wire T319;
  wire T320;
  wire T321;
  wire T322;
  wire T323;
  wire T324;
  reg[0:0] inputReg_0_pointsFinished;
  wire T325;
  wire T326;
  wire T327;
  wire[1:0] T328;
  wire sThreadEncoder_io_chosen;
  wire T329;
  wire T330;
  wire T331;
  wire T332;
  wire T333;
  wire[1:0] T334;
  wire T335;
  wire[1:0] T336;
  wire T337;
  wire T338;
  wire T339;
  wire[7:0] T340;
  wire T341;
  wire[1:0] T342;
  wire T343;
  wire T344;
  wire T345;
  wire T346;
  wire[31:0] T347;
  wire[31:0] T348;
  reg[31:0] mode_0;
  wire T349;
  wire T350;
  wire T351;
  wire T352;
  reg[0:0] inputReg_0_centeroidsFinished;
  wire T353;
  wire[31:0] T354;
  wire[31:0] T355;
  wire T356;
  wire T357;
  wire T358;
  wire T359;
  wire T360;
  wire T361;
  wire T362;
  wire T363;
  wire T364;
  wire T365;
  wire T366;
  wire[1:0] T367;
  wire[7:0] T368;
  wire[7:0] T369;
  wire[7:0] T370;
  wire[7:0] T371;
  wire[7:0] T372;
  wire[7:0] T373;
  wire[7:0] T374;
  wire[7:0] T375;
  wire[7:0] T376;
  wire[7:0] T377;
  wire[7:0] T378;
  wire[7:0] T379;
  wire[7:0] T380;
  wire[7:0] T381;
  wire[7:0] T382;
  wire[7:0] T383;
  wire[7:0] T384;
  wire[7:0] T385;
  wire[7:0] T386;
  wire[7:0] T387;
  wire[7:0] T388;
  wire[7:0] T389;
  wire[7:0] T390;
  reg[7:0] EmitReturnState_0;
  wire[7:0] T391;
  wire[7:0] T392;
  wire[7:0] T393;
  wire[7:0] T394;
  wire[7:0] T395;
  wire[7:0] T396;
  wire[7:0] T397;
  wire[7:0] T398;
  wire[7:0] T399;
  wire[7:0] T400;
  wire[7:0] T401;
  wire[7:0] T402;
  wire[7:0] T403;
  wire[7:0] T404;
  wire[7:0] T405;
  wire T406;
  wire T407;
  wire T408;
  wire T409;
  wire AllOffloadsReady;
  wire T410;
  wire T411;
  wire T412;
  wire T413;
  wire T414;
  reg[0:0] addPortHadReadyRequest;
  wire T415;
  wire T416;
  wire T417;
  wire T418;
  reg[0:0] add_ready_received;
  wire T419;
  wire T420;
  wire addPort_req_ready;
  wire T421;
  wire T422;
  wire T423;
  wire T424;
  wire T425;
  wire T426;
  reg[0:0] partialAccumulatorMemPortHadReadyRequest;
  wire T427;
  wire T428;
  wire T429;
  wire T430;
  reg[0:0] partialAccumulatorMem_ready_received;
  wire T431;
  wire T432;
  wire partialAccumulatorMemPort_req_ready;
  wire T433;
  wire T434;
  wire T435;
  wire T436;
  wire T437;
  wire T438;
  reg[0:0] centeroidMemPortHadReadyRequest;
  wire T439;
  wire T440;
  wire T441;
  wire T442;
  reg[0:0] centeroidMem_ready_received;
  wire T443;
  wire T444;
  wire centeroidMemPort_req_ready;
  wire T445;
  wire T446;
  wire T447;
  wire T448;
  wire T449;
  reg[0:0] distanceFUPortHadReadyRequest;
  wire T450;
  wire T451;
  wire T452;
  wire T453;
  reg[0:0] distanceFU_ready_received;
  wire T454;
  wire T455;
  wire distanceFUPort_req_ready;
  wire T456;
  wire T457;
  wire[1:0] T458;
  wire[15:0] T459;
  wire[15:0] T460;
  reg[15:0] outputReg_0_centeroidIndex;
  wire[31:0] T461;
  wire[31:0] T462;
  reg[7:0] R463;
  wire T464;
  wire[7:0] T465;
  wire T466;
  reg[19:0] R467;
  wire T468;
  wire[19:0] T469;
  wire[19:0] T470;
  wire[19:0] T471;
  wire[19:0] T472;
  wire[19:0] T473;
  wire[19:0] T474;
  wire[19:0] T475;
  wire[19:0] T476;
  wire[19:0] T477;
  reg[19:0] R478;
  wire T479;
  wire T480;
  wire T481;
  wire T482;
  reg[0:0] R483;
  wire T484;
  wire T485;
  wire T486;
  wire[3:0] T0;
  wire T487;
  wire T488;
  wire T489;
  wire[3:0] T1;
  wire T490;
  wire T491;
  wire T492;
  wire T493;
  wire T494;
  wire T495;
  wire T496;
  wire[19:0] T497;
  wire[19:0] T498;
  wire[19:0] T499;
  wire T500;
  wire T501;
  wire[3:0] T2;
  wire T502;
  wire[19:0] T503;
  reg[19:0] R504;
  wire T505;
  wire T506;
  wire T507;
  wire T508;
  wire T509;
  wire T510;
  wire T511;
  wire[19:0] T512;
  wire[19:0] T513;
  wire[19:0] T514;
  wire T515;
  wire T516;
  wire T517;
  wire[19:0] T518;
  reg[19:0] R519;
  wire T520;
  wire T521;
  wire T522;
  wire T523;
  wire T524;
  wire T525;
  wire T526;
  wire[19:0] T527;
  wire[19:0] T528;
  wire[19:0] T529;
  wire T530;
  wire T531;
  wire T532;
  wire[19:0] T533;
  reg[19:0] R534;
  wire T535;
  wire T536;
  wire T537;
  wire T538;
  wire T539;
  wire T540;
  wire T541;
  wire[19:0] T542;
  wire[19:0] T543;
  wire[19:0] T544;
  wire T545;
  wire T546;
  wire T547;
  wire[19:0] T548;
  reg[19:0] R549;
  wire T550;
  wire T551;
  wire T552;
  wire T553;
  wire T554;
  wire T555;
  wire T556;
  wire[19:0] T557;
  wire[19:0] T558;
  wire[19:0] T559;
  wire T560;
  wire T561;
  wire T562;
  wire[19:0] T563;
  reg[19:0] R564;
  wire T565;
  wire T566;
  wire T567;
  wire T568;
  wire T569;
  wire T570;
  wire T571;
  wire[19:0] T572;
  wire[19:0] T573;
  wire[19:0] T574;
  wire T575;
  wire T576;
  wire T577;
  wire[19:0] T578;
  wire[19:0] T579;
  reg[19:0] R580;
  wire T581;
  wire T582;
  wire T583;
  wire T584;
  wire T585;
  wire T586;
  wire T587;
  wire[19:0] T588;
  wire[19:0] T589;
  wire[19:0] T590;
  wire[19:0] T591;
  reg[19:0] R592;
  wire T593;
  wire T594;
  wire T595;
  wire T596;
  wire T597;
  wire T598;
  wire T599;
  wire[19:0] T600;
  wire[19:0] T601;
  wire[19:0] T602;
  wire[19:0] T603;
  reg[19:0] R604;
  wire T605;
  wire T606;
  wire T607;
  wire T608;
  wire T609;
  wire T610;
  wire T611;
  wire[19:0] T612;
  wire[19:0] T613;
  wire[19:0] T614;
  reg[19:0] R615;
  wire T616;
  wire T617;
  wire T618;
  wire T619;
  wire T620;
  wire T621;
  wire T622;
  wire[19:0] T623;
  wire[19:0] T624;
  wire[19:0] T625;
  wire T626;
  wire T627;
  wire T628;
  wire T629;
  wire[3:0] T3;
  wire[4:0] T630;
  wire[4:0] T631;
  reg[4:0] inputTag_0;
  wire[4:0] T632;

  assign io_pcOut_bits_request = R0;
  assign T1 = T3 || T2;
  assign T2 = ! T3;
  assign T3 = T4 && io_pcIn_bits_request;
  assign T4 = io_pcIn_valid && T5;
  assign T5 = io_pcIn_bits_moduleId == 16'h9/* 9*/;
  assign T6 = T2 ? io_pcIn_bits_request : T7;
  assign T7 = T3 ? 1'h0/* 0*/ : R0;
  assign io_pcOut_bits_moduleId = R8;
  assign T9 = T3 || T2;
  assign T10 = T11 ? io_pcIn_bits_moduleId : R8;
  assign T11 = T3 || T2;
  assign io_pcOut_valid = R12;
  assign T13 = T3 || T2;
  assign T14 = T2 ? io_pcIn_valid : T15;
  assign T15 = T3 ? 1'h1/* 1*/ : R12;
  assign io_pcOut_bits_pcType = R16;
  assign T17 = T3 || T2;
  assign T18 = T19 ? io_pcIn_bits_pcType : R16;
  assign T19 = T3 || T2;
  assign io_out_valid = T20;
  assign T20 = T457 && T21;
  assign T21 = T22 == 8'hff/* 255*/;
  assign T22 = State_0 & T23;
  assign T23 = {4'h8/* 8*/{T24}};
  assign T24 = T25;
  assign T25 = T26[1'h0/* 0*/:1'h0/* 0*/];
  assign T26 = 1'h1/* 1*/ << rThreadEncoder_io_chosen;
  assign T27 = subStateTh_0 == 1'h0/* 0*/;
  assign T28 = T200 ? 1'h1/* 1*/ : T29;
  assign T29 = T30 ? 1'h0/* 0*/ : subStateTh_0;
  assign T30 = 1'h0/* 0*/ == vThreadEncoder_io_chosen;
  assign T31 = T199 && AllOffloadsValid_0;
  assign AllOffloadsValid_0 = T32;
  assign T32 = T77 && T33;
  assign T33 = T74 || T34;
  assign T34 = ! addPortHadValidRequest_0;
  assign T35 = T71 && T36;
  assign T36 = addPortHadValidRequest_0 || T37;
  assign T37 = T69 && addPort_req_valid;
  assign addPort_req_valid = T38;
  assign T38 = T56 && T39;
  assign T39 = T45 || T40;
  assign T40 = T43 && T41;
  assign T41 = T22 == T42;
  assign T42 = {4'h0/* 0*/, 4'h9/* 9*/};
  assign T43 = T44 != 2'h1/* 1*/;
  assign T44 = {1'h0/* 0*/, rThreadEncoder_io_chosen};
  assign T45 = T51 || T46;
  assign T46 = T49 && T47;
  assign T47 = T22 == T48;
  assign T48 = {4'h0/* 0*/, 4'h8/* 8*/};
  assign T49 = T50 != 2'h1/* 1*/;
  assign T50 = {1'h0/* 0*/, rThreadEncoder_io_chosen};
  assign T51 = T54 && T52;
  assign T52 = T22 == T53;
  assign T53 = {5'h0/* 0*/, 3'h7/* 7*/};
  assign T54 = T55 != 2'h1/* 1*/;
  assign T55 = {1'h0/* 0*/, rThreadEncoder_io_chosen};
  assign T56 = T67 && T57;
  assign T57 = ! T58;
  assign T58 = add_valid_received_0 & T24;
  assign T59 = T64 && T60;
  assign T60 = add_valid_received_0 || T61;
  assign T61 = addPort_rep_valid && T62;
  assign T62 = addPort_rep_tag == 5'h0/* 0*/;
  assign addPort_rep_tag = mainOff_add_rep_tag;
  assign mainOff_add_rep_ready = addPort_rep_ready;
  assign addPort_rep_ready = 1'h1/* 1*/;
  assign mainOff_add_req_valid = addPort_req_valid;
  assign mainOff_add_req_tag = addPort_req_tag;
  assign addPort_req_tag = T63;
  assign T63 = {4'h0/* 0*/, rThreadEncoder_io_chosen};
  assign addPort_rep_valid = mainOff_add_rep_valid;
  assign T64 = ! T65;
  assign T65 = T66 == 5'h0/* 0*/;
  assign T66 = {4'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T67 = T68 != 2'h1/* 1*/;
  assign T68 = {1'h0/* 0*/, rThreadEncoder_io_chosen};
  assign T69 = 5'h0/* 0*/ == T70;
  assign T70 = {4'h0/* 0*/, rThreadEncoder_io_chosen};
  assign T71 = ! T72;
  assign T72 = T73 == 5'h0/* 0*/;
  assign T73 = {4'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T74 = T75 || add_valid_received_0;
  assign T75 = addPort_rep_valid && T76;
  assign T76 = addPort_rep_tag == 5'h0/* 0*/;
  assign T77 = T128 && T78;
  assign T78 = T125 || T79;
  assign T79 = ! partialAccumulatorMemPortHadValidRequest_0;
  assign T80 = T122 && T81;
  assign T81 = partialAccumulatorMemPortHadValidRequest_0 || T82;
  assign T82 = T120 && partialAccumulatorMemPort_req_valid;
  assign partialAccumulatorMemPort_req_valid = T83;
  assign T83 = T107 && T84;
  assign T84 = T90 || T85;
  assign T85 = T88 && T86;
  assign T86 = T22 == T87;
  assign T87 = {4'h0/* 0*/, 4'hc/* 12*/};
  assign T88 = T89 != 2'h1/* 1*/;
  assign T89 = {1'h0/* 0*/, rThreadEncoder_io_chosen};
  assign T90 = T96 || T91;
  assign T91 = T94 && T92;
  assign T92 = T22 == T93;
  assign T93 = {4'h0/* 0*/, 4'ha/* 10*/};
  assign T94 = T95 != 2'h1/* 1*/;
  assign T95 = {1'h0/* 0*/, rThreadEncoder_io_chosen};
  assign T96 = T102 || T97;
  assign T97 = T100 && T98;
  assign T98 = T22 == T99;
  assign T99 = {5'h0/* 0*/, 3'h6/* 6*/};
  assign T100 = T101 != 2'h1/* 1*/;
  assign T101 = {1'h0/* 0*/, rThreadEncoder_io_chosen};
  assign T102 = T105 && T103;
  assign T103 = T22 == T104;
  assign T104 = {6'h0/* 0*/, 2'h2/* 2*/};
  assign T105 = T106 != 2'h1/* 1*/;
  assign T106 = {1'h0/* 0*/, rThreadEncoder_io_chosen};
  assign T107 = T118 && T108;
  assign T108 = ! T109;
  assign T109 = partialAccumulatorMem_valid_received_0 & T24;
  assign T110 = T115 && T111;
  assign T111 = partialAccumulatorMem_valid_received_0 || T112;
  assign T112 = partialAccumulatorMemPort_rep_valid && T113;
  assign T113 = partialAccumulatorMemPort_rep_tag == 5'h0/* 0*/;
  assign partialAccumulatorMemPort_rep_tag = mainOff_partialAccumulatorMem_rep_tag;
  assign mainOff_partialAccumulatorMem_rep_ready = partialAccumulatorMemPort_rep_ready;
  assign partialAccumulatorMemPort_rep_ready = 1'h1/* 1*/;
  assign mainOff_partialAccumulatorMem_req_valid = partialAccumulatorMemPort_req_valid;
  assign mainOff_partialAccumulatorMem_req_tag = partialAccumulatorMemPort_req_tag;
  assign partialAccumulatorMemPort_req_tag = T114;
  assign T114 = {4'h0/* 0*/, rThreadEncoder_io_chosen};
  assign partialAccumulatorMemPort_rep_valid = mainOff_partialAccumulatorMem_rep_valid;
  assign T115 = ! T116;
  assign T116 = T117 == 5'h0/* 0*/;
  assign T117 = {4'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T118 = T119 != 2'h1/* 1*/;
  assign T119 = {1'h0/* 0*/, rThreadEncoder_io_chosen};
  assign T120 = 5'h0/* 0*/ == T121;
  assign T121 = {4'h0/* 0*/, rThreadEncoder_io_chosen};
  assign T122 = ! T123;
  assign T123 = T124 == 5'h0/* 0*/;
  assign T124 = {4'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T125 = T126 || partialAccumulatorMem_valid_received_0;
  assign T126 = partialAccumulatorMemPort_rep_valid && T127;
  assign T127 = partialAccumulatorMemPort_rep_tag == 5'h0/* 0*/;
  assign T128 = T167 && T129;
  assign T129 = T164 || T130;
  assign T130 = ! centeroidMemPortHadValidRequest_0;
  assign T131 = T161 && T132;
  assign T132 = centeroidMemPortHadValidRequest_0 || T133;
  assign T133 = T159 && centeroidMemPort_req_valid;
  assign centeroidMemPort_req_valid = T134;
  assign T134 = T146 && T135;
  assign T135 = T141 || T136;
  assign T136 = T139 && T137;
  assign T137 = T22 == T138;
  assign T138 = {5'h0/* 0*/, 3'h4/* 4*/};
  assign T139 = T140 != 2'h1/* 1*/;
  assign T140 = {1'h0/* 0*/, rThreadEncoder_io_chosen};
  assign T141 = T144 && T142;
  assign T142 = T22 == T143;
  assign T143 = {6'h0/* 0*/, 2'h2/* 2*/};
  assign T144 = T145 != 2'h1/* 1*/;
  assign T145 = {1'h0/* 0*/, rThreadEncoder_io_chosen};
  assign T146 = T157 && T147;
  assign T147 = ! T148;
  assign T148 = centeroidMem_valid_received_0 & T24;
  assign T149 = T154 && T150;
  assign T150 = centeroidMem_valid_received_0 || T151;
  assign T151 = centeroidMemPort_rep_valid && T152;
  assign T152 = centeroidMemPort_rep_tag == 5'h0/* 0*/;
  assign centeroidMemPort_rep_tag = mainOff_centeroidMem_rep_tag;
  assign mainOff_centeroidMem_rep_ready = centeroidMemPort_rep_ready;
  assign centeroidMemPort_rep_ready = 1'h1/* 1*/;
  assign mainOff_centeroidMem_req_valid = centeroidMemPort_req_valid;
  assign mainOff_centeroidMem_req_tag = centeroidMemPort_req_tag;
  assign centeroidMemPort_req_tag = T153;
  assign T153 = {4'h0/* 0*/, rThreadEncoder_io_chosen};
  assign centeroidMemPort_rep_valid = mainOff_centeroidMem_rep_valid;
  assign T154 = ! T155;
  assign T155 = T156 == 5'h0/* 0*/;
  assign T156 = {4'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T157 = T158 != 2'h1/* 1*/;
  assign T158 = {1'h0/* 0*/, rThreadEncoder_io_chosen};
  assign T159 = 5'h0/* 0*/ == T160;
  assign T160 = {4'h0/* 0*/, rThreadEncoder_io_chosen};
  assign T161 = ! T162;
  assign T162 = T163 == 5'h0/* 0*/;
  assign T163 = {4'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T164 = T165 || centeroidMem_valid_received_0;
  assign T165 = centeroidMemPort_rep_valid && T166;
  assign T166 = centeroidMemPort_rep_tag == 5'h0/* 0*/;
  assign T167 = T196 || T168;
  assign T168 = ! distanceFUPortHadValidRequest_0;
  assign T169 = T193 && T170;
  assign T170 = distanceFUPortHadValidRequest_0 || T171;
  assign T171 = T191 && distanceFUPort_req_valid;
  assign distanceFUPort_req_valid = T172;
  assign T172 = T178 && T173;
  assign T173 = T176 && T174;
  assign T174 = T22 == T175;
  assign T175 = {5'h0/* 0*/, 3'h5/* 5*/};
  assign T176 = T177 != 2'h1/* 1*/;
  assign T177 = {1'h0/* 0*/, rThreadEncoder_io_chosen};
  assign T178 = T189 && T179;
  assign T179 = ! T180;
  assign T180 = distanceFU_valid_received_0 & T24;
  assign T181 = T186 && T182;
  assign T182 = distanceFU_valid_received_0 || T183;
  assign T183 = distanceFUPort_rep_valid && T184;
  assign T184 = distanceFUPort_rep_tag == 5'h0/* 0*/;
  assign distanceFUPort_rep_tag = mainOff_distanceFU_rep_tag;
  assign mainOff_distanceFU_rep_ready = distanceFUPort_rep_ready;
  assign distanceFUPort_rep_ready = 1'h1/* 1*/;
  assign mainOff_distanceFU_req_valid = distanceFUPort_req_valid;
  assign mainOff_distanceFU_req_tag = distanceFUPort_req_tag;
  assign distanceFUPort_req_tag = T185;
  assign T185 = {4'h0/* 0*/, rThreadEncoder_io_chosen};
  assign distanceFUPort_rep_valid = mainOff_distanceFU_rep_valid;
  assign T186 = ! T187;
  assign T187 = T188 == 5'h0/* 0*/;
  assign T188 = {4'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T189 = T190 != 2'h1/* 1*/;
  assign T190 = {1'h0/* 0*/, rThreadEncoder_io_chosen};
  assign T191 = 5'h0/* 0*/ == T192;
  assign T192 = {4'h0/* 0*/, rThreadEncoder_io_chosen};
  assign T193 = ! T194;
  assign T194 = T195 == 5'h0/* 0*/;
  assign T195 = {4'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T196 = T197 || distanceFU_valid_received_0;
  assign T197 = distanceFUPort_rep_valid && T198;
  assign T198 = distanceFUPort_rep_tag == 5'h0/* 0*/;
  assign T199 = subStateTh_0 == 1'h1/* 1*/;
  assign T200 = T406 && T201;
  assign T201 = State_0 != 8'hff/* 255*/;
  assign T202 = T214 || T203;
  assign T203 = T207 && T204;
  assign T204 = T205;
  assign T205 = T206[1'h0/* 0*/:1'h0/* 0*/];
  assign T206 = 1'h1/* 1*/ << vThreadEncoder_io_chosen;
  assign T207 = T212 && T208;
  assign T208 = T210 == T209;
  assign T209 = {4'h0/* 0*/, 4'hd/* 13*/};
  assign T210 = State_0 & T211;
  assign T211 = {4'h8/* 8*/{T204}};
  assign T212 = T213 != 2'h1/* 1*/;
  assign T213 = {1'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T214 = T267 || T215;
  assign T215 = T216 && T204;
  assign T216 = T224 && T217;
  assign T217 = ! T218;
  assign T218 = T219 == 32'ha/* 10*/;
  assign T219 = centeroidIndex_0 & T220;
  assign T220 = {6'h20/* 32*/{T204}};
  assign T221 = T229 || T222;
  assign T222 = T223 && T204;
  assign T223 = T224 && T218;
  assign T224 = T227 && T225;
  assign T225 = T210 == T226;
  assign T226 = {4'h0/* 0*/, 4'hc/* 12*/};
  assign T227 = T228 != 2'h1/* 1*/;
  assign T228 = {1'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T229 = T231 || T230;
  assign T230 = T224 && T204;
  assign T231 = T238 || T232;
  assign T232 = T233 && T204;
  assign T233 = T236 && T234;
  assign T234 = T210 == T235;
  assign T235 = {4'h0/* 0*/, 4'hb/* 11*/};
  assign T236 = T237 != 2'h1/* 1*/;
  assign T237 = {1'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T238 = T245 || T239;
  assign T239 = T240 && T204;
  assign T240 = T243 && T241;
  assign T241 = T210 == T242;
  assign T242 = {5'h0/* 0*/, 3'h5/* 5*/};
  assign T243 = T244 != 2'h1/* 1*/;
  assign T244 = {1'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T245 = T252 || T246;
  assign T246 = T247 && T204;
  assign T247 = T250 && T248;
  assign T248 = T210 == T249;
  assign T249 = {6'h0/* 0*/, 2'h3/* 3*/};
  assign T250 = T251 != 2'h1/* 1*/;
  assign T251 = {1'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T252 = T253 && T204;
  assign T253 = T256 && T254;
  assign T254 = T210 == T255;
  assign T255 = {6'h0/* 0*/, 2'h2/* 2*/};
  assign T256 = T257 != 2'h1/* 1*/;
  assign T257 = {1'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T258 = T222 ? 32'h0/* 0*/ : T259;
  assign T259 = T230 ? T266 : T260;
  assign T260 = T232 ? 32'h0/* 0*/ : T261;
  assign T261 = T239 ? T265 : T262;
  assign T262 = T246 ? 32'h0/* 0*/ : T263;
  assign T263 = T252 ? T264 : centeroidIndex_0;
  assign T264 = T219 + 32'h1/* 1*/;
  assign T265 = T219 + 32'h1/* 1*/;
  assign T266 = T219 + 32'h1/* 1*/;
  assign T267 = T268 || T222;
  assign T268 = T269 || T232;
  assign T269 = T276 || T270;
  assign T270 = T271 && T204;
  assign T271 = T274 && T272;
  assign T272 = T210 == T273;
  assign T273 = {4'h0/* 0*/, 4'ha/* 10*/};
  assign T274 = T275 != 2'h1/* 1*/;
  assign T275 = {1'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T276 = T283 || T277;
  assign T277 = T278 && T204;
  assign T278 = T281 && T279;
  assign T279 = T210 == T280;
  assign T280 = {4'h0/* 0*/, 4'h9/* 9*/};
  assign T281 = T282 != 2'h1/* 1*/;
  assign T282 = {1'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T283 = T290 || T284;
  assign T284 = T285 && T204;
  assign T285 = T288 && T286;
  assign T286 = T210 == T287;
  assign T287 = {4'h0/* 0*/, 4'h8/* 8*/};
  assign T288 = T289 != 2'h1/* 1*/;
  assign T289 = {1'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T290 = T297 || T291;
  assign T291 = T292 && T204;
  assign T292 = T295 && T293;
  assign T293 = T210 == T294;
  assign T294 = {5'h0/* 0*/, 3'h7/* 7*/};
  assign T295 = T296 != 2'h1/* 1*/;
  assign T296 = {1'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T297 = T304 || T298;
  assign T298 = T299 && T204;
  assign T299 = T302 && T300;
  assign T300 = T210 == T301;
  assign T301 = {5'h0/* 0*/, 3'h6/* 6*/};
  assign T302 = T303 != 2'h1/* 1*/;
  assign T303 = {1'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T304 = T309 || T305;
  assign T305 = T306 && T204;
  assign T306 = T240 && T307;
  assign T307 = ! T308;
  assign T308 = T219 == 32'ha/* 10*/;
  assign T309 = T312 || T310;
  assign T310 = T311 && T204;
  assign T311 = T240 && T308;
  assign T312 = T319 || T313;
  assign T313 = T314 && T204;
  assign T314 = T317 && T315;
  assign T315 = T210 == T316;
  assign T316 = {5'h0/* 0*/, 3'h4/* 4*/};
  assign T317 = T318 != 2'h1/* 1*/;
  assign T318 = {1'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T319 = T320 || T246;
  assign T320 = T321 || T252;
  assign T321 = T343 || T322;
  assign T322 = T323 && T204;
  assign T323 = T338 && T324;
  assign T324 = inputReg_0_pointsFinished & T204;
  assign T325 = T332 && T326;
  assign T326 = T327;
  assign T327 = T328[1'h0/* 0*/:1'h0/* 0*/];
  assign T328 = 1'h1/* 1*/ << sThreadEncoder_io_chosen;
  assign T329 = T331 && T330;
  assign T330 = State_0 == 8'h0/* 0*/;
  assign T331 = subStateTh_0 == 1'h0/* 0*/;
  assign T332 = T335 && io_in_valid;
  assign io_in_ready = T333;
  assign T333 = T334 != 2'h1/* 1*/;
  assign T334 = {1'h0/* 0*/, sThreadEncoder_io_chosen};
  assign T335 = T336 != 2'h1/* 1*/;
  assign T336 = {1'h0/* 0*/, sThreadEncoder_io_chosen};
  assign T337 = T325 ? io_in_bits_pointsFinished : inputReg_0_pointsFinished;
  assign T338 = T341 && T339;
  assign T339 = T210 == T340;
  assign T340 = {7'h0/* 0*/, 1'h1/* 1*/};
  assign T341 = T342 != 2'h1/* 1*/;
  assign T342 = {1'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T343 = T356 || T344;
  assign T344 = T345 && T204;
  assign T345 = T338 && T346;
  assign T346 = T347 == 32'h1/* 1*/;
  assign T347 = mode_0 & T348;
  assign T348 = {6'h20/* 32*/{T204}};
  assign T349 = T350 || T322;
  assign T350 = T351 && T204;
  assign T351 = T338 && T352;
  assign T352 = inputReg_0_centeroidsFinished & T204;
  assign T353 = T325 ? io_in_bits_centeroidsFinished : inputReg_0_centeroidsFinished;
  assign T354 = T322 ? 32'h0/* 0*/ : T355;
  assign T355 = T350 ? 32'h1/* 1*/ : mode_0;
  assign T356 = T357 || T350;
  assign T357 = T361 || T358;
  assign T358 = T359 && T204;
  assign T359 = T338 && T360;
  assign T360 = T347 == 32'h0/* 0*/;
  assign T361 = T325 || T362;
  assign T362 = T363 && T24;
  assign T363 = T364 && io_out_ready;
  assign T364 = T366 && T365;
  assign T365 = T22 == 8'hff/* 255*/;
  assign T366 = T367 != 2'h1/* 1*/;
  assign T367 = {1'h0/* 0*/, rThreadEncoder_io_chosen};
  assign T368 = T203 ? 8'hff/* 255*/ : T369;
  assign T369 = T215 ? T405 : T370;
  assign T370 = T222 ? 8'h0/* 0*/ : T371;
  assign T371 = T232 ? T404 : T372;
  assign T372 = T270 ? 8'h0/* 0*/ : T373;
  assign T373 = T277 ? T403 : T374;
  assign T374 = T284 ? T402 : T375;
  assign T375 = T291 ? T401 : T376;
  assign T376 = T298 ? T400 : T377;
  assign T377 = T305 ? T399 : T378;
  assign T378 = T310 ? T398 : T379;
  assign T379 = T313 ? T397 : T380;
  assign T380 = T246 ? T396 : T381;
  assign T381 = T252 ? 8'h0/* 0*/ : T382;
  assign T382 = T322 ? T395 : T383;
  assign T383 = T344 ? T394 : T384;
  assign T384 = T350 ? 8'h0/* 0*/ : T385;
  assign T385 = T358 ? T393 : T386;
  assign T386 = T362 ? T389 : T387;
  assign T387 = T325 ? T388 : State_0;
  assign T388 = {7'h0/* 0*/, 1'h1/* 1*/};
  assign T389 = EmitReturnState_0 & T390;
  assign T390 = {4'h8/* 8*/{T24}};
  assign T391 = T203 ? T392 : EmitReturnState_0;
  assign T392 = {4'h0/* 0*/, 4'hc/* 12*/};
  assign T393 = {6'h0/* 0*/, 2'h2/* 2*/};
  assign T394 = {6'h0/* 0*/, 2'h3/* 3*/};
  assign T395 = {4'h0/* 0*/, 4'hb/* 11*/};
  assign T396 = {5'h0/* 0*/, 3'h4/* 4*/};
  assign T397 = {5'h0/* 0*/, 3'h5/* 5*/};
  assign T398 = {5'h0/* 0*/, 3'h6/* 6*/};
  assign T399 = {5'h0/* 0*/, 3'h4/* 4*/};
  assign T400 = {5'h0/* 0*/, 3'h7/* 7*/};
  assign T401 = {4'h0/* 0*/, 4'h8/* 8*/};
  assign T402 = {4'h0/* 0*/, 4'h9/* 9*/};
  assign T403 = {4'h0/* 0*/, 4'ha/* 10*/};
  assign T404 = {4'h0/* 0*/, 4'hc/* 12*/};
  assign T405 = {4'h0/* 0*/, 4'hd/* 13*/};
  assign T406 = T408 && T407;
  assign T407 = State_0 != 8'h0/* 0*/;
  assign T408 = AllOffloadsReady && T409;
  assign T409 = 1'h0/* 0*/ == rThreadEncoder_io_chosen;
  assign AllOffloadsReady = T410;
  assign T410 = T422 && T411;
  assign T411 = T418 || T412;
  assign T412 = T414 && T413;
  assign T413 = ! addPort_req_valid;
  assign T414 = ! addPortHadReadyRequest;
  assign T415 = T417 && T416;
  assign T416 = addPortHadReadyRequest || addPort_req_valid;
  assign T417 = ! AllOffloadsReady;
  assign T418 = addPort_req_ready || add_ready_received;
  assign T419 = T421 && T420;
  assign T420 = add_ready_received || addPort_req_ready;
  assign addPort_req_ready = mainOff_add_req_ready;
  assign T421 = ! AllOffloadsReady;
  assign T422 = T434 && T423;
  assign T423 = T430 || T424;
  assign T424 = T426 && T425;
  assign T425 = ! partialAccumulatorMemPort_req_valid;
  assign T426 = ! partialAccumulatorMemPortHadReadyRequest;
  assign T427 = T429 && T428;
  assign T428 = partialAccumulatorMemPortHadReadyRequest || partialAccumulatorMemPort_req_valid;
  assign T429 = ! AllOffloadsReady;
  assign T430 = partialAccumulatorMemPort_req_ready || partialAccumulatorMem_ready_received;
  assign T431 = T433 && T432;
  assign T432 = partialAccumulatorMem_ready_received || partialAccumulatorMemPort_req_ready;
  assign partialAccumulatorMemPort_req_ready = mainOff_partialAccumulatorMem_req_ready;
  assign T433 = ! AllOffloadsReady;
  assign T434 = T446 && T435;
  assign T435 = T442 || T436;
  assign T436 = T438 && T437;
  assign T437 = ! centeroidMemPort_req_valid;
  assign T438 = ! centeroidMemPortHadReadyRequest;
  assign T439 = T441 && T440;
  assign T440 = centeroidMemPortHadReadyRequest || centeroidMemPort_req_valid;
  assign T441 = ! AllOffloadsReady;
  assign T442 = centeroidMemPort_req_ready || centeroidMem_ready_received;
  assign T443 = T445 && T444;
  assign T444 = centeroidMem_ready_received || centeroidMemPort_req_ready;
  assign centeroidMemPort_req_ready = mainOff_centeroidMem_req_ready;
  assign T445 = ! AllOffloadsReady;
  assign T446 = T453 || T447;
  assign T447 = T449 && T448;
  assign T448 = ! distanceFUPort_req_valid;
  assign T449 = ! distanceFUPortHadReadyRequest;
  assign T450 = T452 && T451;
  assign T451 = distanceFUPortHadReadyRequest || distanceFUPort_req_valid;
  assign T452 = ! AllOffloadsReady;
  assign T453 = distanceFUPort_req_ready || distanceFU_ready_received;
  assign T454 = T456 && T455;
  assign T455 = distanceFU_ready_received || distanceFUPort_req_ready;
  assign distanceFUPort_req_ready = mainOff_distanceFU_req_ready;
  assign T456 = ! AllOffloadsReady;
  assign T457 = T458 != 2'h1/* 1*/;
  assign T458 = {1'h0/* 0*/, rThreadEncoder_io_chosen};
  assign io_out_bits_centeroidIndex = T459;
  assign T459 = outputReg_0_centeroidIndex & T460;
  assign T460 = {5'h10/* 16*/{T24}};
  assign T461 = T230 ? T219 : T462;
  assign T462 = {16'h0/* 0*/, outputReg_0_centeroidIndex};
  assign io_pcOut_bits_portId = R463;
  assign T464 = T3 || T2;
  assign T465 = T466 ? io_pcIn_bits_portId : R463;
  assign T466 = T3 || T2;
  assign io_pcOut_bits_pcValue = R467;
  assign T468 = T3 || T2;
  assign T469 = T2 ? io_pcIn_bits_pcValue : T470;
  assign T470 = T3 ? T471 : R467;
  assign T471 = T629 ? T579 : T472;
  assign T472 = T575 ? R564 : T473;
  assign T473 = T560 ? R549 : T474;
  assign T474 = T545 ? R534 : T475;
  assign T475 = T530 ? R519 : T476;
  assign T476 = T515 ? R504 : T477;
  assign T477 = T500 ? R478 : 20'h0/* 0*/;
  assign T479 = T488 || T480;
  assign T480 = T496 && T481;
  assign T481 = T494 && T482;
  assign T482 = ! R483;
  assign T484 = T488 || T485;
  assign T485 = T487 && T486;
  assign T486 = io_pcIn_bits_pcType == T0;
  assign T0 = {1'h0/* 0*/, 3'h4/* 4*/};
  assign T487 = io_pcIn_valid && io_pcIn_bits_request;
  assign T488 = T490 && T489;
  assign T489 = io_pcIn_bits_pcType == T1;
  assign T1 = {1'h0/* 0*/, 3'h3/* 3*/};
  assign T490 = io_pcIn_valid && io_pcIn_bits_request;
  assign T491 = T485 ? T493 : T492;
  assign T492 = T488 ? 1'h0/* 0*/ : R483;
  assign T493 = ! R483;
  assign T494 = io_in_valid && T495;
  assign T495 = ! io_in_ready;
  assign T496 = ! T488;
  assign T497 = T480 ? T499 : T498;
  assign T498 = T488 ? 20'h0/* 0*/ : R478;
  assign T499 = R478 + 20'h1/* 1*/;
  assign T500 = T502 && T501;
  assign T501 = io_pcIn_bits_pcType == T2;
  assign T2 = {1'h0/* 0*/, 3'h1/* 1*/};
  assign T502 = T503 == 20'h1/* 1*/;
  assign T503 = {12'h0/* 0*/, io_pcIn_bits_portId};
  assign T505 = T488 || T506;
  assign T506 = T511 && T507;
  assign T507 = T509 && T508;
  assign T508 = ! R483;
  assign T509 = io_out_valid && T510;
  assign T510 = ! io_out_ready;
  assign T511 = ! T488;
  assign T512 = T506 ? T514 : T513;
  assign T513 = T488 ? 20'h0/* 0*/ : R504;
  assign T514 = R504 + 20'h1/* 1*/;
  assign T515 = T517 && T516;
  assign T516 = io_pcIn_bits_pcType == T2;
  assign T517 = T518 == 20'h2/* 2*/;
  assign T518 = {12'h0/* 0*/, io_pcIn_bits_portId};
  assign T520 = T488 || T521;
  assign T521 = T526 && T522;
  assign T522 = T524 && T523;
  assign T523 = ! R483;
  assign T524 = mainOff_distanceFU_req_valid && T525;
  assign T525 = ! mainOff_distanceFU_req_ready;
  assign T526 = ! T488;
  assign T527 = T521 ? T529 : T528;
  assign T528 = T488 ? 20'h0/* 0*/ : R519;
  assign T529 = R519 + 20'h1/* 1*/;
  assign T530 = T532 && T531;
  assign T531 = io_pcIn_bits_pcType == T2;
  assign T532 = T533 == 20'h3/* 3*/;
  assign T533 = {12'h0/* 0*/, io_pcIn_bits_portId};
  assign T535 = T488 || T536;
  assign T536 = T541 && T537;
  assign T537 = T539 && T538;
  assign T538 = ! R483;
  assign T539 = mainOff_centeroidMem_req_valid && T540;
  assign T540 = ! mainOff_centeroidMem_req_ready;
  assign T541 = ! T488;
  assign T542 = T536 ? T544 : T543;
  assign T543 = T488 ? 20'h0/* 0*/ : R534;
  assign T544 = R534 + 20'h1/* 1*/;
  assign T545 = T547 && T546;
  assign T546 = io_pcIn_bits_pcType == T2;
  assign T547 = T548 == 20'h4/* 4*/;
  assign T548 = {12'h0/* 0*/, io_pcIn_bits_portId};
  assign T550 = T488 || T551;
  assign T551 = T556 && T552;
  assign T552 = T554 && T553;
  assign T553 = ! R483;
  assign T554 = mainOff_partialAccumulatorMem_req_valid && T555;
  assign T555 = ! mainOff_partialAccumulatorMem_req_ready;
  assign T556 = ! T488;
  assign T557 = T551 ? T559 : T558;
  assign T558 = T488 ? 20'h0/* 0*/ : R549;
  assign T559 = R549 + 20'h1/* 1*/;
  assign T560 = T562 && T561;
  assign T561 = io_pcIn_bits_pcType == T2;
  assign T562 = T563 == 20'h5/* 5*/;
  assign T563 = {12'h0/* 0*/, io_pcIn_bits_portId};
  assign T565 = T488 || T566;
  assign T566 = T571 && T567;
  assign T567 = T569 && T568;
  assign T568 = ! R483;
  assign T569 = mainOff_add_req_valid && T570;
  assign T570 = ! mainOff_add_req_ready;
  assign T571 = ! T488;
  assign T572 = T566 ? T574 : T573;
  assign T573 = T488 ? 20'h0/* 0*/ : R564;
  assign T574 = R564 + 20'h1/* 1*/;
  assign T575 = T577 && T576;
  assign T576 = io_pcIn_bits_pcType == T2;
  assign T577 = T578 == 20'h6/* 6*/;
  assign T578 = {12'h0/* 0*/, io_pcIn_bits_portId};
  assign T579 = T628 ? T591 : R580;
  assign T581 = T488 || T582;
  assign T582 = T587 && T583;
  assign T583 = T585 && T584;
  assign T584 = ! R483;
  assign T585 = mainOff_add_req_ready && T586;
  assign T586 = ! mainOff_add_req_valid;
  assign T587 = ! T488;
  assign T588 = T582 ? T590 : T589;
  assign T589 = T488 ? 20'h0/* 0*/ : R580;
  assign T590 = R580 + 20'h1/* 1*/;
  assign T591 = T627 ? T603 : R592;
  assign T593 = T488 || T594;
  assign T594 = T599 && T595;
  assign T595 = T597 && T596;
  assign T596 = ! R483;
  assign T597 = mainOff_partialAccumulatorMem_req_ready && T598;
  assign T598 = ! mainOff_partialAccumulatorMem_req_valid;
  assign T599 = ! T488;
  assign T600 = T594 ? T602 : T601;
  assign T601 = T488 ? 20'h0/* 0*/ : R592;
  assign T602 = R592 + 20'h1/* 1*/;
  assign T603 = T626 ? R615 : R604;
  assign T605 = T488 || T606;
  assign T606 = T611 && T607;
  assign T607 = T609 && T608;
  assign T608 = ! R483;
  assign T609 = mainOff_centeroidMem_req_ready && T610;
  assign T610 = ! mainOff_centeroidMem_req_valid;
  assign T611 = ! T488;
  assign T612 = T606 ? T614 : T613;
  assign T613 = T488 ? 20'h0/* 0*/ : R604;
  assign T614 = R604 + 20'h1/* 1*/;
  assign T616 = T488 || T617;
  assign T617 = T622 && T618;
  assign T618 = T620 && T619;
  assign T619 = ! R483;
  assign T620 = mainOff_distanceFU_req_ready && T621;
  assign T621 = ! mainOff_distanceFU_req_valid;
  assign T622 = ! T488;
  assign T623 = T617 ? T625 : T624;
  assign T624 = T488 ? 20'h0/* 0*/ : R615;
  assign T625 = R615 + 20'h1/* 1*/;
  assign T626 = R615 > R604;
  assign T627 = T603 > R592;
  assign T628 = T591 > R580;
  assign T629 = io_pcIn_bits_pcType == T3;
  assign T3 = {1'h0/* 0*/, 3'h2/* 2*/};
  assign io_out_tag = T630;
  assign T630 = inputTag_0 & T631;
  assign T631 = {3'h5/* 5*/{T24}};
  assign T632 = T325 ? io_in_tag : inputTag_0;
  RREncode_3 rThreadEncoder(
       .io_valid_0( T27 ),
       .io_chosen( rThreadEncoder_io_chosen ),
       .io_ready(  ));
  RREncode_4 vThreadEncoder(
       .io_valid_0( T31 ),
       .io_chosen( vThreadEncoder_io_chosen ),
       .io_ready(  ));
  RREncode_5 sThreadEncoder(
       .io_valid_0( T329 ),
       .io_chosen( sThreadEncoder_io_chosen ),
       .io_ready(  ));

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
    subStateTh_0 <= reset ? 1'h0/* 0*/ : T28;
    addPortHadValidRequest_0 <= reset ? 1'h0/* 0*/ : T35;
    add_valid_received_0 <= reset ? 1'h0/* 0*/ : T59;
    partialAccumulatorMemPortHadValidRequest_0 <= reset ? 1'h0/* 0*/ : T80;
    partialAccumulatorMem_valid_received_0 <= reset ? 1'h0/* 0*/ : T110;
    centeroidMemPortHadValidRequest_0 <= reset ? 1'h0/* 0*/ : T131;
    centeroidMem_valid_received_0 <= reset ? 1'h0/* 0*/ : T149;
    distanceFUPortHadValidRequest_0 <= reset ? 1'h0/* 0*/ : T169;
    distanceFU_valid_received_0 <= reset ? 1'h0/* 0*/ : T181;
    if(reset) begin
      State_0 <= 8'h0/* 0*/;
    end else if(T202) begin
      State_0 <= T368;
    end
    if(reset) begin
      centeroidIndex_0 <= 32'h0/* 0*/;
    end else if(T221) begin
      centeroidIndex_0 <= T258;
    end
    if(T325) begin
      inputReg_0_pointsFinished <= T337;
    end
    if(reset) begin
      mode_0 <= 32'h0/* 0*/;
    end else if(T349) begin
      mode_0 <= T354;
    end
    if(T325) begin
      inputReg_0_centeroidsFinished <= T353;
    end
    if(reset) begin
      EmitReturnState_0 <= 8'h0/* 0*/;
    end else if(T203) begin
      EmitReturnState_0 <= T391;
    end
    addPortHadReadyRequest <= reset ? 1'h0/* 0*/ : T415;
    add_ready_received <= reset ? 1'h0/* 0*/ : T419;
    partialAccumulatorMemPortHadReadyRequest <= reset ? 1'h0/* 0*/ : T427;
    partialAccumulatorMem_ready_received <= reset ? 1'h0/* 0*/ : T431;
    centeroidMemPortHadReadyRequest <= reset ? 1'h0/* 0*/ : T439;
    centeroidMem_ready_received <= reset ? 1'h0/* 0*/ : T443;
    distanceFUPortHadReadyRequest <= reset ? 1'h0/* 0*/ : T450;
    distanceFU_ready_received <= reset ? 1'h0/* 0*/ : T454;
    if(T230) begin
      outputReg_0_centeroidIndex <= T461;
    end
    if(reset) begin
      R463 <= 8'h0/* 0*/;
    end else if(T464) begin
      R463 <= T465;
    end
    if(reset) begin
      R467 <= 20'h0/* 0*/;
    end else if(T468) begin
      R467 <= T469;
    end
    if(reset) begin
      R478 <= 20'h0/* 0*/;
    end else if(T479) begin
      R478 <= T497;
    end
    if(reset) begin
      R483 <= 1'h0/* 0*/;
    end else if(T484) begin
      R483 <= T491;
    end
    if(reset) begin
      R504 <= 20'h0/* 0*/;
    end else if(T505) begin
      R504 <= T512;
    end
    if(reset) begin
      R519 <= 20'h0/* 0*/;
    end else if(T520) begin
      R519 <= T527;
    end
    if(reset) begin
      R534 <= 20'h0/* 0*/;
    end else if(T535) begin
      R534 <= T542;
    end
    if(reset) begin
      R549 <= 20'h0/* 0*/;
    end else if(T550) begin
      R549 <= T557;
    end
    if(reset) begin
      R564 <= 20'h0/* 0*/;
    end else if(T565) begin
      R564 <= T572;
    end
    if(reset) begin
      R580 <= 20'h0/* 0*/;
    end else if(T581) begin
      R580 <= T588;
    end
    if(reset) begin
      R592 <= 20'h0/* 0*/;
    end else if(T593) begin
      R592 <= T600;
    end
    if(reset) begin
      R604 <= 20'h0/* 0*/;
    end else if(T605) begin
      R604 <= T612;
    end
    if(reset) begin
      R615 <= 20'h0/* 0*/;
    end else if(T616) begin
      R615 <= T623;
    end
    if(T325) begin
      inputTag_0 <= T632;
    end
  end
endmodule

module RREncode_6(
    input  io_valid_0,
    output io_chosen,
    input  io_ready);

  wire choose;

  assign io_chosen = choose;
  assign choose = io_valid_0 ? 1'h0/* 0*/ : 1'h1/* 1*/;
endmodule

module RREncode_7(
    input  io_valid_0,
    output io_chosen,
    input  io_ready);

  wire choose;

  assign io_chosen = choose;
  assign choose = io_valid_0 ? 1'h0/* 0*/ : 1'h1/* 1*/;
endmodule

module RREncode_8(
    input  io_valid_0,
    output io_chosen,
    input  io_ready);

  wire choose;

  assign io_chosen = choose;
  assign choose = io_valid_0 ? 1'h0/* 0*/ : 1'h1/* 1*/;
endmodule

module distanceFU(input clk, input reset,
    output io_in_ready,
    input  io_in_valid,
    input [63:0] io_in_bits_in1_x,
    input [63:0] io_in_bits_in1_y,
    input [63:0] io_in_bits_in1_z,
    input [63:0] io_in_bits_in2_x,
    input [63:0] io_in_bits_in2_y,
    input [63:0] io_in_bits_in2_z,
    input [4:0] io_in_tag,
    input  io_out_ready,
    output io_out_valid,
    output[63:0] io_out_bits_out,
    output[4:0] io_out_tag,
    input  io_pcIn_valid,
    input  io_pcIn_bits_request,
    input [15:0] io_pcIn_bits_moduleId,
    input [7:0] io_pcIn_bits_portId,
    input [19:0] io_pcIn_bits_pcValue,
    input [3:0] io_pcIn_bits_pcType,
    output io_pcOut_valid,
    output io_pcOut_bits_request,
    output[15:0] io_pcOut_bits_moduleId,
    output[7:0] io_pcOut_bits_portId,
    output[19:0] io_pcOut_bits_pcValue,
    output[3:0] io_pcOut_bits_pcType);

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
  wire[4:0] T20;
  wire[4:0] T21;
  wire T22;
  wire T23;
  wire[1:0] T24;
  wire rThreadEncoder_io_chosen;
  wire T25;
  reg[0:0] subStateTh_0;
  wire T26;
  wire T27;
  wire T28;
  wire vThreadEncoder_io_chosen;
  wire T29;
  wire AllOffloadsValid_0;
  wire T30;
  wire T31;
  wire T32;
  reg[7:0] State_0;
  wire T33;
  wire T34;
  wire T35;
  wire T36;
  wire[1:0] T37;
  wire T38;
  wire T39;
  wire[7:0] T40;
  wire[7:0] T41;
  wire[7:0] T42;
  wire T43;
  wire[1:0] T44;
  wire T45;
  wire T46;
  wire T47;
  wire T48;
  wire[7:0] T49;
  wire T50;
  wire[1:0] T51;
  wire T52;
  wire T53;
  wire T54;
  wire T55;
  wire[7:0] T56;
  wire T57;
  wire[1:0] T58;
  wire T59;
  wire T60;
  wire T61;
  wire T62;
  wire[7:0] T63;
  wire T64;
  wire[1:0] T65;
  wire T66;
  wire T67;
  wire T68;
  wire T69;
  wire[7:0] T70;
  wire T71;
  wire[1:0] T72;
  wire T73;
  wire T74;
  wire T75;
  wire T76;
  wire[7:0] T77;
  wire T78;
  wire[1:0] T79;
  wire T80;
  wire T81;
  wire T82;
  wire T83;
  wire[7:0] T84;
  wire T85;
  wire[1:0] T86;
  wire T87;
  wire T88;
  wire T89;
  wire T90;
  wire[7:0] T91;
  wire T92;
  wire[1:0] T93;
  wire T94;
  wire T95;
  wire T96;
  wire T97;
  wire[7:0] T98;
  wire T99;
  wire[1:0] T100;
  wire T101;
  wire T102;
  wire T103;
  wire T104;
  wire[7:0] T105;
  wire T106;
  wire[1:0] T107;
  wire T108;
  wire T109;
  wire T110;
  wire T111;
  wire[7:0] T112;
  wire T113;
  wire[1:0] T114;
  wire T115;
  wire T116;
  wire T117;
  wire T118;
  wire[7:0] T119;
  wire T120;
  wire[1:0] T121;
  wire T122;
  wire T123;
  wire T124;
  wire T125;
  wire[7:0] T126;
  wire T127;
  wire[1:0] T128;
  wire T129;
  wire T130;
  wire T131;
  wire T132;
  wire[7:0] T133;
  wire T134;
  wire[1:0] T135;
  wire T136;
  wire T137;
  wire T138;
  wire T139;
  wire[7:0] T140;
  wire T141;
  wire[1:0] T142;
  wire T143;
  wire T144;
  wire T145;
  wire T146;
  wire[7:0] T147;
  wire T148;
  wire[1:0] T149;
  wire T150;
  wire T151;
  wire T152;
  wire T153;
  wire[7:0] T154;
  wire T155;
  wire[1:0] T156;
  wire T157;
  wire T158;
  wire T159;
  wire T160;
  wire[7:0] T161;
  wire T162;
  wire[1:0] T163;
  wire T164;
  wire T165;
  wire T166;
  wire T167;
  wire[7:0] T168;
  wire T169;
  wire[1:0] T170;
  wire T171;
  wire T172;
  wire T173;
  wire T174;
  wire[7:0] T175;
  wire T176;
  wire[1:0] T177;
  wire T178;
  wire T179;
  wire T180;
  wire T181;
  wire[7:0] T182;
  wire T183;
  wire[1:0] T184;
  wire T185;
  wire T186;
  wire T187;
  wire T188;
  wire T189;
  wire[7:0] T190;
  wire[7:0] T191;
  wire T192;
  wire[1:0] T193;
  wire T194;
  wire T195;
  wire T196;
  wire[1:0] T197;
  wire sThreadEncoder_io_chosen;
  wire T198;
  wire T199;
  wire T200;
  wire T201;
  wire T202;
  wire[1:0] T203;
  wire[7:0] T204;
  wire[7:0] T205;
  wire[7:0] T206;
  wire[7:0] T207;
  wire[7:0] T208;
  wire[7:0] T209;
  wire[7:0] T210;
  wire[7:0] T211;
  wire[7:0] T212;
  wire[7:0] T213;
  wire[7:0] T214;
  wire[7:0] T215;
  wire[7:0] T216;
  wire[7:0] T217;
  wire[7:0] T218;
  wire[7:0] T219;
  wire[7:0] T220;
  wire[7:0] T221;
  wire[7:0] T222;
  wire[7:0] T223;
  wire[7:0] T224;
  wire[7:0] T225;
  wire[7:0] T226;
  wire[7:0] T227;
  wire[7:0] T228;
  wire[7:0] T229;
  reg[7:0] EmitReturnState_0;
  wire[7:0] T230;
  wire[7:0] T231;
  wire[7:0] T232;
  wire[7:0] T233;
  wire[7:0] T234;
  wire[7:0] T235;
  wire[7:0] T236;
  wire[7:0] T237;
  wire[7:0] T238;
  wire[7:0] T239;
  wire[7:0] T240;
  wire[7:0] T241;
  wire[7:0] T242;
  wire[7:0] T243;
  wire[7:0] T244;
  wire[7:0] T245;
  wire[7:0] T246;
  wire[7:0] T247;
  wire[7:0] T248;
  wire[7:0] T249;
  wire[7:0] T250;
  wire T251;
  wire T252;
  wire T253;
  wire T254;
  wire AllOffloadsReady;
  reg[4:0] inputTag_0;
  wire[4:0] T255;
  wire T256;
  wire T257;
  wire T258;
  wire[1:0] T259;
  wire T260;
  wire[1:0] T261;
  reg[7:0] R262;
  wire T263;
  wire[7:0] T264;
  wire T265;
  reg[19:0] R266;
  wire T267;
  wire[19:0] T268;
  wire[19:0] T269;
  wire[19:0] T270;
  wire[19:0] T271;
  wire[19:0] T272;
  reg[19:0] R273;
  wire T274;
  wire T275;
  wire T276;
  wire T277;
  reg[0:0] R278;
  wire T279;
  wire T280;
  wire T281;
  wire[3:0] T0;
  wire T282;
  wire T283;
  wire T284;
  wire[3:0] T1;
  wire T285;
  wire T286;
  wire T287;
  wire T288;
  wire T289;
  wire T290;
  wire T291;
  wire[19:0] T292;
  wire[19:0] T293;
  wire[19:0] T294;
  wire T295;
  wire T296;
  wire[3:0] T2;
  wire T297;
  wire[19:0] T298;
  reg[19:0] R299;
  wire T300;
  wire T301;
  wire T302;
  wire T303;
  wire T304;
  wire T305;
  wire T306;
  wire[19:0] T307;
  wire[19:0] T308;
  wire[19:0] T309;
  wire T310;
  wire T311;
  wire T312;
  wire[19:0] T313;
  wire[19:0] T314;
  wire T315;
  wire[3:0] T3;

  assign io_pcOut_bits_request = R0;
  assign T1 = T3 || T2;
  assign T2 = ! T3;
  assign T3 = T4 && io_pcIn_bits_request;
  assign T4 = io_pcIn_valid && T5;
  assign T5 = io_pcIn_bits_moduleId == 16'ha/* 10*/;
  assign T6 = T2 ? io_pcIn_bits_request : T7;
  assign T7 = T3 ? 1'h0/* 0*/ : R0;
  assign io_pcOut_bits_moduleId = R8;
  assign T9 = T3 || T2;
  assign T10 = T11 ? io_pcIn_bits_moduleId : R8;
  assign T11 = T3 || T2;
  assign io_pcOut_valid = R12;
  assign T13 = T3 || T2;
  assign T14 = T2 ? io_pcIn_valid : T15;
  assign T15 = T3 ? 1'h1/* 1*/ : R12;
  assign io_pcOut_bits_pcType = R16;
  assign T17 = T3 || T2;
  assign T18 = T19 ? io_pcIn_bits_pcType : R16;
  assign T19 = T3 || T2;
  assign io_out_tag = T20;
  assign T20 = inputTag_0 & T21;
  assign T21 = {3'h5/* 5*/{T22}};
  assign T22 = T23;
  assign T23 = T24[1'h0/* 0*/:1'h0/* 0*/];
  assign T24 = 1'h1/* 1*/ << rThreadEncoder_io_chosen;
  assign T25 = subStateTh_0 == 1'h0/* 0*/;
  assign T26 = T31 ? 1'h1/* 1*/ : T27;
  assign T27 = T28 ? 1'h0/* 0*/ : subStateTh_0;
  assign T28 = 1'h0/* 0*/ == vThreadEncoder_io_chosen;
  assign T29 = T30 && AllOffloadsValid_0;
  assign AllOffloadsValid_0 = 1'h1/* 1*/;
  assign T30 = subStateTh_0 == 1'h1/* 1*/;
  assign T31 = T251 && T32;
  assign T32 = State_0 != 8'hff/* 255*/;
  assign T33 = T45 || T34;
  assign T34 = T38 && T35;
  assign T35 = T36;
  assign T36 = T37[1'h0/* 0*/:1'h0/* 0*/];
  assign T37 = 1'h1/* 1*/ << vThreadEncoder_io_chosen;
  assign T38 = T43 && T39;
  assign T39 = T41 == T40;
  assign T40 = {3'h0/* 0*/, 5'h15/* 21*/};
  assign T41 = State_0 & T42;
  assign T42 = {4'h8/* 8*/{T35}};
  assign T43 = T44 != 2'h1/* 1*/;
  assign T44 = {1'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T45 = T52 || T46;
  assign T46 = T47 && T35;
  assign T47 = T50 && T48;
  assign T48 = T41 == T49;
  assign T49 = {3'h0/* 0*/, 5'h14/* 20*/};
  assign T50 = T51 != 2'h1/* 1*/;
  assign T51 = {1'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T52 = T59 || T53;
  assign T53 = T54 && T35;
  assign T54 = T57 && T55;
  assign T55 = T41 == T56;
  assign T56 = {3'h0/* 0*/, 5'h13/* 19*/};
  assign T57 = T58 != 2'h1/* 1*/;
  assign T58 = {1'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T59 = T66 || T60;
  assign T60 = T61 && T35;
  assign T61 = T64 && T62;
  assign T62 = T41 == T63;
  assign T63 = {3'h0/* 0*/, 5'h12/* 18*/};
  assign T64 = T65 != 2'h1/* 1*/;
  assign T65 = {1'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T66 = T73 || T67;
  assign T67 = T68 && T35;
  assign T68 = T71 && T69;
  assign T69 = T41 == T70;
  assign T70 = {3'h0/* 0*/, 5'h11/* 17*/};
  assign T71 = T72 != 2'h1/* 1*/;
  assign T72 = {1'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T73 = T80 || T74;
  assign T74 = T75 && T35;
  assign T75 = T78 && T76;
  assign T76 = T41 == T77;
  assign T77 = {3'h0/* 0*/, 5'h10/* 16*/};
  assign T78 = T79 != 2'h1/* 1*/;
  assign T79 = {1'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T80 = T87 || T81;
  assign T81 = T82 && T35;
  assign T82 = T85 && T83;
  assign T83 = T41 == T84;
  assign T84 = {4'h0/* 0*/, 4'hf/* 15*/};
  assign T85 = T86 != 2'h1/* 1*/;
  assign T86 = {1'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T87 = T94 || T88;
  assign T88 = T89 && T35;
  assign T89 = T92 && T90;
  assign T90 = T41 == T91;
  assign T91 = {4'h0/* 0*/, 4'he/* 14*/};
  assign T92 = T93 != 2'h1/* 1*/;
  assign T93 = {1'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T94 = T101 || T95;
  assign T95 = T96 && T35;
  assign T96 = T99 && T97;
  assign T97 = T41 == T98;
  assign T98 = {4'h0/* 0*/, 4'hd/* 13*/};
  assign T99 = T100 != 2'h1/* 1*/;
  assign T100 = {1'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T101 = T108 || T102;
  assign T102 = T103 && T35;
  assign T103 = T106 && T104;
  assign T104 = T41 == T105;
  assign T105 = {4'h0/* 0*/, 4'hc/* 12*/};
  assign T106 = T107 != 2'h1/* 1*/;
  assign T107 = {1'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T108 = T115 || T109;
  assign T109 = T110 && T35;
  assign T110 = T113 && T111;
  assign T111 = T41 == T112;
  assign T112 = {4'h0/* 0*/, 4'hb/* 11*/};
  assign T113 = T114 != 2'h1/* 1*/;
  assign T114 = {1'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T115 = T122 || T116;
  assign T116 = T117 && T35;
  assign T117 = T120 && T118;
  assign T118 = T41 == T119;
  assign T119 = {4'h0/* 0*/, 4'ha/* 10*/};
  assign T120 = T121 != 2'h1/* 1*/;
  assign T121 = {1'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T122 = T129 || T123;
  assign T123 = T124 && T35;
  assign T124 = T127 && T125;
  assign T125 = T41 == T126;
  assign T126 = {4'h0/* 0*/, 4'h9/* 9*/};
  assign T127 = T128 != 2'h1/* 1*/;
  assign T128 = {1'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T129 = T136 || T130;
  assign T130 = T131 && T35;
  assign T131 = T134 && T132;
  assign T132 = T41 == T133;
  assign T133 = {4'h0/* 0*/, 4'h8/* 8*/};
  assign T134 = T135 != 2'h1/* 1*/;
  assign T135 = {1'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T136 = T143 || T137;
  assign T137 = T138 && T35;
  assign T138 = T141 && T139;
  assign T139 = T41 == T140;
  assign T140 = {5'h0/* 0*/, 3'h7/* 7*/};
  assign T141 = T142 != 2'h1/* 1*/;
  assign T142 = {1'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T143 = T150 || T144;
  assign T144 = T145 && T35;
  assign T145 = T148 && T146;
  assign T146 = T41 == T147;
  assign T147 = {5'h0/* 0*/, 3'h6/* 6*/};
  assign T148 = T149 != 2'h1/* 1*/;
  assign T149 = {1'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T150 = T157 || T151;
  assign T151 = T152 && T35;
  assign T152 = T155 && T153;
  assign T153 = T41 == T154;
  assign T154 = {5'h0/* 0*/, 3'h5/* 5*/};
  assign T155 = T156 != 2'h1/* 1*/;
  assign T156 = {1'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T157 = T164 || T158;
  assign T158 = T159 && T35;
  assign T159 = T162 && T160;
  assign T160 = T41 == T161;
  assign T161 = {5'h0/* 0*/, 3'h4/* 4*/};
  assign T162 = T163 != 2'h1/* 1*/;
  assign T163 = {1'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T164 = T171 || T165;
  assign T165 = T166 && T35;
  assign T166 = T169 && T167;
  assign T167 = T41 == T168;
  assign T168 = {6'h0/* 0*/, 2'h3/* 3*/};
  assign T169 = T170 != 2'h1/* 1*/;
  assign T170 = {1'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T171 = T178 || T172;
  assign T172 = T173 && T35;
  assign T173 = T176 && T174;
  assign T174 = T41 == T175;
  assign T175 = {6'h0/* 0*/, 2'h2/* 2*/};
  assign T176 = T177 != 2'h1/* 1*/;
  assign T177 = {1'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T178 = T185 || T179;
  assign T179 = T180 && T35;
  assign T180 = T183 && T181;
  assign T181 = T41 == T182;
  assign T182 = {7'h0/* 0*/, 1'h1/* 1*/};
  assign T183 = T184 != 2'h1/* 1*/;
  assign T184 = {1'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T185 = T194 || T186;
  assign T186 = T187 && T22;
  assign T187 = T188 && io_out_ready;
  assign T188 = T192 && T189;
  assign T189 = T190 == 8'hff/* 255*/;
  assign T190 = State_0 & T191;
  assign T191 = {4'h8/* 8*/{T22}};
  assign T192 = T193 != 2'h1/* 1*/;
  assign T193 = {1'h0/* 0*/, rThreadEncoder_io_chosen};
  assign T194 = T201 && T195;
  assign T195 = T196;
  assign T196 = T197[1'h0/* 0*/:1'h0/* 0*/];
  assign T197 = 1'h1/* 1*/ << sThreadEncoder_io_chosen;
  assign T198 = T200 && T199;
  assign T199 = State_0 == 8'h0/* 0*/;
  assign T200 = subStateTh_0 == 1'h0/* 0*/;
  assign T201 = T202 && io_in_valid;
  assign T202 = T203 != 2'h1/* 1*/;
  assign T203 = {1'h0/* 0*/, sThreadEncoder_io_chosen};
  assign T204 = T34 ? 8'hff/* 255*/ : T205;
  assign T205 = T46 ? T250 : T206;
  assign T206 = T53 ? T249 : T207;
  assign T207 = T60 ? T248 : T208;
  assign T208 = T67 ? T247 : T209;
  assign T209 = T74 ? T246 : T210;
  assign T210 = T81 ? T245 : T211;
  assign T211 = T88 ? T244 : T212;
  assign T212 = T95 ? T243 : T213;
  assign T213 = T102 ? T242 : T214;
  assign T214 = T109 ? T241 : T215;
  assign T215 = T116 ? T240 : T216;
  assign T216 = T123 ? T239 : T217;
  assign T217 = T130 ? T238 : T218;
  assign T218 = T137 ? T237 : T219;
  assign T219 = T144 ? T236 : T220;
  assign T220 = T151 ? T235 : T221;
  assign T221 = T158 ? T234 : T222;
  assign T222 = T165 ? T233 : T223;
  assign T223 = T172 ? T232 : T224;
  assign T224 = T179 ? T231 : T225;
  assign T225 = T186 ? T228 : T226;
  assign T226 = T194 ? T227 : State_0;
  assign T227 = {7'h0/* 0*/, 1'h1/* 1*/};
  assign T228 = EmitReturnState_0 & T229;
  assign T229 = {4'h8/* 8*/{T22}};
  assign T230 = T34 ? 8'h0/* 0*/ : EmitReturnState_0;
  assign T231 = {6'h0/* 0*/, 2'h2/* 2*/};
  assign T232 = {6'h0/* 0*/, 2'h3/* 3*/};
  assign T233 = {5'h0/* 0*/, 3'h4/* 4*/};
  assign T234 = {5'h0/* 0*/, 3'h5/* 5*/};
  assign T235 = {5'h0/* 0*/, 3'h6/* 6*/};
  assign T236 = {5'h0/* 0*/, 3'h7/* 7*/};
  assign T237 = {4'h0/* 0*/, 4'h8/* 8*/};
  assign T238 = {4'h0/* 0*/, 4'h9/* 9*/};
  assign T239 = {4'h0/* 0*/, 4'ha/* 10*/};
  assign T240 = {4'h0/* 0*/, 4'hb/* 11*/};
  assign T241 = {4'h0/* 0*/, 4'hc/* 12*/};
  assign T242 = {4'h0/* 0*/, 4'hd/* 13*/};
  assign T243 = {4'h0/* 0*/, 4'he/* 14*/};
  assign T244 = {4'h0/* 0*/, 4'hf/* 15*/};
  assign T245 = {3'h0/* 0*/, 5'h10/* 16*/};
  assign T246 = {3'h0/* 0*/, 5'h11/* 17*/};
  assign T247 = {3'h0/* 0*/, 5'h12/* 18*/};
  assign T248 = {3'h0/* 0*/, 5'h13/* 19*/};
  assign T249 = {3'h0/* 0*/, 5'h14/* 20*/};
  assign T250 = {3'h0/* 0*/, 5'h15/* 21*/};
  assign T251 = T253 && T252;
  assign T252 = State_0 != 8'h0/* 0*/;
  assign T253 = AllOffloadsReady && T254;
  assign T254 = 1'h0/* 0*/ == rThreadEncoder_io_chosen;
  assign AllOffloadsReady = 1'h1/* 1*/;
  assign T255 = T194 ? io_in_tag : inputTag_0;
  assign io_out_valid = T256;
  assign T256 = T258 && T257;
  assign T257 = T190 == 8'hff/* 255*/;
  assign T258 = T259 != 2'h1/* 1*/;
  assign T259 = {1'h0/* 0*/, rThreadEncoder_io_chosen};
  assign io_in_ready = T260;
  assign T260 = T261 != 2'h1/* 1*/;
  assign T261 = {1'h0/* 0*/, sThreadEncoder_io_chosen};
  assign io_pcOut_bits_portId = R262;
  assign T263 = T3 || T2;
  assign T264 = T265 ? io_pcIn_bits_portId : R262;
  assign T265 = T3 || T2;
  assign io_pcOut_bits_pcValue = R266;
  assign T267 = T3 || T2;
  assign T268 = T2 ? io_pcIn_bits_pcValue : T269;
  assign T269 = T3 ? T270 : R266;
  assign T270 = T315 ? T314 : T271;
  assign T271 = T310 ? R299 : T272;
  assign T272 = T295 ? R273 : 20'h0/* 0*/;
  assign T274 = T283 || T275;
  assign T275 = T291 && T276;
  assign T276 = T289 && T277;
  assign T277 = ! R278;
  assign T279 = T283 || T280;
  assign T280 = T282 && T281;
  assign T281 = io_pcIn_bits_pcType == T0;
  assign T0 = {1'h0/* 0*/, 3'h4/* 4*/};
  assign T282 = io_pcIn_valid && io_pcIn_bits_request;
  assign T283 = T285 && T284;
  assign T284 = io_pcIn_bits_pcType == T1;
  assign T1 = {1'h0/* 0*/, 3'h3/* 3*/};
  assign T285 = io_pcIn_valid && io_pcIn_bits_request;
  assign T286 = T280 ? T288 : T287;
  assign T287 = T283 ? 1'h0/* 0*/ : R278;
  assign T288 = ! R278;
  assign T289 = io_in_valid && T290;
  assign T290 = ! io_in_ready;
  assign T291 = ! T283;
  assign T292 = T275 ? T294 : T293;
  assign T293 = T283 ? 20'h0/* 0*/ : R273;
  assign T294 = R273 + 20'h1/* 1*/;
  assign T295 = T297 && T296;
  assign T296 = io_pcIn_bits_pcType == T2;
  assign T2 = {1'h0/* 0*/, 3'h1/* 1*/};
  assign T297 = T298 == 20'h1/* 1*/;
  assign T298 = {12'h0/* 0*/, io_pcIn_bits_portId};
  assign T300 = T283 || T301;
  assign T301 = T306 && T302;
  assign T302 = T304 && T303;
  assign T303 = ! R278;
  assign T304 = io_out_valid && T305;
  assign T305 = ! io_out_ready;
  assign T306 = ! T283;
  assign T307 = T301 ? T309 : T308;
  assign T308 = T283 ? 20'h0/* 0*/ : R299;
  assign T309 = R299 + 20'h1/* 1*/;
  assign T310 = T312 && T311;
  assign T311 = io_pcIn_bits_pcType == T2;
  assign T312 = T313 == 20'h2/* 2*/;
  assign T313 = {12'h0/* 0*/, io_pcIn_bits_portId};
  assign T314 = {15'h0/* 0*/, 5'h14/* 20*/};
  assign T315 = io_pcIn_bits_pcType == T3;
  assign T3 = {1'h0/* 0*/, 3'h2/* 2*/};
  RREncode_6 rThreadEncoder(
       .io_valid_0( T25 ),
       .io_chosen( rThreadEncoder_io_chosen ),
       .io_ready(  ));
  RREncode_7 vThreadEncoder(
       .io_valid_0( T29 ),
       .io_chosen( vThreadEncoder_io_chosen ),
       .io_ready(  ));
  RREncode_8 sThreadEncoder(
       .io_valid_0( T198 ),
       .io_chosen( sThreadEncoder_io_chosen ),
       .io_ready(  ));

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
    subStateTh_0 <= reset ? 1'h0/* 0*/ : T26;
    if(reset) begin
      State_0 <= 8'h0/* 0*/;
    end else if(T33) begin
      State_0 <= T204;
    end
    if(reset) begin
      EmitReturnState_0 <= 8'h0/* 0*/;
    end else if(T34) begin
      EmitReturnState_0 <= T230;
    end
    if(T194) begin
      inputTag_0 <= T255;
    end
    if(reset) begin
      R262 <= 8'h0/* 0*/;
    end else if(T263) begin
      R262 <= T264;
    end
    if(reset) begin
      R266 <= 20'h0/* 0*/;
    end else if(T267) begin
      R266 <= T268;
    end
    if(reset) begin
      R273 <= 20'h0/* 0*/;
    end else if(T274) begin
      R273 <= T292;
    end
    if(reset) begin
      R278 <= 1'h0/* 0*/;
    end else if(T279) begin
      R278 <= T286;
    end
    if(reset) begin
      R299 <= 20'h0/* 0*/;
    end else if(T300) begin
      R299 <= T307;
    end
  end
endmodule

module gOffloadedComponent(input clk, input reset,
    output io_in_ready,
    input  io_in_valid,
    input  io_in_bits_centeroidsFinished,
    input  io_in_bits_pointsFinished,
    input [15:0] io_in_bits_centeroidIndex,
    input [63:0] io_in_bits_point_x,
    input [63:0] io_in_bits_point_y,
    input [63:0] io_in_bits_point_z,
    input [4:0] io_in_tag,
    input  io_out_ready,
    output io_out_valid,
    output io_out_bits_centeroidsFinished,
    output io_out_bits_pointsFinished,
    output[15:0] io_out_bits_centeroidIndex,
    output[63:0] io_out_bits_point_x,
    output[63:0] io_out_bits_point_y,
    output[63:0] io_out_bits_point_z,
    output[4:0] io_out_tag,
    input  io_pcIn_valid,
    input  io_pcIn_bits_request,
    input [15:0] io_pcIn_bits_moduleId,
    input [7:0] io_pcIn_bits_portId,
    input [19:0] io_pcIn_bits_pcValue,
    input [3:0] io_pcIn_bits_pcType,
    output io_pcOut_valid,
    output io_pcOut_bits_request,
    output[15:0] io_pcOut_bits_moduleId,
    output[7:0] io_pcOut_bits_portId,
    output[19:0] io_pcOut_bits_pcValue,
    output[3:0] io_pcOut_bits_pcType,
    input  mainOff_centeroidMem_req_ready,
    output mainOff_centeroidMem_req_valid,
    output mainOff_centeroidMem_req_bits_rw,
    output[15:0] mainOff_centeroidMem_req_bits_addr,
    output[63:0] mainOff_centeroidMem_req_bits_data_x,
    output[63:0] mainOff_centeroidMem_req_bits_data_y,
    output[63:0] mainOff_centeroidMem_req_bits_data_z,
    output[4:0] mainOff_centeroidMem_req_tag,
    output mainOff_centeroidMem_rep_ready,
    input  mainOff_centeroidMem_rep_valid,
    input [63:0] mainOff_centeroidMem_rep_bits_data_x,
    input [63:0] mainOff_centeroidMem_rep_bits_data_y,
    input [63:0] mainOff_centeroidMem_rep_bits_data_z,
    input [4:0] mainOff_centeroidMem_rep_tag,
    input  mainOff_partialAccumulatorMem_req_ready,
    output mainOff_partialAccumulatorMem_req_valid,
    output mainOff_partialAccumulatorMem_req_bits_rw,
    output[15:0] mainOff_partialAccumulatorMem_req_bits_addr,
    output[63:0] mainOff_partialAccumulatorMem_req_bits_data_x,
    output[63:0] mainOff_partialAccumulatorMem_req_bits_data_y,
    output[63:0] mainOff_partialAccumulatorMem_req_bits_data_z,
    output[4:0] mainOff_partialAccumulatorMem_req_tag,
    output mainOff_partialAccumulatorMem_rep_ready,
    input  mainOff_partialAccumulatorMem_rep_valid,
    input [63:0] mainOff_partialAccumulatorMem_rep_bits_data_x,
    input [63:0] mainOff_partialAccumulatorMem_rep_bits_data_y,
    input [63:0] mainOff_partialAccumulatorMem_rep_bits_data_z,
    input [4:0] mainOff_partialAccumulatorMem_rep_tag,
    input  mainOff_add_req_ready,
    output mainOff_add_req_valid,
    output[63:0] mainOff_add_req_bits_in1,
    output[63:0] mainOff_add_req_bits_in2,
    output[4:0] mainOff_add_req_tag,
    output mainOff_add_rep_ready,
    input  mainOff_add_rep_valid,
    input [63:0] mainOff_add_rep_bits_out,
    input [4:0] mainOff_add_rep_tag);

  wire offComp_io_pcOut_bits_request;
  wire mainComp_io_pcOut_bits_request;
  wire[15:0] mainComp_io_pcOut_bits_moduleId;
  wire mainComp_io_pcOut_valid;
  wire[15:0] offComp_io_pcOut_bits_moduleId;
  wire offComp_io_pcOut_valid;
  wire[3:0] offComp_io_pcOut_bits_pcType;
  wire[3:0] mainComp_io_pcOut_bits_pcType;
  wire mainComp_io_out_valid;
  wire mainComp_mainOff_add_rep_ready;
  wire mainComp_mainOff_add_req_valid;
  wire[4:0] mainComp_mainOff_add_req_tag;
  wire mainComp_mainOff_partialAccumulatorMem_rep_ready;
  wire mainComp_mainOff_partialAccumulatorMem_req_valid;
  wire[4:0] mainComp_mainOff_partialAccumulatorMem_req_tag;
  wire mainComp_mainOff_centeroidMem_rep_ready;
  wire mainComp_mainOff_centeroidMem_req_valid;
  wire[4:0] mainComp_mainOff_centeroidMem_req_tag;
  wire[4:0] offComp_io_out_tag;
  wire mainComp_mainOff_distanceFU_rep_ready;
  wire mainComp_mainOff_distanceFU_req_valid;
  wire[4:0] mainComp_mainOff_distanceFU_req_tag;
  wire offComp_io_out_valid;
  wire mainComp_io_in_ready;
  wire offComp_io_in_ready;
  wire[15:0] mainComp_io_out_bits_centeroidIndex;
  wire[7:0] offComp_io_pcOut_bits_portId;
  wire[7:0] mainComp_io_pcOut_bits_portId;
  wire[19:0] offComp_io_pcOut_bits_pcValue;
  wire[19:0] mainComp_io_pcOut_bits_pcValue;
  wire[4:0] mainComp_io_out_tag;

  assign io_pcOut_bits_request = offComp_io_pcOut_bits_request;
  assign io_pcOut_bits_moduleId = offComp_io_pcOut_bits_moduleId;
  assign io_pcOut_valid = offComp_io_pcOut_valid;
  assign io_pcOut_bits_pcType = offComp_io_pcOut_bits_pcType;
  assign io_out_valid = mainComp_io_out_valid;
  assign mainOff_add_rep_ready = mainComp_mainOff_add_rep_ready;
  assign mainOff_add_req_valid = mainComp_mainOff_add_req_valid;
  assign mainOff_add_req_tag = mainComp_mainOff_add_req_tag;
  assign mainOff_partialAccumulatorMem_rep_ready = mainComp_mainOff_partialAccumulatorMem_rep_ready;
  assign mainOff_partialAccumulatorMem_req_valid = mainComp_mainOff_partialAccumulatorMem_req_valid;
  assign mainOff_partialAccumulatorMem_req_tag = mainComp_mainOff_partialAccumulatorMem_req_tag;
  assign mainOff_centeroidMem_rep_ready = mainComp_mainOff_centeroidMem_rep_ready;
  assign mainOff_centeroidMem_req_valid = mainComp_mainOff_centeroidMem_req_valid;
  assign mainOff_centeroidMem_req_tag = mainComp_mainOff_centeroidMem_req_tag;
  assign io_in_ready = mainComp_io_in_ready;
  assign io_out_bits_centeroidIndex = mainComp_io_out_bits_centeroidIndex;
  assign io_pcOut_bits_portId = offComp_io_pcOut_bits_portId;
  assign io_pcOut_bits_pcValue = offComp_io_pcOut_bits_pcValue;
  assign io_out_tag = mainComp_io_out_tag;
  KEngine mainComp(.clk(clk), .reset(reset),
       .io_in_ready( mainComp_io_in_ready ),
       .io_in_valid( io_in_valid ),
       .io_in_bits_centeroidsFinished( io_in_bits_centeroidsFinished ),
       .io_in_bits_pointsFinished( io_in_bits_pointsFinished ),
       .io_in_bits_centeroidIndex(  ),
       .io_in_bits_point_x(  ),
       .io_in_bits_point_y(  ),
       .io_in_bits_point_z(  ),
       .io_in_tag( io_in_tag ),
       .io_out_ready( io_out_ready ),
       .io_out_valid( mainComp_io_out_valid ),
       .io_out_bits_centeroidsFinished(  ),
       .io_out_bits_pointsFinished(  ),
       .io_out_bits_centeroidIndex( mainComp_io_out_bits_centeroidIndex ),
       .io_out_bits_point_x(  ),
       .io_out_bits_point_y(  ),
       .io_out_bits_point_z(  ),
       .io_out_tag( mainComp_io_out_tag ),
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
       .mainOff_distanceFU_req_ready( offComp_io_in_ready ),
       .mainOff_distanceFU_req_valid( mainComp_mainOff_distanceFU_req_valid ),
       .mainOff_distanceFU_req_bits_in1_x(  ),
       .mainOff_distanceFU_req_bits_in1_y(  ),
       .mainOff_distanceFU_req_bits_in1_z(  ),
       .mainOff_distanceFU_req_bits_in2_x(  ),
       .mainOff_distanceFU_req_bits_in2_y(  ),
       .mainOff_distanceFU_req_bits_in2_z(  ),
       .mainOff_distanceFU_req_tag( mainComp_mainOff_distanceFU_req_tag ),
       .mainOff_distanceFU_rep_ready( mainComp_mainOff_distanceFU_rep_ready ),
       .mainOff_distanceFU_rep_valid( offComp_io_out_valid ),
       .mainOff_distanceFU_rep_bits_out(  ),
       .mainOff_distanceFU_rep_tag( offComp_io_out_tag ),
       .mainOff_centeroidMem_req_ready( mainOff_centeroidMem_req_ready ),
       .mainOff_centeroidMem_req_valid( mainComp_mainOff_centeroidMem_req_valid ),
       .mainOff_centeroidMem_req_bits_rw(  ),
       .mainOff_centeroidMem_req_bits_addr(  ),
       .mainOff_centeroidMem_req_bits_data_x(  ),
       .mainOff_centeroidMem_req_bits_data_y(  ),
       .mainOff_centeroidMem_req_bits_data_z(  ),
       .mainOff_centeroidMem_req_tag( mainComp_mainOff_centeroidMem_req_tag ),
       .mainOff_centeroidMem_rep_ready( mainComp_mainOff_centeroidMem_rep_ready ),
       .mainOff_centeroidMem_rep_valid( mainOff_centeroidMem_rep_valid ),
       .mainOff_centeroidMem_rep_bits_data_x(  ),
       .mainOff_centeroidMem_rep_bits_data_y(  ),
       .mainOff_centeroidMem_rep_bits_data_z(  ),
       .mainOff_centeroidMem_rep_tag( mainOff_centeroidMem_rep_tag ),
       .mainOff_partialAccumulatorMem_req_ready( mainOff_partialAccumulatorMem_req_ready ),
       .mainOff_partialAccumulatorMem_req_valid( mainComp_mainOff_partialAccumulatorMem_req_valid ),
       .mainOff_partialAccumulatorMem_req_bits_rw(  ),
       .mainOff_partialAccumulatorMem_req_bits_addr(  ),
       .mainOff_partialAccumulatorMem_req_bits_data_x(  ),
       .mainOff_partialAccumulatorMem_req_bits_data_y(  ),
       .mainOff_partialAccumulatorMem_req_bits_data_z(  ),
       .mainOff_partialAccumulatorMem_req_tag( mainComp_mainOff_partialAccumulatorMem_req_tag ),
       .mainOff_partialAccumulatorMem_rep_ready( mainComp_mainOff_partialAccumulatorMem_rep_ready ),
       .mainOff_partialAccumulatorMem_rep_valid( mainOff_partialAccumulatorMem_rep_valid ),
       .mainOff_partialAccumulatorMem_rep_bits_data_x(  ),
       .mainOff_partialAccumulatorMem_rep_bits_data_y(  ),
       .mainOff_partialAccumulatorMem_rep_bits_data_z(  ),
       .mainOff_partialAccumulatorMem_rep_tag( mainOff_partialAccumulatorMem_rep_tag ),
       .mainOff_add_req_ready( mainOff_add_req_ready ),
       .mainOff_add_req_valid( mainComp_mainOff_add_req_valid ),
       .mainOff_add_req_bits_in1(  ),
       .mainOff_add_req_bits_in2(  ),
       .mainOff_add_req_tag( mainComp_mainOff_add_req_tag ),
       .mainOff_add_rep_ready( mainComp_mainOff_add_rep_ready ),
       .mainOff_add_rep_valid( mainOff_add_rep_valid ),
       .mainOff_add_rep_bits_out(  ),
       .mainOff_add_rep_tag( mainOff_add_rep_tag ));
  distanceFU offComp(.clk(clk), .reset(reset),
       .io_in_ready( offComp_io_in_ready ),
       .io_in_valid( mainComp_mainOff_distanceFU_req_valid ),
       .io_in_bits_in1_x(  ),
       .io_in_bits_in1_y(  ),
       .io_in_bits_in1_z(  ),
       .io_in_bits_in2_x(  ),
       .io_in_bits_in2_y(  ),
       .io_in_bits_in2_z(  ),
       .io_in_tag( mainComp_mainOff_distanceFU_req_tag ),
       .io_out_ready( mainComp_mainOff_distanceFU_rep_ready ),
       .io_out_valid( offComp_io_out_valid ),
       .io_out_bits_out(  ),
       .io_out_tag( offComp_io_out_tag ),
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

module RREncode_9(
    input  io_valid_0,
    output io_chosen,
    input  io_ready);

  wire choose;

  assign io_chosen = choose;
  assign choose = io_valid_0 ? 1'h0/* 0*/ : 1'h1/* 1*/;
endmodule

module RREncode_10(
    input  io_valid_0,
    output io_chosen,
    input  io_ready);

  wire choose;

  assign io_chosen = choose;
  assign choose = io_valid_0 ? 1'h0/* 0*/ : 1'h1/* 1*/;
endmodule

module RREncode_11(
    input  io_valid_0,
    output io_chosen,
    input  io_ready);

  wire choose;

  assign io_chosen = choose;
  assign choose = io_valid_0 ? 1'h0/* 0*/ : 1'h1/* 1*/;
endmodule

module cMem(input clk, input reset,
    output io_in_ready,
    input  io_in_valid,
    input  io_in_bits_rw,
    input [15:0] io_in_bits_addr,
    input [63:0] io_in_bits_data_x,
    input [63:0] io_in_bits_data_y,
    input [63:0] io_in_bits_data_z,
    input [4:0] io_in_tag,
    input  io_out_ready,
    output io_out_valid,
    output[63:0] io_out_bits_data_x,
    output[63:0] io_out_bits_data_y,
    output[63:0] io_out_bits_data_z,
    output[4:0] io_out_tag,
    input  io_pcIn_valid,
    input  io_pcIn_bits_request,
    input [15:0] io_pcIn_bits_moduleId,
    input [7:0] io_pcIn_bits_portId,
    input [19:0] io_pcIn_bits_pcValue,
    input [3:0] io_pcIn_bits_pcType,
    output io_pcOut_valid,
    output io_pcOut_bits_request,
    output[15:0] io_pcOut_bits_moduleId,
    output[7:0] io_pcOut_bits_portId,
    output[19:0] io_pcOut_bits_pcValue,
    output[3:0] io_pcOut_bits_pcType);

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
  wire[4:0] T20;
  wire[4:0] T21;
  wire T22;
  wire T23;
  wire[1:0] T24;
  wire rThreadEncoder_io_chosen;
  wire T25;
  reg[0:0] subStateTh_0;
  wire T26;
  wire T27;
  wire T28;
  wire vThreadEncoder_io_chosen;
  wire T29;
  wire AllOffloadsValid_0;
  wire T30;
  wire T31;
  wire T32;
  reg[7:0] State_0;
  wire T33;
  wire T34;
  wire T35;
  wire T36;
  wire[1:0] T37;
  wire T38;
  wire T39;
  wire[7:0] T40;
  wire[7:0] T41;
  wire[7:0] T42;
  wire T43;
  wire[1:0] T44;
  wire T45;
  wire T46;
  wire T47;
  wire T48;
  wire T49;
  wire[7:0] T50;
  wire[7:0] T51;
  wire T52;
  wire[1:0] T53;
  wire T54;
  wire T55;
  wire T56;
  wire[1:0] T57;
  wire sThreadEncoder_io_chosen;
  wire T58;
  wire T59;
  wire T60;
  wire T61;
  wire T62;
  wire[1:0] T63;
  wire[7:0] T64;
  wire[7:0] T65;
  wire[7:0] T66;
  wire[7:0] T67;
  wire[7:0] T68;
  wire[7:0] T69;
  reg[7:0] EmitReturnState_0;
  wire[7:0] T70;
  wire T71;
  wire T72;
  wire T73;
  wire T74;
  wire AllOffloadsReady;
  reg[4:0] inputTag_0;
  wire[4:0] T75;
  wire T76;
  wire T77;
  wire T78;
  wire[1:0] T79;
  wire T80;
  wire[1:0] T81;
  reg[7:0] R82;
  wire T83;
  wire[7:0] T84;
  wire T85;
  reg[19:0] R86;
  wire T87;
  wire[19:0] T88;
  wire[19:0] T89;
  wire[19:0] T90;
  wire[19:0] T91;
  wire[19:0] T92;
  reg[19:0] R93;
  wire T94;
  wire T95;
  wire T96;
  wire T97;
  reg[0:0] R98;
  wire T99;
  wire T100;
  wire T101;
  wire[3:0] T0;
  wire T102;
  wire T103;
  wire T104;
  wire[3:0] T1;
  wire T105;
  wire T106;
  wire T107;
  wire T108;
  wire T109;
  wire T110;
  wire T111;
  wire[19:0] T112;
  wire[19:0] T113;
  wire[19:0] T114;
  wire T115;
  wire T116;
  wire[3:0] T2;
  wire T117;
  wire[19:0] T118;
  reg[19:0] R119;
  wire T120;
  wire T121;
  wire T122;
  wire T123;
  wire T124;
  wire T125;
  wire T126;
  wire[19:0] T127;
  wire[19:0] T128;
  wire[19:0] T129;
  wire T130;
  wire T131;
  wire T132;
  wire[19:0] T133;
  wire[19:0] T134;
  wire T135;
  wire[3:0] T3;

  assign io_pcOut_bits_request = R0;
  assign T1 = T3 || T2;
  assign T2 = ! T3;
  assign T3 = T4 && io_pcIn_bits_request;
  assign T4 = io_pcIn_valid && T5;
  assign T5 = io_pcIn_bits_moduleId == 16'hb/* 11*/;
  assign T6 = T2 ? io_pcIn_bits_request : T7;
  assign T7 = T3 ? 1'h0/* 0*/ : R0;
  assign io_pcOut_bits_moduleId = R8;
  assign T9 = T3 || T2;
  assign T10 = T11 ? io_pcIn_bits_moduleId : R8;
  assign T11 = T3 || T2;
  assign io_pcOut_valid = R12;
  assign T13 = T3 || T2;
  assign T14 = T2 ? io_pcIn_valid : T15;
  assign T15 = T3 ? 1'h1/* 1*/ : R12;
  assign io_pcOut_bits_pcType = R16;
  assign T17 = T3 || T2;
  assign T18 = T19 ? io_pcIn_bits_pcType : R16;
  assign T19 = T3 || T2;
  assign io_out_tag = T20;
  assign T20 = inputTag_0 & T21;
  assign T21 = {3'h5/* 5*/{T22}};
  assign T22 = T23;
  assign T23 = T24[1'h0/* 0*/:1'h0/* 0*/];
  assign T24 = 1'h1/* 1*/ << rThreadEncoder_io_chosen;
  assign T25 = subStateTh_0 == 1'h0/* 0*/;
  assign T26 = T31 ? 1'h1/* 1*/ : T27;
  assign T27 = T28 ? 1'h0/* 0*/ : subStateTh_0;
  assign T28 = 1'h0/* 0*/ == vThreadEncoder_io_chosen;
  assign T29 = T30 && AllOffloadsValid_0;
  assign AllOffloadsValid_0 = 1'h1/* 1*/;
  assign T30 = subStateTh_0 == 1'h1/* 1*/;
  assign T31 = T71 && T32;
  assign T32 = State_0 != 8'hff/* 255*/;
  assign T33 = T45 || T34;
  assign T34 = T38 && T35;
  assign T35 = T36;
  assign T36 = T37[1'h0/* 0*/:1'h0/* 0*/];
  assign T37 = 1'h1/* 1*/ << vThreadEncoder_io_chosen;
  assign T38 = T43 && T39;
  assign T39 = T41 == T40;
  assign T40 = {7'h0/* 0*/, 1'h1/* 1*/};
  assign T41 = State_0 & T42;
  assign T42 = {4'h8/* 8*/{T35}};
  assign T43 = T44 != 2'h1/* 1*/;
  assign T44 = {1'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T45 = T54 || T46;
  assign T46 = T47 && T22;
  assign T47 = T48 && io_out_ready;
  assign T48 = T52 && T49;
  assign T49 = T50 == 8'hff/* 255*/;
  assign T50 = State_0 & T51;
  assign T51 = {4'h8/* 8*/{T22}};
  assign T52 = T53 != 2'h1/* 1*/;
  assign T53 = {1'h0/* 0*/, rThreadEncoder_io_chosen};
  assign T54 = T61 && T55;
  assign T55 = T56;
  assign T56 = T57[1'h0/* 0*/:1'h0/* 0*/];
  assign T57 = 1'h1/* 1*/ << sThreadEncoder_io_chosen;
  assign T58 = T60 && T59;
  assign T59 = State_0 == 8'h0/* 0*/;
  assign T60 = subStateTh_0 == 1'h0/* 0*/;
  assign T61 = T62 && io_in_valid;
  assign T62 = T63 != 2'h1/* 1*/;
  assign T63 = {1'h0/* 0*/, sThreadEncoder_io_chosen};
  assign T64 = T34 ? 8'hff/* 255*/ : T65;
  assign T65 = T46 ? T68 : T66;
  assign T66 = T54 ? T67 : State_0;
  assign T67 = {7'h0/* 0*/, 1'h1/* 1*/};
  assign T68 = EmitReturnState_0 & T69;
  assign T69 = {4'h8/* 8*/{T22}};
  assign T70 = T34 ? 8'h0/* 0*/ : EmitReturnState_0;
  assign T71 = T73 && T72;
  assign T72 = State_0 != 8'h0/* 0*/;
  assign T73 = AllOffloadsReady && T74;
  assign T74 = 1'h0/* 0*/ == rThreadEncoder_io_chosen;
  assign AllOffloadsReady = 1'h1/* 1*/;
  assign T75 = T54 ? io_in_tag : inputTag_0;
  assign io_out_valid = T76;
  assign T76 = T78 && T77;
  assign T77 = T50 == 8'hff/* 255*/;
  assign T78 = T79 != 2'h1/* 1*/;
  assign T79 = {1'h0/* 0*/, rThreadEncoder_io_chosen};
  assign io_in_ready = T80;
  assign T80 = T81 != 2'h1/* 1*/;
  assign T81 = {1'h0/* 0*/, sThreadEncoder_io_chosen};
  assign io_pcOut_bits_portId = R82;
  assign T83 = T3 || T2;
  assign T84 = T85 ? io_pcIn_bits_portId : R82;
  assign T85 = T3 || T2;
  assign io_pcOut_bits_pcValue = R86;
  assign T87 = T3 || T2;
  assign T88 = T2 ? io_pcIn_bits_pcValue : T89;
  assign T89 = T3 ? T90 : R86;
  assign T90 = T135 ? T134 : T91;
  assign T91 = T130 ? R119 : T92;
  assign T92 = T115 ? R93 : 20'h0/* 0*/;
  assign T94 = T103 || T95;
  assign T95 = T111 && T96;
  assign T96 = T109 && T97;
  assign T97 = ! R98;
  assign T99 = T103 || T100;
  assign T100 = T102 && T101;
  assign T101 = io_pcIn_bits_pcType == T0;
  assign T0 = {1'h0/* 0*/, 3'h4/* 4*/};
  assign T102 = io_pcIn_valid && io_pcIn_bits_request;
  assign T103 = T105 && T104;
  assign T104 = io_pcIn_bits_pcType == T1;
  assign T1 = {1'h0/* 0*/, 3'h3/* 3*/};
  assign T105 = io_pcIn_valid && io_pcIn_bits_request;
  assign T106 = T100 ? T108 : T107;
  assign T107 = T103 ? 1'h0/* 0*/ : R98;
  assign T108 = ! R98;
  assign T109 = io_in_valid && T110;
  assign T110 = ! io_in_ready;
  assign T111 = ! T103;
  assign T112 = T95 ? T114 : T113;
  assign T113 = T103 ? 20'h0/* 0*/ : R93;
  assign T114 = R93 + 20'h1/* 1*/;
  assign T115 = T117 && T116;
  assign T116 = io_pcIn_bits_pcType == T2;
  assign T2 = {1'h0/* 0*/, 3'h1/* 1*/};
  assign T117 = T118 == 20'h1/* 1*/;
  assign T118 = {12'h0/* 0*/, io_pcIn_bits_portId};
  assign T120 = T103 || T121;
  assign T121 = T126 && T122;
  assign T122 = T124 && T123;
  assign T123 = ! R98;
  assign T124 = io_out_valid && T125;
  assign T125 = ! io_out_ready;
  assign T126 = ! T103;
  assign T127 = T121 ? T129 : T128;
  assign T128 = T103 ? 20'h0/* 0*/ : R119;
  assign T129 = R119 + 20'h1/* 1*/;
  assign T130 = T132 && T131;
  assign T131 = io_pcIn_bits_pcType == T2;
  assign T132 = T133 == 20'h2/* 2*/;
  assign T133 = {12'h0/* 0*/, io_pcIn_bits_portId};
  assign T134 = {15'h0/* 0*/, 5'h14/* 20*/};
  assign T135 = io_pcIn_bits_pcType == T3;
  assign T3 = {1'h0/* 0*/, 3'h2/* 2*/};
  RREncode_9 rThreadEncoder(
       .io_valid_0( T25 ),
       .io_chosen( rThreadEncoder_io_chosen ),
       .io_ready(  ));
  RREncode_10 vThreadEncoder(
       .io_valid_0( T29 ),
       .io_chosen( vThreadEncoder_io_chosen ),
       .io_ready(  ));
  RREncode_11 sThreadEncoder(
       .io_valid_0( T58 ),
       .io_chosen( sThreadEncoder_io_chosen ),
       .io_ready(  ));

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
    subStateTh_0 <= reset ? 1'h0/* 0*/ : T26;
    if(reset) begin
      State_0 <= 8'h0/* 0*/;
    end else if(T33) begin
      State_0 <= T64;
    end
    if(reset) begin
      EmitReturnState_0 <= 8'h0/* 0*/;
    end else if(T34) begin
      EmitReturnState_0 <= T70;
    end
    if(T54) begin
      inputTag_0 <= T75;
    end
    if(reset) begin
      R82 <= 8'h0/* 0*/;
    end else if(T83) begin
      R82 <= T84;
    end
    if(reset) begin
      R86 <= 20'h0/* 0*/;
    end else if(T87) begin
      R86 <= T88;
    end
    if(reset) begin
      R93 <= 20'h0/* 0*/;
    end else if(T94) begin
      R93 <= T112;
    end
    if(reset) begin
      R98 <= 1'h0/* 0*/;
    end else if(T99) begin
      R98 <= T106;
    end
    if(reset) begin
      R119 <= 20'h0/* 0*/;
    end else if(T120) begin
      R119 <= T127;
    end
  end
endmodule

module gOffloadedComponent_1(input clk, input reset,
    output io_in_ready,
    input  io_in_valid,
    input  io_in_bits_centeroidsFinished,
    input  io_in_bits_pointsFinished,
    input [15:0] io_in_bits_centeroidIndex,
    input [63:0] io_in_bits_point_x,
    input [63:0] io_in_bits_point_y,
    input [63:0] io_in_bits_point_z,
    input [4:0] io_in_tag,
    input  io_out_ready,
    output io_out_valid,
    output io_out_bits_centeroidsFinished,
    output io_out_bits_pointsFinished,
    output[15:0] io_out_bits_centeroidIndex,
    output[63:0] io_out_bits_point_x,
    output[63:0] io_out_bits_point_y,
    output[63:0] io_out_bits_point_z,
    output[4:0] io_out_tag,
    input  io_pcIn_valid,
    input  io_pcIn_bits_request,
    input [15:0] io_pcIn_bits_moduleId,
    input [7:0] io_pcIn_bits_portId,
    input [19:0] io_pcIn_bits_pcValue,
    input [3:0] io_pcIn_bits_pcType,
    output io_pcOut_valid,
    output io_pcOut_bits_request,
    output[15:0] io_pcOut_bits_moduleId,
    output[7:0] io_pcOut_bits_portId,
    output[19:0] io_pcOut_bits_pcValue,
    output[3:0] io_pcOut_bits_pcType,
    input  mainOff_partialAccumulatorMem_req_ready,
    output mainOff_partialAccumulatorMem_req_valid,
    output mainOff_partialAccumulatorMem_req_bits_rw,
    output[15:0] mainOff_partialAccumulatorMem_req_bits_addr,
    output[63:0] mainOff_partialAccumulatorMem_req_bits_data_x,
    output[63:0] mainOff_partialAccumulatorMem_req_bits_data_y,
    output[63:0] mainOff_partialAccumulatorMem_req_bits_data_z,
    output[4:0] mainOff_partialAccumulatorMem_req_tag,
    output mainOff_partialAccumulatorMem_rep_ready,
    input  mainOff_partialAccumulatorMem_rep_valid,
    input [63:0] mainOff_partialAccumulatorMem_rep_bits_data_x,
    input [63:0] mainOff_partialAccumulatorMem_rep_bits_data_y,
    input [63:0] mainOff_partialAccumulatorMem_rep_bits_data_z,
    input [4:0] mainOff_partialAccumulatorMem_rep_tag,
    input  mainOff_add_req_ready,
    output mainOff_add_req_valid,
    output[63:0] mainOff_add_req_bits_in1,
    output[63:0] mainOff_add_req_bits_in2,
    output[4:0] mainOff_add_req_tag,
    output mainOff_add_rep_ready,
    input  mainOff_add_rep_valid,
    input [63:0] mainOff_add_rep_bits_out,
    input [4:0] mainOff_add_rep_tag);

  wire offComp_io_pcOut_bits_request;
  wire mainComp_io_pcOut_bits_request;
  wire[15:0] mainComp_io_pcOut_bits_moduleId;
  wire mainComp_io_pcOut_valid;
  wire[15:0] offComp_io_pcOut_bits_moduleId;
  wire offComp_io_pcOut_valid;
  wire[3:0] offComp_io_pcOut_bits_pcType;
  wire[3:0] mainComp_io_pcOut_bits_pcType;
  wire mainComp_io_out_valid;
  wire mainComp_mainOff_add_rep_ready;
  wire mainComp_mainOff_add_req_valid;
  wire[4:0] mainComp_mainOff_add_req_tag;
  wire mainComp_mainOff_partialAccumulatorMem_rep_ready;
  wire mainComp_mainOff_partialAccumulatorMem_req_valid;
  wire[4:0] mainComp_mainOff_partialAccumulatorMem_req_tag;
  wire[4:0] offComp_io_out_tag;
  wire mainComp_mainOff_centeroidMem_rep_ready;
  wire mainComp_mainOff_centeroidMem_req_valid;
  wire[4:0] mainComp_mainOff_centeroidMem_req_tag;
  wire offComp_io_out_valid;
  wire mainComp_io_in_ready;
  wire offComp_io_in_ready;
  wire[15:0] mainComp_io_out_bits_centeroidIndex;
  wire[7:0] offComp_io_pcOut_bits_portId;
  wire[7:0] mainComp_io_pcOut_bits_portId;
  wire[19:0] offComp_io_pcOut_bits_pcValue;
  wire[19:0] mainComp_io_pcOut_bits_pcValue;
  wire[4:0] mainComp_io_out_tag;

  assign io_pcOut_bits_request = offComp_io_pcOut_bits_request;
  assign io_pcOut_bits_moduleId = offComp_io_pcOut_bits_moduleId;
  assign io_pcOut_valid = offComp_io_pcOut_valid;
  assign io_pcOut_bits_pcType = offComp_io_pcOut_bits_pcType;
  assign io_out_valid = mainComp_io_out_valid;
  assign mainOff_add_rep_ready = mainComp_mainOff_add_rep_ready;
  assign mainOff_add_req_valid = mainComp_mainOff_add_req_valid;
  assign mainOff_add_req_tag = mainComp_mainOff_add_req_tag;
  assign mainOff_partialAccumulatorMem_rep_ready = mainComp_mainOff_partialAccumulatorMem_rep_ready;
  assign mainOff_partialAccumulatorMem_req_valid = mainComp_mainOff_partialAccumulatorMem_req_valid;
  assign mainOff_partialAccumulatorMem_req_tag = mainComp_mainOff_partialAccumulatorMem_req_tag;
  assign io_in_ready = mainComp_io_in_ready;
  assign io_out_bits_centeroidIndex = mainComp_io_out_bits_centeroidIndex;
  assign io_pcOut_bits_portId = offComp_io_pcOut_bits_portId;
  assign io_pcOut_bits_pcValue = offComp_io_pcOut_bits_pcValue;
  assign io_out_tag = mainComp_io_out_tag;
  gOffloadedComponent mainComp(.clk(clk), .reset(reset),
       .io_in_ready( mainComp_io_in_ready ),
       .io_in_valid( io_in_valid ),
       .io_in_bits_centeroidsFinished( io_in_bits_centeroidsFinished ),
       .io_in_bits_pointsFinished( io_in_bits_pointsFinished ),
       .io_in_bits_centeroidIndex(  ),
       .io_in_bits_point_x(  ),
       .io_in_bits_point_y(  ),
       .io_in_bits_point_z(  ),
       .io_in_tag( io_in_tag ),
       .io_out_ready( io_out_ready ),
       .io_out_valid( mainComp_io_out_valid ),
       .io_out_bits_centeroidsFinished(  ),
       .io_out_bits_pointsFinished(  ),
       .io_out_bits_centeroidIndex( mainComp_io_out_bits_centeroidIndex ),
       .io_out_bits_point_x(  ),
       .io_out_bits_point_y(  ),
       .io_out_bits_point_z(  ),
       .io_out_tag( mainComp_io_out_tag ),
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
       .mainOff_centeroidMem_req_ready( offComp_io_in_ready ),
       .mainOff_centeroidMem_req_valid( mainComp_mainOff_centeroidMem_req_valid ),
       .mainOff_centeroidMem_req_bits_rw(  ),
       .mainOff_centeroidMem_req_bits_addr(  ),
       .mainOff_centeroidMem_req_bits_data_x(  ),
       .mainOff_centeroidMem_req_bits_data_y(  ),
       .mainOff_centeroidMem_req_bits_data_z(  ),
       .mainOff_centeroidMem_req_tag( mainComp_mainOff_centeroidMem_req_tag ),
       .mainOff_centeroidMem_rep_ready( mainComp_mainOff_centeroidMem_rep_ready ),
       .mainOff_centeroidMem_rep_valid( offComp_io_out_valid ),
       .mainOff_centeroidMem_rep_bits_data_x(  ),
       .mainOff_centeroidMem_rep_bits_data_y(  ),
       .mainOff_centeroidMem_rep_bits_data_z(  ),
       .mainOff_centeroidMem_rep_tag( offComp_io_out_tag ),
       .mainOff_partialAccumulatorMem_req_ready( mainOff_partialAccumulatorMem_req_ready ),
       .mainOff_partialAccumulatorMem_req_valid( mainComp_mainOff_partialAccumulatorMem_req_valid ),
       .mainOff_partialAccumulatorMem_req_bits_rw(  ),
       .mainOff_partialAccumulatorMem_req_bits_addr(  ),
       .mainOff_partialAccumulatorMem_req_bits_data_x(  ),
       .mainOff_partialAccumulatorMem_req_bits_data_y(  ),
       .mainOff_partialAccumulatorMem_req_bits_data_z(  ),
       .mainOff_partialAccumulatorMem_req_tag( mainComp_mainOff_partialAccumulatorMem_req_tag ),
       .mainOff_partialAccumulatorMem_rep_ready( mainComp_mainOff_partialAccumulatorMem_rep_ready ),
       .mainOff_partialAccumulatorMem_rep_valid( mainOff_partialAccumulatorMem_rep_valid ),
       .mainOff_partialAccumulatorMem_rep_bits_data_x(  ),
       .mainOff_partialAccumulatorMem_rep_bits_data_y(  ),
       .mainOff_partialAccumulatorMem_rep_bits_data_z(  ),
       .mainOff_partialAccumulatorMem_rep_tag( mainOff_partialAccumulatorMem_rep_tag ),
       .mainOff_add_req_ready( mainOff_add_req_ready ),
       .mainOff_add_req_valid( mainComp_mainOff_add_req_valid ),
       .mainOff_add_req_bits_in1(  ),
       .mainOff_add_req_bits_in2(  ),
       .mainOff_add_req_tag( mainComp_mainOff_add_req_tag ),
       .mainOff_add_rep_ready( mainComp_mainOff_add_rep_ready ),
       .mainOff_add_rep_valid( mainOff_add_rep_valid ),
       .mainOff_add_rep_bits_out(  ),
       .mainOff_add_rep_tag( mainOff_add_rep_tag ));
  cMem offComp(.clk(clk), .reset(reset),
       .io_in_ready( offComp_io_in_ready ),
       .io_in_valid( mainComp_mainOff_centeroidMem_req_valid ),
       .io_in_bits_rw(  ),
       .io_in_bits_addr(  ),
       .io_in_bits_data_x(  ),
       .io_in_bits_data_y(  ),
       .io_in_bits_data_z(  ),
       .io_in_tag( mainComp_mainOff_centeroidMem_req_tag ),
       .io_out_ready( mainComp_mainOff_centeroidMem_rep_ready ),
       .io_out_valid( offComp_io_out_valid ),
       .io_out_bits_data_x(  ),
       .io_out_bits_data_y(  ),
       .io_out_bits_data_z(  ),
       .io_out_tag( offComp_io_out_tag ),
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

module RREncode_12(
    input  io_valid_0,
    output io_chosen,
    input  io_ready);

  wire choose;

  assign io_chosen = choose;
  assign choose = io_valid_0 ? 1'h0/* 0*/ : 1'h1/* 1*/;
endmodule

module RREncode_13(
    input  io_valid_0,
    output io_chosen,
    input  io_ready);

  wire choose;

  assign io_chosen = choose;
  assign choose = io_valid_0 ? 1'h0/* 0*/ : 1'h1/* 1*/;
endmodule

module RREncode_14(
    input  io_valid_0,
    output io_chosen,
    input  io_ready);

  wire choose;

  assign io_chosen = choose;
  assign choose = io_valid_0 ? 1'h0/* 0*/ : 1'h1/* 1*/;
endmodule

module pAccMem(input clk, input reset,
    output io_in_ready,
    input  io_in_valid,
    input  io_in_bits_rw,
    input [15:0] io_in_bits_addr,
    input [63:0] io_in_bits_data_x,
    input [63:0] io_in_bits_data_y,
    input [63:0] io_in_bits_data_z,
    input [4:0] io_in_tag,
    input  io_out_ready,
    output io_out_valid,
    output[63:0] io_out_bits_data_x,
    output[63:0] io_out_bits_data_y,
    output[63:0] io_out_bits_data_z,
    output[4:0] io_out_tag,
    input  io_pcIn_valid,
    input  io_pcIn_bits_request,
    input [15:0] io_pcIn_bits_moduleId,
    input [7:0] io_pcIn_bits_portId,
    input [19:0] io_pcIn_bits_pcValue,
    input [3:0] io_pcIn_bits_pcType,
    output io_pcOut_valid,
    output io_pcOut_bits_request,
    output[15:0] io_pcOut_bits_moduleId,
    output[7:0] io_pcOut_bits_portId,
    output[19:0] io_pcOut_bits_pcValue,
    output[3:0] io_pcOut_bits_pcType);

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
  wire[4:0] T20;
  wire[4:0] T21;
  wire T22;
  wire T23;
  wire[1:0] T24;
  wire rThreadEncoder_io_chosen;
  wire T25;
  reg[0:0] subStateTh_0;
  wire T26;
  wire T27;
  wire T28;
  wire vThreadEncoder_io_chosen;
  wire T29;
  wire AllOffloadsValid_0;
  wire T30;
  wire T31;
  wire T32;
  reg[7:0] State_0;
  wire T33;
  wire T34;
  wire T35;
  wire T36;
  wire[1:0] T37;
  wire T38;
  wire T39;
  wire[7:0] T40;
  wire[7:0] T41;
  wire[7:0] T42;
  wire T43;
  wire[1:0] T44;
  wire T45;
  wire T46;
  wire T47;
  wire T48;
  wire T49;
  wire[7:0] T50;
  wire[7:0] T51;
  wire T52;
  wire[1:0] T53;
  wire T54;
  wire T55;
  wire T56;
  wire[1:0] T57;
  wire sThreadEncoder_io_chosen;
  wire T58;
  wire T59;
  wire T60;
  wire T61;
  wire T62;
  wire[1:0] T63;
  wire[7:0] T64;
  wire[7:0] T65;
  wire[7:0] T66;
  wire[7:0] T67;
  wire[7:0] T68;
  wire[7:0] T69;
  reg[7:0] EmitReturnState_0;
  wire[7:0] T70;
  wire T71;
  wire T72;
  wire T73;
  wire T74;
  wire AllOffloadsReady;
  reg[4:0] inputTag_0;
  wire[4:0] T75;
  wire T76;
  wire T77;
  wire T78;
  wire[1:0] T79;
  wire T80;
  wire[1:0] T81;
  reg[7:0] R82;
  wire T83;
  wire[7:0] T84;
  wire T85;
  reg[19:0] R86;
  wire T87;
  wire[19:0] T88;
  wire[19:0] T89;
  wire[19:0] T90;
  wire[19:0] T91;
  wire[19:0] T92;
  reg[19:0] R93;
  wire T94;
  wire T95;
  wire T96;
  wire T97;
  reg[0:0] R98;
  wire T99;
  wire T100;
  wire T101;
  wire[3:0] T0;
  wire T102;
  wire T103;
  wire T104;
  wire[3:0] T1;
  wire T105;
  wire T106;
  wire T107;
  wire T108;
  wire T109;
  wire T110;
  wire T111;
  wire[19:0] T112;
  wire[19:0] T113;
  wire[19:0] T114;
  wire T115;
  wire T116;
  wire[3:0] T2;
  wire T117;
  wire[19:0] T118;
  reg[19:0] R119;
  wire T120;
  wire T121;
  wire T122;
  wire T123;
  wire T124;
  wire T125;
  wire T126;
  wire[19:0] T127;
  wire[19:0] T128;
  wire[19:0] T129;
  wire T130;
  wire T131;
  wire T132;
  wire[19:0] T133;
  wire[19:0] T134;
  wire T135;
  wire[3:0] T3;

  assign io_pcOut_bits_request = R0;
  assign T1 = T3 || T2;
  assign T2 = ! T3;
  assign T3 = T4 && io_pcIn_bits_request;
  assign T4 = io_pcIn_valid && T5;
  assign T5 = io_pcIn_bits_moduleId == 16'hc/* 12*/;
  assign T6 = T2 ? io_pcIn_bits_request : T7;
  assign T7 = T3 ? 1'h0/* 0*/ : R0;
  assign io_pcOut_bits_moduleId = R8;
  assign T9 = T3 || T2;
  assign T10 = T11 ? io_pcIn_bits_moduleId : R8;
  assign T11 = T3 || T2;
  assign io_pcOut_valid = R12;
  assign T13 = T3 || T2;
  assign T14 = T2 ? io_pcIn_valid : T15;
  assign T15 = T3 ? 1'h1/* 1*/ : R12;
  assign io_pcOut_bits_pcType = R16;
  assign T17 = T3 || T2;
  assign T18 = T19 ? io_pcIn_bits_pcType : R16;
  assign T19 = T3 || T2;
  assign io_out_tag = T20;
  assign T20 = inputTag_0 & T21;
  assign T21 = {3'h5/* 5*/{T22}};
  assign T22 = T23;
  assign T23 = T24[1'h0/* 0*/:1'h0/* 0*/];
  assign T24 = 1'h1/* 1*/ << rThreadEncoder_io_chosen;
  assign T25 = subStateTh_0 == 1'h0/* 0*/;
  assign T26 = T31 ? 1'h1/* 1*/ : T27;
  assign T27 = T28 ? 1'h0/* 0*/ : subStateTh_0;
  assign T28 = 1'h0/* 0*/ == vThreadEncoder_io_chosen;
  assign T29 = T30 && AllOffloadsValid_0;
  assign AllOffloadsValid_0 = 1'h1/* 1*/;
  assign T30 = subStateTh_0 == 1'h1/* 1*/;
  assign T31 = T71 && T32;
  assign T32 = State_0 != 8'hff/* 255*/;
  assign T33 = T45 || T34;
  assign T34 = T38 && T35;
  assign T35 = T36;
  assign T36 = T37[1'h0/* 0*/:1'h0/* 0*/];
  assign T37 = 1'h1/* 1*/ << vThreadEncoder_io_chosen;
  assign T38 = T43 && T39;
  assign T39 = T41 == T40;
  assign T40 = {7'h0/* 0*/, 1'h1/* 1*/};
  assign T41 = State_0 & T42;
  assign T42 = {4'h8/* 8*/{T35}};
  assign T43 = T44 != 2'h1/* 1*/;
  assign T44 = {1'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T45 = T54 || T46;
  assign T46 = T47 && T22;
  assign T47 = T48 && io_out_ready;
  assign T48 = T52 && T49;
  assign T49 = T50 == 8'hff/* 255*/;
  assign T50 = State_0 & T51;
  assign T51 = {4'h8/* 8*/{T22}};
  assign T52 = T53 != 2'h1/* 1*/;
  assign T53 = {1'h0/* 0*/, rThreadEncoder_io_chosen};
  assign T54 = T61 && T55;
  assign T55 = T56;
  assign T56 = T57[1'h0/* 0*/:1'h0/* 0*/];
  assign T57 = 1'h1/* 1*/ << sThreadEncoder_io_chosen;
  assign T58 = T60 && T59;
  assign T59 = State_0 == 8'h0/* 0*/;
  assign T60 = subStateTh_0 == 1'h0/* 0*/;
  assign T61 = T62 && io_in_valid;
  assign T62 = T63 != 2'h1/* 1*/;
  assign T63 = {1'h0/* 0*/, sThreadEncoder_io_chosen};
  assign T64 = T34 ? 8'hff/* 255*/ : T65;
  assign T65 = T46 ? T68 : T66;
  assign T66 = T54 ? T67 : State_0;
  assign T67 = {7'h0/* 0*/, 1'h1/* 1*/};
  assign T68 = EmitReturnState_0 & T69;
  assign T69 = {4'h8/* 8*/{T22}};
  assign T70 = T34 ? 8'h0/* 0*/ : EmitReturnState_0;
  assign T71 = T73 && T72;
  assign T72 = State_0 != 8'h0/* 0*/;
  assign T73 = AllOffloadsReady && T74;
  assign T74 = 1'h0/* 0*/ == rThreadEncoder_io_chosen;
  assign AllOffloadsReady = 1'h1/* 1*/;
  assign T75 = T54 ? io_in_tag : inputTag_0;
  assign io_out_valid = T76;
  assign T76 = T78 && T77;
  assign T77 = T50 == 8'hff/* 255*/;
  assign T78 = T79 != 2'h1/* 1*/;
  assign T79 = {1'h0/* 0*/, rThreadEncoder_io_chosen};
  assign io_in_ready = T80;
  assign T80 = T81 != 2'h1/* 1*/;
  assign T81 = {1'h0/* 0*/, sThreadEncoder_io_chosen};
  assign io_pcOut_bits_portId = R82;
  assign T83 = T3 || T2;
  assign T84 = T85 ? io_pcIn_bits_portId : R82;
  assign T85 = T3 || T2;
  assign io_pcOut_bits_pcValue = R86;
  assign T87 = T3 || T2;
  assign T88 = T2 ? io_pcIn_bits_pcValue : T89;
  assign T89 = T3 ? T90 : R86;
  assign T90 = T135 ? T134 : T91;
  assign T91 = T130 ? R119 : T92;
  assign T92 = T115 ? R93 : 20'h0/* 0*/;
  assign T94 = T103 || T95;
  assign T95 = T111 && T96;
  assign T96 = T109 && T97;
  assign T97 = ! R98;
  assign T99 = T103 || T100;
  assign T100 = T102 && T101;
  assign T101 = io_pcIn_bits_pcType == T0;
  assign T0 = {1'h0/* 0*/, 3'h4/* 4*/};
  assign T102 = io_pcIn_valid && io_pcIn_bits_request;
  assign T103 = T105 && T104;
  assign T104 = io_pcIn_bits_pcType == T1;
  assign T1 = {1'h0/* 0*/, 3'h3/* 3*/};
  assign T105 = io_pcIn_valid && io_pcIn_bits_request;
  assign T106 = T100 ? T108 : T107;
  assign T107 = T103 ? 1'h0/* 0*/ : R98;
  assign T108 = ! R98;
  assign T109 = io_in_valid && T110;
  assign T110 = ! io_in_ready;
  assign T111 = ! T103;
  assign T112 = T95 ? T114 : T113;
  assign T113 = T103 ? 20'h0/* 0*/ : R93;
  assign T114 = R93 + 20'h1/* 1*/;
  assign T115 = T117 && T116;
  assign T116 = io_pcIn_bits_pcType == T2;
  assign T2 = {1'h0/* 0*/, 3'h1/* 1*/};
  assign T117 = T118 == 20'h1/* 1*/;
  assign T118 = {12'h0/* 0*/, io_pcIn_bits_portId};
  assign T120 = T103 || T121;
  assign T121 = T126 && T122;
  assign T122 = T124 && T123;
  assign T123 = ! R98;
  assign T124 = io_out_valid && T125;
  assign T125 = ! io_out_ready;
  assign T126 = ! T103;
  assign T127 = T121 ? T129 : T128;
  assign T128 = T103 ? 20'h0/* 0*/ : R119;
  assign T129 = R119 + 20'h1/* 1*/;
  assign T130 = T132 && T131;
  assign T131 = io_pcIn_bits_pcType == T2;
  assign T132 = T133 == 20'h2/* 2*/;
  assign T133 = {12'h0/* 0*/, io_pcIn_bits_portId};
  assign T134 = {15'h0/* 0*/, 5'h14/* 20*/};
  assign T135 = io_pcIn_bits_pcType == T3;
  assign T3 = {1'h0/* 0*/, 3'h2/* 2*/};
  RREncode_12 rThreadEncoder(
       .io_valid_0( T25 ),
       .io_chosen( rThreadEncoder_io_chosen ),
       .io_ready(  ));
  RREncode_13 vThreadEncoder(
       .io_valid_0( T29 ),
       .io_chosen( vThreadEncoder_io_chosen ),
       .io_ready(  ));
  RREncode_14 sThreadEncoder(
       .io_valid_0( T58 ),
       .io_chosen( sThreadEncoder_io_chosen ),
       .io_ready(  ));

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
    subStateTh_0 <= reset ? 1'h0/* 0*/ : T26;
    if(reset) begin
      State_0 <= 8'h0/* 0*/;
    end else if(T33) begin
      State_0 <= T64;
    end
    if(reset) begin
      EmitReturnState_0 <= 8'h0/* 0*/;
    end else if(T34) begin
      EmitReturnState_0 <= T70;
    end
    if(T54) begin
      inputTag_0 <= T75;
    end
    if(reset) begin
      R82 <= 8'h0/* 0*/;
    end else if(T83) begin
      R82 <= T84;
    end
    if(reset) begin
      R86 <= 20'h0/* 0*/;
    end else if(T87) begin
      R86 <= T88;
    end
    if(reset) begin
      R93 <= 20'h0/* 0*/;
    end else if(T94) begin
      R93 <= T112;
    end
    if(reset) begin
      R98 <= 1'h0/* 0*/;
    end else if(T99) begin
      R98 <= T106;
    end
    if(reset) begin
      R119 <= 20'h0/* 0*/;
    end else if(T120) begin
      R119 <= T127;
    end
  end
endmodule

module gOffloadedComponent_2(input clk, input reset,
    output io_in_ready,
    input  io_in_valid,
    input  io_in_bits_centeroidsFinished,
    input  io_in_bits_pointsFinished,
    input [15:0] io_in_bits_centeroidIndex,
    input [63:0] io_in_bits_point_x,
    input [63:0] io_in_bits_point_y,
    input [63:0] io_in_bits_point_z,
    input [4:0] io_in_tag,
    input  io_out_ready,
    output io_out_valid,
    output io_out_bits_centeroidsFinished,
    output io_out_bits_pointsFinished,
    output[15:0] io_out_bits_centeroidIndex,
    output[63:0] io_out_bits_point_x,
    output[63:0] io_out_bits_point_y,
    output[63:0] io_out_bits_point_z,
    output[4:0] io_out_tag,
    input  io_pcIn_valid,
    input  io_pcIn_bits_request,
    input [15:0] io_pcIn_bits_moduleId,
    input [7:0] io_pcIn_bits_portId,
    input [19:0] io_pcIn_bits_pcValue,
    input [3:0] io_pcIn_bits_pcType,
    output io_pcOut_valid,
    output io_pcOut_bits_request,
    output[15:0] io_pcOut_bits_moduleId,
    output[7:0] io_pcOut_bits_portId,
    output[19:0] io_pcOut_bits_pcValue,
    output[3:0] io_pcOut_bits_pcType,
    input  mainOff_add_req_ready,
    output mainOff_add_req_valid,
    output[63:0] mainOff_add_req_bits_in1,
    output[63:0] mainOff_add_req_bits_in2,
    output[4:0] mainOff_add_req_tag,
    output mainOff_add_rep_ready,
    input  mainOff_add_rep_valid,
    input [63:0] mainOff_add_rep_bits_out,
    input [4:0] mainOff_add_rep_tag);

  wire offComp_io_pcOut_bits_request;
  wire mainComp_io_pcOut_bits_request;
  wire[15:0] mainComp_io_pcOut_bits_moduleId;
  wire mainComp_io_pcOut_valid;
  wire[15:0] offComp_io_pcOut_bits_moduleId;
  wire offComp_io_pcOut_valid;
  wire[3:0] offComp_io_pcOut_bits_pcType;
  wire[3:0] mainComp_io_pcOut_bits_pcType;
  wire mainComp_io_out_valid;
  wire mainComp_mainOff_add_rep_ready;
  wire mainComp_mainOff_add_req_valid;
  wire[4:0] mainComp_mainOff_add_req_tag;
  wire[4:0] offComp_io_out_tag;
  wire mainComp_mainOff_partialAccumulatorMem_rep_ready;
  wire mainComp_mainOff_partialAccumulatorMem_req_valid;
  wire[4:0] mainComp_mainOff_partialAccumulatorMem_req_tag;
  wire offComp_io_out_valid;
  wire mainComp_io_in_ready;
  wire offComp_io_in_ready;
  wire[15:0] mainComp_io_out_bits_centeroidIndex;
  wire[7:0] offComp_io_pcOut_bits_portId;
  wire[7:0] mainComp_io_pcOut_bits_portId;
  wire[19:0] offComp_io_pcOut_bits_pcValue;
  wire[19:0] mainComp_io_pcOut_bits_pcValue;
  wire[4:0] mainComp_io_out_tag;

  assign io_pcOut_bits_request = offComp_io_pcOut_bits_request;
  assign io_pcOut_bits_moduleId = offComp_io_pcOut_bits_moduleId;
  assign io_pcOut_valid = offComp_io_pcOut_valid;
  assign io_pcOut_bits_pcType = offComp_io_pcOut_bits_pcType;
  assign io_out_valid = mainComp_io_out_valid;
  assign mainOff_add_rep_ready = mainComp_mainOff_add_rep_ready;
  assign mainOff_add_req_valid = mainComp_mainOff_add_req_valid;
  assign mainOff_add_req_tag = mainComp_mainOff_add_req_tag;
  assign io_in_ready = mainComp_io_in_ready;
  assign io_out_bits_centeroidIndex = mainComp_io_out_bits_centeroidIndex;
  assign io_pcOut_bits_portId = offComp_io_pcOut_bits_portId;
  assign io_pcOut_bits_pcValue = offComp_io_pcOut_bits_pcValue;
  assign io_out_tag = mainComp_io_out_tag;
  gOffloadedComponent_1 mainComp(.clk(clk), .reset(reset),
       .io_in_ready( mainComp_io_in_ready ),
       .io_in_valid( io_in_valid ),
       .io_in_bits_centeroidsFinished( io_in_bits_centeroidsFinished ),
       .io_in_bits_pointsFinished( io_in_bits_pointsFinished ),
       .io_in_bits_centeroidIndex(  ),
       .io_in_bits_point_x(  ),
       .io_in_bits_point_y(  ),
       .io_in_bits_point_z(  ),
       .io_in_tag( io_in_tag ),
       .io_out_ready( io_out_ready ),
       .io_out_valid( mainComp_io_out_valid ),
       .io_out_bits_centeroidsFinished(  ),
       .io_out_bits_pointsFinished(  ),
       .io_out_bits_centeroidIndex( mainComp_io_out_bits_centeroidIndex ),
       .io_out_bits_point_x(  ),
       .io_out_bits_point_y(  ),
       .io_out_bits_point_z(  ),
       .io_out_tag( mainComp_io_out_tag ),
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
       .mainOff_partialAccumulatorMem_req_ready( offComp_io_in_ready ),
       .mainOff_partialAccumulatorMem_req_valid( mainComp_mainOff_partialAccumulatorMem_req_valid ),
       .mainOff_partialAccumulatorMem_req_bits_rw(  ),
       .mainOff_partialAccumulatorMem_req_bits_addr(  ),
       .mainOff_partialAccumulatorMem_req_bits_data_x(  ),
       .mainOff_partialAccumulatorMem_req_bits_data_y(  ),
       .mainOff_partialAccumulatorMem_req_bits_data_z(  ),
       .mainOff_partialAccumulatorMem_req_tag( mainComp_mainOff_partialAccumulatorMem_req_tag ),
       .mainOff_partialAccumulatorMem_rep_ready( mainComp_mainOff_partialAccumulatorMem_rep_ready ),
       .mainOff_partialAccumulatorMem_rep_valid( offComp_io_out_valid ),
       .mainOff_partialAccumulatorMem_rep_bits_data_x(  ),
       .mainOff_partialAccumulatorMem_rep_bits_data_y(  ),
       .mainOff_partialAccumulatorMem_rep_bits_data_z(  ),
       .mainOff_partialAccumulatorMem_rep_tag( offComp_io_out_tag ),
       .mainOff_add_req_ready( mainOff_add_req_ready ),
       .mainOff_add_req_valid( mainComp_mainOff_add_req_valid ),
       .mainOff_add_req_bits_in1(  ),
       .mainOff_add_req_bits_in2(  ),
       .mainOff_add_req_tag( mainComp_mainOff_add_req_tag ),
       .mainOff_add_rep_ready( mainComp_mainOff_add_rep_ready ),
       .mainOff_add_rep_valid( mainOff_add_rep_valid ),
       .mainOff_add_rep_bits_out(  ),
       .mainOff_add_rep_tag( mainOff_add_rep_tag ));
  pAccMem offComp(.clk(clk), .reset(reset),
       .io_in_ready( offComp_io_in_ready ),
       .io_in_valid( mainComp_mainOff_partialAccumulatorMem_req_valid ),
       .io_in_bits_rw(  ),
       .io_in_bits_addr(  ),
       .io_in_bits_data_x(  ),
       .io_in_bits_data_y(  ),
       .io_in_bits_data_z(  ),
       .io_in_tag( mainComp_mainOff_partialAccumulatorMem_req_tag ),
       .io_out_ready( mainComp_mainOff_partialAccumulatorMem_rep_ready ),
       .io_out_valid( offComp_io_out_valid ),
       .io_out_bits_data_x(  ),
       .io_out_bits_data_y(  ),
       .io_out_bits_data_z(  ),
       .io_out_tag( offComp_io_out_tag ),
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

module RREncode_15(
    input  io_valid_0,
    output io_chosen,
    input  io_ready);

  wire choose;

  assign io_chosen = choose;
  assign choose = io_valid_0 ? 1'h0/* 0*/ : 1'h1/* 1*/;
endmodule

module RREncode_16(
    input  io_valid_0,
    output io_chosen,
    input  io_ready);

  wire choose;

  assign io_chosen = choose;
  assign choose = io_valid_0 ? 1'h0/* 0*/ : 1'h1/* 1*/;
endmodule

module RREncode_17(
    input  io_valid_0,
    output io_chosen,
    input  io_ready);

  wire choose;

  assign io_chosen = choose;
  assign choose = io_valid_0 ? 1'h0/* 0*/ : 1'h1/* 1*/;
endmodule

module add(input clk, input reset,
    output io_in_ready,
    input  io_in_valid,
    input [63:0] io_in_bits_in1,
    input [63:0] io_in_bits_in2,
    input [4:0] io_in_tag,
    input  io_out_ready,
    output io_out_valid,
    output[63:0] io_out_bits_out,
    output[4:0] io_out_tag,
    input  io_pcIn_valid,
    input  io_pcIn_bits_request,
    input [15:0] io_pcIn_bits_moduleId,
    input [7:0] io_pcIn_bits_portId,
    input [19:0] io_pcIn_bits_pcValue,
    input [3:0] io_pcIn_bits_pcType,
    output io_pcOut_valid,
    output io_pcOut_bits_request,
    output[15:0] io_pcOut_bits_moduleId,
    output[7:0] io_pcOut_bits_portId,
    output[19:0] io_pcOut_bits_pcValue,
    output[3:0] io_pcOut_bits_pcType);

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
  wire[4:0] T20;
  wire[4:0] T21;
  wire T22;
  wire T23;
  wire[1:0] T24;
  wire rThreadEncoder_io_chosen;
  wire T25;
  reg[0:0] subStateTh_0;
  wire T26;
  wire T27;
  wire T28;
  wire vThreadEncoder_io_chosen;
  wire T29;
  wire AllOffloadsValid_0;
  wire T30;
  wire T31;
  wire T32;
  reg[7:0] State_0;
  wire T33;
  wire T34;
  wire T35;
  wire T36;
  wire[1:0] T37;
  wire T38;
  wire T39;
  wire[7:0] T40;
  wire[7:0] T41;
  wire[7:0] T42;
  wire T43;
  wire[1:0] T44;
  wire T45;
  wire T46;
  wire T47;
  wire T48;
  wire[7:0] T49;
  wire T50;
  wire[1:0] T51;
  wire T52;
  wire T53;
  wire T54;
  wire T55;
  wire[7:0] T56;
  wire T57;
  wire[1:0] T58;
  wire T59;
  wire T60;
  wire T61;
  wire T62;
  wire[7:0] T63;
  wire T64;
  wire[1:0] T65;
  wire T66;
  wire T67;
  wire T68;
  wire T69;
  wire T70;
  wire[7:0] T71;
  wire[7:0] T72;
  wire T73;
  wire[1:0] T74;
  wire T75;
  wire T76;
  wire T77;
  wire[1:0] T78;
  wire sThreadEncoder_io_chosen;
  wire T79;
  wire T80;
  wire T81;
  wire T82;
  wire T83;
  wire[1:0] T84;
  wire[7:0] T85;
  wire[7:0] T86;
  wire[7:0] T87;
  wire[7:0] T88;
  wire[7:0] T89;
  wire[7:0] T90;
  wire[7:0] T91;
  wire[7:0] T92;
  wire[7:0] T93;
  reg[7:0] EmitReturnState_0;
  wire[7:0] T94;
  wire[7:0] T95;
  wire[7:0] T96;
  wire[7:0] T97;
  wire T98;
  wire T99;
  wire T100;
  wire T101;
  wire AllOffloadsReady;
  reg[4:0] inputTag_0;
  wire[4:0] T102;
  wire T103;
  wire T104;
  wire T105;
  wire[1:0] T106;
  wire T107;
  wire[1:0] T108;
  reg[7:0] R109;
  wire T110;
  wire[7:0] T111;
  wire T112;
  reg[19:0] R113;
  wire T114;
  wire[19:0] T115;
  wire[19:0] T116;
  wire[19:0] T117;
  wire[19:0] T118;
  wire[19:0] T119;
  reg[19:0] R120;
  wire T121;
  wire T122;
  wire T123;
  wire T124;
  reg[0:0] R125;
  wire T126;
  wire T127;
  wire T128;
  wire[3:0] T0;
  wire T129;
  wire T130;
  wire T131;
  wire[3:0] T1;
  wire T132;
  wire T133;
  wire T134;
  wire T135;
  wire T136;
  wire T137;
  wire T138;
  wire[19:0] T139;
  wire[19:0] T140;
  wire[19:0] T141;
  wire T142;
  wire T143;
  wire[3:0] T2;
  wire T144;
  wire[19:0] T145;
  reg[19:0] R146;
  wire T147;
  wire T148;
  wire T149;
  wire T150;
  wire T151;
  wire T152;
  wire T153;
  wire[19:0] T154;
  wire[19:0] T155;
  wire[19:0] T156;
  wire T157;
  wire T158;
  wire T159;
  wire[19:0] T160;
  wire[19:0] T161;
  wire T162;
  wire[3:0] T3;

  assign io_pcOut_bits_request = R0;
  assign T1 = T3 || T2;
  assign T2 = ! T3;
  assign T3 = T4 && io_pcIn_bits_request;
  assign T4 = io_pcIn_valid && T5;
  assign T5 = io_pcIn_bits_moduleId == 16'hd/* 13*/;
  assign T6 = T2 ? io_pcIn_bits_request : T7;
  assign T7 = T3 ? 1'h0/* 0*/ : R0;
  assign io_pcOut_bits_moduleId = R8;
  assign T9 = T3 || T2;
  assign T10 = T11 ? io_pcIn_bits_moduleId : R8;
  assign T11 = T3 || T2;
  assign io_pcOut_valid = R12;
  assign T13 = T3 || T2;
  assign T14 = T2 ? io_pcIn_valid : T15;
  assign T15 = T3 ? 1'h1/* 1*/ : R12;
  assign io_pcOut_bits_pcType = R16;
  assign T17 = T3 || T2;
  assign T18 = T19 ? io_pcIn_bits_pcType : R16;
  assign T19 = T3 || T2;
  assign io_out_tag = T20;
  assign T20 = inputTag_0 & T21;
  assign T21 = {3'h5/* 5*/{T22}};
  assign T22 = T23;
  assign T23 = T24[1'h0/* 0*/:1'h0/* 0*/];
  assign T24 = 1'h1/* 1*/ << rThreadEncoder_io_chosen;
  assign T25 = subStateTh_0 == 1'h0/* 0*/;
  assign T26 = T31 ? 1'h1/* 1*/ : T27;
  assign T27 = T28 ? 1'h0/* 0*/ : subStateTh_0;
  assign T28 = 1'h0/* 0*/ == vThreadEncoder_io_chosen;
  assign T29 = T30 && AllOffloadsValid_0;
  assign AllOffloadsValid_0 = 1'h1/* 1*/;
  assign T30 = subStateTh_0 == 1'h1/* 1*/;
  assign T31 = T98 && T32;
  assign T32 = State_0 != 8'hff/* 255*/;
  assign T33 = T45 || T34;
  assign T34 = T38 && T35;
  assign T35 = T36;
  assign T36 = T37[1'h0/* 0*/:1'h0/* 0*/];
  assign T37 = 1'h1/* 1*/ << vThreadEncoder_io_chosen;
  assign T38 = T43 && T39;
  assign T39 = T41 == T40;
  assign T40 = {5'h0/* 0*/, 3'h4/* 4*/};
  assign T41 = State_0 & T42;
  assign T42 = {4'h8/* 8*/{T35}};
  assign T43 = T44 != 2'h1/* 1*/;
  assign T44 = {1'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T45 = T52 || T46;
  assign T46 = T47 && T35;
  assign T47 = T50 && T48;
  assign T48 = T41 == T49;
  assign T49 = {6'h0/* 0*/, 2'h3/* 3*/};
  assign T50 = T51 != 2'h1/* 1*/;
  assign T51 = {1'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T52 = T59 || T53;
  assign T53 = T54 && T35;
  assign T54 = T57 && T55;
  assign T55 = T41 == T56;
  assign T56 = {6'h0/* 0*/, 2'h2/* 2*/};
  assign T57 = T58 != 2'h1/* 1*/;
  assign T58 = {1'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T59 = T66 || T60;
  assign T60 = T61 && T35;
  assign T61 = T64 && T62;
  assign T62 = T41 == T63;
  assign T63 = {7'h0/* 0*/, 1'h1/* 1*/};
  assign T64 = T65 != 2'h1/* 1*/;
  assign T65 = {1'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T66 = T75 || T67;
  assign T67 = T68 && T22;
  assign T68 = T69 && io_out_ready;
  assign T69 = T73 && T70;
  assign T70 = T71 == 8'hff/* 255*/;
  assign T71 = State_0 & T72;
  assign T72 = {4'h8/* 8*/{T22}};
  assign T73 = T74 != 2'h1/* 1*/;
  assign T74 = {1'h0/* 0*/, rThreadEncoder_io_chosen};
  assign T75 = T82 && T76;
  assign T76 = T77;
  assign T77 = T78[1'h0/* 0*/:1'h0/* 0*/];
  assign T78 = 1'h1/* 1*/ << sThreadEncoder_io_chosen;
  assign T79 = T81 && T80;
  assign T80 = State_0 == 8'h0/* 0*/;
  assign T81 = subStateTh_0 == 1'h0/* 0*/;
  assign T82 = T83 && io_in_valid;
  assign T83 = T84 != 2'h1/* 1*/;
  assign T84 = {1'h0/* 0*/, sThreadEncoder_io_chosen};
  assign T85 = T34 ? 8'hff/* 255*/ : T86;
  assign T86 = T46 ? T97 : T87;
  assign T87 = T53 ? T96 : T88;
  assign T88 = T60 ? T95 : T89;
  assign T89 = T67 ? T92 : T90;
  assign T90 = T75 ? T91 : State_0;
  assign T91 = {7'h0/* 0*/, 1'h1/* 1*/};
  assign T92 = EmitReturnState_0 & T93;
  assign T93 = {4'h8/* 8*/{T22}};
  assign T94 = T34 ? 8'h0/* 0*/ : EmitReturnState_0;
  assign T95 = {6'h0/* 0*/, 2'h2/* 2*/};
  assign T96 = {6'h0/* 0*/, 2'h3/* 3*/};
  assign T97 = {5'h0/* 0*/, 3'h4/* 4*/};
  assign T98 = T100 && T99;
  assign T99 = State_0 != 8'h0/* 0*/;
  assign T100 = AllOffloadsReady && T101;
  assign T101 = 1'h0/* 0*/ == rThreadEncoder_io_chosen;
  assign AllOffloadsReady = 1'h1/* 1*/;
  assign T102 = T75 ? io_in_tag : inputTag_0;
  assign io_out_valid = T103;
  assign T103 = T105 && T104;
  assign T104 = T71 == 8'hff/* 255*/;
  assign T105 = T106 != 2'h1/* 1*/;
  assign T106 = {1'h0/* 0*/, rThreadEncoder_io_chosen};
  assign io_in_ready = T107;
  assign T107 = T108 != 2'h1/* 1*/;
  assign T108 = {1'h0/* 0*/, sThreadEncoder_io_chosen};
  assign io_pcOut_bits_portId = R109;
  assign T110 = T3 || T2;
  assign T111 = T112 ? io_pcIn_bits_portId : R109;
  assign T112 = T3 || T2;
  assign io_pcOut_bits_pcValue = R113;
  assign T114 = T3 || T2;
  assign T115 = T2 ? io_pcIn_bits_pcValue : T116;
  assign T116 = T3 ? T117 : R113;
  assign T117 = T162 ? T161 : T118;
  assign T118 = T157 ? R146 : T119;
  assign T119 = T142 ? R120 : 20'h0/* 0*/;
  assign T121 = T130 || T122;
  assign T122 = T138 && T123;
  assign T123 = T136 && T124;
  assign T124 = ! R125;
  assign T126 = T130 || T127;
  assign T127 = T129 && T128;
  assign T128 = io_pcIn_bits_pcType == T0;
  assign T0 = {1'h0/* 0*/, 3'h4/* 4*/};
  assign T129 = io_pcIn_valid && io_pcIn_bits_request;
  assign T130 = T132 && T131;
  assign T131 = io_pcIn_bits_pcType == T1;
  assign T1 = {1'h0/* 0*/, 3'h3/* 3*/};
  assign T132 = io_pcIn_valid && io_pcIn_bits_request;
  assign T133 = T127 ? T135 : T134;
  assign T134 = T130 ? 1'h0/* 0*/ : R125;
  assign T135 = ! R125;
  assign T136 = io_in_valid && T137;
  assign T137 = ! io_in_ready;
  assign T138 = ! T130;
  assign T139 = T122 ? T141 : T140;
  assign T140 = T130 ? 20'h0/* 0*/ : R120;
  assign T141 = R120 + 20'h1/* 1*/;
  assign T142 = T144 && T143;
  assign T143 = io_pcIn_bits_pcType == T2;
  assign T2 = {1'h0/* 0*/, 3'h1/* 1*/};
  assign T144 = T145 == 20'h1/* 1*/;
  assign T145 = {12'h0/* 0*/, io_pcIn_bits_portId};
  assign T147 = T130 || T148;
  assign T148 = T153 && T149;
  assign T149 = T151 && T150;
  assign T150 = ! R125;
  assign T151 = io_out_valid && T152;
  assign T152 = ! io_out_ready;
  assign T153 = ! T130;
  assign T154 = T148 ? T156 : T155;
  assign T155 = T130 ? 20'h0/* 0*/ : R146;
  assign T156 = R146 + 20'h1/* 1*/;
  assign T157 = T159 && T158;
  assign T158 = io_pcIn_bits_pcType == T2;
  assign T159 = T160 == 20'h2/* 2*/;
  assign T160 = {12'h0/* 0*/, io_pcIn_bits_portId};
  assign T161 = {15'h0/* 0*/, 5'h14/* 20*/};
  assign T162 = io_pcIn_bits_pcType == T3;
  assign T3 = {1'h0/* 0*/, 3'h2/* 2*/};
  RREncode_15 rThreadEncoder(
       .io_valid_0( T25 ),
       .io_chosen( rThreadEncoder_io_chosen ),
       .io_ready(  ));
  RREncode_16 vThreadEncoder(
       .io_valid_0( T29 ),
       .io_chosen( vThreadEncoder_io_chosen ),
       .io_ready(  ));
  RREncode_17 sThreadEncoder(
       .io_valid_0( T79 ),
       .io_chosen( sThreadEncoder_io_chosen ),
       .io_ready(  ));

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
    subStateTh_0 <= reset ? 1'h0/* 0*/ : T26;
    if(reset) begin
      State_0 <= 8'h0/* 0*/;
    end else if(T33) begin
      State_0 <= T85;
    end
    if(reset) begin
      EmitReturnState_0 <= 8'h0/* 0*/;
    end else if(T34) begin
      EmitReturnState_0 <= T94;
    end
    if(T75) begin
      inputTag_0 <= T102;
    end
    if(reset) begin
      R109 <= 8'h0/* 0*/;
    end else if(T110) begin
      R109 <= T111;
    end
    if(reset) begin
      R113 <= 20'h0/* 0*/;
    end else if(T114) begin
      R113 <= T115;
    end
    if(reset) begin
      R120 <= 20'h0/* 0*/;
    end else if(T121) begin
      R120 <= T139;
    end
    if(reset) begin
      R125 <= 1'h0/* 0*/;
    end else if(T126) begin
      R125 <= T133;
    end
    if(reset) begin
      R146 <= 20'h0/* 0*/;
    end else if(T147) begin
      R146 <= T154;
    end
  end
endmodule

module gOffloadedComponent_3(input clk, input reset,
    output io_in_ready,
    input  io_in_valid,
    input  io_in_bits_centeroidsFinished,
    input  io_in_bits_pointsFinished,
    input [15:0] io_in_bits_centeroidIndex,
    input [63:0] io_in_bits_point_x,
    input [63:0] io_in_bits_point_y,
    input [63:0] io_in_bits_point_z,
    input [4:0] io_in_tag,
    input  io_out_ready,
    output io_out_valid,
    output io_out_bits_centeroidsFinished,
    output io_out_bits_pointsFinished,
    output[15:0] io_out_bits_centeroidIndex,
    output[63:0] io_out_bits_point_x,
    output[63:0] io_out_bits_point_y,
    output[63:0] io_out_bits_point_z,
    output[4:0] io_out_tag,
    input  pcIn0_valid,
    input  pcIn0_bits_request,
    input [15:0] pcIn0_bits_moduleId,
    input [7:0] pcIn0_bits_portId,
    input [19:0] pcIn0_bits_pcValue,
    input [3:0] pcIn0_bits_pcType,
    output pcOutN_valid,
    output pcOutN_bits_request,
    output[15:0] pcOutN_bits_moduleId,
    output[7:0] pcOutN_bits_portId,
    output[19:0] pcOutN_bits_pcValue,
    output[3:0] pcOutN_bits_pcType);

  wire offComp_io_pcOut_bits_request;
  wire mainComp_io_pcOut_bits_request;
  wire[15:0] mainComp_io_pcOut_bits_moduleId;
  wire mainComp_io_pcOut_valid;
  wire[15:0] offComp_io_pcOut_bits_moduleId;
  wire offComp_io_pcOut_valid;
  wire[3:0] offComp_io_pcOut_bits_pcType;
  wire[3:0] mainComp_io_pcOut_bits_pcType;
  wire mainComp_io_out_valid;
  wire[4:0] offComp_io_out_tag;
  wire mainComp_mainOff_add_rep_ready;
  wire mainComp_mainOff_add_req_valid;
  wire[4:0] mainComp_mainOff_add_req_tag;
  wire offComp_io_out_valid;
  wire mainComp_io_in_ready;
  wire offComp_io_in_ready;
  wire[15:0] mainComp_io_out_bits_centeroidIndex;
  wire[7:0] offComp_io_pcOut_bits_portId;
  wire[7:0] mainComp_io_pcOut_bits_portId;
  wire[19:0] offComp_io_pcOut_bits_pcValue;
  wire[19:0] mainComp_io_pcOut_bits_pcValue;
  wire[4:0] mainComp_io_out_tag;

  assign pcOutN_bits_request = offComp_io_pcOut_bits_request;
  assign pcOutN_bits_moduleId = offComp_io_pcOut_bits_moduleId;
  assign pcOutN_valid = offComp_io_pcOut_valid;
  assign pcOutN_bits_pcType = offComp_io_pcOut_bits_pcType;
  assign io_out_valid = mainComp_io_out_valid;
  assign io_in_ready = mainComp_io_in_ready;
  assign io_out_bits_centeroidIndex = mainComp_io_out_bits_centeroidIndex;
  assign pcOutN_bits_portId = offComp_io_pcOut_bits_portId;
  assign pcOutN_bits_pcValue = offComp_io_pcOut_bits_pcValue;
  assign io_out_tag = mainComp_io_out_tag;
  gOffloadedComponent_2 mainComp(.clk(clk), .reset(reset),
       .io_in_ready( mainComp_io_in_ready ),
       .io_in_valid( io_in_valid ),
       .io_in_bits_centeroidsFinished( io_in_bits_centeroidsFinished ),
       .io_in_bits_pointsFinished( io_in_bits_pointsFinished ),
       .io_in_bits_centeroidIndex(  ),
       .io_in_bits_point_x(  ),
       .io_in_bits_point_y(  ),
       .io_in_bits_point_z(  ),
       .io_in_tag( io_in_tag ),
       .io_out_ready( io_out_ready ),
       .io_out_valid( mainComp_io_out_valid ),
       .io_out_bits_centeroidsFinished(  ),
       .io_out_bits_pointsFinished(  ),
       .io_out_bits_centeroidIndex( mainComp_io_out_bits_centeroidIndex ),
       .io_out_bits_point_x(  ),
       .io_out_bits_point_y(  ),
       .io_out_bits_point_z(  ),
       .io_out_tag( mainComp_io_out_tag ),
       .io_pcIn_valid( pcIn0_valid ),
       .io_pcIn_bits_request( pcIn0_bits_request ),
       .io_pcIn_bits_moduleId( pcIn0_bits_moduleId ),
       .io_pcIn_bits_portId( pcIn0_bits_portId ),
       .io_pcIn_bits_pcValue( pcIn0_bits_pcValue ),
       .io_pcIn_bits_pcType( pcIn0_bits_pcType ),
       .io_pcOut_valid( mainComp_io_pcOut_valid ),
       .io_pcOut_bits_request( mainComp_io_pcOut_bits_request ),
       .io_pcOut_bits_moduleId( mainComp_io_pcOut_bits_moduleId ),
       .io_pcOut_bits_portId( mainComp_io_pcOut_bits_portId ),
       .io_pcOut_bits_pcValue( mainComp_io_pcOut_bits_pcValue ),
       .io_pcOut_bits_pcType( mainComp_io_pcOut_bits_pcType ),
       .mainOff_add_req_ready( offComp_io_in_ready ),
       .mainOff_add_req_valid( mainComp_mainOff_add_req_valid ),
       .mainOff_add_req_bits_in1(  ),
       .mainOff_add_req_bits_in2(  ),
       .mainOff_add_req_tag( mainComp_mainOff_add_req_tag ),
       .mainOff_add_rep_ready( mainComp_mainOff_add_rep_ready ),
       .mainOff_add_rep_valid( offComp_io_out_valid ),
       .mainOff_add_rep_bits_out(  ),
       .mainOff_add_rep_tag( offComp_io_out_tag ));
  add offComp(.clk(clk), .reset(reset),
       .io_in_ready( offComp_io_in_ready ),
       .io_in_valid( mainComp_mainOff_add_req_valid ),
       .io_in_bits_in1(  ),
       .io_in_bits_in2(  ),
       .io_in_tag( mainComp_mainOff_add_req_tag ),
       .io_out_ready( mainComp_mainOff_add_rep_ready ),
       .io_out_valid( offComp_io_out_valid ),
       .io_out_bits_out(  ),
       .io_out_tag( offComp_io_out_tag ),
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

module gRRDistributor(input clk, input reset,
    input  io_out_0_ready,
    output io_out_0_valid,
    output io_out_0_bits_centeroidsFinished,
    output io_out_0_bits_pointsFinished,
    output[15:0] io_out_0_bits_centeroidIndex,
    output[63:0] io_out_0_bits_point_x,
    output[63:0] io_out_0_bits_point_y,
    output[63:0] io_out_0_bits_point_z,
    output[4:0] io_out_0_tag,
    output io_in_ready,
    input  io_in_valid,
    input  io_in_bits_centeroidsFinished,
    input  io_in_bits_pointsFinished,
    input [15:0] io_in_bits_centeroidIndex,
    input [63:0] io_in_bits_point_x,
    input [63:0] io_in_bits_point_y,
    input [63:0] io_in_bits_point_z,
    input [4:0] io_in_tag,
    output io_chosen);

  wire T0;
  wire T1;
  wire T2;
  wire T3;
  wire T4;
  reg[0:0] last_grant;
  wire T5;
  wire T6;
  wire T7;

  assign io_out_0_valid = T0;
  assign T0 = T1 && io_in_valid;
  assign io_in_ready = io_out_0_ready;
  assign T1 = T7 || T2;
  assign T2 = ! T3;
  assign T3 = io_out_0_ready && T4;
  assign T4 = 1'h0/* 0*/ > last_grant;
  assign T5 = io_in_valid && io_in_ready;
  assign T6 = T5 ? 1'h0/* 0*/ : last_grant;
  assign T7 = 1'h0/* 0*/ > last_grant;
  assign io_out_0_bits_pointsFinished = io_in_bits_pointsFinished;
  assign io_out_0_bits_centeroidsFinished = io_in_bits_centeroidsFinished;
  assign io_out_0_tag = io_in_tag;

  always @(posedge clk) begin
    if(reset) begin
      last_grant <= 1'h0/* 0*/;
    end else if(T5) begin
      last_grant <= T6;
    end
  end
endmodule

module RRDistributorComponent(input clk, input reset,
    input  io_out_0_ready,
    output io_out_0_valid,
    output io_out_0_bits_centeroidsFinished,
    output io_out_0_bits_pointsFinished,
    output[15:0] io_out_0_bits_centeroidIndex,
    output[63:0] io_out_0_bits_point_x,
    output[63:0] io_out_0_bits_point_y,
    output[63:0] io_out_0_bits_point_z,
    output[4:0] io_out_0_tag,
    output io_in_ready,
    input  io_in_valid,
    input  io_in_bits_centeroidsFinished,
    input  io_in_bits_pointsFinished,
    input [15:0] io_in_bits_centeroidIndex,
    input [63:0] io_in_bits_point_x,
    input [63:0] io_in_bits_point_y,
    input [63:0] io_in_bits_point_z,
    input [4:0] io_in_tag,
    output io_chosen);

  wire rrDist_io_out_0_valid;
  wire rrDist_io_in_ready;
  wire rrDist_io_out_0_bits_pointsFinished;
  wire rrDist_io_out_0_bits_centeroidsFinished;
  wire[4:0] rrDist_io_out_0_tag;

  assign io_out_0_valid = rrDist_io_out_0_valid;
  assign io_in_ready = rrDist_io_in_ready;
  assign io_out_0_bits_pointsFinished = rrDist_io_out_0_bits_pointsFinished;
  assign io_out_0_bits_centeroidsFinished = rrDist_io_out_0_bits_centeroidsFinished;
  assign io_out_0_tag = rrDist_io_out_0_tag;
  gRRDistributor rrDist(.clk(clk), .reset(reset),
       .io_out_0_ready( io_out_0_ready ),
       .io_out_0_valid( rrDist_io_out_0_valid ),
       .io_out_0_bits_centeroidsFinished( rrDist_io_out_0_bits_centeroidsFinished ),
       .io_out_0_bits_pointsFinished( rrDist_io_out_0_bits_pointsFinished ),
       .io_out_0_bits_centeroidIndex(  ),
       .io_out_0_bits_point_x(  ),
       .io_out_0_bits_point_y(  ),
       .io_out_0_bits_point_z(  ),
       .io_out_0_tag( rrDist_io_out_0_tag ),
       .io_in_ready( rrDist_io_in_ready ),
       .io_in_valid( io_in_valid ),
       .io_in_bits_centeroidsFinished( io_in_bits_centeroidsFinished ),
       .io_in_bits_pointsFinished( io_in_bits_pointsFinished ),
       .io_in_bits_centeroidIndex(  ),
       .io_in_bits_point_x(  ),
       .io_in_bits_point_y(  ),
       .io_in_bits_point_z(  ),
       .io_in_tag( io_in_tag ),
       .io_chosen(  ));
endmodule

module gRRArbiter(input clk, input reset,
    input  io_out_ready,
    output io_out_valid,
    output io_out_bits_centeroidsFinished,
    output io_out_bits_pointsFinished,
    output[15:0] io_out_bits_centeroidIndex,
    output[63:0] io_out_bits_point_x,
    output[63:0] io_out_bits_point_y,
    output[63:0] io_out_bits_point_z,
    output[4:0] io_out_tag,
    output io_in_0_ready,
    input  io_in_0_valid,
    input  io_in_0_bits_centeroidsFinished,
    input  io_in_0_bits_pointsFinished,
    input [15:0] io_in_0_bits_centeroidIndex,
    input [63:0] io_in_0_bits_point_x,
    input [63:0] io_in_0_bits_point_y,
    input [63:0] io_in_0_bits_point_z,
    input [4:0] io_in_0_tag,
    output io_chosen);

  wire T0;
  wire T1;
  wire T2;
  wire T3;
  wire T4;
  reg[0:0] last_grant;
  wire T5;
  wire T6;
  wire T7;
  wire[15:0] T8;
  wire[15:0] dvec_0_centeroidIndex;
  wire[4:0] T9;
  wire[6:0] T10;
  wire[6:0] tvec_0;
  wire[6:0] T11;

  assign io_out_valid = io_in_0_valid;
  assign io_in_0_ready = T0;
  assign T0 = T1 && io_out_ready;
  assign T1 = T7 || T2;
  assign T2 = ! T3;
  assign T3 = io_in_0_valid && T4;
  assign T4 = 1'h0/* 0*/ > last_grant;
  assign T5 = io_out_valid && io_out_ready;
  assign T6 = T5 ? 1'h0/* 0*/ : last_grant;
  assign T7 = 1'h0/* 0*/ > last_grant;
  assign io_out_bits_centeroidIndex = T8;
  assign T8 = dvec_0_centeroidIndex & 16'hffff/* 65535*/;
  assign dvec_0_centeroidIndex = io_in_0_bits_centeroidIndex;
  assign io_out_tag = T9;
  assign T9 = T10[3'h4/* 4*/:1'h0/* 0*/];
  assign T10 = tvec_0 & 7'h7f/* 127*/;
  assign tvec_0 = T11;
  assign T11 = {2'h0/* 0*/, io_in_0_tag};

  always @(posedge clk) begin
    if(reset) begin
      last_grant <= 1'h0/* 0*/;
    end else if(T5) begin
      last_grant <= T6;
    end
  end
endmodule

module RRAggregatorComponent(input clk, input reset,
    input  io_out_ready,
    output io_out_valid,
    output io_out_bits_centeroidsFinished,
    output io_out_bits_pointsFinished,
    output[15:0] io_out_bits_centeroidIndex,
    output[63:0] io_out_bits_point_x,
    output[63:0] io_out_bits_point_y,
    output[63:0] io_out_bits_point_z,
    output[4:0] io_out_tag,
    output io_in_0_ready,
    input  io_in_0_valid,
    input  io_in_0_bits_centeroidsFinished,
    input  io_in_0_bits_pointsFinished,
    input [15:0] io_in_0_bits_centeroidIndex,
    input [63:0] io_in_0_bits_point_x,
    input [63:0] io_in_0_bits_point_y,
    input [63:0] io_in_0_bits_point_z,
    input [4:0] io_in_0_tag,
    output io_chosen);

  wire rrArb_io_out_valid;
  wire rrArb_io_in_0_ready;
  wire[15:0] rrArb_io_out_bits_centeroidIndex;
  wire[4:0] rrArb_io_out_tag;

  assign io_out_valid = rrArb_io_out_valid;
  assign io_in_0_ready = rrArb_io_in_0_ready;
  assign io_out_bits_centeroidIndex = rrArb_io_out_bits_centeroidIndex;
  assign io_out_tag = rrArb_io_out_tag;
  gRRArbiter rrArb(.clk(clk), .reset(reset),
       .io_out_ready( io_out_ready ),
       .io_out_valid( rrArb_io_out_valid ),
       .io_out_bits_centeroidsFinished(  ),
       .io_out_bits_pointsFinished(  ),
       .io_out_bits_centeroidIndex( rrArb_io_out_bits_centeroidIndex ),
       .io_out_bits_point_x(  ),
       .io_out_bits_point_y(  ),
       .io_out_bits_point_z(  ),
       .io_out_tag( rrArb_io_out_tag ),
       .io_in_0_ready( rrArb_io_in_0_ready ),
       .io_in_0_valid( io_in_0_valid ),
       .io_in_0_bits_centeroidsFinished(  ),
       .io_in_0_bits_pointsFinished(  ),
       .io_in_0_bits_centeroidIndex( io_in_0_bits_centeroidIndex ),
       .io_in_0_bits_point_x(  ),
       .io_in_0_bits_point_y(  ),
       .io_in_0_bits_point_z(  ),
       .io_in_0_tag( io_in_0_tag ),
       .io_chosen(  ));
endmodule

module gReplicatedComponent(input clk, input reset,
    output io_in_ready,
    input  io_in_valid,
    input  io_in_bits_centeroidsFinished,
    input  io_in_bits_pointsFinished,
    input [15:0] io_in_bits_centeroidIndex,
    input [63:0] io_in_bits_point_x,
    input [63:0] io_in_bits_point_y,
    input [63:0] io_in_bits_point_z,
    input [4:0] io_in_tag,
    input  io_out_ready,
    output io_out_valid,
    output io_out_bits_centeroidsFinished,
    output io_out_bits_pointsFinished,
    output[15:0] io_out_bits_centeroidIndex,
    output[63:0] io_out_bits_point_x,
    output[63:0] io_out_bits_point_y,
    output[63:0] io_out_bits_point_z,
    output[4:0] io_out_tag,
    input  io_pcIn_valid,
    input  io_pcIn_bits_request,
    input [15:0] io_pcIn_bits_moduleId,
    input [7:0] io_pcIn_bits_portId,
    input [19:0] io_pcIn_bits_pcValue,
    input [3:0] io_pcIn_bits_pcType,
    output io_pcOut_valid,
    output io_pcOut_bits_request,
    output[15:0] io_pcOut_bits_moduleId,
    output[7:0] io_pcOut_bits_portId,
    output[19:0] io_pcOut_bits_pcValue,
    output[3:0] io_pcOut_bits_pcType);

  wire gOffloadedComponent_pcOutN_bits_request;
  wire[15:0] gOffloadedComponent_pcOutN_bits_moduleId;
  wire gOffloadedComponent_pcOutN_valid;
  wire[3:0] gOffloadedComponent_pcOutN_bits_pcType;
  wire outputArb_io_out_valid;
  wire gOffloadedComponent_io_out_valid;
  wire inputDist_io_out_0_valid;
  wire inputDist_io_in_ready;
  wire gOffloadedComponent_io_in_ready;
  wire inputDist_io_out_0_bits_pointsFinished;
  wire inputDist_io_out_0_bits_centeroidsFinished;
  wire outputArb_io_in_0_ready;
  wire[15:0] outputArb_io_out_bits_centeroidIndex;
  wire[15:0] gOffloadedComponent_io_out_bits_centeroidIndex;
  wire[7:0] gOffloadedComponent_pcOutN_bits_portId;
  wire[19:0] gOffloadedComponent_pcOutN_bits_pcValue;
  wire[4:0] outputArb_io_out_tag;
  wire[4:0] gOffloadedComponent_io_out_tag;
  wire[4:0] inputDist_io_out_0_tag;

  assign io_pcOut_bits_request = gOffloadedComponent_pcOutN_bits_request;
  assign io_pcOut_bits_moduleId = gOffloadedComponent_pcOutN_bits_moduleId;
  assign io_pcOut_valid = gOffloadedComponent_pcOutN_valid;
  assign io_pcOut_bits_pcType = gOffloadedComponent_pcOutN_bits_pcType;
  assign io_out_valid = outputArb_io_out_valid;
  assign io_in_ready = inputDist_io_in_ready;
  assign io_out_bits_centeroidIndex = outputArb_io_out_bits_centeroidIndex;
  assign io_pcOut_bits_portId = gOffloadedComponent_pcOutN_bits_portId;
  assign io_pcOut_bits_pcValue = gOffloadedComponent_pcOutN_bits_pcValue;
  assign io_out_tag = outputArb_io_out_tag;
  gOffloadedComponent_3 gOffloadedComponent(.clk(clk), .reset(reset),
       .io_in_ready( gOffloadedComponent_io_in_ready ),
       .io_in_valid( inputDist_io_out_0_valid ),
       .io_in_bits_centeroidsFinished( inputDist_io_out_0_bits_centeroidsFinished ),
       .io_in_bits_pointsFinished( inputDist_io_out_0_bits_pointsFinished ),
       .io_in_bits_centeroidIndex(  ),
       .io_in_bits_point_x(  ),
       .io_in_bits_point_y(  ),
       .io_in_bits_point_z(  ),
       .io_in_tag( inputDist_io_out_0_tag ),
       .io_out_ready( outputArb_io_in_0_ready ),
       .io_out_valid( gOffloadedComponent_io_out_valid ),
       .io_out_bits_centeroidsFinished(  ),
       .io_out_bits_pointsFinished(  ),
       .io_out_bits_centeroidIndex( gOffloadedComponent_io_out_bits_centeroidIndex ),
       .io_out_bits_point_x(  ),
       .io_out_bits_point_y(  ),
       .io_out_bits_point_z(  ),
       .io_out_tag( gOffloadedComponent_io_out_tag ),
       .pcIn0_valid( io_pcIn_valid ),
       .pcIn0_bits_request( io_pcIn_bits_request ),
       .pcIn0_bits_moduleId( io_pcIn_bits_moduleId ),
       .pcIn0_bits_portId( io_pcIn_bits_portId ),
       .pcIn0_bits_pcValue( io_pcIn_bits_pcValue ),
       .pcIn0_bits_pcType( io_pcIn_bits_pcType ),
       .pcOutN_valid( gOffloadedComponent_pcOutN_valid ),
       .pcOutN_bits_request( gOffloadedComponent_pcOutN_bits_request ),
       .pcOutN_bits_moduleId( gOffloadedComponent_pcOutN_bits_moduleId ),
       .pcOutN_bits_portId( gOffloadedComponent_pcOutN_bits_portId ),
       .pcOutN_bits_pcValue( gOffloadedComponent_pcOutN_bits_pcValue ),
       .pcOutN_bits_pcType( gOffloadedComponent_pcOutN_bits_pcType ));
  RRDistributorComponent inputDist(.clk(clk), .reset(reset),
       .io_out_0_ready( gOffloadedComponent_io_in_ready ),
       .io_out_0_valid( inputDist_io_out_0_valid ),
       .io_out_0_bits_centeroidsFinished( inputDist_io_out_0_bits_centeroidsFinished ),
       .io_out_0_bits_pointsFinished( inputDist_io_out_0_bits_pointsFinished ),
       .io_out_0_bits_centeroidIndex(  ),
       .io_out_0_bits_point_x(  ),
       .io_out_0_bits_point_y(  ),
       .io_out_0_bits_point_z(  ),
       .io_out_0_tag( inputDist_io_out_0_tag ),
       .io_in_ready( inputDist_io_in_ready ),
       .io_in_valid( io_in_valid ),
       .io_in_bits_centeroidsFinished( io_in_bits_centeroidsFinished ),
       .io_in_bits_pointsFinished( io_in_bits_pointsFinished ),
       .io_in_bits_centeroidIndex(  ),
       .io_in_bits_point_x(  ),
       .io_in_bits_point_y(  ),
       .io_in_bits_point_z(  ),
       .io_in_tag( io_in_tag ),
       .io_chosen(  ));
  RRAggregatorComponent outputArb(.clk(clk), .reset(reset),
       .io_out_ready( io_out_ready ),
       .io_out_valid( outputArb_io_out_valid ),
       .io_out_bits_centeroidsFinished(  ),
       .io_out_bits_pointsFinished(  ),
       .io_out_bits_centeroidIndex( outputArb_io_out_bits_centeroidIndex ),
       .io_out_bits_point_x(  ),
       .io_out_bits_point_y(  ),
       .io_out_bits_point_z(  ),
       .io_out_tag( outputArb_io_out_tag ),
       .io_in_0_ready( outputArb_io_in_0_ready ),
       .io_in_0_valid( gOffloadedComponent_io_out_valid ),
       .io_in_0_bits_centeroidsFinished(  ),
       .io_in_0_bits_pointsFinished(  ),
       .io_in_0_bits_centeroidIndex( gOffloadedComponent_io_out_bits_centeroidIndex ),
       .io_in_0_bits_point_x(  ),
       .io_in_0_bits_point_y(  ),
       .io_in_0_bits_point_z(  ),
       .io_in_0_tag( gOffloadedComponent_io_out_tag ),
       .io_chosen(  ));
endmodule

module gChainedComponent(input clk, input reset,
    output io_in_ready,
    input  io_in_valid,
    input  io_in_bits_centeroidsFinished,
    input  io_in_bits_pointsFinished,
    input [15:0] io_in_bits_centeroidIndex,
    input [63:0] io_in_bits_point_x,
    input [63:0] io_in_bits_point_y,
    input [63:0] io_in_bits_point_z,
    input [4:0] io_in_tag,
    input  io_out_ready,
    output io_out_valid,
    output io_out_bits_centeroidsFinished,
    output io_out_bits_pointsFinished,
    output[15:0] io_out_bits_centeroidIndex,
    output[63:0] io_out_bits_point_x,
    output[63:0] io_out_bits_point_y,
    output[63:0] io_out_bits_point_z,
    output[4:0] io_out_tag,
    input  io_pcIn_valid,
    input  io_pcIn_bits_request,
    input [15:0] io_pcIn_bits_moduleId,
    input [7:0] io_pcIn_bits_portId,
    input [19:0] io_pcIn_bits_pcValue,
    input [3:0] io_pcIn_bits_pcType,
    output io_pcOut_valid,
    output io_pcOut_bits_request,
    output[15:0] io_pcOut_bits_moduleId,
    output[7:0] io_pcOut_bits_portId,
    output[19:0] io_pcOut_bits_pcValue,
    output[3:0] io_pcOut_bits_pcType);

  wire sink_io_pcOut_bits_request;
  wire source_io_pcOut_bits_request;
  wire[15:0] source_io_pcOut_bits_moduleId;
  wire source_io_pcOut_valid;
  wire[15:0] sink_io_pcOut_bits_moduleId;
  wire sink_io_pcOut_valid;
  wire[3:0] sink_io_pcOut_bits_pcType;
  wire[3:0] source_io_pcOut_bits_pcType;
  wire sink_io_out_valid;
  wire source_io_out_valid;
  wire sink_io_in_ready;
  wire source_io_out_bits_pointsFinished;
  wire source_io_out_bits_centeroidsFinished;
  wire[15:0] sink_io_out_bits_centeroidIndex;
  wire[7:0] sink_io_pcOut_bits_portId;
  wire[7:0] source_io_pcOut_bits_portId;
  wire[19:0] sink_io_pcOut_bits_pcValue;
  wire[19:0] source_io_pcOut_bits_pcValue;
  wire[4:0] sink_io_out_tag;
  wire[4:0] source_io_out_tag;
  wire source_io_in_ready;

  assign io_pcOut_bits_request = sink_io_pcOut_bits_request;
  assign io_pcOut_bits_moduleId = sink_io_pcOut_bits_moduleId;
  assign io_pcOut_valid = sink_io_pcOut_valid;
  assign io_pcOut_bits_pcType = sink_io_pcOut_bits_pcType;
  assign io_out_valid = sink_io_out_valid;
  assign io_out_bits_centeroidIndex = sink_io_out_bits_centeroidIndex;
  assign io_pcOut_bits_portId = sink_io_pcOut_bits_portId;
  assign io_pcOut_bits_pcValue = sink_io_pcOut_bits_pcValue;
  assign io_out_tag = sink_io_out_tag;
  assign io_in_ready = source_io_in_ready;
  KDistribute source(.clk(clk), .reset(reset),
       .io_in_ready( source_io_in_ready ),
       .io_in_valid( io_in_valid ),
       .io_in_bits_centeroidsFinished( io_in_bits_centeroidsFinished ),
       .io_in_bits_pointsFinished( io_in_bits_pointsFinished ),
       .io_in_bits_centeroidIndex(  ),
       .io_in_bits_point_x(  ),
       .io_in_bits_point_y(  ),
       .io_in_bits_point_z(  ),
       .io_in_tag( io_in_tag ),
       .io_out_ready( sink_io_in_ready ),
       .io_out_valid( source_io_out_valid ),
       .io_out_bits_centeroidsFinished( source_io_out_bits_centeroidsFinished ),
       .io_out_bits_pointsFinished( source_io_out_bits_pointsFinished ),
       .io_out_bits_centeroidIndex(  ),
       .io_out_bits_point_x(  ),
       .io_out_bits_point_y(  ),
       .io_out_bits_point_z(  ),
       .io_out_tag( source_io_out_tag ),
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
       .io_pcOut_bits_pcType( source_io_pcOut_bits_pcType ));
  gReplicatedComponent sink(.clk(clk), .reset(reset),
       .io_in_ready( sink_io_in_ready ),
       .io_in_valid( source_io_out_valid ),
       .io_in_bits_centeroidsFinished( source_io_out_bits_centeroidsFinished ),
       .io_in_bits_pointsFinished( source_io_out_bits_pointsFinished ),
       .io_in_bits_centeroidIndex(  ),
       .io_in_bits_point_x(  ),
       .io_in_bits_point_y(  ),
       .io_in_bits_point_z(  ),
       .io_in_tag( source_io_out_tag ),
       .io_out_ready( io_out_ready ),
       .io_out_valid( sink_io_out_valid ),
       .io_out_bits_centeroidsFinished(  ),
       .io_out_bits_pointsFinished(  ),
       .io_out_bits_centeroidIndex( sink_io_out_bits_centeroidIndex ),
       .io_out_bits_point_x(  ),
       .io_out_bits_point_y(  ),
       .io_out_bits_point_z(  ),
       .io_out_tag( sink_io_out_tag ),
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
       .io_pcOut_bits_pcType( sink_io_pcOut_bits_pcType ));
endmodule

module RREncode_18(
    input  io_valid_0,
    output io_chosen,
    input  io_ready);

  wire choose;

  assign io_chosen = choose;
  assign choose = io_valid_0 ? 1'h0/* 0*/ : 1'h1/* 1*/;
endmodule

module RREncode_19(
    input  io_valid_0,
    output io_chosen,
    input  io_ready);

  wire choose;

  assign io_chosen = choose;
  assign choose = io_valid_0 ? 1'h0/* 0*/ : 1'h1/* 1*/;
endmodule

module RREncode_20(
    input  io_valid_0,
    output io_chosen,
    input  io_ready);

  wire choose;

  assign io_chosen = choose;
  assign choose = io_valid_0 ? 1'h0/* 0*/ : 1'h1/* 1*/;
endmodule

module KReduce(input clk, input reset,
    output io_in_ready,
    input  io_in_valid,
    input  io_in_bits_centeroidsFinished,
    input  io_in_bits_pointsFinished,
    input [15:0] io_in_bits_centeroidIndex,
    input [63:0] io_in_bits_point_x,
    input [63:0] io_in_bits_point_y,
    input [63:0] io_in_bits_point_z,
    input [4:0] io_in_tag,
    input  io_out_ready,
    output io_out_valid,
    output io_out_bits_centeroidsFinished,
    output io_out_bits_pointsFinished,
    output[15:0] io_out_bits_centeroidIndex,
    output[63:0] io_out_bits_point_x,
    output[63:0] io_out_bits_point_y,
    output[63:0] io_out_bits_point_z,
    output[4:0] io_out_tag,
    input  io_pcIn_valid,
    input  io_pcIn_bits_request,
    input [15:0] io_pcIn_bits_moduleId,
    input [7:0] io_pcIn_bits_portId,
    input [19:0] io_pcIn_bits_pcValue,
    input [3:0] io_pcIn_bits_pcType,
    output io_pcOut_valid,
    output io_pcOut_bits_request,
    output[15:0] io_pcOut_bits_moduleId,
    output[7:0] io_pcOut_bits_portId,
    output[19:0] io_pcOut_bits_pcValue,
    output[3:0] io_pcOut_bits_pcType,
    input  mainOff_partialAccumulatorMem_req_ready,
    output mainOff_partialAccumulatorMem_req_valid,
    output mainOff_partialAccumulatorMem_req_bits_rw,
    output[15:0] mainOff_partialAccumulatorMem_req_bits_addr,
    output[63:0] mainOff_partialAccumulatorMem_req_bits_data_x,
    output[63:0] mainOff_partialAccumulatorMem_req_bits_data_y,
    output[63:0] mainOff_partialAccumulatorMem_req_bits_data_z,
    output[4:0] mainOff_partialAccumulatorMem_req_tag,
    output mainOff_partialAccumulatorMem_rep_ready,
    input  mainOff_partialAccumulatorMem_rep_valid,
    input [63:0] mainOff_partialAccumulatorMem_rep_bits_data_x,
    input [63:0] mainOff_partialAccumulatorMem_rep_bits_data_y,
    input [63:0] mainOff_partialAccumulatorMem_rep_bits_data_z,
    input [4:0] mainOff_partialAccumulatorMem_rep_tag,
    input  mainOff_add_req_ready,
    output mainOff_add_req_valid,
    output[63:0] mainOff_add_req_bits_in1,
    output[63:0] mainOff_add_req_bits_in2,
    output[4:0] mainOff_add_req_tag,
    output mainOff_add_rep_ready,
    input  mainOff_add_rep_valid,
    input [63:0] mainOff_add_rep_bits_out,
    input [4:0] mainOff_add_rep_tag);

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
  wire addPort_rep_ready;
  wire addPort_req_valid;
  wire T20;
  wire T21;
  wire T22;
  wire T23;
  wire[7:0] T24;
  wire[7:0] T25;
  wire[7:0] T26;
  wire T27;
  wire T28;
  wire[1:0] T29;
  wire rThreadEncoder_io_chosen;
  wire T30;
  reg[0:0] subStateTh_0;
  wire T31;
  wire T32;
  wire T33;
  wire vThreadEncoder_io_chosen;
  wire T34;
  wire AllOffloadsValid_0;
  wire T35;
  wire T36;
  wire T37;
  reg[0:0] addPortHadValidRequest_0;
  wire T38;
  wire T39;
  wire T40;
  wire T41;
  wire[4:0] T42;
  wire T43;
  wire T44;
  wire[4:0] T45;
  wire T46;
  reg[0:0] add_valid_received_0;
  wire T47;
  wire T48;
  wire T49;
  wire T50;
  wire[4:0] addPort_rep_tag;
  wire[4:0] addPort_req_tag;
  wire[4:0] T51;
  wire addPort_rep_valid;
  wire T52;
  wire T53;
  wire[4:0] T54;
  wire T55;
  wire T56;
  wire T57;
  wire T58;
  reg[0:0] partialAccumulatorMemPortHadValidRequest_0;
  wire T59;
  wire T60;
  wire T61;
  wire partialAccumulatorMemPort_req_valid;
  wire T62;
  wire T63;
  wire T64;
  wire T65;
  wire[7:0] T66;
  wire T67;
  wire[1:0] T68;
  wire T69;
  wire T70;
  wire T71;
  wire[7:0] T72;
  wire T73;
  wire[1:0] T74;
  wire T75;
  wire T76;
  wire[7:0] T77;
  wire T78;
  wire[1:0] T79;
  wire T80;
  wire T81;
  wire T82;
  reg[0:0] partialAccumulatorMem_valid_received_0;
  wire T83;
  wire T84;
  wire T85;
  wire T86;
  wire[4:0] partialAccumulatorMemPort_rep_tag;
  wire partialAccumulatorMemPort_rep_ready;
  wire[4:0] partialAccumulatorMemPort_req_tag;
  wire[4:0] T87;
  wire partialAccumulatorMemPort_rep_valid;
  wire T88;
  wire T89;
  wire[4:0] T90;
  wire T91;
  wire[1:0] T92;
  wire T93;
  wire[4:0] T94;
  wire T95;
  wire T96;
  wire[4:0] T97;
  wire T98;
  wire T99;
  wire T100;
  wire T101;
  wire T102;
  wire T103;
  reg[7:0] State_0;
  wire T104;
  wire T105;
  wire T106;
  wire T107;
  wire[1:0] T108;
  wire T109;
  wire T110;
  wire[7:0] T111;
  wire[7:0] T112;
  wire[7:0] T113;
  wire T114;
  wire[1:0] T115;
  wire T116;
  wire T117;
  wire T118;
  wire T119;
  wire T120;
  wire[32:0] T121;
  wire[15:0] T122;
  wire[15:0] T123;
  reg[15:0] inputReg_0_centeroidIndex;
  wire T124;
  wire T125;
  wire T126;
  wire[1:0] T127;
  wire sThreadEncoder_io_chosen;
  wire T128;
  wire T129;
  wire T130;
  wire T131;
  wire T132;
  wire[1:0] T133;
  wire T134;
  wire[1:0] T135;
  wire[15:0] T136;
  wire T137;
  wire T138;
  wire[7:0] T139;
  wire T140;
  wire[1:0] T141;
  wire T142;
  wire T143;
  wire T144;
  wire T145;
  wire T146;
  wire T147;
  wire T148;
  wire[7:0] T149;
  wire T150;
  wire[1:0] T151;
  wire T152;
  wire T153;
  wire T154;
  wire T155;
  wire[7:0] T156;
  wire T157;
  wire[1:0] T158;
  wire T159;
  wire T160;
  wire T161;
  wire T162;
  wire[7:0] T163;
  wire T164;
  wire[1:0] T165;
  wire T166;
  wire T167;
  wire T168;
  wire T169;
  wire[7:0] T170;
  wire T171;
  wire[1:0] T172;
  wire T173;
  wire T174;
  wire T175;
  wire T176;
  wire T177;
  wire T178;
  wire[1:0] T179;
  wire[7:0] T180;
  wire[7:0] T181;
  wire[7:0] T182;
  wire[7:0] T183;
  wire[7:0] T184;
  wire[7:0] T185;
  wire[7:0] T186;
  wire[7:0] T187;
  wire[7:0] T188;
  wire[7:0] T189;
  wire[7:0] T190;
  wire[7:0] T191;
  reg[7:0] EmitReturnState_0;
  wire T192;
  wire T193;
  wire T194;
  wire T195;
  wire T196;
  wire[31:0] T197;
  wire[31:0] T198;
  reg[31:0] centeroidIndex_0;
  wire T199;
  wire T200;
  wire T201;
  wire T202;
  wire[31:0] T203;
  wire[31:0] T204;
  wire[31:0] T205;
  wire[31:0] T206;
  wire[7:0] T207;
  wire[7:0] T208;
  wire[7:0] T209;
  wire[7:0] T210;
  wire[7:0] T211;
  wire[7:0] T212;
  wire[7:0] T213;
  wire T214;
  wire T215;
  wire T216;
  wire T217;
  wire AllOffloadsReady;
  wire T218;
  wire T219;
  wire T220;
  wire T221;
  wire T222;
  reg[0:0] addPortHadReadyRequest;
  wire T223;
  wire T224;
  wire T225;
  wire T226;
  reg[0:0] add_ready_received;
  wire T227;
  wire T228;
  wire addPort_req_ready;
  wire T229;
  wire T230;
  wire T231;
  wire T232;
  wire T233;
  reg[0:0] partialAccumulatorMemPortHadReadyRequest;
  wire T234;
  wire T235;
  wire T236;
  wire T237;
  reg[0:0] partialAccumulatorMem_ready_received;
  wire T238;
  wire T239;
  wire partialAccumulatorMemPort_req_ready;
  wire T240;
  wire T241;
  wire[1:0] T242;
  wire T243;
  wire T244;
  wire T245;
  wire[7:0] T246;
  wire T247;
  wire[1:0] T248;
  wire T249;
  wire T250;
  wire[7:0] T251;
  wire T252;
  wire[1:0] T253;
  wire T254;
  wire T255;
  wire T256;
  wire T257;
  wire[1:0] T258;
  reg[7:0] R259;
  wire T260;
  wire[7:0] T261;
  wire T262;
  reg[19:0] R263;
  wire T264;
  wire[19:0] T265;
  wire[19:0] T266;
  wire[19:0] T267;
  wire[19:0] T268;
  wire[19:0] T269;
  wire[19:0] T270;
  wire[19:0] T271;
  reg[19:0] R272;
  wire T273;
  wire T274;
  wire T275;
  wire T276;
  reg[0:0] R277;
  wire T278;
  wire T279;
  wire T280;
  wire[3:0] T0;
  wire T281;
  wire T282;
  wire T283;
  wire[3:0] T1;
  wire T284;
  wire T285;
  wire T286;
  wire T287;
  wire T288;
  wire T289;
  wire T290;
  wire[19:0] T291;
  wire[19:0] T292;
  wire[19:0] T293;
  wire T294;
  wire T295;
  wire[3:0] T2;
  wire T296;
  wire[19:0] T297;
  reg[19:0] R298;
  wire T299;
  wire T300;
  wire T301;
  wire T302;
  wire T303;
  wire T304;
  wire T305;
  wire T306;
  wire T307;
  wire[1:0] T308;
  wire T309;
  wire[19:0] T310;
  wire[19:0] T311;
  wire[19:0] T312;
  wire T313;
  wire T314;
  wire T315;
  wire[19:0] T316;
  reg[19:0] R317;
  wire T318;
  wire T319;
  wire T320;
  wire T321;
  wire T322;
  wire T323;
  wire T324;
  wire[19:0] T325;
  wire[19:0] T326;
  wire[19:0] T327;
  wire T328;
  wire T329;
  wire T330;
  wire[19:0] T331;
  reg[19:0] R332;
  wire T333;
  wire T334;
  wire T335;
  wire T336;
  wire T337;
  wire T338;
  wire T339;
  wire[19:0] T340;
  wire[19:0] T341;
  wire[19:0] T342;
  wire T343;
  wire T344;
  wire T345;
  wire[19:0] T346;
  wire[19:0] T347;
  reg[19:0] R348;
  wire T349;
  wire T350;
  wire T351;
  wire T352;
  wire T353;
  wire T354;
  wire T355;
  wire[19:0] T356;
  wire[19:0] T357;
  wire[19:0] T358;
  reg[19:0] R359;
  wire T360;
  wire T361;
  wire T362;
  wire T363;
  wire T364;
  wire T365;
  wire T366;
  wire[19:0] T367;
  wire[19:0] T368;
  wire[19:0] T369;
  wire T370;
  wire T371;
  wire[3:0] T3;
  wire[4:0] T372;
  wire[4:0] T373;
  reg[4:0] inputTag_0;
  wire[4:0] T374;
  wire[15:0] T375;
  wire[15:0] T376;
  reg[15:0] outputReg_0_centeroidIndex;
  wire[15:0] T377;
  wire T378;
  reg[0:0] outputReg_0_pointsFinished;
  wire T379;
  wire T380;
  reg[0:0] outputReg_0_centeroidsFinished;
  wire T381;
  wire[63:0] T382;
  wire[63:0] T383;
  reg[63:0] outputReg_0_point_y;
  wire[63:0] T384;
  wire[63:0] T385;
  wire[63:0] T386;
  reg[63:0] pMemOut_0_data_y;
  wire T387;
  wire T388;
  wire T389;
  wire[63:0] T390;
  wire[63:0] partialAccumulatorMemPort_rep_bits_data_y;
  wire T391;
  wire T392;
  wire[63:0] T393;
  wire[63:0] T394;
  reg[63:0] outputReg_0_point_z;
  wire[63:0] T395;
  wire[63:0] T396;
  wire[63:0] T397;
  reg[63:0] pMemOut_0_data_z;
  wire T398;
  wire T399;
  wire[63:0] T400;
  wire[63:0] partialAccumulatorMemPort_rep_bits_data_z;
  wire T401;
  wire T402;
  wire[63:0] T403;
  wire[63:0] T404;
  reg[63:0] outputReg_0_point_x;
  wire[63:0] T405;
  wire[63:0] T406;
  wire[63:0] T407;
  reg[63:0] pMemOut_0_data_x;
  wire T408;
  wire T409;
  wire[63:0] T410;
  wire[63:0] partialAccumulatorMemPort_rep_bits_data_x;
  wire T411;
  wire T412;

  assign io_pcOut_bits_request = R0;
  assign T1 = T3 || T2;
  assign T2 = ! T3;
  assign T3 = T4 && io_pcIn_bits_request;
  assign T4 = io_pcIn_valid && T5;
  assign T5 = io_pcIn_bits_moduleId == 16'h10/* 16*/;
  assign T6 = T2 ? io_pcIn_bits_request : T7;
  assign T7 = T3 ? 1'h0/* 0*/ : R0;
  assign io_pcOut_bits_moduleId = R8;
  assign T9 = T3 || T2;
  assign T10 = T11 ? io_pcIn_bits_moduleId : R8;
  assign T11 = T3 || T2;
  assign io_pcOut_valid = R12;
  assign T13 = T3 || T2;
  assign T14 = T2 ? io_pcIn_valid : T15;
  assign T15 = T3 ? 1'h1/* 1*/ : R12;
  assign io_pcOut_bits_pcType = R16;
  assign T17 = T3 || T2;
  assign T18 = T19 ? io_pcIn_bits_pcType : R16;
  assign T19 = T3 || T2;
  assign mainOff_add_rep_ready = addPort_rep_ready;
  assign addPort_rep_ready = 1'h1/* 1*/;
  assign mainOff_add_req_valid = addPort_req_valid;
  assign addPort_req_valid = T20;
  assign T20 = T254 && T21;
  assign T21 = T243 || T22;
  assign T22 = T241 && T23;
  assign T23 = T25 == T24;
  assign T24 = {5'h0/* 0*/, 3'h4/* 4*/};
  assign T25 = State_0 & T26;
  assign T26 = {4'h8/* 8*/{T27}};
  assign T27 = T28;
  assign T28 = T29[1'h0/* 0*/:1'h0/* 0*/];
  assign T29 = 1'h1/* 1*/ << rThreadEncoder_io_chosen;
  assign T30 = subStateTh_0 == 1'h0/* 0*/;
  assign T31 = T102 ? 1'h1/* 1*/ : T32;
  assign T32 = T33 ? 1'h0/* 0*/ : subStateTh_0;
  assign T33 = 1'h0/* 0*/ == vThreadEncoder_io_chosen;
  assign T34 = T101 && AllOffloadsValid_0;
  assign AllOffloadsValid_0 = T35;
  assign T35 = T57 && T36;
  assign T36 = T46 || T37;
  assign T37 = ! addPortHadValidRequest_0;
  assign T38 = T43 && T39;
  assign T39 = addPortHadValidRequest_0 || T40;
  assign T40 = T41 && addPort_req_valid;
  assign T41 = 5'h0/* 0*/ == T42;
  assign T42 = {4'h0/* 0*/, rThreadEncoder_io_chosen};
  assign T43 = ! T44;
  assign T44 = T45 == 5'h0/* 0*/;
  assign T45 = {4'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T46 = T55 || add_valid_received_0;
  assign T47 = T52 && T48;
  assign T48 = add_valid_received_0 || T49;
  assign T49 = addPort_rep_valid && T50;
  assign T50 = addPort_rep_tag == 5'h0/* 0*/;
  assign addPort_rep_tag = mainOff_add_rep_tag;
  assign mainOff_add_req_tag = addPort_req_tag;
  assign addPort_req_tag = T51;
  assign T51 = {4'h0/* 0*/, rThreadEncoder_io_chosen};
  assign addPort_rep_valid = mainOff_add_rep_valid;
  assign T52 = ! T53;
  assign T53 = T54 == 5'h0/* 0*/;
  assign T54 = {4'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T55 = addPort_rep_valid && T56;
  assign T56 = addPort_rep_tag == 5'h0/* 0*/;
  assign T57 = T98 || T58;
  assign T58 = ! partialAccumulatorMemPortHadValidRequest_0;
  assign T59 = T95 && T60;
  assign T60 = partialAccumulatorMemPortHadValidRequest_0 || T61;
  assign T61 = T93 && partialAccumulatorMemPort_req_valid;
  assign partialAccumulatorMemPort_req_valid = T62;
  assign T62 = T80 && T63;
  assign T63 = T69 || T64;
  assign T64 = T67 && T65;
  assign T65 = T25 == T66;
  assign T66 = {5'h0/* 0*/, 3'h6/* 6*/};
  assign T67 = T68 != 2'h1/* 1*/;
  assign T68 = {1'h0/* 0*/, rThreadEncoder_io_chosen};
  assign T69 = T75 || T70;
  assign T70 = T73 && T71;
  assign T71 = T25 == T72;
  assign T72 = {5'h0/* 0*/, 3'h5/* 5*/};
  assign T73 = T74 != 2'h1/* 1*/;
  assign T74 = {1'h0/* 0*/, rThreadEncoder_io_chosen};
  assign T75 = T78 && T76;
  assign T76 = T25 == T77;
  assign T77 = {7'h0/* 0*/, 1'h1/* 1*/};
  assign T78 = T79 != 2'h1/* 1*/;
  assign T79 = {1'h0/* 0*/, rThreadEncoder_io_chosen};
  assign T80 = T91 && T81;
  assign T81 = ! T82;
  assign T82 = partialAccumulatorMem_valid_received_0 & T27;
  assign T83 = T88 && T84;
  assign T84 = partialAccumulatorMem_valid_received_0 || T85;
  assign T85 = partialAccumulatorMemPort_rep_valid && T86;
  assign T86 = partialAccumulatorMemPort_rep_tag == 5'h0/* 0*/;
  assign partialAccumulatorMemPort_rep_tag = mainOff_partialAccumulatorMem_rep_tag;
  assign mainOff_partialAccumulatorMem_rep_ready = partialAccumulatorMemPort_rep_ready;
  assign partialAccumulatorMemPort_rep_ready = 1'h1/* 1*/;
  assign mainOff_partialAccumulatorMem_req_valid = partialAccumulatorMemPort_req_valid;
  assign mainOff_partialAccumulatorMem_req_tag = partialAccumulatorMemPort_req_tag;
  assign partialAccumulatorMemPort_req_tag = T87;
  assign T87 = {4'h0/* 0*/, rThreadEncoder_io_chosen};
  assign partialAccumulatorMemPort_rep_valid = mainOff_partialAccumulatorMem_rep_valid;
  assign T88 = ! T89;
  assign T89 = T90 == 5'h0/* 0*/;
  assign T90 = {4'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T91 = T92 != 2'h1/* 1*/;
  assign T92 = {1'h0/* 0*/, rThreadEncoder_io_chosen};
  assign T93 = 5'h0/* 0*/ == T94;
  assign T94 = {4'h0/* 0*/, rThreadEncoder_io_chosen};
  assign T95 = ! T96;
  assign T96 = T97 == 5'h0/* 0*/;
  assign T97 = {4'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T98 = T99 || partialAccumulatorMem_valid_received_0;
  assign T99 = partialAccumulatorMemPort_rep_valid && T100;
  assign T100 = partialAccumulatorMemPort_rep_tag == 5'h0/* 0*/;
  assign T101 = subStateTh_0 == 1'h1/* 1*/;
  assign T102 = T214 && T103;
  assign T103 = State_0 != 8'hff/* 255*/;
  assign T104 = T116 || T105;
  assign T105 = T109 && T106;
  assign T106 = T107;
  assign T107 = T108[1'h0/* 0*/:1'h0/* 0*/];
  assign T108 = 1'h1/* 1*/ << vThreadEncoder_io_chosen;
  assign T109 = T114 && T110;
  assign T110 = T112 == T111;
  assign T111 = {5'h0/* 0*/, 3'h6/* 6*/};
  assign T112 = State_0 & T113;
  assign T113 = {4'h8/* 8*/{T106}};
  assign T114 = T115 != 2'h1/* 1*/;
  assign T115 = {1'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T116 = T142 || T117;
  assign T117 = T118 && T106;
  assign T118 = T137 && T119;
  assign T119 = ! T120;
  assign T120 = T121 == 33'h9/* 9*/;
  assign T121 = {17'h0/* 0*/, T122};
  assign T122 = inputReg_0_centeroidIndex & T123;
  assign T123 = {5'h10/* 16*/{T106}};
  assign T124 = T131 && T125;
  assign T125 = T126;
  assign T126 = T127[1'h0/* 0*/:1'h0/* 0*/];
  assign T127 = 1'h1/* 1*/ << sThreadEncoder_io_chosen;
  assign T128 = T130 && T129;
  assign T129 = State_0 == 8'h0/* 0*/;
  assign T130 = subStateTh_0 == 1'h0/* 0*/;
  assign T131 = T134 && io_in_valid;
  assign io_in_ready = T132;
  assign T132 = T133 != 2'h1/* 1*/;
  assign T133 = {1'h0/* 0*/, sThreadEncoder_io_chosen};
  assign T134 = T135 != 2'h1/* 1*/;
  assign T135 = {1'h0/* 0*/, sThreadEncoder_io_chosen};
  assign T136 = T124 ? io_in_bits_centeroidIndex : inputReg_0_centeroidIndex;
  assign T137 = T140 && T138;
  assign T138 = T112 == T139;
  assign T139 = {5'h0/* 0*/, 3'h5/* 5*/};
  assign T140 = T141 != 2'h1/* 1*/;
  assign T141 = {1'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T142 = T145 || T143;
  assign T143 = T144 && T106;
  assign T144 = T137 && T120;
  assign T145 = T152 || T146;
  assign T146 = T147 && T106;
  assign T147 = T150 && T148;
  assign T148 = T112 == T149;
  assign T149 = {5'h0/* 0*/, 3'h4/* 4*/};
  assign T150 = T151 != 2'h1/* 1*/;
  assign T151 = {1'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T152 = T159 || T153;
  assign T153 = T154 && T106;
  assign T154 = T157 && T155;
  assign T155 = T112 == T156;
  assign T156 = {6'h0/* 0*/, 2'h3/* 3*/};
  assign T157 = T158 != 2'h1/* 1*/;
  assign T158 = {1'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T159 = T166 || T160;
  assign T160 = T161 && T106;
  assign T161 = T164 && T162;
  assign T162 = T112 == T163;
  assign T163 = {6'h0/* 0*/, 2'h2/* 2*/};
  assign T164 = T165 != 2'h1/* 1*/;
  assign T165 = {1'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T166 = T173 || T167;
  assign T167 = T168 && T106;
  assign T168 = T171 && T169;
  assign T169 = T112 == T170;
  assign T170 = {7'h0/* 0*/, 1'h1/* 1*/};
  assign T171 = T172 != 2'h1/* 1*/;
  assign T172 = {1'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T173 = T124 || T174;
  assign T174 = T175 && T27;
  assign T175 = T176 && io_out_ready;
  assign T176 = T178 && T177;
  assign T177 = T25 == 8'hff/* 255*/;
  assign T178 = T179 != 2'h1/* 1*/;
  assign T179 = {1'h0/* 0*/, rThreadEncoder_io_chosen};
  assign T180 = T105 ? 8'hff/* 255*/ : T181;
  assign T181 = T117 ? 8'h0/* 0*/ : T182;
  assign T182 = T143 ? T213 : T183;
  assign T183 = T146 ? T212 : T184;
  assign T184 = T153 ? T211 : T185;
  assign T185 = T160 ? T210 : T186;
  assign T186 = T167 ? T209 : T187;
  assign T187 = T174 ? T190 : T188;
  assign T188 = T124 ? T189 : State_0;
  assign T189 = {7'h0/* 0*/, 1'h1/* 1*/};
  assign T190 = EmitReturnState_0 & T191;
  assign T191 = {4'h8/* 8*/{T27}};
  assign T192 = T200 || T193;
  assign T193 = T194 && T106;
  assign T194 = T109 && T195;
  assign T195 = ! T196;
  assign T196 = T197 == 32'ha/* 10*/;
  assign T197 = centeroidIndex_0 & T198;
  assign T198 = {6'h20/* 32*/{T106}};
  assign T199 = T202 || T200;
  assign T200 = T201 && T106;
  assign T201 = T109 && T196;
  assign T202 = T143 || T105;
  assign T203 = T200 ? 32'h0/* 0*/ : T204;
  assign T204 = T105 ? T206 : T205;
  assign T205 = T143 ? 32'h0/* 0*/ : centeroidIndex_0;
  assign T206 = T197 + 32'h1/* 1*/;
  assign T207 = T193 ? T112 : T208;
  assign T208 = T200 ? 8'h0/* 0*/ : EmitReturnState_0;
  assign T209 = {6'h0/* 0*/, 2'h2/* 2*/};
  assign T210 = {6'h0/* 0*/, 2'h3/* 3*/};
  assign T211 = {5'h0/* 0*/, 3'h4/* 4*/};
  assign T212 = {5'h0/* 0*/, 3'h5/* 5*/};
  assign T213 = {5'h0/* 0*/, 3'h6/* 6*/};
  assign T214 = T216 && T215;
  assign T215 = State_0 != 8'h0/* 0*/;
  assign T216 = AllOffloadsReady && T217;
  assign T217 = 1'h0/* 0*/ == rThreadEncoder_io_chosen;
  assign AllOffloadsReady = T218;
  assign T218 = T230 && T219;
  assign T219 = T226 || T220;
  assign T220 = T222 && T221;
  assign T221 = ! addPort_req_valid;
  assign T222 = ! addPortHadReadyRequest;
  assign T223 = T225 && T224;
  assign T224 = addPortHadReadyRequest || addPort_req_valid;
  assign T225 = ! AllOffloadsReady;
  assign T226 = addPort_req_ready || add_ready_received;
  assign T227 = T229 && T228;
  assign T228 = add_ready_received || addPort_req_ready;
  assign addPort_req_ready = mainOff_add_req_ready;
  assign T229 = ! AllOffloadsReady;
  assign T230 = T237 || T231;
  assign T231 = T233 && T232;
  assign T232 = ! partialAccumulatorMemPort_req_valid;
  assign T233 = ! partialAccumulatorMemPortHadReadyRequest;
  assign T234 = T236 && T235;
  assign T235 = partialAccumulatorMemPortHadReadyRequest || partialAccumulatorMemPort_req_valid;
  assign T236 = ! AllOffloadsReady;
  assign T237 = partialAccumulatorMemPort_req_ready || partialAccumulatorMem_ready_received;
  assign T238 = T240 && T239;
  assign T239 = partialAccumulatorMem_ready_received || partialAccumulatorMemPort_req_ready;
  assign partialAccumulatorMemPort_req_ready = mainOff_partialAccumulatorMem_req_ready;
  assign T240 = ! AllOffloadsReady;
  assign T241 = T242 != 2'h1/* 1*/;
  assign T242 = {1'h0/* 0*/, rThreadEncoder_io_chosen};
  assign T243 = T249 || T244;
  assign T244 = T247 && T245;
  assign T245 = T25 == T246;
  assign T246 = {6'h0/* 0*/, 2'h3/* 3*/};
  assign T247 = T248 != 2'h1/* 1*/;
  assign T248 = {1'h0/* 0*/, rThreadEncoder_io_chosen};
  assign T249 = T252 && T250;
  assign T250 = T25 == T251;
  assign T251 = {6'h0/* 0*/, 2'h2/* 2*/};
  assign T252 = T253 != 2'h1/* 1*/;
  assign T253 = {1'h0/* 0*/, rThreadEncoder_io_chosen};
  assign T254 = T257 && T255;
  assign T255 = ! T256;
  assign T256 = add_valid_received_0 & T27;
  assign T257 = T258 != 2'h1/* 1*/;
  assign T258 = {1'h0/* 0*/, rThreadEncoder_io_chosen};
  assign io_pcOut_bits_portId = R259;
  assign T260 = T3 || T2;
  assign T261 = T262 ? io_pcIn_bits_portId : R259;
  assign T262 = T3 || T2;
  assign io_pcOut_bits_pcValue = R263;
  assign T264 = T3 || T2;
  assign T265 = T2 ? io_pcIn_bits_pcValue : T266;
  assign T266 = T3 ? T267 : R263;
  assign T267 = T371 ? T347 : T268;
  assign T268 = T343 ? R332 : T269;
  assign T269 = T328 ? R317 : T270;
  assign T270 = T313 ? R298 : T271;
  assign T271 = T294 ? R272 : 20'h0/* 0*/;
  assign T273 = T282 || T274;
  assign T274 = T290 && T275;
  assign T275 = T288 && T276;
  assign T276 = ! R277;
  assign T278 = T282 || T279;
  assign T279 = T281 && T280;
  assign T280 = io_pcIn_bits_pcType == T0;
  assign T0 = {1'h0/* 0*/, 3'h4/* 4*/};
  assign T281 = io_pcIn_valid && io_pcIn_bits_request;
  assign T282 = T284 && T283;
  assign T283 = io_pcIn_bits_pcType == T1;
  assign T1 = {1'h0/* 0*/, 3'h3/* 3*/};
  assign T284 = io_pcIn_valid && io_pcIn_bits_request;
  assign T285 = T279 ? T287 : T286;
  assign T286 = T282 ? 1'h0/* 0*/ : R277;
  assign T287 = ! R277;
  assign T288 = io_in_valid && T289;
  assign T289 = ! io_in_ready;
  assign T290 = ! T282;
  assign T291 = T274 ? T293 : T292;
  assign T292 = T282 ? 20'h0/* 0*/ : R272;
  assign T293 = R272 + 20'h1/* 1*/;
  assign T294 = T296 && T295;
  assign T295 = io_pcIn_bits_pcType == T2;
  assign T2 = {1'h0/* 0*/, 3'h1/* 1*/};
  assign T296 = T297 == 20'h1/* 1*/;
  assign T297 = {12'h0/* 0*/, io_pcIn_bits_portId};
  assign T299 = T282 || T300;
  assign T300 = T309 && T301;
  assign T301 = T303 && T302;
  assign T302 = ! R277;
  assign T303 = io_out_valid && T304;
  assign T304 = ! io_out_ready;
  assign io_out_valid = T305;
  assign T305 = T307 && T306;
  assign T306 = T25 == 8'hff/* 255*/;
  assign T307 = T308 != 2'h1/* 1*/;
  assign T308 = {1'h0/* 0*/, rThreadEncoder_io_chosen};
  assign T309 = ! T282;
  assign T310 = T300 ? T312 : T311;
  assign T311 = T282 ? 20'h0/* 0*/ : R298;
  assign T312 = R298 + 20'h1/* 1*/;
  assign T313 = T315 && T314;
  assign T314 = io_pcIn_bits_pcType == T2;
  assign T315 = T316 == 20'h2/* 2*/;
  assign T316 = {12'h0/* 0*/, io_pcIn_bits_portId};
  assign T318 = T282 || T319;
  assign T319 = T324 && T320;
  assign T320 = T322 && T321;
  assign T321 = ! R277;
  assign T322 = mainOff_partialAccumulatorMem_req_valid && T323;
  assign T323 = ! mainOff_partialAccumulatorMem_req_ready;
  assign T324 = ! T282;
  assign T325 = T319 ? T327 : T326;
  assign T326 = T282 ? 20'h0/* 0*/ : R317;
  assign T327 = R317 + 20'h1/* 1*/;
  assign T328 = T330 && T329;
  assign T329 = io_pcIn_bits_pcType == T2;
  assign T330 = T331 == 20'h3/* 3*/;
  assign T331 = {12'h0/* 0*/, io_pcIn_bits_portId};
  assign T333 = T282 || T334;
  assign T334 = T339 && T335;
  assign T335 = T337 && T336;
  assign T336 = ! R277;
  assign T337 = mainOff_add_req_valid && T338;
  assign T338 = ! mainOff_add_req_ready;
  assign T339 = ! T282;
  assign T340 = T334 ? T342 : T341;
  assign T341 = T282 ? 20'h0/* 0*/ : R332;
  assign T342 = R332 + 20'h1/* 1*/;
  assign T343 = T345 && T344;
  assign T344 = io_pcIn_bits_pcType == T2;
  assign T345 = T346 == 20'h4/* 4*/;
  assign T346 = {12'h0/* 0*/, io_pcIn_bits_portId};
  assign T347 = T370 ? R359 : R348;
  assign T349 = T282 || T350;
  assign T350 = T355 && T351;
  assign T351 = T353 && T352;
  assign T352 = ! R277;
  assign T353 = mainOff_add_req_ready && T354;
  assign T354 = ! mainOff_add_req_valid;
  assign T355 = ! T282;
  assign T356 = T350 ? T358 : T357;
  assign T357 = T282 ? 20'h0/* 0*/ : R348;
  assign T358 = R348 + 20'h1/* 1*/;
  assign T360 = T282 || T361;
  assign T361 = T366 && T362;
  assign T362 = T364 && T363;
  assign T363 = ! R277;
  assign T364 = mainOff_partialAccumulatorMem_req_ready && T365;
  assign T365 = ! mainOff_partialAccumulatorMem_req_valid;
  assign T366 = ! T282;
  assign T367 = T361 ? T369 : T368;
  assign T368 = T282 ? 20'h0/* 0*/ : R359;
  assign T369 = R359 + 20'h1/* 1*/;
  assign T370 = R359 > R348;
  assign T371 = io_pcIn_bits_pcType == T3;
  assign T3 = {1'h0/* 0*/, 3'h2/* 2*/};
  assign io_out_tag = T372;
  assign T372 = inputTag_0 & T373;
  assign T373 = {3'h5/* 5*/{T27}};
  assign T374 = T124 ? io_in_tag : inputTag_0;
  assign io_out_bits_centeroidIndex = T375;
  assign T375 = outputReg_0_centeroidIndex & T376;
  assign T376 = {5'h10/* 16*/{T27}};
  assign io_out_bits_pointsFinished = T378;
  assign T378 = outputReg_0_pointsFinished & T27;
  assign io_out_bits_centeroidsFinished = T380;
  assign T380 = outputReg_0_centeroidsFinished & T27;
  assign io_out_bits_point_y = T382;
  assign T382 = outputReg_0_point_y & T383;
  assign T383 = {7'h40/* 64*/{T27}};
  assign T384 = T105 ? T385 : outputReg_0_point_y;
  assign T385 = pMemOut_0_data_y & T386;
  assign T386 = {7'h40/* 64*/{T106}};
  assign T387 = T388 || T105;
  assign T388 = T167 || T389;
  assign T389 = T137 && T106;
  assign T390 = T391 ? partialAccumulatorMemPort_rep_bits_data_y : pMemOut_0_data_y;
  assign partialAccumulatorMemPort_rep_bits_data_y = mainOff_partialAccumulatorMem_rep_bits_data_y;
  assign T391 = T392 || T105;
  assign T392 = T167 || T389;
  assign io_out_bits_point_z = T393;
  assign T393 = outputReg_0_point_z & T394;
  assign T394 = {7'h40/* 64*/{T27}};
  assign T395 = T105 ? T396 : outputReg_0_point_z;
  assign T396 = pMemOut_0_data_z & T397;
  assign T397 = {7'h40/* 64*/{T106}};
  assign T398 = T399 || T105;
  assign T399 = T167 || T389;
  assign T400 = T401 ? partialAccumulatorMemPort_rep_bits_data_z : pMemOut_0_data_z;
  assign partialAccumulatorMemPort_rep_bits_data_z = mainOff_partialAccumulatorMem_rep_bits_data_z;
  assign T401 = T402 || T105;
  assign T402 = T167 || T389;
  assign io_out_bits_point_x = T403;
  assign T403 = outputReg_0_point_x & T404;
  assign T404 = {7'h40/* 64*/{T27}};
  assign T405 = T105 ? T406 : outputReg_0_point_x;
  assign T406 = pMemOut_0_data_x & T407;
  assign T407 = {7'h40/* 64*/{T106}};
  assign T408 = T409 || T105;
  assign T409 = T167 || T389;
  assign T410 = T411 ? partialAccumulatorMemPort_rep_bits_data_x : pMemOut_0_data_x;
  assign partialAccumulatorMemPort_rep_bits_data_x = mainOff_partialAccumulatorMem_rep_bits_data_x;
  assign T411 = T412 || T105;
  assign T412 = T167 || T389;
  RREncode_18 rThreadEncoder(
       .io_valid_0( T30 ),
       .io_chosen( rThreadEncoder_io_chosen ),
       .io_ready(  ));
  RREncode_19 vThreadEncoder(
       .io_valid_0( T34 ),
       .io_chosen( vThreadEncoder_io_chosen ),
       .io_ready(  ));
  RREncode_20 sThreadEncoder(
       .io_valid_0( T128 ),
       .io_chosen( sThreadEncoder_io_chosen ),
       .io_ready(  ));

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
    subStateTh_0 <= reset ? 1'h0/* 0*/ : T31;
    addPortHadValidRequest_0 <= reset ? 1'h0/* 0*/ : T38;
    add_valid_received_0 <= reset ? 1'h0/* 0*/ : T47;
    partialAccumulatorMemPortHadValidRequest_0 <= reset ? 1'h0/* 0*/ : T59;
    partialAccumulatorMem_valid_received_0 <= reset ? 1'h0/* 0*/ : T83;
    if(reset) begin
      State_0 <= 8'h0/* 0*/;
    end else if(T104) begin
      State_0 <= T180;
    end
    if(T124) begin
      inputReg_0_centeroidIndex <= T136;
    end
    if(reset) begin
      EmitReturnState_0 <= 8'h0/* 0*/;
    end else if(T192) begin
      EmitReturnState_0 <= T207;
    end
    if(reset) begin
      centeroidIndex_0 <= 32'h0/* 0*/;
    end else if(T199) begin
      centeroidIndex_0 <= T203;
    end
    addPortHadReadyRequest <= reset ? 1'h0/* 0*/ : T223;
    add_ready_received <= reset ? 1'h0/* 0*/ : T227;
    partialAccumulatorMemPortHadReadyRequest <= reset ? 1'h0/* 0*/ : T234;
    partialAccumulatorMem_ready_received <= reset ? 1'h0/* 0*/ : T238;
    if(reset) begin
      R259 <= 8'h0/* 0*/;
    end else if(T260) begin
      R259 <= T261;
    end
    if(reset) begin
      R263 <= 20'h0/* 0*/;
    end else if(T264) begin
      R263 <= T265;
    end
    if(reset) begin
      R272 <= 20'h0/* 0*/;
    end else if(T273) begin
      R272 <= T291;
    end
    if(reset) begin
      R277 <= 1'h0/* 0*/;
    end else if(T278) begin
      R277 <= T285;
    end
    if(reset) begin
      R298 <= 20'h0/* 0*/;
    end else if(T299) begin
      R298 <= T310;
    end
    if(reset) begin
      R317 <= 20'h0/* 0*/;
    end else if(T318) begin
      R317 <= T325;
    end
    if(reset) begin
      R332 <= 20'h0/* 0*/;
    end else if(T333) begin
      R332 <= T340;
    end
    if(reset) begin
      R348 <= 20'h0/* 0*/;
    end else if(T349) begin
      R348 <= T356;
    end
    if(reset) begin
      R359 <= 20'h0/* 0*/;
    end else if(T360) begin
      R359 <= T367;
    end
    if(T124) begin
      inputTag_0 <= T374;
    end
    outputReg_0_centeroidIndex <= T377;
    outputReg_0_pointsFinished <= T379;
    outputReg_0_centeroidsFinished <= T381;
    if(T105) begin
      outputReg_0_point_y <= T384;
    end
    if(T387) begin
      pMemOut_0_data_y <= T390;
    end
    if(T105) begin
      outputReg_0_point_z <= T395;
    end
    if(T398) begin
      pMemOut_0_data_z <= T400;
    end
    if(T105) begin
      outputReg_0_point_x <= T405;
    end
    if(T408) begin
      pMemOut_0_data_x <= T410;
    end
  end
endmodule

module RREncode_21(
    input  io_valid_0,
    output io_chosen,
    input  io_ready);

  wire choose;

  assign io_chosen = choose;
  assign choose = io_valid_0 ? 1'h0/* 0*/ : 1'h1/* 1*/;
endmodule

module RREncode_22(
    input  io_valid_0,
    output io_chosen,
    input  io_ready);

  wire choose;

  assign io_chosen = choose;
  assign choose = io_valid_0 ? 1'h0/* 0*/ : 1'h1/* 1*/;
endmodule

module RREncode_23(
    input  io_valid_0,
    output io_chosen,
    input  io_ready);

  wire choose;

  assign io_chosen = choose;
  assign choose = io_valid_0 ? 1'h0/* 0*/ : 1'h1/* 1*/;
endmodule

module pAccMem_1(input clk, input reset,
    output io_in_ready,
    input  io_in_valid,
    input  io_in_bits_rw,
    input [15:0] io_in_bits_addr,
    input [63:0] io_in_bits_data_x,
    input [63:0] io_in_bits_data_y,
    input [63:0] io_in_bits_data_z,
    input [4:0] io_in_tag,
    input  io_out_ready,
    output io_out_valid,
    output[63:0] io_out_bits_data_x,
    output[63:0] io_out_bits_data_y,
    output[63:0] io_out_bits_data_z,
    output[4:0] io_out_tag,
    input  io_pcIn_valid,
    input  io_pcIn_bits_request,
    input [15:0] io_pcIn_bits_moduleId,
    input [7:0] io_pcIn_bits_portId,
    input [19:0] io_pcIn_bits_pcValue,
    input [3:0] io_pcIn_bits_pcType,
    output io_pcOut_valid,
    output io_pcOut_bits_request,
    output[15:0] io_pcOut_bits_moduleId,
    output[7:0] io_pcOut_bits_portId,
    output[19:0] io_pcOut_bits_pcValue,
    output[3:0] io_pcOut_bits_pcType);

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
  wire[4:0] T20;
  wire[4:0] T21;
  wire T22;
  wire T23;
  wire[1:0] T24;
  wire rThreadEncoder_io_chosen;
  wire T25;
  reg[0:0] subStateTh_0;
  wire T26;
  wire T27;
  wire T28;
  wire vThreadEncoder_io_chosen;
  wire T29;
  wire AllOffloadsValid_0;
  wire T30;
  wire T31;
  wire T32;
  reg[7:0] State_0;
  wire T33;
  wire T34;
  wire T35;
  wire T36;
  wire[1:0] T37;
  wire T38;
  wire T39;
  wire[7:0] T40;
  wire[7:0] T41;
  wire[7:0] T42;
  wire T43;
  wire[1:0] T44;
  wire T45;
  wire T46;
  wire T47;
  wire T48;
  wire T49;
  wire[7:0] T50;
  wire[7:0] T51;
  wire T52;
  wire[1:0] T53;
  wire T54;
  wire T55;
  wire T56;
  wire[1:0] T57;
  wire sThreadEncoder_io_chosen;
  wire T58;
  wire T59;
  wire T60;
  wire T61;
  wire T62;
  wire[1:0] T63;
  wire[7:0] T64;
  wire[7:0] T65;
  wire[7:0] T66;
  wire[7:0] T67;
  wire[7:0] T68;
  wire[7:0] T69;
  reg[7:0] EmitReturnState_0;
  wire[7:0] T70;
  wire T71;
  wire T72;
  wire T73;
  wire T74;
  wire AllOffloadsReady;
  reg[4:0] inputTag_0;
  wire[4:0] T75;
  wire T76;
  wire T77;
  wire T78;
  wire[1:0] T79;
  wire T80;
  wire[1:0] T81;
  reg[7:0] R82;
  wire T83;
  wire[7:0] T84;
  wire T85;
  reg[19:0] R86;
  wire T87;
  wire[19:0] T88;
  wire[19:0] T89;
  wire[19:0] T90;
  wire[19:0] T91;
  wire[19:0] T92;
  reg[19:0] R93;
  wire T94;
  wire T95;
  wire T96;
  wire T97;
  reg[0:0] R98;
  wire T99;
  wire T100;
  wire T101;
  wire[3:0] T0;
  wire T102;
  wire T103;
  wire T104;
  wire[3:0] T1;
  wire T105;
  wire T106;
  wire T107;
  wire T108;
  wire T109;
  wire T110;
  wire T111;
  wire[19:0] T112;
  wire[19:0] T113;
  wire[19:0] T114;
  wire T115;
  wire T116;
  wire[3:0] T2;
  wire T117;
  wire[19:0] T118;
  reg[19:0] R119;
  wire T120;
  wire T121;
  wire T122;
  wire T123;
  wire T124;
  wire T125;
  wire T126;
  wire[19:0] T127;
  wire[19:0] T128;
  wire[19:0] T129;
  wire T130;
  wire T131;
  wire T132;
  wire[19:0] T133;
  wire[19:0] T134;
  wire T135;
  wire[3:0] T3;
  wire[63:0] T136;
  wire[63:0] T137;
  reg[63:0] outputReg_0_data_y;
  wire[63:0] T138;
  wire[63:0] T139;
  wire[63:0] T140;
  reg[63:0] outputReg_0_data_z;
  wire[63:0] T141;
  wire[63:0] T142;
  wire[63:0] T143;
  reg[63:0] outputReg_0_data_x;
  wire[63:0] T144;

  assign io_pcOut_bits_request = R0;
  assign T1 = T3 || T2;
  assign T2 = ! T3;
  assign T3 = T4 && io_pcIn_bits_request;
  assign T4 = io_pcIn_valid && T5;
  assign T5 = io_pcIn_bits_moduleId == 16'h11/* 17*/;
  assign T6 = T2 ? io_pcIn_bits_request : T7;
  assign T7 = T3 ? 1'h0/* 0*/ : R0;
  assign io_pcOut_bits_moduleId = R8;
  assign T9 = T3 || T2;
  assign T10 = T11 ? io_pcIn_bits_moduleId : R8;
  assign T11 = T3 || T2;
  assign io_pcOut_valid = R12;
  assign T13 = T3 || T2;
  assign T14 = T2 ? io_pcIn_valid : T15;
  assign T15 = T3 ? 1'h1/* 1*/ : R12;
  assign io_pcOut_bits_pcType = R16;
  assign T17 = T3 || T2;
  assign T18 = T19 ? io_pcIn_bits_pcType : R16;
  assign T19 = T3 || T2;
  assign io_out_tag = T20;
  assign T20 = inputTag_0 & T21;
  assign T21 = {3'h5/* 5*/{T22}};
  assign T22 = T23;
  assign T23 = T24[1'h0/* 0*/:1'h0/* 0*/];
  assign T24 = 1'h1/* 1*/ << rThreadEncoder_io_chosen;
  assign T25 = subStateTh_0 == 1'h0/* 0*/;
  assign T26 = T31 ? 1'h1/* 1*/ : T27;
  assign T27 = T28 ? 1'h0/* 0*/ : subStateTh_0;
  assign T28 = 1'h0/* 0*/ == vThreadEncoder_io_chosen;
  assign T29 = T30 && AllOffloadsValid_0;
  assign AllOffloadsValid_0 = 1'h1/* 1*/;
  assign T30 = subStateTh_0 == 1'h1/* 1*/;
  assign T31 = T71 && T32;
  assign T32 = State_0 != 8'hff/* 255*/;
  assign T33 = T45 || T34;
  assign T34 = T38 && T35;
  assign T35 = T36;
  assign T36 = T37[1'h0/* 0*/:1'h0/* 0*/];
  assign T37 = 1'h1/* 1*/ << vThreadEncoder_io_chosen;
  assign T38 = T43 && T39;
  assign T39 = T41 == T40;
  assign T40 = {7'h0/* 0*/, 1'h1/* 1*/};
  assign T41 = State_0 & T42;
  assign T42 = {4'h8/* 8*/{T35}};
  assign T43 = T44 != 2'h1/* 1*/;
  assign T44 = {1'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T45 = T54 || T46;
  assign T46 = T47 && T22;
  assign T47 = T48 && io_out_ready;
  assign T48 = T52 && T49;
  assign T49 = T50 == 8'hff/* 255*/;
  assign T50 = State_0 & T51;
  assign T51 = {4'h8/* 8*/{T22}};
  assign T52 = T53 != 2'h1/* 1*/;
  assign T53 = {1'h0/* 0*/, rThreadEncoder_io_chosen};
  assign T54 = T61 && T55;
  assign T55 = T56;
  assign T56 = T57[1'h0/* 0*/:1'h0/* 0*/];
  assign T57 = 1'h1/* 1*/ << sThreadEncoder_io_chosen;
  assign T58 = T60 && T59;
  assign T59 = State_0 == 8'h0/* 0*/;
  assign T60 = subStateTh_0 == 1'h0/* 0*/;
  assign T61 = T62 && io_in_valid;
  assign T62 = T63 != 2'h1/* 1*/;
  assign T63 = {1'h0/* 0*/, sThreadEncoder_io_chosen};
  assign T64 = T34 ? 8'hff/* 255*/ : T65;
  assign T65 = T46 ? T68 : T66;
  assign T66 = T54 ? T67 : State_0;
  assign T67 = {7'h0/* 0*/, 1'h1/* 1*/};
  assign T68 = EmitReturnState_0 & T69;
  assign T69 = {4'h8/* 8*/{T22}};
  assign T70 = T34 ? 8'h0/* 0*/ : EmitReturnState_0;
  assign T71 = T73 && T72;
  assign T72 = State_0 != 8'h0/* 0*/;
  assign T73 = AllOffloadsReady && T74;
  assign T74 = 1'h0/* 0*/ == rThreadEncoder_io_chosen;
  assign AllOffloadsReady = 1'h1/* 1*/;
  assign T75 = T54 ? io_in_tag : inputTag_0;
  assign io_out_valid = T76;
  assign T76 = T78 && T77;
  assign T77 = T50 == 8'hff/* 255*/;
  assign T78 = T79 != 2'h1/* 1*/;
  assign T79 = {1'h0/* 0*/, rThreadEncoder_io_chosen};
  assign io_in_ready = T80;
  assign T80 = T81 != 2'h1/* 1*/;
  assign T81 = {1'h0/* 0*/, sThreadEncoder_io_chosen};
  assign io_pcOut_bits_portId = R82;
  assign T83 = T3 || T2;
  assign T84 = T85 ? io_pcIn_bits_portId : R82;
  assign T85 = T3 || T2;
  assign io_pcOut_bits_pcValue = R86;
  assign T87 = T3 || T2;
  assign T88 = T2 ? io_pcIn_bits_pcValue : T89;
  assign T89 = T3 ? T90 : R86;
  assign T90 = T135 ? T134 : T91;
  assign T91 = T130 ? R119 : T92;
  assign T92 = T115 ? R93 : 20'h0/* 0*/;
  assign T94 = T103 || T95;
  assign T95 = T111 && T96;
  assign T96 = T109 && T97;
  assign T97 = ! R98;
  assign T99 = T103 || T100;
  assign T100 = T102 && T101;
  assign T101 = io_pcIn_bits_pcType == T0;
  assign T0 = {1'h0/* 0*/, 3'h4/* 4*/};
  assign T102 = io_pcIn_valid && io_pcIn_bits_request;
  assign T103 = T105 && T104;
  assign T104 = io_pcIn_bits_pcType == T1;
  assign T1 = {1'h0/* 0*/, 3'h3/* 3*/};
  assign T105 = io_pcIn_valid && io_pcIn_bits_request;
  assign T106 = T100 ? T108 : T107;
  assign T107 = T103 ? 1'h0/* 0*/ : R98;
  assign T108 = ! R98;
  assign T109 = io_in_valid && T110;
  assign T110 = ! io_in_ready;
  assign T111 = ! T103;
  assign T112 = T95 ? T114 : T113;
  assign T113 = T103 ? 20'h0/* 0*/ : R93;
  assign T114 = R93 + 20'h1/* 1*/;
  assign T115 = T117 && T116;
  assign T116 = io_pcIn_bits_pcType == T2;
  assign T2 = {1'h0/* 0*/, 3'h1/* 1*/};
  assign T117 = T118 == 20'h1/* 1*/;
  assign T118 = {12'h0/* 0*/, io_pcIn_bits_portId};
  assign T120 = T103 || T121;
  assign T121 = T126 && T122;
  assign T122 = T124 && T123;
  assign T123 = ! R98;
  assign T124 = io_out_valid && T125;
  assign T125 = ! io_out_ready;
  assign T126 = ! T103;
  assign T127 = T121 ? T129 : T128;
  assign T128 = T103 ? 20'h0/* 0*/ : R119;
  assign T129 = R119 + 20'h1/* 1*/;
  assign T130 = T132 && T131;
  assign T131 = io_pcIn_bits_pcType == T2;
  assign T132 = T133 == 20'h2/* 2*/;
  assign T133 = {12'h0/* 0*/, io_pcIn_bits_portId};
  assign T134 = {15'h0/* 0*/, 5'h14/* 20*/};
  assign T135 = io_pcIn_bits_pcType == T3;
  assign T3 = {1'h0/* 0*/, 3'h2/* 2*/};
  assign io_out_bits_data_y = T136;
  assign T136 = outputReg_0_data_y & T137;
  assign T137 = {7'h40/* 64*/{T22}};
  assign io_out_bits_data_z = T139;
  assign T139 = outputReg_0_data_z & T140;
  assign T140 = {7'h40/* 64*/{T22}};
  assign io_out_bits_data_x = T142;
  assign T142 = outputReg_0_data_x & T143;
  assign T143 = {7'h40/* 64*/{T22}};
  RREncode_21 rThreadEncoder(
       .io_valid_0( T25 ),
       .io_chosen( rThreadEncoder_io_chosen ),
       .io_ready(  ));
  RREncode_22 vThreadEncoder(
       .io_valid_0( T29 ),
       .io_chosen( vThreadEncoder_io_chosen ),
       .io_ready(  ));
  RREncode_23 sThreadEncoder(
       .io_valid_0( T58 ),
       .io_chosen( sThreadEncoder_io_chosen ),
       .io_ready(  ));

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
    subStateTh_0 <= reset ? 1'h0/* 0*/ : T26;
    if(reset) begin
      State_0 <= 8'h0/* 0*/;
    end else if(T33) begin
      State_0 <= T64;
    end
    if(reset) begin
      EmitReturnState_0 <= 8'h0/* 0*/;
    end else if(T34) begin
      EmitReturnState_0 <= T70;
    end
    if(T54) begin
      inputTag_0 <= T75;
    end
    if(reset) begin
      R82 <= 8'h0/* 0*/;
    end else if(T83) begin
      R82 <= T84;
    end
    if(reset) begin
      R86 <= 20'h0/* 0*/;
    end else if(T87) begin
      R86 <= T88;
    end
    if(reset) begin
      R93 <= 20'h0/* 0*/;
    end else if(T94) begin
      R93 <= T112;
    end
    if(reset) begin
      R98 <= 1'h0/* 0*/;
    end else if(T99) begin
      R98 <= T106;
    end
    if(reset) begin
      R119 <= 20'h0/* 0*/;
    end else if(T120) begin
      R119 <= T127;
    end
    outputReg_0_data_y <= T138;
    outputReg_0_data_z <= T141;
    outputReg_0_data_x <= T144;
  end
endmodule

module gOffloadedComponent_4(input clk, input reset,
    output io_in_ready,
    input  io_in_valid,
    input  io_in_bits_centeroidsFinished,
    input  io_in_bits_pointsFinished,
    input [15:0] io_in_bits_centeroidIndex,
    input [63:0] io_in_bits_point_x,
    input [63:0] io_in_bits_point_y,
    input [63:0] io_in_bits_point_z,
    input [4:0] io_in_tag,
    input  io_out_ready,
    output io_out_valid,
    output io_out_bits_centeroidsFinished,
    output io_out_bits_pointsFinished,
    output[15:0] io_out_bits_centeroidIndex,
    output[63:0] io_out_bits_point_x,
    output[63:0] io_out_bits_point_y,
    output[63:0] io_out_bits_point_z,
    output[4:0] io_out_tag,
    input  io_pcIn_valid,
    input  io_pcIn_bits_request,
    input [15:0] io_pcIn_bits_moduleId,
    input [7:0] io_pcIn_bits_portId,
    input [19:0] io_pcIn_bits_pcValue,
    input [3:0] io_pcIn_bits_pcType,
    output io_pcOut_valid,
    output io_pcOut_bits_request,
    output[15:0] io_pcOut_bits_moduleId,
    output[7:0] io_pcOut_bits_portId,
    output[19:0] io_pcOut_bits_pcValue,
    output[3:0] io_pcOut_bits_pcType,
    input  mainOff_add_req_ready,
    output mainOff_add_req_valid,
    output[63:0] mainOff_add_req_bits_in1,
    output[63:0] mainOff_add_req_bits_in2,
    output[4:0] mainOff_add_req_tag,
    output mainOff_add_rep_ready,
    input  mainOff_add_rep_valid,
    input [63:0] mainOff_add_rep_bits_out,
    input [4:0] mainOff_add_rep_tag);

  wire offComp_io_pcOut_bits_request;
  wire mainComp_io_pcOut_bits_request;
  wire[15:0] mainComp_io_pcOut_bits_moduleId;
  wire mainComp_io_pcOut_valid;
  wire[15:0] offComp_io_pcOut_bits_moduleId;
  wire offComp_io_pcOut_valid;
  wire[3:0] offComp_io_pcOut_bits_pcType;
  wire[3:0] mainComp_io_pcOut_bits_pcType;
  wire mainComp_mainOff_add_rep_ready;
  wire mainComp_mainOff_add_req_valid;
  wire[4:0] mainComp_mainOff_add_req_tag;
  wire[4:0] offComp_io_out_tag;
  wire mainComp_mainOff_partialAccumulatorMem_rep_ready;
  wire mainComp_mainOff_partialAccumulatorMem_req_valid;
  wire[4:0] mainComp_mainOff_partialAccumulatorMem_req_tag;
  wire offComp_io_out_valid;
  wire mainComp_io_in_ready;
  wire offComp_io_in_ready;
  wire[7:0] offComp_io_pcOut_bits_portId;
  wire[7:0] mainComp_io_pcOut_bits_portId;
  wire[19:0] offComp_io_pcOut_bits_pcValue;
  wire[19:0] mainComp_io_pcOut_bits_pcValue;
  wire[4:0] mainComp_io_out_tag;
  wire mainComp_io_out_valid;
  wire[15:0] mainComp_io_out_bits_centeroidIndex;
  wire mainComp_io_out_bits_pointsFinished;
  wire mainComp_io_out_bits_centeroidsFinished;
  wire[63:0] mainComp_io_out_bits_point_y;
  wire[63:0] offComp_io_out_bits_data_y;
  wire[63:0] mainComp_io_out_bits_point_z;
  wire[63:0] offComp_io_out_bits_data_z;
  wire[63:0] mainComp_io_out_bits_point_x;
  wire[63:0] offComp_io_out_bits_data_x;

  assign io_pcOut_bits_request = offComp_io_pcOut_bits_request;
  assign io_pcOut_bits_moduleId = offComp_io_pcOut_bits_moduleId;
  assign io_pcOut_valid = offComp_io_pcOut_valid;
  assign io_pcOut_bits_pcType = offComp_io_pcOut_bits_pcType;
  assign mainOff_add_rep_ready = mainComp_mainOff_add_rep_ready;
  assign mainOff_add_req_valid = mainComp_mainOff_add_req_valid;
  assign mainOff_add_req_tag = mainComp_mainOff_add_req_tag;
  assign io_in_ready = mainComp_io_in_ready;
  assign io_pcOut_bits_portId = offComp_io_pcOut_bits_portId;
  assign io_pcOut_bits_pcValue = offComp_io_pcOut_bits_pcValue;
  assign io_out_tag = mainComp_io_out_tag;
  assign io_out_valid = mainComp_io_out_valid;
  assign io_out_bits_centeroidIndex = mainComp_io_out_bits_centeroidIndex;
  assign io_out_bits_pointsFinished = mainComp_io_out_bits_pointsFinished;
  assign io_out_bits_centeroidsFinished = mainComp_io_out_bits_centeroidsFinished;
  assign io_out_bits_point_y = mainComp_io_out_bits_point_y;
  assign io_out_bits_point_z = mainComp_io_out_bits_point_z;
  assign io_out_bits_point_x = mainComp_io_out_bits_point_x;
  KReduce mainComp(.clk(clk), .reset(reset),
       .io_in_ready( mainComp_io_in_ready ),
       .io_in_valid( io_in_valid ),
       .io_in_bits_centeroidsFinished(  ),
       .io_in_bits_pointsFinished(  ),
       .io_in_bits_centeroidIndex( io_in_bits_centeroidIndex ),
       .io_in_bits_point_x(  ),
       .io_in_bits_point_y(  ),
       .io_in_bits_point_z(  ),
       .io_in_tag( io_in_tag ),
       .io_out_ready( io_out_ready ),
       .io_out_valid( mainComp_io_out_valid ),
       .io_out_bits_centeroidsFinished( mainComp_io_out_bits_centeroidsFinished ),
       .io_out_bits_pointsFinished( mainComp_io_out_bits_pointsFinished ),
       .io_out_bits_centeroidIndex( mainComp_io_out_bits_centeroidIndex ),
       .io_out_bits_point_x( mainComp_io_out_bits_point_x ),
       .io_out_bits_point_y( mainComp_io_out_bits_point_y ),
       .io_out_bits_point_z( mainComp_io_out_bits_point_z ),
       .io_out_tag( mainComp_io_out_tag ),
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
       .mainOff_partialAccumulatorMem_req_ready( offComp_io_in_ready ),
       .mainOff_partialAccumulatorMem_req_valid( mainComp_mainOff_partialAccumulatorMem_req_valid ),
       .mainOff_partialAccumulatorMem_req_bits_rw(  ),
       .mainOff_partialAccumulatorMem_req_bits_addr(  ),
       .mainOff_partialAccumulatorMem_req_bits_data_x(  ),
       .mainOff_partialAccumulatorMem_req_bits_data_y(  ),
       .mainOff_partialAccumulatorMem_req_bits_data_z(  ),
       .mainOff_partialAccumulatorMem_req_tag( mainComp_mainOff_partialAccumulatorMem_req_tag ),
       .mainOff_partialAccumulatorMem_rep_ready( mainComp_mainOff_partialAccumulatorMem_rep_ready ),
       .mainOff_partialAccumulatorMem_rep_valid( offComp_io_out_valid ),
       .mainOff_partialAccumulatorMem_rep_bits_data_x( offComp_io_out_bits_data_x ),
       .mainOff_partialAccumulatorMem_rep_bits_data_y( offComp_io_out_bits_data_y ),
       .mainOff_partialAccumulatorMem_rep_bits_data_z( offComp_io_out_bits_data_z ),
       .mainOff_partialAccumulatorMem_rep_tag( offComp_io_out_tag ),
       .mainOff_add_req_ready( mainOff_add_req_ready ),
       .mainOff_add_req_valid( mainComp_mainOff_add_req_valid ),
       .mainOff_add_req_bits_in1(  ),
       .mainOff_add_req_bits_in2(  ),
       .mainOff_add_req_tag( mainComp_mainOff_add_req_tag ),
       .mainOff_add_rep_ready( mainComp_mainOff_add_rep_ready ),
       .mainOff_add_rep_valid( mainOff_add_rep_valid ),
       .mainOff_add_rep_bits_out(  ),
       .mainOff_add_rep_tag( mainOff_add_rep_tag ));
  pAccMem_1 offComp(.clk(clk), .reset(reset),
       .io_in_ready( offComp_io_in_ready ),
       .io_in_valid( mainComp_mainOff_partialAccumulatorMem_req_valid ),
       .io_in_bits_rw(  ),
       .io_in_bits_addr(  ),
       .io_in_bits_data_x(  ),
       .io_in_bits_data_y(  ),
       .io_in_bits_data_z(  ),
       .io_in_tag( mainComp_mainOff_partialAccumulatorMem_req_tag ),
       .io_out_ready( mainComp_mainOff_partialAccumulatorMem_rep_ready ),
       .io_out_valid( offComp_io_out_valid ),
       .io_out_bits_data_x( offComp_io_out_bits_data_x ),
       .io_out_bits_data_y( offComp_io_out_bits_data_y ),
       .io_out_bits_data_z( offComp_io_out_bits_data_z ),
       .io_out_tag( offComp_io_out_tag ),
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

module RREncode_24(
    input  io_valid_0,
    output io_chosen,
    input  io_ready);

  wire choose;

  assign io_chosen = choose;
  assign choose = io_valid_0 ? 1'h0/* 0*/ : 1'h1/* 1*/;
endmodule

module RREncode_25(
    input  io_valid_0,
    output io_chosen,
    input  io_ready);

  wire choose;

  assign io_chosen = choose;
  assign choose = io_valid_0 ? 1'h0/* 0*/ : 1'h1/* 1*/;
endmodule

module RREncode_26(
    input  io_valid_0,
    output io_chosen,
    input  io_ready);

  wire choose;

  assign io_chosen = choose;
  assign choose = io_valid_0 ? 1'h0/* 0*/ : 1'h1/* 1*/;
endmodule

module add_1(input clk, input reset,
    output io_in_ready,
    input  io_in_valid,
    input [63:0] io_in_bits_in1,
    input [63:0] io_in_bits_in2,
    input [4:0] io_in_tag,
    input  io_out_ready,
    output io_out_valid,
    output[63:0] io_out_bits_out,
    output[4:0] io_out_tag,
    input  io_pcIn_valid,
    input  io_pcIn_bits_request,
    input [15:0] io_pcIn_bits_moduleId,
    input [7:0] io_pcIn_bits_portId,
    input [19:0] io_pcIn_bits_pcValue,
    input [3:0] io_pcIn_bits_pcType,
    output io_pcOut_valid,
    output io_pcOut_bits_request,
    output[15:0] io_pcOut_bits_moduleId,
    output[7:0] io_pcOut_bits_portId,
    output[19:0] io_pcOut_bits_pcValue,
    output[3:0] io_pcOut_bits_pcType);

  reg[0:0] R4;
  wire T5;
  wire T6;
  wire T7;
  wire T8;
  wire T9;
  wire T10;
  wire T11;
  reg[19:0] R12;
  wire T13;
  wire[19:0] T14;
  wire[19:0] T15;
  wire[19:0] T16;
  wire[19:0] T17;
  wire[19:0] T18;
  reg[19:0] R19;
  wire T20;
  wire T21;
  wire T22;
  wire T23;
  reg[0:0] R24;
  wire T25;
  wire T26;
  wire T27;
  wire[3:0] T0;
  wire T28;
  wire T29;
  wire T30;
  wire[3:0] T1;
  wire T31;
  wire T32;
  wire T33;
  wire T34;
  wire T35;
  wire T36;
  wire T37;
  wire[1:0] T38;
  wire sThreadEncoder_io_chosen;
  wire T39;
  wire T40;
  reg[7:0] State_0;
  wire T41;
  wire T42;
  wire T43;
  wire T44;
  wire[1:0] T45;
  wire vThreadEncoder_io_chosen;
  wire T46;
  wire AllOffloadsValid_0;
  wire T47;
  reg[0:0] subStateTh_0;
  wire T48;
  wire T49;
  wire T50;
  wire T51;
  wire T52;
  wire T53;
  wire T54;
  wire T55;
  wire T56;
  wire rThreadEncoder_io_chosen;
  wire T57;
  wire AllOffloadsReady;
  wire T58;
  wire T59;
  wire[7:0] T60;
  wire[7:0] T61;
  wire[7:0] T62;
  wire T63;
  wire[1:0] T64;
  wire T65;
  wire T66;
  wire T67;
  wire T68;
  wire[7:0] T69;
  wire T70;
  wire[1:0] T71;
  wire T72;
  wire T73;
  wire T74;
  wire T75;
  wire[7:0] T76;
  wire T77;
  wire[1:0] T78;
  wire T79;
  wire T80;
  wire T81;
  wire T82;
  wire[7:0] T83;
  wire T84;
  wire[1:0] T85;
  wire T86;
  wire T87;
  wire T88;
  wire T89;
  wire[1:0] T90;
  wire T91;
  wire T92;
  wire T93;
  wire[7:0] T94;
  wire[7:0] T95;
  wire T96;
  wire[1:0] T97;
  wire T98;
  wire T99;
  wire T100;
  wire[1:0] T101;
  wire T102;
  wire[4:0] T103;
  wire[4:0] T104;
  reg[4:0] inputTag_0;
  wire[4:0] T105;
  wire T106;
  wire T107;
  wire T108;
  wire[1:0] T109;
  wire T110;
  wire[1:0] T111;
  wire[7:0] T112;
  wire[7:0] T113;
  wire[7:0] T114;
  wire[7:0] T115;
  wire[7:0] T116;
  wire[7:0] T117;
  wire[7:0] T118;
  wire[7:0] T119;
  wire[7:0] T120;
  reg[7:0] EmitReturnState_0;
  wire[7:0] T121;
  wire[7:0] T122;
  wire[7:0] T123;
  wire[7:0] T124;
  wire T125;
  wire T126;
  wire[19:0] T127;
  wire[19:0] T128;
  wire[19:0] T129;
  wire T130;
  wire T131;
  wire[3:0] T2;
  wire T132;
  wire[19:0] T133;
  reg[19:0] R134;
  wire T135;
  wire T136;
  wire T137;
  wire T138;
  wire T139;
  wire T140;
  wire T141;
  wire[19:0] T142;
  wire[19:0] T143;
  wire[19:0] T144;
  wire T145;
  wire T146;
  wire T147;
  wire[19:0] T148;
  wire[19:0] T149;
  wire T150;
  wire[3:0] T3;
  reg[7:0] R151;
  wire T152;
  wire[7:0] T153;
  wire T154;
  reg[3:0] R155;
  wire T156;
  wire[3:0] T157;
  wire T158;
  reg[15:0] R159;
  wire T160;
  wire[15:0] T161;
  wire T162;
  reg[0:0] R163;
  wire T164;
  wire T165;
  wire T166;

  assign io_pcOut_valid = R4;
  assign T5 = T7 || T6;
  assign T6 = ! T7;
  assign T7 = T8 && io_pcIn_bits_request;
  assign T8 = io_pcIn_valid && T9;
  assign T9 = io_pcIn_bits_moduleId == 16'h12/* 18*/;
  assign T10 = T6 ? io_pcIn_valid : T11;
  assign T11 = T7 ? 1'h1/* 1*/ : R4;
  assign io_pcOut_bits_pcValue = R12;
  assign T13 = T7 || T6;
  assign T14 = T6 ? io_pcIn_bits_pcValue : T15;
  assign T15 = T7 ? T16 : R12;
  assign T16 = T150 ? T149 : T17;
  assign T17 = T145 ? R134 : T18;
  assign T18 = T130 ? R19 : 20'h0/* 0*/;
  assign T20 = T29 || T21;
  assign T21 = T126 && T22;
  assign T22 = T35 && T23;
  assign T23 = ! R24;
  assign T25 = T29 || T26;
  assign T26 = T28 && T27;
  assign T27 = io_pcIn_bits_pcType == T0;
  assign T0 = {1'h0/* 0*/, 3'h4/* 4*/};
  assign T28 = io_pcIn_valid && io_pcIn_bits_request;
  assign T29 = T31 && T30;
  assign T30 = io_pcIn_bits_pcType == T1;
  assign T1 = {1'h0/* 0*/, 3'h3/* 3*/};
  assign T31 = io_pcIn_valid && io_pcIn_bits_request;
  assign T32 = T26 ? T34 : T33;
  assign T33 = T29 ? 1'h0/* 0*/ : R24;
  assign T34 = ! R24;
  assign T35 = io_in_valid && T36;
  assign T36 = ! io_in_ready;
  assign io_in_ready = T37;
  assign T37 = T38 != 2'h1/* 1*/;
  assign T38 = {1'h0/* 0*/, sThreadEncoder_io_chosen};
  assign T39 = T125 && T40;
  assign T40 = State_0 == 8'h0/* 0*/;
  assign T41 = T65 || T42;
  assign T42 = T58 && T43;
  assign T43 = T44;
  assign T44 = T45[1'h0/* 0*/:1'h0/* 0*/];
  assign T45 = 1'h1/* 1*/ << vThreadEncoder_io_chosen;
  assign T46 = T47 && AllOffloadsValid_0;
  assign AllOffloadsValid_0 = 1'h1/* 1*/;
  assign T47 = subStateTh_0 == 1'h1/* 1*/;
  assign T48 = T51 ? 1'h1/* 1*/ : T49;
  assign T49 = T50 ? 1'h0/* 0*/ : subStateTh_0;
  assign T50 = 1'h0/* 0*/ == vThreadEncoder_io_chosen;
  assign T51 = T53 && T52;
  assign T52 = State_0 != 8'hff/* 255*/;
  assign T53 = T55 && T54;
  assign T54 = State_0 != 8'h0/* 0*/;
  assign T55 = AllOffloadsReady && T56;
  assign T56 = 1'h0/* 0*/ == rThreadEncoder_io_chosen;
  assign T57 = subStateTh_0 == 1'h0/* 0*/;
  assign AllOffloadsReady = 1'h1/* 1*/;
  assign T58 = T63 && T59;
  assign T59 = T61 == T60;
  assign T60 = {5'h0/* 0*/, 3'h4/* 4*/};
  assign T61 = State_0 & T62;
  assign T62 = {4'h8/* 8*/{T43}};
  assign T63 = T64 != 2'h1/* 1*/;
  assign T64 = {1'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T65 = T72 || T66;
  assign T66 = T67 && T43;
  assign T67 = T70 && T68;
  assign T68 = T61 == T69;
  assign T69 = {6'h0/* 0*/, 2'h3/* 3*/};
  assign T70 = T71 != 2'h1/* 1*/;
  assign T71 = {1'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T72 = T79 || T73;
  assign T73 = T74 && T43;
  assign T74 = T77 && T75;
  assign T75 = T61 == T76;
  assign T76 = {6'h0/* 0*/, 2'h2/* 2*/};
  assign T77 = T78 != 2'h1/* 1*/;
  assign T78 = {1'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T79 = T86 || T80;
  assign T80 = T81 && T43;
  assign T81 = T84 && T82;
  assign T82 = T61 == T83;
  assign T83 = {7'h0/* 0*/, 1'h1/* 1*/};
  assign T84 = T85 != 2'h1/* 1*/;
  assign T85 = {1'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T86 = T98 || T87;
  assign T87 = T91 && T88;
  assign T88 = T89;
  assign T89 = T90[1'h0/* 0*/:1'h0/* 0*/];
  assign T90 = 1'h1/* 1*/ << rThreadEncoder_io_chosen;
  assign T91 = T92 && io_out_ready;
  assign T92 = T96 && T93;
  assign T93 = T94 == 8'hff/* 255*/;
  assign T94 = State_0 & T95;
  assign T95 = {4'h8/* 8*/{T88}};
  assign T96 = T97 != 2'h1/* 1*/;
  assign T97 = {1'h0/* 0*/, rThreadEncoder_io_chosen};
  assign T98 = T102 && T99;
  assign T99 = T100;
  assign T100 = T101[1'h0/* 0*/:1'h0/* 0*/];
  assign T101 = 1'h1/* 1*/ << sThreadEncoder_io_chosen;
  assign T102 = T110 && io_in_valid;
  assign io_out_tag = T103;
  assign T103 = inputTag_0 & T104;
  assign T104 = {3'h5/* 5*/{T88}};
  assign T105 = T98 ? io_in_tag : inputTag_0;
  assign io_out_valid = T106;
  assign T106 = T108 && T107;
  assign T107 = T94 == 8'hff/* 255*/;
  assign T108 = T109 != 2'h1/* 1*/;
  assign T109 = {1'h0/* 0*/, rThreadEncoder_io_chosen};
  assign T110 = T111 != 2'h1/* 1*/;
  assign T111 = {1'h0/* 0*/, sThreadEncoder_io_chosen};
  assign T112 = T42 ? 8'hff/* 255*/ : T113;
  assign T113 = T66 ? T124 : T114;
  assign T114 = T73 ? T123 : T115;
  assign T115 = T80 ? T122 : T116;
  assign T116 = T87 ? T119 : T117;
  assign T117 = T98 ? T118 : State_0;
  assign T118 = {7'h0/* 0*/, 1'h1/* 1*/};
  assign T119 = EmitReturnState_0 & T120;
  assign T120 = {4'h8/* 8*/{T88}};
  assign T121 = T42 ? 8'h0/* 0*/ : EmitReturnState_0;
  assign T122 = {6'h0/* 0*/, 2'h2/* 2*/};
  assign T123 = {6'h0/* 0*/, 2'h3/* 3*/};
  assign T124 = {5'h0/* 0*/, 3'h4/* 4*/};
  assign T125 = subStateTh_0 == 1'h0/* 0*/;
  assign T126 = ! T29;
  assign T127 = T21 ? T129 : T128;
  assign T128 = T29 ? 20'h0/* 0*/ : R19;
  assign T129 = R19 + 20'h1/* 1*/;
  assign T130 = T132 && T131;
  assign T131 = io_pcIn_bits_pcType == T2;
  assign T2 = {1'h0/* 0*/, 3'h1/* 1*/};
  assign T132 = T133 == 20'h1/* 1*/;
  assign T133 = {12'h0/* 0*/, io_pcIn_bits_portId};
  assign T135 = T29 || T136;
  assign T136 = T141 && T137;
  assign T137 = T139 && T138;
  assign T138 = ! R24;
  assign T139 = io_out_valid && T140;
  assign T140 = ! io_out_ready;
  assign T141 = ! T29;
  assign T142 = T136 ? T144 : T143;
  assign T143 = T29 ? 20'h0/* 0*/ : R134;
  assign T144 = R134 + 20'h1/* 1*/;
  assign T145 = T147 && T146;
  assign T146 = io_pcIn_bits_pcType == T2;
  assign T147 = T148 == 20'h2/* 2*/;
  assign T148 = {12'h0/* 0*/, io_pcIn_bits_portId};
  assign T149 = {15'h0/* 0*/, 5'h14/* 20*/};
  assign T150 = io_pcIn_bits_pcType == T3;
  assign T3 = {1'h0/* 0*/, 3'h2/* 2*/};
  assign io_pcOut_bits_portId = R151;
  assign T152 = T7 || T6;
  assign T153 = T154 ? io_pcIn_bits_portId : R151;
  assign T154 = T7 || T6;
  assign io_pcOut_bits_pcType = R155;
  assign T156 = T7 || T6;
  assign T157 = T158 ? io_pcIn_bits_pcType : R155;
  assign T158 = T7 || T6;
  assign io_pcOut_bits_moduleId = R159;
  assign T160 = T7 || T6;
  assign T161 = T162 ? io_pcIn_bits_moduleId : R159;
  assign T162 = T7 || T6;
  assign io_pcOut_bits_request = R163;
  assign T164 = T7 || T6;
  assign T165 = T6 ? io_pcIn_bits_request : T166;
  assign T166 = T7 ? 1'h0/* 0*/ : R163;
  RREncode_24 rThreadEncoder(
       .io_valid_0( T57 ),
       .io_chosen( rThreadEncoder_io_chosen ),
       .io_ready(  ));
  RREncode_25 vThreadEncoder(
       .io_valid_0( T46 ),
       .io_chosen( vThreadEncoder_io_chosen ),
       .io_ready(  ));
  RREncode_26 sThreadEncoder(
       .io_valid_0( T39 ),
       .io_chosen( sThreadEncoder_io_chosen ),
       .io_ready(  ));

  always @(posedge clk) begin
    if(reset) begin
      R4 <= 1'h0/* 0*/;
    end else if(T5) begin
      R4 <= T10;
    end
    if(reset) begin
      R12 <= 20'h0/* 0*/;
    end else if(T13) begin
      R12 <= T14;
    end
    if(reset) begin
      R19 <= 20'h0/* 0*/;
    end else if(T20) begin
      R19 <= T127;
    end
    if(reset) begin
      R24 <= 1'h0/* 0*/;
    end else if(T25) begin
      R24 <= T32;
    end
    if(reset) begin
      State_0 <= 8'h0/* 0*/;
    end else if(T41) begin
      State_0 <= T112;
    end
    subStateTh_0 <= reset ? 1'h0/* 0*/ : T48;
    if(T98) begin
      inputTag_0 <= T105;
    end
    if(reset) begin
      EmitReturnState_0 <= 8'h0/* 0*/;
    end else if(T42) begin
      EmitReturnState_0 <= T121;
    end
    if(reset) begin
      R134 <= 20'h0/* 0*/;
    end else if(T135) begin
      R134 <= T142;
    end
    if(reset) begin
      R151 <= 8'h0/* 0*/;
    end else if(T152) begin
      R151 <= T153;
    end
    if(reset) begin
      R155 <= 4'h0/* 0*/;
    end else if(T156) begin
      R155 <= T157;
    end
    if(reset) begin
      R159 <= 16'h0/* 0*/;
    end else if(T160) begin
      R159 <= T161;
    end
    if(reset) begin
      R163 <= 1'h1/* 1*/;
    end else if(T164) begin
      R163 <= T165;
    end
  end
endmodule

module gOffloadedComponent_5(input clk, input reset,
    output io_in_ready,
    input  io_in_valid,
    input  io_in_bits_centeroidsFinished,
    input  io_in_bits_pointsFinished,
    input [15:0] io_in_bits_centeroidIndex,
    input [63:0] io_in_bits_point_x,
    input [63:0] io_in_bits_point_y,
    input [63:0] io_in_bits_point_z,
    input [4:0] io_in_tag,
    input  io_out_ready,
    output io_out_valid,
    output io_out_bits_centeroidsFinished,
    output io_out_bits_pointsFinished,
    output[15:0] io_out_bits_centeroidIndex,
    output[63:0] io_out_bits_point_x,
    output[63:0] io_out_bits_point_y,
    output[63:0] io_out_bits_point_z,
    output[4:0] io_out_tag,
    input  io_pcIn_valid,
    input  io_pcIn_bits_request,
    input [15:0] io_pcIn_bits_moduleId,
    input [7:0] io_pcIn_bits_portId,
    input [19:0] io_pcIn_bits_pcValue,
    input [3:0] io_pcIn_bits_pcType,
    output io_pcOut_valid,
    output io_pcOut_bits_request,
    output[15:0] io_pcOut_bits_moduleId,
    output[7:0] io_pcOut_bits_portId,
    output[19:0] io_pcOut_bits_pcValue,
    output[3:0] io_pcOut_bits_pcType);

  wire offComp_io_pcOut_valid;
  wire mainComp_io_pcOut_bits_request;
  wire[15:0] mainComp_io_pcOut_bits_moduleId;
  wire mainComp_io_pcOut_valid;
  wire[19:0] offComp_io_pcOut_bits_pcValue;
  wire[3:0] mainComp_io_pcOut_bits_pcType;
  wire mainComp_mainOff_add_rep_ready;
  wire mainComp_mainOff_add_req_valid;
  wire[4:0] offComp_io_out_tag;
  wire[4:0] mainComp_mainOff_add_req_tag;
  wire offComp_io_out_valid;
  wire mainComp_io_in_ready;
  wire offComp_io_in_ready;
  wire[7:0] mainComp_io_pcOut_bits_portId;
  wire[19:0] mainComp_io_pcOut_bits_pcValue;
  wire[7:0] offComp_io_pcOut_bits_portId;
  wire[3:0] offComp_io_pcOut_bits_pcType;
  wire[15:0] offComp_io_pcOut_bits_moduleId;
  wire offComp_io_pcOut_bits_request;
  wire[4:0] mainComp_io_out_tag;
  wire mainComp_io_out_valid;
  wire[15:0] mainComp_io_out_bits_centeroidIndex;
  wire mainComp_io_out_bits_pointsFinished;
  wire mainComp_io_out_bits_centeroidsFinished;
  wire[63:0] mainComp_io_out_bits_point_y;
  wire[63:0] mainComp_io_out_bits_point_z;
  wire[63:0] mainComp_io_out_bits_point_x;

  assign io_pcOut_valid = offComp_io_pcOut_valid;
  assign io_pcOut_bits_pcValue = offComp_io_pcOut_bits_pcValue;
  assign io_in_ready = mainComp_io_in_ready;
  assign io_pcOut_bits_portId = offComp_io_pcOut_bits_portId;
  assign io_pcOut_bits_pcType = offComp_io_pcOut_bits_pcType;
  assign io_pcOut_bits_moduleId = offComp_io_pcOut_bits_moduleId;
  assign io_pcOut_bits_request = offComp_io_pcOut_bits_request;
  assign io_out_tag = mainComp_io_out_tag;
  assign io_out_valid = mainComp_io_out_valid;
  assign io_out_bits_centeroidIndex = mainComp_io_out_bits_centeroidIndex;
  assign io_out_bits_pointsFinished = mainComp_io_out_bits_pointsFinished;
  assign io_out_bits_centeroidsFinished = mainComp_io_out_bits_centeroidsFinished;
  assign io_out_bits_point_y = mainComp_io_out_bits_point_y;
  assign io_out_bits_point_z = mainComp_io_out_bits_point_z;
  assign io_out_bits_point_x = mainComp_io_out_bits_point_x;
  gOffloadedComponent_4 mainComp(.clk(clk), .reset(reset),
       .io_in_ready( mainComp_io_in_ready ),
       .io_in_valid( io_in_valid ),
       .io_in_bits_centeroidsFinished(  ),
       .io_in_bits_pointsFinished(  ),
       .io_in_bits_centeroidIndex( io_in_bits_centeroidIndex ),
       .io_in_bits_point_x(  ),
       .io_in_bits_point_y(  ),
       .io_in_bits_point_z(  ),
       .io_in_tag( io_in_tag ),
       .io_out_ready( io_out_ready ),
       .io_out_valid( mainComp_io_out_valid ),
       .io_out_bits_centeroidsFinished( mainComp_io_out_bits_centeroidsFinished ),
       .io_out_bits_pointsFinished( mainComp_io_out_bits_pointsFinished ),
       .io_out_bits_centeroidIndex( mainComp_io_out_bits_centeroidIndex ),
       .io_out_bits_point_x( mainComp_io_out_bits_point_x ),
       .io_out_bits_point_y( mainComp_io_out_bits_point_y ),
       .io_out_bits_point_z( mainComp_io_out_bits_point_z ),
       .io_out_tag( mainComp_io_out_tag ),
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
       .mainOff_add_req_ready( offComp_io_in_ready ),
       .mainOff_add_req_valid( mainComp_mainOff_add_req_valid ),
       .mainOff_add_req_bits_in1(  ),
       .mainOff_add_req_bits_in2(  ),
       .mainOff_add_req_tag( mainComp_mainOff_add_req_tag ),
       .mainOff_add_rep_ready( mainComp_mainOff_add_rep_ready ),
       .mainOff_add_rep_valid( offComp_io_out_valid ),
       .mainOff_add_rep_bits_out(  ),
       .mainOff_add_rep_tag( offComp_io_out_tag ));
  add_1 offComp(.clk(clk), .reset(reset),
       .io_in_ready( offComp_io_in_ready ),
       .io_in_valid( mainComp_mainOff_add_req_valid ),
       .io_in_bits_in1(  ),
       .io_in_bits_in2(  ),
       .io_in_tag( mainComp_mainOff_add_req_tag ),
       .io_out_ready( mainComp_mainOff_add_rep_ready ),
       .io_out_valid( offComp_io_out_valid ),
       .io_out_bits_out(  ),
       .io_out_tag( offComp_io_out_tag ),
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

module gChainedComponent_1(input clk, input reset,
    output io_in_ready,
    input  io_in_valid,
    input  io_in_bits_centeroidsFinished,
    input  io_in_bits_pointsFinished,
    input [15:0] io_in_bits_centeroidIndex,
    input [63:0] io_in_bits_point_x,
    input [63:0] io_in_bits_point_y,
    input [63:0] io_in_bits_point_z,
    input [4:0] io_in_tag,
    input  io_out_ready,
    output io_out_valid,
    output io_out_bits_centeroidsFinished,
    output io_out_bits_pointsFinished,
    output[15:0] io_out_bits_centeroidIndex,
    output[63:0] io_out_bits_point_x,
    output[63:0] io_out_bits_point_y,
    output[63:0] io_out_bits_point_z,
    output[4:0] io_out_tag,
    input  io_pcIn_valid,
    input  io_pcIn_bits_request,
    input [15:0] io_pcIn_bits_moduleId,
    input [7:0] io_pcIn_bits_portId,
    input [19:0] io_pcIn_bits_pcValue,
    input [3:0] io_pcIn_bits_pcType,
    output io_pcOut_valid,
    output io_pcOut_bits_request,
    output[15:0] io_pcOut_bits_moduleId,
    output[7:0] io_pcOut_bits_portId,
    output[19:0] io_pcOut_bits_pcValue,
    output[3:0] io_pcOut_bits_pcType);

  wire sink_io_pcOut_valid;
  wire source_io_pcOut_bits_request;
  wire[15:0] source_io_pcOut_bits_moduleId;
  wire source_io_pcOut_valid;
  wire[19:0] sink_io_pcOut_bits_pcValue;
  wire[3:0] source_io_pcOut_bits_pcType;
  wire source_io_out_valid;
  wire sink_io_in_ready;
  wire[15:0] source_io_out_bits_centeroidIndex;
  wire[7:0] source_io_pcOut_bits_portId;
  wire[19:0] source_io_pcOut_bits_pcValue;
  wire[7:0] sink_io_pcOut_bits_portId;
  wire[3:0] sink_io_pcOut_bits_pcType;
  wire[15:0] sink_io_pcOut_bits_moduleId;
  wire sink_io_pcOut_bits_request;
  wire[4:0] sink_io_out_tag;
  wire[4:0] source_io_out_tag;
  wire sink_io_out_valid;
  wire[15:0] sink_io_out_bits_centeroidIndex;
  wire sink_io_out_bits_pointsFinished;
  wire sink_io_out_bits_centeroidsFinished;
  wire[63:0] sink_io_out_bits_point_y;
  wire[63:0] sink_io_out_bits_point_z;
  wire[63:0] sink_io_out_bits_point_x;
  wire source_io_in_ready;

  assign io_pcOut_valid = sink_io_pcOut_valid;
  assign io_pcOut_bits_pcValue = sink_io_pcOut_bits_pcValue;
  assign io_pcOut_bits_portId = sink_io_pcOut_bits_portId;
  assign io_pcOut_bits_pcType = sink_io_pcOut_bits_pcType;
  assign io_pcOut_bits_moduleId = sink_io_pcOut_bits_moduleId;
  assign io_pcOut_bits_request = sink_io_pcOut_bits_request;
  assign io_out_tag = sink_io_out_tag;
  assign io_out_valid = sink_io_out_valid;
  assign io_out_bits_centeroidIndex = sink_io_out_bits_centeroidIndex;
  assign io_out_bits_pointsFinished = sink_io_out_bits_pointsFinished;
  assign io_out_bits_centeroidsFinished = sink_io_out_bits_centeroidsFinished;
  assign io_out_bits_point_y = sink_io_out_bits_point_y;
  assign io_out_bits_point_z = sink_io_out_bits_point_z;
  assign io_out_bits_point_x = sink_io_out_bits_point_x;
  assign io_in_ready = source_io_in_ready;
  gChainedComponent source(.clk(clk), .reset(reset),
       .io_in_ready( source_io_in_ready ),
       .io_in_valid( io_in_valid ),
       .io_in_bits_centeroidsFinished( io_in_bits_centeroidsFinished ),
       .io_in_bits_pointsFinished( io_in_bits_pointsFinished ),
       .io_in_bits_centeroidIndex(  ),
       .io_in_bits_point_x(  ),
       .io_in_bits_point_y(  ),
       .io_in_bits_point_z(  ),
       .io_in_tag( io_in_tag ),
       .io_out_ready( sink_io_in_ready ),
       .io_out_valid( source_io_out_valid ),
       .io_out_bits_centeroidsFinished(  ),
       .io_out_bits_pointsFinished(  ),
       .io_out_bits_centeroidIndex( source_io_out_bits_centeroidIndex ),
       .io_out_bits_point_x(  ),
       .io_out_bits_point_y(  ),
       .io_out_bits_point_z(  ),
       .io_out_tag( source_io_out_tag ),
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
       .io_pcOut_bits_pcType( source_io_pcOut_bits_pcType ));
  gOffloadedComponent_5 sink(.clk(clk), .reset(reset),
       .io_in_ready( sink_io_in_ready ),
       .io_in_valid( source_io_out_valid ),
       .io_in_bits_centeroidsFinished(  ),
       .io_in_bits_pointsFinished(  ),
       .io_in_bits_centeroidIndex( source_io_out_bits_centeroidIndex ),
       .io_in_bits_point_x(  ),
       .io_in_bits_point_y(  ),
       .io_in_bits_point_z(  ),
       .io_in_tag( source_io_out_tag ),
       .io_out_ready( io_out_ready ),
       .io_out_valid( sink_io_out_valid ),
       .io_out_bits_centeroidsFinished( sink_io_out_bits_centeroidsFinished ),
       .io_out_bits_pointsFinished( sink_io_out_bits_pointsFinished ),
       .io_out_bits_centeroidIndex( sink_io_out_bits_centeroidIndex ),
       .io_out_bits_point_x( sink_io_out_bits_point_x ),
       .io_out_bits_point_y( sink_io_out_bits_point_y ),
       .io_out_bits_point_z( sink_io_out_bits_point_z ),
       .io_out_tag( sink_io_out_tag ),
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
       .io_pcOut_bits_pcType( sink_io_pcOut_bits_pcType ));
endmodule

module Top(input clk, input reset,
    output io_in_ready,
    input  io_in_valid,
    input  io_in_bits_centeroidsFinished,
    input  io_in_bits_pointsFinished,
    input [15:0] io_in_bits_centeroidIndex,
    input [63:0] io_in_bits_point_x,
    input [63:0] io_in_bits_point_y,
    input [63:0] io_in_bits_point_z,
    input [4:0] io_in_tag,
    input  io_out_ready,
    output io_out_valid,
    output io_out_bits_centeroidsFinished,
    output io_out_bits_pointsFinished,
    output[15:0] io_out_bits_centeroidIndex,
    output[63:0] io_out_bits_point_x,
    output[63:0] io_out_bits_point_y,
    output[63:0] io_out_bits_point_z,
    output[4:0] io_out_tag,
    input  io_pcIn_valid,
    input  io_pcIn_bits_request,
    input [15:0] io_pcIn_bits_moduleId,
    input [7:0] io_pcIn_bits_portId,
    input [19:0] io_pcIn_bits_pcValue,
    input [3:0] io_pcIn_bits_pcType,
    output io_pcOut_valid,
    output io_pcOut_bits_request,
    output[15:0] io_pcOut_bits_moduleId,
    output[7:0] io_pcOut_bits_portId,
    output[19:0] io_pcOut_bits_pcValue,
    output[3:0] io_pcOut_bits_pcType);

  wire generatedTop_io_pcOut_valid;
  wire[19:0] generatedTop_io_pcOut_bits_pcValue;
  wire[7:0] generatedTop_io_pcOut_bits_portId;
  wire[3:0] generatedTop_io_pcOut_bits_pcType;
  wire[15:0] generatedTop_io_pcOut_bits_moduleId;
  wire generatedTop_io_pcOut_bits_request;
  wire[4:0] generatedTop_io_out_tag;
  wire generatedTop_io_out_valid;
  wire[15:0] generatedTop_io_out_bits_centeroidIndex;
  wire generatedTop_io_out_bits_pointsFinished;
  wire generatedTop_io_out_bits_centeroidsFinished;
  wire[63:0] generatedTop_io_out_bits_point_y;
  wire[63:0] generatedTop_io_out_bits_point_z;
  wire[63:0] generatedTop_io_out_bits_point_x;
  wire generatedTop_io_in_ready;

  assign io_pcOut_valid = generatedTop_io_pcOut_valid;
  assign io_pcOut_bits_pcValue = generatedTop_io_pcOut_bits_pcValue;
  assign io_pcOut_bits_portId = generatedTop_io_pcOut_bits_portId;
  assign io_pcOut_bits_pcType = generatedTop_io_pcOut_bits_pcType;
  assign io_pcOut_bits_moduleId = generatedTop_io_pcOut_bits_moduleId;
  assign io_pcOut_bits_request = generatedTop_io_pcOut_bits_request;
  assign io_out_tag = generatedTop_io_out_tag;
  assign io_out_valid = generatedTop_io_out_valid;
  assign io_out_bits_centeroidIndex = generatedTop_io_out_bits_centeroidIndex;
  assign io_out_bits_pointsFinished = generatedTop_io_out_bits_pointsFinished;
  assign io_out_bits_centeroidsFinished = generatedTop_io_out_bits_centeroidsFinished;
  assign io_out_bits_point_y = generatedTop_io_out_bits_point_y;
  assign io_out_bits_point_z = generatedTop_io_out_bits_point_z;
  assign io_out_bits_point_x = generatedTop_io_out_bits_point_x;
  assign io_in_ready = generatedTop_io_in_ready;
  gChainedComponent_1 generatedTop(.clk(clk), .reset(reset),
       .io_in_ready( generatedTop_io_in_ready ),
       .io_in_valid( io_in_valid ),
       .io_in_bits_centeroidsFinished( io_in_bits_centeroidsFinished ),
       .io_in_bits_pointsFinished( io_in_bits_pointsFinished ),
       .io_in_bits_centeroidIndex(  ),
       .io_in_bits_point_x(  ),
       .io_in_bits_point_y(  ),
       .io_in_bits_point_z(  ),
       .io_in_tag( io_in_tag ),
       .io_out_ready( io_out_ready ),
       .io_out_valid( generatedTop_io_out_valid ),
       .io_out_bits_centeroidsFinished( generatedTop_io_out_bits_centeroidsFinished ),
       .io_out_bits_pointsFinished( generatedTop_io_out_bits_pointsFinished ),
       .io_out_bits_centeroidIndex( generatedTop_io_out_bits_centeroidIndex ),
       .io_out_bits_point_x( generatedTop_io_out_bits_point_x ),
       .io_out_bits_point_y( generatedTop_io_out_bits_point_y ),
       .io_out_bits_point_z( generatedTop_io_out_bits_point_z ),
       .io_out_tag( generatedTop_io_out_tag ),
       .io_pcIn_valid( io_pcIn_valid ),
       .io_pcIn_bits_request( io_pcIn_bits_request ),
       .io_pcIn_bits_moduleId( io_pcIn_bits_moduleId ),
       .io_pcIn_bits_portId( io_pcIn_bits_portId ),
       .io_pcIn_bits_pcValue( io_pcIn_bits_pcValue ),
       .io_pcIn_bits_pcType( io_pcIn_bits_pcType ),
       .io_pcOut_valid( generatedTop_io_pcOut_valid ),
       .io_pcOut_bits_request( generatedTop_io_pcOut_bits_request ),
       .io_pcOut_bits_moduleId( generatedTop_io_pcOut_bits_moduleId ),
       .io_pcOut_bits_portId( generatedTop_io_pcOut_bits_portId ),
       .io_pcOut_bits_pcValue( generatedTop_io_pcOut_bits_pcValue ),
       .io_pcOut_bits_pcType( generatedTop_io_pcOut_bits_pcType ));
endmodule

