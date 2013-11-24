package up5.mi.viethi.tp1Test;

import up5.mi.pary.term.Terminal;
import up5.mi.viethi.tp1.UtilString;


public class TestUtilString {
	
	public static void main(String[] args) {

	    Terminal term=new Terminal("String",400,400);
	    // changement de la fonte pour une fonte fixe
	    term.setFontFamilyName("Monospaced");
	    
	    for (int i=0;i < 5;i++)

	        term.println(UtilString.cadrerADroite(""+Math.pow(10,i),10));

	    term.println(UtilString.cadrerAGauche("bonjour",15)+UtilString.cadrerADroite("bonjour",15));
	    term.println(UtilString.cadrerAGauche("b",15)+UtilString.cadrerAGauche("bo",15));
	    term.println(UtilString.cadrerAGauche("tropLongtropLongtropLongtropLong",15)+UtilString.cadrerAGauche("bo",15));
	    term.end();
	}

}
