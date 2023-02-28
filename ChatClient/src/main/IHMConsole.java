package main;

import java.util.Scanner;

import message.Message;
import service.IHM;
import user.User;

public class IHMConsole implements IHM {
	private Scanner sc;
	private User user;

	public IHMConsole() {
		synchronized(System.in) {
			System.out.println("Quel est votre nom ?");
			this.sc = new Scanner(System.in);
			this.user = new User(sc.nextLine());
			
		}
	}

	@Override
	public void handleMessage(Message m) {
		if(!m.getUser().equals(user))
			System.out.println(m);

	}

	@Override
	public User getUser() {
		return user;
	}

	@Override
	public Message nextMessage() {
		return new Message(user, sc.nextLine());
		
	}

}
