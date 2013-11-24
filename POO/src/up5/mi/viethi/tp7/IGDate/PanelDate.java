package up5.mi.viethi.tp7.IGDate;

import java.awt.BorderLayout;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class PanelDate extends JPanel {

	public PanelDate(Date date) {
		super(new BorderLayout());
		JLabel labelDate = new JLabel("Date : " + date.toString());
		this.add(labelDate, BorderLayout.NORTH);
		Thread dateUp = new ThreadDate(labelDate);
		dateUp.start();
	}
}