package com.il.sod.rest.dto.db;

public class ServiceSpecDTO {
	private int idServiceTypeSpecs;
	private String comments;
	private ServiceDTO service;
	private SpecDTO spec;
	private String selectedValue;
	private float specPrice; 
	private float serviceIncrement;
	
	public int getIdServiceTypeSpecs() {
		return idServiceTypeSpecs;
	}
	public void setIdServiceTypeSpecs(int idServiceTypeSpecs) {
		this.idServiceTypeSpecs = idServiceTypeSpecs;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public ServiceDTO getService() {
		return service;
	}
	public void setService(ServiceDTO service) {
		this.service = service;
	}
	public SpecDTO getSpec() {
		return spec;
	}
	public void setSpec(SpecDTO spec) {
		this.spec = spec;
	}
	public String getSelectedValue() {
		return selectedValue;
	}
	public void setSelectedValue(String selectedValue) {
		this.selectedValue = selectedValue;
	}
	public float getSpecPrice() {
		return specPrice;
	}
	public void setSpecPrice(float specPrice) {
		this.specPrice = specPrice;
	}
	public float getServiceIncrement() {
		return serviceIncrement;
	}
	public void setServiceIncrement(float serviceIncrement) {
		this.serviceIncrement = serviceIncrement;
	}

}
