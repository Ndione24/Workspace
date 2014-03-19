package chat.model;

public interface ChatListener {
	/** une nouveau message a été ajouté dans le chat */
	void messageAjoute(Chat chat,Message message);
}
