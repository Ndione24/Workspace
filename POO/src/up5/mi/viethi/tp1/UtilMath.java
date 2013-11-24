package up5.mi.viethi.tp1;

public class UtilMath {
	
	public static int somNbEntier(int nb1, int nb2, int nb3) {
		
		return nb1+nb2+nb3;
	}
	
	public static int factEntierPos(int n) {
		
		// On veut uniquement les entiers positifs
		if(n < 0) throw new IllegalArgumentException("Must be > 0!");
		// 0!=1
		else if (n == 0) return 1;
		
		// n! = n * (n-1)! => 4! = 4*3*2*1
		int fact = n;
		while (n > 1) {
			fact *= --n; 
		}
		
		return fact;	
	}
	
	public static int comb(int n, int p) {
		return factEntierPos(n)/(factEntierPos(p)*factEntierPos(n-p));
	}

}
