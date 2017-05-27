package com.il.sod.db.model.entities;

import com.google.common.base.Objects;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by cesaregb on 5/21/17.
 */
@Entity
public class PriceAdjustmentType extends SoftDeleteEntity implements IEntity<Integer>{
	private int idPriceAdjustmentType;
	private String name;
	private String description;
	private Set<PriceAdjustment> priceAdjustments;

	@Id
	@Column(name = "idPriceAdjustmentType")
	public int getIdPriceAdjustmentType() {
		return idPriceAdjustmentType;
	}

	public void setIdPriceAdjustmentType(int idPriceAdjustmentType) {
		this.idPriceAdjustmentType = idPriceAdjustmentType;
	}

	@Basic
	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Basic
	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		PriceAdjustmentType that = (PriceAdjustmentType) o;
		return idPriceAdjustmentType == that.idPriceAdjustmentType &&
				Objects.equal(name, that.name) &&
				Objects.equal(description, that.description);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(idPriceAdjustmentType, name, description);
	}

	@OneToMany(mappedBy = "priceAdjustmentType")
	public Set<PriceAdjustment> getPriceAdjustments() {
		return priceAdjustments;
	}

	public void setPriceAdjustments(Set<PriceAdjustment> priceAdjustments) {
		this.priceAdjustments = priceAdjustments;
	}

	public void removePriceAdjustment(PriceAdjustment priceAdjustment){
		this.priceAdjustments.remove(priceAdjustment);
	}

	public void addPriceAdjustment(PriceAdjustment priceAdjustment){
		this.priceAdjustments.add(priceAdjustment);
	}

	@Override
	public Integer getId() {
		return this.idPriceAdjustmentType;
	}

	@Override
	public IEntity setId(Integer id) {
		this.idPriceAdjustmentType = id;
		return this;
	}
}
