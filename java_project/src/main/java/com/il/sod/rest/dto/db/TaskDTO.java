package com.il.sod.rest.dto.db;

import com.google.common.base.MoreObjects;

public class TaskDTO extends DeletableDTO{
	private int idTask;
	private String description;
	private String name;
	private int idTaskType;
	private String taskTypeName;

	//for generica usage.
	private int typeTask = 0; // 0 = order; 1 = service
	private int idParent = 0; // idOrder or idService.

	public TaskDTO() {}

	public TaskDTO(int idTask) {
		this.idTask = idTask;
	}

	public TaskDTO(int idTask, int typeTask, int idParent) {
		this.idTask = idTask;
		this.typeTask = typeTask;
		this.idParent = idParent;
	}

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
	public int getIdTaskType() {
		return idTaskType;
	}
	public void setIdTaskType(int idTaskType) {
		this.idTaskType = idTaskType;
	}
	public String getTaskTypeName() {
		return taskTypeName;
	}
	public void setTaskTypeName(String taskTypeName) {
		this.taskTypeName = taskTypeName;
	}

	public int getTypeTask() {
		return typeTask;
	}

	public void setTypeTask(int typeTask) {
		this.typeTask = typeTask;
	}

	public int getIdParent() {
		return idParent;
	}

	public void setIdParent(int idParent) {
		this.idParent = idParent;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("idTask", idTask)
				.add("description", description)
				.add("name", name)
				.add("idTaskType", idTaskType)
				.add("taskTypeName", taskTypeName)
				.add("typeTask", typeTask)
				.add("idParent", idParent)
				.toString();
	}
}
