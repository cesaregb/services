package com.il.sod.rest.dto.specifics;

import com.il.sod.rest.dto.db.SubproductDTO;

import java.util.Set;

public class WServiceTypeDTO {
	private int idServiceType;
	private String description;
	private String name;
	private double price;
	private int time;
	private Set<WSpecDTO> specs;
	private Set<SubproductDTO> subproducts;

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
	public Set<WSpecDTO> getSpecs() {
		return specs;
	}
	public void setSpecs(Set<WSpecDTO> specs) {
		this.specs = specs;
	}

	public Set<SubproductDTO> getSubproducts() {
		return subproducts;
	}

	public void setSubproducts(Set<SubproductDTO> subproducts) {
		this.subproducts = subproducts;
	}
}
