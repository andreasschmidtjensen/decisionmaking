/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dm.syntax;

import dm.semantics.QDTModel;
import dm.syntax.Formula;
import dm.syntax.LessPreferred;
import dm.syntax.Negated;
import java.util.Set;

/**
 *
 * @author asj
 */
public class SomeMorePreferred implements Formula {

	private Formula fml;

	public SomeMorePreferred(Formula fml) {
		this.fml = new Negated(new LessPreferred(new Negated(fml)));
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
