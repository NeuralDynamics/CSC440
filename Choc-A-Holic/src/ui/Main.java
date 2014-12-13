package ui;

import app.Controller;
import app.IController;
import app.IUserInterface;
import app.StateMachineCtrllr;

public class Main {
	/**
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) {
		
		// Instantiate a User Interface
		IUserInterface ui = new CmdLnUI();
		//IUserInterface ui = new WinUI();
		
		// Instantiate a Controller
		//IController ctrl = new Controller();
		IController ctrl = new StateMachineCtrllr();
		
		ui.setMethodInvoker(ctrl);
		ctrl.setUserInterface(ui);
		
		ctrl.start();
	}
}