package app;

import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class RptProvider extends AReport {
	
	@Override
	protected void buildRecords() {
		
		double totalFee = 0;
		int recCount = 0;
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		
		String record = "";
		
		// Get a listing of all provided services
		List<ProvidedService> provServList = _providedServiceMgr.findByProvider(provider.getProviderNumber());
		
		// Allocate space for the queue
		allocateQueue(provServList.size() + 2);
		
		// Write out the Provider Name
		record += provider.getName() + delimiter;
				
		// Write out the Provider Number
		record += String.format(Locale.US, "%09d", provider.getProviderNumber());
		
		// Write out the Address
		record += provider.getAddress() + delimiter;
		
		// Write out the City
		record += provider.getCity() + delimiter;
		
		// Write out the State
		record += provider.getState() + delimiter;
		
		// Write out the Zip Code
		record += String.format(Locale.US, "%09d", provider.getZip());
		
		// Enqueue the record
		enqueueRecord(record);
		
		// Loop through all of the Provided Service records
		for (ProvidedService ps: provServList) {
			
			// Increment our record counter
			recCount++;
			
			// Initialize the record
			record = "";
			
			// Service Date
			record += dtFormat_Dt.format(ps.getDateOfService()) + delimiter;
			
			// Received Date/Time
			record += dtFormat_DtTm.format(new Date()) + delimiter;
			
			// Member Name
			record += Misc.padRight(ps.getMemberName(), 25) + delimiter;
			
			// Write out the Member Number
			record += String.format(Locale.US, "%09d", ps.getMemberNumber()) + delimiter;
			
			// Write out the Service Code
			record += String.format(Locale.US, "%06d", ps.getServiceCode()) + delimiter;
			
			// Write out the Service Fee
			record += formatter.format(ps.getServiceFee());
			totalFee += ps.getServiceFee();			
			
			// Enqueue the record for output
			enqueueRecord(record);
		}
		
		// Total Record
		record = "";
		record += formatter.format(totalFee) + delimiter;
		record += String.format(Locale.US, "%03d", recCount);
		enqueueRecord(record);
	}
}
