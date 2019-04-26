package _02_Chat_Application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatClient2 {

	private String ip;
	private int port;
	
	Socket connection;
	
	ObjectOutputStream os;
	ObjectInputStream is;
	
	public ChatClient2(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}
	
	public void start() {
		
		try {
			
			connection = new Socket(ip, port);
			
			os = new ObjectOutputStream(connection.getOutputStream());
			is = new ObjectInputStream(connection.getInputStream());
			
			os.flush();
			
			while (connection.isConnected()) {
				
			}
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void sendText(String text) {
		try {
			os.writeObject(text);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
