package app;

import java.util.Date;

public class CmdLnController extends MenuSystem {
	
	boolean _continue;
	String methodToCall = null;
	String quitToMethod = null;
	
	@Override
	public void start() {
		initialize();
		menu_LogInProvider();
	}
	
	protected void initialize() {
		_continue = true;
	}

	protected void processQuit(String methodToQuitTo) {
		_continue = false;
		quitToMethod = methodToQuitTo;
	}

	protected void resetSelectedOption() {
		methodToCall = null;
	}

	protected boolean checkQuitToMethod() {
		// Get the method name in the 2nd position in the array (this counts as a method)
		String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
		
		if (quitToMethod == null || quitToMethod.equals(methodName)) {
			// Since we have reached the method we need to quit to, reset this value
			quitToMethod  = null;

			return false;
		}		

		// We have not yet reached the method in question
		return true;
	}

	/****************************************************
	 * Menu - Log In Provider
	 * Welcome Message & Provider Log In Prompt
	****************************************************/
	public void menu_LogInProvider() {
		while (_continue) {
			displayMenu_LogInProvider();
			userInterface.displayMsg("processInput_LogInProvider", long.class, 9);
	
			// If the method to call is not null, then call it now
			if (methodToCall != null) {
				callMethod(methodToCall);
			}

			// If there is a method we need to quit to, and we have not yet reached it, continue breaking
			if (checkQuitToMethod() == false) { break; }
		}
	
		_continue = true;
	}
	
	public void processInput_LogInProvider(long ProviderID, boolean quit) {
		// Reset the Option Chosen
		resetSelectedOption();

		// Determine if we need to quit this menu
		if (quit) { processQuit(null); return; }

		// Validate the provider ID (if invalid notify the user)
		if (ValidateProviderID(ProviderID) == false) {
			userInterface.addMessageLine("Invalid Provider Number!");
			return;
		}

		methodToCall = "menu_Provider";
	}

	/****************************************************
	 * Menu - Provider
	 * Provider Menu Options
	****************************************************/
	public void menu_Provider() {
		while (_continue) {
			displayMenu_Provider();
			userInterface.displayMsg("processInput_Provider", int.class, 9);

			// If the method to call is not null, then call it now
			if (methodToCall != null) {
				callMethod(methodToCall);
			}

			// If there is a method we need to quit to, and we have not yet reached it, continue breaking
			if (checkQuitToMethod() == false) { break; }
		}

		_continue = true;
	}

	public void processInput_Provider(int option, boolean quit) {
		// Reset the Option Chosen
		resetSelectedOption();

		// Determine if we need to quit this menu
		if (quit) { processQuit(null); return; }

		// Determine which option to call
		switch (option) {
			case 1:
				methodToCall = "menu_LogInMember";
				break;
			case 2:
				methodToCall = "menu_LogService";
				break;
			case 3:
				methodToCall = "menu_RunReport";
				break;
		}
	}

	/****************************************************
	 * Menu - Log Service
	 * Log Service Prompts
	****************************************************/
	public void menu_LogService() {
		while (_continue) {
			displayMenu_LogService1();
			userInterface.displayMsg("processInput_LogService", long.class, 9);

			// If the method to call is not null, then call it now
			if (methodToCall != null) {
				callMethod(methodToCall);
			}

			// If there is a method we need to quit to, and we have not yet reached it, continue breaking
			if (checkQuitToMethod() == false) { break; }
		}

		_continue = true;
	}

	public void processInput_LogService(long memberNumber, boolean quit) {
		// Reset the Option Chosen
		resetSelectedOption();

		// Determine if we need to quit this menu
		if (quit) { processQuit("menu_Provider"); return; }

		// Validate the Member Number
		if (ValidateMemberNumber(memberNumber)) {
			methodToCall = "menu_LogService_Step2";
		}
	}

	public void menu_LogService_Step2() {
		while (_continue) {
			displayMenu_LogService2();
			userInterface.displayMsg("processInput_LogService_Step2", Date.class, 9);

			// If the method to call is not null, then call it now
			if (methodToCall != null) {
				callMethod(methodToCall);
			}

			// If there is a method we need to quit to, and we have not yet reached it, continue breaking
			if (checkQuitToMethod() == false) { break; }
		}

		_continue = true;
	}

	public void processInput_LogService_Step2(Date serviceDate, boolean quit) {
		// Reset the Option Chosen
		resetSelectedOption();

		// Determine if we need to quit this menu
		if (quit) { processQuit("menu_Provider"); return; }

		// Validate the Member Number
		if (LogServiceDate(serviceDate)) {
			methodToCall = "menu_LogService_Step3";
		}
	}

	public void menu_LogService_Step3() {
		while (_continue) {
			displayMenu_LogService3();
			userInterface.displayMsg("processInput_LogService_Step3", long.class, 9);

			// If the method to call is not null, then call it now
			if (methodToCall != null) {
				callMethod(methodToCall);
			}

			// If there is a method we need to quit to, and we have not yet reached it, continue breaking
			if (checkQuitToMethod() == false) { break; }
		}

		_continue = true;
	}

	public void processInput_LogService_Step3(long serviceCode, boolean quit) {
		// Reset the Option Chosen
		resetSelectedOption();

		// Determine if we need to quit this menu
		if (quit) { processQuit("menu_Provider"); return; }

		// Validate the Member Number
		if (LogServiceCode(serviceCode)) {
			methodToCall = "menu_ServiceLogged";
		}
	}

	/****************************************************
	 * Menu - Report
	 * Report Menu Options
	****************************************************/
	public void menu_Report() {
		while (_continue) {
			displayMenu_Report();
			userInterface.displayMsg("processInput_Report", int.class, 9);

			// If the method to call is not null, then call it now
			if (methodToCall != null) {
				callMethod(methodToCall);
			}

			// If there is a method we need to quit to, and we have not yet reached it, continue breaking
			if (checkQuitToMethod() == false) { break; }
		}

		_continue = true;
	}

	public void processInput_Report(int option, boolean quit) {
		// Reset the Option Chosen
		resetSelectedOption();

		// Determine if we need to quit this menu
		if (quit) { processQuit(null); return; }

		// Determine which option to call
		switch (option) {
			case 1:
				methodToCall = "runReportProvider";
				break;
			case 2:
				methodToCall = "runReportMember";
				break;
			case 3:
				methodToCall = "runReportServices";
				break;
			case 4:
				methodToCall = "runReportEFT";
				break;
		}
	}
	
	private boolean ValidateProviderID(long providerID) {
		// TODO Auto-generated method stub
		return false;
	}
	
	private boolean LogServiceCode(long serviceCode) {
		// TODO Auto-generated method stub
		return false;
	}
	
	private boolean LogServiceDate(Date serviceDate) {
		// TODO Auto-generated method stub
		return false;
	}
	
	private boolean ValidateMemberNumber(long memberNumber) {
		// TODO Auto-generated method stub
		return false;
	}
}