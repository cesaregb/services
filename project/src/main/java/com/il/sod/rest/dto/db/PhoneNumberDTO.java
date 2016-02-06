package com.il.sod.rest.dto.db;

public class PhoneNumberDTO {
	private int idPhoneNumber;
	private String number;
	private ClientDTO client;
	public int getIdPhoneNumber() {
		return idPhoneNumber;
	}
	public void setIdPhoneNumber(int idPhoneNumber) {
		this.idPhoneNumber = idPhoneNumber;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public ClientDTO getClient() {
		return client;
	}
	public void setClient(ClientDTO client) {
		this.client = client;
	}
}
