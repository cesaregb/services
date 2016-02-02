package com.il.sod.db.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the AssetType database table.
 * 
 */
@Entity
@NamedQuery(name="AssetType.findAll", query="SELECT a FROM AssetType a")
public class AssetType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idAssetType;

	private String description;

	private String name;

	//bi-directional many-to-one association to Asset
	@OneToMany(mappedBy="assetType")
	private List<Asset> assets;

	public AssetType() {
	}

	public int getIdAssetType() {
		return this.idAssetType;
	}

	public void setIdAssetType(int idAssetType) {
		this.idAssetType = idAssetType;
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

	public List<Asset> getAssets() {
		return this.assets;
	}

	public void setAssets(List<Asset> assets) {
		this.assets = assets;
	}

	public Asset addAsset(Asset asset) {
		getAssets().add(asset);
		asset.setAssetType(this);

		return asset;
	}

	public Asset removeAsset(Asset asset) {
		getAssets().remove(asset);
		asset.setAssetType(null);

		return asset;
	}

}