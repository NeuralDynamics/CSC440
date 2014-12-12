package app;

public abstract class MenuSystem extends AMethodInvoker implements IController {
	IUserInterface _userInterface = null;
	
	@Override
	public void setUserInterface(IUserInterface userInterface) {
		this._userInterface = userInterface;
	}
	
	@Override
	public IUserInterface getUserInterface() {
		return this._userInterface;
	}
	
	// Main Menu
	public void displayMenu_LogInProvider() {
		_userInterface.addMessageLine("Welcome to Choc-An-Holics!");
		_userInterface.addMessageLine("Please your Provider Number");
	}

	// Provider Menu
	public void displayMenu_Provider() {
		_userInterface.addMessageLine("Select an option below");
		_userInterface.addMessageLine("1: Log in Member");
		_userInterface.addMessageLine("2: Log Service");
		_userInterface.addMessageLine("3: Run Report");
	}

	// Report Menu
	public void displayMenu_Report() {
		_userInterface.addMessageLine("Select an option below");
		_userInterface.addMessageLine("1: Provider Report");
		_userInterface.addMessageLine("2: Member Report");
		_userInterface.addMessageLine("3: Available Services Report");
		_userInterface.addMessageLine("4: EFT Record Report");
	}

	// Log In Member
	public void displayMenu_LogInMember() {
		_userInterface.addMessageLine("Please enter the Member Number");
	}

	// Log Service Menu - Steps 1-3
	public void displayMenu_LogService1() {
		_userInterface.addMessageLine("Please enter the Member Number");
	}

	public void displayMenu_LogService2() {
		_userInterface.addMessageLine("Please enter the Service Date");
	}

	public void displayMenu_LogService3() {
		_userInterface.addMessageLine("Please enter the Service Code");
	}
	
	public void displayMenu_ServiceLogged() {
		_userInterface.addMessageLine("Service has been logged");
	}
}