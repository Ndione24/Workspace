package up5.mi.viethi.tp5.IGSecret;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JPanelJeu extends JPanel {
	
	private static final int minInit = 0;
	private static final int maxInit = 100;

	public JPanelJeu() {
		super(new BorderLayout());
		
		JLabel label = new JLabel("je propose " + String.valueOf((maxInit+minInit)/2));
		this.add(label, BorderLayout.NORTH);
		
		JPanel command = new JPanel();
		command.setLayout(new FlowLayout());
		
		JButton bPlus = new JButton("+");
		command.add(bPlus);
		JButton bMoins = new JButton("-");
		command.add(bMoins);
		JButton bFinded = new JButton("Trouve");
		command.add(bFinded);
		
		this.add(command, BorderLayout.CENTER);
		
		Auditeur auditeur = new Auditeur(minInit, maxInit, label);
		bPlus.addActionListener(auditeur);
		bMoins.addActionListener(auditeur);
		bFinded.addActionListener(auditeur);
	}
	
}
