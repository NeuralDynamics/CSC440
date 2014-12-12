package app;

import java.util.*;

public class ProvidedServiceManager {
	private static String filename = "ProvidedServMgrFile.txt";
	private List<ProvidedService> provServList;
	private IReader<List<ProvidedService>> reader;
	private IWriter<List<ProvidedService>> writer;
	
	public ProvidedServiceManager(){
		provServList = new ArrayList<ProvidedService>();
		reader = new FileReader<List<ProvidedService>>(filename);
		writer = new FileWriter<List<ProvidedService>>(filename);
	}

	public List<ProvidedService> getProvServList() {
		return provServList;
	}

	public void setProvServList(List<ProvidedService> provServList) {
		this.provServList = provServList;
	}
	
	public void addProvidedService(ProvidedService p){
		provServList.add(p);
	}
	
	public List<ProvidedService> findByProvider(long provNum){
		List<ProvidedService> lstFound = new ArrayList<ProvidedService>();
		
		for (ProvidedService provServ: provServList) {
			if (provServ.getProviderNumber() == provNum) {
				lstFound.add(provServ);
			}
		}
		return lstFound;
	}
	
	public List<ProvidedService> findByMember(long memNum){
		List<ProvidedService> lstFound = new ArrayList<ProvidedService>();
		
		for (ProvidedService provServ: provServList) {
			if (provServ.getProviderNumber() == memNum) {
				lstFound.add(provServ);
			}
		}
		return lstFound;
	}
	
	public List<ProvidedService> findByProviderAndMember(long provNum, long memNum){
		List<ProvidedService> lstFound = new ArrayList<ProvidedService>();
		
		for (ProvidedService provServ: provServList) {
			if (provServ.getProviderNumber() == provNum &&
				provServ.getMemberNumber() == memNum) {
				lstFound.add(provServ);
			}
		}
		return lstFound;
	}
	
	public void load(){
		//Tries to read member list from file, otherwise creates a new list
		provServList = reader.readData();
		if (provServList == null) provServList = new ArrayList<ProvidedService>();
	}
	
	public void save(){
		writer.writeData(provServList);
	}
}