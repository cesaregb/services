package com.il.sod.rest.dto.db;

import java.util.List;

public class ServiceTypeDTO {
	private int idServiceType;
	private String description;
	private String name;
	private double price;
	private int time;
	private List<Integer> services;
	private List<Integer> serviceTypeSpecs;
	private List<Integer> serviceTypeTasks;
	private List<Integer> orderTypes;
	
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
	public List<Integer> getServiceTypeTasks() {
		return serviceTypeTasks;
	}
	public void setServiceTypeTasks(List<Integer> serviceTypeTasks) {
		this.serviceTypeTasks = serviceTypeTasks;
	}
	public List<Integer> getServices() {
		return services;
	}
	public void setServices(List<Integer> services) {
		this.services = services;
	}
	public List<Integer> getServiceTypeSpecs() {
		return serviceTypeSpecs;
	}
	public void setServiceTypeSpecs(List<Integer> serviceTypeSpecs) {
		this.serviceTypeSpecs = serviceTypeSpecs;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public List<Integer> getOrderTypes() {
		return orderTypes;
	}
	public void setOrderTypes(List<Integer> orderTypes) {
		this.orderTypes = orderTypes;
	}
}
