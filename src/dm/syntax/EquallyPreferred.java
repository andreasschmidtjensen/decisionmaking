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
public class EquallyPreferred implements Formula {

	private Formula fml;
	
	public EquallyPreferred(Formula phi, Formula psi) {
		And con1 = new And(new RelativePreference(phi, psi), new RelativePreference(psi, phi));
		And con2 = new And(new NotAsPreferred(phi, psi), new NotAsPreferred(psi, phi));
		this.fml = new Or(con1, con2);
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
