package main;

import java.net.Socket;

import chat.ChatService;
import server.FactoryService;
import server.Service;

public class FactoryChatService implements FactoryService{

	@Override
	public Service getService(Socket s) {
		return new ChatService(s);
	}

}
