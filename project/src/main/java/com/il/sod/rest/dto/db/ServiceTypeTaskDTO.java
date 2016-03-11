package com.il.sod.rest.dto.db;

public class ServiceTypeTaskDTO {
	private int idServiceTypeTask;
	private String comments;
	private Integer serviceType;
	private Integer task;
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
	public Integer getServiceType() {
		return serviceType;
	}
	public void setServiceType(Integer serviceType) {
		this.serviceType = serviceType;
	}
	public Integer getTask() {
		return task;
	}
	public void setTask(Integer task) {
		this.task = task;
	}
}
