package tpnote;

import ij.ImagePlus;
import ij.process.ImageProcessor;
import tp5.FiltreMoyen;
import tp5.Outils;

public class Traitement extends Thread {

    private ImagePlus imp;

    public Traitement(ImagePlus imp) {
        this.imp = imp;
    }

    public static void traiter(ImagePlus imp) {
        Thread thread = new Traitement(imp);
        thread.start();
    }

    @Override
    /** Le traitement est assez long, on le place dans un thread pour ne pas bloquer l'IG */
    public void run() {
        imp = tp4.Image.createGrayImage(imp);
        ImageProcessor ip = imp.getProcessor();
        new FiltreMoyen(ip, 5);
        imp = tp4.Image.createOTSUImage(imp);
        imp.show();
    }

    public static void main(String[] args) {
        ImagePlus imp = Outils.openImage("i1.jpg");
        Traitement tr = new Traitement(imp);
        tr.run();
    }
}
