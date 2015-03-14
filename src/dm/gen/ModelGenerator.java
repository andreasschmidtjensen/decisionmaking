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
import java.util.stream.Collectors;

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
	
	/**
	 * The relevant propositions given the influences
	 */
	private Set<Term> atoms;
	
	/**
	 * Checked propositions (for traversing through the rules to find relevant propositions)
	 */
	private Set<Term> checked;
	/**
	 * Map from proposition to the rules it is used in
	 */
	private Map<Term, Set<Rule>> map;
		
	public ModelGenerator(RuleProgram program) {
		this.program = program;
	}
		
	public QDTModel generate(Set<Literal> influences, Set<Term> controllables) throws DMException {
		List<Rule> rules = new ArrayList<>();
		rules.addAll(program.getNormalityRules());
		rules.addAll(program.getPreferenceRules());
		
		atoms = retrieveAtoms(influences, controllables, rules);
		Map<Integer, Set<Term>> worlds = init(program.getImpossible());
		
		return new QDTModel(worlds, 
							generate(worlds, filter(program.getPreferenceRules())), 
							generate(worlds, filter(program.getNormalityRules())),
							atoms);
	}
	
	private Ordering generate(Map<Integer, Set<Term>> worlds, List<Rule> rules) throws DMException {
		lock = new HashSet<>();
		
		Ordering ordering = new Ordering(worlds.keySet());
		
		rules = new ArrayList<>(rules);
		Collections.sort(rules);
		
		for (Rule r : rules) {
			if (!apply(r, worlds, ordering)) {
				throw new DMException("Could not apply " + r);
			}
		}
		
		return ordering;
	}

	private Set<Term> retrieveAtoms(Set<Literal> influences, Set<Term> controllables, List<Rule> rules) {
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
			
			for (Rule rule : rules) {
				// ignore rule if none of the consequents are controllable by the agent 
				// (i.e. the agent's actions cannot lead to this and the rule is thus irrelevant)
				Set<Term> consequent = new HashSet<>(rule.getConsequent().getPropositions());
				consequent.retainAll(controllables);
				if (consequent.isEmpty()) {
					continue;
				}
				Set<Term> termsInRule = rule.getPropositions();
				if (termsInRule.contains(a)) {
					atoms.addAll(termsInRule);
					pending.addAll(termsInRule);
				}
			}
			
			checked.add(a);
		}
		
		return atoms;
	}

	private boolean reaches(Term test, Set<Term> influences, Set<Term> controllables) {
		if (!map.containsKey(test)) {
			return false;
		}
		
		for (Rule rule : map.get(test)) {
			if (rule.getPropositions().stream().anyMatch((Term t) ->
					influences.contains(t))) {
				return true;
			}
		}	
		for (Rule rule : map.get(test)) {
			// ignore rule if none of the consequents are controllable by the agent 
			// (i.e. the agent's actions cannot lead to this and the rule is thus irrelevant)
			Set<Term> consequent = new HashSet<>(rule.getConsequent().getPropositions());
			consequent.retainAll(controllables);
			if (consequent.isEmpty()) {
				continue;
			}
			checked.add(test);
			for (Term t : rule.getPropositions()) {
				if (!checked.contains(t) && reaches(t, influences, controllables)) {
					return true;
				}
			}
		}
		return false;
	}
	
	private Map<Integer, Set<Term>> init(List<Set<Literal>> impossible) {
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

	private List<Rule> filter(List<Rule> rules) {
		return rules.stream().filter((Rule r) -> r.getPropositions().stream().allMatch(atoms::contains)).collect(Collectors.toList());	
	}
	
	private boolean apply(Rule r, Map<Integer, Set<Term>> worlds, Ordering ordering) {
		QDTModel qdt = new QDTModel(worlds, ordering, ordering, atoms);
		
		Set<Integer> match = new HashSet<>();
		Set<Integer> contradicts = new HashSet<>();
		for (int w : worlds.keySet()) {
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
