package _02_Chat_Application;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

public class ChatClient {
	
	private String ip;
	private int port;

	Socket connection;

	DataOutputStream os;
	DataInputStream is;
	
	String message = "";
	String messages = "";

	public ChatClient(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}
	
	public void start(){
		try {

			connection = new Socket(ip, port);

			os = new DataOutputStream(connection.getOutputStream());
			is = new DataInputStream(connection.getInputStream());

			os.flush();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		while (connection.isConnected()) {
			try {

				messages = messages + is.readUTF();
				System.out.println(is.readUTF());
				
			} catch (Exception e) {
				System.out.println("here!");
			}
		}
	}
	
	public void sendMessage(String send) {
		
		if (os != null) {
			try {
				os.writeUTF(send);
				os.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
