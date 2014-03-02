import ij.IJ;
import ij.ImagePlus;
import ij.gui.NewImage;
import ij.process.ImageProcessor;

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
				impp.putPixelValue(x, y, getGray(imp.getPixel(x, y)));

		return impGray;
	}
	
	/**
	 * Fonction permettant d'étirer l'image
	 * 
	 * @param imp
	 */
	public static ImagePlus createNormalizeImage(ImagePlus imp) {
		ImagePlus newImg = NewImage.createByteImage(
				"Normalize " + imp.getTitle(), imp.getWidth(),
				imp.getHeight(), 1, NewImage.GRAY8);
		ImageProcessor impp = newImg.getProcessor();

		// Nvg(x',y') = 255 * (Nvg(x,y) - min) / (max-min)
		int[] histo = getHistogram(imp);
		int min = getMin(histo), max = getMax(histo), value;
		System.out.println("min="+min+" max="+max);
		
		for (int x = 0; x < imp.getWidth(); ++x) {
			for (int y = 0; y < imp.getHeight(); ++y) {
				value = 255 * (getGray(imp.getPixel(x, y)) - min) / (max - min);
				impp.putPixelValue(x, y, value);
			}
		}
		
		return newImg;
	}

	// Histogramme cumulé C(i) = ∑h(k) -> k[0,i]
	// Transformation T(i) = (n/N)*C(i)
	// n : nb de valeur de nvg
	// N : nb de pixel de l'image
	public static ImagePlus createEgalisationImage(ImagePlus imp) {
		ImagePlus newImg = NewImage.createByteImage("Egalisation " + imp.getTitle(),
				imp.getWidth(), imp.getHeight(), 1, NewImage.GRAY8);
		ImageProcessor impp = newImg.getProcessor();

		final int n = getNvgCount(getHistogram(imp));
		final int N = impp.getPixelCount();
		final int[] C = getHistogramCumul(imp);
		double ratio = 255.0 / impp.getPixelCount();
		
		System.out.println("Histo cumul");
		for (int i = 0; i < C.length; i++) {
			System.out.println(i + "=" + C[i] * ratio);
		}
		
		
//		for (int x = 0; x < imp.getWidth(); ++x) {
//			for (int y = 0; y < imp.getHeight(); ++y) {
//				int i = getGray(imp.getPixel(x, y));
//				int value = C[i]/N * ratio;
//				System.out.println("value = C[i] " + C[i] + " /N  " + N + " ratio " + ratio);
//				System.out.println("i: "+ i + " new i :" + value);
//				impp.putPixelValue(x, y, value);
//			}
//		}
		
//		int i, T;
//		for (int x = 0; x < imp.getWidth(); ++x) {
//			for (int y = 0; y < imp.getHeight(); ++y) {
//				i = getGray(imp.getPixel(x, y));
//				T = getTransformation(n, N, C[i]);
//				impp.putPixelValue(x, y, T);
//			}
//		}
		
		return newImg;
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
		int max = 0;
		for (int i : histo) 
			if (i > max) max = i;
		// On trace le graphe
		for (int i = 0; i < 256; ++i) {
			int hauteur = histo[i] * 256 / max;
			if (hauteur > 0)
				imppHisto.drawLine(i, 255, i, 256 - hauteur);
		}
		
		return impHisto;
	}
	
	public static ImagePlus getHistogramCumulWindow(ImagePlus imp) {
		// Création de l'histogramme nvg
		ImagePlus impHisto = NewImage.createByteImage(
				"Histogramme cumulé" + imp.getTitle(), WIN_WIDTH, WIN_HEIGHT, 1,
				NewImage.FILL_WHITE);

		// On récupére le processor pour tracer le graphe
		ImageProcessor imppHisto = impHisto.getProcessor();
		// On récupére le tableau des occurences cumulés
		int[] histo = getHistogramCumul(imp);
		// On récupére la valeur max pour normalisé
		int max = 0;
		for (int i : histo) 
			if (i > max) max = i;
		// On trace le graphe
		for (int i = 0; i < 256; ++i) {
			int hauteur = histo[i] * 256 / max;
			if (hauteur > 0)
				imppHisto.drawLine(i, 255, i, 256 - hauteur);
		}
		
		return impHisto;
	}

	public static int[] getHistogram(ImagePlus imp) {
		int[] histo = new int[256];
		// On parcours l'image sur l'axe des x
		for (int x = 0; x < imp.getHeight(); ++x) {
			// On parcours l'image sur l'axe des y
			for (int y = 0; y < imp.getWidth(); ++y) {
				// On incrémente le nb d'occurence du niveau de gris
				// correspondant
				++histo[getGray(imp.getPixel(x, y))];
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
	
	// Transformation T(i) = (n/N)*C(i)
	public static int getTransformation(int n, int N, int C) {
		return (n/N)*C;
	}

	/**
	 * Calcul la couleur grise d'un pixel de couleur
	 * @param rgb
	 * @return
	 */
	public static int getGray(int[] rgb) {
		return (rgb[0] + rgb[1] + rgb[2]) / 3;
	}

	/**
	 * Récupére le nombre de niveau de gris dans une image
	 * @param tab
	 * @return
	 */
	public static int getNvgCount(int[] tab) {
		int count = 0;
		for (int val : tab)
			if (val > 0) ++count;
		return count;
	}

	/**
	 * Récupére le dernier nvg ayant une occurence supérieur à 0
	 * @param tab
	 * @return
	 */
	public static int getMax(int[] tab) {
		int i = tab.length - 1;
		while (tab[i] == 0) --i;
		return i;
	}

	/**
	 * Récupére le premier nvg ayant une occurence supérieur à 0
	 * @param tab
	 * @return
	 */
	public static int getMin(int[] tab) {
		int i = 0;
		while (tab[i] == 0) ++i;
		return i;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/* Déclaration des paramètres */
		String[] img = {"enhance-me.png", "paysage.png", "3D.jpg" };
		String path;
		ImagePlus imp, hw;
		
		/*
		// On récupére le chemin vers l'image
		path = new File("src/Images/" + img[0])
				.getAbsolutePath();
		// On récupére l'image et son histogramme
		imp = IJ.openImage(path);
		hw = getHistogramWindow(imp);
		// On les affiches
		imp.show();
		hw.show();

		// Création de l'image en gris
		final ImagePlus impGray = createGrayImage(imp);
		ImagePlus hwGray = getHistogramWindow(impGray);
		impGray.show();
		hwGray.show();
		
		// Création de l'image étiré
		final ImagePlus impNormalize = createNormalizeImage(imp);
		ImagePlus hwNormalize = getHistogramWindow(impNormalize);
		impNormalize.show();
		hwNormalize.show();
		*/
		
		// On change d'image pour l'exemple d'égalisation
		path = new File("src/Images/" + img[1]).getAbsolutePath();
		imp = IJ.openImage(path);
		imp.show();
		
		hw = getHistogramWindow(imp);
		hw.show();
		
		// On affiche l'histogramme cumulé
		hw = getHistogramCumulWindow(imp);
		hw.show();

		final ImagePlus impEgalisation = createEgalisationImage(imp);
		impEgalisation.show();
		
		hw = getHistogramWindow(impEgalisation);
		hw.show();
		
		// TODO
		// Etirement, Egalisation, Seuillage OTSU

		// Vérification
//		HistogramWindow hw2 = new HistogramWindow(impGray);
//		hw2.setVisible(true);
	}

}
