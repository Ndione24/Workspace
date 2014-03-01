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

import javax.swing.JFrame;

public class Test {
	static final int WIN_WIDTH = 256;
	static final int WIN_HEIGHT = 256;

	public static ImagePlus createGrayImage(ImagePlus imp) {
		ImagePlus impGray = NewImage.createByteImage("Gray " + imp.getTitle(),
				imp.getWidth(), imp.getHeight(), 1, NewImage.GRAY8);
		ImageProcessor impp = impGray.getProcessor();

		for (int x = 0; x < imp.getWidth(); ++x)
			for (int y = 0; y < imp.getHeight(); ++y)
				impp.putPixelValue(x, y, getGray(imp.getPixel(x, y)));

		return impGray;
	}

	public static ImagePlus getHistogramWindow(ImagePlus imp) {
		// Création de l'histogramme nvg
		ImagePlus impHisto = NewImage.createByteImage(
				"Histogramme " + imp.getTitle(), WIN_WIDTH, WIN_HEIGHT, 1,
				NewImage.FILL_WHITE);

		// On récupére le processor pour tracer le graphe
		ImageProcessor imppHisto = impHisto.getProcessor();
		// On récupére le tableau des occurences
		int[] histo = getHistogram(imp);
		// On récupére la valeur max pour normalisé
		int max = getMax(histo);
		// On trace le graphe
		for (int i = 0; i < 256; ++i) {
			int hauteur = histo[i] * 256 / max;
			if (hauteur > 0)
				imppHisto.drawLine(i, 255, i, 256 - hauteur);
		}
		
		return impHisto;
	}

	public static int[] getHistogram(ImagePlus imp) {
		int[] histo = new int[256], rgb;
		// On parcours l'image sur l'axe des x
		for (int x = 0; x < imp.getHeight(); ++x) {
			// On parcours l'image sur l'axe des y
			for (int y = 0; y < imp.getWidth(); ++y) {
				// On récupére la niveau de gris du pixel
				rgb = imp.getPixel(x, y);
				// On incrémente le nb d'occurence du niveau de gris
				// correspondant
				++histo[rgb[0]];
			}
		}
		
		return histo;
	}
	
	public static int[] getHistogramCumul(ImagePlus imp) {
		int[] histoCumul = getHistogram(imp);
		
		for (int i = 1; i < histoCumul.length; ++i)
			histoCumul[i] = histoCumul[i] + histoCumul[i-1];
		
		return histoCumul;
	}

	/**
	 * Fonction permettant d'étirer l'image
	 * 
	 * @param imp
	 */
	public static ImagePlus createEnlargementImage(ImagePlus imp) {
		ImagePlus output = NewImage.createByteImage(
				"Enlargement " + imp.getTitle(), imp.getWidth(),
				imp.getHeight(), imp.getSlice(), NewImage.GRAY8);
		ImageProcessor impp = output.getProcessor();

		// Nvg(x',y') = 255 * (Nvg(x,y) - min) / (max-min))
		int[] histo = getHistogram(imp);
		int min = getMin(histo), max = getMax(histo), value;
		
		for (int x = 0; x < imp.getWidth(); ++x) {
			for (int y = 0; y < imp.getHeight(); ++y) {
				value = 255 * (getGray(imp.getPixel(x, y)) - min / (max - min));
				impp.putPixelValue(x, y, value);
			}
		}
		
		return output;
	}

	// Histogramme cumulé C(i) = ∑h(k) -> k[0,i]
	// Transformation T(i) = (n/N)*C(i)
	// n : nb de valeur de nvg
	// N : nb de pixel de l'image
	public static ImagePlus createEgalisationImage(ImagePlus imp) {
		ImagePlus output = NewImage.createByteImage(
				"Egalisation " + imp.getTitle(), imp.getWidth(),
				imp.getWidth(), imp.getSlice(), NewImage.GRAY8);

		ImageProcessor impp = output.getProcessor();

		// int n = getNvgCount(getHistogram(imp));
		int n = 256;
		int N = imp.getWidth() * imp.getWidth(); // ImageProcessor getPixelCount();
		// C[i] histogramme cumulé de taille 256
		int[] C = getHistogramCumul(imp);
		
		int value, i;
		int tab[];
		for (int x = 0; x < imp.getWidth(); ++x) {
			for (int y = 0; y < imp.getHeight(); ++y) {
				// On récupére la valeur du pixel
				tab = imp.getPixel(x, y);
				i = tab[0];
				// On effectue une transformation
				value = getTransformation(n, N, C[i]);
				impp.putPixelValue(x, y, value);
			}
		}
		
		return output;
	}
	
	// Transformation T(i) = (n/N)*C(i)
	public static int getTransformation(int n, int N, int C) {
		System.out.println("n="+n+'/'+"N="+N+'*'+"C="+C+" res= "+ (n/N)*C);
		return (n/N)*C;
	}

	public static int getGray(int[] rgb) {
		return (rgb[0] + rgb[1] + rgb[2]) / 3;
	}

	public static int getNvgCount(int[] tab) {
		int count = 0;
		for (int val : tab)
			if (val > 0) ++count;
		
		return count;
	}

	public static int getMax(int[] tab) {
		int max = tab[0];
		for (int i = 1; i < tab.length; i++)
			if (tab[i] > max) max = tab[i];
		return max;
	}

	public static int getMin(int[] tab) {
		int min = tab[0];
		for (int i = 1; i < tab.length; i++)
			if (tab[i] < min) min = tab[i];
		return min;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// On récupére le chemin vers l'image
		final String imageFilePath = new File("src/Images/enhance-me.bmp")
				.getAbsolutePath();
		final ImagePlus imp = IJ.openImage(imageFilePath);
		imp.show();

		// On créer une nouvelle image en gris
		final ImagePlus impGray = createGrayImage(imp);
		impGray.show();

		// On affiche l'histogramme
		ImagePlus hw = getHistogramWindow(impGray);
		hw.show();

		final ImagePlus impElargement = createEnlargementImage(impGray);
		impElargement.show();

		hw = getHistogramWindow(impElargement);
		hw.show();

		final ImagePlus impEgalisation = createEgalisationImage(impGray);
//		impEgalisation.show();
		
		hw = getHistogramWindow(impEgalisation);
//		hw.show();
		
		// TODO
		// Etirement, Egalisation, Seuillage OTSU

	}

}
