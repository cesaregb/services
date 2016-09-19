package com.il.sod.db.model.entities;

import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the Subproduct database table.
 * 
 */
@Entity
@NamedQuery(name="Subproduct.findAll", query="SELECT s FROM Subproduct s")
public class Subproduct extends SoftDeleteEntity implements IEntity<Integer>{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idSubproduct;

	private int maxQty;

	private String name;

	private double price;

	//bi-directional many-to-one association to ServiceSubproduct
	@OneToMany(mappedBy="subproduct", fetch=FetchType.EAGER)
	private Set<ServiceSubproduct> serviceSubproducts;

	//bi-directional many-to-one association to SubproductType
	@ManyToOne
	@JoinColumn(name="idSubproductType")
	private SubproductType subproductType;

	public Subproduct() {
	}

	public int getIdSubproduct() {
		return this.idSubproduct;
	}

	public void setIdSubproduct(int idSubproduct) {
		this.idSubproduct = idSubproduct;
	}

	public int getMaxQty() {
		return this.maxQty;
	}

	public void setMaxQty(int maxQty) {
		this.maxQty = maxQty;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Set<ServiceSubproduct> getServiceSubproducts() {
		return this.serviceSubproducts;
	}

	public void setServiceSubproducts(Set<ServiceSubproduct> serviceSubproducts) {
		this.serviceSubproducts = serviceSubproducts;
	}

	public ServiceSubproduct addServiceSubproduct(ServiceSubproduct serviceSubproduct) {
		getServiceSubproducts().add(serviceSubproduct);
		serviceSubproduct.setSubproduct(this);

		return serviceSubproduct;
	}

	public ServiceSubproduct removeServiceSubproduct(ServiceSubproduct serviceSubproduct) {
		getServiceSubproducts().remove(serviceSubproduct);
		serviceSubproduct.setSubproduct(null);

		return serviceSubproduct;
	}

	public SubproductType getSubproductType() {
		return this.subproductType;
	}

	public void setSubproductType(SubproductType subproductType) {
		this.subproductType = subproductType;
	}

	@Override
	public Integer getId() {
		return this.idSubproduct;
	}

	@Override
	public Subproduct setId(Integer id) {
		this.idSubproduct = id;
		return this;
	}

}