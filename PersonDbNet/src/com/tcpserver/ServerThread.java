package com.tcpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import com.person.DataStorageFactory;
import com.person.IDataStorage;
import com.person.Person;
import com.person.PersonList;

public class ServerThread extends Thread
{
	private PrintStream os;
	private BufferedReader is;
	private InetAddress addr;
	private IDataStorage dataStorage;
	public enum Actions{INSERT,SELECT,UPDATE,DELETE};
	
	

	public ServerThread(Socket s) throws IOException
	{
		os = new PrintStream(s.getOutputStream());
		is = new BufferedReader( new InputStreamReader(s.getInputStream(),"UTF-8"));
		addr = s.getInetAddress();	
	}

	public void run()
	{
		String str;
		String fileName = "";
		String action = "";
		Person person = new Person();

		try
		{
			while ((str = is.readLine()) != null)
			{
				System.out.println("GOT QUERY: "+str);
				String [] field = str.split("\\|");
		
				fileName = field[0];
				action = field[1];
				
				if (!action.equalsIgnoreCase("Select"))
				{
					person.fromXML(field[2]);
				}
                dataStorage = DataStorageFactory.getInstance(fileName);
                String strResult = "OK";	
              
                   	Actions currAction = Actions.valueOf(action.toUpperCase());
              
                                               
				switch (currAction)
				{
					case INSERT:
                        dataStorage.create(person);
                        os.println("OK");
//                        os.flush();
						break;
					case UPDATE:
						dataStorage.update(person);
						os.println("OK");
//						os.flush();
						break;
					case DELETE:
						dataStorage.delete(person);
						os.println("OK");
//						os.flush();
						break;
					case SELECT:
						PersonList pList = dataStorage.read();
                        strResult = pList.toString();
                        if (pList.isEmpty())
                            strResult = "Empty";
                        else os.println(strResult);
//                        os.flush();
						break;
					default:
						
						break;
				}
				System.out.println(strResult);
				os.println(strResult);;
			}

		} 
		catch (IOException  e)
		{
			System.err.println(e.getMessage());		
		} 
	
		finally
		{
			disconnect();		
		}
	}

	public void disconnect()
	{
		try
		{
			System.out.println(addr.getHostName() + " disconnected");
			os.close();
			is.close();

		} catch (IOException e)
		{
			e.printStackTrace();
		} 

	}

}
