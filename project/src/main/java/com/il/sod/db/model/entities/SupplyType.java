package com.il.sod.db.model.entities;

import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the SupplyType database table.
 *
 */
@Entity
@NamedQuery(name="SupplyType.findAll", query="SELECT p FROM SupplyType p")
public class SupplyType implements IEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idSupplyType;

	private String description;

	private String name;

	//bi-directional many-to-one association to Supply
	@OneToMany(mappedBy="supplyType", fetch=FetchType.EAGER)
	private Set<Supply> supplies;

	public SupplyType() {
	}

	public int getIdSupplyType() {
		return this.idSupplyType;
	}

	public void setIdSupplyType(int idSupplyType) {
		this.idSupplyType = idSupplyType;
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

	public Set<Supply> getSupplies() {
		return this.supplies;
	}

	public void setSupplies(Set<Supply> supplies) {
		this.supplies = supplies;
	}

	public Supply addSupply(Supply supply) {
		getSupplies().add(supply);
		supply.setSupplyType(this);

		return supply;
	}

	public Supply removeSupply(Supply supply) {
		getSupplies().remove(supply);
		supply.setSupplyType(null);

		return supply;
	}

	public Integer getId() {
		return this.idSupplyType;
	}

	@Override
	public SupplyType setId(Integer id) {
		this.idSupplyType = id;
		return this;
	}
}
