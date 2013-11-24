package up5.mi.viethi.tp4.IGDate;

import java.awt.BorderLayout;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class IGDate extends JPanel {

	public static void main(String[] args) {
		Date date = new Date();
		JFrame frame = new JFrame("Mettre à jour la date !");
		JPanel panel = new PanelDate(date);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
	}

}
