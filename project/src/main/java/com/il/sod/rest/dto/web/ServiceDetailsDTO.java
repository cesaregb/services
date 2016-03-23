package com.il.sod.rest.dto.web;

import java.util.List;

import com.il.sod.rest.dto.db.TaskDTO;

public class ServiceDetailsDTO {
	private int idServiceType;
	private String description;
	private String name;
	private double price;
	private int time;
	private List<TaskDTO> tasks;
	private List<SpecInfoDTO> specs;
	
	public int getIdServiceType() {
		return idServiceType;
	}
	public void setIdServiceType(int idServiceType) {
		this.idServiceType = idServiceType;
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public List<TaskDTO> getTasks() {
		return tasks;
	}
	public void setTasks(List<TaskDTO> tasks) {
		this.tasks = tasks;
	}
	public List<SpecInfoDTO> getSpecs() {
		return specs;
	}
	public void setSpecs(List<SpecInfoDTO> specs) {
		this.specs = specs;
	}
}
