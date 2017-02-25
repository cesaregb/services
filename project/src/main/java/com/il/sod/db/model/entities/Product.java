package com.il.sod.db.model.entities;

import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the Product database table.
 * 
 */
@Entity
@NamedQuery(name="Product.findAll", query="SELECT s FROM Product s")
public class Product extends SoftDeleteEntity implements IEntity<Integer>{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idProduct;

	private int maxQty;

	private String name;

	private double price;

	//bi-directional many-to-one association to ServiceProduct
	@OneToMany(mappedBy="product", fetch=FetchType.EAGER)
	private Set<ServiceProduct> serviceProducts;

	//bi-directional many-to-one association to ProductType
	@ManyToOne
	@JoinColumn(name="idProductType")
	private ProductType productType;

	public Product() {
	}


	public Product(String name, int maxQty, double price, ProductType productType) {
		this.maxQty = maxQty;
		this.name = name;
		this.price = price;
		this.productType = productType;
	}

	public int getIdProduct() {
		return this.idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
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

	public Set<ServiceProduct> getServiceProducts() {
		return this.serviceProducts;
	}

	public void setServiceProducts(Set<ServiceProduct> serviceProducts) {
		this.serviceProducts = serviceProducts;
	}

	public ServiceProduct addServiceProduct(ServiceProduct serviceProduct) {
		getServiceProducts().add(serviceProduct);
		serviceProduct.setProduct(this);

		return serviceProduct;
	}

	public ServiceProduct removeServiceProduct(ServiceProduct serviceProduct) {
		getServiceProducts().remove(serviceProduct);
		serviceProduct.setProduct(null);

		return serviceProduct;
	}

	public ProductType getProductType() {
		return this.productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	@Override
	public Integer getId() {
		return this.idProduct;
	}

	@Override
	public Product setId(Integer id) {
		this.idProduct = id;
		return this;
	}

}