package tp5;

import ij.*;
import ij.gui.*;
import ij.process.*;


public class FiltreMedian{
  public FiltreMedian(ImageProcessor ip) {
    GenericDialog gd = new GenericDialog("Options du filtre median");
    gd.addNumericField("Rayon du voisinage", 1, 0);
    gd.showDialog();
    if (gd.wasCanceled()) return;

    // rayon: rayon du voisinage considere pour chaque pixel
    // Autrement dit, le voisinage est un carre de cote (2 * rayon + 1)
    int rayon = (int) gd.getNextNumber();

    ImagePlus impMedian = NewImage.createByteImage("Filtre median",
        ip.getWidth(), ip.getHeight(), 1, NewImage.FILL_BLACK);

    // ipMedian est l'image dans laquelle vous stockerez le
    // resultat du filtre median
    ImageProcessor ipMedian = impMedian.getProcessor();

    /**
     * A faire: appliquer un filtre median a l'image ip en considerant
     * un voisinage dont la taille est definie plus haut, et stoker le
     * resultat dans l'image 'ipMedian'.
     */
	final int lig = ip.getWidth(), col = ip.getHeight();
	double res;
	for (int y = 0; y < col; ++y) {
		for (int x = 0; x < lig; ++x) {
			res = Outils.getMedian(ip, x, y, rayon);
			ipMedian.putPixelValue(x, y, res);
		}
	}

    /**
     * Fin de la partie a completer
     */
    new ImageWindow(impMedian);
  }
}
