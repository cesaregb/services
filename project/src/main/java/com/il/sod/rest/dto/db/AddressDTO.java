package com.il.sod.rest.dto.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.il.sod.rest.dto.GenericDBDTO;

public class AddressDTO extends AddressGenericDTO implements GenericDBDTO{
	private int idAddress;
	private int idClient;
	private boolean prefered;
	
	public int getIdAddress() {
		return idAddress;
	}
	
	public void setIdAddress(int idAddress) {
		this.idAddress = idAddress;
	}
	
	@Override
	@JsonIgnore
	public Integer getParentId() {
		return this.idClient;
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
