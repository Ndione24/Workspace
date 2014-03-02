import ij.IJ;
import ij.ImagePlus;
import ij.gui.NewImage;
import ij.process.ImageProcessor;

import java.io.File;

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
		ImagePlus impEgalisation = createEqualizeImage(imp);
		impEgalisation.show();
		getHistogramWindow(impEgalisation).show();
	}

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

		for (int x = 0; x < imp.getWidth(); ++x) {
			for (int y = 0; y < imp.getHeight(); ++y) {
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
		for (int x = 0; x < imp.getWidth(); ++x) {
			for (int y = 0; y < imp.getHeight(); ++y) {
				i = getGray(imp.getPixel(x, y));
				// Transformation T(i) = (n/N)*C(i)
				value = (int) ((double) n/N * C[i]);
				impp.putPixelValue(x, y, value);
			}
		}

		return impEqualize;
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
	 * @param args
	 */
	public static void main(String[] args) {
		/* Déclaration des paramètres */
		String[] img = { "3D.jpg", "enhance-me.png", "paysage.png" };
		String path;
		ImagePlus imp;

		// On prend l'image 3D pour l'affiche en gris
		path = new File("src/Images/" + img[0]).getAbsolutePath();
		imp = IJ.openImage(path);

		defaultImage(imp);
		grayImage(imp);

		// On prend l'image enhance-me pour la normalisation
		path = new File("src/Images/" + img[1]).getAbsolutePath();
		imp = IJ.openImage(path);

		defaultImage(imp);
		normalizeImage(imp);

		// On prend une autre image pour l'égalisation
		path = new File("src/Images/" + img[2]).getAbsolutePath();
		imp = IJ.openImage(path);

		defaultImage(imp);
		equalizeImage(imp);

		// TODO
		// Etirement, Egalisation, Seuillage OTSU

		// Vérification
//		HistogramWindow hw2 = new HistogramWindow(impEgalisation);
//		hw2.setVisible(true);
	}

}
