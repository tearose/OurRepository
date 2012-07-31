package com.tcpseserverold;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client2
{
	private Socket s;
	private BufferedReader socketReader;
	private BufferedWriter socketWriter;
	private String sendMessage = "";
	private String acceptedMessage = "";


	public String getAcceptedMessage() {
		return acceptedMessage;
	}
	
	public void Send (String address, int port, String message) throws IOException
	{
		s = new Socket(address, port);
		
		socketReader = new BufferedReader(new InputStreamReader(
				s.getInputStream(), "UTF-8"));
		socketWriter = new BufferedWriter(new OutputStreamWriter(
				s.getOutputStream(), "UTF-8"));

		while (true)
		{
			try
			{
				socketWriter.write(sendMessage);
				socketWriter.write("\n");
				socketWriter.flush();
			}
			catch (IOException ignored)
			{
			}
			new Thread(new Receiver()).start();
		}
	}

	private class Receiver implements Runnable
	{

		public void run()
		{
			while (!s.isClosed())
			{
				String line = null;

				try
				{
					line = socketReader.readLine();
					acceptedMessage = line;
				}
				catch (IOException e)
				{
				}
			}
		}
	}

	public static void main(String[] args)
	{
		 		
		try
		{
			Client2 client = new Client2(); 
			client.Send("localhost", 3180, "csv|insert|<Person><id>1</id><firstName>2</firstName>3<lastName>4</lastName><age>5</age></Person>");
		}
		catch (IOException e)
		{
			System.out.println("Unable to connect. Server not running?"+e.getMessage());
		}
	}

}