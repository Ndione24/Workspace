package chat.model;

import java.util.List;

public interface IChat {
	public List<Message> getMessages(int indexFrom);
	public void addMessage(Message message);
}
