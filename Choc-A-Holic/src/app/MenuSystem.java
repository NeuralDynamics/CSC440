package app;

public abstract class MenuSystem extends AMethodInvoker implements IController {
	private IUserInterface _userInterface = null;
	private static String QUALIFIER = "***";
	
	protected boolean _isEventBased = false;
	
	protected void displayMsg(String methodName, Class<?> cls, int maxCharCount) {
		_userInterface.displayMsg(methodName, cls, maxCharCount);
	}
	
	protected void initialize() {
		// Set up for Event Based processing or not
		_isEventBased = _userInterface.getIsEventBased();
	}
	
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
		_userInterface.addMessageLine("Please your Provider Number or 'q' to Quit:");
		addDivider();
	}

	// Provider Menu
	public void displayMenu_Provider() {
		_userInterface.addMessageLine("Select an option below:");
		_userInterface.addMessageLine("1: Log in Member");
		_userInterface.addMessageLine("2: Log Service");
		_userInterface.addMessageLine("3: Run Report");
		_userInterface.addMessageLine("q: Quit");
		addDivider();
	}

	// Report Menu
	public void displayMenu_Report() {
		_userInterface.addMessageLine("Select an option below:");
		_userInterface.addMessageLine("1: Provider Report");
		_userInterface.addMessageLine("2: Member Report");
		_userInterface.addMessageLine("3: Available Services Report");
		_userInterface.addMessageLine("4: EFT Record Report");
		_userInterface.addMessageLine("q: Quit");
		addDivider();
	}

	// Log In Member
	public void displayMenu_LogInMember() {
		_userInterface.addMessageLine("Please enter the Member Number (Max 9 Numbers) or 'q' to quit:");
		addDivider();
	}

	// Log Service Menu - Steps 1-4
	public void displayMenu_LogService1() {
		_userInterface.addMessageLine("Please enter the Member Number (Max 9 Numbers) or 'q' to quit:");
		addDivider();
	}

	public void displayMenu_LogService2() {
		_userInterface.addMessageLine("Please enter the Service Code (Max 6 Numbers) or 'q' to quit:");
		addDivider();
	}

	public void displayMenu_LogService3() {
		_userInterface.addMessageLine("Please enter the Service Date (yyyy-DD-MM Format) or 'q' to quit:");
		addDivider();
	}
	
	public void displayMenu_LogService4() {
		_userInterface.addMessageLine("Please enter any comments (Max 100 Characters) or 'q' to quit:");
		addDivider();
	}
	
	public void displayMenu_ServiceLogged() {
		_userInterface.addMessageLine(QUALIFIER + " Service has been Logged " + QUALIFIER);
	}
	
	public void display_MemberValid() {
		_userInterface.addMessageLine(QUALIFIER + " Validated " + QUALIFIER);
	}
	
	public void display_MemberInvalid() {
		_userInterface.addMessageLine(QUALIFIER + " Invalid Member Number " + QUALIFIER);
	}
	
	public void display_MemberSuspended() {
		_userInterface.addMessageLine(QUALIFIER + " Member is Suspended " + QUALIFIER);
	}
	
	public void display_MemberNotLoggedIn() {
		_userInterface.addMessageLine(QUALIFIER + " A Member has not been Logged In " + QUALIFIER);
	}
	
	public void display_ProviderNotValid() {
		_userInterface.addMessageLine(QUALIFIER + " Invalid Provider Number " + QUALIFIER);
	}
	
	public void display_InvalidServiceCode() {
		_userInterface.addMessageLine(QUALIFIER + " Invalid Service Code " + QUALIFIER);
	}
	
	protected void addDivider() {
		_userInterface.addMessageLine("----------------------------");
	}
	
	public void display_ReportComplete() {
		_userInterface.addMessageLine(QUALIFIER + " Report Completed Successfully " + QUALIFIER);
	}
}