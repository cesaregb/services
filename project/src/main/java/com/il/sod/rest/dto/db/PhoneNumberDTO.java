package com.il.sod.rest.dto.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.il.sod.rest.dto.GenericDBDTO;

public class PhoneNumberDTO implements GenericDBDTO{
	private int idPhoneNumber;
	private String number;
	private int prefered;
	private int client;
	
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
	
	@Override
	@JsonIgnore
	public Integer getParentId() {
		return client;
	}
	public int getPrefered() {
		return prefered;
	}
	public void setPrefered(int prefered) {
		this.prefered = prefered;
	}
	public int getClient() {
		return client;
	}
	public void setClient(int client) {
		this.client = client;
	}
	
}
