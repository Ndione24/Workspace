package tp4;

import ij.ImagePlus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AuditeurSelect extends Image implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent evt) {
		if (evt.getActionCommand().equals("Gris")) {
			ImagePlus imp = openImage("lenna.png");
			ImagePlus imp2 = createGrayImage(imp);
			new IGImage("Gris", new PanelImage(imp, imp2));
		} else if (evt.getActionCommand().equals("Normalisation")) {
			ImagePlus imp = openImage("enhance-me.png");
			ImagePlus imp2 = createNormalizeImage(imp);
			new IGImage("Normalisation", new PanelImage(imp, imp2));
		} else if (evt.getActionCommand().equals("Egalisation")) {
			ImagePlus imp = openImage("montagne.jpg");
			ImagePlus imp2 = createEqualizeImage(imp);
			new IGImage("Egalisation", new PanelImage(imp, imp2));
		} else if (evt.getActionCommand().equals("Seuillage")) {
			ImagePlus imp = openImage("neige.jpg");
			ImagePlus imp2 = createThresholingImage(imp, 125);
			new IGImage("Seuillage", new PanelImage(imp, imp2));
		}
	}
	
}
