#!/usr/bin/perl
if (scalar @ARGV != 2) {
  print "Error: This program needs two arguments as the name of the module and number of threads " . 
   " arguments are @ARGV[0] @ARGV[1]\n";
} else {
  $moduleName = @ARGV[0];
  $numOfThreads =  @ARGV[1];

}
open INPUT, "<", "engine.template" or die "engine.template does not exist";
$foreqach_fu = 0;
$foreqach_instr = 0;
my @fu_file_line_array ; 

my @instr_array; 

open FU_FILE, "<", "offloadPorts.magillac" or die "can not open FU list file";
@fu_file_line_array = <FU_FILE>;
close FU_FILE;

open INSTR_FILE, "<", "instr.magillac" or die "can not open instr list file";
@instr_array = <INSTR_FILE>;
close INSTR_FILE;

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
#	if ($line =~ /(.*)#MODULE_NAME(.*)/){
#      print $1 . $moduleName . $2 . "\n";
#      $noPrint = 1; 
#    }
	$line =~ s/(.*)#MODULE_NAME(.*)/$1$moduleName$2/g;
	$line =~ s/(.*)#FIRST_STATE(.*)/$1$first_instr$2/g;
	$line =~ s/(.*)#IO(.*)/$1 $io $2/g;
	$line =~ s/(.*)#NUM_OF_THREADS(.*)/$1$numOfThreads$2/g;

	if ($line =~ /#DUMP_FILE\W*(\w*.magillac)/){
		open FILE, "<", $1 or die "can not open file" . $1;
		while (my $file_line = <FILE>) {
			print "   " . $file_line;
		} 
	}
		
	if ($foreach_instr && $line !~ /#END_FOREACH_INSTR/) {
		$instr_string = $instr_string . $line; 
	}
	if ($foreach_fu && $line !~ /#END_FOREACH_FU/) {
		$fu_string = $fu_string . $line; 
	}
	if ($line =~ /#FOREACH_INSTR/) {
		$foreach_instr = 1;
		$instr_string = "";
	}
	if ($line =~ /#FOREACH_FU/) {
		$foreach_fu = 1;
		$fu_string = "";
	}
	if (!$noPrint && !$foreach_fu && !$foreach_instr && $line !~ /#END_FOREACH_FU/&& $line !~ /#END_FOREACH_INSTR/ && $line !~ /#DUMP_FILE/) {
		print $line;
	}
	if ($line =~ /#END_FOREACH_FU/) {
		$foreach_fu = 0;
		foreach $fu (@fu_array) {
		  $fu_string_temp = $fu_string;
		  chomp($fu);
		  $fu_string_temp =~ s/##/$fu/g;
		  print $fu_string_temp;
		}
	}
	if ($line =~ /#END_FOREACH_INSTR/) {
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
}
