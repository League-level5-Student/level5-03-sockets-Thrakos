package _02_Chat_Application;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import _00_Click_Chat.networking.Server;

public class ChatApp2 implements KeyListener {

	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	// JLabel label = new JLabel();

	JTextArea textArea = new JTextArea(30, 100);

	JTextField textField = new JTextField(25);
	JButton button = new JButton();

	JScrollPane scroll = new JScrollPane(textArea);

	String messages = "";

	ChatServer2 server;
	ChatClient2 client;
	
	boolean serversend = true;

	public static void main(String[] args) {
		new ChatApp2();
	}

	public ChatApp2() {

		textArea.setEditable(false);
		frame.add(panel);
		panel.add(/* label */ scroll);
		panel.add(textField);
		button.setText("SEND");
		panel.add(button);
		textField.addKeyListener(this);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		int response = JOptionPane.showConfirmDialog(null, "Would you like to host a connection?", "Buttons!",
				JOptionPane.YES_NO_OPTION);

		if (response == JOptionPane.YES_OPTION) {

			String name = JOptionPane.showInputDialog("Who are you?");
			server = new ChatServer2(8080, textArea, name);
			frame.setTitle(name);
			JOptionPane.showMessageDialog(null, "ip: " + server.getIPAddress() + ", port: " + server.getPort());
			serversend = true;
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
			serversend = false;
			client = new ChatClient2(ipStr, port, textArea, name);
			button.addActionListener((e) -> {
				String thing = textField.getText();
				textField.setText("");
				client.sendText(thing);
			});
			client.start();

		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyChar() == (KeyEvent.VK_ENTER)) {
			String thing = textField.getText();
			textField.setText("");
			if (serversend) {
				server.sendText(thing);
			} else {
				client.sendText(thing);
			}
		}
	}
}
