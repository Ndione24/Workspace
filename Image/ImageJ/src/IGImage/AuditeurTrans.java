package IGImage;

import ij.ImagePlus;
import ij.gui.GenericDialog;
import tp4.Image;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class AuditeurTrans extends Image implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent evt) {
        ImagePlus imp, imp2;
        if (evt.getActionCommand().equals("Gris")) {
            imp = openImage("lenna.png");
            imp2 = createGrayImage(imp);
            new IGImage("Gris", new PanelImage(imp, imp2));
        } else if (evt.getActionCommand().equals("Normalisation")) {
            imp = openImage("enhance-me.png");
            imp2 = createNormalizeImage(imp);
            new IGImage("Normalisation", new PanelImage(imp, imp2));
        } else if (evt.getActionCommand().equals("Egalisation")) {
            imp = openImage("montagne.jpg");
            imp2 = createEqualizeImage(imp);
            new IGImage("Egalisation", new PanelImage(imp, imp2));
        } else if (evt.getActionCommand().equals("Binarisation")) {
            imp = openImage("neige.jpg");
            GenericDialog gd = new GenericDialog("Options du seuillage");
            gd.addNumericField("Seuil", 125, 0);
            gd.showDialog();
            if (gd.wasCanceled()) return;
            // rayon: rayon du masque moyenneur
            int rayon = (int) gd.getNextNumber();
            imp2 = createBinaryImage(imp, rayon);
            new IGImage("Seuillage", new PanelImage(imp, imp2));
        } else if (evt.getActionCommand().equals("Otsu")) {
            imp = openImage("neige.jpg");
            imp2 = createOTSUImage(imp);
            new IGImage("Seuillage OTSU", new PanelImage(imp, imp2));
        }
    }

}
