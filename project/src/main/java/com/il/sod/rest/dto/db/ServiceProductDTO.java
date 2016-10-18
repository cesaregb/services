package com.il.sod.rest.dto.db;

public class ServiceProductDTO{
	private int idServiceProducts;
	private double price;
	private int quantity;
	private int idService;
	private ProductDTO product;

	public int getIdServiceProducts() {
		return idServiceProducts;
	}

	public void setIdServiceProducts(int idServiceProducts) {
		this.idServiceProducts = idServiceProducts;
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

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}

	public int getIdService() {
		return idService;
	}

	public void setIdService(int idService) {
		this.idService = idService;
	}
}