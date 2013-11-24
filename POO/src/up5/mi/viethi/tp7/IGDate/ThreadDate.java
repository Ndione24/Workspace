package up5.mi.viethi.tp7.IGDate;

import java.awt.Label;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class ThreadDate extends Thread {
	private JLabel date;
	
	/**
	 * crée un thread d'affichage de la date toutes les 'periode' secondes
	 * pendant 'duree' secondes
	 */
	public ThreadDate(JLabel labelDate) {
		this.date = labelDate;
	}

	/**
	 * le travail à effectuer par ce thread : affichage à intervalle régulier
	 */
	public void run(){
		while (true) {
			SwingUtilities.invokeLater(new Runnable(){public void run(){date.setText(new Date().toString());}});
			try { Thread.sleep(1000); } 
			catch (InterruptedException exp){ exp.printStackTrace(); }
		}
	}
}
