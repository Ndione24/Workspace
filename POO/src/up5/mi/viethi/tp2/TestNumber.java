package up5.mi.viethi.tp2;

import java.util.ArrayList;

public class TestNumber {
	
	/** rend le Number le plus grand de cette ArrayList non vide de Number
	la comparaison entre 2 numbers se fait en comparant les doubles correspondants
	*/
	public static Number getMaximum(ArrayList list){
		Number nbcur = (Number) list.get(0);
		
		for (int i = 1; i < list.size(); ++i) {
			if ( nbcur.doubleValue() < ((Number)list.get(i)).doubleValue() ) {
				nbcur = (Number)list.get(i);
			}
		}
		return nbcur;
	}
	public static void main(String[] args) {

	    ArrayList list = new ArrayList();
	    list.add(new Integer(6));
	    list.add(new Double(8.2));
	    list.add(new Short((short)10));
	    list.add(new Integer(-16));
	    list.add(new Float(4));
	    System.out.println(getMaximum(list));// affiche 10
	    System.out.println(getMaximum(list).getClass().getName()); // affiche java.lang.Short

	}
}
