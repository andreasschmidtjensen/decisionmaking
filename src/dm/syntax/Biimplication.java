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
public class Biimplication implements Formula {
	
	private And fml;

	public Biimplication(Formula fml1, Formula fml2) {
		this.fml = new And(new Implication(fml1, fml2), new Implication(fml2, fml1));
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
