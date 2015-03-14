/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dm.syntax;

import dm.semantics.QDTModel;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author asj
 */
public class Term implements Formula {
	
	private String name;

	public Term(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	@Override
	public boolean check(QDTModel m, int world) {
		return m.evaluate(world, this);
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 79 * hash + Objects.hashCode(this.name);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Term other = (Term) obj;
		if (!Objects.equals(this.name, other.name)) {
			return false;
		}
		return true;
	}
	
	@Override
	public int size() {
		return 1;
	}
	
	@Override
	public Set<Term> getPropositions() {
		Set<Term> result = new HashSet<>();
		result.add(this);
		return result;
	}
	
	@Override
	public boolean contains(Term t) {
		return equals(t);
	}
}
