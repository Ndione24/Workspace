package IGImage;

import ij.ImagePlus;
import ij.gui.NewImage;
import ij.process.ImageProcessor;
import tp4.Image;
import tp5.FiltreGaussien;
import tp5.FiltreMedian;
import tp5.Outils;
import tp6.ElementStructurant;
import tp6.Morpho;
import tpnote.Traitement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by melkir on 03/04/14.
 */
class AuditeurMorpho implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent evt) {
        // Création des images d'entrée et de sortie
        ImagePlus imp = Outils.openImage("i1.jpg");
        ImagePlus imp2 = NewImage.createByteImage(imp.getTitle(), imp.getWidth(), imp.getHeight(), 1, NewImage.GRAY8);

        new Traitement(imp);
        imp = Image.createGrayImage(imp);
        new FiltreMedian(imp.getProcessor(), 3);
        new FiltreGaussien(imp.getProcessor(), 11);

        // On binarise l'image d'entrée
        imp = Image.createOTSUImage(imp);
        // On récupére les processor et on créer l'élément structurant
        ImageProcessor ip = imp.getProcessor();
        ImageProcessor ip2 = imp2.getProcessor();
        ElementStructurant es = ElementStructurant.creerRectangle4connexe();
        // On applique une morphologie binaire
        String cmd = evt.getActionCommand();
        String title = null;
        if (cmd.equals("Dilatation")) {
            Morpho.dilatation(ip, es, ip2);
            title = "dilatation";
        } else if (cmd.equals("Erosion")) {
            Morpho.erosion(ip, es, ip2);
            title = "erosion";
        } else if (cmd.equals("Ouverture")) {
            Morpho.ouverture(ip, es, ip2);
            title = "ouverture";
        } else if (cmd.equals("Fermeture")) {
            Morpho.fermeture(ip, es, ip2);
            title = "fermeture";
        }
        // On visualise la modification
        new IGImage("Morphologie binaire " + title, new Panel2Image(imp, imp2));
    }
}
