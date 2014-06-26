module spMem32(input clk, input reset,
    output io_in_ready,
    input  io_in_valid,
    input [9:0] io_in_bits_address,
    input  io_in_bits_rw,
    input [1023:0] io_in_bits_wData,
    input [9:0] io_in_tag,
    input  io_out_ready,
    output io_out_valid,
    output[1023:0] io_out_bits_rData,
    output[9:0] io_out_tag,
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

  reg[1023:0] R0;
  wire[1023:0] T1;
  reg [1023:0] T2 [1023:0];
  wire[9:0] T3;
  wire T4;
  wire T5;
  wire[1023:0] T6;
  wire[1023:0] T7;
  wire T8;
  wire T9;

  assign io_in_ready = 1'h1/* 1*/;
  assign io_out_valid = 1'h1/* 1*/;
  assign io_out_bits_rData = R0;
  assign T4 = io_in_valid && T5;
  assign T5 = io_in_bits_rw == 1'h0/* 0*/;
  assign T7 = io_in_bits_wData;
  assign T8 = io_in_valid && T9;
  assign T9 = io_in_bits_rw == 1'h1/* 1*/;

  always @(posedge clk) begin
    if(T4)
      R0 <= T2[io_in_bits_address];
    if (T8)
      T2[io_in_bits_address] <= T7;
  end
endmodule

