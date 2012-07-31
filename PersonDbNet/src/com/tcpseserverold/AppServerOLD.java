package com.tcpseserverold;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.io.*;


public class AppServerOLD extends Thread 
{
	private ServerSocket serverSocket;

	public AppServerOLD(int port) throws IOException
	{
	   serverSocket = new ServerSocket(port);
	   //serverSocket.setSoTimeout(1000);
	}

	public void run()
	{
	   while(true)
	   {
	      try
	      {
	         System.out.println("Waiting for client on port " +
	         serverSocket.getLocalPort() + "...");
	         
	         Socket server = serverSocket.accept();
	         
	         System.out.println("Just connected to " + server.getRemoteSocketAddress());
	         
	         DataInputStream in =
	               new DataInputStream(server.getInputStream());
	         System.out.println(in.readUTF());
	         DataOutputStream out =
	              new DataOutputStream(server.getOutputStream());
	         out.writeUTF("Thank you for connecting to "
	           + server.getLocalSocketAddress() + "\nGoodbye!");
	         
	         server.close();
	      }catch(SocketTimeoutException s)
	      {
	         System.out.println("Socket timed out!");
	         break;
	      }catch(IOException e)
	      {
	         e.printStackTrace();
	         break;
	      }
	   }
	}
	
	
	public static void main(String[] args)	
	{
		int port = 3180;
		if (args.length == 1)
		{
			port = Integer.parseInt(args[0]);
		}
//		System.out.println("Trying start server on port: "+port+" ");
		   try
		   {
		      Thread t = new AppServerOLD(port);
		      t.start();
		   }catch(IOException e)
		   {
		      e.printStackTrace();
		   }
		
	}    
    
}