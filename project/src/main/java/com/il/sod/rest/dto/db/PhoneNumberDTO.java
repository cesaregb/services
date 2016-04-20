package com.il.sod.rest.dto.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.il.sod.rest.dto.GenericDBDTO;

public class PhoneNumberDTO implements GenericDBDTO{
	private int idPhoneNumber;
	private String number;
	private boolean prefered;
	private int idClient;
	
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
		return idClient;
	}
	public int getIdClient() {
		return idClient;
	}
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	public boolean isPrefered() {
		return prefered;
	}
	public void setPrefered(boolean prefered) {
		this.prefered = prefered;
	}
	
}
