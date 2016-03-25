package com.il.sod.rest.dto.web;

import java.util.List;
import java.util.Map;

import com.il.sod.rest.dto.KeyValue;

public class SpecSubDTO {
	private int idSpecs;
    private String name;
    private Map<String, List<KeyValue>> options;
    
	public int getIdSpecs() {
		return idSpecs;
	}
	public void setIdSpecs(int idSpecs) {
		this.idSpecs = idSpecs;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<String, List<KeyValue>> getOptions() {
		return options;
	}
	public void setOptions(Map<String, List<KeyValue>> options) {
		this.options = options;
	}
}
