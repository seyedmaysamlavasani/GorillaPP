#Module=updateWriter
#Module=pageRank
#Module=qosCount
#Module=header
#Module=KEngine
#Module=qdr
#Module=ipv4Engine
#Module=lookup
#Module=lookupheader
#Module=incThroughOffload
#Module=updateWriter
#Module=sendConst
#Module=cache
#Module=multiProtocolEngine
#Module=config
Module=adder
#Module=pageRank
Source=./$(Module).c
#Source=./$(Module).h

LexerParserFiles= GorillaPP_grammarParser.java GorillaPP_grammarLexer.java GorillaPP_grammar.tokens 

generate: $(LexerParserFiles) 
	java -cp .:antlr-3.4-complete.jar Main $(Source) 2>&1 #> /dev/null
	grep INPUT $(Source) > /dev/null && ./thread_engine_gen.pl $(Module) 1 | tee $(Module).scala 
	grep INPUT $(Source) > /dev/null || cat definition.magillac > $(Module).scala
$(LexerParserFiles): GorillaPP_grammar.g
	java -jar ./antlr-3.4-complete.jar GorillaPP_grammar.g 
	javac -classpath ./antlr-3.4-complete.jar -Xlint *.java

cleanAll:
	rm -rf *.class; rm -rf $(LexerParserFiles); rm -rf *.magillac; rm -f *.scala; rm -f *.c; rm -f *.h

clean:
	rm -rf *.class; rm -rf $(LexerParserFiles); rm -rf *.magillac; rm -f *.scala
