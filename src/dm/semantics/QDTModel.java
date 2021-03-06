/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dm.semantics;

import dm.syntax.Literal;
import dm.syntax.Term;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @author asj
 */
public class QDTModel {

	private Map<Integer, Set<Term>> worlds;
	private Ordering preferenceOrdering;
	private Ordering normalityOrdering;
	private Set<Term> atoms;

	public QDTModel(Map<Integer, Set<Term>> worlds, Ordering preferenceOrdering,
			Ordering normalityOrdering, Set<Term> atoms) {
		this.worlds = worlds;
		this.preferenceOrdering = preferenceOrdering;
		this.normalityOrdering = normalityOrdering;
		this.atoms = atoms;
	}

	public Map<Integer, Set<Term>> getWorlds() {
		return worlds;
	}

	public Set<Term> getAtoms() {
		return atoms;
	}
	
	public int getWorld() {
		return worlds.keySet().toArray(new Integer[0])[0];
	}

	void update(Set<Literal> beliefBase, Set<Literal> controllable) {
		for (Iterator<Entry<Integer, Set<Term>>> it
				= worlds.entrySet().iterator(); it.hasNext();) {
			Entry<Integer, Set<Term>> entry = it.next();
			int w = entry.getKey();
			for (Literal l : beliefBase) {
				// only remove worlds that dont match BB if proposition is not controllable, 
				// since otherwise an action could change the proposition
				if (!controllable.contains(l)) {
					if (!l.check(this, w)) {
						preferenceOrdering.remove(w);
						normalityOrdering.remove(w);

						it.remove();
						break;
					}
				}
			}
		}
	}

	private String printOrdering(Ordering o) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < o.groups.size(); i++) {
			Ordering.Group g = o.groups.get(i);
			sb.append("\t").append(i).append(": ");
			for (int w : g.worlds) {
				sb.append(worlds.get(w)).append(" ");
			}
			sb.append("\n");
		}
		
		return sb.toString();
	}
	
	public String printPreferenceOrdering() {
		return printOrdering(preferenceOrdering);
	}

	public String printNormalityOrdering() {
		return printOrdering(normalityOrdering);
	}

	
	public Ordering getPreferenceOrdering() {
		return preferenceOrdering;
	}

	public Ordering getNormalityOrdering() {
		return normalityOrdering;
	}

	public boolean evaluate(int world, Term term) {
		return worlds.get(world).contains(term);
	}

	public boolean isEmpty() {
		return worlds.isEmpty() || (worlds.size() == 1 && worlds.get(getWorld()).isEmpty());
	}

}
