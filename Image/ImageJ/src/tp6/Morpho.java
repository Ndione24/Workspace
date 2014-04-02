package tp6;

import ij.ImagePlus;
import ij.gui.NewImage;
import ij.process.ImageProcessor;
import tp4.Image;
import tp5.Outils;

import java.util.Arrays;

public class Morpho {

    private static final int BLANC = 255;
    private static final int NOIR = 0;

    /**
     * Quelques opérations ensemblistes et morphologiques.
     * <p/>
     * IMPORTANT:
     * Dans toutes ces méthodes, on considère qu'un pixel appartient é l'ensemble
     * image si et seulement si sa couleur est noire (0).
     * <p/>
     * Ces méthodes ne doivent être utilisées qu'avec des images binaires
     * (les deux seuls niveaux de gris autorisés sont 0 et 255).
     * <p/>
     * Le code des méthodes dilatation(...) et erosion(...) est à compléter.
     */

    /**
     * Stocke dans 'sortie' l'union de 'entree1' et 'entree2'.
     * 'sortie' peut éventuellement pointer sur l'une des deux entrées.
     *
     * @param entree1 Première image d'entrée.
     * @param entree2 Seconde image d'entrée.
     * @param sortie  Image de sortie.
     */
    public static void union(
            ImageProcessor entree1,
            ImageProcessor entree2,
            ImageProcessor sortie) {
        if (entree1.getWidth() != entree2.getWidth() ||
                entree1.getWidth() != sortie.getWidth() ||
                entree1.getHeight() != entree2.getHeight() ||
                entree1.getHeight() != sortie.getHeight()) {
            throw new IllegalArgumentException(
                    "Les tailles des images de correspondent pas.");
        }

        for (int y = 0; y < entree1.getHeight(); y++) {
            for (int x = 0; x < entree1.getWidth(); x++) {
                if (entree1.getPixel(x, y) == 0 || entree2.getPixel(x, y) == 0)
                    sortie.putPixel(x, y, 0);
                else sortie.putPixel(x, y, 255);
            }
        }
    }

    /**
     * Stocke dans 'sortie' l'intersection de 'entree1' et 'entree2'.
     * 'sortie' peut éventuellement pointer sur l'une des deux entrées.
     *
     * @param entree1 Premiere image d'entrée.
     * @param entree2 Seconde image d'entrée.
     * @param sortie  Image de sortie.
     */
    public static void intersection(
            ImageProcessor entree1,
            ImageProcessor entree2,
            ImageProcessor sortie) {
        if (entree1.getWidth() != entree2.getWidth() ||
                entree1.getWidth() != sortie.getWidth() ||
                entree1.getHeight() != entree2.getHeight() ||
                entree1.getHeight() != sortie.getHeight()) {
            throw new IllegalArgumentException(
                    "Les tailles des images de correspondent pas.");
        }

        for (int y = 0; y < entree1.getHeight(); y++) {
            for (int x = 0; x < entree1.getWidth(); x++) {
                if (entree1.getPixel(x, y) == 0 && entree2.getPixel(x, y) == 0)
                    sortie.putPixel(x, y, 0);
                else sortie.putPixel(x, y, 255);
            }
        }
    }

    /**
     * Stocke dans 'sortie' le complémentaire de 'entree'.
     * 'sortie' peut éventuellement pointer sur l'entrée.
     *
     * @param entree Image d'entrée
     * @param sortie Image de sortie
     */
    public static void complementaire(
            ImageProcessor entree,
            ImageProcessor sortie) {
        if (entree.getWidth() != sortie.getWidth() ||
                entree.getHeight() != sortie.getHeight()) {
            throw new IllegalArgumentException(
                    "Les tailles des images de correspondent pas.");
        }

        for (int y = 0; y < entree.getHeight(); y++) {
            for (int x = 0; x < entree.getWidth(); x++) {
                sortie.putPixel(x, y, 255 - entree.getPixel(x, y));
            }
        }
    }

    /**
     * Stocke dans 'sortie' la différence entre 'entree1' et 'entree2'.
     * 'sortie' peut éventuellement pointer sur l'une des deux entrées.
     *
     * @param entree1 Premiere image d'entrée.
     * @param entree2 Seconde image d'entrée.
     * @param sortie  Image de sortie.
     */
    public static void difference(
            ImageProcessor entree1,
            ImageProcessor entree2,
            ImageProcessor sortie) {
        if (entree1.getWidth() != entree2.getWidth() ||
                entree1.getWidth() != sortie.getWidth() ||
                entree1.getHeight() != entree2.getHeight() ||
                entree1.getHeight() != sortie.getHeight()) {
            throw new IllegalArgumentException(
                    "Les tailles des images de correspondent pas.");
        }

        for (int y = 0; y < entree1.getHeight(); y++) {
            for (int x = 0; x < entree1.getWidth(); x++) {
                if (entree1.getPixel(x, y) == 0 && entree2.getPixel(x, y) != 0)
                    sortie.putPixel(x, y, 0);
                else sortie.putPixel(x, y, 255);
            }
        }
    }

    /**
     * Réalise la dilatation de l'image 'in' par l'élément structurant 'es'
     * et stocke le résultat dans 'out'.
     *
     * @param in  Image d'entrée
     * @param es  Element structurant
     * @param out Image de sortie
     */
    public static void dilatation(
            ImageProcessor in,
            ElementStructurant es,
            ImageProcessor out) {
        verifie(in, out);

        Boolean abord;
        // Pour chaque pixel de l'image in
        for (int y = 0; y < in.getHeight(); y++) {
            for (int x = 0; x < in.getWidth(); x++) {
                // On vérifie que la condition soit vérifié pour que le pixel soit noir
                abord = false;
                // On lui applique l'élément structurant es
                for (int v = es.getYmin(); v <= es.getYmax() && !abord; v++) {
                    for (int u = es.getXmin(); u <= es.getXmax(); u++) {
                        // Si un pixel est noir de l'image d'entrée et qu'il l'est aussi dans l'es
                        if (es.get(u,v) == NOIR && in.getPixel(x - u, y - v) == NOIR) {
                            // La condition est vérifié
                            abord = true;
                            break;
                        }
                    }
                }
                // Si la condition est vérifié le pixel est noir dans l'image de sortie out
                // Autrement le pixel est blanc
                out.putPixel(x, y, (abord) ? NOIR : BLANC);
            }
        }
    }

    /**
     * Réalise l'érosion de l'image 'in' par l'élément structurant 'es'
     * et stocke le résultat dans 'out'.
     *
     * @param in  Image d'entrée
     * @param es  Element structurant
     * @param out Image de sortie
     */
    public static void erosion(
            ImageProcessor in,
            ElementStructurant es,
            ImageProcessor out) {
        verifie(in, out);

        Boolean abord;
        // Pour chaque pixel de l'image in
        for (int y = 0; y < in.getHeight(); y++) {
            for (int x = 0; x < in.getWidth(); x++) {
                // On vérifie que la condition soit vérifié pour que le pixel soit noir
                abord = false;
                for (int v = es.getYmin(); v <= es.getYmax() && !abord; v++) {
                    for (int u = es.getXmin(); u <= es.getXmax(); u++) {
                        // Si un pixel noir de l'es n'est pas dans l'image d'entrée in
                        if (es.get(u, v) == NOIR && in.getPixel(x - u, y - v) == BLANC) {
                            // La condition n'est pas vérifiée
                            abord = true;
                            break;
                        }
                    }
                }
                // Si la condition n'est pas vérifié le pixel est blanc dans l'image de sortie out
                // Autrement le pixel est noir
                out.putPixel(x, y, (abord) ? BLANC : NOIR);
            }
        }

    }

    /**
     * Réalise l'ouverture morphologique de l'image 'in' par
     * l'élément structurant 'es' et stocke le résultat dans 'out'.
     *
     * @param in  Image d'entrée
     * @param es  Element structurant
     * @param out Image de sortie
     */
    public static void ouverture(
            ImageProcessor in,
            ElementStructurant es,
            ImageProcessor out) {
        // érosion avec l'élément structurant puis dilatation avec l'élément structurant symétrique
        ImageProcessor temp = (ImageProcessor)out.clone();
        erosion(in, es, temp);
        dilatation(temp, es.symetrique(), out);
    }

    /**
     * Réalise la fermeture morphologique de l'image 'in' par
     * l'élément structurant 'es' et stocke le résultat dans 'out'.
     *
     * @param in  Image d'entrée
     * @param es  Element structurant
     * @param out Image de sortie
     */
    public static void fermeture(
            ImageProcessor in,
            ElementStructurant es,
            ImageProcessor out) {
        // dilatation avec l'élément structurant puis érosion avec l'élément structurant symétrique
        ImageProcessor temp = (ImageProcessor)out.clone();
        dilatation(in, es, temp);
        erosion(temp, es.symetrique(), out);
    }

    /**
     * Vérifie que l'image 'out' peut bien être utilisée pour y stocker le
     * résultat d'une opération morphologique sur 'in'.
     * Si c'est le cas, l'image de sortie est repeinte en blanc.
     *
     * @param in  Image d'entrée
     * @param out Image de sortie
     */
    private static void verifie(ImageProcessor in, ImageProcessor out) {
        if (in.getWidth() != out.getWidth() || in.getHeight() != out.getHeight())
            throw new IllegalArgumentException(
                    "in et out doivent avoir les mêmes dimensions !");

        if (in == out)
            throw new IllegalArgumentException(
                    "in et out ne doivent pas pointer sur la même image !");

        byte[] pixels = (byte[]) out.getPixels();
        Arrays.fill(pixels, (byte) 255);
    }

    public static void main(String[] args) {
        ImagePlus ip = Outils.openImage("lenna.png");
        ip = Image.createOTSUImage(ip);
//        ImagePlus ip2 = NewImage.createByteImage("Dilatation " + ip.getTitle(), ip.getWidth(), ip.getHeight(), 1, NewImage.GRAY8);
//        dilatation(ip.getProcessor(), ElementStructurant.creerRectangle4connexe(), ip2.getProcessor());
//        ImagePlus ip2 = NewImage.createByteImage("Erosion " + ip.getTitle(), ip.getWidth(), ip.getHeight(), 1, NewImage.GRAY8);
//        erosion(ip.getProcessor(), ElementStructurant.creerRectangle4connexe(), ip2.getProcessor());
        ImagePlus ip2 = NewImage.createByteImage("Erosion " + ip.getTitle(), ip.getWidth(), ip.getHeight(), 1, NewImage.GRAY8);
//        ouverture(ip.getProcessor(), ElementStructurant.creerRectangle4connexe(), ip2.getProcessor());
//        fermeture(ip.getProcessor(), ElementStructurant.creerRectangle4connexe(), ip2.getProcessor());
        ip.show();
        ip2.show();
    }
}
