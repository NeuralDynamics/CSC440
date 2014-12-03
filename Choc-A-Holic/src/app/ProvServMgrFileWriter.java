package app;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class ProvServMgrFileWriter<T> implements IWriter<T> 
{
	String fileName = "ProvServMgrFile.txt";
	@Override
	public void writeData(List<T> list) 
	{
		PrintWriter outputFile;
		try 
		{
			outputFile = new PrintWriter(fileName);
			
			while(!list.isEmpty())
			{
				outputFile.println(list.remove(0));
			}
			outputFile.close();
		} 
		catch (FileNotFoundException e) 
		{
			
			e.printStackTrace();
		}
	}
}
