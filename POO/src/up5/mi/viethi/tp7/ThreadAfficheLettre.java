package up5.mi.viethi.tp7;

public class ThreadAfficheLettre extends Thread {

	/** la lettre à afficher */
	private char lettre;
	/** le nombre d'affichage à effectuer */
	private int lim;
	/** le nombre d'affichage effectué */
	private int cpt = 0;
	/** le nombre de seconde par période */
	private int periode = 1;

	/** crée un thread affichant 'lim' fois la lettre 'lettre' */
	public ThreadAfficheLettre(char lettre, int lim) {
		this.lettre = lettre;
		this.lim = lim;
	}

	/** le travail à effectuer par ce thread */
	public void run() {
		while (cpt <= lim) {
			cpt++;
			System.out.print(lettre);
			System.out.flush();
			
			try {
				Thread.sleep(periode*10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread ta = new ThreadAfficheLettre ('a',20);
		Thread tb = new ThreadAfficheLettre ('b',20);
		//ta.start();
		ta.join();
		System.out.println("he");
		tb.start();
	}

}
