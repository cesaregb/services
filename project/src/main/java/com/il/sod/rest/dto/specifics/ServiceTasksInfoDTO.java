package com.il.sod.rest.dto.specifics;

import com.il.sod.rest.dto.db.ServiceTaskDTO;

import java.util.Set;

public class ServiceTasksInfoDTO {
	private int idService;
	private String name;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
