package tp4;

import ij.ImagePlus;
import ij.gui.NewImage;
import ij.io.Opener;
import ij.process.ImageProcessor;

import java.net.URL;

import javax.swing.JPanel;

/**
 * Projet réalisé dans le cadre d'un projet Paris Descartes L3 Image
 * 
 * @author Thibault Vieux
 */
public class Image {
	static final int WIN_WIDTH = 256;
	static final int WIN_HEIGHT = 256;

	public static void createIGImageGray() {
		URL url = Image.class.getResource("/lena.png");
		ImagePlus imp = new Opener().openURL(url.toString());
		ImagePlus imp2 = createGrayImage(imp);
		JPanel panel = new PanelImage(imp, getHistogramWindow(imp), imp2,
				getHistogramWindow(imp2));
		new IGImage("Gris", panel);
	}

	public static void createIGImageNormalize() {
		URL url = Image.class.getResource("/enhance-me.png");
		ImagePlus imp = new Opener().openURL(url.toString());
		ImagePlus imp2 = createNormalizeImage(imp);
		JPanel panel = new PanelImage(imp, getHistogramWindow(imp), imp2,
				getHistogramWindow(imp2));
		new IGImage("Normalisation", panel);
	}

	public static void createIGImageEqualize() {
		URL url = Image.class.getResource("/montagne.jpg");
		ImagePlus imp = new Opener().openURL(url.toString());
		ImagePlus imp2 = createEqualizeImage(imp);
		JPanel panel = new PanelImage(imp, getHistogramWindow(imp), imp2,
				getHistogramWindow(imp2));
		new IGImage("Egalisation", panel);
	}

	public static void createIGImageThresholding() {
		URL url = Image.class.getResource("/neige.jpg");
		ImagePlus imp = new Opener().openURL(url.toString());
		ImagePlus imp2 = createThresholingImage(imp, 125);
		JPanel panel = new PanelImage(imp, getHistogramWindow(imp), imp2,
				getHistogramWindow(imp2));
		new IGImage("Seuillage", panel);
	}

	/** Créer une image de couleur grise à partir d'une image */
	public static ImagePlus createGrayImage(ImagePlus imp) {
		final int hauteur = imp.getHeight(), largeur = imp.getWidth();
		ImagePlus impGray = NewImage.createByteImage("Gris " + imp.getTitle(),
				largeur, hauteur, 1, NewImage.GRAY8);
		ImageProcessor impp = impGray.getProcessor();

		for (int y = 0; y < hauteur; ++y)
			for (int x = 0; x < largeur; ++x)
				impp.putPixelValue(x, y, getGray(imp.getPixel(x, y)));
		
		return impGray;
	}

	/** Créer une image normalisée (étirée) à partir d'une image */
	public static ImagePlus createNormalizeImage(ImagePlus imp) {
		final int hauteur = imp.getHeight(), largeur = imp.getWidth();
		ImagePlus impNormalize = NewImage.createByteImage("Normalisation "
				+ imp.getTitle(), largeur, hauteur, 1, NewImage.GRAY8);
		ImageProcessor impp = impNormalize.getProcessor();

		// Nvg(x',y') = 255 * (Nvg(x,y) - min) / (max-min)
		int[] histo = getHistogram(createGrayImage(imp));
		int min = getMin(histo), max = getMax(histo), value;

		for (int y = 0; y < hauteur; ++y) {
			for (int x = 0; x < largeur; ++x) {
				value = 255 * (getGray(imp.getPixel(x, y)) - min) / (max - min);
				impp.putPixelValue(x, y, value);
			}
		}

		return impNormalize;
	}

	/** Créer une image égalisée à partir d'une image */
	public static ImagePlus createEqualizeImage(ImagePlus imp) {
		final int hauteur = imp.getHeight(), largeur = imp.getWidth();
		ImagePlus impEqualize = NewImage.createByteImage("Egalisation " 
				+ imp.getTitle(), largeur, hauteur, 1, NewImage.GRAY8);
		ImageProcessor impp = impEqualize.getProcessor();

		// Histogramme cumulé C(i) = ∑h(k) -> k[0,i]
		final int[] C = getHistogramCumul(imp);
		// nb valeur nvg, nb pixel sur l'image
		final int n = 255, N = impp.getPixelCount();

		int i, value;
		for (int y = 0; y < hauteur; ++y) {
			for (int x = 0; x < largeur; ++x) {
				i = getGray(imp.getPixel(x, y));
				// Transformation T(i) = (n/N)*C(i)
				value = (int) ((double) n / N * C[i]);
				impp.putPixelValue(x, y, value);
			}
		}

		return impEqualize;
	}

	/** Créer une image binarisée à partir d'une image */
	public static ImagePlus createThresholingImage(ImagePlus imp, int seuil) {
		final int hauteur = imp.getHeight(), largeur = imp.getWidth();
		ImagePlus impThresholing = NewImage.createByteImage("Seuillage " 
				+ imp.getTitle(), largeur, hauteur, 1, NewImage.GRAY8);
		ImageProcessor impp = impThresholing.getProcessor();
		
		int value;
		for (int y = 0; y < hauteur; ++y) {
			for (int x = 0; x < largeur; ++x) {
				value = getGray(imp.getPixel(x, y));
				if (value > seuil) value = 255;
				else value = 0;
				impp.putPixelValue(x, y, value);		
			}			
		}

		return impThresholing;
	}

	/** Affiche l'histogramme d'une image */
	public static ImagePlus getHistogramWindow(ImagePlus imp) {
		// Création de l'histogramme nvg
		ImagePlus impHisto = NewImage.createByteImage("Histogramme " 
				+ imp.getTitle(), WIN_WIDTH, WIN_HEIGHT, 1, NewImage.FILL_WHITE);

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

	/** Récupére l'histogramme d'une image sous forme d'un tableau d'entier */
	public static int[] getHistogram(ImagePlus imp) {
		int[] histo = new int[256];
		ImageProcessor ip = imp.getProcessor();
		final int hauteur = imp.getHeight(), largeur = imp.getWidth();
		
		// On parcours l'image sur l'axe des y
		for (int y = 0; y < hauteur; ++y) {
			// On parcours l'image sur l'axe des x
			for (int x = 0; x < largeur; ++x) {
				// On incrémente le nb d'occurence du niveau de gris
				// correspondant
				++histo[(int) ip.getPixelValue(x, y)];
			}
		}

		return histo;
	}

	/** Affiche l'histogramme cumulé d'une image */
	public static ImagePlus getHistogramCumulWindow(ImagePlus imp) {
		// Création de l'histogramme nvg
		ImagePlus impHisto = NewImage.createByteImage("Histogramme cumulé" 
				+ imp.getTitle(), WIN_WIDTH, WIN_HEIGHT, 1, NewImage.FILL_WHITE);

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

	/** Récupére l'histogramme cumulé d'une image */
	public static int[] getHistogramCumul(ImagePlus imp) {
		int[] histoCumul = getHistogram(imp);
		for (int i = 1, size = histoCumul.length; i < size; ++i)
			histoCumul[i] = histoCumul[i] + histoCumul[i - 1];

		return histoCumul;
	}

	/** Récupére la couleur grise d'un pixel de couleur */
	public static int getGray(int[] rgb) {
		return (rgb[0] + rgb[1] + rgb[2]) / 3;
	}

	/** Récupére le dernier nvg ayant une occurence supérieur à 0 */
	public static int getMax(int[] tab) {
		int i = tab.length - 1;
		while (tab[i] == 0) --i;
		return i;
	}

	/** Récupére le premier nvg ayant une occurence supérieur à 0 */
	public static int getMin(int[] tab) {
		int i = 0;
		while (tab[i] == 0) ++i;
		return i;
	}

	/**
	 * Exercice sur l'étirement, l'égalisation, la binarisation et le seuillage
	 * d'une image
	 */
	public static void main(String[] args) {
		new IGSelect();
	}

}
