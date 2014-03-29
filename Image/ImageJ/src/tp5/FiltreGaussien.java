package tp5;

import ij.gui.GenericDialog;
import ij.process.ImageProcessor;

public class FiltreGaussien {

    public FiltreGaussien(ImageProcessor ip) {
        GenericDialog gd = new GenericDialog("Options du flou gaussien");
        gd.addNumericField("Rayon du masque", 1, 0);
        gd.showDialog();
        if (gd.wasCanceled())
            return;

        // rayon: rayon du masque gaussien
        int rayon = (int) gd.getNextNumber();

        /**
         * A faire: convoluer l'image 'ip' avec un masque gaussien dont
         * la taille et l'ecart-type sont precises plus haut.
         * Entree: ip (ImageProcessor)
         * Sortie: mat (double[][])
         * Contrainte: la somme de tous les elements du masque doit etre
         * egale a 1, pour ne pas sortir du domaine de definition des
         * pixels [0..255]
         */

        Masque masque = creerMasque(rayon);
        double[][] mat = Outils.convoluer(ip, masque);

        // affichage de la matrice
        Outils.afficheMatrice(mat, "Filtre gaussien", false);
    }

    public FiltreGaussien(ImageProcessor ip, int rayon) {
        Masque masque = creerMasque(rayon);
        double[][] mat = Outils.convoluer(ip, masque);
        Outils.appliquerMatrice(mat, ip);
    }

    /**
     * creer un masque gaussien
     */
    private Masque creerMasque(int rayon) {
        Masque kernel = new Masque(rayon);
        // pour avoir une "cloche" a peu pres complete,
        // on choisit un ecart-type egal a un tiers du rayon.
        double sigma = rayon / 3.0;

        double gaussianKernelFactor = 0, e = 0;
        for (int ky = -rayon; ky <= rayon; ++ky) {
            for (int kx = -rayon; kx <= rayon; ++kx) {
                // G (x,y) = exp ( -(x²+y²) / 2 sigma² )
                e = Math.exp(-(kx * kx + ky * ky) / (2 * sigma * sigma));
                gaussianKernelFactor += e;
                kernel.put(kx, ky, e);
            }
        }

		/*
		 * On divise tout par le facteur afin que la somme de tous les éléments
		 * soient égaux à 1
		 */
        double value;
        for (int ky = -rayon; ky <= rayon; ++ky) {
            for (int kx = -rayon; kx <= rayon; ++kx) {
                value = kernel.get(kx, ky);
                kernel.put(kx, ky, value / gaussianKernelFactor);
            }
        }

        return kernel;
    }

}
