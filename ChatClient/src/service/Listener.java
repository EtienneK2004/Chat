package service;

import java.io.IOException;
import java.io.ObjectInputStream;

import message.Message;

public class Listener implements Runnable {
	private IHM ihm;
	private ObjectInputStream input;

	public Listener(ObjectInputStream objectInputStream, IHM ihm) {
		this.input = objectInputStream;
		this.ihm = ihm;
	}

	@Override
	public void run() {
		try {
			while(true) {
				try {
					//Reads messages from the socket input stream and calls the ihm
					Message m = (Message) input.readObject();
					ihm.handleMessage(m);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}catch(IOException e) {
			e.printStackTrace();
		}

	}

}
