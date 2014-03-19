package abalone.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import abalone.tools.FrameUtil;

public class PanelGameboard extends JPanel {
	
	private BufferedImage iGameboard;
	
	public PanelGameboard() throws IOException {
		// Création de l'image
		iGameboard = ImageIO.read(new File("./data/gameboard.jpg"));
		int width = iGameboard.getWidth();
		int height = iGameboard.getHeight();
		setPreferredSize(new Dimension(width, height));
		// Contexte graphique de l'image
		Graphics gPlateau = iGameboard.getGraphics();
		// Dessine le composant
		paintComponent(gPlateau);
		// Libération
		gPlateau.dispose();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		// Appel de la méthode paintComponent de la classe mère
		super.paintComponents(g);
		g.drawImage(iGameboard, 0, 0, null);
	}
	
	public static void main(String[] args) throws IOException {
		PanelGameboard panGB = new PanelGameboard();
		FrameUtil.createFrame("Gameboard", panGB);
	}

}
