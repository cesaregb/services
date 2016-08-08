package com.il.sod.rest.dto.specifics;

import java.util.Set;

import com.il.sod.rest.dto.db.ServiceTaskDTO;

public class ServiceTasksInfoDTO {
	private int idService;
	private Set<ServiceTaskDTO> serviceTasks;
	private int idOrder;
	
	public int getIdService() {
		return idService;
	}
	public void setIdService(int idService) {
		this.idService = idService;
	}
	public Set<ServiceTaskDTO> getServiceTasks() {
		return serviceTasks;
	}
	public void setServiceTasks(Set<ServiceTaskDTO> serviceTasks) {
		this.serviceTasks = serviceTasks;
	}
	public int getIdOrder() {
		return idOrder;
	}
	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}
	
}
