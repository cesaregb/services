package com.il.sod.rest.dto.db;

public class SupplyDTO {
	private int idSupply;
	private String description;
	private String name;
	private double price;
	private double serviceIncrement;
	private int status;
	private int idSupplyType;
	private String supplyTypeName;
	
	public int getIdSupply() {
		return idSupply;
	}
	public void setIdSupply(int idSupply) {
		this.idSupply = idSupply;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public double getServiceIncrement() {
		return serviceIncrement;
	}
	public void setServiceIncrement(double serviceIncrement) {
		this.serviceIncrement = serviceIncrement;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getIdSupplyType() {
		return idSupplyType;
	}
	public void setIdSupplyType(int idSupplyType) {
		this.idSupplyType = idSupplyType;
	}
	public String getSupplyTypeName() {
		return supplyTypeName;
	}
	public void setSupplyTypeName(String supplyTypeName) {
		this.supplyTypeName = supplyTypeName;
	}

}
