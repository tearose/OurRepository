package com.person;

import java.util.ArrayList;

public class PersonList extends ArrayList<Person>
{
	private static final long serialVersionUID = 1L;

//	IDataStorage ds = null;
//
//	public void Init(String dataType)
//	{
//		ds = DataStorageFactory.getInstance(dataType);
//	}
//
//	public void create(PersonList pList) 
//	{
////		Init(type);
//		for (Person p : pList)
//		{
//			ds.create(p);
//		}		
//		
//	}
//	public PersonList read()
//	{
////		Init(type);
//		return ds.read();
//	}
//	public void update(PersonList pList)
//	{
////		Init(type);
//		for (Person p : pList)
//		{
//			ds.update(p);
//		}
//	}
//	public void delete(PersonList pList)
//	{
////		Init(type);
//		for (Person p : pList)
//		{
//			ds.delete(p);
//		}
//	}
	
	public String toString() {
		String ret = "";
		for(Person p:this)
		{
			ret += p.toString();
		}
		return ret;
	}

/*	public static void main(String[] args)
	{
		PersonaList l = new PersonaList();
		l.add(new Persona());
		l.get(0).setAge(12);
		try
		{
			l.create(l, "jdbc:mysql://localhost:3306/test", "JDBC");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}*/
}