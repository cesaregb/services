package com.il.sod.db.model.entities;

import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the SubproductType database table.
 * 
 */
@Entity
@NamedQuery(name="SubproductType.findAll", query="SELECT s FROM SubproductType s")
public class SubproductType implements IEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idSubproductType;

	private String description;

	private String name;

	//bi-directional many-to-one association to ServiceTypeSubproductType
	@OneToMany(mappedBy="subproductType")
	private Set<ServiceTypeSubproductType> serviceTypeSubproductTypes;

	//bi-directional many-to-one association to Subproduct
	@OneToMany(mappedBy="subproductType", fetch=FetchType.EAGER, cascade=CascadeType.ALL, orphanRemoval=true)
	private Set<Subproduct> subproducts;

	public SubproductType() {
	}

	public int getIdSubproductType() {
		return this.idSubproductType;
	}

	public void setIdSubproductType(int idSubproductType) {
		this.idSubproductType = idSubproductType;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<ServiceTypeSubproductType> getServiceTypeSubproductTypes() {
		return this.serviceTypeSubproductTypes;
	}

	public void setServiceTypeSubproductTypes(Set<ServiceTypeSubproductType> serviceTypeSubproductTypes) {
		this.serviceTypeSubproductTypes = serviceTypeSubproductTypes;
	}

	public ServiceTypeSubproductType addServiceTypeSubproductType(ServiceTypeSubproductType serviceTypeSubproductType) {
		getServiceTypeSubproductTypes().add(serviceTypeSubproductType);
		serviceTypeSubproductType.setSubproductType(this);

		return serviceTypeSubproductType;
	}

	public ServiceTypeSubproductType removeServiceTypeSubproductType(ServiceTypeSubproductType serviceTypeSubproductType) {
		getServiceTypeSubproductTypes().remove(serviceTypeSubproductType);
		serviceTypeSubproductType.setSubproductType(null);

		return serviceTypeSubproductType;
	}

	public Set<Subproduct> getSubproducts() {
		return this.subproducts;
	}

	public void setSubproducts(Set<Subproduct> subproducts) {
		this.subproducts = subproducts;
	}

	public Subproduct addSubproduct(Subproduct subproduct) {
		getSubproducts().add(subproduct);
		subproduct.setSubproductType(this);

		return subproduct;
	}

	public Subproduct removeSubproduct(Subproduct subproduct) {
		getSubproducts().remove(subproduct);
		subproduct.setSubproductType(null);

		return subproduct;
	}
	
	@Override
	public Integer getId() {
		return this.idSubproductType;
	}

	@Override
	public SubproductType setId(Integer id) {
		this.idSubproductType = id;
		return this;
	}

}