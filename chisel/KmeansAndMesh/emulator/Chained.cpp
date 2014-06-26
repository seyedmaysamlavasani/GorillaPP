#include "Chained.h"

void Chained_t::init ( bool rand_init ) {
  { Chained_gChainedComponent_1_sink__outputData.values[0] = rand_init ? rand_val() & 4294967295 : 0; }
  { R0.values[0] = rand_init ? rand_val() & 1 : 0; }
  { R1.values[0] = rand_init ? rand_val() & 65535 : 0; }
  { R2.values[0] = rand_init ? rand_val() & 1 : 0; }
  { R3.values[0] = rand_init ? rand_val() & 255 : 0; }
  { R4.values[0] = rand_init ? rand_val() & 65535 : 0; }
  { Chained_gChainedComponent_1_sink__outputValid.values[0] = rand_init ? rand_val() & 1 : 0; }
  { R5.values[0] = rand_init ? rand_val() & 65535 : 0; }
  { R6.values[0] = rand_init ? rand_val() & 65535 : 0; }
  { R7.values[0] = rand_init ? rand_val() & 15 : 0; }
  { Chained_gChainedComponent_1_source__outputData.values[0] = rand_init ? rand_val() & 4294967295 : 0; }
  { R8.values[0] = rand_init ? rand_val() & 65535 : 0; }
  { R9.values[0] = rand_init ? rand_val() & 65535 : 0; }
  { R10.values[0] = rand_init ? rand_val() & 65535 : 0; }
  { R11.values[0] = rand_init ? rand_val() & 255 : 0; }
  { Chained_gChainedComponent_1_source__outputValid.values[0] = rand_init ? rand_val() & 1 : 0; }
  { R12.values[0] = rand_init ? rand_val() & 1 : 0; }
  { R13.values[0] = rand_init ? rand_val() & 65535 : 0; }
  { R14.values[0] = rand_init ? rand_val() & 1 : 0; }
  { R15.values[0] = rand_init ? rand_val() & 15 : 0; }
}
void Chained_t::clock_lo ( dat_t<1> reset ) {
  { Chained_gChainedComponent_1_source__io_out_bits.values[0] = Chained_gChainedComponent_1_source__outputData.values[0]; }
  { Chained_gChainedComponent_1_sink__io_in_bits.values[0] = Chained_gChainedComponent_1_source__io_out_bits.values[0]; }
  val_t T16__w0;
  { T16__w0 = Chained_gChainedComponent_1_sink__io_in_bits.values[0]+0x1L; }
  T16__w0 = T16__w0 & 4294967295;
  { Chained_gChainedComponent_1_sink__outputData_shadow.values[0] = T16__w0; }
  { Chained_gChainedComponent_1__reset.values[0] = reset.values[0]; }
  { Chained_gChainedComponent_1_sink__reset.values[0] = Chained_gChainedComponent_1__reset.values[0]; }
  { Chained_gChainedComponent_1_source__io_pcOut_bits_request.values[0] = R14.values[0]; }
  { Chained_gChainedComponent_1_sink__io_pcIn_bits_request.values[0] = Chained_gChainedComponent_1_source__io_pcOut_bits_request.values[0]; }
  { Chained_gChainedComponent_1_source__io_pcOut_bits_moduleId.values[0] = R13.values[0]; }
  { Chained_gChainedComponent_1_sink__io_pcIn_bits_moduleId.values[0] = Chained_gChainedComponent_1_source__io_pcOut_bits_moduleId.values[0]; }
  val_t T17__w0;
  T17__w0 = Chained_gChainedComponent_1_sink__io_pcIn_bits_moduleId.values[0] == 0x3L;
  { Chained_gChainedComponent_1_source__io_pcOut_valid.values[0] = R12.values[0]; }
  { Chained_gChainedComponent_1_sink__io_pcIn_valid.values[0] = Chained_gChainedComponent_1_source__io_pcOut_valid.values[0]; }
  val_t T18__w0;
  { T18__w0 = Chained_gChainedComponent_1_sink__io_pcIn_valid.values[0]&&T17__w0; }
  val_t T19__w0;
  { T19__w0 = T18__w0&&Chained_gChainedComponent_1_sink__io_pcIn_bits_request.values[0]; }
  val_t T20__w0;
  { val_t __mask = -T19__w0; T20__w0 = R0.values[0] ^ ((R0.values[0] ^ 0x1L) & __mask); }
  val_t T21__w0;
  T21__w0 = !T19__w0;
  val_t T22__w0;
  { val_t __mask = -T21__w0; T22__w0 = T20__w0 ^ ((T20__w0 ^ Chained_gChainedComponent_1_sink__io_pcIn_valid.values[0]) & __mask); }
  { R0_shadow.values[0] = TERNARY(Chained_gChainedComponent_1_sink__reset.values[0], 0x0L, T22__w0); }
  val_t T23__w0;
  { T23__w0 = T19__w0||T21__w0; }
  val_t T24__w0;
  { val_t __mask = -T23__w0; T24__w0 = R1.values[0] ^ ((R1.values[0] ^ Chained_gChainedComponent_1_sink__io_pcIn_bits_moduleId.values[0]) & __mask); }
  { R1_shadow.values[0] = TERNARY(Chained_gChainedComponent_1_sink__reset.values[0], 0x0L, T24__w0); }
  val_t T25__w0;
  { val_t __mask = -T19__w0; T25__w0 = R2.values[0] ^ ((R2.values[0] ^ 0x0L) & __mask); }
  val_t T26__w0;
  { val_t __mask = -T21__w0; T26__w0 = T25__w0 ^ ((T25__w0 ^ Chained_gChainedComponent_1_sink__io_pcIn_bits_request.values[0]) & __mask); }
  { R2_shadow.values[0] = TERNARY(Chained_gChainedComponent_1_sink__reset.values[0], 0x1L, T26__w0); }
  { Chained_gChainedComponent_1_source__io_pcOut_bits_portId.values[0] = R11.values[0]; }
  { Chained_gChainedComponent_1_sink__io_pcIn_bits_portId.values[0] = Chained_gChainedComponent_1_source__io_pcOut_bits_portId.values[0]; }
  val_t T27__w0;
  { T27__w0 = T19__w0||T21__w0; }
  val_t T28__w0;
  { val_t __mask = -T27__w0; T28__w0 = R3.values[0] ^ ((R3.values[0] ^ Chained_gChainedComponent_1_sink__io_pcIn_bits_portId.values[0]) & __mask); }
  { R3_shadow.values[0] = TERNARY(Chained_gChainedComponent_1_sink__reset.values[0], 0x0L, T28__w0); }
  { Chained_gChainedComponent_1_source__io_pcOut_bits_pcType.values[0] = R15.values[0]; }
  { Chained_gChainedComponent_1_sink__io_pcIn_bits_pcType.values[0] = Chained_gChainedComponent_1_source__io_pcOut_bits_pcType.values[0]; }
  val_t T29__w0;
  T29__w0 = Chained_gChainedComponent_1_sink__io_pcIn_bits_pcType.values[0] == 0x2L;
  val_t T30__w0;
  { T30__w0 = Chained_gChainedComponent_1_sink__io_pcIn_valid.values[0]&&Chained_gChainedComponent_1_sink__io_pcIn_bits_request.values[0]; }
  val_t T31__w0;
  { T31__w0 = T30__w0&&T29__w0; }
  val_t T32__w0;
  { val_t __mask = -T31__w0; T32__w0 = R4.values[0] ^ ((R4.values[0] ^ 0x0L) & __mask); }
  val_t T33__w0;
  { T33__w0 = R4.values[0]+0x1L; }
  T33__w0 = T33__w0 & 65535;
  { Chained_gChainedComponent_1__io_out_ready.values[0] = Chained__io_out_ready.values[0]; }
  { Chained_gChainedComponent_1_sink__io_out_ready.values[0] = Chained_gChainedComponent_1__io_out_ready.values[0]; }
  { Chained_gChainedComponent_1_sink__io_in_ready.values[0] = Chained_gChainedComponent_1_sink__io_out_ready.values[0]; }
  val_t T34__w0;
  T34__w0 = !Chained_gChainedComponent_1_sink__io_in_ready.values[0];
  { Chained_gChainedComponent_1_source__io_out_ready.values[0] = Chained_gChainedComponent_1_sink__io_in_ready.values[0]; }
  val_t T35__w0;
  { T35__w0 = Chained_gChainedComponent_1_source__outputValid.values[0]&&Chained_gChainedComponent_1_source__io_out_ready.values[0]; }
  { Chained_gChainedComponent_1_source__io_out_valid.values[0] = T35__w0; }
  { Chained_gChainedComponent_1_sink__io_in_valid.values[0] = Chained_gChainedComponent_1_source__io_out_valid.values[0]; }
  val_t T36__w0;
  { T36__w0 = Chained_gChainedComponent_1_sink__io_in_valid.values[0]&&T34__w0; }
  val_t T37__w0;
  T37__w0 = !T31__w0;
  val_t T38__w0;
  { T38__w0 = T37__w0&&T36__w0; }
  val_t T39__w0;
  { val_t __mask = -T38__w0; T39__w0 = T32__w0 ^ ((T32__w0 ^ T33__w0) & __mask); }
  { R4_shadow.values[0] = TERNARY(Chained_gChainedComponent_1_sink__reset.values[0], 0x0L, T39__w0); }
  { Chained_gChainedComponent_1_sink__outputValid_shadow.values[0] = Chained_gChainedComponent_1_sink__io_in_valid.values[0]; }
  val_t T40__w0;
  T40__w0 = Chained_gChainedComponent_1_sink__io_pcIn_bits_pcType.values[0] == 0x2L;
  val_t T41__w0;
  { T41__w0 = Chained_gChainedComponent_1_sink__io_pcIn_valid.values[0]&&Chained_gChainedComponent_1_sink__io_pcIn_bits_request.values[0]; }
  val_t T42__w0;
  { T42__w0 = T41__w0&&T40__w0; }
  val_t T43__w0;
  { val_t __mask = -T42__w0; T43__w0 = R5.values[0] ^ ((R5.values[0] ^ 0x0L) & __mask); }
  val_t T44__w0;
  { T44__w0 = R5.values[0]+0x1L; }
  T44__w0 = T44__w0 & 65535;
  val_t T45__w0;
  T45__w0 = !Chained_gChainedComponent_1_sink__io_out_ready.values[0];
  val_t T46__w0;
  { T46__w0 = Chained_gChainedComponent_1_sink__outputValid.values[0]&&Chained_gChainedComponent_1_sink__io_out_ready.values[0]; }
  { Chained_gChainedComponent_1_sink__io_out_valid.values[0] = T46__w0; }
  val_t T47__w0;
  { T47__w0 = Chained_gChainedComponent_1_sink__io_out_valid.values[0]&&T45__w0; }
  val_t T48__w0;
  T48__w0 = !T42__w0;
  val_t T49__w0;
  { T49__w0 = T48__w0&&T47__w0; }
  val_t T50__w0;
  { val_t __mask = -T49__w0; T50__w0 = T43__w0 ^ ((T43__w0 ^ T44__w0) & __mask); }
  { R5_shadow.values[0] = TERNARY(Chained_gChainedComponent_1_sink__reset.values[0], 0x0L, T50__w0); }
  val_t T51__w0;
  { T51__w0 = Chained_gChainedComponent_1_sink__io_pcIn_bits_portId.values[0] | 0x0L << 8; }
  val_t T52__w0;
  T52__w0 = T51__w0 == 0x1L;
  val_t T53__w0;
  { val_t __mask = -T52__w0; T53__w0 = R5.values[0] ^ ((R5.values[0] ^ R4.values[0]) & __mask); }
  val_t T54__w0;
  { val_t __mask = -T19__w0; T54__w0 = R6.values[0] ^ ((R6.values[0] ^ T53__w0) & __mask); }
  { Chained_gChainedComponent_1_source__io_pcOut_bits_pcValue.values[0] = R10.values[0]; }
  { Chained_gChainedComponent_1_sink__io_pcIn_bits_pcValue.values[0] = Chained_gChainedComponent_1_source__io_pcOut_bits_pcValue.values[0]; }
  val_t T55__w0;
  { val_t __mask = -T21__w0; T55__w0 = T54__w0 ^ ((T54__w0 ^ Chained_gChainedComponent_1_sink__io_pcIn_bits_pcValue.values[0]) & __mask); }
  { R6_shadow.values[0] = TERNARY(Chained_gChainedComponent_1_sink__reset.values[0], 0x0L, T55__w0); }
  val_t T56__w0;
  { T56__w0 = T19__w0||T21__w0; }
  val_t T57__w0;
  { val_t __mask = -T56__w0; T57__w0 = R7.values[0] ^ ((R7.values[0] ^ Chained_gChainedComponent_1_sink__io_pcIn_bits_pcType.values[0]) & __mask); }
  { R7_shadow.values[0] = TERNARY(Chained_gChainedComponent_1_sink__reset.values[0], 0x0L, T57__w0); }
  { Chained_gChainedComponent_1__io_in_bits.values[0] = Chained__io_in_bits.values[0]; }
  { Chained_gChainedComponent_1_source__io_in_bits.values[0] = Chained_gChainedComponent_1__io_in_bits.values[0]; }
  val_t T58__w0;
  { T58__w0 = Chained_gChainedComponent_1_source__io_in_bits.values[0]+0x1L; }
  T58__w0 = T58__w0 & 4294967295;
  { Chained_gChainedComponent_1_source__outputData_shadow.values[0] = T58__w0; }
  { Chained_gChainedComponent_1_source__reset.values[0] = Chained_gChainedComponent_1__reset.values[0]; }
  { Chained_gChainedComponent_1__io_pcIn_bits_pcType.values[0] = Chained__io_pcIn_bits_pcType.values[0]; }
  { Chained_gChainedComponent_1_source__io_pcIn_bits_pcType.values[0] = Chained_gChainedComponent_1__io_pcIn_bits_pcType.values[0]; }
  val_t T59__w0;
  T59__w0 = Chained_gChainedComponent_1_source__io_pcIn_bits_pcType.values[0] == 0x2L;
  { Chained_gChainedComponent_1__io_pcIn_bits_request.values[0] = Chained__io_pcIn_bits_request.values[0]; }
  { Chained_gChainedComponent_1_source__io_pcIn_bits_request.values[0] = Chained_gChainedComponent_1__io_pcIn_bits_request.values[0]; }
  { Chained_gChainedComponent_1__io_pcIn_valid.values[0] = Chained__io_pcIn_valid.values[0]; }
  { Chained_gChainedComponent_1_source__io_pcIn_valid.values[0] = Chained_gChainedComponent_1__io_pcIn_valid.values[0]; }
  val_t T60__w0;
  { T60__w0 = Chained_gChainedComponent_1_source__io_pcIn_valid.values[0]&&Chained_gChainedComponent_1_source__io_pcIn_bits_request.values[0]; }
  val_t T61__w0;
  { T61__w0 = T60__w0&&T59__w0; }
  val_t T62__w0;
  { val_t __mask = -T61__w0; T62__w0 = R8.values[0] ^ ((R8.values[0] ^ 0x0L) & __mask); }
  val_t T63__w0;
  { T63__w0 = R8.values[0]+0x1L; }
  T63__w0 = T63__w0 & 65535;
  { Chained_gChainedComponent_1_source__io_in_ready.values[0] = Chained_gChainedComponent_1_source__io_out_ready.values[0]; }
  val_t T64__w0;
  T64__w0 = !Chained_gChainedComponent_1_source__io_in_ready.values[0];
  { Chained_gChainedComponent_1__io_in_valid.values[0] = Chained__io_in_valid.values[0]; }
  { Chained_gChainedComponent_1_source__io_in_valid.values[0] = Chained_gChainedComponent_1__io_in_valid.values[0]; }
  val_t T65__w0;
  { T65__w0 = Chained_gChainedComponent_1_source__io_in_valid.values[0]&&T64__w0; }
  val_t T66__w0;
  T66__w0 = !T61__w0;
  val_t T67__w0;
  { T67__w0 = T66__w0&&T65__w0; }
  val_t T68__w0;
  { val_t __mask = -T67__w0; T68__w0 = T62__w0 ^ ((T62__w0 ^ T63__w0) & __mask); }
  { R8_shadow.values[0] = TERNARY(Chained_gChainedComponent_1_source__reset.values[0], 0x0L, T68__w0); }
  val_t T69__w0;
  T69__w0 = Chained_gChainedComponent_1_source__io_pcIn_bits_pcType.values[0] == 0x2L;
  val_t T70__w0;
  { T70__w0 = Chained_gChainedComponent_1_source__io_pcIn_valid.values[0]&&Chained_gChainedComponent_1_source__io_pcIn_bits_request.values[0]; }
  val_t T71__w0;
  { T71__w0 = T70__w0&&T69__w0; }
  val_t T72__w0;
  { val_t __mask = -T71__w0; T72__w0 = R9.values[0] ^ ((R9.values[0] ^ 0x0L) & __mask); }
  val_t T73__w0;
  { T73__w0 = R9.values[0]+0x1L; }
  T73__w0 = T73__w0 & 65535;
  val_t T74__w0;
  T74__w0 = !Chained_gChainedComponent_1_source__io_out_ready.values[0];
  val_t T75__w0;
  { T75__w0 = Chained_gChainedComponent_1_source__io_out_valid.values[0]&&T74__w0; }
  val_t T76__w0;
  T76__w0 = !T71__w0;
  val_t T77__w0;
  { T77__w0 = T76__w0&&T75__w0; }
  val_t T78__w0;
  { val_t __mask = -T77__w0; T78__w0 = T72__w0 ^ ((T72__w0 ^ T73__w0) & __mask); }
  { R9_shadow.values[0] = TERNARY(Chained_gChainedComponent_1_source__reset.values[0], 0x0L, T78__w0); }
  { Chained_gChainedComponent_1__io_pcIn_bits_portId.values[0] = Chained__io_pcIn_bits_portId.values[0]; }
  { Chained_gChainedComponent_1_source__io_pcIn_bits_portId.values[0] = Chained_gChainedComponent_1__io_pcIn_bits_portId.values[0]; }
  val_t T79__w0;
  { T79__w0 = Chained_gChainedComponent_1_source__io_pcIn_bits_portId.values[0] | 0x0L << 8; }
  val_t T80__w0;
  T80__w0 = T79__w0 == 0x1L;
  val_t T81__w0;
  { val_t __mask = -T80__w0; T81__w0 = R9.values[0] ^ ((R9.values[0] ^ R8.values[0]) & __mask); }
  { Chained_gChainedComponent_1__io_pcIn_bits_moduleId.values[0] = Chained__io_pcIn_bits_moduleId.values[0]; }
  { Chained_gChainedComponent_1_source__io_pcIn_bits_moduleId.values[0] = Chained_gChainedComponent_1__io_pcIn_bits_moduleId.values[0]; }
  val_t T82__w0;
  T82__w0 = Chained_gChainedComponent_1_source__io_pcIn_bits_moduleId.values[0] == 0x2L;
  val_t T83__w0;
  { T83__w0 = Chained_gChainedComponent_1_source__io_pcIn_valid.values[0]&&T82__w0; }
  val_t T84__w0;
  { T84__w0 = T83__w0&&Chained_gChainedComponent_1_source__io_pcIn_bits_request.values[0]; }
  val_t T85__w0;
  { val_t __mask = -T84__w0; T85__w0 = R10.values[0] ^ ((R10.values[0] ^ T81__w0) & __mask); }
  { Chained_gChainedComponent_1__io_pcIn_bits_pcValue.values[0] = Chained__io_pcIn_bits_pcValue.values[0]; }
  { Chained_gChainedComponent_1_source__io_pcIn_bits_pcValue.values[0] = Chained_gChainedComponent_1__io_pcIn_bits_pcValue.values[0]; }
  val_t T86__w0;
  T86__w0 = !T84__w0;
  val_t T87__w0;
  { val_t __mask = -T86__w0; T87__w0 = T85__w0 ^ ((T85__w0 ^ Chained_gChainedComponent_1_source__io_pcIn_bits_pcValue.values[0]) & __mask); }
  { R10_shadow.values[0] = TERNARY(Chained_gChainedComponent_1_source__reset.values[0], 0x0L, T87__w0); }
  val_t T88__w0;
  { T88__w0 = T84__w0||T86__w0; }
  val_t T89__w0;
  { val_t __mask = -T88__w0; T89__w0 = R11.values[0] ^ ((R11.values[0] ^ Chained_gChainedComponent_1_source__io_pcIn_bits_portId.values[0]) & __mask); }
  { R11_shadow.values[0] = TERNARY(Chained_gChainedComponent_1_source__reset.values[0], 0x0L, T89__w0); }
  { Chained_gChainedComponent_1_source__outputValid_shadow.values[0] = Chained_gChainedComponent_1_source__io_in_valid.values[0]; }
  val_t T90__w0;
  { val_t __mask = -T84__w0; T90__w0 = R12.values[0] ^ ((R12.values[0] ^ 0x1L) & __mask); }
  val_t T91__w0;
  { val_t __mask = -T86__w0; T91__w0 = T90__w0 ^ ((T90__w0 ^ Chained_gChainedComponent_1_source__io_pcIn_valid.values[0]) & __mask); }
  { R12_shadow.values[0] = TERNARY(Chained_gChainedComponent_1_source__reset.values[0], 0x0L, T91__w0); }
  val_t T92__w0;
  { T92__w0 = T84__w0||T86__w0; }
  val_t T93__w0;
  { val_t __mask = -T92__w0; T93__w0 = R13.values[0] ^ ((R13.values[0] ^ Chained_gChainedComponent_1_source__io_pcIn_bits_moduleId.values[0]) & __mask); }
  { R13_shadow.values[0] = TERNARY(Chained_gChainedComponent_1_source__reset.values[0], 0x0L, T93__w0); }
  val_t T94__w0;
  { val_t __mask = -T84__w0; T94__w0 = R14.values[0] ^ ((R14.values[0] ^ 0x0L) & __mask); }
  val_t T95__w0;
  { val_t __mask = -T86__w0; T95__w0 = T94__w0 ^ ((T94__w0 ^ Chained_gChainedComponent_1_source__io_pcIn_bits_request.values[0]) & __mask); }
  { R14_shadow.values[0] = TERNARY(Chained_gChainedComponent_1_source__reset.values[0], 0x1L, T95__w0); }
  val_t T96__w0;
  { T96__w0 = T84__w0||T86__w0; }
  val_t T97__w0;
  { val_t __mask = -T96__w0; T97__w0 = R15.values[0] ^ ((R15.values[0] ^ Chained_gChainedComponent_1_source__io_pcIn_bits_pcType.values[0]) & __mask); }
  { R15_shadow.values[0] = TERNARY(Chained_gChainedComponent_1_source__reset.values[0], 0x0L, T97__w0); }
  { Chained_gChainedComponent_1__io_in_ready.values[0] = Chained_gChainedComponent_1_source__io_in_ready.values[0]; }
  { Chained__io_in_ready.values[0] = Chained_gChainedComponent_1__io_in_ready.values[0]; }
  { Chained_gChainedComponent_1__io_out_valid.values[0] = Chained_gChainedComponent_1_sink__io_out_valid.values[0]; }
  { Chained__io_out_valid.values[0] = Chained_gChainedComponent_1__io_out_valid.values[0]; }
  { Chained_gChainedComponent_1_sink__io_out_bits.values[0] = Chained_gChainedComponent_1_sink__outputData.values[0]; }
  { Chained_gChainedComponent_1__io_out_bits.values[0] = Chained_gChainedComponent_1_sink__io_out_bits.values[0]; }
  { Chained__io_out_bits.values[0] = Chained_gChainedComponent_1__io_out_bits.values[0]; }
  { Chained_gChainedComponent_1_sink__io_pcOut_valid.values[0] = R0.values[0]; }
  { Chained_gChainedComponent_1__io_pcOut_valid.values[0] = Chained_gChainedComponent_1_sink__io_pcOut_valid.values[0]; }
  { Chained__io_pcOut_valid.values[0] = Chained_gChainedComponent_1__io_pcOut_valid.values[0]; }
  { Chained_gChainedComponent_1_sink__io_pcOut_bits_moduleId.values[0] = R1.values[0]; }
  { Chained_gChainedComponent_1__io_pcOut_bits_moduleId.values[0] = Chained_gChainedComponent_1_sink__io_pcOut_bits_moduleId.values[0]; }
  { Chained__io_pcOut_bits_moduleId.values[0] = Chained_gChainedComponent_1__io_pcOut_bits_moduleId.values[0]; }
  { Chained_gChainedComponent_1_sink__io_pcOut_bits_request.values[0] = R2.values[0]; }
  { Chained_gChainedComponent_1__io_pcOut_bits_request.values[0] = Chained_gChainedComponent_1_sink__io_pcOut_bits_request.values[0]; }
  { Chained__io_pcOut_bits_request.values[0] = Chained_gChainedComponent_1__io_pcOut_bits_request.values[0]; }
  { Chained_gChainedComponent_1_sink__io_pcOut_bits_portId.values[0] = R3.values[0]; }
  { Chained_gChainedComponent_1__io_pcOut_bits_portId.values[0] = Chained_gChainedComponent_1_sink__io_pcOut_bits_portId.values[0]; }
  { Chained__io_pcOut_bits_portId.values[0] = Chained_gChainedComponent_1__io_pcOut_bits_portId.values[0]; }
  { Chained_gChainedComponent_1_sink__io_pcOut_bits_pcValue.values[0] = R6.values[0]; }
  { Chained_gChainedComponent_1__io_pcOut_bits_pcValue.values[0] = Chained_gChainedComponent_1_sink__io_pcOut_bits_pcValue.values[0]; }
  { Chained__io_pcOut_bits_pcValue.values[0] = Chained_gChainedComponent_1__io_pcOut_bits_pcValue.values[0]; }
  { Chained_gChainedComponent_1_sink__io_pcOut_bits_pcType.values[0] = R7.values[0]; }
  { Chained_gChainedComponent_1__io_pcOut_bits_pcType.values[0] = Chained_gChainedComponent_1_sink__io_pcOut_bits_pcType.values[0]; }
  { Chained__io_pcOut_bits_pcType.values[0] = Chained_gChainedComponent_1__io_pcOut_bits_pcType.values[0]; }
}
void Chained_t::clock_hi ( dat_t<1> reset ) {
  dat_t<32> Chained_gChainedComponent_1_sink__outputData_shadow_out = Chained_gChainedComponent_1_sink__outputData;
  dat_t<1> R0_shadow_out = R0;
  dat_t<16> R1_shadow_out = R1;
  dat_t<1> R2_shadow_out = R2;
  dat_t<8> R3_shadow_out = R3;
  dat_t<16> R4_shadow_out = R4;
  dat_t<1> Chained_gChainedComponent_1_sink__outputValid_shadow_out = Chained_gChainedComponent_1_sink__outputValid;
  dat_t<16> R5_shadow_out = R5;
  dat_t<16> R6_shadow_out = R6;
  dat_t<4> R7_shadow_out = R7;
  dat_t<32> Chained_gChainedComponent_1_source__outputData_shadow_out = Chained_gChainedComponent_1_source__outputData;
  dat_t<16> R8_shadow_out = R8;
  dat_t<16> R9_shadow_out = R9;
  dat_t<16> R10_shadow_out = R10;
  dat_t<8> R11_shadow_out = R11;
  dat_t<1> Chained_gChainedComponent_1_source__outputValid_shadow_out = Chained_gChainedComponent_1_source__outputValid;
  dat_t<1> R12_shadow_out = R12;
  dat_t<16> R13_shadow_out = R13;
  dat_t<1> R14_shadow_out = R14;
  dat_t<4> R15_shadow_out = R15;
  Chained_gChainedComponent_1_sink__outputData = Chained_gChainedComponent_1_sink__outputData_shadow;
  R0 = R0_shadow;
  R1 = R1_shadow;
  R2 = R2_shadow;
  R3 = R3_shadow;
  R4 = R4_shadow;
  Chained_gChainedComponent_1_sink__outputValid = Chained_gChainedComponent_1_sink__outputValid_shadow;
  R5 = R5_shadow;
  R6 = R6_shadow;
  R7 = R7_shadow;
  Chained_gChainedComponent_1_source__outputData = Chained_gChainedComponent_1_source__outputData_shadow;
  R8 = R8_shadow;
  R9 = R9_shadow;
  R10 = R10_shadow;
  R11 = R11_shadow;
  Chained_gChainedComponent_1_source__outputValid = Chained_gChainedComponent_1_source__outputValid_shadow;
  R12 = R12_shadow;
  R13 = R13_shadow;
  R14 = R14_shadow;
  R15 = R15_shadow;
}
void Chained_t::print ( FILE* f ) {
  fprintf(f, "%s", TO_CSTR(Chained__io_in_ready));
  fprintf(f, "%s", " ");
  fprintf(f, "%s", TO_CSTR(Chained__io_out_valid));
  fprintf(f, "%s", " ");
  fprintf(f, "%s", TO_CSTR(Chained__io_out_bits));
  fprintf(f, "%s", " ");
  fprintf(f, "%s", TO_CSTR(Chained__io_pcOut_valid));
  fprintf(f, "%s", " ");
  fprintf(f, "%s", TO_CSTR(Chained__io_pcOut_bits_request));
  fprintf(f, "%s", " ");
  fprintf(f, "%s", TO_CSTR(Chained__io_pcOut_bits_moduleId));
  fprintf(f, "%s", " ");
  fprintf(f, "%s", TO_CSTR(Chained__io_pcOut_bits_portId));
  fprintf(f, "%s", " ");
  fprintf(f, "%s", TO_CSTR(Chained__io_pcOut_bits_pcValue));
  fprintf(f, "%s", " ");
  fprintf(f, "%s", TO_CSTR(Chained__io_pcOut_bits_pcType));
  fprintf(f, "\n");
  fflush(f);
}
bool Chained_t::scan ( FILE* f ) {
  str_to_dat(read_tok(f), Chained__io_in_valid);
  str_to_dat(read_tok(f), Chained__io_in_bits);
  str_to_dat(read_tok(f), Chained__io_out_ready);
  str_to_dat(read_tok(f), Chained__io_pcIn_valid);
  str_to_dat(read_tok(f), Chained__io_pcIn_bits_request);
  str_to_dat(read_tok(f), Chained__io_pcIn_bits_moduleId);
  str_to_dat(read_tok(f), Chained__io_pcIn_bits_portId);
  str_to_dat(read_tok(f), Chained__io_pcIn_bits_pcValue);
  str_to_dat(read_tok(f), Chained__io_pcIn_bits_pcType);
  return(!feof(f));
}
void Chained_t::dump(FILE *f, int t) {
  if (t == 0) {
    fprintf(f, "$timescale 1ps $end\n");
    fprintf(f, "$scope module Chained $end\n");
    fprintf(f, "$var wire 1 N3 reset $end\n");
    fprintf(f, "$var wire 1 N20 io_out_ready $end\n");
    fprintf(f, "$var wire 32 N35 io_in_bits $end\n");
    fprintf(f, "$var wire 4 N40 io_pcIn_bits_pcType $end\n");
    fprintf(f, "$var wire 1 N43 io_pcIn_bits_request $end\n");
    fprintf(f, "$var wire 1 N46 io_pcIn_valid $end\n");
    fprintf(f, "$var wire 1 N50 io_in_valid $end\n");
    fprintf(f, "$var wire 8 N55 io_pcIn_bits_portId $end\n");
    fprintf(f, "$var wire 16 N58 io_pcIn_bits_moduleId $end\n");
    fprintf(f, "$var wire 16 N61 io_pcIn_bits_pcValue $end\n");
    fprintf(f, "$var wire 1 N72 io_in_ready $end\n");
    fprintf(f, "$var wire 1 N74 io_out_valid $end\n");
    fprintf(f, "$var wire 32 N77 io_out_bits $end\n");
    fprintf(f, "$var wire 1 N80 io_pcOut_valid $end\n");
    fprintf(f, "$var wire 16 N83 io_pcOut_bits_moduleId $end\n");
    fprintf(f, "$var wire 1 N86 io_pcOut_bits_request $end\n");
    fprintf(f, "$var wire 8 N89 io_pcOut_bits_portId $end\n");
    fprintf(f, "$var wire 16 N92 io_pcOut_bits_pcValue $end\n");
    fprintf(f, "$var wire 4 N95 io_pcOut_bits_pcType $end\n");
    fprintf(f, "$scope module gChainedComponent_1 $end\n");
    fprintf(f, "$var wire 1 N4 reset $end\n");
    fprintf(f, "$var wire 1 N21 io_out_ready $end\n");
    fprintf(f, "$var wire 32 N36 io_in_bits $end\n");
    fprintf(f, "$var wire 4 N41 io_pcIn_bits_pcType $end\n");
    fprintf(f, "$var wire 1 N44 io_pcIn_bits_request $end\n");
    fprintf(f, "$var wire 1 N47 io_pcIn_valid $end\n");
    fprintf(f, "$var wire 1 N51 io_in_valid $end\n");
    fprintf(f, "$var wire 8 N56 io_pcIn_bits_portId $end\n");
    fprintf(f, "$var wire 16 N59 io_pcIn_bits_moduleId $end\n");
    fprintf(f, "$var wire 16 N62 io_pcIn_bits_pcValue $end\n");
    fprintf(f, "$var wire 1 N71 io_in_ready $end\n");
    fprintf(f, "$var wire 1 N73 io_out_valid $end\n");
    fprintf(f, "$var wire 32 N76 io_out_bits $end\n");
    fprintf(f, "$var wire 1 N79 io_pcOut_valid $end\n");
    fprintf(f, "$var wire 16 N82 io_pcOut_bits_moduleId $end\n");
    fprintf(f, "$var wire 1 N85 io_pcOut_bits_request $end\n");
    fprintf(f, "$var wire 8 N88 io_pcOut_bits_portId $end\n");
    fprintf(f, "$var wire 16 N91 io_pcOut_bits_pcValue $end\n");
    fprintf(f, "$var wire 4 N94 io_pcOut_bits_pcType $end\n");
    fprintf(f, "$scope module IncgComponent_2 $end\n");
    fprintf(f, "$var wire 32 N0 io_out_bits $end\n");
    fprintf(f, "$var wire 1 N6 io_pcOut_bits_request $end\n");
    fprintf(f, "$var wire 16 N8 io_pcOut_bits_moduleId $end\n");
    fprintf(f, "$var wire 1 N10 io_pcOut_valid $end\n");
    fprintf(f, "$var wire 8 N15 io_pcOut_bits_portId $end\n");
    fprintf(f, "$var wire 4 N18 io_pcOut_bits_pcType $end\n");
    fprintf(f, "$var wire 1 N24 io_out_ready $end\n");
    fprintf(f, "$var wire 1 N25 io_out_valid $end\n");
    fprintf(f, "$var wire 16 N31 io_pcOut_bits_pcValue $end\n");
    fprintf(f, "$var wire 32 N37 io_in_bits $end\n");
    fprintf(f, "$var wire 32 N38 outputData $end\n");
    fprintf(f, "$var wire 1 N39 reset $end\n");
    fprintf(f, "$var wire 4 N42 io_pcIn_bits_pcType $end\n");
    fprintf(f, "$var wire 1 N45 io_pcIn_bits_request $end\n");
    fprintf(f, "$var wire 1 N48 io_pcIn_valid $end\n");
    fprintf(f, "$var wire 1 N49 io_in_ready $end\n");
    fprintf(f, "$var wire 1 N52 io_in_valid $end\n");
    fprintf(f, "$var wire 16 N53 R8 $end\n");
    fprintf(f, "$var wire 16 N54 R9 $end\n");
    fprintf(f, "$var wire 8 N57 io_pcIn_bits_portId $end\n");
    fprintf(f, "$var wire 16 N60 io_pcIn_bits_moduleId $end\n");
    fprintf(f, "$var wire 16 N63 io_pcIn_bits_pcValue $end\n");
    fprintf(f, "$var wire 16 N64 R10 $end\n");
    fprintf(f, "$var wire 8 N65 R11 $end\n");
    fprintf(f, "$var wire 1 N66 outputValid $end\n");
    fprintf(f, "$var wire 1 N67 R12 $end\n");
    fprintf(f, "$var wire 16 N68 R13 $end\n");
    fprintf(f, "$var wire 1 N69 R14 $end\n");
    fprintf(f, "$var wire 4 N70 R15 $end\n");
    fprintf(f, "$upscope $end\n");
    fprintf(f, "$scope module IncgComponent_3 $end\n");
    fprintf(f, "$var wire 32 N1 io_in_bits $end\n");
    fprintf(f, "$var wire 32 N2 outputData $end\n");
    fprintf(f, "$var wire 1 N5 reset $end\n");
    fprintf(f, "$var wire 1 N7 io_pcIn_bits_request $end\n");
    fprintf(f, "$var wire 16 N9 io_pcIn_bits_moduleId $end\n");
    fprintf(f, "$var wire 1 N11 io_pcIn_valid $end\n");
    fprintf(f, "$var wire 1 N12 R0 $end\n");
    fprintf(f, "$var wire 16 N13 R1 $end\n");
    fprintf(f, "$var wire 1 N14 R2 $end\n");
    fprintf(f, "$var wire 8 N16 io_pcIn_bits_portId $end\n");
    fprintf(f, "$var wire 8 N17 R3 $end\n");
    fprintf(f, "$var wire 4 N19 io_pcIn_bits_pcType $end\n");
    fprintf(f, "$var wire 1 N22 io_out_ready $end\n");
    fprintf(f, "$var wire 1 N23 io_in_ready $end\n");
    fprintf(f, "$var wire 1 N26 io_in_valid $end\n");
    fprintf(f, "$var wire 16 N27 R4 $end\n");
    fprintf(f, "$var wire 1 N28 outputValid $end\n");
    fprintf(f, "$var wire 1 N29 io_out_valid $end\n");
    fprintf(f, "$var wire 16 N30 R5 $end\n");
    fprintf(f, "$var wire 16 N32 io_pcIn_bits_pcValue $end\n");
    fprintf(f, "$var wire 16 N33 R6 $end\n");
    fprintf(f, "$var wire 4 N34 R7 $end\n");
    fprintf(f, "$var wire 32 N75 io_out_bits $end\n");
    fprintf(f, "$var wire 1 N78 io_pcOut_valid $end\n");
    fprintf(f, "$var wire 16 N81 io_pcOut_bits_moduleId $end\n");
    fprintf(f, "$var wire 1 N84 io_pcOut_bits_request $end\n");
    fprintf(f, "$var wire 8 N87 io_pcOut_bits_portId $end\n");
    fprintf(f, "$var wire 16 N90 io_pcOut_bits_pcValue $end\n");
    fprintf(f, "$var wire 4 N93 io_pcOut_bits_pcType $end\n");
    fprintf(f, "$upscope $end\n");
    fprintf(f, "$upscope $end\n");
    fprintf(f, "$upscope $end\n");
    fprintf(f, "$enddefinitions $end\n");
    fprintf(f, "$dumpvars\n");
    fprintf(f, "$end\n");
  }
  fprintf(f, "#%d\n", t);
  if (t == 0 || (Chained_gChainedComponent_1_source__io_out_bits != Chained_gChainedComponent_1_source__io_out_bits__prev).to_bool())
    dat_dump(f, Chained_gChainedComponent_1_source__io_out_bits, "N0");
  Chained_gChainedComponent_1_source__io_out_bits__prev = Chained_gChainedComponent_1_source__io_out_bits;
  if (t == 0 || (Chained_gChainedComponent_1_sink__io_in_bits != Chained_gChainedComponent_1_sink__io_in_bits__prev).to_bool())
    dat_dump(f, Chained_gChainedComponent_1_sink__io_in_bits, "N1");
  Chained_gChainedComponent_1_sink__io_in_bits__prev = Chained_gChainedComponent_1_sink__io_in_bits;
  if (t == 0 || (Chained_gChainedComponent_1_sink__outputData != Chained_gChainedComponent_1_sink__outputData__prev).to_bool())
    dat_dump(f, Chained_gChainedComponent_1_sink__outputData, "N2");
  Chained_gChainedComponent_1_sink__outputData__prev = Chained_gChainedComponent_1_sink__outputData;
  if (t == 0 || (Chained_gChainedComponent_1__reset != Chained_gChainedComponent_1__reset__prev).to_bool())
    dat_dump(f, Chained_gChainedComponent_1__reset, "N4");
  Chained_gChainedComponent_1__reset__prev = Chained_gChainedComponent_1__reset;
  if (t == 0 || (Chained_gChainedComponent_1_sink__reset != Chained_gChainedComponent_1_sink__reset__prev).to_bool())
    dat_dump(f, Chained_gChainedComponent_1_sink__reset, "N5");
  Chained_gChainedComponent_1_sink__reset__prev = Chained_gChainedComponent_1_sink__reset;
  if (t == 0 || (Chained_gChainedComponent_1_source__io_pcOut_bits_request != Chained_gChainedComponent_1_source__io_pcOut_bits_request__prev).to_bool())
    dat_dump(f, Chained_gChainedComponent_1_source__io_pcOut_bits_request, "N6");
  Chained_gChainedComponent_1_source__io_pcOut_bits_request__prev = Chained_gChainedComponent_1_source__io_pcOut_bits_request;
  if (t == 0 || (Chained_gChainedComponent_1_sink__io_pcIn_bits_request != Chained_gChainedComponent_1_sink__io_pcIn_bits_request__prev).to_bool())
    dat_dump(f, Chained_gChainedComponent_1_sink__io_pcIn_bits_request, "N7");
  Chained_gChainedComponent_1_sink__io_pcIn_bits_request__prev = Chained_gChainedComponent_1_sink__io_pcIn_bits_request;
  if (t == 0 || (Chained_gChainedComponent_1_source__io_pcOut_bits_moduleId != Chained_gChainedComponent_1_source__io_pcOut_bits_moduleId__prev).to_bool())
    dat_dump(f, Chained_gChainedComponent_1_source__io_pcOut_bits_moduleId, "N8");
  Chained_gChainedComponent_1_source__io_pcOut_bits_moduleId__prev = Chained_gChainedComponent_1_source__io_pcOut_bits_moduleId;
  if (t == 0 || (Chained_gChainedComponent_1_sink__io_pcIn_bits_moduleId != Chained_gChainedComponent_1_sink__io_pcIn_bits_moduleId__prev).to_bool())
    dat_dump(f, Chained_gChainedComponent_1_sink__io_pcIn_bits_moduleId, "N9");
  Chained_gChainedComponent_1_sink__io_pcIn_bits_moduleId__prev = Chained_gChainedComponent_1_sink__io_pcIn_bits_moduleId;
  if (t == 0 || (Chained_gChainedComponent_1_source__io_pcOut_valid != Chained_gChainedComponent_1_source__io_pcOut_valid__prev).to_bool())
    dat_dump(f, Chained_gChainedComponent_1_source__io_pcOut_valid, "N10");
  Chained_gChainedComponent_1_source__io_pcOut_valid__prev = Chained_gChainedComponent_1_source__io_pcOut_valid;
  if (t == 0 || (Chained_gChainedComponent_1_sink__io_pcIn_valid != Chained_gChainedComponent_1_sink__io_pcIn_valid__prev).to_bool())
    dat_dump(f, Chained_gChainedComponent_1_sink__io_pcIn_valid, "N11");
  Chained_gChainedComponent_1_sink__io_pcIn_valid__prev = Chained_gChainedComponent_1_sink__io_pcIn_valid;
  if (t == 0 || (R0 != R0__prev).to_bool())
    dat_dump(f, R0, "N12");
  R0__prev = R0;
  if (t == 0 || (R1 != R1__prev).to_bool())
    dat_dump(f, R1, "N13");
  R1__prev = R1;
  if (t == 0 || (R2 != R2__prev).to_bool())
    dat_dump(f, R2, "N14");
  R2__prev = R2;
  if (t == 0 || (Chained_gChainedComponent_1_source__io_pcOut_bits_portId != Chained_gChainedComponent_1_source__io_pcOut_bits_portId__prev).to_bool())
    dat_dump(f, Chained_gChainedComponent_1_source__io_pcOut_bits_portId, "N15");
  Chained_gChainedComponent_1_source__io_pcOut_bits_portId__prev = Chained_gChainedComponent_1_source__io_pcOut_bits_portId;
  if (t == 0 || (Chained_gChainedComponent_1_sink__io_pcIn_bits_portId != Chained_gChainedComponent_1_sink__io_pcIn_bits_portId__prev).to_bool())
    dat_dump(f, Chained_gChainedComponent_1_sink__io_pcIn_bits_portId, "N16");
  Chained_gChainedComponent_1_sink__io_pcIn_bits_portId__prev = Chained_gChainedComponent_1_sink__io_pcIn_bits_portId;
  if (t == 0 || (R3 != R3__prev).to_bool())
    dat_dump(f, R3, "N17");
  R3__prev = R3;
  if (t == 0 || (Chained_gChainedComponent_1_source__io_pcOut_bits_pcType != Chained_gChainedComponent_1_source__io_pcOut_bits_pcType__prev).to_bool())
    dat_dump(f, Chained_gChainedComponent_1_source__io_pcOut_bits_pcType, "N18");
  Chained_gChainedComponent_1_source__io_pcOut_bits_pcType__prev = Chained_gChainedComponent_1_source__io_pcOut_bits_pcType;
  if (t == 0 || (Chained_gChainedComponent_1_sink__io_pcIn_bits_pcType != Chained_gChainedComponent_1_sink__io_pcIn_bits_pcType__prev).to_bool())
    dat_dump(f, Chained_gChainedComponent_1_sink__io_pcIn_bits_pcType, "N19");
  Chained_gChainedComponent_1_sink__io_pcIn_bits_pcType__prev = Chained_gChainedComponent_1_sink__io_pcIn_bits_pcType;
  if (t == 0 || (Chained__io_out_ready != Chained__io_out_ready__prev).to_bool())
    dat_dump(f, Chained__io_out_ready, "N20");
  Chained__io_out_ready__prev = Chained__io_out_ready;
  if (t == 0 || (Chained_gChainedComponent_1__io_out_ready != Chained_gChainedComponent_1__io_out_ready__prev).to_bool())
    dat_dump(f, Chained_gChainedComponent_1__io_out_ready, "N21");
  Chained_gChainedComponent_1__io_out_ready__prev = Chained_gChainedComponent_1__io_out_ready;
  if (t == 0 || (Chained_gChainedComponent_1_sink__io_out_ready != Chained_gChainedComponent_1_sink__io_out_ready__prev).to_bool())
    dat_dump(f, Chained_gChainedComponent_1_sink__io_out_ready, "N22");
  Chained_gChainedComponent_1_sink__io_out_ready__prev = Chained_gChainedComponent_1_sink__io_out_ready;
  if (t == 0 || (Chained_gChainedComponent_1_sink__io_in_ready != Chained_gChainedComponent_1_sink__io_in_ready__prev).to_bool())
    dat_dump(f, Chained_gChainedComponent_1_sink__io_in_ready, "N23");
  Chained_gChainedComponent_1_sink__io_in_ready__prev = Chained_gChainedComponent_1_sink__io_in_ready;
  if (t == 0 || (Chained_gChainedComponent_1_source__io_out_ready != Chained_gChainedComponent_1_source__io_out_ready__prev).to_bool())
    dat_dump(f, Chained_gChainedComponent_1_source__io_out_ready, "N24");
  Chained_gChainedComponent_1_source__io_out_ready__prev = Chained_gChainedComponent_1_source__io_out_ready;
  if (t == 0 || (Chained_gChainedComponent_1_source__io_out_valid != Chained_gChainedComponent_1_source__io_out_valid__prev).to_bool())
    dat_dump(f, Chained_gChainedComponent_1_source__io_out_valid, "N25");
  Chained_gChainedComponent_1_source__io_out_valid__prev = Chained_gChainedComponent_1_source__io_out_valid;
  if (t == 0 || (Chained_gChainedComponent_1_sink__io_in_valid != Chained_gChainedComponent_1_sink__io_in_valid__prev).to_bool())
    dat_dump(f, Chained_gChainedComponent_1_sink__io_in_valid, "N26");
  Chained_gChainedComponent_1_sink__io_in_valid__prev = Chained_gChainedComponent_1_sink__io_in_valid;
  if (t == 0 || (R4 != R4__prev).to_bool())
    dat_dump(f, R4, "N27");
  R4__prev = R4;
  if (t == 0 || (Chained_gChainedComponent_1_sink__outputValid != Chained_gChainedComponent_1_sink__outputValid__prev).to_bool())
    dat_dump(f, Chained_gChainedComponent_1_sink__outputValid, "N28");
  Chained_gChainedComponent_1_sink__outputValid__prev = Chained_gChainedComponent_1_sink__outputValid;
  if (t == 0 || (Chained_gChainedComponent_1_sink__io_out_valid != Chained_gChainedComponent_1_sink__io_out_valid__prev).to_bool())
    dat_dump(f, Chained_gChainedComponent_1_sink__io_out_valid, "N29");
  Chained_gChainedComponent_1_sink__io_out_valid__prev = Chained_gChainedComponent_1_sink__io_out_valid;
  if (t == 0 || (R5 != R5__prev).to_bool())
    dat_dump(f, R5, "N30");
  R5__prev = R5;
  if (t == 0 || (Chained_gChainedComponent_1_source__io_pcOut_bits_pcValue != Chained_gChainedComponent_1_source__io_pcOut_bits_pcValue__prev).to_bool())
    dat_dump(f, Chained_gChainedComponent_1_source__io_pcOut_bits_pcValue, "N31");
  Chained_gChainedComponent_1_source__io_pcOut_bits_pcValue__prev = Chained_gChainedComponent_1_source__io_pcOut_bits_pcValue;
  if (t == 0 || (Chained_gChainedComponent_1_sink__io_pcIn_bits_pcValue != Chained_gChainedComponent_1_sink__io_pcIn_bits_pcValue__prev).to_bool())
    dat_dump(f, Chained_gChainedComponent_1_sink__io_pcIn_bits_pcValue, "N32");
  Chained_gChainedComponent_1_sink__io_pcIn_bits_pcValue__prev = Chained_gChainedComponent_1_sink__io_pcIn_bits_pcValue;
  if (t == 0 || (R6 != R6__prev).to_bool())
    dat_dump(f, R6, "N33");
  R6__prev = R6;
  if (t == 0 || (R7 != R7__prev).to_bool())
    dat_dump(f, R7, "N34");
  R7__prev = R7;
  if (t == 0 || (Chained__io_in_bits != Chained__io_in_bits__prev).to_bool())
    dat_dump(f, Chained__io_in_bits, "N35");
  Chained__io_in_bits__prev = Chained__io_in_bits;
  if (t == 0 || (Chained_gChainedComponent_1__io_in_bits != Chained_gChainedComponent_1__io_in_bits__prev).to_bool())
    dat_dump(f, Chained_gChainedComponent_1__io_in_bits, "N36");
  Chained_gChainedComponent_1__io_in_bits__prev = Chained_gChainedComponent_1__io_in_bits;
  if (t == 0 || (Chained_gChainedComponent_1_source__io_in_bits != Chained_gChainedComponent_1_source__io_in_bits__prev).to_bool())
    dat_dump(f, Chained_gChainedComponent_1_source__io_in_bits, "N37");
  Chained_gChainedComponent_1_source__io_in_bits__prev = Chained_gChainedComponent_1_source__io_in_bits;
  if (t == 0 || (Chained_gChainedComponent_1_source__outputData != Chained_gChainedComponent_1_source__outputData__prev).to_bool())
    dat_dump(f, Chained_gChainedComponent_1_source__outputData, "N38");
  Chained_gChainedComponent_1_source__outputData__prev = Chained_gChainedComponent_1_source__outputData;
  if (t == 0 || (Chained_gChainedComponent_1_source__reset != Chained_gChainedComponent_1_source__reset__prev).to_bool())
    dat_dump(f, Chained_gChainedComponent_1_source__reset, "N39");
  Chained_gChainedComponent_1_source__reset__prev = Chained_gChainedComponent_1_source__reset;
  if (t == 0 || (Chained__io_pcIn_bits_pcType != Chained__io_pcIn_bits_pcType__prev).to_bool())
    dat_dump(f, Chained__io_pcIn_bits_pcType, "N40");
  Chained__io_pcIn_bits_pcType__prev = Chained__io_pcIn_bits_pcType;
  if (t == 0 || (Chained_gChainedComponent_1__io_pcIn_bits_pcType != Chained_gChainedComponent_1__io_pcIn_bits_pcType__prev).to_bool())
    dat_dump(f, Chained_gChainedComponent_1__io_pcIn_bits_pcType, "N41");
  Chained_gChainedComponent_1__io_pcIn_bits_pcType__prev = Chained_gChainedComponent_1__io_pcIn_bits_pcType;
  if (t == 0 || (Chained_gChainedComponent_1_source__io_pcIn_bits_pcType != Chained_gChainedComponent_1_source__io_pcIn_bits_pcType__prev).to_bool())
    dat_dump(f, Chained_gChainedComponent_1_source__io_pcIn_bits_pcType, "N42");
  Chained_gChainedComponent_1_source__io_pcIn_bits_pcType__prev = Chained_gChainedComponent_1_source__io_pcIn_bits_pcType;
  if (t == 0 || (Chained__io_pcIn_bits_request != Chained__io_pcIn_bits_request__prev).to_bool())
    dat_dump(f, Chained__io_pcIn_bits_request, "N43");
  Chained__io_pcIn_bits_request__prev = Chained__io_pcIn_bits_request;
  if (t == 0 || (Chained_gChainedComponent_1__io_pcIn_bits_request != Chained_gChainedComponent_1__io_pcIn_bits_request__prev).to_bool())
    dat_dump(f, Chained_gChainedComponent_1__io_pcIn_bits_request, "N44");
  Chained_gChainedComponent_1__io_pcIn_bits_request__prev = Chained_gChainedComponent_1__io_pcIn_bits_request;
  if (t == 0 || (Chained_gChainedComponent_1_source__io_pcIn_bits_request != Chained_gChainedComponent_1_source__io_pcIn_bits_request__prev).to_bool())
    dat_dump(f, Chained_gChainedComponent_1_source__io_pcIn_bits_request, "N45");
  Chained_gChainedComponent_1_source__io_pcIn_bits_request__prev = Chained_gChainedComponent_1_source__io_pcIn_bits_request;
  if (t == 0 || (Chained__io_pcIn_valid != Chained__io_pcIn_valid__prev).to_bool())
    dat_dump(f, Chained__io_pcIn_valid, "N46");
  Chained__io_pcIn_valid__prev = Chained__io_pcIn_valid;
  if (t == 0 || (Chained_gChainedComponent_1__io_pcIn_valid != Chained_gChainedComponent_1__io_pcIn_valid__prev).to_bool())
    dat_dump(f, Chained_gChainedComponent_1__io_pcIn_valid, "N47");
  Chained_gChainedComponent_1__io_pcIn_valid__prev = Chained_gChainedComponent_1__io_pcIn_valid;
  if (t == 0 || (Chained_gChainedComponent_1_source__io_pcIn_valid != Chained_gChainedComponent_1_source__io_pcIn_valid__prev).to_bool())
    dat_dump(f, Chained_gChainedComponent_1_source__io_pcIn_valid, "N48");
  Chained_gChainedComponent_1_source__io_pcIn_valid__prev = Chained_gChainedComponent_1_source__io_pcIn_valid;
  if (t == 0 || (Chained_gChainedComponent_1_source__io_in_ready != Chained_gChainedComponent_1_source__io_in_ready__prev).to_bool())
    dat_dump(f, Chained_gChainedComponent_1_source__io_in_ready, "N49");
  Chained_gChainedComponent_1_source__io_in_ready__prev = Chained_gChainedComponent_1_source__io_in_ready;
  if (t == 0 || (Chained__io_in_valid != Chained__io_in_valid__prev).to_bool())
    dat_dump(f, Chained__io_in_valid, "N50");
  Chained__io_in_valid__prev = Chained__io_in_valid;
  if (t == 0 || (Chained_gChainedComponent_1__io_in_valid != Chained_gChainedComponent_1__io_in_valid__prev).to_bool())
    dat_dump(f, Chained_gChainedComponent_1__io_in_valid, "N51");
  Chained_gChainedComponent_1__io_in_valid__prev = Chained_gChainedComponent_1__io_in_valid;
  if (t == 0 || (Chained_gChainedComponent_1_source__io_in_valid != Chained_gChainedComponent_1_source__io_in_valid__prev).to_bool())
    dat_dump(f, Chained_gChainedComponent_1_source__io_in_valid, "N52");
  Chained_gChainedComponent_1_source__io_in_valid__prev = Chained_gChainedComponent_1_source__io_in_valid;
  if (t == 0 || (R8 != R8__prev).to_bool())
    dat_dump(f, R8, "N53");
  R8__prev = R8;
  if (t == 0 || (R9 != R9__prev).to_bool())
    dat_dump(f, R9, "N54");
  R9__prev = R9;
  if (t == 0 || (Chained__io_pcIn_bits_portId != Chained__io_pcIn_bits_portId__prev).to_bool())
    dat_dump(f, Chained__io_pcIn_bits_portId, "N55");
  Chained__io_pcIn_bits_portId__prev = Chained__io_pcIn_bits_portId;
  if (t == 0 || (Chained_gChainedComponent_1__io_pcIn_bits_portId != Chained_gChainedComponent_1__io_pcIn_bits_portId__prev).to_bool())
    dat_dump(f, Chained_gChainedComponent_1__io_pcIn_bits_portId, "N56");
  Chained_gChainedComponent_1__io_pcIn_bits_portId__prev = Chained_gChainedComponent_1__io_pcIn_bits_portId;
  if (t == 0 || (Chained_gChainedComponent_1_source__io_pcIn_bits_portId != Chained_gChainedComponent_1_source__io_pcIn_bits_portId__prev).to_bool())
    dat_dump(f, Chained_gChainedComponent_1_source__io_pcIn_bits_portId, "N57");
  Chained_gChainedComponent_1_source__io_pcIn_bits_portId__prev = Chained_gChainedComponent_1_source__io_pcIn_bits_portId;
  if (t == 0 || (Chained__io_pcIn_bits_moduleId != Chained__io_pcIn_bits_moduleId__prev).to_bool())
    dat_dump(f, Chained__io_pcIn_bits_moduleId, "N58");
  Chained__io_pcIn_bits_moduleId__prev = Chained__io_pcIn_bits_moduleId;
  if (t == 0 || (Chained_gChainedComponent_1__io_pcIn_bits_moduleId != Chained_gChainedComponent_1__io_pcIn_bits_moduleId__prev).to_bool())
    dat_dump(f, Chained_gChainedComponent_1__io_pcIn_bits_moduleId, "N59");
  Chained_gChainedComponent_1__io_pcIn_bits_moduleId__prev = Chained_gChainedComponent_1__io_pcIn_bits_moduleId;
  if (t == 0 || (Chained_gChainedComponent_1_source__io_pcIn_bits_moduleId != Chained_gChainedComponent_1_source__io_pcIn_bits_moduleId__prev).to_bool())
    dat_dump(f, Chained_gChainedComponent_1_source__io_pcIn_bits_moduleId, "N60");
  Chained_gChainedComponent_1_source__io_pcIn_bits_moduleId__prev = Chained_gChainedComponent_1_source__io_pcIn_bits_moduleId;
  if (t == 0 || (Chained__io_pcIn_bits_pcValue != Chained__io_pcIn_bits_pcValue__prev).to_bool())
    dat_dump(f, Chained__io_pcIn_bits_pcValue, "N61");
  Chained__io_pcIn_bits_pcValue__prev = Chained__io_pcIn_bits_pcValue;
  if (t == 0 || (Chained_gChainedComponent_1__io_pcIn_bits_pcValue != Chained_gChainedComponent_1__io_pcIn_bits_pcValue__prev).to_bool())
    dat_dump(f, Chained_gChainedComponent_1__io_pcIn_bits_pcValue, "N62");
  Chained_gChainedComponent_1__io_pcIn_bits_pcValue__prev = Chained_gChainedComponent_1__io_pcIn_bits_pcValue;
  if (t == 0 || (Chained_gChainedComponent_1_source__io_pcIn_bits_pcValue != Chained_gChainedComponent_1_source__io_pcIn_bits_pcValue__prev).to_bool())
    dat_dump(f, Chained_gChainedComponent_1_source__io_pcIn_bits_pcValue, "N63");
  Chained_gChainedComponent_1_source__io_pcIn_bits_pcValue__prev = Chained_gChainedComponent_1_source__io_pcIn_bits_pcValue;
  if (t == 0 || (R10 != R10__prev).to_bool())
    dat_dump(f, R10, "N64");
  R10__prev = R10;
  if (t == 0 || (R11 != R11__prev).to_bool())
    dat_dump(f, R11, "N65");
  R11__prev = R11;
  if (t == 0 || (Chained_gChainedComponent_1_source__outputValid != Chained_gChainedComponent_1_source__outputValid__prev).to_bool())
    dat_dump(f, Chained_gChainedComponent_1_source__outputValid, "N66");
  Chained_gChainedComponent_1_source__outputValid__prev = Chained_gChainedComponent_1_source__outputValid;
  if (t == 0 || (R12 != R12__prev).to_bool())
    dat_dump(f, R12, "N67");
  R12__prev = R12;
  if (t == 0 || (R13 != R13__prev).to_bool())
    dat_dump(f, R13, "N68");
  R13__prev = R13;
  if (t == 0 || (R14 != R14__prev).to_bool())
    dat_dump(f, R14, "N69");
  R14__prev = R14;
  if (t == 0 || (R15 != R15__prev).to_bool())
    dat_dump(f, R15, "N70");
  R15__prev = R15;
  if (t == 0 || (Chained_gChainedComponent_1__io_in_ready != Chained_gChainedComponent_1__io_in_ready__prev).to_bool())
    dat_dump(f, Chained_gChainedComponent_1__io_in_ready, "N71");
  Chained_gChainedComponent_1__io_in_ready__prev = Chained_gChainedComponent_1__io_in_ready;
  if (t == 0 || (Chained__io_in_ready != Chained__io_in_ready__prev).to_bool())
    dat_dump(f, Chained__io_in_ready, "N72");
  Chained__io_in_ready__prev = Chained__io_in_ready;
  if (t == 0 || (Chained_gChainedComponent_1__io_out_valid != Chained_gChainedComponent_1__io_out_valid__prev).to_bool())
    dat_dump(f, Chained_gChainedComponent_1__io_out_valid, "N73");
  Chained_gChainedComponent_1__io_out_valid__prev = Chained_gChainedComponent_1__io_out_valid;
  if (t == 0 || (Chained__io_out_valid != Chained__io_out_valid__prev).to_bool())
    dat_dump(f, Chained__io_out_valid, "N74");
  Chained__io_out_valid__prev = Chained__io_out_valid;
  if (t == 0 || (Chained_gChainedComponent_1_sink__io_out_bits != Chained_gChainedComponent_1_sink__io_out_bits__prev).to_bool())
    dat_dump(f, Chained_gChainedComponent_1_sink__io_out_bits, "N75");
  Chained_gChainedComponent_1_sink__io_out_bits__prev = Chained_gChainedComponent_1_sink__io_out_bits;
  if (t == 0 || (Chained_gChainedComponent_1__io_out_bits != Chained_gChainedComponent_1__io_out_bits__prev).to_bool())
    dat_dump(f, Chained_gChainedComponent_1__io_out_bits, "N76");
  Chained_gChainedComponent_1__io_out_bits__prev = Chained_gChainedComponent_1__io_out_bits;
  if (t == 0 || (Chained__io_out_bits != Chained__io_out_bits__prev).to_bool())
    dat_dump(f, Chained__io_out_bits, "N77");
  Chained__io_out_bits__prev = Chained__io_out_bits;
  if (t == 0 || (Chained_gChainedComponent_1_sink__io_pcOut_valid != Chained_gChainedComponent_1_sink__io_pcOut_valid__prev).to_bool())
    dat_dump(f, Chained_gChainedComponent_1_sink__io_pcOut_valid, "N78");
  Chained_gChainedComponent_1_sink__io_pcOut_valid__prev = Chained_gChainedComponent_1_sink__io_pcOut_valid;
  if (t == 0 || (Chained_gChainedComponent_1__io_pcOut_valid != Chained_gChainedComponent_1__io_pcOut_valid__prev).to_bool())
    dat_dump(f, Chained_gChainedComponent_1__io_pcOut_valid, "N79");
  Chained_gChainedComponent_1__io_pcOut_valid__prev = Chained_gChainedComponent_1__io_pcOut_valid;
  if (t == 0 || (Chained__io_pcOut_valid != Chained__io_pcOut_valid__prev).to_bool())
    dat_dump(f, Chained__io_pcOut_valid, "N80");
  Chained__io_pcOut_valid__prev = Chained__io_pcOut_valid;
  if (t == 0 || (Chained_gChainedComponent_1_sink__io_pcOut_bits_moduleId != Chained_gChainedComponent_1_sink__io_pcOut_bits_moduleId__prev).to_bool())
    dat_dump(f, Chained_gChainedComponent_1_sink__io_pcOut_bits_moduleId, "N81");
  Chained_gChainedComponent_1_sink__io_pcOut_bits_moduleId__prev = Chained_gChainedComponent_1_sink__io_pcOut_bits_moduleId;
  if (t == 0 || (Chained_gChainedComponent_1__io_pcOut_bits_moduleId != Chained_gChainedComponent_1__io_pcOut_bits_moduleId__prev).to_bool())
    dat_dump(f, Chained_gChainedComponent_1__io_pcOut_bits_moduleId, "N82");
  Chained_gChainedComponent_1__io_pcOut_bits_moduleId__prev = Chained_gChainedComponent_1__io_pcOut_bits_moduleId;
  if (t == 0 || (Chained__io_pcOut_bits_moduleId != Chained__io_pcOut_bits_moduleId__prev).to_bool())
    dat_dump(f, Chained__io_pcOut_bits_moduleId, "N83");
  Chained__io_pcOut_bits_moduleId__prev = Chained__io_pcOut_bits_moduleId;
  if (t == 0 || (Chained_gChainedComponent_1_sink__io_pcOut_bits_request != Chained_gChainedComponent_1_sink__io_pcOut_bits_request__prev).to_bool())
    dat_dump(f, Chained_gChainedComponent_1_sink__io_pcOut_bits_request, "N84");
  Chained_gChainedComponent_1_sink__io_pcOut_bits_request__prev = Chained_gChainedComponent_1_sink__io_pcOut_bits_request;
  if (t == 0 || (Chained_gChainedComponent_1__io_pcOut_bits_request != Chained_gChainedComponent_1__io_pcOut_bits_request__prev).to_bool())
    dat_dump(f, Chained_gChainedComponent_1__io_pcOut_bits_request, "N85");
  Chained_gChainedComponent_1__io_pcOut_bits_request__prev = Chained_gChainedComponent_1__io_pcOut_bits_request;
  if (t == 0 || (Chained__io_pcOut_bits_request != Chained__io_pcOut_bits_request__prev).to_bool())
    dat_dump(f, Chained__io_pcOut_bits_request, "N86");
  Chained__io_pcOut_bits_request__prev = Chained__io_pcOut_bits_request;
  if (t == 0 || (Chained_gChainedComponent_1_sink__io_pcOut_bits_portId != Chained_gChainedComponent_1_sink__io_pcOut_bits_portId__prev).to_bool())
    dat_dump(f, Chained_gChainedComponent_1_sink__io_pcOut_bits_portId, "N87");
  Chained_gChainedComponent_1_sink__io_pcOut_bits_portId__prev = Chained_gChainedComponent_1_sink__io_pcOut_bits_portId;
  if (t == 0 || (Chained_gChainedComponent_1__io_pcOut_bits_portId != Chained_gChainedComponent_1__io_pcOut_bits_portId__prev).to_bool())
    dat_dump(f, Chained_gChainedComponent_1__io_pcOut_bits_portId, "N88");
  Chained_gChainedComponent_1__io_pcOut_bits_portId__prev = Chained_gChainedComponent_1__io_pcOut_bits_portId;
  if (t == 0 || (Chained__io_pcOut_bits_portId != Chained__io_pcOut_bits_portId__prev).to_bool())
    dat_dump(f, Chained__io_pcOut_bits_portId, "N89");
  Chained__io_pcOut_bits_portId__prev = Chained__io_pcOut_bits_portId;
  if (t == 0 || (Chained_gChainedComponent_1_sink__io_pcOut_bits_pcValue != Chained_gChainedComponent_1_sink__io_pcOut_bits_pcValue__prev).to_bool())
    dat_dump(f, Chained_gChainedComponent_1_sink__io_pcOut_bits_pcValue, "N90");
  Chained_gChainedComponent_1_sink__io_pcOut_bits_pcValue__prev = Chained_gChainedComponent_1_sink__io_pcOut_bits_pcValue;
  if (t == 0 || (Chained_gChainedComponent_1__io_pcOut_bits_pcValue != Chained_gChainedComponent_1__io_pcOut_bits_pcValue__prev).to_bool())
    dat_dump(f, Chained_gChainedComponent_1__io_pcOut_bits_pcValue, "N91");
  Chained_gChainedComponent_1__io_pcOut_bits_pcValue__prev = Chained_gChainedComponent_1__io_pcOut_bits_pcValue;
  if (t == 0 || (Chained__io_pcOut_bits_pcValue != Chained__io_pcOut_bits_pcValue__prev).to_bool())
    dat_dump(f, Chained__io_pcOut_bits_pcValue, "N92");
  Chained__io_pcOut_bits_pcValue__prev = Chained__io_pcOut_bits_pcValue;
  if (t == 0 || (Chained_gChainedComponent_1_sink__io_pcOut_bits_pcType != Chained_gChainedComponent_1_sink__io_pcOut_bits_pcType__prev).to_bool())
    dat_dump(f, Chained_gChainedComponent_1_sink__io_pcOut_bits_pcType, "N93");
  Chained_gChainedComponent_1_sink__io_pcOut_bits_pcType__prev = Chained_gChainedComponent_1_sink__io_pcOut_bits_pcType;
  if (t == 0 || (Chained_gChainedComponent_1__io_pcOut_bits_pcType != Chained_gChainedComponent_1__io_pcOut_bits_pcType__prev).to_bool())
    dat_dump(f, Chained_gChainedComponent_1__io_pcOut_bits_pcType, "N94");
  Chained_gChainedComponent_1__io_pcOut_bits_pcType__prev = Chained_gChainedComponent_1__io_pcOut_bits_pcType;
  if (t == 0 || (Chained__io_pcOut_bits_pcType != Chained__io_pcOut_bits_pcType__prev).to_bool())
    dat_dump(f, Chained__io_pcOut_bits_pcType, "N95");
  Chained__io_pcOut_bits_pcType__prev = Chained__io_pcOut_bits_pcType;
}
