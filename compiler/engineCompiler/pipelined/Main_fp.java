import java.io.*;
import org.antlr.runtime.*;
	
public class Main_fp {
    private static void parse(String fileName) throws Exception {
        GorillaPP_grammar_fpLexer lex = new GorillaPP_grammar_fpLexer(new ANTLRFileStream(fileName));
        CommonTokenStream tokens = new CommonTokenStream(lex);

        GorillaPP_grammar_fpParser g = new GorillaPP_grammar_fpParser(tokens);
	
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
