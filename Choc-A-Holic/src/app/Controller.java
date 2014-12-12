package app;

import java.util.Date;

public class Controller extends AMethodInvoker {
	
	private MemberManager mm = null;
	private ServiceManager sm = null;
	private ProviderManager pm = null;
	private ProvidedServiceManager psm = null;
	
	private IUserInterface userInterface = null;
	
	public void setUserInterface(IUserInterface userInterface) {
		this.userInterface = userInterface;
	}
	
	// Sample Output
	public void test() {
		userInterface.addMessageLine("Option 1");
		userInterface.addMessageLine("Option 2");
		userInterface.addMessageLine("Option 3");
		userInterface.displayMsg("testCall", String.class, 10);
	}
	
	public void testCall(String input) {
		// Success!!!
		// Now we need to do something with the input value....
		System.out.println(input);
	}
	
	public void initialize() {
		
		// Create the manager instances
		mm = new MemberManager();
		sm = new ServiceManager();
		pm = new ProviderManager();
		psm = new ProvidedServiceManager();
		
		// Load the Member Data
		try {
			mm.load();
		} catch (Exception Ex) {
			
		}
		
		// Load the Service data
		try {
			sm.load();
		} catch (Exception Ex) {
			
		}
		
		// Load the Provider data
		try {
			pm.load();
		} catch (Exception Ex) {
			
		}
		
		// Load the ProvidedService data
		try {
			psm.load();
		} catch (Exception Ex) {
			
		}
	}
	
	public void save() {
		try {
			mm.save();
		} catch (Exception Ex) {
			System.out.println("Error saving Member Data");
		}
		
		try {
			sm.save();
		} catch (Exception Ex) {
			System.out.println("Error saving Service Data");
		}
		
		try {
			pm.save();
		} catch (Exception Ex) {
			System.out.println("Error saving Provider Data");
		}
		
		try {
			psm.save();
		} catch (Exception Ex) {
			System.out.println("Error saving ProvidedService Data");
		}
	}
}
