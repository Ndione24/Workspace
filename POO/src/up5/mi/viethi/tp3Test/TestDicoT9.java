package up5.mi.viethi.tp3Test;

import up5.mi.viethi.tp3.DicoT9;

public class TestDicoT9 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DicoT9 dico = new DicoT9();
		dico.enregistrer("Dupond");
		dico.enregistrer("Fuponf");
		dico.enregistrer("Durand");
		System.out.println(dico.recuperer("387663"));// affiche [Dupond, Fuponf]
		System.out.println(dico.recuperer("217663"));// affiche []
	}

}
