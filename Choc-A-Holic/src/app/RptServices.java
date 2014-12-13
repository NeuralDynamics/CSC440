package app;

import java.text.NumberFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class RptServices extends AReport {

	@Override
	protected void buildRecords() {
		// Service Name, Service Fee, Service Code
		
		// Currency Formatter
		NumberFormat formatter = NumberFormat.getCurrencyInstance();

		String record = "";
		
		// Get a list of all of the available services
		List<Service> svcList = _serviceMgr.getServiceList();
		
		// Allocate space for the queue
		allocateQueue(svcList.size() + 1);
		
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
			record += s.getServiceName() + delimiter;
			
			// Service Fee
			record += formatter.format(s.getServiceFee());
			
			// Enqueue the record for output
			enqueueRecord(record);
		}
	}
}
