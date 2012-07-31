package com.tcpserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import com.person.DataStorageFactory;
import com.person.IDataStorage;
import com.person.Person;
import com.person.PersonList;

public class ServerThreadByte extends Thread
{
//	private PrintStream os;
//	private BufferedReader is;
	private InetAddress addr;
	private Socket connect;
	private IDataStorage dataStorage;
	public enum Actions{INSERT,SELECT,UPDATE,DELETE};
	
	private DataOutputStream out;
	private DataInputStream in;	
	
	public ServerThreadByte(Socket connect) throws IOException
	{
		this.connect = connect;		
		out = new DataOutputStream(connect.getOutputStream());
		in = new DataInputStream(connect.getInputStream());
		addr = connect.getInetAddress();	
		
	}
	public void run()
	{
		String str = "";
		String fileName = "";
		String action = "";
		Person person = new Person();
		byte[] bRes; 		

		try
		{
		

					
			
			while (true)				
			{
				
				int len = connect.getInputStream().available();

				if (len > 0)
				{
					System.out.println("LENGTH =====" + len);					
					byte[] bytesFrom = new byte[len];
					int m = in.read(bytesFrom, 0, len);
					if (m == -1) break;
					str = new String(bytesFrom,"US-ASCII");
					System.out.println("GOT QUERY: "+ str);
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
							break;
						case UPDATE:
							dataStorage.update(person);
							break;
						case DELETE:
							dataStorage.delete(person);
							break;
						case SELECT:
							PersonList pList = dataStorage.read();
	                        strResult = pList.toString();
	                        if (pList.isEmpty())
	                            strResult = "Empty";
	                        break;
						default:
							break;
					}
					//bRes = new byte[strResult.getBytes("US-ASCII").length];
					bRes = strResult.getBytes("US-ASCII");
					System.out.println("RESULT:" + strResult);
					out.write(bRes, 0, bRes.length);
					out.flush();
				}
				this.sleep(100);
			}

		} 
		catch (IOException  e)
		{
			System.err.println(e.getMessage());		
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	
		finally
		{
			try
			{
				System.out.println(addr.getHostName() + " disconnected");
				if (out != null) out.close();
				if (in != null) in.close();
				if (connect != null) connect.close();			

			} catch (IOException e)
			{
				e.printStackTrace();
			} 
		
		}
	}


}
