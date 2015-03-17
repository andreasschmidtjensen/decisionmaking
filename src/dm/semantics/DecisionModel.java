/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dm.semantics;

import dm.syntax.And;
import dm.syntax.ConditionalNormality;
import dm.syntax.Formula;
import dm.syntax.Literal;
import dm.syntax.Or;
import dm.syntax.RelativePreference;
import dm.syntax.RelativeTolerance;
import dm.syntax.Term;
import dm.syntax.True;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author asj
 */
public class DecisionModel {
	
	private QDTModel qdt;
	private Set<Literal> influences;
	private Set<Literal> controllable;
	private Set<Literal> beliefBase;

	public DecisionModel(QDTModel qdt, Set<Literal> influences,
			Set<Term> controllable,
			Set<Literal> beliefBase) {
		this.qdt = qdt;
		this.influences = influences;
		this.controllable = new HashSet<>();
		for (Term l : controllable) {
			this.controllable.add(new Literal(l.getName(), true));
			this.controllable.add(new Literal(l.getName(), false));
		}
		this.beliefBase = beliefBase;
		removeIrrelevantFromBB();
		updateWorlds();
	}
	
	public boolean isEmpty() {
		return qdt.isEmpty();
	}
	
	private void removeIrrelevantFromBB() {
		for (Iterator<Literal> it = beliefBase.iterator(); it.hasNext();) {
			Literal l = it.next();
			if (!qdt.getAtoms().contains(new Term(l.getName()))) {
				it.remove();
			}
		}
	}
	
	private void updateWorlds() {
		qdt.update(beliefBase, controllable);
	}

	public Set<Literal> getBeliefBase() {
		return beliefBase;
	}
	
	public Formula getExpectedConsequences(Formula fml) {
		True TRUE = new True();
		Formula result = TRUE;
		
		Set<Literal> resultSet = new HashSet<>();
		
		for (Literal c : controllable) {
			if (fml.equals(c)) {
				continue;
			}
			
			Set<Literal> bb = new HashSet<>(beliefBase);
			bb.remove(c.negate());

			Formula test = revise(bb, fml);
			ConditionalNormality norm = new ConditionalNormality(test, c);
			boolean normResult = norm.check(qdt, qdt.getWorld());
			
			if (normResult) {
				resultSet.add(c);
				resultSet.remove(c.negate());
			}
		}
		
		for (Literal l : resultSet) {
			if (result == TRUE) {
				result = l;
			} else {
				result = new And(result, l);
			}
		}
		return result;
	}
	
	private Formula revise(Set<Literal> bb, Formula fml) {
		Formula result = fml;
		for (Literal b : bb) {
			if (!fml.contains(b.negate()) && !fml.contains(b)) {
				result = new And(result, b);
			}
		}
		return result;
	}
	
	public Set<Literal> getMostPreferredInfluences() {
		Set<Literal> result = new HashSet<>();
		for (Literal phi : influences) {
			for (Literal psi : influences) {
				if (phi != psi) {
					RelativePreference rPref = new RelativePreference(phi, psi);
					if (rPref.check(qdt, qdt.getWorld())) {
						result.add(phi);
					}
				}
			}
		}
		return result;
	}
	
	public Set<Literal> getMostTolerableConsequences() {
		Set<Literal> result = new HashSet<>();
		for (Literal phi : influences) {
			for (Literal psi : influences) {
				if (phi != psi) {
					Formula ecPhi = getExpectedConsequences(phi);
					Formula ecPsi = getExpectedConsequences(psi);
					RelativeTolerance tol = 
							new RelativeTolerance(ecPhi, ecPsi, new Or(phi, psi));
					if (tol.check(qdt, qdt.getWorld())) {
						result.add(phi);
					}
				}
			}
		}
		return result;
	}
	
	public Set<Literal> getDecision() {		
		Set<Literal> tol = getMostTolerableConsequences();
		Set<Literal> pref = getMostPreferredInfluences();
		Set<Literal> dec = new HashSet<>(tol);
		dec.retainAll(pref);
		if (dec.isEmpty()) {
			dec = tol;
		}
		return dec;
	}

}
