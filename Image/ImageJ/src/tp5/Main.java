package tp5;

import ij.ImagePlus;
import ij.process.ImageProcessor;

public class Main extends Outils {
	
	public static void main(String[] args) {
		ImagePlus imp = openImage("lenna_noiser.png");
//		ImagePlus imp = openImage("matrice.png");
		ImageProcessor ip = imp.getProcessor();
//		imp.show();
		// Application du filtre moyen sur l'image
//		new FiltreMoyen(ip);
//		imp.show();
		// Application du filtre median sur l'image
		new FiltreMedian(ip);
		imp.show();
	}
}
