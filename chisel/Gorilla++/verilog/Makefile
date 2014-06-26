basedir = ..
include ../Makefrag
SBTARGS = --backend v --targetDir ../verilog/ --genHarness

prj_v = $(addsuffix .v, $(prjs))

all: $(prj_v)

$(prj_v): %.v: ../src/%.scala
	cd $(basedir)/sbt; $(SBT) "project KmeansAndMesh" "run $* $(SBTARGS)"


vcs:
	vcs -debug +v2k  Top.v Top-harness-augmented-incThroughOffload.v
#	vcs -debug +v2k  TopOneThread.v Top-harness-augmented-k-means.v
#	vcs -debug +v2k  TopTwoThreads.v Top-harness-augmented-k-means.v
