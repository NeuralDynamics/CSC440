package app;

import java.io.IOException;

public interface IReport {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public void runReport(String filename) throws IOException;
	
	public void setMember(Member m);
	
	public void setProvider(Provider p);

	public void setMemberManager(MemberManager m);
	public void setServiceManager(ServiceManager m);
	public void setProviderManager(ProviderManager m);
	public void setProvidedServiceManager(ProvidedServiceManager m);
}
