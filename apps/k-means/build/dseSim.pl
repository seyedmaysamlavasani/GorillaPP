#!/usr/bin/perl
if (scalar @ARGV != 1) {
  die ("This program needs one argument as the start version of design spaces\n");
} 
$version = @ARGV[0];
#@ENGINES = (1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16);
#@THREADS = (1,2,3,4,5,6,7,8);
@ENGINES = (1,2,3,4,5,6,7,8);
@THREADS = (1);

sub generateConfig {
  $engines = shift;
  $threads = shift;
  open(FILE, ">TopConfig");
print FILE <<EOF;
NUM_OF_POINTS 40 
NUM_OF_CENTEROIDS 40
NUM_OF_ENGINES $engines
NUM_OF_THREADS $threads
EOF
}

foreach $engines(@ENGINES) {
  foreach $threads(@THREADS) {
      print "DSE engines " . $engines . " threads " . $threads ;
      generateConfig($engines, $threads, $offload);
      `make | grep PC | tee PCResult`;
      `mkdir dse/design_$version/`;
      `cp  TopConfig dse/design_$version/`;
      `cp  PCResult dse/design_$version/`;
      $version = $version + 1; 
}} 
