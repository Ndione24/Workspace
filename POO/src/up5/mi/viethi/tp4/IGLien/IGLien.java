package up5.mi.viethi.tp4.IGLien;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class IGLien {

	public static void main(String[] args) {
		JFrame fenetre = new JFrame("JLabelLien");
		JPanel panneau = new JPanel();
		
		JLabelLien lien = new JLabelLien();
		panneau.add(lien);
		
		fenetre.setContentPane(panneau);
		fenetre.pack();
        fenetre.setVisible(true);
	}

}
