package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Date;
import java.util.Queue;

public class CommandLine implements IUserInterface {
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
		while (dataInput.equals("q") == false)
		{
			try {
				dataInput = br.readLine();
		      } catch (IOException ioe) {
		         System.out.println("IO error trying to read data!");
		      }
			
			if (dataInput.length() > maxCharCount) {
				System.out.println("Too many characters entered. A maximum " + maxCharCount + " characters is allowed");
			}
			
			if (CastValue(dataInput)) {
				break;
			}
			
			// Display an error message to the user indicating that the selection was not valid
			System.out.println("Invalid Entry. Please select enter a valid entry or 'q' to quit");
		}
		
		methodInvoker.callMethod(methodName, String.class, dataInput);
	}
	
	@SuppressWarnings("deprecation")
	protected boolean CastValue(String dataInput) {
		// Cast String
		if (cls == String.class) { return true; }
		
		// Cast Date
		if (cls == Date.class) {
			try { Date.parse(dataInput); return true; } catch (Exception Ex) { }
		}
		
		// Cast Long
		if (cls == Long.class) {
			try { Long.parseLong(dataInput); return true; } catch (Exception Ex) {}
		}
		
		return false;
	}
}
