package IGImage;

import ij.ImagePlus;
import ij.process.ImageProcessor;
import tp5.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class AuditeurFiltre implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent evt) {
        // TODO Auto-generated method stub
        ImagePlus imp = Outils.openImage("lenna.png");
        ImageProcessor ip = imp.getProcessor();
        if (evt.getActionCommand().equals("Moyenneur")) {
            new FiltreMoyen(ip);
        } else if (evt.getActionCommand().equals("Median")) {
            new FiltreMedian(ip);
        } else if (evt.getActionCommand().equals("Gaussien")) {
            new FiltreGaussien(ip);
        } else if (evt.getActionCommand().equals("Sobel")) {
            new Sobel(ip);
        }
    }

}
