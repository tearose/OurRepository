package com.person;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PersonListTest extends TestCase
{
	PersonList pl = null;
	public static final String strXML = 
			"<Person><id>0</id><firstName>Anna0</firstName><lastName>Vostrykova0</lastName><age>30</age></Person>" +
			"<Person><id>1</id><firstName>Anna1</firstName><lastName>Vostrykova1</lastName><age>31</age></Person>" +
			"<Person><id>2</id><firstName>Anna2</firstName><lastName>Vostrykova2</lastName><age>32</age></Person>";
			 
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
	}

	@Before
	public void setUp() throws Exception
	{
		pl = new PersonList();
	}

	@After
	public void tearDown() throws Exception
	{
		pl = null;
	}

	@Test
	public void testToString()
	{
		Person p0  = new Person(0,"Anna0","Vostrykova0",30);
		Person p1  = new Person(1,"Anna1","Vostrykova1",31);
		Person p2  = new Person(2,"Anna2","Vostrykova2",32);
		pl.add(p0);
		pl.add(p1);
		pl.add(p2);

		
		assertTrue(strXML.equals(pl.toString()));
	}

	@Test
	public void testFromXML()
	{
		pl.fromXML(strXML);
		String str = "";
		for(Person p:pl)
		{
			str += p.toString();
		}
		assertTrue(str.equals(strXML));
	}

}
