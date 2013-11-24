package up5.mi.viethi.tp4.IGCouleurBouton;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

public class Auditeur implements ActionListener {
    private JLabel label;
	
    public Auditeur(JLabel label) {
		this.label = label;
	}
    
	public void actionPerformed(ActionEvent evt) {
		if (evt.getActionCommand().equals("couleur rouge")) {
			label.setForeground(Color.RED);
		} else if (evt.getActionCommand().equals("couleur vert")) {
			label.setForeground(Color.GREEN);
		}
		
	}

}
