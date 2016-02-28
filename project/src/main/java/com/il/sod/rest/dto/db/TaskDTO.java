package com.il.sod.rest.dto.db;

public class TaskDTO {
	private int idTask;
	private String description;
	private String name;
	private Integer taskTypeId;
	
	public int getIdTask() {
		return idTask;
	}
	public void setIdTask(int idTask) {
		this.idTask = idTask;
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
	public Integer getTaskTypeId() {
		return taskTypeId;
	}
	public void setTaskTypeId(Integer taskTypeId) {
		this.taskTypeId = taskTypeId;
	}
	
	public TaskDTO setId(int id){
		this.idTask = id;
		return this;
	}
	public Integer getId(){
		return this.idTask;
	}
}
