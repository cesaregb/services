package com.il.sod.rest.dto.db;

import java.util.List;

public class SpecDTO {
	private int idSpecs;
	private String description;
	private String name;
	private List<SpecsValueDTO> specsValues;
	
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
}
