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
 * The persistent class for the Stops database table.
 * 
 */
@Entity
@Table(name = "Stops")
@NamedQuery(name = "Stop.findAll", query = "SELECT s FROM Stop s")
public class Stop implements IEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idStops;

	private String arriveAt;

	private String description;

	private String name;

	private int stopAction;

	private int time;
	
	private int idAddress;

	// bi-directional many-to-one association to Route
	@ManyToOne
	@JoinColumn(name = "idRoutes")
	@JsonBackReference
	private Route route;
	
	private int type;
	
	public Stop() {
	}

	public int getIdStops() {
		return this.idStops;
	}

	public void setIdStops(int idStops) {
		this.idStops = idStops;
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

	public int getStopAction() {
		return this.stopAction;
	}

	public void setStopAction(int stopAction) {
		this.stopAction = stopAction;
	}

	public int getTime() {
		return this.time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public Route getRoute() {
		return this.route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	@Override
	public Integer getId() {
		return this.idStops;
	}

	@Override
	public Stop setId(Integer id) {
		this.idStops = id;
		return this;
	}

	public String getArriveAt() {
		return arriveAt;
	}

	public void setArriveAt(String arriveAt) {
		this.arriveAt = arriveAt;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getIdAddress() {
		return idAddress;
	}

	public void setIdAddress(int idAddress) {
		this.idAddress = idAddress;
	}
}