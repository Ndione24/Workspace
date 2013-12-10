package up5.mi.viethi.tp9.RepertoireMVC;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class PanelVisuRepertoire extends JPanel {
	private JTextArea zoneDesContacts = new JTextArea(30, 30);
	private Repertoire repertoire;

	public PanelVisuRepertoire(Repertoire repertoire) {
		super(new BorderLayout());
		this.repertoire = repertoire;
		this.zoneDesContacts.setEditable(false);
		this.add(new JScrollPane(this.zoneDesContacts), BorderLayout.CENTER);
		repertoireModifie();
	}

	private void repertoireModifie() {
		this.zoneDesContacts.setText(repertoire.toString());
	}

	public void entreeAjoute(String nom, String tel) {
		repertoireModifie();
	}

}
