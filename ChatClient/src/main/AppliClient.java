package main;

import service.ServiceChat;

public class AppliClient {

	public static void main(String[] args) {
		IHMConsole ihm = new IHMConsole();
		
		new Thread(new ServiceChat(ihm, 5000)).start();
	}

}
