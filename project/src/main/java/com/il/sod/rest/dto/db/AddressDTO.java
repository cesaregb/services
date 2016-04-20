package com.il.sod.rest.dto.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.il.sod.rest.dto.GenericDBDTO;

public class AddressDTO implements GenericDBDTO{
	private int idAddress;
	private String address;
	private String address2;
	private String city;
	private String country;
	private String zipcode;
	private String state;
	private int idClient;
	private boolean prefered;
	
	
	public int getIdAddress() {
		return idAddress;
	}
	public void setIdAddress(int idAddress) {
		this.idAddress = idAddress;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	@Override
	@JsonIgnore
	public Integer getParentId() {
		return this.idClient;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public int getIdClient() {
		return idClient;
	}
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	
	@Override
	public String toString(){
		return this.getAddress() 
				+ " " + this.getAddress2()
				+ " " + this.getZipcode();
	}
	public boolean isPrefered() {
		return prefered;
	}
	public void setPrefered(boolean prefered) {
		this.prefered = prefered;
	}
}
