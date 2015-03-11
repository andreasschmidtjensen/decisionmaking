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
public class ConditionalPreference implements Formula {

	private Formula fml;

	public ConditionalPreference(Formula psi, Formula phi) {
		AllPreferred a = new AllPreferred(new Negated(phi));
		SomePreferred b = new SomePreferred(new And(phi, new AtLeastAsPreferred(new Implication(phi, psi))));
		this.fml = new Or(a, b);
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
	public Set<? extends Term> getTerms() {
		return fml.getTerms();
	}
	
}
