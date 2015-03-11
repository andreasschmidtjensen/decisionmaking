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
public class RelativePreference implements Formula {
	
	private final Formula phi;
	private final Formula psi;
	
	public RelativePreference(Formula phi, Formula psi) {
		this.phi = phi;
		this.psi = psi;
	}

	@Override
	public boolean check(QDTModel m, int world) {
		Implication imp = new Implication(psi, new SomeAtLeastAsPreferred(phi));
		return new AllPreferred(imp).check(m, world);
	}

	@Override
	public String toString() {
		return phi.toString() + " <=(p) " + psi.toString();
	}
	
	@Override
	public int size() {
		return phi.size() + psi.size();
	}

	@Override
	public Set<? extends Term> getTerms() {
		Set<Term> t = new HashSet<>();
		t.addAll(phi.getTerms());
		t.addAll(psi.getTerms());
		return t;
	}
		
}
