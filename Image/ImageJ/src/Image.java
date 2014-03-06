import ij.IJ;
import ij.ImagePlus;
import ij.gui.NewImage;
import ij.process.ImageProcessor;

import java.io.File;

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
		String path = new File("images/3Des.jpg").getPath();
		ImagePlus imp = IJ.openImage(path);
		ImagePlus imp2 = createGrayImage(imp);
		JPanel panel = new PanelImage(imp, getHistogramWindow(imp), imp2,
				getHistogramWindow(imp2));
		new IGImage("Gris", panel);
	}

	public static void createIGImageNormalize() {
		String path = new File("images/enhance-me.png").getPath();
		ImagePlus imp = IJ.openImage(path);
		ImagePlus imp2 = createNormalizeImage(imp);
		JPanel panel = new PanelImage(imp, getHistogramWindow(imp), imp2,
				getHistogramWindow(imp2));
		new IGImage("Normalisation", panel);
	}

	public static void createIGImageEqualize() {
		String path = new File("images/montagne.jpg").getPath();
		ImagePlus imp = IJ.openImage(path);
		ImagePlus imp2 = createEqualizeImage(imp);
		JPanel panel = new PanelImage(imp, getHistogramWindow(imp), imp2,
				getHistogramWindow(imp2));
		new IGImage("Egalisation", panel);
	}

	public static void createIGImageThresholding() {
		String path = new File("images/neige.jpg").getPath();
		ImagePlus imp = IJ.openImage(path);
		ImagePlus imp2 = createThresholingImage(imp, 220, 255);
		JPanel panel = new PanelImage(imp, getHistogramWindow(imp), imp2,
				getHistogramWindow(imp2));
		new IGImage("Seuillage", panel);
	}

	/**
	 * Créer une image de couleur grise à partir d'une image
	 * 
	 * @param imp
	 * @return
	 */
	public static ImagePlus createGrayImage(ImagePlus imp) {
		ImagePlus impGray = NewImage.createByteImage("Gris " + imp.getTitle(), imp.getWidth(),
				imp.getHeight(), 1, NewImage.GRAY8);
		ImageProcessor impp = impGray.getProcessor();

		for (int y = 0; y < imp.getHeight(); ++y)
			for (int x = 0; x < imp.getWidth(); ++x)
				impp.putPixelValue(x, y, getGray(imp.getPixel(x, y)));

		return impGray;
	}

	/**
	 * Créer une image normalisée (étirée) à partir d'une image
	 * 
	 * @param imp
	 * @return
	 */
	public static ImagePlus createNormalizeImage(ImagePlus imp) {
		ImagePlus impNormalize = NewImage.createByteImage("Normalisation " + imp.getTitle(),
				imp.getWidth(), imp.getHeight(), 1, NewImage.GRAY8);
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
	 * Créer une image égalisée à partir d'une image
	 * 
	 * @param imp
	 * @return
	 */
	public static ImagePlus createEqualizeImage(ImagePlus imp) {
		ImagePlus impEqualize = NewImage.createByteImage("Egalisation " + imp.getTitle(),
				imp.getWidth(), imp.getHeight(), 1, NewImage.GRAY8);
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

	/**
	 * Créer une image avec la méthode OTSU de seuillage
	 * 
	 * @param imp Image
	 * @param inf Borne inférieur
	 * @param sup Borne supérieur
	 * @return
	 */
	public static ImagePlus createThresholingImage(ImagePlus imp, int inf, int sup) {
		ImagePlus impThresholing = NewImage.createByteImage("Seuillage " + imp.getTitle(),
				imp.getWidth(), imp.getHeight(), 1, NewImage.GRAY8);
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

	/**
	 * Affiche l'histogramme d'une image
	 * 
	 * @param imp
	 * @return
	 */
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

	/**
	 * Récupére l'histgramme d'une image sous forme d'un tableau d'entier
	 * 
	 * @param imp
	 * @return
	 */
	public static int[] getHistogram(ImagePlus imp) {
		int[] histo = new int[256];
		// On parcours l'image sur l'axe des y
		for (int y = 0; y < imp.getHeight(); ++y) {
			// On parcours l'image sur l'axe des x
			for (int x = 0; x < imp.getWidth(); ++x) {
				// On incrémente le nb d'occurence du niveau de gris
				// correspondant
				++histo[getGray(imp.getPixel(x, y))];
			}
		}

		return histo;
	}

	/**
	 * Affiche l'histogramme cumulé d'une image
	 * 
	 * @param imp
	 * @return
	 */
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

	/**
	 * Récupére l'histogramme cumulé d'une image sous forme d'un tableau
	 * d'entier
	 * 
	 * @param imp
	 * @return
	 */
	public static int[] getHistogramCumul(ImagePlus imp) {
		int[] histoCumul = getHistogram(imp);
		for (int i = 1; i < histoCumul.length; ++i)
			histoCumul[i] = histoCumul[i] + histoCumul[i - 1];

		return histoCumul;
	}

	/**
	 * Récupére la couleur grise d'un pixel de couleur
	 * 
	 * @param rgb
	 * @return
	 */
	public static int getGray(int[] rgb) {
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
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new IGSelect();
	}

}
