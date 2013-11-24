package up5.mi.viethi.tp5.IGSecret;

import javax.swing.JFrame;
import javax.swing.JPanel;

import up5.mi.viethi.tp4.IGCouleurBouton.JPanelCouleur;

public class IGSecret extends JFrame {
	
	public IGSecret() {
		super("L'ordinateur trouve un nombre secret");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JPanel panneau = new JPanelJeu();
		setContentPane(panneau);
		setSize(450, 150);
        setVisible(true);
	}
	
	public static void main(String[] args) {
		new IGSecret();
	}
}
