package com.il.sod.rest.dto.db;

import java.util.List;

public class AssetDTO {
	private int idAsset;
	private String description;
	private String name;
	private int status;
	private AssetTypeDTO assetType;
	private List<AssetTaskOrderDTO> assetTaskOrders;
	private List<AssetTaskServiceDTO> assetTaskServices;
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
	public AssetTypeDTO getAssetType() {
		return assetType;
	}
	public void setAssetType(AssetTypeDTO assetType) {
		this.assetType = assetType;
	}
	public List<AssetTaskOrderDTO> getAssetTaskOrders() {
		return assetTaskOrders;
	}
	public void setAssetTaskOrders(List<AssetTaskOrderDTO> assetTaskOrders) {
		this.assetTaskOrders = assetTaskOrders;
	}
	public List<AssetTaskServiceDTO> getAssetTaskServices() {
		return assetTaskServices;
	}
	public void setAssetTaskServices(List<AssetTaskServiceDTO> assetTaskServices) {
		this.assetTaskServices = assetTaskServices;
	}

}
