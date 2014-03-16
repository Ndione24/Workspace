package IGImage;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelEval extends JPanel {
	public PanelEval() {
		add(new JLabel("Pré-traitement"));
		ArrayList<JButton> listButton = new ArrayList<JButton>();
		for (int i = 1; i < 18; i++) {
			listButton.add(new JButton(""+i));
		}
		ActionListener listeneur = new AuditeurEval();
		JButton bCur;
		for (int i = 0; i < listButton.size(); i++) {
			bCur = listButton.get(i);
			add(bCur);
			bCur.addActionListener(listeneur);
		}

	}
}
