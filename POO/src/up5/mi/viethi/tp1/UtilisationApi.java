package up5.mi.viethi.tp1;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class UtilisationApi {

	public static String getChaineDesNombresDe1aN(int n){
		StringBuffer strNb = new StringBuffer();
		for (int i = 1; i <= n; ++i) {
			strNb.append(i);
		}
		return strNb.toString();
	}

	public static BigInteger factorielle (int n) {

		// On veut uniquement les entiers positifs
		if(n < 0) throw new IllegalArgumentException("Must be > 0!");
		// 0!=1
		else if (n == 0) return BigInteger.valueOf(1);

		BigInteger fact = BigInteger.valueOf(n);

		BigInteger nb = BigInteger.valueOf(n);

		// n! = n * (n-1)! => 4! = 4*3*2*1
		while (nb.compareTo(new BigInteger("1")) > 0) {
			nb = nb.add(BigInteger.valueOf(-1));
			fact = fact.multiply(nb);
		}

		return fact;
	}

	public static void afficherFact(int i) {
		System.out.print("fact("+ i+")=");
		System.out.println(factorielle(i));
		System.out.print("fact("+ i+")~=");
		System.out.println(factorielle(i).doubleValue());
	}

	public static void orderTimezone () {
	 	String [] ids = TimeZone.getAvailableIDs();
	 	Date cal = new GregorianCalendar().getTime();
	 	System.out.println("Heure locale : " + TimeZone.getDefault().getID()+" "+cal);
	 	for (String id : ids) {
	 		TimeZone.setDefault(TimeZone.getTimeZone(id));
	 		System.out.println(id+" "+cal);
		}
	}

	public static void main(String[] args) throws ParseException {
		orderTimezone();
	}

}


