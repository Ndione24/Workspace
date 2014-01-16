package up5.mi.viethi.tp1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JeuDuNombreSecret {

	/**
	 * @param args
	 */
	static int min = 0, max = 100;

	public static int Randomise(int min, int max) {
		return (int)(Math.random()*( max - min + 1 ) ) + min;
	}

	public static boolean isNumber(int x) throws IOException {
		char cmd = (char)(new BufferedReader(new InputStreamReader(System.in))).read();
		if (cmd == '=') return true;
		else if (cmd == '+') min = x;
		else if (cmd == '-') max = x;
		else System.out.println("Invalid command");
		return false;
	}

	public static void start() throws IOException {
		int x = Randomise(min, max);
		int nbEssai = 1;
		System.out.print("Je propose " + x + ". Est-ce =, + ou - grand que le nombre à trouver ? ");
		while (false == isNumber(x)) {
			x = Randomise(min, max);
			++nbEssai;
			System.out.print("Je propose " + x + ". Est-ce =, + ou - grand que le nombre à trouver ? ");
		}
		System.out.println("J'ai gagné en " + nbEssai + " coups");
	}

}
