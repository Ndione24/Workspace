package tp6;

import ij.ImagePlus;
import ij.process.ImageProcessor;
import tp5.Outils;

import java.util.LinkedList;

/**
 * Created by melkir on 18/04/14.
 */
public class RegionGrow implements Runnable {
    // Image d'entrée
    private final ImageProcessor in;
    // La taille de l'image
    private final int width, height;
    // Matrice des pixels étiquetés
    private final int[][] labels;
    // Le nombre de régions dans l'image
    private int numberOfRegions;

    public RegionGrow(ImageProcessor in) {
        this.in = in;
        this.width = in.getWidth();
        this.height = in.getHeight();
        this.numberOfRegions = 0;
        this.labels = new int[width][height];
    }

    public static void main(String[] args) {
        ImagePlus imp = Outils.openImage("i2Binaire.jpg");
        RegionGrow rg = new RegionGrow(imp.getProcessor());
        rg.regionGrowing();
        System.out.println("Nombre de régions : " + rg.getNumberOfRegions());
    }

    /**
     * @return Nombre de régions trouvées pendant la segmentation
     */
    public int getNumberOfRegions() {
        return numberOfRegions;
    }

    @Override
    public void run() {
        regionGrowing();
    }

    public void showSegment(LinkedList<Coords> c) {
        // Calcul de la taille de l'image
        int width /* = Xmax - Xmin */;
        int heing /* = Ymax - Ymin */;
//        ImagePlus imp = NewImage.createByteImage("Segment", c.size() / 2, c.size() / 2, 1, NewImage.GRAY8);
    }

    /**
     * Algorithme de region-growing:
     * 1 - Pour chacun des pixels de l'image :
     * 2 - Ajouter le premier pixel blanc non étiqueté dans la pile et l'étiqueter
     * 3 - Tant qu'il y a des pixels dans la pile :
     * 4 - Récupérer le premier pixel de la pile et le supprimer de la pile
     * 5 - Pour chacun des pixels voisins du pixel courant
     * S'ils ne sont pas étiquetés les ajouter à la pile et les étiqueter
     */
    public void regionGrowing() {
        // La liste des pixels à traiter
        LinkedList<Coords> listeATraiter = new LinkedList<Coords>();
        // On parcours tous les pixels de l'images
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Si le pixel n'est pas étiqueté et que c'est un pixel blanc
                if (0 == labels[x][y] && 255 == in.getPixel(x, y)) {
                    // C'est une nouvelle région que l'on ajoute dans la liste à traiter
                    listeATraiter.add(new Coords(x, y));
                    labels[x][y] = (++numberOfRegions);
                }
                // Tant qu'il reste des pixels à traiter dans la liste
                while (!listeATraiter.isEmpty()) {
                    // On récupére le premier pixel de la liste
                    Coords currentPoint = listeATraiter.getFirst();
                    // On l'enlève de la liste a traiter
                    listeATraiter.removeFirst();
                    // On ajoute les voisins du pixels courant
                    listeATraiter.addAll(getNeighbours(currentPoint.x, currentPoint.y));
                }
            }
        }
    }

    /**
     * Récupére la liste des voisins proche du pixel dans le rayon donnée
     *
     * @param x Abscisse du pixel
     * @param y Ordonnée du pixel
     * @return Liste des pixels voisins
     */
    private LinkedList<Coords> getNeighbours(int x, int y) {
        // La liste des pixels voisin à traiter dans le rayon
        LinkedList<Coords> listeVoisinATraiter = new LinkedList<Coords>();
        for (int th = -1; th <= 1; th++) {
            for (int tw = -1; tw <= 1; tw++) {
                int rx = x + tw;
                int ry = y + th;
                // Ne pas parcourir les pixels hors de l'image
                if ((rx < 0) || (ry < 0) || (ry >= height) || (rx >= width)) continue;
                // Si le pixel n'a pas déjà été traité et qu'il est blanc
                if (0 == labels[rx][ry] && 255 == in.getPixel(rx, ry)) {
                    // L'ajouter à la liste
                    listeVoisinATraiter.add(new Coords(rx, ry));
                    labels[rx][ry] = numberOfRegions;
                }
            }
        }
        return listeVoisinATraiter;
    }
}
