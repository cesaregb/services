package com.il.sod.rest.dto.db;

public class EmployeeTaskOrderDTO {
	private int idEmployeeTaskOrder;
	private String comments;
	private EmployeeDTO employee;
	private OrderTaskDTO orderTask;
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
	public EmployeeDTO getEmployee() {
		return employee;
	}
	public void setEmployee(EmployeeDTO employee) {
		this.employee = employee;
	}
	public OrderTaskDTO getOrderTask() {
		return orderTask;
	}
	public void setOrderTask(OrderTaskDTO orderTask) {
		this.orderTask = orderTask;
	}

}
