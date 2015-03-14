/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dm.example;

import dm.ProgramLoader;
import dm.gen.ModelGenerator;
import dm.gen.Rule;
import dm.gen.RuleProgram;
import dm.semantics.DecisionModel;
import dm.semantics.QDTModel;
import dm.syntax.And;
import dm.syntax.Literal;
import dm.syntax.Term;
import dm.syntax.True;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author asj
 */
public class Snow {
	
	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) throws Exception {
		Literal work = new Literal("w", true);
		Literal negWork = new Literal("w", false);
		Set<Literal> influences = asSet(negWork, work);
		
		RuleProgram program = ProgramLoader.load("src/dm/example/snow.dm"); //new RuleProgram(pRules, nRules, impossible);
				
		System.out.println("Influences: " + influences);
		System.out.println("Impossible: " + program.getImpossible());
		System.out.println("P-Rules: " + program.getPreferenceRules());
		System.out.println("N-Rules: " + program.getNormalityRules());

		ModelGenerator mg = new ModelGenerator(program);
		Set<Term> controllables
				= asSet(new Term("f"), new Term("w"), new Term("e"));
		QDTModel qdt = mg.generate(influences, controllables);

		System.out.println("Initial worlds: " + qdt.getWorlds());
		System.out.println("Initial P:\n" + qdt.getPreferenceOrdering());
		System.out.println("Initial N:\n" + qdt.getNormalityOrdering());
				
		DecisionModel m = new DecisionModel(qdt, influences, controllables, 
				asSet(new Literal("s", false)));
		
		System.out.println("BB: " + m.getBeliefBase());
		System.out.println("Worlds: " + qdt.getWorlds());
		System.out.println("P: " + qdt.getPreferenceOrdering());
		System.out.println("N: " + qdt.getNormalityOrdering());
		
		Set<Literal> tol = m.getMostTolerableConsequences();
		Set<Literal> pref = m.getMostPreferredInfluences();
		Set<Literal> dec = new HashSet<>(tol);
		dec.retainAll(pref);
		if (dec.isEmpty()) {
			dec = tol;
		}
		
		System.out.println("Pref=" + pref);
		System.out.println("Tol=" + tol);
		System.out.println("Dec=" + dec);
	}

	private static Set<Term> asSet(Term... terms) {
		Set<Term> s = new HashSet<>();
		for (Term t : terms) {
			s.add(t);
		}
		return s;
	}

	private static Set<Integer> asSet(int... is) {
		Set<Integer> s = new HashSet<>();
		for (int i : is) {
			s.add(i);
		}
		return s;
	}

	private static Set<Literal> asSet(Literal... literals) {
		Set<Literal> s = new HashSet<>();
		for (Literal l : literals) {
			s.add(l);
		}
		return s;
	}

}
