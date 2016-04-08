package com.il.sod.rest.dto.db;

public class ProductDTO {
	private int idProduct;
	private String description;
	private String name;
	private double price;
	private double serviceIncrement;
	private int status;
	private int idProductType;
	
	public int getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
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
	public int getIdProductType() {
		return idProductType;
	}
	public void setIdProductType(int idProductType) {
		this.idProductType = idProductType;
	}

}
