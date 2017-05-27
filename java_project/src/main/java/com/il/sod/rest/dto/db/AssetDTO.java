package com.il.sod.rest.dto.db;

public class AssetDTO extends DeletableDTO {
	private int idAsset;
	private String description;
	private String name;
	private int status;
	private int idAssetType;
	private String assetTypeName;
	
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
	public int getIdAssetType() {
		return idAssetType;
	}
	public void setIdAssetType(int idAssetType) {
		this.idAssetType = idAssetType;
	}
	public String getAssetTypeName() {
		return assetTypeName;
	}
	public void setAssetTypeName(String assetTypeName) {
		this.assetTypeName = assetTypeName;
	}
}
