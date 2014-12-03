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
		record += provider.getName().PadRight(25).SubString(0, 25) + delimiter;
				
		// Write out the Member Number
		record += String.format(Locale.US, "%09d", provider.getNumber());
		
		// Write out the Address
		record += provider.getAddress().PadRight(25).SubString(0, 25) + delimiter;
		
		// Write out the City
		record += provider.getCity().PadRight(14).SubString(0, 14) + delimiter;
		
		// Write out the State
		record += provider.getState().PadRight(2).SubString(0, 2) + delimiter;
		
		// Write out the Zip Code
		record += String.format(Locale.US, "%09d", provider.getZipCode());
		
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
			record += dtFormat_Dt.format(ps.getServiceDate()) + delimiter;
			
			// Received Date/Time
			record += dtFormat_DtTm.format(ps.getReceivedDate()) + delimiter;
			
			// Member Name
			record += ps.getMemberName().PadRight(25).SubString(0,25) + delimiter;
			
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
