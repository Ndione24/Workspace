package up5.mi.viethi.tp1Test;

import up5.mi.viethi.tp1.UtilTab;

public class TestUtilTab {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int tabInt[] = { 1, 2, 3, 4, 5, 6, 8, 9 };
		
		boolean res = UtilTab.isTabElemSupNb(tabInt, 1);
		System.out.print(res);
	}

}
