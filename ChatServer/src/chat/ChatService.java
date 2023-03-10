package chat;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import message.Message;
import request.Request;
import server.Service;
import user.User;

public class ChatService implements Service {

	private Socket socket;
	private User user;
	private static HashMap<User, ObjectOutputStream> all = new HashMap<>();
	private List<Message> historique;

	public ChatService(Socket accept) {
		this.socket = accept;
		this.historique = new LinkedList<>();
	}
	/*
	 * @brief sends the message to all users currently connected
	 */
	private void sendToAll(Message m) throws IOException {
		for(ObjectOutputStream output : all.values()) {
			System.out.println("je renvoie "+m);
			output.writeObject(m);
		}
	}

	@Override
	public void run() {
		try {
			System.out.println("Connexion");
			InputStream inputStream = socket.getInputStream();
		    ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
		    
		    OutputStream outputStream = socket.getOutputStream();
		    ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
		    
		    //Add the connecting user to the hashmap
		    Object user = objectInputStream.readObject();
		    if(user instanceof User) {
				this.user = (User) user;
			}
		    all.put(this.user, objectOutputStream);
		    
		    
		    
			while(true) {
				try {
					//Reads object from the socket
					Object o = objectInputStream.readObject();
					if(o instanceof Message) {
						Message msg = (Message) o;
						historique.add(msg);
						sendToAll(msg);
					}
					else if(o instanceof Request) {
						objectOutputStream.writeObject(historique);
					}
					
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}catch(IOException e) {
			if(e instanceof SocketException) {
				//User disconnection
				try {
					this.socket.close();
					all.remove(this.user);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			else {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}

}
