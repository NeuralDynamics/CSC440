package app;

import java.io.*;

public class Logger {
	private static final String filename = "ChocLogs.txt";
	
	public static void writeLog(String text) {
		try {
			PrintWriter out = new PrintWriter(new java.io.FileWriter(filename, true));
			out.append(text + "\n");
			out.close();
		} catch (IOException e) {
		} 
	}
}
