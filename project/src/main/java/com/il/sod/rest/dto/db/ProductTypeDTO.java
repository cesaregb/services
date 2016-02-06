package com.il.sod.rest.dto.db;

import java.util.List;

public class ProductTypeDTO {
	private int idProductType;
	private String description;
	private String name;
	private List<ProductDTO> products;
	private List<SpecDTO> specs;
	public int getIdProductType() {
		return idProductType;
	}
	public void setIdProductType(int idProductType) {
		this.idProductType = idProductType;
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
	public List<ProductDTO> getProducts() {
		return products;
	}
	public void setProducts(List<ProductDTO> products) {
		this.products = products;
	}
	public List<SpecDTO> getSpecs() {
		return specs;
	}
	public void setSpecs(List<SpecDTO> specs) {
		this.specs = specs;
	}

}
