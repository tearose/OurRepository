package com.tcpclient;

import com.person.Person;

public class AppClient
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		ClientCRUD c = null;
			c = new ClientCRUD("localhost", 3180);
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
