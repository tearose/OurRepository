package com.tcpclient;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;

import com.person.*;


public class Client 
{
	private Socket socket; 
	private BufferedWriter bw;
	private BufferedReader userInput;
	private PrintStream ps;

	public Client(String host, int port) throws IOException
	{
		socket = new Socket(host, port);
//		bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"));
		bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"));

		userInput = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
		ps = new PrintStream(socket.getOutputStream());
	}

	public void create(Person person, String fileType)
	{	
		String command = fileType+"|Insert|"+ person.toXML();
		ps.println(command);
		
		String line = "";
		try
		{
			line = userInput.readLine();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!line.equals(null))
		{
			System.out.println(line);
		}
		else
		{
			System.out.println("no respone");
		}
		
				
	}


	public PersonList read(String fileType)
	{
	
		String command = fileType+"|Select|";
		ps.println(command);		
		String line = "";
		try
		{
			line = userInput.readLine();
			
			//System.out.println(line);			
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!line.equals(null))
		{
			System.out.println(line);
		}
		else
		{
			System.out.println("no respone");
		}

		return null;
	}


	public void update(Person person, String fileType)
	{
		String command = fileType+"|Update|"+ person.toXML();
		ps.println(command);
		String line = "";
		try
		{
			line = userInput.readLine();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!line.equals(null))
		{
			System.out.println(line);
		}
		else
		{
			System.out.println("no respone");
		}
		
		
	}
	public void delete(Person person, String fileType)
	{
		String command = fileType+"|Delete|"+ person.toXML();
		ps.println(command);
		String line = "";
		try
		{
			line = userInput.readLine();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!line.equals(null))
		{
			System.out.println(line);
		}
		else
		{
			System.out.println("no respone");
		}
		
	}

	public void close()
	{
		if (socket != null && !socket.isClosed())
		{
			try
			{
				socket.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws InterruptedException, SQLException
	{
		Client c = null;
		try
		{
			c = new Client("localhost", 3180);
			//c.run();
			
			Person person = new Person(1223, "Mark", "Jons", 23331);
			
			c.create(person, "blabla.csv");
//			Thread.sleep(2000);			
//			c.delete(person, "blabla.csv");
			System.out.println("--");			
			c.read("person.csv");
//			Thread.sleep(2000);		
			System.out.println("-");
//			c.delete(person, "blabla.csv");			
			c.read("person.csv");
			
			//c.Delete(111);
			//c.update(person, null)
			//c.Load();
//			c.Read(113);

			//ps.println(comand);			
			c.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			if (c != null)
			{
				c.close();
			}
		}

	}



}
