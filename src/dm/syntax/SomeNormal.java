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
public class SomeNormal implements Formula {

	private Formula fml;

	public SomeNormal(Formula fml) {
		this.fml = fml;
	}

	@Override
	public boolean check(QDTModel m, int world) {
		return new Negated(new AllNormal(new Negated(fml))).check(m, world);
	}

	@Override
	public String toString() {
		return "<<>>(n) " + fml.toString();
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
