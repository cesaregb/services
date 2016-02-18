package com.il.sod.rest.dto.db;

import java.util.List;

import com.il.sod.db.model.entities.AccessKey;
import com.il.sod.db.model.entities.Address;
import com.il.sod.db.model.entities.PhoneNumber;

public class ClientDTO {
	private int idClient;
	private String email;
	private String lasName;
	private String name;
	private String password;
	private String phoneNumber;
	private String twitter;
	private List<Integer> orders;
	private List<AccessKey> accessKeys;
	private List<Address> addresses;
	private List<PhoneNumber> phoneNumbers;
	
	
	public int getIdClient() {
		return idClient;
	}
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLasName() {
		return lasName;
	}
	public void setLasName(String lasName) {
		this.lasName = lasName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getTwitter() {
		return twitter;
	}
	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}
	public List<Integer> getOrders() {
		return orders;
	}
	public void setOrders(List<Integer> orders) {
		this.orders = orders;
	}
	public List<AccessKey> getAccessKeys() {
		return accessKeys;
	}
	public void setAccessKeys(List<AccessKey> accessKeys) {
		this.accessKeys = accessKeys;
	}
	public List<Address> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
	public List<PhoneNumber> getPhoneNumbers() {
		return phoneNumbers;
	}
	public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

}
