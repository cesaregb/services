package com.il.sod.db.model.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * The persistent class for the ProductType database table.
 * 
 */

@Entity
@NamedQuery(name="ProductType.findAll", query="SELECT s FROM ProductType s")
public class ProductType extends SoftDeleteEntity implements IEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idProductType;

	private String description;

	private String name;

	//bi-directional many-to-one association to Product
	@OneToMany(mappedBy="productType", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Set<Product> products;

	//bi-directional many-to-many association to ServiceType
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
			name="ServiceTypeProductType"
			, joinColumns={
			@JoinColumn(name="idProductType")
	}
			, inverseJoinColumns={
			@JoinColumn(name="idServiceType")
	}
	)
	private Set<ServiceType> serviceTypes;

	public ProductType() {
	}

	public int getIdProductType() {
		return this.idProductType;
	}

	public void setIdProductType(int idProductType) {
		this.idProductType = idProductType;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Product> getProducts() {
		return this.products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public Product addProduct(Product product) {
		getProducts().add(product);
		product.setProductType(this);

		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setProductType(null);

		return product;
	}

	public Set<ServiceType> getServiceTypes() {
		if(serviceTypes == null){
			serviceTypes = new HashSet<>();
		}
		return serviceTypes;
	}

	public void setServiceTypes(Set<ServiceType> serviceTypes) {
		this.serviceTypes = serviceTypes;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ProductType)) return false;

		ProductType that = (ProductType) o;

		if (idProductType != that.idProductType) return false;
		if (description != null ? !description.equals(that.description) : that.description != null) return false;
		return name != null ? name.equals(that.name) : that.name == null;

	}

	@Override
	public int hashCode() {
		int result = idProductType;
		result = 31 * result + (description != null ? description.hashCode() : 0);
		result = 31 * result + (name != null ? name.hashCode() : 0);
		return result;
	}

	@Override
	public Integer getId() {
		return this.idProductType;
	}

	@Override
	public ProductType setId(Integer id) {
		this.idProductType = id;
		return this;
	}

}