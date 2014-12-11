package app;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class RptProviderDirectory extends AReport {

	@Override
	protected void buildRecords() {
		// Service Name, Service Fee, Service Code

		String record = "";
		
		// Get a list of all of the available services
		List<Service> svcList = _serviceMgr.getServiceList();
		
		// Allocate space for the queue
		allocateQueue(1 + svcList.size());
		
		// Enqueue the record
		enqueueRecord(record);
		
		// Loop through all of the Provided Service records
		Iterator<Service> it = svcList.iterator();
		while (it.hasNext()) {			
			// Initialize the record
			record = "";
			Service s = (Service) it.next();
			
			// Service Code
			record += String.format(Locale.US, "%06d", s.getServiceCode()) + delimiter;
			
			// Service Name
			record += Misc.padRight(s.getServiceName(), 25) + delimiter;
			
			// Service Fee
			record += String.format(Locale.US, "%06d", s.getServiceFee());
			
			// Enqueue the record for output
			enqueueRecord(record);
		}
	}
}
