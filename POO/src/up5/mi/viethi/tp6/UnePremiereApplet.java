package up5.mi.viethi.tp6;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JApplet;
import javax.swing.JButton;

public class UnePremiereApplet extends JApplet{

	public void init() {
		final JButton button = new JButton("Cliquer pour afficher l'heure");
		this.getContentPane().add(button);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				button.setText(new Date().toString());
			}
		});
	}

}
