package tp5;

import ij.gui.*;
import ij.process.*;

public class FiltreGaussien{
public FiltreGaussien(ImageProcessor ip) {
    GenericDialog gd = new GenericDialog("Options du flou gaussien");
    gd.addNumericField("Rayon du masque", 1, 0);
    gd.showDialog();
    if (gd.wasCanceled()) return;

    // rayon: rayon du masque gaussien
    int rayon = (int) gd.getNextNumber();

    // pour avoir une "cloche" a peu pres complete,
    // on choisit un ecart-type egal a un tiers du rayon.
    double sigma = rayon / 3.0;

    /**
     * A faire: convoluer l'image 'ip' avec un masque gaussien dont
     * la taille et l'ecart-type sont precises plus haut.
     * Entree: ip (ImageProcessor)
     * Sortie: mat (double[][])
     * Contrainte: la somme de tous les elements du masque doit etre
     * egale a 1, pour ne pas sortir du domaine de definition des
     * pixels [0..255]
     */
    // mat: une matrice pour stocker le resultat de la convolution.
    double[][] mat = null;



    // remplir ici



    /**
     * Fin de la partie a completer
     */
    // affichage de la matrice
    if (mat != null) {
      Outils.afficheMatrice(mat, "Filtre gaussien", false);
    }
  }

}
