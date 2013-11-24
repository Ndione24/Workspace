package up5.mi.viethi.tp4.IGCouleurBouton;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JPanelCouleur extends JPanel {

	public JPanelCouleur() {
		
		super(new BorderLayout( ));
		
		JButton bRouge = new JButton("couleur rouge");
		bRouge.setBackground(Color.RED);
		this.add(bRouge, BorderLayout.WEST);
		
		JButton bVert = new JButton("couleur vert");
		bVert.setBackground(Color.GREEN);
		this.add(bVert, BorderLayout.EAST);
		
		JLabel label = new JLabel("Je change de couleur");
		this.add(label, BorderLayout.SOUTH);
		
		Auditeur auditeur = new Auditeur(label);
		bRouge.addActionListener(auditeur);
		bVert.addActionListener(auditeur);
		
	}
	
}
