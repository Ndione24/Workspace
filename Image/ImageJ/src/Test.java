import ij.ImagePlus;
import ij.gui.HistogramWindow;
import ij.gui.ImageWindow;
import ij.gui.NewImage;
import ij.io.Opener;
import ij.process.ImageConverter;
import ij.process.ImageProcessor;

import java.awt.Color;
import java.io.File;

public class Test {
	static final int WIN_WIDTH = 256;
	static final int WIN_HEIGHT = 256;

	public static int getGray (int[] rgb) {
		// (Rouge + Vert + Bleu) / 3
		return (rgb[1]+rgb[2]+rgb[3])/3;
	}
	
	public static int[] changePixelToGray(int[] rgb) {
		int gray = getGray(rgb);
		return new int[] { gray, gray, gray };
	}

	public static void convertToGray(ImagePlus imp) {
		ImageProcessor impp = imp.getProcessor();
		int[] rgb;
		for (int i = 0; i < imp.getWidth(); i++) {
			for (int j = 0; j < imp.getHeight(); j++) {
				rgb = changePixelToGray(imp.getPixel(i, j));
				impp.putPixel(i, j, rgb);
			}
		}
	}

	public static void tracerHistogram(ImagePlus imp) {
		// Création de l'histogramme nvg
		ImagePlus impHisto = NewImage.createByteImage(
				"Histogramme " + imp.getTitle(), WIN_WIDTH, WIN_HEIGHT, 1,
				NewImage.FILL_WHITE);
		
		ImageProcessor ipHisto = impHisto.getProcessor();

		// Tableau des occurences, il existe 256 valeurs de gris possibles
		int[] histo = new int[256];
		int[] rgb;
		// On parcours l'image sur l'axe des x
		for (int x = 0; x < imp.getHeight(); x++) {
			// On parcours l'image sur l'axe des y
			for (int y = 0; y < imp.getWidth(); y++) {
				// On récupére la niveau de gris du pixel
				rgb = imp.getPixel(x, y);
				// On incrémente le nb d'occurence du niveau de gris
				// correspondant
				++histo[rgb[0]];
			}
		}
		
		System.out.print("["+histo[0]);
		for (int i = 1; i < histo.length; i++) {
			System.out.print("," + histo[i]);
		}
		System.out.println("]");

		// Redimensionnement de l'histogramme
		int max = 0;
		for (int i = 0; i < 256; i++) {
			if (histo[i] > max)
				max = histo[i];
		}

		for (int i = 0; i < 256; i++) {
			int hauteur = histo[i] * 256 / max;
			if (hauteur > 0)
				ipHisto.drawLine(i, 255, i, 256 - hauteur);
		}

		new ImageWindow(impHisto);

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Opener opener = new Opener();
		String imageFilePath = new File("src/Images/3D.jpg").getAbsolutePath();
		
		ImagePlus imp = opener.openImage(imageFilePath);
		ImageProcessor ip = imp.getProcessor();

//		On convertie l'image en gris
		convertToGray(imp);
		
//		On affiche l'image
//		imp.show();
		
//		HistogramWindow hw = new HistogramWindow(imp);
//		hw.setVisible(true);
		
		tracerHistogram(imp);
	}

}
