package app;

import java.io.IOException;
import java.util.Date;

public class CmdLnController extends AController {
	
	boolean _continue;
	String methodToCall = null;
	String quitToMethod = null;
	
	@Override
	public void start() {
		initialize();
		menu_LogInProvider();
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
		// Get the method name in the 2nd position in the array (this counts as a method)
		String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
		
		// If null, then don't quit this method
		if (quitToMethod == null) { return false; }
		
		if (quitToMethod.equals(methodName)) {
			// Since we have reached the method we need to quit to, reset this value
			quitToMethod  = null;

			return false;
		}
		
		return true;
	}

	/****************************************************
	 * Menu - Log In Provider
	 * Welcome Message & Provider Log In Prompt
	****************************************************/
	public void menu_LogInProvider() {
		while (_continue) {
			displayMenu_LogInProvider();
			displayMsg("processInput_LogInProvider", long.class, 9);
	
			// If the method to call is not null, then call it now
			if (methodToCall != null) {
				callMethod(methodToCall);
			}

			// If there is a method we need to quit to, and we have not yet reached it, continue breaking
			if (quitNow()) { break; }
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
			displayMsg("processInput_Provider", int.class, 9);

			// If the method to call is not null, then call it now
			if (methodToCall != null) {
				callMethod(methodToCall);
			}

			// If there is a method we need to quit to, and we have not yet reached it, continue breaking
			if (quitNow()) { break; }
		}

		_continue = true;
	}

	public void processInput_Provider(int option, boolean quit) {
		// Reset the Option Chosen
		resetSelectedOption();

		// Determine if we need to quit this menu
		if (quit) { processQuit("menu_LogInProvider"); return; }

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
	 * Menu - Log In Provider
	 * Welcome Message & Provider Log In Prompt
	****************************************************/
	public void menu_LogInMember() {
		while (_continue) {
			displayMenu_LogInMember();
			displayMsg("processInput_LogInMember", long.class, 9);
	
			// If the method to call is not null, then call it now
			if (methodToCall != null) {
				callMethod(methodToCall);
			}

			// If there is a method we need to quit to, and we have not yet reached it, continue breaking
			if (quitNow()) { break; }
		}
	
		_continue = true;
	}
	
	public void processInput_LogInMember(long MemberID, boolean quit) {
		// Reset the Option Chosen
		resetSelectedOption();

		// Determine if we need to quit this menu
		if (quit) { processQuit("menu_Provider"); return; }

		// Validate the provider ID (if invalid notify the user)
		if (ValidateMemberNumber(MemberID) == false) {
			return;
		}

		methodToCall = "menu_Provider";
	}

	/****************************************************
	 * Menu - Log Service
	 * Log Service Prompts
	****************************************************/
	public void menu_LogService() {
		while (_continue) {
			displayMenu_LogService1();
			displayMsg("processInput_LogService", long.class, 9);

			// If the method to call is not null, then call it now
			if (methodToCall != null) {
				callMethod(methodToCall);
			}

			// If there is a method we need to quit to, and we have not yet reached it, continue breaking
			if (quitNow()) { break; }
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
			displayMsg("processInput_LogService_Step2", long.class, 9);

			// If the method to call is not null, then call it now
			if (methodToCall != null) {
				callMethod(methodToCall);
			}

			// If there is a method we need to quit to, and we have not yet reached it, continue breaking
			if (quitNow()) { break; }
		}

		_continue = true;
	}

	public void processInput_LogService_Step2(long serviceCode, boolean quit) {
		// Reset the Option Chosen
		resetSelectedOption();

		// Determine if we need to quit this menu
		if (quit) { processQuit("menu_Provider"); return; }

		// Validate the Member Number
		if (LogServiceCode(serviceCode)) {
			methodToCall = "menu_LogService_Step3";
		}
	}

	public void menu_LogService_Step3() {
		while (_continue) {
			displayMenu_LogService3();
			displayMsg("processInput_LogService_Step3", Date.class, 10);

			// If the method to call is not null, then call it now
			if (methodToCall != null) {
				callMethod(methodToCall);
			}

			// If there is a method we need to quit to, and we have not yet reached it, continue breaking
			if (quitNow()) { break; }
		}

		_continue = true;
	}

	public void processInput_LogService_Step3(Date serviceDate, boolean quit) {
		// Reset the Option Chosen
		resetSelectedOption();

		// Determine if we need to quit this menu
		if (quit) { processQuit("menu_Provider"); return; }

		// Validate the Member Number
		if (LogServiceDate(serviceDate)) {
			methodToCall = "menu_ServiceLogged";
		}
	}
	
	/****************************************************
	 * Menu - Report
	 * Report Menu Options
	****************************************************/
	public void menu_ServiceLogged() {
		_providedServiceMgr.save();
		displayMenu_ServiceLogged();
		processQuit("menu_Provider");	
	}

	/****************************************************
	 * Menu - Report
	 * Report Menu Options
	****************************************************/
	public void menu_Report() {
		while (_continue) {
			displayMenu_Report();
			displayMsg("processInput_Report", int.class, 1);

			// If the method to call is not null, then call it now
			if (methodToCall != null) {
				callMethod(methodToCall);
			}

			// If there is a method we need to quit to, and we have not yet reached it, continue breaking
			if (quitNow()) { break; }
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
	
	public void runReportProvider() throws ReportNotFoundException, IOException {
		IReport rpt = ReportFactory.createReport("Provider", _member, _provider);
		rpt.runReport("ProviderReport.txt");
	}
	
	public void runReportMember() throws ReportNotFoundException, IOException {
		IReport rpt = ReportFactory.createReport("MemberService", _member, _provider);
		rpt.runReport("MemberServiceReport.txt");
	}
	
	public void runReportServices() throws ReportNotFoundException, IOException {
		IReport rpt = ReportFactory.createReport("Services", _member, _provider);
		rpt.runReport("ServicesReport.txt");
	}
	
	public void runReportEFT() throws ReportNotFoundException, IOException {
		IReport rpt = ReportFactory.createReport("EFTRecords", _member, _provider);
		rpt.runReport("EFTRecordsReport.txt");
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
		// Make sure a valid Member Number has been entered (should never be seen!)
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
		// Make sure a valid Member Number has been entered (should never be seen!)
		if (_providedService == null) {
			display_MemberNotLoggedIn();
			return false;
		}
		
		// Set the Date of Service
		_providedService.setDateOfService(serviceDate);
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
			return false;
		}
		
		// Display 'Validated'
		display_MemberValid();
		
		return true;
	}
}