package app;

import java.util.*;

public class ProviderManager {
	private static String filename = "ProvMgrFile.txt";
	private List<Provider> provList;
	private IReader<List<Provider>> reader;
	private IWriter<List<Provider>> writer;
	
	public ProviderManager(){
		provList = new ArrayList<Provider>();
		reader = new FileReader<List<Provider>>(filename);
		writer = new FileWriter<List<Provider>>(filename);
	}

	public List<Provider> getProvList() {
		return provList;
	}

	public void setProvList(List<Provider> provList) {
		this.provList = provList;
	}

	public void addProvider(Provider p){
		provList.add(p);
	}
	
	public Provider findProvider(long provNum){
		for (Provider prov: provList) {
			if (prov.getProviderNumber() == provNum) {
				return prov;
			}
		}
		return null;
	}
	
	public void load(){
		provList = (List<Provider>) reader.readData();
	}
	
	public void save(){
		writer.writeData(provList);
	}
	
	public void display(){
		for (Provider pvr: provList) {
			System.out.println(pvr.toString());
		}
	}
}