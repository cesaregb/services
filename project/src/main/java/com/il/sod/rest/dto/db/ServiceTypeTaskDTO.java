package com.il.sod.rest.dto.db;

public class ServiceTypeTaskDTO {
	private int idServiceTypeTask;
	private String comments;
	private ServiceTypeDTO serviceType;
	private TaskDTO task;
	public int getIdServiceTypeTask() {
		return idServiceTypeTask;
	}
	public void setIdServiceTypeTask(int idServiceTypeTask) {
		this.idServiceTypeTask = idServiceTypeTask;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public ServiceTypeDTO getServiceType() {
		return serviceType;
	}
	public void setServiceType(ServiceTypeDTO serviceType) {
		this.serviceType = serviceType;
	}
	public TaskDTO getTask() {
		return task;
	}
	public void setTask(TaskDTO task) {
		this.task = task;
	}
}
