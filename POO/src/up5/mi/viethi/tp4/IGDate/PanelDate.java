package up5.mi.viethi.tp4.IGDate;

import java.awt.BorderLayout;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class PanelDate extends JPanel {

	public PanelDate(Date date) {
		super(new BorderLayout());
		JLabel labelDate = new JLabel("Date : " + date.toString());
		this.add(labelDate, BorderLayout.NORTH);
		JButton bUpdate = new JButton("Mettre à jour !");
		this.add(bUpdate, BorderLayout.SOUTH);

		JLabel labelTemps = new JLabel("Temps écoulé : " + 0 + " sec");
		this.add(labelTemps, BorderLayout.CENTER);
		
		// créer un écouteur d'événements
		ActionListener ecouteur = new EcouteurClick(date, labelDate, labelTemps);
		// 'ecouteur' devient un écouteur d'événements pour
		// les clicks sur le bouton bOK
		bUpdate.addActionListener(ecouteur);
		
	}
}
