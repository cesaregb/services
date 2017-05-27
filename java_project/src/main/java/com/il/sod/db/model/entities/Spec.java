package com.il.sod.db.model.entities;

import com.google.common.base.Objects;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


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
	@OneToMany(mappedBy="spec", fetch=FetchType.LAZY)
	private Set<ServiceSpec> serviceSpecs;

	//bi-directional many-to-one association to ServiceTypeSpec
	@ManyToMany(mappedBy="specs", fetch=FetchType.EAGER)
	private Set<ServiceType> serviceTypes;

	//bi-directional many-to-one association to SpecsValue
	@OneToMany(mappedBy="spec", fetch=FetchType.EAGER, cascade=CascadeType.ALL, orphanRemoval = true)
	private Set<SpecsValue> specsValues;

	private boolean optional;

	@Column(name="max_qty")
	private int maxQty;
	
	private int deleted;
	
	public Spec() {
	}

	public Spec(String name, String description, boolean optional, int maxQty) {
		this.description = description;
		this.name = name;
		this.optional = optional;
		this.maxQty = maxQty;
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

	public Set<ServiceType> getServiceTypes() {
		if (serviceTypes == null){
			serviceTypes = new HashSet<>();
		}
		return serviceTypes;
	}

	public void setServiceTypes(Set<ServiceType> serviceTypes) {
		this.serviceTypes = serviceTypes;
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

	public boolean isOptional() {
		return optional;
	}

	public void setOptional(boolean optional) {
		this.optional = optional;
	}

	public int getMaxQty() {
		return maxQty;
	}

	public void setMaxQty(int max_qty) {
		this.maxQty = max_qty;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Spec spec = (Spec) o;
		return idSpecs == spec.idSpecs &&
				optional == spec.optional &&
				maxQty == spec.maxQty &&
				deleted == spec.deleted &&
				Objects.equal(description, spec.description) &&
				Objects.equal(name, spec.name);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(idSpecs, description, name, optional, maxQty, deleted);
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("idSpecs", idSpecs)
				.append("description", description)
				.append("name", name)
				.append("specsValues", specsValues)
				.append("optional", optional)
				.append("maxQty", maxQty)
				.append("deleted", deleted)
				.toString();
	}
}
