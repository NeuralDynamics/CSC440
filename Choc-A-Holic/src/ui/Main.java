package ui;

import app.Controller;
import app.IController;
import app.IUserInterface;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
	
		/*IUserInterface userInterface = new CmdLnInterface();
		Controller ctrl = new Controller();
		
		userInterface.setMethodInvoker(ctrl);
		ctrl.setUserInterface(userInterface);
		*/
		
		/*ctrl.test();*/
		
		//IUserInterface ui = new CmdLnUI();
		IUserInterface ui = new WinUI();
		IController ctrl2 = new Controller();
		
		ui.setMethodInvoker(ctrl2);
		ctrl2.setUserInterface(ui);
		
		ctrl2.start();
		
		
		//ctrl.initialize();
	}
}