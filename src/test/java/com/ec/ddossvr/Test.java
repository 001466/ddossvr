package com.ec.ddossvr;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

public class Test {

	public static void main(String[] args) throws UnknownHostException, IOException {

        Socket sock;
        DataInputStream in;
        PrintStream out;
		int botIdentifier;

		String hostname = "Unknown";
		Random rand = new Random(); 
		botIdentifier = rand.nextInt(500); 

		try
		{
			InetAddress addr;
			addr = InetAddress.getLocalHost();
			hostname = addr.getHostName();
		}
		catch (UnknownHostException ex)
		{
			System.out.println("Hostname can not be resolved");
		}
        
   
        sock = new Socket ("127.0.0.1", 55);
		
		while (true) {

		    //Get IO streams from the socket
		    in = new DataInputStream (sock.getInputStream());
		    out = new PrintStream (sock.getOutputStream());
		    
			System.out.println("Ready to execute");
			out.println("Bot " + hostname + botIdentifier + ": Ready to execute\r\n");
			out.flush();
		    
			String fromServer = in.readLine();		    
		    System.out.println("Incoming command from server:" + fromServer);
			
			
			break;
		}
        sock.close();
	
	}

}
