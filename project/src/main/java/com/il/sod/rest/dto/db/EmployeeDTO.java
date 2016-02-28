package com.il.sod.rest.dto.db;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class EmployeeDTO {
	private int idEmployee;
	private String lastname;
	private String name;
	private String password;
	private int status;
	private String username;
	private int employeeTypeId;
	
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
	public int getEmployeeTypeId() {
		return employeeTypeId;
	}
	public void setEmployeeTypeId(int employeeTypeId) {
		this.employeeTypeId = employeeTypeId;
	}
	
	public EmployeeDTO setId(int id){
		this.idEmployee = id;
		return this;
	}
	@JsonIgnore
	public Integer getId(){
		return this.idEmployee;
	}
}
