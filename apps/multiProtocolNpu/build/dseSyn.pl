#!/usr/bin/perl
if (scalar @ARGV != 1) {
  die ("This program needs one argument as the start version of design spaces\n");
} 
$version = @ARGV[0];
@ENGINES = (3,5,6,7,9,10,11,12,13,14,15);
@THREADS = (1,2,3,4);
@OFFLOAD = ("true", "false");

sub generateConfig {
  $engines = shift;
  $threads = shift;
  $offload = shift;
  open(FILE, ">TopConfig");
print FILE <<EOF;
ipv4Engine_counts $engines
ipv4Engine_numOfThreads $threads
lookup_offloaded $offload 
lookup_pipelined false 
EOF
}

foreach $engines(@ENGINES) {
  foreach $threads(@THREADS) {
    foreach $offload(@OFFLOAD) {
      print "DSE engines " . $engines . " threads " . $threads . " offload " . $offload;
      generateConfig($engines, $threads, $offload);
      `make verilog`;
      `make syn_clean`;
      `make syn_all`;
      `mkdir dse/design_$version/`;
      `cp  synthesis/*.srp dse/design_$version/`;
      `cp  synthesis/*.mrp dse/design_$version/`;
      `cp  synthesis/*.par dse/design_$version/`;
      `cp  synthesis/*.pwr dse/design_$version/`;
      `cp  TopConfig dse/design_$version/`;
      $version = $version + 1; 
}}} 
