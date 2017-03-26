package com.il.sod.db.model.entities;

import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the ServiceCategory database table.
 * 
 */
@Entity
@NamedQuery(name="ServiceCategory.findAll", query="SELECT s FROM ServiceCategory s")
public class ServiceCategory implements IEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idServiceCategory;

	private String description;

	private String name;

	//bi-directional many-to-one association to ServiceType
	@OneToMany(mappedBy="serviceCategory", fetch=FetchType.EAGER)
	private Set<ServiceType> serviceTypes;

	public ServiceCategory() {
	}

	public int getIdServiceCategory() {
		return this.idServiceCategory;
	}

	public void setIdServiceCategory(int idServiceCategory) {
		this.idServiceCategory = idServiceCategory;
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

	public Set<ServiceType> getServiceTypes() {
		return this.serviceTypes;
	}

	public void setServiceTypes(Set<ServiceType> serviceTypes) {
		this.serviceTypes = serviceTypes;
	}

	public ServiceType addServiceType(ServiceType serviceType) {
		getServiceTypes().add(serviceType);
		serviceType.setServiceCategory(this);

		return serviceType;
	}

	public ServiceType removeServiceType(ServiceType serviceType) {
		getServiceTypes().remove(serviceType);
		serviceType.setServiceCategory(null);

		return serviceType;
	}

	@Override
	public Integer getId() {
		return this.idServiceCategory;
	}

	@Override
	public ServiceCategory setId(Integer id) {
		this.idServiceCategory = id;
		return this;
	}

}