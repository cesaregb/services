package com.il.sod.rest.dto.web;

import java.util.List;

public class ServiceSubDTO {
	private int idServiceType;
	private double price;
	private String comments;
	private List<SpecSubDTO> specs;
	
	public int getIdServiceType() {
		return idServiceType;
	}
	public void setIdServiceType(int idServiceType) {
		this.idServiceType = idServiceType;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public List<SpecSubDTO> getSpecs() {
		return specs;
	}
	public void setSpecs(List<SpecSubDTO> specs) {
		this.specs = specs;
	} 
}
