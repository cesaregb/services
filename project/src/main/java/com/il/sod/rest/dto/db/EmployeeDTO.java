package com.il.sod.rest.dto.db;

import java.util.List;

public class EmployeeDTO {
	private int idEmployee;
	private String lastname;
	private String name;
	private String password;
	private int status;
	private String username;
	private EmployeeTypeDTO employeeType;
	private List<EmployeeTaskOrderDTO> employeeTaskOrders;
	private List<EmployeeTaskServiceDTO> employeeTaskServices;
	public int getIdEmployee() {
		return idEmployee;
	}
	public void setIdEmployee(int idEmployee) {
		this.idEmployee = idEmployee;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public EmployeeTypeDTO getEmployeeType() {
		return employeeType;
	}
	public void setEmployeeType(EmployeeTypeDTO employeeType) {
		this.employeeType = employeeType;
	}
	public List<EmployeeTaskOrderDTO> getEmployeeTaskOrders() {
		return employeeTaskOrders;
	}
	public void setEmployeeTaskOrders(List<EmployeeTaskOrderDTO> employeeTaskOrders) {
		this.employeeTaskOrders = employeeTaskOrders;
	}
	public List<EmployeeTaskServiceDTO> getEmployeeTaskServices() {
		return employeeTaskServices;
	}
	public void setEmployeeTaskServices(List<EmployeeTaskServiceDTO> employeeTaskServices) {
		this.employeeTaskServices = employeeTaskServices;
	}

}
