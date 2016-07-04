package com.il.sod.rest.dto.db;

public class OrderTypeTaskDTO {
	private int idOrderTypeTasks;
	private int time;
	private Integer orderType;
	private Integer task;
	private int sortingOrder;
	
	public int getIdOrderTypeTasks() {
		return idOrderTypeTasks;
	}
	public void setIdOrderTypeTasks(int idOrderTypeTasks) {
		this.idOrderTypeTasks = idOrderTypeTasks;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public Integer getOrderType() {
		return orderType;
	}
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	public Integer getTask() {
		return task;
	}
	public void setTask(Integer task) {
		this.task = task;
	}
	public int getSortingOrder() {
		return sortingOrder;
	}
	public void setSortingOrder(int sortingOrder) {
		this.sortingOrder = sortingOrder;
	}

}
