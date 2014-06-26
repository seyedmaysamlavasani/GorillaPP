#!/usr/bin/perl

@file_line_array = <STDIN>;
%offloads;
#pass one: detects output and input types
foreach $line (@file_line_array) {
  if ($line =~ /OUTPUT\(\W*(\w*)\).*/) {
    $outputType = $1
  }
  if ($line =~ /INPUT\(\W*(\w*)\).*/) {
    $inputType = $1
  }
  if ($line =~ /OFFLOAD\(\W*(\w*)\W*,\W*(\w*)\W*\)/) {
   $offloads{$1} = $2;
  }
}
#pass two: generate the code
#
LOOP: foreach $line (@file_line_array) {
  #replace the OUTPUT(x) with x
  if ($line =~ s/OUTPUT\(\W*(\w*)\)(.*)/$1 $2/) {
    print $outputType . "Output";
  }
  #replace the INPUT(x) with x Input
  if ($line =~ s/(.*)INPUT\(\W*(\w*)\)(.*)/$1 $2 Input OFFLOADS $3/) {
    @offloadNames  = (keys %offloads);
    $n = $#offloadNames;
    $s = "";
    $c = 0;
    foreach my $off (keys %offloads) {
      if ($c == 0) {
        $c++;
        $s =  ", " . $s;
      }
      if ($n--) {
        $s = $s . $offloads{$off} . " " . $off . ", ";
      } else {
        $s = $s . $offloads{$off} . " " . $off;
      }
    }  
    $line =~ s/(.*)OFFLOADS(.*)/$1 $s $2/;
  }
  if ($line =~ s/GORILLAHEADER//) {
    next LOOP;
  }
  if ($line =~ s/GORILLATRAILER//) {
    print "  return Output;\n";
    next LOOP;
  }
  if ($line =~ s/OFFLOAD\(\W*(\w*)\W*,\W*(\w*)\W*\)/$1 $2/) {
    next LOOP;
  }
  print $line;
}
