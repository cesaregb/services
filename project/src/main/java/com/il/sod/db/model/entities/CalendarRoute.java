package com.il.sod.db.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * The persistent class for the CalendarRoute database table.
 * 
 */
@Entity
@NamedQuery(name="CalendarRoute.findAll", query="SELECT c FROM CalendarRoute c")
public class CalendarRoute implements IEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idCalendarRoute;

	private int day;

	private String time;

	//bi-directional many-to-one association to Route
	@ManyToOne
	@JoinColumn(name="idRoutes")
	@JsonBackReference
	private Route route;

	public CalendarRoute() {
	}

	public int getIdCalendarRoute() {
		return this.idCalendarRoute;
	}

	public void setIdCalendarRoute(int idCalendarRoute) {
		this.idCalendarRoute = idCalendarRoute;
	}

	public int getDay() {
		return this.day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public Route getRoute() {
		return this.route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	@Override
	public Integer getId() {
		return this.idCalendarRoute;
	}

	@Override
	public CalendarRoute setId(Integer id) {
		this.idCalendarRoute = id;
		return this;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}