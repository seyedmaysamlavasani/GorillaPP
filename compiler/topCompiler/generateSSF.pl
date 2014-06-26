#!/usr/bin/perl

#  Regular expression that matches balanced braces
#my $re; $re = qr/ \{ (?: [^{}]* | (??{$re}) )* \} /x;
#my @array = $str =~ /$re/xg;

if ($#ARGV != 1) {
  printf "$#ARGV Error: input file and mode (source/refine) should be passed to SSF generator\n"; 
  exit;
}
$inputModule = $ARGV[0];
$mode = $ARGV[1];
#`cp ../src/Top.scala temp.scala`;

if ($mode eq "source" || $inputModule ne "Top") {
  print {STDERR} "generate SSF in source mode for $inputModule\n";
  `cp ../src/$inputModule.scala temp.scala`;
} else {
  print {STDERR} "generate SSF in refine mode for $inputModule\n";
  my $file="refine/output/$inputModule" . "Refined.scala";
  `cp $file temp.scala`;
}
$SSFModule = $inputModule . "SSF";
`>> SSF/$SSFModule.scala`;
do {
  $moreChanges = 0;
  open(INPUT, "temp.scala");
  open(OUTPUT, ">SSF/$SSFModule.scala");
  while (my $line = <INPUT>) {
    if ($line =~ /\/\/XX variable version (\d*)/) {
      $version = $1;
    } elsif ($line =~ /(\s*)(.*?)\((.*?)(\w*)\((.*)\)(.*)\)/ && 
     $line !~ /new/ && $line !~ /gInOut/ &&  
     $line !~ /ArrayBuffer/ && $line !~ /gComponent/) {
      $version = $version + 1;
      print OUTPUT "$1val xx$version = $4($5)\n";
      print OUTPUT "$1$2($3 xx$version $6)\n";
      $moreChanges = 1;
    } else {
     print OUTPUT $line;
    }
  }
  close(INPUT);
  close(OUTPUT);
  `>>version`;
  `echo "//XX variable version $version" > version`; 
  `cat version SSF/$SSFModule.scala > temp.scala`;
  #print "Version is $version\n";
} while ($moreChanges);
`mv temp.scala SSF/$SSFModule.scala`;
