#!/usr/bin/perl
if ($#ARGV != 0) {
  printf "$#ARGV Error: input file should be passed to configTop\n"; 
  exit;
}
$inputModule = $ARGV[0];
open INPUT, "<", "TopConfig" or die "TopConfig does not exist";
@configParams_array = <INPUT>;
close INPUT;
%config_array = ();
`> config.h`;
foreach $line (@configParams_array) {
	@line_array = split(' ', $line);
	$line_array_size = scalar @line_array;
	if ($line_array_size == 2) {
		$config_array{$line_array[0]}=$line_array[1];
        `echo '#define $line_array[0]  $line_array[1]' >> config.h`;
	} else {
          print "Error: topConfig format is wrong\n";
        }
}
`mv config.h ../src/include`;

open INPUT, "<", "SSF/$inputModule" . "SSF.scala" or die "$inputModule" . 
 "SSF.scala does not exist";

while (my $line = <INPUT>) {
  if ($line =~ /(.*)#(\w*)(.*)/) {
    print $1 . $config_array{$2} . $3 . "\n";  
  } else  {
    print $line;
  }
}

close INPUT;
