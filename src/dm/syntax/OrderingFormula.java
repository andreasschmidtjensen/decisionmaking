/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dm.syntax;

import dm.semantics.QDTModel;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author asj
 */
public abstract class OrderingFormula implements Formula {

	private final Formula fml;

	public OrderingFormula(Formula fml) {
		this.fml = fml;
	}
	
	@Override
	public boolean check(QDTModel m, int world) {
		Iterator<Integer> it = getIterator(m, world);
		while (it.hasNext()) {
			int w = it.next();
			if (!fml.check(m, w)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		return fml.toString();
	}

	@Override
	public int size() {
		return fml.size();
	}
	
	@Override
	public Set<? extends Term> getTerms() {
		return fml.getTerms();
	}
	
	protected abstract Iterator<Integer> getIterator(QDTModel m, int world);
	
}
