package com.tcpseserverold;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class TcpClient
{
	private Socket s;
	private BufferedReader socketReader;
	private BufferedWriter socketWriter;
	private String sendMessage = "";
	private String acceptedMessage = "";


	public String getAcceptedMessage() {
		return acceptedMessage;
	}
	
	public void Send (String host, int port, String message) throws IOException
	{
		s = new Socket(host, port);
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


}
