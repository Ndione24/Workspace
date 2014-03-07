package tp4;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelSelect extends JPanel {

	public PanelSelect() {
		setLayout(new FlowLayout());
		JButton bGris, bEgalisation, bNormalisation, bSeuillage;
		bGris = new JButton("Gris");
		bEgalisation = new JButton("Egalisation");
		bNormalisation = new JButton("Normalisation");
		bSeuillage = new JButton("Seuillage");
		
		add(bGris);
		add(bEgalisation);
		add(bNormalisation);
		add(bSeuillage);
		
		AuditeurSelect auditeur = new AuditeurSelect();
		bGris.addActionListener(auditeur);
		bEgalisation.addActionListener(auditeur);
		bNormalisation.addActionListener(auditeur);
		bSeuillage.addActionListener(auditeur);
	}
}
