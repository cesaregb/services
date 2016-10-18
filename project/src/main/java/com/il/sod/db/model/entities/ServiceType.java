package com.il.sod.db.model.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


/**
 * The persistent class for the ServiceType database table.
 *
 */
@Entity
@NamedQuery(name="ServiceType.findAll", query="SELECT s FROM ServiceType s")
public class ServiceType implements IEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idServiceType;

	private String description;

	private String name;

	private double price;

	private Integer time;

	//bi-directional many-to-one association to Service
	@OneToMany(mappedBy="serviceType", fetch=FetchType.EAGER)
	private Set<Service> services;

	//bi-directional many-to-one association to ServiceTypeSpec
	@OneToMany(mappedBy="serviceType", fetch=FetchType.EAGER)
	private Set<ServiceTypeSpec> serviceTypeSpecs;

	//bi-directional many-to-one association to ServiceTypeTask
	@OneToMany(mappedBy="serviceType", fetch=FetchType.EAGER)
	private Set<ServiceTypeTask> serviceTypeTasks;
	
	//bi-directional many-to-one association to ServiceCategory
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idServiceCategory")
	private ServiceCategory serviceCategory;

	//bi-directional many-to-many association to ProductType
	@ManyToMany(mappedBy="serviceTypes", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<ProductType> productTypes;

	private boolean calculator;
	
	public ServiceType() {
	}

	public int getIdServiceType() {
		return this.idServiceType;
	}

	public void setIdServiceType(int idServiceType) {
		this.idServiceType = idServiceType;
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

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Set<Service> getServices() {
		return this.services;
	}

	public void setServices(Set<Service> services) {
		this.services = services;
	}

	public Service addService(Service service) {
		getServices().add(service);
		service.setServiceType(this);

		return service;
	}

	public Service removeService(Service service) {
		getServices().remove(service);
		service.setServiceType(null);

		return service;
	}

	public Set<ServiceTypeSpec> getServiceTypeSpecs() {
		return this.serviceTypeSpecs;
	}

	public void setServiceTypeSpecs(Set<ServiceTypeSpec> serviceTypeSpecs) {
		this.serviceTypeSpecs = serviceTypeSpecs;
	}

	public ServiceTypeSpec addServiceTypeSpec(ServiceTypeSpec serviceTypeSpec) {
		getServiceTypeSpecs().add(serviceTypeSpec);
		serviceTypeSpec.setServiceType(this);

		return serviceTypeSpec;
	}

	public ServiceTypeSpec removeServiceTypeSpec(ServiceTypeSpec serviceTypeSpec) {
		getServiceTypeSpecs().remove(serviceTypeSpec);
		serviceTypeSpec.setServiceType(null);

		return serviceTypeSpec;
	}

	public Set<ServiceTypeTask> getServiceTypeTasks() {
		return this.serviceTypeTasks;
	}

	public void setServiceTypeTasks(Set<ServiceTypeTask> serviceTypeTasks) {
		this.serviceTypeTasks = serviceTypeTasks;
	}

	public ServiceTypeTask addServiceTypeTask(ServiceTypeTask serviceTypeTask) {
		getServiceTypeTasks().add(serviceTypeTask);
		serviceTypeTask.setServiceType(this);

		return serviceTypeTask;
	}

	public ServiceTypeTask removeServiceTypeTask(ServiceTypeTask serviceTypeTask) {
		getServiceTypeTasks().remove(serviceTypeTask);
		serviceTypeTask.setServiceType(null);

		return serviceTypeTask;
	}
	
	@Override
	public Integer getId() {
		return this.idServiceType;
	}

	@Override
	public ServiceType setId(Integer id) {
		this.idServiceType = id;
		return this;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public ServiceCategory getServiceCategory() {
		return this.serviceCategory;
	}

	public void setServiceCategory(ServiceCategory serviceCategory) {
		this.serviceCategory = serviceCategory;
	}

	public Set<ProductType> getProductTypes() {
		if (productTypes == null){
			productTypes = new HashSet<>();
		}
		return productTypes;
	}

	public void setProductTypes(Set<ProductType> productTypes) {
		this.productTypes = productTypes;
	}

	public void addProductType(ProductType productType) {
		getProductTypes().add(productType);
		productType.getServiceTypes().add(this);
	}

	public void removeProductType(ProductType productType) {
		getServiceTypeTasks().remove(productType);
		productType.getServiceTypes().remove(this);
	}

	public boolean isCalculator() {
		return calculator;
	}

	public void setCalculator(boolean calculator) {
		this.calculator = calculator;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ServiceType)) return false;

		ServiceType that = (ServiceType) o;

		if (idServiceType != that.idServiceType) return false;
		if (Double.compare(that.price, price) != 0) return false;
		if (description != null ? !description.equals(that.description) : that.description != null) return false;
		if (name != null ? !name.equals(that.name) : that.name != null) return false;
		return time != null ? time.equals(that.time) : that.time == null;

	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = idServiceType;
		result = 31 * result + (description != null ? description.hashCode() : 0);
		result = 31 * result + (name != null ? name.hashCode() : 0);
		temp = Double.doubleToLongBits(price);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		result = 31 * result + (time != null ? time.hashCode() : 0);
		return result;
	}
}
