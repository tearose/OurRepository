package com.tcpclient;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

import com.person.*;


public class ClientByte 
{
	private Socket socket = null; 
	private PrintStream out = null;
	private DataInputStream in = null;
	private String host = "";
	private int port;
	

	public ClientByte(String host, int port)
	{
		this.host = host;
		this.port = port;
	}

	public void connect()
	{
		
		try
		{
			socket = new Socket(host, port);
			out = new PrintStream(socket.getOutputStream());
			in = new DataInputStream(socket.getInputStream());;			
		} catch (UnknownHostException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e1) {
			// TODO: handle exception
		}
//		finally
//		{
//			disconnect();
//		}
	}

	public void create(Person person, String fileType)
	{
		connect();
		String strResult = queryModificationData(person, fileType, "Insert");
		System.out.println("The answer is: " + strResult );
		disconnect();
		
		
	}
	public void update(Person person, String fileType)
	{
		connect();
		String strResult = queryModificationData(person, fileType, "Update");
		System.out.println("The answer is: " + strResult );
		disconnect();
	}
	public void delete(Person person, String fileType)
	{
		connect();
		String strResult = queryModificationData(person, fileType, "Delete");
		System.out.println("The answer is: " + strResult );
		disconnect();
	}	


	private String queryModificationData(Person person, String fileType, String queryType)
	{
		byte[] bCommand; 
		boolean gotAnswer = false;
		String strCommand = "";
		 strCommand = fileType+"|"+queryType+"|"+ person.toXML();
		if (person.toXML().equals("")) strCommand = fileType+"|"+queryType; 
		
		try
		{
			bCommand = strCommand.getBytes("US-ASCII");
			out.write(bCommand, 0, bCommand.length);
			out.flush();			
			
		} catch (UnsupportedEncodingException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		System.out.println("Command:" + strCommand);
				
		
		String strResult = "";
		int lengthAvailable = 0;
		try
		{
			while(gotAnswer == false)
			{
				
				
			lengthAvailable = socket.getInputStream().available();			
			if (lengthAvailable > 0 )
			{
				byte[] bResult = new byte[lengthAvailable];	
				try
				{
//						strResult = in.readLine();
						
						int m = in.read(bResult, 0, lengthAvailable);						
						if (m == -1) break;
						
						strResult = new String(bResult,"US-ASCII");
						System.out.println(strResult);
						gotAnswer = true;
			
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else 
			strResult = "NO ANSWER";
			try
			{
				Thread.sleep(100);
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		} catch (IOException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return strResult;

	}

	public String read(String fileType)
	{
		connect();
		Person person  = new Person();
		String strResult = queryModificationData(person, fileType, "Select");
		System.out.println("The answer is: " + strResult );
		disconnect();
		return strResult;
	}	

	public void disconnect()
	{
			try
			{
				if (out != null) out.close();
				if (in != null) in.close();
				if (socket != null) socket.close();			
				
			} catch (IOException e)
			{
				e.printStackTrace();
			}
	}
	public static void main(String[] args)
	{
		ClientByte c = null;
			c = new ClientByte("localhost", 3180);
			//c.run();
			
			Person person = new Person(88, "ALEX", "SOS", 99);
			
			c.create(person, "csv");
			c.read("csv");			
			System.out.println("--");
//			c.read("person.csv");
//			c.read("person.csv");			

//			c.close();

	}



}
