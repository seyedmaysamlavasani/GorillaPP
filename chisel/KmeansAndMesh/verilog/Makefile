basedir = ..
include ../Makefrag
SBTARGS = --backend v --targetDir ../verilog --genHarness

tut_v = $(addsuffix .v, $(prjs))

all: $(tut_v)

$(tut_v): %.v: ../src/%.scala
	cd $(basedir)/sbt; $(SBT) "project KmeansAndMesh" "run $* $(SBTARGS)"

clean: 
	rm -f *.out *.o *-harness.* $(tutorials) $(addsuffix .v, $(tutorials)) *.conf

v:
	vcs +v2k *.v
