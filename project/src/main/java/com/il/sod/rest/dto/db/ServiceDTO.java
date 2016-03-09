package com.il.sod.rest.dto.db;

import java.util.Date;
import java.util.List;

public class ServiceDTO {
	private int idService;
	private String description;
	private String name;
	private double price;
	private Date time;
	private Integer serviceType;
	private List<Integer> serviceSpecs;
	private List<Integer> serviceTasks;
	public int getIdService() {
		return idService;
	}
	public void setIdService(int idService) {
		this.idService = idService;
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
	public List<Integer> getServiceSpecs() {
		return serviceSpecs;
	}
	public void setServiceSpecs(List<Integer> serviceSpecs) {
		this.serviceSpecs = serviceSpecs;
	}
	public List<Integer> getServiceTasks() {
		return serviceTasks;
	}
	public void setServiceTasks(List<Integer> serviceTasks) {
		this.serviceTasks = serviceTasks;
	}
	public Integer getServiceType() {
		return serviceType;
	}
	public void setServiceType(Integer serviceType) {
		this.serviceType = serviceType;
	}

}
