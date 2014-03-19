package abalone.view;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class DialogOption extends JDialog implements ActionListener {

	private JPanel panel, sousPanJ1, sousPanJ2, panelBoutons;
	private DefaultComboBoxModel comboModel1, comboModel2;
	private JComboBox colorChoice1, colorChoice2;
	private TitledBorder title;
	private JTextField textFieldJ1, textFieldJ2;
	private JCheckBox ia;

	public DialogOption(JFrame owner, String title) {
		super(owner, title);
		setLocationRelativeTo(owner);
		setSize(300, 200);
		setAlwaysOnTop(true);
		setContentPane(buildContentPane());
		setVisible(true);
	}

	private JPanel buildContentPane() {
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		// TODO
		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}

}
