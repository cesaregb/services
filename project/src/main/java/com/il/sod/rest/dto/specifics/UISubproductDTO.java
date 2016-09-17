package com.il.sod.rest.dto.specifics;

public class UISubproductDTO {
	private int idSubproduct;
	private int quantity;
    private float price;

	public int getIdSubproduct() {
		return idSubproduct;
	}

	public void setIdSubproduct(int idSubproduct) {
		this.idSubproduct = idSubproduct;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
}
