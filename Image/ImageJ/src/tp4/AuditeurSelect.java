package tp4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AuditeurSelect extends Image implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent evt) {
		if (evt.getActionCommand().equals("Gris")) {
			IGImage.createIGImageGray();
		} else if (evt.getActionCommand().equals("Normalisation")) {
			IGImage.createIGImageNormalize();
		} else if (evt.getActionCommand().equals("Egalisation")) {
			IGImage.createIGImageEqualize();
		} else if (evt.getActionCommand().equals("Seuillage")) {
			IGImage.createIGImageThresholding();
		}
	}
	
}
