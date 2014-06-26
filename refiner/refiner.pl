#!/usr/bin/perl
#`cat TopConfig`;
sub r1 {
  $out=`make refineOneIteration 2>/dev/null | egrep "Refine"`;
  #$out=`make refineOneIteration | egrep "Refine"`;
  @lines = split(/\n/, $out);
  $bottleneckNode = "";
  foreach $line (@lines) {
    if ($line =~ /Refine/ && $line !~ /TopRefined/) {
      print $line . "\n";
      if ($line =~ /bottleneck node (\w+)/) {
        $bottleneckNode = $1;
      }
    }
  }
}

`>> refine.out`;
`> refine.out`;
 
print "Building original micro-arch\n";
$out=`make 2>/dev/null | egrep throughput`;
#$out=`make | egrep throughput`;
@lines = split(/\n/, $out);
foreach $line (@lines) {
  if ($line =~ /throughput (\d+\.\d+)/) {
    $throughput = $1;
    print "Throughput is $throughput\n";
  }
}
$rThreshold = 0.05;
#$rThreshold = 0.0005;
$version = 1;
$oldThroughput = 0;
$moreRefinements = 1;
$backTrack = 0;
`echo $backTrack > refine/backTrack`;
$oldBottleneckNode = "";
while($moreRefinements) {
  $oldThroughput = $throughput;
  print "\033[32mRefine micro-arch iteration: " . $version . "\033[0m\n";
  $version = $version + 1;
  $bottleneckNode = "";
  r1();
  if ($bottleneckNode eq "") {
    print "No more refinement found\n";
    $moreRefinements = 0;
  } else {
    $out=`make simulateRefined 2>/dev/null | egrep "throughput|back pressure|offload rate" | grep -v TopRefined`;
    #$out=`make simulateRefined | egrep "throughput|back pressure|offload rate" | grep -v TopRefined`;
    @lines = split(/\n/, $out);
    foreach $line (@lines) {
      if ($line =~ /throughput (\d+\.\d+)/) {
        $throughput = $1;
        print "Refined throughput is $throughput\n";
      }
      if (!($bottleneckNode eq "")  && $line =~ /back pressure/ && $line =~ /$bottleneckNode /) {
        print $line . "\n"; 
      }
      if (!($bottleneckNode eq "")  && $line =~ /offload rate/ && $line =~ /$bottleneckNode /) {
        print $line . "\n"; 
      }

    }
    print `cat refine/output/TopConfig`;
    #$oldBottleneckNode = $bottleneckNode;
    #r1(); 
    if (((($throughput - $oldThroughput) / $throughput) > $rThreshold) || 
     ($bottleneckNode ne $oldBottleneckNode)) {
      $backTrack = 0;
    } else { 
      print "Minor improvment, back track the change\n";
      $backTrack = $backTrack + 1;
      `make backTrack`;
      `cp refine/input/TopSSF.scala Top.scala`;
      `cp refine/input/TopConfig refine/output/TopConfig`;
    } 
    `echo $backTrack > refine/backTrack`;
  }
  `cat PCREPORT.out >> refine.out`;
  `cat refine/output/TopRefined.scala >> refine.out`;
  `echo "****************************************" >> refine.out`;
}
