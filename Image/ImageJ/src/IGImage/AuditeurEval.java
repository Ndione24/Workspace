package IGImage;

import ij.ImagePlus;
import tp5.Outils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuditeurEval implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent evt) {
        ImagePlus imp = Outils.openImage("Image_OK_" + evt.getActionCommand() + ".jpg");
        tpnote.Traitement.traiter(imp);
    }

}
