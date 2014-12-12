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
		
		IUserInterface ui = new CmdLnInterface();
		IController ctrl2 = new CmdLnController();
		
		ui.setMethodInvoker(ctrl2);
		ctrl2.setUserInterface(ui);
		
		ctrl2.start();
		
		
		//ctrl.initialize();
	}
}