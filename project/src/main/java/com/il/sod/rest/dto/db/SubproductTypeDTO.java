package com.il.sod.rest.dto.db;

import java.util.Set;

public class SubproductTypeDTO extends DeletableDTO{
	private int idSubproductType;
	private String description;
	private String name;
	private Set<SubproductDTO> subproducts;
	private Set<Integer> serviceTypes;

	public int getIdSubproductType() {
		return idSubproductType;
	}

	public void setIdSubproductType(int idSubproductType) {
		this.idSubproductType = idSubproductType;
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

	public Set<SubproductDTO> getSubproducts() {
		return subproducts;
	}

	public void setSubproducts(Set<SubproductDTO> subproducts) {
		this.subproducts = subproducts;
	}

	public Set<Integer> getServiceTypes() {
		return serviceTypes;
	}

	public void setServiceTypes(Set<Integer> serviceTypes) {
		this.serviceTypes = serviceTypes;
	}
}