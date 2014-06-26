#Module=updateWriter
#Module=pageRank
#Module=KEngine
#Module=qdr
#Module=mem
#Module=rankCalculator
#Module=ipv4Lookup
#Module=ipv4Engine
#Module=lookup
##Module=incThroughOffload
#Module=sendConst
#Module=updateWriter
Module=adder
Source=./$(Module).c

LexerParserJavas= GorillaPP_grammarParser.java GorillaPP_grammarLexer.java 
LexerParserMainJavas= $(LexerParserJavas)  Main.java
LexerParserFiles= $(LexerParserMainJavas) GorillaPP_grammar.tokens 
LexerParser_fpJavas= GorillaPP_grammar_fpParser.java GorillaPP_grammar_fpLexer.java 
LexerParser_fpMainJavas= $(LexerParser_fpJavas)  Main_fp.java
LexerParser_fpFiles= $(LexerParser_fpMainJavas) GorillaPP_grammar_fp.tokens 

all: generate_fp generate 
	grep INPUT $(Source) > /dev/null && ./thread_engine_gen.pl $(Module) 1 | tee $(Module).scala 
	grep INPUT $(Source) > /dev/null || cat definition.magillac > $(Module).scala
	 
generate: $(LexerParserFiles) 
	java -cp .:antlr-3.4-complete.jar Main $(Source) 2>&1 #> /dev/null

$(LexerParserFiles): GorillaPP_grammar.g
	java -jar ./antlr-3.4-complete.jar GorillaPP_grammar.g 
	javac -classpath ./antlr-3.4-complete.jar -Xlint:unchecked $(LexerParserMainJavas)

generate_fp: $(LexerParser_fpFiles) 
	cp $(Source) ../firstPass
	cd ../firstPass/; java -cp .:antlr-3.4-complete.jar Main_fp $(Source) 2>&1 #> /dev/null
	cp ../firstPass/instr.magillac instr_fp.magillac

$(LexerParser_fpFiles): GorillaPP_grammar_fp.g
	java -jar ./antlr-3.4-complete.jar GorillaPP_grammar_fp.g 
	javac -classpath ./antlr-3.4-complete.jar -Xlint:unchecked $(LexerParser_fpMainJavas) 

clean:
	#rm -rf *.class; rm -rf $(LexerParserFiles); rm -rf *.magillac; rm -f *.scala
	rm -rf *.class; rm -rf $(LexerParserJavas); rm -rf $(LexerParser_fpJavas); rm -rf *.magillac; rm -f *.scala

.PHONY: clean generate_fp generate 
