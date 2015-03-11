/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dm.syntax;

import dm.semantics.QDTModel;
import java.util.Iterator;

/**
 *
 * @author asj
 */
public class LessNormal extends OrderingFormula {

	public LessNormal(Formula fml) {
		super(fml);
	}

	@Override
	protected Iterator<Integer> getIterator(QDTModel m, int world) {
		return m.getNormalityOrdering().getWorse(world);
	}
	
	@Override
	public String toString() {
		return "[<](n) " + super.toString();
	}
	
}
