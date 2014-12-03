package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProvMgrFileReader<T> implements IReader<String> 
{
	String fileName = "ProvMgrFile.txt";
	@Override
	public List<String> readData() 
	{
		List<String> list = new ArrayList<String>();
		File myFile = new File(fileName);
		try 
		{
			Scanner inputFile = new Scanner(myFile);
			while(inputFile.hasNext())
			{
				list.add(inputFile.next());
			}
			inputFile.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		return list;
	}
}
