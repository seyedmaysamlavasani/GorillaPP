module test;
  reg clk = 0;
  reg reset = 1;

  initial begin
    reset = 1;
    #250 reset = 0;
  end

  always #100 clk = ~clk;
    spMem32
      spMem32(
        .clk(clk),
        .reset(reset),

 );
  integer count;
  always @(negedge clk) begin;
  #50;
    if (!reset) count = $fscanf('h80000000, "");
      if (count == -1) $finish(1);
  end
  always @(posedge clk) begin
    if (!reset) $display("");
  end
endmodule
