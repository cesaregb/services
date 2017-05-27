package com.il.sod.rest.dto.parse;

import java.util.List;

public class UIServiceDTO {
	private int idServiceType;
	private String comments;
	private double price;
	private double specsPrice;
	private double productsPrice;
	private double totalPrice;
	private List<UISpecDTO> specs;
	private List<UIProductDTO> products;
	
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
	public List<UISpecDTO> getSpecs() {
		return specs;
	}
	public void setSpecs(List<UISpecDTO> specs) {
		this.specs = specs;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public double getSpecsPrice() {
		return specsPrice;
	}

	public void setSpecsPrice(double specsPrice) {
		this.specsPrice = specsPrice;
	}

	public double getProductsPrice() {
		return productsPrice;
	}

	public void setProductsPrice(double productsPrice) {
		this.productsPrice = productsPrice;
	}

	public List<UIProductDTO> getProducts() {
		return products;
	}

	public void setProducts(List<UIProductDTO> products) {
		this.products = products;
	}
}