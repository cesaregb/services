package com.il.sod.rest.dto.db;

import java.util.List;

public class OrderTemplateDTO {
	private int idOrderTemplate;
	private String description;
	private String name;
	private List<OrderDTO> orders;
	private List<OrderTemplateTaskDTO> orderTemplateTasks;
	public int getIdOrderTemplate() {
		return idOrderTemplate;
	}
	public void setIdOrderTemplate(int idOrderTemplate) {
		this.idOrderTemplate = idOrderTemplate;
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
	public List<OrderDTO> getOrders() {
		return orders;
	}
	public void setOrders(List<OrderDTO> orders) {
		this.orders = orders;
	}
	public List<OrderTemplateTaskDTO> getOrderTemplateTasks() {
		return orderTemplateTasks;
	}
	public void setOrderTemplateTasks(List<OrderTemplateTaskDTO> orderTemplateTasks) {
		this.orderTemplateTasks = orderTemplateTasks;
	}

}
