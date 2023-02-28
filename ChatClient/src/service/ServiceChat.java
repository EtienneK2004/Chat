package service;

import java.io.*;
import java.net.Socket;

public class ServiceChat implements Runnable{
	private IHM ihm;
	private int port;

	public ServiceChat(IHM ihm, int port) {
		this.ihm = ihm;
		this.port = port;
	}
	
	

	@Override
	public void run() {
		try {
			
			try (Socket s = new Socket("localhost", port)) {
				OutputStream outputStream = s.getOutputStream();
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
				InputStream inputStream = s.getInputStream();
				ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
				
				objectOutputStream.writeObject(ihm.getUser());
				
				Thread listener = new Thread(new Listener(objectInputStream, ihm));
				Thread sender = new Thread(new Sender(objectOutputStream, ihm));
				listener.start(); sender.start();
				
				try {
					listener.join();
					sender.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
			
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}
