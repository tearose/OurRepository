package com.tcpseserverold;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;



public class AppServer
{
	private ServerSocket server;

	private AppServer(int port)
	{
		try
		{
		 server = new ServerSocket(port);
		 System.out.println("Server listening on port: "+port);
			while (true)
			{

				Socket sock = server.accept();
				ServerThread serverThread = new ServerThread(sock);  
				serverThread.start();
				System.out.println("client connected");
			}

		} catch (IOException e)
		{
			System.out.println("Can not start server on port: "+port+" error: "+e.getMessage());
			System.err.println(e);
		}
	}


	public static void main(String[] args) throws IOException
	{
		int port = 3180;
		if (args.length == 1)
		{
			port = Integer.parseInt(args[0]);
		}		
		new AppServer(port);
	}

}
