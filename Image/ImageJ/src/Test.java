import ij.ImagePlus;
import ij.gui.HistogramWindow;
import ij.io.Opener;
import ij.process.ImageProcessor;
import ij.process.ImageStatistics;

public class Test {

	public static int[] changePixelToGray(int[] rgb) {
		int gray = (rgb[0]+rgb[1]+rgb[2])/3;
		int[] tab = {gray, gray, gray};
		return tab;
	}
	
	public static void convertToGray(ImagePlus imp) {
		ImageProcessor impp = imp.getProcessor();
		int[] rgb;
		for (int i = 0; i < imp.getWidth(); i++) {
			for (int j = 0; j < imp.getHeight(); j++) {
				rgb = changePixelToGray(imp.getPixel(i, j));
				impp.putPixel(i, j, rgb);
			}
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Opener opener = new Opener();  
		String imageFilePath = "/users/licence/if00538/Images/3D.jpg";
		ImagePlus imp = opener.openImage(imageFilePath);
		convertToGray(imp);
		imp.show();
		HistogramWindow hw = new HistogramWindow(imp);
		hw.setVisible(true);
	}

}
