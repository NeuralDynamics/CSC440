package app;

public abstract class AController extends MenuSystem {
	protected MemberManager _memberMgr = null;
	protected ServicesManager _serviceMgr = null;
	protected ProviderManager _providerMgr = null;
	protected ProvidedServiceManager _providedServiceMgr = null;
	
	protected Member _member = null;
	protected Provider _provider = null;
	protected Service _service = null;
	protected ProvidedService _providedService = null;
	
	protected void initialize() {
		
		// Create the manager instances
		_memberMgr = new MemberManager();
		_serviceMgr = new ServicesManager();
		_providerMgr = new ProviderManager();
		_providedServiceMgr = new ProvidedServiceManager();
		
		ReportFactory.setMemberManager(_memberMgr);
		ReportFactory.setServicesManager(_serviceMgr);
		ReportFactory.setProviderManager(_providerMgr);
		
		// Load the Member Data
		try {
			_memberMgr.load();
		} catch (Exception Ex) {
			
		}
		
		// Load the Service data
		try {
			_serviceMgr.load();
		} catch (Exception Ex) {
			
		}
		
		// Load the Provider data
		try {
			_providerMgr.load();
		} catch (Exception Ex) {
			
		}
		
		// Load the ProvidedService data
		try {
			_providedServiceMgr.load();
		} catch (Exception Ex) {
			
		}
	}
	
	protected void save() {
		try {
			_memberMgr.save();
		} catch (Exception Ex) {
			System.out.println("Error saving Member Data");
		}
		
		try {
			_serviceMgr.save();
		} catch (Exception Ex) {
			System.out.println("Error saving Service Data");
		}
		
		try {
			_providerMgr.save();
		} catch (Exception Ex) {
			System.out.println("Error saving Provider Data");
		}
		
		try {
			_providedServiceMgr.save();
		} catch (Exception Ex) {
			System.out.println("Error saving ProvidedService Data");
		}
	}
}
