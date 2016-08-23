package com.il.sod.rest.dto.db;

public class OrderTypeTaskDTO {
	private int idOrderTypeTasks;
	private Integer idOrderType;
	private TaskDTO task;
	private int sortingOrder;
	private String taskTypeName;
	
	public int getIdOrderTypeTasks() {
		return idOrderTypeTasks;
	}
	public void setIdOrderTypeTasks(int idOrderTypeTasks) {
		this.idOrderTypeTasks = idOrderTypeTasks;
	}
	public int getSortingOrder() {
		return sortingOrder;
	}
	public void setSortingOrder(int sortingOrder) {
		this.sortingOrder = sortingOrder;
	}
	public TaskDTO getTask() {
		return task;
	}
	public void setTask(TaskDTO task) {
		this.task = task;
	}
	public String getTaskTypeName() {
		return taskTypeName;
	}
	public void setTaskTypeName(String taskTypeName) {
		this.taskTypeName = taskTypeName;
	}
	public Integer getIdOrderType() {
		return idOrderType;
	}
	public void setIdOrderType(Integer idOrderType) {
		this.idOrderType = idOrderType;
	}

}
