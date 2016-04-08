package com.il.sod.rest.dto.db;

import java.util.Date;
import java.util.Set;

public class OrderTaskDTO {	
	private int idOrderTask;
	private String comments;
	private Date time;
	private Set<AssetTaskOrderDTO> assetTaskOrders;
	private Set<EmployeeTaskOrderDTO> employeeTaskOrders;
	private int idOrder;
	private int idTask;
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

}
