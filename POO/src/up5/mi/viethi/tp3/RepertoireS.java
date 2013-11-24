package up5.mi.viethi.tp3;

import java.io.Serializable;
import java.util.ArrayList;

/** RepertoireS (Repertoire Simplifiée) est une classe
* permettant de créer des répertoires simples <br/>
* en particulier, elle permet de<ul>
* <li>Enregistrer une nouvelle entrée composée d'un nom et d'un num de tél associé</li>
* <li>Déterminer un numéro de tel en connaissant le nom.</li>
* </ul>
*/
public class RepertoireS implements Serializable {
	protected ArrayList<Contact> listContact = new ArrayList<Contact>();

	public void ajouterEntree(String nom, String tel) {
		Contact monContact = new Contact(nom, tel);
		listContact.add(monContact);
	}

	public String getTel(String nom) {
		for (Contact contact : listContact) {
			if (contact.getNom().equals(nom)) {
				return contact.getTel();
			}
		}
		return "Contact inconnu";
	}
	
}
