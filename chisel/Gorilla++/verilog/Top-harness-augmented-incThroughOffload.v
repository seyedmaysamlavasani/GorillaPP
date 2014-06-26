module test;
  reg clk = 0;
  reg reset = 1;
  wire [31:0] io_in_bits;
  integer sinked = 0;
  integer sourced = 0;
  reg io_in_valid_reg = 0;
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
  assign io_in_valid = io_in_valid_reg;
  assign io_out_ready = 1'b1;
  
  always @(negedge clk) begin;
  end
  always @(posedge clk) begin
    if (io_out_valid) begin 
      sinked = sinked + 1;
    end
    if (sourced > 50) begin 
      io_in_valid_reg = 1'b0;
    end else begin 
      io_in_valid_reg = 1'b1;
    end
    if (sourced <= 50 && io_in_ready) begin 
      sourced = sourced + 1;
    end 
  end
endmodule
