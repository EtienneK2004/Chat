package message;

import java.io.Serializable;

import user.User;

public class Message implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4815688877236184158L;
	private User sender;
	private String text;

	public Message(User s, String text) {
		this.sender = s;
		this.text = text;
	}
	
	@Override
	public String toString() {
		return sender+": "+text;
	}

	public User getUser() {
		return sender;
	}
}
