package com.il.sod.rest.dto.db;

import java.math.BigDecimal;
import java.util.Set;

public class AddressRouteDTO{

	private int idAddressRoute;
	private String address;
	private String address2;
	private String city;
	private String comments;
	private String country;
	private BigDecimal lat;
	private BigDecimal lng;
	private byte prefered;
	private String state;
	private String zipcode;
	private Set<Integer> stops;
	
	public int getIdAddressRoute() {
		return idAddressRoute;
	}
	public void setIdAddressRoute(int idAddressRoute) {
		this.idAddressRoute = idAddressRoute;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public BigDecimal getLat() {
		return lat;
	}
	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}
	public BigDecimal getLng() {
		return lng;
	}
	public void setLng(BigDecimal lng) {
		this.lng = lng;
	}
	public byte getPrefered() {
		return prefered;
	}
	public void setPrefered(byte prefered) {
		this.prefered = prefered;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public Set<Integer> getStops() {
		return stops;
	}
	public void setStops(Set<Integer> stops) {
		this.stops = stops;
	}
}