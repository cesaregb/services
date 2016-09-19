package com.il.sod.db.model.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;


/**
 * The persistent class for the Stores database table.
 * 
 */
@Entity
@Table(name="Stores")
@NamedQuery(name="Store.findAll", query="SELECT s FROM Store s")
public class Store  implements IEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idStore;

	private int idEmployee;

	private BigDecimal lat;

	private BigDecimal lng;

	private String name;

	//bi-directional many-to-one association to DistanceInfo
	@OneToMany(mappedBy="store", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<DistanceInfo> distanceInfos;

	public Store() {
	}

	public int getIdStore() {
		return idStore;
	}

	public void setIdStore(int idStore) {
		this.idStore = idStore;
	}

	public int getIdEmployee() {
		return this.idEmployee;
	}

	public void setIdEmployee(int idEmployee) {
		this.idEmployee = idEmployee;
	}

	public BigDecimal getLat() {
		return this.lat;
	}

	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}

	public BigDecimal getLng() {
		return this.lng;
	}

	public void setLng(BigDecimal lng) {
		this.lng = lng;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<DistanceInfo> getDistanceInfos() {
		if (this.distanceInfos == null){
			this.distanceInfos = new HashSet<>();
		}
		return this.distanceInfos;
	}

	public void setDistanceInfos(Set<DistanceInfo> distanceInfos) {
		this.distanceInfos = distanceInfos;
	}

	public DistanceInfo addDistanceInfo(DistanceInfo distanceInfo) {
		getDistanceInfos().add(distanceInfo);
		distanceInfo.setStore(this);

		return distanceInfo;
	}

	public DistanceInfo removeDistanceInfo(DistanceInfo distanceInfo) {
		getDistanceInfos().remove(distanceInfo);
		distanceInfo.setStore(null);

		return distanceInfo;
	}

	@Override
	public Integer getId() {
		return this.getIdStore();
	}

	@Override
	public Store setId(Integer id) {
		this.idStore = id;
		return this;
	}
}