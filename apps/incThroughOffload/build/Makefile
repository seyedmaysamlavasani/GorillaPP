SBT :=java -Xmx4096M -Xss8M -XX:MaxPermSize=2G -jar sbt-launch.jar
topCompilerDir :=../../../compiler/topCompiler
refinerDir :=../../../refiner

################################### USABLE TARGETS ################################
simulate:               #default rule for compile and simulate. 
                        #Origianl config is used. 
dseSimStep:             #compile and simulate. Original config 
                        #is not used. 
dseSim:                 #dse based on the given set of paeameters 
                        #in dseSim.pl
parDseSim:              #parallel dseSim
verilog: simVerilog     #Generate verilog for simulation. Original 
                        #config is used. 
#fpga: fpgaBaseSynthesis timing power
fpga: fpgaBaseSynthesis #synthesis the verilog on a Xilinx 
                        #FPGA. Configurations 
                        #in synthesis/fpga directory.
asic: asicBaseSynthesis #synthesis the verilog using synopsys 
                        #design compiler. Configurations in 
                        #synthesis/asic directory.
dseVerilogStep:         #Generate verilog. Original config is not 
                        #used.
dseSynStep:             #Generate verilog and synthesis it. Original 
                        #config is not used.
dseSyn:                 #dse based on the given set of parameters in 
                        #dseSyn.pl
parDseSyn:              #parallel dseSyn
r1:                     #Use this only after compile. It does one 
                        #refinement based on Top file in SSF directory 
                        #and PCResults.out file 
rs:                     #refine and simulate 
simVerilog:             #Generate verilog for simulations. 
clean:                  #Remove all scala files, simulation and synthesis 
                        #outputs
#################################### END OF USABLE TARGETS ########################
synVerilog: originalConfigVerilog
mvExp:
	for i in ../*Exp*; do mv $$i/dse/design* dse; done

simulate:originalConfigCompileSimulate
originalConfigCompileSimulate: synSim=sim
originalConfigCompileSimulate: pcEnable=true
originalConfigCompileSimulate: baseAndTopConfig chiselSimulate

dseSimStep: synSim=sim
dseSimStep: pcEnable=true
dseSimStep: base chiselSimulate

originalConfigVerilog: synSim=syn
originalConfigVerilog: pcEnable=false 
originalConfigVerilog: baseAndTopConfig  verilogBackend

dseVerilogStep: synSim=syn
dseVerilogStep: pcEnable=false 
dseVerilogStep: base verilogBackend

simVerilog: synSim=sim
simVerilog: pcEnable=false 
simVerilog: baseAndTopConfig verilogBackend

verilogBackend:
	cd ../../../chisel/Gorilla++/verilog; make
	rm verilog/Top.v; cp ../../../chisel/Gorilla++/verilog/Top.v verilog

#Use this only after compile. It does one refinement based 
#on Top file in SSF directory and PCResults.out file 
r1: SSFFromSourceOrRefine=source
r1: cpOriginalTopConfig ./SSF/Top.scala refineOneIteration


rs: refineAndSimulate



clean:
	rm -rf *.scala; rm -rf version; rm -rf enginesMD; rm -rf PCREPORT.out; rm -rf refine.out; rm -rf TopConfig; rm -rf verilog/*.v; rm -rf synthesis/fpga/GorilaaPP.ngc synthesis/fpga/GorillaPP.srp

fpgaAll: fpgaParSynthesis fpgaPower fpgaTiming

dseSim:
	./dseSim.pl 1

dseSyn:
	./dseSyn.pl 1

parDseSyn:
	for i in ../*Exp*; do cd $$i; make dseSyn & bg; done
##################################################################################
compositions=$(wildcard ../src/*.scala)
libGeneratedCompositions=$(patsubst ../src/%.scala, ./%LibGenerated.scala, $(compositions))
configedCompositions=$(patsubst ../src/%.scala, ./%Configed.scala, $(compositions))
SSFCompositions=$(patsubst ../src/%.scala, ./SSF/%.scala, $(compositions))
generatedCompositions=$(patsubst ../src/%.scala, ./%.scala, $(compositions))

#SSFFromSource: $(SSFCompositions) 
./SSF/%.scala: ../src/%.scala $(wildcard ../src/engines/*.c)
	$(topCompilerDir)/generateSSF.pl $* $(SSFFromSourceOrRefine)


chiselSimulate:
	cd ../../../chisel/Gorilla++/emulator; make
	egrep 'PCREPORT|hierarchy' ../../../chisel/Gorilla++/emulator/Top.out > PCREPORT.out

refine:
	$(refinerDir)/refiner.pl

refineAndSimulate: refineOneIteration simulateRefined

simulateRefined: pcEnable=true
simulateRefined: SSFFromSourceOrRefine=refine
simulateRefined: synSim=sim
#simulateRefined: SSFromRefined configTop libGenTop generateTop PrepareChiselCompile simulate
simulateRefined: cpRefinedTopConfig baseBackend chiselSimulate

PWD := $(shell pwd)	


cpTopSSFfromRefineInput:
	cp refine/input/TopSSF.scala SSF
	echo "0" > refine/backTrack

refineOneIteration:
	mv SSF/TopSSF.scala refine/input
	cp TopConfig refine/input	
	cp refine/input/TopConfig refine/output/TopConfig
	cd refine; $(SBT) "run $(PWD)" 

baseAndTopConfig: cpOriginalTopConfig base 

base : SSFFromSourceOrRefine=source 
base: baseBackend

baseBackend: compilerConfig SSFTop configTop libGenTop generateTop PrepareChiselCompile

SSFTop: 

libGenTop: $(libGeneratedCompositions)

./%LibGenerated.scala: ./%Configed.scala
	$(topCompilerDir)/libGen.pl $* $(synSim) > $*LibGenerated.scala 
	rm -f $< 

configTop: $(configedCompositions)

./%Configed.scala: ./SSF/%.scala 
	$(topCompilerDir)/configTop.pl $* > $*Configed.scala



generateTop: $(generatedCompositions)

./%.scala: ./%LibGenerated.scala
	$(topCompilerDir)/generateTop.pl $* > $@ 
	rm -f $< 

PrepareChiselCompile:	
	cp ../../include/*.scala .
	cat ../src/test/TopTest.scala >> Top.scala
	cd ../../../chisel/Gorilla++/emulator; make rm
	cp *.scala ../../../chisel/Gorilla++/src/ 
	cp ../../lib/*.scala ../../../chisel/Gorilla++/src/

cpOriginalTopConfig:
	cp TopConfig.orig TopConfig

cpRefinedTopConfig:
	cp refine/output/TopConfig .

backTrack:
	cp refine/input/TopSSF.scala SSF/
	cp refine/input/TopConfig .

compilerConfig:
	sed 's/.*pcEnable.*/  val pcEnable = $(pcEnable)/g' < compilerControl.scala.orig >  compilerControl.scala

.PHONY: base baseBackend refineOneIteration refine simulateRefined configTop PrepareChiselCompile cpTopSSFfromRefineInput r1 SSFFromSource cpRefinedTopConfig  generateTop libGenTop compilerConfig dse



SYN_SRCS=verilog/Top.v 
fpgaPower:fpgaParSynthesis	
	cd synthesis/fpga; xpwr GorillaPP_post_PAR.ncd GorillaPP.pcf 	
#	xpwr -s snthesis/GorillaPP_top.vcd synthesis/fpga/GorillaPP_post_PAR.ncd synthesis/fpga/GorillaPP.pcf 	
fpgaTiming:	fpgaParSynthesis
	cd synthesis/fpga; trce -v -o GorillaPP_post_PAR.twr GorillaPP_post_PAR.ncd GorillaPP.pcf	
fpgaParSynthesis:	synthesis/fpga/GorillaPP_post_PAR.ncd 
dseSynStep: dseVerilogStep synthesis/fpga/GorillaPP.ngc
fpgaBaseSynthesis:	synVerilog synthesis/fpga/GorillaPP.ngc 
asicBaseSynthesis: simVerilog
	cd synthesis/asic; dc_shell -f GorillaPP.scr
synthesis/fpga/GorillaPP_post_PAR.ncd:	synthesis/fpga/GorillaPP.ncd
	cd synthesis/fpga; par  -w GorillaPP.ncd GorillaPP_post_PAR
synthesis/fpga/GorillaPP.ncd:	synthesis/fpga/GorillaPP.ngd
	cd synthesis/fpga; map -w GorillaPP.ngd
synthesis/fpga/GorillaPP.ngd: synthesis/fpga/GorillaPP.ngc
	cd synthesis/fpga; ngdbuild -dd synthesis -uc GorillaPP.ucf GorillaPP.ngc
synthesis/fpga/GorillaPP.ngc: synthesis/fpga/GorillaPP.scr $(SYN_SRCS)
	cd synthesis/fpga; xst -ifn GorillaPP.scr -ofn GorillaPP.srp 
synClean:
	rm -rf synthesis/fpga/GorillaPP.ngc synthesis/fpga/GorillaPP.ngd synthesis/fpga/GorillaPP.ncd synthesis/fpga/GorillaPP_post_PAR.ncd
