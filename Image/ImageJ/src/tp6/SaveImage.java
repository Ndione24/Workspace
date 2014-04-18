package tp6;

import ij.ImagePlus;
import ij.io.FileSaver;
import ij.process.ImageConverter;
import ij.process.ImageProcessor;
import tp5.FiltreMedian;
import tp5.Outils;

/**
 * Created by melkir on 03/04/14.
 */
public class SaveImage {

    private static void save(ImagePlus imp) {
        FileSaver fs = new FileSaver(imp);
        fs.saveAsJpeg();
    }

    public static void main(String[] args) {
/*        ImagePlus imp = Image.openImage("i1.jpg");
        save(Image.createOTSUImage(imp));*/
        ImagePlus imp = Outils.openImage("i2Binaire.jpg");

        ImageConverter ic = new ImageConverter(imp);
        ic.convertToGray8();
        ImageProcessor ip = imp.getProcessor();
        new FiltreMedian(ip, 5);
        imp = tp4.Image.createOTSUImage(imp);

        save(imp);
    }
}
