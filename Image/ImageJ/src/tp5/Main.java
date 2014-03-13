package tp5;

import ij.ImagePlus;
import ij.process.ImageProcessor;

public class Main extends Outils {
	
	public static void main(String[] args) {
		// On prend une image bruité
		ImagePlus imp = openImage("lenna_noiser.png");
		ImageProcessor ip = imp.getProcessor();
		imp.show();
		// Application du filtre moyen sur l'image
		new FiltreMoyen(ip);
		// Application du filtre median sur l'image
		new FiltreMedian(ip);
		// Application du filtre gaussien sur l'image
		new FiltreGaussien(ip);
		// On change d'image pour en avoir une qui soit nette
		imp = openImage("biche.jpg");
		ip = imp.getProcessor();
		// Application du filtre Sobel sur la nouvelle image
		imp.show();
		new Sobel(ip);
	}
}
