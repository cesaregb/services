package com.il.sod.rest.dto.db;

import java.util.Date;
import java.util.Set;

public class ServiceDTO {
	private int idService;
	private String description;
	private String name;
	private double price;
	private double composedPrice;
	private double totalPrice;
	private int idServiceType;
	private int idServiceCategory;
	private Set<ServiceSpecDTO> serviceSpecs;
	private Set<ServiceTaskDTO> serviceTasks;
	private Date created;
	private Date updated;
	private int time;
	private int idOrder;
	private int nTasks;
	private int currentTask;
	private int deleted;
	private Set<ServiceSubproductDTO> serviceSubproducts;

	// UI Requirements. 
//	private Set<SpecDTO> specs;
	
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
	public int getnTasks() {
		return nTasks;
	}
	public void setnTasks(int nTasks) {
		this.nTasks = nTasks;
	}
	public int getCurrentTask() {
		return currentTask;
	}
	public void setCurrentTask(int currentTask) {
		this.currentTask = currentTask;
	}
	public Set<ServiceSpecDTO> getServiceSpecs() {
		return serviceSpecs;
	}
	public void setServiceSpecs(Set<ServiceSpecDTO> serviceSpecs) {
		this.serviceSpecs = serviceSpecs;
	}
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	public double getComposedPrice() {
		return composedPrice;
	}
	public void setComposedPrice(double composedPrice) {
		this.composedPrice = composedPrice;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public int getIdServiceCategory() {
		return idServiceCategory;
	}
	public void setIdServiceCategory(int idServiceCategory) {
		this.idServiceCategory = idServiceCategory;
	}

	public Set<ServiceSubproductDTO> getServiceSubproducts() {
		return serviceSubproducts;
	}

	public void setServiceSubproducts(Set<ServiceSubproductDTO> serviceSubproducts) {
		this.serviceSubproducts = serviceSubproducts;
	}
}
