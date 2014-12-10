package app;

import java.util.*;

public class ServicesManager {
	private String filename;	//what is its purpose?
	private List<Service> serviceList;
	private IReader reader;
	
	public ServicesManager() {
		reader = new ServiceMgrFileReader<Service>();
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
		
	}
	
	public void load() {
		serviceList = reader.readData();
	}
	
}
