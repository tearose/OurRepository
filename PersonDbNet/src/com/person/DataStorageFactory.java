package com.person;


public class DataStorageFactory {
	private static IDataStorage p = null;	
	static
	{
	    add(new DsCSV());
	    add(new DsMySQL());	    
	}
	
	public static void add(IDataStorage ds)
	{
		ds.next = p;
		p = ds;
	}
	
	public static IDataStorage getInstance(String dataType)
	{
		return p.isReady(dataType);
	}
		

}
