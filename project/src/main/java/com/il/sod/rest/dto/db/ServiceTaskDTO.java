package com.il.sod.rest.dto.db;

import java.util.Set;

public class ServiceTaskDTO {
	private int idServiceTask;
	private String comments;
	private Set<AssetTaskServiceDTO> assetTaskServices;
	private Set<EmployeeTaskServiceDTO> employeeTaskServices;
	private ServiceDTO service;
	private TaskDTO task;
	public int getIdServiceTask() {
		return idServiceTask;
	}
	public void setIdServiceTask(int idServiceTask) {
		this.idServiceTask = idServiceTask;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Set<AssetTaskServiceDTO> getAssetTaskServices() {
		return assetTaskServices;
	}
	public void setAssetTaskServices(Set<AssetTaskServiceDTO> assetTaskServices) {
		this.assetTaskServices = assetTaskServices;
	}
	public Set<EmployeeTaskServiceDTO> getEmployeeTaskServices() {
		return employeeTaskServices;
	}
	public void setEmployeeTaskServices(Set<EmployeeTaskServiceDTO> employeeTaskServices) {
		this.employeeTaskServices = employeeTaskServices;
	}
	public ServiceDTO getService() {
		return service;
	}
	public void setService(ServiceDTO service) {
		this.service = service;
	}
	public TaskDTO getTask() {
		return task;
	}
	public void setTask(TaskDTO task) {
		this.task = task;
	}
}
