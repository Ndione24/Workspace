package tp5;

import ij.process.ImageProcessor;

public class Sobel {

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
		// ▽ variation d'intensité d'un pixel
		// ||▽I(x,y)|| = √( Ix²(x,y) + Iy²(x,y) )

		ImageProcessor ipSobX = ip.duplicate();
		new SobelX(ipSobX);
		ImageProcessor ipSobY = ip.duplicate();
		new SobelY(ipSobY);

		final int lig = ip.getWidth(), col = ip.getHeight(); 
		mat = new double[lig][col];

		double pixel_x, pixel_y, grad;
		for (int y = 0; y < col; ++y) {
			for (int x = 0; x < lig; ++x) {
				pixel_x = ipSobX.getPixelValue(x, y);
				pixel_y = ipSobY.getPixelValue(x, y);
				grad = (int)Math.sqrt((pixel_x * pixel_x) + (pixel_y * pixel_y));
				if (grad < 0) grad = 0;
				if (grad > 255) grad = 255;
				mat[x][y] = grad;
			}
		}
    
		/**
		 * Fin de la partie completer
		 */
		// Affichage de la matrice
		if (mat != null) {
			Outils.afficheMatrice(mat, "Sobel", true);
		}
	}
}
