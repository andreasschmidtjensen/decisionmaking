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
public class True implements Formula {

	@Override
	public boolean check(QDTModel m, int world) {
		return true;
	}

	@Override
	public String toString() {
		return "true";
	}

	@Override
	public int hashCode() {
		return toString().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof True;
	}
	
	@Override
	public int size() {
		return 0;
	}
	
	@Override
	public Set<Term> getPropositions() {
		return new HashSet<>();
	}
	
	@Override
	public boolean contains(Term t) {
		return false;
	}
	
}
