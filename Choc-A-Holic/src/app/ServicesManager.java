package app;

import java.util.*;

public class ServicesManager {
	private String filename = "ServiceMgrFile.txt";	//what is its purpose?
	private List<Service> serviceList;
	private IReader<List<Service>> reader;
	
	public ServicesManager() {
		//Since the reader is specific for a data model are generics really necessary for it?
		//Also reader doesn't even seem to use the generic typing in its main method implementation
		reader = new FileReader<List<Service>>(filename);
		load();
	}
	
	public Service findService(long servCode) {
		for (Service svc: serviceList) {
			if (svc.getServiceCode() == servCode) {
				return svc;
			}
		}
		return null;
	}
	
	public void save() {
		//Nothing to save as there are no writers for this type
	}
	
	public void load() {
		//Reader returns an improper List<String> instead of List<Service>
		serviceList = reader.readData();
	}
	
}
