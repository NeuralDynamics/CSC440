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
	
	protected String getDateFormat() {
		return _userInterface.getDateFormat();
	}
	
	protected int getNumDays() {
		return _userInterface.getNumDays();
	}
	
	@Override
	public void setUserInterface(IUserInterface userInterface) {
		this._userInterface = userInterface;
	}
	
	@Override
	public IUserInterface getUserInterface() {
		return this._userInterface;
	}
	
	private void addQuitLine() {
		_userInterface.addMessageLine("Enter 'q' to Quit:");
	}
	
	// Main Menu
	public void displayMenu_LogInProvider() {
		_userInterface.addMessageLine("Welcome to Choc-An-Holics!");
		_userInterface.addMessageLine("Please your Provider Number");
		addQuitLine();
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
		_userInterface.addMessageLine("Please enter the Member Number");
		_userInterface.addMessageLine("(Max 9 Numbers)");
		addQuitLine();
		addDivider();
	}

	// Log Service Menu - Steps 1-4
	public void displayMenu_LogService1() {
		_userInterface.addMessageLine("Please enter the Member Number");
		_userInterface.addMessageLine("(Max 9 Numbers)");
		addQuitLine();
		addDivider();
	}

	public void displayMenu_LogService2() {
		_userInterface.addMessageLine("Please enter the Service Code");
		_userInterface.addMessageLine("(Max 6 Numbers)");
		addQuitLine();
		addDivider();
	}

	public void displayMenu_LogService3() {
		_userInterface.addMessageLine("Please enter the Service Date");
		_userInterface.addMessageLine("(" + getDateFormat() + " Format)");
		addQuitLine();
		addDivider();
	}
	
	public void displayMenu_LogService4() {
		_userInterface.addMessageLine("Please enter any comments");
		_userInterface.addMessageLine("(Max 100 Characters)");
		addQuitLine();
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
	
	public void display_InvalidDateEntry() {
		_userInterface.addMessageLine(QUALIFIER + " Please enter a valid date in the format " + getDateFormat() + " " + QUALIFIER);
	}
	
	public void display_InvalidDate_Future() {
		_userInterface.addMessageLine(QUALIFIER + " The date entered is in the future. " + QUALIFIER);
		_userInterface.addMessageLine(QUALIFIER + " Please enter a date between Today and " + getNumDays() + " in past " + QUALIFIER);
	}
	
	public void display_InvalidDate_Past() {
		_userInterface.addMessageLine(QUALIFIER + " The date entered is too far in the past. " + QUALIFIER);
		_userInterface.addMessageLine(QUALIFIER + " Please enter a date between Today and " + getNumDays() + " in past " + QUALIFIER);
	}
	
	public void display_InvalidOption() {
		_userInterface.addMessageLine(QUALIFIER + " Invalid Option Selected " + QUALIFIER);
	}
	
	protected void addDivider() {
		_userInterface.addMessageLine("----------------------------");
	}
	
	public void display_ReportComplete() {
		_userInterface.addMessageLine(QUALIFIER + " Report Completed Successfully " + QUALIFIER);
	}
}