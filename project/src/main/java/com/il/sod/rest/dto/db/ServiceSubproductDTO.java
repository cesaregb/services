package com.il.sod.rest.dto.db;

public class ServiceSubproductDTO{
	private int idServiceSubproducts;
	private double price;
	private int quantity;
	private int idService;
	private SubproductDTO subproduct;

	public int getIdServiceSubproducts() {
		return idServiceSubproducts;
	}

	public void setIdServiceSubproducts(int idServiceSubproducts) {
		this.idServiceSubproducts = idServiceSubproducts;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public SubproductDTO getSubproduct() {
		return subproduct;
	}

	public void setSubproduct(SubproductDTO subproduct) {
		this.subproduct = subproduct;
	}

	public int getIdService() {
		return idService;
	}

	public void setIdService(int idService) {
		this.idService = idService;
	}
}