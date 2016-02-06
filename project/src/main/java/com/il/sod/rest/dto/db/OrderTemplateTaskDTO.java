package com.il.sod.rest.dto.db;

public class OrderTemplateTaskDTO {
	private int idOrderTemplateTasks;
	private String description;
	private int time;
	private OrderTemplateDTO orderTemplate;
	private TaskDTO task;
	public int getIdOrderTemplateTasks() {
		return idOrderTemplateTasks;
	}
	public void setIdOrderTemplateTasks(int idOrderTemplateTasks) {
		this.idOrderTemplateTasks = idOrderTemplateTasks;
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
	public OrderTemplateDTO getOrderTemplate() {
		return orderTemplate;
	}
	public void setOrderTemplate(OrderTemplateDTO orderTemplate) {
		this.orderTemplate = orderTemplate;
	}
	public TaskDTO getTask() {
		return task;
	}
	public void setTask(TaskDTO task) {
		this.task = task;
	}

}
