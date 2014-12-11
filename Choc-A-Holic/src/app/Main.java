package app;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		IUserInterface userInterface = new CommandLine();		
		Controller ctrl = new Controller();
		
		userInterface.setMethodInvoker(ctrl);
		ctrl.setUserInterface(userInterface);
		
		ctrl.test();
		
		//ctrl.initialize();
	}
}