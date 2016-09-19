package com.il.sod.db.model.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * The persistent class for the SubproductType database table.
 * 
 */

@Entity
@NamedQuery(name="SubproductType.findAll", query="SELECT s FROM SubproductType s")
public class SubproductType extends SoftDeleteEntity implements IEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idSubproductType;

	private String description;

	private String name;

	//bi-directional many-to-one association to Subproduct
	@OneToMany(mappedBy="subproductType", fetch=FetchType.EAGER, cascade=CascadeType.ALL, orphanRemoval=true)
	private Set<Subproduct> subproducts;

	//bi-directional many-to-many association to ServiceType
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
			name="ServiceTypeSubproductType"
			, joinColumns={
			@JoinColumn(name="idSubproductType")
	}
			, inverseJoinColumns={
			@JoinColumn(name="idServiceType")
	}
	)
	private Set<ServiceType> serviceTypes;

	public SubproductType() {
	}

	public int getIdSubproductType() {
		return this.idSubproductType;
	}

	public void setIdSubproductType(int idSubproductType) {
		this.idSubproductType = idSubproductType;
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

	public Set<Subproduct> getSubproducts() {
		return this.subproducts;
	}

	public void setSubproducts(Set<Subproduct> subproducts) {
		this.subproducts = subproducts;
	}

	public Subproduct addSubproduct(Subproduct subproduct) {
		getSubproducts().add(subproduct);
		subproduct.setSubproductType(this);

		return subproduct;
	}

	public Subproduct removeSubproduct(Subproduct subproduct) {
		getSubproducts().remove(subproduct);
		subproduct.setSubproductType(null);

		return subproduct;
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
		if (!(o instanceof SubproductType)) return false;

		SubproductType that = (SubproductType) o;

		if (idSubproductType != that.idSubproductType) return false;
		if (description != null ? !description.equals(that.description) : that.description != null) return false;
		return name != null ? name.equals(that.name) : that.name == null;

	}

	@Override
	public int hashCode() {
		int result = idSubproductType;
		result = 31 * result + (description != null ? description.hashCode() : 0);
		result = 31 * result + (name != null ? name.hashCode() : 0);
		return result;
	}

	@Override
	public Integer getId() {
		return this.idSubproductType;
	}

	@Override
	public SubproductType setId(Integer id) {
		this.idSubproductType = id;
		return this;
	}

}