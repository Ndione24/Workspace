package up5.mi.viethi.tp3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DicoT9 {

	private static final HashMap<String, List> mapDico = new HashMap();
	
	public static byte getChiffreT9(char c) {
		switch (Character.toLowerCase(c)) {
		case 'a': case 'b': case 'c':
			return 2;
		case 'd': case 'e': case 'f':
			return 3;
		case 'g': case 'h': case 'i':
			return 4;
		case 'j': case 'k': case 'l':
			return 5;
		case 'm': case 'n': case 'o':
			return 6;
		case 'p': case 'q': case 'r': case 's':
			return 7;
		case 't': case 'u': case 'v':
			return 8;
		case 'w': case 'x': case 'y': case 'z':
			return 9;
		default:
			return 0;
		}
	}
	
	public static String getChaineCodeeT9(String chaine) {
		String chaineCode = "";
		for (int i = 0; i < chaine.length(); i++) {
			chaineCode += getChiffreT9(chaine.charAt(i));
		}
		return chaineCode;
	}
	
	public static void enregistrer(String chaine) {
		String key = getChaineCodeeT9(chaine);
		// Si le mapDico ne contient pas la clé
		if (!mapDico.containsKey(key)) {
			// On créer une ArrayList
			List list = new ArrayList();
			//on ajoute la chaine à la liste 
			list.add(chaine);
			// On ajoute cette liste dans le dico avec la key
			// correspondante
			mapDico.put(key, list);
		}
		// On récupère la liste correspondante a la key
		List list = (List) mapDico.get(key);
		// On ajoute la chaine à la liste
		list.add(chaine);
	}
	
	public static ArrayList<String> recuperer(String chaineCodee) {
		if (mapDico.containsKey(chaineCodee))
			return (ArrayList<String>) mapDico.get(chaineCodee);
		else {
			
			return null ;
		}
	}

}
