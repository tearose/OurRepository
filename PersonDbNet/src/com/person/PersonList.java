package com.person;

import java.util.ArrayList;

public class PersonList extends ArrayList<Person>
{
	private static final long serialVersionUID = 1L;


	public String toString() {
		String ret = "";
		for(Person p:this)
		{
			ret += p.toString();
		}
		return ret;
	}
	public PersonList fromXML(String xmlString)
	{ 	
		xmlString=xmlString.replace("</Person>", "</Person><end>");
		String[] strPersons = xmlString.split("<end>");
		for (int i = 0; i< strPersons.length;i++)
		{
			Person tmpPerson = new Person();
			tmpPerson.fromXML(strPersons[i]);
			this.add(tmpPerson);					
		}    
		return this;
	}

}