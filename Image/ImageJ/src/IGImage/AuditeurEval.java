package IGImage;

import ij.ImagePlus;
import tp5.Outils;
import tpnote.ImageProcessing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class AuditeurEval implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent evt) {
        ImagePlus imp = Outils.openImage("Image_OK_" + evt.getActionCommand() + ".jpg");
        ImageProcessing processing = new ImageProcessing(imp);
        processing.run();
        imp.show();
    }

}
