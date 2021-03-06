package _01_Intro_To_Sockets.client;

import java.net.*;

import javax.swing.JOptionPane;

import java.io.*;

public class ClientGreeter {

   public static void main(String [] args) {
	  //1. Create a String for the ip address of the server. 
	  // If you don't know how to find a computer's ip address, ask about ifconfig on linux/mac and ipconfig on windows.
	   String ipAddress = "";
		try {
			ipAddress = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	   
      //2. Create an integer for the server's port number
      int portNumber = 9876;
		
      //3. Surround steps 4-9 in a try-catch block that catches any IOExceptions.
    
      try {
    	  	//4. Create an object of the Socket class. When initializing the object, pass in the ip address and the port number
    	  	Socket socket = new Socket(ipAddress, portNumber);
    	  	
         //5. Create a DataOutputStream object. When initializing it, use the Socket object you created in step 4 to call the getOutputStream() method.
    	  	DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
    	  	
         //6. Use the DataOutputStream object to send a message to the server using the writeUTF(String message) method
    	  	String string = JOptionPane.showInputDialog("type: ");
    	  	dos.writeUTF(string); 
    	  	
         //7. Create a DataInputStream object. When initializing it, use the Server object you created in step 4 to call the getInputStream() method.
        DataInputStream dis = new DataInputStream(socket.getInputStream()); 
        
         //8. Use the DataInputStream object to print a message from the server using the readUTF() method.
        JOptionPane.showMessageDialog(null, dis.readUTF());
        
         //9. Close the client's server object
        socket.close();
      } catch (IOException e) {
    	  	
      }

   }
}
