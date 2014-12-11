package app;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Queue;

public class RptMemberService extends AReport {

	@Override
	protected void buildRecords() {
		
		String record = "";		
		
		// Allocate space for the queue
		allocateQueue(1 + providedSvc.size());
		
		// Write out the Member Name
		record += Misc.padRight(member.getName(), 25) + delimiter;
				
		// Write out the Member Number
		record += String.format(Locale.US, "%09d", member.getNumber());
		
		// Write out the Address
		record += Misc.padRight(member.getAddress(), 25) + delimiter;
		
		// Write out the City
		record += Misc.padRight(member.getCity(), 14) + delimiter;
		
		// Write out the State
		record += Misc.padRight(member.getState(), 2) + delimiter;
		
		// Write out the Zip Code
		record += String.format(Locale.US, "%09d", member.getZipCode());
		
		// Enqueue the record
		enqueueRecord(record);
		
		// Loop through all of the Provided Service records
		while (providedSvc.isEmpty() == false) {
			
			// Initialize the record
			record = "";
			ProvidedService ps = providedSvc.poll();
			
			// Service Date
			record += dtFormat_Dt.format(ps.getDateOfService()) + delimiter;
			
			// Provider Name
			record += Misc.padRight(ps.getProviderName(), 25) + delimiter;
			
			// Service Name
			record += Misc.padRight(ps.getServiceName(), 20);
			
			// Enqueue the record for output
			enqueueRecord(record);
		}
	}
}
