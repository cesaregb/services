package com.il.sod.rest.dto.web;

public class InputSpecDTO {
	private int idSpec;
	private String value; 
	private int quantity;
	
	public int getIdSpec() {
		return idSpec;
	}
	public void setIdSpec(int idSpec) {
		this.idSpec = idSpec;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
