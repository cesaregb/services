package com.il.sod.rest.dto.db;

public class ServiceTypeSpecDTO {
	private int idServiceTypeSpecs;
	private int idServiceType;
	private SpecDTO spec;
	
	public int getIdServiceTypeSpecs() {
		return idServiceTypeSpecs;
	}
	public void setIdServiceTypeSpecs(int idServiceTypeSpecs) {
		this.idServiceTypeSpecs = idServiceTypeSpecs;
	}
	public SpecDTO getSpec() {
		return spec;
	}
	public void setSpec(SpecDTO spec) {
		this.spec = spec;
	}
	public int getIdServiceType() {
		return idServiceType;
	}
	public void setIdServiceType(int idServiceType) {
		this.idServiceType = idServiceType;
	}
}
