package com.il.sod.rest.dto.db;

public class EmployeeTaskOrderDTO {
	private int idEmployeeTaskOrder;
	private String comments;
	private Integer employee;
	private Integer orderTask;
	
	public int getIdEmployeeTaskOrder() {
		return idEmployeeTaskOrder;
	}
	public void setIdEmployeeTaskOrder(int idEmployeeTaskOrder) {
		this.idEmployeeTaskOrder = idEmployeeTaskOrder;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Integer getEmployee() {
		return employee;
	}
	public void setEmployee(Integer employee) {
		this.employee = employee;
	}
	public Integer getOrderTask() {
		return orderTask;
	}
	public void setOrderTask(Integer orderTask) {
		this.orderTask = orderTask;
	}

}
