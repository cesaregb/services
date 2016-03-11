package com.il.sod.rest.dto.db;

public class ServiceTypeSpecDTO {
	private int idServiceTypeSpecs;
	private String comments;
	private Integer serviceType;
	private Integer spec;
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
	public Integer getServiceType() {
		return serviceType;
	}
	public void setServiceType(Integer serviceType) {
		this.serviceType = serviceType;
	}
	public Integer getSpec() {
		return spec;
	}
	public void setSpec(Integer spec) {
		this.spec = spec;
	}
}
