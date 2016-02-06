package com.il.sod.rest.dto.db;

public class ServiceSpecDTO {
	private int idServiceTypeSpecs;
	private String comments;
	private ServiceDTO service;
	private SpecDTO spec;
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

}
