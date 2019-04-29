package _02_Chat_Application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JLabel;
import javax.swing.JTextArea;

public class ChatServer2 {

	private int port;

	private ServerSocket server;
	private Socket connection;

	ObjectOutputStream os;
	ObjectInputStream is;

	//JLabel label;
	JTextArea label;
	
	String chatLog = "";
	
	String name;
	String other;

	public ChatServer2(int port, /*JLabel*/ JTextArea label, String name) {
		this.port = port;
		this.label = label;
		this.name = name;
	}

	public void start() {

		try {

			server = new ServerSocket(port);

			connection = server.accept();

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
//					chatLog += other + ": " + receive + "<br>";
					chatLog += other + ": " + receive + "\n";
//					label.setText("<html>" + chatLog + "</html>");
					label.setText(chatLog);

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
			if (!text.trim().equals("")) {
				os.writeObject(text);
//				chatLog += name + ": " + text + "<br>";
				chatLog += name + ": " + text + "\n";
//				label.setText("<html>" + chatLog + "</html>");
				label.setText(chatLog);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
