package IGImage;

import ij.ImagePlus;
import ij.process.ImageProcessor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import tp5.FiltreGaussien;
import tp5.FiltreMedian;
import tp5.FiltreMoyen;
import tp5.Outils;
import tp5.Sobel;

public class AuditeurSelectFiltre implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		if (evt.getActionCommand().equals("Moyenneur")) {
			ImagePlus imp = Outils.openImage("lenna_noiser.png");
			ImageProcessor ip = imp.getProcessor();
			new FiltreMoyen(ip);
		}
		else if (evt.getActionCommand().equals("Median")) {
			ImagePlus imp = Outils.openImage("lenna_noiser.png");
			ImageProcessor ip = imp.getProcessor();
			new FiltreMedian(ip);
		}
		else if (evt.getActionCommand().equals("Gaussien")) {
			ImagePlus imp = Outils.openImage("lenna_noiser.png");
			ImageProcessor ip = imp.getProcessor();
			new FiltreGaussien(ip);
		}
		else if (evt.getActionCommand().equals("Sobel")) {
			ImagePlus imp = Outils.openImage("biche.jpg");
			ImageProcessor ip = imp.getProcessor();
			imp.show();
			new Sobel(ip);
		}
	}

}
