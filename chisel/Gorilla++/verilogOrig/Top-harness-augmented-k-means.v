module test;
  reg clk = 0;
  reg reset = 1;

  initial begin
    reset = 1;
    #250 reset = 0;
  end

  always #100 clk = ~clk;
  reg  io_in_bits_centeroidsFinished ; 
  reg io_in_bits_pointsFinished ; 
  wire [15:0] io_in_bits_centeroidIndex;
  wire [63:0] io_in_bits_point_x;
  wire [63:0] io_in_bits_point_y;
  wire [63:0] io_in_bits_point_z;
  wire [15:0] io_out_bits_centeroidIndex;
  wire [63:0] io_out_bits_point_x;
  wire [63:0] io_out_bits_point_y;
  wire [63:0] io_out_bits_point_z;

  assign io_pcIn_valid = 1'b0;
  assign io_in_valid = 1'b1;
  assign io_out_ready = 1'b1;
 
    Top
      Top(
        clk,
        reset,
     io_in_ready,
     io_in_valid,
     io_in_bits_centeroidsFinished,
    io_in_bits_pointsFinished,
     io_in_bits_centeroidIndex,
     io_in_bits_point_x,
     io_in_bits_point_y,
     io_in_bits_point_z,
     io_out_ready,
     io_out_valid,
     io_out_bits_centeroidsFinished,
     io_out_bits_pointsFinished,
     io_out_bits_centeroidIndex,
     io_out_bits_point_x,
     io_out_bits_point_y,
     io_out_bits_point_z,
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

  integer count = 0;
  always @(negedge clk) begin
  #50;

  io_in_bits_centeroidsFinished = 1'b0; 
  io_in_bits_pointsFinished = 1'b0; 
  if (count == 10) begin
    io_in_bits_centeroidsFinished = 1'b1; 
  end  
  if (count == 100) begin
    io_in_bits_pointsFinished = 1'b1; 
  end
  if (io_in_ready) begin
    count = count + 1;
  end
  end
endmodule
