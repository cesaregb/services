package com.il.sod.rest.dto.db;

import java.sql.Time;

public class CalendarRouteDTO{
	
	private int idCalendarCalendarRoute;
	private int day;
	private Time time;
	private int idRoutes;
	
	public int getIdCalendarCalendarRoute() {
		return idCalendarCalendarRoute;
	}
	public void setIdCalendarCalendarRoute(int idCalendarCalendarRoute) {
		this.idCalendarCalendarRoute = idCalendarCalendarRoute;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	public int getIdRoutes() {
		return idRoutes;
	}
	public void setIdRoutes(int idRoutes) {
		this.idRoutes = idRoutes;
	}
}