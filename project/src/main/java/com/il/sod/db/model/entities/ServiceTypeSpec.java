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
 * The persistent class for the ServiceTypeSpecs database table.
 */

@Entity
@Table(name="ServiceTypeSpecs")
@NamedQuery(name="ServiceTypeSpec.findAll", query="SELECT s FROM ServiceTypeSpec s")
public class ServiceTypeSpec implements IEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idServiceTypeSpecs;

	//bi-directional many-to-one association to ServiceType
	@ManyToOne
	@JoinColumn(name="idServiceType")
	private ServiceType serviceType;

	//bi-directional many-to-one association to Spec
	@ManyToOne
	@JoinColumn(name="idSpecs")
	private Spec spec;

	public ServiceTypeSpec() {
	}

	public int getIdServiceTypeSpecs() {
		return this.idServiceTypeSpecs;
	}

	public void setIdServiceTypeSpecs(int idServiceTypeSpecs) {
		this.idServiceTypeSpecs = idServiceTypeSpecs;
	}

	public ServiceType getServiceType() {
		return this.serviceType;
	}

	public void setServiceType(ServiceType serviceType) {
		this.serviceType = serviceType;
	}

	public Spec getSpec() {
		return this.spec;
	}

	public void setSpec(Spec spec) {
		this.spec = spec;
	}
	@Override
	public Integer getId() {
		return this.idServiceTypeSpecs;
	}

	@Override
	public ServiceTypeSpec setId(Integer id) {
		this.idServiceTypeSpecs = id;
		return this;
	}
}
