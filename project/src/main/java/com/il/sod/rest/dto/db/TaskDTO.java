package com.il.sod.rest.dto.db;

import java.util.List;

public class TaskDTO {
	private int idTask;
	private String description;
	private String name;
	private List<OrderTaskDTO> orderTasks;
	private List<OrderTemplateTaskDTO> orderTemplateTasks;
	private List<ServiceTaskDTO> serviceTasks;
	private List<ServiceTypeTaskDTO> serviceTypeTasks;
	private TaskTypeDTO taskType;
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
	public List<OrderTaskDTO> getOrderTasks() {
		return orderTasks;
	}
	public void setOrderTasks(List<OrderTaskDTO> orderTasks) {
		this.orderTasks = orderTasks;
	}
	public List<OrderTemplateTaskDTO> getOrderTemplateTasks() {
		return orderTemplateTasks;
	}
	public void setOrderTemplateTasks(List<OrderTemplateTaskDTO> orderTemplateTasks) {
		this.orderTemplateTasks = orderTemplateTasks;
	}
	public List<ServiceTaskDTO> getServiceTasks() {
		return serviceTasks;
	}
	public void setServiceTasks(List<ServiceTaskDTO> serviceTasks) {
		this.serviceTasks = serviceTasks;
	}
	public List<ServiceTypeTaskDTO> getServiceTypeTasks() {
		return serviceTypeTasks;
	}
	public void setServiceTypeTasks(List<ServiceTypeTaskDTO> serviceTypeTasks) {
		this.serviceTypeTasks = serviceTypeTasks;
	}
	public TaskTypeDTO getTaskType() {
		return taskType;
	}
	public void setTaskType(TaskTypeDTO taskType) {
		this.taskType = taskType;
	}
}
