package com.il.sod.db.model.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


/**
 * The persistent class for the Asset database table.
 *
 */
@Entity
@NamedQuery(name="Asset.findAll", query="SELECT a FROM Asset a")
public class Asset implements IEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idAsset;

	private String description;

	private String name;

	private int status;

	//bi-directional many-to-one association to AssetType
	@ManyToOne
	@JoinColumn(name="idAssetType")
	private AssetType assetType;

	//bi-directional many-to-one association to AssetTaskOrder
	@OneToMany(mappedBy="asset", fetch=FetchType.EAGER)
	private Set<AssetTaskOrder> assetTaskOrders;

	//bi-directional many-to-one association to AssetTaskService
	@OneToMany(mappedBy="asset", fetch=FetchType.EAGER)
	private Set<AssetTaskService> assetTaskServices;

	public Asset() {
	}

	public int getIdAsset() {
		return this.idAsset;
	}

	public void setIdAsset(int idAsset) {
		this.idAsset = idAsset;
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

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public AssetType getAssetType() {
		return this.assetType;
	}

	public void setAssetType(AssetType assetType) {
		this.assetType = assetType;
	}

	public Set<AssetTaskOrder> getAssetTaskOrders() {
		return this.assetTaskOrders;
	}

	public void setAssetTaskOrders(Set<AssetTaskOrder> assetTaskOrders) {
		this.assetTaskOrders = assetTaskOrders;
	}

	public AssetTaskOrder addAssetTaskOrder(AssetTaskOrder assetTaskOrder) {
		getAssetTaskOrders().add(assetTaskOrder);
		assetTaskOrder.setAsset(this);

		return assetTaskOrder;
	}

	public AssetTaskOrder removeAssetTaskOrder(AssetTaskOrder assetTaskOrder) {
		getAssetTaskOrders().remove(assetTaskOrder);
		assetTaskOrder.setAsset(null);

		return assetTaskOrder;
	}

	public Set<AssetTaskService> getAssetTaskServices() {
		return this.assetTaskServices;
	}

	public void setAssetTaskServices(Set<AssetTaskService> assetTaskServices) {
		this.assetTaskServices = assetTaskServices;
	}

	public AssetTaskService addAssetTaskService(AssetTaskService assetTaskService) {
		getAssetTaskServices().add(assetTaskService);
		assetTaskService.setAsset(this);

		return assetTaskService;
	}

	public AssetTaskService removeAssetTaskService(AssetTaskService assetTaskService) {
		getAssetTaskServices().remove(assetTaskService);
		assetTaskService.setAsset(null);

		return assetTaskService;
	}
	@Override
	public Integer getId() {
		return this.idAsset;
	}

	@Override
	public Asset setId(Integer id) {
		this.idAsset = id;
		return this;
	}
}
