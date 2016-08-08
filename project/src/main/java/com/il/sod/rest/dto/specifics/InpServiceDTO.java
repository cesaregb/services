package com.il.sod.rest.dto.specifics;

import java.util.List;

public class InpServiceDTO {
	private int idServiceType;
	private String comments;
	private double price;
	private List<InputSpecDTO> specs;
	
	public int getIdServiceType() {
		return idServiceType;
	}
	public void setIdServiceType(int idServiceType) {
		this.idServiceType = idServiceType;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public List<InputSpecDTO> getSpecs() {
		return specs;
	}
	public void setSpecs(List<InputSpecDTO> specs) {
		this.specs = specs;
	}
}