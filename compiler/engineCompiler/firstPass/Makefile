#Module=updateWriter
#Module=pageRank
#Module=KEngine
#Module=qdr
#Module=mem
#Module=rankCalculator
#Module=cache
#Module=ipv4Engine
#Module=ipv4Lookup
Module=adder
##Module=incThroughOffload
#Module=sendConst
#Module=updateWriter
#Module=multiProtocolEngine
Source=./$(Module).c

LexerParser_fpJavas= GorillaPP_grammar_fpParser.java GorillaPP_grammar_fpLexer.java
LexerParser_fpMainJavas= $(LexerParser_fpJavas) \
Main_fp.java Variable.java Variables.java CodeGen.java FpCodeGen.java
LexerParser_fpFiles= $(LexerParser_fpMainJavas) GorillaPP_grammar_fp.tokens 

all: generate_fp 
	 
generate_fp: $(LexerParser_fpFiles) 
	java -cp .:antlr-3.4-complete.jar Main_fp $(Source) 2>&1 #> /dev/null
	cp instr.magillac instrFp.magillac

$(LexerParser_fpFiles): GorillaPP_grammar_fp.g
	java -jar ./antlr-3.4-complete.jar GorillaPP_grammar_fp.g 
	javac -classpath ./antlr-3.4-complete.jar -Xlint:unchecked $(LexerParser_fpMainJavas) 

clean:
	rm -rf *.class; rm -rf $(LexerParser_fpJavas); 
	rm -rf *.magillac; rm -f *.scala; 
	rm -f *.tokens

cleanAll:
	rm -rf *.class; rm -rf $(LexerParser_fpJavas); 
	rm -rf *.magillac; rm -f *.scala; 
	rm -f *.c; rm -f *.h; rm -f *.tokens



.PHONY: clean generate_fp generate 
