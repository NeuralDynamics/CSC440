package app;

import java.io.IOException;
import java.util.Date;

import date.conversion.DateChecker;

enum State {
	// Unknown State
	Unknown,

	// Main Menu
	MainMenu,

	// Provider Menu
	ProviderMenu, LoginMember, LogService, ReportMenu,

	// Log Service Steps
	LogService_Member, LogService_Code, LogService_Date, LogService_Comments, LogService_Complete,

	// Available Reports
	Rpt_EFTRecs, Rpt_MemberSvc, Rpt_ProviderSvc, Rpt_Services,

	// Exit the application
	Exit
}

public class StateMachineCtrllr extends AController {

	boolean _continue;
	String methodToCall = null;
	String quitToMethod = null;

	@Override
	public void start() {
		initialize();
		runStateMachine();
	}

	@Override
	protected void initialize() {
		_continue = true;
		super.initialize();
	}

	protected void processQuit(String methodToQuitTo) {
		_continue = false;
		quitToMethod = methodToQuitTo;
	}

	protected void resetSelectedOption() {
		methodToCall = null;
	}

	protected boolean quitNow() {
		// Get the method name in the 2nd position in the array (this counts as
		// a method)
		String methodName = Thread.currentThread().getStackTrace()[2]
				.getMethodName();

		// If null, then don't quit this method
		if (quitToMethod == null) {
			return false;
		}

		if (quitToMethod.equals(methodName)) {
			// Since we have reached the method we need to quit to, reset this
			// value
			quitToMethod = null;

			return false;
		}

		return true;
	}

	private State _currState = State.Unknown;
	private State _quitToState = State.Unknown;

	private String _displayMenuMethod;
	private String _inputProcessMethod;
	private Class<?> _inputProcessParamType;
	private int _inputProcessMaxCharCount;
	
	private boolean _noInteraction = false;

	protected void configureState() {
		// Auto-set the current state to the main menu if its not known what
		// state it currently is in
		if (_currState == State.Unknown) {
			_currState = State.MainMenu;
		}

		// Main Menu State
		if (_currState == State.MainMenu) {
			_displayMenuMethod = "displayMenu_LogInProvider";
			_inputProcessMethod = "processInput_LogInProvider";
			_inputProcessParamType = long.class;
			_inputProcessMaxCharCount = 9;
			_quitToState = State.Exit;
			return;
		}

		// Provider Menu State
		if (_currState == State.ProviderMenu) {
			_displayMenuMethod = "displayMenu_Provider";
			_inputProcessMethod = "processInput_Provider";
			_inputProcessParamType = int.class;
			_inputProcessMaxCharCount = 1;
			_quitToState = State.MainMenu;
			return;
		}

		// Log In Member State
		if (_currState == State.LoginMember) {
			_displayMenuMethod = "displayMenu_LogInMember";
			_inputProcessMethod = "processInput_LogInMember";
			_inputProcessParamType = long.class;
			_inputProcessMaxCharCount = 9;
			_quitToState = State.ProviderMenu;
			return;
		}
		
		// Log Service State (START)
		if (_currState == State.LogService) {
			_displayMenuMethod = "processInput_LogService";
			_inputProcessMethod = null;
			_inputProcessParamType = null;
			_inputProcessMaxCharCount = -1;
			_quitToState = State.ProviderMenu;
			return;
		}

		// Log Service State (Step 1 - Member Login)
		if (_currState == State.LogService_Member) {
			_displayMenuMethod = "displayMenu_LogService1";
			_inputProcessMethod = "processInput_LogService_Step1";
			_inputProcessParamType = long.class;
			_inputProcessMaxCharCount = 9;
			_quitToState = State.ProviderMenu;
			return;
		}

		// Log Service State (Step 2 - Service Code)
		if (_currState == State.LogService_Code) {
			_displayMenuMethod = "displayMenu_LogService2";
			_inputProcessMethod = "processInput_LogService_Step2";
			_inputProcessParamType = long.class;
			_inputProcessMaxCharCount = 6;
			_quitToState = State.ProviderMenu;
			return;
		}

		// Log Service State (Step 3 - Service Date)
		if (_currState == State.LogService_Date) {
			_displayMenuMethod = "displayMenu_LogService3";
			_inputProcessMethod = "processInput_LogService_Step3";
			_inputProcessParamType = Date.class;
			_inputProcessMaxCharCount = 10;
			_quitToState = State.ProviderMenu;
			return;
		}

		// Log Service State (Step 4 - Service Comments)
		if (_currState == State.LogService_Comments) {
			_displayMenuMethod = "displayMenu_LogService4";
			_inputProcessMethod = "processInput_LogService_Step4";
			_inputProcessParamType = String.class;
			_inputProcessMaxCharCount = 100;
			_quitToState = State.ProviderMenu;
			return;
		}

		// Log Service State (Step 5 - Service Log Complete & Save)
		if (_currState == State.LogService_Complete) {
			_displayMenuMethod = "menu_ServiceLogged";
			_inputProcessMethod = null;
			_inputProcessParamType = null;
			_inputProcessMaxCharCount = -1;
			_quitToState = State.ProviderMenu;
			return;
		}
		
		// Reports - Menu
		if (_currState == State.ReportMenu) {
			_displayMenuMethod = "displayMenu_Report";
			_inputProcessMethod = "processInput_Report";
			_inputProcessParamType = int.class;
			_inputProcessMaxCharCount = 1;
			_quitToState = State.ProviderMenu;
			return;
		}
		
		// Reports - Available Services
		if (_currState == State.Rpt_Services) {
			_displayMenuMethod = "runReportServices";
			_inputProcessMethod = null;
			_inputProcessParamType = null;
			_inputProcessMaxCharCount = -1;
			_quitToState = State.ReportMenu;
			return;
		}
		
		// Reports - Member Records
		if (_currState == State.Rpt_MemberSvc) {
			_displayMenuMethod = "runReportMember";
			_inputProcessMethod = null;
			_inputProcessParamType = null;
			_inputProcessMaxCharCount = -1;
			_quitToState = State.ReportMenu;
			return;
		}
		
		// Reports - Provider Records
		if (_currState == State.Rpt_ProviderSvc) {
			_displayMenuMethod = "runReportProvider";
			_inputProcessMethod = null;
			_inputProcessParamType = null;
			_inputProcessMaxCharCount = -1;
			_quitToState = State.ReportMenu;
			return;
		}
		
		// Reports - EFT Records
		if (_currState == State.Rpt_EFTRecs) {
			_displayMenuMethod = "runReportEFT";
			_inputProcessMethod = null;
			_inputProcessParamType = null;
			_inputProcessMaxCharCount = -1;
			_quitToState = State.ReportMenu;
			return;
		}
	}

	protected void runStateMachine() {
		do {
			// Reset every pass
			_noInteraction = false;
			
			// Configure the parameters of the Machine based upon its current
			// state
			configureState();

			// Configure the menu
			if (_displayMenuMethod != null) {
				callMethod(_displayMenuMethod); }

			// Display the menu & Call the method passed after receiving a
			// potential valid value
			if (_inputProcessMethod != null) {
				displayMsg(_inputProcessMethod, _inputProcessParamType, _inputProcessMaxCharCount);
			}

			if (quitNow()) {
				break;
			}

			// We only want to loop if we are not event based (CommandLine in
			// other words)
		} while ((_isEventBased == false && _currState != State.Exit) || _noInteraction);
	}

	/****************************************************
	 * Menu - Log In Provider Welcome Message & Provider Log In Prompt
	 ****************************************************/
	public void processInput_LogInProvider(long ProviderID, boolean quit) {
		// If we should quit, quit to the main menu
		if (quit) {
			_currState = _quitToState;
			return;
		}

		// Validate the provider ID (if invalid notify the user)
		if (ValidateProviderID(ProviderID) == false) {
			// If validation fails, we don't want to change the state, just
			// redisplay our current state.
			return;
		}

		// Update our state to the next state
		_currState = State.ProviderMenu;
	}

	/****************************************************
	 * Menu - Provider Provider Menu Options
	 ****************************************************/
	public void processInput_Provider(int option, boolean quit) {
		// If we should quit, quit to the main menu
		if (quit) {
			_currState = _quitToState;
			return;
		}

		// Determine which option to call
		switch (option) {
		case 1:
			// methodToCall = "menu_LogInMember";
			_currState = State.LoginMember;
			break;
		case 2:
			// methodToCall = "menu_LogService";
			_currState = State.LogService;
			break;
		case 3:
			// methodToCall = "menu_RunReport";
			_currState = State.ReportMenu;
			break;
		}
	}

	/****************************************************
	 * Menu - Log In Member Member Log In Prompt
	 ****************************************************/
	public void processInput_LogInMember(long MemberID, boolean quit) {
		// If we should quit, quit to the main menu
		if (quit) {
			_currState = _quitToState;
			return;
		}

		// Validate the provider ID (if invalid notify the user)
		if (ValidateMemberNumber(MemberID) == false) {
			return;
		}

		// Go back to the Provider Menu
		_currState = State.ProviderMenu;
	}

	/****************************************************
	 * Menu - Log Service Log Service Prompts
	 ****************************************************/
	public void processInput_LogService() {
		_currState = State.LogService_Member;
		_noInteraction = true;
	}
	
	public void processInput_LogService_Step1(long memberNumber, boolean quit) {
		// If we should quit, quit to the main menu
		if (quit) {
			_currState = _quitToState;
			return;
		}

		// Validate the Member Number
		if (ValidateMemberNumber(memberNumber) && _member != null) {
			_currState = State.LogService_Code;
			return;
		}
	}

	public void processInput_LogService_Step2(long serviceCode, boolean quit) {
		// If we should quit, quit to the main menu
		if (quit) {
			_currState = _quitToState;
			return;
		}

		// Validate the Member Number
		if (LogServiceCode(serviceCode)) {
			_currState = State.LogService_Date;
		}
	}

	public void processInput_LogService_Step3(Date serviceDate, boolean quit) {
		// If we should quit, quit to the main menu
		if (quit) {
			_currState = _quitToState;
			return;
		}

		// Log the Service Date
		if (LogServiceDate(serviceDate)) {
			_currState = State.LogService_Comments;
		}
	}

	public void processInput_LogService_Step4(String comments, boolean quit) {
		// If we should quit, quit to the main menu
		if (quit) {
			_currState = _quitToState;
			return;
		}

		// Validate the Member Number
		if (LogServiceComments(comments)) {
			_currState = State.LogService_Complete;
		}
	}

	public void menu_ServiceLogged() {
		_providedServiceMgr.addProvidedService(_providedService);
		_providedServiceMgr.save();
		displayMenu_ServiceLogged();
		_currState = State.ProviderMenu;
	}

	/****************************************************
	 * Menu - Report Report Menu Options
	 ****************************************************/
	public void processInput_Report(int option, boolean quit) {
		// If we should quit, quit to the main menu
		if (quit) {
			_currState = _quitToState;
			return;
		}
		
		// Determine which option to call
		switch (option) {
		case 1:
			_currState = State.Rpt_ProviderSvc;
			break;
		case 2:
			_currState = State.Rpt_MemberSvc;
			break;
		case 3:
			_currState = State.Rpt_Services;
			break;
		case 4:
			_currState = State.Rpt_EFTRecs;
			break;
		}
	}

	public void runReport(String ReportName) throws ReportNotFoundException, IOException {
		IReport rpt = ReportFactory.createReport(ReportName, _member, _provider);
		rpt.runReport(ReportName + " Report.txt");
		display_ReportComplete();
		_currState = _quitToState;
		
		// Setup 'No Interaction' so our state machine knows to continue to the next state right away
		_noInteraction = true;
	}

	public void runReportProvider() throws ReportNotFoundException, IOException {
		runReport("Provider");
	}

	public void runReportMember() throws ReportNotFoundException, IOException {
		runReport("MemberService");
	}

	public void runReportServices() throws ReportNotFoundException, IOException {
		runReport("Services");
	}

	public void runReportEFT() throws ReportNotFoundException, IOException {
		runReport("EFTRecords");
	}

	private boolean ValidateProviderID(long providerID) {
		_provider = _providerMgr.findProvider(providerID);

		// If the provider is NULL let the user know they weren't found
		if (_provider == null) {
			display_ProviderNotValid();
			return false;
		}

		return true;
	}

	private boolean LogServiceCode(long serviceCode) {
		// Make sure a valid Member Number has been entered (should never be
		// seen!)
		if (_member == null) {
			display_MemberNotLoggedIn();
			return false;
		}

		// Find the Service by Service Code
		_service = _serviceMgr.findService(serviceCode);

		if (_service == null) {
			display_InvalidServiceCode();
			return false;
		}

		// Set the properties of the provided service
		_providedService = new ProvidedService();
		_providedService.setServiceCode(serviceCode);
		_providedService.setServiceFee(_service.getServiceFee());
		_providedService.setMemberNumber(_member.getMemberNumber());
		_providedService.setMemberName(_member.getName());
		_providedService.setProviderNumber(_provider.getProviderNumber());
		_providedService.setDateTimeReceived(new Date());
		return true;
	}

	private boolean LogServiceDate(Date serviceDate) {
		// Make sure a valid Member Number has been entered (should never be
		// seen!)
		if (_providedService == null) {
			display_MemberNotLoggedIn();
			return false;
		}
		
		// Make sure we have a valid date
		if (serviceDate == null) {
			display_InvalidDateEntry();
			return false;
		}
		
		// Make sure the date isn't in the future
		if (DateChecker.BeforeOrToday(serviceDate) == false) {
			display_InvalidDate_Future();
			return false;
		}
		
		// Make sure the date isn't too far in the past
		if (DateChecker.withinDays(serviceDate, getNumDays()) == false) {
			display_InvalidDate_Future();
			return false;
		}

		// Set the Date of Service
		_providedService.setDateOfService(serviceDate);
		return true;
	}

	private boolean LogServiceComments(String comments) {
		// Make sure a valid Member Number has been entered (should never be
		// seen!)
		if (_providedService == null) {
			display_MemberNotLoggedIn();
			return false;
		}

		// Set the Date of Service
		_providedService.setComments(comments);
		return true;
	}

	private boolean ValidateMemberNumber(long memberNumber) {
		// Find Member by Member Number
		_member = _memberMgr.findMember(memberNumber);

		// Notify the user the member was not found
		if (_member == null) {
			display_MemberInvalid();
			return false;
		}

		// Determine if the member is suspended or not
		if (_member.getIsSuspended()) {
			display_MemberSuspended();
			_member = null; // force the member back to null since they are
							// currently suspended
			return true;
		}

		// Display 'Validated'
		display_MemberValid();

		return true;
	}
}