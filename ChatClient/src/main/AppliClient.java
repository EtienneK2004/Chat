package main;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

import message.Message;
import user.User;

public class AppliClient {
	
	private static User USER;
	
	private static void envoyerMsg(Socket s, String text,ObjectOutputStream objectOutputStream) throws IOException {
		Message m = new Message(USER, text);
		
	    objectOutputStream.writeObject(m);
	}

	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(System.in);
			Socket s = new Socket("localhost", 5000);
			OutputStream outputStream = s.getOutputStream();
		    ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
			
			
			System.out.println("votre nom?");
			USER = new User(sc.nextLine());
			
			new Thread(new Listener(s)).start();
			
			while(true) {
				envoyerMsg(s, sc.nextLine(), objectOutputStream);
			}
		}catch(IOException e) {
			e.printStackTrace();
		}

	}

}
