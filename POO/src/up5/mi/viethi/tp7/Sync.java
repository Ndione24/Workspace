package up5.mi.viethi.tp7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Sync {

	private final int nbThread;
	private List<Integer> list = new ArrayList<Integer>();
	private Random gen = new Random();

	// Longueur liste, nombre de Thread
	public Sync(int longueurListe,int nbThread){
		// Initialise nbThread de la classe Sync
		this.nbThread=nbThread;
		// On ajoute "longeurListe" Integer (0,1,2,3...) à la liste
		for (int i=0;i<longueurListe;i++)
			list.add(new Integer(i));
	}

	// Vérifie que la liste ne soit pas "troué"
	public void verify(){
		// Créer une copie de la liste
		List<Integer> copie= new ArrayList<Integer>(this.list);
		// Classe les nombres de la liste par ordre croissant
		Collections.sort(copie);
		// Pour chaque éléments de la liste
		for (int i=0;i<copie.size();i++)
			// On vérifie si l'indice i est bien égale à l'élément i de la liste
			if (copie.get(i).intValue()!=i) 
				// Si ce n'est pas le cas, une exception Runtime est levé
				throw new RuntimeException("indice "+i+" val : "+copie.get(i));
	}

	private void tester() {
		// Vérifie que la liste ne soit pas "troué"
		verify();
		List<ThreadList> tl = new ArrayList<ThreadList>();
		// On créer "nbThread" ThreadList
		for (int i=0;i<nbThread;i++) tl.add(new ThreadList(i+""));
		// Pour chaque ThreadList, on exécute la méthode start
		for (int i=0;i<nbThread;i++) tl.get(i).start();
		for (int i=0;i<nbThread;i++)
			// On attend la fin de chaque thread
			try {tl.get(i).join();} 
			catch (InterruptedException e) {e.printStackTrace();}

		try {
			// On vérifie que la liste ne soit pas "troué" après le mélange
			verify();
			System.out.println("OK pour "+nbThread+" thread(s)");
		}
		// Un élément de la liste n'est pas correcte, une exception est levé
		catch (RuntimeException exp){System.out.println("PAS OK pour "+nbThread+" thread(s)");}
	}

	public static void main(String[] args) {
		// Créer une List de taille 1000 avec 1 thread
		new Sync(1000,1).tester();
		new Sync(1000,2).tester();
		new Sync(1000,30).tester();
		new Sync(1000,1000).tester();
		
		new Sync(100000,1).tester();
		new Sync(100000,2).tester();
		new Sync(1000000,3).tester();
		new Sync(100000,1000).tester();
	} 

	// ThreadListe déplace aléatoirement 10 éléments de la liste au début
	class ThreadList extends Thread{
		private String name;
		// Le constructeur prend en paramètre une String
		public ThreadList(String name){this.name=name;}
		// Méthode qu'exécute le thread
		public void run(){
			int cpt=10;
			// La boucle while est parcouru 10 fois
			while (cpt!=0){
				// Génére un nombre un aléatoire entre 0 et la taille de la liste
				int index=gen.nextInt(list.size());
				// On récupére la valeur de l'index
				Integer value = list.get(index);
				// On supprime cette valeur
				list.remove(index);
				// On ajoute au début de la liste, la valeur trouvé précedement
				list.add(0,value);
				cpt--;
			}
		}
	}

}
// Question 1 : que fait ce programme ?
// Question 2 : pourquoi l'execution ne donne pas le résultat que l'on pourrait attendre ?
// Question 3 : utiliser un bloc synchronized pour obtenir un resultat correct