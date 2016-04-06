package com.il.sod.rest.dto.db;

import java.util.List;

public class SpecDTO {
	private int idSpecs;
	private String description;
	private String name;
	private List<SpecsValueDTO> specsValues;
	private int optional;
	private int max_qty;
	
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
	public List<SpecsValueDTO> getSpecsValues() {
		return specsValues;
	}
	public void setSpecsValues(List<SpecsValueDTO> specsValues) {
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
}
