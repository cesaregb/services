package com.il.sod.db.model.entities;

import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

/**
 * The persistent class for the CalendarRoute database table.
 * 
 */
@Entity
@NamedQuery(name="CalendarRoute.findAll", query="SELECT c FROM CalendarRoute c")
public class CalendarRoute implements IEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idCalendarCalendarRoute;

	private int day;

	private Time time;

	//bi-directional many-to-one association to Route
	@ManyToOne
	@JoinColumn(name="idRoutes")
	private Route route;

	public CalendarRoute() {
	}

	public int getIdCalendarCalendarRoute() {
		return this.idCalendarCalendarRoute;
	}

	public void setIdCalendarCalendarRoute(int idCalendarCalendarRoute) {
		this.idCalendarCalendarRoute = idCalendarCalendarRoute;
	}

	public int getDay() {
		return this.day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public Time getTime() {
		return this.time;
	}

	public void setTime(Time time) {
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
		return this.idCalendarCalendarRoute;
	}

	@Override
	public CalendarRoute setId(Integer id) {
		this.idCalendarCalendarRoute = id;
		return this;
	}

}