package service;

import message.Message;
import user.User;

public interface IHM {

	void handleMessage(Message m);

	Message nextMessage();

	User getUser();

}
