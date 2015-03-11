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
public interface Formula {
	
	boolean check(QDTModel m, int world);
	int size();
	Set<? extends Term> getTerms();
	
	@Override
	String toString();

}
