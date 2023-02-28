package service;

import java.io.IOException;
import java.io.ObjectOutputStream;

import message.Message;

public class Sender implements Runnable{
	private ObjectOutputStream output;
	private IHM ihm;

	public Sender(ObjectOutputStream output, IHM ihm) {
		this.output = output;
		this.ihm = ihm;
	}
	
	private void sendMessage(Message m) throws IOException {
	    output.writeObject(m);
	}

	@Override
	public void run() {
		try {
			while(true) {
				//calls the interface for a message to send to the server
				sendMessage(ihm.nextMessage());
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

}
