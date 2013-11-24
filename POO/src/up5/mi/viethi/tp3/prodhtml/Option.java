package up5.mi.viethi.tp3.prodhtml;

public class Option {

	private String nom, valeur;

	public Option(String nom, String valeur) {

		this.nom = nom;
		this.valeur = valeur;
	}

	public String toString() {
		return this.nom + '=' + '\"' + this.valeur + '\"';
	}
}
