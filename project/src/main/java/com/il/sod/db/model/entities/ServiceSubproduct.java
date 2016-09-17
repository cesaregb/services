package com.il.sod.db.model.entities;

import javax.persistence.*;


/**
 * The persistent class for the ServiceSubproducts database table.
 * 
 */
@Entity
@Table(name="ServiceSubproducts")
@NamedQuery(name="ServiceSubproduct.findAll", query="SELECT s FROM ServiceSubproduct s")
public class ServiceSubproduct implements IEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idServiceSubproducts;

	private double price;

	private int quantity;

	//bi-directional many-to-one association to Service
	@ManyToOne
	@JoinColumn(name="idService")
	private Service service;

	//bi-directional many-to-one association to Subproduct
	@ManyToOne(fetch= FetchType.EAGER)
	@JoinColumn(name="idSubproduct")
	private Subproduct subproduct;

	public ServiceSubproduct() {
	}

	public int getIdServiceSubproducts() {
		return this.idServiceSubproducts;
	}

	public void setIdServiceSubproducts(int idServiceSubproducts) {
		this.idServiceSubproducts = idServiceSubproducts;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Service getService() {
		return this.service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public Subproduct getSubproduct() {
		return this.subproduct;
	}

	public void setSubproduct(Subproduct subproduct) {
		this.subproduct = subproduct;
	}
	
	@Override
	public Integer getId() {
		return this.idServiceSubproducts;
	}

	@Override
	public ServiceSubproduct setId(Integer id) {
		this.idServiceSubproducts = id;
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ServiceSubproduct)) return false;

		ServiceSubproduct that = (ServiceSubproduct) o;

		if (idServiceSubproducts != that.idServiceSubproducts) return false;
		if (Double.compare(that.price, price) != 0) return false;
		if (quantity != that.quantity) return false;
		if (service != null ? !service.equals(that.service) : that.service != null) return false;
		return subproduct != null ? subproduct.equals(that.subproduct) : that.subproduct == null;

	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = idServiceSubproducts;
		temp = Double.doubleToLongBits(price);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		result = 31 * result + quantity;
		result = 31 * result + (service != null ? service.hashCode() : 0);
		result = 31 * result + (subproduct != null ? subproduct.hashCode() : 0);
		return result;
	}
}