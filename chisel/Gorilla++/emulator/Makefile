basedir = ..
include $(basedir)/Makefrag
SBTARGS = --backend c --vcd --compile --test --targetDir ../emulator --genHarness
#SBTARGS = --backend v --compile --test --targetDir ../emulator --genHarness

prj_out = $(addsuffix .out, $(prjs))


all: $(prj_out)
	@echo; perl -ne 'print "  [$$1] $$ARGV \t$$2\n" if /(PASSED|FAILED)/' $(prj_out); echo

rm: 
	rm -f ../src/*
	cp ../Gorilla++/* ../src/
 

$(prjs): %: $(basedir)/src/%.scala
	cd $(basedir)/sbt; $(SBT) "project KmeansAndMesh" "run $@ $(SBTARGS)"

$(prj_out): %.out: $(basedir)/src/%.scala
	cd $(basedir)/sbt; $(SBT) "project KmeansAndMesh" "run $* $(SBTARGS)" | tee $(basedir)/emulator/$@

clean: 
	rm -f *.out *.o emulator.h *-emulator.* $(prjs) $(addsuffix .h, $(prjs)) $(addsuffix .cpp, $(prjs))

.PHONY: all clean
