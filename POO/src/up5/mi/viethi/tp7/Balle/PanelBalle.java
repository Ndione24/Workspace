package up5.mi.viethi.tp7.Balle;

import java.awt.Color;
import java.util.Random;

import javax.swing.JPanel;

public class PanelBalle extends JPanel {

	public PanelBalle() {
		Balle uneBalle = new Balle(Color.red, 1, 20);
		createBalls(20);
	}

	private void createBalls(int nbBalls) {		
		int maxAngle = 360;
		int maxCouleur = 255;
		int angle, r, g, b;
		Color color; 
		
		Random rand = new Random();

		for (int i = 1; i < nbBalls+1; i++) {
			angle = rand.nextInt(maxAngle + 1);
			r = rand.nextInt(maxCouleur + 1);
			g = rand.nextInt(maxCouleur + 1);
			b = rand.nextInt(maxCouleur + 1);
			color = new Color(r,g,b);
			Balle uneBalle = new Balle(color, i, angle);
			add(uneBalle);
			uneBalle.start();
		}
		
	}
}
