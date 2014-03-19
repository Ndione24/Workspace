package chat.model;

import java.util.ArrayList;
import java.util.List;

public class Chat implements IChat {
	/** la liste des messages */
	private List<Message> listMessages;
	/** la liste des écouteurs */
	private List<ChatListener> listeners;

	public Chat() {
		this.listMessages = new ArrayList<Message>();
		this.listeners = new ArrayList<ChatListener>();
	}

	public void addMessage(String user, String text) {
		Message message = new Message(user, text);
		addMessage(message);
		fireMessageAjoute(message);
	}

	@Override
	public void addMessage(Message message) {
		listMessages.add(message);
	}

	/**
	 * 
	 * @param indexFrom
	 *            l'indice du premier message souhaité
	 * @return les messages à partir de cet indice
	 */
	@Override
	public List<Message> getMessages(int indexFrom) {
		return new ArrayList<Message>(this.listMessages.subList(indexFrom,
				this.listMessages.size()));
	}

	/**
	 * le nombre de messages du chat
	 * 
	 * @return le nombre de messages du chat
	 */
	public int getMessagesCount() {
		return this.listMessages.size();
	}

	/** ajout d'un écouteur */
	public void addChatListener(ChatListener listener) {
		this.listeners.add(listener);
	}

	/** avertie tous les écouteurs qu'un nouveau message a été ajouté au chat */
	private void fireMessageAjoute(Message message) {
		for (ChatListener listener : this.listeners)
			listener.messageAjoute(this, message);
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (Message message : listMessages)
			sb.append(message + "\n");
		return sb.toString();
	}
}
