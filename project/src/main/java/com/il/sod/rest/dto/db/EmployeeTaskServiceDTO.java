package com.il.sod.rest.dto.db;

public class EmployeeTaskServiceDTO {
	private int idEmployeeTaskService;
	private String comments;
	private EmployeeDTO employee;
	private ServiceTaskDTO serviceTask;
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
	public EmployeeDTO getEmployee() {
		return employee;
	}
	public void setEmployee(EmployeeDTO employee) {
		this.employee = employee;
	}
	public ServiceTaskDTO getServiceTask() {
		return serviceTask;
	}
	public void setServiceTask(ServiceTaskDTO serviceTask) {
		this.serviceTask = serviceTask;
	}

}
