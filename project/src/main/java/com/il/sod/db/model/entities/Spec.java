package com.il.sod.db.model.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;


/**
 * The persistent class for the Specs database table.
 *
 */
@Entity
@Table(name="Specs")
@NamedQuery(name="Spec.findAll", query="SELECT s FROM Spec s")
public class Spec implements IEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idSpecs;

	private String description;

	private String name;

	//bi-directional many-to-one association to ServiceSpec
	@OneToMany(mappedBy="spec", fetch=FetchType.EAGER)
	private Set<ServiceSpec> serviceSpecs;

	//bi-directional many-to-one association to ServiceTypeSpec
	@OneToMany(mappedBy="spec", fetch=FetchType.EAGER)
	private Set<ServiceTypeSpec> serviceTypeSpecs;
	
	//bi-directional many-to-one association to SpecsValue
	@OneToMany(mappedBy="spec", fetch=FetchType.EAGER, cascade=CascadeType.ALL, orphanRemoval=true)
	private Set<SpecsValue> specsValues;

	private int optional;
	
	private int max_qty;
	
	private boolean primarySpec;
	
	private int deleted;
	
	public Spec() {
	}

	public int getIdSpecs() {
		return this.idSpecs;
	}

	public void setIdSpecs(int idSpecs) {
		this.idSpecs = idSpecs;
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

	public Set<ServiceSpec> getServiceSpecs() {
		return this.serviceSpecs;
	}

	public void setServiceSpecs(Set<ServiceSpec> serviceSpecs) {
		this.serviceSpecs = serviceSpecs;
	}

	public ServiceSpec addServiceSpec(ServiceSpec serviceSpec) {
		if (serviceSpecs == null){
			serviceSpecs = new HashSet<>();
		}
		getServiceSpecs().add(serviceSpec);
		serviceSpec.setSpec(this);

		return serviceSpec;
	}

	public ServiceSpec removeServiceSpec(ServiceSpec serviceSpec) {
		getServiceSpecs().remove(serviceSpec);
		serviceSpec.setSpec(null);

		return serviceSpec;
	}

	public Set<ServiceTypeSpec> getServiceTypeSpecs() {
		return this.serviceTypeSpecs;
	}

	public void setServiceTypeSpecs(Set<ServiceTypeSpec> serviceTypeSpecs) {
		this.serviceTypeSpecs = serviceTypeSpecs;
	}

	public ServiceTypeSpec addServiceTypeSpec(ServiceTypeSpec serviceTypeSpec) {
		if (serviceTypeSpecs == null){
			serviceTypeSpecs = new HashSet<>();
		}
		getServiceTypeSpecs().add(serviceTypeSpec);
		serviceTypeSpec.setSpec(this);

		return serviceTypeSpec;
	}

	public ServiceTypeSpec removeServiceTypeSpec(ServiceTypeSpec serviceTypeSpec) {
		getServiceTypeSpecs().remove(serviceTypeSpec);
		serviceTypeSpec.setSpec(null);

		return serviceTypeSpec;
	}
	
	public Set<SpecsValue> getSpecsValues() {
		return this.specsValues;
	}

	public void setSpecsValues(Set<SpecsValue> specsValues) {
		this.specsValues = specsValues;
	}

	public SpecsValue addSpecsValue(SpecsValue specsValue) {
		getSpecsValues().add(specsValue);
		specsValue.setSpec(this);

		return specsValue;
	}

	public SpecsValue removeSpecsValue(SpecsValue specsValue) {
		getSpecsValues().remove(specsValue);
		specsValue.setSpec(null);
		return specsValue;
	}

	@Override
	public Integer getId() {
		return this.idSpecs;
	}

	@Override
	public Spec setId(Integer id) {
		this.idSpecs = id;
		return this;
	}

	public int getOptional() {
		return optional;
	}

	public void setOptional(int optional) {
		this.optional = optional;
	}

	public int getMax_qty() {
		return max_qty;
	}

	public void setMax_qty(int max_qty) {
		this.max_qty = max_qty;
	}

	public boolean isPrimarySpec() {
		return primarySpec;
	}

	public void setPrimarySpec(boolean primarySpec) {
		this.primarySpec = primarySpec;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
}
