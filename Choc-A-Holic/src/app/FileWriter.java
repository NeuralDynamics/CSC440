package app;

import java.io.*;


public class FileWriter<T> implements IWriter<T>
{
	String fileName;
	public FileWriter(String fileName)
	{
		this.fileName = fileName;
	}
	
	@Override
	public void writeData(T obj) 
	{
		try
	    {
	       FileOutputStream fileOut = new FileOutputStream(fileName);
	       ObjectOutputStream out = new ObjectOutputStream(fileOut);
	       out.writeObject(obj);
	       out.close();
	       fileOut.close();
	    }
	    catch(IOException i)
	    {
	    	Logger.writeLog(i.toString());
	    	for (int x = 0; x < i.getStackTrace().length; x++) {
	    		Logger.writeLog("	 at " + i.getStackTrace()[x].toString());
	    	}
	    }
	}
}

