package com.il.sod.rest.dto.db;

import java.util.List;

public class ServiceTaskDTO {
	private int idServiceTask;
	private String comments;
	private List<AssetTaskServiceDTO> assetTaskServices;
	private List<EmployeeTaskServiceDTO> employeeTaskServices;
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
	public List<AssetTaskServiceDTO> getAssetTaskServices() {
		return assetTaskServices;
	}
	public void setAssetTaskServices(List<AssetTaskServiceDTO> assetTaskServices) {
		this.assetTaskServices = assetTaskServices;
	}
	public List<EmployeeTaskServiceDTO> getEmployeeTaskServices() {
		return employeeTaskServices;
	}
	public void setEmployeeTaskServices(List<EmployeeTaskServiceDTO> employeeTaskServices) {
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
