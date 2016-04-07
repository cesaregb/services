package com.il.sod.rest.dto.db;

import java.util.Set;

public class OrderTypeDTO {
	private int idOrderType;
	private String description;
	private String name;
	private Set<Integer> orders;
	private Set<OrderTypeTaskDTO> orderTypeTasks;
	private Set<ServiceTypeDTO> serviceTypes;
	public int getIdOrderType() {
		return idOrderType;
	}
	public void setIdOrderType(int idOrderType) {
		this.idOrderType = idOrderType;
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
	public Set<Integer> getOrders() {
		return orders;
	}
	public void setOrders(Set<Integer> orders) {
		this.orders = orders;
	}
	public Set<OrderTypeTaskDTO> getOrderTypeTasks() {
		return orderTypeTasks;
	}
	public void setOrderTypeTasks(Set<OrderTypeTaskDTO> orderTypeTasks) {
		this.orderTypeTasks = orderTypeTasks;
	}
	public Set<ServiceTypeDTO> getServiceTypes() {
		return serviceTypes;
	}
	public void setServiceTypes(Set<ServiceTypeDTO> serviceTypes) {
		this.serviceTypes = serviceTypes;
	}

}
