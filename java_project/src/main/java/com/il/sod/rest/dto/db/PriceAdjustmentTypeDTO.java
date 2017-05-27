package com.il.sod.rest.dto.db;

import java.util.Set;


public class PriceAdjustmentTypeDTO extends DeletableDTO{
	private int idPriceAdjustmentType;
	private String description;
	private String name;
	private Set<PriceAdjustmentDTO> priceAdjustments;

	public int getIdPriceAdjustmentType() {
		return idPriceAdjustmentType;
	}

	public void setIdPriceAdjustmentType(int idPriceAdjustmentType) {
		this.idPriceAdjustmentType = idPriceAdjustmentType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<PriceAdjustmentDTO> getPriceAdjustments() {
		return priceAdjustments;
	}

	public void setPriceAdjustments(Set<PriceAdjustmentDTO> priceAdjustments) {
		this.priceAdjustments = priceAdjustments;
	}
}