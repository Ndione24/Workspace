package tp6;

import ij.ImagePlus;
import ij.gui.NewImage;
import ij.process.ImageProcessor;
import tp5.Outils;
import tpnote.ImageProcessing;

import java.util.ArrayList;
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
    // Liste des coordonnées de chaque segments dans l'image
    private final TreeMap<Integer, LinkedList<Coords>> collection = new TreeMap<Integer, LinkedList<Coords>>();
    // Les images résultats de région growing
    private final ArrayList<ImagePlus> imgResult = new ArrayList<ImagePlus>();
    // Le nombre de régions dans l'image
    private int numberOfRegions = 0;
    // Seuil correspondant à la taille min. de l'image à obtenir
    private int seuilDim = 400;
    // Seuil correspondant à la différence max. de dimension par rapport à la forme carré
    private int seuilForme = 25;
    // La couleur de l'objet à trouver
    private ColorBin color;

    /**
     * Constructeur region-grow, détermination de l'avant plan automatique
     *
     * @param in Processor de l'image
     */
    public RegionGrow(ImageProcessor in) {
        this.in = in;
        this.width = in.getWidth();
        this.height = in.getHeight();
        this.labels = new int[width][height];
        this.color = getForegroundColor(in);
        System.out.println("Background " + color.other().toString());
    }

    /**
     * Constructeur region-grow avec spécification de la couleur à rechercher
     *
     * @param in    Processor de l'image
     * @param color Couleur à rechercher
     */
    public RegionGrow(ImageProcessor in, ColorBin color) {
        this.in = in;
        this.color = color;
        this.width = in.getWidth();
        this.height = in.getHeight();
        this.labels = new int[width][height];
    }

    public static void main(String[] args) {
        ImagePlus imp = Outils.openImage("Image_OK_3.jpg");
        ImageProcessing imageProcessing = new ImageProcessing(imp);
        imageProcessing.run();
        imp.show();
        RegionGrow rg = new RegionGrow(imp.getProcessor());
        rg.run();
    }

    private static ColorBin getForegroundColor(ImageProcessor ip) {
        int[] histo = ip.getHistogram();
        return (histo[0] > histo[255] ? ColorBin.WHITE : ColorBin.BLACK);
    }

    /**
     * Défini le seuil basé sur la forme à détecté
     * Un seuil trop bas risque d'afficher des faux positifs
     * Un seuil trop haut risque de supprimer des vrai positif
     *
     * @param seuilForme
     */
    public void setSeuilForme(int seuilForme) {
        this.seuilForme = seuilForme;
    }

    /**
     * Défini le seuil basé sur la dimension de l'objet à détecté
     *
     * @param seuilDim
     */
    public void setSeuilDim(int seuilDim) {
        this.seuilDim = seuilDim;
    }

    @Override
    public void run() {
        regionGrowing();
        System.out.println("Nombre de dès : " + numberOfRegions);
        showResult();
        getValuesInnerRegion();
    }

    public void showResult() {
        for (ImagePlus imp : imgResult) imp.show();
    }

    public int getNbRegions() {
        return numberOfRegions;
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
                if (0 == labels[x][y] && color.value == in.getPixel(x, y)) {
                    // C'est une nouvelle région que l'on ajoute dans la liste à traiter
                    Coords firstCoords = new Coords(x, y);
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
        // Nettoyage des faux positifs
        clearResult();
        // Sauvegarde des images résultats
        storeImageResult();
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
                if (0 == labels[rx][ry] && color.value == in.getPixel(rx, ry)) {
                    // L'ajouter à la liste
                    listeVoisinATraiter.add(new Coords(rx, ry));
                    labels[rx][ry] = numberOfRegions;
                }
            }
        }
        return listeVoisinATraiter;
    }

    /**
     * Supprimer les faux positifs du résultat
     */
    private void clearResult() {
        ArrayList<Integer> keyToRemove = new ArrayList<Integer>();
        for (Integer key : collection.keySet()) {
            // On supprime les images trop petites
            if (collection.get(key).size() < seuilDim)
                keyToRemove.add(key);
            // On vérifie que ce soit bien des carrés
            int[] dim = getRegionDimension(collection.get(key));
            if (Math.abs(dim[0] - dim[1]) > seuilForme) {
                keyToRemove.add(key);
            }
        }

        for (Integer key : keyToRemove)
            collection.remove(key);

        numberOfRegions = collection.size();
    }

    /**
     * Return the dimension of the segment and his offset
     *
     * @param list List of coords
     * @return Dimension from origin and x, y offset
     */
    private int[] getRegionDimension(LinkedList<Coords> list) {
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
        dim[0] = xmax - xmin; // Largeur
        dim[1] = ymax - ymin; // Hauteur
        dim[2] = xmin;
        dim[3] = ymin;
        return dim;
    }

    private void storeImageResult() {
        for (Integer region : collection.keySet()) {
            imgResult.add(createImage(collection.get(region)));
        }
    }

    /**
     * Créer une image à partir d'une liste de coordonnées
     *
     * @param list Liste de coordonnées
     */
    private ImagePlus createImage(LinkedList<Coords> list) {
        int[] dim = getRegionDimension(list);
        int offsetx = dim[2];
        int offsety = dim[3];
        ImagePlus imp = NewImage.createByteImage("Segment", dim[0], dim[1], 1, NewImage.GRAY8);
        ImageProcessor ip = imp.getProcessor();
        int height = ip.getHeight(), width = ip.getWidth();
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++)
                ip.putPixel(x, y, in.getPixel(x + offsetx, y + offsety));

        return imp;
    }

    /**
     * Renvoie vrai si un pixel voisin n'est pas de la bonne couleur
     *
     * @param x
     * @param y
     * @return
     */
    private boolean checkNeighboursPixels(ImageProcessor ip, int x, int y) {
        int rx, ry;
        boolean breakLoop = false;
        for (int th = -1; th <= 1 && !breakLoop; th++) {
            for (int tw = -1; tw <= 1; tw++) {
                rx = x + tw;
                ry = y + th;
                // Ne pas parcourir les pixels hors de l'image
                if ((rx < 0) || (ry < 0) || (ry >= height) || (rx >= width)) continue;
                if (color.value != ip.getPixel(rx, ry)) {
                    breakLoop = true;
                    break;
                }
            }
        }
        return breakLoop;
    }

    /**
     * Renvoie vrai si une extrémité n'est pas de la bonne couleur
     *
     * @param ip
     * @return
     */
    private boolean checkExtremity(ImageProcessor ip) {
        int[] borderValues = new int[4];
        int xmax = ip.getWidth();
        int ymax = ip.getHeight();
        borderValues[0] = ip.getPixel(0, ymax); // bord gauche, bas
        borderValues[1] = ip.getPixel(xmax, ymax); // bord droit, bas
        borderValues[2] = ip.getPixel(0, 0); // bord gauche, haut
        borderValues[3] = ip.getPixel(xmax, 0); // bord droit, haut
        boolean breakLoop = false;
        for (int value : borderValues) {
            if (value != color.value) {
                breakLoop = true;
                break;
            }
        }
        return !breakLoop;
    }

    /**
     * Récupére les ronds à l'intérieur de chaque dés
     */
    private void getValuesInnerRegion() {
        ImageProcessor ip;
        RegionGrow rg;
        int i = 0;
        for (ImagePlus imp : imgResult) {
            ip = imp.getProcessor();
            rg = new RegionGrow(ip, color.other());
            System.out.print("Dès n° " + (++i) + " : ");
            // Les points étant dans les dès on diminue les seuils
            rg.setSeuilDim(seuilDim / 50); // Dimension des ronds intérieur ~ 4 fois plus petit que le dés
            rg.setSeuilForme(seuilForme / 5); // Les ronds donne presque une image carré en résultat
            rg.regionGrowing();
            rg.testCenterColor();
            System.out.println(rg.getNbRegions());
        }
    }

    /**
     * Test pour vérifier si le centre de l'image correspond à la couleur que l'on recherche
     * Dans notre cas on suppose que tous les cercles de dés sont coloriés au centre.
     */
    private void testCenterColor() {
        ImageProcessor ip;
        int xmid, ymid;
        ArrayList<ImagePlus> keyToRemove = new ArrayList<ImagePlus>();
        for (ImagePlus imp : imgResult) {
            ip = imp.getProcessor();
            xmid = ip.getWidth() / 2;
            ymid = ip.getHeight() / 2;
            // Dans un rayon 3x3 autour du centre
            if (checkNeighboursPixels(ip, xmid, ymid) || checkExtremity(ip)) keyToRemove.add(imp);
        }
        for (ImagePlus key : keyToRemove) {
            imgResult.remove(key);
            --numberOfRegions;
        }
    }
}