package up5.mi.viethi.tp3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import up5.mi.pary.term.Terminal;
import up5.mi.viethi.tp5.RepertoireException;

public class RepertoireSD extends RepertoireS {
	private String nom;
	static final long serialVersionUID = 6427035972083200386L;
	
	public RepertoireSD(String nom) {
		this.nom = nom;
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/* si le numéro est à 9 chiffres, ajouter en tête (33)
	 * si le numéro est à 10 chiffres avec un zéro en tête, 
	 * supprimer ce zéro et ajouter (33) */
	public String normalize(String num) {
		// 688533573 -> +33688533573
		if (9 == num.length()) {
			return num = "+33" + num;
		} else if (10 == num.length()) { // 0688533573 -> +33688533573 
			return num = "+33" + num.substring(1,num.length());
		} else {
			System.out.println("Bad number format");
			return "";
		}
	}
	
	public void ajouterEntree(String nom, String tel) {
		Contact monContact = new Contact(nom, normalize(tel));
		listContact.add(monContact);
	}

	public String getNom(String tel) {
		for (Contact contact : listContact) {
			if (contact.getTel().equals(normalize(tel))) {
				return contact.getNom();
			}
		}
		return "Contact inconnu";
	}
	
	public void sauvegarder (File file) throws RepertoireException, IOException {
		if (file.exists() && !file.canWrite()) {
			throw new IOException("Impossible d'écrire dans le fichier destination");
		}
		RepertoireSD rep = new RepertoireSD(this.nom);
		// serialisation
		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file));
		os.writeObject(this);
		os.close();
	}
	
	public static RepertoireSD restaurer (File file) throws RepertoireException, IOException, ClassNotFoundException {
		if (!file.canRead()) {
			throw new IOException("Impossible d'ouvrir le fichier source");
		}
		// deserialisation
		ObjectInputStream is = new ObjectInputStream(new FileInputStream(file));
		RepertoireSD rep = (RepertoireSD) is.readObject();
		is.close();
		return rep;
	}
	
	public static void main(String[] args) throws RepertoireException, IOException, ClassNotFoundException {
		/* Sauvegarde 
		RepertoireSD r = new RepertoireSD("Dupond");
		r.ajouterEntree("Lucie","0123456789");
		r.ajouterEntree("Pierre","0987654321");
		System.out.println("Sauvegarde du répertoire " + r.nom);
		r.sauvegarder(new File("monRep.txt"));
		*/
		
		/* Restauration */
		System.out.print("Restauration du répertoire ");
		RepertoireSD rep = RepertoireSD.restaurer(new File("monRep.txt"));
		System.out.println(rep.nom);
		
		  
		/* Affichage */
		Terminal term = new Terminal("répertoire",400,400);
		term.println(rep.getTel("Lucie"));
		term.println(rep.getNom("0123456789"));
		term.end();
	}
}
