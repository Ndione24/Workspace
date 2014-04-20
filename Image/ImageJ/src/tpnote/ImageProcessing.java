package tpnote;

import ij.ImagePlus;
import ij.process.ImageConverter;
import ij.process.ImageProcessor;
import tp5.FiltreMedian;

/**
 * Created by melkir on 20/04/14.
 */
public class ImageProcessing implements Runnable {

    ImagePlus imp;

    public ImageProcessing(ImagePlus imp) {
        this.imp = imp;
    }

    @Override
    public void run() {
        ImageConverter ic = new ImageConverter(imp);
        ic.convertToGray8();
        ImageProcessor ip = imp.getProcessor();
        ip.autoThreshold();
        new FiltreMedian(ip, 2);
    }
}
