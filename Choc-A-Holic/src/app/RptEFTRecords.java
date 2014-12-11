package app;

import java.util.*;

public class RptEFTRecords extends AReport {
	
	@Override
	protected void buildRecords() {				
		String output = "";

		// Allocate space for the queue
		allocateQueue(1);
		// Loop through all of the Provided Service records
		while (providedSvc.isEmpty() == false) {
			
			// Initialize the record
			output = "";
			ProvidedService ps = providedSvc.poll();
		
			// Write out the current Date/Time
			output += dtFormat_DtTm.format(new Date()) + delimiter;
			
			// Write out the Date of the Service
			output += dtFormat_Dt.format(ps.getDateOfService()) + delimiter;
					
			// Write out the Provider Number
			output += String.format(Locale.US, "%09d", ps.getProviderNumber()) + delimiter;
			
			// Write out the Member Number
			output += String.format(Locale.US, "%09d", ps.getMemberNumber()) + delimiter;
			
			// Write out the Service Code
			output += String.format(Locale.US, "%06d", ps.getServiceCode()) + delimiter;
			
			// Write out the Comments
			output += Misc.padRight(ps.getComments(), 100);
		}
		
		// Queue up the record
		enqueueRecord(output);
	}
}
