package app;

import java.util.*;
import java.io.Serializable;

public class ProvidedService implements Serializable {
	private static final long serialVersionUID = -6748274577370518763L;

	private Date dateOfService;
	private Date dateTimeReceived;
	private String memberName;
	private long memberNumber;
	private long serviceCode;
	private double serviceFee;
	private long providerNumber;
	private String providerName;
	
	public ProvidedService(Date dateOfService, Date dateTimeReceived, String memberName, long memberNumber, long serviceCode, double serviceFee, long providerNumber, String providerName){
		this.dateOfService = dateOfService;
		this.dateTimeReceived = dateTimeReceived;
		this.memberName = memberName;
		this.memberNumber = memberNumber;
		this.serviceCode = serviceCode;
		this.serviceFee = serviceFee;
		this.providerNumber = providerNumber;
		this.providerName = providerName;
	}
	
	public Date getDateOfService() {
		return dateOfService;
	}
	
	public void setDateOfService(Date dateOfService) {
		this.dateOfService = dateOfService;
	}
	
	public Date getDateTimeReceived() {
		return dateTimeReceived;
	}
	
	public void setDateTimeReceived(Date dateTimeReceived) {
		this.dateTimeReceived = dateTimeReceived;
	}
	
	public String getMemberName() {
		return memberName;
	}
	
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	
	public long getMemberNumber() {
		return memberNumber;
	}
	
	public void setMemberNumber(long memberNumber) {
		this.memberNumber = memberNumber;
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
	
	public long getProviderNumber() {
		return providerNumber;
	}
	
	public void setProviderNumber(long providerNumber) {
		this.providerNumber = providerNumber;
	}
	
	public String toString() {
		return dateOfService + "~" + dateTimeReceived + "~" + memberName + "~" + memberNumber + "~" + serviceCode + "~" + serviceFee + "~" + providerNumber + "~" + providerName;
	}
}