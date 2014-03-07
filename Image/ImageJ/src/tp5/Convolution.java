package tp5;

import ij.IJ;
import ij.ImagePlus;
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
	
	public static double[][] creerMatrice(ImagePlus imp) {
		// On récupére les dimensions de l'image
		int hauteur = imp.getHeight(), largeur = imp.getWidth();
		double[][] matrice = new double [largeur][hauteur];

		for (int y = 0; y < hauteur; ++y)
			for (int x = 0; x < largeur; ++x)
				matrice[x][y] = Image.getGray(imp.getPixel(x, y));
		
		return matrice;
	}
	
	public static void main(String[] args) {
		ImagePlus imp = IJ.openImage("images/matrice.png");
		double[][] matrice = creerMatrice(imp);
		afficherMatrice(matrice);
	}
}
