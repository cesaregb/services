package com.il.sod.db.model.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ServiceSpecs database table.
 * 
 */
@Entity
@Table(name="ServiceSpecs")
@NamedQuery(name="ServiceSpec.findAll", query="SELECT s FROM ServiceSpec s")
public class ServiceSpec implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idServiceTypeSpecs;

	private String comments;

	//bi-directional many-to-one association to Service
	@ManyToOne
	@JoinColumn(name="idService")
	private Service service;

	//bi-directional many-to-one association to Spec
	@ManyToOne
	@JoinColumn(name="idSpecs")
	private Spec spec;

	public ServiceSpec() {
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

	public Service getService() {
		return this.service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public Spec getSpec() {
		return this.spec;
	}

	public void setSpec(Spec spec) {
		this.spec = spec;
	}

}