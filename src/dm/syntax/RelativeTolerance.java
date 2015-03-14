/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dm.syntax;

import dm.semantics.QDTModel;
import java.util.Set;

/**
 *
 * @author asj
 */
public class RelativeTolerance implements Formula {
	
	private Or fml;

	public RelativeTolerance(Formula phi, Formula psi, Formula gamma) {
		ConditionalTolerance t1 = new ConditionalTolerance(phi, gamma);
		ConditionalTolerance t2 = new ConditionalTolerance(psi, gamma);
		Negated n = new Negated(t2);
		And con1 = new And(t1, n);
		
		Biimplication biimp = new Biimplication(t1, t2);
		Or or = new Or(new RelativePreference(phi, psi), new EquallyPreferred(phi, psi));
		
		this.fml = new Or(con1, new And(biimp, or));
	}
	
	@Override
	public boolean check(QDTModel m, int world) {
		return fml.check(m, world);
	}
	
	@Override
	public int size() {
		return fml.size();
	}
	
	@Override
	public Set<Term> getPropositions() {
		return fml.getPropositions();
	}
	
	@Override
	public boolean contains(Term t) {
		return fml.contains(t);
	}
}
