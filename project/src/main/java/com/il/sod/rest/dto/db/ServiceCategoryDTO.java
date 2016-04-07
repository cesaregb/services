package com.il.sod.rest.dto.db;

import java.util.Set;

public class ServiceCategoryDTO {
	private int idServiceCategory;
	private String description;
	private String name;
	private Set<ServiceTypeDTO> serviceTypes;

	public ServiceCategoryDTO() {
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

	public Set<ServiceTypeDTO> getServiceTypes() {
		return this.serviceTypes;
	}

	public void setServiceTypes(Set<ServiceTypeDTO> serviceTypes) {
		this.serviceTypes = serviceTypes;
	}
}