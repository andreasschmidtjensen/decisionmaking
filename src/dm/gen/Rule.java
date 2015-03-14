/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dm.gen;

import dm.syntax.Formula;
import dm.syntax.Term;
import dm.syntax.True;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author asj
 */
public class Rule implements Comparable<Rule> {
	
	private Formula antecedent;
	private Formula consequent;

	public Rule(Formula antecedent, Formula consequent) {
		this.antecedent = antecedent;
		this.consequent = consequent;
	}

	public Formula getAntecedent() {
		return antecedent;
	}

	public Formula getConsequent() {
		return consequent;
	}

	@Override
	public String toString() {
		return antecedent + " => " + consequent;
	}

	private int size() {
		if (antecedent instanceof True) {
			return consequent.size() - 1;
		} else {
			return antecedent.size() + consequent.size();
		}
	}
	
	Set<Term> getPropositions() {
		Set<Term> terms = new HashSet<>();
		terms.addAll(antecedent.getPropositions());
		terms.addAll(consequent.getPropositions());
		return terms;
	}
	
	@Override
	public int compareTo(Rule o) {
		Integer a = size();
		Integer b = o.size();
		// largest value first
		return b.compareTo(a);
	}

}
