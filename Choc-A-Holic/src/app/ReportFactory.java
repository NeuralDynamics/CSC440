package app;

public final class ReportFactory {
	public final IReport createReport(String reportType) throws ReportNotFoundException {
		IReport rpt = null;
		
		switch(reportType) {
		case "Provider":
			rpt = new ProviderReport();
			break;
		case "MemberService":
			rpt = new MemberServiceReport();
			break;
		case "EFTRecords":
			rpt = new EFTRecordsReport();
			break;
		}
		
		if (rpt == null) {
			throw new ReportNotFoundException("The Report " + reportType + " was not found.");
		}
		
		return rpt;
	}
}