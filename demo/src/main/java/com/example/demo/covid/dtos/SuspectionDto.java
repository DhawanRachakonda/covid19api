
package com.example.demo.covid.dtos;

/**
 * @author explorervarun
 *
 */
public class SuspectionDto {
	
	private String name;
	private String aadharNo;
	private String mobileNo;
	private String email;
	private String dateOfSusInf;
	private String suspectedLocationId;
	private Address address;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAadharNo() {
		return aadharNo;
	}
	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDateOfSusInf() {
		return dateOfSusInf;
	}
	public void setDateOfSusInf(String dateOfSusInf) {
		this.dateOfSusInf = dateOfSusInf;
	}
	public String getSuspectedLocationId() {
		return suspectedLocationId;
	}
	public void setSuspectedLocationId(String suspectedLocationId) {
		this.suspectedLocationId = suspectedLocationId;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	

}
