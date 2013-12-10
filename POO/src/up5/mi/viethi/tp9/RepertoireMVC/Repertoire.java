package up5.mi.viethi.tp9.RepertoireMVC;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Repertoire {

	private HashMap<String, String> elements;
	
	/** la liste des écouteurs */
	private List<RepertoireListener> listeners=new ArrayList<RepertoireListener>();
	
	/** ajout d'un écouteur */
	public void addRepertoireListener(RepertoireListener listener){
		this.listeners.add(listener);
	}

	/** creation d'un repertoire vide */
	public Repertoire() {
		this.elements = new HashMap<String, String>();
	}

	/** ajout d'une nouvelle entree 'nom'-'tel' dans ce repertoire */
	public void ajouterEntree(String nom, String tel) {
		this.elements.put(nom, tel);
		fireEntreeAjoutee(nom, tel);
	}

	private void fireEntreeAjoutee(String nom, String tel) {
		for(RepertoireListener listener : this.listeners)
			listener.entreeAjoute(this, nom, tel);
	}

	/**
	 * rend le numero de telephone asocie 'nom' dans ce repertoire rend null si
	 * ce n'est pas un nom de ce repertoire
	 */
	public String getTel(String nom) {
		return (String) this.elements.get(nom);
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (String key : this.elements.keySet())
			sb.append(key + " : " + this.elements.get(key) + '\n');
		return sb.toString();
	}

}
