package tp5;

import ij.ImagePlus;
import ij.gui.ImageWindow;
import ij.gui.NewImage;
import ij.io.Opener;
import ij.process.ImageProcessor;

import java.net.URL;

import tp4.Image;

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
		 * A faire: effectuer la convolution. Reflechir a la question des bords.
		 */
		
		// On récupére les dimensions de l'image
		final int lig = ip.getWidth(), col = ip.getHeight();
		// resultat: la matrice dans laquelle sera stocke le resultat de la convolution.
		double[][] resultat = new double[lig][col]; 
		// matrice: la matrice d'origine créer à partir de l'image
		final double[][] matrice = creerMatrice(ip);
		// On utilise l'effet mirroir pour gérer les bords
		// Miroir : Si un pixel du voisinage est en dehors de l'image d'origine,
		// sa valeur est celle du pixel symétrique par rapport au bord de
		// l'image. C'est à dire : Image[-1][y]= Image[1][y] pour le bord gauche

		// Convolution pour une zone affectée par l'effet de bord
		// J(x,y) = (I(*)M)(x,y) = ∑u=-1->1∑v=-1->1 I(x-u, y-v)*M(u,v)

		// Cas normal : J(x,y) = I(x,y)(*)M(x,y) = ∑∑I(x+u,y+v)*M(u,v)
		// Cas bord : 	J(x,y) = I(x,y)(*)M(x,y) = ∑∑I(x-u,y-v)*M(u,v)
		int X, Y, rayon = masque.getRayon();
		for (int y = 0; y < col; ++y) {
			for (int x = 0; x < lig; ++x) {
				for (int v = -rayon; v <= rayon; ++v) {
					for (int u = -rayon; u <= rayon; ++u) {
						X = x + u; Y = y + v;
						// Gestion du bord gauche et droite
						if (X < 0 || X >= lig) X = x - u;
						// Gestion du bord haut et bas
						if (Y < 0 || Y >= col) Y = y - v;
						resultat[x][y] += (double) (matrice[X][Y] * masque.get(u, v));
					}
				}
			}
		}

		/**
		 * Fin de la partie a completer
		 */
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
			boolean normaliser) {
		ImagePlus imp = NewImage.createByteImage(titre, mat.length,
				mat[0].length, 1, NewImage.FILL_BLACK);
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
						ip.putPixel(x, y, (int) ((255 * (mat[x][y] - min)) / (max - min)));
					}
				}
			}
		} else {
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
  	
	/** Créer une matrice de double à partir d'une image */
  	public static double[][] creerMatrice(ImageProcessor ip) {
		// On récupére les dimensions de l'image
		final int hauteur = ip.getHeight(), largeur = ip.getWidth();
		// matrice : matrice qui contiendra le résultat
		double[][] matrice = new double[largeur][hauteur];

		for (int y = 0; y < hauteur; ++y)
			for (int x = 0; x < largeur; ++x)
				matrice[x][y] = (int) ip.getPixelValue(x, y);

		return matrice;
	}

  	/** Affiche le contenu de la matrice dans la console */
	public static void afficherMatrice(double[][] matrice) {
		// On récupére les dimensions de la matrice
		final int largeur = matrice.length, hauteur = matrice[0].length;
		for (int y = 0; y < hauteur; ++y) {
			for (int x = 0; x < largeur; ++x)
				System.out.print(matrice[x][y] + "\t");
			System.out.println();
		}
	}
	
	/** Affiche le contenu dans masque dans la console */
	public static void afficherMasque(Masque masque) {
		final int rayon = masque.getRayon();
		for (int y = -rayon; y <= rayon; ++y) {
			for (int x = -rayon; x <= rayon; ++x) {
				System.out.print(masque.get(x, y) + " ");				
			}
			System.out.println();
		}
	}
	
	/** Récupére l'image comme une ressource pour être compatible avec JAR */
	public static ImagePlus openImage(String name) {
		URL url = Image.class.getResource("/"+ name);
		if (null == url) throw new NullPointerException("Impossible de trouver l'image " + name);
		return new Opener().openURL(url.toString());
	}

}
