package up5.mi.viethi.tp2;

import up5.mi.pary.term.Terminal;

public class TestFactComb {

	/** rend la valeur de la fonction factorielle en 'n', 'n'>=0 */
	public static long fact(long n) {
		if (n < 0) throw new ArithmeticException("n doit être >= 0");
		int res = 1;
		for (int i = 2; i <= n; i++)
			res *= i;
		return res;
		
	}

	/** rend la combinaison de 'n' et de 'p' */
	public static long comb(int n, int p) {
		if (n < 0) throw new ArithmeticException("n doit être >= 0");
		return fact(n) / (fact(p) * fact(n - p));
		
	}

	/**
	 * demande l'utilisateur, apres affiche du message 'message' un entier lu au
	 * terminal 'term' compris entre 'min' et 'max' reitere la demande tant que
	 * l'entier n'est pas valide 
	 * Rend cet entier
	 */
	public static int readInt(Terminal term, String message, int min, int max) {
		int res;
		do {
			res = term.readInt(message);
		} while (res < min || res > max);
		return res;
		
	}

	public static void main(String[] args) {
		try {
			Terminal term = new Terminal("dowhile", 400, 400);
			int choix;
			do {
				term.println("0 Quitter");
				term.println("1 Calcul d\'une factorielle");
				term.println("2 Calcul d\'une combinaison");
				choix = readInt(term, "votre choix ?", 0, 2);
				switch (choix) {
				case 1:
					term.println(fact(term.readInt("entrer un entier")));
					break;
				case 2:
					term.println(comb(term.readInt("entrer un entier"),
							term.readInt("entrer un autre entier")));
					break;
				}
				
			} while (choix != 0);
		} catch (ArithmeticException eA) {
			System.out.println(eA.toString());
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}