package up5.mi.viethi.tp9;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

public class UseTriServlets {

	public static void afficher(int[] tableau) {
		for (int valeur : tableau)
			System.out.print(valeur + " ");
		System.out.println();
	}
	
	public void test() {
		int[] tab = { 1, 5, 6, 2, 7, 3, 8, 4, 9 };
		System.out.println("Tableau avant le tri :");
		afficher(tab);
		Arrays.sort(tab);
		System.out.println("Tableau après le tri :");
		afficher(tab);
	}
	
	public static URLConnection initConnectionServletsTri() throws IOException {
		String protocol = "http", hostname="localhost:8080", contexte="tp9", path="triservlets";
		String urlString = protocol+"://"+hostname+"/"+contexte+"/"+path;
		// création d'un objet URL
		URL url = new URL(urlString);
		// création d'une URLConnection
		URLConnection urlc = url.openConnection();
		// paramétrage de la connexion
		urlc.setDoOutput(true); // false pour GET (defaut), true pour POST
		// connection au serveur
		urlc.connect();
		return urlc;
	}
	
	public static void envoyerTabInt(URLConnection urlc, int[] tab) throws IOException {
		// récupération du flux de sortie
		ObjectOutputStream oos = new ObjectOutputStream(urlc.getOutputStream());
		System.out.print("Envoi du tableau à la servlets...");
		oos.writeObject(tab);
		// fermeture du flux de sortie
		System.out.println(" OK !");
		oos.close();
	}
	
	public static int[] recupererTabInt(URLConnection urlc) throws IOException, ClassNotFoundException {
		System.out.print("Récupération du tableau trié depuis la servlets...");
		ObjectInputStream ois = new ObjectInputStream(urlc.getInputStream());
		int[] tab = (int[]) ois.readObject();
		System.out.println(" OK !");
		ois.close();
		return tab;
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		URLConnection urlc = initConnectionServletsTri();
		// écrire dans le flux de sortie ce que l'on désire écrire vers l'url
		int[] tab = { 1, 5, 6, 2, 7, 3, 8, 4, 9 };
		System.out.print("Avant le tri : ");
		afficher(tab);
		envoyerTabInt(urlc, tab);
		tab = recupererTabInt(urlc);
		System.out.print("Après le tri : ");
		afficher(tab);
		
	}

}
