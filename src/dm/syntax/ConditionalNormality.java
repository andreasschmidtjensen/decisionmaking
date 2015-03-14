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
public class ConditionalNormality implements Formula {

	private Formula phi;
	private Formula psi;
	private String toString;

	public ConditionalNormality(Formula phi, Formula psi) {
		this.phi = phi;
		this.psi = psi;
				
		toString = "(" + phi + " => " + psi + ")";
	}

	@Override
	public boolean check(QDTModel m, int world) {
		
		AllNormal a = new AllNormal(new Negated(phi));
		SomeNormal b = new SomeNormal(new And(phi, new AtLeastAsNormal(new Implication(phi, psi))));
		
		Formula fml = new Or(a, b);
		
		return fml.check(m, world);
	}

	@Override
	public String toString() {
		return toString;
	}
	
	@Override
	public int size() {
		return phi.size() + psi.size();
	}
	
	@Override
	public Set<Term> getPropositions() {
		Set<Term> t = new HashSet<>();
		t.addAll(phi.getPropositions());
		t.addAll(psi.getPropositions());
		return t;
	}
	
	@Override
	public boolean contains(Term t) {
		return phi.contains(t) || psi.contains(t);
	}
}
