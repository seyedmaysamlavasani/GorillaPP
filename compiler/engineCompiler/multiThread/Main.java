import java.io.*;
import org.antlr.runtime.*;
	
public class Main {
    private static void parse(String fileName) throws Exception {
        GorillaPP_grammarLexer lex = new GorillaPP_grammarLexer(new ANTLRFileStream(fileName));
        CommonTokenStream tokens = new CommonTokenStream(lex);

        GorillaPP_grammarParser g = new GorillaPP_grammarParser(tokens);
	
        try {
            g.translation_unit();
        } catch (RecognitionException e) {
            e.printStackTrace();
        }
    }
	
    public static void main(String args[]) throws Exception {
        for (String fileName: args) {
            System.out.println();
            System.out.println(fileName + ": ");
            System.out.flush();
            parse(fileName);
        }
   }
}
