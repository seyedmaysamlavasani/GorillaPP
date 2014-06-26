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
    input [19:0] pcIn0_bits_pcValue,
    input [3:0] pcIn0_bits_pcType,
    output pcOutN_valid,
    output pcOutN_bits_request,
    output[15:0] pcOutN_bits_moduleId,
    output[7:0] pcOutN_bits_portId,
    output[19:0] pcOutN_bits_pcValue,
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
  wire[4:0] incrementFactorPort_req_tag;
  wire[4:0] T49;
  wire incrementFactorPort_rep_valid;
  wire T50;
  wire T51;
  wire[4:0] T52;
  wire T53;
  wire T54;
  wire T55;
  wire T56;
  wire T57;
  reg[7:0] State_0;
  wire T58;
  wire T59;
  wire T60;
  wire T61;
  wire[1:0] T62;
  wire T63;
  wire T64;
  wire[7:0] T65;
  wire[7:0] T66;
  wire[7:0] T67;
  wire T68;
  wire[1:0] T69;
  wire T70;
  wire T71;
  wire T72;
  wire T73;
  wire[31:0] T74;
  wire[31:0] T75;
  reg[31:0] counter_0;
  wire T76;
  wire T77;
  wire T78;
  wire T79;
  wire[7:0] T80;
  wire T81;
  wire[1:0] T82;
  wire T83;
  wire T84;
  wire T85;
  wire[7:0] T86;
  wire T87;
  wire[1:0] T88;
  wire[31:0] T89;
  wire[31:0] T90;
  wire[31:0] T91;
  wire T92;
  wire T93;
  wire T94;
  wire T95;
  wire T96;
  wire T97;
  wire T98;
  wire[1:0] T99;
  wire T100;
  wire T101;
  wire T102;
  wire[1:0] T103;
  wire T104;
  wire T105;
  wire T106;
  wire[1:0] T107;
  wire sThreadEncoder_io_chosen;
  wire T108;
  wire T109;
  wire T110;
  wire T111;
  wire T112;
  wire[1:0] T113;
  wire T114;
  wire[1:0] T115;
  wire[7:0] T116;
  wire[7:0] T117;
  wire[7:0] T118;
  wire[7:0] T119;
  wire[7:0] T120;
  wire[7:0] T121;
  wire[7:0] T122;
  wire[7:0] T123;
  reg[7:0] EmitReturnState_0;
  wire[7:0] T124;
  wire[7:0] T125;
  wire[7:0] T126;
  wire T127;
  wire T128;
  wire T129;
  wire T130;
  wire AllOffloadsReady;
  wire T131;
  wire T132;
  wire T133;
  wire T134;
  reg[0:0] incrementFactorPortHadReadyRequest;
  wire T135;
  wire T136;
  wire T137;
  wire T138;
  reg[0:0] incrementFactor_ready_received;
  wire T139;
  wire T140;
  wire incrementFactorPort_req_ready;
  wire T141;
  wire T142;
  wire[1:0] T143;
  wire T144;
  wire T145;
  wire T146;
  wire T147;
  wire[1:0] T148;
  reg[7:0] R149;
  wire T150;
  wire[7:0] T151;
  wire T152;
  reg[19:0] R153;
  wire T154;
  wire[19:0] T155;
  wire[19:0] T156;
  wire[19:0] T157;
  wire[19:0] T158;
  wire[19:0] T159;
  wire[19:0] T160;
  reg[19:0] R161;
  wire T162;
  wire T163;
  wire T164;
  wire T165;
  reg[0:0] R166;
  wire T167;
  wire T168;
  wire T169;
  wire[3:0] T0;
  wire T170;
  wire T171;
  wire T172;
  wire[3:0] T1;
  wire T173;
  wire T174;
  wire T175;
  wire T176;
  wire T177;
  wire T178;
  wire T179;
  wire[19:0] T180;
  wire[19:0] T181;
  wire[19:0] T182;
  wire T183;
  wire T184;
  wire[3:0] T2;
  wire T185;
  wire[19:0] T186;
  reg[19:0] R187;
  wire T188;
  wire T189;
  wire T190;
  wire T191;
  wire T192;
  wire T193;
  wire T194;
  wire[19:0] T195;
  wire[19:0] T196;
  wire[19:0] T197;
  wire T198;
  wire T199;
  wire T200;
  wire[19:0] T201;
  reg[19:0] R202;
  wire T203;
  wire T204;
  wire T205;
  wire T206;
  wire T207;
  wire T208;
  wire T209;
  wire[19:0] T210;
  wire[19:0] T211;
  wire[19:0] T212;
  wire T213;
  wire T214;
  wire T215;
  wire[19:0] T216;
  reg[19:0] R217;
  wire T218;
  wire T219;
  wire T220;
  wire T221;
  wire T222;
  wire T223;
  wire T224;
  wire[19:0] T225;
  wire[19:0] T226;
  wire[19:0] T227;
  wire T228;
  wire[3:0] T3;
  wire[4:0] T229;
  wire[4:0] T230;
  reg[4:0] inputTag_0;
  wire[4:0] T231;
  wire[31:0] T232;
  wire[31:0] T233;
  reg[31:0] outputReg_0;
  wire[31:0] T234;
  wire[31:0] T235;
  wire[31:0] incF;
  wire[31:0] T236;
  wire[31:0] T237;
  reg[31:0] inputReg_0;
  wire[31:0] T238;

  assign pcOutN_bits_request = R0;
  assign T1 = T3 || T2;
  assign T2 = ! T3;
  assign T3 = T4 && pcIn0_bits_request;
  assign T4 = pcIn0_valid && T5;
  assign T5 = pcIn0_bits_moduleId == 16'h3/* 3*/;
  assign T6 = T2 ? pcIn0_bits_request : T7;
  assign T7 = T3 ? 1'h0/* 0*/ : R0;
  assign pcOutN_bits_moduleId = R8;
  assign T9 = T3 || T2;
  assign T10 = T11 ? pcIn0_bits_moduleId : R8;
  assign T11 = T3 || T2;
  assign pcOutN_valid = R12;
  assign T13 = T3 || T2;
  assign T14 = T2 ? pcIn0_valid : T15;
  assign T15 = T3 ? 1'h1/* 1*/ : R12;
  assign pcOutN_bits_pcType = R16;
  assign T17 = T3 || T2;
  assign T18 = T19 ? pcIn0_bits_pcType : R16;
  assign T19 = T3 || T2;
  assign io_off_incrementFactor_rep_ready = incrementFactorPort_rep_ready;
  assign incrementFactorPort_rep_ready = 1'h1/* 1*/;
  assign io_off_incrementFactor_req_valid = incrementFactorPort_req_valid;
  assign incrementFactorPort_req_valid = T20;
  assign T20 = T144 && T21;
  assign T21 = T142 && T22;
  assign T22 = T24 == T23;
  assign T23 = {6'h0/* 0*/, 2'h3/* 3*/};
  assign T24 = State_0 & T25;
  assign T25 = {4'h8/* 8*/{T26}};
  assign T26 = T27;
  assign T27 = T28[1'h0/* 0*/:1'h0/* 0*/];
  assign T28 = 1'h1/* 1*/ << rThreadEncoder_io_chosen;
  assign T29 = subStateTh_0 == 1'h0/* 0*/;
  assign T30 = T56 ? 1'h1/* 1*/ : T31;
  assign T31 = T32 ? 1'h0/* 0*/ : subStateTh_0;
  assign T32 = 1'h0/* 0*/ == vThreadEncoder_io_chosen;
  assign T33 = T55 && AllOffloadsValid_0;
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
  assign T44 = T53 || incrementFactor_valid_received_0;
  assign T45 = T50 && T46;
  assign T46 = incrementFactor_valid_received_0 || T47;
  assign T47 = incrementFactorPort_rep_valid && T48;
  assign T48 = incrementFactorPort_rep_tag == 5'h0/* 0*/;
  assign incrementFactorPort_rep_tag = io_off_incrementFactor_rep_tag;
  assign io_off_incrementFactor_req_tag = incrementFactorPort_req_tag;
  assign incrementFactorPort_req_tag = T49;
  assign T49 = {4'h0/* 0*/, rThreadEncoder_io_chosen};
  assign incrementFactorPort_rep_valid = io_off_incrementFactor_rep_valid;
  assign T50 = ! T51;
  assign T51 = T52 == 5'h0/* 0*/;
  assign T52 = {4'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T53 = incrementFactorPort_rep_valid && T54;
  assign T54 = incrementFactorPort_rep_tag == 5'h0/* 0*/;
  assign T55 = subStateTh_0 == 1'h1/* 1*/;
  assign T56 = T127 && T57;
  assign T57 = State_0 != 8'hff/* 255*/;
  assign T58 = T70 || T59;
  assign T59 = T63 && T60;
  assign T60 = T61;
  assign T61 = T62[1'h0/* 0*/:1'h0/* 0*/];
  assign T62 = 1'h1/* 1*/ << vThreadEncoder_io_chosen;
  assign T63 = T68 && T64;
  assign T64 = T66 == T65;
  assign T65 = {6'h0/* 0*/, 2'h3/* 3*/};
  assign T66 = State_0 & T67;
  assign T67 = {4'h8/* 8*/{T60}};
  assign T68 = T69 != 2'h1/* 1*/;
  assign T69 = {1'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T70 = T92 || T71;
  assign T71 = T72 && T60;
  assign T72 = T78 && T73;
  assign T73 = T74 == 32'h0/* 0*/;
  assign T74 = counter_0 & T75;
  assign T75 = {6'h20/* 32*/{T60}};
  assign T76 = T83 || T77;
  assign T77 = T78 && T60;
  assign T78 = T81 && T79;
  assign T79 = T66 == T80;
  assign T80 = {6'h0/* 0*/, 2'h2/* 2*/};
  assign T81 = T82 != 2'h1/* 1*/;
  assign T82 = {1'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T83 = T84 && T60;
  assign T84 = T87 && T85;
  assign T85 = T66 == T86;
  assign T86 = {7'h0/* 0*/, 1'h1/* 1*/};
  assign T87 = T88 != 2'h1/* 1*/;
  assign T88 = {1'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T89 = T77 ? T91 : T90;
  assign T90 = T83 ? 32'ha/* 10*/ : counter_0;
  assign T91 = T74 - 32'h1/* 1*/;
  assign T92 = T93 || T83;
  assign T93 = T104 || T94;
  assign T94 = T95 && T26;
  assign T95 = T100 && io_out_ready;
  assign io_out_valid = T96;
  assign T96 = T98 && T97;
  assign T97 = T24 == 8'hff/* 255*/;
  assign T98 = T99 != 2'h1/* 1*/;
  assign T99 = {1'h0/* 0*/, rThreadEncoder_io_chosen};
  assign T100 = T102 && T101;
  assign T101 = T24 == 8'hff/* 255*/;
  assign T102 = T103 != 2'h1/* 1*/;
  assign T103 = {1'h0/* 0*/, rThreadEncoder_io_chosen};
  assign T104 = T111 && T105;
  assign T105 = T106;
  assign T106 = T107[1'h0/* 0*/:1'h0/* 0*/];
  assign T107 = 1'h1/* 1*/ << sThreadEncoder_io_chosen;
  assign T108 = T110 && T109;
  assign T109 = State_0 == 8'h0/* 0*/;
  assign T110 = subStateTh_0 == 1'h0/* 0*/;
  assign T111 = T114 && io_in_valid;
  assign io_in_ready = T112;
  assign T112 = T113 != 2'h1/* 1*/;
  assign T113 = {1'h0/* 0*/, sThreadEncoder_io_chosen};
  assign T114 = T115 != 2'h1/* 1*/;
  assign T115 = {1'h0/* 0*/, sThreadEncoder_io_chosen};
  assign T116 = T59 ? 8'hff/* 255*/ : T117;
  assign T117 = T71 ? T126 : T118;
  assign T118 = T83 ? T125 : T119;
  assign T119 = T94 ? T122 : T120;
  assign T120 = T104 ? T121 : State_0;
  assign T121 = {7'h0/* 0*/, 1'h1/* 1*/};
  assign T122 = EmitReturnState_0 & T123;
  assign T123 = {4'h8/* 8*/{T26}};
  assign T124 = T59 ? 8'h0/* 0*/ : EmitReturnState_0;
  assign T125 = {6'h0/* 0*/, 2'h2/* 2*/};
  assign T126 = {6'h0/* 0*/, 2'h3/* 3*/};
  assign T127 = T129 && T128;
  assign T128 = State_0 != 8'h0/* 0*/;
  assign T129 = AllOffloadsReady && T130;
  assign T130 = 1'h0/* 0*/ == rThreadEncoder_io_chosen;
  assign AllOffloadsReady = T131;
  assign T131 = T138 || T132;
  assign T132 = T134 && T133;
  assign T133 = ! incrementFactorPort_req_valid;
  assign T134 = ! incrementFactorPortHadReadyRequest;
  assign T135 = T137 && T136;
  assign T136 = incrementFactorPortHadReadyRequest || incrementFactorPort_req_valid;
  assign T137 = ! AllOffloadsReady;
  assign T138 = incrementFactorPort_req_ready || incrementFactor_ready_received;
  assign T139 = T141 && T140;
  assign T140 = incrementFactor_ready_received || incrementFactorPort_req_ready;
  assign incrementFactorPort_req_ready = io_off_incrementFactor_req_ready;
  assign T141 = ! AllOffloadsReady;
  assign T142 = T143 != 2'h1/* 1*/;
  assign T143 = {1'h0/* 0*/, rThreadEncoder_io_chosen};
  assign T144 = T147 && T145;
  assign T145 = ! T146;
  assign T146 = incrementFactor_valid_received_0 & T26;
  assign T147 = T148 != 2'h1/* 1*/;
  assign T148 = {1'h0/* 0*/, rThreadEncoder_io_chosen};
  assign pcOutN_bits_portId = R149;
  assign T150 = T3 || T2;
  assign T151 = T152 ? pcIn0_bits_portId : R149;
  assign T152 = T3 || T2;
  assign pcOutN_bits_pcValue = R153;
  assign T154 = T3 || T2;
  assign T155 = T2 ? pcIn0_bits_pcValue : T156;
  assign T156 = T3 ? T157 : R153;
  assign T157 = T228 ? R217 : T158;
  assign T158 = T213 ? R202 : T159;
  assign T159 = T198 ? R187 : T160;
  assign T160 = T183 ? R161 : 20'h0/* 0*/;
  assign T162 = T171 || T163;
  assign T163 = T179 && T164;
  assign T164 = T177 && T165;
  assign T165 = ! R166;
  assign T167 = T171 || T168;
  assign T168 = T170 && T169;
  assign T169 = pcIn0_bits_pcType == T0;
  assign T0 = {1'h0/* 0*/, 3'h4/* 4*/};
  assign T170 = pcIn0_valid && pcIn0_bits_request;
  assign T171 = T173 && T172;
  assign T172 = pcIn0_bits_pcType == T1;
  assign T1 = {1'h0/* 0*/, 3'h3/* 3*/};
  assign T173 = pcIn0_valid && pcIn0_bits_request;
  assign T174 = T168 ? T176 : T175;
  assign T175 = T171 ? 1'h0/* 0*/ : R166;
  assign T176 = ! R166;
  assign T177 = io_in_valid && T178;
  assign T178 = ! io_in_ready;
  assign T179 = ! T171;
  assign T180 = T163 ? T182 : T181;
  assign T181 = T171 ? 20'h0/* 0*/ : R161;
  assign T182 = R161 + 20'h1/* 1*/;
  assign T183 = T185 && T184;
  assign T184 = pcIn0_bits_pcType == T2;
  assign T2 = {1'h0/* 0*/, 3'h1/* 1*/};
  assign T185 = T186 == 20'h1/* 1*/;
  assign T186 = {12'h0/* 0*/, pcIn0_bits_portId};
  assign T188 = T171 || T189;
  assign T189 = T194 && T190;
  assign T190 = T192 && T191;
  assign T191 = ! R166;
  assign T192 = io_out_valid && T193;
  assign T193 = ! io_out_ready;
  assign T194 = ! T171;
  assign T195 = T189 ? T197 : T196;
  assign T196 = T171 ? 20'h0/* 0*/ : R187;
  assign T197 = R187 + 20'h1/* 1*/;
  assign T198 = T200 && T199;
  assign T199 = pcIn0_bits_pcType == T2;
  assign T200 = T201 == 20'h2/* 2*/;
  assign T201 = {12'h0/* 0*/, pcIn0_bits_portId};
  assign T203 = T171 || T204;
  assign T204 = T209 && T205;
  assign T205 = T207 && T206;
  assign T206 = ! R166;
  assign T207 = io_off_incrementFactor_req_valid && T208;
  assign T208 = ! io_off_incrementFactor_req_ready;
  assign T209 = ! T171;
  assign T210 = T204 ? T212 : T211;
  assign T211 = T171 ? 20'h0/* 0*/ : R202;
  assign T212 = R202 + 20'h1/* 1*/;
  assign T213 = T215 && T214;
  assign T214 = pcIn0_bits_pcType == T2;
  assign T215 = T216 == 20'h3/* 3*/;
  assign T216 = {12'h0/* 0*/, pcIn0_bits_portId};
  assign T218 = T171 || T219;
  assign T219 = T224 && T220;
  assign T220 = T222 && T221;
  assign T221 = ! R166;
  assign T222 = io_off_incrementFactor_req_ready && T223;
  assign T223 = ! io_off_incrementFactor_req_valid;
  assign T224 = ! T171;
  assign T225 = T219 ? T227 : T226;
  assign T226 = T171 ? 20'h0/* 0*/ : R217;
  assign T227 = R217 + 20'h1/* 1*/;
  assign T228 = pcIn0_bits_pcType == T3;
  assign T3 = {1'h0/* 0*/, 3'h2/* 2*/};
  assign io_out_tag = T229;
  assign T229 = inputTag_0 & T230;
  assign T230 = {3'h5/* 5*/{T26}};
  assign T231 = T104 ? io_in_tag : inputTag_0;
  assign io_out_bits = T232;
  assign T232 = outputReg_0 & T233;
  assign T233 = {6'h20/* 32*/{T26}};
  assign T234 = T59 ? T235 : outputReg_0;
  assign T235 = T236 + incF;
  assign incF = io_off_incrementFactor_rep_bits;
  assign T236 = inputReg_0 & T237;
  assign T237 = {6'h20/* 32*/{T60}};
  assign T238 = T104 ? io_in_bits : inputReg_0;
  RREncode rThreadEncoder(
       .io_valid_0( T29 ),
       .io_chosen( rThreadEncoder_io_chosen ),
       .io_ready(  ));
  RREncode_1 vThreadEncoder(
       .io_valid_0( T33 ),
       .io_chosen( vThreadEncoder_io_chosen ),
       .io_ready(  ));
  RREncode_2 sThreadEncoder(
       .io_valid_0( T108 ),
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
    end else if(T58) begin
      State_0 <= T116;
    end
    if(T76) begin
      counter_0 <= T89;
    end
    if(reset) begin
      EmitReturnState_0 <= 8'h0/* 0*/;
    end else if(T59) begin
      EmitReturnState_0 <= T124;
    end
    incrementFactorPortHadReadyRequest <= reset ? 1'h0/* 0*/ : T135;
    incrementFactor_ready_received <= reset ? 1'h0/* 0*/ : T139;
    if(reset) begin
      R149 <= 8'h0/* 0*/;
    end else if(T150) begin
      R149 <= T151;
    end
    if(reset) begin
      R153 <= 20'h0/* 0*/;
    end else if(T154) begin
      R153 <= T155;
    end
    if(reset) begin
      R161 <= 20'h0/* 0*/;
    end else if(T162) begin
      R161 <= T180;
    end
    if(reset) begin
      R166 <= 1'h0/* 0*/;
    end else if(T167) begin
      R166 <= T174;
    end
    if(reset) begin
      R187 <= 20'h0/* 0*/;
    end else if(T188) begin
      R187 <= T195;
    end
    if(reset) begin
      R202 <= 20'h0/* 0*/;
    end else if(T203) begin
      R202 <= T210;
    end
    if(reset) begin
      R217 <= 20'h0/* 0*/;
    end else if(T218) begin
      R217 <= T225;
    end
    if(T104) begin
      inputTag_0 <= T231;
    end
    if(T59) begin
      outputReg_0 <= T234;
    end
    if(T104) begin
      inputReg_0 <= T238;
    end
  end
endmodule

module gRRDistributor(input clk, input reset,
    input  io_out_0_ready,
    output io_out_0_valid,
    output[31:0] io_out_0_bits,
    output[4:0] io_out_0_tag,
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
  reg[0:0] last_grant;
  wire T5;
  wire T6;
  wire T7;

  assign io_out_0_valid = T0;
  assign T0 = T1 && io_in_valid;
  assign T1 = T7 || T2;
  assign T2 = ! T3;
  assign T3 = io_out_0_ready && T4;
  assign T4 = 1'h0/* 0*/ > last_grant;
  assign T5 = io_in_valid && io_in_ready;
  assign io_in_ready = io_out_0_ready;
  assign T6 = T5 ? 1'h0/* 0*/ : last_grant;
  assign T7 = 1'h0/* 0*/ > last_grant;
  assign io_out_0_tag = io_in_tag;
  assign io_out_0_bits = io_in_bits;

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
    output[31:0] io_out_0_bits,
    output[4:0] io_out_0_tag,
    output io_in_ready,
    input  io_in_valid,
    input [31:0] io_in_bits,
    input [4:0] io_in_tag,
    output io_chosen);

  wire rrDist_io_out_0_valid;
  wire[4:0] rrDist_io_out_0_tag;
  wire[31:0] rrDist_io_out_0_bits;
  wire rrDist_io_in_ready;

  assign io_out_0_valid = rrDist_io_out_0_valid;
  assign io_out_0_tag = rrDist_io_out_0_tag;
  assign io_out_0_bits = rrDist_io_out_0_bits;
  assign io_in_ready = rrDist_io_in_ready;
  gRRDistributor rrDist(.clk(clk), .reset(reset),
       .io_out_0_ready( io_out_0_ready ),
       .io_out_0_valid( rrDist_io_out_0_valid ),
       .io_out_0_bits( rrDist_io_out_0_bits ),
       .io_out_0_tag( rrDist_io_out_0_tag ),
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
  wire[4:0] T8;
  wire[6:0] T9;
  wire[6:0] tvec_0;
  wire[6:0] T10;
  wire[31:0] T11;
  wire[31:0] dvec_0;

  assign io_in_0_ready = T0;
  assign T0 = T1 && io_out_ready;
  assign T1 = T7 || T2;
  assign T2 = ! T3;
  assign T3 = io_in_0_valid && T4;
  assign T4 = 1'h0/* 0*/ > last_grant;
  assign T5 = io_out_valid && io_out_ready;
  assign io_out_valid = io_in_0_valid;
  assign T6 = T5 ? 1'h0/* 0*/ : last_grant;
  assign T7 = 1'h0/* 0*/ > last_grant;
  assign io_out_tag = T8;
  assign T8 = T9[3'h4/* 4*/:1'h0/* 0*/];
  assign T9 = tvec_0 & 7'h7f/* 127*/;
  assign tvec_0 = T10;
  assign T10 = {2'h0/* 0*/, io_in_0_tag};
  assign io_out_bits = T11;
  assign T11 = dvec_0 & 32'hffffffff/* 4294967295*/;
  assign dvec_0 = io_in_0_bits;

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
    output[31:0] io_out_bits,
    output[4:0] io_out_tag,
    output io_in_0_ready,
    input  io_in_0_valid,
    input [31:0] io_in_0_bits,
    input [4:0] io_in_0_tag,
    output io_chosen);

  wire rrArb_io_in_0_ready;
  wire[4:0] rrArb_io_out_tag;
  wire rrArb_io_out_valid;
  wire[31:0] rrArb_io_out_bits;

  assign io_in_0_ready = rrArb_io_in_0_ready;
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
    output io_chosen);

  wire[4:0] T0;
  wire[6:0] T1;
  wire[6:0] tvec_0;
  wire[6:0] T2;
  wire T3;
  wire T4;
  wire T5;
  wire T6;
  wire T7;
  reg[0:0] last_grant;
  wire T8;
  wire T9;
  wire T10;

  assign io_out_valid = io_in_0_valid;
  assign io_out_tag = T0;
  assign T0 = T1[3'h4/* 4*/:1'h0/* 0*/];
  assign T1 = tvec_0 & 7'h7f/* 127*/;
  assign tvec_0 = T2;
  assign T2 = {2'h0/* 0*/, io_in_0_tag};
  assign io_in_0_ready = T3;
  assign T3 = T4 && io_out_ready;
  assign T4 = T10 || T5;
  assign T5 = ! T6;
  assign T6 = io_in_0_valid && T7;
  assign T7 = 1'h0/* 0*/ > last_grant;
  assign T8 = io_out_valid && io_out_ready;
  assign T9 = T8 ? 1'h0/* 0*/ : last_grant;
  assign T10 = 1'h0/* 0*/ > last_grant;

  always @(posedge clk) begin
    if(reset) begin
      last_grant <= 1'h0/* 0*/;
    end else if(T8) begin
      last_grant <= T9;
    end
  end
endmodule

module gRRDistributor_1(input clk, input reset,
    input  io_out_0_ready,
    output io_out_0_valid,
    output[31:0] io_out_0_bits,
    output[4:0] io_out_0_tag,
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
  reg[0:0] last_grant;
  wire T5;
  wire T6;
  wire T7;

  assign io_in_ready = io_out_0_ready;
  assign io_out_0_tag = io_in_tag;
  assign io_out_0_valid = T0;
  assign T0 = T1 && io_in_valid;
  assign T1 = T7 || T2;
  assign T2 = ! T3;
  assign T3 = io_out_0_ready && T4;
  assign T4 = 1'h0/* 0*/ > last_grant;
  assign T5 = io_in_valid && io_in_ready;
  assign T6 = T5 ? 1'h0/* 0*/ : last_grant;
  assign T7 = 1'h0/* 0*/ > last_grant;
  assign io_out_0_bits = io_in_bits;

  always @(posedge clk) begin
    if(reset) begin
      last_grant <= 1'h0/* 0*/;
    end else if(T5) begin
      last_grant <= T6;
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
    input [19:0] io_pcIn_bits_pcValue,
    input [3:0] io_pcIn_bits_pcType,
    output io_pcOut_valid,
    output io_pcOut_bits_request,
    output[15:0] io_pcOut_bits_moduleId,
    output[7:0] io_pcOut_bits_portId,
    output[19:0] io_pcOut_bits_pcValue,
    output[3:0] io_pcOut_bits_pcType,
    input  mainOff_incrementFactor_req_ready,
    output mainOff_incrementFactor_req_valid,
    output[31:0] mainOff_incrementFactor_req_bits,
    output[4:0] mainOff_incrementFactor_req_tag,
    output mainOff_incrementFactor_rep_ready,
    input  mainOff_incrementFactor_rep_valid,
    input [31:0] mainOff_incrementFactor_rep_bits,
    input [4:0] mainOff_incrementFactor_rep_tag);

  wire incThroughOffload_pcOutN_bits_request;
  wire[15:0] incThroughOffload_pcOutN_bits_moduleId;
  wire incThroughOffload_pcOutN_valid;
  wire[3:0] incThroughOffload_pcOutN_bits_pcType;
  wire gRRDistributor_io_in_ready;
  wire incThroughOffload_io_off_incrementFactor_rep_ready;
  wire gRRArbiter_io_out_valid;
  wire incThroughOffload_io_off_incrementFactor_req_valid;
  wire[4:0] gRRDistributor_io_out_0_tag;
  wire[4:0] gRRArbiter_io_out_tag;
  wire[4:0] incThroughOffload_io_off_incrementFactor_req_tag;
  wire gRRDistributor_io_out_0_valid;
  wire outputArb_io_in_0_ready;
  wire incThroughOffload_io_out_valid;
  wire inputDist_io_out_0_valid;
  wire incThroughOffload_io_in_ready;
  wire gRRArbiter_io_in_0_ready;
  wire[7:0] incThroughOffload_pcOutN_bits_portId;
  wire[19:0] incThroughOffload_pcOutN_bits_pcValue;
  wire[4:0] outputArb_io_out_tag;
  wire[4:0] incThroughOffload_io_out_tag;
  wire[4:0] inputDist_io_out_0_tag;
  wire outputArb_io_out_valid;
  wire[31:0] outputArb_io_out_bits;
  wire[31:0] incThroughOffload_io_out_bits;
  wire[31:0] gRRDistributor_io_out_0_bits;
  wire[31:0] inputDist_io_out_0_bits;
  wire inputDist_io_in_ready;

  assign io_pcOut_bits_request = incThroughOffload_pcOutN_bits_request;
  assign io_pcOut_bits_moduleId = incThroughOffload_pcOutN_bits_moduleId;
  assign io_pcOut_valid = incThroughOffload_pcOutN_valid;
  assign io_pcOut_bits_pcType = incThroughOffload_pcOutN_bits_pcType;
  assign mainOff_incrementFactor_rep_ready = gRRDistributor_io_in_ready;
  assign mainOff_incrementFactor_req_valid = gRRArbiter_io_out_valid;
  assign mainOff_incrementFactor_req_tag = gRRArbiter_io_out_tag;
  assign io_pcOut_bits_portId = incThroughOffload_pcOutN_bits_portId;
  assign io_pcOut_bits_pcValue = incThroughOffload_pcOutN_bits_pcValue;
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
       .pcOutN_valid( incThroughOffload_pcOutN_valid ),
       .pcOutN_bits_request( incThroughOffload_pcOutN_bits_request ),
       .pcOutN_bits_moduleId( incThroughOffload_pcOutN_bits_moduleId ),
       .pcOutN_bits_portId( incThroughOffload_pcOutN_bits_portId ),
       .pcOutN_bits_pcValue( incThroughOffload_pcOutN_bits_pcValue ),
       .pcOutN_bits_pcType( incThroughOffload_pcOutN_bits_pcType ),
       .io_off_incrementFactor_req_ready( gRRArbiter_io_in_0_ready ),
       .io_off_incrementFactor_req_valid( incThroughOffload_io_off_incrementFactor_req_valid ),
       .io_off_incrementFactor_req_bits(  ),
       .io_off_incrementFactor_req_tag( incThroughOffload_io_off_incrementFactor_req_tag ),
       .io_off_incrementFactor_rep_ready( incThroughOffload_io_off_incrementFactor_rep_ready ),
       .io_off_incrementFactor_rep_valid( gRRDistributor_io_out_0_valid ),
       .io_off_incrementFactor_rep_bits( gRRDistributor_io_out_0_bits ),
       .io_off_incrementFactor_rep_tag( gRRDistributor_io_out_0_tag ));
  RRDistributorComponent inputDist(.clk(clk), .reset(reset),
       .io_out_0_ready( incThroughOffload_io_in_ready ),
       .io_out_0_valid( inputDist_io_out_0_valid ),
       .io_out_0_bits( inputDist_io_out_0_bits ),
       .io_out_0_tag( inputDist_io_out_0_tag ),
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
       .io_chosen(  ));
  gRRArbiter_1 gRRArbiter(.clk(clk), .reset(reset),
       .io_out_ready( mainOff_incrementFactor_req_ready ),
       .io_out_valid( gRRArbiter_io_out_valid ),
       .io_out_bits(  ),
       .io_out_tag( gRRArbiter_io_out_tag ),
       .io_in_0_ready( gRRArbiter_io_in_0_ready ),
       .io_in_0_valid( incThroughOffload_io_off_incrementFactor_req_valid ),
       .io_in_0_bits(  ),
       .io_in_0_tag( incThroughOffload_io_off_incrementFactor_req_tag ),
       .io_chosen(  ));
  gRRDistributor_1 gRRDistributor(.clk(clk), .reset(reset),
       .io_out_0_ready( incThroughOffload_io_off_incrementFactor_rep_ready ),
       .io_out_0_valid( gRRDistributor_io_out_0_valid ),
       .io_out_0_bits( gRRDistributor_io_out_0_bits ),
       .io_out_0_tag( gRRDistributor_io_out_0_tag ),
       .io_in_ready( gRRDistributor_io_in_ready ),
       .io_in_valid( mainOff_incrementFactor_rep_valid ),
       .io_in_bits( mainOff_incrementFactor_rep_bits ),
       .io_in_tag( mainOff_incrementFactor_rep_tag ),
       .io_chosen(  ));
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
  wire[1:0] T76;
  wire T77;
  wire T78;
  wire T79;
  wire[7:0] T80;
  wire[7:0] T81;
  wire T82;
  wire[1:0] T83;
  wire T84;
  wire T85;
  wire T86;
  wire[1:0] T87;
  wire T88;
  wire[4:0] T89;
  wire[4:0] T90;
  reg[4:0] inputTag_0;
  wire[4:0] T91;
  wire T92;
  wire T93;
  wire T94;
  wire[1:0] T95;
  wire T96;
  wire[1:0] T97;
  wire[7:0] T98;
  wire[7:0] T99;
  wire[7:0] T100;
  wire[7:0] T101;
  wire[7:0] T102;
  wire[7:0] T103;
  wire[7:0] T104;
  reg[7:0] EmitReturnState_0;
  wire[7:0] T105;
  wire[7:0] T106;
  wire T107;
  wire T108;
  wire[19:0] T109;
  wire[19:0] T110;
  wire[19:0] T111;
  wire T112;
  wire T113;
  wire[3:0] T2;
  wire T114;
  wire[19:0] T115;
  reg[19:0] R116;
  wire T117;
  wire T118;
  wire T119;
  wire T120;
  wire T121;
  wire T122;
  wire T123;
  wire[19:0] T124;
  wire[19:0] T125;
  wire[19:0] T126;
  wire T127;
  wire T128;
  wire T129;
  wire[19:0] T130;
  wire[19:0] T131;
  wire T132;
  wire[3:0] T3;
  reg[7:0] R133;
  wire T134;
  wire[7:0] T135;
  wire T136;
  reg[3:0] R137;
  wire T138;
  wire[3:0] T139;
  wire T140;
  reg[15:0] R141;
  wire T142;
  wire[15:0] T143;
  wire T144;
  reg[0:0] R145;
  wire T146;
  wire T147;
  wire T148;
  wire[31:0] T149;
  wire[31:0] T150;
  reg[31:0] outputReg_0;
  wire[31:0] T151;

  assign io_pcOut_valid = R4;
  assign T5 = T7 || T6;
  assign T6 = ! T7;
  assign T7 = T8 && io_pcIn_bits_request;
  assign T8 = io_pcIn_valid && T9;
  assign T9 = io_pcIn_bits_moduleId == 16'h4/* 4*/;
  assign T10 = T6 ? io_pcIn_valid : T11;
  assign T11 = T7 ? 1'h1/* 1*/ : R4;
  assign io_pcOut_bits_pcValue = R12;
  assign T13 = T7 || T6;
  assign T14 = T6 ? io_pcIn_bits_pcValue : T15;
  assign T15 = T7 ? T16 : R12;
  assign T16 = T132 ? T131 : T17;
  assign T17 = T127 ? R116 : T18;
  assign T18 = T112 ? R19 : 20'h0/* 0*/;
  assign T20 = T29 || T21;
  assign T21 = T108 && T22;
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
  assign T39 = T107 && T40;
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
  assign T60 = {6'h0/* 0*/, 2'h2/* 2*/};
  assign T61 = State_0 & T62;
  assign T62 = {4'h8/* 8*/{T43}};
  assign T63 = T64 != 2'h1/* 1*/;
  assign T64 = {1'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T65 = T72 || T66;
  assign T66 = T67 && T43;
  assign T67 = T70 && T68;
  assign T68 = T61 == T69;
  assign T69 = {7'h0/* 0*/, 1'h1/* 1*/};
  assign T70 = T71 != 2'h1/* 1*/;
  assign T71 = {1'h0/* 0*/, vThreadEncoder_io_chosen};
  assign T72 = T84 || T73;
  assign T73 = T77 && T74;
  assign T74 = T75;
  assign T75 = T76[1'h0/* 0*/:1'h0/* 0*/];
  assign T76 = 1'h1/* 1*/ << rThreadEncoder_io_chosen;
  assign T77 = T78 && io_out_ready;
  assign T78 = T82 && T79;
  assign T79 = T80 == 8'hff/* 255*/;
  assign T80 = State_0 & T81;
  assign T81 = {4'h8/* 8*/{T74}};
  assign T82 = T83 != 2'h1/* 1*/;
  assign T83 = {1'h0/* 0*/, rThreadEncoder_io_chosen};
  assign T84 = T88 && T85;
  assign T85 = T86;
  assign T86 = T87[1'h0/* 0*/:1'h0/* 0*/];
  assign T87 = 1'h1/* 1*/ << sThreadEncoder_io_chosen;
  assign T88 = T96 && io_in_valid;
  assign io_out_tag = T89;
  assign T89 = inputTag_0 & T90;
  assign T90 = {3'h5/* 5*/{T74}};
  assign T91 = T84 ? io_in_tag : inputTag_0;
  assign io_out_valid = T92;
  assign T92 = T94 && T93;
  assign T93 = T80 == 8'hff/* 255*/;
  assign T94 = T95 != 2'h1/* 1*/;
  assign T95 = {1'h0/* 0*/, rThreadEncoder_io_chosen};
  assign T96 = T97 != 2'h1/* 1*/;
  assign T97 = {1'h0/* 0*/, sThreadEncoder_io_chosen};
  assign T98 = T42 ? 8'hff/* 255*/ : T99;
  assign T99 = T66 ? T106 : T100;
  assign T100 = T73 ? T103 : T101;
  assign T101 = T84 ? T102 : State_0;
  assign T102 = {7'h0/* 0*/, 1'h1/* 1*/};
  assign T103 = EmitReturnState_0 & T104;
  assign T104 = {4'h8/* 8*/{T74}};
  assign T105 = T42 ? 8'h0/* 0*/ : EmitReturnState_0;
  assign T106 = {6'h0/* 0*/, 2'h2/* 2*/};
  assign T107 = subStateTh_0 == 1'h0/* 0*/;
  assign T108 = ! T29;
  assign T109 = T21 ? T111 : T110;
  assign T110 = T29 ? 20'h0/* 0*/ : R19;
  assign T111 = R19 + 20'h1/* 1*/;
  assign T112 = T114 && T113;
  assign T113 = io_pcIn_bits_pcType == T2;
  assign T2 = {1'h0/* 0*/, 3'h1/* 1*/};
  assign T114 = T115 == 20'h1/* 1*/;
  assign T115 = {12'h0/* 0*/, io_pcIn_bits_portId};
  assign T117 = T29 || T118;
  assign T118 = T123 && T119;
  assign T119 = T121 && T120;
  assign T120 = ! R24;
  assign T121 = io_out_valid && T122;
  assign T122 = ! io_out_ready;
  assign T123 = ! T29;
  assign T124 = T118 ? T126 : T125;
  assign T125 = T29 ? 20'h0/* 0*/ : R116;
  assign T126 = R116 + 20'h1/* 1*/;
  assign T127 = T129 && T128;
  assign T128 = io_pcIn_bits_pcType == T2;
  assign T129 = T130 == 20'h2/* 2*/;
  assign T130 = {12'h0/* 0*/, io_pcIn_bits_portId};
  assign T131 = {15'h0/* 0*/, 5'h14/* 20*/};
  assign T132 = io_pcIn_bits_pcType == T3;
  assign T3 = {1'h0/* 0*/, 3'h2/* 2*/};
  assign io_pcOut_bits_portId = R133;
  assign T134 = T7 || T6;
  assign T135 = T136 ? io_pcIn_bits_portId : R133;
  assign T136 = T7 || T6;
  assign io_pcOut_bits_pcType = R137;
  assign T138 = T7 || T6;
  assign T139 = T140 ? io_pcIn_bits_pcType : R137;
  assign T140 = T7 || T6;
  assign io_pcOut_bits_moduleId = R141;
  assign T142 = T7 || T6;
  assign T143 = T144 ? io_pcIn_bits_moduleId : R141;
  assign T144 = T7 || T6;
  assign io_pcOut_bits_request = R145;
  assign T146 = T7 || T6;
  assign T147 = T6 ? io_pcIn_bits_request : T148;
  assign T148 = T7 ? 1'h0/* 0*/ : R145;
  assign io_out_bits = T149;
  assign T149 = outputReg_0 & T150;
  assign T150 = {6'h20/* 32*/{T74}};
  assign T151 = T66 ? 32'h2/* 2*/ : outputReg_0;
  RREncode_3 rThreadEncoder(
       .io_valid_0( T57 ),
       .io_chosen( rThreadEncoder_io_chosen ),
       .io_ready(  ));
  RREncode_4 vThreadEncoder(
       .io_valid_0( T46 ),
       .io_chosen( vThreadEncoder_io_chosen ),
       .io_ready(  ));
  RREncode_5 sThreadEncoder(
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
      R19 <= T109;
    end
    if(reset) begin
      R24 <= 1'h0/* 0*/;
    end else if(T25) begin
      R24 <= T32;
    end
    if(reset) begin
      State_0 <= 8'h0/* 0*/;
    end else if(T41) begin
      State_0 <= T98;
    end
    subStateTh_0 <= reset ? 1'h0/* 0*/ : T48;
    if(T84) begin
      inputTag_0 <= T91;
    end
    if(reset) begin
      EmitReturnState_0 <= 8'h0/* 0*/;
    end else if(T42) begin
      EmitReturnState_0 <= T105;
    end
    if(reset) begin
      R116 <= 20'h0/* 0*/;
    end else if(T117) begin
      R116 <= T124;
    end
    if(reset) begin
      R133 <= 8'h0/* 0*/;
    end else if(T134) begin
      R133 <= T135;
    end
    if(reset) begin
      R137 <= 4'h0/* 0*/;
    end else if(T138) begin
      R137 <= T139;
    end
    if(reset) begin
      R141 <= 16'h0/* 0*/;
    end else if(T142) begin
      R141 <= T143;
    end
    if(reset) begin
      R145 <= 1'h1/* 1*/;
    end else if(T146) begin
      R145 <= T147;
    end
    if(T66) begin
      outputReg_0 <= T151;
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
  wire mainComp_mainOff_incrementFactor_rep_ready;
  wire mainComp_mainOff_incrementFactor_req_valid;
  wire[4:0] offComp_io_out_tag;
  wire[4:0] mainComp_mainOff_incrementFactor_req_tag;
  wire offComp_io_out_valid;
  wire offComp_io_in_ready;
  wire[7:0] mainComp_io_pcOut_bits_portId;
  wire[19:0] mainComp_io_pcOut_bits_pcValue;
  wire[7:0] offComp_io_pcOut_bits_portId;
  wire[3:0] offComp_io_pcOut_bits_pcType;
  wire[15:0] offComp_io_pcOut_bits_moduleId;
  wire offComp_io_pcOut_bits_request;
  wire[4:0] mainComp_io_out_tag;
  wire mainComp_io_out_valid;
  wire[31:0] mainComp_io_out_bits;
  wire[31:0] offComp_io_out_bits;
  wire mainComp_io_in_ready;

  assign io_pcOut_valid = offComp_io_pcOut_valid;
  assign io_pcOut_bits_pcValue = offComp_io_pcOut_bits_pcValue;
  assign io_pcOut_bits_portId = offComp_io_pcOut_bits_portId;
  assign io_pcOut_bits_pcType = offComp_io_pcOut_bits_pcType;
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
  wire[31:0] generatedTop_io_out_bits;
  wire generatedTop_io_in_ready;

  assign io_pcOut_valid = generatedTop_io_pcOut_valid;
  assign io_pcOut_bits_pcValue = generatedTop_io_pcOut_bits_pcValue;
  assign io_pcOut_bits_portId = generatedTop_io_pcOut_bits_portId;
  assign io_pcOut_bits_pcType = generatedTop_io_pcOut_bits_pcType;
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

