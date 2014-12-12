package app;

public abstract class MenuSystem extends AMethodInvoker implements IController {
	IUserInterface userInterface = null;
	
	@Override
	public void setUserInterface(IUserInterface userInterface) {
		this.userInterface = userInterface;
	}
	
	@Override
	public IUserInterface getUserInterface() {
		return this.userInterface;
	}
	
	// Main Menu
	public void displayMenu_LogInProvider() {
		userInterface.addMessageLine("Welcome to Choc-An-Holics!");
		userInterface.addMessageLine("Please your Provider Number");
	}

	// Provider Menu
	public void displayMenu_Provider() {
		userInterface.addMessageLine("Select an option below");
		userInterface.addMessageLine("1: Log in Member");
		userInterface.addMessageLine("2: Log Service");
		userInterface.addMessageLine("3: Run Report");
	}

	// Report Menu
	public void displayMenu_Report() {
		userInterface.addMessageLine("Select an option below");
		userInterface.addMessageLine("1: Provider Report");
		userInterface.addMessageLine("2: Member Report");
		userInterface.addMessageLine("3: Available Services Report");
		userInterface.addMessageLine("4: EFT Record Report");
	}

	// Log In Member
	public void displayMenu_LogInMember() {
		userInterface.addMessageLine("Please enter the Member Number");
	}

	// Log Service Menu - Steps 1-3
	public void displayMenu_LogService1() {
		userInterface.addMessageLine("Please enter the Member Number");
	}

	public void displayMenu_LogService2() {
		userInterface.addMessageLine("Please enter the Service Date");
	}

	public void displayMenu_LogService3() {
		userInterface.addMessageLine("Please enter the Service Code");
	}
}