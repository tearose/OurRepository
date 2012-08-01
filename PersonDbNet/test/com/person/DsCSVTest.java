package com.person;

import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DsCSVTest extends TestCase
{
	DsCSV ds;

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
		ds = new DsCSV();
	}

	@After
	public void tearDown() throws Exception
	{
		ds = null;
	}

	@Test
	public void testCreate()
	{
		
		Person p = new Person(1, "Alex", "Vostrykov", 31);
		ds.create(p);		
		Person p2 = new Person(2, "Anna", "Vostrykova", 31);		
		ds.create(p2);
		Person p3 = new Person(1, "Alex", "Vostrykov", 31);
		ds.create(p3);		
		Person p4 = new Person(2, "Anna", "Vostrykova", 31);		
		ds.create(p4);		
	}

	@Test
	public void testRead()
	{
				
		System.out.println(ds.read().toString());
//		pl.toString();
	}

	@Test
	public void testUpdate()
	{
		Person p = new Person(1, "Alex", "Ivanov", 31);
		ds.update(p);		
	
	}

	@Test
	public void testDelete()
	{
		Person p = new Person(1, "Alex", "Ivanov", 31);
		ds.delete(p);		

	}

	@Test
	public void testIsReady()
	{
		IDataStorage csvds = null;
		csvds = ds.isReady("test.csv");
		assertNotNull(csvds);
		
	}

}
