package com.il.sod.db.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the ServiceTypeSubproductTypes database table.
 * 
 */
@Entity
@Table(name="ServiceTypeSubproductTypes")
@NamedQuery(name="ServiceTypeSubproductType.findAll", query="SELECT s FROM ServiceTypeSubproductType s")
public class ServiceTypeSubproductType implements IEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idServiceTypeSubproductTypes;

	//bi-directional many-to-one association to ServiceType
	@ManyToOne
	@JoinColumn(name="idServiceType")
	private ServiceType serviceType;

	//bi-directional many-to-one association to SubproductType
	@ManyToOne
	@JoinColumn(name="idSubproductType")
	private SubproductType subproductType;

	public ServiceTypeSubproductType() {
	}

	public int getIdServiceTypeSubproductTypes() {
		return this.idServiceTypeSubproductTypes;
	}

	public void setIdServiceTypeSubproductTypes(int idServiceTypeSubproductTypes) {
		this.idServiceTypeSubproductTypes = idServiceTypeSubproductTypes;
	}

	public ServiceType getServiceType() {
		return this.serviceType;
	}

	public void setServiceType(ServiceType serviceType) {
		this.serviceType = serviceType;
	}

	public SubproductType getSubproductType() {
		return this.subproductType;
	}

	public void setSubproductType(SubproductType subproductType) {
		this.subproductType = subproductType;
	}
	
	@Override
	public Integer getId() {
		return this.idServiceTypeSubproductTypes;
	}

	@Override
	public ServiceTypeSubproductType setId(Integer id) {
		this.idServiceTypeSubproductTypes = id;
		return this;
	}

}