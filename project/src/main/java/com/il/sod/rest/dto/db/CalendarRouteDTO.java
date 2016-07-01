package com.il.sod.rest.dto.db;

public class CalendarRouteDTO{
	
	private int idCalendarRoute;
	private int day;
	private String time;
	private int idRoutes;
	private int action;
	
	public int getIdCalendarRoute() {
		return idCalendarRoute;
	}
	public void setIdCalendarRoute(int idCalendarRoute) {
		this.idCalendarRoute = idCalendarRoute;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getIdRoutes() {
		return idRoutes;
	}
	public void setIdRoutes(int idRoutes) {
		this.idRoutes = idRoutes;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getAction() {
		return action;
	}
	public void setAction(int action) {
		this.action = action;
	}
}