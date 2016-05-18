package com.il.sod.rest.dto.db;

public class StopDTO{
	private int idStops;
	private int arrive;
	private int day;
	private String description;
	private String name;
	private int stopAction;
	private int time;
	private AddressRouteDTO addressRoute;
	private int idRoutes;
	
	public int getIdStops() {
		return idStops;
	}
	public void setIdStops(int idStops) {
		this.idStops = idStops;
	}
	public int getArrive() {
		return arrive;
	}
	public void setArrive(int arrive) {
		this.arrive = arrive;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStopAction() {
		return stopAction;
	}
	public void setStopAction(int stopAction) {
		this.stopAction = stopAction;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public AddressRouteDTO getAddressRoute() {
		return addressRoute;
	}
	public void setAddressRoute(AddressRouteDTO addressRoute) {
		this.addressRoute = addressRoute;
	}
	public int getIdRoutes() {
		return idRoutes;
	}
	public void setIdRoutes(int idRoutes) {
		this.idRoutes = idRoutes;
	}

}