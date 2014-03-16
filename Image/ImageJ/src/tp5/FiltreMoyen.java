package tp5;

import ij.gui.GenericDialog;
import ij.process.ImageProcessor;

public class FiltreMoyen {
	
	/** Elimine les bruits impusionnels en contre partie on perd des détails l'image */
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
		Masque masque = creerMasque(rayon);
		mat = Outils.convoluer(ip, masque);
		
		/**
		 * Fin de la partie a completer
		 */
		// Affichage de la matrice
		if (mat != null) {
			Outils.afficheMatrice(mat, "Filtre moyen", false);
		}
	}
	
	public FiltreMoyen(ImageProcessor ip, int rayon) {
		Masque masque = creerMasque(rayon);
		double [][] mat = Outils.convoluer(ip, masque);
		Outils.appliquerMatrice(mat, ip);
	}
	
	public static Masque creerMasque(int rayon) {
		// Vérification des arguments
		if (rayon < 1) throw new Error("bad argument for rayon");
		// Création du masque vide
		Masque masque = new Masque(rayon);
		// Récupération de la taille du masque
		final int taille = masque.getLargeur() * masque.getLargeur();
		masque.remplirAvec((double) 1/taille);
		
		return masque;
	}

}
