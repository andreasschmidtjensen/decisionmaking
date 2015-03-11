/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dm.gen;

import dm.semantics.Ordering;
import dm.semantics.QDTModel;
import dm.syntax.And;
import dm.syntax.Formula;
import dm.syntax.Literal;
import dm.syntax.Negated;
import dm.syntax.Term;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 *
 * @author asj
 */
public class ModelGenerator {
		
	private final RuleProgram program;
	
	/**
	 * Locked worlds (we dont care about what world caused the lock)
	 */
	private Set<Integer> lock;
	
	public ModelGenerator(RuleProgram program) {
		this.program = program;
	}
		
	public QDTModel generate(Set<Literal> influences) {
		List<Rule> rules = new ArrayList<>();
		rules.addAll(program.getNormalityRules());
		rules.addAll(program.getPreferenceRules());
		
		Set<Term> atoms = retrieveAtoms(influences, rules);
		Map<Integer, Set<Term>> worlds = init(atoms, program.getImpossible());
		
		return new QDTModel(worlds, 
							generate(worlds, program.getPreferenceRules()), 
							generate(worlds, program.getNormalityRules()));
	}
	
	private Ordering generate(Map<Integer, Set<Term>> worlds, List<Rule> rules) {
		lock = new HashSet<>();
		
		Ordering ordering = new Ordering(worlds.keySet());
		
		rules = new ArrayList<>(rules);
		Collections.sort(rules);
		
		for (Rule r : rules) {
			if (!apply(r, worlds, ordering)) {
				System.err.println("Could not apply " + r);
			}
		}
		
		return ordering;
	}

	private Set<Term> retrieveAtoms(Set<Literal> influences, List<Rule> rules) {
		Set<Term> atoms = new HashSet<>();
		for (Literal l : influences) {
			atoms.add(new Term(l.getName()));
		}
		Set<Term> checked = new HashSet<>();
		Queue<Term> pending = new LinkedList<>(atoms);
		while (!pending.isEmpty()) {
			Term a = pending.poll();
			if (checked.contains(a)) {
				continue;
			}
			
			for (Rule r : rules) {
				Set<Term> termsInRule = r.getTerms();
				if (termsInRule.contains(a)) {
					atoms.addAll(termsInRule);
					pending.addAll(termsInRule);
				}
			}
			
			checked.add(a);
		}
		
		return atoms;
	}

	private Map<Integer, Set<Term>> init(Set<Term> atoms, List<Set<Literal>> impossible) {
		Map<Integer, Set<Term>> result = new HashMap<>();
		int w = 1;
		Set<Set<Term>> powerset = powerset(atoms);
		boolean containsAll = !impossible.isEmpty();
		for (Set<Term> set : powerset) {
			for (Set<Literal> i : impossible) {
				containsAll = true;
				for (Literal l : i) {
					if (l.isNegated()) {
						if (set.contains(new Term(l.getName()))) {
							containsAll = false;
							break;
						}
					} else if (!set.contains(new Term(l.getName()))) {
						containsAll = false;
						break;
					}
				}
				if (containsAll) {
					break;
				}
			}
			if (!containsAll) {
				result.put(w++, set);
			}
		}
		return result;
	}
	
	private static <T> Set<Set<T>> powerset(Set<T> originalSet) {
		Set<Set<T>> sets = new HashSet<>();
		if (originalSet.isEmpty()) {
			sets.add(new HashSet<T>());
			return sets;
		}
		List<T> list = new ArrayList<>(originalSet);
		T head = list.get(0);
		Set<T> rest = new HashSet<>(list.subList(1, list.size()));
		for (Set<T> set : powerset(rest)) {
			Set<T> newSet = new HashSet<>();
			newSet.add(head);
			newSet.addAll(set);
			sets.add(newSet);
			sets.add(set);
		}
		return sets;
	}

	private boolean apply(Rule r, Map<Integer, Set<Term>> worlds, Ordering ordering) {
		QDTModel qdt = new QDTModel(worlds, ordering, ordering);
		
		Set<Integer> match = new HashSet<>();
		Set<Integer> contradicts = new HashSet<>();
		for (int w : worlds.keySet()) {
			Set<Term> propositions = worlds.get(w);
			
			Formula c = new And(r.getAntecedent(), new Negated(r.getConsequent()));
			Formula m = new And(r.getAntecedent(), r.getConsequent()); 
			
			if (c.check(qdt, w)) {
				contradicts.add(w);
			} else if (m.check(qdt, w)) {
				if (!lock.contains(w)) {
					match.add(w);
				}
			}
		}
		
		if (match.isEmpty()) {
			return false;
		} else {
			ordering.moveToTop(match);
			for (int c : contradicts) {
				lock.add(c);
			}
			
			return true;
		}
		
	}

	
}
