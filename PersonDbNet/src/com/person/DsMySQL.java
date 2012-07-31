package com.person;

import java.sql.ResultSet;
import java.sql.SQLException;




public class DsMySQL extends IDataStorage
{
	

	@Override
	public void create(Person person)
	{
		MySQLDB.insert(person.toMySQL());
	}

	@Override
	public PersonList read()
	{
		int i = 0;
		PersonList pList = new PersonList();
//	--	DataBase.setDB_CONNECTION(path);
		ResultSet rs = null;
		try
		{
			rs = MySQLDB.select("select ID, FNAME, LNAME, AGE from PERSON");
			while (rs.next())
			{
				
				pList.add(new Person());
				pList.get(i).fromMySQL(rs);
				i++;
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return pList;
	}

	@Override
	public void update(Person person)
	{
		MySQLDB.update("update PERSON set	FNAME = \""+person.getFirstName()+
				"\",	LNAME = \""+person.getLastName()+"\",	AGE  = "+person.getAge()+
				" where ID  = "+person.getId()+";");
		
	}

	@Override
	public void delete(Person person)
	{
		try
		{
			MySQLDB.delete("delete from PERSON where ID = "+person.getId()+";");
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public IDataStorage isReady(String type)
	{
		IDataStorage ret = null;
		if (type.toUpperCase().contains("mysql".toUpperCase()))
		{
			return this;
		}
		else if (next != null)
		{
			ret = next.isReady(type);
		}
		return ret;
	}

}
