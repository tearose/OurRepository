package com.person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Person {
	private int id;
	private String firstName;
	private String lastName;
	private int age;
	
	
	public Person()
	{	
	}
	public Person(int id, String firstName, String lastName, int age)
	{
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String toCSV()
	{
		String ret = "";
		ret += getId() + ";" + getFirstName() + ";" + getLastName() + ";" + getAge() ;		
		return ret;
	}

	public void fromCSV(String line)
	{
		String [] field = line.split(";");
		
		setId(Integer.parseInt(field[0]));
		setFirstName(field[1]);
		setLastName(field[2]);
		setAge(Integer.parseInt(field[3]));
	}
	
	public String toMySQL()
	{
		String query = "insert into xlist.PERSON values("+
				getId()+
				",\""+getFirstName()+
				"\",\""+getLastName()+
				"\",\""+getAge()+
				"\")";
		return query;
	}

	public void fromMySQL(ResultSet result) throws SQLException
	{
		setId(Integer.parseInt(result.getString("ID")));
		setFirstName(result.getString("FNAME"));
		setLastName(result.getString("LNAME"));
		setAge(Integer.parseInt(result.getString("AGE")));
	}
	public String toXML()
	{	
		String strResult = "";
		if (getFirstName() != null && getLastName() != null)
		{
	    strResult = "<Person><id>" + getId() + "</id>";
        strResult += "<firstName>" + getFirstName() + "</firstName>";
        strResult += "<lastName>" + getLastName() + "</lastName>";
        strResult += "<age>" + getAge() + "</age></Person>";
		}
		
        return strResult;
	}
	public void fromXML(String xmlString)
	{
		xmlString=xmlString.
				 replace("<Person>", "").
				 replace("<id>", "").
				 replace("</id>", ";").
				 replace("<firstName>", "").
				 replace("</firstName>", ";").
				 replace("<lastName>", "").
				 replace("</lastName>", ";").
				 replace("<age>", "").
				 replace("</age>", ";").
				 replace("</Person>", "");
		String[] fields = xmlString.split(";");
    	int id = Integer.parseInt(fields[0]);
        setId(id);
		setFirstName(fields[1]);
		setLastName(fields[2]);
    	int age = Integer.parseInt(fields[3]);
        setAge(age);		
	}
	public String toString()
	{
		return toXML();
	}
	
	

}
