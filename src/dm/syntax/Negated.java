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
public class Negated implements Formula {
	
	private Formula fml;

	public Negated(Formula fml) {
		this.fml = fml;
	}

	@Override
	public boolean check(QDTModel m, int world) {
		boolean check = !fml.check(m, world);
		return check;
	}

	@Override
	public String toString() {
		return "!" + fml;
	}
	
	@Override
	public int size() {
		return fml.size() + 1;
	}
	
	@Override
	public Set<? extends Term> getTerms() {
		return fml.getTerms();
	}
	
}
