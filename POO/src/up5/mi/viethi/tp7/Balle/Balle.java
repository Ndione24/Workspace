package up5.mi.viethi.tp7.Balle;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

public class Balle extends JButton implements Runnable {

	// la position de la balle dans son container
	private double x, y;
	// L'angle de déplacement
	private double angle;
	// vrai si en mouvement
	private boolean roule = true;

	public Balle(Color bgColor, int i, double angle) {
		super("" + i);
		this.setBackground(bgColor);
		this.angle = angle;
		this.setSize(20, 20);
	}

	public void stop() {
		this.roule = false;
	}

	public void start ()
	{
		Thread th = new Thread(this);
		th.start();
	}
	
	public void run() {
		while (this.roule) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException exp) {
				exp.printStackTrace();
			}
			x = (x + Math.cos(angle) * 10) % 400;
			if (x < 0)
				x = x + 400;
			y = (y + Math.sin(angle) * 10) % 400;
			if (y < 0)
				y = y + 400;
			// appel de setLocation pour mettre à jour la position de la balle
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					Balle.this.setLocation((int) x, (int) y);
				}
			});

		}
	}
}