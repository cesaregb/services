package com.il.sod.rest.dto.db;

public class OrderTypeTaskDTO {
	private int idOrderTypeTasks;
	private String description;
	private int time;
	private OrderTypeDTO orderType;
	private TaskDTO task;
	public int getIdOrderTypeTasks() {
		return idOrderTypeTasks;
	}
	public void setIdOrderTypeTasks(int idOrderTypeTasks) {
		this.idOrderTypeTasks = idOrderTypeTasks;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public OrderTypeDTO getOrderType() {
		return orderType;
	}
	public void setOrderType(OrderTypeDTO orderType) {
		this.orderType = orderType;
	}
	public TaskDTO getTask() {
		return task;
	}
	public void setTask(TaskDTO task) {
		this.task = task;
	}

}
