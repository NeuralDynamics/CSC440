package app;

import java.util.List;
import java.util.Locale;

public class RptMemberService extends AReport {

	@Override
	protected void buildRecords() {
		String record = "";		
		
		// Get a listing of all provided services
		List<ProvidedService> provServList = _providedServiceMgr.findByMember(member.getMemberNumber());
		
		// Allocate space for the queue
		allocateQueue(provServList.size() + 1);		
		
		// Write out the Member Name
		record += member.getName() + delimiter;
				
		// Write out the Member Number
		record += String.format(Locale.US, "%09d", member.getNumber());
		
		// Write out the Address
		record += member.getAddress() + delimiter;
		
		// Write out the City
		record += member.getCity() + delimiter;
		
		// Write out the State
		record += member.getState() + delimiter;
		
		// Write out the Zip Code
		record += String.format(Locale.US, "%09d", member.getZipCode());
		
		// Enqueue the record
		enqueueRecord(record);
		
		// Loop through all of the Provided Service records
		for (ProvidedService ps: provServList) {
			
			// Initialize the record
			record = "";
			
			// Load the Provider
			Provider p = _providerMgr.findProvider(ps.getProviderNumber());
			
			// Load the Service
			Service s = _serviceMgr.findService(ps.getServiceCode());
			
			// Service Date
			record += dtFormat_Dt.format(ps.getDateOfService()) + delimiter;
			
			// Provider Name
			record += Misc.padRight(p.getName(), 25) + delimiter;
			
			// Service Name
			record += Misc.padRight(s.getServiceName(), 20);
			
			// Enqueue the record for output
			enqueueRecord(record);
		}
	}
}
