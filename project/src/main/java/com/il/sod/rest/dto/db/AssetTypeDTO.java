package com.il.sod.rest.dto.db;

import java.util.List;

public class AssetTypeDTO {
	private int idAssetType;
	private String description;
	private String name;
	private List<AssetDTO> assets;
	public int getIdAssetType() {
		return idAssetType;
	}
	public void setIdAssetType(int idAssetType) {
		this.idAssetType = idAssetType;
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
	public List<AssetDTO> getAssets() {
		return assets;
	}
	public void setAssets(List<AssetDTO> assets) {
		this.assets = assets;
	}
}
