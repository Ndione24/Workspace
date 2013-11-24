package up5.mi.viethi.tp3Test;

import up5.mi.pary.term.Terminal;
import up5.mi.viethi.tp3.RepertoireSD;

public class TestRepertoireSD {
	
	public static void main(String[] args) {
		Terminal term = new Terminal("répertoire", 400, 400);
		RepertoireSD r = new RepertoireSD("Dupond");
		term.println("Propriétaire du répertoire " + r.getNom());
		r.ajouterEntree("Lucie", "0123456789");
		r.ajouterEntree("Pierre", "0987654321");
		term.println(r.getTel("Lucie"));
		term.println(r.getNom("0123456789"));
		term.end();
	}
	
}
