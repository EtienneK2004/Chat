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
	
	private void envoyerMsg(Message m) throws IOException {
		//Message m = new Message(user, text);
		
	    output.writeObject(m);
	}

	@Override
	public void run() {
		try {
			while(true) {
				envoyerMsg(ihm.nextMessage());
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

}
