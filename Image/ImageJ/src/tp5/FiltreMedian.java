package tp5;

import ij.ImagePlus;
import ij.gui.GenericDialog;
import ij.gui.ImageWindow;
import ij.gui.NewImage;
import ij.process.ImageProcessor;

import java.util.Arrays;

public class FiltreMedian {

    /**
     * Ce filtre est efficace contre du bruit poivre et sel dans des images à niveaux de gris
     */
    public FiltreMedian(ImageProcessor ip) {
        GenericDialog gd = new GenericDialog("Options du filtre median");
        gd.addNumericField("Rayon du voisinage", 1, 0);
        gd.showDialog();
        if (gd.wasCanceled())
            return;

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
                res = getMedian(ip, x, y, rayon);
                ipMedian.putPixelValue(x, y, res);
            }
        }

        /**
         * Fin de la partie a completer
         */
        new ImageWindow(impMedian);
    }

    public FiltreMedian(ImageProcessor ip, int rayon) {
        final int lig = ip.getWidth(), col = ip.getHeight();
        double res;
        for (int y = 0; y < col; ++y) {
            for (int x = 0; x < lig; ++x) {
                res = getMedian(ip, x, y, rayon);
                ip.putPixelValue(x, y, res);
            }
        }
    }

    /**
     * Renvoi le médian d'un pixel en fonction de ses voisins
     */
    private double getMedian(ImageProcessor ip, int x, int y, int rayon) {
        return getMedian(getPixelsVoisins(ip, x, y, rayon));
    }

    /**
     * Renvoi la médiane d'un tableau de double
     */
    private double getMedian(double[] value) {
        Arrays.sort(value);
        return value[value.length / 2];
    }

    /**
     * Renvoi un tableau de double contenant les pixels voisins au point x, y
     * avec gestion des bords
     */
    private double[] getPixelsVoisins(ImageProcessor ip, int x, int y, int rayon) {
        // On récupére les dimensions de l'image
        final int lig = ip.getWidth(), col = ip.getHeight();
        final int voisin = rayon * 2 + 1;
        double[] res = new double[voisin * voisin];

        int X, Y, i = 0;
        for (int v = -rayon; v <= rayon; ++v) {
            for (int u = -rayon; u <= rayon; ++u) {
                X = x + u;
                Y = y + v;
                // Gestion du bord gauche et droite
                if (X < 0 || X >= lig) X = x - u;
                // Gestion du bord haut et bas
                if (Y < 0 || Y >= col) Y = y - v;
                res[i++] = ip.getPixelValue(X, Y);
            }
        }
        return res;
    }

}
