package com.il.sod.db.model.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ServiceTypeSpecs database table.
 * 
 */
@Entity
@Table(name="ServiceTypeSpecs")
@NamedQuery(name="ServiceTypeSpec.findAll", query="SELECT s FROM ServiceTypeSpec s")
public class ServiceTypeSpec implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idServiceTypeSpecs;

	private String comments;

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

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
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

}