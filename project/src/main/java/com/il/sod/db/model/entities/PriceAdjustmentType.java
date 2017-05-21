package com.il.sod.db.model.entities;

import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the PriceAdjustmentTypeDTO database table.
 * 
 */
@Entity
@NamedQuery(name="PriceAdjustmentType.findAll", query="SELECT p FROM PriceAdjustmentType p")
public class PriceAdjustmentType extends SoftDeleteEntity implements IEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idPriceAdjustmentType;

	private String description;

	private String name;

	//bi-directional many-to-one association to PriceAdjustmentDTO
	@OneToMany(mappedBy="priceAdjustmentType", fetch = FetchType.EAGER)
	private Set<PriceAdjustment> priceAdjustments;

	public PriceAdjustmentType() {
	}

	public int getIdPriceAdjustmentType() {
		return this.idPriceAdjustmentType;
	}

	public void setIdPriceAdjustmentType(int idPriceAdjustmentType) {
		this.idPriceAdjustmentType = idPriceAdjustmentType;
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

	public Set<PriceAdjustment> getPriceAdjustments() {
		return this.priceAdjustments;
	}

	public void setPriceAdjustments(Set<PriceAdjustment> priceAdjustments) {
		this.priceAdjustments = priceAdjustments;
	}

	public PriceAdjustment addPriceAdjustment(PriceAdjustment priceAdjustment) {
		getPriceAdjustments().add(priceAdjustment);
		priceAdjustment.setPriceAdjustmentType(this);

		return priceAdjustment;
	}

	public PriceAdjustment removePriceAdjustment(PriceAdjustment priceAdjustment) {
		getPriceAdjustments().remove(priceAdjustment);
		priceAdjustment.setPriceAdjustmentType(null);

		return priceAdjustment;
	}

	@Override
	public Integer getId() {
		return this.idPriceAdjustmentType;
	}

	@Override
	public PriceAdjustmentType setId(Integer id) {
		this.idPriceAdjustmentType = id;
		return this;
	}
}