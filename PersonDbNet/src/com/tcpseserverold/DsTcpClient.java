package com.tcpseserverold;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.person.IDataStorage;
import com.person.MySQLDB;
import com.person.Person;
import com.person.PersonList;

public class DsTcpClient extends IDataStorage
{
	private int port = 3180;
	private String address = "localhost";
	private String fileName = "";
	public DsTcpClient(String address, int port, String fileName )
	{
		super();
		this.port = port;
		this.address = address;
		this.fileName = fileName;
	}

	public int getPort()
	{
		return port;
	}

	public String getFileName()
	{
		return fileName;
	}

	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	public void setPort(int port)
	{
		this.port = port;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	@Override
	public void create(Person person)
	{
		  String dataSend = getFileName() + "|" + "Insert|" + person.toXML();
		  TcpClient client = new TcpClient();
		try
		{
			client.Send(getAddress(), getPort(), dataSend);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	public PersonList read()
	{
		
		int i = 0;
		int length = 0;
		String acceptedMessage = "";
		PersonList pList = new PersonList();
		
		String dataSend = getFileName() + "|" + "Select";
		TcpClient client = new TcpClient();
		try
		{
			client.Send(getAddress(), getPort(), dataSend);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		acceptedMessage = client.getAcceptedMessage();
		
		while(length != acceptedMessage.length())
		{
			String line = "", tmpLine = "";
			while(tmpLine != "\n")
			{
				line += acceptedMessage.charAt(length);
				tmpLine = acceptedMessage.substring(length, length + 1);
			}
			pList.add(new Person());
			pList.get(i).fromXML(line);
			i++;
		}		
		return pList;
	}

	@Override
	public void update(Person person)
	{
		  String dataSend = getFileName() + "|" + "Update|" + person.toXML();
		  TcpClient client = new TcpClient();
		  try
		{
			client.Send(getAddress(), getPort(), dataSend);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}					
	}

	@Override
	public void delete(Person person)
	{
		  String dataSend = getFileName() + "|" + "Delete|" + person.toXML();
		  TcpClient client = new TcpClient();
		  try
		{
			client.Send(getAddress(), getPort(), dataSend);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}

	@Override
	public IDataStorage isReady(String type)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
