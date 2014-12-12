package app;

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
		
		IUserInterface ui = new CmdLnUI();
		IController ctrl2 = new Controller();
		
		ui.setMethodInvoker(ctrl2);
		ctrl2.setUserInterface(ui);
		
		ctrl2.start();
		
		
		//ctrl.initialize();
	}
}