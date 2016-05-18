package com.il.sod.db.model.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The persistent class for the AddressRoutes database table.
 * 
 */
@Entity
@Table(name="AddressRoutes")
@NamedQuery(name="AddressRoute.findAll", query="SELECT a FROM AddressRoute a")
public class AddressRoute implements IEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
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

	@OneToOne(mappedBy="addressRoute", fetch=FetchType.EAGER)
	private Stop stop;

	public AddressRoute() {
	}

	public int getIdAddressRoute() {
		return this.idAddressRoute;
	}

	public void setIdAddressRoute(int idAddressRoute) {
		this.idAddressRoute = idAddressRoute;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress2() {
		return this.address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public BigDecimal getLat() {
		return this.lat;
	}

	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}

	public BigDecimal getLng() {
		return this.lng;
	}

	public void setLng(BigDecimal lng) {
		this.lng = lng;
	}

	public byte getPrefered() {
		return this.prefered;
	}

	public void setPrefered(byte prefered) {
		this.prefered = prefered;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	@Override
	public Integer getId() {
		return this.idAddressRoute;
	}

	@Override
	public AddressRoute setId(Integer id) {
		this.idAddressRoute = id;
		return this;
	}

	public Stop getStop() {
		return stop;
	}

	public void setStop(Stop stop) {
		this.stop = stop;
	}
}