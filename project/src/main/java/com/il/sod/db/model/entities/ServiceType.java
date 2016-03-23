package com.il.sod.db.model.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import borrame.OrderType;


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
	private List<Service> services;

	//bi-directional many-to-one association to ServiceTypeSpec
	@OneToMany(mappedBy="serviceType", fetch=FetchType.EAGER)
	private List<ServiceTypeSpec> serviceTypeSpecs;

	//bi-directional many-to-one association to ServiceTypeTask
	@OneToMany(mappedBy="serviceType", fetch=FetchType.EAGER)
	private List<ServiceTypeTask> serviceTypeTasks;
	
	//bi-directional many-to-many association to OrderType
	@ManyToMany
	@JoinTable(
		name="ServiceType_has_OrderType"
		, joinColumns={
			@JoinColumn(name="ServiceType_idServiceType")
			}
		, inverseJoinColumns={
			@JoinColumn(name="OrderType_idOrderType")
			}
		)
	private List<OrderType> orderTypes;

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

	public List<Service> getServices() {
		return this.services;
	}

	public void setServices(List<Service> services) {
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

	public List<ServiceTypeSpec> getServiceTypeSpecs() {
		return this.serviceTypeSpecs;
	}

	public void setServiceTypeSpecs(List<ServiceTypeSpec> serviceTypeSpecs) {
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

	public List<ServiceTypeTask> getServiceTypeTasks() {
		return this.serviceTypeTasks;
	}

	public void setServiceTypeTasks(List<ServiceTypeTask> serviceTypeTasks) {
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

	public List<OrderType> getOrderTypes() {
		return orderTypes;
	}

	public void setOrderTypes(List<OrderType> orderTypes) {
		this.orderTypes = orderTypes;
	}
}
