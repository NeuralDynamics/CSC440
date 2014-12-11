package app;

import java.util.*;

public class ServicesManager {
	private  static String filename = "ServiceMgrFile.txt";
	private List<Service> serviceList;
	private IReader<List<Service>> reader;
	private IWriter<List<Service>> writer;
	
	public ServicesManager() {
		reader = new FileReader<List<Service>>(filename);
		load();
	}
	
	public void addService(Service svc) {
		serviceList.add(svc);
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
		//Saves service list to file
		writer.writeData(serviceList);
	}
	
	public void load() {
		//Tries to read service list from file, otherwise creates a new list
		serviceList = reader.readData();
		if (serviceList == null) serviceList = new ArrayList<Service>();
	}
	
}
