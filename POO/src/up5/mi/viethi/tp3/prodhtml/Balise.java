package up5.mi.viethi.tp3.prodhtml;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import up5.mi.viethi.tp3.prodhtml.Option;

public class Balise {
	
	/** le nom de cette balise */
	private String nom;
	/** le texte associee cette balise */
	private String texte;
	/** la liste des sous balises */
	private List<Balise> balises=new ArrayList<Balise>();
	/** les options de cette balise */
	private List<Option> options=new ArrayList<Option>();
	
	/** Constructeur avec le nom de la balise */
	public Balise(String nom) {
		this.nom = nom;
	}
	/** Constructeur avec le nom de la balise et son texte */
	public Balise(String nom, String texte) {
		this.nom = nom;
		this.texte = texte;
	}
	
	/** Affiche la balise avec ses balises ouvrante et fermante */
	public String toString() {

		return this.toStringOuvrante() + this.toStringSousBalises()
				+ this.toStringFermante();
	}
	
	public String toStringOptions() {
		// On créer l'itérateur sur la liste d'option
		Iterator<Option> it = options.iterator();
		String mesOptions = "";
		// Tant qu'il y a des éléments on les ajoutes a la suite
		while(it.hasNext()) mesOptions+= " " + it.next().toString();
		
		return mesOptions;
	}

	/** Affiche la balise ouvrante */
	private String toStringOuvrante() {
		String strOuvrante = '<' + this.nom + toStringOptions()+ '>';
		if (this.texte != null) {
			return strOuvrante += this.texte;
		} else {
			return strOuvrante + '\n';
		}
	}
	
	/** Affiche la balise fermante */
	private String toStringFermante() {
		return "</" + this.nom + '>';
	}
	
	/** Affiche les sous balises lié a la balise */
	private String toStringSousBalises() {
		// On créer l'itérateur sur la liste de balises
		Iterator<Balise> it = balises.iterator();
		// Si celui est vide on renvoi une chaine vide
		if ( ! ( it.hasNext() ) ) return "";
		// Autrement on initialise notre chaine avec le premier élément
		String mesBalises = it.next().toString();
		// Tant qu'il y a des éléments on les ajoutes a la suite
		while(it.hasNext()) mesBalises+= '\n' + it.next().toString() + '\n';
		return mesBalises;
	}

	/** Ajoute une balise dans List balises */
	public void add(Balise nom) {
		this.balises.add(nom);
	}
	
	/** Ajoute une option dans List options */
	public void addOption(String nom, String valeur) {
		this.options.add(new Option(nom, valeur));
	}
	
	/** Récupère la balise applet associée */
	public static Balise getBaliseApplet(String code, String archive) {
		Balise applet = new Balise("applet");
		applet.options.add(new Option("code", code));
		applet.options.add(new Option("archive", archive));
		
		return applet;
	}

}
