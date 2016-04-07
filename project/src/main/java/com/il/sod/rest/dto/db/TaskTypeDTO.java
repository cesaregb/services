package com.il.sod.rest.dto.db;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class TaskTypeDTO {
	private int idTaskType;
	private String description;
	private String name;
	private Set<TaskDTO> tasks;
	
	public int getIdTaskType() {
		return idTaskType;
	}
	public void setIdTaskType(int idTaskType) {
		this.idTaskType = idTaskType;
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
	public Set<TaskDTO> getTasks() {
		return tasks;
	}
	public void setTasks(Set<TaskDTO> tasks) {
		this.tasks = tasks;
	}
}
