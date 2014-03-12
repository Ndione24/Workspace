package tp5;

import ij.ImagePlus;
import ij.process.ImageProcessor;

public class Convolution {
	
	public static void main(String[] args) {
		ImagePlus imp = Outils.openImage("matrice.png");
		ImageProcessor ip = imp.getProcessor();

		Masque masque = new Masque(1);
		masque.remplirAvec(1);
		
		double mat[][] = Outils.convoluer(ip, masque);
		Outils.afficherMatrice(mat);
		// Application du filtre moyen sur l'image
//		new FiltreMoyen(ip);
//		imp.show();
		
	}
}
