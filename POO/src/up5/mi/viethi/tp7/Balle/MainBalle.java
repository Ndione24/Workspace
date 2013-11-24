package up5.mi.viethi.tp7.Balle;

import java.awt.Color;
import java.util.Random;

import javax.swing.JFrame;

import up5.mi.viethi.tp7.Balle.Balle;

public class MainBalle extends JFrame {
	
	public MainBalle() {
		super("Balles");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(new PanelBalle());
		setSize(400, 400);
		setVisible(true);
	}

	public static void main(String[] args) {
		new MainBalle();
	}
}
