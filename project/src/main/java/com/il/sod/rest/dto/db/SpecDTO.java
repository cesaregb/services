package com.il.sod.rest.dto.db;

import java.util.Set;

public class SpecDTO {
	private int idSpecs;
	private String description;
	private String name;
	private Set<SpecsValueDTO> specsValues;
	private int optional;
	private int max_qty;
	private int primary;
	
	public int getIdSpecs() {
		return idSpecs;
	}
	public void setIdSpecs(int idSpecs) {
		this.idSpecs = idSpecs;
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
	public Set<SpecsValueDTO> getSpecsValues() {
		return specsValues;
	}
	public void setSpecsValues(Set<SpecsValueDTO> specsValues) {
		this.specsValues = specsValues;
	}
	public int getOptional() {
		return optional;
	}
	public void setOptional(int optional) {
		this.optional = optional;
	}
	public int getMax_qty() {
		return max_qty;
	}
	public void setMax_qty(int max_qty) {
		this.max_qty = max_qty;
	}
	public int getPrimary() {
		return primary;
	}
	public void setPrimary(int primary) {
		this.primary = primary;
	}
}
