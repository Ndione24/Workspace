package chat.model;

import java.util.Calendar;

public class Message {
	private String user, message, date;

	public Message(String user, String message) {
		this.user = user;
		this.message = message;
		Calendar c = Calendar.getInstance();
		this.date = "["	+ c.get(Calendar.HOUR_OF_DAY)+':'
						+ c.get(Calendar.MINUTE)+':'
						+ c.get(Calendar.SECOND)+"]";
	}

	public String getUser() {
		return user;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return  date + user + " --> " + message;
	}
}
