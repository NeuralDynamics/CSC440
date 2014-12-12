package app;

import java.io.Serializable;

public class Member implements Serializable {
	private static final long serialVersionUID = -5376054535207365087L;
	private long memberNumber;
	private String fileName;	//what is its purpose?
	private String name;
	private String address;
	private String city;
	private String state;
	private long zip;	//Shouldn't this be a String??
	private String phoneNumber;
	private String email;
	private boolean isSuspended = false;
	
	public Member(long memberNumber, String name, String address, String city, String state, long zip, String phoneNumber, String email) {
		this.memberNumber = memberNumber;
		this.name = name;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phoneNumber = phoneNumber;
		this.email = email;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String toString() {
		return memberNumber + "~" + name + "~" + address + "~" + city + "~" + state + "~" + zip + "~" + phoneNumber + "~" + email + "~" + isSuspended;
	}
	
	//Methods called from RptMemberService class
	
	public long getNumber() {
		return getMemberNumber();
	}
	
	public long getZipCode() {
		return getZip();
	}
	
	public boolean getIsSuspended() {
		return this.isSuspended;
	}
	
	public void setIsSuspended(boolean isSuspended) {
		this.isSuspended = isSuspended;
	}
}
