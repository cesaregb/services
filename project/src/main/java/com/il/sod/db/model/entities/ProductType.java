package com.il.sod.db.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ProductType database table.
 * 
 */
@Entity
@NamedQuery(name="ProductType.findAll", query="SELECT p FROM ProductType p")
public class ProductType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idProductType;

	private String description;

	private String name;

	//bi-directional many-to-one association to Product
	@OneToMany(mappedBy="productType")
	private List<Product> products;

	//bi-directional many-to-one association to Spec
	@OneToMany(mappedBy="productType")
	private List<Spec> specs;

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

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
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

	public List<Spec> getSpecs() {
		return this.specs;
	}

	public void setSpecs(List<Spec> specs) {
		this.specs = specs;
	}

	public Spec addSpec(Spec spec) {
		getSpecs().add(spec);
		spec.setProductType(this);

		return spec;
	}

	public Spec removeSpec(Spec spec) {
		getSpecs().remove(spec);
		spec.setProductType(null);

		return spec;
	}

}