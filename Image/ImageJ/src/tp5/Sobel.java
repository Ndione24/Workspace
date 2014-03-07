package tp5;

import ij.*;
import ij.process.*;

public class Sobel{
@SuppressWarnings("unused")
public Sobel(ImageProcessor ip) {
    /**
     * A faire: remplir la matrice mat avec la norme
     * du gradient de Sobel de l'image 'ip'.
     * Entree: ip (ImageProcessor)
     * Sortie: mat (double[][])
     * Contrainte: utiliser la norme euclidienne.
     */
    // mat: la matrice a remplir
    double[][] mat = null;
    
	
    // remplir ici


    /**
     * Fin de la partie  completer
     */
    // Affichage de la matrice
    if (mat != null) {
      Outils.afficheMatrice(mat, "Sobel", true);
    }
  }
}
