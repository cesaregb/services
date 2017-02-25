package com.il.sod.db.model.entities;

import com.google.common.base.Objects;
import org.apache.commons.lang3.builder.ToStringBuilder;

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

	//bi-directional many-to-many association to ProductType
	@ManyToMany(mappedBy = "productTypes", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<ServiceType> serviceTypes;

	public ProductType() {
	}

	public ProductType(String name, String description) {
		this.description = description;
		this.name = name;
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
		if (o == null || getClass() != o.getClass()) return false;
		ProductType that = (ProductType) o;
		return idProductType == that.idProductType &&
				Objects.equal(description, that.description) &&
				Objects.equal(name, that.name);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(idProductType, description, name);
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

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("idProductType", idProductType)
				.append("description", description)
				.append("name", name)
				.append("products", products)
				.toString();
	}
}