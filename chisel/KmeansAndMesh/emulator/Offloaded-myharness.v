module test;
    reg [0:0] io_in_valid;
    reg [31:0] io_in_bits;
    reg [0:0] io_out_ready;
    reg [0:0] io_pcIn_valid;
    reg [0:0] io_pcIn_bits_request;
    reg [15:0] io_pcIn_bits_moduleId;
    reg [7:0] io_pcIn_bits_portId;
    reg [15:0] io_pcIn_bits_pcValue;
    reg [3:0] io_pcIn_bits_pcType;
     wire [0:0] io_in_ready;
     wire [0:0] io_out_valid;
     wire [31:0] io_out_bits;
     wire [0:0] io_pcOut_valid;
     wire [0:0] io_pcOut_bits_request;
     wire [15:0] io_pcOut_bits_moduleId;
     wire [7:0] io_pcOut_bits_portId;
     wire [15:0] io_pcOut_bits_pcValue;
     wire [3:0] io_pcOut_bits_pcType;
  reg clk = 0;
  reg reset = 1;

  initial begin
    reset = 1;
    #250 reset = 0;
  end

  always #100 clk = ~clk;
    Offloaded
      Offloaded(
        .clk(clk),
        .reset(reset),
        .io_in_valid(io_in_valid),
        .io_in_bits(io_in_bits),
        .io_out_ready(io_out_ready),
        .io_pcIn_valid(io_pcIn_valid),
        .io_pcIn_bits_request(io_pcIn_bits_request),
        .io_pcIn_bits_moduleId(io_pcIn_bits_moduleId),
        .io_pcIn_bits_portId(io_pcIn_bits_portId),
        .io_pcIn_bits_pcValue(io_pcIn_bits_pcValue),
        .io_pcIn_bits_pcType(io_pcIn_bits_pcType),
        .io_in_ready(io_in_ready),
        .io_out_valid(io_out_valid),
        .io_out_bits(io_out_bits),
        .io_pcOut_valid(io_pcOut_valid),
        .io_pcOut_bits_request(io_pcOut_bits_request),
        .io_pcOut_bits_moduleId(io_pcOut_bits_moduleId),
        .io_pcOut_bits_portId(io_pcOut_bits_portId),
        .io_pcOut_bits_pcValue(io_pcOut_bits_pcValue),
        .io_pcOut_bits_pcType(io_pcOut_bits_pcType)
 );
  integer count;
  always @(negedge clk) begin;
  #50;
//    if (!reset) count = $fscanf('h80000000, "%x %x %x %x %x %x %x %x %x", io_in_valid, io_in_bits, io_out_ready, io_pcIn_valid, io_pcIn_bits_request, io_pcIn_bits_moduleId, io_pcIn_bits_portId, io_pcIn_bits_pcValue, io_pcIn_bits_pcType);
//      if (count == -1) $finish(1);
    io_in_valid = 1;
    io_in_bits = 1;
    io_out_ready = 1;
  end
  always @(posedge clk) begin
    if (!reset) $display("0x%x 0x%x 0x%x 0x%x 0x%x 0x%x 0x%x 0x%x 0x%x", io_in_ready, io_out_valid, io_out_bits, io_pcOut_valid, io_pcOut_bits_request, io_pcOut_bits_moduleId, io_pcOut_bits_portId, io_pcOut_bits_pcValue, io_pcOut_bits_pcType);
  end
endmodule
