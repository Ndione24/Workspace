package IGImage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelSelectEval extends JPanel {
	public PanelSelectEval() {
		add(new JLabel("TP Noté"));
		JButton start = new JButton("Lancer");
		add(start);
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tpnote.Main.main(null);
			}
		});
	}
}
