package app;

public final class ReportFactory {
	protected static MemberManager _memberMgr = null;
	protected static ServiceManager _servicesMgr = null;
	protected static ProviderManager _providerManager = null;
	protected static ProvidedServiceManager _providedServiceMgr = null;
	
	public static void setMemberManager(MemberManager memberMgr) {
		_memberMgr = memberMgr;
	}
	
	public static void setServicesManager(ServiceManager servicesMgr) {
		_servicesMgr = servicesMgr;
	}
	
	public static void setProviderManager(ProviderManager providerManager) {
		_providerManager = providerManager;
	}
	
	public static void setProvidedServiceMgr(ProvidedServiceManager providedServiceMgr) {
		_providedServiceMgr = providedServiceMgr;
	}
	
	public static IReport createReport(String reportType, Member member, Provider provider) throws ReportNotFoundException {
		IReport rpt = null;
		
		switch(reportType) {
		case "Provider":
			rpt = new RptProvider();
			break;
		case "MemberService":
			rpt = new RptMemberService();
			break;
		case "EFTRecords":
			rpt = new RptEFTRecords();
			break;
		case "Services":
			rpt = new RptServices();
		}
		
		if (rpt == null) {
			throw new ReportNotFoundException("The Report " + reportType + " was not found.");
		}
		
		// Set the managers
		rpt.setMemberManager(_memberMgr);
		rpt.setProviderManager(_providerManager);
		rpt.setServiceManager(_servicesMgr);
		rpt.setProvidedServiceManager(_providedServiceMgr);
		
		// Set the Member/Provider instances
		rpt.setMember(member);
		rpt.setProvider(provider);
		
		// Return the report
		return rpt;
	}
}
