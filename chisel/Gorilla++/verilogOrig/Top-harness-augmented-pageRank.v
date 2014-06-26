module test;
  reg clk = 0;
  reg reset = 1;
  reg sent = 0;
  reg previous_sent;
  wire [31:0] io_in_bits_length;
  assign io_out_ready = 1;
  assign io_in_bits_startPageId = 0;
  assign io_in_bits_length = 100;
  assign io_in_valid = sent & !previous_sent;
  assign io_in_bits_done = 0;
  always @(negedge clk) begin
    #50;
    previous_sent = sent;
    if (sent == 0 && !reset) begin
      sent = 1;
    end 
  end

Top Top(clk,  reset,
     io_in_ready,
     io_in_valid,
     io_in_bits_done,
    io_in_bits_startPageId,
    io_in_bits_length,
     io_out_ready,
     io_out_valid,
     io_out_bits_out,
     io_pcIn_valid,
     io_pcIn_bits_request,
    io_pcIn_bits_moduleId,
    io_pcIn_bits_portId,
    io_pcIn_bits_pcValue,
    io_pcIn_bits_pcType,
     io_pcOut_valid,
     io_pcOut_bits_request,
    io_pcOut_bits_moduleId,
    io_pcOut_bits_portId,
    io_pcOut_bits_pcValue,
    io_pcOut_bits_pcType);


  initial begin
    reset = 1;
    #250 reset = 0;
  end

  always #100 clk = ~clk;
endmodule 
