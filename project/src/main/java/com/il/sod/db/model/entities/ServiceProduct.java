package com.il.sod.db.model.entities;

import javax.persistence.*;


/**
 * The persistent class for the ServiceProducts database table.
 * 
 */
@Entity
@Table(name="ServiceProducts")
@NamedQuery(name="ServiceProduct.findAll", query="SELECT s FROM ServiceProduct s")
public class ServiceProduct implements IEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idServiceProducts;

	private double price;

	private int quantity;

	//bi-directional many-to-one association to Service
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idService")
	private Service service;

	//bi-directional many-to-one association to Product
	@ManyToOne(fetch= FetchType.EAGER)
	@JoinColumn(name="idProduct")
	private Product product;

	public ServiceProduct() {
	}

	public int getIdServiceProducts() {
		return this.idServiceProducts;
	}

	public void setIdServiceProducts(int idServiceProducts) {
		this.idServiceProducts = idServiceProducts;
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

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	@Override
	public Integer getId() {
		return this.idServiceProducts;
	}

	@Override
	public ServiceProduct setId(Integer id) {
		this.idServiceProducts = id;
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ServiceProduct)) return false;

		ServiceProduct that = (ServiceProduct) o;

		if (idServiceProducts != that.idServiceProducts) return false;
		if (Double.compare(that.price, price) != 0) return false;
		if (quantity != that.quantity) return false;
		if (service != null ? !service.equals(that.service) : that.service != null) return false;
		return product != null ? product.equals(that.product) : that.product == null;

	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = idServiceProducts;
		temp = Double.doubleToLongBits(price);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		result = 31 * result + quantity;
		result = 31 * result + (service != null ? service.hashCode() : 0);
		result = 31 * result + (product != null ? product.hashCode() : 0);
		return result;
	}
}