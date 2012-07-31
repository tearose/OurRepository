package com.person;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class DsCSV extends IDataStorage
{
	private String fileName = "person.csv";

	@Override
	public void create(Person person) 
	{
		BufferedReader in=null;
		BufferedWriter out = null;
		FileWriter fw=null;
		String line, tmpString = "";		
		boolean exists = (new File(fileName)).exists();
		if (exists) 
		{
			try
			{
				in = new BufferedReader(new FileReader(getFileName()));
			} catch (FileNotFoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			try
			{
				while((line = in.readLine()) != null)
				{
					if (!line.equals(""))
					tmpString += line+"\n";
				}
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}					
		} 				
		try 
		{
			
			out = new BufferedWriter(new FileWriter(getFileName()));
			out.write(tmpString);
			out.write(person.toCSV());			
			out.newLine();
			
			
//			fw = new FileWriter(getFileName());
//			fw.write(tmpString);
//			fw.write(person.toCSV()+"\n");
					
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				if (fw != null) fw.close();
				if (in != null) in.close();
				if (out != null)
				{
					out.flush();
					out.close();
				}
			} catch (IOException e) 
			{
				e.printStackTrace();
			}	
		}				
	}

	@Override
	public PersonList read() 
{
		PersonList list = new PersonList();
		BufferedReader in = null;
		try
		{
			in = new BufferedReader(new FileReader(getFileName()));
			String line;
				while((line = in.readLine()) != null)
				{
					if (!line.equals(""))
					{
						list.add(new Person());						
						list.get(list.size()-1).fromCSV(line);
					}
				}
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (in != null) in.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
			
		}
		return list;
	}

	@Override
	public void update(Person person) 
	{
		BufferedReader in=null;
		FileWriter fw=null;		
		try 
		{
			in = new BufferedReader(new FileReader(getFileName()));
			String line, tmpString = "";
			while((line = in.readLine()) != null)
			{				
				if (isEqualPerson(line, person))
				{
					line  = person.toCSV();
				}

				if (!line.equals(""))
				tmpString += line+"\n";

			}
			
			fw = new FileWriter(getFileName());
			fw.write(tmpString);
			
					
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				if (fw != null) fw.close();
				if (in != null) in.close();
			} catch (IOException e) 
			{
				e.printStackTrace();
			}	
		}				
		
	}

	@Override
	public void delete(Person person) {
		BufferedReader in=null;
		FileWriter fw=null;		
		try 
		{
			in = new BufferedReader(new FileReader(getFileName()));
			String line, tmpString = "";
			while((line = in.readLine()) != null)
			{				
				if (isEqualPerson(line, person))
				{
					line  = "";
				}
				if (!line.equals(""))
				tmpString += line+"\n";
				
			}
			
			fw = new FileWriter(getFileName());
			fw.write(tmpString);
			
					
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				if (fw != null) fw.close();
				if (in != null) in.close();
			} catch (IOException e) 
			{
				e.printStackTrace();
			}	
		}				
		
		
	}

	@Override
	public IDataStorage isReady(String type) 
	{
		IDataStorage ret = null;
		if(type.toUpperCase().contains("CSV"))
		{
			ret =  this;
		}
		else if(next != null)
		{
			ret = next.isReady(type);
		}
		return ret;
		
	}

	public String getFileName() 
	{
		return fileName;
	}

	public void setFileName(String fileName) 
	{
		this.fileName = fileName;
	}
	
	private boolean isEqualPerson(String line, Person p)
	{
		boolean res = false;
		String [] field = line.split(";");		
		if (Integer.parseInt(field[0]) == p.getId())
		res = true;		
		return res;
	}
	
//	public static void main(String[] args)
//	{
//		DsCSV ds = new DsCSV();
//		ds.read();
//	}

}


