package tp5;

import ij.ImagePlus;
import ij.gui.ImageWindow;
import ij.gui.NewImage;
import ij.process.ImageProcessor;

/**
 * Quelques methodes statiques qui seront utiles
 * pour le deroulement du TP.
 */
public class Outils {
  /**
   * Effectue la convolution de l'image 'ip' avec un masque carre.
   * Le resultat d'un produit de convolution n'est pas forcement dans le meme
   * domaine de definition que l'image d'origine. C'est pourquoi le resultat
   * est stocke dans une matrice de nombres reels.
   * @param ip		L'image a convoluer.
   * @param masque	Le masque de convolution.
   * @return		La matrice resultat.
   */
  public static double[][] convoluer(ImageProcessor ip, Masque masque) {
    /**
     * A faire: effectuer la convolution.
     * Reflechir a la question des bords.
     */
	int lig = ip.getWidth(), col = ip.getHeight();
    // resultat: la matrice dans laquelle sera stocke le resultat de la convolution.
	// matrice:  la matrice d'origine créer à partir de l'image
    double[][] resultat = new double[lig][col], matrice = creerMatrice(ip);
    // On utilise l'effet mirroir pour gérer les bords
    // Miroir : Si un pixel du voisinage est en dehors de l'image d'origine,
	// sa valeur est celle du pixel symétrique par rapport au bord de
	// l'image. C'est à dire : Image[-1][y]= Image[1][y] pour le bord gauche

    // Convolution, zone affectée par l'effet de bord
    // J(x,y) = (I(*)M)(x,y) = ∑u=-1->1∑v=-1->1 I(x-u, y-v)*M(u,v)
    int a, b, res = 0, rayon = masque.getRayon();
    for (int y = 0; y < col; ++y) {
		for (int x = 0; x < lig; ++x) {
			for (int v = -rayon; v < rayon+1; ++v) {
				for (int u = -rayon; u < rayon+1; ++u) {
					a = x+u; b = y+v;
					// Gestion du bord gauche et droite
					if (a < 0 || a >= lig) { a = x-u; }
					// Gestion du bord haut et bas
					if (b < 0 || b >= col) { b = y-v; }
					res += matrice[a][b] * masque.get(u, v);
				}
			}
			resultat[x][y] = res/9;
			res = 0;
		}
	}

    return resultat;
  }

  /**
   * Affiche une matrice de nombres reels dans une nouvelle fenetre.
   * Comme les elements de cette matrice ne sont pas forcement dans le domaine [0..255],
   * on a le choix entre:
   * 1) normaliser: c'est-a-dire faire une mise a l'echelle de maniere a ce que la valeur
   * la plus faible soit 0 et la valeur la plus haute 255. (voir TP: etirement d'histogramme).
   * 2) ne pas normaliser: tous les elements dont la valeur est inferieure a 0 sont fixes a 0
   * et tous les elements dont la valeur est superieure a 255 sont fixes a 255.
   * @param mat            La matrice a afficher.
   * @param titre          Le titre de la fenetre.
   * @param normaliser     Faut-il normaliser ?
   */
  public static void afficheMatrice(double[][] mat, String titre,
                                    boolean normaliser)
  {
    ImagePlus imp = NewImage.createByteImage(
        titre, mat.length, mat[0].length, 1, NewImage.FILL_BLACK);
    ImageProcessor ip = imp.getProcessor();

    if (normaliser) {
      double max = mat[0][0];
      double min = mat[0][0];
      for (int y = 0; y < mat[0].length; y++) {
        for (int x = 0; x < mat.length; x++) {
          if (mat[x][y] > max) max = mat[x][y];
          if (mat[x][y] < min) min = mat[x][y];
        }
      }

      if (min != max) {
        for (int y = 0; y < mat[0].length; y++) {
          for (int x = 0; x < mat.length; x++) {
            ip.putPixel(x, y, (int) ( (255 * (mat[x][y] - min)) / (max - min)));
          }
        }
      }
    }
    else {
      for (int y = 0; y < mat[0].length; y++) {
        for (int x = 0; x < mat.length; x++) {
          int p = (int) Math.min(mat[x][y], 255);
          p = Math.max(p, 0);
          ip.putPixel(x, y, p);
        }
      }
    }

    new ImageWindow(imp);
  }
  
	public static double[][] creerMatrice(ImageProcessor ip) {
		// On récupére les dimensions de l'image
		int hauteur = ip.getHeight(), largeur = ip.getWidth();
		double[][] matrice = new double [largeur][hauteur];

		for (int y = 0; y < hauteur; ++y)
			for (int x = 0; x < largeur; ++x)
				matrice[x][y] = ip.getPixelValue(x, y);
		
		return matrice;
	}
}
