package app;

import java.util.*;

public class ProviderManager {
	private String fileName;
	private List<Provider> provList;
	private IReader reader;
	private IWriter writer;
	
	public ProviderManager(){
		reader = new FileReader<List<Provider>>(fileName);
		writer = new FileWriter<List<Provider>>(fileName);
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public List<Provider> getProvList() {
		return provList;
	}

	public void setProvList(List<Provider> provList) {
		this.provList = provList;
	}

	public IReader getReader() {
		return reader;
	}

	public void setReader(IReader reader) {
		this.reader = reader;
	}

	public IWriter getWriter() {
		return writer;
	}

	public void setWriter(IWriter writer) {
		this.writer = writer;
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
}