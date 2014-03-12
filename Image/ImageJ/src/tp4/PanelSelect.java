package tp4;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelSelect extends JPanel {

	public PanelSelect() {
		setLayout(new FlowLayout());
		JButton bGris, bEgalisation, bNormalisation, bSeuillage;
		bGris = new JButton("Gris");
		bNormalisation = new JButton("Normalisation");
		bEgalisation = new JButton("Egalisation");
		bSeuillage = new JButton("Seuillage");
		
		add(bGris);
		add(bNormalisation);
		add(bEgalisation);
		add(bSeuillage);
		
		AuditeurSelect auditeur = new AuditeurSelect();
		bGris.addActionListener(auditeur);
		bNormalisation.addActionListener(auditeur);
		bEgalisation.addActionListener(auditeur);
		bSeuillage.addActionListener(auditeur);
	}
}
