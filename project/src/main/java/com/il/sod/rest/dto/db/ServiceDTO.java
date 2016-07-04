package com.il.sod.rest.dto.db;

import java.util.Date;
import java.util.Set;

public class ServiceDTO {
	private int idService;
	private String description;
	private String name;
	private double price;
	private int idServiceType;
	private Set<Integer> serviceSpecs;
	private Set<ServiceTaskDTO> serviceTasks;
	private Date created;
	private Date updated;
	private int time;
	private int idOrder;
	
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
	public Set<Integer> getServiceSpecs() {
		return serviceSpecs;
	}
	public void setServiceSpecs(Set<Integer> serviceSpecs) {
		this.serviceSpecs = serviceSpecs;
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
	public int getIdOrder() {
		return idOrder;
	}
	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}
	public int getIdServiceType() {
		return idServiceType;
	}
	public void setIdServiceType(int idServiceType) {
		this.idServiceType = idServiceType;
	}
	public Set<ServiceTaskDTO> getServiceTasks() {
		return serviceTasks;
	}
	public void setServiceTasks(Set<ServiceTaskDTO> serviceTasks) {
		this.serviceTasks = serviceTasks;
	}
}
