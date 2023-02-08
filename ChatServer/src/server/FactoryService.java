package server;

import java.net.Socket;

public interface FactoryService {
	public Service getService(Socket n);
}
