package _02_Chat_Application;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import _00_Click_Chat.networking.Server;

public class ChatApp2 {
	
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JLabel label = new JLabel();
	JTextField textField = new JTextField(25);
	JButton button = new JButton();
	
	String messages = "";

	ChatServer2 server;
	ChatClient2 client;
	
	public static void main(String[] args) {
		new ChatApp2();
	}
	
	public ChatApp2() {
		
		frame.add(panel);
		panel.add(label);
		panel.add(textField);
		button.setText("SEND");
		panel.add(button);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		int response = JOptionPane.showConfirmDialog(null, "Would you like to host a connection?", "Buttons!", JOptionPane.YES_NO_OPTION);
		
		if(response == JOptionPane.YES_OPTION) {
			
			String name = JOptionPane.showInputDialog("Who are you?");
			server = new ChatServer2(8080, label, name);
			frame.setTitle(name);
			JOptionPane.showMessageDialog(null, "ip: " + server.getIPAddress() + ", port: " + server.getPort());
			button.addActionListener((e) -> {
				String thing = textField.getText();
				textField.setText("");
				server.sendText(thing);
			});
			server.start();
			
		} else {

			String ipStr = JOptionPane.showInputDialog("Enter the IP Address");
			String prtStr = JOptionPane.showInputDialog("Enter the port number");
			String name = JOptionPane.showInputDialog("Who are you?");
			frame.setTitle(name);
			int port = Integer.parseInt(prtStr);
			client = new ChatClient2(ipStr, port, label, name);
			button.addActionListener((e) -> {
				String thing = textField.getText();
				textField.setText("");
				client.sendText(thing);
			});
			client.start();
			
		}
		
	}
}
