package _02_Chat_Application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JLabel;

public class ChatClient2 {

	private String ip;
	private int port;

	Socket connection;

	ObjectOutputStream os;
	ObjectInputStream is;

	JLabel label;
	
	String chatLog = "";
	
	String name;
	String other;

	public ChatClient2(String ip, int port, JLabel label, String name) {
		this.ip = ip;
		this.port = port;
		this.label = label;
		this.name = name;
	}

	public void start() {

		try {

			connection = new Socket(ip, port);

			os = new ObjectOutputStream(connection.getOutputStream());
			is = new ObjectInputStream(connection.getInputStream());

			os.flush();
			
			os.writeObject(name);
			
			try {
				other = (String) is.readObject();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}

			while (connection.isConnected()) {

				try {

					String receive = (String) is.readObject();
					chatLog += other + ": " + receive + "<br>";
//					String prev = label.getText();
					label.setText("<html>" + chatLog + "</html>");

				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void sendText(String text) {
		try {
			if (!text.trim().equals("")) {
				os.writeObject(text);
				chatLog += name + ": " + text + "<br>";
//				String prev = label.getText();
				label.setText("<html>" + chatLog + "</html>");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
