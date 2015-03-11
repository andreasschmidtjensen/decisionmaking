/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dm.gen;

import dm.syntax.Literal;
import java.util.List;
import java.util.Set;

/**
 *
 * @author asj
 */
public class RuleProgram {
	
	private List<Rule> preferenceRules;
	private List<Rule> normalityRules;
	private List<Set<Literal>> impossible;

	public RuleProgram(List<Rule> preferenceRules, List<Rule> normalityRules,
			List<Set<Literal>> impossible) {
		this.preferenceRules = preferenceRules;
		this.normalityRules = normalityRules;
		this.impossible = impossible;
	}

	public List<Rule> getNormalityRules() {
		return normalityRules;
	}

	public List<Rule> getPreferenceRules() {
		return preferenceRules;
	}

	public List<Set<Literal>> getImpossible() {
		return impossible;
	}
	
}
