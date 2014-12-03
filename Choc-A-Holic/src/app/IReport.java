package app;

import java.io.IOException;

public interface IReport {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public void runReport(String filename) throws IOException;
	
	public void setMemeber(Member m);
	
	public void setProvider(Provider p);
	
	public void addProviderService(ProvidedService ps);

}
