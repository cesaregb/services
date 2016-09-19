package com.il.sod.rest.dto.db;

import java.util.List;
import java.util.Set;

public class ServiceTypeDTO {
	private int idServiceType;
	private String description;
	private String name;
	private double price;
	private int time;
	private Set<Integer> services;
	private Set<ServiceTypeSpecDTO> serviceTypeSpecs;
	private List<ServiceTypeTaskDTO> serviceTypeTasks;
	private int idServiceCategory;
	private String serviceTypeCategoryName;
	private Set<SubproductTypeDTO> subproductTypes;
	private boolean calculator;
	
	public int getIdServiceType() {
		return idServiceType;
	}
	public void setIdServiceType(int idServiceType) {
		this.idServiceType = idServiceType;
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Set<Integer> getServices() {
		return services;
	}
	public void setServices(Set<Integer> services) {
		this.services = services;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public Set<ServiceTypeSpecDTO> getServiceTypeSpecs() {
		return serviceTypeSpecs;
	}
	public void setServiceTypeSpecs(Set<ServiceTypeSpecDTO> serviceTypeSpecs) {
		this.serviceTypeSpecs = serviceTypeSpecs;
	}
	public int getIdServiceCategory() {
		return idServiceCategory;
	}
	public void setIdServiceCategory(int idServiceCategory) {
		this.idServiceCategory = idServiceCategory;
	}

	public List<ServiceTypeTaskDTO> getServiceTypeTasks() {
		return serviceTypeTasks;
	}

	public void setServiceTypeTasks(List<ServiceTypeTaskDTO> serviceTypeTasks) {
		this.serviceTypeTasks = serviceTypeTasks;
	}

	public String getServiceTypeCategoryName() {
		return serviceTypeCategoryName;
	}
	public void setServiceTypeCategoryName(String serviceTypeCategoryName) {
		this.serviceTypeCategoryName = serviceTypeCategoryName;
	}

	public Set<SubproductTypeDTO> getSubproductTypes() {
		return subproductTypes;
	}

	public void setSubproductTypes(Set<SubproductTypeDTO> subproductTypes) {
		this.subproductTypes = subproductTypes;
	}

	public boolean isCalculator() {
		return calculator;
	}

	public void setCalculator(boolean calculator) {
		this.calculator = calculator;
	}
}
