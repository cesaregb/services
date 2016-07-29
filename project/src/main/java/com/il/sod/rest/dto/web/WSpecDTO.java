package com.il.sod.rest.dto.web;

import java.util.List;
import java.util.Map;

import com.il.sod.rest.dto.KeyValue;

public class WSpecDTO {
	private int idSpecs;
	private String description;
	private String name;
	private Map<Integer, List<KeyValue<Integer, String>>> options;
	private int optional;
	private int max_qty;
	private double price;
	private boolean primarySpec;
	
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
	public Map<Integer, List<KeyValue<Integer, String>>> getOptions() {
		return options;
	}
	public void setOptions(Map<Integer, List<KeyValue<Integer, String>>> options) {
		this.options = options;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public boolean isPrimarySpec() {
		return primarySpec;
	}
	public void setPrimarySpec(boolean primarySpec) {
		this.primarySpec = primarySpec;
	}
}
