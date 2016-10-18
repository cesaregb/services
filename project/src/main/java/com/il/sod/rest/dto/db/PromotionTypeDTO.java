package com.il.sod.rest.dto.db;

import java.util.Set;


public class PromotionTypeDTO extends DeletableDTO{
	private int idPromotionType;
	private String description;
	private String name;
	private Set<PromotionDTO> promotions;

	public int getIdPromotionType() {
		return idPromotionType;
	}

	public void setIdPromotionType(int idPromotionType) {
		this.idPromotionType = idPromotionType;
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

	public Set<PromotionDTO> getPromotions() {
		return promotions;
	}

	public void setPromotions(Set<PromotionDTO> promotions) {
		this.promotions = promotions;
	}
}