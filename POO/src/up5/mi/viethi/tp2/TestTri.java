package up5.mi.viethi.tp2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/*
 * Question 1
 * Ecrire une fonction réalisant le tri par sélection échange d'un tableau de String. (On trouvera à la fin du "cours partie 1" des fonctions permettant de trier un tableau d'entiers. On repartira de ces fonctions en les adaptant au tri d'un tableau de String)
 * Question 2
 * Ecrire une fonction réalisant le tri par sélection échange d'une arraylist de String
 * Question 3
 * En modifiant légèrement le résultat obtenu en question 2 et en utilisant l'interface java.lang.Comparable, écrire une fonction de tri pour trier des listes dont tous les éléments sont de la même classe, cette classe implémentant l'interface Comparable.
 * Exemple : une liste d'Integer, une liste de chaînes de caractères 
 * 
 */

public abstract class TestTri implements Comparator<List> {
	
	/** recherche l'indice du plus petit élément
	* du tableau 'tab' entre les indices 'imin' et 'imax' */
	public static int indiceDuPlusPetitNombre(int [] tab,int imin,int imax){
		int res = imin;
		for (int i=imin+1;i<=imax;i++)
			if (tab[i] < tab [res]) res=i;
		return res;
	}
	
	public static int indiceDeLaPlusPetiteString(String[] tab, int imin, int imax){
		int res = imin;
		for (int i=imin+1;i<=imax;i++)
			// compareTo renvoi 0 si tab[i] égale tab[res], < 0 si inférieur, > 0 si supérieur
			if (tab[i].compareTo(tab[res]) < 0) res=i;
		return res;
	}
	
	public static int indiceDeLaPlusPetiteAString(ArrayList<String> arrayList, int imin, int imax) {
		int res = imin;
		for (int i=imin+1;i<=imax;i++)
			// compareTo renvoi 0 si tab[i] égale tab[res], < 0 si inférieur, > 0 si supérieur
			if (arrayList.get(i).compareTo(arrayList.get(res)) < 0) res=i;
		return res;	
	}
	
	/*
	public static int indiceDeLaPlusPetiteList(ArrayList<List> arrayList, int imin, int imax) {
		int res = imin;
		for (int i=imin+1;i<=imax;i++)
			// compareTo renvoi 0 si tab[i] égale tab[res], < 0 si inférieur, > 0 si supérieur
			if (arrayList.get(i).compareTo(arrayList.get(res))) res=i;
		return res;
		
	}
	*/
	
	/* échange les éléments 'tab'['i'] et 'tab'['j'] */
	public static void echangerNombre(int [] tab, int i, int j){
		int tabj=tab [j];
		tab [j]=tab [i];
		tab [i]=tabj;
	}

	// i = 0 , j = 1
	public static void echangerString(String[] tab, int i, int j){
		String tabj=tab[j];
		tab [j]=tab[i]; // tab[1] = tab[0]
		tab [i]=tabj; // tab[0] = tab[1]
	}
	
	// 
	public static void echangerAString(ArrayList<String> aStr, int i, int j){
		String tabi = aStr.set(i, aStr.get(j));
		aStr.set(j, tabi);
	}
	
	// 
	public static void echangerAList(ArrayList<List> aList, int i, int j){
		List tabi = aList.set(i, aList.get(j));
		aList.set(j, tabi);
	}
	
	private static void afficherTabStr(String[] tabStr) {
		for (String string : tabStr) {
			System.out.println(string);
		}
	}
	
	private static void afficherAStr(ArrayList<String> tabAStr) {
	  for (String str : tabAStr) {
	      System.out.println(str);
	  }
	}
	
	private static void afficherAList(ArrayList<List> tabAList) {
		for (List list : tabAList) {
			System.out.println(list);
		}
	}
	
	public static void triSelectionNombre(int[] tab) {
		for (int etape = 1; etape < tab.length-1; etape++) {
			// dep est l'indice à partir duquel le plus petit élément est cherché
			int dep = etape-1;
			// recherche de l'indice du plus petit élément entre les indices dep et tab.length-1
			int j = indiceDuPlusPetitNombre(tab,dep,tab.length-1);
			// permutation des éléments d'indice dep et j
			echangerNombre(tab,dep,j);
		}
	}
	
	public static void triSelectionString(String[] tabStr) {
		for (int etape = 1; etape < tabStr.length-1; etape++) {
			// dep est l'indice à partir duquel le plus petit élément est cherché
			int dep = etape-1;
			// recherche de l'indice du plus petit élément entre les indices dep et tab.length-1
			int j = indiceDeLaPlusPetiteString(tabStr,dep,tabStr.length-1);
			// permutation des éléments d'indice dep et j
			echangerString(tabStr,dep,j);
		}
		afficherTabStr(tabStr);
	}
	
	private static void triSelectionAStr(ArrayList<String> aStr) {
		for (int etape = 1; etape < aStr.size()-1; etape++) {
			// dep est l'indice à partir duquel le plus petit élément est cherché
			int dep = etape-1;
			// recherche de l'indice du plus petit élément entre les indices dep et tab.length-1
			int j = indiceDeLaPlusPetiteAString(aStr,dep,aStr.size()-1);
			// permutation des éléments d'indice dep et j
			echangerAString(aStr,dep,j);
		}
		afficherAStr(aStr);
	}
	
	/*
	private static void triSelection(ArrayList<List> arraylist) {
		for (int etape = 1; etape < arraylist.size()-1; etape++) {
			// dep est l'indice à partir duquel le plus petit élément est cherché
			int dep = etape-1;
			// recherche de l'indice du plus petit élément entre les indices dep et tab.length-1
			int j = indiceDeLaPlusPetiteList(arraylist,dep,arraylist.size()-1);
			// permutation des éléments d'indice dep et j
			echangerAList(arraylist,dep,j);
		}
		afficherAList(arraylist);
	}
	*/
	
	public static void main(String[] args) {
		/* String[] tabStr = {"hello", "bonsoir", "bonjour","nawak", "ninja"};
		triSelectionString(tabStr); */
		ArrayList<String> arrlist = new ArrayList<String>(2);
		arrlist.add("hello");
		arrlist.add("girl");
		arrlist.add("bonsoir");
		arrlist.add("nawak");
		arrlist.add("ninja");
		triSelectionAStr(arrlist);
		
	}
	
}
