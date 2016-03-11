package com.il.sod.rest.dto.db;

import java.util.List;

public class OrderTypeDTO {
	private int idOrderType;
	private String description;
	private String name;
	private List<Integer> orders;
	private List<OrderTypeTaskDTO> orderTypeTasks;
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
	public List<Integer> getOrders() {
		return orders;
	}
	public void setOrders(List<Integer> orders) {
		this.orders = orders;
	}
	public List<OrderTypeTaskDTO> getOrderTypeTasks() {
		return orderTypeTasks;
	}
	public void setOrderTypeTasks(List<OrderTypeTaskDTO> orderTypeTasks) {
		this.orderTypeTasks = orderTypeTasks;
	}

}
