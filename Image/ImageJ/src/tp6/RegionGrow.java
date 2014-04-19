package tp6;

import ij.ImagePlus;
import ij.gui.NewImage;
import ij.process.ImageProcessor;
import tp5.Outils;

import java.util.LinkedList;
import java.util.TreeMap;

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
    // Liste des coordonnées de chaque segments dans l'image
    private TreeMap<Integer, LinkedList<Coords>> collection;

    public RegionGrow(ImageProcessor in) {
        this.in = in;
        this.width = in.getWidth();
        this.height = in.getHeight();
        this.numberOfRegions = 0;
        this.labels = new int[width][height];
        this.collection = new TreeMap<Integer, LinkedList<Coords>>();
    }

    public static void main(String[] args) {
        ImagePlus imp = Outils.openImage("i1Binaire.jpg");
        RegionGrow rg = new RegionGrow(imp.getProcessor());
        rg.regionGrowing();
        System.out.println("Nombre de régions détectés : " + rg.collection.size());
        // Pour chaque région trouver les afficher
        for (Integer region : rg.collection.keySet()) {
            // On ignore les faux positifs
            if (rg.collection.get(region).size() > 100) {
                rg.showSegment(rg.collection.get(region));
            }
        }
    }

    public int[][] getLabels() {
        return labels;
    }

    /**
     * Return the dimension of the segment and his offset
     *
     * @param list List of coords
     * @return Dimension from origin and x, y offset
     */
    private int[] getSegmentDimension(LinkedList<Coords> list) {
        int[] dim = new int[4];
        int cx = list.getFirst().x;
        int cy = list.getFirst().y;
        // Déclaration des paramètres de la recherche
        int xmin, xmax, ymin, ymax;
        xmin = xmax = cx;
        ymin = ymax = cy;
        for (Coords c : list) {
            // Recherche de l'abscisse min et max
            if (c.x > xmax) xmax = c.x;
            else if (c.x < xmin) xmin = c.x;
            // Recherche de l'ordonnée min et max
            if (c.y > ymax) ymax = c.y;
            else if (c.y < ymin) ymin = c.y;
        }
        dim[0] = xmax - xmin;
        dim[1] = ymax - ymin;
        dim[2] = xmin;
        dim[3] = ymin;
        return dim;
    }

    @Override
    public void run() {
        regionGrowing();
    }

    /**
     * Affiche la liste de coordonnées dans une image
     *
     * @param list Liste de coordonnées
     */
    public void showSegment(LinkedList<Coords> list) {
        int[] dim = getSegmentDimension(list);
        int offsetx = dim[2];
        int offsety = dim[3];
        ImagePlus imp = NewImage.createByteImage("Segment", dim[0], dim[1], 1, NewImage.GRAY8);
        ImageProcessor ip = imp.getProcessor();

        for (int y = 0; y < ip.getHeight(); y++) {
            for (int x = 0; x < ip.getWidth(); x++) {
                ip.putPixel(x, y, in.getPixel(x + offsetx, y + offsety));
            }
        }
        imp.show();
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
                    Coords firstCoords = new Coords(x,y);
                    listeATraiter.add(firstCoords);
                    labels[x][y] = (++numberOfRegions);
                    // On créer liste qui va contenir toutes les coordonnées de la nouvelle région
                    collection.put(numberOfRegions, new LinkedList<Coords>());
                    collection.get(numberOfRegions).add(firstCoords);
                }
                // Tant qu'il reste des pixels à traiter dans la liste
                while (!listeATraiter.isEmpty()) {
                    // On récupére le premier pixel de la liste
                    Coords currentPoint = listeATraiter.getFirst();
                    // On l'enlève de la liste a traiter
                    listeATraiter.removeFirst();
                    LinkedList<Coords> listVoisins = getNeighbours(currentPoint.x, currentPoint.y);
                    // On ajoute les voisins du pixels courant
                    listeATraiter.addAll(listVoisins);
                    // On sauvegarde les coordonnées de la région
                    collection.get(numberOfRegions).addAll(listVoisins);
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
        int rx, ry;
        for (int th = -1; th <= 1; th++) {
            for (int tw = -1; tw <= 1; tw++) {
                rx = x + tw;
                ry = y + th;
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