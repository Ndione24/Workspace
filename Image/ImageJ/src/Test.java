import ij.IJ;
import ij.ImagePlus;
import ij.gui.HistogramWindow;
import ij.gui.NewImage;
import ij.process.ImageProcessor;

import java.io.File;
import java.util.Scanner;


public class Test {
	static final int WIN_WIDTH = 256;
	static final int WIN_HEIGHT = 256;

	public static void defaultImage(ImagePlus imp) {
		imp.show();
		getHistogramWindow(imp).show();
	}

	public static void grayImage(ImagePlus imp) {
		// Création de l'image en gris
		ImagePlus impGray = createGrayImage(imp);
		impGray.show();
		getHistogramWindow(impGray).show();
	}

	public static void normalizeImage(ImagePlus imp) {
		// Création de l'image étiré
		ImagePlus impNormalize = createNormalizeImage(imp);
		impNormalize.show();
		getHistogramWindow(impNormalize).show();
	}

	public static void equalizeImage(ImagePlus imp) {
		// Création de l'image égalisé
		ImagePlus impEqualize = createEqualizeImage(imp);
		impEqualize.show();
		getHistogramWindow(impEqualize).show();
	}

	public static void thresholdingImage(ImagePlus imp) {
		// On récupére les bornes choisi par l'utilisateur
		Scanner in = new Scanner(System.in);
		System.out.print("Borne inférieure (valeur conseillée 220) : ");
		int inf = in.nextInt();
		System.out.print("Borne supérieur  (valeur conseillée 255) : ");
		int sup = in.nextInt();
		in.close();
		// Création de l'image avec le seuillage OTSU
		ImagePlus impThresholing = createThresholingImage(imp, inf, sup);
		impThresholing.show();
		getHistogramWindow(impThresholing);
	}

	public static ImagePlus createGrayImage(ImagePlus imp) {
		ImagePlus impGray = NewImage.createByteImage("Gray " + imp.getTitle(),
				imp.getWidth(), imp.getHeight(), 1, NewImage.GRAY8);
		ImageProcessor impp = impGray.getProcessor();

		for (int y = 0; y < imp.getHeight(); ++y)
			for (int x = 0; x < imp.getWidth(); ++x)
				impp.putPixelValue(x, y, getGray(imp.getPixel(x, y)));

		return impGray;
	}

	/**
	 * Créer une image normalisé
	 * 
	 * @param imp
	 */
	public static ImagePlus createNormalizeImage(ImagePlus imp) {
		ImagePlus impNormalize = NewImage.createByteImage(
				"Normalize " + imp.getTitle() + imp.getTitle(), imp.getWidth(),
				imp.getHeight(), 1, NewImage.GRAY8);
		ImageProcessor impp = impNormalize.getProcessor();

		// Nvg(x',y') = 255 * (Nvg(x,y) - min) / (max-min)
		int[] histo = getHistogram(imp);
		int min = getMin(histo), max = getMax(histo), value;

		for (int y = 0; y < imp.getHeight(); ++y) {
			for (int x = 0; x < imp.getWidth(); ++x) {
				value = 255 * (getGray(imp.getPixel(x, y)) - min) / (max - min);
				impp.putPixelValue(x, y, value);
			}
		}

		return impNormalize;
	}

	/**
	 * Créer une image égalisé
	 * 
	 * @param imp
	 * @return
	 */
	public static ImagePlus createEqualizeImage(ImagePlus imp) {
		ImagePlus impEqualize = NewImage.createByteImage(
				"Egalisation " + imp.getTitle(), imp.getWidth(),
				imp.getHeight(), 1, NewImage.GRAY8);
		ImageProcessor impp = impEqualize.getProcessor();

		// Histogramme cumulé C(i) = ∑h(k) -> k[0,i]
		final int[] C = getHistogramCumul(imp);
		// nb valeur nvg, nb pixel sur l'image
		final int n = 255, N = impp.getPixelCount();

		int i, value;
		for (int y = 0; y < imp.getHeight(); ++y) {
			for (int x = 0; x < imp.getWidth(); ++x) {
				i = getGray(imp.getPixel(x, y));
				// Transformation T(i) = (n/N)*C(i)
				value = (int) ((double) n / N * C[i]);
				impp.putPixelValue(x, y, value);
			}
		}

		return impEqualize;
	}

	public static ImagePlus createThresholingImage(ImagePlus imp, int inf, int sup) {
		ImagePlus impThresholing = NewImage.createByteImage(
				"Seuillage " + imp.getTitle(), imp.getWidth(),
				imp.getHeight(), 1, NewImage.GRAY8);
		ImageProcessor impp = impThresholing.getProcessor();
		
		int value;
		for (int y = 0; y < imp.getHeight(); ++y) {
			for (int x = 0; x < imp.getWidth(); ++x) {
				value = getGray(imp.getPixel(x, y));
				if (value > inf && value < sup) value = 255;
				else value = 0;
				impp.putPixelValue(x, y, value);		
			}			
		}

		return impThresholing;
	}

	public static ImagePlus getHistogramWindow(ImagePlus imp) {
		// Création de l'histogramme nvg
		ImagePlus impHisto = NewImage.createByteImage(
				"Histogramme " + imp.getTitle(), WIN_WIDTH, WIN_HEIGHT, 1,
				NewImage.FILL_WHITE);

		// On récupére le processor pour tracer le graphe
		ImageProcessor imppHisto = impHisto.getProcessor();
		// On récupére le tableau des occurences
		final int[] histo = getHistogram(imp);
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
		// On parcours l'image sur l'axe des y
		for (int y = 0; y < imp.getHeight(); ++y) {
			// On parcours l'image sur l'axe des x
			for (int x = 0; x < imp.getWidth(); ++x) {
				// On incrémente le nb d'occurence du niveau de gris
				// correspondant
				++histo[getGray(imp.getPixel(x, y))];
				// DEBUG : Affichage des nvg de chaque pixel
//				System.out.println(getGray(imp.getPixel(x, y)));
			}
		}

		// DEBUG : Affichage des valeurs de l'histo
//		for (int i = 0; i < histo.length; ++i) {
//			System.out.println(i + " " + histo[i]);
//		}

		return histo;
	}

	public static ImagePlus getHistogramCumulWindow(ImagePlus imp) {
		// Création de l'histogramme nvg
		ImagePlus impHisto = NewImage
				.createByteImage("Histogramme cumulé" + imp.getTitle(),
						WIN_WIDTH, WIN_HEIGHT, 1, NewImage.FILL_WHITE);

		// On récupére le processor pour tracer le graphe
		ImageProcessor imppHisto = impHisto.getProcessor();
		// On récupére le tableau des occurences cumulés
		final int[] histo = getHistogramCumul(imp);
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

	public static int[] getHistogramCumul(ImagePlus imp) {
		int[] histoCumul = getHistogram(imp);
		for (int i = 1; i < histoCumul.length; ++i)
			histoCumul[i] = histoCumul[i] + histoCumul[i - 1];

		return histoCumul;
	}

	/**
	 * Renvoi la couleur grise d'un pixel de couleur
	 * 
	 * @param rgb
	 * @return
	 */
	public static int getGray(int[] rgb) {
		/*
		 * // DEBUG : Affiche les couleurs du pixel
		 * System.out.println("r:"+rgb[0] + " v:"+rgb[1] + " b:"+rgb[2]);
		 */

		// Permet de corriger un bug avec la couleur blanc
		if (rgb[0] == 255 && rgb[1] == 0 && rgb[2] == 0)
			return 255;

		return (rgb[0] + rgb[1] + rgb[2]) / 3;
	}

	/**
	 * Récupére le dernier nvg ayant une occurence supérieur à 0
	 * 
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
	 * 
	 * @param tab
	 * @return
	 */
	public static int getMin(int[] tab) {
		int i = 0;
		while (tab[i] == 0) ++i;
		return i;
	}

	/**
	 * Exercice sur l'étirement, l'égalisation et le seuillage d'une image
	 * @param args
	 */
	public static void main(String[] args) {
		/* Déclaration des paramètres */
		String[] img = { "3Des.jpg", "enhance-me.png", "paysage.png",
				"neige.jpg", "white.jpg" };
		String path;
		ImagePlus imp;

		// On prend l'image 3Des pour l'affiche en gris
		path = new File("src/Images/" + img[0]).getAbsolutePath();
		imp = IJ.openImage(path);

		defaultImage(imp);
		grayImage(imp);

		// On prend l'image enhance-me pour la normalisation
		path = new File("src/Images/" + img[1]).getAbsolutePath();
		imp = IJ.openImage(path);

		defaultImage(imp);
		normalizeImage(imp);

		// On prend l'image paysage pour l'égalisation
		path = new File("src/Images/" + img[2]).getAbsolutePath();
		imp = IJ.openImage(path);

		defaultImage(imp);
		equalizeImage(imp);

		// On prend l'image neige pour le seuillage
		path = new File("src/Images/" + img[3]).getAbsolutePath();
		imp = IJ.openImage(path);
		
		defaultImage(imp);
		thresholdingImage(imp);

		// Vérification de l'histogramme 
//		HistogramWindow hw2 = new HistogramWindow(imp); hw2.setVisible(true);
		
	}

}
