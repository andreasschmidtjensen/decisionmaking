/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dm.syntax;

import dm.semantics.QDTModel;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author asj
 */
public class Literal extends Term {

	private boolean negated;

	public Literal(String name) {
		this(name, true);
	}
	
	public Literal(String name, boolean positive) {
		super(name);
		this.negated = !positive;
	}

	public boolean isNegated() {
		return negated;
	}
	
	public Literal negate() {
		return new Literal(getName(), negated);
	}
	
	@Override
	public boolean check(QDTModel m, int world) {
		boolean result = new Term(getName()).check(m, world);
		return negated ? !result : result;
	}

	@Override
	public String toString() {
		return (negated ? "~" : "") + super.toString();
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 53 * hash + (this.negated ? 1 : 0);
		return hash + super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Literal other = (Literal) obj;
		if (this.negated != other.negated) {
			return false;
		}
		return super.equals(obj);
	}

	@Override
	public int size() {
		return (negated ? 1 : 0) + super.size(); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Set<Term> getPropositions() {
		Set<Term> result = new HashSet<>();
		result.add(new Term(getName()));
		return result;
	}
	
	@Override
	public boolean contains(Term t) {
		return equals(t);
	}
	
}
