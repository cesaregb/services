package com.il.sod.rest.dto.db;

public class AssetDTO {
	private int idAsset;
	private String description;
	private String name;
	private int status;
	private Integer assetType;
	public int getIdAsset() {
		return idAsset;
	}
	public void setIdAsset(int idAsset) {
		this.idAsset = idAsset;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Integer getAssetType() {
		return assetType;
	}
	public void setAssetType(Integer assetType) {
		this.assetType = assetType;
	}
}
