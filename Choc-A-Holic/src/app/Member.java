package app;

public class Member {
	private long memberNumber;
	private String fileName;	//what is its purpose?
	private String name;
	private String address;
	private String city;
	private String state;
	private long zip;	//Shouldn't this be a String??
	private String phoneNumber;
	private String emailStatus;
	private String accountNumber;
	private String routingNumber;
	
	public Member(long memberNumber, String name, String address, String city, String state, long zip, String phoneNumber, String emailStatus, String accountNumber, String routingNumber) {
		this.memberNumber = memberNumber;
		this.name = name;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phoneNumber = phoneNumber;
		this.emailStatus = emailStatus;
		this.accountNumber = accountNumber;
		this.routingNumber = routingNumber;
	}

	public Member() {
	}

	public long getMemberNumber() {
		return memberNumber;
	}

	public void setMemberNumber(long memberNumber) {
		this.memberNumber = memberNumber;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public long getZip() {
		return zip;
	}

	public void setZip(long zip) {
		this.zip = zip;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmailStatus() {
		return emailStatus;
	}

	public void setEmailStatus(String emailStatus) {
		this.emailStatus = emailStatus;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getRoutingNumber() {
		return routingNumber;
	}

	public void setRoutingNumber(String routingNumber) {
		this.routingNumber = routingNumber;
	}
	
	public String toString() {
		return memberNumber + "~" + name + "~" + address + "~" + city + "~" + state + "~" + zip + "~" + phoneNumber + "~" + emailStatus + "~" + accountNumber + "~" + routingNumber;
	}
	
	//Methods called from RptMemberService class
	
	public long getNumber() {
		return getMemberNumber();
	}
	
	public long getZipCode() {
		return getZip();
	}

}
