package app;

import java.io.*;

public class FileReader<T> implements IReader<T>
{
	String fileName;
	
	public FileReader(String fileName)
	{
		this.fileName = fileName;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T readData() 
	{
		T obj = null;
	    try
	    {
	       FileInputStream fileIn = new FileInputStream(fileName);
	       ObjectInputStream in = new ObjectInputStream(fileIn);
	       obj = (T) in.readObject();
	       in.close();
	       fileIn.close();
	    }
	    catch(FileNotFoundException i)
	    {
	    	Logger.writeLog(i.toString());
	    	for (int x = 0; x < i.getStackTrace().length; x++) {
	    		Logger.writeLog("	 at " + i.getStackTrace()[x].toString());
	    	}
	       return null;
	    }
	    catch(IOException i)
	    {
	    	Logger.writeLog(i.toString());
	    	for (int x = 0; x < i.getStackTrace().length; x++) {
	    		Logger.writeLog("	 at " + i.getStackTrace()[x].toString());
	    	}
	       return null;
	    }
	    catch(ClassNotFoundException c)
	    {
	       System.out.println("class not found");
	       Logger.writeLog(c.toString());
	    	for (int x = 0; x < c.getStackTrace().length; x++) {
	    		Logger.writeLog("	 at " + c.getStackTrace()[x].toString());
	    	}
	       return null;
	    }
	    
	    return obj;
	}
}
