package app;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class RptProvider extends AReport {
	
	@Override
	protected void buildRecords() {
		
		double totalFee = 0;
		int recCount = 0;
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		
		String record = "";		
		
		// Allocate space for the queue
		allocateQueue(2 + providedSvc.size());
		
		// Write out the Member Name
		record += Misc.padRight(provider.getName(), 25).SubString(0, 25) + delimiter;
				
		// Write out the Member Number
		record += String.format(Locale.US, "%09d", provider.getNumber());
		
		// Write out the Address
		record += Misc.padRight(provider.getAddress(), 25) + delimiter;
		
		// Write out the City
		record += Misc.padRight(provider.getCity(), 14) + delimiter;
		
		// Write out the State
		record += Misc.padRight(provider.getState(), 2) + delimiter;
		
		// Write out the Zip Code
		record += String.format(Locale.US, "%09d", provider.getZip());
		
		// Enqueue the record
		enqueueRecord(record);
		
		// Loop through all of the Provided Service records
		while (providedSvc.isEmpty() == false) {
			
			// Increment our record counter
			recCount++;
			
			// Initialize the record
			record = "";
			ProvidedService ps = providedSvc.poll();
			
			// Service Date
			record += dtFormat_Dt.format(ps.getDateOfService()) + delimiter;
			
			// Received Date/Time
			record += dtFormat_DtTm.format(ps.getReceivedDate()) + delimiter;
			
			// Member Name
			record += Misc.padRight(ps.getMemberName(), 25) + delimiter;
			
			// Write out the Member Number
			record += String.format(Locale.US, "%09d", ps.getMemberNumber()) + delimiter;
			
			// Write out the Member Number
			record += String.format(Locale.US, "%06d", ps.getServiceCode()) + delimiter;
			
			// Write out the Member Number
			record += String.format(Locale.US, "%06d", ps.getServiceFee());
			totalFee += ps.getServiceFee();			
			
			// Enqueue the record for output
			enqueueRecord(record);
		}
		
		// Total Record
		record = "";
		record += formatter.format(totalFee) + delimiter;
		record += record += String.format(Locale.US, "%03d", recCount);
		enqueueRecord(record);
	}
}
