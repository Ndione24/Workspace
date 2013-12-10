package up5.mi.viethi.tp9.RepertoireMVC;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelRepertoire extends PanelVisuRepertoire {
	// Corriger le fait qu'un message ne nécessite qu'un champ a envoyer et un contact deux
	private JTextField contactAEnvoyer = new JTextField(20);
	private JTextField nomAEnvoyer = new JTextField(20);
	private JTextField telAEnvoyer = new JTextField(20);

	private List<PanelRepertoireListener> panelListeners = new ArrayList<PanelRepertoireListener>();

	public PanelRepertoire(Repertoire repertoire) {
		super(repertoire);
		JPanel panelEnvoi = new JPanel(new BorderLayout());
		panelEnvoi.add(this.contactAEnvoyer, BorderLayout.CENTER);
		JButton buttonEnvoi = new JButton("Envoi");
		panelEnvoi.add(buttonEnvoi, BorderLayout.EAST);
		this.add(panelEnvoi, BorderLayout.SOUTH);
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fireRepertoireAEnvoyer();
			}
		};
		buttonEnvoi.addActionListener(listener);
		this.contactAEnvoyer.addActionListener(listener);
	}

	public void addPanelRepertoireListener(PanelRepertoireListener listener) {
		this.panelListeners.add(listener);
	}

	private void fireRepertoireAEnvoyer() {
		for (PanelRepertoireListener listener : this.panelListeners)
			listener.contactAEnvoyer(this.nomAEnvoyer.getText(),this.telAEnvoyer.getText());
		this.nomAEnvoyer.setText("");
		this.telAEnvoyer.setText("");
	}

}
