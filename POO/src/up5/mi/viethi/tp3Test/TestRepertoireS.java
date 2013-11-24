package up5.mi.viethi.tp3Test;

import java.util.Iterator;

import up5.mi.pary.term.Terminal;
import up5.mi.viethi.tp3.RepertoireS;

public class TestRepertoireS {

	public static void main(String[] args) {
		Terminal term = new Terminal("Repertoire", 400, 400);
		RepertoireS repertoire = new RepertoireS();
		repertoire.ajouterEntree("Lucie", "0123456789");
		repertoire.ajouterEntree("Pierre", "0987654321");
		System.out.println(repertoire.getTel("Lucie"));
		int choix;
		do {
			do {
				term.println("0 : Quitter\n" + "1 : Ajouter une entr\u00E9e\n"
						+ "2: Obtenir un num\u00E9ro de t\u00E9l\u00E9phone\n");
				choix = term.readInt("votre choix ?");
			} while (choix < 0 || choix > 2);

			switch (choix) {
			case 1:
				repertoire.ajouterEntree(term.readString("quel nom ?"),
						term.readString("quel num\u00E9ro ?"));
				break;
			case 2:
				term.println(repertoire.getTel(term.readString("quel nom ?")));
			}

		} while (choix != 0);
	}
}