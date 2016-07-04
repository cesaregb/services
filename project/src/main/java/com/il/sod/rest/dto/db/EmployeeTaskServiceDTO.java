package com.il.sod.rest.dto.db;

public class EmployeeTaskServiceDTO {
	private int idEmployeeTaskService;
	private String comments;
	private int idEmployee;
	private int idServiceTask;
	
	public int getIdEmployeeTaskService() {
		return idEmployeeTaskService;
	}
	public void setIdEmployeeTaskService(int idEmployeeTaskService) {
		this.idEmployeeTaskService = idEmployeeTaskService;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public int getIdEmployee() {
		return idEmployee;
	}
	public void setIdEmployee(int idEmployee) {
		this.idEmployee = idEmployee;
	}
	public int getIdServiceTask() {
		return idServiceTask;
	}
	public void setIdServiceTask(int idServiceTask) {
		this.idServiceTask = idServiceTask;
	}
}
