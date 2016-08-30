package com.il.sod.rest.dto.db;

public class ServiceTypeSubproductTypeDTO{
	private int idServiceTypeSubproductTypes;
	private ServiceTypeDTO serviceType;
	private SubproductTypeDTO subproductType;

	public int getIdServiceTypeSubproductTypes() {
		return idServiceTypeSubproductTypes;
	}

	public void setIdServiceTypeSubproductTypes(int idServiceTypeSubproductTypes) {
		this.idServiceTypeSubproductTypes = idServiceTypeSubproductTypes;
	}

	public ServiceTypeDTO getServiceType() {
		return serviceType;
	}

	public void setServiceType(ServiceTypeDTO serviceType) {
		this.serviceType = serviceType;
	}

	public SubproductTypeDTO getSubproductType() {
		return subproductType;
	}

	public void setSubproductType(SubproductTypeDTO subproductType) {
		this.subproductType = subproductType;
	}
}