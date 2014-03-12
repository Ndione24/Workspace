package tp4;

import ij.ImagePlus;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class IGImage extends JFrame {
	
	public IGImage(String title, JPanel panel) {
		super(title);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setResizable(false);
		add(panel);
		pack();
		setVisible(true);
	}
	
	public static void createIGImageGray() {
		ImagePlus imp = Image.openImage("lena.png");
		ImagePlus imp2 = Image.createGrayImage(imp);
		new IGImage("Gris", new PanelImage(imp, imp2));
	}

	public static void createIGImageNormalize() {
		ImagePlus imp = Image.openImage("enhance-me.png");
		ImagePlus imp2 = Image.createNormalizeImage(imp);
		new IGImage("Normalisation", new PanelImage(imp, imp2));
	}

	public static void createIGImageEqualize() {
		ImagePlus imp = Image.openImage("montagne.jpg");
		ImagePlus imp2 = Image.createEqualizeImage(imp);
		new IGImage("Egalisation", new PanelImage(imp, imp2));
	}

	public static void createIGImageThresholding() {
		ImagePlus imp = Image.openImage("neige.jpg");
		ImagePlus imp2 = Image.createThresholingImage(imp, 125);
		new IGImage("Seuillage", new PanelImage(imp, imp2));
	}
	
}
