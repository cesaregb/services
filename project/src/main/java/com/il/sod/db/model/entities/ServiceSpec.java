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
 * The persistent class for the ServiceSpecs database table.
 *
 */
@Entity
@Table(name="ServiceSpecs")
@NamedQuery(name="ServiceSpec.findAll", query="SELECT s FROM ServiceSpec s")
public class ServiceSpec implements IEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
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
	@Override
	public Integer getId() {
		return this.idServiceTypeSpecs;
	}

	@Override
	public ServiceSpec setId(Integer id) {
		this.idServiceTypeSpecs = id;
		return this;
	}
}
