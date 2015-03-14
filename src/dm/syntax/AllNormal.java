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
public class AllNormal implements Formula {

	private Formula fml;

	public AllNormal(Formula fml) {
		this.fml = fml;
	}
	
	@Override
	public boolean check(QDTModel m, int world) {
		
		AtLeastAsNormal atLeast = new AtLeastAsNormal(fml);
		LessNormal more = new LessNormal(fml);
		
		return new And(atLeast, more).check(m, world);
	}

	@Override
	public String toString() {
		return "[<>](n) " + fml.toString();
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
