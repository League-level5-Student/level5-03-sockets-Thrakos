package _02_Chat_Application;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.SynchronousQueue;

import javax.swing.JOptionPane;

public class ChatServer {
	private int port;

	private ServerSocket server;
	private Socket connection;

	DataOutputStream os;
	DataInputStream is;
	
	String messages = "";
	String message = "";

	public ChatServer(int port) {
		this.port = port;
	}

	public void start(){
		
		try {			
			server = new ServerSocket(port);
			
			JOptionPane.showMessageDialog(null, messages);
			
			connection = server.accept();
			
			os = new DataOutputStream(connection.getOutputStream());
			is = new DataInputStream(connection.getInputStream());

			os.flush();
			
			while (connection.isConnected()) {
				try {
					
					messages = messages + is.readUTF();
					System.out.println(is.readUTF());
					
				}catch(EOFException e) {
					JOptionPane.showMessageDialog(null, "Connection Lost");
					System.exit(0);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
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
	
}
