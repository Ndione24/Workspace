package tp5;

import ij.gui.GenericDialog;
import ij.process.ImageProcessor;

public class FiltreMoyen {
	
	/** Elimine le bruit en contre partie on perd des détails sur l'image */
	public FiltreMoyen(ImageProcessor ip) {
		GenericDialog gd = new GenericDialog("Options du filtre moyenneur");
		gd.addNumericField("Rayon du masque", 1, 0);
		gd.showDialog();
		if (gd.wasCanceled())
			return;

		// rayon: rayon du masque moyenneur
		int rayon = (int) gd.getNextNumber();

	    /**
	     * A faire: convoluer l'image 'ip' avec un masque moyenneur.
	     * Entree: ip (ImageProcessor)
	     * Sortie: mat (double[][])
	     * Contrainte: la somme de tous les elements du masque doit etre
	     * egale a 1, pour ne pas sortir du domaine de definition des
	     * pixels [0..255]
	     */
		
		// mat: une matrice pour stocker le resultat de la convolution
		double[][] mat = null;

		// Création du masque moyenneur
		Masque masque = new Masque(rayon);
		// largeur : Largeur du masque 
		// N : nombre de valeur du masque
		final int largeur = masque.getLargeur(), N = largeur * largeur;;
		// Somme des valeurs du masque égal à 1
		masque.remplirAvec((double)1/N);

		mat = Outils.convoluer(ip, masque);

		/**
		 * Fin de la partie a completer
		 */
		// Affichage de la matrice
		if (mat != null) {
			Outils.afficheMatrice(mat, "Filtre moyen", false);
		}
	}

}
