package _00_Click_Chat.networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class Client {
	private String ip;
	private int port;

	Socket connection;

	ObjectOutputStream os;
	ObjectInputStream is;

	public Client(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}

	public void start(){
		try {

			connection = new Socket(ip, port);

			os = new ObjectOutputStream(connection.getOutputStream());
			is = new ObjectInputStream(connection.getInputStream());

			os.flush();

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		while (connection.isConnected()) {
			try {
				String thing = (String) is.readObject();
				JOptionPane.showMessageDialog(null, /*is.readObject()*/ thing);
				System.out.println(/*is.readObject()*/ thing);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void sendClick() {
		try {
			if (os != null) {
				os.writeObject("CLICK SENT FROM CLIENT");
				os.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
