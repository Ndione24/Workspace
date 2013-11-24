package up5.mi.viethi.tp1;

public class UtilString {
	
	/*
	 * affichage de la chaine Bonjour sur 15 caractères avec cadrage à droite :
	(pour des raisons de lisibilité, les espaces sont remplacés par des _ )
	--------Bonjour
	 */
	public static String getStringFromChar(char c, int n){
		String res="";
		for (int i=0;i<n;i++) res+=c;
		return res;
	} 
	
	public static String cadrerAGauche(String str, int nbCar) {
		// hello, 3
		if (str.length() > nbCar) return str.substring(0,nbCar);
	
		else if (str.length() < nbCar) {
			return str + getStringFromChar('-', nbCar-str.length());
		}
		else throw new IllegalArgumentException("Error");
		
	}

	public static String cadrerADroite(String str, int nbCar) {
		
		if (str.length() > nbCar) return str.substring(0,nbCar);
		
		if (str.length() < nbCar) {
			return getStringFromChar('-', nbCar-str.length())+str;
		}
		else throw new IllegalArgumentException("Error");
	}

}
