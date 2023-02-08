package main;

import java.io.IOException;

import server.Server;

public class AppliServ {
	public static void main(String[] args) {
		try {
			new Thread(new Server(new FactoryChatService())).start();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
