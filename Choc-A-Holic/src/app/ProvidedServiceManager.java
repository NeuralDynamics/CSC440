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
	
	public ProvidedService findByProvider(long provNum){
		for (ProvidedService provServ: provServList) {
			if (provServ.getProviderNumber() == provNum) {
				return provServ;
			}
		}
		return null;
	}
	
	public ProvidedService findByMember(long memNum){
		for (ProvidedService provServ: provServList) {
			if (provServ.getProviderNumber() == memNum) {
				return provServ;
			}
		}
		return null;
	}
	
	public void load(){
		try { 
			provServList = (List<ProvidedService>) reader.readData(); }
		catch (Exception Ex) {
		}
	}
	
	public void save(){
		writer.writeData(provServList);
	}
}