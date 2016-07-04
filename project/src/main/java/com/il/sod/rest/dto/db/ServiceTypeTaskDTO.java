package com.il.sod.rest.dto.db;

public class ServiceTypeTaskDTO {
	private int idServiceTypeTask;
	private int idServiceType;
	private int idTask;
	private int sortingOrder;
	private int time;
	
	public int getIdServiceTypeTask() {
		return idServiceTypeTask;
	}
	public void setIdServiceTypeTask(int idServiceTypeTask) {
		this.idServiceTypeTask = idServiceTypeTask;
	}
	public int getSortingOrder() {
		return sortingOrder;
	}
	public void setSortingOrder(int sortingOrder) {
		this.sortingOrder = sortingOrder;
	}
	public int getIdServiceType() {
		return idServiceType;
	}
	public void setIdServiceType(int idServiceType) {
		this.idServiceType = idServiceType;
	}
	public int getIdTask() {
		return idTask;
	}
	public void setIdTask(int idTask) {
		this.idTask = idTask;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
}
