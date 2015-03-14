/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dm.syntax;

import dm.semantics.QDTModel;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author asj
 */
public class Implication implements Formula {

	private Formula fml1;
	private Formula fml2;

	public Implication(Formula fml1, Formula fml2) {
		this.fml1 = fml1;
		this.fml2 = fml2;
	}
	
	
	@Override
	public boolean check(QDTModel m, int world) {
		return !fml1.check(m, world) || fml2.check(m, world);
	}

	@Override
	public String toString() {
		return "(" + fml1 + " -> " + fml2 + ")";
	}
	
	@Override
	public int size() {
		return fml1.size() + fml2.size() + 1;
	}
	
	@Override
	public Set<Term> getPropositions() {
		Set<Term> t = new HashSet<>();
		t.addAll(fml1.getPropositions());
		t.addAll(fml2.getPropositions());
		return t;
	}
	
	@Override
	public boolean contains(Term t) {
		return fml1.contains(t) || fml2.contains(t);
	}
}
