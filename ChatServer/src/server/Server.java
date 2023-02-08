package server;

import java.io.IOException;
import java.net.ServerSocket;

public class Server implements Runnable {

	private ServerSocket servSocket;
	private FactoryService factory;
	
	public Server(FactoryService f) throws IOException {
		this.servSocket = new ServerSocket(5000);
		this.factory = f;
	}

	@Override
	public void run() {
		System.out.println("serveurlancé");
		try {
			while(true) 
				new Thread(factory.getService(servSocket.accept())).start();
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}

}
