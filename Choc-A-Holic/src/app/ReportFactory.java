package app;

public final class ReportFactory {
	public final IReport createReport(String reportType) throws ReportNotFoundException {
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
		
		return rpt;
	}
}
