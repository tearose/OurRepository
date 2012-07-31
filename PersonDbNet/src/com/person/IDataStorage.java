package com.person;

public abstract class IDataStorage
{
	public abstract void create(Person person);
	public abstract PersonList read();
	public abstract void update(Person person);
	public abstract void delete(Person person);
	
	public abstract IDataStorage isReady(String type); 	
	public IDataStorage next;
}
