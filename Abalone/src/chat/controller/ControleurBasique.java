package chat.controller;

import javax.swing.JPanel;

import chat.model.Chat;
import chat.model.ChatListener;
import chat.model.Message;
import chat.tools.FrameUtil;
import chat.view.PanelChat;
import chat.view.PanelChatListener;

public class ControleurBasique {
	private final Chat chat = new Chat();

	public ControleurBasique(String userName) {
		FrameUtil.createFrame("PanelVisu", createPanelChat(userName));
	}

	public ControleurBasique(int nbChat) {
		for (int i = 1; i <= nbChat; i++) {
			String name = "etu" + "_" + i;
			FrameUtil.createFrame(name, createPanelChat(name));
		}
	}

	protected Chat getChat() {
		return this.chat;
	}

	private JPanel createPanelChat(final String userName) {
		final PanelChat panel = new PanelChat(chat);
		chat.addChatListener(new ChatListener() {
			@Override
			public void messageAjoute(Chat chat, Message message) {
				panel.messageAjoute(message);
			}
		});
		// abonnement du controleur aux événements de l'interface graphique
		panel.addPanelChatListener(new PanelChatListener() {
			@Override
			public void messageAEnvoyer(String text) {
				// dans un cas réel, on ferait appel ici au serveur de chat ...
				// on se contente de modifier le modèle
				chat.addMessage(userName, text);
			}
		});
		return panel;
	}

	public static void main(String[] args) {
		new ControleurBasique(2);
	}
}
