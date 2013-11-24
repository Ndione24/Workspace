package up5.mi.viethi.tp5.IGSecret;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import up5.mi.viethi.tp5.IGSecret.Jeu;

public class Auditeur extends Jeu implements ActionListener {
	
	private JLabel label;
	
    public Auditeur(int minInitial, int maxInitial, JLabel label) {
		super(minInitial, maxInitial);
		this.label = label;
	}
    
	public void actionPerformed(ActionEvent evt) {
		if (evt.getActionCommand().equals("+")) {
			noterPropositionTropPetite();
			label.setText("je propose " + getProposition());
			
		} else if (evt.getActionCommand().equals("-")) {
			noterPropositionTropGrande();
			label.setText("je propose " + getProposition());
			
		} else if (evt.getActionCommand().equals("Trouve")) {
			noterPropositionOK();
			int result = getProposition();
			int nbProp = getNbPropositions();
			reset();
			label.setText("TROUVE en " + nbProp + " coups : " + result + " Nouveau jeu démarré ! je propose " + getProposition());
		}
		
	}

}