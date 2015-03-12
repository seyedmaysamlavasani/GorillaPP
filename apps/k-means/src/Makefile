generate: ../src/Top.scala $(wildcard ../src/engines/*.c)
	../../../compiler/topCompiler/generateTop.pl > Top.scala
	cat ../src/TopTest.scala >> Top.scala
	cp *.scala ../../../chisel/Gorilla++/src/ 
clean:
	rm *.scala
