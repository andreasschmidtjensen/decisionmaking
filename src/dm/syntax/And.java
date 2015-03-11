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
public class And implements Formula {

	private Formula f1;
	private Formula f2;

	public And(Formula f1, Formula f2) {
		this.f1 = f1;
		this.f2 = f2;
	}
	
	@Override
	public boolean check(QDTModel m, int world) {
		return f1.check(m, world) && f2.check(m, world);
	}

	@Override
	public String toString() {
		return "(" + f1 + " && " + f2 + ")";
	}
	
	@Override
	public int size() {
		return f1.size() + f2.size() + 1;
	}
	
	@Override
	public Set<? extends Term> getTerms() {
		Set<Term> t = new HashSet<>();
		t.addAll(f1.getTerms());
		t.addAll(f2.getTerms());
		return t;
	}
	
}
