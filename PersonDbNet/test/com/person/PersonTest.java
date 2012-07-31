package com.person;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PersonTest
{
	private Person p;
	private static final String personXML = "<Person><id>1</id><firstName>Alex</firstName><lastName>Vostrykov</lastName><age>31</age></Person>";
	private static final String personCSV = "1;Alex;Vostrykov;31";
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
		p = new Person(1, "Alex","Vostrykov" , 31);
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void testToXML()
	{
		assertEquals(true, p.toXML().equals(personXML));	
	}
	@Test
	public void testToCSV()
	{
		assertEquals(true, p.toCSV().equals(personCSV));
	}	
	@Test
	public void testFromXML()
	{
		Person person = new Person();
		person.fromXML(personXML);
		assertEquals(true, p.toXML().equals(personXML));	
	}
	@Test
	public void testFromCSV()
	{
		Person person = new Person();
		person.fromXML(personCSV);
		assertEquals(true, p.toCSV().equals(personCSV));	
	}
	

}
