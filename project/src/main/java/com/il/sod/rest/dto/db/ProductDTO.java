package com.il.sod.rest.dto.db;

public class ProductDTO {
	private int idProduct;
	private String description;
	private String name;
	private float price;
	private int status;
	private ProductTypeDTO productType;
	
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
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public ProductTypeDTO getProductType() {
		return productType;
	}
	public void setProductType(ProductTypeDTO productType) {
		this.productType = productType;
	}

}
