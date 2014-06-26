#!/usr/bin/perl
use Term::ANSIColor qw(:constants);
#$firstPassCompileType="pipelined";
$firstPassCompileType="firstPass";
if ($#ARGV > 0) {
  print {STDERR} "$#ARGV Error: input file should be passed to generateTop\n"; 
  exit;
}

if ($#ARGV == -1) {
  #We want this for debug
  $topModuleName="Top";  
} elsif ($#ARGV == 0) {
  $topModuleName=$ARGV[0];  
} else {
  print "Error too many arguments for generateTop\n";
}

$headersCompileType="multiThread"; 
open FILE, "<", "$topModuleName" . "LibGenerated.scala" or die "cannot open input file\n";
@file_line_array = <FILE>;
close FILE;
$enginesMDFile="../../../compiler/engineCompiler/$compileType/enginesMD";

sub initializeMacrosAndTypes {
  my $compileType = shift;
  `> ../../../compiler/engineCompiler/$compileType/typedef.magillac`;
  `> ../../../compiler/engineCompiler/$compileType/macros.magillac`;
}

sub copyMacrosAndTypesFromHeaderFiles {
  my $compileType = shift;
  `../../../compiler/engineCompiler/$compileType/print_macros.pl < ../src/include/$fileName >> ../../../compiler/engineCompiler/$compileType/macros.magillac`;
  `../../../compiler/engineCompiler/$compileType/print_typedef.pl < ../src/include/$fileName >> ../../../compiler/engineCompiler/$compileType/typedef.magillac`;
  `awk '!/typedef/ || /struct/' < ../src/include/$fileName > ../../../compiler/engineCompiler/$compileType/$fileName`;
}


sub generate_engine_metaData {
  my $name = shift;
  my  $compileType = $compileTypes{$name};
  $fileName = "../src/engines/" . $name . ".c";
  `grep CONCURRENT_SAFE $fileName && echo "$name CONCURRENT_SAFE\n">> $enginesMDFile`;
  $numOfProcessingSteps=`wc ../../../compiler/engineCompiler/$compileType/instr.magillac | awk '{print $1}'`;
  $pipelineStages = $numOfProcessingSteps * 2;
  if ($pipelinable{$name}) { 
    `echo "$name PIPELINABLE  $pipelineStages\n" >> $enginesMDFile`;
  } 
}

sub compile_engine {
  my $moduleOrHeader =  shift; 
  my $mhName = shift;
  my $compileType;

  if ($moduleOrHeader eq "header") {
    $compileType = $headersCompileType;
  } 
  if ($moduleOrHeader eq "module") {
    $compileType = $compileTypes{$mhName};
    $numOfThreads = shift;
  }
  print {STDERR} "compile_engine called " . $moduleOrHeader . " " . $mhName . "\n"; 
  #print "module type is $moduleOrHeader name is $mhName\n";
  if ($moduleOrHeader eq "module") {
    $fileName = $mhName . ".c";
  } else {
    $fileName = $mhName . ".h";
  } 
  if ($moduleOrHeader eq "module") {
    `cp ../src/engines/$fileName ../../../compiler/engineCompiler/$compileType/$fileName`;
    #copy to pipelined anyway because we need to do the first pass
    `cp ../src/engines/$fileName ../../../compiler/engineCompiler/$firstPassCompileType/$fileName`;
  } 
  if ($moduleOrHeader eq "header") {
    copyMacrosAndTypesFromHeaderFiles("multiThread"); 
    copyMacrosAndTypesFromHeaderFiles("pipelined"); 
  } 
  ######################First pass Compile########################
  if ($moduleOrHeader ne "header") {
    $out=`cd ../../../compiler/engineCompiler/$firstPassCompileType/; java -cp .:antlr-3.4-complete.jar Main_fp $fileName 2>&1 | egrep 'Error|line'`; 
    `cd ../../../compiler/engineCompiler/$firstPassCompileType/; cp instr.magillac ../$compileType/instrFp.magillac; cp globalLvalues.magillac ../$compileType/globalLvalues.magillac`;
    if ($out !~ /not pipelin/) {
      $pipelinable{$mhName} = 1;
    } else {
      if ($compileType eq "pipelined") {
        print {STDERR} "Gorilla++Error: cannot pipeline engine " . $fileName . " \n"; 
      }
    }
  } 
  ######################Second pass Compile########################
  $out=`cd ../../../compiler/engineCompiler/$compileType/; java -cp .:antlr-3.4-complete.jar Main $fileName 2>&1 | egrep 'Error| line'`; 

  print {STDERR} RED, $out, RESET;
  if ($moduleOrHeader eq "module") {
    $out=`cd ../../../compiler/engineCompiler/$compileType/; ./thread_engine_gen.pl $mhName $numOfThreads| tee $mhName.scala`;
    #print $out;
    `cp ../../../compiler/engineCompiler/$compileType/$mhName.scala .`;
    open FILE, "<", "../../../compiler/engineCompiler/$compileType/io.magillac" or die "cannot open  io.magillac file\n";
    @io_file_line_array = <FILE>;
    close FILE;
  } 
  if ($moduleOrHeader eq "header") {
    `cp ../../../compiler/engineCompiler/$compileType/genericHeader ./$mhName.scala`;
    `cat ../../../compiler/engineCompiler/$compileType/definition.magillac >> ./$mhName.scala`;
  } 
}
###################### Create the macro file ################
`cp  ../../../compiler/engineCompiler/$headersCompileType/genericHeader macros.scala`;
`echo "trait include extends GorillaUtil {\n val dummy = 0 \n" >> macros.scala`;
###################### Compile the header files ##############
initializeMacrosAndTypes("multiThread");
initializeMacrosAndTypes("pipelined"); 

$buffer = `ls ../src/include/`;
chomp($buffer);
@headerFiles = split (' ', $buffer); 
foreach $hFile (@headerFiles) {
  chomp($hFile);
  #print "Header file " . $hFile . "\n";
  if ($hFile =~ /(.*)\.h/) {
    $hFile =~ s/(.*)\.h/$1/;
    compile_engine("header", $hFile);
    `cat  ../../../compiler/engineCompiler/$headersCompileType/macroDefintions.magillac >> macros.scala`;
  }
}
###################
`echo "}\n" >> macros.scala`;

################# Find the engines ###################
@engineFileNames = ();
%numOfThreads = ();
%compileTypes = ();
%pipelinable = ();

foreach $line (@file_line_array) {
  %efnHash = map {$_ => 1} @engineFileNames; 
  if ($line =~ /.*Engine\(\"(.*)\.c\"\).*/ && !exists($efnHash{$1})) {
    push(@engineFileNames, $1);
    $numOfThreads{$1} = "1";
    $compileTypes{$1} = "multiThread";
    #$compileTypes{$1} = "simpleStateMachine"; //THIS COMPILER HAS A BUG

  }
  if ($line =~ /.*MTEngine\(\"(.*)\.c\"\,\W*(\w*)\W*\).*/ && !exists($efnHash{$1})) {
    push(@engineFileNames, $1);
    $numOfThreads{$1} = $2;
    $compileTypes{$1} = "multiThread";
  }
  if ($line =~ /.*PipeEngine\(\"(.*)\.c\"\).*/ && !exists($efnHash{$1})) {
    push(@engineFileNames, $1);
    $numOfThreads{$1} = "4";
    $compileTypes{$1} = "pipelined";
  }
}
################# Compile the engines #################
`>> $enginesMDFile`;
`> $enginesMDFile`;
%ioStrings = ();
foreach $ef (@engineFileNames) {
  #print "compiling module" . $ef . "\n";
  if (!exists $ioStrings{$ef}) {
    compile_engine("module", $ef, $numOfThreads{$ef});
    $ioStrings{$ef} = $io_file_line_array[0]; 
    generate_engine_metaData($ef);
  }
}
`cp $enginesMDFile .`;
################# Generate the top  #################
%generatedMD = ();
foreach $line (@file_line_array) {
  if ($line =~ /.*(Engine)\(\"(.*)\.c\"\).*/ || 
   $line =~ /.*(MTEngine)\(\"(.*)\.c\"\,\W*\w*\W*\).*/) {
    $engineName =  $2;
    $ioString = $ioStrings{$engineName};
    $ioString =~ s/\((.*)\)\((.*)\) \((.*)\)/\($1, $2, $3\)/; 
    if (!exists($generatedMD{$engineName})) {
      print  "  val " . $engineName . "_MD =" .  " new gComponentMD" . $ioString . "\n"; 
      $generatedMD{$engineName} = 1;
    }
    $engine_MD = $engineName . "_MD";
    $line =~ s/(\W*)(\w+)?\b(\w+)\W*=\W*Engine\(\"(.*)\.c\"\)(.*)/$1$2 $3 =  ($engine_MD , () => new $engineName (extCompName=\"$3\") ).asInstanceOf[(gComponentMD[Chisel.Data,Chisel.Data], () => gComponent[Chisel.Data,Chisel.Data])]/;
    $line =~ s/(\W*)(\w+)?\b(\w+)\W*=\W*MTEngine\(\"(.*)\.c\"\,\W*\w*\W*\)(.*)/$1$2 $3 =  ($engine_MD , () => new $engineName (extCompName=\"$3\")).asInstanceOf[(gComponentMD[Chisel.Data,Chisel.Data], () => gComponent[Chisel.Data,Chisel.Data])]/;

###Pipeline compiler Active
    $line =~ s/(\W*)(\w+)?\b(\w+)\W*=\W*PipeEngine\(\"(.*)\.c\"\)(.*)/$1$2 $3 =  ($engine_MD , () => new $engineName (extCompName=\"$3\")).asInstanceOf[(gComponentMD[Chisel.Data,Chisel.Data], () => gComponent[Chisel.Data,Chisel.Data])]/;

  }
  if ($line =~ /(\W*)(\w+)?\b(\w+)\W*=\W*DummyPipeEngine\(\W*(\w*)\W*\)(.*)/) {
    $pipeDef = "gPipe(" . $4 . ")";   
    $line = "$1$2 $3 =  ($3" . "_MD , () => (new $pipeDef)).asInstanceOf[(gComponentMD[Chisel.Data,Chisel.Data], () => gComponent[Chisel.Data,Chisel.Data])]\n";
  }
  if ($line =~ /.*Offload.*/) {
    $line =~ s/(\W*)(\w+)?\b(\w+)\W*=\W*Offload\((.*)\)/$1$2 $3 = Offload \($4, extCompName="$3")/;
  }
  if ($line =~ /.*Chain.*/) {
    $line =~ s/(\W*)(\w+)?\b(\w+)\W*=\W*Chain\((.*)\)/$1$2 $3 = Chain \($4, extCompName="$3")/;
  }
  if ($line =~ /.*Replicate.*/) {
    $line =~ s/(\W*)(\w+)?\b(\w+)\W*=\W*Replicate\((.*)\)/$1$2 $3 = Replicate \($4, extCompName="$3")/;
  }
  if ($line =~ /.*result.*/ && ! ($line =~ /\/\/.*/)) {
     print $line;
     print "  val generatedTop = result._2()\n";
     print "  generatedTop.io <> io\n";
  } else {
     print $line;
  } 
}

