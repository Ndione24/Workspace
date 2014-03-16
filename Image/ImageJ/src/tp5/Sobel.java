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
		
		int lig = ip.getWidth(), col = ip.getHeight();
		
		double[][] pic = Outils.creerMatrice(ip); 		// matrice d'origine
		double[][] outpicx = new double[lig][col];		// matrice de transition horizontal
		double[][] outpicy = new double[lig][col];		// matrice de transition vertical
		mat = new double[lig][col];						// matrice de sortie
		int[][] maskx = {{-1,0,1},{-2,0,2},{-1,0,1}}; 	// masque horizontal 
		int[][] masky = {{1,2,1},{0,0,0},{-1,-2,-1}}; 	// masque vertical 
		double maxival;									// variable temporelle
		
		int i,j,p,q,sum1,sum2;
		final int mr = 1;

		for (i=mr; i<lig-mr; i++) {
		    for (j=mr; j<col-mr; j++) {
		        sum1 = 0; sum2 = 0;
		        for (p=-mr; p<=mr; p++) {
		            for (q=-mr;q<=mr;q++) {
		                sum1 += pic[i+p][j+q] * maskx[p+mr][q+mr]; // calculer GHorizontal
		                sum2 += pic[i+p][j+q] * masky[p+mr][q+mr]; // calculer GVertical
		            }
		        }
		        outpicx[i][j] = sum1;
		        outpicy[i][j] = sum2;
		    }
		}

		// Calcule de la norme du gradient
		maxival = 0;
		for (i=mr; i<lig-mr; i++) {
		    for (j=mr; j<col-mr; j++) {
		        mat[i][j]=Math.sqrt((double)((outpicx[i][j]*outpicx[i][j]) +
		                       (outpicy[i][j]*outpicy[i][j])));
		        if (mat[i][j] > maxival) maxival = mat[i][j];
		    }
		}

		// Filter l’image de sortie (supprimer les overflow)
		for (i=0; i<lig; i++)
		    for (j=0; j<col; j++)
		       mat[i][j] = (mat[i][j] / maxival) * 255;

		/**
		 * Fin de la partie completer
		 */
		// Affichage de la matrice
		if (mat != null) {
			Outils.afficheMatrice(mat, "Sobel", true);
		}
	}
}
