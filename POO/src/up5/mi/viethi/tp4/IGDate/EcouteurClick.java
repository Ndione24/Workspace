package up5.mi.viethi.tp4.IGDate;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JLabel;

public class EcouteurClick implements ActionListener {

	private Date date;// la date actuelle
	private JLabel labelTemps;
	private JLabel labelDate;

	public EcouteurClick(Date date, JLabel labelDate, JLabel labelTemps) {
		this.date = date;
		this.labelDate = labelDate;
		this.labelTemps = labelTemps;
	}

	public void actionPerformed(ActionEvent event) {
		long temps = ( System.currentTimeMillis() - this.date.getTime() ) / 1000;
		this.date = new Date();
		this.labelDate.setText("Date : " + date);
		this.labelTemps.setText("Temps écoulé : " + temps + " sec");
	}

}
