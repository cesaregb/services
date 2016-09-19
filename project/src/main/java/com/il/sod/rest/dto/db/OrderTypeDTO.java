package com.il.sod.rest.dto.db;

import java.util.List;
import java.util.Set;

public class OrderTypeDTO {
	private int idOrderType;
	private String description;
	private String name;
	private Set<Integer> orders;
	private List<OrderTypeTaskDTO> orderTypeTasks;
	private int transportInfo;
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

	public List<OrderTypeTaskDTO> getOrderTypeTasks() {
		return orderTypeTasks;
	}

	public void setOrderTypeTasks(List<OrderTypeTaskDTO> orderTypeTasks) {
		this.orderTypeTasks = orderTypeTasks;
	}

	public int getTransportInfo() {
		return transportInfo;
	}
	public void setTransportInfo(int transportInfo) {
		this.transportInfo = transportInfo;
	}

}
