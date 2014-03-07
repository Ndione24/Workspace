package tp5;

import ij.process.*;

/**
 * Rien a completer, juste a tester.
 * (Encore faut-il que la methode Outils.convoluer(...) fonctionne.)
 * Le masque de convolution est le suivant:
 *      |-1  0  1 |
 * Sx = |-2  0  2 |
 *      |-1  0  1 |
 */
public class SobelX{
  public SobelX(ImageProcessor ip) {
    Masque sobelX = new Masque(1);

    sobelX.put( -1, -1, -1);
    sobelX.put(1, -1, 1);
    sobelX.put( -1, 0, -2);
    sobelX.put(1, 0, 2);
    sobelX.put( -1, 1, -1);
    sobelX.put(1, 1, 1);

    double[][] mat = Outils.convoluer(ip, sobelX);

    Outils.afficheMatrice(mat, "Sobel X", true);
  }
}
