/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dm;

import dm.gen.ModelGenerator;
import dm.gen.RuleProgram;
import dm.semantics.DecisionModel;
import dm.semantics.QDTModel;
import dm.syntax.Literal;
import dm.syntax.Term;
import jason.asSemantics.Agent;
import jason.asSemantics.Event;
import jason.runtime.Settings;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Queue;
import java.util.Set;
import java.util.logging.Level;
import org.antlr.runtime.RecognitionException;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class DMAgent extends Agent {

	private RuleProgram rp;
	private Set<Term> controllable;

	@Override
	public void initAg() {
		super.initAg();
		Settings settings = getTS().getSettings();
		String dmFile = settings.getUserParameter("dm");
		String controllables = settings.getUserParameter("c");

		try {
			rp = ProgramLoader.load(dmFile);
			controllable = ProgramLoader.parseTerms(controllables);
		} catch (IOException | RecognitionException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public Event selectEvent(Queue<Event> events) {
		ModelGenerator gen = new ModelGenerator(rp);

		for (Iterator<Event> it = events.iterator(); it.hasNext();) {
			Event e = it.next();
			if (!e.getTrigger().isAchvGoal()) {
				continue;
			}
			jason.asSyntax.Literal literal = (jason.asSyntax.Literal) e.getTrigger().getLiteral().clone();
			literal.clearAnnots();
			
			Literal pos = new Literal(literal.toString());
			Literal neg = new Literal(literal.toString(), false);
			Set<Literal> influences = new HashSet<>();
			influences.add(pos);
			influences.add(neg);
			
			try {
				QDTModel qdt = gen.generate(influences);
				Set<Literal> beliefs = new HashSet<>();
				for (jason.asSyntax.Literal bel : getBB()) {
					bel = (jason.asSyntax.Literal) bel.clone();
					bel.clearAnnots();

					if (bel.negated()) {
						beliefs.add(new Literal(bel.toString().substring(1),
								false));
					} else {
						beliefs.add(new Literal(bel.toString()));
					}
				}

				DecisionModel dm = new DecisionModel(qdt, influences,
						controllable, beliefs);
				if (!dm.isEmpty()) {
					Set<Literal> decision = dm.getDecision();
					if (decision.contains(pos)) {
						ts.getLogger().log(Level.INFO, "Deciding " + pos);
						it.remove();
						return e;
					} else if (decision.contains(neg)) {
						ts.getLogger().log(Level.INFO, "Dropping " + pos);
						it.remove();
					}
				}

			} catch (Exception ex) {
				ts.getLogger().log(Level.WARNING, "Could not apply some rules: ", ex.getMessage());
			}
		}
		
		ts.getLogger().log(Level.WARNING,"DM did not select any intention!");
		// TODO: return null?
		return super.selectEvent(events);
	}

}
