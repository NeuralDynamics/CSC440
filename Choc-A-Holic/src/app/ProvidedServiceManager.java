package app;

import java.util.*;

public class ProvidedServiceManager {
	private String fileName;
	private List<ProvidedService> provServList;
	private IReader<List<ProvidedService>> reader;
	private IWriter<List<ProvidedService>> writer;
	
	public ProvidedServiceManager(){
		reader = new FileReader<List<ProvidedService>>(fileName);
		writer = new FileWriter<List<ProvidedService>>(fileName);
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
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
		provServList = (List<ProvidedService>) reader.readData();
	}
	
	public void save(){
		writer.writeData(provServList);
	}
}