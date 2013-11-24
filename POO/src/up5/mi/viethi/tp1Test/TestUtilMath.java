package up5.mi.viethi.tp1Test;

import up5.mi.viethi.tp1.UtilMath;
import up5.mi.viethi.tp1.UtilisationApi;

public class TestUtilMath {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.print("Somme de 1+2+3=");
		System.out.println(UtilMath.somNbEntier(1, 2, 3));
		UtilisationApi.afficherFact(0);
		UtilisationApi.afficherFact(7);
		UtilisationApi.afficherFact(70);
	}

}
