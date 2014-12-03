package app;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public abstract class AReport implements IReport {

	String delimiter = "|";
	Member member = null;
	Provider provider = null;
	Queue<ProvidedService> providedSvc = null;
	Queue<String> recordQue = null;
	
	DateFormat dtFormat_DtTm = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
	DateFormat dtFormat_Dt = new SimpleDateFormat("MM-dd-yyyy");
	
	public AReport() {
		providedSvc = new ArrayDeque<ProvidedService>();
		recordQue = new ArrayDeque<String>();
	}
	
	@Override
	public void runReport(String filename) throws IOException {
		
		// Build the Records for the Report
		buildRecords();
		
		// Open the file for append
		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename, true)))) {
			while (recordQue.isEmpty() == false) {
				// Write the record
				out.println(recordQue.poll());
			}
						
			// Automatically Flushes & Closes the file
		} catch (IOException e) {
			throw new IOException("The file '" + filename + "' could not be opened.");
		}
		
		// Clear the Queue
		recordQue.clear();
	}	

	@Override
	public void setMemeber(Member m) {
		member = m;
	}

	@Override
	public void setProvider(Provider p) {
		provider = p;
	}

	@Override
	public void addProviderService(ProvidedService ps) {		
		providedSvc.add(ps);
	}
	
	protected abstract void buildRecords();
	
	protected void allocateQueue(int size) {
		recordQue = new ArrayDeque<String>(size);
	}
	
	protected void enqueueRecord(String record) {
		recordQue.add(record);
	}
}
