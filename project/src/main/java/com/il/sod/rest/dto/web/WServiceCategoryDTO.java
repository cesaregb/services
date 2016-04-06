package com.il.sod.rest.dto.web;

import java.util.List;

public class WServiceCategoryDTO {
	private int idServiceCategory;
	private String description;
	private String name;
	private List<WServiceTypeDTO> serviceTypes;

	public WServiceCategoryDTO() {
	}

	public int getIdServiceCategory() {
		return this.idServiceCategory;
	}

	public void setIdServiceCategory(int idServiceCategory) {
		this.idServiceCategory = idServiceCategory;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<WServiceTypeDTO> getServiceTypes() {
		return this.serviceTypes;
	}

	public void setServiceTypes(List<WServiceTypeDTO> serviceTypes) {
		this.serviceTypes = serviceTypes;
	}
}