package com.il.sod.db.model.entities;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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

	//bi-directional many-to-one association to Stop
	@OneToMany(mappedBy="addressRoute")
	private Set<Stop> stops;

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

	public Set<Stop> getStops() {
		return this.stops;
	}

	public void setStops(Set<Stop> stops) {
		this.stops = stops;
	}

	public Stop addStop(Stop stop) {
		getStops().add(stop);
		stop.setAddressRoute(this);

		return stop;
	}

	public Stop removeStop(Stop stop) {
		getStops().remove(stop);
		stop.setAddressRoute(null);

		return stop;
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
}