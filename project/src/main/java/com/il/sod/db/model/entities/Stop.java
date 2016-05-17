package com.il.sod.db.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the Stops database table.
 * 
 */
@Entity
@Table(name="Stops")
@NamedQuery(name="Stop.findAll", query="SELECT s FROM Stop s")
public class Stop implements IEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idStops;

	private int arrive;

	private int day;

	private String description;

	private String name;

	@Column(name="stop_action")
	private int stopAction;

	private int time;

	//bi-directional many-to-one association to AddressRoute
	@ManyToOne
	@JoinColumn(name="idAddressRoute")
	private AddressRoute addressRoute;

	//bi-directional many-to-one association to Route
	@ManyToOne
	@JoinColumn(name="idRoutes")
	private Route route;

	public Stop() {
	}

	public int getIdStops() {
		return this.idStops;
	}

	public void setIdStops(int idStops) {
		this.idStops = idStops;
	}

	public int getArrive() {
		return this.arrive;
	}

	public void setArrive(int arrive) {
		this.arrive = arrive;
	}

	public int getDay() {
		return this.day;
	}

	public void setDay(int day) {
		this.day = day;
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

	public AddressRoute getAddressRoute() {
		return this.addressRoute;
	}

	public void setAddressRoute(AddressRoute addressRoute) {
		this.addressRoute = addressRoute;
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
}