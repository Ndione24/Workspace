package chat.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import chat.model.Chat;
import chat.model.Message;

public class PanelVisuChat extends JPanel {
	private JTextArea zoneDesMessagesRecus = new JTextArea(7, 10);
	private Chat chat;

	public PanelVisuChat(Chat chat) {
		super(new BorderLayout());
		this.chat = chat;
		this.zoneDesMessagesRecus.setEditable(false);
		this.add(new JScrollPane(this.zoneDesMessagesRecus),
				BorderLayout.CENTER);
		chatModifie();
	}


	public void messageAjoute(Message message) {
		chatModifie();
	}

	private void chatModifie() {
		this.zoneDesMessagesRecus.setText(chat.toString());
	}
}
