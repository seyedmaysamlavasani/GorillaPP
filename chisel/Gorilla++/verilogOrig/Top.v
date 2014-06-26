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

module incThroughOffload(input clk, input reset,
    output io_in_ready,
    input  io_in_valid,
    input [31:0] io_in_bits,
    input [4:0] io_in_tag,
    input  io_out_ready,
    output io_out_valid,
    output[31:0] io_out_bits,
    output[4:0] io_out_tag,
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
    input  io_off_incrementFactor_req_ready,
    output io_off_incrementFactor_req_valid,
    output[31:0] io_off_incrementFactor_req_bits,
    output[4:0] io_off_incrementFactor_req_tag,
    output io_off_incrementFactor_rep_ready,
    input  io_off_incrementFactor_rep_valid,
    input [31:0] io_off_incrementFactor_rep_bits,
    input [4:0] io_off_incrementFactor_rep_tag);

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
  wire incrementFactorPort_rep_ready;
  wire incrementFactorPort_req_valid;
  wire T20;
  wire T21;
  wire T22;
  wire[7:0] T23;
  wire[7:0] T24;
  wire[7:0] T25;
  wire T26;
  wire T27;
  wire[1:0] T28;
  wire rThreadEncoder_io_chosen;
  wire T29;
  reg[0:0] subStateTh_0;
  wire T30;
  wire T31;
  wire T32;
  wire vThreadEncoder_io_chosen;
  wire T33;
  wire AllOffloadsValid_0;
  wire T34;
  wire T35;
  reg[0:0] incrementFactorPortHadValidRequest_0;
  wire T36;
  wire T37;
  wire T38;
  wire T39;
  wire[4:0] T40;
  wire T41;
  wire T42;
  wire[4:0] T43;
  wire T44;
  reg[0:0] incrementFactor_valid_received_0;
  wire T45;
  wire T46;
  wire T47;
  wire T48;
  wire[4:0] incrementFactorPort_rep_tag;
  wire incrementFactorPort_rep_valid;
  wire T49;
  wire T50;
  wire[4:0] T51;
  wire T52;
  wire T53;
  wire T54;
  wire T55;
  wire T56;
  reg[7:0] State_0;
  wire T57;
  wire T58;
  wire T59;
  wire T60;
  wire[1:0] T61;
  wire T62;
  wire T63;
  wire[7:0] T64;
  wire[7:0] T65;
  wire[7:0] T66;
  wire T67;
  wire T68;
  wire T69;
  wire T70;
  wire T71;
  wire[31:0] T72;
  wire[31:0] T73;
  reg[31:0] counter_0;
  wire T74;
  wire T75;
  wire T76;
  wire T77;
  wire[7:0] T78;
  wire T79;
  wire T80;
  wire T81;
  wire T82;
  wire[7:0] T83;
  wire T84;
  wire[31:0] T85;
  wire[31:0] T86;
  wire[31:0] T87;
  wire T88;
  wire T89;
  wire T90;
  wire T91;
  wire T92;
  wire T93;
  wire T94;
  wire T95;
  wire T96;
  wire T97;
  wire T98;
  wire T99;
  wire T100;
  wire[1:0] T101;
  wire sThreadEncoder_io_chosen;
  wire T102;
  wire T103;
  wire T104;
  wire T105;
  wire T106;
  wire T107;
  wire[7:0] T108;
  wire[7:0] T109;
  wire[7:0] T110;
  wire[7:0] T111;
  wire[7:0] T112;
  wire[7:0] T113;
  wire[7:0] T114;
  wire[7:0] T115;
  reg[7:0] EmitReturnState_0;
  wire[7:0] T116;
  wire[7:0] T117;
  wire[7:0] T118;
  wire T119;
  wire T120;
  wire T121;
  wire T122;
  wire T123;
  wire T124;
  wire T125;
  wire T126;
  wire[4:0] incrementFactorPort_req_tag;
  wire[4:0] T127;
  reg[7:0] R128;
  wire T129;
  wire[7:0] T130;
  wire T131;
  reg[15:0] R132;
  wire T133;
  wire[15:0] T134;
  wire[15:0] T135;
  wire[15:0] T136;
  reg[15:0] R137;
  wire T138;
  wire T139;
  wire T140;
  wire T141;
  wire T142;
  wire T143;
  wire T144;
  wire[3:0] T145;
  wire T146;
  wire[15:0] T147;
  wire[15:0] T148;
  wire[15:0] T149;
  reg[15:0] R150;
  wire T151;
  wire T152;
  wire T153;
  wire T154;
  wire T155;
  wire T156;
  wire T157;
  wire[3:0] T158;
  wire T159;
  wire[15:0] T160;
  wire[15:0] T161;
  wire[15:0] T162;
  wire T163;
  wire[15:0] T164;
  wire[4:0] T165;
  wire[4:0] T166;
  reg[4:0] inputTag_0;
  wire[4:0] T167;
  wire[31:0] T168;
  wire[31:0] T169;
  reg[31:0] outputReg_0;
  wire[31:0] T170;
  wire[31:0] T171;
  wire[31:0] incrementFactorPort_rep_bits;
  wire[31:0] T172;
  wire[31:0] T173;
  reg[31:0] inputReg_0;
  wire[31:0] T174;

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
  assign io_off_incrementFactor_rep_ready = incrementFactorPort_rep_ready;
  assign incrementFactorPort_rep_ready = 1'h1/* 1*/;
  assign io_off_incrementFactor_req_valid = incrementFactorPort_req_valid;
  assign incrementFactorPort_req_valid = T20;
  assign T20 = T123 && T21;
  assign T21 = T122 && T22;
  assign T22 = T24 == T23;
  assign T23 = {6'h0/* 0*/, 2'h3/* 3*/};
  assign T24 = State_0 & T25;
  assign T25 = {4'h8/* 8*/{T26}};
  assign T26 = T27;
  assign T27 = T28[1'h0/* 0*/:1'h0/* 0*/];
  assign T28 = 1'h1/* 1*/ << rThreadEncoder_io_chosen;
  assign T29 = subStateTh_0 == 1'h0/* 0*/;
  assign T30 = T55 ? 1'h1/* 1*/ : T31;
  assign T31 = T32 ? 1'h0/* 0*/ : subStateTh_0;
  assign T32 = 1'h0/* 0*/ == vThreadEncoder_io_chosen;
  assign T33 = T54 && AllOffloadsValid_0;
  assign AllOffloadsValid_0 = T34;
  assign T34 = T44 || T35;
  assign T35 = ! incrementFactorPortHadValidRequest_0;
  assign T36 = T41 && T37;
  assign T37 = incrementFactorPortHadValidRequest_0 || T38;
  assign T38 = T39 && incrementFactorPort_req_valid;
  assign T39 = 5'h0/* 0*/ == T40;
  assign T40 = {4'h0/* 0*/, rThreadEncoder_io_chosen};
  assign T41 = ! T42;
  assign T42 = T43 == 5'h0/* 0*/;
  assign T43 = {4'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T44 = T52 || incrementFactor_valid_received_0;
  assign T45 = T49 && T46;
  assign T46 = incrementFactor_valid_received_0 || T47;
  assign T47 = incrementFactorPort_rep_valid && T48;
  assign T48 = incrementFactorPort_rep_tag == 5'h0/* 0*/;
  assign incrementFactorPort_rep_tag = io_off_incrementFactor_rep_tag;
  assign incrementFactorPort_rep_valid = io_off_incrementFactor_rep_valid;
  assign T49 = ! T50;
  assign T50 = T51 == 5'h0/* 0*/;
  assign T51 = {4'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T52 = incrementFactorPort_rep_valid && T53;
  assign T53 = incrementFactorPort_rep_tag == 5'h0/* 0*/;
  assign T54 = subStateTh_0 == 1'h1/* 1*/;
  assign T55 = T119 && T56;
  assign T56 = State_0 != 8'hff/* 255*/;
  assign T57 = T68 || T58;
  assign T58 = T62 && T59;
  assign T59 = T60;
  assign T60 = T61[1'h0/* 0*/:1'h0/* 0*/];
  assign T61 = 1'h1/* 1*/ << vThreadEncoder_io_chosen;
  assign T62 = T67 && T63;
  assign T63 = T65 == T64;
  assign T64 = {6'h0/* 0*/, 2'h3/* 3*/};
  assign T65 = State_0 & T66;
  assign T66 = {4'h8/* 8*/{T59}};
  assign T67 = vThreadEncoder_io_chosen != 1'h1/* 1*/;
  assign T68 = T88 || T69;
  assign T69 = T70 && T59;
  assign T70 = T76 && T71;
  assign T71 = T72 == 32'h0/* 0*/;
  assign T72 = counter_0 & T73;
  assign T73 = {6'h20/* 32*/{T59}};
  assign T74 = T80 || T75;
  assign T75 = T76 && T59;
  assign T76 = T79 && T77;
  assign T77 = T65 == T78;
  assign T78 = {6'h0/* 0*/, 2'h2/* 2*/};
  assign T79 = vThreadEncoder_io_chosen != 1'h1/* 1*/;
  assign T80 = T81 && T59;
  assign T81 = T84 && T82;
  assign T82 = T65 == T83;
  assign T83 = {7'h0/* 0*/, 1'h1/* 1*/};
  assign T84 = vThreadEncoder_io_chosen != 1'h1/* 1*/;
  assign T85 = T75 ? T87 : T86;
  assign T86 = T80 ? 32'ha/* 10*/ : counter_0;
  assign T87 = T72 - 32'h1/* 1*/;
  assign T88 = T89 || T80;
  assign T89 = T98 || T90;
  assign T90 = T91 && T26;
  assign T91 = T95 && io_out_ready;
  assign io_out_valid = T92;
  assign T92 = T94 && T93;
  assign T93 = T24 == 8'hff/* 255*/;
  assign T94 = rThreadEncoder_io_chosen != 1'h1/* 1*/;
  assign T95 = T97 && T96;
  assign T96 = T24 == 8'hff/* 255*/;
  assign T97 = rThreadEncoder_io_chosen != 1'h1/* 1*/;
  assign T98 = T105 && T99;
  assign T99 = T100;
  assign T100 = T101[1'h0/* 0*/:1'h0/* 0*/];
  assign T101 = 1'h1/* 1*/ << sThreadEncoder_io_chosen;
  assign T102 = T104 && T103;
  assign T103 = State_0 == 8'h0/* 0*/;
  assign T104 = subStateTh_0 == 1'h0/* 0*/;
  assign T105 = T107 && io_in_valid;
  assign io_in_ready = T106;
  assign T106 = sThreadEncoder_io_chosen != 1'h1/* 1*/;
  assign T107 = sThreadEncoder_io_chosen != 1'h1/* 1*/;
  assign T108 = T58 ? 8'hff/* 255*/ : T109;
  assign T109 = T69 ? T118 : T110;
  assign T110 = T80 ? T117 : T111;
  assign T111 = T90 ? T114 : T112;
  assign T112 = T98 ? T113 : State_0;
  assign T113 = {7'h0/* 0*/, 1'h1/* 1*/};
  assign T114 = EmitReturnState_0 & T115;
  assign T115 = {4'h8/* 8*/{T26}};
  assign T116 = T58 ? 8'h0/* 0*/ : EmitReturnState_0;
  assign T117 = {6'h0/* 0*/, 2'h2/* 2*/};
  assign T118 = {6'h0/* 0*/, 2'h3/* 3*/};
  assign T119 = T121 && T120;
  assign T120 = State_0 != 8'h0/* 0*/;
  assign T121 = 1'h0/* 0*/ == rThreadEncoder_io_chosen;
  assign T122 = rThreadEncoder_io_chosen != 1'h1/* 1*/;
  assign T123 = T126 && T124;
  assign T124 = ! T125;
  assign T125 = incrementFactor_valid_received_0 & T26;
  assign T126 = rThreadEncoder_io_chosen != 1'h1/* 1*/;
  assign io_off_incrementFactor_req_tag = incrementFactorPort_req_tag;
  assign incrementFactorPort_req_tag = T127;
  assign T127 = {4'h0/* 0*/, rThreadEncoder_io_chosen};
  assign io_pcOut_bits_portId = R128;
  assign T129 = T3 || T2;
  assign T130 = T131 ? pcIn0_bits_portId : R128;
  assign T131 = T3 || T2;
  assign io_pcOut_bits_pcValue = R132;
  assign T133 = T3 || T2;
  assign T134 = T2 ? pcIn0_bits_pcValue : T135;
  assign T135 = T3 ? T136 : R132;
  assign T136 = T163 ? R150 : R137;
  assign T138 = T143 || T139;
  assign T139 = T142 && T140;
  assign T140 = io_out_valid && T141;
  assign T141 = ! io_out_ready;
  assign T142 = ! T143;
  assign T143 = T146 && T144;
  assign T144 = pcIn0_bits_pcType == T145;
  assign T145 = {2'h0/* 0*/, 2'h2/* 2*/};
  assign T146 = pcIn0_valid && pcIn0_bits_request;
  assign T147 = T139 ? T149 : T148;
  assign T148 = T143 ? 16'h0/* 0*/ : R137;
  assign T149 = R137 + 16'h1/* 1*/;
  assign T151 = T156 || T152;
  assign T152 = T155 && T153;
  assign T153 = io_in_valid && T154;
  assign T154 = ! io_in_ready;
  assign T155 = ! T156;
  assign T156 = T159 && T157;
  assign T157 = pcIn0_bits_pcType == T158;
  assign T158 = {2'h0/* 0*/, 2'h2/* 2*/};
  assign T159 = pcIn0_valid && pcIn0_bits_request;
  assign T160 = T152 ? T162 : T161;
  assign T161 = T156 ? 16'h0/* 0*/ : R150;
  assign T162 = R150 + 16'h1/* 1*/;
  assign T163 = T164 == 16'h1/* 1*/;
  assign T164 = {8'h0/* 0*/, pcIn0_bits_portId};
  assign io_out_tag = T165;
  assign T165 = inputTag_0 & T166;
  assign T166 = {3'h5/* 5*/{T26}};
  assign T167 = T98 ? io_in_tag : inputTag_0;
  assign io_out_bits = T168;
  assign T168 = outputReg_0 & T169;
  assign T169 = {6'h20/* 32*/{T26}};
  assign T170 = T58 ? T171 : outputReg_0;
  assign T171 = T172 + incrementFactorPort_rep_bits;
  assign incrementFactorPort_rep_bits = io_off_incrementFactor_rep_bits;
  assign T172 = inputReg_0 & T173;
  assign T173 = {6'h20/* 32*/{T59}};
  assign T174 = T98 ? io_in_bits : inputReg_0;
  RREncode rThreadEncoder(
       .io_valid_0( T29 ),
       .io_chosen( rThreadEncoder_io_chosen ),
       .io_ready(  ));
  RREncode_1 vThreadEncoder(
       .io_valid_0( T33 ),
       .io_chosen( vThreadEncoder_io_chosen ),
       .io_ready(  ));
  RREncode_2 sThreadEncoder(
       .io_valid_0( T102 ),
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
    subStateTh_0 <= reset ? 1'h0/* 0*/ : T30;
    incrementFactorPortHadValidRequest_0 <= reset ? 1'h0/* 0*/ : T36;
    incrementFactor_valid_received_0 <= reset ? 1'h0/* 0*/ : T45;
    if(reset) begin
      State_0 <= 8'h0/* 0*/;
    end else if(T57) begin
      State_0 <= T108;
    end
    if(T74) begin
      counter_0 <= T85;
    end
    if(reset) begin
      EmitReturnState_0 <= 8'h0/* 0*/;
    end else if(T58) begin
      EmitReturnState_0 <= T116;
    end
    if(reset) begin
      R128 <= 8'h0/* 0*/;
    end else if(T129) begin
      R128 <= T130;
    end
    if(reset) begin
      R132 <= 16'h0/* 0*/;
    end else if(T133) begin
      R132 <= T134;
    end
    if(reset) begin
      R137 <= 16'h0/* 0*/;
    end else if(T138) begin
      R137 <= T147;
    end
    if(reset) begin
      R150 <= 16'h0/* 0*/;
    end else if(T151) begin
      R150 <= T160;
    end
    if(T98) begin
      inputTag_0 <= T167;
    end
    if(T58) begin
      outputReg_0 <= T170;
    end
    if(T98) begin
      inputReg_0 <= T174;
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

module incThroughOffload_1(input clk, input reset,
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
    output pcOutN_valid,
    output pcOutN_bits_request,
    output[15:0] pcOutN_bits_moduleId,
    output[7:0] pcOutN_bits_portId,
    output[15:0] pcOutN_bits_pcValue,
    output[3:0] pcOutN_bits_pcType,
    input  io_off_incrementFactor_req_ready,
    output io_off_incrementFactor_req_valid,
    output[31:0] io_off_incrementFactor_req_bits,
    output[4:0] io_off_incrementFactor_req_tag,
    output io_off_incrementFactor_rep_ready,
    input  io_off_incrementFactor_rep_valid,
    input [31:0] io_off_incrementFactor_rep_bits,
    input [4:0] io_off_incrementFactor_rep_tag);

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
  wire incrementFactorPort_rep_ready;
  wire incrementFactorPort_req_valid;
  wire T20;
  wire T21;
  wire T22;
  wire[7:0] T23;
  wire[7:0] T24;
  wire[7:0] T25;
  wire T26;
  wire T27;
  wire[1:0] T28;
  wire rThreadEncoder_io_chosen;
  wire T29;
  reg[0:0] subStateTh_0;
  wire T30;
  wire T31;
  wire T32;
  wire vThreadEncoder_io_chosen;
  wire T33;
  wire AllOffloadsValid_0;
  wire T34;
  wire T35;
  reg[0:0] incrementFactorPortHadValidRequest_0;
  wire T36;
  wire T37;
  wire T38;
  wire T39;
  wire[4:0] T40;
  wire T41;
  wire T42;
  wire[4:0] T43;
  wire T44;
  reg[0:0] incrementFactor_valid_received_0;
  wire T45;
  wire T46;
  wire T47;
  wire T48;
  wire[4:0] incrementFactorPort_rep_tag;
  wire T49;
  wire T50;
  wire T51;
  wire T52;
  wire sThreadEncoder_io_chosen;
  wire T53;
  wire T54;
  reg[7:0] State_0;
  wire T55;
  wire T56;
  wire T57;
  wire T58;
  wire[1:0] T59;
  wire T60;
  wire T61;
  wire[7:0] T62;
  wire[7:0] T63;
  wire[7:0] T64;
  wire T65;
  wire T66;
  wire T67;
  wire T68;
  wire T69;
  wire[31:0] T70;
  wire[31:0] T71;
  reg[31:0] counter_0;
  wire T72;
  wire T73;
  wire T74;
  wire T75;
  wire[7:0] T76;
  wire T77;
  wire T78;
  wire T79;
  wire T80;
  wire[7:0] T81;
  wire T82;
  wire[31:0] T83;
  wire[31:0] T84;
  wire[31:0] T85;
  wire T86;
  wire T87;
  wire T88;
  wire T89;
  wire T90;
  wire T91;
  wire T92;
  wire T93;
  wire T94;
  wire T95;
  wire[1:0] T96;
  wire T97;
  wire T98;
  wire[7:0] T99;
  wire[7:0] T100;
  wire[7:0] T101;
  wire[7:0] T102;
  wire[7:0] T103;
  wire[7:0] T104;
  wire[7:0] T105;
  wire[7:0] T106;
  reg[7:0] EmitReturnState_0;
  wire[7:0] T107;
  wire[7:0] T108;
  wire[7:0] T109;
  wire T110;
  wire[4:0] incrementFactorPort_req_tag;
  wire[4:0] T111;
  wire incrementFactorPort_rep_valid;
  wire T112;
  wire T113;
  wire[4:0] T114;
  wire T115;
  wire T116;
  wire T117;
  wire T118;
  wire T119;
  wire T120;
  wire T121;
  wire T122;
  wire T123;
  wire T124;
  wire T125;
  wire T126;
  wire T127;
  reg[7:0] R128;
  wire T129;
  wire[7:0] T130;
  wire T131;
  reg[15:0] R132;
  wire T133;
  wire[15:0] T134;
  wire[15:0] T135;
  wire[15:0] T136;
  reg[15:0] R137;
  wire T138;
  wire T139;
  wire T140;
  wire T141;
  wire T142;
  wire T143;
  wire T144;
  wire[3:0] T145;
  wire T146;
  wire[15:0] T147;
  wire[15:0] T148;
  wire[15:0] T149;
  reg[15:0] R150;
  wire T151;
  wire T152;
  wire T153;
  wire T154;
  wire T155;
  wire T156;
  wire T157;
  wire[3:0] T158;
  wire T159;
  wire[15:0] T160;
  wire[15:0] T161;
  wire[15:0] T162;
  wire T163;
  wire[15:0] T164;
  wire[4:0] T165;
  wire[4:0] T166;
  reg[4:0] inputTag_0;
  wire[4:0] T167;
  wire[31:0] T168;
  wire[31:0] T169;
  reg[31:0] outputReg_0;
  wire[31:0] T170;
  wire[31:0] T171;
  wire[31:0] incrementFactorPort_rep_bits;
  wire[31:0] T172;
  wire[31:0] T173;
  reg[31:0] inputReg_0;
  wire[31:0] T174;

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
  assign io_off_incrementFactor_rep_ready = incrementFactorPort_rep_ready;
  assign incrementFactorPort_rep_ready = 1'h1/* 1*/;
  assign io_off_incrementFactor_req_valid = incrementFactorPort_req_valid;
  assign incrementFactorPort_req_valid = T20;
  assign T20 = T124 && T21;
  assign T21 = T123 && T22;
  assign T22 = T24 == T23;
  assign T23 = {6'h0/* 0*/, 2'h3/* 3*/};
  assign T24 = State_0 & T25;
  assign T25 = {4'h8/* 8*/{T26}};
  assign T26 = T27;
  assign T27 = T28[1'h0/* 0*/:1'h0/* 0*/];
  assign T28 = 1'h1/* 1*/ << rThreadEncoder_io_chosen;
  assign T29 = subStateTh_0 == 1'h0/* 0*/;
  assign T30 = T118 ? 1'h1/* 1*/ : T31;
  assign T31 = T32 ? 1'h0/* 0*/ : subStateTh_0;
  assign T32 = 1'h0/* 0*/ == vThreadEncoder_io_chosen;
  assign T33 = T117 && AllOffloadsValid_0;
  assign AllOffloadsValid_0 = T34;
  assign T34 = T44 || T35;
  assign T35 = ! incrementFactorPortHadValidRequest_0;
  assign T36 = T41 && T37;
  assign T37 = incrementFactorPortHadValidRequest_0 || T38;
  assign T38 = T39 && incrementFactorPort_req_valid;
  assign T39 = 5'h0/* 0*/ == T40;
  assign T40 = {4'h0/* 0*/, rThreadEncoder_io_chosen};
  assign T41 = ! T42;
  assign T42 = T43 == 5'h0/* 0*/;
  assign T43 = {4'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T44 = T115 || incrementFactor_valid_received_0;
  assign T45 = T112 && T46;
  assign T46 = incrementFactor_valid_received_0 || T47;
  assign T47 = incrementFactorPort_rep_valid && T48;
  assign T48 = incrementFactorPort_rep_tag == 5'h0/* 0*/;
  assign incrementFactorPort_rep_tag = io_off_incrementFactor_rep_tag;
  assign io_out_valid = T49;
  assign T49 = T51 && T50;
  assign T50 = T24 == 8'hff/* 255*/;
  assign T51 = rThreadEncoder_io_chosen != 1'h1/* 1*/;
  assign io_in_ready = T52;
  assign T52 = sThreadEncoder_io_chosen != 1'h1/* 1*/;
  assign T53 = T110 && T54;
  assign T54 = State_0 == 8'h0/* 0*/;
  assign T55 = T66 || T56;
  assign T56 = T60 && T57;
  assign T57 = T58;
  assign T58 = T59[1'h0/* 0*/:1'h0/* 0*/];
  assign T59 = 1'h1/* 1*/ << vThreadEncoder_io_chosen;
  assign T60 = T65 && T61;
  assign T61 = T63 == T62;
  assign T62 = {6'h0/* 0*/, 2'h3/* 3*/};
  assign T63 = State_0 & T64;
  assign T64 = {4'h8/* 8*/{T57}};
  assign T65 = vThreadEncoder_io_chosen != 1'h1/* 1*/;
  assign T66 = T86 || T67;
  assign T67 = T68 && T57;
  assign T68 = T74 && T69;
  assign T69 = T70 == 32'h0/* 0*/;
  assign T70 = counter_0 & T71;
  assign T71 = {6'h20/* 32*/{T57}};
  assign T72 = T78 || T73;
  assign T73 = T74 && T57;
  assign T74 = T77 && T75;
  assign T75 = T63 == T76;
  assign T76 = {6'h0/* 0*/, 2'h2/* 2*/};
  assign T77 = vThreadEncoder_io_chosen != 1'h1/* 1*/;
  assign T78 = T79 && T57;
  assign T79 = T82 && T80;
  assign T80 = T63 == T81;
  assign T81 = {7'h0/* 0*/, 1'h1/* 1*/};
  assign T82 = vThreadEncoder_io_chosen != 1'h1/* 1*/;
  assign T83 = T73 ? T85 : T84;
  assign T84 = T78 ? 32'ha/* 10*/ : counter_0;
  assign T85 = T70 - 32'h1/* 1*/;
  assign T86 = T87 || T78;
  assign T87 = T93 || T88;
  assign T88 = T89 && T26;
  assign T89 = T90 && io_out_ready;
  assign T90 = T92 && T91;
  assign T91 = T24 == 8'hff/* 255*/;
  assign T92 = rThreadEncoder_io_chosen != 1'h1/* 1*/;
  assign T93 = T97 && T94;
  assign T94 = T95;
  assign T95 = T96[1'h0/* 0*/:1'h0/* 0*/];
  assign T96 = 1'h1/* 1*/ << sThreadEncoder_io_chosen;
  assign T97 = T98 && io_in_valid;
  assign T98 = sThreadEncoder_io_chosen != 1'h1/* 1*/;
  assign T99 = T56 ? 8'hff/* 255*/ : T100;
  assign T100 = T67 ? T109 : T101;
  assign T101 = T78 ? T108 : T102;
  assign T102 = T88 ? T105 : T103;
  assign T103 = T93 ? T104 : State_0;
  assign T104 = {7'h0/* 0*/, 1'h1/* 1*/};
  assign T105 = EmitReturnState_0 & T106;
  assign T106 = {4'h8/* 8*/{T26}};
  assign T107 = T56 ? 8'h0/* 0*/ : EmitReturnState_0;
  assign T108 = {6'h0/* 0*/, 2'h2/* 2*/};
  assign T109 = {6'h0/* 0*/, 2'h3/* 3*/};
  assign T110 = subStateTh_0 == 1'h0/* 0*/;
  assign io_off_incrementFactor_req_tag = incrementFactorPort_req_tag;
  assign incrementFactorPort_req_tag = T111;
  assign T111 = {4'h0/* 0*/, rThreadEncoder_io_chosen};
  assign incrementFactorPort_rep_valid = io_off_incrementFactor_rep_valid;
  assign T112 = ! T113;
  assign T113 = T114 == 5'h0/* 0*/;
  assign T114 = {4'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T115 = incrementFactorPort_rep_valid && T116;
  assign T116 = incrementFactorPort_rep_tag == 5'h0/* 0*/;
  assign T117 = subStateTh_0 == 1'h1/* 1*/;
  assign T118 = T120 && T119;
  assign T119 = State_0 != 8'hff/* 255*/;
  assign T120 = T122 && T121;
  assign T121 = State_0 != 8'h0/* 0*/;
  assign T122 = 1'h0/* 0*/ == rThreadEncoder_io_chosen;
  assign T123 = rThreadEncoder_io_chosen != 1'h1/* 1*/;
  assign T124 = T127 && T125;
  assign T125 = ! T126;
  assign T126 = incrementFactor_valid_received_0 & T26;
  assign T127 = rThreadEncoder_io_chosen != 1'h1/* 1*/;
  assign pcOutN_bits_portId = R128;
  assign T129 = T3 || T2;
  assign T130 = T131 ? io_pcIn_bits_portId : R128;
  assign T131 = T3 || T2;
  assign pcOutN_bits_pcValue = R132;
  assign T133 = T3 || T2;
  assign T134 = T2 ? io_pcIn_bits_pcValue : T135;
  assign T135 = T3 ? T136 : R132;
  assign T136 = T163 ? R150 : R137;
  assign T138 = T143 || T139;
  assign T139 = T142 && T140;
  assign T140 = io_out_valid && T141;
  assign T141 = ! io_out_ready;
  assign T142 = ! T143;
  assign T143 = T146 && T144;
  assign T144 = io_pcIn_bits_pcType == T145;
  assign T145 = {2'h0/* 0*/, 2'h2/* 2*/};
  assign T146 = io_pcIn_valid && io_pcIn_bits_request;
  assign T147 = T139 ? T149 : T148;
  assign T148 = T143 ? 16'h0/* 0*/ : R137;
  assign T149 = R137 + 16'h1/* 1*/;
  assign T151 = T156 || T152;
  assign T152 = T155 && T153;
  assign T153 = io_in_valid && T154;
  assign T154 = ! io_in_ready;
  assign T155 = ! T156;
  assign T156 = T159 && T157;
  assign T157 = io_pcIn_bits_pcType == T158;
  assign T158 = {2'h0/* 0*/, 2'h2/* 2*/};
  assign T159 = io_pcIn_valid && io_pcIn_bits_request;
  assign T160 = T152 ? T162 : T161;
  assign T161 = T156 ? 16'h0/* 0*/ : R150;
  assign T162 = R150 + 16'h1/* 1*/;
  assign T163 = T164 == 16'h1/* 1*/;
  assign T164 = {8'h0/* 0*/, io_pcIn_bits_portId};
  assign io_out_tag = T165;
  assign T165 = inputTag_0 & T166;
  assign T166 = {3'h5/* 5*/{T26}};
  assign T167 = T93 ? io_in_tag : inputTag_0;
  assign io_out_bits = T168;
  assign T168 = outputReg_0 & T169;
  assign T169 = {6'h20/* 32*/{T26}};
  assign T170 = T56 ? T171 : outputReg_0;
  assign T171 = T172 + incrementFactorPort_rep_bits;
  assign incrementFactorPort_rep_bits = io_off_incrementFactor_rep_bits;
  assign T172 = inputReg_0 & T173;
  assign T173 = {6'h20/* 32*/{T57}};
  assign T174 = T93 ? io_in_bits : inputReg_0;
  RREncode_3 rThreadEncoder(
       .io_valid_0( T29 ),
       .io_chosen( rThreadEncoder_io_chosen ),
       .io_ready(  ));
  RREncode_4 vThreadEncoder(
       .io_valid_0( T33 ),
       .io_chosen( vThreadEncoder_io_chosen ),
       .io_ready(  ));
  RREncode_5 sThreadEncoder(
       .io_valid_0( T53 ),
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
    subStateTh_0 <= reset ? 1'h0/* 0*/ : T30;
    incrementFactorPortHadValidRequest_0 <= reset ? 1'h0/* 0*/ : T36;
    incrementFactor_valid_received_0 <= reset ? 1'h0/* 0*/ : T45;
    if(reset) begin
      State_0 <= 8'h0/* 0*/;
    end else if(T55) begin
      State_0 <= T99;
    end
    if(T72) begin
      counter_0 <= T83;
    end
    if(reset) begin
      EmitReturnState_0 <= 8'h0/* 0*/;
    end else if(T56) begin
      EmitReturnState_0 <= T107;
    end
    if(reset) begin
      R128 <= 8'h0/* 0*/;
    end else if(T129) begin
      R128 <= T130;
    end
    if(reset) begin
      R132 <= 16'h0/* 0*/;
    end else if(T133) begin
      R132 <= T134;
    end
    if(reset) begin
      R137 <= 16'h0/* 0*/;
    end else if(T138) begin
      R137 <= T147;
    end
    if(reset) begin
      R150 <= 16'h0/* 0*/;
    end else if(T151) begin
      R150 <= T160;
    end
    if(T93) begin
      inputTag_0 <= T167;
    end
    if(T56) begin
      outputReg_0 <= T170;
    end
    if(T93) begin
      inputReg_0 <= T174;
    end
  end
endmodule

module gRRDistributor(input clk, input reset,
    input  io_out_0_ready,
    output io_out_0_valid,
    output[31:0] io_out_0_bits,
    output[4:0] io_out_0_tag,
    input  io_out_1_ready,
    output io_out_1_valid,
    output[31:0] io_out_1_bits,
    output[4:0] io_out_1_tag,
    output io_in_ready,
    input  io_in_valid,
    input [31:0] io_in_bits,
    input [4:0] io_in_tag,
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
  wire choose;
  wire T19;
  wire T20;
  wire T21;
  wire T22;

  assign io_out_0_valid = T0;
  assign T0 = T1 && io_in_valid;
  assign T1 = T22 || T2;
  assign T2 = ! T3;
  assign T3 = T13 || T4;
  assign T4 = io_out_1_ready && T5;
  assign T5 = 1'h1/* 1*/ > last_grant;
  assign T6 = io_in_valid && io_in_ready;
  assign io_in_ready = T7;
  assign T7 = io_out_0_ready || io_out_1_ready;
  assign io_out_1_valid = T8;
  assign T8 = T9 && io_in_valid;
  assign T9 = T15 || T10;
  assign T10 = ! T11;
  assign T11 = T12 || io_out_0_ready;
  assign T12 = T13 || T4;
  assign T13 = io_out_0_ready && T14;
  assign T14 = 1'h0/* 0*/ > last_grant;
  assign T15 = T17 && T16;
  assign T16 = 1'h1/* 1*/ > last_grant;
  assign T17 = ! T13;
  assign T18 = T6 ? choose : last_grant;
  assign choose = T20 ? 1'h1/* 1*/ : T19;
  assign T19 = io_out_0_ready ? 1'h0/* 0*/ : 1'h1/* 1*/;
  assign T20 = io_out_1_ready && T21;
  assign T21 = 1'h1/* 1*/ > last_grant;
  assign T22 = 1'h0/* 0*/ > last_grant;
  assign io_out_1_tag = io_in_tag;
  assign io_out_0_tag = io_in_tag;
  assign io_out_1_bits = io_in_bits;
  assign io_out_0_bits = io_in_bits;

  always @(posedge clk) begin
    if(reset) begin
      last_grant <= 1'h0/* 0*/;
    end else if(T6) begin
      last_grant <= T18;
    end
  end
endmodule

module RRDistributorComponent(input clk, input reset,
    input  io_out_0_ready,
    output io_out_0_valid,
    output[31:0] io_out_0_bits,
    output[4:0] io_out_0_tag,
    input  io_out_1_ready,
    output io_out_1_valid,
    output[31:0] io_out_1_bits,
    output[4:0] io_out_1_tag,
    output io_in_ready,
    input  io_in_valid,
    input [31:0] io_in_bits,
    input [4:0] io_in_tag,
    output io_chosen);

  wire rrDist_io_out_0_valid;
  wire rrDist_io_out_1_valid;
  wire[4:0] rrDist_io_out_1_tag;
  wire[4:0] rrDist_io_out_0_tag;
  wire[31:0] rrDist_io_out_1_bits;
  wire[31:0] rrDist_io_out_0_bits;
  wire rrDist_io_in_ready;

  assign io_out_0_valid = rrDist_io_out_0_valid;
  assign io_out_1_valid = rrDist_io_out_1_valid;
  assign io_out_1_tag = rrDist_io_out_1_tag;
  assign io_out_0_tag = rrDist_io_out_0_tag;
  assign io_out_1_bits = rrDist_io_out_1_bits;
  assign io_out_0_bits = rrDist_io_out_0_bits;
  assign io_in_ready = rrDist_io_in_ready;
  gRRDistributor rrDist(.clk(clk), .reset(reset),
       .io_out_0_ready( io_out_0_ready ),
       .io_out_0_valid( rrDist_io_out_0_valid ),
       .io_out_0_bits( rrDist_io_out_0_bits ),
       .io_out_0_tag( rrDist_io_out_0_tag ),
       .io_out_1_ready( io_out_1_ready ),
       .io_out_1_valid( rrDist_io_out_1_valid ),
       .io_out_1_bits( rrDist_io_out_1_bits ),
       .io_out_1_tag( rrDist_io_out_1_tag ),
       .io_in_ready( rrDist_io_in_ready ),
       .io_in_valid( io_in_valid ),
       .io_in_bits( io_in_bits ),
       .io_in_tag( io_in_tag ),
       .io_chosen(  ));
endmodule

module gRRArbiter(input clk, input reset,
    input  io_out_ready,
    output io_out_valid,
    output[31:0] io_out_bits,
    output[4:0] io_out_tag,
    output io_in_0_ready,
    input  io_in_0_valid,
    input [31:0] io_in_0_bits,
    input [4:0] io_in_0_tag,
    output io_in_1_ready,
    input  io_in_1_valid,
    input [31:0] io_in_1_bits,
    input [4:0] io_in_1_tag,
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
  wire[4:0] T23;
  wire[6:0] T24;
  wire[6:0] T25;
  wire[6:0] T26;
  wire T27;
  wire[1:0] T28;
  wire[2:0] T29;
  wire[6:0] tvec_1;
  wire[6:0] T30;
  wire[6:0] T31;
  wire[6:0] T32;
  wire T33;
  wire[6:0] tvec_0;
  wire[6:0] T34;
  wire[31:0] T35;
  wire[31:0] T36;
  wire[31:0] T37;
  wire T38;
  wire[1:0] T39;
  wire[2:0] T40;
  wire[31:0] dvec_1;
  wire[31:0] T41;
  wire[31:0] T42;
  wire T43;
  wire[31:0] dvec_0;

  assign io_in_0_ready = T0;
  assign T0 = T1 && io_out_ready;
  assign T1 = T14 || T2;
  assign T2 = ! T3;
  assign T3 = T12 || T4;
  assign T4 = io_in_1_valid && T5;
  assign T5 = 1'h1/* 1*/ > last_grant;
  assign T6 = io_out_valid && io_out_ready;
  assign io_out_valid = T7;
  assign T7 = io_in_0_valid || io_in_1_valid;
  assign T8 = T6 ? choose : last_grant;
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
  assign T19 = T12 || T4;
  assign T20 = T22 && T21;
  assign T21 = 1'h1/* 1*/ > last_grant;
  assign T22 = ! T12;
  assign io_out_tag = T23;
  assign T23 = T24[3'h4/* 4*/:1'h0/* 0*/];
  assign T24 = T31 | T25;
  assign T25 = tvec_1 & T26;
  assign T26 = {3'h7/* 7*/{T27}};
  assign T27 = T28[1'h1/* 1*/];
  assign T28 = T29[1'h1/* 1*/:1'h0/* 0*/];
  assign T29 = 2'h1/* 1*/ << choose;
  assign tvec_1 = T30;
  assign T30 = {2'h0/* 0*/, io_in_1_tag};
  assign T31 = tvec_0 & T32;
  assign T32 = {3'h7/* 7*/{T33}};
  assign T33 = T28[1'h0/* 0*/];
  assign tvec_0 = T34;
  assign T34 = {2'h0/* 0*/, io_in_0_tag};
  assign io_out_bits = T35;
  assign T35 = T41 | T36;
  assign T36 = dvec_1 & T37;
  assign T37 = {6'h20/* 32*/{T38}};
  assign T38 = T39[1'h1/* 1*/];
  assign T39 = T40[1'h1/* 1*/:1'h0/* 0*/];
  assign T40 = 2'h1/* 1*/ << choose;
  assign dvec_1 = io_in_1_bits;
  assign T41 = dvec_0 & T42;
  assign T42 = {6'h20/* 32*/{T43}};
  assign T43 = T39[1'h0/* 0*/];
  assign dvec_0 = io_in_0_bits;

  always @(posedge clk) begin
    if(reset) begin
      last_grant <= 1'h0/* 0*/;
    end else if(T6) begin
      last_grant <= T8;
    end
  end
endmodule

module RRAggregatorComponent(input clk, input reset,
    input  io_out_ready,
    output io_out_valid,
    output[31:0] io_out_bits,
    output[4:0] io_out_tag,
    output io_in_0_ready,
    input  io_in_0_valid,
    input [31:0] io_in_0_bits,
    input [4:0] io_in_0_tag,
    output io_in_1_ready,
    input  io_in_1_valid,
    input [31:0] io_in_1_bits,
    input [4:0] io_in_1_tag,
    output io_chosen);

  wire rrArb_io_in_0_ready;
  wire rrArb_io_in_1_ready;
  wire[4:0] rrArb_io_out_tag;
  wire rrArb_io_out_valid;
  wire[31:0] rrArb_io_out_bits;

  assign io_in_0_ready = rrArb_io_in_0_ready;
  assign io_in_1_ready = rrArb_io_in_1_ready;
  assign io_out_tag = rrArb_io_out_tag;
  assign io_out_valid = rrArb_io_out_valid;
  assign io_out_bits = rrArb_io_out_bits;
  gRRArbiter rrArb(.clk(clk), .reset(reset),
       .io_out_ready( io_out_ready ),
       .io_out_valid( rrArb_io_out_valid ),
       .io_out_bits( rrArb_io_out_bits ),
       .io_out_tag( rrArb_io_out_tag ),
       .io_in_0_ready( rrArb_io_in_0_ready ),
       .io_in_0_valid( io_in_0_valid ),
       .io_in_0_bits( io_in_0_bits ),
       .io_in_0_tag( io_in_0_tag ),
       .io_in_1_ready( rrArb_io_in_1_ready ),
       .io_in_1_valid( io_in_1_valid ),
       .io_in_1_bits( io_in_1_bits ),
       .io_in_1_tag( io_in_1_tag ),
       .io_chosen(  ));
endmodule

module gRRArbiter_1(input clk, input reset,
    input  io_out_ready,
    output io_out_valid,
    output[31:0] io_out_bits,
    output[4:0] io_out_tag,
    output io_in_0_ready,
    input  io_in_0_valid,
    input [31:0] io_in_0_bits,
    input [4:0] io_in_0_tag,
    output io_in_1_ready,
    input  io_in_1_valid,
    input [31:0] io_in_1_bits,
    input [4:0] io_in_1_tag,
    output io_chosen);

  wire T0;
  wire[4:0] T1;
  wire[6:0] T2;
  wire[6:0] T3;
  wire[6:0] T4;
  wire T5;
  wire[1:0] T6;
  wire[2:0] T7;
  wire choose;
  wire T8;
  wire T9;
  wire T10;
  reg[0:0] last_grant;
  wire T11;
  wire T12;
  wire[6:0] tvec_1;
  wire[6:0] T13;
  wire[6:0] T14;
  wire[6:0] T15;
  wire T16;
  wire[6:0] tvec_0;
  wire[6:0] T17;

  assign io_out_valid = T0;
  assign T0 = io_in_0_valid || io_in_1_valid;
  assign io_out_tag = T1;
  assign T1 = T2[3'h4/* 4*/:1'h0/* 0*/];
  assign T2 = T14 | T3;
  assign T3 = tvec_1 & T4;
  assign T4 = {3'h7/* 7*/{T5}};
  assign T5 = T6[1'h1/* 1*/];
  assign T6 = T7[1'h1/* 1*/:1'h0/* 0*/];
  assign T7 = 2'h1/* 1*/ << choose;
  assign choose = T9 ? 1'h1/* 1*/ : T8;
  assign T8 = io_in_0_valid ? 1'h0/* 0*/ : 1'h1/* 1*/;
  assign T9 = io_in_1_valid && T10;
  assign T10 = 1'h1/* 1*/ > last_grant;
  assign T11 = io_out_valid && io_out_ready;
  assign T12 = T11 ? choose : last_grant;
  assign tvec_1 = T13;
  assign T13 = {2'h0/* 0*/, io_in_1_tag};
  assign T14 = tvec_0 & T15;
  assign T15 = {3'h7/* 7*/{T16}};
  assign T16 = T6[1'h0/* 0*/];
  assign tvec_0 = T17;
  assign T17 = {2'h0/* 0*/, io_in_0_tag};

  always @(posedge clk) begin
    if(reset) begin
      last_grant <= 1'h0/* 0*/;
    end else if(T11) begin
      last_grant <= T12;
    end
  end
endmodule

module gRRDistributor_1(input clk, input reset,
    input  io_out_0_ready,
    output io_out_0_valid,
    output[31:0] io_out_0_bits,
    output[4:0] io_out_0_tag,
    input  io_out_1_ready,
    output io_out_1_valid,
    output[31:0] io_out_1_bits,
    output[4:0] io_out_1_tag,
    output io_in_ready,
    input  io_in_valid,
    input [31:0] io_in_bits,
    input [4:0] io_in_tag,
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
  assign io_out_1_tag = io_in_tag;
  assign io_out_0_tag = io_in_tag;
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
    output[3:0] io_pcOut_bits_pcType,
    input  mainOff_incrementFactor_req_ready,
    output mainOff_incrementFactor_req_valid,
    output[31:0] mainOff_incrementFactor_req_bits,
    output[4:0] mainOff_incrementFactor_req_tag,
    output mainOff_incrementFactor_rep_ready,
    input  mainOff_incrementFactor_rep_valid,
    input [31:0] mainOff_incrementFactor_rep_bits,
    input [4:0] mainOff_incrementFactor_rep_tag);

  wire incThroughOffload_1_pcOutN_bits_request;
  wire incThroughOffload_io_pcOut_bits_request;
  wire[15:0] incThroughOffload_io_pcOut_bits_moduleId;
  wire incThroughOffload_io_pcOut_valid;
  wire[15:0] incThroughOffload_1_pcOutN_bits_moduleId;
  wire incThroughOffload_1_pcOutN_valid;
  wire[3:0] incThroughOffload_1_pcOutN_bits_pcType;
  wire[3:0] incThroughOffload_io_pcOut_bits_pcType;
  wire gRRDistributor_io_in_ready;
  wire incThroughOffload_1_io_off_incrementFactor_rep_ready;
  wire incThroughOffload_io_off_incrementFactor_rep_ready;
  wire gRRArbiter_io_out_valid;
  wire incThroughOffload_1_io_off_incrementFactor_req_valid;
  wire[4:0] gRRDistributor_io_out_1_tag;
  wire[4:0] gRRArbiter_io_out_tag;
  wire incThroughOffload_io_off_incrementFactor_req_valid;
  wire[4:0] gRRDistributor_io_out_0_tag;
  wire gRRDistributor_io_out_0_valid;
  wire outputArb_io_in_0_ready;
  wire incThroughOffload_1_io_out_valid;
  wire incThroughOffload_io_out_valid;
  wire inputDist_io_out_0_valid;
  wire incThroughOffload_1_io_in_ready;
  wire outputArb_io_in_1_ready;
  wire inputDist_io_out_1_valid;
  wire incThroughOffload_io_in_ready;
  wire[4:0] incThroughOffload_1_io_off_incrementFactor_req_tag;
  wire[4:0] incThroughOffload_io_off_incrementFactor_req_tag;
  wire gRRDistributor_io_out_1_valid;
  wire[7:0] incThroughOffload_1_pcOutN_bits_portId;
  wire[7:0] incThroughOffload_io_pcOut_bits_portId;
  wire[15:0] incThroughOffload_1_pcOutN_bits_pcValue;
  wire[15:0] incThroughOffload_io_pcOut_bits_pcValue;
  wire[4:0] outputArb_io_out_tag;
  wire[4:0] incThroughOffload_1_io_out_tag;
  wire[4:0] inputDist_io_out_1_tag;
  wire[4:0] incThroughOffload_io_out_tag;
  wire[4:0] inputDist_io_out_0_tag;
  wire outputArb_io_out_valid;
  wire[31:0] outputArb_io_out_bits;
  wire[31:0] incThroughOffload_1_io_out_bits;
  wire[31:0] gRRDistributor_io_out_1_bits;
  wire[31:0] inputDist_io_out_1_bits;
  wire[31:0] incThroughOffload_io_out_bits;
  wire[31:0] gRRDistributor_io_out_0_bits;
  wire[31:0] inputDist_io_out_0_bits;
  wire inputDist_io_in_ready;

  assign io_pcOut_bits_request = incThroughOffload_1_pcOutN_bits_request;
  assign io_pcOut_bits_moduleId = incThroughOffload_1_pcOutN_bits_moduleId;
  assign io_pcOut_valid = incThroughOffload_1_pcOutN_valid;
  assign io_pcOut_bits_pcType = incThroughOffload_1_pcOutN_bits_pcType;
  assign mainOff_incrementFactor_rep_ready = gRRDistributor_io_in_ready;
  assign mainOff_incrementFactor_req_valid = gRRArbiter_io_out_valid;
  assign mainOff_incrementFactor_req_tag = gRRArbiter_io_out_tag;
  assign io_pcOut_bits_portId = incThroughOffload_1_pcOutN_bits_portId;
  assign io_pcOut_bits_pcValue = incThroughOffload_1_pcOutN_bits_pcValue;
  assign io_out_tag = outputArb_io_out_tag;
  assign io_out_valid = outputArb_io_out_valid;
  assign io_out_bits = outputArb_io_out_bits;
  assign io_in_ready = inputDist_io_in_ready;
  incThroughOffload incThroughOffload(.clk(clk), .reset(reset),
       .io_in_ready( incThroughOffload_io_in_ready ),
       .io_in_valid( inputDist_io_out_0_valid ),
       .io_in_bits( inputDist_io_out_0_bits ),
       .io_in_tag( inputDist_io_out_0_tag ),
       .io_out_ready( outputArb_io_in_0_ready ),
       .io_out_valid( incThroughOffload_io_out_valid ),
       .io_out_bits( incThroughOffload_io_out_bits ),
       .io_out_tag( incThroughOffload_io_out_tag ),
       .pcIn0_valid( io_pcIn_valid ),
       .pcIn0_bits_request( io_pcIn_bits_request ),
       .pcIn0_bits_moduleId( io_pcIn_bits_moduleId ),
       .pcIn0_bits_portId( io_pcIn_bits_portId ),
       .pcIn0_bits_pcValue( io_pcIn_bits_pcValue ),
       .pcIn0_bits_pcType( io_pcIn_bits_pcType ),
       .io_pcOut_valid( incThroughOffload_io_pcOut_valid ),
       .io_pcOut_bits_request( incThroughOffload_io_pcOut_bits_request ),
       .io_pcOut_bits_moduleId( incThroughOffload_io_pcOut_bits_moduleId ),
       .io_pcOut_bits_portId( incThroughOffload_io_pcOut_bits_portId ),
       .io_pcOut_bits_pcValue( incThroughOffload_io_pcOut_bits_pcValue ),
       .io_pcOut_bits_pcType( incThroughOffload_io_pcOut_bits_pcType ),
       .io_off_incrementFactor_req_ready(  ),
       .io_off_incrementFactor_req_valid( incThroughOffload_io_off_incrementFactor_req_valid ),
       .io_off_incrementFactor_req_bits(  ),
       .io_off_incrementFactor_req_tag( incThroughOffload_io_off_incrementFactor_req_tag ),
       .io_off_incrementFactor_rep_ready( incThroughOffload_io_off_incrementFactor_rep_ready ),
       .io_off_incrementFactor_rep_valid( gRRDistributor_io_out_0_valid ),
       .io_off_incrementFactor_rep_bits( gRRDistributor_io_out_0_bits ),
       .io_off_incrementFactor_rep_tag( gRRDistributor_io_out_0_tag ));
  incThroughOffload_1 incThroughOffload_1(.clk(clk), .reset(reset),
       .io_in_ready( incThroughOffload_1_io_in_ready ),
       .io_in_valid( inputDist_io_out_1_valid ),
       .io_in_bits( inputDist_io_out_1_bits ),
       .io_in_tag( inputDist_io_out_1_tag ),
       .io_out_ready( outputArb_io_in_1_ready ),
       .io_out_valid( incThroughOffload_1_io_out_valid ),
       .io_out_bits( incThroughOffload_1_io_out_bits ),
       .io_out_tag( incThroughOffload_1_io_out_tag ),
       .io_pcIn_valid( incThroughOffload_io_pcOut_valid ),
       .io_pcIn_bits_request( incThroughOffload_io_pcOut_bits_request ),
       .io_pcIn_bits_moduleId( incThroughOffload_io_pcOut_bits_moduleId ),
       .io_pcIn_bits_portId( incThroughOffload_io_pcOut_bits_portId ),
       .io_pcIn_bits_pcValue( incThroughOffload_io_pcOut_bits_pcValue ),
       .io_pcIn_bits_pcType( incThroughOffload_io_pcOut_bits_pcType ),
       .pcOutN_valid( incThroughOffload_1_pcOutN_valid ),
       .pcOutN_bits_request( incThroughOffload_1_pcOutN_bits_request ),
       .pcOutN_bits_moduleId( incThroughOffload_1_pcOutN_bits_moduleId ),
       .pcOutN_bits_portId( incThroughOffload_1_pcOutN_bits_portId ),
       .pcOutN_bits_pcValue( incThroughOffload_1_pcOutN_bits_pcValue ),
       .pcOutN_bits_pcType( incThroughOffload_1_pcOutN_bits_pcType ),
       .io_off_incrementFactor_req_ready(  ),
       .io_off_incrementFactor_req_valid( incThroughOffload_1_io_off_incrementFactor_req_valid ),
       .io_off_incrementFactor_req_bits(  ),
       .io_off_incrementFactor_req_tag( incThroughOffload_1_io_off_incrementFactor_req_tag ),
       .io_off_incrementFactor_rep_ready( incThroughOffload_1_io_off_incrementFactor_rep_ready ),
       .io_off_incrementFactor_rep_valid( gRRDistributor_io_out_1_valid ),
       .io_off_incrementFactor_rep_bits( gRRDistributor_io_out_1_bits ),
       .io_off_incrementFactor_rep_tag( gRRDistributor_io_out_1_tag ));
  RRDistributorComponent inputDist(.clk(clk), .reset(reset),
       .io_out_0_ready( incThroughOffload_io_in_ready ),
       .io_out_0_valid( inputDist_io_out_0_valid ),
       .io_out_0_bits( inputDist_io_out_0_bits ),
       .io_out_0_tag( inputDist_io_out_0_tag ),
       .io_out_1_ready( incThroughOffload_1_io_in_ready ),
       .io_out_1_valid( inputDist_io_out_1_valid ),
       .io_out_1_bits( inputDist_io_out_1_bits ),
       .io_out_1_tag( inputDist_io_out_1_tag ),
       .io_in_ready( inputDist_io_in_ready ),
       .io_in_valid( io_in_valid ),
       .io_in_bits( io_in_bits ),
       .io_in_tag( io_in_tag ),
       .io_chosen(  ));
  RRAggregatorComponent outputArb(.clk(clk), .reset(reset),
       .io_out_ready( io_out_ready ),
       .io_out_valid( outputArb_io_out_valid ),
       .io_out_bits( outputArb_io_out_bits ),
       .io_out_tag( outputArb_io_out_tag ),
       .io_in_0_ready( outputArb_io_in_0_ready ),
       .io_in_0_valid( incThroughOffload_io_out_valid ),
       .io_in_0_bits( incThroughOffload_io_out_bits ),
       .io_in_0_tag( incThroughOffload_io_out_tag ),
       .io_in_1_ready( outputArb_io_in_1_ready ),
       .io_in_1_valid( incThroughOffload_1_io_out_valid ),
       .io_in_1_bits( incThroughOffload_1_io_out_bits ),
       .io_in_1_tag( incThroughOffload_1_io_out_tag ),
       .io_chosen(  ));
  gRRArbiter_1 gRRArbiter(.clk(clk), .reset(reset),
       .io_out_ready( mainOff_incrementFactor_req_ready ),
       .io_out_valid( gRRArbiter_io_out_valid ),
       .io_out_bits(  ),
       .io_out_tag( gRRArbiter_io_out_tag ),
       .io_in_0_ready(  ),
       .io_in_0_valid( incThroughOffload_io_off_incrementFactor_req_valid ),
       .io_in_0_bits(  ),
       .io_in_0_tag( incThroughOffload_io_off_incrementFactor_req_tag ),
       .io_in_1_ready(  ),
       .io_in_1_valid( incThroughOffload_1_io_off_incrementFactor_req_valid ),
       .io_in_1_bits(  ),
       .io_in_1_tag( incThroughOffload_1_io_off_incrementFactor_req_tag ),
       .io_chosen(  ));
  gRRDistributor_1 gRRDistributor(.clk(clk), .reset(reset),
       .io_out_0_ready( incThroughOffload_io_off_incrementFactor_rep_ready ),
       .io_out_0_valid( gRRDistributor_io_out_0_valid ),
       .io_out_0_bits( gRRDistributor_io_out_0_bits ),
       .io_out_0_tag( gRRDistributor_io_out_0_tag ),
       .io_out_1_ready( incThroughOffload_1_io_off_incrementFactor_rep_ready ),
       .io_out_1_valid( gRRDistributor_io_out_1_valid ),
       .io_out_1_bits( gRRDistributor_io_out_1_bits ),
       .io_out_1_tag( gRRDistributor_io_out_1_tag ),
       .io_in_ready( gRRDistributor_io_in_ready ),
       .io_in_valid( mainOff_incrementFactor_rep_valid ),
       .io_in_bits( mainOff_incrementFactor_rep_bits ),
       .io_in_tag( mainOff_incrementFactor_rep_tag ),
       .io_chosen(  ));
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
  reg[15:0] R12;
  wire T13;
  wire[15:0] T14;
  wire[15:0] T15;
  wire[15:0] T16;
  reg[15:0] R17;
  wire T18;
  wire T19;
  wire T20;
  wire T21;
  wire T22;
  wire T23;
  wire[7:0] T24;
  wire[7:0] T25;
  wire T26;
  wire T27;
  wire[1:0] T28;
  wire rThreadEncoder_io_chosen;
  wire T29;
  reg[0:0] subStateTh_0;
  wire T30;
  wire T31;
  wire T32;
  wire vThreadEncoder_io_chosen;
  wire T33;
  wire AllOffloadsValid_0;
  wire T34;
  wire T35;
  wire T36;
  reg[7:0] State_0;
  wire T37;
  wire T38;
  wire T39;
  wire T40;
  wire[1:0] T41;
  wire T42;
  wire T43;
  wire[7:0] T44;
  wire[7:0] T45;
  wire[7:0] T46;
  wire T47;
  wire T48;
  wire T49;
  wire T50;
  wire T51;
  wire[7:0] T52;
  wire T53;
  wire T54;
  wire T55;
  wire T56;
  wire T57;
  wire T58;
  wire T59;
  wire T60;
  wire T61;
  wire T62;
  wire[1:0] T63;
  wire sThreadEncoder_io_chosen;
  wire T64;
  wire T65;
  wire T66;
  wire T67;
  wire[4:0] T68;
  wire[4:0] T69;
  reg[4:0] inputTag_0;
  wire[4:0] T70;
  wire T71;
  wire T72;
  wire[7:0] T73;
  wire[7:0] T74;
  wire[7:0] T75;
  wire[7:0] T76;
  wire[7:0] T77;
  wire[7:0] T78;
  wire[7:0] T79;
  reg[7:0] EmitReturnState_0;
  wire[7:0] T80;
  wire[7:0] T81;
  wire T82;
  wire T83;
  wire T84;
  wire T85;
  wire T86;
  wire T87;
  wire T88;
  wire[3:0] T89;
  wire T90;
  wire[15:0] T91;
  wire[15:0] T92;
  wire[15:0] T93;
  reg[15:0] R94;
  wire T95;
  wire T96;
  wire T97;
  wire T98;
  wire T99;
  wire T100;
  wire T101;
  wire[3:0] T102;
  wire T103;
  wire[15:0] T104;
  wire[15:0] T105;
  wire[15:0] T106;
  wire T107;
  wire[15:0] T108;
  reg[7:0] R109;
  wire T110;
  wire[7:0] T111;
  wire T112;
  reg[15:0] R113;
  wire T114;
  wire[15:0] T115;
  wire T116;
  reg[0:0] R117;
  wire T118;
  wire T119;
  wire T120;
  wire[31:0] T121;
  wire[31:0] T122;
  reg[31:0] outputReg_0;
  wire[31:0] T123;

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
  assign io_pcOut_bits_pcValue = R12;
  assign T13 = T3 || T2;
  assign T14 = T2 ? io_pcIn_bits_pcValue : T15;
  assign T15 = T3 ? T16 : R12;
  assign T16 = T107 ? R94 : R17;
  assign T18 = T87 || T19;
  assign T19 = T86 && T20;
  assign T20 = io_out_valid && T21;
  assign T21 = ! io_out_ready;
  assign io_out_valid = T22;
  assign T22 = T85 && T23;
  assign T23 = T24 == 8'hff/* 255*/;
  assign T24 = State_0 & T25;
  assign T25 = {4'h8/* 8*/{T26}};
  assign T26 = T27;
  assign T27 = T28[1'h0/* 0*/:1'h0/* 0*/];
  assign T28 = 1'h1/* 1*/ << rThreadEncoder_io_chosen;
  assign T29 = subStateTh_0 == 1'h0/* 0*/;
  assign T30 = T35 ? 1'h1/* 1*/ : T31;
  assign T31 = T32 ? 1'h0/* 0*/ : subStateTh_0;
  assign T32 = 1'h0/* 0*/ == vThreadEncoder_io_chosen;
  assign T33 = T34 && AllOffloadsValid_0;
  assign AllOffloadsValid_0 = 1'h1/* 1*/;
  assign T34 = subStateTh_0 == 1'h1/* 1*/;
  assign T35 = T82 && T36;
  assign T36 = State_0 != 8'hff/* 255*/;
  assign T37 = T48 || T38;
  assign T38 = T42 && T39;
  assign T39 = T40;
  assign T40 = T41[1'h0/* 0*/:1'h0/* 0*/];
  assign T41 = 1'h1/* 1*/ << vThreadEncoder_io_chosen;
  assign T42 = T47 && T43;
  assign T43 = T45 == T44;
  assign T44 = {6'h0/* 0*/, 2'h2/* 2*/};
  assign T45 = State_0 & T46;
  assign T46 = {4'h8/* 8*/{T39}};
  assign T47 = vThreadEncoder_io_chosen != 1'h1/* 1*/;
  assign T48 = T54 || T49;
  assign T49 = T50 && T39;
  assign T50 = T53 && T51;
  assign T51 = T45 == T52;
  assign T52 = {7'h0/* 0*/, 1'h1/* 1*/};
  assign T53 = vThreadEncoder_io_chosen != 1'h1/* 1*/;
  assign T54 = T60 || T55;
  assign T55 = T56 && T26;
  assign T56 = T57 && io_out_ready;
  assign T57 = T59 && T58;
  assign T58 = T24 == 8'hff/* 255*/;
  assign T59 = rThreadEncoder_io_chosen != 1'h1/* 1*/;
  assign T60 = T67 && T61;
  assign T61 = T62;
  assign T62 = T63[1'h0/* 0*/:1'h0/* 0*/];
  assign T63 = 1'h1/* 1*/ << sThreadEncoder_io_chosen;
  assign T64 = T66 && T65;
  assign T65 = State_0 == 8'h0/* 0*/;
  assign T66 = subStateTh_0 == 1'h0/* 0*/;
  assign T67 = T72 && io_in_valid;
  assign io_out_tag = T68;
  assign T68 = inputTag_0 & T69;
  assign T69 = {3'h5/* 5*/{T26}};
  assign T70 = T60 ? io_in_tag : inputTag_0;
  assign io_in_ready = T71;
  assign T71 = sThreadEncoder_io_chosen != 1'h1/* 1*/;
  assign T72 = sThreadEncoder_io_chosen != 1'h1/* 1*/;
  assign T73 = T38 ? 8'hff/* 255*/ : T74;
  assign T74 = T49 ? T81 : T75;
  assign T75 = T55 ? T78 : T76;
  assign T76 = T60 ? T77 : State_0;
  assign T77 = {7'h0/* 0*/, 1'h1/* 1*/};
  assign T78 = EmitReturnState_0 & T79;
  assign T79 = {4'h8/* 8*/{T26}};
  assign T80 = T38 ? 8'h0/* 0*/ : EmitReturnState_0;
  assign T81 = {6'h0/* 0*/, 2'h2/* 2*/};
  assign T82 = T84 && T83;
  assign T83 = State_0 != 8'h0/* 0*/;
  assign T84 = 1'h0/* 0*/ == rThreadEncoder_io_chosen;
  assign T85 = rThreadEncoder_io_chosen != 1'h1/* 1*/;
  assign T86 = ! T87;
  assign T87 = T90 && T88;
  assign T88 = io_pcIn_bits_pcType == T89;
  assign T89 = {2'h0/* 0*/, 2'h2/* 2*/};
  assign T90 = io_pcIn_valid && io_pcIn_bits_request;
  assign T91 = T19 ? T93 : T92;
  assign T92 = T87 ? 16'h0/* 0*/ : R17;
  assign T93 = R17 + 16'h1/* 1*/;
  assign T95 = T100 || T96;
  assign T96 = T99 && T97;
  assign T97 = io_in_valid && T98;
  assign T98 = ! io_in_ready;
  assign T99 = ! T100;
  assign T100 = T103 && T101;
  assign T101 = io_pcIn_bits_pcType == T102;
  assign T102 = {2'h0/* 0*/, 2'h2/* 2*/};
  assign T103 = io_pcIn_valid && io_pcIn_bits_request;
  assign T104 = T96 ? T106 : T105;
  assign T105 = T100 ? 16'h0/* 0*/ : R94;
  assign T106 = R94 + 16'h1/* 1*/;
  assign T107 = T108 == 16'h1/* 1*/;
  assign T108 = {8'h0/* 0*/, io_pcIn_bits_portId};
  assign io_pcOut_bits_portId = R109;
  assign T110 = T3 || T2;
  assign T111 = T112 ? io_pcIn_bits_portId : R109;
  assign T112 = T3 || T2;
  assign io_pcOut_bits_moduleId = R113;
  assign T114 = T3 || T2;
  assign T115 = T116 ? io_pcIn_bits_moduleId : R113;
  assign T116 = T3 || T2;
  assign io_pcOut_bits_request = R117;
  assign T118 = T3 || T2;
  assign T119 = T2 ? io_pcIn_bits_request : T120;
  assign T120 = T3 ? 1'h0/* 0*/ : R117;
  assign io_out_bits = T121;
  assign T121 = outputReg_0 & T122;
  assign T122 = {6'h20/* 32*/{T26}};
  assign T123 = T49 ? 32'h2/* 2*/ : outputReg_0;
  RREncode_6 rThreadEncoder(
       .io_valid_0( T29 ),
       .io_chosen( rThreadEncoder_io_chosen ),
       .io_ready(  ));
  RREncode_7 vThreadEncoder(
       .io_valid_0( T33 ),
       .io_chosen( vThreadEncoder_io_chosen ),
       .io_ready(  ));
  RREncode_8 sThreadEncoder(
       .io_valid_0( T64 ),
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
      R12 <= 16'h0/* 0*/;
    end else if(T13) begin
      R12 <= T14;
    end
    if(reset) begin
      R17 <= 16'h0/* 0*/;
    end else if(T18) begin
      R17 <= T91;
    end
    subStateTh_0 <= reset ? 1'h0/* 0*/ : T30;
    if(reset) begin
      State_0 <= 8'h0/* 0*/;
    end else if(T37) begin
      State_0 <= T73;
    end
    if(T60) begin
      inputTag_0 <= T70;
    end
    if(reset) begin
      EmitReturnState_0 <= 8'h0/* 0*/;
    end else if(T38) begin
      EmitReturnState_0 <= T80;
    end
    if(reset) begin
      R94 <= 16'h0/* 0*/;
    end else if(T95) begin
      R94 <= T104;
    end
    if(reset) begin
      R109 <= 8'h0/* 0*/;
    end else if(T110) begin
      R109 <= T111;
    end
    if(reset) begin
      R113 <= 16'h0/* 0*/;
    end else if(T114) begin
      R113 <= T115;
    end
    if(reset) begin
      R117 <= 1'h1/* 1*/;
    end else if(T118) begin
      R117 <= T119;
    end
    if(T49) begin
      outputReg_0 <= T123;
    end
  end
endmodule

module gOffloadedComponent(input clk, input reset,
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

  wire offComp_io_pcOut_valid;
  wire mainComp_io_pcOut_bits_request;
  wire[15:0] mainComp_io_pcOut_bits_moduleId;
  wire mainComp_io_pcOut_valid;
  wire[3:0] offComp_io_pcOut_bits_pcType;
  wire[3:0] mainComp_io_pcOut_bits_pcType;
  wire[15:0] offComp_io_pcOut_bits_pcValue;
  wire mainComp_mainOff_incrementFactor_rep_ready;
  wire mainComp_mainOff_incrementFactor_req_valid;
  wire[4:0] offComp_io_out_tag;
  wire[4:0] mainComp_mainOff_incrementFactor_req_tag;
  wire offComp_io_out_valid;
  wire offComp_io_in_ready;
  wire[7:0] mainComp_io_pcOut_bits_portId;
  wire[15:0] mainComp_io_pcOut_bits_pcValue;
  wire[7:0] offComp_io_pcOut_bits_portId;
  wire[15:0] offComp_io_pcOut_bits_moduleId;
  wire offComp_io_pcOut_bits_request;
  wire[4:0] mainComp_io_out_tag;
  wire mainComp_io_out_valid;
  wire[31:0] mainComp_io_out_bits;
  wire[31:0] offComp_io_out_bits;
  wire mainComp_io_in_ready;

  assign io_pcOut_valid = offComp_io_pcOut_valid;
  assign io_pcOut_bits_pcType = offComp_io_pcOut_bits_pcType;
  assign io_pcOut_bits_pcValue = offComp_io_pcOut_bits_pcValue;
  assign io_pcOut_bits_portId = offComp_io_pcOut_bits_portId;
  assign io_pcOut_bits_moduleId = offComp_io_pcOut_bits_moduleId;
  assign io_pcOut_bits_request = offComp_io_pcOut_bits_request;
  assign io_out_tag = mainComp_io_out_tag;
  assign io_out_valid = mainComp_io_out_valid;
  assign io_out_bits = mainComp_io_out_bits;
  assign io_in_ready = mainComp_io_in_ready;
  gReplicatedComponent mainComp(.clk(clk), .reset(reset),
       .io_in_ready( mainComp_io_in_ready ),
       .io_in_valid( io_in_valid ),
       .io_in_bits( io_in_bits ),
       .io_in_tag( io_in_tag ),
       .io_out_ready( io_out_ready ),
       .io_out_valid( mainComp_io_out_valid ),
       .io_out_bits( mainComp_io_out_bits ),
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
       .mainOff_incrementFactor_req_ready( offComp_io_in_ready ),
       .mainOff_incrementFactor_req_valid( mainComp_mainOff_incrementFactor_req_valid ),
       .mainOff_incrementFactor_req_bits(  ),
       .mainOff_incrementFactor_req_tag( mainComp_mainOff_incrementFactor_req_tag ),
       .mainOff_incrementFactor_rep_ready( mainComp_mainOff_incrementFactor_rep_ready ),
       .mainOff_incrementFactor_rep_valid( offComp_io_out_valid ),
       .mainOff_incrementFactor_rep_bits( offComp_io_out_bits ),
       .mainOff_incrementFactor_rep_tag( offComp_io_out_tag ));
  sendConst offComp(.clk(clk), .reset(reset),
       .io_in_ready( offComp_io_in_ready ),
       .io_in_valid( mainComp_mainOff_incrementFactor_req_valid ),
       .io_in_bits(  ),
       .io_in_tag( mainComp_mainOff_incrementFactor_req_tag ),
       .io_out_ready( mainComp_mainOff_incrementFactor_rep_ready ),
       .io_out_valid( offComp_io_out_valid ),
       .io_out_bits( offComp_io_out_bits ),
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

module Top(input clk, input reset,
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

  wire generatedTop_io_pcOut_valid;
  wire[3:0] generatedTop_io_pcOut_bits_pcType;
  wire[15:0] generatedTop_io_pcOut_bits_pcValue;
  wire[7:0] generatedTop_io_pcOut_bits_portId;
  wire[15:0] generatedTop_io_pcOut_bits_moduleId;
  wire generatedTop_io_pcOut_bits_request;
  wire[4:0] generatedTop_io_out_tag;
  wire generatedTop_io_out_valid;
  wire[31:0] generatedTop_io_out_bits;
  wire generatedTop_io_in_ready;

  assign io_pcOut_valid = generatedTop_io_pcOut_valid;
  assign io_pcOut_bits_pcType = generatedTop_io_pcOut_bits_pcType;
  assign io_pcOut_bits_pcValue = generatedTop_io_pcOut_bits_pcValue;
  assign io_pcOut_bits_portId = generatedTop_io_pcOut_bits_portId;
  assign io_pcOut_bits_moduleId = generatedTop_io_pcOut_bits_moduleId;
  assign io_pcOut_bits_request = generatedTop_io_pcOut_bits_request;
  assign io_out_tag = generatedTop_io_out_tag;
  assign io_out_valid = generatedTop_io_out_valid;
  assign io_out_bits = generatedTop_io_out_bits;
  assign io_in_ready = generatedTop_io_in_ready;
  gOffloadedComponent generatedTop(.clk(clk), .reset(reset),
       .io_in_ready( generatedTop_io_in_ready ),
       .io_in_valid( io_in_valid ),
       .io_in_bits( io_in_bits ),
       .io_in_tag( io_in_tag ),
       .io_out_ready( io_out_ready ),
       .io_out_valid( generatedTop_io_out_valid ),
       .io_out_bits( generatedTop_io_out_bits ),
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

