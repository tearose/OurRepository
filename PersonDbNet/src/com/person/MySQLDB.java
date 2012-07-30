package com.person;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLDB
{
	//create database xlist;
	//use xlist;
	//CREATE USER xlixtusr IDENTIFIED BY 'xlistpwd'; 
	//grant usage on *.* to xlistusr@localhost identified by 'xlistpwd'; 
	//grant all privileges on tablename.* to xlistusr@localhost;

//	String fullurl = "jdbc:mysql://localhost/xlist?user=xlistusr&password=xlistpwd";
//	try {
//			connect = DriverManager.getConnection(fullurl);
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static String DB_CONNECTION = "jdbc:mysql://localhost:3306/xlist";
	private static final String DB_USER = "xlistusr";
	private static final String DB_PASSWORD = "xlistpwd";
	
	private static Connection getDBConnection()
	{

		Connection dbConnection = null;
		try
		{
			Class.forName(DB_DRIVER);
			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
		}
		catch (ClassNotFoundException e)
		{
			System.err.println(e.getMessage());
		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage());
		}
		return dbConnection;

	}
	private static ResultSet executeQuery(String sqlQuery, boolean isDebug)
	
	{
		Connection dbConnection = null;
		Statement statement = null;
		ResultSet rs = null;
		
		try
		{
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			if (isDebug) System.out.println("DEBUG: " + sqlQuery);
			rs = statement.executeQuery(sqlQuery);
			if (isDebug)System.out.println("DEBUG: Query executed successfuly!");

		}
		catch (SQLException e)
		{
			System.err.println("ERROR: "+ sqlQuery + e.getMessage());		
		}
		finally
		{
			try
			{
				if (statement != null) statement.close();
				if (dbConnection != null) dbConnection.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return rs;
	}	

	public static void CreateTable(String sqlQuery)
	{
		executeQuery(sqlQuery, true);
	}

	public static void insert(String sqlQuery)	
	{
		executeQuery(sqlQuery, true);
	}

	public static void update(String sqlQuery)
	{
		executeQuery(sqlQuery, true);
	}

	public static ResultSet select(String sqlQuery)
	{
		ResultSet rs = null;
		rs = executeQuery(sqlQuery, true);
		return rs;
	}

	public static void delete(String sqlQuery)
			throws SQLException
	{
		executeQuery(sqlQuery, true);		
	}	

}
