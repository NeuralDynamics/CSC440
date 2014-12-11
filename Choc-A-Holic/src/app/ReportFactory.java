package app;

public final class ReportFactory {
	public final IReport createReport(String reportType, MemberManager memMgr, ServicesManager svcMgr, ProviderManager prvMgr) throws ReportNotFoundException {
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
		}
		
		if (rpt == null) {
			throw new ReportNotFoundException("The Report " + reportType + " was not found.");
		}
		
		// Set the managers
		rpt.setMemberManager(memMgr);
		rpt.setProviderManager(prvMgr);
		rpt.setServiceManager(svcMgr);
		
		// Return the report
		return rpt;
	}
}
