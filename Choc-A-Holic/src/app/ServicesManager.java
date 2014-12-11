package app;

import java.util.*;

public class ServicesManager {
	private static String filename = "ServiceMgrFile.txt";
	private List<Service> serviceList;
	private IReader<List<Service>> reader;
	private IWriter<List<Service>> writer;
	
	public ServicesManager() {
		reader = new FileReader<List<Service>>(filename);
		writer = new FileWriter<List<Service>>(filename);
	}
	
	public void addService(Service svc) {
		//Adds a service to the service list
		serviceList.add(svc);
	}
	
	public Service findService(long servCode) {
		//Finds a service using a specified service code then returns it, otherwise returns null
		for (Service svc: serviceList) {
			if (svc.getServiceCode() == servCode) {
				return svc;
			}
		}
		return null;
	}
	
	public List<Service> getServiceList() {
		return serviceList;
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
