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
public class SomeAtLeastAsNormal implements Formula {

	private Formula fml;

	public SomeAtLeastAsNormal(Formula fml) {
		this.fml = new Negated(new AtLeastAsNormal(new Negated(fml)));
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
