package com.il.sod.db.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the Address database table.
 *
 */
@Entity
@NamedQuery(name="Address.findAll", query="SELECT a FROM Address a")
public class Address implements IEntity<Integer> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idAddress;

	private String address;

	private String address2;

	private String city;

	private String country;

	private String state;
	
	private String zipcode;

	private String comments;

	private boolean prefered;
	
	private boolean factura;
	
	private BigDecimal lat;
	
	private BigDecimal lng;

	//bi-directional many-to-one association to Client
	@ManyToOne
	@JoinColumn(name="idClient")
	@JsonBackReference
	private Client client;
	

	public Address() {
	}

	public int getIdAddress() {
		return this.idAddress;
	}

	public void setIdAddress(int idAddress) {
		this.idAddress = idAddress;
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

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	@Override
	public Integer getId() {
		return this.idAddress;
	}

	@Override
	public Address setId(Integer id) {
		this.idAddress = id;
		return this;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	

	public boolean isPrefered() {
		return prefered;
	}
	public void setPrefered(boolean prefered) {
		this.prefered = prefered;
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

	public boolean isFactura() {
		return factura;
	}

	public void setFactura(boolean factura) {
		this.factura = factura;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public int hashCode(){
		return Objects.hashCode(lat, lng, address, address2, city, country, state, factura, prefered, comments);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Address address1 = (Address) o;
		return idAddress == address1.idAddress &&
				prefered == address1.prefered &&
				factura == address1.factura &&
				Objects.equal(address, address1.address) &&
				Objects.equal(address2, address1.address2) &&
				Objects.equal(city, address1.city) &&
				Objects.equal(country, address1.country) &&
				Objects.equal(state, address1.state) &&
				Objects.equal(zipcode, address1.zipcode) &&
				Objects.equal(comments, address1.comments) &&
				Objects.equal(lat, address1.lat) &&
				Objects.equal(lng, address1.lng);
	}
}
