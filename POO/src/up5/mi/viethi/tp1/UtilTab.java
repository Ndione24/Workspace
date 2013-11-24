package up5.mi.viethi.tp1;

public class UtilTab {

	/**
	 * @param args
	 */
	public static boolean isPair(int a) {
		if ((a%2) == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public static int nbPairTab(int tabInt[]) {
		int res = 0;
		for (int val : tabInt) {
			if (isPair(val)==true){ 
				res += 1; 
			}
		}
		
		return res;
	}
	
	public static int[] somTab(int tabInt1[], int tabInt2[]) {
		
		for (int i = 0; i < tabInt1.length; i++) {
			tabInt1[i] += tabInt2[i];
		}
		
		return tabInt1;
	}
	
	public static boolean isTabElemSupNb(int tabInt[], int a) {
		int som = 0;
		for (int i : tabInt) {
			if(i < 0) throw new IllegalArgumentException("Must be > 0!");
			if ((som += i) > a) return true;
		}
		return false;
	}

}
