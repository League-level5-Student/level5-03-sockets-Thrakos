package _02_Chat_Application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JLabel;

public class ChatServer2 {

	private int port;
	
	private ServerSocket server;
	private Socket connection;
	
	ObjectOutputStream os;
	ObjectInputStream is;
	
	JLabel label;
	
	public ChatServer2(int port, JLabel label) {
		this.port = port;
		this.label = label;
	}
	
	public void start() {
		
		try {
			
			server = new ServerSocket(port);
			
			connection = server.accept();
			
			os = new ObjectOutputStream(connection.getOutputStream());
			is = new ObjectInputStream(connection.getInputStream());
			
			os.flush();
			
			while (connection.isConnected()) {
				try {
					String message = (String) is.readObject();
					
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
		
		
	}
	
	public String getIPAddress() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			return "ERROR!!!!!";
		}
	}

	public int getPort() {
		return port;
	}
	
	public void sendText(String text) {
		try {
			os.writeObject(text);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
