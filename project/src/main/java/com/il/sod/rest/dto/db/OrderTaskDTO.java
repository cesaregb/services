package com.il.sod.rest.dto.db;

import java.util.Set;

public class OrderTaskDTO {	
	private int idOrderTask;
	private String comments;
	private int time;
	private Set<AssetTaskOrderDTO> assetTaskOrders;
	private Set<EmployeeTaskOrderDTO> employeeTaskOrders;
	private int idOrder;
	private int idTask;
	private int status;
	private int sortingOrder; 
	
	
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
	public Set<AssetTaskOrderDTO> getAssetTaskOrders() {
		return assetTaskOrders;
	}
	public void setAssetTaskOrders(Set<AssetTaskOrderDTO> assetTaskOrders) {
		this.assetTaskOrders = assetTaskOrders;
	}
	public Set<EmployeeTaskOrderDTO> getEmployeeTaskOrders() {
		return employeeTaskOrders;
	}
	public void setEmployeeTaskOrders(Set<EmployeeTaskOrderDTO> employeeTaskOrders) {
		this.employeeTaskOrders = employeeTaskOrders;
	}
	public int getIdOrder() {
		return idOrder;
	}
	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}
	public int getIdTask() {
		return idTask;
	}
	public void setIdTask(int idTask) {
		this.idTask = idTask;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getSortingOrder() {
		return sortingOrder;
	}
	public void setSortingOrder(int sortingOrder) {
		this.sortingOrder = sortingOrder;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}

}
