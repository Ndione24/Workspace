package up5.mi.viethi.tp3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Hasmap {

	public static byte getChiffreT9(char c) {
		switch (Character.toLowerCase(c)) {
		case 'a':
		case 'b':
		case 'c':
			return 2;
		case 'd':
		case 'e':
		case 'f':
			return 3;
		case 'g':
		case 'h':
		case 'i':
			return 4;
		case 'j':
		case 'k':
		case 'l':
			return 5;
		case 'm':
		case 'n':
		case 'o':
			return 6;
		case 'p':
		case 'q':
		case 'r':
		case 's':
			return 7;
		case 't':
		case 'u':
		case 'v':
			return 8;
		case 'w':
		case 'x':
		case 'y':
		case 'z':
			return 9;
		default:
			return 0;
		}
	}

	/**
	 * étant donné une chaine, rend une chaine codée T9 de cette chaine
	 * 
	 * @param chaine
	 *            la chaine à coder
	 * @return la chaine codee
	 */
	public static String getChaineCodeeT9(String chaine) {
		String chaineCode = "";
		for (int i = 0; i < chaine.length(); i++) {
			chaineCode += getChiffreT9(chaine.charAt(i));
		}
		return chaineCode;

	}

	/**
	 * enregistre une nouvelle chaine dans une HashMap dictionnaire
	 * 
	 * @param mapDico
	 *            la HashMap correspondant au dictionnaire
	 * @param chaine
	 *            la chaine à enregistrer
	 */
	public static void enregistrer(HashMap<String, List> mapDico, String chaine) {
		String key = getChaineCodeeT9(chaine);
		// Si le mapDico ne contient pas la clé
		if (!mapDico.containsKey(key)) {
			// On créer une ArrayList
			List list = new ArrayList();
			// On ajoute cette liste (vide) dans le dico avec la key
			// correspondante
			mapDico.put(key, list);
		}
		// On récupère la liste correspondante a la key
		List list = (List) mapDico.get(key);
		// On ajoute la chaine à la liste
		list.add(chaine);
	}

	/**
	 * Etant donné un dictionnaire et une chaine codée, rend tous les mots du
	 * dictionnaire ayant cette chaine codée comme code
	 * 
	 * @param mapDico
	 *            la HashMap correspondant au dictionnaire
	 * @param chaineCodee
	 *            la chaine codée
	 * @return tous les mots du dictionnaire ayant cette chaine codée comme code
	 */
	public static ArrayList<String> recuperer(HashMap<Integer, List> mapDico,
			String chaineCodee) {
		if (mapDico.containsKey(chaineCodee))
			return (ArrayList<String>) mapDico.get(chaineCodee);
		else {
			List listNull = new ArrayList();
			listNull.add("");
			return (ArrayList<String>) listNull;
		}
	}

	public static void main(String[] args) {
		HashMap mapDico = new HashMap();
		enregistrer(mapDico, "Dupond");
		enregistrer(mapDico, "Fuponf");
		enregistrer(mapDico, "Durand");

		System.out.println(recuperer(mapDico, "387663"));// affiche [Dupond, Fuponf]
		System.out.println(recuperer(mapDico, "217663"));// affiche []

	}

}
