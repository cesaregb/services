package com.il.sod.db.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Specs database table.
 * 
 */
@Entity
@Table(name="Specs")
@NamedQuery(name="Spec.findAll", query="SELECT s FROM Spec s")
public class Spec implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idSpecs;

	private String description;

	private String name;

	//bi-directional many-to-one association to ServiceSpec
	@OneToMany(mappedBy="spec", fetch=FetchType.EAGER)
	private List<ServiceSpec> serviceSpecs;

	//bi-directional many-to-one association to ServiceTypeSpec
	@OneToMany(mappedBy="spec", fetch=FetchType.EAGER)
	private List<ServiceTypeSpec> serviceTypeSpecs;

	//bi-directional many-to-one association to ProductType
	@ManyToOne
	@JoinColumn(name="idProductType")
	private ProductType productType;

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

	public List<ServiceSpec> getServiceSpecs() {
		return this.serviceSpecs;
	}

	public void setServiceSpecs(List<ServiceSpec> serviceSpecs) {
		this.serviceSpecs = serviceSpecs;
	}

	public ServiceSpec addServiceSpec(ServiceSpec serviceSpec) {
		getServiceSpecs().add(serviceSpec);
		serviceSpec.setSpec(this);

		return serviceSpec;
	}

	public ServiceSpec removeServiceSpec(ServiceSpec serviceSpec) {
		getServiceSpecs().remove(serviceSpec);
		serviceSpec.setSpec(null);

		return serviceSpec;
	}

	public List<ServiceTypeSpec> getServiceTypeSpecs() {
		return this.serviceTypeSpecs;
	}

	public void setServiceTypeSpecs(List<ServiceTypeSpec> serviceTypeSpecs) {
		this.serviceTypeSpecs = serviceTypeSpecs;
	}

	public ServiceTypeSpec addServiceTypeSpec(ServiceTypeSpec serviceTypeSpec) {
		getServiceTypeSpecs().add(serviceTypeSpec);
		serviceTypeSpec.setSpec(this);

		return serviceTypeSpec;
	}

	public ServiceTypeSpec removeServiceTypeSpec(ServiceTypeSpec serviceTypeSpec) {
		getServiceTypeSpecs().remove(serviceTypeSpec);
		serviceTypeSpec.setSpec(null);

		return serviceTypeSpec;
	}

	public ProductType getProductType() {
		return this.productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

}