package app;

import java.io.*;
import java.text.*;
import java.util.*;

public class RptEFTRecords extends AReport {
	
	@Override
	protected void buildRecords() {				
		String output = "";
		
		// Allocate space for the queue
		allocateQueue(1);
		
		// Write out the current Date/Time
		output += dtFormat_DtTm.format(new Date()) + delimiter;
		
		// Write out the Date of the Service
		output += dtFormat_Dt.format(providedSvc.getServiceDate()) + delimiter;
				
		// Write out the Provider Number
		output += String.format(Locale.US, "%09d", provider.getProviderNumber()) + delimiter;
		
		// Write out the Member Number
		output += String.format(Locale.US, "%09d", provider.getMemberNumber()) + delimiter;
		
		// Write out the Member Number
		output += String.format(Locale.US, "%06d", providedSvc.getServiceCode()) + delimiter;
		
		// Write out the Comments
		output += providedSvc.getComments().PadRight(100).SubString(0, 100);
		
		// Queue up the record		
		enqueueRecord(output);
	}
}
