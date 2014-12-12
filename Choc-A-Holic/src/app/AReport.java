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
	Queue<String> recordQue = null;
	
	DateFormat dtFormat_DtTm = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
	DateFormat dtFormat_Dt = new SimpleDateFormat("MM-dd-yyyy");
	
	MemberManager _memberMgr = null;
	ServiceManager _serviceMgr = null;
	ProviderManager _providerMgr = null;
	ProvidedServiceManager _providedServiceMgr = null;
	
	public AReport() {
		recordQue = new ArrayDeque<String>();
	}
	
	@Override
	public void setMemberManager(MemberManager m){
		_memberMgr = m;
	}
	@Override
	public void setServiceManager(ServiceManager m){
		_serviceMgr = m;
	}
	@Override
	public void setProviderManager(ProviderManager m){
		_providerMgr = m;
	}
	@Override
	public void setProvidedServiceManager(ProvidedServiceManager m) {
		_providedServiceMgr = m;
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
	public void setMember(Member m) {
		member = m;
	}

	@Override
	public void setProvider(Provider p) {
		provider = p;
	}
	
	protected abstract void buildRecords();
	
	protected void allocateQueue(int size) {
		recordQue = new ArrayDeque<String>(size);
	}
	
	protected void enqueueRecord(String record) {
		recordQue.add(record);
	}
}
