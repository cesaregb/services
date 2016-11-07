package com.il.sod.db.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;


/**
 * The persistent class for the Supply database table.
 *
 */
@Entity
@NamedQuery(name="Supply.findAll", query="SELECT p FROM Supply p")
public class Supply implements IEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idSupply;

	private String description;

	private String name;

	private double price;
	
	private double serviceIncrement;

	private int status;

	//bi-directional many-to-one association to SupplyType
	@ManyToOne
	@JoinColumn(name="idSupplyType")
	@JsonBackReference
	private SupplyType supplyType;

	public Supply() {
	}

	public int getIdSupply() {
		return this.idSupply;
	}

	public void setIdSupply(int idSupply) {
		this.idSupply = idSupply;
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

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public SupplyType getSupplyType() {
		return this.supplyType;
	}

	public void setSupplyType(SupplyType supplyType) {
		this.supplyType = supplyType;
	}
	@Override
	public Integer getId() {
		return this.idSupply;
	}

	@Override
	public Supply setId(Integer id) {
		this.idSupply = id;
		return this;
	}

	public double getServiceIncrement() {
		return serviceIncrement;
	}

	public void setServiceIncrement(double serviceIncrement) {
		this.serviceIncrement = serviceIncrement;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
