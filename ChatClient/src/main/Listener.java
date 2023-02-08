package main;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

import message.Message;

public class Listener implements Runnable {
	
	private Socket s;

	public Listener(Socket s) {
		this.s = s;
	}

	@Override
	public void run() {
		try {
			InputStream inputStream = s.getInputStream();
		    ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
			while(true) {
				try {
					Message m = (Message) objectInputStream.readObject();
					System.out.println(m);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}catch(IOException e) {
			e.printStackTrace();
		}

	}

}
