/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dm;

import dm.gen.RuleProgram;
import dm.parser.DMLexer;
import dm.parser.DMParser;
import dm.syntax.Literal;
import dm.syntax.Term;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

/**
 *
 * @author asj
 */
public class ProgramLoader {
	
	public static RuleProgram load(String file) throws IOException, RecognitionException {
		DMLexer lexer = new DMLexer(new ANTLRFileStream(file));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		DMParser parser = new DMParser(tokens);
		return parser.program();
	}

	public static Set<Literal> parseLiterals(String text) throws RecognitionException {
		DMLexer lexer = new DMLexer(new ANTLRStringStream(text));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		DMParser parser = new DMParser(tokens);
		
		return parser.literalset();
	}
	
	public static Literal parseLiteral(String text) throws RecognitionException {
		DMLexer lexer = new DMLexer(new ANTLRStringStream(text));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		DMParser parser = new DMParser(tokens);
		
		return parser.literal();
	}
	
	public static Set<Term> parseTerms(String text) throws RecognitionException {
		Set<Literal> literals = parseLiterals(text);
		
		Set<Term> terms = new HashSet<>();
		for (Literal l : literals) {
			terms.add(new Term(l.getName()));
		}
		
		return terms;
	}
}
