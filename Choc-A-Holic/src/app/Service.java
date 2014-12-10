package app;

public class Service {
	private String serviceName;
	private long serviceCode;
	private double serviceFee;
	
	public Service(String serviceName, long serviceCode, double serviceFee) {
		this.serviceName = serviceName;
		this.serviceCode = serviceCode;
		this.serviceFee = serviceFee;
	}
	
	public Service() {
	}
	
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public long getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(long serviceCode) {
		this.serviceCode = serviceCode;
	}
	public double getServiceFee() {
		return serviceFee;
	}
	public void setServiceFee(double serviceFee) {
		this.serviceFee = serviceFee;
	}
	
	
	
	
}
