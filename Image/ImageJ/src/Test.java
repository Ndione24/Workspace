import ij.IJ;
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

	public static ImagePlus createGrayImage(ImagePlus imp) {
		ImagePlus impGray = NewImage.createByteImage("Gray " + imp.getTitle(),
				imp.getWidth(), imp.getHeight(), 1, NewImage.GRAY8);
		ImageProcessor impp = impGray.getProcessor();

		for (int x = 0; x < imp.getWidth(); ++x)
			for (int y = 0; y < imp.getHeight(); ++y)
				impp.putPixelValue(x, y, getGrayValue(imp.getPixel(x, y)));

		return impGray;
	}

	public static ImagePlus getHistogramWindow(ImagePlus imp) {
		// Création de l'histogramme nvg
		ImagePlus impHisto = NewImage.createByteImage(
				"Histogramme " + imp.getTitle(), WIN_WIDTH, WIN_HEIGHT, 0,
				NewImage.FILL_WHITE);

		// On récupére le processor pour tracer le graphe
		ImageProcessor imppHisto = impHisto.getProcessor();
		// On récupére le tableau des occurences
		int[] histo = getHistogram(imp);
		// On récupére la valeur max pour normalisé
		int max = getMax(histo);
		// On trace le graphe
		for (int i = 0; i < 256; i++) {
			int hauteur = histo[i] * 256 / max;
			if (hauteur > 0)
				imppHisto.drawLine(i, 255, i, 256 - hauteur);
		}
		return impHisto;

	}

	public static int[] getHistogram(ImagePlus imp) {
		int[] histo = new int[256], rgb;
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
		return histo;
	}

	/**
	 * Fonction permettant d'étirer l'image
	 * 
	 * @param imp
	 */
	public static ImagePlus CreateDrawOutImage(ImagePlus imp) {

		ImagePlus output = NewImage.createByteImage(
				"Draw out " + imp.getTitle(), imp.getWidth(), imp.getWidth(),
				imp.getSlice(), NewImage.GRAY8);
		int[] histo = getHistogram(imp);
		int min = getMin(histo), max = getMax(histo), value;
		ImageProcessor impp = output.getProcessor();

		// Nvg(x',y') = 255 * (Nvg(x,y) - min) / (max-min))
		for (int x = 0; x < histo.length; x++) {
			for (int y = 0; y < histo.length; y++) {
				value = 255 * (getGrayValue(imp.getPixel(x, y)) - min
						/ (max - min));
				impp.putPixelValue(x, y, value);
			}
		}
		return output;
	}

	public static int getGrayValue(int[] rgb) {
		return (rgb[0] + rgb[1] + rgb[2]) / 3;
	}

	public static int getMax(int[] tab) {
		int max = tab[0];
		for (int i = 1; i < tab.length; i++) {
			if (tab[i] > max)
				max = tab[i];
		}
		return max;
	}

	public static int getMin(int[] tab) {
		int min = tab[0];
		for (int i = 1; i < tab.length; i++) {
			if (tab[i] < min)
				min = tab[i];
		}
		return min;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// On récupére le chemin vers l'image
		String imageFilePath = new File("src/Images/enhance-me.bmp")
				.getAbsolutePath();
		ImagePlus imp = IJ.openImage(imageFilePath);
		imp.show();

		// On créer une nouvelle image en gris
		ImagePlus impGray = createGrayImage(imp);
		impGray.show();

		// On affiche l'histogramme
		ImagePlus hw = getHistogramWindow(impGray);
		hw.show();

		ImagePlus impGrayDrawOut = CreateDrawOutImage(impGray);
		impGrayDrawOut.show();
		// TODO
		// Etirement, Egalisation, Seuillage OTSU

	}

}
