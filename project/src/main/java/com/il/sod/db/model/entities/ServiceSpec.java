package com.il.sod.db.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;


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
	private int idServiceSpecs;

	private String comments;

	//bi-directional many-to-one association to Service
	@ManyToOne
	@JoinColumn(name="idService")
	@JsonBackReference
	private Service service;

	//bi-directional many-to-one association to Spec
	@ManyToOne
	@JoinColumn(name="idSpecs")
	@JsonBackReference
	private Spec spec;
	private int quantity;
	private float specPrice; 
	private float serviceIncrement; 
	
	private String selectedValue;

	public ServiceSpec() {
	}

	public int getIdServiceSpecs() {
		return this.idServiceSpecs;
	}

	public void setIdServiceSpecs(int idServiceSpecs) {
		this.idServiceSpecs = idServiceSpecs;
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
		return this.idServiceSpecs;
	}

	@Override
	public ServiceSpec setId(Integer id) {
		this.idServiceSpecs = id;
		return this;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getSelectedValue() {
		return selectedValue;
	}

	public void setSelectedValue(String selectedValue) {
		this.selectedValue = selectedValue;
	}

	public float getSpecPrice() {
		return specPrice;
	}

	public void setSpecPrice(float specPrice) {
		this.specPrice = specPrice;
	}

	public float getServiceIncrement() {
		return serviceIncrement;
	}

	public void setServiceIncrement(float serviceIncrement) {
		this.serviceIncrement = serviceIncrement;
	}

}
