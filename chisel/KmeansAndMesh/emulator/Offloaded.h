#include "emulator.h"

class Offloaded_t : public mod_t {
 public:
  dat_t<32> Offloaded_f_offComp__outputData;
  dat_t<32> Offloaded_f_offComp__outputData_shadow;
  dat_t<32> Offloaded_f_offComp__outputData__prev;
  dat_t<1> Offloaded_f__reset;
  dat_t<1> Offloaded_f__reset__prev;
  dat_t<1> Offloaded_f_offComp__reset;
  dat_t<1> Offloaded_f_offComp__reset__prev;
  dat_t<1> Offloaded_f_mainComp_IncThroughOffloadgComponent_3__pcOutN_bits_request;
  dat_t<1> Offloaded_f_mainComp_IncThroughOffloadgComponent_3__pcOutN_bits_request__prev;
  dat_t<1> Offloaded_f_mainComp__io_pcOut_bits_request;
  dat_t<1> Offloaded_f_mainComp__io_pcOut_bits_request__prev;
  dat_t<1> Offloaded_f_offComp__io_pcIn_bits_request;
  dat_t<1> Offloaded_f_offComp__io_pcIn_bits_request__prev;
  dat_t<16> Offloaded_f_mainComp_IncThroughOffloadgComponent_3__pcOutN_bits_moduleId;
  dat_t<16> Offloaded_f_mainComp_IncThroughOffloadgComponent_3__pcOutN_bits_moduleId__prev;
  dat_t<16> Offloaded_f_mainComp__io_pcOut_bits_moduleId;
  dat_t<16> Offloaded_f_mainComp__io_pcOut_bits_moduleId__prev;
  dat_t<16> Offloaded_f_offComp__io_pcIn_bits_moduleId;
  dat_t<16> Offloaded_f_offComp__io_pcIn_bits_moduleId__prev;
  dat_t<1> Offloaded_f_mainComp_IncThroughOffloadgComponent_3__pcOutN_valid;
  dat_t<1> Offloaded_f_mainComp_IncThroughOffloadgComponent_3__pcOutN_valid__prev;
  dat_t<1> Offloaded_f_mainComp__io_pcOut_valid;
  dat_t<1> Offloaded_f_mainComp__io_pcOut_valid__prev;
  dat_t<1> Offloaded_f_offComp__io_pcIn_valid;
  dat_t<1> Offloaded_f_offComp__io_pcIn_valid__prev;
  dat_t<1> R0;
  dat_t<1> R0_shadow;
  dat_t<1> R0__prev;
  dat_t<16> R1;
  dat_t<16> R1_shadow;
  dat_t<16> R1__prev;
  dat_t<1> R2;
  dat_t<1> R2_shadow;
  dat_t<1> R2__prev;
  dat_t<8> Offloaded_f_mainComp_IncThroughOffloadgComponent_3__pcOutN_bits_portId;
  dat_t<8> Offloaded_f_mainComp_IncThroughOffloadgComponent_3__pcOutN_bits_portId__prev;
  dat_t<8> Offloaded_f_mainComp__io_pcOut_bits_portId;
  dat_t<8> Offloaded_f_mainComp__io_pcOut_bits_portId__prev;
  dat_t<8> Offloaded_f_offComp__io_pcIn_bits_portId;
  dat_t<8> Offloaded_f_offComp__io_pcIn_bits_portId__prev;
  dat_t<8> R3;
  dat_t<8> R3_shadow;
  dat_t<8> R3__prev;
  dat_t<4> Offloaded_f_mainComp_IncThroughOffloadgComponent_3__pcOutN_bits_pcType;
  dat_t<4> Offloaded_f_mainComp_IncThroughOffloadgComponent_3__pcOutN_bits_pcType__prev;
  dat_t<4> Offloaded_f_mainComp__io_pcOut_bits_pcType;
  dat_t<4> Offloaded_f_mainComp__io_pcOut_bits_pcType__prev;
  dat_t<4> Offloaded_f_offComp__io_pcIn_bits_pcType;
  dat_t<4> Offloaded_f_offComp__io_pcIn_bits_pcType__prev;
  dat_t<1> Offloaded_f_mainComp_IncThroughOffloadgComponent_3__io_off_constOff_rep_ready;
  dat_t<1> Offloaded_f_mainComp_IncThroughOffloadgComponent_3__io_off_constOff_rep_ready__prev;
  dat_t<1> Offloaded_f_mainComp_RRDistributer_1__io_out_1_ready;
  dat_t<1> Offloaded_f_mainComp_RRDistributer_1__io_out_1_ready__prev;
  dat_t<1> Offloaded_f_mainComp_IncThroughOffloadgComponent_2__io_off_constOff_rep_ready;
  dat_t<1> Offloaded_f_mainComp_IncThroughOffloadgComponent_2__io_off_constOff_rep_ready__prev;
  dat_t<1> Offloaded_f_mainComp_RRDistributer_1__io_out_0_ready;
  dat_t<1> Offloaded_f_mainComp_RRDistributer_1__io_out_0_ready__prev;
  dat_t<1> Offloaded_f_mainComp_RRDistributer_1__io_in_ready;
  dat_t<1> Offloaded_f_mainComp_RRDistributer_1__io_in_ready__prev;
  dat_t<1> Offloaded_f_mainComp__mainOff_constOff_rep_ready;
  dat_t<1> Offloaded_f_mainComp__mainOff_constOff_rep_ready__prev;
  dat_t<1> Offloaded_f_offComp__io_out_ready;
  dat_t<1> Offloaded_f_offComp__io_out_ready__prev;
  dat_t<1> Offloaded_f_offComp__io_in_ready;
  dat_t<1> Offloaded_f_offComp__io_in_ready__prev;
  dat_t<1> Offloaded_f_mainComp_IncThroughOffloadgComponent_3__io_off_constOff_req_valid;
  dat_t<1> Offloaded_f_mainComp_IncThroughOffloadgComponent_3__io_off_constOff_req_valid__prev;
  dat_t<1> Offloaded_f_mainComp_RRArbiter_1__io_in_1_valid;
  dat_t<1> Offloaded_f_mainComp_RRArbiter_1__io_in_1_valid__prev;
  dat_t<1> Offloaded_f_mainComp_IncThroughOffloadgComponent_2__io_off_constOff_req_valid;
  dat_t<1> Offloaded_f_mainComp_IncThroughOffloadgComponent_2__io_off_constOff_req_valid__prev;
  dat_t<1> Offloaded_f_mainComp_RRArbiter_1__io_in_0_valid;
  dat_t<1> Offloaded_f_mainComp_RRArbiter_1__io_in_0_valid__prev;
  dat_t<1> Offloaded_f_mainComp_RRArbiter_1__io_out_valid;
  dat_t<1> Offloaded_f_mainComp_RRArbiter_1__io_out_valid__prev;
  dat_t<1> Offloaded_f_mainComp__mainOff_constOff_req_valid;
  dat_t<1> Offloaded_f_mainComp__mainOff_constOff_req_valid__prev;
  dat_t<1> Offloaded_f_offComp__io_in_valid;
  dat_t<1> Offloaded_f_offComp__io_in_valid__prev;
  dat_t<16> R4;
  dat_t<16> R4_shadow;
  dat_t<16> R4__prev;
  dat_t<1> Offloaded_f_offComp__outputValid;
  dat_t<1> Offloaded_f_offComp__outputValid_shadow;
  dat_t<1> Offloaded_f_offComp__outputValid__prev;
  dat_t<1> Offloaded_f_offComp__io_out_valid;
  dat_t<1> Offloaded_f_offComp__io_out_valid__prev;
  dat_t<16> R5;
  dat_t<16> R5_shadow;
  dat_t<16> R5__prev;
  dat_t<16> Offloaded_f_mainComp_IncThroughOffloadgComponent_3__pcOutN_bits_pcValue;
  dat_t<16> Offloaded_f_mainComp_IncThroughOffloadgComponent_3__pcOutN_bits_pcValue__prev;
  dat_t<16> Offloaded_f_mainComp__io_pcOut_bits_pcValue;
  dat_t<16> Offloaded_f_mainComp__io_pcOut_bits_pcValue__prev;
  dat_t<16> Offloaded_f_offComp__io_pcIn_bits_pcValue;
  dat_t<16> Offloaded_f_offComp__io_pcIn_bits_pcValue__prev;
  dat_t<16> R6;
  dat_t<16> R6_shadow;
  dat_t<16> R6__prev;
  dat_t<4> R7;
  dat_t<4> R7_shadow;
  dat_t<4> R7__prev;
  dat_t<1> Offloaded_f_mainComp__reset;
  dat_t<1> Offloaded_f_mainComp__reset__prev;
  dat_t<1> Offloaded_f_mainComp_RRDistributer_1__reset;
  dat_t<1> Offloaded_f_mainComp_RRDistributer_1__reset__prev;
  dat_t<1> Offloaded_f_mainComp__mainOff_constOff_rep_valid;
  dat_t<1> Offloaded_f_mainComp__mainOff_constOff_rep_valid__prev;
  dat_t<1> Offloaded_f_mainComp_RRDistributer_1__io_in_valid;
  dat_t<1> Offloaded_f_mainComp_RRDistributer_1__io_in_valid__prev;
  dat_t<1> Offloaded_f_mainComp_RRDistributer_1__last_grant;
  dat_t<1> Offloaded_f_mainComp_RRDistributer_1__last_grant_shadow;
  dat_t<1> Offloaded_f_mainComp_RRDistributer_1__last_grant__prev;
  dat_t<1> Offloaded_f_mainComp_RRArbiter_1__reset;
  dat_t<1> Offloaded_f_mainComp_RRArbiter_1__reset__prev;
  dat_t<1> Offloaded_f_mainComp__mainOff_constOff_req_ready;
  dat_t<1> Offloaded_f_mainComp__mainOff_constOff_req_ready__prev;
  dat_t<1> Offloaded_f_mainComp_RRArbiter_1__io_out_ready;
  dat_t<1> Offloaded_f_mainComp_RRArbiter_1__io_out_ready__prev;
  dat_t<1> Offloaded_f_mainComp_RRArbiter_1__last_grant;
  dat_t<1> Offloaded_f_mainComp_RRArbiter_1__last_grant_shadow;
  dat_t<1> Offloaded_f_mainComp_RRArbiter_1__last_grant__prev;
  dat_t<1> Offloaded_f_mainComp_outputArb__reset;
  dat_t<1> Offloaded_f_mainComp_outputArb__reset__prev;
  dat_t<1> Offloaded_f_mainComp_IncThroughOffloadgComponent_2__io_out_valid;
  dat_t<1> Offloaded_f_mainComp_IncThroughOffloadgComponent_2__io_out_valid__prev;
  dat_t<1> Offloaded_f_mainComp_outputArb__io_in_0_valid;
  dat_t<1> Offloaded_f_mainComp_outputArb__io_in_0_valid__prev;
  dat_t<1> Offloaded_f_mainComp_IncThroughOffloadgComponent_3__io_out_valid;
  dat_t<1> Offloaded_f_mainComp_IncThroughOffloadgComponent_3__io_out_valid__prev;
  dat_t<1> Offloaded_f_mainComp_outputArb__io_in_1_valid;
  dat_t<1> Offloaded_f_mainComp_outputArb__io_in_1_valid__prev;
  dat_t<1> Offloaded__io_out_ready;
  dat_t<1> Offloaded__io_out_ready__prev;
  dat_t<1> Offloaded_f__io_out_ready;
  dat_t<1> Offloaded_f__io_out_ready__prev;
  dat_t<1> Offloaded_f_mainComp__io_out_ready;
  dat_t<1> Offloaded_f_mainComp__io_out_ready__prev;
  dat_t<1> Offloaded_f_mainComp_outputArb__io_out_ready;
  dat_t<1> Offloaded_f_mainComp_outputArb__io_out_ready__prev;
  dat_t<1> Offloaded_f_mainComp_outputArb__io_out_valid;
  dat_t<1> Offloaded_f_mainComp_outputArb__io_out_valid__prev;
  dat_t<1> Offloaded_f_mainComp_outputArb__last_grant;
  dat_t<1> Offloaded_f_mainComp_outputArb__last_grant_shadow;
  dat_t<1> Offloaded_f_mainComp_outputArb__last_grant__prev;
  dat_t<1> Offloaded_f_mainComp_inputDist__reset;
  dat_t<1> Offloaded_f_mainComp_inputDist__reset__prev;
  dat_t<1> Offloaded_f_mainComp_IncThroughOffloadgComponent_2__io_in_ready;
  dat_t<1> Offloaded_f_mainComp_IncThroughOffloadgComponent_2__io_in_ready__prev;
  dat_t<1> Offloaded_f_mainComp_inputDist__io_out_0_ready;
  dat_t<1> Offloaded_f_mainComp_inputDist__io_out_0_ready__prev;
  dat_t<1> Offloaded_f_mainComp_IncThroughOffloadgComponent_3__io_in_ready;
  dat_t<1> Offloaded_f_mainComp_IncThroughOffloadgComponent_3__io_in_ready__prev;
  dat_t<1> Offloaded_f_mainComp_inputDist__io_out_1_ready;
  dat_t<1> Offloaded_f_mainComp_inputDist__io_out_1_ready__prev;
  dat_t<1> Offloaded_f_mainComp_inputDist__io_in_ready;
  dat_t<1> Offloaded_f_mainComp_inputDist__io_in_ready__prev;
  dat_t<1> Offloaded__io_in_valid;
  dat_t<1> Offloaded__io_in_valid__prev;
  dat_t<1> Offloaded_f__io_in_valid;
  dat_t<1> Offloaded_f__io_in_valid__prev;
  dat_t<1> Offloaded_f_mainComp__io_in_valid;
  dat_t<1> Offloaded_f_mainComp__io_in_valid__prev;
  dat_t<1> Offloaded_f_mainComp_inputDist__io_in_valid;
  dat_t<1> Offloaded_f_mainComp_inputDist__io_in_valid__prev;
  dat_t<1> Offloaded_f_mainComp_inputDist__last_grant;
  dat_t<1> Offloaded_f_mainComp_inputDist__last_grant_shadow;
  dat_t<1> Offloaded_f_mainComp_inputDist__last_grant__prev;
  dat_t<1> Offloaded_f_mainComp_IncThroughOffloadgComponent_3__reset;
  dat_t<1> Offloaded_f_mainComp_IncThroughOffloadgComponent_3__reset__prev;
  dat_t<32> Offloaded__io_in_bits;
  dat_t<32> Offloaded__io_in_bits__prev;
  dat_t<32> Offloaded_f__io_in_bits;
  dat_t<32> Offloaded_f__io_in_bits__prev;
  dat_t<32> Offloaded_f_mainComp__io_in_bits;
  dat_t<32> Offloaded_f_mainComp__io_in_bits__prev;
  dat_t<32> Offloaded_f_mainComp_inputDist__io_in_bits;
  dat_t<32> Offloaded_f_mainComp_inputDist__io_in_bits__prev;
  dat_t<32> Offloaded_f_mainComp_inputDist__io_out_1_bits;
  dat_t<32> Offloaded_f_mainComp_inputDist__io_out_1_bits__prev;
  dat_t<32> Offloaded_f_mainComp_IncThroughOffloadgComponent_3__io_in_bits;
  dat_t<32> Offloaded_f_mainComp_IncThroughOffloadgComponent_3__io_in_bits__prev;
  dat_t<1> Offloaded_f_mainComp_inputDist__io_out_1_valid;
  dat_t<1> Offloaded_f_mainComp_inputDist__io_out_1_valid__prev;
  dat_t<1> Offloaded_f_mainComp_IncThroughOffloadgComponent_3__io_in_valid;
  dat_t<1> Offloaded_f_mainComp_IncThroughOffloadgComponent_3__io_in_valid__prev;
  dat_t<32> Offloaded_f_mainComp_IncThroughOffloadgComponent_3__inputReg;
  dat_t<32> Offloaded_f_mainComp_IncThroughOffloadgComponent_3__inputReg_shadow;
  dat_t<32> Offloaded_f_mainComp_IncThroughOffloadgComponent_3__inputReg__prev;
  dat_t<32> Offloaded_f_offComp__io_out_bits;
  dat_t<32> Offloaded_f_offComp__io_out_bits__prev;
  dat_t<32> Offloaded_f_mainComp__mainOff_constOff_rep_bits;
  dat_t<32> Offloaded_f_mainComp__mainOff_constOff_rep_bits__prev;
  dat_t<32> Offloaded_f_mainComp_RRDistributer_1__io_in_bits;
  dat_t<32> Offloaded_f_mainComp_RRDistributer_1__io_in_bits__prev;
  dat_t<32> Offloaded_f_mainComp_RRDistributer_1__io_out_1_bits;
  dat_t<32> Offloaded_f_mainComp_RRDistributer_1__io_out_1_bits__prev;
  dat_t<32> Offloaded_f_mainComp_IncThroughOffloadgComponent_3__io_off_constOff_rep_bits;
  dat_t<32> Offloaded_f_mainComp_IncThroughOffloadgComponent_3__io_off_constOff_rep_bits__prev;
  dat_t<1> Offloaded_f_mainComp_RRDistributer_1__io_out_1_valid;
  dat_t<1> Offloaded_f_mainComp_RRDistributer_1__io_out_1_valid__prev;
  dat_t<1> Offloaded_f_mainComp_IncThroughOffloadgComponent_3__io_off_constOff_rep_valid;
  dat_t<1> Offloaded_f_mainComp_IncThroughOffloadgComponent_3__io_off_constOff_rep_valid__prev;
  dat_t<1> Offloaded_f_mainComp_RRArbiter_1__io_in_1_ready;
  dat_t<1> Offloaded_f_mainComp_RRArbiter_1__io_in_1_ready__prev;
  dat_t<1> Offloaded_f_mainComp_IncThroughOffloadgComponent_3__io_off_constOff_req_ready;
  dat_t<1> Offloaded_f_mainComp_IncThroughOffloadgComponent_3__io_off_constOff_req_ready__prev;
  dat_t<32> Offloaded_f_mainComp_IncThroughOffloadgComponent_3__offReg;
  dat_t<32> Offloaded_f_mainComp_IncThroughOffloadgComponent_3__offReg_shadow;
  dat_t<32> Offloaded_f_mainComp_IncThroughOffloadgComponent_3__offReg__prev;
  dat_t<4> Offloaded_f_mainComp_IncThroughOffloadgComponent_2__io_pcOut_bits_pcType;
  dat_t<4> Offloaded_f_mainComp_IncThroughOffloadgComponent_2__io_pcOut_bits_pcType__prev;
  dat_t<4> Offloaded_f_mainComp_IncThroughOffloadgComponent_3__io_pcIn_bits_pcType;
  dat_t<4> Offloaded_f_mainComp_IncThroughOffloadgComponent_3__io_pcIn_bits_pcType__prev;
  dat_t<1> Offloaded_f_mainComp_IncThroughOffloadgComponent_2__io_pcOut_bits_request;
  dat_t<1> Offloaded_f_mainComp_IncThroughOffloadgComponent_2__io_pcOut_bits_request__prev;
  dat_t<1> Offloaded_f_mainComp_IncThroughOffloadgComponent_3__io_pcIn_bits_request;
  dat_t<1> Offloaded_f_mainComp_IncThroughOffloadgComponent_3__io_pcIn_bits_request__prev;
  dat_t<1> Offloaded_f_mainComp_IncThroughOffloadgComponent_2__io_pcOut_valid;
  dat_t<1> Offloaded_f_mainComp_IncThroughOffloadgComponent_2__io_pcOut_valid__prev;
  dat_t<1> Offloaded_f_mainComp_IncThroughOffloadgComponent_3__io_pcIn_valid;
  dat_t<1> Offloaded_f_mainComp_IncThroughOffloadgComponent_3__io_pcIn_valid__prev;
  dat_t<16> R8;
  dat_t<16> R8_shadow;
  dat_t<16> R8__prev;
  dat_t<1> Offloaded_f_mainComp_outputArb__io_in_1_ready;
  dat_t<1> Offloaded_f_mainComp_outputArb__io_in_1_ready__prev;
  dat_t<1> Offloaded_f_mainComp_IncThroughOffloadgComponent_3__io_out_ready;
  dat_t<1> Offloaded_f_mainComp_IncThroughOffloadgComponent_3__io_out_ready__prev;
  dat_t<16> R9;
  dat_t<16> R9_shadow;
  dat_t<16> R9__prev;
  dat_t<8> Offloaded_f_mainComp_IncThroughOffloadgComponent_2__io_pcOut_bits_portId;
  dat_t<8> Offloaded_f_mainComp_IncThroughOffloadgComponent_2__io_pcOut_bits_portId__prev;
  dat_t<8> Offloaded_f_mainComp_IncThroughOffloadgComponent_3__io_pcIn_bits_portId;
  dat_t<8> Offloaded_f_mainComp_IncThroughOffloadgComponent_3__io_pcIn_bits_portId__prev;
  dat_t<16> Offloaded_f_mainComp_IncThroughOffloadgComponent_2__io_pcOut_bits_moduleId;
  dat_t<16> Offloaded_f_mainComp_IncThroughOffloadgComponent_2__io_pcOut_bits_moduleId__prev;
  dat_t<16> Offloaded_f_mainComp_IncThroughOffloadgComponent_3__io_pcIn_bits_moduleId;
  dat_t<16> Offloaded_f_mainComp_IncThroughOffloadgComponent_3__io_pcIn_bits_moduleId__prev;
  dat_t<16> Offloaded_f_mainComp_IncThroughOffloadgComponent_2__io_pcOut_bits_pcValue;
  dat_t<16> Offloaded_f_mainComp_IncThroughOffloadgComponent_2__io_pcOut_bits_pcValue__prev;
  dat_t<16> Offloaded_f_mainComp_IncThroughOffloadgComponent_3__io_pcIn_bits_pcValue;
  dat_t<16> Offloaded_f_mainComp_IncThroughOffloadgComponent_3__io_pcIn_bits_pcValue__prev;
  dat_t<16> R10;
  dat_t<16> R10_shadow;
  dat_t<16> R10__prev;
  dat_t<8> R11;
  dat_t<8> R11_shadow;
  dat_t<8> R11__prev;
  dat_t<32> Offloaded_f_mainComp_IncThroughOffloadgComponent_3__counter;
  dat_t<32> Offloaded_f_mainComp_IncThroughOffloadgComponent_3__counter_shadow;
  dat_t<32> Offloaded_f_mainComp_IncThroughOffloadgComponent_3__counter__prev;
  dat_t<3> Offloaded_f_mainComp_IncThroughOffloadgComponent_3__state;
  dat_t<3> Offloaded_f_mainComp_IncThroughOffloadgComponent_3__state_shadow;
  dat_t<3> Offloaded_f_mainComp_IncThroughOffloadgComponent_3__state__prev;
  dat_t<1> R12;
  dat_t<1> R12_shadow;
  dat_t<1> R12__prev;
  dat_t<16> R13;
  dat_t<16> R13_shadow;
  dat_t<16> R13__prev;
  dat_t<1> R14;
  dat_t<1> R14_shadow;
  dat_t<1> R14__prev;
  dat_t<4> R15;
  dat_t<4> R15_shadow;
  dat_t<4> R15__prev;
  dat_t<1> Offloaded_f_mainComp_IncThroughOffloadgComponent_2__reset;
  dat_t<1> Offloaded_f_mainComp_IncThroughOffloadgComponent_2__reset__prev;
  dat_t<32> Offloaded_f_mainComp_inputDist__io_out_0_bits;
  dat_t<32> Offloaded_f_mainComp_inputDist__io_out_0_bits__prev;
  dat_t<32> Offloaded_f_mainComp_IncThroughOffloadgComponent_2__io_in_bits;
  dat_t<32> Offloaded_f_mainComp_IncThroughOffloadgComponent_2__io_in_bits__prev;
  dat_t<1> Offloaded_f_mainComp_inputDist__io_out_0_valid;
  dat_t<1> Offloaded_f_mainComp_inputDist__io_out_0_valid__prev;
  dat_t<1> Offloaded_f_mainComp_IncThroughOffloadgComponent_2__io_in_valid;
  dat_t<1> Offloaded_f_mainComp_IncThroughOffloadgComponent_2__io_in_valid__prev;
  dat_t<32> Offloaded_f_mainComp_IncThroughOffloadgComponent_2__inputReg;
  dat_t<32> Offloaded_f_mainComp_IncThroughOffloadgComponent_2__inputReg_shadow;
  dat_t<32> Offloaded_f_mainComp_IncThroughOffloadgComponent_2__inputReg__prev;
  dat_t<32> Offloaded_f_mainComp_RRDistributer_1__io_out_0_bits;
  dat_t<32> Offloaded_f_mainComp_RRDistributer_1__io_out_0_bits__prev;
  dat_t<32> Offloaded_f_mainComp_IncThroughOffloadgComponent_2__io_off_constOff_rep_bits;
  dat_t<32> Offloaded_f_mainComp_IncThroughOffloadgComponent_2__io_off_constOff_rep_bits__prev;
  dat_t<1> Offloaded_f_mainComp_RRDistributer_1__io_out_0_valid;
  dat_t<1> Offloaded_f_mainComp_RRDistributer_1__io_out_0_valid__prev;
  dat_t<1> Offloaded_f_mainComp_IncThroughOffloadgComponent_2__io_off_constOff_rep_valid;
  dat_t<1> Offloaded_f_mainComp_IncThroughOffloadgComponent_2__io_off_constOff_rep_valid__prev;
  dat_t<1> Offloaded_f_mainComp_RRArbiter_1__io_in_0_ready;
  dat_t<1> Offloaded_f_mainComp_RRArbiter_1__io_in_0_ready__prev;
  dat_t<1> Offloaded_f_mainComp_IncThroughOffloadgComponent_2__io_off_constOff_req_ready;
  dat_t<1> Offloaded_f_mainComp_IncThroughOffloadgComponent_2__io_off_constOff_req_ready__prev;
  dat_t<32> Offloaded_f_mainComp_IncThroughOffloadgComponent_2__offReg;
  dat_t<32> Offloaded_f_mainComp_IncThroughOffloadgComponent_2__offReg_shadow;
  dat_t<32> Offloaded_f_mainComp_IncThroughOffloadgComponent_2__offReg__prev;
  dat_t<4> Offloaded__io_pcIn_bits_pcType;
  dat_t<4> Offloaded__io_pcIn_bits_pcType__prev;
  dat_t<4> Offloaded_f__io_pcIn_bits_pcType;
  dat_t<4> Offloaded_f__io_pcIn_bits_pcType__prev;
  dat_t<4> Offloaded_f_mainComp__io_pcIn_bits_pcType;
  dat_t<4> Offloaded_f_mainComp__io_pcIn_bits_pcType__prev;
  dat_t<4> Offloaded_f_mainComp_IncThroughOffloadgComponent_2__pcIn0_bits_pcType;
  dat_t<4> Offloaded_f_mainComp_IncThroughOffloadgComponent_2__pcIn0_bits_pcType__prev;
  dat_t<1> Offloaded__io_pcIn_bits_request;
  dat_t<1> Offloaded__io_pcIn_bits_request__prev;
  dat_t<1> Offloaded_f__io_pcIn_bits_request;
  dat_t<1> Offloaded_f__io_pcIn_bits_request__prev;
  dat_t<1> Offloaded_f_mainComp__io_pcIn_bits_request;
  dat_t<1> Offloaded_f_mainComp__io_pcIn_bits_request__prev;
  dat_t<1> Offloaded_f_mainComp_IncThroughOffloadgComponent_2__pcIn0_bits_request;
  dat_t<1> Offloaded_f_mainComp_IncThroughOffloadgComponent_2__pcIn0_bits_request__prev;
  dat_t<1> Offloaded__io_pcIn_valid;
  dat_t<1> Offloaded__io_pcIn_valid__prev;
  dat_t<1> Offloaded_f__io_pcIn_valid;
  dat_t<1> Offloaded_f__io_pcIn_valid__prev;
  dat_t<1> Offloaded_f_mainComp__io_pcIn_valid;
  dat_t<1> Offloaded_f_mainComp__io_pcIn_valid__prev;
  dat_t<1> Offloaded_f_mainComp_IncThroughOffloadgComponent_2__pcIn0_valid;
  dat_t<1> Offloaded_f_mainComp_IncThroughOffloadgComponent_2__pcIn0_valid__prev;
  dat_t<16> R16;
  dat_t<16> R16_shadow;
  dat_t<16> R16__prev;
  dat_t<1> Offloaded_f_mainComp_outputArb__io_in_0_ready;
  dat_t<1> Offloaded_f_mainComp_outputArb__io_in_0_ready__prev;
  dat_t<1> Offloaded_f_mainComp_IncThroughOffloadgComponent_2__io_out_ready;
  dat_t<1> Offloaded_f_mainComp_IncThroughOffloadgComponent_2__io_out_ready__prev;
  dat_t<16> R17;
  dat_t<16> R17_shadow;
  dat_t<16> R17__prev;
  dat_t<8> Offloaded__io_pcIn_bits_portId;
  dat_t<8> Offloaded__io_pcIn_bits_portId__prev;
  dat_t<8> Offloaded_f__io_pcIn_bits_portId;
  dat_t<8> Offloaded_f__io_pcIn_bits_portId__prev;
  dat_t<8> Offloaded_f_mainComp__io_pcIn_bits_portId;
  dat_t<8> Offloaded_f_mainComp__io_pcIn_bits_portId__prev;
  dat_t<8> Offloaded_f_mainComp_IncThroughOffloadgComponent_2__pcIn0_bits_portId;
  dat_t<8> Offloaded_f_mainComp_IncThroughOffloadgComponent_2__pcIn0_bits_portId__prev;
  dat_t<16> Offloaded__io_pcIn_bits_moduleId;
  dat_t<16> Offloaded__io_pcIn_bits_moduleId__prev;
  dat_t<16> Offloaded_f__io_pcIn_bits_moduleId;
  dat_t<16> Offloaded_f__io_pcIn_bits_moduleId__prev;
  dat_t<16> Offloaded_f_mainComp__io_pcIn_bits_moduleId;
  dat_t<16> Offloaded_f_mainComp__io_pcIn_bits_moduleId__prev;
  dat_t<16> Offloaded_f_mainComp_IncThroughOffloadgComponent_2__pcIn0_bits_moduleId;
  dat_t<16> Offloaded_f_mainComp_IncThroughOffloadgComponent_2__pcIn0_bits_moduleId__prev;
  dat_t<16> Offloaded__io_pcIn_bits_pcValue;
  dat_t<16> Offloaded__io_pcIn_bits_pcValue__prev;
  dat_t<16> Offloaded_f__io_pcIn_bits_pcValue;
  dat_t<16> Offloaded_f__io_pcIn_bits_pcValue__prev;
  dat_t<16> Offloaded_f_mainComp__io_pcIn_bits_pcValue;
  dat_t<16> Offloaded_f_mainComp__io_pcIn_bits_pcValue__prev;
  dat_t<16> Offloaded_f_mainComp_IncThroughOffloadgComponent_2__pcIn0_bits_pcValue;
  dat_t<16> Offloaded_f_mainComp_IncThroughOffloadgComponent_2__pcIn0_bits_pcValue__prev;
  dat_t<16> R18;
  dat_t<16> R18_shadow;
  dat_t<16> R18__prev;
  dat_t<8> R19;
  dat_t<8> R19_shadow;
  dat_t<8> R19__prev;
  dat_t<32> Offloaded_f_mainComp_IncThroughOffloadgComponent_2__counter;
  dat_t<32> Offloaded_f_mainComp_IncThroughOffloadgComponent_2__counter_shadow;
  dat_t<32> Offloaded_f_mainComp_IncThroughOffloadgComponent_2__counter__prev;
  dat_t<3> Offloaded_f_mainComp_IncThroughOffloadgComponent_2__state;
  dat_t<3> Offloaded_f_mainComp_IncThroughOffloadgComponent_2__state_shadow;
  dat_t<3> Offloaded_f_mainComp_IncThroughOffloadgComponent_2__state__prev;
  dat_t<1> R20;
  dat_t<1> R20_shadow;
  dat_t<1> R20__prev;
  dat_t<16> R21;
  dat_t<16> R21_shadow;
  dat_t<16> R21__prev;
  dat_t<1> R22;
  dat_t<1> R22_shadow;
  dat_t<1> R22__prev;
  dat_t<4> R23;
  dat_t<4> R23_shadow;
  dat_t<4> R23__prev;
  dat_t<1> Offloaded_f_mainComp__io_in_ready;
  dat_t<1> Offloaded_f_mainComp__io_in_ready__prev;
  dat_t<1> Offloaded_f__io_in_ready;
  dat_t<1> Offloaded_f__io_in_ready__prev;
  dat_t<1> Offloaded__io_in_ready;
  dat_t<1> Offloaded__io_in_ready__prev;
  dat_t<1> Offloaded_f_mainComp__io_out_valid;
  dat_t<1> Offloaded_f_mainComp__io_out_valid__prev;
  dat_t<1> Offloaded_f__io_out_valid;
  dat_t<1> Offloaded_f__io_out_valid__prev;
  dat_t<1> Offloaded__io_out_valid;
  dat_t<1> Offloaded__io_out_valid__prev;
  dat_t<32> Offloaded_f_mainComp_IncThroughOffloadgComponent_3__io_out_bits;
  dat_t<32> Offloaded_f_mainComp_IncThroughOffloadgComponent_3__io_out_bits__prev;
  dat_t<32> Offloaded_f_mainComp_outputArb__io_in_1_bits;
  dat_t<32> Offloaded_f_mainComp_outputArb__io_in_1_bits__prev;
  dat_t<32> Offloaded_f_mainComp_IncThroughOffloadgComponent_2__io_out_bits;
  dat_t<32> Offloaded_f_mainComp_IncThroughOffloadgComponent_2__io_out_bits__prev;
  dat_t<32> Offloaded_f_mainComp_outputArb__io_in_0_bits;
  dat_t<32> Offloaded_f_mainComp_outputArb__io_in_0_bits__prev;
  dat_t<32> Offloaded_f_mainComp_outputArb__io_out_bits;
  dat_t<32> Offloaded_f_mainComp_outputArb__io_out_bits__prev;
  dat_t<32> Offloaded_f_mainComp__io_out_bits;
  dat_t<32> Offloaded_f_mainComp__io_out_bits__prev;
  dat_t<32> Offloaded_f__io_out_bits;
  dat_t<32> Offloaded_f__io_out_bits__prev;
  dat_t<32> Offloaded__io_out_bits;
  dat_t<32> Offloaded__io_out_bits__prev;
  dat_t<1> Offloaded_f_offComp__io_pcOut_valid;
  dat_t<1> Offloaded_f_offComp__io_pcOut_valid__prev;
  dat_t<1> Offloaded_f__io_pcOut_valid;
  dat_t<1> Offloaded_f__io_pcOut_valid__prev;
  dat_t<1> Offloaded__io_pcOut_valid;
  dat_t<1> Offloaded__io_pcOut_valid__prev;
  dat_t<16> Offloaded_f_offComp__io_pcOut_bits_moduleId;
  dat_t<16> Offloaded_f_offComp__io_pcOut_bits_moduleId__prev;
  dat_t<16> Offloaded_f__io_pcOut_bits_moduleId;
  dat_t<16> Offloaded_f__io_pcOut_bits_moduleId__prev;
  dat_t<16> Offloaded__io_pcOut_bits_moduleId;
  dat_t<16> Offloaded__io_pcOut_bits_moduleId__prev;
  dat_t<1> Offloaded_f_offComp__io_pcOut_bits_request;
  dat_t<1> Offloaded_f_offComp__io_pcOut_bits_request__prev;
  dat_t<1> Offloaded_f__io_pcOut_bits_request;
  dat_t<1> Offloaded_f__io_pcOut_bits_request__prev;
  dat_t<1> Offloaded__io_pcOut_bits_request;
  dat_t<1> Offloaded__io_pcOut_bits_request__prev;
  dat_t<8> Offloaded_f_offComp__io_pcOut_bits_portId;
  dat_t<8> Offloaded_f_offComp__io_pcOut_bits_portId__prev;
  dat_t<8> Offloaded_f__io_pcOut_bits_portId;
  dat_t<8> Offloaded_f__io_pcOut_bits_portId__prev;
  dat_t<8> Offloaded__io_pcOut_bits_portId;
  dat_t<8> Offloaded__io_pcOut_bits_portId__prev;
  dat_t<16> Offloaded_f_offComp__io_pcOut_bits_pcValue;
  dat_t<16> Offloaded_f_offComp__io_pcOut_bits_pcValue__prev;
  dat_t<16> Offloaded_f__io_pcOut_bits_pcValue;
  dat_t<16> Offloaded_f__io_pcOut_bits_pcValue__prev;
  dat_t<16> Offloaded__io_pcOut_bits_pcValue;
  dat_t<16> Offloaded__io_pcOut_bits_pcValue__prev;
  dat_t<4> Offloaded_f_offComp__io_pcOut_bits_pcType;
  dat_t<4> Offloaded_f_offComp__io_pcOut_bits_pcType__prev;
  dat_t<4> Offloaded_f__io_pcOut_bits_pcType;
  dat_t<4> Offloaded_f__io_pcOut_bits_pcType__prev;
  dat_t<4> Offloaded__io_pcOut_bits_pcType;
  dat_t<4> Offloaded__io_pcOut_bits_pcType__prev;

  void init ( bool rand_init = false );
  void clock_lo ( dat_t<1> reset );
  void clock_hi ( dat_t<1> reset );
  void print ( FILE* f );
  bool scan ( FILE* f );
  void dump ( FILE* f, int t );
};
