package IGImage;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelSelectTrans extends JPanel {

	public PanelSelectTrans() {
		setLayout(new FlowLayout());
		JButton bGris, bEgalisation, bNormalisation, bBinarisation, bOTSU;
		bGris = new JButton("Gris");
		bNormalisation = new JButton("Normalisation");
		bEgalisation = new JButton("Egalisation");
		bBinarisation = new JButton("Binarisation");
		bOTSU = new JButton("Otsu");
		
		add(bGris);
		add(bNormalisation);
		add(bEgalisation);
		add(bBinarisation);
		add(bOTSU);
		
		AuditeurSelectTrans auditeur = new AuditeurSelectTrans();
		bGris.addActionListener(auditeur);
		bNormalisation.addActionListener(auditeur);
		bEgalisation.addActionListener(auditeur);
		bBinarisation.addActionListener(auditeur);
		bOTSU.addActionListener(auditeur);
	}
}
