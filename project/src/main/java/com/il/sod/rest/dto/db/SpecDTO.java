package com.il.sod.rest.dto.db;

public class SpecDTO {
	private int idSpecs;
	private String description;
	private String name;
	private Integer productType;
	public int getIdSpecs() {
		return idSpecs;
	}
	public void setIdSpecs(int idSpecs) {
		this.idSpecs = idSpecs;
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
	public Integer getProductType() {
		return productType;
	}
	public void setProductType(Integer productType) {
		this.productType = productType;
	}
}
