package ui;

import app.AUserInterface;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CmdLnUI extends AUserInterface {
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	@Override
	public void displayMsg(String methodName, Class<?> cls, int maxCharCount) {
		// Call the super's method to set the local variables as needed
		super.displayMsg(methodName, cls, maxCharCount);
		
		// Display the text
		while (messages.isEmpty() == false)
		{ System.out.println(messages.poll()); }
		
		// Read the User Input
		readUserInput();
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
				addMessageLine("IO error trying to read data!");
			}
			
			// If our input says to quit, quit!
			if (dataInput.toLowerCase().equals("q")) {
				quit = true;
				objData = castObjHelper.getDefaultValue(cls);
				break;
			}
			
			if (testCharCount(dataInput.length(), maxCharCount) == false) {
				continue;
			}
			
			// Cast the value and return a valid object
			objData = castObjHelper.CastValue(cls, dataInput);
			if (objData != null) { break; }
			
			// Display an error message to the user indicating that the selection was not valid & break the loop!
			displayInvalidEntry();
			break;
		}
		
		methodInvoker.callMethod(this.methodName, this.cls, objData, boolean.class, quit);
	}


	@Override
	public boolean getIsEventBased() {
		// Never Event Based
		return false;
	}
}
