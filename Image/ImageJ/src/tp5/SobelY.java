package tp5;

import ij.process.ImageProcessor;

/**
 * Rien a completer, juste a tester.
 * (Encore faut-il que la methode Outils.convoluer(...) fonctionne.)
 * Le masque de convolution est le suivant:
 * |-1 -2 -1 |
 * Sy = | 0  0  0 |
 * | 1  2  1 |
 */
public class SobelY {
    public SobelY(ImageProcessor ip) {
        Masque sobelY = new Masque(1);

        sobelY.put(-1, -1, -1);
        sobelY.put(0, -1, -2);
        sobelY.put(1, -1, -1);
        sobelY.put(-1, 1, 1);
        sobelY.put(0, 1, 2);
        sobelY.put(1, 1, 1);

        double[][] mat = Outils.convoluer(ip, sobelY);

        Outils.afficheMatrice(mat, "Sobel Y", true);
    }
}
