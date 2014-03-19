package chat.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import chat.model.Chat;

public class PanelChat extends PanelVisuChat {

	private JTextField messageAEnvoyer = new JTextField(20);
	private List<PanelChatListener> panelListeners = new ArrayList<PanelChatListener>();

	public PanelChat(Chat chat) {
		super(chat);
		JPanel panelEnvoi = new JPanel(new BorderLayout());
		panelEnvoi.add(this.messageAEnvoyer, BorderLayout.CENTER);
		JButton buttonEnvoi = new JButton("Envoi");
		panelEnvoi.add(buttonEnvoi, BorderLayout.EAST);
		this.add(panelEnvoi, BorderLayout.SOUTH);
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fireMessageAEnvoyer();
			}
		};
		buttonEnvoi.addActionListener(listener);
		this.messageAEnvoyer.addActionListener(listener);
	}

	public void addPanelChatListener(PanelChatListener listener) {
		this.panelListeners.add(listener);
	}

	private void fireMessageAEnvoyer() {
		for (PanelChatListener listener : this.panelListeners)
			listener.messageAEnvoyer(this.messageAEnvoyer.getText());
		this.messageAEnvoyer.setText("");
	}
}
