module test;
  reg clk = 0;
  reg reset = 1;
  wire [31:0] io_in_bits;

  initial begin
    reset = 1;
    #250 reset = 0;
  end

  always #100 clk = ~clk;
    Top
      Top(
        .clk(clk),
        .reset(reset),
        .io_in_ready(io_in_ready),
        .io_in_valid(io_in_valid),
        .io_in_bits(io_in_bits),
        .io_out_ready(io_out_ready),
        .io_out_valid(io_out_valid),
        .io_out_bits(io_out_bits)
        );
  integer count;
  assign io_in_bits = 32'd1;
  assign io_in_valid = 1'b1;
  assign io_out_ready = 1'b1;
  
  always @(negedge clk) begin;
  #50;
  end
  always @(posedge clk) begin
  end
endmodule
