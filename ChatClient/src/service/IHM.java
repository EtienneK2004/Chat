package service;

import message.Message;
import user.User;

public interface IHM {
	
	/*
	 * @brief This function is called when a message is recieved
	 */
	void handleMessage(Message m);
	
	/*
	 * @brief This function is called, waiting for a message to send
	 */
	Message nextMessage();
	
	
	/*
	 * @brief returns the user asociated with the interface
	 */
	User getUser();

}
