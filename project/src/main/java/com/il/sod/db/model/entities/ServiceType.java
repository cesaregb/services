package com.il.sod.db.model.entities;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


/**
 * The persistent class for the ServiceType database table.
 */
@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@NamedQuery(name = "ServiceType.findAll", query = "SELECT s FROM ServiceType s")
public class ServiceType implements IEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idServiceType;

	private String description;

	private String name;

	private double price;

	private Integer time;


	//bi-directional many-to-many association to ServiceType
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			name = "ServiceTypeSpecs"
			, joinColumns = {
			@JoinColumn(name = "idServiceType")
	}
			, inverseJoinColumns = {
			@JoinColumn(name = "idSpecs")
	})
	private Set<Spec> specs;

	//bi-directional many-to-one association to Service
	@OneToMany(mappedBy = "serviceType", fetch = FetchType.LAZY)
	private Set<Service> services;

	//bi-directional many-to-one association to ServiceTypeTask
	@OneToMany(mappedBy = "serviceType", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<ServiceTypeTask> serviceTypeTasks;

	//bi-directional many-to-one association to ServiceCategory
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idServiceCategory")
	private ServiceCategory serviceCategory;

	//bi-directional many-to-many association to ServiceType
	@ManyToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			name="ServiceTypeProductType"
			, joinColumns={
			@JoinColumn(name="idServiceType")
	}
			, inverseJoinColumns={
			@JoinColumn(name="idProductType")
	})
	private Set<ProductType> productTypes;

	private boolean calculator;

	public ServiceType() {
	}

	public ServiceType(String name, String description, double price, Integer time, ServiceCategory serviceCategory, boolean calculator) {
		this.description = description;
		this.name = name;
		this.price = price;
		this.time = time;
		this.serviceCategory = serviceCategory;
		this.calculator = calculator;
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

	public Set<Spec> getSpecs() {
		if (specs == null) {
			specs = new HashSet<>();
		}
		return specs;
	}

	public void setSpecs(Set<Spec> specs) {
		this.specs = specs;
	}

	public Spec addSpec(Spec specs) {
		getSpecs().add(specs);
		specs.getServiceTypes().add(this);
		return specs;
	}

	public Spec removeSpec(Spec specs) {
		getSpecs().remove(specs);
		specs.getServiceTypes().remove(this);
		return specs;
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
		if (productTypes == null) {
			productTypes = new HashSet<>();
		}
		return productTypes;
	}

	public void setProductTypes(Set<ProductType> productTypes) {
		this.productTypes = productTypes;
	}

	public ProductType addProductType(ProductType productType) {
		getProductTypes().add(productType);
		productType.getServiceTypes().add(this);
		return productType;
	}

	public ProductType removeProductType(ProductType productType) {
		getProductTypes().remove(productType);
		productType.getServiceTypes().remove(this);
		return productType;
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

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
				.append("idServiceType", idServiceType)
				.append("description", description)
				.append("name", name)
				.append("price", price)
				.append("time", time)
				.append("specs", specs)
				.append("productTypes", productTypes)
				.append("calculator", calculator)
				.toString();
	}

	public boolean containsSpec(int idSpec){
		for (Spec s :
				getSpecs()) {
			if (s.getId() == idSpec){
				return true;
			}
		}
		return false;
	}

	public boolean containsProductType(int idProductType){
		for (ProductType pt :
				getProductTypes()) {
			if (pt.getId() == idProductType){
				return true;
			}
		}
		return false;
	}

	public boolean containsServiceTypeTask(int idServiceTypeTask){
		for (ServiceTypeTask stt :
				getServiceTypeTasks()) {
			if (stt.getId() == idServiceTypeTask) return true;
		}
		return false;
	}
}
