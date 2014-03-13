package IGImage;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelSelectFiltre extends JPanel {

	public PanelSelectFiltre() {
		setLayout(new FlowLayout());
		JButton bFiltreMoyen, bFiltreMedian, bFiltreGaussien, bFiltreSobel;
		add(new JLabel("Filtre"));
		bFiltreMoyen = new JButton("Moyenneur");
		bFiltreMedian = new JButton("Median");
		bFiltreGaussien = new JButton("Gaussien");
		bFiltreSobel = new JButton("Sobel");
		
		add(bFiltreMoyen);
		add(bFiltreMedian);
		add(bFiltreGaussien);
		add(bFiltreSobel);
		
		ActionListener auditeur = new AuditeurSelectFiltre();
		bFiltreMoyen.addActionListener(auditeur);
		bFiltreMedian.addActionListener(auditeur);
		bFiltreGaussien.addActionListener(auditeur);
		bFiltreSobel.addActionListener(auditeur);
	}
}
