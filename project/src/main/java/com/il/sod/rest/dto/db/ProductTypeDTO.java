package com.il.sod.rest.dto.db;

import java.util.Set;

public class ProductTypeDTO extends DeletableDTO{
	private int idProductType;
	private String description;
	private String name;
	private Set<ProductDTO> products;
	private Set<Integer> serviceTypes;

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

	public Set<ProductDTO> getProducts() {
		return products;
	}

	public void setProducts(Set<ProductDTO> products) {
		this.products = products;
	}

	public Set<Integer> getServiceTypes() {
		return serviceTypes;
	}

	public void setServiceTypes(Set<Integer> serviceTypes) {
		this.serviceTypes = serviceTypes;
	}
}