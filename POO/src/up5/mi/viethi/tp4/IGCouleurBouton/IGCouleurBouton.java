package up5.mi.viethi.tp4.IGCouleurBouton;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class IGCouleurBouton extends JFrame  {
	
	public IGCouleurBouton() {
		super("Couleurs");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JPanel panneau = new JPanelCouleur();
		setContentPane(panneau);
		pack();
        setVisible(true);

	}
	
	public static void main(String[] args) {
		new IGCouleurBouton();
	}
}