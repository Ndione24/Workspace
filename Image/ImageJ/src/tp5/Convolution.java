package tp5;

import ij.ImagePlus;
import ij.process.ImageProcessor;

public class Convolution {
	
	public static void main(String[] args) {
		ImagePlus imp = Outils.openImage("matrice.png");
		ImageProcessor ip = imp.getProcessor();

		// Application du filtre moyen sur l'image
		new FiltreMoyen(ip);
		imp.show();
		
	}
}
