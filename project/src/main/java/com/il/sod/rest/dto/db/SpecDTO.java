package com.il.sod.rest.dto.db;

import java.util.List;

public class SpecDTO {
	private int idSpecs;
	private String description;
	private String name;
	private List<ServiceSpecDTO> serviceSpecs;
	private List<ServiceTypeSpecDTO> serviceTypeSpecs;
	private ProductTypeDTO productType;
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
	public List<ServiceSpecDTO> getServiceSpecs() {
		return serviceSpecs;
	}
	public void setServiceSpecs(List<ServiceSpecDTO> serviceSpecs) {
		this.serviceSpecs = serviceSpecs;
	}
	public List<ServiceTypeSpecDTO> getServiceTypeSpecs() {
		return serviceTypeSpecs;
	}
	public void setServiceTypeSpecs(List<ServiceTypeSpecDTO> serviceTypeSpecs) {
		this.serviceTypeSpecs = serviceTypeSpecs;
	}
	public ProductTypeDTO getProductType() {
		return productType;
	}
	public void setProductType(ProductTypeDTO productType) {
		this.productType = productType;
	}
}
