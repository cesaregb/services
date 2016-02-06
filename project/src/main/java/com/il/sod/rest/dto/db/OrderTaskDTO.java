package com.il.sod.rest.dto.db;

import java.util.Date;
import java.util.List;

public class OrderTaskDTO {	
	private int idOrderTask;
	private String comments;
	private Date time;
	private List<AssetTaskOrderDTO> assetTaskOrders;
	private List<EmployeeTaskOrderDTO> employeeTaskOrders;
	private OrderDTO order;
	private TaskDTO task;
	public int getIdOrderTask() {
		return idOrderTask;
	}
	public void setIdOrderTask(int idOrderTask) {
		this.idOrderTask = idOrderTask;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public List<AssetTaskOrderDTO> getAssetTaskOrders() {
		return assetTaskOrders;
	}
	public void setAssetTaskOrders(List<AssetTaskOrderDTO> assetTaskOrders) {
		this.assetTaskOrders = assetTaskOrders;
	}
	public List<EmployeeTaskOrderDTO> getEmployeeTaskOrders() {
		return employeeTaskOrders;
	}
	public void setEmployeeTaskOrders(List<EmployeeTaskOrderDTO> employeeTaskOrders) {
		this.employeeTaskOrders = employeeTaskOrders;
	}
	public OrderDTO getOrder() {
		return order;
	}
	public void setOrder(OrderDTO order) {
		this.order = order;
	}
	public TaskDTO getTask() {
		return task;
	}
	public void setTask(TaskDTO task) {
		this.task = task;
	}

}
