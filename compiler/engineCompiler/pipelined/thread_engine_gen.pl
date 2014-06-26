#!/usr/bin/perl
if (scalar @ARGV != 2) {
  print "Error: This program needs two arguments as the name of the" .  
   "module and number of threads\n";
} else {
  $moduleName = @ARGV[0];
  $numOfThreads =  @ARGV[1];
}

open INPUT, "<", "engine.template" or die "thread_engine.template does not exist";
$foreach_fu = 0;
$foreach_instr = 0;
$foreach_instr_with_offload = 0;
$foreach_fu_of_this_instr = 0;

my @fu_file_line_array ; 
my @instr_array; 
%instr_has_offload;
%fuInstrMap;
sub nextInstrReady {
  my $instr = shift;
  my ( $index )= grep { $instr_array[$_] =~ /$instr/ } 0..$#instr_array;
  if ($index == @instr_array-1) {
    return "outputReg_ready";
  } else {
    %instrFuMap = reverse %fuInstrMap; 
    if (exists $instrFuMap{$instr_array[$index+1]}) {
      ##Next stage is DS
      return $instr_array[$index+1] . "PRegPreOff_ready";  
    } else {
      return $instr_array[$index+1] . "PRegSS_ready";  
    } 
  }
}
open INSTR_FILE, "<", "instr_fp.magillac" or die "can not open instr list file";
while (my $line = <INSTR_FILE>) {
  @line_array = split ('\s+', $line);
  $instr_array[@instr_array]=$line_array[0];
  if (@line_array >= 2 && $line_array[2] ne "hasNoOffload") {
    $instr_has_offload{$line_array[0]} = 1; 
  } else {
    $instr_has_offload{$line_array[0]} = 0; 
  }
  for (my $i=2; $i < @line_array; $i = $i + 1) { 
    my $fuName = $line_array[$i];
    if ($fuName ne "hasNoOffload") {
      $fuInstrMap{$fuName}=$line_array[0];
    }
  }
}
close INSTR_FILE;
#print "$_\t $fuInstrMap{$_}\n" for (keys %fuInstrMap);

open FU_FILE, "<", "offloadPorts.magillac" or die "can not open FU list file";
@fu_file_line_array = <FU_FILE>;
close FU_FILE;


open GLOBAL_DEFINITIONS_FILE, "<", "definition.magillac" or die "cannot open gobal definitions file";
@definition_array = <GLOBAL_DEFINITIONS_FILE>;
close GLOBAL_DEFINITION_FILE;

open IO_FILE, "<", "io.magillac" or die "cannot open io definitions file";
@io_array = <IO_FILE>;
close IO_FILE;
$io = @io_array[0];

$first_instr = @instr_array[0];

%bus_type;

foreach $line (@fu_file_line_array) {
  @line_array = split(' ', $line);
  $line_array_size = scalar @line_array;
  if ($line_array_size == 1) {
  	$fu_array[@fu_array]=$line_array[0];
  	#print "line_array is 1\n";
  } else {
  	$fu_array[@fu_array]=$line_array[0];
  	$bus_type{$line_array[0]}=$line_array[1];
  	#print "line_array is $line_array_size\n";
  }
}

while (my $line = <INPUT>) {
  $noPrint = 0;
#  if ($line =~ /(.*)#MODULE_NAME(.*)/){
#    print $1 . $moduleName . $2 . "\n";
#    $noPrint = 1; 
#  }
  $line =~ s/(.*)#MODULE_NAME(.*)/$1$moduleName$2/g;
  if ($instr_has_offload{$first_instr}) {
    $first_state = $first_instr . "PRegPreOff";
  } else {
    $first_state = $first_instr . "PRegSS";
  }
  $line =~ s/(.*)#FIRST_STATE(.*)/$1$first_state$2/g;
  $line =~ s/(.*)#IO(.*)/$1 $io $2/g;
  $line =~ s/(.*)#NUM_OF_THREADS(.*)/$1$numOfThreads$2/g;

  if ($line =~ /#DUMP_FILE\W*(\w*.magillac)/){
    open FILE, "<", $1 or die "can not open file" . $1;
    while (my $file_line = <FILE>) {
      print "   " . $file_line;
    } 
  }
		
  if (($foreach_instr || $foreach_instr_with_offload) && $line !~ /#END_FOREACH_INSTR/) {
    $instr_string = $instr_string . $line; 
  }
  if ($foreach_fu && $line !~ /#END_FOREACH_FU/) {
    $fu_string = $fu_string . $line; 
  }
  if ($line =~ /#FOREACH_INSTR_WITH_OFFLOAD/) {
    $foreach_instr_with_offload = 1;
    $instr_string = "";
  } elsif ($line =~ /#FOREACH_INSTR/) {
    $foreach_instr = 1;
    $instr_string = "";
  }
  if ($line =~ /#FOREACH_FU/ && $line !~ /#FOREACH_FU_OF_THIS_INSTR/) {
    $foreach_fu = 1;
    $fu_string = "";
  }

  if (!$noPrint && !$foreach_fu && !$foreach_instr && !$foreach_instr_with_offload && 
    $line !~ /#END_FOREACH_FU/&& $line !~ /#END_FOREACH_INSTR/&& $line !~ /#DUMP_FILE/) {
    print $line;
  }

  if ($line =~ /#END_FOREACH_FU/ && $line !~ /#END_FOREACH_FU_OF_THIS_INSTR/) {
    $foreach_fu = 0;
    foreach $fu (@fu_array) {
      $fu_string_temp = $fu_string;
      chomp($fu);
      $fu_string_temp =~ s/##/$fu/g;
      print $fu_string_temp;
    }
  }

  if ($line =~ /#END_FOREACH_INSTR/ && $line !~ /#END_FOREACH_INSTR_WITH_OFFLOAD/) {
    $foreach_instr = 0;
    $i = 0;
    foreach $instr (@instr_array) {
      chomp($instr);
      $instr_string_temp = $instr_string;
      $instr_string_temp =~ s/##/$instr/g;
      $instr_string_temp =~ s/#index/$i/g;
      print $instr_string_temp;
      $i = $i + 1;
    }
  }

  if ($line =~ /#END_FOREACH_INSTR_WITH_OFFLOAD/) {
    $i = 0;
    $out_string = "";
    foreach $instr (@instr_array) {
      chomp($instr);
      if ($instr_has_offload{$instr} == 1) {
        $instr_string_temp = $instr_string;
        $instr_string_temp =~ s/##/$instr/g;
        $instr_string_temp =~ s/#index/$i/g;
        $nextInstrReady = nextInstrReady($instr);
        $instr_string_temp =~ s/#nextInstrReady/$nextInstrReady/g;
        $out_string = $out_string . $instr_string_temp;
        $i = $i + 1;
      }
    }
    @out_string_array =  split('\n', $out_string);
    my $current_instr;
    $current_instr_index = 0;
    foreach $out_string_line (@out_string_array) {
      #print "AAA " . $out_string_line . "\n";
      if ($out_string_line =~ /#FOREACH_FU_OF_THIS_INSTR/) {
        $foreach_fu_of_this_instr = 1;
        $fu_string = "";
        while ($current_instr_index < @instr_array && 
         $instr_has_offload{$instr_array[$current_instr_index]} == 0) {
          $current_instr_index += 1; 
        }
        $current_instr =  $instr_array[$current_instr_index];
        $current_instr_index += 1;
        #print "BBB predicted current instr is " . $current_instr . "\n";
      }
      if ($foreach_fu_of_this_instr && 
       $out_string_line !~ /#END_FOREACH_FU_OF_THIS_INSTR/ &&
       $out_string_line !~ /#FOREACH_FU_OF_THIS_INSTR/) {
        $fu_string = $fu_string . $out_string_line . "\n"; 
      }
      if ($out_string_line =~ /#END_FOREACH_FU_OF_THIS_INSTR/) {
        $foreach_fu_of_this_instr = 0;
        foreach $fu (@fu_array) {
          if ($fuInstrMap{$fu} eq $current_instr) { 
            $fu_string_temp = $fu_string;
            chomp($fu);
            $fu_string_temp =~ s/%%/$fu/g;
            print $fu_string_temp;
          }
        }
      }
      if (!$foreach_fu_of_this_instr && $out_string_line !~ /#END_FOREACH/ && 
        $out_string_line !~ /#DUMP_FILE/) {
        print $out_string_line . "\n";
      }
    }
    $foreach_instr_with_offload = 0;
  }
}
