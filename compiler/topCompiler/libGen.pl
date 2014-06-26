#!/usr/bin/perl
if ($#ARGV == 1) {
  $topModuleName=$ARGV[0];  
  $synSim=$ARGV[1];  
} else {
  print "libGen needs two arguemnts as top module name and synSim type\n";
}

$libDir = "../../lib/";
$DPFPlibDir = $libDir . "floatingPoints/doublePrecision/";
$partDir = "xilinx/virtex6/xc6vhx250t/";
%lib = ( 
  FPDPAdder => {
    "simModelFile" => $DPFPlibDir . "adder/sim/adder.c",  
    "simModel" => "Engine(\"adder.c\")",                  
    "synModelWrapperFile" => $DPFPlibDir .                
     "adder/pipelinedVerilog/" . $partDir .               
     "doublePrecisionAdderWrapper.scala",
    "synModelVerilogFile" => $DPFPlibDir . 
     "adder/pipelinedVerilog/" . $partDir . 
     "types_float_double_grp_fu_86_ACMP_dadd_2.v",
    "synModel" =>  "() => new FUSynWrapper (() => UFix(width=64)) " . 
     "(() => new types_float_double_grp_fu_86_ACMP_dadd_2_io) (stages=4)" ,
    "ioType" => "() => UFix(width=64)"
  },
  FPDPMultiplier => {
    "simModelFile" => $DPFPlibDir . "multiplier/sim/multiplier.c",
    "simModel" => "Engine(\"multiplier.c\")",
    "synModelWrapperFile" => $DPFPlibDir . 
     "multiplier/pipelinedVerilog/" . $partDir . 
     "doublePrecisionMultiplierWrapper.scala",
    "synModelVerilogFile" => $DPFPlibDir . 
     "multiplier/pipelinedVerilog/" . $partDir . 
     "types_float_double_grp_fu_93_ACMP_dmul_3.v",
    "synModel" =>  "() => new FUSynWrapper (() => UFix(width=64)) " . 
     "(() => new types_float_double_grp_fu_93_ACMP_dmul_3_io) (stages=4)" ,
    "ioType" => "() => UFix(width=64)"
  },
  FPDPDivider => {
    "simModelFile" => $DPFPlibDir . "divider/sim/divider.c",
    "simModel" => "Engine(\"divider.c\")",
    "synModelWrapperFile" => $DPFPlibDir . 
     "divider/pipelinedVerilog/" . $partDir . 
     "doublePrecisionDividerWrapper.scala",
    "synModelVerilogFile" => $DPFPlibDir . 
     "divider/pipelinedVerilog/" . $partDir . 
     "types_float_double_grp_fu_100_ACMP_ddiv_4.v",
    "synModel" =>  "() => new FUSynWrapper (() => UFix(width=64)) " . 
     "(() => new types_float_double_grp_fu_100_ACMP_ddiv_4_io) (stages=12)" ,
    "ioType" => "() => UFix(width=64)"
  },
  FPDPSqrt => {
    "simModelFile" => $DPFPlibDir . "sqrt/sim/sqrt.c",
    "simModel" => "Engine(\"sqrt.c\")",
    "synModelWrapperFile" => $DPFPlibDir . 
     "sqrt/pipelinedVerilog/" . $partDir . 
     "doublePrecisionSqrtWrapper.scala",
    "synModelVerilogFile" => $DPFPlibDir . 
     "sqrt/pipelinedVerilog/" . $partDir . 
     "types_float_double_grp_fu_100_ACMP_dsqrt_4.v",
    "synModel" =>  "() => new FUSynWrapper (() => UFix(width=64)) " . 
     "(() => new types_float_double_grp_fu_100_ACMP_dsqrt_4_io) (stages=12)" ,
    "ioType" => "() => UFix(width=64)"
  },

  PipedFPDPAdder => {
    "simModelFile" => $DPFPlibDir . "adder/sim/adder.c",
    "simModel" => "PipeEngine(\"adder.c\")",
    "synModelWrapperFile" => $DPFPlibDir . 
     "adder/pipelinedVerilog/" . $partDir . 
     "doublePrecisionAdderWrapper.scala",
    "synModelVerilogFile" => $DPFPlibDir . 
     "adder/pipelinedVerilog/" . $partDir . 
     "types_float_double_grp_fu_86_ACMP_dadd_2.v",
    "synModel" =>  "() => new FUSynWrapper (() => UFix(width=64)) " . 
     "(() => new types_float_double_grp_fu_86_ACMP_dadd_2_io) (stages=4)" ,
    "ioType" => "() => UFix(width=64)"
  },
  PipedFPDPMultiplier => {
    "simModelFile" => $DPFPlibDir . "multiplier/sim/multiplier.c",
    "simModel" => "PipeEngine(\"multiplier.c\")",
    "synModelWrapperFile" => $DPFPlibDir . 
     "multiplier/pipelinedVerilog/" . $partDir . 
     "doublePrecisionMultiplierWrapper.scala",
    "synModelVerilogFile" => $DPFPlibDir . 
     "multiplier/pipelinedVerilog/" . $partDir . 
     "types_float_double_grp_fu_93_ACMP_dmul_3.v",
    "synModel" =>  "() => new FUSynWrapper (() => UFix(width=64)) " . 
     "(() => new types_float_double_grp_fu_93_ACMP_dmul_3_io) (stages=4)" ,
    "ioType" => "() => UFix(width=64)"
  },
  PipedFPDPDivider => {
    "simModelFile" => $DPFPlibDir . "divider/sim/divider.c",
    "simModel" => "PipeEngine(\"divider.c\")",
    "synModelWrapperFile" => $DPFPlibDir . 
     "divider/pipelinedVerilog/" . $partDir . 
     "doublePrecisionDividerWrapper.scala",
    "synModelVerilogFile" => $DPFPlibDir . 
     "divider/pipelinedVerilog/" . $partDir . 
     "types_float_double_grp_fu_100_ACMP_ddiv_4.v",
    "synModel" =>  "() => new FUSynWrapper (() => UFix(width=64)) " . 
     "(() => new types_float_double_grp_fu_100_ACMP_ddiv_4_io) (stages=12)" ,
    "ioType" => "() => UFix(width=64)"
  },
  PipedFPDPSqrt => {
    "simModelFile" => $DPFPlibDir . "sqrt/sim/sqrt.c",
    "simModel" => "PipeEngine(\"sqrt.c\")",
    "synModelWrapperFile" => $DPFPlibDir . 
     "sqrt/pipelinedVerilog/" . $partDir . 
     "doublePrecisionSqrtWrapper.scala",
    "synModelVerilogFile" => $DPFPlibDir . 
     "sqrt/pipelinedVerilog/" . $partDir . 
     "types_float_double_grp_fu_100_ACMP_dsqrt_4.v",
    "synModel" =>  "() => new FUSynWrapper (() => UFix(width=64)) " . 
     "(() => new types_float_double_grp_fu_100_ACMP_dsqrt_4_io) (stages=12)" ,
    "ioType" => "() => UFix(width=64)"
  }
);
open FILE, "<", "$topModuleName" . "Configed.scala" or die "cannot open input file\n";
@file_line_array = <FILE>;

sub copyLibFiles {
  my $module = shift;
  if ($synSim eq "sim") {
    $file = $lib{$module}{"simModelFile"};
    `cp $file ../src/engines`; 
  } elsif ($synSim eq "syn") {
    $file = $lib{$module}{"synModelWrapperFile"}; 
    `cp $file .`; 
    $file = $lib{$module}{"synModelVerilogFile"}; 
    `cp $file verilog/`; 
  }
}

if ($synSim == "syn") {
  `cp ../../lib/floatingPoints/common/chiselWrapper.scala .`;
}

foreach $line (@file_line_array) {
  #Parse a SSF line to see if it is a library call
  if ($line =~ /(\W*)(\w*)\W*(\w*)\W*=\W*(\w*)\(\W*\)(.*)/ && exists($lib{$4})) {
    copyLibFiles($4);
    if ($synSim eq "sim") {
      $line = "$1$2 $3 = " . $lib{$4}{"simModel"} . "\n"; 
    } elsif ($synSim eq "syn") {
      print "$1$2 $3_MD = new gComponentMD(() => (new dualInput(" .  $lib{$4}{"ioType"} .
       ")), " . $lib{$4}{"ioType"} . ", ArrayBuffer())\n";  
      $line = "$1$2 $3 = ($3_MD, " . $lib{$4}{"synModel"} . "(extCompName = \"$3\"))" . 
       ".asInstanceOf[(gComponentMD[Chisel.Data,Chisel.Data], " . 
       "() => gComponent[Chisel.Data,Chisel.Data])]\n"; 
    }
  }
  print $line;
}

