package abalone.controller;

public class GameController {
	
	public GameController() {
		// TODO Auto-generated constructor stub
	}
	
	/** Determine si les coordonnees en entree sont hors-plateau */
	public static boolean isOut(int i, int j) {
		return  ((i==0) && (j > 4) 		||
				 (i==1) && (j > 5) 		||
				 (i==2) && (j > 6) 		||
				 (i==3) && (j > 7) 		||
				 (i==4) && (j > 8) 		|| 
				 (i > 4) && (j < i-4) 	||
				 (j < 0) || (j > 8) 	|| 
				 (i < 0) || (i > 8));
	}

}
