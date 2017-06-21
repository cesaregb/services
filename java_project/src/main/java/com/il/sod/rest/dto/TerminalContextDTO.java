package com.il.sod.rest.dto;

import com.il.sod.rest.dto.db.DistanceInfoDTO;
import com.il.sod.rest.dto.db.MenuDTO;
import com.il.sod.rest.dto.db.PriceAdjustmentDTO;

import java.util.List;

/**
 * Created by cesaregb on 6/3/17.
 */
public class TerminalContextDTO {

	private List<MenuDTO> menu;

	private List<DistanceInfoDTO> distances;

	private List<PriceAdjustmentDTO> priceAdjustments;

	public List<MenuDTO> getMenu() {
		return menu;
	}

	public void setMenu(List<MenuDTO> menu) {
		this.menu = menu;
	}

	public List<DistanceInfoDTO> getDistances() {
		return distances;
	}

	public void setDistances(List<DistanceInfoDTO> distances) {
		this.distances = distances;
	}

	public List<PriceAdjustmentDTO> getPriceAdjustments() {
		return priceAdjustments;
	}

	public void setPriceAdjustments(List<PriceAdjustmentDTO> priceAdjustments) {
		this.priceAdjustments = priceAdjustments;
	}
}
