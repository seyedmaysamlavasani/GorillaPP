module test;
  reg clk = 0;
  reg reset = 1;
  wire [31:0] io_in_bits;
  wire [7:0] io_in_bits_l2Protocol;
  wire [7:0] io_in_bits_eth_l3Type;
  wire [7:0] io_in_bits_ipv4_ttl; 

  initial begin
    reset = 1;
    #250 reset = 0;
  end

  always #100 clk = ~clk;
    Top
      Top(
        .clk(clk),
        .reset(reset),
.io_in_ready(    io_in_ready),
.io_in_valid(    io_in_valid),
.io_in_bits_l2Protocol(    io_in_bits_l2Protocol),
.io_in_bits_eth_dstAddr(    io_in_bits_eth_dstAddr),
.io_in_bits_eth_srcAddr(    io_in_bits_eth_srcAddr),
.io_in_bits_eth_l3Type(    io_in_bits_eth_l3Type),
.io_in_bits_eth_length(    io_in_bits_eth_length),
.io_in_bits_ipv4_version(    io_in_bits_ipv4_version),
.io_in_bits_ipv4_hLength(    io_in_bits_ipv4_hLength),
.io_in_bits_ipv4_tos(    io_in_bits_ipv4_tos),
.io_in_bits_ipv4_length(    io_in_bits_ipv4_length),
.io_in_bits_ipv4_identification(    io_in_bits_ipv4_identification),
.io_in_bits_ipv4_flagsOffset(    io_in_bits_ipv4_flagsOffset),
.io_in_bits_ipv4_ttl(    io_in_bits_ipv4_ttl),
.io_in_bits_ipv4_protocol(    io_in_bits_ipv4_protocol),
.io_in_bits_ipv4_chksum(    io_in_bits_ipv4_chksum),
.io_in_bits_ipv4_srcAddr(    io_in_bits_ipv4_srcAddr),
.io_in_bits_ipv4_dstAddr(    io_in_bits_ipv4_dstAddr),
.io_out_ready(    io_out_ready),
.io_out_valid(    io_out_valid),
.io_out_bits_l2Protocol(    io_out_bits_l2Protocol),
.io_out_bits_eth_dstAddr(    io_out_bits_eth_dstAddr),
.io_out_bits_eth_srcAddr(    io_out_bits_eth_srcAddr),
.io_out_bits_eth_l3Type(    io_out_bits_eth_l3Type),
.io_out_bits_eth_length(    io_out_bits_eth_length),
.io_out_bits_ipv4_version(    io_out_bits_ipv4_version),
.io_out_bits_ipv4_hLength(    io_out_bits_ipv4_hLength),
.io_out_bits_ipv4_tos(    io_out_bits_ipv4_tos),
.io_out_bits_ipv4_length(    io_out_bits_ipv4_length),
.io_out_bits_ipv4_identification(    io_out_bits_ipv4_identification),
.io_out_bits_ipv4_flagsOffset(    io_out_bits_ipv4_flagsOffset),
.io_out_bits_ipv4_ttl(    io_out_bits_ipv4_ttl),
.io_out_bits_ipv4_protocol(    io_out_bits_ipv4_protocol),
.io_out_bits_ipv4_chksum(    io_out_bits_ipv4_chksum),
.io_out_bits_ipv4_srcAddr(    io_out_bits_ipv4_srcAddr),
.io_out_bits_ipv4_dstAddr(    io_out_bits_ipv4_dstAddr),
.io_pcIn_valid(    io_pcIn_valid),
.io_pcIn_bits_request(    io_pcIn_bits_request),
.io_pcIn_bits_moduleId(     io_pcIn_bits_moduleId),
.io_pcIn_bits_portId(    io_pcIn_bits_portId),
.io_pcIn_bits_pcValue(    io_pcIn_bits_pcValue),
.io_pcIn_bits_pcType(    io_pcIn_bits_pcType),
.io_pcOut_valid(    io_pcOut_valid),
.io_pcOut_bits_request(    io_pcOut_bits_request),
.io_pcOut_bits_moduleId(     io_pcOut_bits_moduleId),
.io_pcOut_bits_portId(    io_pcOut_bits_portId),
.io_pcOut_bits_pcValue(    io_pcOut_bits_pcValue),
    .io_pcOut_bits_pcType (io_pcOut_bits_pcType)
        );
  integer count;
  assign io_in_bits_l2Protocol = 8'd128;
  assign io_in_bits_eth_l3Type = 8'd64;
  assign io_in_bits_ipv4_ttl = 8'd3;
  assign io_in_valid = 1'b1;
  assign io_out_ready = 1'b1;
  
  always @(negedge clk) begin;
  #50;
  end
  always @(posedge clk) begin
  end
endmodule
