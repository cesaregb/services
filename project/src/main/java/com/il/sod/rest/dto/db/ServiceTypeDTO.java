package com.il.sod.rest.dto.db;

import java.util.Date;
import java.util.List;

public class ServiceTypeDTO {
	private int idServiceType;
	private String description;
	private String name;
	private double price;
	private Date time;
	private List<ServiceDTO> services;
	private List<ServiceTypeSpecDTO> serviceTypeSpecs;
	private List<ServiceTypeTaskDTO> serviceTypeTasks;
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
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public List<ServiceDTO> getServices() {
		return services;
	}
	public void setServices(List<ServiceDTO> services) {
		this.services = services;
	}
	public List<ServiceTypeSpecDTO> getServiceTypeSpecs() {
		return serviceTypeSpecs;
	}
	public void setServiceTypeSpecs(List<ServiceTypeSpecDTO> serviceTypeSpecs) {
		this.serviceTypeSpecs = serviceTypeSpecs;
	}
	public List<ServiceTypeTaskDTO> getServiceTypeTasks() {
		return serviceTypeTasks;
	}
	public void setServiceTypeTasks(List<ServiceTypeTaskDTO> serviceTypeTasks) {
		this.serviceTypeTasks = serviceTypeTasks;
	}
}
