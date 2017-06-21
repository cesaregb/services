package com.il.sod.rest.dto.db;

import java.math.BigDecimal;
import java.util.Set;

public class StoreDTO {

	private int idStore;
	private int idEmployee;
	private BigDecimal lat;
	private BigDecimal lng;
	private String name;
	private Set<DistanceInfoDTO> distanceInfos;

	public int getIdStore() {
		return idStore;
	}

	public void setIdStore(int idStore) {
		this.idStore = idStore;
	}

	public int getIdEmployee() {
		return idEmployee;
	}

	public void setIdEmployee(int idEmployee) {
		this.idEmployee = idEmployee;
	}

	public BigDecimal getLat() {
		return lat;
	}

	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}

	public BigDecimal getLng() {
		return lng;
	}

	public void setLng(BigDecimal lng) {
		this.lng = lng;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<DistanceInfoDTO> getDistanceInfos() {
		return distanceInfos;
	}

	public void setDistanceInfos(Set<DistanceInfoDTO> distanceInfos) {
		this.distanceInfos = distanceInfos;
	}
}