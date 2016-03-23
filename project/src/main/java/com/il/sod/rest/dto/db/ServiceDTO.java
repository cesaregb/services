package com.il.sod.rest.dto.db;

import java.util.Date;
import java.util.List;

public class ServiceDTO {
	private int idService;
	private String description;
	private String name;
	private double price;
	private Integer serviceType;
	private List<Integer> serviceSpecs;
	private List<Integer> serviceTasks;
	private Date created;
	private Date updated;
	private int time;
	
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
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public int getTime() {
		return time;
	}

}
