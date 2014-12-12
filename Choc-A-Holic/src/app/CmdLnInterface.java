package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.Date;
import java.util.Queue;

public class CmdLnInterface implements IUserInterface {
	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	private IMethodInvoker methodInvoker = null;
	private Queue<String> messages = new ArrayDeque<String>();
	
	private String methodName = "";
	private Class<?> cls;
	private int maxCharCount = 0;

	@Override
	public void addMessageLine(String message) {
		messages.add(message);
	}

	@Override
	public void displayMsg(String methodName, Class<?> cls, int maxCharCount) {
		this.maxCharCount = maxCharCount;
		this.cls = cls;
		this.methodName = methodName;
		
		// Display the text
		while (messages.isEmpty() == false)
		{ System.out.println(messages.poll()); }
		
		// Read the User Input
		readUserInput();
	}
	
	@Override
	public void setMethodInvoker(IMethodInvoker methodInvoker) {
		this.methodInvoker = methodInvoker;
	}
	
	
	protected void readUserInput() {
		String dataInput = "";
		Object objData = null;
		boolean quit = false;
		
		while (dataInput.equals("q") == false)
		{
			try {
				dataInput = br.readLine();
		      } catch (IOException ioe) {
		         System.out.println("IO error trying to read data!");
		      }
			
			if (dataInput.toLowerCase().equals('q')) {
				quit = true;
				break;
			}
			
			if (dataInput.length() > maxCharCount) {
				System.out.println("Too many characters entered. A maximum " + maxCharCount + " characters is allowed");
			}
			
			objData = CastValue(dataInput);
			if (objData != null) { break; }
			
			// Display an error message to the user indicating that the selection was not valid
			System.out.println("Invalid Entry. Please select enter a valid entry or 'q' to quit");
		}
		
		methodInvoker.callMethod(this.methodName, this.cls, objData, boolean.class, quit);
	}
	
	protected Object CastValue(String dataInput) {
		// Cast String
		if (cls == String.class) { return dataInput; }
		
		// Cast Date
		if (cls == Date.class) {
			try { Date d = dateFormat.parse(dataInput); return d; } catch (Exception Ex) { }
		}
		
		// Cast Long
		if (cls == long.class) {
			try { long l = Long.parseLong(dataInput); return l; } catch (Exception Ex) {}
		}
		
		// Cast Int
		if (cls == int.class) {
			try { int l = Integer.parseInt(dataInput); return l; } catch (Exception Ex) {}
		}
		
		return false;
	}
}
