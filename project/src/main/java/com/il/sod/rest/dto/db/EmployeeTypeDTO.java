package com.il.sod.rest.dto.db;

import java.util.List;

public class EmployeeTypeDTO {
	private int idEmployeeType;
	private String description;
	private String name;
	private List<EmployeeDTO> employees;
	public int getIdEmployeeType() {
		return idEmployeeType;
	}
	public void setIdEmployeeType(int idEmployeeType) {
		this.idEmployeeType = idEmployeeType;
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
	public List<EmployeeDTO> getEmployees() {
		return employees;
	}
	public void setEmployees(List<EmployeeDTO> employees) {
		this.employees = employees;
	}
}
