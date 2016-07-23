package com.il.sod.rest.dto.web;

import java.util.Set;

import com.il.sod.rest.dto.db.OrderTaskDTO;

public class OrderTasksInfoDTO {
	private int idOrder;
	private Set<OrderTaskDTO> orderTasks;
	private Set<ServiceTasksInfoDTO> services;
	
	public int getIdOrder() {
		return idOrder;
	}
	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}
	public Set<OrderTaskDTO> getOrderTasks() {
		return orderTasks;
	}
	public void setOrderTasks(Set<OrderTaskDTO> orderTasks) {
		this.orderTasks = orderTasks;
	}
	public Set<ServiceTasksInfoDTO> getServices() {
		return services;
	}
	public void setServices(Set<ServiceTasksInfoDTO> services) {
		this.services = services;
	}
}
