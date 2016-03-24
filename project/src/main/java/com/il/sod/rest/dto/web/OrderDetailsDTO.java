package com.il.sod.rest.dto.web;

import java.util.List;

import com.il.sod.rest.dto.db.TaskDTO;

public class OrderDetailsDTO {
	private int idOrderType;
	private String description;
	private String name;
	private List<TaskDTO> tasks;
	private List<ServiceDetailsDTO> services;
	
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
	public List<TaskDTO> getTasks() {
		return tasks;
	}
	public void setTasks(List<TaskDTO> tasks) {
		this.tasks = tasks;
	}
	public List<ServiceDetailsDTO> getServices() {
		return services;
	}
	public void setServices(List<ServiceDetailsDTO> services) {
		this.services = services;
	}
	
}
