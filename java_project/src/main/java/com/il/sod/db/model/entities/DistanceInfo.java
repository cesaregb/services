package com.il.sod.db.model.entities;

import javax.persistence.*;


/**
 * The persistent class for the DistanceInfo database table.
 */
@Entity
@NamedQuery(name = "DistanceInfo.findAll", query = "SELECT d FROM DistanceInfo d")
public class DistanceInfo implements IEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idDistanceInfo;

	private int distance;

	private double price;

	private int source;

	//bi-directional many-to-one association to Store
	@ManyToOne
	@JoinColumn(name = "idStore")
	private Store store;

	public DistanceInfo() {
	}

	public int getIdDistanceInfo() {
		return this.idDistanceInfo;
	}

	public void setIdDistanceInfo(int idDistanceInfo) {
		this.idDistanceInfo = idDistanceInfo;
	}

	public int getDistance() {
		return this.distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getSource() {
		return this.source;
	}

	public void setSource(int source) {
		this.source = source;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	@Override
	public Integer getId() {
		return this.idDistanceInfo;
	}

	@Override
	public DistanceInfo setId(Integer id) {
		this.idDistanceInfo = id;
		return this;
	}

}