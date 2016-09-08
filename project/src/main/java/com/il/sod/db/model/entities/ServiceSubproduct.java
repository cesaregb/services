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
 * The persistent class for the ServiceSubproducts database table.
 * 
 */
@Entity
@Table(name="ServiceSubproducts")
@NamedQuery(name="ServiceSubproduct.findAll", query="SELECT s FROM ServiceSubproduct s")
public class ServiceSubproduct implements IEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idServiceSubproducts;

	private double price;

	private int quantity;

	//bi-directional many-to-one association to Service
	@ManyToOne
	@JoinColumn(name="idService")
	private Service service;

	//bi-directional many-to-one association to Subproduct
	@ManyToOne
	@JoinColumn(name="idSubproduct")
	private Subproduct subproduct;

	public ServiceSubproduct() {
	}

	public int getIdServiceSubproducts() {
		return this.idServiceSubproducts;
	}

	public void setIdServiceSubproducts(int idServiceSubproducts) {
		this.idServiceSubproducts = idServiceSubproducts;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Service getService() {
		return this.service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public Subproduct getSubproduct() {
		return this.subproduct;
	}

	public void setSubproduct(Subproduct subproduct) {
		this.subproduct = subproduct;
	}
	
	@Override
	public Integer getId() {
		return this.idServiceSubproducts;
	}

	@Override
	public ServiceSubproduct setId(Integer id) {
		this.idServiceSubproducts = id;
		return this;
	}

}