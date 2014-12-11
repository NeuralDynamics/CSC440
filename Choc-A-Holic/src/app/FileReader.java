package app;

import java.io.*;

public class FileReader<T> implements IReader<T>
{
	String fileName;
	
	public FileReader(String fileName)
	{
		this.fileName = fileName;
	}
	
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
	       i.printStackTrace();
	       return null;
	    }
	    catch(IOException i)
	    {
	       i.printStackTrace();
	       return null;
	    }
	    catch(ClassNotFoundException c)
	    {
	       System.out.println("class not found");
	       c.printStackTrace();
	       return null;
	    }
	    
	    return obj;
	}
}
