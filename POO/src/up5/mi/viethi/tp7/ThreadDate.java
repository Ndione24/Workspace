package up5.mi.viethi.tp7;

import java.util.Date;

public class ThreadDate extends Thread {
	private int periode;
	private Date dateLimite;

	/**
	 * crée un thread d'affichage de la date toutes les 'periode' secondes
	 * pendant 'duree' secondes
	 */
	public ThreadDate(int periode, int duree) {
		this.periode = periode;
		this.dateLimite = new Date(new Date().getTime() + duree * 1000);
	}

	/**
	 * le travail à effectuer par ce thread : affichage à intervalle régulier
	 * d'une date jusqu'à une date limite
	 */
	public void run(){
		boolean continuer = true;
		while (continuer) {
			Date dateActuelle = new Date();
			if (dateActuelle.after(dateLimite)) continuer = false;
			else { System.out.println("Il est "+ dateActuelle);
				try { Thread.sleep(periode *1000); } 
				catch (InterruptedException exp){ exp.printStackTrace(); }
			}
		}
	}
	
	public static void main(String[] args) {
		ThreadDate td = new ThreadDate(1, 30);
		td.run();
	}
}
