package _02_Chat_Application;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * Using the Click_Chat example, write an application that allows a server computer to chat with a client computer.
 */

public class ChatApp {

	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JTextField textBox = new JTextField(50);
	JLabel text = new JLabel();
	JButton send = new JButton();

	ChatServer chatServer;
	ChatClient chatClient;

	public ChatApp() {

		frame.add(panel);
		frame.setSize(300, 500);
		frame.setVisible(true);
		panel.add(text);
		panel.add(textBox);
		panel.add(send);
		send.setText("SEND");
		textBox.setSize(450, 10);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		int response = JOptionPane.showConfirmDialog(null, "Would you like to host the chat?", "Buttons!",
				JOptionPane.YES_NO_OPTION);
		if (response == JOptionPane.YES_OPTION) {

			chatServer = new ChatServer(4444);
			chatServer.messages = "ip address: " + chatServer.getIPAddress() + "\n waiting for a connection...";

			send.addActionListener((e) -> {
				if (!textBox.getText().equals("")) {
					String thing = textBox.getText();
					textBox.setText("");
					chatServer.sendMessage(thing + "\n\n");
					text.setText(chatServer.messages);
				}

			});

			chatServer.start();
		} else {

			String ip = JOptionPane.showInputDialog("enter ip address: ");
			chatClient = new ChatClient(ip, 4444);

			send.addActionListener((e) -> {
				if (!textBox.getText().equals("")) {
					String thing = textBox.getText();
					textBox.setText("");
					chatClient.sendMessage(thing + "\n\n");
					text.setText(chatClient.messages);
				}

			});

			chatClient.start();

		}

	}

	public static void main(String[] args) {
		new ChatApp();
	}

}
