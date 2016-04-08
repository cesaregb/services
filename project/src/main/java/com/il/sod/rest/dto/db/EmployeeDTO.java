package com.il.sod.rest.dto.db;

import java.util.Date;

public class EmployeeDTO {
	private int idEmployee;
	private String lastname;
	private String name;
	private String password;
	private int status;
	private String username;
	private int idEmployeeType;
	private String email;
	
	private Date created;
	private Date updated;
	
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
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getIdEmployeeType() {
		return idEmployeeType;
	}
	public void setIdEmployeeType(int idEmployeeType) {
		this.idEmployeeType = idEmployeeType;
	}
}
