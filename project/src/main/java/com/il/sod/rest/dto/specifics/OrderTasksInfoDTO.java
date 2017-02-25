package com.il.sod.rest.dto.specifics;

import com.il.sod.rest.dto.db.OrderTaskDTO;

import java.util.Set;

public class OrderTasksInfoDTO {

	private int idOrder;
	private int idClient;
	private String clientName;
	private String orderTypeName;
	private double completed;
	private Set<OrderTaskDTO> orderTasks;
	private Set<ServiceTasksInfoDTO> services;

	public int getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getOrderTypeName() {
		return orderTypeName;
	}

	public void setOrderTypeName(String orderTypeName) {
		this.orderTypeName = orderTypeName;
	}

	public double getCompleted() {
		return completed;
	}

	public void setCompleted(double completed) {
		this.completed = completed;
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
