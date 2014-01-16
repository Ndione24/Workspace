package up5.mi.viethi.tp1;

public class NbPremier {
	/**
	 * @param args
	 */

	public static boolean isPremierA1(int n) {
		// n = 5
		if (n == 0 || n == 1)
			return false;
		for (int i = 2; i < n; ++i) {
			if (n % i == 0)
				return false;
		}
		return true;
	}

	public static boolean isPremierA2(int n) {
		if (n == 0 || n == 1 || n % 2 == 0)
			return false;
		for (int i = 3; i < n; i += 2) {
			if (n % i == 0)
				return false;
		}
		return true;
	}

	public static boolean isPremierA3(int n) {
		if (n == 0 || n == 1 || n % 2 == 0)
			return false;

		for (int i = 3; i < Math.sqrt(n); i += 2) {
			if (n % i == 0)
				return false;
		}
		return true;
	}

	public static void afficherNPremier(int n) {
		if (n < 2)
			throw new IllegalArgumentException("Must be > 1!");

		int i = 0, j = 1;

		while (i < n) {
			if (isPremierA3(j)) {
				System.out.print(j + " ");
				++i;
			}
			++j;
		}
	}

}
