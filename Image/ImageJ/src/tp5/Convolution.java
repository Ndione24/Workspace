package tp5;

import ij.IJ;
import ij.ImagePlus;
import ij.io.Opener;
import ij.process.ImageProcessor;

import java.net.URL;

import tp4.Image;

public class Convolution {
	
	public static void afficherMatrice(double[][] matrice) {
		// On récupére les dimensions de la matrice
		int largeur = matrice.length, hauteur = matrice[0].length;
		for (int y = 0; y < hauteur; y++) {
			for (int x = 0; x < largeur; x++) 
				System.out.print(matrice[x][y] + "\t");
			System.out.println();
		}
	}
	
	public static double[][] creerMatrice(ImageProcessor ip) {
		// On récupére les dimensions de l'image
		int hauteur = ip.getHeight(), largeur = ip.getWidth();
		double[][] matrice = new double [largeur][hauteur];

		for (int y = 0; y < hauteur; ++y)
			for (int x = 0; x < largeur; ++x)
				matrice[x][y] = (int) ip.getPixelValue(x, y);
		
		return matrice;
	}
	
	public static void main(String[] args) {
		URL url = Convolution.class.getResource("/matrice.png");
		ImagePlus imp = new Opener().openURL(url.toString());
		ImageProcessor ip = imp.getProcessor();
		
		double[][] matrice = creerMatrice(ip);
		afficherMatrice(matrice);
		
		Masque masque = new Masque(1);
		masque.remplirAvec(1);
		Outils.convoluer(ip, masque);
		
	}
}
