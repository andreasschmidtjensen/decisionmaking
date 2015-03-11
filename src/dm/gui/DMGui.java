/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dm.gui;

import dm.ProgramLoader;
import dm.gen.ModelGenerator;
import dm.gen.Rule;
import dm.gen.RuleProgram;
import dm.semantics.DecisionModel;
import dm.semantics.QDTModel;
import dm.syntax.Literal;
import dm.syntax.Term;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import org.antlr.runtime.RecognitionException;

/**
 *
 * @author asj
 */
public class DMGui extends JFrame {
	
	private Properties lastInput = new Properties();
	
	private JTextArea fullModel;
	private JTextArea runOutput;
	
	private RuleProgram program;
	private Set<Literal> f;

	public DMGui() {
		setTitle("Decision-Making using Qualitative Decision Theory");
		setSize(1100, 800);
		
		if (!new File(DM_LASTRUN).exists()) {
			lastInput.setProperty("file", "");
			lastInput.setProperty("influences", "");
			lastInput.setProperty("beliefs", "");
			lastInput.setProperty("controllables", "");
			lastInput.setProperty("ec", "");
			
			save();
		} else {
			try {
				lastInput.load(new FileReader(DM_LASTRUN));
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		}
				
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		setLayout(new BorderLayout(10,10));
		
		JPanel setup = createSetup();
		JPanel outputs = createOutputs();
		
		add(setup, BorderLayout.LINE_START);
		add(outputs, BorderLayout.CENTER);
	}

	private void save() {
		try {
			FileWriter wr = new FileWriter(DM_LASTRUN);
			lastInput.store(wr, "");
		} catch (IOException ex) {
			System.err.println("Could not save properties: " + ex.getMessage());
		}
	}
	
	private static final String DM_LASTRUN = "dm_lastrun";

	private JPanel createSetup() {
		JPanel setup = new JPanel();
		setup.setLayout(new BoxLayout(setup, BoxLayout.Y_AXIS));
		
		JLabel lblLocation = new JLabel("Program location:");
		final JTextField ruleLocationText = new JTextField(lastInput.getProperty("file"));
		ruleLocationText.setMaximumSize(new Dimension(600, 30));
		setup.add(lblLocation);
		setup.add(ruleLocationText);
		
		JLabel lblInfluences = new JLabel("Influences:");
		final JTextField influences = new JTextField(lastInput.getProperty("influences"));
		influences.setMaximumSize(new Dimension(600, 30));
		setup.add(lblInfluences);
		setup.add(influences);

		JButton loadRules = new JButton("Generate model");
		setup.add(loadRules);		
		loadRules.addActionListener((ActionEvent e) -> {
			fullModel.setText("");
			runOutput.setText("");
			
			try {
				fullModel.append("Parsing program...\n");
				program = ProgramLoader.load(ruleLocationText.getText());
				fullModel.append("Program successfully parsed...\n");
				
				fullModel.append("Preferences:\n");
				for (Rule r : program.getPreferenceRules()) {
					fullModel.append("\t" + r.toString() + "\n");
				}
				fullModel.append("\nExpectations:\n");
				for (Rule r : program.getNormalityRules()) {
					fullModel.append("\t" + r.toString() + "\n");
				}
				fullModel.append("\nImpossible states:\n");
				for (Set<Literal> r : program.getImpossible()) {
					fullModel.append("\t" + r.toString() + "\n");
				}
				
				try {
					f = ProgramLoader.parseLiterals(influences.getText());
				} catch (RecognitionException ex) {
					fullModel.append("Could not read influences\n");
					fullModel.append(exceptionToString(ex));
					return;
				}
				
				fullModel.append("Generating model...\n");
				ModelGenerator gen = new ModelGenerator(program);
				QDTModel qdt = gen.generate(f);

				fullModel.append("Worlds: " + qdt.getWorlds() + "\n");
				fullModel.append("P: " + qdt.printPreferenceOrdering() + "\n");
				fullModel.append("N: " + qdt.printNormalityOrdering() + "\n");
				
				lastInput.setProperty("file", ruleLocationText.getText());
				lastInput.setProperty("influences", influences.getText());
				save();
				
			} catch (IOException | RecognitionException ex) {
				fullModel.append("Could not parse program!\n");
				fullModel.append(exceptionToString(ex));
			}
		});
		
		JLabel lblBeliefs = new JLabel("Beliefs:");
		final JTextField beliefs = new JTextField(lastInput.getProperty("beliefs"));
		beliefs.setMaximumSize(new Dimension(600, 30));
		setup.add(lblBeliefs);
		setup.add(beliefs);
				
		JLabel lblControllable = new JLabel("Controllable:");
		final JTextField controllable = new JTextField(lastInput.getProperty("controllables"));
		controllable.setMaximumSize(new Dimension(600, 30));
		setup.add(lblControllable);
		setup.add(controllable);
		
		JButton run = new JButton("Run");
		setup.add(run);
		
		run.addActionListener((ActionEvent e) -> {
			if (program == null) {
				fullModel.setText("Generate model first!");
				return;
			}
			
			runOutput.setText("Parsing input...\n");
			
			Set<Term> c;
			try {
				c = ProgramLoader.parseTerms(controllable.getText());
			} catch (RecognitionException ex) {
				runOutput.append("Could not read controllables\n");
				runOutput.append(exceptionToString(ex));
				return;
			}
			Set<Literal> bb;
			try {
				bb = ProgramLoader.parseLiterals(beliefs.getText());
			} catch (RecognitionException ex) {
				runOutput.append("Could not read beliefs\n");
				runOutput.append(exceptionToString(ex));
				return;
			}
			
			lastInput.setProperty("controllables", controllable.getText());
			lastInput.setProperty("beliefs", beliefs.getText());
			save();
			
			runOutput.append("Generating model based on beliefs...\n");
			ModelGenerator gen = new ModelGenerator(program);
			QDTModel qdt = gen.generate(f);

			DecisionModel model = new DecisionModel(qdt, f, c, bb);
			
			runOutput.append("Making a decision...\n");
			
			if (qdt.getWorlds().isEmpty()) {
				runOutput.append("No possible worlds!\n");
				return;
			}
			
			Set<Literal> tol = model.getMostTolerableConsequences();
			Set<Literal> pref = model.getMostPreferredInfluences();
			Set<Literal> dec = model.getDecision();
						
			runOutput.append("Worlds: " + qdt.getWorlds() + "\n");
			runOutput.append("P: " + qdt.printPreferenceOrdering() + "\n");
			runOutput.append("N: " + qdt.printNormalityOrdering() + "\n");

			runOutput.append("Pref=" + pref + "\n");
			runOutput.append("Tol=" + tol + "\n");
			runOutput.append("Dec=" + dec + "\n");
		});
		
				
		JLabel lblEc = new JLabel("Expected consequence:");
		final JTextField ec = new JTextField(lastInput.getProperty("ec"));
		ec.setMaximumSize(new Dimension(600, 30));
		setup.add(lblEc);
		setup.add(ec);
		
		JButton checkEc = new JButton("Get EC");
		setup.add(checkEc);
		checkEc.addActionListener((ActionEvent e) -> {
			if (program == null) {
				fullModel.setText("Generate model first!");
				return;				
			}
			
			runOutput.append("Parsing input...\n");
			
			Set<Term> c;
			try {
				c = ProgramLoader.parseTerms(controllable.getText());
			} catch (RecognitionException ex) {
				runOutput.append("Could not read controllables\n");
				runOutput.append(exceptionToString(ex));
				return;
			}
			Set<Literal> bb;
			try {
				bb = ProgramLoader.parseLiterals(beliefs.getText());
			} catch (RecognitionException ex) {
				runOutput.append("Could not read beliefs\n");
				runOutput.append(exceptionToString(ex));
				return;
			}
			Literal l;
			try {
				l = ProgramLoader.parseLiteral(ec.getText());
			} catch (RecognitionException ex) {
				runOutput.append("Could not read expected consequence\n");
				runOutput.append(exceptionToString(ex));
				return;
			}
					
			lastInput.setProperty("controllables", controllable.getText());
			lastInput.setProperty("beliefs", beliefs.getText());
			lastInput.setProperty("ec", ec.getText());
			save();
			
			ModelGenerator gen = new ModelGenerator(program);
			QDTModel qdt = gen.generate(f);

			DecisionModel model = new DecisionModel(qdt, f, c, bb);
			
			runOutput.append("Getting expected consequences of " + l + "\n");
			
			if (qdt.getWorlds().isEmpty()) {
				runOutput.append("No possible worlds!\n");
				return;
			}
			runOutput.append("EC=" + model.getExpectedConsequences(l) + "\n");
		});
		
		return setup;		
	}

	private String exceptionToString(final java.lang.Exception ex) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		String string = sw.toString();
		return string;
	}

	private JPanel createOutputs() {
		JPanel outputs = new JPanel();
		outputs.setLayout(new GridLayout(0,1));
		
		fullModel = new JTextArea();
		outputs.add(new JScrollPane(fullModel));
		
		runOutput = new JTextArea();
		outputs.add(new JScrollPane(runOutput));
		
		return outputs;
	}

}
