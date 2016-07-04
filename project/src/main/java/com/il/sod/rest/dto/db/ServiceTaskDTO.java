package com.il.sod.rest.dto.db;

import java.util.Date;
import java.util.Set;

public class ServiceTaskDTO {
	private int idServiceTask;
	private String comments;
	private Set<AssetTaskServiceDTO> assetTaskServices;
	private Set<EmployeeTaskServiceDTO> employeeTaskServices;
	private int idService;
	private int idTask;
	private Date started;
	private Date ended;
	private int status;
	private int sortingOrder;
	private int time;
	
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
	public Date getStarted() {
		return started;
	}
	public void setStarted(Date started) {
		this.started = started;
	}
	public Date getEnded() {
		return ended;
	}
	public void setEnded(Date ended) {
		this.ended = ended;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getIdService() {
		return idService;
	}
	public void setIdService(int idService) {
		this.idService = idService;
	}
	public int getIdTask() {
		return idTask;
	}
	public void setIdTask(int idTask) {
		this.idTask = idTask;
	}
	public int getSortingOrder() {
		return sortingOrder;
	}
	public void setSortingOrder(int sortingOrder) {
		this.sortingOrder = sortingOrder;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
}
