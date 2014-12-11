package app;

public class Controller {
	
	private MemberManager mm = null;
	private ServicesManager sm = null;
	private ProviderManager pm = null;
	
	public void initialize() {
		
		// Create the manager instances
		mm = new MemberManager();
		sm = new ServicesManager();
		pm = new ProviderManager();
		
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
	}
}
