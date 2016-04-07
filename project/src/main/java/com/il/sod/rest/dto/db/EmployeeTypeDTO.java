package com.il.sod.rest.dto.db;

import java.util.Set;

public class EmployeeTypeDTO {
	private int idEmployeeType;
	private String description;
	private String name;
	private Set<EmployeeDTO> employees;
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
	public Set<EmployeeDTO> getEmployees() {
		return employees;
	}
	public void setEmployees(Set<EmployeeDTO> employees) {
		this.employees = employees;
	}
}
